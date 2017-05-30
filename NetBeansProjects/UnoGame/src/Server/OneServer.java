package Server;

import java.io.*;
import java.net.*;
import java.util.HashMap;

public class OneServer implements Runnable {

    private OneServerThread clients[] = new OneServerThread[50];
    private ServerSocket server = null;
    private Thread thread = null;
    private int clientCount = 0;
    private HashMap<String, Command> commands;
    private Game game;

    public OneServer(int port, Game game) {
        this.game = game;
        registerCommands();
        try {
            System.out.println("Binding to port " + port + ", please wait  ...");
            server = new ServerSocket(port);
            System.out.println("Server started: " + server);
            start();
        } catch (IOException ioe) {
            System.out.println("Can not bind to port " + port + ": " + ioe.getMessage());
        }
    }

    public void run() {
        while (thread != null) {
            try {
                System.out.println("Waiting for a client ...");
                if (clientCount < this.game.getMaxPlayers()) {
                    addThread(server.accept());
                } else {
                    break;
                }
            } catch (IOException ioe) {
                System.out.println("Server accept error: " + ioe);
                stop();
            }
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() {
        if (thread != null) {
            thread.stop();
            thread = null;
        }
    }

    private int findClient(int ID) {
        for (int i = 0; i < clientCount; i++) {
            if (clients[i].getID() == ID) {
                return i;
            }
        }
        return -1;
    }

    public synchronized void handle(int ID, String input) {
        System.out.println("Client:" + input);
        String[] info = input.split("\\|");
        System.out.println(info.toString());
        Command command = commands.get(info[0]);

        if (command != null) {
            command.exec(info, ID);
        } else {
            System.out.println("Command" + info[0] + "not found");

        }

    }

    public synchronized void send(int ID, String data) {
        System.out.println(data);
        clients[findClient(ID)].send(data);
    }

    public synchronized void broadcast(String data, int exclude) {
        for (int i = 0; i < clientCount; i++) {
            clients[i].send(data);
        }
    }

    public synchronized void remove(int ID) {
        int pos = findClient(ID);
        if (pos >= 0) {
            OneServerThread toTerminate = clients[pos];
            System.out.println("Removing client thread " + ID + " at " + pos);
            if (pos < clientCount - 1) {
                for (int i = pos + 1; i < clientCount; i++) {
                    clients[i - 1] = clients[i];
                }
            }
            clientCount--;
            try {
                toTerminate.close();
            } catch (IOException ioe) {
                System.out.println("Error closing thread: " + ioe);
            }
            toTerminate.stop();
        }
    }

    private void addThread(Socket socket) {
        if (clientCount < clients.length) {
            System.out.println("Client accepted: " + socket);
            clients[clientCount] = new OneServerThread(this, socket);
            try {
                clients[clientCount].open();
                clients[clientCount].start();
                clientCount++;
            } catch (IOException ioe) {
                System.out.println("Error opening thread: " + ioe);
            }
        } else {
            System.out.println("Client refused: maximum " + clients.length + " reached.");
        }
    }

    public static void main(String args[]) {
        OneServer server = null;
        Game game = new Game(3);
        server = new OneServer(4444, game);
    }

    public void registerCommands() {
        commands = new HashMap<>();
        commands.put(".quit", new Quit(this, this.game));
        commands.put(".play", new Play(this, this.game));
        commands.put(".throwCard", new ThrowCard(this, this.game));
        commands.put(".pass", new Pass(this, game));
        commands.put(".addCards", new AddCards(this, game));
        commands.put(".one", new One(this, game));
    }

    public int getClientCount() {
        return clientCount;
    }

}

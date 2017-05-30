/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.*;
import java.net.*;
import java.util.HashMap;

public class OneClient {

    private Socket socket = null;
    private Thread thread = null;
    private DataInputStream console = null;
    private DataOutputStream streamOut = null;
    private OneClientThread client = null;
    private HashMap<String, Command> commands;
    private BoardModel model;

    public OneClient(String serverName, int serverPort, BoardModel model) {
        System.out.println(serverPort);
        this.model = model;
        this.commands = new HashMap<>();
        registerCommands();
        System.out.println("Establishing connection. Please wait ...");
        try {
            socket = new Socket(serverName, serverPort);
            System.out.println("Connected: " + socket);
            open();
        } catch (UnknownHostException uhe) {
            System.out.println("Host unknown: " + uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println("Unexpected exception: " + ioe.getMessage());
        }
    }

    public void sendToServer(String data) {
        try {
            streamOut.writeUTF(data);
            System.out.println(data);
            streamOut.flush();
        } catch (IOException ioe) {
            System.out.println("Sending error: " + ioe.getMessage());
            stop();
        }
    }

    public synchronized void handle(String input) {
        System.out.println("Server:" + input);
        String[] info = input.split("\\|");
        Client.Command command = commands.get(info[0]);

        if (command != null) {
            command.exec(info);
        } else {
            System.out.println("Command " + input + "not found");
        }

    }

    public void open() {
        try {
            streamOut = new DataOutputStream(socket.getOutputStream());
            client = new OneClientThread(this, socket);
        } catch (IOException ioe) {
            System.out.println("Error opening output stream: " + ioe);
        }
    }

    public void stop() {
        try {
            if (streamOut != null) {
                streamOut.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ioe) {
            System.out.println("Error closing ...");
        }
        client.close();
        client.stop();
    }

    public void registerCommands() {
        this.commands.put(".accepted", new Accepted(this, model));
        this.commands.put(".status", new Status(this, model));
        this.commands.put(".setActive", new SetActive(this, model));
        this.commands.put(".cardRejected", new CardRejected(this, model));
        this.commands.put(".addCards", new AddCards(this, model));
    }
}

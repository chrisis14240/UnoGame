/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Server.OneServer;

/**
 *
 * @author chris
 */

public class Quit extends Command {
    
    public Quit(OneServer server, Game game) {
        super(server, game);
    }

    @Override
    public void exec(String[] info, int clientID) {
        server.send(clientID, ".quit");
        server.remove(clientID);
    }
}

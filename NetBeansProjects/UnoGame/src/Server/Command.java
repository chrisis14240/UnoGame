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
public abstract class Command {
    OneServer server;
    Game game;
    
    public Command(OneServer server, Game game) {
        this.server = server;
        this.game = game;
    }

    public abstract void exec(String[] info, int ID);
}


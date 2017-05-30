/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

/**
 *
 * @author chris
 */
public abstract class Command {
    protected OneClient client; 
    protected BoardModel model;
    
    public Command(OneClient client, BoardModel model) {
        this.client = client;
        this.model = model;
    }
    
    public abstract void exec(String[] info);
    
}

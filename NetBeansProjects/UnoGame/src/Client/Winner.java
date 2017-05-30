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
public class Winner extends Command {

    public Winner(OneClient client, BoardModel model) {
        super(client, model);
    }

    @Override
    public void exec(String[] info) {
        model.setWinnerName(info[1]);
    }

}

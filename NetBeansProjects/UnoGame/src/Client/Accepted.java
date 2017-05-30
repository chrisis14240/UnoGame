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
public class Accepted extends Command {

    public Accepted(OneClient client, BoardModel model) {
        super(client, model);
    }

    @Override
    public void exec(String[] info) {
        // ".accepted|id|5,Yellow|5,Yellow"
        model.setUid(Integer.parseInt(info[1]));
        for (int i = 2; i < info.length; i++) {
            String[] cardInfo = info[i].split(",");
            model.addCard(Integer.parseInt(cardInfo[0]), cardInfo[1]);
        }

    }

}

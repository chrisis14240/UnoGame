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
public class AddCards extends Command {

    public AddCards(OneClient client, BoardModel model) {
        super(client, model);
    }

    @Override
    public void exec(String[] info) {
        for (int i = 1; i < info.length; i++) {
            String[] cardInfo = info[i].split(",");
            model.addCard(Integer.parseInt(cardInfo[0]), cardInfo[1]);
        }
    }
}

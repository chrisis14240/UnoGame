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
public class CardRejected extends Command {

    public CardRejected(OneClient client, BoardModel model) {
        super(client, model);
    }

    @Override
    public void exec(String[] info) {
        String[] cardInfo = info[1].split(",");
        model.addCard(Integer.parseInt(cardInfo[0]), cardInfo[1]);
        model.repaint();
    }

}

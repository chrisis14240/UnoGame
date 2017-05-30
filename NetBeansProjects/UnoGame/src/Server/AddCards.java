/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author chris
 */
public class AddCards extends Command {

    public AddCards(OneServer server, Game game) {
        super(server, game);
    }

    @Override
    public void exec(String[] info, int ID) {
        Card card = game.getDeck().pop();
        game.getPlayer(ID).addCard(card);
        game.getPlayer(ID).setOne(false);
        String data = ".addCards|" + card.toString();
        server.send(ID, data);
    }

}

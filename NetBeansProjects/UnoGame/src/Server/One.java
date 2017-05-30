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
public class One extends Command {

    public One(OneServer server, Game game) {
        super(server, game);
    }

    @Override
    public void exec(String[] info, int ID) {
        if (game.findPlayer(ID).HaveOne()) {
            game.findPlayer(ID).setOne(true);
        } else {
            Card card = game.getDeck().pop();
            game.findPlayer(ID).addCard(card);
            server.send(ID, ".addCards|" + card.toString());
        }
    }
}

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
public class Acusse extends Command {

    public Acusse(OneServer server, Game game) {
        super(server, game);
    }

    @Override
    public void exec(String[] info, int ID) {
        for (int i = 0; i < game.getPlayers().size(); i++) {
            Player player = game.getPlayers().get(i);
            if (player.getUid() == ID) {
                continue;
            }

            if (!player.isOne() && player.HaveOne()) {
                for (int j = 0; j < 2; j++) {
                    Card card = game.getDeck().pop();
                    player.addCard(card);
                    server.send(ID, ".addCards|" + card.toString());
                }

            }
            server.broadcast(game.toString(), -1);
        }

    }

}

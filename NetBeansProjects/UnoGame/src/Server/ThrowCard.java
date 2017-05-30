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
public class ThrowCard extends Command {

    public ThrowCard(OneServer server, Game game) {
        super(server, game);
    }

    @Override
    public void exec(String[] info, int ID) {
        System.out.println("ThrowCard");
        String[] cardInfo = info[1].split(",");
        Card card = new Card(Integer.parseInt(cardInfo[0]), cardInfo[1]);
        if (!game.drawCard(card, ID)) {
            server.send(ID, ".cardRejected|" + card.toString());
            server.send(ID, ".addCards|" + game.getDeck().pop().toString());
        }

        if (game.getAddedCards().size() != 0) {
            String cardsInfo = "";
            for (int i = 0; i < game.getAddedCards().size(); i++) {
                cardsInfo += "|" + game.getAddedCards().get(i).toString();
            }
            System.out.println(cardInfo);
            server.send(game.prevPlayer().getUid(), ".addCards" + cardsInfo);
            game.resetAddeCards();
            server.broadcast(".setActive|" + game.getActivePlayer().getUid(), -1);
        }

        server.broadcast(".status|" + game.toString(), -1);
        server.broadcast(".setActive|" + game.getActivePlayer().getUid(), -1);

        if (game.isWinner()) {
            server.broadcast(".winner|" + game.prevPlayer().getName(), -1);
        }
    }

}

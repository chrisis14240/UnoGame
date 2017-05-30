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
public class Play extends Command {

    public Play(OneServer server, Game game) {
        super(server, game);
    }

    @Override
    public void exec(String[] info, int ID) {
        System.out.println("Entro!!");
        Player player = game.addPlayer(info[1], ID);
        String accepted = ".accepted|"+ String.valueOf(ID);

        for (int i = 0; i < player.getHand().size(); i++) {
            Card card = player.getHand().get(i);
            accepted += "|" + card.getNumber() + "," + card.getColor();
        }

        server.send(ID, accepted);
        System.out.println(accepted);

        if (this.server.getClientCount() == this.game.getMaxPlayers()) {
            game.initCurrentCard();
            String status = ".status|" + game.toString();
            server.broadcast(status, -1);
            this.server.broadcast(".setActive|"  + this.game.getPlayers().get(0).getUid(), -1);
        } else {
            String status = ".status|" + game.toString();
            server.broadcast(status, -1);
        }
    }

}

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
public class Status extends Command {

    public Status(OneClient client, BoardModel model) {
        super(client, model);
    }

    @Override
    public void exec(String[] info) {
        // ".status|dir|Blue,7,+2|name,nCards,uid|player2,5"

        model.setDir(Boolean.parseBoolean(info[1]));

        String[] cardInfo = info[2].split(",");
        if (cardInfo.length > 1) {
            Card card = new Card(null, Integer.parseInt(cardInfo[0]), cardInfo[1]);
            model.setCurrentCard(card);
        }

        model.resetPlayerList();

        for (int i = 3; i < info.length; i++) {
            String[] playerInfo = info[i].split(",");
            Player player = new Player(
                    playerInfo[0],
                    Integer.parseInt(playerInfo[1]),
                    Integer.parseInt(playerInfo[2]),
                    Boolean.parseBoolean(playerInfo[3])
            );

            if (player.getUid() != model.getUid()) {
                model.addPlayer(player);
            }
        }

        model.repaint();
    }

}

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
public class SetActive extends Command {

    public SetActive(OneClient client, BoardModel model) {
        super(client, model);
    }

    @Override
    public void exec(String[] info) {

        model.setActive(false);
        if (model.getUid() == Integer.parseInt(info[1])) {
            model.setActive(true);
        }
        for (int i = 0; i < model.getPlayers().size(); i++) {
            Player player = model.getPlayers().get(i);
            player.setActive(player.getUid() == Integer.parseInt(info[1]));

        }
        model.stopWaiting();
        model.getButtons().resetButtons();
        model.repaint();
    }

}

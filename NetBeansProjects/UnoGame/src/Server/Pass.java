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
public class Pass extends Command {

    public Pass(OneServer server, Game game) {
        super(server, game);
    }

    @Override
    public void exec(String[] info, int ID) {
        game.nextTurn();
        server.broadcast(".status|" + game.toString(), -1);
        server.broadcast(".setActive|" + game.getActivePlayer().getUid(), -1);
    }

}

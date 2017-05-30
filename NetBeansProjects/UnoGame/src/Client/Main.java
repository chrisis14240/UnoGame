/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author chris
 */
public class Main {

    public static void main(String args[]) {

        String name = JOptionPane.showInputDialog(null, "Input your name");
//        String server = JOptionPane.showInputDialog(null,"Input the server", "localhost");
//        String port = JOptionPane.showInputDialog(null,"Input the port", "44444");

        JFrame frame = new JFrame("One - " + name);
        frame.setLayout(new BorderLayout());

        BoardModel judge = new BoardModel(frame);
        OneClient client = new OneClient("localhost", 4444, judge);
        judge.setClient(client);
        ButtonsPanel buttons = new ButtonsPanel(judge);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2,
                dim.height / 2 - frame.getSize().height / 2);

        PlayerHand playerHand = new PlayerHand(judge);
        playerHand.initCards();

        judge.setPlayerHand(playerHand);

        ControlPane controller = new ControlPane(buttons, playerHand);
        Board table = new Board(judge);
        judge.setBoard(table);
        judge.setButtons(buttons);

        frame.add(table, BorderLayout.CENTER);
        frame.add(controller, BorderLayout.SOUTH);
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        client.sendToServer(".play|" + name);
    }

}

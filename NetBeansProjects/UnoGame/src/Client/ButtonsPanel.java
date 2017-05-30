/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author chris
 */
public class ButtonsPanel extends JPanel {

    private BoardModel judge;
    private JButton pass;
    private JButton take;

    public ButtonsPanel(BoardModel judge) {

        this.setPreferredSize(new Dimension(200, 100));
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.judge = judge;

        JButton oneButton = new Buttons("UNO", this);
        JButton passButton = new Buttons("PASS", this);
        JButton takeCardButton = new Buttons("TAKE", this);
        JButton quitButton = new Buttons("QUIT", this);
        JButton acusse = new Buttons("ACUSSE", this);

        this.pass = passButton;
        this.take = takeCardButton;

        passButton.setEnabled(false);
        passButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                judge.getClient().sendToServer(".pass");
            }
        });

        oneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                judge.getClient().sendToServer(".one");
            }
        });

        takeCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!judge.isActive()) {
                    JOptionPane.showMessageDialog(null, "Hey! it's not your turn, please wait");
                    return;
                } else {
                    judge.getClient().sendToServer(".addCards");
                    passButton.setEnabled(true);
                    takeCardButton.setEnabled(false);
                }

            }
        });

        acusse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                judge.getClient().sendToServer(".acusse");
            }
        });

        this.add(oneButton);
        this.add(passButton);
        this.add(takeCardButton);
        this.add(acusse);
    }

    public void resetButtons() {
        this.pass.setEnabled(false);
        this.take.setEnabled(true);
    }

}

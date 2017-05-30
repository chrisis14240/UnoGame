/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author chris
 */
public class ControlPane extends JPanel {

    private BorderLayout layout;

    public ControlPane(JPanel buttons, PlayerHand hand) {
        Color color = new Color(242, 242, 242);
        buttons.setBackground(color);
        hand.setBackground(color);
        this.layout = new BorderLayout();
        this.setLayout(this.layout);

        JScrollPane scroll = new JScrollPane(hand);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scroll.setBackground(color);

        this.add(buttons, BorderLayout.WEST);
        this.add(scroll, BorderLayout.CENTER);
    }

}

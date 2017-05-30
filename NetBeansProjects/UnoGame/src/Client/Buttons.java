/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author chris
 */
public class Buttons extends JButton {

    private String label;
    private BoardModel model;

    public Buttons(String label, JPanel panel) {
        super(label);
        this.label = label;

        setBackground(Color.lightGray);
        setFocusable(false);

        this.setMaximumSize(new Dimension(200, 150));
        /*
     These statements enlarge the button so that it
     becomes a circle rather than an oval.
         */ /*
     This call causes the JButton not to paint the background.
     This allows us to paint a round background.
         */
        setContentAreaFilled(false);
    }
}

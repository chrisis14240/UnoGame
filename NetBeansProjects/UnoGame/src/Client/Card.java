/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author chris
 */
public class Card extends JPanel implements MouseListener {

    private PlayerHand playerHand;
    private int number;
    private String color;

    public Card(PlayerHand hand, int number, String color) {
        this.number = number;
        this.color = color;
        this.playerHand = hand;

        this.addMouseListener(this);
        this.setPreferredSize(new Dimension(140, 220));
        this.setMaximumSize(new Dimension(140, 220));
        this.setBackground(this.systemColor());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Font font = new Font("Serif", Font.BOLD, 50);
        g.setColor(Color.WHITE);
        g.setFont(font);

        super.paintComponent(g);

        g.fillRect(5, 5, 130, 210);
        g.setColor(systemColor());
        g.fillRect(10, 10, 120, 200);
        g.setColor(Color.WHITE);
        int[] x = {10, 135, 135, 10};
        int[] y = {100, 10, 130, 210};
        g.fillPolygon(x, y, 4);
        g.setColor(systemColor());

        g.translate(getSize().width / 2, getSize().height / 2);
        String msg = "";

        if (this.number == 10) {
            msg = "+2";
        } else if (this.number == 11) {
            msg = "✘";
        } else if (this.number == 12) {
            msg = "↑↓";
        } else if (this.number == 13) {
            msg = "+4";
        } else if (this.number == 14) {
            g.setColor(Color.red);
            g.fillArc(-40, -40, 80, 80, 0, 90);
            g.setColor(Color.blue);
            g.fillArc(-40, -40, 80, 80, 90, 90);
            g.setColor(Color.green);
            g.fillArc(-40, -40, 80, 80, 180, 90);
            g.setColor(Color.yellow);
            g.fillArc(-40, -40, 80, 80, 270, 90);

        } else {
            msg = String.valueOf(this.number);
        }

        if (!msg.equals("")) {
            FontMetrics metric = g.getFontMetrics();
            g.drawString(msg, (int) -(metric.stringWidth(msg) / 2), (int) (metric.getHeight() / 4));
        }

    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        System.out.println(this.getBackground().toString());
        this.playerHand.resetCards(this);
//        this.playerHand.getBoardModel().setCurrentCard(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public String toString() {
        return String.valueOf(this.number) + "," + this.color;
    }

    public Color systemColor() {
        Color nColor;

        switch (this.color) {
            case "Red":
                nColor = Color.RED;
                break;
            case "Yellow":
                nColor = Color.YELLOW;
                break;
            case "Blue":
                nColor = Color.BLUE;
                break;
            case "Green":
                nColor = Color.GREEN;
                break;
            default:
                nColor = Color.BLACK;
        }

        return nColor;
    }

    public int getNumber() {
        return number;
    }

    public void setColor(String color) {
        this.color = color;
    }

}

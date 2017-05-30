package Client;

import java.awt.*;
import javax.swing.*;

class Board extends JPanel {

    private int total = 3;
    private int center;
    private BoardModel board;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font font = new Font("Serif", Font.BOLD, 25);
        g.setFont(font);
        g.setColor(Color.WHITE);
        this.center = getSize().width / 2;
        g.translate(getSize().width / 2, getSize().height / 2);
        if (this.board.isWaiting()) {
            drawMessage(g, "Waiting for other players ...");
            return;
        }

        for (int i = 0; i < this.board.getPlayers().size(); i++) {
            Player player = this.board.getPlayers().get(i);
            this.drawPlayer(g, i, player);
        }
        drawDir(g, this.board.getDir());

        if (board.getCurrentCard() != null) {
            drawCard(g, board.getCurrentCard().getNumber(), board.getCurrentCard().systemColor());
        }

        if (board.getWinnerName() != null) {
            drawMessage(g, "Winner: " + board.getWinnerName());
        }

    }

    public Board(BoardModel model) {
        this.setBackground(new Color(19, 46, 73));
        this.board = model;
    }

//    public void drawPlayer(Graphics g, int pos, Player player) {
//
//        Color oldColor = g.getColor();
//        double b = (getSize().width * 0.85) - (getSize().width / 2);
//        double a = (getSize().height * 0.85) - (getSize().height / 2);
//        double delta = 180.0 / (total - 1);
//        double teta = (delta * (pos + 1) * (Math.PI / 180));
//
//        int x = (int) (r(teta, a, b) * Math.sin(teta));
//        int y = (int) (int) (r(teta, a, b) * Math.cos(teta));
//
//        g.fillOval(x + 50, y - 100, 50, 50);
//
//        g.drawString(text, x, y);
//
//        g.setColor(oldColor);
//    }
    public void drawPlayer(Graphics g, int pos, Player player) {
        g.setFont(g.getFont().deriveFont(25.0f));
        double b = (getSize().width * 0.85) - (getSize().width / 2);
        double a = (getSize().height * 0.85) - (getSize().height / 2);
        double delta = 180.0 / (total - 1);
        double teta = (delta * (pos + 1) * (Math.PI / 180));

        int x = (int) (r(teta, a, b) * Math.sin(teta));
        int y = (int) (int) (r(teta, a, b) * Math.cos(teta));

        FontMetrics metric = g.getFontMetrics();

        String text = "";
        if (player.getActive()) {
            text += "* ";
        }

        text += player.getName() + " : " + Integer.toString(player.getnCards());

        if (player.isOne()) {
            g.setColor(Color.red);
        }

        g.drawString(text, x - (int) (metric.stringWidth(text) / 2), y + (int) (metric.getHeight() / 4));

        g.fillOval(x - 22, y - (int) (metric.getHeight() * 2) - 20, 45, 45);
        g.fillRect(x - 26, y - (int) (metric.getHeight() * 2) + 12, 53, 30);
    }

    public void drawDir(Graphics g, boolean dir) {
        Font font = new Font("Serif", Font.BOLD, 50);
        g.setFont(font);

        if (dir) {
            g.drawString("⟲", (int) (getSize().width * 0.1), (int) (-getSize().height * 0.10));
        } else {
            g.drawString("⟳", (int) (getSize().width * 0.1), (int) (-getSize().height * 0.10));
        }
    }

    public void drawCard(Graphics g, int number, Color color) {
        Color oldColor = g.getColor();
        Font oldFont = g.getFont();

        Font font = new Font("Serif", Font.BOLD, 50);
        g.setColor(Color.WHITE);
        g.setFont(font);
        int w = 140;
        int h = 220;
        g.translate(-(w / 2), -(h / 2));

        g.fillRect(5, 5, 130, 210);
        g.setColor(color);
        g.fillRect(10, 10, 120, 200);
        g.setColor(Color.WHITE);
        int[] x = {10, 135, 135, 10};
        int[] y = {100, 10, 130, 210};
        g.fillPolygon(x, y, 4);
        g.setColor(color);

        g.translate((w / 2), (h / 2));
//        g.translate((getSize().width / 2), (getSize().height / 2));
        String msg = "";

        if (number == 10) {
            msg = "+2";
        } else if (number == 11) {
            msg = "✘";
        } else if (number == 12) {
            msg = "↑↓";
        } else if (number == 13) {
            msg = "+4";
        } else if (number == 14) {
            g.setColor(Color.red);
            g.fillArc(-40, -40, 80, 80, 0, 90);
            g.setColor(Color.blue);
            g.fillArc(-40, -40, 80, 80, 90, 90);
            g.setColor(Color.green);
            g.fillArc(-40, -40, 80, 80, 180, 90);
            g.setColor(Color.yellow);
            g.fillArc(-40, -40, 80, 80, 270, 90);

        } else {
            msg = String.valueOf(number);
        }

        if (!msg.equals("")) {
            FontMetrics metric = g.getFontMetrics();
            g.drawString(msg, (int) -(metric.stringWidth(msg) / 2), (int) (metric.getHeight() / 4));
        }

        FontMetrics metric = g.getFontMetrics();
        g.drawString(msg, (int) -(metric.stringWidth(msg) / 2), (int) (metric.getHeight() / 4));

        g.setColor(oldColor);
        g.setFont(oldFont);

    }

    private double r(double teta, double a, double b) {
        return 1 / Math.sqrt((Math.pow(Math.cos(teta), 2) / Math.pow(a, 2)) + (Math.pow(Math.sin(teta), 2) / Math.pow(b, 2)));
    }

    public void drawMessage(Graphics g, String msg) {
        Font font = new Font("Serif", Font.BOLD, 50);
        g.setFont(font);
        FontMetrics metric = g.getFontMetrics();
        g.drawString(msg, (int) -(metric.stringWidth(msg) / 2), (int) (metric.getHeight() / 4));
    }
}

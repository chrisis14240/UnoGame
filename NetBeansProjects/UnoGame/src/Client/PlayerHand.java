/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.Color;
import java.awt.Dialog;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author chrisis14240
 */
public class PlayerHand extends Box {

    private int nCards;
    private BoxLayout layout;
    private int xPosition;
    private BoardModel boardModel;
    private ArrayList<Card> cards;
    private ArrayList<Color> colors;

    public PlayerHand(BoardModel model) {

        super(BoxLayout.X_AXIS);
        this.nCards = 10;
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.boardModel = model;

        this.cards = new ArrayList<>();
        this.colors = new ArrayList<>();

        this.colors.add(Color.red);
        this.colors.add(Color.blue);
        this.colors.add(Color.green);
        this.colors.add(Color.yellow);
    }

    public void initCards() {
        for (int i = 0; i < this.cards.size(); i++) {
            this.add(cards.get(i));
        }
    }

    public void resetCards(Card card) {
        if (!boardModel.isActive()) {
            JOptionPane.showMessageDialog(null, "Hey it's not your turn!");
            return;
        }

        if (card.getNumber() == 14 || card.getNumber() == 13) {
            ColorPicker dialog;
            try {
                dialog = new ColorPicker();
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
                dialog.setVisible(true);
                card.setColor(dialog.getSelectedColor());
                boardModel.repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.boardModel.setThrownCard(card);
        this.remove(card);
        this.cards.remove(card);

        this.revalidate();
        this.repaint();
    }

    public void setnCards(int nCards) {
        this.nCards = nCards;
    }

    public int getnCards() {
        return nCards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public BoardModel getBoardModel() {
        return boardModel;
    }

    public void addCard(int number, String color) {
        Card card = new Card(this, number, color);
        this.cards.add(card);
        this.add(card);

        this.revalidate();
        this.repaint();
    }
}

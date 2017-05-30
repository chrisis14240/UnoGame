/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.ArrayList;

/**
 *
 * @author Invitado1
 */
public class Player {

    private Game game;
    private String name;
    private ArrayList<Card> hand;
    private int nCards;
    private boolean one;
    private boolean myTurn;
    private int uid;

    public Player(Game game, String name, int uid) {
        this.game = game;
        this.name = name;
        this.one = false;
        this.hand = new ArrayList<>();
        this.uid = uid;
    }

    public void addCard(Card card) {
        this.hand.add(card);
    }

    public void drawCard(Card card) {
        this.hand.remove(card);
        this.nCards = this.hand.size();
    }

    public boolean HaveOne() {
        return (this.getNCards() == 1);
    }

    public void printCards() {
        this.hand.forEach(card -> System.out.println(card));
    }

    public int getNCards() {
        return this.hand.size();
    }

    public String chooseColor(String Color) {
        return Color;
    }

    public ArrayList< Card> getHand() {
        return hand;
    }

    public void setOne(boolean one) {
        this.one = one;
    }

    public boolean isOne() {
        return one;
    }

    public void setTurn(boolean turn) {
        this.myTurn = turn;
    }

    public String getName() {
        return name;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String toString() {
        return this.getName() + "," + String.valueOf(this.getNCards()) + "," + String.valueOf(this.getUid() + "," + this.one);
    }

}

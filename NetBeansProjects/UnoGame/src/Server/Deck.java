/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Invitado1
 */
public class Deck {

    private ArrayList<Card> Cards;
    private int nCards;

    public Deck() {
        this.nCards = 112;
        this.Cards = new ArrayList<>();

        for (int i = 0; i < this.nCards; i++) {
            this.Cards.add(new Card());
        }

        int y;
        for (int i = 0; i < 8; i++) {

            for (int k = 0; k < 10; k++) {
                y = k + (10 * i);
                this.Cards.get(k + (10 * i)).setNumber(k);
                if (y < 20 && y > -1) {
                    this.Cards.get(k + (10 * i)).setColor("Yellow");
                }
                if (y < 40 && y > 19) {
                    this.Cards.get(k + (10 * i)).setColor("Green");
                }
                if (y < 60 && y > 39) {
                    this.Cards.get(k + (10 * i)).setColor("Red");
                }
                if (y < 80 && y > 59) {
                    this.Cards.get(k + (10 * i)).setColor("Blue");
                }

            }

        }
        for (int i = 0; i < 6; i++) {
            this.Cards.get(80 + i).setColor("Yellow");
            this.Cards.get(86 + i).setColor("Green");
            this.Cards.get(92 + i).setColor("Red");
            this.Cards.get(98 + i).setColor("Blue");
        }
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 2; i++) {

                this.Cards.get(80 + (6 * j) + i).setAction("+2");
                this.Cards.get(82 + (6 * j) + i).setAction("Skip");
                this.Cards.get(84 + (6 * j) + i).setAction("ChangeDirection");
                this.Cards.get(80 + (6 * j) + i).setNumber(10);
                this.Cards.get(82 + (6 * j) + i).setNumber(11);
                this.Cards.get(84 + (6 * j) + i).setNumber(12);
            }
        }
        for (int i = 0; i < 8; i++) {
            this.Cards.get(104 + i).setColor("WildCard");
            if (i < 4) {
                this.Cards.get(104 + i).setAction("+4");
                this.Cards.get(104 + i).setNumber(Card.PLUS_FOUR);
            } else {
                this.Cards.get(104 + i).setAction("ChangeColor");
                this.Cards.get(104 + i).setNumber(Card.CHANGECOLOR);
            }
        }

        for (int i = 0; i < 3; i++) {
            Collections.shuffle(this.Cards);
        }

    }

    public void imprimir() {
        for (int i = 0; i < 112; i++) {
            System.out.println(i + " = " + this.Cards.get(i).getNumber() + " " + this.Cards.get(i).getColor() + " " + this.Cards.get(i).getAction());
        }

    }

    public ArrayList<Card> getCards() {
        return Cards;
    }

    public Card pop() {
        Card card = this.Cards.get(0);
        this.Cards.remove(0);
        return card;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author chrisis14240
 */
public class Game {

    private Scanner sc;

    private ArrayList<Player> players;
    private int nPlayers;
    private Deck deck;
    private Card currentCard;
    private String color;
    private int turn;
    private boolean dir;
    private boolean isWinner;
    private int maxPlayers;
    private ArrayList<Card> addedCards;

    public Game(int maxPlayers) {
        this.maxPlayers = maxPlayers;
        this.dir = true;
        this.deck = new Deck();
        this.players = new ArrayList<>();
        this.turn = 0;
        this.sc = new Scanner(System.in);
        this.addedCards = new ArrayList<>();
    }

    public Player addPlayer(String name, int uid) {
        if (this.players.size() < this.maxPlayers) {
            Player player = new Player(this, name, uid);
            this.players.add(player);

            for (int i = 0; i < 7; i++) {
                player.addCard(this.deck.pop());
            }
            return player;
        }
        return null;
    }

    public void changeDirection() {
        dir = !dir;
    }

    public boolean canBePlayed(Card card) {
        return this.currentCard.getColor().equals(card.getColor())
                || this.currentCard.getNumber() == card.getNumber()
                || card.isWildCard();
    }

    public void setCurrentCard(Card card) {
        if (card.getAction() == "Number" && !card.getColor().equals(this.currentCard.getColor())) {
            this.color = card.getColor();
        } else {
            this.cardAction(card);
        }
        this.currentCard = card;
    }

    public Player findPlayer(int uid) {
        for (int i = 0; i < this.players.size(); i++) {
            if (this.players.get(i).getUid() == uid) {
                return this.players.get(i);
            }
        }
        return null;
    }

    public boolean drawCard(Card card, int uid) {
        if (canBePlayed(card)) {
            this.findPlayer(uid).drawCard(card);
            this.setCurrentCard(card);
            this.nextTurn();
            return true;
        }

        this.nextTurn();
        return false;
    }

    public void cardAction(Card card) {
        if (card.getNumber() == Card.PLUS_TWO) {
            for (int i = 0; i < 2; i++) {
                Card addedCard = this.deck.pop();
                this.addedCards.add(addedCard);
                this.nextPlayer().addCard(addedCard);
                this.nextPlayer().setOne(false);
            }
            this.nextTurn();
        } else if (card.getNumber() == Card.PLUS_FOUR) {
            for (int i = 0; i < 4; i++) {
                Card addedCard = this.deck.pop();
                this.addedCards.add(addedCard);
                this.nextPlayer().addCard(addedCard);
                this.color = card.getColor();
                this.nextPlayer().setOne(false);
            }
            this.nextTurn();
        } else if (card.getNumber() == Card.CHANGECOLOR) {
            this.color = card.getColor();
        } else if (card.getNumber() == Card.CHANGEDIR) {
            this.changeDirection();
        } else if (card.getNumber() == Card.SKIP) {
            this.nextTurn();
        }
    }

    public void nextTurn() {
        int delta = this.dir ? 1 : -1;
        this.turn = (this.turn + delta) % this.players.size();

        if (this.turn < 0) {
            this.turn = this.players.size() + this.turn;
        }
        System.out.println(this.turn);
    }

    public void initCurrentCard() {

        Card card = this.deck.pop();
        while (card.getAction() != "Number") {
            this.deck.getCards().add(card);
            card = this.deck.pop();
        }

        this.currentCard = card;
    }

    public void addCardToPlayer() {
        this.players.get(turn).addCard(this.deck.pop());
        this.turn++;
    }

    public boolean isWinner() {
        for (int i = 0; i < this.players.size(); i++) {
            if (this.players.get(i).getNCards() == 0) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public String toString() {
        String playerInfo = "";
        for (int i = 0; i < this.players.size(); i++) {
            playerInfo += "|" + this.players.get(i).toString();
        }

        String cardInfo = "";
        if (this.currentCard != null) {
            cardInfo = this.currentCard.toString();
        }

        String status = this.dir + "|" + cardInfo + playerInfo;
        return status;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public Deck getDeck() {
        return this.deck;
    }

    public Player getActivePlayer() {
        return this.players.get(turn);
    }

    public Player getPlayer(int uid) {
        for (int i = 0; i < this.players.size(); i++) {
            Player player = this.players.get(i);
            if (player.getUid() == uid) {
                return player;
            }
        }
        return null;
    }

    public ArrayList<Card> getAddedCards() {
        return addedCards;
    }

    public void resetAddeCards() {
        this.addedCards.clear();
    }

    public Player nextPlayer() {
        int delta = this.dir ? 1 : -1;
        int nextTurn = (this.turn + delta) % this.players.size();
        if (nextTurn < 0) {
            nextTurn = this.players.size() - 1;
        }
        return this.players.get(nextTurn);
    }

    public Player prevPlayer() {
        int delta = !this.dir ? 1 : -1;
        int prevTurn = turn + delta;
        if (prevTurn < 0) {
            prevTurn = this.players.size() + prevTurn;
        }
        return this.players.get(prevTurn);
    }

    public static void main(String[] args) {
        Game one = new Game(2);
        one.changeDirection();
        one.addPlayer("Jorge", 0);
        one.addPlayer("Cristian", 1);
        Card card = new Card(Card.PLUS_FOUR, "Black");
        one.initCurrentCard();
        one.drawCard(card, 0);

        if (one.getAddedCards().size() != 0) {
            String cardsInfo = "";
            for (int i = 0; i < one.getAddedCards().size(); i++) {
                cardsInfo += "|" + one.getAddedCards().get(i).toString();
            }
            System.out.println(cardsInfo);

        }

    }
}

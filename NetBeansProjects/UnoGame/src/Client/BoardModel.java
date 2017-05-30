/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author chris
 */
public class BoardModel {

    private Card currentCard;
    private ArrayList<Player> players;
    private boolean orientation;
    private OneClient client;
    private PlayerHand hand;
    private Player currentPlayer;
    private boolean dir;
    private Board board;
    private int uid;
    private boolean active;
    private Card thrownCard;
    private JFrame mWindow;
    private ButtonsPanel buttons;
    private String winnerName;
    private boolean waiting = true;

    public BoardModel(JFrame mWindow) {
        this.players = new ArrayList<>();
        this.currentCard = null;
        this.mWindow = mWindow;
    }

    public void setPlayerHand(PlayerHand hand) {
        this.hand = hand;
    }

    public void setClient(OneClient client) {
        this.client = client;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public boolean isOrientation() {
        return orientation;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setDir(boolean dir) {
        this.dir = dir;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void addCard(int number, String color) {
        this.hand.addCard(number, color);
    }

    public boolean getDir() {
        return dir;
    }

    @Override
    public String toString() {
        return "BoardModel{" + "currentCard=" + currentCard.toString() + ", players=" + players.toString() + ", orientation=" + orientation + ", client=" + client + ", hand=" + hand + ", currentPlayer=" + currentPlayer.toString() + ", dir=" + dir + '}';
    }

    public void resetPlayerList() {
        this.players.clear();
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void repaint() {
        this.board.repaint();
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setThrownCard(Card card) {
        this.thrownCard = card;
        this.client.sendToServer(".throwCard|" + card.toString());
    }

    public void sendButtonAction(Buttons button) {

    }

    public Card getThrownCard() {
        return thrownCard;
    }

    public boolean isActive() {
        return active;
    }

    public OneClient getClient() {
        return client;
    }

    public ButtonsPanel getButtons() {
        return buttons;
    }

    public void setButtons(ButtonsPanel buttons) {
        this.buttons = buttons;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public void stopWaiting() {
        this.waiting = false;
    }

    public boolean isWaiting() {
        return this.waiting;
    }

}

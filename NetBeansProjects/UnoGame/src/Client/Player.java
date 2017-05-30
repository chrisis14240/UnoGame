/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

/**
 *
 * @author chris
 */
public class Player {

    private String name;
    private int nCards;
    private int uid;
    private boolean active;
    private boolean one;

    public Player(String name, int nCards, int uid, boolean one) {
        this.name = name;
        this.nCards = nCards;
        this.uid = uid;
        this.one = one;
    }

    public String getName() {
        return name;
    }

    public int getnCards() {
        return nCards;
    }

    public int getUid() {
        return uid;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getActive() {
        return active;
    }

    public boolean isOne() {
        return one;
    }

}

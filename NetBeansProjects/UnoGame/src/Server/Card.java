/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author Invitado1
 */
public class Card {

    private String color;
    private int number;
    private String action;
    public static final int PLUS_TWO = 10;
    public static final int SKIP = 11;
    public static final int CHANGEDIR = 12;
    public static final int PLUS_FOUR = 13;
    public static final int CHANGECOLOR = 14;

    public Card() {
        this.action = "Number";
    }

    public Card(int number, String color) {
        this.number = number;
        this.color = color;
    }

    @Override
    public String toString() {
        return number + "," + color + "," + action;
    }

    public void setColor(String Color) {
        this.color = Color;
    }

    public void setNumber(int Number) {
        this.number = Number;
    }

    public void setAction(String Action) {
        this.action = Action;
    }

    public String getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }

    public String getAction() {
        if (this.number < 9) {
            return "Number";
        } else {
            String action;
            switch (this.number) {
                case Card.PLUS_TWO:
                    action = "+2";
                case Card.PLUS_FOUR:
                    action = "+4";
                case Card.CHANGECOLOR:
                    action = "ChangeColor";
                case Card.CHANGEDIR:
                    action = "ChangeDIrection";
                case Card.SKIP:
                    action = "Skip";
                default:
                    action = "None";
            }

            return action;
        }
    }

    public boolean isWildCard() {
        return (this.getNumber() == Card.PLUS_FOUR) || (this.getNumber() == Card.CHANGECOLOR);
    }

}

package com.ixd3grp2.frontend.wishLists;

// Backend class for wish lists
public class listOfWishesBoxes {
    private final String name;
    private final String timeLeft;

    public listOfWishesBoxes(String name, String timeLeft) {
        this.name = name;
        this.timeLeft = timeLeft;
    }

    public String getName() { return name; }
    public String getTimeLeft() { return timeLeft; }
}

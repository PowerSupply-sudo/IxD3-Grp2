package com.ixd3grp2.frontend.wishLists;

public class Wish {
    private String name;
    private String timeLeft;

    public Wish(String name, String timeLeft) {
        this.name = name;
        this.timeLeft = timeLeft;
    }

    public String getName() {
        return name;
    }

    public String getTimeLeft() {
        return timeLeft;
    }
}

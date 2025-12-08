package com.ixd3grp2.frontend.wishLists;

// Backend class for wish lists
public class listOfWishesBoxes {
    // This class represents a single "wish" in a wish list.
    // It stores the name of the wish and how much time is left.

    private final String name;      // Field to store the name of the wish (e.g., "Headphones")
    private final String timeLeft;  // Field to store how much time is left (e.g., "30d 14h")

    // Constructor: called when creating a new listOfWishesBoxes object.
    // Example: new listOfWishesBoxes("Headphones", "30d 14h")
    public listOfWishesBoxes(String name, String timeLeft) {
        this.name = name;        // Assigns the parameter 'name' to the class field 'name'
        this.timeLeft = timeLeft;// Assigns the parameter 'timeLeft' to the class field 'timeLeft'
    }

    // Getter method: returns the name of the wish
    public String getName() { 
        return name; 
    }

    // Getter method: returns the time left for the wish
    public String getTimeLeft() { 
        return timeLeft; 
    }
}

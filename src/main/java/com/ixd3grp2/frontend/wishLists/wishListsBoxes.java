package com.ixd3grp2.frontend.wishLists;

// Backend class for wish lists overview
public class wishListsBoxes {
    private final String name; 

    // Constructor
    public wishListsBoxes(String name) {
        this.name = name;
    }//Constructor'en gør det muligt at oprette en ny instans af wishListsBoxes med et specifikt navn for ønskelisten.

    // Getters
    public String getName() {
        return name;
    }// Getter-metoden gør det muligt at hente navnet på ønskelisten fra en instans af wishListsBoxes.
}
// Denne klasse repræsenterer en ønskeliste med et navn, som kan bruges i frontend til at vise en oversigt over forskellige ønskelister.
// den virker på den måde at hver instans af wishListsBoxes indeholder navnet på en ønskeliste, som kan vises i et grid eller liste i brugergrænsefladen.
// wishListsFE kan bruge denne klasse til at hente og vise oplysninger om hver ønskeliste i oversigten.
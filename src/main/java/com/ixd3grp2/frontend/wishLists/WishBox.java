package com.ixd3grp2.frontend.wishLists;

// Backend class for individual wishes, tilsvarende til wishViewFE, som håndterer frontend visningen af et ønske    
// når det er blevet valgt fra wishListsBoxes oversigten, så kort sagt en model klasse for et ønske, med attributter som navn og tid tilbage.
// kort sagt en simpel POJO (Plain Old Java Object) klasse. som repræsenterer et ønske i ønskelisten, når en bruger vil se detaljer om et specifikt ønske.
public class WishBox {
    private String name;
    private String timeLeft;

    public WishBox(String name, String timeLeft) {
        this.name = name;
        this.timeLeft = timeLeft;
    }// Constructor til at initialisere et ønske med navn og tid tilbage

    public String getName() {
        return name;
    }// Getters til name

    public String getTimeLeft() {
        return timeLeft;
    }// Getters til timeLeft
}
// Frontend klassen wishViewFE kan bruge denne backend klasse Wish til at hente og vise oplysninger om et specifikt ønske.
// den virker på den måde at den viser detaljerne for et enkelt ønske, når brugeren vælger det fra en liste over ønsker.
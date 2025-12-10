-- -------------------------------
-- ✅ Opret database (valgfrit)
-- -------------------------------
CREATE DATABASE IF NOT EXISTS ixd3grp2;
USE ixd3grp2;

-- -------------------------------
-- ✅ Tabel til ønskelister
-- -------------------------------
-- Her gemmes selve listerne (fx "Min ønskeliste")
CREATE TABLE wish_lists (
    id INT AUTO_INCREMENT PRIMARY KEY,   -- Unik ID for listen
    name VARCHAR(255) NOT NULL           -- Navn på ønskelisten
);

-- -------------------------------
-- ✅ Tabel til ønsker
-- -------------------------------
-- Her gemmes de enkelte ønsker, som hører til en liste
CREATE TABLE wishes (
    id INT AUTO_INCREMENT PRIMARY KEY,   -- Unik ID for hvert ønske
    list_id INT NOT NULL,                -- Reference til hvilken liste det tilhører
    name VARCHAR(255) NOT NULL,          -- Navn på ønsket
    time_left VARCHAR(50),               -- Tid tilbage (kan senere laves som dato)
    FOREIGN KEY (list_id) REFERENCES wish_lists(id) ON DELETE CASCADE
);

-- -------------------------------
-- ✅ Indsæt testdata
-- -------------------------------
-- Eksempel på en ønskeliste
INSERT INTO wish_lists (name) VALUES ('Min første ønskeliste');

-- Eksempel på ønsker i listen med id=1
INSERT INTO wishes (list_id, name, time_left)
VALUES 
(1, 'Noise-cancelling headphones', '30d 14h'),
(1, 'Minimalist backpack', '25d 6h'),
(1, 'Smartwatch', '12d 3h');

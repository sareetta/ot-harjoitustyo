# Käyttöohje
Lataa tiedosto Sudoku.jar

Ohjelman pystyy ajamaan esimerkiksi kotihakemistosta.

## Konfigurointi
Ohjelma olettaa, että sen käynnistyshakemistossa on konfiguraatiotiedosto config.properties, joka määrittelee pelituloksien tietokannan nimen sekä taulujen nimet. 
Tiedoston muoto on seuraava:

```javascript
sudokuDB=scores.db
easyTable=Easy
mediumTable=Medium
hardTable=Hard
```

## Ohjelman käynnistäminen
Ohjelman voi käynnistää komennolla:

```javascript
java -jar Sudoku.jar
```
## Sudokun pelaaminen
Ohjelman käynnistyttyä avautuu alkuvalikko. Sudokun pelaaminen aloitetaan painamalla nappia Play. Tämän jälkeen avautuu näkymä, jossa valitaan vaikeustaso kolmesta vaihtoehdosta (helppo, keskitaso, vaikea).
Vaikeustasojen ero on, että tyhjien ruutujen määrä on suurempi vaikeammilla tasoilla.

Kun ohjelma on ohjannut käyttäjän pelinäkymään, pelaaminen tapahtuu täyttämällä ikkunassa olevaan sudokuruudukkoon numeroita klikkaamalla tyhjää ruutua.
Jokaisella rivillä, sarakkeessa ja 3x3 -ruudukossa numerot 1-9 saa esiintyä vain kerran. Kun ruudukkoon täytetyn ratkaisun haluaa tarkistaa, painetaan Check -nappia.
Ohjelma ilmoittaa, mikäli ratkaisu on oikein. Jos ratkaisu on oikea, käyttäjä voi tallentaa tuloksen kirjoittamalla nimimerkkinsä ilmestyvään tekstikenttään ja painamalla tallennusnappia.
Ohjelma hyväksyy nimeksi vain 1 - 15 merkkiä pitkän merkkijonon. 
Tämän jälkeen pelaaja voi alottaa uuden pelin, kun näkymä on vaihtunut takaisin aloitusvalikkoon. Kesken pelin käyttäjä voi pyytää uuden sudokun painamalla new Sudoku -nappia. 
Mikäli käyttäjä haluaa palata vaikeustason valintavalikkoon, se voidaan tehdä painamalla Return to menu -nappia. Exit -napilla ohjelman voi sulkea. Tällöin avautuu näkymä, jossa
kysytään haluaako käyttäjä varmasti poistu, koska poistuminen aiheuttaa pelitilanteen menetyksen eli pelitilannetta ei tallenneta.

## Ennätyksien katselu
Käyttäjän parhaiden tulosten katselu onnistuu painamalla alkuvalikossa Statistics -nappia. 
Nappi avaa ennätysnäkymän, jossa on vasemmalla listattuna top-10 suoritukset kaikilta vaikeustasoilta.
Halutessaan käyttäjä voi palata takaisin alkuvalikkoon painamalla vasemmassa alakulmassa olevaa Return to menu -nappia.

## Huomioita
Tiedon pysyväistallennukseen käytetään tietokantaa, joka tallentuu scores.db -tiedostoon.
Jos kyseisen tietokannan poistaa, niin tiedot menetetään lopullisesti. Tällöin vanhoja ennätyksiä ei voi enää katsoa. 
Jos haluaa poistaa ennätykset, niin tämä onnistuu poistamalla kyseisen tiedoston. Tällöin käynnistyksen yhteydessä sovellus luo uuden tietokannan.


# Vaatimusmäärittely

## Sovelluksen tarkoitus
Sovellus on logiikkapeli Sudoku. Pelin ideana on ruutujen täyttäminen luvuilla yhdestä yhdeksään paria sääntöä noudattaen.
Tarkoituksena on täyttää 9x9 -kokoisia ruudukoita niin, että jokaisella vaaka- ja pystyrivillä jokainen numero esiintyy vain kerran.
Kyseinen ruudukko koostuu pienemmistä 3x3 -kokoisista ruudukoista, joissa jokaisessa saa esiintyä luvut yhdestä yhdeksään vain kerran.
Ruudukossa on pelin alussa osa ruuduista täytettyinä ja pelaaja voi vaikuttaa niiden määrään valitsemalla pelin vaikeustason.

## Käyttäjät
Pelissä jokainen käyttäjä on rooliltaan _normaali_ _käyttäjä_.

## Perusversion toiminnallisuudet
* Sovelluksen käynnistyessä
  * Käyttäjä voi valita haluaako aloittaa pelaamisen vai katsoa ennätysaikoja
  * Käyttäjä voi valita kahdesta vaikeustasosta _helppo_ ja _keskitaso_ mieluisemman
* Kun käyttäjä aloittaa pelin, hänelle generoidaan peliruudukko. Ruudukkoon syötetään numeroita näppäimistöltä klikkaamalla hiirellä haluttua sijoituskohtaa
* Ruudulla näkyy kello, josta pelaaja voi seurata kauanko hänellä menee aikaa pelin ratkaisemiseen
* Käyttäjä pystyy pelin aikana tarkastamaan ratkaisunsa tarkistusnapista
* Kun ruudukko on täytetty oikein kokonaisuudessaan eli tarkistus menee läpi, peli päättyy
* Pelin päättymisen jälkeen pelaajalta kysytään nimimerkki, joka tallennetaan tilastoihin hänen peliaikansa kanssa.
* Jos käyttäjä valitsee katsoa tilastoja, niin hänelle näytetään 10 parhaiten sijoittunutta käyttäjää molemmista vaikeustasoista
* Vaikeustasot eroavat toisistaan niissä olevien valmiiksi täytettyjen ruutujen määrällä

## Jatkokehitysideoita
* Mahdollisuus jatkaa jo aloitettua peliä eli pelitilanteen tallentaminen
* Vaikeustasojen lisääminen
* Värien vaihtaminen ja muita visuaalisia ominaisuuksia
* Peli voisi antaa vihjeitä tarvittaessa



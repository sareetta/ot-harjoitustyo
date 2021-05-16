# Testausdokumentti

Ohjelmaa on testattu yksikkö- ja integraatiotestein JUnitilla. Näitä testejä on tehty pakettien sudoku.domain ja sudoku.dao luokille. 
Käyttöliittymälle on tehty manuaalisia testejä.

## JUnit-testit

### Sovelluslogiikka

Suurin osa automatisoiduista JUnit-testeistä testaavat ohjelman sovelluslogiikkaa.
Sovelluslogiikka on pakkauksessa sudoku.domain (luokat SudokuGame ja SudokuScore). 
Testipakkauksen pakkauksessa sudoku.domain on näille sovelluslogiikan luokille testiluokat SudokuGameTest ja SudokuScoreTest.

SudokuScoreTest-luokassa testataan SudokuScore -luokan toimivuutta kattavasti. 
Myös SudokuGameTest testaa todella kattavasti kaikki luokan metodit ja näin Sudokun sovelluslogiikka tulee testattua kokonaisuudessaan.
Testiluokissa on pyritty käymään läpi testattavan luokan kaikki metodit niin, että on samalla tarkasteltu erilaisia haarautumisia.

### DAO-luokat 
ScoreDao -luokan toimintaa on testattu testiluokassa SudokuScoreTest ja DBScore -luokan toimintaa testiluokassa DBScoreTest.
Molemmat testiluokat löytyvät testipakkauksesta sudoku.dao.
Testausta varten käytetään testitietokantaa test.db, joka testien jälkeen poistetaan.
Testitietokannan avulla testataan varsinaisen tietokannan toimintaa testaamalla tietokantaluokkien metodeja tällä testitietokannalla.

## Testikattavuus
Käyttöliittymää lukuunottamatta sovelluksen testauksen rivikattavuus on 95% ja haarautumakattavuus 98%.

![Sudoku_jacoco_raportti](https://user-images.githubusercontent.com/81009944/118409014-12642c00-b691-11eb-96d0-ad418d20d7c4.png)

Testaamatta on muutamia settereitä ja gettereitä sekä DAO-puolella testaamattomat tilanteet ovat sellaiset, joissa käsitellään SQLException.

## Järejestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti. Käyttöliittymää on testattu manuaalisesti testaamalla mahdollisia tilanteita.

## Asennus ja konfigurointi
Sovellus on haettu ja sitä on testattu [käyttöohjeen](https://github.com/sareetta/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md) kuvaamalla tavalla sekä Windows- että Linux-ympäristöön siten, että sovelluksen käynnistyshakemistossa on oikeanlainen config.properties-tiedosto.

Sovellusta on testattu siten, että tietokanta on ollut olemassa ja niin, että sitä ei ole ollut olemassa, jolloin ohjelma on luonut sen itse.

## Toiminnallisuus

Sovelluksen kaikki toiminnallisuudet [vaatimusmäärittelystä](https://github.com/sareetta/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittelu.md) on pyritty testaamaan.
On huomioitu, että käyttäjän käyttäjänimi on sallituissa rajoissa ja ettei sudokun tarkistuksessa tule virhetilannetta, jos käyttäjä on syöttänyt sudokuun epäkelpoja arvoja.

## Sovelluksen laatuongelmat
Virheilmoitukset on pyritty kirjoittamaan laajasti, mutta on mahdollista, että joissain tilanteissa sovellus ei anna järkevää ilmoitusta.

Sudokun täyttäminen toteutetaan tällä hetkellä hiiren ja näppäimistön avulla. 
Jotkut mielummin käyttäisivät pelkkää hiirtä tai nuolinäppäimiä sudokun täyttämiseen, mutta itse pidän nykyisestä toteutuksesta.
Käyttöliittymään on jäänyt hieman sovelluslogiikkaa, mutta sen määrää on pyritty minimoimaan.


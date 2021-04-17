# Ohjelmistotekniikka, harjoitustyö


## Dokumentaatio

[Vaatimusmäärittely](https://github.com/sareetta/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmäärittely.md)

[Tuntikirjanpito](https://github.com/sareetta/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/sareetta/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Komentorivitoiminnot

### Ohjelman suoritus
Ohjelman voi suorittaa kansiosta Sudoku komennolla:
```javascript
mvn compile exec:java -Dexec.mainClass=sudoku.Main
```
### Testaus
Testit voi suorittaa komennolla:
```javascript
mvn test
```
Testikattavuuden saa komennolla:
```javascript
mvn jacoco:report
```
Komento luo target-kansioon projektista testien rivi- ja haaraumakattavuusraportin (target/site/jacoco/index.html).

### Checkstyle
Checkstyle-raportin saa komennolla:
```javascript
mvn jxr:jxr checkstyle:checkstyle
```
Komento luo target-kansioon projektista tyylivirheraportin (target/site/checkstyle.html).


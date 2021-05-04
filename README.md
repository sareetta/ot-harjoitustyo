# Ohjelmistotekniikka, harjoitustyö


## Dokumentaatio

[Vaatimusmäärittely](https://github.com/sareetta/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmäärittely.md)

[Tuntikirjanpito](https://github.com/sareetta/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/sareetta/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Käyttöohje](https://github.com/sareetta/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

## Releaset
[Viikko 5](https://github.com/sareetta/ot-harjoitustyo/releases)
[Viikko6](https://github.com/sareetta/ot-harjoitustyo/releases)
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

### Suoritettavan Jarin generointi

```javascript
mvn package
```
Komento generoi hakemistoon target suoritettavan jar -tiedoston Sudoku-1.0-SNAPSHOT.jar

### Checkstyle
Checkstyle-raportin saa komennolla:
```javascript
mvn jxr:jxr checkstyle:checkstyle
```
Komento luo target-kansioon projektista tyylivirheraportin (target/site/checkstyle.html).

### JavaDoc
JavaDoc generoidaan komennolla
```javascript
mvn javadoc:javadoc
```
JavaDocia voi tarkastella avaamalla selaimella tiedosto target/site/apidocs/index.html



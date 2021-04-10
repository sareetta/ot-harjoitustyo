# Ohjelmistotekniikka, harjoitustyö


## Dokumentaatio

[Vaatimusmäärittely](https://github.com/sareetta/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmäärittely.md)

[Tuntikirjanpito](https://github.com/sareetta/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Komentorivitoiminnot

### Ohjelman suoritus
Ohjelman voi suorittaa kansiosta Sudoku komennolla:
```javascript
mvn compile exec:java -Dexec.mainClass=sudoku.Main
```
### Testaus
Testit suoritetaan komennolla:
```javascript
mvn test
```
Testikattavuus saadaan komennolla:
```javascript
mvn jacoco:report
```


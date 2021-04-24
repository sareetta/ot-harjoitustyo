### Arkkitehtuurikuvaus

## Rakenne
Ohjelman rakenne noudattaa kolmikerroksista arkkitehtuuria.
Koodin pakkausrakenne on seuraava:

![Sudoku_package_diagram](https://user-images.githubusercontent.com/81009944/115229863-3bd67a00-a11c-11eb-9668-585ea7b22d1e.png)

Käyttöliittymän, sovelluslogiikan ja tiedon pysyväistallennuksen luokkien välisiä suhteita kuvaava luokka-/pakkauskaavio:

![Sudoku_package_class_diagram](https://user-images.githubusercontent.com/81009944/115229890-4264f180-a11c-11eb-8110-b987497f6022.png)

### Päätoiminnallisuudet
Kuvaus Sudokun päätoiminnallisuuksista.

Sekvenssikaavio seuraavasta tapauksesta:

Pelaaja aloittaa uuden pelin ja tarkistaa ratkaisun.

![Uuden Sudokun generointi ja tarkistus](https://user-images.githubusercontent.com/81009944/115964602-f6f08000-a52d-11eb-91d2-8f20ac8ad9c2.png)

Pelaaja voi aloittaa uuden pelin painamalla play -nappia, jonka jälkeen hän voi valita haluamansa vaikeustason. 
Uuden pelin voi generoida myös pelinäkymästä painamalla new Sudoku -nappia.
Valinnan jälkeen tapahtuu seuraavat asiat: 
Käyttöliittymä asettaa pelaajan haluaman vaikeustason kutsumalla SudokuGame -luokan metodia setDifficulty(difficulty).
Tämän jälkeen käyttöliittymä kutsuu SudokuGame -luokkan metodia newGame(). 
Metodi kutsuu toista metodia generateSudoku(indeksi) indeksillä 0. 
Tämä aloittaa rekursion, joka täyttää koko Sudoku -laudan etsien metodin getNextPossibleValue(row, column, values) avulla seuraavan mahdollisen luvun ruutuun.
Luvut valitaan listasta, jossa on luvut 1-9 sekoitettuna. Luvut tulevat siis satunnaisesti ja metodi tarkistaa seuraavien metodien avulla, onko luku mahdollinen kyseiseen ruutuun. Metodi isPossibleRow(column, value) tarkistaa onko luku jo kyseisellä rivillä, kun metodille annetaan kiinnitetty sarake. Metodi isPossibleColumn(row, value) tarkistaa onko luku jo kyseisessä sarakkeessa, kun annetaan kiinnitetty rivi. Metodi isPossibleSubgrid(row, column, value) tarkistaa onko kyseinen luku jo 3x3 -ruudukossa. Jos nämä kaikki kolme metodia palauttaa true, voidaan luku sijoittaa ruutuun. Kun rekursio päättyy, niin Sudokun ratkaisu on valmis. Tämän jälkeen newSudoku() kutsuu vielä metodia addDifficultyLevel(difficulty), joka lisää satunnaisesti tyhjiä ruutuja valitun vaikeustason mukaisesti ruudukkoon. Sudokun tarkistaminen onnistuu painamalla check -nappia. Tällöin käyttöjärjestelmä kutsuu SudokuGame -luokan metodia setValue(row, int, value) jokaiselle Sudokun ruudulle, jolla asetetaan pelaajan lisäämät luvut Sudokuun. Sitten lukujen lisäämisen jälkeen käyttöjärjestelmä kutsuu metodia checkSudoku() luokasta SudokuGame. Metodi tarkistaa vastaako pelaajan ratkaisu alussa generoitua Sudokun ratkaisua, joka on kopiona luokassa.




# Arkkitehtuurikuvaus

## Rakenne
Ohjelman rakenne noudattaa kolmikerroksista arkkitehtuuria.
Koodin pakkausrakenne on seuraava:

![Sudoku_package_diagram](https://user-images.githubusercontent.com/81009944/115229863-3bd67a00-a11c-11eb-9668-585ea7b22d1e.png)

Pakkaus sudoku.ui sisältää JavaFX:llä toteutetun käyttöliittymän, sudoku.domain sovelluslogiikan ja sudoku.dao tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä
Sovelluksen graafinen käyttöliittymä on luokissa sudoku.ui.SudokuUi ja sudoku.ui.SudokuDisplay. SudokuUi sisältää suurimman osan käyttöliittymästä, mutta luokassa SudokuDisplay on sudokulaudan asettelusta vastaavaa koodia. Luokka sudoku.ui.Timer sisältää käyttöliittymässä olevan kellon ylläpidon.

Käyttöliittymässä on seuraavat näkymät:
* päävalikko
* ennätykset
* vaikeustason valintavalikko
* pelinäkymä
* tuloksen tallenussnäkymä
* poistumisnäkymä

jokainen näistä on toteutettu omana Scene-oliona. Näkymistä yksi kerrallaan on näkyvänä.

Valikko-näkymä avautuu sovelluksen käynnistyessä. Siitä pystyy valitsemaan pelaamisen, ennätyksien katselun tai poistumisen. Ennätysnäkymässä näkee molempien vaikeusluokkien top-10 listan. Tästä näkymästä pääsee takaisin päävalikkoon. Jos valitsee päävalikosta pelaamisen, avautuu näkyy vaikeustason valintaan, jotka ovat _easy_ ja _medium_. Tästäkin näkymästä pääsee takaisin päävalikkoon. Kun on valinnut vaikeustason, niin avautuu pelinäkymä. Tässä näkymässä itse Sudokua voi pelata. Näkymästä voi sulkea pelin, jolloin avautuu ikkuna, joka varmistaa haluaako pelaaja poistua. Muita vaihtoehtoja pelinäkymässä on palata vaikeustason valintanäkymään, tarkistaa Sudoku ja uuden sudokun luominen.

Käyttöliittymä on eriytetty sovelluslogiikasta ja se kutsuu luokan SudokuGame metodeja oliolla _sudoku_. Käyttöliittymässä myös käytetään SudokuScore -oliota, joka on tulossolio. Se viedään ScoreDao -oliota käyttäen tietokantaan. Dao -oliota tarvitaan tulosten tallentamiseen.

## Sovelluslogiikka

Sovelluslogiikka koostuu sudokuruudukkoa sudokulautaa esittävästä luokasta SudokuGame sekä pelitulosta esittävästä luokasta SudokuScore. Sudokulla on oliomuuttujana ruudukkoa esittävä kaksiulotteinen taulukko ja Sudokun ratkaisua esittävä kaksiulotteinen ruudukko. SudokuGame-luokan tehtävä on sudokuruudukon numeroiden hallinnoiminen. Sen avulla täytetään pelattava Sudoku sekä huolehditaan Sudokun tarkastamisesta.

SudokuScorella on oliomuuttujina id, pelaajan nimi ja suoritusaika tietokantaan tallentamista varten. Sudoku ja pelitulokset eivät tiedä toisistaan mitään. 
Luokkakaavio paketin sudoku.domain sisällöstä:

![Class_diagram_sudoku domain](https://user-images.githubusercontent.com/81009944/116816516-e6ae5580-ab6a-11eb-83d9-536652e38c86.png)

Toiminnallisista kokonaisuuksista vastaa luokka SudokuGame. Luokka tarjoaa kaikille käyttöliittymän toiminnoille oman metodin. Näitä ovat esim.

* void newSudoku()
* int[][] generateSudoku(int index)
* boolean isPossibleSubgrid(int column, int row, int value)
* boolean isPossibleRow(int column, int value)
* boolean isPossibleColumn(int row, int value)
* int getNextPossibleValue(int column, int row, List<Integer> values)
* void addDifficultyLevel(int difficulty)
* boolean checkSudoku()
* int[][] copy(int[][] sudoku)

SudokuScore-luokalla on yhteys käyttöliittymässä käytössä olevaan DAO-olioon ScoreDao, koska DAO:n kautta tietokantaan tallennetaan tuloksia SudokuScore-olioiden tietojen perusteella. Tiedot tuodaan tietokannasta niin, että luodaan niitä vastaavat SudokuScore-oliot.

Käyttöliittymän, sovelluslogiikan ja tiedon pysyväistallennuksen luokkien välisiä suhteita kuvaava luokka-/pakkauskaavio:

![Sudoku_package_class_diagram (1)](https://user-images.githubusercontent.com/81009944/116818453-24fc4280-ab74-11eb-8a04-2145f1a8c939.png)

## Tietojen pysyväistallennus
Pakkauksen sudoku.dao luokan ScoreDao kautta tehdään pelitulosten pitkäaikaistallennus tietokantaan. Itse tallennuksen hoitaa luokka DBScore. Tämä luokka on vastuussa tietokannan ylläpidsta ja muokkaamisesta. ScoreDaon:n kautta hoidetaan molempien vaikeutasojen tulosten tallennus tietokantaan.

DAO-luokissa on myötäilty normaalia Data Access Object -mallia. Niissä on metodit rivien lisäämiseen tietokantatauluun ja taulun kaikkien rivien listaamiseen järjestyksessä suoritusajan mukaan. Luokat käyttävät rajapintaa SQLDao.

Tulostietojen tallennus tapahtuu SQL-tietokantaan, jonka DBScore luo tarvittaessa, mikäli sitä ei löydy. Tietokannassa on kunkin tuloksen kohdalla id, pelaajan antama käyttäjänimi sekä Sudokun suoritusaika.

## Päätoiminnallisuudet
Tärkeimmät toiminnallisuudet Sudokun toiminnan kannalta ovat uuden Sudokun generoiminen ja Sudokun tarkastaminen. Erityisesti Sudokun generoiminen on välttämätöntä, jotta peliä voi pelata sovelluksessa.

Pelaajalle generoidaan uusi Sudoku, kun hän pyytää sitä painamalla vaikeustason valintanappia, tai kun hän pelinäkymässä klikkaa nappia uuden sudokun generoimiseksi. Kun pelaaja on valmis Sudokun kanssa, hän voi painaa tarkista nappia, jolloin tarkistetaan onko ratkaisu oikea.
Sekvenssikaavio seuraavasta tapauksesta:
Pelaaja aloittaa uuden pelin ja tarkistaa ratkaisun.

![Uuden Sudokun generointi ja tarkistus](https://user-images.githubusercontent.com/81009944/115964602-f6f08000-a52d-11eb-91d2-8f20ac8ad9c2.png)

Pelin valinnan jälkeen tapahtuu seuraavat asiat: 

Käyttöliittymä asettaa pelaajan haluaman vaikeustason kutsumalla SudokuGame -luokan metodia setDifficulty(difficulty).
Tämän jälkeen käyttöliittymä kutsuu SudokuGame -luokkan metodia newGame(). 
Metodi kutsuu toista metodia generateSudoku(indeksi) indeksillä 0. 
Tämä aloittaa rekursion, joka täyttää koko Sudoku -laudan etsien metodin getNextPossibleValue(row, column, values) avulla seuraavan mahdollisen luvun ruutuun.
Luvut valitaan listasta, jossa on luvut 1-9 sekoitettuna. Luvut tulevat siis satunnaisesti ja metodi tarkistaa seuraavien metodien avulla, onko luku mahdollinen kyseiseen ruutuun. Metodi isPossibleRow(column, value) tarkistaa onko luku jo kyseisellä rivillä, kun metodille annetaan kiinnitetty sarake. Metodi isPossibleColumn(row, value) tarkistaa onko luku jo kyseisessä sarakkeessa, kun annetaan kiinnitetty rivi. Metodi isPossibleSubgrid(row, column, value) tarkistaa onko kyseinen luku jo 3x3 -ruudukossa. Jos nämä kaikki kolme metodia palauttaa true, voidaan luku sijoittaa ruutuun. Kun rekursio päättyy, niin Sudokun ratkaisu on valmis. Tämän jälkeen newSudoku() kutsuu vielä metodia addDifficultyLevel(difficulty), joka lisää satunnaisesti tyhjiä ruutuja valitun vaikeustason mukaisesti ruudukkoon. Sudokun tarkistaminen onnistuu painamalla check -nappia. Tällöin käyttöjärjestelmä kutsuu SudokuGame -luokan metodia setValue(row, int, value) jokaiselle Sudokun ruudulle, jolla asetetaan pelaajan lisäämät luvut Sudokuun. Sitten lukujen lisäämisen jälkeen käyttöjärjestelmä kutsuu metodia checkSudoku() luokasta SudokuGame. Metodi tarkistaa vastaako pelaajan ratkaisu alussa generoitua Sudokun ratkaisua, joka on kopiona luokassa.

Sudokun generoimisen ja tarkistamisen lisäksi tärkeimmät toiminnot ovat varmaankin tulosten tallentaminen ja listaaminen. Tuloksen tallentaminen tapahtuu niin, että pelaaja kirjoittaa tekstikenttään nimimerkkinsä ja painaa tallentamiseen tarkoitettua nappia. Käyttöliittymä lähettää kyseessä olevaa vaikeustasoa vastaavalle DAO:lle save-metodin parametrina uuden SudokuScore-olion, jonka tietona ovat käyttäjän nimi ja aika. Listaaminen puolestaan tapahtuu niin, että käyttäjän painaessa alkuvalikossa ennätyksiin vievää nappia, käyttöliittymä kutsuu kummankin DAO:n list-metodia, ja asettaa niistä SudokuScore-olioista top-10 suoritusta tulosnäkymään.

## Ohjelman rakenteeseen jääneet heikkoudet

Sovelluslogiikka

Sudoku-luokka on luokkana aika iso, ja siinä on yksi metodi, joka on yli 20 riviä. Metodi generateSudoku on suositeltua pidempi. Metodi käyttää rekursiota Sudokun täyttämiseen enkä tiedä miten sitä voisi lyhentää. Sudoku-luokka jäi pitkäksi, koska kaikki siinä olevat metodit liittyvät sudokuun ja sen ylläpitoon. Sudokun generoimiseen ja tarkistukseen tarvitsee useita metodeja, joten pituutta tuli siitä.




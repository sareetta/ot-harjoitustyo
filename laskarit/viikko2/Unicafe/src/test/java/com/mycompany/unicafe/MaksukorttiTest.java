package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {
    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);
    }

    @Test
    public void konstruktioAsettaaSaldonOikein() {
        assertEquals("saldo: 10.00", kortti.toString());
    }

    @Test
    public void saldonkutsuminenToimii() {
        assertEquals(1000,kortti.saldo());
    }

    @Test
    public void rahanLatausToimii() {
        kortti.lataaRahaa(205);
        assertEquals("saldo: 12.05",kortti.toString());
    }

    @Test
    public void rahanOttaminenToimii() {
        kortti.otaRahaa(200);
        assertEquals("saldo: 8.00",kortti.toString());
    }

    @Test
    public void rahanOttaminenEiVieSaldoaNegatiiviseksi() {
        kortti.otaRahaa(2000);
        assertEquals("saldo: 10.00", kortti.toString());
    }

    @Test
    public void rahanOttaminenPalauttaaTrueJosTarpeeksi() {
        assertTrue(kortti.otaRahaa(900));
    }

    @Test
    public void rahanOttaminenPalauttaaFalseJosEiTarpeeksi() {
        assertFalse(kortti.otaRahaa(1200));
    }

    @Test
    public void toStringPalauttaaOikeinKunYliKymmenenSenttiä() {
        kortti.lataaRahaa(220);
        assertEquals("saldo: 12.20",kortti.toString());
    }


}

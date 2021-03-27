package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);
    }

    @Test
    public void konstruktioAsettaaSaldonOikein() {
        assertTrue(10==kortti.saldo());
    }

    @Test
    public void rahanLatausToimii() {
        kortti.lataaRahaa(10);
        assertTrue(20==kortti.saldo());
    }

    @Test
    public void rahanOttaminenToimii() {
        kortti.otaRahaa(2);
        assertTrue(8==kortti.saldo());
    }

    @Test
    public void rahanOttaminenEiVieSaldoaNegatiiviseksi() {
        kortti.otaRahaa(20);
        assertTrue(10==kortti.saldo());
    }

    @Test
    public void rahanOttaminenPalauttaaTrueJosTarpeeksi() {
        assertTrue(kortti.otaRahaa(2));
    }

    @Test
    public void rahanOttaminenPalauttaaFalseJosEiTarpeeksi() {
        assertFalse(kortti.otaRahaa(12));
    }


}

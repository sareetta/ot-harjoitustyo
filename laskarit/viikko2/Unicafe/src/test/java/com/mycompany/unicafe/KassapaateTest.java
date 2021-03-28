package com.mycompany.unicafe;

import com.mycompany.unicafe.Kassapaate;
import com.mycompany.unicafe.Maksukortti;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KassapaateTest {
    Kassapaate kassa;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
    }

    @Test
    public void kassanLuominenOnnisttuu() {
        assertTrue(kassa!=null);
    }
    @Test
    public void konstruktioToimiiOikein() {
        assertEquals(100000,kassa.kassassaRahaa());
    }

    @Test
    public void maukkaitaLounaitaMyytyAlussa() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void edullisiaLounaitaMyytyAlussa() {
        assertEquals(0,kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiLisaaKassaanRahaa() {
        kassa.syoEdullisesti(500);
        assertEquals(100240,kassa.kassassaRahaa());
    }

    @Test
    public void syoMaukkaastiLisaaKasaanRahaa() {
        kassa.syoMaukkaasti(500);
        assertEquals(100400,kassa.kassassaRahaa());
    }

    @Test
    public void vaihtorahaaOikeaMaaraEdullisestaLounaasta() {
        assertEquals(160,kassa.syoEdullisesti(400));
    }

    @Test
    public void vaihtorahaaOikeaMaaraMaukkaastaLounaasta() {
        assertEquals(100,kassa.syoMaukkaasti(500));
    }

    @Test
    public void edullisenLounaidenMyyntiKasvaa() {
        kassa.syoEdullisesti(240);
        kassa.syoEdullisesti(240);
        assertEquals(2,kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void maukkaidenLounaidenMyyntiKasvaa() {
        kassa.syoMaukkaasti(400);
        kassa.syoMaukkaasti(400);
        assertEquals(2,kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void edullistaLounastaEiMyydäJosEiTarpeeksiRahaa() {
        kassa.syoEdullisesti(100);
        assertEquals(100000,kassa.kassassaRahaa());

    }

    @Test
    public void maukastaLounastaEiMyydäJosEiTarpeeksiRahaa() {
        kassa.syoMaukkaasti(300);
        assertEquals(100000,kassa.kassassaRahaa());
    }

    @Test
    public void oikeaRahamaaraTakaisinJosEdullistaLounastaEiOsteta() {
        assertEquals(200,kassa.syoEdullisesti(200));
    }

    @Test
    public void oikeaRahamaaraTakaisinJosMaukastaLounastaEiOsteta() {
        assertEquals(200,kassa.syoMaukkaasti(200));
    }

    @Test
    public void myyntiEiKasvaEdullisellaLounaallaJosOstoaEiTapahdu() {
        kassa.syoEdullisesti(200);
        assertEquals(0,kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void myyntiEiKasvaMaukkaallaLounaallaJosOstoaEiTapahdu() {
        kassa.syoMaukkaasti(200);
        assertEquals(0,kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void korttiostoPalauttaaTrueKunOstoTapahtuu() {
        assertTrue(kassa.syoEdullisesti(kortti));
    }

    @Test
    public void korttiostoPalauttaaFalseKunOstoaEiTapahdu() {
        kortti.otaRahaa(900);
        assertFalse(kassa.syoEdullisesti(kortti));
    }

    @Test
    public void korttiostoLisaaEdullistenLounaidenMyyntiaKunOstoTapahtuu() {
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void korttiostoLisaaMaukkaidenLounaidenMyyntiaKunOstoTapahtuu() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void korttiostoEiLisaaEdullistenLounaidenMyyntiaKunOstoaEiTapahdu() {
        kortti.otaRahaa(900);
        kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void korttiostoEiLisaaMaukkaidenLounaidenMyyntiaKunOstoaEiTapahdu() {
        kortti.otaRahaa(900);
        kassa.syoMaukkaasti(kortti);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void edullisenLounaanOstoVahentaaKortinSaldoa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }

    @Test
    public void maukkaanlounaanOstoVahentaaKortinSaldoa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }

    @Test
    public void korttiostoEdullisellaLounaallaEiVahennaSaldoaJosOstoaEiTapahdu() {
        kortti.otaRahaa(900);
        kassa.syoEdullisesti(kortti);
        assertEquals(100, kortti.saldo());
    }

    @Test
    public void korttiostoMaukkaallaLounaallaEiVahennaSaldoaJosOstoaEiTapahdu() {
        kortti.otaRahaa(900);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100, kortti.saldo());
    }

    @Test
    public void kassanSaldoKasvaaKunKortilleLadataanRahaa() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(100500, kassa.kassassaRahaa());
    }

    @Test
    public void kortinSaldoKasvaaKunSilleLadataanRahaa() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(1500, kortti.saldo());
    }

    @Test
    public void kassanSaldoEiKasvaKorttiostossa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000,kassa.kassassaRahaa());
    }

    @Test
    public void negatiivinenSummaEiKasvataKassanSaldoa() {
        kassa.lataaRahaaKortille(kortti, -500);
        assertEquals(100000,kassa.kassassaRahaa());
    }

    @Test
    public void negatiivisenSummaEiKasvataKassanSaldoa() {
        kassa.lataaRahaaKortille(kortti, -500);
        assertEquals(1000, kortti.saldo());
    }




}
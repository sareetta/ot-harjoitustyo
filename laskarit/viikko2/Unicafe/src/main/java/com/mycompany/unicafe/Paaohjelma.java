package com.mycompany.unicafe;

public class Paaohjelma {

    public static void main(String[] args) {
        Kassapaate unicafeExactum = new Kassapaate();
        Maksukortti kortti = new Maksukortti(10000);
        
        unicafeExactum.syoEdullisesti(kortti);
        
        System.out.println( unicafeExactum.edullisiaLounaitaMyyty() );
        System.out.println(kortti);

        Maksukortti kortti2 = new Maksukortti(1000);
        kortti2.lataaRahaa(220);
        System.out.println(kortti2);


    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BB.java.DBConnectionSample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bevásárlás {

    private static final String ARUSITO_FILE = "d:\\tmp\\f1\\vizsgafajlok\\prog\\arusitohelyek.txt";
    private static final String MEGYEK_FILE = "d:\\tmp\\f1\\vizsgafajlok\\prog\\megyek.txt"; // Fájlnév a megyékhez

    private List<Vásárok> vasarok;

    public Bevásárlás() {
        vasarok = new ArrayList<>();
    }

    public void beolvasAdatok() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(ARUSITO_FILE));
        
        br.readLine(); // Fejléc sor kihagyása

        String sor;
        while ((sor = br.readLine()) != null/* && vasarok.size() < 100*/) {
            String[] adatok = sor.split(";");
            if (adatok.length == 6) { // Ellenőrzés, hogy minden adat megvan-e
                Vásárok vasar = new Vásárok(adatok[0], adatok[1], adatok[2], adatok[3], adatok[4], adatok[5]);
                vasarok.add(vasar);
            }
        }
        br.close();
    }

    public int szamlalSorok() {
        return vasarok.size();
    }

    public int szamlalDelDunantuliermeloiPiacok() {
        int count = 0;
        
        for (Vásárok vasar : vasarok) {
            if (vasar.getTipus().equals("termelői piac") 
                    &&
                    IsDelDunantuli(vasar))
                 {
                count++;
            }
        }
        return count;
    }
    
    private static boolean IsDelDunantuli(Vásárok vasar)
    {
        return vasar.getMegye().equals("Baranya") || vasar.getMegye().equals("Somogy") || vasar.getMegye().equals("Tolna");
    }

    public String osszehasonlitPecsesSzekszard() {
        int pecsCount = 0;
        int szekszardCount = 0;
        for (Vásárok vasar : vasarok) {
            if (vasar.getTelepules().equals("Pécs")) {
                pecsCount++;
            } else if (vasar.getTelepules().equals("Szekszárd")) {
                szekszardCount++;
            }
        }

        if (pecsCount > szekszardCount) {
            return "Pécsett van több árusítóhely.";
        } else if (szekszardCount > pecsCount) {
            return "Szekszárdon van több árusítóhely.";
        } else {
            return "Egyenlő számú árusítóhely van Pécsett és Szekszárdon.";
        }
    }

    public List<String> szaszvariArusitohelyek() {
        List<String> szaszvariHelyek = new ArrayList<>();

        for (Vásárok vasar : vasarok) {
            if (vasar.getTelepules().equals("Szászváron")) {
                szaszvariHelyek.add(vasar.getNev() + " (" + vasar.getTipus() + ")");
            }
        }
        return szaszvariHelyek;
    }

    public boolean vanKirakodoVasar() {
        for (Vásárok vasar : vasarok) {
            if (vasar.getTipus().equals("kirakodóvásár")
                    || 
                vasar.getNev().toLowerCase().contains("kirakodóvásár")) {
                return true;
            }
        }
        return false;
    }

    public void megyenkentiArusitohelyek() throws IOException {
        Map<String, Integer> megyek = new HashMap<>();
        
        for (Vásárok vasar : vasarok) {
            String megye = vasar.getMegye();
            megyek.put(megye, megyek.getOrDefault(megye, 0) + 1);
        }
        
        // Kiírás a fájlba try-with-resources blokkal
        try (FileWriter fw = new FileWriter(MEGYEK_FILE)) {
            for (Map.Entry<String, Integer> entry : megyek.entrySet()) {
                fw.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        } // A fájl automatikusan lezáródik itt
       

        // Kiírás a konzolra
        System.out.println("8. Feladat: Megyénkénti árusítóhelyek száma:");
        for (Map.Entry<String, Integer> entry : megyek.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) throws IOException {
        Bevásárlás bevásárlás = new Bevásárlás();

        // 2. Feladat: Adatok beolvasása
        bevásárlás.beolvasAdatok();

        // 3. Feladat: Beolvasott sorok száma
        System.out.println("3. Feladat: " + bevásárlás.szamlalSorok() + " sort olvastam be.");

        // 4. Feladat: Termelői piacok száma
        System.out.println("4. Feladat: Termelői piacok száma a Dél-Dunántúlon: " + bevásárlás.szamlalDelDunantuliermeloiPiacok());

        // 5. Feladat: Pécs vagy Szekszárd
        System.out.println("5. Feladat: " + bevásárlás.osszehasonlitPecsesSzekszard());

        // 6. Feladat: Szászvári árusítóhelyek
        System.out.println("6. Feladat: Árusítóhelyek Szászváron:");
        for (String hely : bevásárlás.szaszvariArusitohelyek()) {
            System.out.println("    " + hely);
        }

        // 7. Feladat: Van-e kirakodóvásár?
        System.out.println("7. Feladat: Van kirakodóvásár: " + (bevásárlás.vanKirakodoVasar() ? "Igen" : "Nem"));

        // 8. Feladat: Megyénkénti árusítóhelyek
        bevásárlás.megyenkentiArusitohelyek();
    }
}
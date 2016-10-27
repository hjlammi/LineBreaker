/*
 * Lausekielinen ohjelmointi II, harjoitustyö 1.
 *
 * Heidi Lammi-Mihaljov, Lammi-Mihaljov.Heidi.J@student.uta.fi
 *
 * Viimeksi muokattu: 27.10.2016.
 *
 */

public class LineBreaker {
    public static void main(String[] args) {
        final int MINTEKSTINLEVEYS = 3;
        final char YES = 'y';
        final char NO = 'n';
        final char EROTIN = ' ';
        final String ERRORMESSAGE = "Error";
        final char RIVINPAATOSMERKKI = '/';

        int tekstialueenLeveys = 0;
        boolean syoteOk = false;
        String tekstisyote = "";
        int tekstisyotteenPituus = 0;
        int sananPituus = 0;
        int pisinSananPituus;
        boolean liianPitkaSana = true;
        char vastaus;
        boolean vastausOK = false;
        boolean erotinOk = true;
        boolean kaikkiRivitetty = false;

        // Kerrotaan käyttäjälle, että pätkitään rivejä.
        System.out.println("Hello! I break lines.");

        do {
            do {
                // Pyydetään käyttäjää syöttämään tekstialueen leveys.
                System.out.println("Enter area width:");
                // Luetaan tekstialueen leveys käyttäjältä.
                tekstialueenLeveys = In.readInt();
                // Jos tekstialueen leveys on suurempi tai yhtä suuri kuin
                // määritelty minimileveys, syöte on ok.
                if (tekstialueenLeveys >= MINTEKSTINLEVEYS) {
                    syoteOk = true;
                    do {
                        // Nollataan pisinSananPituus aina silmukan alussa, kun lähdetään
                        // pyytämään uutta merkkijonoa.
                        pisinSananPituus = 0;
                        // Käännetään silmukan alussa lippu.
                        erotinOk = true;
                        // Pyydetään käyttäjää antamaan merkkijono.
                        System.out.println("Enter a line:");
                        tekstisyote = In.readString();

                        tekstisyotteenPituus = tekstisyote.length();

                        for (int i = 0; i <= tekstisyotteenPituus; i++) {
                            // Jos i on yhtä suuri kuin tekstisyotteenPituus tai
                            // tekstisyote-merkkijonossa tulee vastaan välilyönti, suoritetaan if-lause.
                            if (i == tekstisyotteenPituus || tekstisyote.charAt(i) == EROTIN) {
                                // Jos sanan pituus on suurempi kuin siihen asti pisimmän sanan pituus
                                // niin nykyisen sanan pituus on pisin sanan pituus.
                                if (sananPituus > pisinSananPituus) {
                                    pisinSananPituus = sananPituus;
                                }
                                // Nollataan sananPituus.
                                sananPituus = 0;
                            // Jos i ei ole yhtä suuri kuin tekstisyotteenPituus eikä merkkijonossa
                            // tule vastaan välilyöntiä, kasvatetaan sananPituus-muuttujan arvoa.
                            } else {
                                sananPituus++;
                            }
                            // Syöte sisältää liian pitkän sanan, jos pisimmän sanan pituus on suurempi
                            // kuin tekstialueen leveys.
                            liianPitkaSana = pisinSananPituus >= tekstialueenLeveys;
                        }

                        for (int j = 0; j < tekstisyotteenPituus; j++) {
                            if (tekstisyote.charAt(0) == EROTIN
                            || (tekstisyote.charAt(tekstisyotteenPituus - 1) == EROTIN)
                            || ((tekstisyote.charAt(j) == EROTIN)
                            && (tekstisyote.charAt(j + 1) == EROTIN))) {
                                erotinOk = false;
                            }
                        }

                        // Jos pisimmän sanan pituus on suurempi kuin tekstialueen leveys tai
                        // välilyönti on väärässä paikassa, tulostetaan virheilmoitus.
                        // System.out.println("liianPitkaSana: " + liianPitkaSana);
                        // System.out.println("erotinOk: " + erotinOk);
                        if (liianPitkaSana || !erotinOk) {
                            System.out.println(ERRORMESSAGE);
                        }
                    // Suoritetaan silmukkaa niin kauan kuin merkkijonossa on liian pitkä osa
                    // tai jos välilyönti on väärässä paikassa.
                } while (liianPitkaSana || !erotinOk);
                } else {
                    System.out.println(ERRORMESSAGE);
                }
            // Suoritetaan silmukkaa kunnes käyttäjä syöttää kelvollisen tekstialueen leveyden.
            } while (!syoteOk);

            rivita(tekstisyote, tekstialueenLeveys);

            do {
                // Kysytään käyttäjältä, jatketaanko merkkijonojen rivittämistä.
                System.out.println("Continue (y/n)?");
                // Luetaan vastaus.
                // vastaus = In.readChar();
                vastaus = 'n';
                // Vastaus on ok jos se on YES tai NO.
                vastausOK = ((vastaus == YES) || (vastaus == NO));
                // Tutkitaan onko vastaus ok.
                if (vastausOK) {
                    // Jos vastaus on NO, niin tulostetaan heipat.
                    if (vastaus == NO) {
                        System.out.println("See you soon.");
                    }
                // Jos vastaus ei ole ok eli ei ole YES tai NO, tulostetaan virheilmoitus.
                } else {
                    System.out.println(ERRORMESSAGE);
                }
            // Tingataan vastausta niin kauan kuin vastaus ei ole ok.
            } while (!vastausOK);
        // Niin kauan kuin vastaus on YES, kysytään käyttäjältä uusia rivitettäviä merkkijonoja.
        } while (vastaus == YES);
    }

    public static void rivita(String syote, int tekstialueenLeveys) {
        final char RIVINPAATOSMERKKI = '/';
        final char EROTIN = ' ';

        int tekstisyotteenPituus = syote.length();
        boolean erotinLoytyi = false;
        int tutkittavaIndeksi;
        int rivinViimIndeksi = 0;
        boolean kaikkiRivitetty = false;
        int syotteenViimIndeksi = tekstisyotteenPituus - 1;
        boolean ekaRivi = true;
        int tulostetutMerkit = 0;
        int tulostettavaIndeksi = 0;

        do {
            // Erotinta ei ole vielä kierroksen alussa löytynyt.
            erotinLoytyi = false;

            // Jos on kyseessä ensimmäinen rivi, tutkittavaksi indeksiksi on
            // tekstialueen leveys - 1, muuten tutkittava indeksi on
            // rivin viimeinen indeksi + 1 (EROTIN) + tekstialueen leveys.
            if (ekaRivi) {
                tutkittavaIndeksi = tekstialueenLeveys - 1;
            } else {
                tutkittavaIndeksi = rivinViimIndeksi + 1 + tekstialueenLeveys;
            }

            // Silmukka tutkii, milloin rivi pitää katkaista.
            do {
                // Jos tutkittava indeksi on pienempi tai yhtä suuri kuin merkkijonon viimeisen
                // merkin indeksi, lähdetään rivin loppupäästä tutkimaan, milloin tulee vastaan ensimmäinen erotin.
                if (tutkittavaIndeksi <= syotteenViimIndeksi) {
                    // Jos tutkittavassa indeksissä oleva merkki on erotin, rivin viimeinen indeksi on
                    // tutkittavan indeksin arvo - 1 (eli vähennetään EROTIN).
                    if (syote.charAt(tutkittavaIndeksi) == EROTIN) {
                        rivinViimIndeksi = tutkittavaIndeksi - 1;
                        // Käännetään lippu.
                        erotinLoytyi = true;
                    }
                // Jos tutkittava indeksi suurempi kuin merkkijonon viimeinen indeksi
                // rivin viimeinen indeksi on merkkijonon viimeinen indeksi.
                } else {
                    rivinViimIndeksi = syotteenViimIndeksi;
                    // Käännetään lippu, jotta päästään silmukasta.
                    erotinLoytyi = true;
                }
                // Joka kierroksella vähennetään yksi, jotta päästään liikkumaan merkkijonossa taaksepäin.
                tutkittavaIndeksi--;
            // Tutkitaan indeksejä niin kauan kuin tutkittava indeksi on suurempi tai yhtäsuuri kuin nolla
            // ja erotinta ei ole vielä löytynyt.
            } while (tutkittavaIndeksi >= 0 && !erotinLoytyi);
            // System.out.println(rivinLoppu);

            // Esitellään ja nollataan laskuri, joka pitää kirjaa riville tulostetuista merkeistä.
            int rivinTulostetutMerkit = 0;

            // Niin kauan kuin tulostettava indeksi saavuttaa rivin viimeisen indeksin tulostetaan
            // merkki tulostettavasta indeksistä.
            while (tulostettavaIndeksi <= rivinViimIndeksi) {
                System.out.print(syote.charAt(tulostettavaIndeksi));
                // Kasvatetaan laskureita.
                rivinTulostetutMerkit++;
                tulostettavaIndeksi++;
            }
            // Kasvatetaan vielä tulostettavaa indeksiä yhdellä, jotta päästään seuraavalla kierroksella
            // erottimen ohi seuraavan rivin ekaan tulostettavaan merkkiin.
            tulostettavaIndeksi++;

            // Jos tulostettujen merkkien määrä on yhtä suuri tai suurempi kuin tekstisyotteenPituus,
            // on kaikki merkit tulostettu.
            if (tulostettavaIndeksi >= tekstisyotteenPituus) {
                kaikkiRivitetty = true;
            }

            // Esitellään ja alustetaan muuttuja, joka on yhtä kuin tekstialueen leveys
            // josta on vähennetty rivin tulostetut merkit sekä RIVINPAATOSMERKIN viemä yksi paikka.
            int riviaJaljella = tekstialueenLeveys - rivinTulostetutMerkit - 1;

            // Silmukassa tulostetaan erotinta niin kauan kuin rivissä on sille tilaa.
            for (int k = 0; k < riviaJaljella; k++) {
                System.out.print(EROTIN);
            }
            // Lopuksi tulostetaan päätösmerkki.
            System.out.println(RIVINPAATOSMERKKI);
            // Käännetään lippu, ensimmäisen rivin päättymisen merkiksi.
            ekaRivi = false;

        // Tulostetaan rivejä, niin kauan kuin kaikki on rivitetty.
        } while (!kaikkiRivitetty);
    }
}

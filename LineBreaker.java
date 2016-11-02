/*
 * Lausekielinen ohjelmointi II, harjoitustyö 1.
 *
 * Heidi Lammi-Mihaljov, Lammi-Mihaljov.Heidi.J@student.uta.fi
 *
 * Viimeksi muokattu: 2.11.2016.
 *
 * Ohjelma pyytää käyttäjältä tekstialueen leveyden ja tekstisyötteen ja rivittää
 */

public class LineBreaker {
    public static void main(String[] args) {
        // Esitellään ja alustetaan vakiot.
        final int MINTEKSTINLEVEYS = 3;
        final char YES = 'y';
        final char NO = 'n';
        final char EROTIN = ' ';
        final String ERRORMESSAGE = "Error!";
        final char RIVINPAATOSMERKKI = '/';

        // Esitellään ja alustetaan muut main-metodissa käytettävät muuttujat.
        int tekstialueenLeveys = 0;
        boolean syoteOk = false;
        String tekstisyote = "";
        int tekstisyotteenPituus = 0;
        boolean liianPitkaSana = true;
        char vastaus;
        boolean vastausOK = false;
        boolean erotinOk = true;

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
                        // Oletetaan aina silmukan alussa, että erotin on ok.
                        erotinOk = true;
                        // Pyydetään käyttäjää antamaan merkkijono.
                        System.out.println("Enter a line:");
                        tekstisyote = In.readString();

                        // Alustetaan tekstisyotteenPituus käyttäjän antaman tekstisyotteen
                        // mukaiseksi.
                        tekstisyotteenPituus = tekstisyote.length();

                        // Tarkistetaan erillisessä metodissa onko liian pitkiä sanoja,
                        // ja sijoitetaan tulos (true/false) liianPitkaSana-muuttujaan.
                        liianPitkaSana = tutkiOnkoLiianPitkiaSanoja(tekstisyote, EROTIN, tekstialueenLeveys);

                        // Tutkitaan, onko annettu tekstisyöte virheellinen eli onko
                        // erotinta väärässä paikassa. Suoritetaan silmukkaa niin kauan kuin
                        // laskuri on pienempi kuin tekstisyotteen pituus.
                        for (int j = 0; j < tekstisyotteenPituus; j++) {
                            // Jos ensimmäisessä indeksissä on erotin...
                            if (tekstisyote.charAt(0) == EROTIN
                            // ...tai jos viimeisessä indeksissä on erotin...
                            || (tekstisyote.charAt(tekstisyotteenPituus - 1) == EROTIN)
                            // ...tai jos jossain kohti tekstisyötettä on kaksi erotinta peräkkäin,
                            // tarkoittaa se sitä, että syöte ei ole oikeellinen eli että erotinok = false.
                            || ((tekstisyote.charAt(j) == EROTIN)
                            && (tekstisyote.charAt(j + 1) == EROTIN))) {
                                erotinOk = false;
                            }
                        }

                        // Jos pisimmän sanan pituus on suurempi kuin tekstialueen leveys tai
                        // välilyönti on väärässä paikassa, tulostetaan virheilmoitus.
                        if (liianPitkaSana || !erotinOk) {
                            System.out.println(ERRORMESSAGE);
                        }
                    // Suoritetaan silmukkaa niin kauan kuin merkkijonossa on liian pitkä sana
                    // tai jos erotin on väärässä paikassa.
                    } while (liianPitkaSana || !erotinOk);

                // Tulostetaan virheilmoitus, jos tekstialueen leveys on pienempi kuin
                // määritelty minimileveys.
                } else {
                    System.out.println(ERRORMESSAGE);
                }

            // Suoritetaan silmukkaa kunnes käyttäjä syöttää kelvollisen tekstialueen leveyden.
            } while (!syoteOk);

            // Tulostetaan rivita-metodin avulla rivitetty teksti.
            System.out.print(rivita(tekstisyote, EROTIN, tekstialueenLeveys, RIVINPAATOSMERKKI));

            do {
                // Kysytään käyttäjältä, jatketaanko merkkijonojen rivittämistä.
                System.out.println("Continue (y/n)?");
                // Luetaan vastaus.
                vastaus = In.readChar();
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

    // Metodissa etsitään syotteestä rivin viimeinen tulostettava indeksi.
    public static int etsiRivinViimTulostettavaIndeksi(String tekstisyote, char erotin, int tekstialueenLeveys, int rivinEnsimmainenIndeksi) {
        // Esitellään muuttujat ja alustetaan muuttujat.
        boolean erotinLoytyi = false;
        // Aloitetaan tutkiminen rivin ensimmäisestä indeksistä.
        int tutkittavaIndeksi = rivinEnsimmainenIndeksi;
        int rivinViimTulostettavaIndeksi = 0;
        // Rivin viimeiseksi tutkittavaksi indeksiksi määritellään rivin ensimmäisen indeksin ja
        // tekstialueen leveyden summa.
        int rivinViimTutkittavaIndeksi = rivinEnsimmainenIndeksi + tekstialueenLeveys;
        int syotteenViimIndeksi = tekstisyote.length() - 1;

        do {
            // Jos tekstisyötteessä tulee vastaan erotin, on rivin viimeinen tulostettava indeksi
            // kyseistä erottimellista indeksiä edeltävä indeksi.
            if (tekstisyote.charAt(tutkittavaIndeksi) == erotin) {
                rivinViimTulostettavaIndeksi = tutkittavaIndeksi - 1;
            // Jos taas tutkittava indeksi saavuttaa tekstisyotteen viimeisen indeksin,
            // on rivin viimeinen tulostettava indeksi tekstisyotteen viimeinen indeksi.
            // Siinä tapauksessa erotin on löytynyt ja halutaan silmukasta pois.
            } else if (tutkittavaIndeksi >= syotteenViimIndeksi) {
                rivinViimTulostettavaIndeksi = syotteenViimIndeksi;
                erotinLoytyi = true;
            }
            // Kasvatetaan tutkittavaa indeksiä yhdellä aina silmukan lopuksi.
            tutkittavaIndeksi++;
        // Etsitään rivin viimeistä indeksiä niin kauan kuin tutkittava indeksi on
        // pienempi tai yhtä suuri kuin rivin viimeinen tutkittava indeksi eli rivin loppu
        // tai kunnes erotin löytyy.
        } while (tutkittavaIndeksi <= rivinViimTutkittavaIndeksi && !erotinLoytyi);
        // Palautetaan metodin antama tulos.
        return rivinViimTulostettavaIndeksi;
    }

    // Metodi tutkii onko tekstisyötteessä liian pitkiä sanoja.
    public static boolean tutkiOnkoLiianPitkiaSanoja(String tekstisyote, char erotin, int tekstialueenLeveys) {
        // Esitellään ja alustetaan muuttujat.
        int tekstisyotteenPituus = tekstisyote.length();
        int sananPituus = 0;
        int pisinSananPituus = 0;

        // Tutkitaan onko liian pitkää sanaa, niin kauan kuin laskuri-i on pienempi tai yhtä
        // suuri kuin tekstisyotteenPituus.
        for (int i = 0; i <= tekstisyotteenPituus; i++) {
            // Jos i on yhtä suuri kuin tekstisyotteenPituus tai
            // tekstisyote-merkkijonossa tulee vastaan välilyönti, suoritetaan if-lause.
            if (i == tekstisyotteenPituus || tekstisyote.charAt(i) == erotin) {
                // Jos sanan pituus on suurempi kuin siihen asti pisimmän sanan pituus
                // niin nykyisen sanan pituus on pisin sanan pituus.
                if (sananPituus > pisinSananPituus) {
                    pisinSananPituus = sananPituus;
                }
                // Nollataan sananPituus ennen kuin aletaan tutkia seuraavan sanan pituutta..
                sananPituus = 0;
            // Jos i ei ole yhtä suuri kuin tekstisyotteenPituus eikä merkkijonossa
            // tule vastaan välilyöntiä, kasvatetaan sananPituus-muuttujan arvoa.
            } else {
                sananPituus++;
            }
        }
        // Esitellään muuttuja. Syöte sisältää liian pitkän sanan, jos pisimmän sanan pituus on suurempi
        // kuin tekstialueen leveys.
        boolean liianPitkaSana = pisinSananPituus > tekstialueenLeveys;

        // Palautetaan muuttujan saama arvo (true/false).
        return liianPitkaSana;
    }

    public static String rivinMerkit(String tekstisyote, char erotin, int tekstialueenLeveys, int rivinEnsimmainenIndeksi) {
        int tutkittavaIndeksi = rivinEnsimmainenIndeksi;
        String rivi = "";

        int rivinViimTulostettavaIndeksi = etsiRivinViimTulostettavaIndeksi(tekstisyote, erotin, tekstialueenLeveys, rivinEnsimmainenIndeksi);

        while (tutkittavaIndeksi <= rivinViimTulostettavaIndeksi) {
            rivi = rivi + tekstisyote.charAt(tutkittavaIndeksi);
            tutkittavaIndeksi++;
        }
        return rivi;
    }

    public static String taytaRivi(String vajaaRivi, int tekstialueenLeveys, char rivinpaatosmerkki) {
        String tulos = vajaaRivi;
        int erottimienMaara = tekstialueenLeveys - vajaaRivi.length();
        for (int i = 0; i < erottimienMaara; i++) {
            tulos = tulos + ' ';
        }
        tulos = tulos + rivinpaatosmerkki;
        return tulos;
    }

    public static String rivita(String tekstisyote, char erotin, int tekstialueenLeveys, char rivinpaatosmerkki) {
        int rivinEnsimmainenIndeksi = 0;
        String tulos = "";
        String rivi = "";
        do {
            rivi = rivinMerkit(tekstisyote, erotin, tekstialueenLeveys, rivinEnsimmainenIndeksi);
            rivinEnsimmainenIndeksi = rivinEnsimmainenIndeksi + rivi.length() - 1 + 2;
            rivi = taytaRivi(rivi, tekstialueenLeveys, rivinpaatosmerkki) + "\n";
            tulos = tulos + rivi;
        } while (rivinEnsimmainenIndeksi < tekstisyote.length());
        return tulos;
    }
}

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
        final int MINTEKSTINLEVEYS = 3;
        final char YES = 'y';
        final char NO = 'n';
        final char EROTIN = ' ';
        final String ERRORMESSAGE = "Error!";
        final char RIVINPAATOSMERKKI = '/';

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
                        // Oletetaan, että erotin on ok.
                        erotinOk = true;
                        // Pyydetään käyttäjää antamaan merkkijono.
                        System.out.println("Enter a line:");
                        tekstisyote = In.readString();

                        tekstisyotteenPituus = tekstisyote.length();

                        // Tarkistetaan onko liian pitkiä sanoja, ja sijoitetaan tulos (true/false)
                        // liianPitkaSana-muuttujaan.
                        liianPitkaSana = tutkiOnkoLiianPitkiaSanoja(tekstisyote, EROTIN, tekstialueenLeveys);

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

    public static int etsiRivinViimTulostettavaIndeksi(String syote, char erotin, int rivinPituus, int rivinEnsimmainenIndeksi) {
        boolean erotinLoytyi = false;
        int tutkittavaIndeksi = rivinEnsimmainenIndeksi;
        int rivinViimTulostettavaIndeksi = 0;
        int rivinViimTutkittavaIndeksi = rivinEnsimmainenIndeksi + rivinPituus;
        int syotteenViimIndeksi = syote.length() - 1;

        do {
            if (syote.charAt(tutkittavaIndeksi) == erotin) {
                rivinViimTulostettavaIndeksi = tutkittavaIndeksi - 1;
            } else if (tutkittavaIndeksi >= syotteenViimIndeksi) {
                rivinViimTulostettavaIndeksi = syotteenViimIndeksi;
                erotinLoytyi = true;
            }
            tutkittavaIndeksi++;
        } while (tutkittavaIndeksi <= rivinViimTutkittavaIndeksi && !erotinLoytyi);
        return rivinViimTulostettavaIndeksi;
    }

    public static boolean tutkiOnkoLiianPitkiaSanoja(String syote, char erotin, int rivinPituus) {
        int tekstisyotteenPituus = syote.length();
        int sananPituus = 0;
        int pisinSananPituus = 0;
        boolean liianPitkaSana = true;

        for (int i = 0; i <= tekstisyotteenPituus; i++) {
            // Jos i on yhtä suuri kuin tekstisyotteenPituus tai
            // syote-merkkijonossa tulee vastaan välilyönti, suoritetaan if-lause.
            if (i == tekstisyotteenPituus || syote.charAt(i) == erotin) {
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
            liianPitkaSana = pisinSananPituus > rivinPituus;
        }

        return liianPitkaSana;
    }

    public static String rivinMerkit(String syote, char erotin, int rivinPituus, int rivinEnsimmainenIndeksi) {
        int tutkittavaIndeksi = rivinEnsimmainenIndeksi;
        String rivi = "";

        int rivinViimTulostettavaIndeksi = etsiRivinViimTulostettavaIndeksi(syote, erotin, rivinPituus, rivinEnsimmainenIndeksi);

        while (tutkittavaIndeksi <= rivinViimTulostettavaIndeksi) {
            rivi = rivi + syote.charAt(tutkittavaIndeksi);
            tutkittavaIndeksi++;
        }
        return rivi;
    }

    public static String taytaRivi(String vajaaRivi, int rivinPituus, char rivinpaatosmerkki) {
        String tulos = vajaaRivi;
        int erottimienMaara = rivinPituus - vajaaRivi.length();
        for (int i = 0; i < erottimienMaara; i++) {
            tulos = tulos + ' ';
        }
        tulos = tulos + rivinpaatosmerkki;
        return tulos;
    }

    public static String rivita(String syote, char erotin, int rivinPituus, char rivinpaatosmerkki) {
        int rivinEnsimmainenIndeksi = 0;
        String tulos = "";
        String rivi = "";
        do {
            rivi = rivinMerkit(syote, erotin, rivinPituus, rivinEnsimmainenIndeksi);
            rivinEnsimmainenIndeksi = rivinEnsimmainenIndeksi + rivi.length() - 1 + 2;
            rivi = taytaRivi(rivi, rivinPituus, rivinpaatosmerkki) + "\n";
            tulos = tulos + rivi;
        } while (rivinEnsimmainenIndeksi < syote.length());
        return tulos;
    }
}

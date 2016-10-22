/*
 * Lausekielinen ohjelmointi II, harjoitustyö 1.
 *
 * Heidi Lammi-Mihaljov, Lammi-Mihaljov.Heidi.J@student.uta.fi
 *
 * Viimeksi muokattu: 17.10.2016.
 *
 */

public class LineBreaker {
    public static void main(String[] args) {
        final int MINTEKSTINLEVEYS = 3;
        final char YES = 'y';
        final char NO = 'n';
        final char EROTIN = ' ';
        final String ERRORMESSAGE = "Error";

        int tekstialueenLeveys = 0;
        boolean syoteOk = false;
        String tekstisyote = "";
        int tekstisyotteenPituus;
        int sananPituus = 0;
        int pisinSananPituus;
        boolean liianPitkaSana = true;
        char vastaus;
        boolean vastausOK = false;
        boolean valilyontiOk = true;

        // Kerrotaan käyttäjälle, että pätkitään rivejä.
        System.out.println("Hello! I break lines.");

        do {
            do {
                // Pyydetään käyttäjää syöttämään tekstialueen leveys.
                System.out.println("Enter area width:");
                // Luetaan tekstialueen leveys käyttäjältä.
                //tekstialueenLeveys = In.readInt();
                tekstialueenLeveys = 9;
                // Jos tekstialueen leveys on suurempi tai yhtä suuri kuin
                // määritelty minimileveys, syöte on ok.
                if (tekstialueenLeveys >= MINTEKSTINLEVEYS) {
                    syoteOk = true;
                    do {
                        // Nollataan pisinSananPituus aina silmukan alussa, kun lähdetään
                        // pyytämään uutta merkkijonoa.
                        pisinSananPituus = 0;
                        // Käännetään silmukan alussa lippu.
                        valilyontiOk = true;
                        // Pyydetään käyttäjää antamaan merkkijono.
                        System.out.println("Enter a line:");
                        // tekstisyote = In.readString();
                        tekstisyote = "testing, testing, one, two, three.";

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
                                valilyontiOk = false;
                            }
                        }

                        // Jos pisimmän sanan pituus on suurempi kuin tekstialueen leveys tai
                        // välilyönti on väärässä paikassa, tulostetaan virheilmoitus.
                        // System.out.println("liianPitkaSana: " + liianPitkaSana);
                        // System.out.println("valilyontiOk: " + valilyontiOk);
                        if (liianPitkaSana || !valilyontiOk) {
                            System.out.println(ERRORMESSAGE);
                        }
                    // Suoritetaan silmukkaa niin kauan kuin merkkijonossa on liian pitkä osa
                    // tai jos välilyönti on väärässä paikassa.
                    } while (liianPitkaSana || !valilyontiOk);
                } else {
                    System.out.println(ERRORMESSAGE);
                }
            // Suoritetaan silmukkaa kunnes käyttäjä syöttää kelvollisen tekstialueen leveyden.
            } while (!syoteOk);

            sananPituus = 0;
            for (int i = 0; i < tekstisyote.length(); i++) {
                System.out.print(tekstisyote.charAt(i));
                sananPituus++;
                if (tekstisyote.charAt(i) == EROTIN) {
                    for (int j = 0; (j < tekstisyote.length() - sananPituus - 1); j++) {
                        System.out.print(EROTIN);
                    }
                    System.out.println("/");
                    sananPituus = 0;
                }
            }

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
}

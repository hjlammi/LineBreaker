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

        int tekstialueenLeveys = 0;
        boolean syoteOk = false;
        String tekstisyote = "";
        char valilyonti = ' ';
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
                tekstialueenLeveys = In.readInt();
                // Jos tekstialueen leveys on suurempi tai yhtä suuri kuin
                // määritelty minimileveys, syöte on ok.
                if (tekstialueenLeveys >= MINTEKSTINLEVEYS) {
                    syoteOk = true;
                    do {
                        // Nollataan pisinSananPituus aina silmukan alussa, kun lähdetään
                        // pyytämään uutta merkkijonoa.
                        pisinSananPituus = 0;
                        // Pyydetään käyttäjää antamaan merkkijono.
                        System.out.println("Enter a line:");
                        tekstisyote = In.readString();

                        tekstisyotteenPituus = tekstisyote.length();

                        for (int i = 0; i <= tekstisyotteenPituus; i++) {
                            // Jos i on yhtä suuri kuin tekstisyotteenPituus tai
                            // tekstisyote-merkkijonossa tulee vastaan välilyönti, suoritetaan if-lause.
                            if (i == tekstisyotteenPituus || tekstisyote.charAt(i) == valilyonti) {
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
                            liianPitkaSana = pisinSananPituus > tekstialueenLeveys;
                        }

                        for (int j = 0; j <= tekstisyotteenPituus; j++) {
                            if (tekstisyote.charAt(0) == valilyonti || (tekstisyote.charAt(tekstisyotteenPituus - 1 ) == valilyonti)) {
                                valilyontiOk = false;
                            }
                        }

                        // Jos pisimmän sanan pituus on suurempi kuin tekstialueen leveys, tulostetaan virheilmoitus.
                        if (liianPitkaSana || !valilyontiOk) {
                            System.out.println("Error!");
                        }
                    // Suoritetaan silmukkaa niin kauan kuin merkkijonossa on liian pitkä osa.
                    } while (liianPitkaSana || !valilyontiOk);
                } else {
                    System.out.println("Error!");
                }
            // Suoritetaan silmukkaa kunnes käyttäjä syöttää kelvollisen tekstialueen leveyden.
            } while (!syoteOk);

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
                    System.out.println("Error!");
                }
            // Tingataan vastausta niin kauan kuin vastaus ei ole ok.
            } while (!vastausOK);
        // Niin kauan kuin vastaus on YES, kysytään käyttäjältä uusia rivitettäviä merkkijonoja.
        } while (vastaus == YES);
    }
}

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

        System.out.println("Hello! I break lines.");

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

        do {
            do {
                // Pyydetään käyttäjää syöttämään tekstialueen leveys.
                System.out.println("Enter area width:");
                // Luetaan tekstialueen leveys käyttäjältä.
                tekstialueenLeveys = In.readInt();
                // Jos tekstialueen leveys on suurempi tai yhtä suuri kuin
                // määritelty minimileveys...
                if (tekstialueenLeveys >= MINTEKSTINLEVEYS) {
                    do {
                        pisinSananPituus = 0;
                        System.out.println("Enter a line:");
                        syoteOk = true;
                        tekstisyote = In.readString();
                        tekstisyotteenPituus = tekstisyote.length();

                        for (int i = 0; i <= tekstisyotteenPituus; i++) {
                            liianPitkaSana = pisinSananPituus > tekstialueenLeveys;
                            if (i == tekstisyotteenPituus || tekstisyote.charAt(i) == valilyonti) {
                                if (sananPituus > pisinSananPituus) {
                                    pisinSananPituus = sananPituus;
                                }
                                sananPituus = 0;
                            } else {
                                sananPituus++;
                            }
                        }
                        if (liianPitkaSana) {
                            System.out.println("Error!");
                        }
                    } while (liianPitkaSana);
                } else {
                    System.out.println("Error!");
                }
            } while (!syoteOk);
            // Kysytään käyttäjältä, jatketaanko merkkijonojen rivittämistä.
            System.out.println("Continue (y/n)?");
            // Luetaan vastaus
            vastaus = In.readChar();
            do {
                vastausOK = ((vastaus == YES) || (vastaus == NO));
                if (vastaus == NO) {
                    System.out.println("See you soon.");
                    }
            } while (!vastausOK);
        } while (vastaus == YES);

    }
}

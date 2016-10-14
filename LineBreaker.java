/*
 * Lausekielinen ohjelmointi II, harjoitustyö 1.
 *
 * Heidi Lammi-Mihaljov, Lammi-Mihaljov.Heidi.J@student.uta.fi
 *
 * Viimeksi muokattu: 13.10.2016.
 *
 */

public class LineBreaker {
    public static void main(String[] args) {
        final int MINTEKSTINLEVEYS = 3;

        System.out.println("Hello! I break lines.");

        // Jos uudestaan true,
        boolean syoteOk = false;
        String tekstisyote = "";
        char valilyonti = ' ';
        int tekstisyotteenPituus = tekstisyote.length();
        int sananPituus = tekstisyotteenPituus + 1;
        int pisinSananPituus = tekstisyotteenPituus + 1;

        do {
            // Pyydetään käyttäjää syöttämään tekstialueen leveys.
            System.out.println("Enter area width:");
            // Luetaan tekstialueen leveys käyttäjältä.
            int tekstialueenLeveys = In.readInt();
            // Jos tekstialueen leveys on suurempi tai yhtä suuri kuin
            // määritelty minimileveys...
            if (tekstialueenLeveys >= MINTEKSTINLEVEYS) {
                do {
                    System.out.println("Enter a line:");
                    syoteOk = true;
                    tekstisyote = In.readString();

                        for (int i = 0; i < tekstisyotteenPituus; i++) {
                            if (i == tekstisyotteenPituus || tekstisyote.charAt(i) == valilyonti) {
                                if (sananPituus < pisinSananPituus) {
                                    pisinSananPituus = sananPituus;
                                }
                            }
                            sananPituus++;
                        }

                } while (pisinSananPituus > tekstialueenLeveys);
            } else {
                System.out.println("Error!");
            }
        } while (!syoteOk);
    }
}

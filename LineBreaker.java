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
        System.out.println("Hello! I break lines.");

        // Jos uudestaan true,
        boolean uudestaan = true;

        // Pyydetään käyttäjää syöttämään tekstialueen leveys.
        System.out.println("Enter area width:");
        // Luetaan tekstialueen leveys käyttäjältä.
        int tekstialueenLeveys = In.readInt();
        if (tekstialueenLeveys >= 3) {
            System.out.println("Enter a line:");
            String tekstirivi = In.readString();
        } else {
            System.out.println("Error!");
        }
    }
}

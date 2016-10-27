
public class LineBreakerTesti {
    public static void main(String[] args) {
        final char RIVINPAATOSMERKKI = '/';
        final char EROTIN = ' ';

        String syote = "hello, world, he said, and lorem ipsum dolor sit amet vähän lisää lol bitte schön";
        int tekstisyotteenPituus = syote.length();
        int tekstialueenLeveys = 15;
        boolean erotinLoytyi = false;
        int tutkittavaIndeksi;
        int rivinViimIndeksi = 0;
        int tulostetutMerkit = 0;
        boolean kaikkiRivitetty = false;
        int syotteenViimIndeksi = tekstisyotteenPituus - 1;
        boolean ekaRivi = true;

        // System.out.println(tekstisyotteenPituus);
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

            // System.out.println(tutkittavaIndeksi);
            // System.out.println(syote.charAt(tutkittavaIndeksi));
            // Silmukka tutkii, milloin rivi pitää katkaista.
            do {
                // Jos tutkittava indeksi on pienempi tai yhtä suuri kuin merkkijonon viimeisen
                // merkin indeksi, lähdetään rivin loppupäästä tutkimaan, milloin tulee vastaan ensimmäinen erotin.
                if (tutkittavaIndeksi <= syotteenViimIndeksi) {
                // System.out.println(syote.charAt(i));
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


            int rivinTulostetutMerkit = 0;
            int tulostettavaIndeksi = 0;
            // System.out.println("rivitetyt" + tulostetutMerkit);
            // Niin kauan kuin tulostettavaIndeksi on pienempi kuin rivin loppu,
            // tulostetaan merkki indeksistä, joka on kaikkien tulostettujen merkkien ja
            // kyseisen rivin tulostettujen merkien summa.
            while (tulostettavaIndeksi < rivinViimIndeksi) {
                if (ekaRivi) {
                    tulostettavaIndeksi = tulostetutMerkit + rivinTulostetutMerkit;
                } else {
                    tulostettavaIndeksi = tulostetutMerkit + rivinTulostetutMerkit + 1;
                }
                // System.out.print(rivinTulostetutMerkit + " ");
                System.out.print(syote.charAt(tulostettavaIndeksi));
                rivinTulostetutMerkit++;
            }
            // System.out.println(rivinTulostetutMerkit);
            // Merkkejä on tulostettu riviin rivin viimeisen merkin indeksin + 1 verran.
            tulostetutMerkit = rivinViimIndeksi + 1;
            // Jos tulostettujen merkkien määrä on yhtä suuri kuin tekstisyotteenPituus, on kaikki merkit
            // tulostettu.
            if (tulostetutMerkit == tekstisyotteenPituus) {
                kaikkiRivitetty = true;
            }

            int riviaJaljella = tekstialueenLeveys - rivinTulostetutMerkit - 1;
            // System.out.println();
            // System.out.println(rivinLoppu);
            // System.out.println(riviaJaljella);

            for (int k = 0; k < riviaJaljella; k++) {
                System.out.print(EROTIN);
            }
            System.out.println(RIVINPAATOSMERKKI);
            ekaRivi = false;

        } while (!kaikkiRivitetty);
    }
}

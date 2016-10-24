
public class LineBreakerTesti {
    public static void main(String[] args) {
        String syote = "hello, world, he said, and lorem ipsum dolor sit amet vähän lisää lol bitte schön";
        int tekstisyotteenPituus = syote.length();
        int tekstialueenLeveys = 15;
        boolean erotinLoytyi = false;
        final char EROTIN = ' ';
        int tutkittavaIndeksi;
        int rivinViimIndeksi = 0;
        int tulostetutMerkit = 0;
        boolean kaikkiRivitetty = false;
        int syotteenViimIndeksi = tekstisyotteenPituus - 1;
        boolean ekaRivi = true;

        // System.out.println(tekstisyotteenPituus);
        do {
            // Erotinta ei ole vielä löytynyt.
            erotinLoytyi = false;

            if (ekaRivi) {
                tutkittavaIndeksi = tekstialueenLeveys;
            } else {
                tutkittavaIndeksi = rivinViimIndeksi + 1 + tekstialueenLeveys;
            }

            // System.out.println(tutkittavaIndeksi);
            do {
                if (tutkittavaIndeksi <= syotteenViimIndeksi) {
                // System.out.println(syote.charAt(i));
                // Lähdetään rivin loppupäästä tutkimaan, milloin tulee vastaan ensimmäinen erotin.
                // Jos tutkittavassa indeksissä oleva merkki on erotin, rivinLoppu-muuttuja saa
                // tutkittavan indeksin arvon.
                    if (syote.charAt(tutkittavaIndeksi) == EROTIN) {
                        rivinViimIndeksi = tutkittavaIndeksi - 1;
                        // Käännetään lippu.
                        erotinLoytyi = true;
                    }
                } else {
                    rivinViimIndeksi = syotteenViimIndeksi;
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
            // Niin kauan kuin tulostettavaIndeksi on pienempi kuin rivin loppu eli
            // rivin katkaiseva erotin, tulostetaan merkki indeksistä, joka on jo
            // rivitettyjen merkkien ja laskuri-rivinTulostetutMerkit:n summa.
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

            char rivinPaatosMerkki = '/';
            int riviaJaljella = tekstialueenLeveys - rivinTulostetutMerkit - 1;
            // System.out.println();
            // System.out.println(rivinLoppu);
            // System.out.println(riviaJaljella);

            for (int k = 0; k < riviaJaljella; k++) {
                System.out.print(EROTIN);
            }
            System.out.println(rivinPaatosMerkki);
            ekaRivi = false;

        } while (!kaikkiRivitetty);
    }
}

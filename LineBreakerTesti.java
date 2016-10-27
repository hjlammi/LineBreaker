
public class LineBreakerTesti {
    public static void main(String[] args) {
        final char RIVINPAATOSMERKKI = '/';
        final char EROTIN = ' ';

        String syote = "hello, world, he said, and lorem ipsum dolor sit amet vähän lisää lol bitte schön";
        int tekstisyotteenPituus = syote.length();
        int tekstialueenLeveys = 14;
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

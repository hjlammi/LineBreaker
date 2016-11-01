public class LineBreaker4 {
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

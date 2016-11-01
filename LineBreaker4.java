public class LineBreaker4 {
    public static int etsiRivinViimTulostettavaIndeksi(String syote, char erotin, int rivinPituus, int rivinEnsimmainenIndeksi) {
        boolean erotinLoytyi = false;
        int tutkittavaIndeksi = rivinEnsimmainenIndeksi;
        int rivinViimTulostettavaIndeksi = 0;
        int rivinViimTutkittavaIndeksi = rivinEnsimmainenIndeksi + rivinPituus;

        do {
            if (syote.charAt(tutkittavaIndeksi) == erotin) {
                rivinViimTulostettavaIndeksi = tutkittavaIndeksi - 1;
            }
            tutkittavaIndeksi++;
        } while (tutkittavaIndeksi <= rivinViimTutkittavaIndeksi);
        return rivinViimTulostettavaIndeksi;
    }
}

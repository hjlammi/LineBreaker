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
}

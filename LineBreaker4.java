public class LineBreaker4 {
    public static int etsiRivinViimTulostettavaIndeksi(String syote, char erotin, int rivinPituus) {
        boolean erotinLoytyi = false;
        int tutkittavaIndeksi = 0;
        int rivinViimTulostettavaIndeksi = 0;
        int rivinViimTutkittavaIndeksi = 8;

        do {
            if (syote.charAt(tutkittavaIndeksi) == erotin) {
                rivinViimTulostettavaIndeksi = tutkittavaIndeksi - 1;
            }
            tutkittavaIndeksi++;
        } while (tutkittavaIndeksi <= rivinViimTutkittavaIndeksi);
        return rivinViimTulostettavaIndeksi;
    }
}

import static org.junit.Assert.*;

import org.junit.Test;

public class LineBreakerTest {

    @Test
    public void viimIndeksiEkanRivinLopussa() {
        assertEquals(7, LineBreaker.etsiRivinViimTulostettavaIndeksi("Testing, testing. One, two, three.", ' ', 8, 0));
    }

    @Test
    public void viimIndeksiEnnenEkanRivinLopussa() {
        assertEquals(4, LineBreaker.etsiRivinViimTulostettavaIndeksi("Test, test. One, two, three.", ' ', 8, 0));
    }

    @Test
    public void viimIndeksiMontaSanaaEkallaRivilla() {
        assertEquals(6, LineBreaker.etsiRivinViimTulostettavaIndeksi("One two three four five.", ' ', 8, 0));
    }

    @Test
    public void viimIndeksiTokanRivinLopussa() {
        assertEquals(16, LineBreaker.etsiRivinViimTulostettavaIndeksi("Testing, testing. One, two, three.", ' ', 8, 9));
    }

    @Test
    public void viimIndeksiSyotteenLopussa() {
        assertEquals(33, LineBreaker.etsiRivinViimTulostettavaIndeksi("Testing, testing. One, two, three.", ' ', 8, 28));
    }

    @Test
    public void viimIndeksiRivinPituus13() {
        assertEquals(16, LineBreaker.etsiRivinViimTulostettavaIndeksi("Testing, testing. One, two, three.", ' ', 20, 0));
    }

    @Test
    public void eiLiianPitkiaSanoja() {
        assertEquals(false, LineBreaker.tutkiOnkoLiianPitkiaSanoja("Testing, testing. One, two, three.", ' ', 8));
    }

    @Test
    public void liianPitkaSana() {
        assertEquals(true, LineBreaker.tutkiOnkoLiianPitkiaSanoja("Teersting, testing. One, two, three.", ' ', 8));
    }

    @Test
    public void ekanRivinTulostus() {
        assertEquals("Testing,", LineBreaker.rivinMerkit("Testing, testing. One, two, three.", ' ', 8, 0));
    }

    @Test
    public void tokanRivinTulostus() {
        assertEquals("testing.", LineBreaker.rivinMerkit("Testing, testing. One, two, three.", ' ', 8, 9));
    }

    @Test
    public void vikanRivinTulostus() {
        assertEquals("three.", LineBreaker.rivinMerkit("Testing, testing. One, two, three.", ' ', 8, 28));
    }

    @Test
    public void vikanRivinTulostusRivinPituus20() {
        assertEquals("three.", LineBreaker.rivinMerkit("Testing, testing. One, two, three.", ' ', 20, 28));
    }

    @Test
    public void tokanRivinTulostusRivinPituus20() {
        assertEquals("testing. One,", LineBreaker.rivinMerkit("Testing, testing. One, two, three.", ' ', 15, 9));
    }

    @Test
    public void taytaTaysiRivi () {
        assertEquals("Testing,/", LineBreaker.taytaRivi("Testing,", 8, '/'));
    }

    @Test
    public void taytaVajaaRivi () {
        assertEquals("One, two   /", LineBreaker.taytaRivi("One, two", 11, '/'));
    }

    @Test
    public void rivitaTesting8() {
        String syote = "Testing, testing. One, two, three.";
        String odotettu = "Testing,/\n" +
                          "testing./\n" +
                          "One,    /\n" +
                          "two,    /\n" +
                          "three.  /\n";
        assertEquals(odotettu, LineBreaker.rivita(syote, ' ', 8, '/'));
    }

    @Test
    public void rivitaJavaVitsi15() {
        String syote = "Q: What did the Java code say to the C code. A: You have got no class.";
        String odotettu = "Q: What did the/\n" +
                          "Java code say  /\n" +
                          "to the C code. /\n" +
                          "A: You have got/\n" +
                          "no class.      /\n";
        assertEquals(odotettu, LineBreaker.rivita(syote, ' ', 15, '/'));
    }
}

import static org.junit.Assert.*;

import org.junit.Test;

public class LineBreakerTest {

    @Test
    public void viimIndeksiEkanRivinLopussa() {
        assertEquals(7, LineBreaker4.etsiRivinViimTulostettavaIndeksi("Testing, testing. One, two, three.", ' ', 8, 0));
    }

    @Test
    public void viimIndeksiEnnenEkanRivinLopussa() {
        assertEquals(4, LineBreaker4.etsiRivinViimTulostettavaIndeksi("Test, test. One, two, three.", ' ', 8, 0));
    }

    @Test
    public void viimIndeksiMontaSanaaEkallaRivilla() {
        assertEquals(6, LineBreaker4.etsiRivinViimTulostettavaIndeksi("One two three four five.", ' ', 8, 0));
    }

    @Test
    public void viimIndeksiTokanRivinLopussa() {
        assertEquals(16, LineBreaker4.etsiRivinViimTulostettavaIndeksi("Testing, testing. One, two, three.", ' ', 8, 9));
    }

    @Test
    public void viimIndeksiSyotteenLopussa() {
        assertEquals(33, LineBreaker4.etsiRivinViimTulostettavaIndeksi("Testing, testing. One, two, three.", ' ', 8, 28));
    }

    @Test
    public void viimIndeksiRivinPituus13() {
        assertEquals(16, LineBreaker4.etsiRivinViimTulostettavaIndeksi("Testing, testing. One, two, three.", ' ', 20, 0));
    }

    @Test
    public void eiLiianPitkiaSanoja() {
        assertEquals(false, LineBreaker4.tutkiOnkoLiianPitkiaSanoja("Testing, testing. One, two, three.", ' ', 8));
    }

    @Test
    public void liianPitkaSana() {
        assertEquals(true, LineBreaker4.tutkiOnkoLiianPitkiaSanoja("Teersting, testing. One, two, three.", ' ', 8));
    }

    @Test
    public void ekanRivinTulostus() {
        assertEquals("Testing,", LineBreaker4.rivinMerkit("Testing, testing. One, two, three.", ' ', 8, 0));
    }

    @Test
    public void tokanRivinTulostus() {
        assertEquals("testing.", LineBreaker4.rivinMerkit("Testing, testing. One, two, three.", ' ', 8, 9));
    }

    @Test
    public void vikanRivinTulostus() {
        assertEquals("three.", LineBreaker4.rivinMerkit("Testing, testing. One, two, three.", ' ', 8, 28));
    }

    @Test
    public void vikanRivinTulostusRivinPituus20() {
        assertEquals("three.", LineBreaker4.rivinMerkit("Testing, testing. One, two, three.", ' ', 20, 28));
    }

    @Test
    public void tokanRivinTulostusRivinPituus20() {
        assertEquals("testing. One,", LineBreaker4.rivinMerkit("Testing, testing. One, two, three.", ' ', 15, 9));
    }
}

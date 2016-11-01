import static org.junit.Assert.*;

import org.junit.Test;

public class LineBreakerTest {

    @Test
    public void viimIndeksiEkanRivinLopussa() {
        assertEquals(7, LineBreaker4.etsiRivinViimTulostettavaIndeksi("Testing, testing. One, two, three.", ' ', 8));
    }

    @Test
    public void viimIndeksiEnnenEkanRivinLopussa() {
        assertEquals(4, LineBreaker4.etsiRivinViimTulostettavaIndeksi("Test, test. One, two, three.", ' ', 8));
    }
    @Test
    public void viimIndeksiMontaSanaaEkallaRivilla() {
        assertEquals(6, LineBreaker4.etsiRivinViimTulostettavaIndeksi("One two three four five.", ' ', 8));
    }
}

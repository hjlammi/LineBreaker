import static org.junit.Assert.*;

import org.junit.Test;

public class LineBreakerTest {

    @Test
    public void ekanRivinViimIndeksi() {
        assertEquals(7, LineBreaker4.etsiRivinViimTulostettavaIndeksi("Testing, testing. One, two, three.", ' ', 8));
    }

}

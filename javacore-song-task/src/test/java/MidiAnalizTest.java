import org.junit.Before;
import org.junit.Test;
import ru.liga.songtask.analiz.MidiAnaliz;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class MidiAnalizTest {
    private MidiAnaliz ma;

    @Before
    public void setUp() {
        ma = new MidiAnaliz("C:\\Users\\123\\Documents\\JavaLige\\day_5\\dz 5\\liga-internship\\zombie.txt");
    }

    @Test
    public void AnalizOfAmmountAndDurations() {
        assertThat(ma.getKol()).isEqualTo(289);
        assertThat(ma.getDurations()).isEqualTo(210);
    }

    @Test
    public void DiapozonEquals17() {
        ma.noteDiapozon();
        assertThat(ma.getDiap()).isEqualTo(17);
    }

    @Test
    public void hiNoteEqualsA() {
        ma.noteDiapozon();
        assertThat(ma.hiNote()).isEqualTo("A");
    }

    @Test
    public void loNoteEqualsA() {
        ma.noteDiapozon();
        assertThat(ma.loNote()).isEqualTo("E");
    }

    @Test
    public void largestTimeEquals2926() {
        Map<Long, Integer> map = ma.noteTime();
        Object[] dlit = map.keySet().toArray();
        assertThat((Long) dlit[dlit.length - 1]).isEqualTo(2926);
    }

    @Test
    public void smallestTimeEquals189() {
        Map<Long, Integer> map = ma.noteTime();
        Object[] dlit = map.keySet().toArray();
        assertThat((Long) dlit[0]).isEqualTo(182);
    }
}
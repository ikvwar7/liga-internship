import org.junit.Test;
import ru.liga.App;

import java.io.*;

import static org.assertj.core.api.Assertions.*;

public class ArgumentParserTest {

    @Test
    public void whenFlagFReturnTxtFile() {
        //String[] args = new String[]{"C:\\Users\\123\\Documents\\JavaLige\\day_5\\dz 5\\liga-internship\\zombie.txt", "analize", "-f"};
        String[] args = new String[]{"src\\main\\resources\\zombie.txt", "analize", "-f"};
        App.main(args);
        //File file = new File("C:\\Users\\123\\Documents\\JavaLige\\day_5\\dz 5\\liga-internship\\out.txt");
        File file = new File("src\\main\\resources\\out.txt");
        assertThat(file.exists()).isEqualTo(true);
    }

    @Test
    public void whenNotFlagFReturnLogFile() {
        String[] args = new String[]{"src\\main\\resources\\zombie.txt", "analize"};
        App.main(args);
        File file = new File("C:\\Users\\123\\Documents\\JavaLige\\day_5\\dz 5\\liga-internship\\javacore-song-task\\logFile.log");
        assertThat(file.exists()).isEqualTo(true);
    }

    @Test
    public void whenHaveChangeReturnChangeMidiFile() {
        String[] args = new String[]{"src\\main\\resources\\zombie.txt", "change", "-trans", "2", "-tempo", "20"};
        App.main(args);
        File file = new File("src\\main\\resources\\zombi-trans2-tempo20.mid");
        assertThat(file.exists()).isEqualTo(true);
    }
}
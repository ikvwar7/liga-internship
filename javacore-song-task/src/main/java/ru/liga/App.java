package ru.liga;

import ru.liga.songtask.analiz.Change;
import ru.liga.songtask.analiz.MyClass;
import ru.liga.songtask.analiz.Output;
import ru.liga.songtask.analiz.WriteToFile;
import ru.liga.songtask.content.Content;
import ru.liga.songtask.domain.SimpleMidiFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import java.io.*;

import static ru.liga.songtask.analiz.Output.*;

/**
 * Всего нот: 15
 * <p>
 * Анализ диапазона:
 * верхняя: E4
 * нижняя: F3
 * диапазон: 11
 * <p>
 * Анализ длительности нот (мс):
 * 4285: 10
 * 2144: 5
 * <p>
 * Анализ нот по высоте:
 * E4: 3
 * D4: 3
 * A3: 3
 * G3: 3
 * F3: 3
 * <p>
 * Анализ интервалов:
 * 2: 9
 * 5: 3
 * 11: 2
 */
public class App {

    public static Logger logger = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        File file = new File("C:\\Users\\123\\Documents\\JavaLige\\day_4\\dz_2\\liga-internship\\zombie.txt");
        SimpleMidiFile simpleMidiFile = new SimpleMidiFile(file);
        MyClass mc = new MyClass("");
        WriteToFile fou = new WriteToFile("C:\\Users\\123\\Documents\\JavaLige\\day_4\\dz_2\\liga-internship\\out.txt");
        if (args.length == 3 && args[2].compareTo("-f") == 0) {
            fou.write("Количество нот: ");
            fou.write(simpleMidiFile.vocalNoteList().size());
            fou.write(System.lineSeparator());
            fou.write("Длительность (сек): ");
            fou.write(Long.toString(simpleMidiFile.durationMs() / 1000));
            fou.write(System.lineSeparator());
            diap(fou, mc);
            time(fou, mc);
            high(fou, mc);
            interval(fou, mc);
            fou.close();
        } else if (args.length == 2 && args[1].compareTo("analize") == 0) {
            diap(logger, mc);
            time(logger, mc);
            high(logger, mc);
            interval(logger, mc);
        } else if (args.length == 6) {
            Integer trans = Integer.parseInt(args[3]);
            Integer temp = Integer.parseInt(args[5]);
            Change ch = new Change(trans, temp);
            String path = "C:\\Users\\123\\Documents\\JavaLige\\day_4\\dz_2\\liga-internship\\" + "zombie" + args[2].trim() + args[3].trim() + args[4].trim() + args[5].trim() + ".txt";
            ch.change_midi(path);
        }
    }
}


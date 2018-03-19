package ru.liga.songtask.ArgumentParser;

import ru.liga.songtask.analiz.Change;
import ru.liga.songtask.analiz.MidiAnaliz;
import ru.liga.songtask.analiz.WriteToFile;

import java.io.File;

import static ru.liga.songtask.analiz.Output.*;


public class ArgumentParser {
    private final File file;
    private final MidiAnaliz ma;
    private final String path;
    private final String pathOut;
    private final WriteToFile fou;

    public ArgumentParser(String[] args) {
        file = new File(args[0]);
        ma = new MidiAnaliz(args[0]);
        path = file.getAbsolutePath().replaceAll(file.getName(), "");
        pathOut = path + "out.txt";
        fou = new WriteToFile(pathOut);
    }

    public void argsStrategy(String[] args) {
        if (args.length == 3 && args[2].compareTo("-f") == 0) {
            ma.allAnaliz(fou, "Количество нот: ", "Длительность (сек): ");
            noteDiap(fou, ma);
            noteTime(fou, ma);
            noteHigh(fou, ma);
            noteInterval(fou, ma);
            fou.close();
        } else if (args.length == 2 && args[1].compareTo("analize") == 0) {
            noteDiap(ma);
            noteTime(ma);
            noteHigh(ma);
            noteInterval(ma);
        } else if (args.length == 6 && args[1].compareTo("change") == 0 && args[2].compareTo("-trans") == 0) {
            Integer trans = Integer.parseInt(args[3]);
            Integer temp = Integer.parseInt(args[5]);
            Change ch = new Change(trans, temp, args[0]);
            String pa = file.getName();
            String pathOutMid = path + pa.substring(0, pa.lastIndexOf('.') - 1) + args[2].trim() + args[3].trim() + args[4].trim() + args[5].trim() + ".mid";
            ch.changeMidi();
            ch.save(pathOutMid);
        }
    }
}

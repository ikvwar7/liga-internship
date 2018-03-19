package ru.liga.songtask.analiz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.Set;

public class Output {
    private static Logger logger = LoggerFactory.getLogger(Output.class);

    public static void noteDiap(WriteToFile fou, MidiAnaliz mc) {
        fou.write("Анализ диапазона:");
        fou.write(System.lineSeparator());
        Map<String, String> nd = mc.noteDiapozon();
        nd.forEach((key, value) -> fou.write(key + ": " + value + "\r\n"));
    }

    public static void noteDiap(MidiAnaliz mc) {
        logger.info("Анализ диапазона:");
        Map<String, String> nd = mc.noteDiapozon();
        nd.forEach((key, value) -> logger.info(key + ": " + value));
    }

    public static void noteTime(WriteToFile fou, MidiAnaliz mc) {
        fou.write("Анализ длительности нот (мс):");
        fou.write(System.lineSeparator());
        Map<Long, Integer> nt = mc.noteTime();
        nt.forEach((key, value) -> fou.write(key + ": " + value + "\r\n"));
    }


    public static void noteTime(MidiAnaliz mc) {
        logger.info("Анализ длительности нот (мс):");
        Map<Long, Integer> nt = mc.noteTime();
        Set<Map.Entry<Long, Integer>> set = nt.entrySet();
        nt.forEach((key, value) -> logger.info(key + ": " + value));
    }


    public static void noteHigh(WriteToFile fou, MidiAnaliz mc) {
        fou.write("Анализ нот по высоте:");
        fou.write(System.lineSeparator());
        Map<Integer, String> nh = mc.noteHigh();
        nh.forEach((key, value) -> fou.write(key + ": " + value + "\r\n"));
    }

    public static void noteHigh(MidiAnaliz mc) {
        logger.info("Анализ нот по высоте:");
        Map<Integer, String> nh = mc.noteHigh();
        nh.forEach((key, value) -> logger.info(key + ": " + value));
    }

    public static void noteInterval(WriteToFile fou, MidiAnaliz mc) {
        fou.write("Анализ интервалов:");
        fou.write(System.lineSeparator());
        Map<Integer, Integer> ni = mc.noteInterval();
        ni.forEach((key, value) -> fou.write(key + ": " + value + "\r\n"));
    }

    public static void noteInterval(MidiAnaliz mc) {
        logger.info("Анализ интервалов:");
        Map<Integer, Integer> ni = mc.noteInterval();
        ni.forEach((key, value) -> logger.info(key + ": " + value));
    }
}

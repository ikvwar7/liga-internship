package ru.liga.songtask.analiz;
import org.slf4j.Logger;
import ru.liga.App;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Output {
    public static void diap(WriteToFile fou, MyClass mc) {
        fou.write("Анализ диапазона:");
        fou.write(System.lineSeparator());
        Map<String, String> nd = mc.noteDiapozon();
        Object[] keys = nd.keySet().toArray();
        Object[] val = nd.values().toArray();
        for (int i = 0; i < keys.length - 1; i++) {
            String o = (String) keys[i];
            fou.write(o);
            fou.write(System.lineSeparator());
        }
        fou.write((String) keys[keys.length - 1]);
        fou.write((String) val[val.length - 1]);
        fou.write(System.lineSeparator());
    }

    public static void diap(Logger logger, MyClass mc) {
        App.logger.info("Анализ диапазона:");
        Map<String, String> nd = mc.noteDiapozon();
        Object[] keys = nd.keySet().toArray();
        Object[] val = nd.values().toArray();
        for (int i = 0; i < keys.length - 1; i++) {
            String o = (String) keys[i];
            logger.info(o);
        }
        App.logger.info((String) keys[keys.length - 1] + (String) val[val.length - 1]);
    }

    public static void time(WriteToFile fou, MyClass mc) {
        fou.write("Анализ длительности нот (мс):");
        fou.write(System.lineSeparator());
        Map<Long, Integer> nt = mc.noteTime();
        Set<Map.Entry<Long, Integer>> set = nt.entrySet();
        Iterator<Map.Entry<Long, Integer>> iter = set.iterator();
        while (iter.hasNext() == true) {
            Map.Entry<Long, Integer> ent = iter.next();
            fou.write(Long.toString(ent.getKey()));
            fou.write(": ");
            fou.write(ent.getValue());
            fou.write(System.lineSeparator());
        }
    }

    public static void time(Logger logger, MyClass mc) {
        App.logger.info("Анализ длительности нот (мс):");
        Map<Long, Integer> nt = mc.noteTime();
        Set<Map.Entry<Long, Integer>> set = nt.entrySet();
        Iterator<Map.Entry<Long, Integer>> iter = set.iterator();
        while (iter.hasNext() == true) {
            Map.Entry<Long, Integer> ent = iter.next();
            App.logger.info(Long.toString(ent.getKey()) + ": " + Integer.toString(ent.getValue()));
        }
    }

    public static void high(WriteToFile fou, MyClass mc) {
        fou.write("Анализ нот по высоте:");
        fou.write(System.lineSeparator());
        Map<Integer, String> nh = mc.noteHigh();
        Set<Map.Entry<Integer, String>> set1 = nh.entrySet();
        Iterator<Map.Entry<Integer, String>> iter1 = set1.iterator();
        while (iter1.hasNext() == true) {
            Map.Entry<Integer, String> ent1 = iter1.next();
            fou.write(ent1.getValue() + ": ");
            fou.write(ent1.getKey());
            fou.write(System.lineSeparator());
        }
    }

    public static void high(Logger logger, MyClass mc) {
        App.logger.info("Анализ нот по высоте:");
        Map<Integer, String> nh = mc.noteHigh();
        Set<Map.Entry<Integer, String>> set1 = nh.entrySet();
        Iterator<Map.Entry<Integer, String>> iter1 = set1.iterator();
        while (iter1.hasNext() == true) {
            Map.Entry<Integer, String> ent1 = iter1.next();
            App.logger.info(ent1.getValue() + ": " + Integer.toString(ent1.getKey()));
        }
    }

    public static void interval(WriteToFile fou, MyClass mc) {
        fou.write("Анализ интервалов:");
        fou.write(System.lineSeparator());
        Map<Integer, Integer> ni = mc.noteInterval();
        Set<Map.Entry<Integer, Integer>> set2 = ni.entrySet();
        Iterator<Map.Entry<Integer, Integer>> iter2 = set2.iterator();
        while (iter2.hasNext() == true) {
            Map.Entry<Integer, Integer> ent2 = iter2.next();
            fou.write(Integer.toString(ent2.getKey()) + ": " + Integer.toString(ent2.getValue()));
            fou.write(System.lineSeparator());
        }
    }

    public static void interval(Logger logger, MyClass mc) {
        App.logger.info("Анализ интервалов:");
        Map<Integer, Integer> ni = mc.noteInterval();
        Set<Map.Entry<Integer, Integer>> set2 = ni.entrySet();
        Iterator<Map.Entry<Integer, Integer>> iter2 = set2.iterator();
        while (iter2.hasNext() == true) {
            Map.Entry<Integer, Integer> ent2 = iter2.next();
            App.logger.info(Integer.toString(ent2.getKey()) + ": " + Integer.toString(ent2.getValue()));
        }
    }
}

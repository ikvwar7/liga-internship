package ru.liga;

import ru.liga.songtask.analiz.MyClass;
import ru.liga.songtask.content.Content;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.util.*;

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
    public static void main(String[] args) {
        SimpleMidiFile simpleMidiFile = new SimpleMidiFile(Content.ZOMBIE);
        System.out.println("Количество нот: " + simpleMidiFile.vocalNoteList().size());
        System.out.println("Длительность (сек): " + simpleMidiFile.durationMs() / 1000);
        MyClass mc=new MyClass();

        System.out.println("Анализ диапазона:");
        Map<String,Integer> nd = mc.noteDiapozon();
        Object[] keys= nd.keySet().toArray();
        Object [] val=nd.values().toArray();
        for(int i=0;i<keys.length-1;i++)
        System.out.println(keys[i]);
        System.out.print(keys[keys.length-1]);System.out.println(val[val.length-1]);

        System.out.println("Анализ длительности нот (мс):");
        Map<Long,Integer> nt=mc.noteTime();
        Set<Map.Entry<Long,Integer>> set=nt.entrySet();
        Iterator<Map.Entry<Long,Integer>> iter= set.iterator();
        while(iter.hasNext()==true){
           Map.Entry<Long,Integer> ent=iter.next();
           System.out.println(ent.getKey()+": "+ent.getValue());
        }

        System.out.println("Анализ нот по высоте:");
        Map<Integer,String> nh= mc.noteHigh();
        Set<Map.Entry<Integer,String>> set1=nh.entrySet();
        Iterator<Map.Entry<Integer,String>> iter1= set1.iterator();
        while(iter1.hasNext()==true){
            Map.Entry<Integer,String> ent1=iter1.next();
            System.out.println(ent1.getValue()+": "+ent1.getKey());
        }

        System.out.println("Анализ интервалов:");
        Map<Integer,Integer> ni= mc.noteInterval();
        Set<Map.Entry<Integer,Integer>> set2=ni.entrySet();
        Iterator<Map.Entry<Integer,Integer>> iter2= set2.iterator();
        while(iter2.hasNext()==true){
            Map.Entry<Integer,Integer> ent2=iter2.next();
            System.out.println(ent2.getKey()+": "+ent2.getValue());
        }
        System.out.println(mc.noteInterval());
    }
}

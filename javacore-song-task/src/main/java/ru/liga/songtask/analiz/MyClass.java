package ru.liga.songtask.analiz;

import ru.liga.songtask.content.Content;
import ru.liga.songtask.domain.Note;
import ru.liga.songtask.domain.NoteSign;
import ru.liga.songtask.domain.SimpleMidiFile;
import java.io.File;
import java.util.*;

public class MyClass {
    private  SimpleMidiFile smf;
    private  List<Note> noteList;
    public MyClass(String str){
        smf=new SimpleMidiFile(new File("C:\\Users\\123\\Documents\\JavaLige\\day_4\\dz_2\\liga-internship\\zombie.txt"));
        noteList=smf.vocalNoteList();
    }
    public MyClass(){
         smf=new SimpleMidiFile(Content.ZOMBIE);
         noteList=smf.vocalNoteList();
    }

    public  Map<Integer,Integer> noteInterval(){//анализ интервалов

        int a,v,count;
        Map<Integer,Integer> noteInterval=new TreeMap<Integer, Integer>();

        for(int i=0;i<noteList.size()-1;i++){
            a=noteList.get(i).sign().getMidi();
            v=noteList.get(i+1).sign().getMidi();
            int interval=Math.abs(a-v);
            if(noteInterval.containsKey(interval)) {
                count=noteInterval.get(interval);
                noteInterval.replace(interval,count+1);
            }
            else noteInterval.put(interval,0);
        }
        return noteInterval;
    }

    public  Map<Long,Integer> noteTime(){//список длительностей нот с количеством вхождений
        Map<Long,Integer> noteTime=new TreeMap<>();
        Note a;
        long dlit;
        int count=0;

        for(int i=0;i<noteList.size();i++){
            a=noteList.get(i);
            dlit=(long)(a.durationTicks()*smf.tickInMs());

            if(noteTime.containsKey(dlit)) {
                count=noteTime.get(dlit);
                noteTime.replace(dlit,count+1);
            }
            else noteTime.put(dlit,1);
        }
        return  noteTime;
    }

    public  Map<String,String> noteDiapozon(){//анализ диапозонов
        Map<String ,String> noteDiapozon=new LinkedHashMap<>();
        Note a,hi_note=noteList.get(0),lo_note=noteList.get(0);

        for(int i=0;i<noteList.size();i++){
            a=noteList.get(i);
            if(a.sign().higher(hi_note.sign()))
                hi_note=a;
            else if (a.sign().lower(lo_note.sign()))
                lo_note=a;
        }
        noteDiapozon.put("верхняя: "+ hi_note.sign().getNoteName()+" октава "+Integer.toString((hi_note.sign().getOctave())),Integer.toString(hi_note.sign().getMidi()));
        noteDiapozon.put("нижняя: " + lo_note.sign().getNoteName()+" октава "+Integer.toString((lo_note.sign().getOctave())),Integer.toString(lo_note.sign().getMidi()));
        noteDiapozon.put("диапозон: ",Integer.toString(hi_note.sign().getMidi()-lo_note.sign().getMidi()));
        return  noteDiapozon;
    }

    public   Map<Integer,String> noteHigh(){//анализ нот по высоте
        Map<Integer,String> noteHigh=new TreeMap<>();

        for(int i=0;i<noteList.size();i++){
            NoteSign a=noteList.get(i).sign();
            noteHigh.put(a.getMidi(),a.getNoteName()+" октава "+Integer.toString((a.getOctave())));
        }
        return  noteHigh;
    }

}


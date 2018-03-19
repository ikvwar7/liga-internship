package ru.liga.songtask.analiz;

import ru.liga.songtask.content.Content;
import ru.liga.songtask.domain.Note;
import ru.liga.songtask.domain.NoteSign;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.io.File;
import java.util.*;

public class MidiAnaliz {
    private final SimpleMidiFile smf;
    private final List<Note> noteList;
    private int diapozon;
    private Note hi_note;
    private Note lo_note;

    public MidiAnaliz(String str) {
        smf = new SimpleMidiFile(new File(str));
        noteList = smf.vocalNoteList();
    }

    public MidiAnaliz() {
        smf = new SimpleMidiFile(Content.ZOMBIE);
        noteList = smf.vocalNoteList();
    }

    public void allAnaliz(WriteToFile fou, String str1, String str2) {//количество нот и длительность
        fou.write(str1);
        fou.write(getKol() + "\r\n");
        fou.write(str2);
        fou.write(Long.toString(getDurations()) + "\r\n");
    }

    public Map<Integer, Integer> noteInterval() {//анализ интервалов
        Map<Integer, Integer> noteInterval = new TreeMap<Integer, Integer>();

        for (int i = 0; i < noteList.size() - 1; i++) {
            int a = noteList.get(i).sign().getMidi();
            int v = noteList.get(i + 1).sign().getMidi();
            int interval = Math.abs(a - v);
            if (noteInterval.containsKey(interval)) {
                int count = noteInterval.get(interval);
                noteInterval.replace(interval, count + 1);
            } else noteInterval.put(interval, 0);
        }
        return noteInterval;
    }

    public Map<Long, Integer> noteTime() {//список длительностей нот с количеством вхождений
        Map<Long, Integer> noteTime = new TreeMap<>();

        for (int i = 0; i < noteList.size(); i++) {
            Note a = noteList.get(i);
            long dlit = (long) (a.durationTicks() * smf.tickInMs());

            if (noteTime.containsKey(dlit)) {
                int count = noteTime.get(dlit);
                noteTime.replace(dlit, count + 1);
            } else noteTime.put(dlit, 1);
        }
        return noteTime;
    }

    public Map<String, String> noteDiapozon() {//анализ диапозонов
        Map<String, String> noteDiapozon = new LinkedHashMap<>();
        hi_note = noteList.get(0);
        lo_note = noteList.get(0);

        for (int i = 0; i < noteList.size(); i++) {
            Note a = noteList.get(i);
            if (a.sign().higher(hi_note.sign()))
                hi_note = a;
            else if (a.sign().lower(lo_note.sign()))
                lo_note = a;
        }
        noteDiapozon.put("верхняя: " + hi_note.sign().getNoteName() + " октава " + Integer.toString((hi_note.sign().getOctave())), Integer.toString(hi_note.sign().getMidi()));
        noteDiapozon.put("нижняя: " + lo_note.sign().getNoteName() + " октава " + Integer.toString((lo_note.sign().getOctave())), Integer.toString(lo_note.sign().getMidi()));
        diapozon = hi_note.sign().getMidi() - lo_note.sign().getMidi();
        noteDiapozon.put("диапозон: ", Integer.toString(diapozon));
        return noteDiapozon;
    }

    public Map<Integer, String> noteHigh() {//анализ нот по высоте
        Map<Integer, String> noteHigh = new TreeMap<>();

        for (int i = 0; i < noteList.size(); i++) {
            NoteSign a = noteList.get(i).sign();
            noteHigh.put(a.getMidi(), a.getNoteName() + " октава " + Integer.toString((a.getOctave())));
        }
        return noteHigh;
    }

    public int getKol() {
        return smf.vocalNoteList().size();
    }

    public long getDurations() {
        return smf.durationMs() / 1000;
    }

    public int getDiap() {
        return diapozon;
    }

    public String hiNote() {
        return hi_note.sign().shortName();
    }

    public String loNote() {
        return lo_note.sign().shortName();
    }
}


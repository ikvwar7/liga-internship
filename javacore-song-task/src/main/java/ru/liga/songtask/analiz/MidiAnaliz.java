package ru.liga.songtask.analiz;


import com.sun.java.swing.plaf.windows.WindowsTreeUI;
import javafx.util.Pair;
import ru.liga.songtask.content.Content;
import ru.liga.songtask.domain.Note;
import ru.liga.songtask.domain.NoteSign;
import ru.liga.songtask.domain.SimpleMidiFile;
//import java.util.function.Function;
import java.io.File;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

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
        final NoteSign[] prev = new NoteSign[1];
        noteList.stream()
                .map(Note::sign)
                .filter(note -> {
                            if (prev[0] == null) {
                                prev[0] = note;
                                return false;
                            }
                            return true;
                        }
                )
                .map(note -> {
                    int count = 0;
                    int interval = Math.abs(note.getMidi() - prev[0].getMidi());
                    if (noteInterval.containsKey(interval)) {
                        count = noteInterval.get(interval);
                    }
                    prev[0] = note;
                    return new Pair<>(interval, ++count);
                })
                .forEach(keyValue -> noteInterval.put(keyValue.getKey(), keyValue.getValue()));
        return noteInterval;
    }

    public Map<Long, Integer> noteTime() {//map длительностей нот с количеством вхождений

        Map<Float, Long> res = noteList.stream()
                .map(a -> a.durationTicks() * smf.tickInMs())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<Long, Integer> map = new TreeMap<>();
        res.entrySet().stream()
                .forEach(e -> map.put(e.getKey().longValue(), e.getValue().intValue()));
        return map;
    }

    public Map<String, String> noteDiapozon() {//анализ диапозонов
        Map<String, String> noteDiapozon = new LinkedHashMap<>();
        lo_note = noteList.stream().min((o1, o2) -> {
            if (o1.sign().higher(o2.sign())) return 1;
            else return -1;
        }).get();

        hi_note = noteList.stream().max((o1, o2) -> {
            if (o1.sign().higher(o2.sign())) return 1;
            else return -1;
        }).get();
        noteDiapozon.put("верхняя: " + hi_note.sign().getNoteName() + " октава " + Integer.toString((hi_note.sign().getOctave())), Integer.toString(hi_note.sign().getMidi()));
        noteDiapozon.put("нижняя: " + lo_note.sign().getNoteName() + " октава " + Integer.toString((lo_note.sign().getOctave())), Integer.toString(lo_note.sign().getMidi()));
        diapozon = hi_note.sign().getMidi() - lo_note.sign().getMidi();
        noteDiapozon.put("диапозон: ", Integer.toString(diapozon));
        return noteDiapozon;
    }

    public Map<Integer, String> noteHigh() {//анализ нот по высоте
        Map<Integer, String> map = noteList.stream()
                .map(Note::sign)
                .collect(Collectors.toMap(a -> a.getMidi(), a -> a.fullName() + " октава " + Integer.toString((a.getOctave())), (a, b) -> a, TreeMap::new));
        return map;
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


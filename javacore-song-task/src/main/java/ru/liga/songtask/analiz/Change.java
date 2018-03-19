package ru.liga.songtask.analiz;

import com.leff.midi.event.MidiEvent;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;
import com.leff.midi.event.meta.Tempo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.io.File;
import java.io.IOException;

public class Change {

    private static Logger logger = LoggerFactory.getLogger(Change.class);
    private SimpleMidiFile smf;
    private final int trans, temp;

    public Change(int trans, int temp, String str) {
        smf = new SimpleMidiFile(new File(str));
        this.trans = trans;
        this.temp = temp;
    }

    public void changeMidi() {
        for (MidiEvent event : smf.getMidiFormat().getTracks().get(0).getEvents()) {
            if (event instanceof Tempo) {
                Tempo tempo = (Tempo) event;
                float old = tempo.getBpm();
                float newt = old + old * (float) temp / 100;
                tempo.setBpm(newt);
            }
        }
        for (MidiEvent midiEvent : smf.getMidiFormat().getTracks().get(1).getEvents()) {
            if (midiEvent instanceof NoteOn) {
                NoteOn no = (NoteOn) midiEvent;
                no.setNoteValue(no.getNoteValue() + trans);
            }
            if (midiEvent instanceof NoteOff) {
                NoteOff nof = (NoteOff) midiEvent;
                nof.setNoteValue(nof.getNoteValue() + trans);
            }
        }
    }

    public void save(String path) {
        try {
            smf.getMidiFormat().writeToFile(new File(path));
        } catch (IOException e) {
            logger.error("Ошибка " + e.getMessage());
        }
    }

}

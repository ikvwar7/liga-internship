package ru.liga.songtask.analiz;

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
        smf.getMidiFormat().getTracks().get(0).getEvents().stream()
                .filter(event -> event instanceof Tempo)
                .map(event -> (Tempo) event)
                .forEach(tempo -> tempo.setBpm(tempo.getBpm() * (1 + (float) temp / 100)));

        smf.getMidiFormat().getTracks().get(1).getEvents().stream()
                .filter(midiEvent -> midiEvent instanceof NoteOn)
                .map(midiEvent -> (NoteOn) midiEvent)
                .forEach(noteOn -> noteOn.setNoteValue(noteOn.getNoteValue() + trans));

        smf.getMidiFormat().getTracks().get(1).getEvents().stream()
                .filter(midiEvent -> midiEvent instanceof NoteOff)
                .map(midiEvent -> (NoteOff) midiEvent)
                .forEach(noteOf -> noteOf.setNoteValue(noteOf.getNoteValue() + trans));
    }

    public void save(String path) {
        try {
            smf.getMidiFormat().writeToFile(new File(path));
        } catch (IOException e) {
            logger.error("Ошибка " + e.getMessage());
        }
    }

}

package ru.liga.songtask.analiz;

import com.leff.midi.event.MidiEvent;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;
import com.leff.midi.event.meta.Tempo;
import ru.liga.songtask.domain.SimpleMidiFile;
import java.io.File;
import java.io.IOException;

public class Change{

    private SimpleMidiFile smf;
    int trans,temp;
    public Change(int trans,int temp){
        smf=new SimpleMidiFile(new File("C:\\Users\\123\\Documents\\JavaLige\\day_4\\dz_2\\liga-internship\\zombie.txt"));
        this.trans=trans;
        this.temp=temp;
    }
 public void change_midi(String path){
     for(MidiEvent event : smf.getMidiFormat().getTracks().get(0).getEvents()){
         if(event instanceof Tempo) {
             Tempo tempo = (Tempo) event;
             float old=tempo.getBpm();
             float newt=old +old*(float)temp/100;
             tempo.setBpm(newt);
         }
     }
        for (MidiEvent midiEvent : smf.getMidiFormat().getTracks().get(1).getEvents()) {
            if (midiEvent instanceof NoteOn) {
                 NoteOn no=(NoteOn) midiEvent;
                 no.setNoteValue(no.getNoteValue() +trans);
            }
             if (midiEvent instanceof NoteOff) {
                 NoteOff nof=(NoteOff) midiEvent;
                 nof.setNoteValue(nof.getNoteValue() +trans);
            }
        }
     try{
        smf.getMidiFormat().writeToFile(new File(path));
     }
     catch (IOException e){System.out.print(e.getMessage());}
    }
}

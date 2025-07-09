package Musicgenerator;

import javax.sound.midi.*;
import java.io.File;

public class generator {

    public static void main(String[] args) {
            try {
                Sequence sequence = new Sequence(Sequence.PPQ, 480);
                Track track = sequence.createTrack();

                int[] scale = {60, 62, 64, 65, 67, 69, 71, 72};



                int tick = 0;
                for (int i = 0; i < 16; i++) {
                    int note = scale[(int) (Math.random() * scale.length)];


                    track.add(createNoteEvent(ShortMessage.NOTE_ON, note, 100, tick));


                    tick += 480;
                    track.add(createNoteEvent(ShortMessage.NOTE_OFF, note, 100, tick));
                }


                File midiFile = new File("generated_music.mid");
                MidiSystem.write(sequence, 1, midiFile);

                System.out.println("MIDI file 'generated_music.mid' created successfully.");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        public static MidiEvent createNoteEvent(int command, int note, int velocity, long tick) throws Exception {
            ShortMessage message = new ShortMessage();
            message.setMessage(command, 0, note, velocity);
            return new MidiEvent(message, tick);
        }


}

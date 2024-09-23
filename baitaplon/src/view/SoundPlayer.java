package view;

import java.io.File;
import javax.sound.sampled.*;

public class SoundPlayer {
    
    private Clip clip;
    private static FloatControl volumeControl;
    
    public SoundPlayer(File path){
        try{
            AudioInputStream ais;
            ais = AudioSystem.getAudioInputStream(path);
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels()*2,
                    baseFormat.getSampleRate(),
                    false
            );
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
            clip = AudioSystem.getClip();
            clip.open(dais);

            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void play(){
        if(clip !=null){
            stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }
    public void playLoop() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            System.out.println("Clip is null, cannot play.");
        }
    }

    public void stop(){
        if(clip.isRunning()) clip.stop();
    }
    
    public void close(){
        clip.close();
    }

}

package android.workshop.dmii.playlistspotifygenerator.models;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;

public class Player {

    private Music currentMusic;
    public boolean canSendCommand;
    public MediaPlayer mediaPlayer;


    public static final Player playerInstance = new Player();
    public static Player getInstance() {
        return playerInstance;
    }

    public Player(){
        this.canSendCommand = false;
        this.mediaPlayer = new MediaPlayer();
    }

    public void setCurrentMusic(Music music){

        this.currentMusic = music;
        this.canSendCommand = false;
        if (!TextUtils.isEmpty(this.currentMusic.getUrl())) {
            Log.d("Player", "NEW MUSIC");
            updatePlayer();
        }
    }

    public void updatePlayer(){

        mediaPlayer.stop();
        mediaPlayer.reset();


        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(this.currentMusic.getUrl());
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                    canSendCommand = true;
                }
            });

        } catch (IllegalArgumentException e) {
            Log.d("Player", "You might not set the URI correctly!");
        } catch (SecurityException e) {
            Log.d("Player", "You might not set the URI correctly!");
        } catch (IllegalStateException e) {
            Log.d("Player", "You might not set the URI correctly!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

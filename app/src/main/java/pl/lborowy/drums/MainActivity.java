package pl.lborowy.drums;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener{

    HashMap<Integer, MediaPlayer> musicsHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicsHashMap = new HashMap<>();

        putMusicPlayer(R.id.imageView);
        putMusicPlayer(R.id.imageView2);
        putMusicPlayer(R.id.imageView3);
        putMusicPlayer(R.id.imageView4);
        putMusicPlayer(R.id.imageView5);
    }

    private void putMusicPlayer(int vievId) {
        musicsHashMap.put(vievId, MediaPlayer.create(this, getMusicByViewId(vievId)));
    }

    public void playSound(View view) {
        //// TODO: 2017-06-08  play sounds
        MediaPlayer sound = musicsHashMap.get(view.getId());
        if (sound != null) {
            seekMusicToBeginningAndPlay(sound);
        }
        else {
            putMusicPlayer(view.getId());
            seekMusicToBeginningAndPlay(musicsHashMap.get(view.getId()));
        }
        
        //old
//        int musicId = getMusicByViewId(view.getId());
//        MediaPlayer sound = MediaPlayer.create(this, musicId);
//        sound.setOnCompletionListener(this);
//        sound.start();
    }

    private void seekMusicToBeginningAndPlay(MediaPlayer sound) {
        sound.seekTo(0);
        if (!sound.isPlaying()) {
            sound.start();
        }
    }

    private int getMusicByViewId(int viewId) {
        switch (viewId) {
            case R.id.imageView:
                return R.raw.cymbal;
            case R.id.imageView2:
                return R.raw.drum1;
            case R.id.imageView3:
                return R.raw.bass;
            case R.id.imageView4:
                return R.raw.fdrum;
            case R.id.imageView5:
                return R.raw.gong;

            default:
                return R.raw.paleczki;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.release();
    }
}

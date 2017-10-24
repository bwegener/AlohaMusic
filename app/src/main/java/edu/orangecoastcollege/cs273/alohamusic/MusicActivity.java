package edu.orangecoastcollege.cs273.alohamusic;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 *
 *
 * @author Brian Wegener
 * @version 1.0
 *
 * Created on 10/24/2017
 */
public class MusicActivity extends AppCompatActivity {

    // References to UI components
    Button ukeleleButton;
    Button ipuButton;
    Button hulaButton;

    VideoView hulaVideoView;

    MediaPlayer ukelelePlayer;
    MediaPlayer ipuPlayer;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        // Associate the components
        ukeleleButton = (Button) findViewById(R.id.ukeleleButton);
        ipuButton = (Button) findViewById(R.id.ipuButton);
        hulaButton = (Button) findViewById(R.id.hulaButton);

        hulaVideoView = (VideoView) findViewById(R.id.hulaVideoView);

        // Associate the Media Players:
        ukelelePlayer = MediaPlayer.create(this, R.raw.ukulele);
        ipuPlayer = MediaPlayer.create(this, R.raw.ipu);

        // Associate the Video View with its URI
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.hula;

        hulaVideoView.setVideoURI(Uri.parse(uri));

        // Create a MediaController for the VideoView
        // MediaController =  controls (play/pause/forward/rewind)
        hulaVideoView.setMediaController(new MediaController(this));
    }


    // Method will handle all 3 button clicks
    // Use the button id to see which was clicked

    /**
     * The playMedia class associates the buttons with the media
     * associated with them
     * @param v
     */
    public void playMedia(View v)
    {
        // Make a decision based on the id of the view
        switch (v.getId())
        {
            case R.id.ukeleleButton:
                // if it's playing, pause it
                if(ukelelePlayer.isPlaying())
                {
                    ukelelePlayer.pause();
                    // Change the text
                    ukeleleButton.setText(R.string.ukulele_button_play_text);
                    // Show the other two buttons:
                    ipuButton.setVisibility(View.VISIBLE);
                    hulaButton.setVisibility(View.VISIBLE);
                }
                // else, play it
                else{
                    ukelelePlayer.start();
                    ukeleleButton.setText(R.string.ukulele_button_pause_text);
                    // Hide the other buttons
                    ipuButton.setVisibility(View.INVISIBLE);
                    hulaButton.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.ipuButton:
                if(ipuPlayer.isPlaying())
                {
                    ipuPlayer.pause();
                    ipuButton.setText(R.string.ipu_button_play_text);
                    ukeleleButton.setVisibility(View.VISIBLE);
                    hulaButton.setVisibility(View.VISIBLE);
                }
                else {
                    ipuPlayer.start();
                    ipuButton.setText(R.string.ipu_button_pause_text);
                    ukeleleButton.setVisibility(View.INVISIBLE);
                    hulaButton.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.hulaButton:
                if(hulaVideoView.isPlaying())
                {
                    hulaVideoView.pause();
                    hulaButton.setText(R.string.hula_button_watch_text);
                    ukeleleButton.setVisibility(View.VISIBLE);
                    ipuButton.setVisibility(View.VISIBLE);
                }
                else
                {
                    hulaVideoView.start();
                    hulaButton.setText(R.string.hula_button_pause_text);
                    ukeleleButton.setVisibility(View.INVISIBLE);
                    ipuButton.setVisibility(View.INVISIBLE);
                }
                break;
        }
    }

    // Override onStop method to release MediaPlayers
    // Prevent memory leaks

    /**
     * The onStop method releases the ukelelePlayer and ipuPlayer
     */
    @Override
    protected void onStop() {
        super.onStop();
        ukelelePlayer.release();
        ipuPlayer.release();
    }
}

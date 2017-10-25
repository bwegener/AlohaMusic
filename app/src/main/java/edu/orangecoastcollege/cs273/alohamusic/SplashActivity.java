package edu.orangecoastcollege.cs273.alohamusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The <code>SplashActivity</code> hosts a splash page
 * which runs for 3 seconds before sending the user to
 * <code>MusicActivity</code>
 *
 * @author Brian Wegener
 * @version 1.0
 *
 * Created on 10/24/2017
 */
public class SplashActivity extends AppCompatActivity {

    /**
     * The onCreate shows the splash page then sets up a TimerTask
     * to send the user to the <code>MusicActivity</code> after 3 seconds.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Create a TimerTask to defer the loading of MusicActivity by 3 seconds
        // Anonymous Interclass (which is why it requires the semicolon)
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Finish the current SplashActivity, then:
                finish();

                // Launch the MusicActivity
                Intent musicIntent = new Intent(SplashActivity.this, MusicActivity.class);

                startActivity(musicIntent);

            }
        };
        // Run the timer task after 3 seconds (3000ms)
        Timer timer = new Timer();
        timer.schedule(task, 3000);
    }
}

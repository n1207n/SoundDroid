package silin.study.sounddroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;

import silin.study.sounddroid.objects.Track;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new Gson();
        Track track = gson.fromJson(trackJSON(), Track.class);
        Log.d(TAG, "Track title is " + track.getTitle());
    }

    private String trackJSON() {
        return "{\n" +
                "kind: \"track\",\n" +
                "id: 13158665,\n" +
                "created_at: \"2011/04/06 15:37:43 +0000\",\n" +
                "user_id: 3699101,\n" +
                "duration: 18109,\n" +
                "commentable: true,\n" +
                "state: \"finished\",\n" +
                "original_content_size: 201483,\n" +
                "last_modified: \"2013/02/18 19:18:11 +0000\",\n" +
                "sharing: \"public\",\n" +
                "tag_list: \"soundcloud:source=iphone-record\",\n" +
                "permalink: \"munching-at-tiannas-house\",\n" +
                "streamable: true,\n" +
                "embeddable_by: \"all\",\n" +
                "downloadable: false,\n" +
                "purchase_url: null,\n" +
                "label_id: null,\n" +
                "purchase_title: null,\n" +
                "genre: null,\n" +
                "title: \"Munching at Tiannas house\",\n" +
                "description: null,\n" +
                "label_name: null,\n" +
                "release: null,\n" +
                "track_type: \"recording\",\n" +
                "key_signature: null,\n" +
                "isrc: null,\n" +
                "video_url: null,\n" +
                "bpm: null,\n" +
                "release_year: null,\n" +
                "release_month: null,\n" +
                "release_day: null,\n" +
                "original_format: \"m4a\",\n" +
                "license: \"all-rights-reserved\",\n" +
                "uri: \"https://api.soundcloud.com/tracks/13158665\",\n" +
                "user: {\n" +
                "id: 3699101,\n" +
                "kind: \"user\",\n" +
                "permalink: \"alex-stevenson\",\n" +
                "username: \"Alex Stevenson\",\n" +
                "last_modified: \"2011/06/13 23:58:44 +0000\",\n" +
                "uri: \"https://api.soundcloud.com/users/3699101\",\n" +
                "permalink_url: \"http://soundcloud.com/alex-stevenson\",\n" +
                "avatar_url: \"https://i1.sndcdn.com/avatars-000004193858-jnf2pd-large.jpg\"\n" +
                "},\n" +
                "created_with: {\n" +
                "id: 124,\n" +
                "kind: \"app\",\n" +
                "name: \"SoundCloud iOS\",\n" +
                "uri: \"https://api.soundcloud.com/apps/124\",\n" +
                "permalink_url: \"http://soundcloud.com/apps/iphone\",\n" +
                "external_url: \"http://itunes.com/app/soundcloud\"\n" +
                "},\n" +
                "permalink_url: \"http://soundcloud.com/alex-stevenson/munching-at-tiannas-house\",\n" +
                "artwork_url: null,\n" +
                "waveform_url: \"https://w1.sndcdn.com/fxguEjG4ax6B_m.png\",\n" +
                "stream_url: \"https://api.soundcloud.com/tracks/13158665/stream\",\n" +
                "playback_count: 5328,\n" +
                "download_count: 134,\n" +
                "favoritings_count: 2,\n" +
                "comment_count: 3,\n" +
                "attachments_uri: \"https://api.soundcloud.com/tracks/13158665/attachments\",\n" +
                "policy: \"ALLOW\"\n" +
                "}";
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

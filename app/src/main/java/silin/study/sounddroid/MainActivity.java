package silin.study.sounddroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import silin.study.sounddroid.adapters.TracksAdapter;
import silin.study.sounddroid.soundcloud.SoundCloud;
import silin.study.sounddroid.soundcloud.SoundCloudService;
import silin.study.sounddroid.soundcloud.Track;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";

    @InjectView(R.id.song_rv)
    RecyclerView mSongRecyclerView;

    private TracksAdapter mTracksAdapter;
    private List<Track> mTracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        mSongRecyclerView = (RecyclerView) findViewById(R.id.song_rv);
        mSongRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTracks = new ArrayList<Track>();
        mTracksAdapter = new TracksAdapter(mTracks);
        mSongRecyclerView.setAdapter(mTracksAdapter);

        SoundCloudService soundCloudService = SoundCloud.getInstance().getService();
        soundCloudService.searchSongs("dark horse", new Callback<List<Track>>() {
            @Override
            public void success(List<Track> tracks, Response response) {
                mTracks.clear();
                mTracks.addAll(tracks);
                mTracksAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, error.toString());
            }
        });
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

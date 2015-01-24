package silin.study.sounddroid;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @InjectView(R.id.player_tb)
    Toolbar mPlayerToolbar;

    @InjectView(R.id.selected_song_title_tv)
    TextView mSelectedSongTitleTextView;

    @InjectView(R.id.selected_song_thumbnail_iv)
    ImageView mSelectedSongThumbnailImageView;

    @InjectView(R.id.player_state_iv)
    ImageView mPlayerStateImageView;

    private TracksAdapter mTracksAdapter;
    private List<Track> mTracks;

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                toggleSongState();
            }
        });

        mPlayerStateImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSongState();
            }
        });
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mPlayerStateImageView.setImageResource(R.drawable.ic_play);
            }
        });

        mSongRecyclerView = (RecyclerView) findViewById(R.id.song_rv);
        mSongRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTracks = new ArrayList<Track>();
        mTracksAdapter = new TracksAdapter(this, mTracks);
        mTracksAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Track selectedTrack = mTracks.get(position);
                mSelectedSongTitleTextView.setText(selectedTrack.getTitle());
                Picasso.with(MainActivity.this).load(selectedTrack.getAvatarURL()).into(mSelectedSongThumbnailImageView);

                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                    mMediaPlayer.reset();
                }

                try {
                    mMediaPlayer.setDataSource(selectedTrack.getStreamURL() + "?client_id=" + SoundCloudService.CLIENT_ID);
                    mMediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        mSongRecyclerView.setAdapter(mTracksAdapter);

        SoundCloudService soundCloudService = SoundCloud.getInstance().getService();
//        soundCloudService.searchSongs("Trance", new Callback<List<Track>>() {
//            @Override
//            public void success(List<Track> tracks, Response response) {
//                mTracks.clear();
//                mTracks.addAll(tracks);
//                mTracksAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Log.d(TAG, error.toString());
//            }
//        });
        soundCloudService.getRecentSongs(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()), new Callback<List<Track>>() {
            @Override
            public void success(List<Track> tracks, Response response) {
                mTracks.clear();
                mTracks.addAll(tracks);
                mTracksAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void toggleSongState() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mPlayerStateImageView.setImageResource(R.drawable.ic_play);
        }

        else {
            mMediaPlayer.start();
            mPlayerStateImageView.setImageResource(R.drawable.ic_pause);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }

            mMediaPlayer.release();
            mMediaPlayer = null;
        }
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
        if (id == R.id.action_search_new_songs) {
//            new AlertDialog.Builder(this).setTitle("Looking for new jam")
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

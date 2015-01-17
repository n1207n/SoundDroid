package silin.study.sounddroid.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import silin.study.sounddroid.R;
import silin.study.sounddroid.soundcloud.Track;

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.ViewHolder> {
    private List<Track> mTracks;

    public TracksAdapter(List<Track> trackList) {
        mTracks = trackList;
    }

    @Override
    public TracksAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.track_row, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TracksAdapter.ViewHolder viewHolder, int i) {
        viewHolder.mTitleTextView.setText(mTracks.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return mTracks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.track_title)
        TextView mTitleTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);
        }
    }
}

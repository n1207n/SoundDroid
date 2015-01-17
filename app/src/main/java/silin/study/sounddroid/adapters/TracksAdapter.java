package silin.study.sounddroid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import silin.study.sounddroid.R;
import silin.study.sounddroid.soundcloud.Track;

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.ViewHolder> {
    private List<Track> mTracks;

    private Context mContext;

    public TracksAdapter(Context context, List<Track> trackList) {
        mContext = context;
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
        Picasso.with(mContext).load(mTracks.get(i).getAvatarURL()).into(viewHolder.mThumbnailImageView);
    }

    @Override
    public int getItemCount() {
        return mTracks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.track_title)
        TextView mTitleTextView;

        @InjectView(R.id.track_thumbnail)
        ImageView mThumbnailImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.inject(this, itemView);
        }
    }
}

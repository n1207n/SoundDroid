package silin.study.sounddroid.soundcloud;

import com.google.gson.annotations.SerializedName;

public class Track {
    @SerializedName("title")
    private String mTitle;

    @SerializedName("stream_url")
    private String mStreamURL;

    @SerializedName("artwork_url")
    private String mArtworkURL;

    @SerializedName("id")
    private int mID;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getStreamURL() {
        return mStreamURL;
    }

    public String getArtworkURL() {
        return mArtworkURL;
    }

    public int getID() {
        return mID;
    }

    public String getAvatarURL() {
        String avatarURL = mArtworkURL;

        if (avatarURL != null) avatarURL.replace("large", "tiny");

        return avatarURL;
    }
}

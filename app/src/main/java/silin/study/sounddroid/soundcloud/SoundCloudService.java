package silin.study.sounddroid.soundcloud;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface SoundCloudService {
    static final String CLIENT_ID = "f0832be8105aced062b07b20cf371eae";

    @GET("/tracks?client_id="+CLIENT_ID)
    public void searchSongs(@Query("q") String query, Callback<List<Track>> callback);
}

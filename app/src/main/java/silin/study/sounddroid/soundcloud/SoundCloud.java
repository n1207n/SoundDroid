package silin.study.sounddroid.soundcloud;

import retrofit.RestAdapter;

public class SoundCloud {
    private static final String API_URL = "http://api.soundcloud.com";

    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            .setEndpoint(API_URL).setLogLevel(RestAdapter.LogLevel.FULL)
            .build();

    private final SoundCloudService SERVICE = REST_ADAPTER.create(SoundCloudService.class);

    private static SoundCloud ourInstance = new SoundCloud();

    public static SoundCloud getInstance() {
        return ourInstance;
    }

    private SoundCloud() {
    }

    public SoundCloudService getService() {
        return SERVICE;
    }
}

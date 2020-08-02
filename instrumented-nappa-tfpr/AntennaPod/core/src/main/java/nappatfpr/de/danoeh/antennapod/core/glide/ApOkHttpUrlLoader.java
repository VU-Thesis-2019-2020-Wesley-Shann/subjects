package nappatfpr.de.danoeh.antennapod.core.glide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.bumptech.glide.integration.okhttp3.OkHttpStreamFetcher;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.signature.ObjectKey;
import nappatfpr.de.danoeh.antennapod.core.service.download.AntennapodHttpClient;
import nappatfpr.de.danoeh.antennapod.core.service.download.HttpDownloader;
import nappatfpr.de.danoeh.antennapod.core.storage.DBReader;
import nappatfpr.de.danoeh.antennapod.core.util.NetworkUtils;
import nl.vu.cs.s2group.nappa.*;
import nl.vu.cs.s2group.nappa.nappaexperimentation.MetricNetworkRequestExecutionTime;
import okhttp3.*;

/**
 * @see com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
 */
class ApOkHttpUrlLoader implements ModelLoader<String, InputStream> {

    private static final String TAG = ApOkHttpUrlLoader.class.getSimpleName();

    /**
     * The default factory for {@link ApOkHttpUrlLoader}s.
     */
    public static class Factory implements ModelLoaderFactory<String, InputStream> {

        private static volatile OkHttpClient internalClient;
        private final OkHttpClient client;

        private static OkHttpClient getInternalClient() {
            if (internalClient == null) {
                synchronized (Factory.class) {
                    if (internalClient == null) {
                        OkHttpClient.Builder builder = AntennapodHttpClient.newBuilder();
                        builder.interceptors().add(new NetworkAllowanceInterceptor());
                        builder.interceptors().add(new BasicAuthenticationInterceptor());
//                      Apps had conflicts with Glide while this statement was instrumented
//                      internalClient = Nappa.getOkHttp(builder.build());
                        internalClient = builder.build();
                    }
                }
            }
            return internalClient;
        }

        /**
         * Constructor for a new Factory that runs requests using a static singleton client.
         */
        Factory() {
            this(getInternalClient());
        }

        /**
         * Constructor for a new Factory that runs requests using given client.
         */
        Factory(OkHttpClient client) {
            this.client = client;
        }

        @NonNull
        @Override
        public ModelLoader<String, InputStream> build(@NonNull MultiModelLoaderFactory multiFactory) {
            return new ApOkHttpUrlLoader(client);
        }

        @Override
        public void teardown() {
            // Do nothing, this instance doesn't own the client.
        }
    }

    private final OkHttpClient client;

    private ApOkHttpUrlLoader(OkHttpClient client) {
        this.client = client;
    }

//    Lots of resources pass by here, but I could not find all where thw request are first issued.
//    Could not see it in the stack trace
//    The images are in the interceptor
    @Nullable
    @Override
    public LoadData<InputStream> buildLoadData(@NonNull String model, int width, int height, @NonNull Options options) {
        Log.d(TAG, "buildLoadData() called with: " + "model = [" + model + "], width = ["
                + width + "], height = [" + height + "]");
        if(TextUtils.isEmpty(model)) {
            return null;
        } else if(model.startsWith("/")) {
            return new LoadData<>(new ObjectKey(model), new AudioCoverFetcher(model));
        } else {
            GlideUrl url = new GlideUrl(model);
            return new LoadData<>(new ObjectKey(model), new OkHttpStreamFetcher(client, url));
        }
    }

    @Override
    public boolean handles(@NonNull String s) {
        return true;
    }

    private static class NetworkAllowanceInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
//            Both interceptors are used for the same reqeusts.
//            The interceptor below might issue a new request
//            Request request = chain.request();
            Response response;
//            long sentRequestAtMillis;
            if (NetworkUtils.isImageAllowed()) {
//                Log.d("MYTAG", "interceptor 1a" + request.url().toString());
//                sentRequestAtMillis = System.currentTimeMillis();
                response = chain.proceed(chain.request());
            } else {
//                Log.d("MYTAG", "interceptor 1b" + request.url().toString());
//                sentRequestAtMillis = System.currentTimeMillis();
                response = new Response.Builder()
                        .protocol(Protocol.HTTP_2)
                        .code(420)
                        .message("Policy Not Fulfilled")
                        .body(ResponseBody.create(null, new byte[0]))
                        .request(chain.request())
                        .build();
            }
//            long receivedResponseAtMillis = System.currentTimeMillis();
//            MetricNetworkRequestExecutionTime.log(response, sentRequestAtMillis, receivedResponseAtMillis, true);
            return response;
        }

    }

    private static class BasicAuthenticationInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String url = request.url().toString();
            String authentication = DBReader.getImageAuthentication(url);

            if(TextUtils.isEmpty(authentication)) {
                Log.d(TAG, "no credentials for '" + url + "'");
                long sentRequestAtMillis = System.currentTimeMillis();
                Response r = chain.proceed(request);
                long receivedResponseAtMillis = System.currentTimeMillis();
                Log.d("MYTAG", "interceptor 2a");
                MetricNetworkRequestExecutionTime.log(r, sentRequestAtMillis, receivedResponseAtMillis, true);
                return r;
            }

            // add authentication
            String[] auth = authentication.split(":");
            String credentials = HttpDownloader.encodeCredentials(auth[0], auth[1], "ISO-8859-1");
            Request newRequest = request
                    .newBuilder()
                    .addHeader("Authorization", credentials)
                    .build();
            Log.d(TAG, "Basic authentication with ISO-8859-1 encoding");
            long sentRequestAtMillis = System.currentTimeMillis();
            Response response = chain.proceed(newRequest);
            long receivedResponseAtMillis = System.currentTimeMillis();
            if (!response.isSuccessful() && response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                credentials = HttpDownloader.encodeCredentials(auth[0], auth[1], "UTF-8");
                newRequest = request
                        .newBuilder()
                        .addHeader("Authorization", credentials)
                        .build();
                Log.d(TAG, "Basic authentication with UTF-8 encoding");
                long sentRequestAtMillis2 = System.currentTimeMillis();
                Response r3 = chain.proceed(newRequest);
                long receivedResponseAtMillis2 = System.currentTimeMillis();
                Log.d("MYTAG", "interceptor 2b");
                MetricNetworkRequestExecutionTime.log(r3, sentRequestAtMillis2, receivedResponseAtMillis2, true);
                return r3;
            } else {
                Log.d("MYTAG", "interceptor 2c");
                MetricNetworkRequestExecutionTime.log(response, sentRequestAtMillis, receivedResponseAtMillis, true);
                return response;
            }
        }
    }

}
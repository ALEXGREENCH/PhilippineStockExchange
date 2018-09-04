package net.bplaced.greench.philippinestockexchange.net;

import net.bplaced.greench.philippinestockexchange.model.Stocks;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {

    private static final String BASE_URL = "http://phisix-api3.appspot.com";

    public static Call<Stocks> call(){
        return create().getStockData();
    }

    private static IConnect create() {
        OkHttpClient client = getOkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IConnect.class);
    }


    private static OkHttpClient getOkHttpClient() {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            return builder.addInterceptor(httpLoggingInterceptor)
                    //.connectTimeout(60, TimeUnit.SECONDS)
                    //.readTimeout(60, TimeUnit.SECONDS)
                    //.writeTimeout(10, TimeUnit.MINUTES)
                    .build();
        }
}

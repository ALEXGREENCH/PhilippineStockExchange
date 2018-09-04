package net.bplaced.greench.philippinestockexchange.net;

import net.bplaced.greench.philippinestockexchange.model.Stocks;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IConnect {

    @GET("/stocks.json")
    Call<Stocks> getStockData();
}
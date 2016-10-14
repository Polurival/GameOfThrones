package home.skwmium.skilltest.model.network;

import android.support.annotation.NonNull;

import home.skwmium.skilltest.model.dto.Character;
import home.skwmium.skilltest.model.dto.House;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

public interface ApiClient {
    @GET
    Observable<Character> characterGet(@NonNull @Url String userUrl);

    @GET("houses/{houseId}")
    Observable<House> houseGet(@NonNull @Path("houseId") String houseId);
}

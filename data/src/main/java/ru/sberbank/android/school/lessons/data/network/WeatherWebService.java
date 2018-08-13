package ru.sberbank.android.school.lessons.data.network;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import ru.sberbank.android.school.lessons.domain.model.Weather;

public interface WeatherWebService {

    @Headers("X-Yandex-API-Key: " + ApiKey.KEY)
    @GET("forecast?lat=55.75396&lon=37.620393&hours=true&limit=7")
    Single<Weather> getWeather();
}

package home.skwmium.skilltest.model.network;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import home.skwmium.skilltest.utils.Const;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public final class ServiceGenerator {

    public static ApiClient createApiInterface() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        ObjectMapper jacksonObjectMapper = new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        JacksonConverterFactory jacksonConverterFactory = JacksonConverterFactory
                .create(jacksonObjectMapper);

        return new Retrofit.Builder()
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .addConverterFactory(jacksonConverterFactory)
                .client(httpClient)
                .baseUrl(Const.API_BASE_URL)
                .build()
                .create(ApiClient.class);
    }
}

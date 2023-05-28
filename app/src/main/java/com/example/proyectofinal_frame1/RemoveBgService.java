package com.example.proyectofinal_frame1;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import com.google.gson.annotations.SerializedName;

import java.io.File;

public class RemoveBgService {
    private static final String API_KEY = "TU_API_KEY"; // Reemplaza con tu API Key

    private RemoveBgApi removeBgApi;

    public RemoveBgService() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    okhttp3.Request request = chain.request().newBuilder()
                            .addHeader("X-Api-Key", API_KEY)
                            .build();
                    return chain.proceed(request);
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.remove.bg/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        removeBgApi = retrofit.create(RemoveBgApi.class);
    }

    public void removeBackground(File imageFile, Callback<RemoveBgResponse> callback) {
        RequestBody fileRequestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("image_file", imageFile.getName(), fileRequestBody);

        Call<RemoveBgResponse> call = removeBgApi.removeBackground(filePart);
        call.enqueue(callback);
    }

    public interface RemoveBgApi {
        @Multipart
        @POST("v1.0/removebg")
        Call<RemoveBgResponse> removeBackground(@Part MultipartBody.Part imageFile);
    }

    public class RemoveBgResponse {
        @SerializedName("data")
        private Data data;

        public Data getData() {
            return data;
        }

        public class Data {
            @SerializedName("result_url")
            private String resultUrl;

            public String getResultUrl() {
                return resultUrl;
            }
        }
    }
}




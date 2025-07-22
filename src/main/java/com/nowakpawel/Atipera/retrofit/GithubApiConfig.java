package com.nowakpawel.Atipera.retrofit;

import com.nowakpawel.Atipera.retrofit.configuration.PropertiesConfig;
import lombok.AllArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
@AllArgsConstructor
public class GithubApiConfig {
    private final PropertiesConfig propertiesConfig;
    @Bean
    public GithubClient getGithubClient() {
        OkHttpClient httpClient = new OkHttpClient();

        return new Retrofit.Builder()
                .baseUrl(propertiesConfig.getUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubClient.class);
    }
}

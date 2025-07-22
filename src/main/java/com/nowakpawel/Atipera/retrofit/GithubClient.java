package com.nowakpawel.Atipera.retrofit;

import com.nowakpawel.Atipera.retrofit.dto.GitRepoDto;
import com.nowakpawel.Atipera.retrofit.dto.ResponseDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface GithubClient {
//    @GET("/users/{user}/repos")
    @GET("/search/repositories")
    Call<ResponseDto> repositoriesForUser(@Query("q") String query);
}

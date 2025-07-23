package com.nowakpawel.Atipera.retrofit;

import com.nowakpawel.Atipera.retrofit.record.BranchDto;
import com.nowakpawel.Atipera.retrofit.record.RepositoriesResponseDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface GithubClient {
    @GET("/search/repositories")
    Call<RepositoriesResponseDto> repositoriesForUser(@Query("q") String query);

    @GET("/repos/{username}/{reponame}/branches")
    Call<List<BranchDto>> getBranchesForRepository(@Path("username") String username, @Path("reponame") String reponame);
}

package com.nowakpawel.Atipera.web.service;

import com.nowakpawel.Atipera.retrofit.GithubClient;
import com.nowakpawel.Atipera.retrofit.dto.BranchDto;
import com.nowakpawel.Atipera.retrofit.dto.BranchesResponseDto;
import com.nowakpawel.Atipera.retrofit.dto.GitRepoDto;
import com.nowakpawel.Atipera.retrofit.dto.RepositoriesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GithubClientService {
    private final GithubClient client;

    public ResponseEntity<RepositoriesResponseDto> getRepositories(String username) throws IOException {
        String query = "user:" + username;
        Response<RepositoriesResponseDto> response = client.repositoriesForUser(query).execute();

        if (response.isSuccessful() && !(response.body() == null)) {
            RepositoriesResponseDto responseDto = new RepositoriesResponseDto();
            List<GitRepoDto> foundRepos = response.body().getItems().stream()
                    .filter(i -> i.getFork().equals(Boolean.FALSE)).toList();
            responseDto.setItems(foundRepos);
            return ResponseEntity.ok(responseDto);
        } else {
            throw new RuntimeException("Unsuccessful response");
        }
    }

    public ResponseEntity<List<BranchDto>> getBranchesForRepository(String username, String repositoryName) throws IOException {
        Response<List<BranchDto>> response = client.getBranchesForRepository(username, repositoryName).execute();

        if (response.isSuccessful() && !(response.body() == null)) {
            List<BranchDto> foundBranches = response.body();

            return ResponseEntity.ok(foundBranches);
        } else {
            throw new RuntimeException("Unsuccessful response");
        }
    }

}

package com.nowakpawel.Atipera.web.service;

import com.nowakpawel.Atipera.helpers.UserNotFoundException;
import com.nowakpawel.Atipera.retrofit.GithubClient;
import com.nowakpawel.Atipera.retrofit.record.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GithubClientService {
    private final GithubClient client;

    public ResponseEntity<RepositoriesResponseDto> getRepositories(String username) throws IOException {
        String query = "user:" + username;
        Response<RepositoriesResponseDto> response = client.repositoriesForUser(query).execute();

        if (response.isSuccessful() && (response.body() != null)) {
            RepositoriesResponseDto responseDto = new RepositoriesResponseDto();
            List<GitRepoDto> foundRepos = response.body().getItems().stream()
//                    .filter(i -> i.getFork().equals(Boolean.FALSE)).toList();
                    .map(repo -> switch (repo) {
                                case GitRepoDto(OwnerDto owner, String name, Boolean fork) when fork.equals(Boolean.FALSE) ->
                                    new GitRepoDto(owner, name, false);
                                default -> null;
                            })
                    .filter(Objects::nonNull)
                    .toList();
            responseDto.setItems(foundRepos);
            return ResponseEntity.ok(responseDto);
        } else if(response.code() == 422) {
            throw new UserNotFoundException(String.format("User %s not found", username));
        } else {
            throw new RuntimeException("Unsuccessful response");
        }
    }

    public ResponseEntity<BranchesResponseDto> getBranchesForRepository(String username, String repositoryName) throws IOException {
        Response<List<BranchDto>> response = client.getBranchesForRepository(username, repositoryName).execute();

        if (response.isSuccessful() && !(response.body() == null)) {
            BranchesResponseDto branchesResponseDto = new BranchesResponseDto();
            List<BranchDto> foundBranches = response.body();
            branchesResponseDto.setBranchList(foundBranches);

            return ResponseEntity.ok(branchesResponseDto);
        } else if(response.code() == 404) {
            throw new UserNotFoundException(String.format("User %s not found", username));
        } else {
            throw new RuntimeException("Unsuccessful response");
        }
    }

}

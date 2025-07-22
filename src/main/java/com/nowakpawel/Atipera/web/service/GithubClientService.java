package com.nowakpawel.Atipera.web.service;

import com.nowakpawel.Atipera.retrofit.GithubClient;
import com.nowakpawel.Atipera.retrofit.dto.GitRepoDto;
import com.nowakpawel.Atipera.retrofit.dto.ResponseDto;
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

    public ResponseEntity<ResponseDto> getRepositories(String username) throws IOException {
        String query = "user:" + username;
        Response<ResponseDto> response = client.repositoriesForUser(query).execute();

        if ((response.isSuccessful()) && !(response.body() == null)) {
            ResponseDto responseDto = new ResponseDto();
            List<GitRepoDto> foundRepos = response.body().getItems().stream()
                    .filter(i -> i.getFork().equals(Boolean.FALSE)).toList();
            responseDto.setItems(foundRepos);
            return ResponseEntity.ok(responseDto);
        } else {
            throw new RuntimeException("Unsuccessful response");
        }
    }

}

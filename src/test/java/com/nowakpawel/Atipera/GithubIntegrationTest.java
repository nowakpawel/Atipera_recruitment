package com.nowakpawel.Atipera;

import com.nowakpawel.Atipera.retrofit.GithubClient;
import com.nowakpawel.Atipera.retrofit.record.GitRepoDto;
import com.nowakpawel.Atipera.retrofit.record.OwnerDto;
import com.nowakpawel.Atipera.retrofit.record.RepositoriesResponseDto;
import com.nowakpawel.Atipera.web.service.GithubClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GithubIntegrationTest {
    @Mock
    private GithubClient githubClient;
    @InjectMocks
    private GithubClientService service;

    @Test
    void shouldFetchNonForkReposAndBranchesSuccessfully() throws IOException {
        final String USER = "nowakpawel";
        String query = "user:" + USER;
        OwnerDto owner = new OwnerDto(USER);

        GitRepoDto repository = new GitRepoDto(
        owner, "Test Repository",
        Boolean.FALSE);

        List<GitRepoDto> mockRepositories = List.of(repository);
        RepositoriesResponseDto mockResponse = new RepositoriesResponseDto();
        mockResponse.setItems(mockRepositories);

        Call<RepositoriesResponseDto> mockCall = mock(Call.class);

        when(mockCall.execute()).thenReturn(Response.success(mockResponse));
        when(githubClient.repositoriesForUser(query)).thenReturn(mockCall);

        ResponseEntity<RepositoriesResponseDto> response = service.getRepositories(USER);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
}

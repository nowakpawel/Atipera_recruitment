package com.nowakpawel.Atipera;

import com.nowakpawel.Atipera.retrofit.GithubClient;
import com.nowakpawel.Atipera.retrofit.dto.GitRepoDto;
import com.nowakpawel.Atipera.retrofit.dto.OwnerDto;
import com.nowakpawel.Atipera.retrofit.dto.RepositoriesResponseDto;
import com.nowakpawel.Atipera.web.service.GithubClientService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
public class GithubIntegrationTest {
    @MockitoBean
    private GithubClient githubClient;
    @Autowired
    private GithubClientService service;

    @Test
    void shouldFetchNonForkReposAndBranchesSuccessfully() throws IOException {
        String user = "nowakpawel";
        GitRepoDto repository = new GitRepoDto();
        repository.setName("Test repository");
        repository.setOwner(new OwnerDto("nowakpawel"));
        repository.setFork(Boolean.FALSE);

        List<GitRepoDto> mockRepositories = List.of(repository);
        RepositoriesResponseDto mockResponse = new RepositoriesResponseDto();
        mockResponse.setItems(mockRepositories);

        Call<RepositoriesResponseDto> mockCall = mock(Call.class);

        when(mockCall.execute()).thenReturn(Response.success(mockResponse));
        when(githubClient.repositoriesForUser(user)).thenReturn(mockCall);

        ResponseEntity<RepositoriesResponseDto> response = service.getRepositories(user);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
}

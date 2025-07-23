package com.nowakpawel.Atipera;

import com.nowakpawel.Atipera.retrofit.GithubClient;
import com.nowakpawel.Atipera.retrofit.dto.GitRepoDto;
import com.nowakpawel.Atipera.retrofit.dto.OwnerDto;
import com.nowakpawel.Atipera.retrofit.dto.RepositoriesResponseDto;
import com.nowakpawel.Atipera.web.service.GithubClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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


//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
        GitRepoDto repository = new GitRepoDto();
        repository.setName("Test repository");
        repository.setOwner(new OwnerDto("nowakpawel"));
        repository.setFork(Boolean.FALSE);

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

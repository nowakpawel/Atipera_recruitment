package com.nowakpawel.Atipera.web.controller;

import com.nowakpawel.Atipera.retrofit.record.BranchesResponseDto;
import com.nowakpawel.Atipera.retrofit.record.RepositoriesResponseDto;
import com.nowakpawel.Atipera.web.service.GithubClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/github-client")
public class GithubClientController {
    private final GithubClientService service;

    @GetMapping("/repositories/{username}")
    public ResponseEntity<RepositoriesResponseDto> displayRepositories(@PathVariable("username") String username) throws IOException {
        return service.getRepositories(username);
    }

    @GetMapping("project-branches/{username}/{repository}")
    public ResponseEntity<BranchesResponseDto> getBranchesForRepository(@PathVariable("username") String username, @PathVariable("repository") String repository) throws IOException {
        return service.getBranchesForRepository(username, repository);
    }
}

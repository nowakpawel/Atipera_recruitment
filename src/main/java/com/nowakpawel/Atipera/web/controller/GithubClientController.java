package com.nowakpawel.Atipera.web.controller;

import com.nowakpawel.Atipera.retrofit.dto.GitRepoDto;
import com.nowakpawel.Atipera.retrofit.dto.ResponseDto;
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

    @GetMapping("/{username}")
    public ResponseEntity<ResponseDto> displayRepositories(@PathVariable("username") String username) throws IOException {//TODO: use ResponseEntity
        return service.getRepositories(username);
    }
}

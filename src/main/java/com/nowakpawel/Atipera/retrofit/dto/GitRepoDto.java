package com.nowakpawel.Atipera.retrofit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GitRepoDto {
    @JsonProperty
    private OwnerDto owner;
    //TODO: each branch name and last commit

    @JsonProperty
    private String name;

    @JsonProperty
    private Boolean fork;
}

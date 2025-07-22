package com.nowakpawel.Atipera.retrofit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GitRepoDto {
    @JsonProperty
    private OwnerDto owner;

    @JsonProperty
    private String name;

    @JsonProperty
    private Boolean fork;
}

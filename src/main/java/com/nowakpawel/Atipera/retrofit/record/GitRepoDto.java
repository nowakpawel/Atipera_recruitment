package com.nowakpawel.Atipera.retrofit.record;

import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//@Data
//@AllArgsConstructor
public record GitRepoDto (@JsonProperty OwnerDto owner, @JsonProperty String name, @JsonProperty Boolean fork){}

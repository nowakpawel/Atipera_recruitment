package com.nowakpawel.Atipera.retrofit.record;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
@Data
public class RepositoriesResponseDto {
    private List<GitRepoDto> items;
}

package com.nowakpawel.Atipera.retrofit.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
@Data
public class ResponseDto {
//    private Integer total_count;
//    private Boolean incomplete_results;
    private List<GitRepoDto> items;
}

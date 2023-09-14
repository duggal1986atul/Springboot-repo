package com.telstra.codechallenge.gitusers.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@Setter
public class UsersAPIResponseDto {
    List<ItemAPIResponseDto> items;

}

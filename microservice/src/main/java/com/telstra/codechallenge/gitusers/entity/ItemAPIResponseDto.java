package com.telstra.codechallenge.gitusers.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@Setter
public class ItemAPIResponseDto {
    String login;
    Integer id;
    String html_url;

}

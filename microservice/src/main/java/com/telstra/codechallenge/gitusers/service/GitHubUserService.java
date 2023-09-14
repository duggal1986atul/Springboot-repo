package com.telstra.codechallenge.gitusers.service;

import com.telstra.codechallenge.gitusers.entity.UsersAPIResponseDto;
import com.telstra.codechallenge.gitusers.exception.APIException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@Slf4j
public class GitHubUserService {
    @Value("${github.user.base.url}")
    private String gitHubBaseUrl;

    @Value("${github.user.api.version.value}")
    private String value;

    @Value("${github.user.api.version.key}")
    private String key;

    @Value("${github.user.api.queryParam}")
    private  String queryParam;

    private final RestTemplate restTemplate;
    private HttpEntity<Void> requestEntity;

    public GitHubUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UsersAPIResponseDto getUserAccount() throws APIException{
       try {
           log.info("inside rest connection {}",gitHubBaseUrl);
           requestEntity  = new HttpEntity<>(getHeaders());
           ResponseEntity<UsersAPIResponseDto> response= restTemplate.exchange(gitHubBaseUrl+"?"+ queryParam, HttpMethod.GET,requestEntity, UsersAPIResponseDto.class);
           if (!ObjectUtils.isEmpty(response) && !ObjectUtils.isEmpty(response.getBody())) {
               return response.getBody();
           }
       }
       catch (HttpClientErrorException exception){
           log.error("HttpClientErrorException Exception while connection {}",exception.getMessage());
           throw new APIException(exception.getStatusCode(),exception.getMessage(), LocalDateTime.now());

       }
       catch(HttpStatusCodeException e) {
           log.error("HttpStatusCodeException Exception while connection {}",e.getMessage());
           throw new APIException(e.getStatusCode(),e.getMessage(), LocalDateTime.now());
       }

       catch(RestClientException e){
           log.error("RestClientException Exception while connection {}",e.getMessage());
           throw new APIException(null,e.getMessage(), LocalDateTime.now());
       }

       return null;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(key,value);
        httpHeaders.set("Accept","application/vnd.github+json");
        return httpHeaders;
    }
}

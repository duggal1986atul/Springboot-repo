package com.telstra.codechallenge.gitusers.controller;

import com.telstra.codechallenge.gitusers.entity.ItemAPIResponseDto;
import com.telstra.codechallenge.gitusers.entity.UsersAPIResponseDto;
import com.telstra.codechallenge.gitusers.exception.APIException;
import com.telstra.codechallenge.gitusers.service.GitHubUserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private GitHubUserService userService;

    @GetMapping(path = "/getUserAccounts",produces = "application/json")
    public ResponseEntity<UsersAPIResponseDto> getUserAccounts(@RequestParam(required=true,defaultValue="30") Integer resultSize) throws APIException{

        if(resultSize>30)
        {
            throw new APIException(
                HttpStatusCode.valueOf(400), "Resultset cant be more than 30", LocalDateTime.now());
        }
        UsersAPIResponseDto responseDto = userService.getUserAccount();
        return  ResponseEntity.ok().body(sublist(responseDto,resultSize));
    }

    private UsersAPIResponseDto sublist(@NonNull UsersAPIResponseDto apiResponse,Integer resultSize){
        if(ObjectUtils.isEmpty(apiResponse.getItems()))
            return apiResponse;
        UsersAPIResponseDto responseDto = new UsersAPIResponseDto();
        List<ItemAPIResponseDto> responseDtoList = new ArrayList<ItemAPIResponseDto>();
        responseDtoList.addAll(apiResponse.getItems().subList(0,resultSize));
        responseDto.setItems(responseDtoList);
        return responseDto;
    }
}
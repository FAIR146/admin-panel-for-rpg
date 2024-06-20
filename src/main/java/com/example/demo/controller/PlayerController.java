package com.example.demo.controller;

import com.example.demo.controller.put.CreatePlayerRequest;
import com.example.demo.controller.response.CreatePlayerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface PlayerController {
    @PostMapping("/rest/players")
    CreatePlayerResponse createPlayer (@Valid @RequestBody CreatePlayerRequest createPlayerRequest);
    @DeleteMapping("/rest/players/{id}")
    void deletePlayerById (@PathVariable long id);
    @GetMapping("/rest/players/{id}")
    ResponseEntity<GetPlayerResponse> getPlayerById (@PathVariable long id);
}

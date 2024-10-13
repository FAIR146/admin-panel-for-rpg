package com.example.demo.controller;

import com.example.demo.controller.put.CreatePlayerRequest;
import com.example.demo.controller.put.GetPlayersRequest;
import com.example.demo.controller.response.PlayerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface PlayerController {
    @PostMapping("/rest/players")
    PlayerResponse createPlayer (@Valid @RequestBody CreatePlayerRequest createPlayerRequest);
    @DeleteMapping("/rest/players/{id}")
    ResponseEntity<PlayerResponse> deletePlayerById (@PathVariable long id);
    @GetMapping("/rest/players/{id}")
    ResponseEntity<PlayerResponse> getPlayerById (@PathVariable long id);
    @PostMapping("/rest/players/{id}")
    ResponseEntity<PlayerResponse> updatePlayerById (@Valid @RequestBody CreatePlayerRequest createPlayerRequest, @PathVariable long id);
    @GetMapping("/rest/players/count")
    Integer getFilteredPlayersCount (GetPlayersRequest getPlayersRequest);
    @GetMapping("/rest/players")
    List<PlayerResponse> getFilteredPlayers(GetPlayersRequest getPlayersRequest);

}

package com.example.demo.controller;

import com.example.demo.controller.put.CreatePlayerRequest;
import com.example.demo.controller.put.GetPlayerCountRequest;
import com.example.demo.controller.put.GetPlayersListRequest;
import com.example.demo.controller.response.PlayerResponse;
import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

public interface PlayerController {
    @PostMapping("/rest/players")
    PlayerResponse createPlayer (@Valid @RequestBody CreatePlayerRequest createPlayerRequest);
    @DeleteMapping("/rest/players/{id}")
    void deletePlayerById (@PathVariable long id);
    @GetMapping("/rest/players/{id}")
    ResponseEntity<PlayerResponse> getPlayerById (@PathVariable long id);
    @PostMapping("/players/{id}")
    PlayerResponse updatePlayerById (@Valid @RequestBody CreatePlayerRequest createPlayerRequest, @PathVariable long id);
//    @GetMapping("/rest/players")
//    List<PlayerDto> getAllPlayers();
    @GetMapping("/rest/players/count")
    int getFilteredPlayersCount(@RequestBody GetPlayerCountRequest getPlayerCountRequest);
    @GetMapping("/rest/players")
    List<PlayerResponse> getFilteredPlayers(@RequestBody GetPlayersListRequest getPlayersListRequest);

}

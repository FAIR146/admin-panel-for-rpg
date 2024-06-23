package com.example.demo.controller;

import com.example.demo.controller.put.CreatePlayerRequest;
import com.example.demo.controller.put.GetPlayerCountRequest;
import com.example.demo.controller.response.PlayerResponse;
import com.example.demo.dto.PlayerDto;
import com.example.demo.service.Mapper;
import com.example.demo.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;


@RequiredArgsConstructor
@RestController
public class PlayerControllerImpl implements PlayerController {
    private final PlayerService playerService;

    @Override
    public PlayerResponse createPlayer(CreatePlayerRequest createPlayerRequest) {
        PlayerDto playerDto = Mapper.mapFromRequestToDto(createPlayerRequest);
        PlayerDto createdPlayerDto = playerService.createPlayer(playerDto);

        return Mapper.mapFromDtoToCreateResponse(createdPlayerDto);
    }

    @Override
    public void deletePlayerById (long id) {
        playerService.removePlayerById(id);
    }

    @Override
    public ResponseEntity<PlayerResponse> getPlayerById (@RequestParam long id) {
        PlayerDto playerDto = playerService.getPlayerById(id);
        if (playerDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PlayerResponse PlayerResponse = Mapper.mapFromDtoToGetResponse(playerDto);
        return ResponseEntity.ok(PlayerResponse);
    }
    @Override
    public int getPlayersCount (GetPlayerCountRequest getPlayerCountRequest) {
        return 1;
    }
    @Override
    public PlayerResponse updatePlayerById(CreatePlayerRequest createPlayerRequest, long id) {
        PlayerDto playerDto = Mapper.mapFromRequestToDto(createPlayerRequest);
        playerService.updatePlayerById(playerDto);
        return Mapper.mapFromDtoToGetResponse(playerDto);
    }
}

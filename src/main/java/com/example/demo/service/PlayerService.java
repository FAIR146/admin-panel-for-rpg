package com.example.demo.service;

import com.example.demo.controller.put.GetPlayersRequest;
import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Player;

import java.util.List;

public interface PlayerService {
    PlayerDto createPlayer (PlayerDto playerDto);
    PlayerDto removePlayerById (long id);
    PlayerDto getPlayerById (long id);
    PlayerDto updatePlayerById (PlayerDto playerDto);
    List<Player> getAllPlayers();
    Integer getFilteredPlayersCount(GetPlayersRequest getPlayersRequest);
    List<PlayerDto> getFilteredPlayers(GetPlayersRequest getPlayersRequest);

}

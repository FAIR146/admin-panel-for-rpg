package com.example.demo.service;

import com.example.demo.controller.put.GetPlayerCountRequest;
import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Player;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;

import java.util.List;

public interface PlayerService {
    PlayerDto createPlayer (PlayerDto playerDto);
    PlayerDto removePlayerById (long id);
    PlayerDto getPlayerById (long id);
    PlayerDto updatePlayerById (PlayerDto playerDto);
    List<Player> getAllPlayers();
    int getPlayersCount();
    int getFilteredPlayersCount(GetPlayerCountRequest getPlayerCountRequest);
}

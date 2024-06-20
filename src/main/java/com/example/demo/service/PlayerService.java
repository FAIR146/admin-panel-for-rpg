package com.example.demo.service;

import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Player;

public interface PlayerService {
    PlayerDto createPlayer (PlayerDto playerDto);
    PlayerDto removePlayerById (long id);
    PlayerDto getPlayerById (long id);
    PlayerDto updatePlayerById (PlayerDto playerDto);
}

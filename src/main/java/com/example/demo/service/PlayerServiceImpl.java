package com.example.demo.service;

import com.example.demo.dao.PlayerDao;
import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    private final PlayerDao playerDao;
    public PlayerServiceImpl(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    public PlayerDto createPlayer (PlayerDto playerDto) {
        Player player = Mapper.mapFromDtoToPlayer(playerDto);
        Player createdPlayer = playerDao.createPlayer(player);
        return Mapper.mapToDto(createdPlayer);
    }

    @Override
    public PlayerDto removePlayerById(long id) {
        Player player = playerDao.getPlayerById(id);
        PlayerDto playerDto = Mapper.mapToDto(player);
        playerDao.removePlayerById(id);
        return playerDto;
    }

    @Override
    public PlayerDto getPlayerById(long id) {
        Player player = playerDao.getPlayerById(id);
        return Mapper.mapToDto(player);
    }

    @Override
    public PlayerDto updatePlayerById (PlayerDto playerDto) {
        Player player = Mapper.mapFromDtoToPlayer(playerDto);
        PlayerDto updatedPlayerDto = Mapper.mapToDto(player);

        playerDao.updatePlayer(playerDto);
        return updatedPlayerDto;
    }

}

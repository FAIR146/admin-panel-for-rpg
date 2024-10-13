package com.example.demo.service;

import com.example.demo.controller.put.GetPlayersRequest;
import com.example.demo.dao.PlayerDao;
import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    private final PlayerDao playerDao;
    public PlayerServiceImpl(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    public PlayerDto createPlayer (PlayerDto playerDto) {
        calculateLevelAndExperience(playerDto);
        Player player = Mapper.mapFromDtoToPlayer(playerDto);
        Player createdPlayer = playerDao.createPlayer(player);
        return Mapper.mapToDto(createdPlayer);
    }

    @Override
    public PlayerDto removePlayerById(long id) {
        Player player = playerDao.getPlayerById(id);
        if (player == null) {
            return null;
        }

        PlayerDto playerDto = Mapper.mapToDto(player);
        playerDao.removePlayerById(id);
        return playerDto;
    }

    @Override
    public PlayerDto getPlayerById(long id) {
        Player player = playerDao.getPlayerById(id);
        if (player == null) {
            return null;
        }
        return Mapper.mapToDto(player);
    }

    @Override
    public PlayerDto  updatePlayerById (PlayerDto playerDto) {
        calculateLevelAndExperience(playerDto);
        Player player = Mapper.mapFromDtoToPlayer(playerDto);
        PlayerDto updatedPlayerDto = Mapper.mapToDto(player);

        playerDao.updatePlayer(playerDto);
        return updatedPlayerDto;
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerDao.getAllPlayers();
    }

    private void calculateLevelAndExperience(PlayerDto playerDto) {
        int experience = playerDto.getExperience();
        int level = (int) ((Math.sqrt(2500 + 200 * experience) - 50) / 100);
        int untilNextLevel = 50 * (level + 1) * (level + 2) - experience;

        playerDto.setLevel(level);
        playerDto.setUntilNextLevel(untilNextLevel);
    }
    @Override
    public Integer getFilteredPlayersCount(GetPlayersRequest getPlayersRequest) {

        return playerDao.getFilteredPlayersCount(getPlayersRequest);
    }

    @Override
    public List<PlayerDto> getFilteredPlayers(GetPlayersRequest getPlayersRequest) {
        List<Player> players = playerDao.getFilteredPlayers(getPlayersRequest);
        return players.stream()
                .map(Mapper::mapToDto)
                .collect(Collectors.toList());
    }
}

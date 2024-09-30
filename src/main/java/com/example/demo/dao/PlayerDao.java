package com.example.demo.dao;

import com.example.demo.controller.put.GetPlayersRequest;
import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Player;

import java.util.List;

public interface PlayerDao {
    Player createPlayer(Player player);

    boolean removePlayerById(long id);

    Player getPlayerById(long id);

    void updatePlayer(PlayerDto playerDto);

    List<Player> getAllPlayers();

    Integer getFilteredPlayersCount(GetPlayersRequest getPlayersRequest);
    List<Player> getFilteredPlayers(GetPlayersRequest getPlayersRequest);



}

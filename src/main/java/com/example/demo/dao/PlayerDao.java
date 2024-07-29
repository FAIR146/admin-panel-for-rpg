package com.example.demo.dao;

import com.example.demo.controller.put.GetPlayerCountRequest;
import com.example.demo.controller.put.GetPlayersListRequest;
import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Player;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;

import java.util.List;

public interface PlayerDao {
    Player createPlayer(Player player);

    void removePlayerById(long id);

    Player getPlayerById(long id);

    void updatePlayer(PlayerDto playerDto);

    List<Player> getAllPlayers();

    int getFilteredPlayersCount(GetPlayerCountRequest getPlayerCountRequest);

    List<Player> getFilteredPlayers(GetPlayersListRequest getPlayersListRequest);



}

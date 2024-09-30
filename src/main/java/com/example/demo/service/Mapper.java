package com.example.demo.service;

import com.example.demo.controller.put.CreatePlayerRequest;
import com.example.demo.controller.response.PlayerResponse;
import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Player;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;


public class Mapper {
    public static PlayerDto mapToDto (Player player) {
        PlayerDto playerDto = new PlayerDto();

        playerDto.setId(player.getId());
        playerDto.setName(player.getName());
        playerDto.setTitle(player.getTitle());
        playerDto.setRace(player.getRace());
        playerDto.setProfession(player.getProfession());
        playerDto.setBirthday(player.getBirthday());
        playerDto.setBanned(player.isBanned());
        playerDto.setExperience(player.getExperience());
        playerDto.setLevel(player.getLevel());
        playerDto.setUntilNextLevel(player.getUntilNextLevel());

        return playerDto;
    }

    public static PlayerResponse mapFromDtoToCreateResponse(PlayerDto playerDto) {
        PlayerResponse playerResponse = new PlayerResponse();

        playerResponse.setId(playerDto.getId());
        playerResponse.setName(playerDto.getName());
        playerResponse.setTitle(playerDto.getTitle());
        playerResponse.setRace(playerDto.getRace());
        playerResponse.setProfession(playerDto.getProfession());
        playerResponse.setBirthday(playerDto.getBirthday());
        playerResponse.setBanned(playerDto.isBanned());
        playerResponse.setExperience(playerDto.getExperience());
        playerResponse.setLevel(playerDto.getLevel());
        playerResponse.setUntilNextLevel(playerDto.getUntilNextLevel());

        return playerResponse;
    }

    public static PlayerResponse mapFromDtoToGetResponse (PlayerDto playerDto) {
        PlayerResponse PlayerResponse = new PlayerResponse();

        PlayerResponse.setId(playerDto.getId());
        PlayerResponse.setName(playerDto.getName());
        PlayerResponse.setTitle(playerDto.getTitle());
        PlayerResponse.setRace(playerDto.getRace());
        PlayerResponse.setProfession(playerDto.getProfession());
        PlayerResponse.setBirthday(playerDto.getBirthday());
        PlayerResponse.setBanned(playerDto.isBanned());
        PlayerResponse.setExperience(playerDto.getExperience());
        PlayerResponse.setLevel(playerDto.getLevel());
        PlayerResponse.setUntilNextLevel(playerDto.getUntilNextLevel());

        return PlayerResponse;
    }
    public static PlayerDto mapFromRequestToDto (CreatePlayerRequest playerRequest) {
        PlayerDto playerDto = new PlayerDto();

        playerDto.setId(playerRequest.getId());
        playerDto.setName(playerRequest.getName());
        playerDto.setBirthday(playerRequest.getBirthday());
        playerDto.setTitle(playerRequest.getTitle());
        playerDto.setRace(playerRequest.getRace());
        playerDto.setProfession(playerRequest.getProfession());
        playerDto.setBanned(playerRequest.getBanned());
        playerDto.setExperience(playerRequest.getExperience());

        return playerDto;
    }

    public static Player mapFromDtoToPlayer (PlayerDto playerDto) {
        Player player = new Player();

        player.setId(playerDto.getId());
        player.setName(playerDto.getName());
        player.setTitle(playerDto.getTitle());
        player.setRace(playerDto.getRace());
        player.setProfession(playerDto.getProfession());
        player.setBirthday(playerDto.getBirthday());
        player.setBanned(playerDto.isBanned());
        player.setExperience(playerDto.getExperience());
        player.setLevel(playerDto.getLevel());
        player.setUntilNextLevel(playerDto.getUntilNextLevel());

        return player;
    }
}

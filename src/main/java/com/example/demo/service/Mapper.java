package com.example.demo.service;

import com.example.demo.controller.put.CreatePlayerRequest;
import com.example.demo.controller.response.CreatePlayerResponse;
import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Player;


public class Mapper {
    public static PlayerDto mapToDto (Player player) {
        PlayerDto playerDto = new PlayerDto();

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

    public static CreatePlayerResponse mapFromDtoToCreateResponse(PlayerDto playerDto) {
        CreatePlayerResponse createPlayerResponse = new CreatePlayerResponse();

        createPlayerResponse.setName(playerDto.getName());
        createPlayerResponse.setTitle(playerDto.getTitle());
        createPlayerResponse.setRace(playerDto.getRace());
        createPlayerResponse.setProfession(playerDto.getProfession());
        createPlayerResponse.setBirthday(playerDto.getBirthday());
        createPlayerResponse.setBanned(playerDto.isBanned());
        createPlayerResponse.setExperience(playerDto.getExperience());
        createPlayerResponse.setLevel(playerDto.getLevel());
        createPlayerResponse.setUntilNextLevel(playerDto.getUntilNextLevel());

        return createPlayerResponse;
    }

    public static GetPlayerResponse mapFromDtoToGetResponse (PlayerDto playerDto) {
        GetPlayerResponse getPlayerResponse = new GetPlayerResponse();

        getPlayerResponse.setName(playerDto.getName());
        getPlayerResponse.setTitle(playerDto.getTitle());
        getPlayerResponse.setRace(playerDto.getRace());
        getPlayerResponse.setProfession(playerDto.getProfession());
        getPlayerResponse.setBirthday(playerDto.getBirthday());
        getPlayerResponse.setBanned(playerDto.isBanned());
        getPlayerResponse.setExperience(playerDto.getExperience());
        getPlayerResponse.setLevel(playerDto.getLevel());
        getPlayerResponse.setUntilNextLevel(playerDto.getUntilNextLevel());

        return getPlayerResponse;
    }
    public static PlayerDto mapFromRequestToDto (CreatePlayerRequest playerRequest) {
        PlayerDto playerDto = new PlayerDto();

        playerDto.setName(playerRequest.getName());
        playerDto.setTitle(playerRequest.getTitle());
        playerDto.setRace(playerRequest.getRace());
        playerDto.setProfession(playerRequest.getProfession());
        playerDto.setBirthday(playerRequest.getBirthday());
        playerDto.setBanned(playerRequest.getBanned());
        playerDto.setExperience(playerRequest.getExperience());

        return playerDto;
    }

    public static Player mapFromDtoToPlayer (PlayerDto playerDto) {
        Player player = new Player();

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

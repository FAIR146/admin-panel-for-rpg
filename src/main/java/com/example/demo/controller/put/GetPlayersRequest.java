package com.example.demo.controller.put;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import com.example.demo.filter.PlayerOrder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetPlayersRequest {
    private long id;
    private String name;
    private String title;
    @NotNull
    private Race race;
    private Profession profession;
    private Long after;
    private Long before;
    private Boolean banned;
    private Integer minExperience;
    private Integer maxExperience;
    private Integer minLevel;
    private Integer maxLevel;
    private PlayerOrder order = PlayerOrder.ID;
    private Integer pageNumber = 0;
    private Integer pageSize = 3;

}

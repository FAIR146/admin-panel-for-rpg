package com.example.demo.controller.put;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import com.example.demo.filter.PlayerOrder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class GetPlayersRequest {
    @NotNull
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String title;
    @NotNull
    private Race race;
    @NotNull
    private Profession profession;
    @NotNull
    private Long after;
    @NotNull
    private Long before;
    @NotNull
    private Boolean banned;
    @NotNull
    private Integer minExperience;
    @NotNull
    private Integer maxExperience;
    @Size @NotNull
    private Integer minLevel;
    @NotNull
    private Integer maxLevel;
    @NotNull
    private PlayerOrder order = PlayerOrder.ID;
    @NotNull
    private Integer pageNumber = 0;
    @NotNull
    private Integer pageSize = 3;

}

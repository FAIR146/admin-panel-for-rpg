package com.example.demo.controller.put;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import com.example.demo.filter.PlayerOrder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    @Min(0)
    private Long after;
    @NotNull
    @Min(0)
    private Long before;
    @NotNull
    private Boolean banned;
    @NotNull
    @Min(0)
    private Integer minExperience;
    @NotNull
    @Min(0)
    private Integer maxExperience;
    @NotNull
    @Min(0)
    private Integer minLevel;
    @NotNull
    @Min(0)
    private Integer maxLevel;
    @NotNull
    private PlayerOrder order = PlayerOrder.ID;
    @NotNull
    private Integer pageNumber = 0;
    @NotNull
    private Integer pageSize = 3;

}

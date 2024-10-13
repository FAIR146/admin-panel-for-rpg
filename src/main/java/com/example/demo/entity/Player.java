package com.example.demo.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Player {
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
    private Long birthday;
    @NotNull
    private boolean banned;
    @NotBlank
    @Min(0)
    private int experience;
    @NotNull
    @Min(0)
    private int level;
    @NotNull
    @Min(0)
    private int untilNextLevel;

}

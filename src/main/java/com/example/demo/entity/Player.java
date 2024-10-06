package com.example.demo.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

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
    @NotNull @Size
    private Long birthday;
    @NotNull
    private boolean banned;
    @NotBlank
    private int experience;
    @NotNull @Size
    private int level;
    @NotNull @Size
    private int untilNextLevel;

}

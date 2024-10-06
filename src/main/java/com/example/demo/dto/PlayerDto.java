package com.example.demo.dto;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class PlayerDto {
    @NotNull
    private Long id;
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
    @NotNull @Size
    private int experience;
    @NotNull @Size
    private int level;
    @NotNull @Size
    private int untilNextLevel;
}

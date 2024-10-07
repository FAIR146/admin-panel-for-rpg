package com.example.demo.dto;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.Data;

import javax.validation.constraints.Min;
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
    @NotNull
    @Min(0)
    private Long birthday;
    @NotNull
    private boolean banned;
    @NotNull
    @Min(0)
    private int experience;
    @NotNull
    @Min(0)
    private int level;
    @NotNull
    @Min(0)
    private int untilNextLevel;
}

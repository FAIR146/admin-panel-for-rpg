package com.example.demo.controller.put;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class CreatePlayerRequest {
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
    private Boolean banned = false;
    @NotNull
    @Min(0)
    private Integer experience;

}

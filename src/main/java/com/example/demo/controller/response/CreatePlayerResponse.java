package com.example.demo.controller.response;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreatePlayerResponse {
    private Long id;
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private Long birthday;
    private Boolean banned;
    private Integer experience;
    private Integer level;
    private Integer untilNextLevel;
}

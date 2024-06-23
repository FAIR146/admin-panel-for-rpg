package com.example.demo.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Player {
    private long id;
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private LocalDate birthday;
    private boolean banned;
    private int experience;
    private int level;
    private int untilNextLevel;

}

package com.example.demo.dao;

import com.example.demo.controller.put.GetPlayerCountRequest;
import com.example.demo.controller.put.GetPlayersListRequest;
import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Player;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DataBaseDao implements PlayerDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsertPlayer;

    private final RowMapper<Player> playerRowMapper = (rs, rowNum) -> {
        Player player = new Player();
        player.setId(rs.getLong("id"));
        player.setName(rs.getString("name"));
        player.setTitle(rs.getString("title"));
        player.setRace(Race.valueOf(rs.getString("race")));
        player.setProfession(Profession.valueOf(rs.getString("profession")));
        player.setBirthday(rs.getDate("birthday").toLocalDate());
        player.setBanned(rs.getBoolean("banned"));
        player.setExperience(rs.getInt("experience"));
        player.setLevel(rs.getInt("level"));
        player.setUntilNextLevel(rs.getInt("untilnextlevel"));
        return player;
    };


    DataBaseDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        this.simpleJdbcInsertPlayer = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("player")
                .usingColumns("name", "title", "race", "profession", "birthday", "banned", "experience", "level", "untilnextlevel")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Player createPlayer(Player player) {
        String sql = "INSERT INTO player (name, title, race, profession, birthday, banned, experience, level, untilnextlevel) VALUES (?, ?, ?, ?, ?, ?, ?,?,?) RETURNING id";
        long playerId = jdbcTemplate.queryForObject(sql, new Object[]{player.getName(), player.getTitle(), player.getRace().name(), player.getProfession().name(), java.sql.Date.valueOf(player.getBirthday()), player.isBanned(), player.getExperience(),player.getLevel(), player.getUntilNextLevel()}, Long.class);

        String selectSql = "SELECT * FROM player WHERE id = ?";
        return jdbcTemplate.queryForObject(selectSql, new Object[]{playerId}, playerRowMapper);
    }
    @Override
    public void removePlayerById (long id) {
        String sql = "DELETE FROM player WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    @Override
    public Player getPlayerById (long id) {
        String sql = "SELECT id, name, title, race, profession, birthday, banned, experience, level, untilnextlevel " +
                "FROM " +
                "player " +
                "WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, playerRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public void updatePlayer (PlayerDto playerDto) {
        String sql = "UPDATE player SET " +
                "name = ?, " +
                "title = ?, " +
                "race = ?, " +
                "profession = ?, " +
                "birthday = ?, " +
                "banned = ?, " +
                "experience = ?, " +
                "level = ?, " +
                "untilnextlevel = ? " +
                "WHERE id = ?";

        jdbcTemplate.update(sql, playerDto.getName(), playerDto.getTitle(), playerDto.getRace().name(), playerDto.getProfession().name(), playerDto.getBirthday(), playerDto.isBanned(), playerDto.getExperience(), playerDto.getLevel(), playerDto.getUntilNextLevel(), playerDto.getId());
    }
    @Override
    public List<Player> getAllPlayers() {
        String sql = "SELECT id, name, title, race, profession, birthday, banned, experience, level, untilnextlevel FROM player";
        return jdbcTemplate.query(sql, playerRowMapper);
    }
    @Override
    public Integer getFilteredPlayersCount(GetPlayerCountRequest getPlayerCountRequest) {
        String sql = "SELECT COUNT(*) FROM player WHERE name like ? AND title = ? AND race = ? AND profession = ? AND " +
                "? <= birthday AND birthday <= ? AND banned = ? AND ? <= experience AND experience <= ? AND ? <= level AND level <= ?";

        return jdbcTemplate.queryForObject(sql, Integer.class, "%" + getPlayerCountRequest.getName() + "%", getPlayerCountRequest.getTitle(), getPlayerCountRequest.getRace().name(),
                getPlayerCountRequest.getProfession().name(), new java.sql.Date(getPlayerCountRequest.getAfter()), new java.sql.Date(getPlayerCountRequest.getBefore()), getPlayerCountRequest.getBanned(),
                getPlayerCountRequest.getMinExperience(), getPlayerCountRequest.getMaxExperience(), getPlayerCountRequest.getMinLevel(), getPlayerCountRequest.getMaxLevel());
    }
    @Override
    public List<Player> getFilteredPlayers(GetPlayersListRequest getPlayersListRequest) {
        String sql = "SELECT FROM player WHERE name like ? AND title = ? AND race = ? AND profession = ? AND " +
                "? <= birthday AND birthday <= ? AND banned = ? AND ? <= experience AND experience <= ? AND ? <= level AND level <= ? AND ";

        if (getPlayersListRequest.getTitle() != null) {
            sql + "title = ? AND ";
        }
        if (getPlayersListRequest.getRace() != null) {
            String sqlRace = "race = ? AND";
        }
        if (getPlayersListRequest.getProfession() != null) {
            String sqlProfsesion
        }


        return jdbcTemplate.query(sql, playerRowMapper,"%" + getPlayersListRequest.getName() + "%", getPlayersListRequest.getTitle(), getPlayersListRequest.getRace().name(),
                getPlayersListRequest.getProfession().name(), getPlayersListRequest.getBanned(), getPlayersListRequest.getMinExperience(), getPlayersListRequest.getMaxExperience(), getPlayersListRequest.getMinLevel(),
                getPlayersListRequest.getMaxLevel());
    }
}

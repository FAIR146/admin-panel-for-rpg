package com.example.demo.dao;

import com.example.demo.controller.put.GetPlayersRequest;
import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Player;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
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
        player.setBirthday(rs.getDate("birthday").getTime());
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

//    @Override
//    public Player createPlayer(Player player)  {
//        String sql = "INSERT INTO player (name, title, race, profession, birthday, banned, experience, level, untilnextlevel) VALUES (?, ?, ?, ?, ?, ?, ?,?,?) RETURNING id";
//        long playerId = jdbcTemplate.queryForObject(sql, new Object[]{player.getName(), player.getTitle(), player.getRace().name(), player.getProfession().name(), player.getBirthday(), player.isBanned(), player.getExperience(),player.getLevel(), player.getUntilNextLevel()}, Long.class);
//
//        String selectSql = "SELECT * FROM player WHERE id = ?";
//        return jdbcTemplate.queryForObject(selectSql, new Object[]{playerId}, playerRowMapper);
//    }
@Override
public Player createPlayer(Player player) {
    java.sql.Date birthdayDate = new java.sql.Date(player.getBirthday());
    String sql = "INSERT INTO player (name, title, race, profession, birthday, banned, experience, level, untilnextlevel) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";


    long playerId = jdbcTemplate.queryForObject(sql, new Object[]{player.getName(), player.getTitle(), player.getRace().name(), player.getProfession().name(), birthdayDate, player.isBanned(), player.getExperience(), player.getLevel(), player.getUntilNextLevel()}, Long.class);
    String selectSql = "SELECT * FROM player WHERE id = ?";
    return jdbcTemplate.queryForObject(selectSql, new Object[]{playerId}, playerRowMapper);
}
    @Override
    public boolean removePlayerById (long id) {
        String sql = "DELETE FROM player WHERE id = ?";
        int updatedRows = jdbcTemplate.update(sql, id);

        return updatedRows != 0;
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

        log.info("Updating player with ID: " + playerDto.getId() + " Name: " + playerDto.getName());
        java.sql.Date birthdayDate = new java.sql.Date(playerDto.getBirthday());

        jdbcTemplate.update(sql, playerDto.getName(), playerDto.getTitle(), playerDto.getRace().name(), playerDto.getProfession().name(), birthdayDate, playerDto.isBanned(), playerDto.getExperience(), playerDto.getLevel(), playerDto.getUntilNextLevel(), playerDto.getId());
    }

    @Override
    public List<Player> getAllPlayers() {
        String sql = "SELECT id, name, title, race, profession, birthday, banned, experience, level, untilnextlevel FROM player";
        return jdbcTemplate.query(sql, playerRowMapper);
    }
    @Override
    public Integer getFilteredPlayersCount(GetPlayersRequest getPlayersRequest) {
        String sql = "SELECT COUNT(*) FROM player";
        List<Object> values = new ArrayList<>();
        sql = filter(getPlayersRequest,sql,values);

        return jdbcTemplate.queryForObject(sql, Integer.class, values.toArray());
        }

    @Override
    public List<Player> getFilteredPlayers(GetPlayersRequest getPlayersRequest) {
        String sql = "SELECT * FROM player";
        List<Object> values = new ArrayList<>();

        sql = filter(getPlayersRequest, sql, values);


        if (getPlayersRequest.getOrder() != null) {
            sql+= " ORDER BY " + getPlayersRequest.getOrder();
        }

        if (getPlayersRequest.getPageNumber() != null && getPlayersRequest.getPageSize() != null) {
            int offset = getPlayersRequest.getPageNumber() * getPlayersRequest.getPageSize();
            sql+= " LIMIT ? OFFSET ? ";
            values.add(getPlayersRequest.getPageSize());
            values.add(offset);
        }

        return jdbcTemplate.query(sql.toString(), playerRowMapper, values.toArray());
    }

    public String filter(GetPlayersRequest getPlayersRequest, String rows, List<Object> values) {
        List<String> clauses = new ArrayList<>();

        if (getPlayersRequest.getName() != null) {
            clauses.add("name LIKE ?");
            values.add("%" + getPlayersRequest.getName() + "%");
        }

        if (getPlayersRequest.getTitle() != null) {
            clauses.add("title LIKE ?");
            values.add("%" + getPlayersRequest.getTitle() + "%");
        }

        if (getPlayersRequest.getRace() != null) {
            clauses.add("race = ?");
            values.add(getPlayersRequest.getRace().name());
        }

        if (getPlayersRequest.getProfession() != null) {
            clauses.add("profession = ?");
            values.add(getPlayersRequest.getProfession().name());
        }

        if (getPlayersRequest.getAfter() != null) {
            clauses.add("birthday >= ?");
            values.add(new java.sql.Timestamp(getPlayersRequest.getAfter()));
        }

        if (getPlayersRequest.getBefore() != null) {
            clauses.add("birthday <= ?");
            values.add(new java.sql.Timestamp(getPlayersRequest.getBefore()));
        }

        if (getPlayersRequest.getBanned() != null) {
            clauses.add("banned = ?");
            values.add(getPlayersRequest.getBanned());
        }

        if (getPlayersRequest.getMinExperience() != null) {
            clauses.add("experience >= ?");
            values.add(getPlayersRequest.getMinExperience());
        }

        if (getPlayersRequest.getMaxExperience() != null) {
            clauses.add("experience <= ?");
            values.add(getPlayersRequest.getMaxExperience());
        }

        if (getPlayersRequest.getMinLevel() != null) {
            clauses.add("level >= ?");
            values.add(getPlayersRequest.getMinLevel());
        }

        if (getPlayersRequest.getMaxLevel() != null) {
            clauses.add("level <= ? ");
            values.add(getPlayersRequest.getMaxLevel());
        }

        if (!clauses.isEmpty()) {
            rows += " WHERE " + String.join(" AND ", clauses);
        }

//        if (!clauses.isEmpty()) {
//            String joinedClauses = String.join(" AND ", clauses);
//            rows += " WHERE " + joinedClauses;
//        }

        return rows;
    }
}



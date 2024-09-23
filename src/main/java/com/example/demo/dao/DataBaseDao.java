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
        player.setBirthday(rs.getLong("birthday"));
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
        long playerId = jdbcTemplate.queryForObject(sql, new Object[]{player.getName(), player.getTitle(), player.getRace().name(), player.getProfession().name(),player.getBirthday(), player.isBanned(), player.getExperience(),player.getLevel(), player.getUntilNextLevel()}, Long.class);

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

        log.info("Updating player with ID: " + playerDto.getId() + " Name: " + playerDto.getName());
        jdbcTemplate.update(sql, playerDto.getName(), playerDto.getTitle(), playerDto.getRace().name(), playerDto.getProfession().name(), playerDto.getBirthday(), playerDto.isBanned(), playerDto.getExperience(), playerDto.getLevel(), playerDto.getUntilNextLevel(), playerDto.getId());
    }

    @Override
    public List<Player> getAllPlayers() {
        String sql = "SELECT id, name, title, race, profession, birthday, banned, experience, level, untilnextlevel FROM player";
        return jdbcTemplate.query(sql, playerRowMapper);
    }
        @Override
        public Integer getFilteredPlayersCount(GetPlayersRequest getPlayersRequest) {
            StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM player ");
            List<Object> params = new ArrayList<>();
            boolean isFirstCondition = true;

            if (getPlayersRequest.getName() != null) {
                sql.append(isFirstCondition ? "WHERE " : "AND ");
                sql.append("name LIKE ? ");
                params.add("%" + getPlayersRequest.getName() + "%");
                isFirstCondition = false;
            }

            if (getPlayersRequest.getTitle() != null) {
                sql.append(isFirstCondition ? "WHERE " : "AND ");
                sql.append("title LIKE ? ");
                params.add("%" + getPlayersRequest.getTitle() + "%");
                isFirstCondition = false;
            }

            if (getPlayersRequest.getRace() != null) {
                sql.append(isFirstCondition ? "WHERE " : "AND ");
                sql.append("race = ? ");
                params.add(getPlayersRequest.getRace().name());
                isFirstCondition = false;
            }

            if (getPlayersRequest.getProfession() != null) {
                sql.append(isFirstCondition ? "WHERE " : "AND ");
                sql.append("profession = ? ");
                params.add(getPlayersRequest.getProfession().name());
                isFirstCondition = false;
            }

            if (getPlayersRequest.getAfter() != null) {
                sql.append(isFirstCondition ? "WHERE " : "AND ");
                sql.append("birthday >= ? ");
                params.add(new java.sql.Timestamp(getPlayersRequest.getAfter()));
                isFirstCondition = false;
            }

            if (getPlayersRequest.getBefore() != null) {
                sql.append(isFirstCondition ? "WHERE " : "AND ");
                sql.append("birthday <= ? ");
                params.add(new java.sql.Timestamp(getPlayersRequest.getBefore()));
                isFirstCondition = false;
            }

            if (getPlayersRequest.getBanned() != null) {
                sql.append(isFirstCondition ? "WHERE " : "AND ");
                sql.append("banned = ? ");
                params.add(getPlayersRequest.getBanned());
                isFirstCondition = false;
            }

            if (getPlayersRequest.getMinExperience() != null) {
                sql.append(isFirstCondition ? "WHERE " : "AND ");
                sql.append("experience >= ? ");
                params.add(getPlayersRequest.getMinExperience());
                isFirstCondition = false;
            }

            if (getPlayersRequest.getMaxExperience() != null) {
                sql.append(isFirstCondition ? "WHERE " : "AND ");
                sql.append("experience <= ? ");
                params.add(getPlayersRequest.getMaxExperience());
                isFirstCondition = false;
            }

            if (getPlayersRequest.getMinLevel() != null) {
                sql.append(isFirstCondition ? "WHERE " : "AND ");
                sql.append("level >= ? ");
                params.add(getPlayersRequest.getMinLevel());
                isFirstCondition = false;
            }

            if (getPlayersRequest.getMaxLevel() != null) {
                sql.append(isFirstCondition ? "WHERE " : "AND ");
                sql.append("level <= ? ");
                params.add(getPlayersRequest.getMaxLevel());
            }

            return jdbcTemplate.queryForObject(sql.toString(), Integer.class, params.toArray());
        }


    @Override
    public List<Player> getFilteredPlayers(GetPlayersRequest getPlayersRequest) {
        StringBuilder sql = new StringBuilder("SELECT * FROM player ");
        List<Object> params = new ArrayList<>();
        boolean isFirstCondition = true;

        if (getPlayersRequest.getName() != null) {
            sql.append(isFirstCondition ? "WHERE " : "AND ");
            sql.append("name LIKE ? ");
            params.add("%" + getPlayersRequest.getName() + "%");
            isFirstCondition = false;
        }

        if (getPlayersRequest.getTitle() != null) {
            sql.append(isFirstCondition ? "WHERE " : "AND ");
            sql.append("title LIKE ? ");
            params.add("%" + getPlayersRequest.getTitle() + "%");
            isFirstCondition = false;
        }

        if (getPlayersRequest.getRace() != null) {
            sql.append(isFirstCondition ? "WHERE " : "AND ");
            sql.append("race = ? ");
            params.add(getPlayersRequest.getRace().name());
            isFirstCondition = false;
        }

        if (getPlayersRequest.getProfession() != null) {
            sql.append(isFirstCondition ? "WHERE " : "AND ");
            sql.append("profession = ? ");
            params.add(getPlayersRequest.getProfession().name());
            isFirstCondition = false;
        }

        if (getPlayersRequest.getAfter() != null) {
            sql.append(isFirstCondition ? "WHERE " : "AND ");
            sql.append("birthday >= ? ");
            params.add(new java.sql.Date(getPlayersRequest.getAfter()));
            isFirstCondition = false;
        }

        if (getPlayersRequest.getBefore() != null) {
            sql.append(isFirstCondition ? "WHERE " : "AND ");
            sql.append("birthday <= ? ");
            params.add(new java.sql.Date(getPlayersRequest.getBefore()));
            isFirstCondition = false;
        }

        if (getPlayersRequest.getBanned() != null) {
            sql.append(isFirstCondition ? "WHERE " : "AND ");
            sql.append("banned = ? ");
            params.add(getPlayersRequest.getBanned());
            isFirstCondition = false;
        }

        if (getPlayersRequest.getMinExperience() != null) {
            sql.append(isFirstCondition ? "WHERE " : "AND ");
            sql.append("experience >= ? ");
            params.add(getPlayersRequest.getMinExperience());
            isFirstCondition = false;
        }

        if (getPlayersRequest.getMaxExperience() != null) {
            sql.append(isFirstCondition ? "WHERE " : "AND ");
            sql.append("experience <= ? ");
            params.add(getPlayersRequest.getMaxExperience());
            isFirstCondition = false;
        }

        if (getPlayersRequest.getMinLevel() != null) {
            sql.append(isFirstCondition ? "WHERE " : "AND ");
            sql.append("level >= ? ");
            params.add(getPlayersRequest.getMinLevel());
            isFirstCondition = false;
        }

        if (getPlayersRequest.getMaxLevel() != null) {
            sql.append(isFirstCondition ? "WHERE " : "AND ");
            sql.append("level <= ? ");
            params.add(getPlayersRequest.getMaxLevel());
        }

        if (getPlayersRequest.getOrder() != null) {
            sql.append("ORDER BY ").append(getPlayersRequest.getOrder()).append(" ");
        }

        if (getPlayersRequest.getPageNumber() != null && getPlayersRequest.getPageSize() != null) {
            int offset = getPlayersRequest.getPageNumber() * getPlayersRequest.getPageSize();
            sql.append("LIMIT ? OFFSET ? ");
            params.add(getPlayersRequest.getPageSize());
            params.add(offset);
        }

        return jdbcTemplate.query(sql.toString(), playerRowMapper, params.toArray());
    }
}

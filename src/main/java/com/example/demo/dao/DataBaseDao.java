package com.example.demo.dao;

import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Player;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class DataBaseDao implements PlayerDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsertPlayer;

    private final RowMapper<Player> playerRowMapper = (rs, rowNum) -> {
        Player player = new Player();
        player.setName(rs.getString("name"));
        player.setTitle(rs.getString("title"));
        player.setRace(Race.valueOf(rs.getString("race")));
        player.setProfession(Profession.valueOf(rs.getString("profession")));
        player.setBirthday(rs.getLong("birthday"));
        player.setBanned(rs.getBoolean("banned"));
        player.setExperience(rs.getInt("experience"));
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
        String sql = "INSERT INTO player (name, title, race, profession, birthday, banned, experience) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, player.getName(), player.getTitle(), player.getRace().name(), player.getProfession().name(), player.getBirthday(), player.isBanned(), player.getExperience());

        String selectSql = "SELECT * FROM player WHERE id = LAST_INSERT_ID()";
        return jdbcTemplate.queryForObject(selectSql, playerRowMapper);
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
                "race = ? " +
                "profession = ?, " +
                "birthday = ?, " +
                "banned = ?, " +
                "experience = ?, " +
                "level = ?, " +
                "untilnextlevel = ?, " +
                "WHERE id = ?";
        jdbcTemplate.update(sql, playerDto.getName(), playerDto.getTitle(), playerDto.getRace(), playerDto.getProfession(), playerDto.getBirthday(), playerDto.isBanned(), playerDto.getExperience(), playerDto.getLevel(), playerDto.getUntilNextLevel());
    }
}

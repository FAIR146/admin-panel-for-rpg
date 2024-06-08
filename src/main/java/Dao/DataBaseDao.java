package Dao;

import com.example.demo.entity.Player;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Objects;

@Repository
public class DataBaseDao implements PlayerDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsertPlayer;

    private final RowMapper<Player> playerRowMapper = (rs, rowNum) -> {
        Player player = new Player();
        player.setId(rs.getLong("id"));
        player.setName(rs.getString("name"));
        player.setTitle(rs.getString("title"));
        player.setRace(rs.getString("race"));
        player.setProfession(rs.getString("profession"));
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
    public long addPlayer (Player player) {
        HashMap<String, Object> hashMap = new HashMap<>(){{
            put("name", player.getName());
            put("title", player.getTitle());
            put("race", player.getRace());
            put("profession", player.getProfession());
            put("birthday", player.getBirthday());
            put("banned", player.isBanned());
            put("experience", player.getExperience());
            put("level", player.getLevel());
            put("unitlnextlevel", player.getUntilNextLevel());
        }};
        return simpleJdbcInsertPlayer.executeAndReturnKey(hashMap).longValue();
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
    public void updatePlayer (Player player) {
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
        jdbcTemplate.update(sql, player.getName(), player.getTitle(), player.getRace(), player.getProfession(), player.getBirthday(), player.isBanned(), player.getExperience(), player.getLevel(), player.getUntilNextLevel());
    }
}

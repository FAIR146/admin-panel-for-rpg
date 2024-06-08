package Dao;

import com.example.demo.entity.Player;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Objects;

@Repository
public class DataBaseDao implements PlayerDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsertPlayer;

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
}

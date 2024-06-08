package Service;

import Dao.PlayerDao;
import com.example.demo.entity.Player;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class MangerImpl implements Manager{
    private final PlayerDao playerDao;
    public MangerImpl (PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    public long addPlayer (String name, String title, Race race, Profession profession, LocalDate birthday, boolean banned, int experience, int level, int untilNextLevel) {
        Player player = new Player();
        player.setName(name);
        player.setTitle(title);
        player.setRace(race);
        player.setProfession(profession);
        player.setBirthday(birthday);
        player.setBanned(banned);
        player.setExperience(experience);
        player.setLevel(level);
        player.setUntilNextLevel(untilNextLevel);

        long id = playerDao.addPlayer(player);

        return id;
    }

    @Override
    public void removePlayerById(long id) {
        playerDao.removePlayerById(id);
    }

    @Override
    public Player getPlayerById(long id) {
        return playerDao.getPlayerById(id);
    }

    @Override
    public void updatePlayerById (long id, String name, String title, Race race, Profession profession, LocalDate birthday, boolean banned, int experience, int level, int untilNextLevel) {
        Player player = new Player();
        player.setId(id);
        player.setName(name);
        player.setTitle(title);
        player.setRace(race);
        player.setProfession(profession);
        player.setBirthday(birthday);
        player.setBanned(banned);
        player.setExperience(experience);
        player.setLevel(level);
        player.setUntilNextLevel(untilNextLevel);

        playerDao.updatePlayer(player);
    }

}

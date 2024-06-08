package Service;

import com.example.demo.entity.Player;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;

import java.time.LocalDate;

public interface Manager {
    long addPlayer (String name, String title, Race race, Profession profession, LocalDate birthday, boolean banned, int experience, int level, int untilNextLevel);
    void removePlayerById (long id);
    Player getPlayerById (long id);
    void updatePlayerById (long id, String name, String title, Race race, Profession profession, LocalDate birthday, boolean banned, int experience, int level, int untilNextLevel);
}

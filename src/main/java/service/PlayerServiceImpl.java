package service;

import dao.PlayerDao;
import com.example.demo.entity.Player;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import dto.PlayerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService {
    private final PlayerDao playerDao;
    public PlayerServiceImpl(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    public PlayerDto createPlayer (PlayerDto playerDto) {
        Player player = new Player();
        player.setName(playerDto.getName());
        player.setTitle(playerDto.getTitle());
        player.setRace(playerDto.getRace());
        player.setProfession(playerDto.getProfession());
        player.setBirthday(playerDto.getBirthday());
        player.setBanned(playerDto.isBanned());
        player.setExperience(playerDto.getExperience());


        Player player1 = playerDao.createPlayer(player);

        return null;
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
    public void updatePlayerById (PlayerDto playerDto) {
        Player player = new Player();
        player.setName(playerDto.getName());
        player.setTitle(playerDto.getTitle());
        player.setRace(playerDto.getRace());
        player.setProfession(playerDto.getProfession());
        player.setBirthday(playerDto.getBirthday());
        player.setBanned(playerDto.isBanned());
        player.setExperience(playerDto.getExperience());

        playerDao.updatePlayer(player);
    }

}

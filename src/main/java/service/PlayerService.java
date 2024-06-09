package service;

import com.example.demo.entity.Player;
import dto.PlayerDto;

public interface PlayerService {
    PlayerDto createPlayer (PlayerDto playerDto);
    void removePlayerById (long id);
    Player getPlayerById (long id);
    void updatePlayerById (PlayerDto playerDto);
}

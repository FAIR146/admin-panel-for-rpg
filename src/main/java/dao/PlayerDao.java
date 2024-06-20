package dao;

import com.example.demo.entity.Player;

public interface PlayerDao {
    Player createPlayer(Player player);
    void removePlayerById (long id);
    Player getPlayerById (long id);
    void updatePlayer (Player player);
}

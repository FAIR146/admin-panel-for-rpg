package Dao;

import com.example.demo.entity.Player;

public interface PlayerDao {
    long addPlayer (Player player);
    void removePlayerById (long id);
}

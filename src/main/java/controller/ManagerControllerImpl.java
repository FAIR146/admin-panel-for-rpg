package controller;

import Service.Manager;
import com.example.demo.entity.Player;
import controller.Put.PutPlayerRequest;
import controller.Response.GetPlayerResponse;
import controller.Response.PutPlayerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class ManagerControllerImpl implements ManagerController{
    private final Manager manager;
    public ManagerControllerImpl (Manager manager) {
        this.manager = manager;
    }

    @Override
    public PutPlayerResponse putPlayer(PutPlayerRequest putPlayerRequest) {
        PutPlayerResponse putPlayerResponse = new PutPlayerResponse();
        long id = manager.addPlayer(putPlayerRequest.getName(), putPlayerRequest.getTitle(), putPlayerRequest.getRace(), putPlayerRequest.getProfession(), putPlayerRequest.getBirthday(), putPlayerRequest.isBanned(), putPlayerRequest.getExperience(), putPlayerRequest.getLevel(), putPlayerRequest.getUntilNextLevel());
        putPlayerResponse.setId(id);
        return putPlayerResponse;
    }
    @Override
    public void deletePlayerById (long id) {
        manager.removePlayerById(id);
    }
    @Override
    public ResponseEntity<GetPlayerResponse> getPlayerById (@RequestParam long id) {
        Player player = manager.getPlayerById(id);
        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        GetPlayerResponse getPlayerResponse = new GetPlayerResponse();
        getPlayerResponse.setName(player.getName());
        getPlayerResponse.setTitle(player.getTitle());
        getPlayerResponse.setRace(player.getRace());
        getPlayerResponse.setProfession(player.getProfession());
        getPlayerResponse.setBirthday(player.getBirthday());
        getPlayerResponse.setBanned(player.isBanned());
        getPlayerResponse.setExperience(player.getExperience());
        getPlayerResponse.setLevel(player.getLevel());
        getPlayerResponse.setUntilNextLevel(player.getUntilNextLevel());
        return ResponseEntity.ok(getPlayerResponse);
    }
}

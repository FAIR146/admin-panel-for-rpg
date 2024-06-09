package controller;

import dto.PlayerDto;
import service.PlayerService;
import com.example.demo.entity.Player;
import controller.put.CreatePlayerRequest;
import controller.response.GetPlayerResponse;
import controller.response.CreatePlayerResponse;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;


public class PlayerControllerImpl implements PlayerController {

    private final PlayerService playerService;


    @Override
    public CreatePlayerResponse createPlayer(CreatePlayerRequest createPlayerRequest) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setBanned(false);

        PlayerDto createdPlayerDto = playerService.createPlayer(playerDto);

        CreatePlayerResponse createPlayerResponse = new CreatePlayerResponse();

        return createPlayerResponse;
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

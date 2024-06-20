package controller;

import dto.PlayerDto;
import org.springframework.web.bind.annotation.RestController;
import service.PlayerService;
import com.example.demo.entity.Player;
import controller.put.CreatePlayerRequest;
import controller.response.GetPlayerResponse;
import controller.response.CreatePlayerResponse;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;


@Data
@RestController
public class PlayerControllerImpl implements PlayerController {

    private final PlayerService playerService;

@Override
public CreatePlayerResponse createPlayer(CreatePlayerRequest createPlayerRequest) {
    PlayerDto playerDto = new PlayerDto();
    playerDto.setName(createPlayerRequest.getName());
    playerDto.setTitle(createPlayerRequest.getTitle());
    playerDto.setRace(createPlayerRequest.getRace());
    playerDto.setProfession(createPlayerRequest.getProfession());
    playerDto.setBirthday(createPlayerRequest.getBirthday());
    playerDto.setExperience(createPlayerRequest.getExperience());
    playerDto.setBanned(false);

    PlayerDto createdPlayerDto = playerService.createPlayer(playerDto);

    CreatePlayerResponse createPlayerResponse = new CreatePlayerResponse();
    createPlayerResponse.setName(createdPlayerDto.getName());
    createPlayerResponse.setTitle(createdPlayerDto.getTitle());
    createPlayerResponse.setRace(createdPlayerDto.getRace());
    createPlayerResponse.setProfession(createdPlayerDto.getProfession());
    createPlayerResponse.setBirthday(createdPlayerDto.getBirthday());
    createPlayerResponse.setBanned(createdPlayerDto.isBanned());
    createPlayerResponse.setExperience(createdPlayerDto.getExperience());

    return createPlayerResponse;
}

    @Override
    public void deletePlayerById (long id) {
        playerService.removePlayerById(id);
    }
    @Override
    public ResponseEntity<GetPlayerResponse> getPlayerById (@RequestParam long id) {
        Player player = playerService.getPlayerById(id);
        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        GetPlayerResponse getPlayerResponse = new GetPlayerResponse();


        return ResponseEntity.ok(getPlayerResponse);
    }
}

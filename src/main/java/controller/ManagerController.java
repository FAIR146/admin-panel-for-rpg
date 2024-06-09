package controller;

import controller.Put.PutPlayerRequest;
import controller.Response.GetPlayerResponse;
import controller.Response.PutPlayerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

public interface ManagerController {
    @PostMapping("/rest/players")
    PutPlayerResponse putPlayer (@Valid @RequestBody PutPlayerRequest putPlayerRequest);
    @DeleteMapping("/rest/players")
    void deletePlayerById (@Valid @RequestParam long id);
    @GetMapping("/rest/players")
    ResponseEntity<GetPlayerResponse> getPlayerById (@Valid @RequestParam long id);
}

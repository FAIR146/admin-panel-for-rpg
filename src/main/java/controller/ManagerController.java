package controller;

import controller.Put.PutPlayerRequest;
import controller.Response.PutPlayerResponse;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ManagerController {
    @PutMapping("/putPlayer")
    PutPlayerResponse putPlayer (@Valid @RequestBody PutPlayerRequest putPlayerRequest);
}

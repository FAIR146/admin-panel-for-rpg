package controller;

import Service.Manager;
import com.example.demo.entity.Player;
import controller.Put.PutPlayerRequest;
import controller.Response.PutPlayerResponse;

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
}

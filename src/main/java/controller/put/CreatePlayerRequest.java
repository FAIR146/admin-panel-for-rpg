package controller.put;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CreatePlayerRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String title;
    @NotNull
    private Race race;
    @NotNull
    private Profession profession;
    @NotBlank
    private Long birthday;
    @NotBlank
    private Boolean banned = false;
    @NotBlank
    private Integer experience;

}

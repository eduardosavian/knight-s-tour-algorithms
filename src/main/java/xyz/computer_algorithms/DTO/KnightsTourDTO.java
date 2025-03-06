package xyz.computer_algorithms.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KnightsTourDTO {
    @Min(4)
    @NotBlank(message = "Must more than 4")
    private int boardSize;

    @Min(0)
    @NotBlank(message = "Must more than 0")
    private int startX;

    @Min(0)
    @NotBlank(message = "Must more than 0")
    private int startY;

    @NotBlank
    @NotBlank(message = "Backtrack Type cannot be blank")
    private String backtrackType;
}
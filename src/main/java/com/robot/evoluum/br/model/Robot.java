package com.robot.evoluum.br.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Robot {

    private int positionX;
    private int positionY;
    private String coordinate;

    @Override
    public String toString() {
        return "(" + Math.abs(positionX) + "," + Math.abs(positionY) + "," + coordinate + ")";
    }
}

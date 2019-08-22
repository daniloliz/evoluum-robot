package com.robot.evoluum.br.controller.endpoint;

import com.robot.evoluum.br.model.Area;
import com.robot.evoluum.br.model.Robot;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@Api(value = "Move Robot")
public class MoveRobotEndPoint {

    private Area initialArea;

    @ApiOperation(value = "Post robot movement")
    @PostMapping(value = "/movement", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String postMove(@RequestParam String movement) {
        Robot initialPosition = new Robot(0, 0, "N");
        String[] movements = movement.split("");
        return processMovement(initialPosition, movements);
    }

    // Process robot movement
    private String processMovement(Robot robot, String[] movements) {
        initialArea = new Area(5, 5);
        String[] filterValidMovements = Arrays.stream(movements)
                .filter(this::isValidMove).toArray(String[]::new);
        if (filterValidMovements.length != movements.length) return "400 Bad Request";
        Arrays.stream(filterValidMovements)
                .forEach(validMov -> execMov(robot, validMov));
        return isValidPosition(robot) ? robot.toString() :
                "400 Bad Request";
    }

    // Verify is valid the position
    private boolean isValidPosition(Robot robot) {
        return initialArea.getHeight() >= Math.abs(robot.getPositionY()) &&
                initialArea.getWidth() >= Math.abs(robot.getPositionX());
    }

    // Verify input command valid
    private boolean isValidMove(String move) {
        return move.equals("M") || move.equals("R") || move.equals("L");
    }

    // Decision the movement
    private void execMov(Robot robot, String mov) {
        if (mov.equals("M") && robot.getCoordinate().equals("N")) {
            moveN(robot, robot.getPositionY());
        } else {
            if (mov.equals("M") && robot.getCoordinate().equals("S")) {
                moveS(robot, robot.getPositionY());
            } else {
                if (mov.equals("M") && robot.getCoordinate().equals("L")) {
                    moveL(robot, robot.getPositionX());
                } else {
                    if (mov.equals("M") && robot.getCoordinate().equals("W")) {
                        moveW(robot, robot.getPositionX());
                    } else {
                        if (mov.equals("L")) {
                            movementCoordinateLeft(robot, robot.getCoordinate());
                        } else {
                            movementCoordinateRight(robot, robot.getCoordinate());
                        }
                    }
                }
            }
        }

    }

    // Exec North movement
    private void moveN(Robot robot, int position) {
        robot.setPositionY(position + 1);
        robot.setCoordinate("N");
    }

    // Exec South movement
    private void moveS(Robot robot, int position) {
        robot.setPositionY(position - 1);
        robot.setCoordinate("S");
    }

    // Exec West movement
    private void moveW(Robot robot, int position) {
        robot.setPositionX(position - 1);
        robot.setCoordinate("W");
    }

    // Exec Lest movement
    private void moveL(Robot robot, int position) {
        robot.setPositionX(position + 1);
        robot.setCoordinate("L");
    }

    // Rotate robot left
    private void movementCoordinateLeft(Robot robot, String oldCoord) {
        if (oldCoord.equals("N")) {
            robot.setCoordinate("W");
        } else {
            if (oldCoord.equals("W")) {
                robot.setCoordinate("S");
            } else {
                if (oldCoord.equals("S")) {
                    robot.setCoordinate("L");
                } else {
                    robot.setCoordinate("N");
                }
            }
        }
    }

    // Rotate robot right
    private void movementCoordinateRight(Robot robot, String oldCoord) {
        if (oldCoord.equals("N")) {
            robot.setCoordinate("L");
        } else {
            if (oldCoord.equals("L")) {
                robot.setCoordinate("S");
            } else {
                if (oldCoord.equals("S")) {
                    robot.setCoordinate("W");
                } else {
                    robot.setCoordinate("N");
                }
            }
        }
    }
}

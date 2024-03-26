package ch.epfl.cs107.icmon.gamelogic.message;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonDoor;

public class PassDoorMessage extends GamePlayMessage {
    private ICMonDoor door;

    public PassDoorMessage(ICMonDoor door) {
        this.door = door;
    }


    public void process(ICMon.ICMonGameState gameState) {
        gameState.switchArea(door.getDestinationArea(), door.getDestinationCoordinates());
    }

    public void process() {

    }
}

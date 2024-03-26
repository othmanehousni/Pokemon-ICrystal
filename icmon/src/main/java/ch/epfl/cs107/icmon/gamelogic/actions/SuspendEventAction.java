package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;

public class SuspendEventAction implements Action {

    private ICMon.ICMonGameState gameState;
    private ICMonFight fight;

    public SuspendEventAction (ICMon.ICMonGameState gameState, ICMonFight fight) {
        this.gameState = gameState;
        this.fight = fight;
    }

    public void perform() {
        gameState.SuspendFight(fight);
    }
}

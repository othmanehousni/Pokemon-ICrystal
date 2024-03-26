package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;

public class ResumeEventAction implements Action {
    private ICMon.ICMonGameState gameState;

    public ResumeEventAction(ICMon.ICMonGameState gameState) {
        this.gameState = gameState;
    }

    public void perform() {
        gameState.ResumeGame();
    }
}

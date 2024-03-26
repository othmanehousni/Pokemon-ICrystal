package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.actor.ICMonActor;

public class LeaveAreaAction implements Action{

    private ICMonActor actor;

    public LeaveAreaAction(ICMonActor actor) {
        this.actor = actor;
    }
    public void perform () {
        actor.leaveArea();
    }
}

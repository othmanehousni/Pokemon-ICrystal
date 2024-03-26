package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.Actor;

public class UnregisterInAreaAction implements Action {

    private Area area;
    private Actor actor;
    public UnregisterInAreaAction (Area area, Actor actor) {
        this.area = area;
        this.actor = actor;
    }
    @Override
    public void perform() {
        area.unregisterActor(actor);
    }
}

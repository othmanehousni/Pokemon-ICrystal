package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class RegisterEventAction implements Action {
    private ICMonEvent event;
    private ICMon.ICMonEventManager manager;

    public RegisterEventAction(ICMonEvent event, ICMon.ICMonEventManager manager) {
        this.event = event;
        this.manager = manager;
    }
    @Override
    public void perform() {
        manager.registerEvent(event);
    }


}
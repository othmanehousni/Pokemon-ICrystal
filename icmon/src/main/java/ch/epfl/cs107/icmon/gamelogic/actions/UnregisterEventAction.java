package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class UnregisterEventAction implements Action {
    private ICMonEvent event;
    private ICMon.ICMonEventManager manager;

    public UnregisterEventAction(ICMonEvent event, ICMon.ICMonEventManager manager) {
        this.event = event;
        this.manager = manager;
    }
    @Override
    public void perform() {
        manager.unregisterEvent(event);
    }
}


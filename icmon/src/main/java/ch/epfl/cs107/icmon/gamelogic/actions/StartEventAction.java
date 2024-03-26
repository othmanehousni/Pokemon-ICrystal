package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class StartEventAction extends ICMonEvent implements Action {
    private ICMonEvent event;
    private ICMon.ICMonEventManager manager;
    public StartEventAction(ICMonEvent event, ICMon.ICMonEventManager manager) {
        this.event = event;
        this.manager = manager;
    }
    public void perform() {
        event.start();
    }

}

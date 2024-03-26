package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.actions.Action;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class CompleteEventAction extends ICMonEvent implements Action {
    private ICMonEvent event;
    private ICMon.ICMonEventManager manager;

    public CompleteEventAction (ICMonEvent event, ICMon.ICMonEventManager manager) {
        this.event = event;
        this.manager = manager;

    }

    @Override
    public void perform() {
        event.complete();
    }
}

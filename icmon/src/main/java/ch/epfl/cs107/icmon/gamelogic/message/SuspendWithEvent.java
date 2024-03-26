package ch.epfl.cs107.icmon.gamelogic.message;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonFightEvent;
import ch.epfl.cs107.icmon.gamelogic.message.GamePlayMessage;
import ch.epfl.cs107.icmon.gamelogic.actions.ResumeEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.SuspendEventAction;

public class SuspendWithEvent extends GamePlayMessage {

    private PokemonFightEvent event;

    public SuspendWithEvent(PokemonFightEvent event) {
        this.event = event;

    }

    public PokemonFightEvent getEvent () {
        return event;

    }
    public void process(ICMon.ICMonGameState gameState) {
        event.onStart(new SuspendEventAction(gameState,event.getFight()));
        event.onComplete(new ResumeEventAction(gameState));
        event.start();

    }


}




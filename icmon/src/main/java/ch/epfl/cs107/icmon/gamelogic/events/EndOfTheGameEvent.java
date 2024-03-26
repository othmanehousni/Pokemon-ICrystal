package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.play.engine.actor.Dialog;

public class EndOfTheGameEvent extends ICMonEvent {
    private ICMonPlayer player;
    private ICMon.ICMonEventManager manager;

    public EndOfTheGameEvent(ICMon.ICMonEventManager manager, ICMonPlayer player) {
        this.manager = manager;
        this.player = player;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (player.wantsViewInteraction()) {
            this.complete();
        }
    }

    @Override
    public void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {
        System.out.println("lalalla");
        player.openDialog(new Dialog("end_of_game_event_interaction_with_icshopassistant"));
    }

}

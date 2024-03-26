package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICMonBall;
import ch.epfl.cs107.icmon.actor.items.ICMonItem;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.gamelogic.actions.RegisterinAreaAction;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.Updatable;
import ch.epfl.cs107.play.engine.actor.Dialog;

import javax.swing.*;

public class CollectItemEvent extends ICMonEvent {
    private ICMonItem item;
    private Area mainArea;

    public CollectItemEvent(Area mainArea, ICMonItem item, ICMonPlayer player) {
        this.item = item;
        this.mainArea = mainArea;
        this.player = player;

    }



    public void update(float deltaTime) {
        if (item.isCollected()) {
            this.complete();
        }

    }

    public void interactWith(ICShopAssistant assistant, boolean isCellInteraction){
        player.openDialog(new Dialog("collect_item_event_interaction_with_icshopassistant"));
    }

    public void interactWith(ICMonBall ball, boolean IsCellInteraction) {
        ball.collect();
        player.openDialog(new Dialog("collectedBall"));
    }
}


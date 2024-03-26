package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.gamelogic.actions.UnregisterInAreaAction;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;
import ch.epfl.cs107.play.engine.actor.Dialog;

public class FirstInteractionWithGarryEvent extends ICMonEvent {
    private Dialog dialog = new Dialog("first_interaction_with_prof_oak");
    private ICMonPlayer player;
    private ICMonArea area;
    private ICMon.ICMonEventManager manager;

    public FirstInteractionWithGarryEvent(ICMonPlayer player, ICMonArea area, ICMon.ICMonEventManager manager) {
        this.area = area;
        this.player = player;
        this.manager = manager;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }


    @Override
    public void start() {
        super.start();
        if (dialog.isCompleted()) {
            this.complete();
        }
    }

    @Override
    public void interactWith(Garry garry, boolean isCellInteraction) {
        if (player.wantsViewInteraction()) {
            player.fight(garry.getGarryPokemonList().get(0));
            this.complete();
            area.unregisterActor(garry);
            area.unregisterActor(garry.getGarryPokemonList().get(0));
        }
    }
}


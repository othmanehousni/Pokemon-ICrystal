package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.actor.pokemon.pokemonList.Latios;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.area.maps.Arena;
import ch.epfl.cs107.icmon.gamelogic.actions.RegisterinAreaAction;
import ch.epfl.cs107.play.engine.actor.Dialog;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class FirstInteractionWithProfOakEvent extends ICMonEvent {
    private Dialog dialog = new Dialog("first_interaction_with_prof_oak");
    private ICMonPlayer player;
    private ICMonArea area;

    private String dialogText;

    private ICMon.ICMonEventManager manager;

    public FirstInteractionWithProfOakEvent(ICMonPlayer player, ICMonArea area, ICMon.ICMonEventManager manager) {
        this.area = area;
        this.player = player;
        this.manager = manager;
        this.dialog = new Dialog("first_interaction_with_prof_oak");
        this.dialogText = "first_interacton_with_prof_oak";
    }


    @Override
    public void update(float deltaTime) {
        if (dialog.isCompleted()) {
            this.complete();
        }
    }

    @Override
    public void start() {
        super.start();
        if(dialog.isCompleted()){
            this.complete();

        }
    }

    @Override
    public void interactWith(ProfOak profOak, boolean isCellInteraction) {
        if(player.wantsViewInteraction() && !dialog.isCompleted()) {
            player.openDialog(dialog);
            player.getPokemonList().add(new Latios(area));
        }
    }

    @Override
    public void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {
        if(player.wantsViewInteraction()) {
            player.openDialog(new Dialog("first_interaction_with_oak_event_icshopassistant"));
        }

    }

    @Override
    public void interactWith(Pokemon pokemon, boolean isCellInteraction) {
        if(player.wantsViewInteraction()) {
            player.openDialog(new Dialog("noPokemon"));
        }
    }
}
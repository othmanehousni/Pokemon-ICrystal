package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.actions.AfterPokemonSelectionFightAction;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;
import ch.epfl.cs107.icmon.gamelogic.fights.PokemonSelectionMenu;

import java.util.List;

/** Class is a try in creating the Pokemon Selection Menu, but again in vain */

public class PokemonSelectionEvent extends ICMonEvent {
    private PokemonSelectionMenu selectionMenu;

    private PokemonSelectionEvent event;
    private ICMon.ICMonEventManager manager;
    private List<Pokemon> pokemons;
    private Pokemon opponentPokemon;
    public PokemonSelectionEvent(Pokemon opponentPokemon, PokemonSelectionMenu selectionMenu, ICMon.ICMonGameState gameState, ICMon.ICMonEventManager manager) {
        this.selectionMenu = selectionMenu;
        this.manager = manager;
        onComplete(new AfterPokemonSelectionFightAction(opponentPokemon, selectionMenu, gameState,manager));

    }
    @Override
    public void update(float deltaTime) {
        selectionMenu.update(deltaTime);
        if (selectionMenu.choice() != null) {
            complete();
        }
    }
    @Override
    public void complete() {
        super.complete();
        Pokemon selectedPokemon = selectionMenu.choice();
        manager.registerEvent(new PokemonFightEvent(new ICMonFight(selectedPokemon,opponentPokemon),selectedPokemon));
    }
}

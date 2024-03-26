package ch.epfl.cs107.icmon.gamelogic.actions;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonFightEvent;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;
import ch.epfl.cs107.icmon.gamelogic.fights.PokemonSelectionMenu;
import ch.epfl.cs107.icmon.gamelogic.message.SuspendWithEvent;

/**
 * AfterPokemonSelectionFightAction is an action to be performed after selecting a Pokemon,
 * typically leading to a fight event.
 */
public class AfterPokemonSelectionFightAction implements Action {
    private Pokemon opponentPokemon;
    private PokemonSelectionMenu menu;
    private ICMon.ICMonGameState gameState;
    private ICMon.ICMonEventManager manager;

    /**
     * Constructor for the AfterPokemonSelectionFightAction.
     *
     * @param opponentPokemon (Pokemon): The opponent Pokemon in the upcoming fight.
     * @param menu (PokemonSelectionMenu): The menu used for Pokemon selection.
     * @param gameState (ICMon.ICMonGameState): The current state of the game.
     * @param manager (ICMon.ICMonEventManager): The manager handling game events.
     */
    public AfterPokemonSelectionFightAction(Pokemon opponentPokemon, PokemonSelectionMenu menu, ICMon.ICMonGameState gameState, ICMon.ICMonEventManager manager) {
        this.menu = menu;
        this.manager = manager;
        this.gameState = gameState;
        this.opponentPokemon = opponentPokemon;
    }

    @Override
    public void perform() {
        PokemonFightEvent fight = new PokemonFightEvent(new ICMonFight(menu.choice(), opponentPokemon), opponentPokemon);
        manager.registerEvent(fight);
        SuspendWithEvent suspendMessage = new SuspendWithEvent(fight);
        gameState.send(suspendMessage);
        fight.start();

    }
}

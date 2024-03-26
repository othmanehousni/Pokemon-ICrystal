package ch.epfl.cs107.icmon.actor.pokemon.fightActions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;

/**
 * RunAway is an implementation of the ICMonFightAction interface, representing a runaway action in a fight.
 * The class was used in order to verify the "type" of interaction with the Pokémon : Attack or RunAway.
 * It wasn't used with the doAction and perform.
 */
public class RunAway implements ICMonFightAction {
    @Override
    public String name() {
        return "RunAway";
    }

    /**
     * Attempts to perform the runaway action for the target Pokémon.
     * @param target (Pokémon): The Pokémon attempting to run away.
     * @return (boolean): The result of the action, false as a placeholder.
     */
    @Override
    public boolean doAction(Pokemon target) {
        return false;
    }

    @Override
    public void perform(Pokemon pokemon, Pokemon opponent) {

    }
}

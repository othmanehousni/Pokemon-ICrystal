package ch.epfl.cs107.icmon.actor.pokemon.fightActions;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;

/**
 * Attack is an implementation of the ICMonFightAction interface, representing an attack action in a fight.
 * The class was used in order to verify the "type" of interaction with the Pokémon : Attack or RunAway.
 * It wasn't used with the doAction and perform.
 */
public class Attack implements ICMonFightAction {
    @Override
    public String name() {
        return "Attack";
    }

    /**
     * Performs the attack action on the target Pokémon.
     * @param target (Pokémon): The Pokémon being targeted by the attack.
     * @return (boolean): The result of the action, false as a placeholder.
     */
    @Override
    public boolean doAction(Pokemon target) {
        return true;
    }


    @Override
    public void perform(Pokemon pokemon, Pokemon opponent) {

    }
}

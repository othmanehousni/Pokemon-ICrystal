package ch.epfl.cs107.icmon.gamelogic.fights;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;

/**
 * ???
 *
 * @author Hamza REMMAL (hamza.remmal@epfl.ch)
 */
public interface ICMonFightAction {

    String name();


    boolean doAction(Pokemon target);

    void perform(Pokemon pokemon, Pokemon opponent);

}

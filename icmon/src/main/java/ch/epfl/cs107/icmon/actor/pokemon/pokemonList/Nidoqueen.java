package ch.epfl.cs107.icmon.actor.pokemon.pokemonList;

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.actor.pokemon.fightActions.Attack;
import ch.epfl.cs107.icmon.actor.pokemon.fightActions.RunAway;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFightAction;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import java.util.ArrayList;
import java.util.List;

/**
 * Nidoqueen is a specific type of Pokemon in the ICMon game world.
 */
public class Nidoqueen extends Pokemon {

    private static final int maxHp = 10;
    private static final int damage = 1;
    private List<ICMonFightAction> fightingActions = new ArrayList<>();

    /**
     * Constructor for Nidoqueen, setting up its initial state and available actions.
     * @param area (Area): The area where Nidoqueen is initially located.
     */
    public Nidoqueen(Area area) {
        super(area, Orientation.DOWN, new DiscreteCoordinates(5, 5),"nidoqueen", maxHp, damage);
        fightingActions.add(new Attack());
        fightingActions.add(new RunAway());

    }

    /**
     * Returns the list of fight actions available to Nidoqueen.
     * @return (List<ICMonFightAction>): List of fighting actions.
     */
    public List<ICMonFightAction> getFightingActions() {
        return fightingActions; }

    /**
     * Method to handle interactions with Nidoqueen.
     * @param v (AreaInteractionVisitor): Visitor pattern for handling interactions.
     * @param isCellInteraction (boolean): Indicates if it's a cell level interaction.
     */
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);

    }
}
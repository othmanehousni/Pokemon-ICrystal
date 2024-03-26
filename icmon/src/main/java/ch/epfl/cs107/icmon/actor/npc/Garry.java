package ch.epfl.cs107.icmon.actor.npc;

import ch.epfl.cs107.icmon.actor.pokemon.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.actor.pokemon.pokemonList.Nidoqueen;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.math.RegionOfInterest;

import java.util.ArrayList;
import java.util.List;

/**
 * Garry is a specific type of NPCActor that is capable of fighting in the ICMon game.
 * It implements the ICMonFightableActor interface for interaction capabilities.
 */

public class Garry extends NPCActor implements ICMonFightableActor {
    private String spriteName;
    private RPGSprite professor;
    private List<Pokemon> GarryPokemonList = new ArrayList<>();

    /**
     * Constructor for Garry, an NPC with fighting capabilities.
     * @param area (Area): The area where Garry is located.
     * @param orientation (Orientation): The orientation of Garry in the area.
     * @param position (DiscreteCoordinates): The position of Garry in the area.
     * @param spriteName (String): The name of the sprite representing Garry.
     */

    public Garry(Area area, Orientation orientation, DiscreteCoordinates position, String spriteName) {
        super(area, orientation, position, spriteName);
        this.spriteName = spriteName;
        this.GarryPokemonList.add(new Nidoqueen(area));
        professor = new RPGSprite("actors/garry", 1,1.3125f, this, new RegionOfInterest(0,0,16,21));
    }

    /**
     * Returns a list of Pokémon that Garry can use for fighting.
     * @return (List<Pokemon>): List of Garry's Pokémon.
     */
    public List<Pokemon> getGarryPokemonList() {
        return GarryPokemonList;
    }

    /**
     * Method to accept and handle interactions with Garry.
     * @param v (AreaInteractionVisitor): Visitor interface for handling interactions.
     * @param isCellInteraction (boolean): Indicates if it's a cell level interaction.
     */

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }
}


package ch.epfl.cs107.icmon.actor.npc;

import ch.epfl.cs107.icmon.actor.pokemon.ICMonFightableActor;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.math.RegionOfInterest;

/**
 * Infirmary represents a specialized NPCActor within the ICMon game that provides healing services.
 * Implements ICMonFightableActor for potential interaction capabilities.
 */

public class Infirmary extends NPCActor implements ICMonFightableActor {
    private String spriteName;
    private RPGSprite infirmary;
    /**
     * Constructor for Infirmary, a special type of NPCActor in the ICMon game.
     * @param area (Area): The area where the infirmary is located.
     * @param orientation (Orientation): The initial orientation of the infirmary in the area.
     * @param position (DiscreteCoordinates): The initial position of the infirmary in the area.
     * @param spriteName (String): The name of the sprite representing the infirmary.
     */
    public Infirmary(Area area, Orientation orientation, DiscreteCoordinates position, String spriteName) {
        super(area, orientation, position, spriteName);
        this.spriteName = spriteName;
        infirmary = new RPGSprite("actors/infirmary", 1,1.3125f, this, new RegionOfInterest(0,0,16,21));
    }
    /**
     * Method to accept and handle interactions with the infirmary.
     * @param v (AreaInteractionVisitor): Visitor interface for handling interactions.
     * @param isCellInteraction (boolean): Indicates if it's a cell level interaction.
     */
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }

}


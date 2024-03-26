package ch.epfl.cs107.icmon.actor.npc;

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.math.RegionOfInterest;

/**
 * ICShopAssistant represents a non-player character in the game that assists and gives instructions in the map.
 */
public class ICShopAssistant extends NPCActor {
    public String spriteName;
    public RPGSprite assistant;

    /**
     * Constructor for ICShopAssistant, a non-player character that assists and gives instructions in the map.
     * @param area (Area): The area where the shop assistant is located.
     * @param orientation (Orientation): The initial orientation of the shop assistant in the area.
     * @param position (DiscreteCoordinates): The initial position of the shop assistant in the area.
     * @param spriteName (String): The name of the sprite representing the shop assistant.
     */
    public ICShopAssistant(Area area, Orientation orientation, DiscreteCoordinates position, String spriteName) {
        super(area, orientation, position, spriteName);
        this.spriteName = spriteName;
        assistant = new RPGSprite("actors/assistant", 1,1.3125f, this, new RegionOfInterest(0,0,16,21));
    }

    /**
     * Method to accept and handle interactions with the shop assistant.
     * @param v (AreaInteractionVisitor): Visitor interface for handling interactions.
     * @param isCellInteraction (boolean): Indicates if it's a cell level interaction.
     */

    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }

}

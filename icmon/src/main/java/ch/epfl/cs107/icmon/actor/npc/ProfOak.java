package ch.epfl.cs107.icmon.actor.npc;

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.math.RegionOfInterest;

/**
 * ProfOak is a specific type of NPCActor in the ICMon game world, representing a knowledgeable professor character.
 */
public class ProfOak extends NPCActor {
    public String spriteName;
    public RPGSprite professor;

    /**
     * Constructor for ProfOak, setting up its basic state and graphical representation.
     * @param area (Area): The area where Professor Oak is located.
     * @param orientation (Orientation): The initial orientation of Professor Oak in the area.
     * @param position (DiscreteCoordinates): The initial position of Professor Oak in the area.
     * @param spriteName (String): The name of the sprite representing Professor Oak.
     */
    public ProfOak(Area area, Orientation orientation, DiscreteCoordinates position, String spriteName) {
        super(area, orientation, position, spriteName);
        this.spriteName = spriteName;
        professor = new RPGSprite("actors/prof-oak", 1,1.3125f, this, new RegionOfInterest(0,0,16,21));
    }

    /**
     * Method to accept and handle interactions with Professor Oak.
     * @param v (AreaInteractionVisitor): Visitor interface for handling interactions.
     * @param isCellInteraction (boolean): Indicates if it's a cell level interaction.
     */
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }
}

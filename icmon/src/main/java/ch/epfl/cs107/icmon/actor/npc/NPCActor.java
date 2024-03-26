package ch.epfl.cs107.icmon.actor.npc;

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

/**
 * NPCActor is an abstract class representing non-player characters in the ICMon game.
 * It extends ICMonActor and implements the Interactable interface for interaction capabilities.
 */
public abstract class NPCActor extends ICMonActor implements Interactable {

    private RPGSprite npcSprite;

    /**
     * Constructor for NPCActor, setting up its basic state and graphical representation.
     * @param area (Area): The area where the NPC is located.
     * @param orientation (Orientation): The initial orientation of the NPC in the area.
     * @param position (DiscreteCoordinates): The initial position of the NPC in the area.
     * @param spriteName (String): The name of the sprite representing the NPC.
     */
    public NPCActor(Area area, Orientation orientation, DiscreteCoordinates position, String spriteName) {
        super(area, orientation, position);
        this.npcSprite = new RPGSprite(spriteName, 1, 1.3125f, this, new RegionOfInterest(0, 0, 16, 21));
    }

    // Indicates that the NPC occupies space on the grid.
    @Override
    public boolean takeCellSpace() {
        return true;
    }

    // Indicates that the NPC is not interactable at the cell level.
    @Override
    public boolean isCellInteractable() {
        return false;
    }

    // Indicates that the NPC is interactable at the view level.
    @Override
    public boolean isViewInteractable() {
        return true;
    }

    /**
     * Returns the current cells occupied by the NPC.
     * @return (List<DiscreteCoordinates>): List containing the main cell coordinates of this NPC.
     */
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    /**
     * Draws the NPC on the canvas.
     * @param canvas (Canvas): the canvas to draw onto. Not null.
     */
    @Override
    public void draw(Canvas canvas) {
        npcSprite.draw(canvas);

    }

}

package ch.epfl.cs107.icmon.actor.items;

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import java.util.Collections;
import java.util.List;

/**
 * ICMonBall extends ICMonItem and represents a collectible item in the game that can interact with other actors.
 */

public class ICMonBall extends ICMonItem implements Interactable {

    private Area area;

    /**
     * Default constructor of ICMonBall.
     * @param area (Area): Owner area where the ball is found.
     * @param orientation (Orientation): As an item, unchanged orientation of the ball in the Area.
     * @param position (DiscreteCoordinates): Unchanged position of the item in the Area.
     * @param spriteName (String): Name of the sprite to be used for this item.
     */

    public ICMonBall(Area area, Orientation orientation, DiscreteCoordinates position, String spriteName) {
        super(area, orientation, position, spriteName);
    }
    /**
     * Returns the current cells occupied by the ICMonBall.
     * @return (List<DiscreteCoordinates>): List containing the main cell coordinates of this ICMonBall.
     */
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    /**
     * Method to handle the interaction of this ICMonBall with other actors.
     * @param v (AreaInteractionVisitor): Visitor pattern for handling interactions. Casts to ICMonInteractionVisitor.
     * @param isCellInteraction (boolean): Indicates if it's a cell level interaction.
     */
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }

}

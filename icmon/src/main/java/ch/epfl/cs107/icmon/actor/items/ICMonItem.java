package ch.epfl.cs107.icmon.actor.items;

import ch.epfl.cs107.play.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.List;

/**
 * Abstract class ICMonItem representing an item in the ICMon game.
 * It extends CollectableAreaEntity indicating it can be collected within an area.
 */
public abstract class ICMonItem extends CollectableAreaEntity {
    private RPGSprite sprite;
    /**
     * Default constructor of ICMonItem.
     * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the item in the Area. Not null
     * @param position (DiscreteCoordinates): Initial position of the item in the Area. Not null
     * @param spriteName (String): Name of the sprite to be used for this item. Not null
     */
    public ICMonItem(Area area, Orientation orientation, DiscreteCoordinates position, String spriteName) {
        super(area, orientation, position);
        this.sprite = new RPGSprite(spriteName, 1f, 1f, this);
    }

    /**
     * Indicates that the ICMonItem takes up cell space.
     * @return (boolean): always true, as it occupies space on the grid.
     */

    public boolean takeCellSpace() {
        return true;
    }

    /**
     * Indicates that the ICMonItem isn't cell interactable.
     * @return (boolean): always false, it cannot interact with other cell entities.
     */

    public boolean isCellInteractable() {
        return false;
    }

    /**
     * Indicates that the ICMonItem is view interactable.
     * @return (boolean): always true, it does interact with the view. Every item has to be interacted with.
     */

    public boolean isViewInteractable() {
        return true;
    }

    /**
     * Draws the ICMonItem on the canvas.
     * @param canvas (Canvas): the canvas to draw onto. Not null.
     */

    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }

}


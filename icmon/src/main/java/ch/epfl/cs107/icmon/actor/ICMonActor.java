package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

/**
 * ICMonActor is an abstract class representing actors in the ICMon game world that are movable and/or interactable.
 * It extends MovableAreaEntity and implements Interactable interface.
 */
public abstract class ICMonActor extends MovableAreaEntity implements Interactable {

    /**
     * Constructor for ICMonActor, setting up its basic state in the game area.
     * @param area (Area): The area where the actor is initially located.
     * @param orientation (Orientation): The initial orientation of the actor in the area.
     * @param position (DiscreteCoordinates): The initial position of the actor in the area.
     */

    public ICMonActor(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
    }

    /**
     * Indicates that the ICMonActor occupies space on the grid.
     * @return (boolean): always true, as it typically occupies space.
     */
    public boolean takeCellSpace() {
        return true;
    }

    /**
     * Indicates that the ICMonActor is view interactable.
     * @return (boolean): always true, it does interact with the view.
     */
    public boolean isViewInteractable() {
        return true;
    }

    /**
     * Indicates that the ICMonActor is not cell interactable.
     * @return (boolean): always false, as it is typically not interactable at the cell level.
     */
    public boolean isCellInteractable() { return false; }

    /**
     * Removes the actor from its current area.
     */
    public void leaveArea() {
        getOwnerArea().unregisterActor(this);
    }

    /**
     * Adds the actor to a new area at the given position.
     * @param area (Area): The new area to enter.
     * @param position (DiscreteCoordinates): The position in the new area.
     */
    public void enterArea(Area area, DiscreteCoordinates position) {
        area.registerActor(this);
        area.setViewCandidate(this);
        setOwnerArea(area);
        setCurrentPosition(position.toVector());
        resetMotion();
    }


}

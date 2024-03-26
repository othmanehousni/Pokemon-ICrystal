package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ICMonDoor is a class representing doors in the ICMon game world that connect different areas.
 */
public class ICMonDoor extends AreaEntity {

    private String destinationArea;
    private DiscreteCoordinates destinationCoordinates;
    private List<DiscreteCoordinates> allCells = new ArrayList<DiscreteCoordinates>();

    /**
     * Constructor for ICMonDoor, setting up its basic state in the game area.
     * @param destinationArea (String): The name of the destination area.
     * @param destinationPosition (DiscreteCoordinates): The position in the destination area.
     * @param area (Area): The current area where the door is located.
     * @param orientation (Orientation): The orientation of the door.
     * @param position (DiscreteCoordinates...): The positions occupied by the door.
     */
    public ICMonDoor(String destinationArea,DiscreteCoordinates destinationPosition, Area area, Orientation orientation, DiscreteCoordinates ... position) {
        super(area, orientation, position[0]);
        this.destinationArea = destinationArea;
        this.destinationCoordinates = destinationPosition;
        Collections.addAll(this.allCells, position);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return allCells;
    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor)v).interactWith(this,isCellInteraction);
    }

    @Override
    public void draw(Canvas canvas) {

    }

    /**
     * Getter : Returns the name of the destination area.
     * @return (String): The name of the destination area.
     */
    public String getDestinationArea() {
        return destinationArea;
    }

    /**
     * Getter : Returns the coordinates in the destination area.
     * @return (DiscreteCoordinates): The coordinates in the destination area.
     */
    public DiscreteCoordinates getDestinationCoordinates() {
        return destinationCoordinates;
    }
}

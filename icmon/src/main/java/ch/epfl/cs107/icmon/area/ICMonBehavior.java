package ch.epfl.cs107.icmon.area;

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.AreaBehavior;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.Entity;
import ch.epfl.cs107.play.window.Window;

/**
 * ICMonBehavior is a class representing the behavior of the areas in the ICMon game world, particularly concerning
 * how entities can interact with the area's cells.
 */
public class ICMonBehavior extends AreaBehavior {
    /**
     * Default ICMon2Behavior Constructor
     *
     * @param window (Window), not null
     * @param name   (String): Name of the Behavior, not null
     */
    public ICMonBehavior(Window window, String name) {
        super(window, name);
        int height = getHeight();
        int width = getWidth();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ICMonCellType color = ICMonCellType.toType(getRGB(height - 1 - y, x));
                setCell(x, y, new ICMonCell(x, y, color));
            }
        }
    }

    public boolean isCellInteractable() {
        return false;
    }

    public boolean isViewInteractable() {
        return false;
    }

    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {

    }


    public enum AllowedWalkingType {
        NONE,
        SURF,
        FEET,
        ALL
    }


    public enum ICMonCellType {
        //https://stackoverflow.com/questions/25761438/understanding-bufferedimage-getrgb-output-values
        NULL(0, AllowedWalkingType.NONE),
        WALL(-16777216, AllowedWalkingType.NONE),
        BUILDING(-8750470, AllowedWalkingType.NONE),
        INTERACT(-256, AllowedWalkingType.NONE),
        DOOR(-195580, AllowedWalkingType.ALL),
        INDOOR_WALKABLE(-1, AllowedWalkingType.FEET),
        OUTDOOR_WALKABLE(-14112955, AllowedWalkingType.FEET),
        WATER(-16776961, AllowedWalkingType.SURF),
        GRASS(-16743680, AllowedWalkingType.FEET);

        final int type;
        final AllowedWalkingType allowedWalkingType;

        ICMonCellType(int type, AllowedWalkingType allowedWalkingType) {
            this.type = type;
            this.allowedWalkingType = allowedWalkingType;
        }


        public static ICMonCellType toType(int type) {
            for (ICMonCellType ict : ICMonCellType.values()) {
                if (ict.type == type)
                    return ict;
            }
            System.out.println(type);
            return NULL;
        }
    }

    public class ICMonCell extends Cell {
        /// Type of the cell following the enum
        private ICMonCellType type;

        /**
         * Constructor for an ICMonCell.
         *
         * @param x    (int): x-coordinate of the cell
         * @param y    (int): y-coordinate of the cell
         * @param type (ICMonCellType): the type of cell
         */
        public ICMonCell(int x, int y, ICMonCellType type) {
            super(x, y);
            this.type = type;
        }

        public ICMonBehavior.ICMonCellType getType(ICMonBehavior.ICMonCell cell) {
            return this.type;
        }

        @Override
        protected boolean canLeave(Interactable entity) {
            return true;
        }

        @Override
        protected boolean canEnter(Interactable entity) {
            for (Interactable otherEntity : entities) {
                if (otherEntity.takeCellSpace()) {
                    return false;
                }
            } return type.allowedWalkingType != AllowedWalkingType.NONE;

        }

        public boolean canSurf() {
            return (type.allowedWalkingType == AllowedWalkingType.SURF);
        }

        @Override
        public boolean takeCellSpace() {
            return (type.allowedWalkingType != AllowedWalkingType.NONE);
        }

        @Override
        public boolean isCellInteractable() {
            return false;
        }


        @Override
        public boolean isViewInteractable() {
            return false;
        }

        @Override
        public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
            ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
        }
    }
}



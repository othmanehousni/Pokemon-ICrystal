package ch.epfl.cs107.icmon.area;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.play.window.Window;

/**
 * ICMonArea is an abstract class representing an area in the ICMon game world, such as a town, a route, or a building.
 * It extends the Area class, providing additional game-specific functionalities and behaviors.
 */
public abstract class ICMonArea extends Area {

    protected abstract void createArea();

    public abstract DiscreteCoordinates getPlayerSpawnPosition();

    @Override
    public final float getCameraScaleFactor() {
        return ICMon.CAMERA_SCALE_FACTOR;
    }


    /**
     * Initializes the area, setting up the behavior, creating the area's content and starting the game logic.
     * @param window (Window): The graphical window of the game.
     * @param fileSystem (FileSystem): The file system of the game, used for data reading and writing.
     * @return (boolean): True if the area begins successfully, false otherwise.
     */
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            setBehavior(new ICMonBehavior(window, getTitle()));
            createArea();
            return true;
        }
        return false;
    }

}

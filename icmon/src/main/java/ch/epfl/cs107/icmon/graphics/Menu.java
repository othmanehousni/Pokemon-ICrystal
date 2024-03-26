package ch.epfl.cs107.icmon.graphics;

import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.security.Key;

public class Menu extends PauseMenu {

    private ICMonPauseMenuGraphics graphics;

    private boolean isRunning = true;

    public Menu() {
        graphics = new ICMonPauseMenuGraphics(17f);
    }
    @Override
    protected void drawMenu(Canvas c) {
        graphics.draw(c);
    }

    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Keyboard keyboard = getKeyboard();
        if (keyboard.get((Keyboard.A)).isDown()){
            System.exit(0);
        }
        if(keyboard.get(Keyboard.Q).isDown()) {
            if (getOwner() != null) {
                getOwner().requestResume();
            }
        }
    }
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        return super.begin(window, fileSystem);
    }

    @Override
    public void end() {
        this.isRunning = false;
    }
}

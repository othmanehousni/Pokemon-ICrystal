package ch.epfl.cs107.icmon.graphics;

import ch.epfl.cs107.play.engine.actor.Graphics;
import ch.epfl.cs107.play.engine.actor.ImageGraphics;
import ch.epfl.cs107.play.engine.actor.TextGraphics;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.awt.*;
import java.security.Key;

public final class ICMonPauseMenuGraphics implements Graphics {

    private final ImageGraphics background;
    private final TextGraphics resumeText;
    private final TextGraphics settingsText;
    private final TextGraphics quitText;
    public ICMonPauseMenuGraphics(float scaleFactor) {

        background = new ImageGraphics("images/backgrounds/menu.png", 14, 11);
        background.setRelativeTransform(Transform.I.translated(0,-1));

        resumeText = new TextGraphics("Resume", 1f, Color.BLACK);
        //resumeText.setParent(this);
        resumeText.setRelativeTransform(Transform.I.translated(1f, 8));

        settingsText = new TextGraphics("Settings", 1f, Color.BLACK);
        //settingsText.setParent(this);
        settingsText.setRelativeTransform(Transform.I.translated(1f, 6.5f));

        quitText = new TextGraphics("Quit", 1f, Color.BLACK);
        //quitText.setParent(this);
        quitText.setRelativeTransform(Transform.I.translated(1.5f, 5.2f));
    }

    @Override
    public void draw(Canvas canvas) {
        background.draw(canvas);
        // Draw the menu options
        resumeText.draw(canvas);
        settingsText.draw(canvas);
        quitText.draw(canvas);
    }





}

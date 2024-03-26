package ch.epfl.cs107.icmon.graphics;

import ch.epfl.cs107.play.engine.actor.OrientedAnimation;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.math.Positionable;

import ch.epfl.cs107.play.engine.actor.Animation;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.engine.actor.RPGSprite;


/** Small size modifications to the OrientedAnimation class in order to implement a bike*/
public class BikingAnimation extends OrientedAnimation {

    private final Animation animationsUP;
    private final Animation animationsDOWN;
    private final Animation animationsLEFT;
    private final Animation animationsRIGHT;

    private Animation current;

    public BikingAnimation(String name, int duration, Orientation orientation, Positionable parent) {
        super(name, duration, orientation, parent);
        var spritesUP = new Sprite[4];
        var spritesDOWN = new Sprite[4];
        var spritesLEFT = new Sprite[4];
        var spritesRIGHT = new Sprite[4];

        for(int i = 0; i<4; i++){
            spritesUP[i] = new RPGSprite(name, 1, 1.3125f, parent, new RegionOfInterest(i*48, 144, 48, 48));
            spritesDOWN[i] = new RPGSprite(name, 1, 1.3125f, parent, new RegionOfInterest(i*48, 0, 48, 48));
            spritesLEFT[i] = new RPGSprite(name, 1, 1.3125f, parent, new RegionOfInterest(i*48, 48, 48, 48));
            spritesRIGHT[i] = new RPGSprite(name, 1, 1.3125f, parent, new RegionOfInterest(i*48, 96, 48, 48));
        }

        animationsUP = new Animation(duration, spritesUP);
        animationsDOWN = new Animation(duration, spritesDOWN);
        animationsLEFT = new Animation(duration, spritesLEFT);
        animationsRIGHT = new Animation(duration, spritesRIGHT);

        // HR : Kick-start - first configuration
        orientate(orientation);
    }

    @Override
    public void update(float deltaTime) {
        current.update(deltaTime);
    }
    @Override
    public void draw(Canvas canvas) {
        current.draw(canvas);
    }

    /**
     * ???
     * @param orientation ???
     */
    public void orientate(Orientation orientation){
        current = switch (orientation){
            case UP -> animationsUP;
            case DOWN -> animationsDOWN;
            case LEFT -> animationsLEFT;
            case RIGHT -> animationsRIGHT;
        };
    }

    /**
     * ???
     */
    public void reset(){
        animationsUP.reset();
        animationsDOWN.reset();
        animationsLEFT.reset();
        animationsRIGHT.reset();
    }

}
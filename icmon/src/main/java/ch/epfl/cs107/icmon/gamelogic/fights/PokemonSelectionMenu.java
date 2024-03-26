package ch.epfl.cs107.icmon.gamelogic.fights;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.actor.pokemon.pokemonList.Bulbizarre;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.area.maps.House;
import ch.epfl.cs107.icmon.graphics.ICMonFightActionSelectionGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightArenaGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightTextGraphics;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.engine.Updatable;
import ch.epfl.cs107.play.engine.actor.Graphics;
import ch.epfl.cs107.play.engine.actor.GraphicsEntity;
import ch.epfl.cs107.play.engine.actor.ImageGraphics;
import ch.epfl.cs107.play.engine.actor.TextGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.io.ResourcePath;
import ch.epfl.cs107.play.math.TextAlign;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Objects.nonNull;

/** Class is a try in creating the Pokemon Selection Menu, but again in vain */

public class PokemonSelectionMenu extends PauseMenu implements Updatable {
    private Pokemon playerPokemon;
    private static final float FONT_SIZE = .6f;
    private Keyboard keyboard;
    private int currentChoice;
    private Pokemon choice;
    private final Graphics header;
    private List<Pokemon> pokemons = new ArrayList<>();
    private final GraphicsEntity[] selectors = new GraphicsEntity[3];
    private float scaleFactor;
    private ICMonArea town = new House();

    public PokemonSelectionMenu(float scaleFactor, List<Pokemon> pokemonDeck) {
        this.scaleFactor = scaleFactor;
        pokemons.add(new Bulbizarre(town));
        header = new GraphicsEntity(new Vector(scaleFactor / 2f, scaleFactor / 3 - 1f), new TextGraphics("please pokenin", FONT_SIZE, Color.WHITE, null, 0.0f, true, false, Vector.ZERO, TextAlign.Horizontal.CENTER, TextAlign.Vertical.MIDDLE,  1f, 1003));
        currentChoice = 0;

    }

    @Override
    public void update(float deltaTime) {
        var spriteName = "pokemon/" + pokemons.get(currentChoice).getProperties().name();
        var scale = CAMERA_SCALE_FACTOR;
        var image = new ImageGraphics(ResourcePath.getSprite(spriteName), scale / 2, scale / 2);
        image.setAlpha(.6f);
        if (keyboard.get(Keyboard.LEFT).isPressed()) {
            currentChoice = max(0, currentChoice - 1);
        } else if (keyboard.get(Keyboard.RIGHT).isPressed())
            currentChoice = min(currentChoice + 1, pokemons.size() - 1);
        else if (keyboard.get(Keyboard.ENTER).isPressed())
            choice = pokemons.get(currentChoice);
        if (currentChoice == 0) {
            selectors[0] = null;
        } else {
            selectors[0] = new GraphicsEntity(new Vector(scale / 3 - 3f, scale / 3 - 2f), image);
            selectors[1] = new GraphicsEntity(new Vector(scale * 2 / 3 - 3f, scale / 3 - 2f), image);
            if (currentChoice == pokemons.size() - 1) {
                selectors[2] = null;
            } else {
                selectors[2] = new GraphicsEntity(new Vector(scale / 3 + 3f, scale / 2 - 4f), image);
            }
        }
        super.update(deltaTime);

    }
    @Override
    protected void drawMenu(Canvas c) {
        header.draw(c);
        for (var selector : selectors)
            if(nonNull(selector))
                selector.draw(c);
    }
    public Pokemon choice(){
        return choice;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        return super.begin(window, fileSystem);
    }

    public String getTitle() {
        return "Selection Menu";
    }



}

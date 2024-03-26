package ch.epfl.cs107.icmon.gamelogic.fights;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.npc.Garry;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.actor.pokemon.fightActions.Attack;
import ch.epfl.cs107.icmon.gamelogic.actions.AfterPokemonSelectionFightAction;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonSelectionEvent;
import ch.epfl.cs107.icmon.graphics.ICMonFightActionSelectionGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightArenaGraphics;
import ch.epfl.cs107.icmon.graphics.ICMonFightTextGraphics;
import ch.epfl.cs107.play.engine.PauseMenu;
import ch.epfl.cs107.play.engine.Updatable;
import ch.epfl.cs107.play.engine.actor.SoundAcoustics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.io.ResourcePath;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.util.ArrayList;
import java.util.List;

/**
 * ICMonFight is a class representing a fight between two Pokemon in the ICMon game world.
 * It extends PauseMenu and implements Updatable for updating fight logic.
 */
public class ICMonFight extends PauseMenu implements Updatable {

    private Pokemon playerPokemon;
    private Pokemon opponentPokemon;
    private ICMonFightArenaGraphics arena;
    private float counter;
    private final static float BACKGROUND_VOLUME = 1f;
    private FightState currentState;
    private ICMonFightActionSelectionGraphics actionSelectionGraphics;
    private boolean isRunning = true;
    List<Pokemon> pokemonDeck;
    private ICMonFightAction choosenAction;

    public boolean getIsRunning() {
        return isRunning;
    }

    /**
     * Enum for the different states of the fight.
     */
    public enum FightState {
        INTRODUCTION,
        ACTION_SELECTION,
        ACTION_EXECUTION,
        OPPOSANT_ACTION,
        CONCLUSION
    }

    /**
     * Constructor for ICMonFight.
     *
     * @param playerPokemon (Pokemon): The player's Pokemon.
     * @param opponentPokemon (Pokemon): The opponent's Pokemon.
     */
    public ICMonFight(Pokemon playerPokemon, Pokemon opponentPokemon) {
        this.currentState = FightState.INTRODUCTION;
        this.playerPokemon = playerPokemon;
        this.opponentPokemon = opponentPokemon;
        this.counter = 5.0f;
        this.pokemonDeck = new ArrayList<>();
        this.arena = new ICMonFightArenaGraphics(CAMERA_SCALE_FACTOR, opponentPokemon.getProperties(), playerPokemon.getProperties());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Keyboard keyboard = getKeyboard();
        switch (currentState) {
            case INTRODUCTION -> {
                arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, "Welcome to the fight"));
                if (keyboard.get(Keyboard.SPACE).isPressed()) {
                    currentState = FightState.ACTION_SELECTION;
                    actionSelectionGraphics = new ICMonFightActionSelectionGraphics(CAMERA_SCALE_FACTOR, keyboard, playerPokemon.getFightingActions());
                    arena.setInteractionGraphics(actionSelectionGraphics);
                }

            }
            case ACTION_SELECTION -> {
                this.actionSelectionGraphics.update(deltaTime);
                choosenAction = actionSelectionGraphics.choice();
                if (actionSelectionGraphics.choice() != null) {
                    currentState = FightState.ACTION_EXECUTION;
                    actionSelectionGraphics = new ICMonFightActionSelectionGraphics(CAMERA_SCALE_FACTOR, keyboard, playerPokemon.getFightingActions());
                    arena.setInteractionGraphics(actionSelectionGraphics);
                }
            }

            case ACTION_EXECUTION -> {
                if (choosenAction instanceof Attack) {
                    opponentPokemon.receivedDamage(playerPokemon.getDamage());
                    if (opponentPokemon.isDead()) {
                        arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, "The player has won the fight"));
                        currentState = FightState.CONCLUSION;
                    } else {
                        currentState = FightState.OPPOSANT_ACTION;
                    }
                } else {
                    this.actionSelectionGraphics.update(deltaTime);
                    currentState = FightState.CONCLUSION;
                    arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, "The player decided not to continue the fight"));
                }
            }

            case OPPOSANT_ACTION -> {
                this.actionSelectionGraphics.update(deltaTime);
                if (choosenAction instanceof Attack) {
                    playerPokemon.receivedDamage(opponentPokemon.getDamage());
                    if (playerPokemon.isDead()) {
                        arena.setInteractionGraphics(new ICMonFightTextGraphics(CAMERA_SCALE_FACTOR, "The opponent has won the fight"));
                        currentState = FightState.CONCLUSION;
                    } else {
                        currentState = FightState.ACTION_SELECTION;
                    }
                }
            }
            case CONCLUSION -> {
                if (keyboard.get(Keyboard.SPACE).isPressed()) {
                    end();


                }
            }
        }
    }

    public void end() {
        this.isRunning = false;
    }

    @Override
    protected void drawMenu(Canvas c) {
        arena.draw(c);
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        SoundAcoustics sound = new SoundAcoustics(ResourcePath.getSound("pokeFight"), BACKGROUND_VOLUME, false, false, false, true);
        sound.shouldBeStarted();
        sound.bip(window);
        return super.begin(window, fileSystem);
    }


    public String getTitle() {
        return "ICMon Fight";
    }

}
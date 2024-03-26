package ch.epfl.cs107.icmon.actor;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.items.ICMonBall;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.Infirmary;
import ch.epfl.cs107.icmon.actor.pokemon.ICMonFightableActor;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.icmon.gamelogic.actions.AfterPokemonSelectionFightAction;
import ch.epfl.cs107.icmon.gamelogic.actions.LeaveAreaAction;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonFightEvent;
import ch.epfl.cs107.icmon.gamelogic.events.PokemonSelectionEvent;
import ch.epfl.cs107.icmon.gamelogic.fights.PokemonSelectionMenu;
import ch.epfl.cs107.icmon.gamelogic.message.PassDoorMessage;
import ch.epfl.cs107.icmon.gamelogic.message.SuspendWithEvent;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;
import ch.epfl.cs107.icmon.graphics.BikingAnimation;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.actor.Interactor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.Dialog;
import ch.epfl.cs107.play.engine.actor.OrientedAnimation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ch.epfl.cs107.icmon.ICMon.CAMERA_SCALE_FACTOR;

/**
 * ICMonPlayer is a class representing the player in the ICMon game world.
 * It extends ICMonActor and implements Interactor for interaction with other entities.
 */

public class ICMonPlayer extends ICMonActor implements Interactor {
    private Dialog dialog;
    private boolean isInDialog = false;
    private boolean wantsPause = false;

    private static int ANIMATION_DURATION = 6;
    public OrientedAnimation orientedAnimation;
    private String spriteName;
    private ICMon.ICMonGameState gameState;
    private String newStringName;
    private ICMon.ICMonEventManager manager;
    private List<Pokemon> pokemonList = new ArrayList<>();

    /**
     * Constructor for ICMonPlayer, setting up its initial state in the game area.
     *
     * @param owner       (Area): The area where the player is initially located.
     * @param orientation (Orientation): The initial orientation of the player in the area.
     * @param coordinates (DiscreteCoordinates): The initial position of the player in the area.
     * @param spriteName  (String): The name of the sprite representing the player.
     * @param gameState   (ICMon.ICMonGameState): The current state of the game.
     * @param manager     (ICMon.ICMonEventManager): The manager handling game events.
     */

    public ICMonPlayer(Area owner, Orientation orientation, DiscreteCoordinates coordinates, String spriteName, ICMon.ICMonGameState gameState, ICMon.ICMonEventManager manager) {
        super(owner, orientation, coordinates);
        this.manager = manager;
        this.spriteName = spriteName;
        this.gameState = gameState;
        orientedAnimation = new OrientedAnimation(spriteName, ANIMATION_DURATION / 2, getOrientation(), this);
        resetMotion();
    }


    @Override
    public void draw(Canvas canvas) {
        orientedAnimation.draw(canvas);
        if (isInDialog && dialog != null) {
            dialog.draw(canvas);
        }
    }

    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates().jump(getOrientation().toVector()));
    }

    @Override
    public boolean wantsCellInteraction() {
        return true;
    }

    @Override
    public boolean wantsViewInteraction() {
        return (!isInDialog) && (getOwnerArea().getKeyboard().get(Keyboard.L).isPressed());
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    public List<Pokemon> getPlayerPokemons() {
        return new ArrayList<>(pokemonList);
    }

    public void centerCamera() {
        getOwnerArea().setViewCandidate(this);
    }


    /**
     * Initiates a fight between the player and an opponent.
     *
     * @param opponent (ICMonFightableActor): The opponent in the fight.
     */
    public void fight(ICMonFightableActor opponent) {
        PokemonFightEvent fightEvent = new PokemonFightEvent(new ICMonFight(pokemonList.get(0), (Pokemon) opponent), (Pokemon) opponent);
        manager.registerEvent(fightEvent);
        SuspendWithEvent message = new SuspendWithEvent(fightEvent);
        gameState.send(message);
        fightEvent.onComplete(new LeaveAreaAction((ICMonActor) opponent));
    }

    /**
     * Another version of initiating a fight, with more complex logic or alternate processing.
     * Created in order to be used in the PokemonSelectionMenu, but in vain.
     *
     * @param opponent (Pokemon): The opponent Pokemon in the fight.
     */
    public void fightBIS(Pokemon opponent) {
        PokemonSelectionMenu menu = new PokemonSelectionMenu(CAMERA_SCALE_FACTOR, getPlayerPokemons());
        PokemonSelectionEvent selectionEvent = new PokemonSelectionEvent(opponent, menu, gameState, manager);
        selectionEvent.onComplete(new AfterPokemonSelectionFightAction(opponent, menu, gameState, manager));
        manager.registerEvent(selectionEvent);
        selectionEvent.start();
    }

    private final ICMonPlayerInteractionHandler handler = new ICMonPlayerInteractionHandler();

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this, isCellInteraction);
    }

    /**
     * Handles the interaction with another interactable entity.
     *
     * @param other             (Interactable): The entity to interact with.
     * @param isCellInteraction (boolean): Indicates if the interaction is at the cell level.
     */

    @Override
    public void interactWith(Interactable other, boolean isCellInteraction) {
        other.acceptInteraction(handler, isCellInteraction);
        gameState.acceptInteraction(other, isCellInteraction);
    }



    /**
     * Private class for handling player's interactions.
     */
    private class ICMonPlayerInteractionHandler implements ICMonInteractionVisitor {

        @Override
        public void interactWith(ICMonBehavior.ICMonCell other, boolean isCellInteraction) {
            Keyboard keyboard = getOwnerArea().getKeyboard();
            ICMonInteractionVisitor.super.interactWith(other, isCellInteractable());
            if (other.canSurf()) {
                newStringName = "actors/player_water";
            } else {
                if (keyboard.get(Keyboard.V).isDown()) {
                    ANIMATION_DURATION = 2;
                    newStringName = "actors/velo3";
                } else {
                    newStringName = "actors/player";
                    ANIMATION_DURATION = 6;
                }
            }
            updateAnimation(newStringName);
        }

        @Override
        public void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {
            ICMonInteractionVisitor.super.interactWith(assistant, isCellInteractable());
        }

        @Override
        public void interactWith(ICMonBall ball, boolean isCellInteraction) {
            ICMonInteractionVisitor.super.interactWith(ball, isCellInteractable());
        }

        @Override
        public void interactWith(Pokemon pokemon, boolean isCellInteraction) {
            if (wantsViewInteraction())  {
                if (pokemonList.isEmpty()) {
                openDialog(new Dialog("noPokemon")); }
                if (pokemonList.size() > 1) {
                    pokemonList.remove(0);

                } else { fight(pokemon); }
            }

        }

        @Override
        public void interactWith(ICMonDoor door, boolean isCellInteraction) {
            if (isCellInteraction) {
                PassDoorMessage message = new PassDoorMessage(door);
                gameState.send(message);
            }
        }

        public void interactWith(Infirmary infirmary, boolean isCellInteraction) {
            if(wantsViewInteraction()) {
                openDialog(new Dialog("healInfirmary"));
                for(Pokemon pokemon : pokemonList) {
                    pokemon.getProperties().gainHp(5);
                }

            }
            }
    }



    /**
     * Updates the player's animation based on changes in state or environment.
     * @param newSpriteName (String): The name of the new sprite for the player.
     */
    public void updateAnimation(String newSpriteName) {
        if ((!spriteName.equals(newSpriteName))) {
            spriteName = newSpriteName;
            orientedAnimation = new OrientedAnimation(spriteName, ANIMATION_DURATION/2, getOrientation(), this);

        }
    }


    @Override
    public void update(float deltaTime) {
        System.out.println(pokemonList);
        Keyboard keyboard = getOwnerArea().getKeyboard();
        if (isInDialog) {
            if (keyboard.get(Keyboard.SPACE).isPressed()) {
                dialog.update(deltaTime);
                if (dialog.isCompleted()) {
                    isInDialog = false;
                }
            }
        } else {
            moveIfPressed(Orientation.LEFT, keyboard.get(Keyboard.LEFT));
            moveIfPressed(Orientation.UP, keyboard.get(Keyboard.UP));
            moveIfPressed(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
            moveIfPressed(Orientation.DOWN, keyboard.get(Keyboard.DOWN));
            super.update(deltaTime);
            if (isDisplacementOccurs()) {
                orientedAnimation.update(deltaTime);
            } else {
                resetMotion();
            }
        }
    }

    private void moveIfPressed(Orientation orientation, Button b) {
        if (b.isDown()) {
            if (!isDisplacementOccurs()) {
                orientate(orientation);
                orientedAnimation = new OrientedAnimation(spriteName, ANIMATION_DURATION/2, getOrientation(), this);
                move(ANIMATION_DURATION);
            }
        }
    }

    public void openDialog(Dialog dialog) {
        this.dialog = dialog;
        isInDialog = true;
    }

    /**
     * Retrieves the current dialog associated with the player.
     * @return (Dialog): The current dialog.
     */
    public Dialog getDialog() {
        return this.dialog;

    }

    public boolean isInDialog() {
        return isInDialog;
    }


}
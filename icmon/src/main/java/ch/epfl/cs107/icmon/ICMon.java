package ch.epfl.cs107.icmon;

import ch.epfl.cs107.icmon.actor.items.ICMonBall;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.ProfOak;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.area.maps.*;
import ch.epfl.cs107.icmon.gamelogic.message.GamePlayMessage;
import ch.epfl.cs107.icmon.gamelogic.actions.*;
import ch.epfl.cs107.icmon.gamelogic.events.*;
import ch.epfl.cs107.icmon.gamelogic.fights.ICMonFight;
import ch.epfl.cs107.icmon.gamelogic.message.SuspendWithEvent;
import ch.epfl.cs107.icmon.graphics.ICMonPauseMenuGraphics;
import ch.epfl.cs107.icmon.graphics.Menu;
import ch.epfl.cs107.play.areagame.AreaGame;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

import java.util.ArrayList;
import java.util.List;

public class ICMon extends AreaGame {
    public ArrayList<ICMonEvent> activeEvents = new ArrayList<ICMonEvent>();
    public ArrayList<ICMonEvent> newEvents = new ArrayList<ICMonEvent>();
    private ArrayList<ICMonEvent> completedEvents = new ArrayList<ICMonEvent>();
    private GamePlayMessage messageInMailbox;
    public final static float CAMERA_SCALE_FACTOR = 13.f;
    private ICMonEvent collectBallEvent;
    private ICMonEvent endOfGameEvent;

    private final String[] areas = {"town", "lab","arena","shop","House", "viridianCity", "viridianLab", "viridianArena", "viridianShop", "viridianHouse"};
    private ICMonPlayer player;
    private int areaIndex;
    private Town town = new Town();
    private House house = new House();
    private Menu menu = new Menu();

    private ICMonArea mainArea;
    private ICMonArea townArea;
    private ICMonEventManager manager = new ICMonEventManager();
    private ICMon.ICMonGameState gameState = new ICMonGameState();

    /**
     * Creates and adds all the areas to the game.
     */
    private void createAreas() {
        addArea(new Lab());
        addArea(new Arena());
        addArea(new House());
        addArea(new Shop());
        addArea(town);
        addArea(new ViridianCity());
        addArea(new ViridianArena());
    }


    @Override
    public String getTitle() {
        return "PokeICrystal";
    }

    /**
     * Initializes the game, setting up areas, player, and events.
     * @param window (Window): The graphical window of the game.
     * @param fileSystem (FileSystem): The file system of the game, used for data reading and writing.
     * @return (boolean): True if the game begins successfully, false otherwise.
     */

    // Initialize game, areas, player, and start everthing needed
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            activeEvents.clear();
            createAreas();
            areaIndex = 0;
            this.townArea = (ICMonArea) setCurrentArea("town", false);
            this.mainArea = (ICMonArea) setCurrentArea("house", false);
            player = new ICMonPlayer(mainArea, Orientation.DOWN, new DiscreteCoordinates(5, 5), "actors/player", gameState, manager);
            player.enterArea(mainArea, mainArea.getPlayerSpawnPosition());
            player.centerCamera();
            events();
            return true;
        }
        return false;
    }


    /**
     * Updates the state of the game, called every frame.
     * @param deltaTime (float): The time elapsed since the last update.
     */
    // Handle event updates, keyboard inputs, and state transitions
    @Override
    public void update(float deltaTime) {
        Keyboard keyboard = getWindow().getKeyboard();
        super.update(deltaTime);
        if (messageInMailbox instanceof SuspendWithEvent) {
            if (((SuspendWithEvent) messageInMailbox).getEvent().isStarted()) {
                for (ICMonEvent event : activeEvents) {
                    event.suspend();
                }
            }
            if (((SuspendWithEvent) messageInMailbox).getEvent().isCompleted()) {
                for (ICMonEvent event : activeEvents) {
                    event.resume();
                }
            }
        }
            if (messageInMailbox != null) {
                messageInMailbox.process(gameState);
                messageInMailbox = null;
            }
        activeEvents.removeIf(ICMonEvent::isCompleted);
        activeEvents.addAll(newEvents);
        newEvents.clear();
        for (ICMonEvent event : activeEvents) {
            event.update(deltaTime);
        }
        if (wantResetGame()) {
            resetGame();
        }
        if(keyboard.get(Keyboard.M).isPressed()) {
            SetPause();
            }
    }

    /**
     * Initiates various events and chains them together.
     */
    public void events() {
        ICMonEvent introductionEvent = new IntroductionEvent(player);
        ICMonEvent interactionOak = new FirstInteractionWithProfOakEvent(player,mainArea, manager);
        interactionOak.onComplete(new RegisterinAreaAction(townArea, town.getBall()));
        ICMonEvent collectBallEvent = new CollectItemEvent(mainArea, town.getBall(), player);
        collectBallEvent.onComplete(new UnregisterInAreaAction(townArea, town.getBall()));
        EndOfTheGameEvent endOfGameEvent = new EndOfTheGameEvent(manager, player);
        ICMonEvent interactionGarry = new FirstInteractionWithGarryEvent(player, mainArea, manager);
        interactionGarry.onComplete(new UnregisterInAreaAction(mainArea, house.getGarry()));
        ICMonEvent winningGarry = new AfterWinningGarryEvent(mainArea, player);
        ICMonChainedEvent chainedEvents = new ICMonChainedEvent(manager,introductionEvent,interactionOak,collectBallEvent, interactionGarry,endOfGameEvent, winningGarry);
        manager.registerEvent(chainedEvents);
        chainedEvents.start();
    }

    private boolean wantResetGame() {
        Keyboard keyboard = getWindow().getKeyboard();
        Button key = keyboard.get(Keyboard.R);
        return key.isPressed();
    }

    private void resetGame() {
        begin(getWindow(), getFileSystem());
    }

    public void SetPause() {
        if (menu == null) {
            menu = new Menu();
            setPauseMenu(menu);
        }
    }


    public class ICMonGameState {
        public ICMonGameState() {
        }
        public void acceptInteraction(Interactable interactable, boolean isCellInteraction) {
            for (var event : ICMon.this.activeEvents)
                interactable.acceptInteraction(event, isCellInteraction);
        }

        public void send(GamePlayMessage message) {
            messageInMailbox = message;
        }


        public void SuspendFight(ICMonFight fightMenu) {
            setPauseMenu(fightMenu);
            requestPause();
        }


        public void ResumeGame() {
            requestResume();
        }

        public void switchArea(String areaKey, DiscreteCoordinates coords) {
            player.leaveArea();
            ICMonArea area = (ICMonArea) setCurrentArea(areaKey, false);
            player.enterArea(area, coords);
            player.centerCamera();
        }


    }

    public class ICMonEventManager {
        public void registerEvent(ICMonEvent event) {
            newEvents.add(event);
        }

        public void unregisterEvent(ICMonEvent event) {
            completedEvents.add(event);
        }

    }

}
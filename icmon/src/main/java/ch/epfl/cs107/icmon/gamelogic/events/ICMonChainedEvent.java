package ch.epfl.cs107.icmon.gamelogic.events;

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.actions.CompleteEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.RegisterEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.StartEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.UnregisterEventAction;

import java.util.*;

/**
 * ICMonChainedEvent is a class representing a sequence of events that occur one after another in a chain.
 */
public class ICMonChainedEvent extends ICMonEvent {
    private ICMonEvent currentEvent;
    private ICMonEvent initialEvent;
    private ICMonEvent nextEvent;
    private List<ICMonEvent> eventChain;

    private ICMon.ICMonEventManager manager;

    /**
     * Constructor for ICMonChainedEvent.
     *
     * @param manager (ICMon.ICMonEventManager): The manager handling game events.
     * @param initialEvent (ICMonEvent): The initial event in the chain.
     * @param chain (ICMonEvent...): A sequence of events forming the chain.
     */
    public ICMonChainedEvent(ICMon.ICMonEventManager manager, ICMonEvent initialEvent, ICMonEvent... chain) {
        this.initialEvent = initialEvent;
        this.manager = manager;
        this.eventChain = new ArrayList<>();
        eventChain.add(initialEvent);
        eventChain.addAll(Arrays.asList(chain));
        initialEvent.onComplete(new RegisterEventAction(chain[0],manager));
        initialEvent.onComplete(new StartEventAction(chain[0], manager));
        initialEvent.onComplete(new UnregisterEventAction(initialEvent,manager));
        for (int i = 0; i< chain.length ; i++){
            currentEvent = eventChain.get(i);
            nextEvent = eventChain.get(i + 1);
            currentEvent.onComplete(new StartEventAction(nextEvent, manager));
            currentEvent.onComplete(new RegisterEventAction(nextEvent,manager));
            currentEvent.onComplete(new UnregisterEventAction(currentEvent,manager));

        }
        ICMonEvent endEvent = eventChain.get(eventChain.size() - 1);
        endEvent.onComplete(new CompleteEventAction(this, manager));
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        }

    public void start() {
        manager.registerEvent(initialEvent);
        initialEvent.start();
    }

    }

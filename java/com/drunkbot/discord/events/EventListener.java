package com.drunkbot.discord.events;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.Status;

public class EventListener implements IListener<ReadyEvent> {


    public void handle(ReadyEvent readyEvent) {
        this.performActions(readyEvent);
    }

    private void performActions(ReadyEvent event) {
        IDiscordClient client = event.getClient();
        if (client.isReady()) {
            client.changePresence(false);
            client.changeStatus(Status.game("--help"));
        }
    }
}

package com.drunkbot.discord.events;

import com.drunkbot.discord.DrunkBot;
import com.drunkbot.discord.WebRequest;
import com.drunkbot.discord.audio.AudioHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.runtime.ECMAException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.audio.impl.AudioManager;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.Status;

import java.util.Map;

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

package com.drunkbot.discord.events;

import com.drunkbot.discord.DrunkBot;
import com.drunkbot.discord.audio.AudioHandler;
import org.apache.commons.lang3.exception.ExceptionUtils;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.audio.impl.AudioManager;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;

/**
 * Created by dylan on 11/23/16.
 */
public class EventListener implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent messageReceivedEvent) {
        this.performActions(messageReceivedEvent);
    }

    private void performActions(MessageReceivedEvent event) {
        AudioHandler handler = new AudioHandler();
        try {
            handler.OnMesageEvent(event);
        } catch (Exception e) {
            DrunkBot.logger.error(ExceptionUtils.getMessage(e));
        }

    }
}

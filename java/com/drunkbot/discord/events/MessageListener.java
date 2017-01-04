package com.drunkbot.discord.events;

import com.drunkbot.discord.DrunkBot;
import org.apache.commons.lang3.exception.ExceptionUtils;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

import java.util.Random;

/**
 * Created by Dylan on 11/25/2016.
 */
public class MessageListener implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent event)  {
        IMessage message = event.getMessage();
        Random randomGenerator = new Random();

        try {
            if (message.getContent().toLowerCase().contains("ping")) {
                message.getChannel().sendMessage("pong");
            }else if (message.getContent().toLowerCase().contains("pong")) {
                message.getChannel().sendMessage("ping");

            }


        } catch (Exception e ) {
            DrunkBot.logger.error(ExceptionUtils.getMessage(e));
        }
    }


}

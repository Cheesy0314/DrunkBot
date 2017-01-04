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

            } else if (message.getContent().toLowerCase().contains("--help")) {
                String rules = "Hello, thanks for seeking help!\n" +
                        "--cat: Generates random cat picture\n" +
                        "--summon: Summons me into your voice channel\n" +
                        "--dismiss: I will leave the voice channel\n" +
                        "--volume: I will set player volume to specified level\n" +
                        "--airhorn: I will play or queue an airhorn effect\n" +
                        "--skip: I will skip to the next queue item\n" +
                        "--pause: I will pause audio stream\n" +
                        "--resume: I will resume audio stream\n" +
                        "--play: I will play specified URL audio if possible\n" +
                        "--prune: I will delete specified number of messages\n" +
                        "--kick: I will kick specified user\n" +
                        "--ban: I will ban specified user\n" +
                        "--soft: I will soft ban specified user\n";
                message.getAuthor().getOrCreatePMChannel().sendMessage(rules);
            }

        } catch (Exception e ) {
            DrunkBot.logger.error(ExceptionUtils.getMessage(e));
        }
    }


}

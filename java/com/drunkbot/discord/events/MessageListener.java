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
            } else if (message.getContent().contains("SodiumCh1oride") || message.getMentions().get(0).getName().equalsIgnoreCase("SodiumCh1oride")) {
                message.reply("That dude is a fag");
            } else if (message.getAuthor().getName().equalsIgnoreCase("sodiumch1oride")) {
                int ran = randomGenerator.nextInt(100);
                switch (ran % 4) {
                    case 0:
                        message.reply("Shut the fuck up gaylord!");
                        break;
                    case 1:
                        message.reply("Yep yep yep... no one gives a shit a");
                        break;
                    case 2:
                        message.reply("Dude... really?");
                        break;
                    case 3:
                        message.reply("DrunkBot needs you to take the dick out of your mouth dude");
                        break;
                    default:
                        message.reply("Fuck off butt pirate");
                        break;
                }
            }


        } catch (Exception e ) {
            DrunkBot.logger.error(ExceptionUtils.getMessage(e));
        }
    }


}

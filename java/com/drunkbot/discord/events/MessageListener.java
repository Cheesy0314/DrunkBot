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
            } else if (message.getAuthor().getName().equalsIgnoreCase("sodiumch1oride")){
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
            } else if ((message.getAuthor().getID().equalsIgnoreCase("222431757826392065") || message.getAuthor().getID().equalsIgnoreCase("193466098815991809" )) && message.getContent().toLowerCase().contains("timeout")) {
                message.getGuild().getUserByID("221377585215438848").moveToVoiceChannel(message.getGuild().getVoiceChannelByID("250414798376927232"));
                message.getChannel().sendMessage("Sending " +  message.getGuild().getUserByID("221377585215438848").getName() + "to the timeout channel");
            } else if (message.getContent().equalsIgnoreCase("help!")) {
                message.getAuthor().getOrCreatePMChannel().sendMessage("Avaliable Commands:\nsummon - Summons me to voice channel\ndismiss - removes me from voice channel\nHelp! - sens PM of help messages\n ping/pong - pings and pongs all day\nplay/airhorn - plays airhorn in summoned voice channel\nthere are secret commands too....");
                if (message.getAuthor().getID().equalsIgnoreCase("193466098815991809")) {
                    message.getAuthor().getOrCreatePMChannel().sendMessage("timeout - sends warren to the timeout channel");
                }
            }
        } catch (Exception e ) {
            DrunkBot.logger.error(ExceptionUtils.getMessage(e));
        }
    }


}

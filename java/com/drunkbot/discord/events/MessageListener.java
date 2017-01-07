package com.drunkbot.discord.events;

import com.drunkbot.discord.DrunkBot;
import com.drunkbot.discord.connectors.WebRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;

import java.util.Map;
import java.util.Random;

/**
 * Created by Dylan on 11/25/2016.
 */
public class MessageListener implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent event)  {
        IMessage message = event.getMessage();
        try {
            if (message.getContent().equalsIgnoreCase("ping")) {
                message.getChannel().sendMessage("pong");
            }else if (message.getContent().equalsIgnoreCase("pong")) {
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
            } else if (message.getContent().toLowerCase().contains("i was diamond")) {
                message.reply("sure you were buddy....");
                message.addReaction(message.getGuild().getEmojiByName("douche"));
            } else  if (message.getContent().equalsIgnoreCase("--cat")) {
                try {

                    ObjectMapper mapper = new ObjectMapper();

                    WebRequest requestor = new WebRequest();
                    String response = requestor.doRequest("http://random.cat/meow");
                    Map<String, Object> JSON = mapper.readValue(response, Map.class);
                    String file = (String) JSON.get("file");

                    message.reply(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e ) {
            DrunkBot.logger.error(ExceptionUtils.getMessage(e));
        }
    }


}

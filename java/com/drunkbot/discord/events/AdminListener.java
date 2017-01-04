package com.drunkbot.discord.events;

import com.drunkbot.discord.DrunkBot;
import org.apache.commons.lang3.exception.ExceptionUtils;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;

import java.util.List;
import java.util.Random;

/**
 * Created by Dylan on 11/25/2016.
 */
public class AdminListener implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent event) {
        IMessage message = event.getMessage();
        IGuild guild = message.getGuild();

        List<IRole> rolesForAuthor = message.getAuthor().getRolesForGuild(guild);
        boolean valid = false;

        for (IRole currentRole : rolesForAuthor) {
            if (valid == false) {
                if (currentRole.getID().equalsIgnoreCase("221379836776546304") || currentRole.getID().equalsIgnoreCase("231254104545034240")) {
                    valid = true;
                }
            }
        }
        try {
                if (message.getContent().contains("--kick" ) && valid) {
                    String user = message.getContent().substring(7);

                    message.reply("Kick: " + user);
                    try {
                        IUser userObj = guild.getUsersByName(user, true).get(0);
                        message.getGuild().kickUser(guild.getUserByID(userObj.getID()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (message.getContent().contains("--ban") && valid) {
                    String user = message.getContent().substring(6);

                    message.reply("Ban: " + user);
                    try {
                        IUser userObj = guild.getUsersByName(user, true).get(0);
                        message.getGuild().kickUser(guild.getUserByID(userObj.getID()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (message.getContent().contains("--soft") && valid) {
                    String user = message.getContent().substring(7);
                    message.reply("Soft Ban: " + user);
                    try {
                        IUser userObj = guild.getUsersByName(user, true).get(0);
                        message.getGuild().kickUser(guild.getUserByID(userObj.getID()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (valid == false && message.getContent().startsWith("--")) {
                message.reply("You can't do that shit stain");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

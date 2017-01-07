package com.drunkbot.discord.events;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.UserVoiceChannelJoinEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;

/**
 * Created by Dylan on 1/6/2017.
 */
public class LFGListener implements IListener<UserVoiceChannelJoinEvent>{
    @Override
    public void handle(UserVoiceChannelJoinEvent userVoiceChannelJoinEvent) {
        System.out.println(userVoiceChannelJoinEvent);
        IUser user = userVoiceChannelJoinEvent.getUser();
        IGuild guild = userVoiceChannelJoinEvent.getChannel().getGuild();
        IVoiceChannel voiceChannel = guild.getVoiceChannelByID("265994889676718082");
        try {


            if (user.getConnectedVoiceChannels().contains(voiceChannel)) {
                    guild.getChannelByID("221334865155457025").sendMessage("@everyone," + user.getName() + " is looking for a group to play R6 with.");

            }
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
}

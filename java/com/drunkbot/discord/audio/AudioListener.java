package com.drunkbot.discord.audio;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.VoiceUserSpeakingEvent;
import sx.blah.discord.handle.obj.IVoiceChannel;

/**
 * Created by Dylan on 1/4/2017.
 */
public class AudioListener implements IListener<VoiceUserSpeakingEvent> {
    public void handle(VoiceUserSpeakingEvent event) {
        if ( event.getClient().getOurUser().getConnectedVoiceChannels().size() > 0  && event.getUser().getConnectedVoiceChannels().size() > 0) {
            for (IVoiceChannel voiceChannel : event.getUser().getConnectedVoiceChannels()) {
                 if (voiceChannel.getConnectedUsers().contains(event.getClient().getOurUser())) {
                     event.getSsrc();
                 }

            }
        }
    }
}

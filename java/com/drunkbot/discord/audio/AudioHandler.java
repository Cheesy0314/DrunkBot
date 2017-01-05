package com.drunkbot.discord.audio;
import com.fasterxml.jackson.databind.ObjectMapper;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.audio.impl.AudioManager;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.obj.VoiceChannel;
import sx.blah.discord.handle.obj.*;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;
import sx.blah.discord.util.audio.AudioPlayer;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by dylan on 11/23/16.
 */
public class AudioHandler {
    private static final String botprefix = "";
    @EventSubscriber
    public void OnMesageEvent(MessageReceivedEvent event) throws IOException, UnsupportedAudioFileException, RateLimitException, MissingPermissionsException, DiscordException {

        IMessage message = event.getMessage(); // Get message from event

            if(message.getContent().toLowerCase().contains("--summon")) {
                try {
                    IVoiceChannel voicechannel;

                    if (message.getAuthor().getConnectedVoiceChannels().size() > 0) {
                        voicechannel = message.getAuthor().getConnectedVoiceChannels().get(0);
                    } else  {
                        voicechannel = event.getClient().getGuildByID(message.getGuild().getID()).getVoiceChannelByID("221334865780539392");
                    }
                    voicechannel.join();
                    message.getChannel().sendMessage("Joined `" + voicechannel.getName() + "`.");

                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else if (message.getContent().toLowerCase().contains("--dismiss")) {
                IVoiceChannel voiceChannel = event.getClient().getOurUser().getConnectedVoiceChannels().get(0);
                voiceChannel.leave();
                message.getChannel().sendMessage("Left channel `" + voiceChannel.getName() + "`.");


            }else if(message.getContent().toLowerCase().contains("--volume")) {
                float vol = Float.parseFloat(message.getContent().split(" ")[1]);
                AudioPlayer player = AudioPlayer.getAudioPlayerForGuild(message.getGuild());
                player.setVolume(vol/100);
                int nv = java.lang.Math.round(vol);
                message.reply("Set volume to " + nv);

            }  else if (message.getContent().toLowerCase().contains("--john") || message.getContent().toLowerCase().contains("--cena") ) {

                    AudioPlayer player = AudioPlayer.getAudioPlayerForGuild(message.getGuild());

                        player.queue(new File("src\\resources\\jc.mp3"));

                    ObjectMapper mapper = new ObjectMapper();
                    System.out.println(mapper.writeValueAsString(player.getCurrentTrack().getMetadata()));
//                    message.reply("You asked for it....");

            }  else if ( message.getContent().toLowerCase().contains("airhorn") ) {
                AudioPlayer player = AudioPlayer.getAudioPlayerForGuild(message.getGuild());

                    player.queue(new File("src\\resources\\airhorn.mp3"));
            } else if (message.getContent().toLowerCase().contains("--pause")) {
                AudioPlayer player = AudioPlayer.getAudioPlayerForGuild(message.getGuild());
                player.setPaused(true);
                message.getChannel().sendMessage("Pausing.");
            } else if (message.getContent().toLowerCase().contains("--resume")) {
                AudioPlayer player = AudioPlayer.getAudioPlayerForGuild(message.getGuild());
                player.setPaused(false);
                message.getChannel().sendMessage("Resuming Audio.");
            } else if (message.getContent().equalsIgnoreCase("--skip")) {
                AudioPlayer player = AudioPlayer.getAudioPlayerForGuild(message.getGuild());
                player.skip();
            } else if (message.getContent().toLowerCase().contains("--play")) {
                try {
                    AudioPlayer player = AudioPlayer.getAudioPlayerForGuild(message.getGuild());
                    player.queue(new URL(message.getContent().substring(7)));
                } catch (Exception e ) {
                   try {
                       YoutubeProvider provider = new YoutubeProvider(message.getContent().substring(7).substring(message.getContent().substring(7).indexOf(".com/")));
                       AudioPlayer player = AudioPlayer.getAudioPlayerForGuild(message.getGuild());
                       player.queue(provider);
                   } catch (Exception t) {t.printStackTrace(); message.reply("That's not a valid link asshole");
                   }
                }
            }

    }


    // Queue audio from specified file for guild
    private static void playAudioFromFile(String s_file, IGuild guild) throws IOException, UnsupportedAudioFileException {
//        File file = new File("main/resources/jc.mp3"); // Get file
        AudioPlayer player = AudioPlayer.getAudioPlayerForGuild(guild);
//        player.
        player.setPaused(false);
        player.setVolume(80.0f);
        player.queue(new File("main/resources/jc.mp3"));
        AudioPlayer.Track track = player.getCurrentTrack();
        track.getStream().read(player.provide());
    }

    private static void setVolume(float vol, IGuild guild){
        AudioPlayer player = AudioPlayer.getAudioPlayerForGuild(guild);
        player.setVolume(vol);
    }
}

package com.drunkbot.discord;
//import
/**
 * Created by Dylan on 11/23/2016.
 */
import com.drunkbot.discord.Users.Anthony;
import de.btobastian.javacord.*;
import de.btobastian.javacord.entities.User;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;
import com.google.common.util.concurrent.FutureCallback;

import java.util.Random;

public class DrunkBot {
    public static Random randomGenerator = new Random();
    public DrunkBot(String token) {
        // Get the token from here: https://discordapp.com/developers/applications/me
        DiscordAPI api = Javacord.getApi(token, true);
        // connectFuture
//        FutureCallback
//        MessageCreateListener
        api.connect(new FutureCallback<DiscordAPI>() {
//            @Override
            public void onSuccess(DiscordAPI api) {
                // register listener
                api.registerListener(new MessageCreateListener() {
//                    @Override
                    public void onMessageCreate(DiscordAPI api, Message message) {
                        // check the content of the message
                        if (message.getContent().equalsIgnoreCase("ping")) {
                            // reply to the message
                            message.reply("pong");
                        }

                        if (message.getContent().contains("SodiumCh1oride") || message.getMentions().get(0).getName().equalsIgnoreCase("SodiumCh1oride")) {
                            message.reply("That dude is a fag");
                        }

                        if (message.getAuthor().getName().equalsIgnoreCase("sodiumch1oride")){
//                            int ran = randomGenerator.nextInt(100);

                                int ran = randomGenerator.nextInt(100);
                            switch (ran % 4) {
                                case 0:
                                    message.reply("Shut the fuck up gaylord!");
                                    break;
                                case 1:
                                    message.reply("Yep yep yep... no one gives a shit Anthony");
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

                    }
                });
            }

//            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

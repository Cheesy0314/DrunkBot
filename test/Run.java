import com.drunkbot.discord.DrunkBot;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

import java.util.Random;

public class Run {
    public static void main (String[] args) {
        DrunkBot bot = new DrunkBot("");
        IDiscordClient client = bot.getClient();

        EventDispatcher dispatcher = client.getDispatcher();
        dispatcher.registerListener(bot.getListener());
//        dispatcher.registerListener(new IListener<MessageReceivedEvent>() {
//            @Override
//            public void handle(MessageReceivedEvent event) {
//                try {
//                    IMessage message = event.getMessage();
//                    if (message.getContent().contains("SodiumCh1oride") || message.getMentions().get(0).getName().equalsIgnoreCase("SodiumCh1oride")) {
//                        message.getChannel().sendMessage("That dude is a fag");
//                    }
//
//                    if (message.getAuthor().getName().equalsIgnoreCase("sodiumch1oride")) {
//                        Random randomGenerator = new Random();
//                        int ran = randomGenerator.nextInt(100);
//                        switch (ran % 4) {
//                            case 0:
//                                message.reply("Shut the fuck up gaylord!");
//                                break;
//                            case 1:
//                                message.reply("Yep yep yep... no one gives a shit Anthony");
//                                break;
//                            case 2:
//                                message.reply("Dude... really?");
//                                break;
//                            case 3:
//                                message.reply("DrunkBot needs you to take the dick out of your mouth dude");
//                                break;
//                            default:
//                                message.reply("Fuck off butt pirate");
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }
}
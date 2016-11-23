import com.drunkbot.discord.DrunkBot;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;

public class Run {
    public static void main (String[] args) {
        DrunkBot bot = new DrunkBot("MjUwODY4OTE3NzkzMzI1MDU2.CxbOQg.WyFVI8qe03Nl1FvWdf3HUdOXowg");
        IDiscordClient client = bot.getClient();

        EventDispatcher dispatcher = client.getDispatcher();
        dispatcher.registerListener(bot.getListener());
    }
}
package listener;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.ArrayList;
import java.util.List;

public class EventListener extends ListenerAdapter {



    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
       String message = event.getMessage().getContentRaw();
       if (message.equalsIgnoreCase("ping")){
        event.getChannel().sendMessage("pong").queue();
       }
    }


}

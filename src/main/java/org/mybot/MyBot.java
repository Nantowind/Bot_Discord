package org.mybot;

import commands.CommandManager;
import data.envData;
import listener.EventListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;

/**
 * Clase MyBot que se encarga de la creación y gestión del bot de Discord.
 */
public class MyBot {

    // Atributo para gestionar las conexiones del bot
    private final ShardManager shardManager;

    /**
     * Constructor de la clase MyBot.
     *
     * @throws LoginException Si hay un problema con el token de autenticación.
     */
    public MyBot() throws LoginException {
        // Crea una instancia de DefaultShardManagerBuilder con la configuración predeterminada y el token de autenticación
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(envData.TOKEN);

        // Habilita todos los GatewayIntents utilizando el método getIntents() y la constante ALL_INTENTS
        builder.enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS));
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);

        // Establece el estado en línea para el bot
        builder.setStatus(OnlineStatus.ONLINE);

        // Establece la actividad del bot como "viendo" con un mensaje personalizado
        builder.setActivity(Activity.watching("bien de cerca lo que haces"));

        // Construye el ShardManager con la configuración establecida en el builder
        shardManager = builder.build();

        // Registra un objeto EventListener para manejar los eventos de Discord
        shardManager.addEventListener(new EventListener(), new CommandManager());
    }

    /**
     * Método getter para obtener la instancia de ShardManager.
     *
     * @return La instancia de ShardManager.
     */
    public ShardManager getShardManager() {
        return shardManager;
    }

    /**
     * Método principal (main) y punto de entrada del programa.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        try {
            MyBot bot = new MyBot();
        } catch (LoginException e) {
            e.printStackTrace();
            System.out.println("Error: El token no está funcionando.");
        }
    }
}

package commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.ArrayList;
import java.util.List;

import static commands.CommandFunctions.registerProgressCommand;

public class CommandManager extends ListenerAdapter {
    // Escucha y maneja los eventos de comandos slash
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();

        // Comando "bienvenido": envía un mensaje de bienvenida
        if (command.equals("bienvenido")) {
            CommandFunctions.welcome(event);
        }
        // Comando "registrarse": registra al usuario en el bot
        else if (command.equals("registrarse")) {
            CommandFunctions.register(event);
        }
        // Comando "registrarprogreso": registra el progreso diario del usuario
        else if (event.getName().equals("registrarprogreso")) {
            String progress = event.getOption("ingresa_tu_progreso_de_hoy").getAsString();
            CommandFunctions.registerProgressCommand(event, progress);
        }
        // Comando "verprogresodiario": muestra el progreso diario del usuario
        else if (command.equals("verprogresodiario")) {
            CommandFunctions.viewDailyProgressCommand(event);
        }
        // Comando "eliminarprogresodiario": elimina el progreso diario del usuario
        else if (event.getName().equals("eliminarprogresodiario")) {
            int idResumenDiario = event.getOption("id_progreso_diario_a_eliminar").getAsInt();
            CommandFunctions.deleteDailyProgressCommand(event, idResumenDiario);
        }
    }

    // Maneja el evento cuando un servidor está listo
    @Override
    public void onGuildReady(GuildReadyEvent event) {
        // Crea una lista para almacenar los datos de los comandos
        List<CommandData> commandData = new ArrayList<>();

        // Comandos simples
        // Comando "bienvenido": envía un mensaje de bienvenida
        commandData.add(Commands.slash("bienvenido", "Mensaje de bienvenida"));
        // Comando "registrarse": registra al usuario en el bot
        commandData.add(Commands.slash("registrarse", "Registro unico en el bot"));
        // Comando "verprogresodiario": muestra el progreso diario del usuario
        commandData.add(Commands.slash("verprogresodiario", "Devuelve una lista de tus registros diarios"));

        // Comandos con opciones de texto
        // Comando "registrarprogreso": registra el progreso diario del usuario
        CommandData registrarProgresoCommand = Commands.slash("registrarprogreso", "Registra tu progreso diario");
        ((SlashCommandData) registrarProgresoCommand).addOption(OptionType.STRING, "ingresa_tu_progreso_de_hoy",
                "ingrese tu progreso de hoy", true);
        commandData.add(registrarProgresoCommand);

        // Comando "eliminarprogresodiario": elimina el progreso diario del usuario
        CommandData eliminarProgresoCommand = Commands.slash("eliminarprogresodiario", "Elimina tu progreso diario");
        ((SlashCommandData) eliminarProgresoCommand).addOption(OptionType.INTEGER, "id_progreso_diario_a_eliminar",
                "Ingrese el ID del progreso a eliminar", true);
        commandData.add(eliminarProgresoCommand);

        // Actualiza la lista de comandos en el servidor
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
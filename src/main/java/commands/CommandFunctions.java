// Paquete que contiene la clase CommandFunctions
package commands;

// Importaciones necesarias

import data.ConnectDatabase;
import data.envData;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.awt.*;
import java.util.List;

// Clase que contiene funciones de comandos para la aplicación
public class CommandFunctions {


     //Método que envía un mensaje de bienvenida con saltos de línea en un evento de interacción de comando.
     //Reemplaza los caracteres "()n" por saltos de línea en el mensaje de bienvenida y envía el mensaje
     //como respuesta al evento de interacción de comando.

     // Método para enviar un mensaje de bienvenida al usuario en un evento de interacción de comando
     public static void welcome(SlashCommandInteractionEvent event) {
         // Reemplaza los caracteres "()n" por saltos de línea en el mensaje de bienvenida
         String MensajeBienvenidaConSaltosDeLinea = replaceCharacters(envData.BIENVENIDA);

         // Envía el mensaje de bienvenida como respuesta al evento de interacción de comando
         event.reply(MensajeBienvenidaConSaltosDeLinea).setEphemeral(true).queue();
     }


    // Método para registrar un usuario en la base de datos
    public static void register(SlashCommandInteractionEvent event) {
        long userId = event.getUser().getIdLong();
        String nombreDiscord = event.getUser().getName();
        String apodoServer = event.getMember().getEffectiveName();

        boolean registered = ConnectDatabase.registerUser(userId, nombreDiscord, apodoServer);

        String response;
        if (registered) {
            response = ":partying_face: ¡Felicitaciones! :tada:  "+apodoServer+" se ha registrado exitosamente :white_check_mark:. ¡Bienvenid@ al servidor! :wave: ¡Esperamos que disfrutes tu tiempo aquí! :smiley:!";
        } else {
            response = ":warning: ¡Lo sentimos! :sweat_smile:   "+apodoServer+" no pudo ser registrado exitosamente :x: debido a que  usuario ya está registrado. :scream:";
        }

        event.reply(String.valueOf(response)).setEphemeral(true).queue();
    }


    // Método para registrar el progreso diario de un usuario
    public static void registerProgressCommand(SlashCommandInteractionEvent event, String progress){
        long userId = event.getUser().getIdLong();
        boolean registeredProgress = ConnectDatabase.registerProgress(userId, progress);

        String response;
        if (registeredProgress) {
            response = ":white_check_mark: ¡Tu progreso ha sido registrado exitosamente!";
        } else {
            response = ":x: No se pudo registrar tu progreso. Asegúrate de estar registrado en el sistema.";
        }

        event.reply(response).setEphemeral(true).queue();
    }

    // Método para ver el progreso diario registrado de un usuario
    public static void viewDailyProgressCommand(SlashCommandInteractionEvent event) {
        long userId = event.getUser().getIdLong();
        List<String> progressList = ConnectDatabase.getDailyProgress(userId);

        if (!progressList.isEmpty()) {
            String response = "Aquí está tu progreso diario:\n" + String.join("\n", progressList);
            event.reply(response).setEphemeral(true).queue();
        } else {
            event.reply(":x: No se encontró ningún progreso diario registrado para tu usuario.").setEphemeral(true).queue();
        }
    }

    // Método para eliminar un registro de progreso diario de un usuario
    public static void deleteDailyProgressCommand(SlashCommandInteractionEvent event, int idResumenDiario) {
        long userId = event.getUser().getIdLong();
        boolean isDeleted = ConnectDatabase.deleteDailyProgress(userId, idResumenDiario);

        String response;
        if (isDeleted) {
            response = ":white_check_mark: El progreso diario con ID " + idResumenDiario + " ha sido eliminado exitosamente.";
        } else {
            response = ":x: No se pudo eliminar el progreso diario con ID " + idResumenDiario + ". Asegúrate de que el ID sea correcto y que pertenezca a tu usuario.";
        }

        event.reply(response).setEphemeral(true).queue();
    }

    // Método interno que reemplaza los caracteres "()n" por saltos de línea en una cadena de entrada
    public static String replaceCharacters(String input) {
        return input.replace("()n", "\n");
    }
}

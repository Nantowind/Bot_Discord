// Importamos las bibliotecas necesarias
package data;

// Importa la biblioteca para cargar las variables de entorno desde un archivo .env
import io.github.cdimascio.dotenv.Dotenv;

/**
 * Clase envData que se encarga de cargar las variables de entorno desde un archivo .env.
 */
public class envData {

    // Atributo estático para almacenar las variables de entorno cargadas desde el archivo .env
    public static Dotenv dotenv = Dotenv.load();

    // Atributo estático para almacenar el token de autenticación de Discord
    public static String TOKEN = dotenv.get("TOKEN");
    // Atributo estático para almacenar el mensaje de bienvenida
    public static String BIENVENIDA = dotenv.get("BIENVENIDA");
    // Atributo estático para almacenar la URL de la base de datos
    public static final String DB_URL = dotenv.get("DB_URL");
    // Atributo estático para almacenar el nombre de usuario de la base de datos
    public static final String USER = dotenv.get("USER");
    // Atributo estático para almacenar la contraseña de la base de datos
    public static final String PASS = dotenv.get("PASS");
}

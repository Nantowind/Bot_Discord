Tutorial: Creación de un bot de Discord con JDA
En este tutorial, explicaremos paso a paso cómo se creó el programa MyBot para gestionar un bot de Discord utilizando la librería JDA (Java Discord API).

Índice
Configurar el entorno de desarrollo
Crear el proyecto y las clases principales
Implementar la clase envData
Implementar la clase EventListener
Implementar la clase MyBot
Ejecutar y probar el bot
1. Configurar el entorno de desarrollo
Antes de comenzar, necesitarás tener instalado Java Development Kit (JDK) y un entorno de desarrollo integrado (IDE) como Eclipse o IntelliJ IDEA. Además, asegúrate de tener una cuenta de Discord y acceso al portal de desarrolladores para crear un bot.

2. Crear el proyecto y las clases principales
Crea un nuevo proyecto de Java en tu IDE e inicializa las siguientes clases y paquetes:

org.mybot: Este paquete contendrá la clase principal MyBot.
data: Este paquete contendrá la clase envData, la cual almacenará datos sensibles, como el token de autenticación del bot.
listener: Este paquete contendrá la clase EventListener, la cual manejará los eventos de Discord.
3. Implementar la clase envData
La clase envData se utiliza para almacenar datos sensibles, como el token de autenticación del bot de Discord. Crea una clase llamada envData en el paquete data y añade una variable estática llamada token que contendrá el token de tu bot.


package data;

public class envData {
    public static final String token = "TU_TOKEN_DE_DISCORD";
}
Recuerda reemplazar "TU_TOKEN_DE_DISCORD" con el token de autenticación real de tu bot de Discord.

4. Implementar la clase EventListener
La clase EventListener se encargará de manejar los eventos de Discord. Crea una clase llamada EventListener en el paquete listener e implementa la interfaz EventListener de JDA.

En este ejemplo, no se han implementado métodos específicos para manejar eventos, pero puedes agregarlos según las necesidades de tu bot.


package listener;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventListener extends ListenerAdapter {
    // Implementa los métodos para manejar eventos aquí
}
5. Implementar la clase MyBot
La clase MyBot es la clase principal del programa y se encarga de la creación y gestión del bot de Discord. Copia el código proporcionado en la pregunta en la clase MyBot del paquete org.mybot.

6. Ejecutar y probar el bot
Ejecuta la clase MyBot y asegúrate de que el bot se conecte correctamente a Discord. Si se produce algún error, revisa la configuración y el token de autenticación.

Una vez que el bot esté en línea, podrás ver su actividad como "viendo bien de cerca lo que haces" en Discord. A partir de aquí, puedes comenzar a agregar funcionalidades a tu bot utilizando la clase EventListener para manejar eventos específicos.

¡Eso es todo! Ahora has creado un bot básico de Discord utilizando la librería JDA en Java.

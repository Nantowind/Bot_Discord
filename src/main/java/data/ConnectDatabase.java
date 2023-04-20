package data;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConnectDatabase {

    // Variables de conexión a la base de datos
    // Usa envData donde se traen los datos de .env para evitar exponer datos sensibles
    private static final String DB_URL = envData.DB_URL;
    private static final String USER = envData.USER;
    private static final String PASS = envData.PASS;

    // Función para registrar un nuevo usuario en la base de datos
    public static boolean registerUser(long id, String nombreDiscord, String apodoServer) {
        try {
            // Carga el driver de PostgreSQL y establece la conexión
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // Consulta para verificar si el usuario ya existe
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuario WHERE id_usuario = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            // Si el usuario no existe, inserta el nuevo usuario en la base de datos
            if (!resultSet.next()) {
                PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO usuario (id_usuario, nombre_discord, apodo_server, fecha_registro) VALUES (?, ?, ?, ?)");
                insertStatement.setLong(1, id);
                insertStatement.setString(2, nombreDiscord);
                insertStatement.setString(3, apodoServer);
                insertStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));

                insertStatement.executeUpdate();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // Función para registrar el progreso diario de un usuario
    public static boolean registerProgress(long id, String progress) {
        try {
            // Carga el driver de PostgreSQL y establece la conexión
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // Consulta para verificar si el usuario existe
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuario WHERE id_usuario = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            // Si el usuario existe, inserta el progreso diario en la base de datos
            if (resultSet.next()) {
                PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO resumen_diario (id_usuario, contenido, fecha_registro) VALUES (?, ?, ?)");
                insertStatement.setLong(1, id);
                insertStatement.setString(2, progress);
                insertStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));

                insertStatement.executeUpdate();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Función para eliminar un registro de progreso diario de un usuario
    public static boolean deleteDailyProgress(long id, int idResumenDiario) {
        boolean isDeleted = false;

        try {
            // Carga el driver de PostgreSQL y establece la conexión
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // Consulta para eliminar el registro de progreso diario
            PreparedStatement statement = connection.prepareStatement("DELETE FROM resumen_diario WHERE id_usuario = ? AND id_resumen_diario = ?");
            statement.setLong(1, id);
            statement.setInt(2, idResumenDiario);

            int affectedRows = statement.executeUpdate();

            // Verifica si se eliminó el registro
            if (affectedRows > 0) {
                isDeleted = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isDeleted;
    }
    // Función para obtener el progreso diario de un usuario
    public static List<String> getDailyProgress(long id) {
        List<String> progressList = new ArrayList<>();

        try {
            // Carga el driver de PostgreSQL y establece la conexión
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // Consulta para verificar si el usuario existe
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuario WHERE id_usuario = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            // Si el usuario existe, consulta el progreso diario
            if (resultSet.next()) {
                PreparedStatement selectStatement = connection.prepareStatement("SELECT id_resumen_diario, contenido, fecha_registro FROM resumen_diario WHERE id_usuario = ?");
                selectStatement.setLong(1, id);
                ResultSet progressResultSet = selectStatement.executeQuery();

                // Recorre los resultados y añade cada registro de progreso a la lista
                while (progressResultSet.next()) {
                    int idResumenDiario = progressResultSet.getInt("id_resumen_diario");
                    String contenido = progressResultSet.getString("contenido");
                    Timestamp fechaRegistro = progressResultSet.getTimestamp("fecha_registro");

                    String progress = "ID: " + idResumenDiario + " | Contenido: " + contenido + " | Fecha: " + fechaRegistro.toString();
                    progressList.add(progress);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return progressList;
    }

}

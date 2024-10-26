package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import learningPath.LearningPath;
import usuarios.Estudiante;
import usuarios.Usuario;

public class PersistenciaInscripciones {
    private static final String RUTA_CSV = "inscripciones.csv";

    public static void guardarInscripcion(String correoEstudiante, String nombreLearningPath) {
        try (FileWriter writer = new FileWriter(RUTA_CSV, true)) {
            writer.append(correoEstudiante)
                  .append(",")
                  .append(nombreLearningPath)
                  .append("\n");
        } catch (IOException e) {
            System.out.println("Error al guardar la inscripción: " + e.getMessage());
        }
    }
    
 
    public static void cargarInscripciones(Map<String, Usuario> usuarios, Map<String, LearningPath> learningPaths) {
        String archivo = "inscripciones.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String correoEstudiante = datos[0];
                String nombreLearningPath = datos[1];

                // Verificar que el estudiante y el learning path existan en los mapas
                Usuario estudiante = usuarios.get(correoEstudiante);
                LearningPath learningPath = learningPaths.get(nombreLearningPath);

                // Si existen ambos, inscribir al estudiante en el Learning Path
                if (estudiante != null && learningPath != null) {
                    ((Estudiante) estudiante).addLearningPath(learningPath);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar las inscripciones: " + e.getMessage());
        }
    
    }

}

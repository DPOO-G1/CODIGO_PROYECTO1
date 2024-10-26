package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import actividades.Actividad;
import learningPath.LearningPath;
import usuarios.Profesor;
import usuarios.Usuario;
import java.time.LocalDate;

public class PersistenciaLearningPaths {
		
	public static void guardarLearningPath(LearningPath learningPath, String correo) {
		try (FileWriter writer = new FileWriter("learningPaths.csv", true)) {
		      
            writer.append(correo)
            .append(",").append(learningPath.getTitulo())
            .append(",").append(learningPath.getDescripcion())
            .append(",").append(learningPath.getObjetivos())
            .append(",").append(learningPath.getNivelDificultad())
            .append(",").append((learningPath.getFechaCreacion()).toString())
            .append(",").append((learningPath.getFechaUltModificacion()).toString())
            .append("\n");
            
        } catch (IOException e) {
            System.out.println("Ocurrió un error al el LearningPath.");
            e.printStackTrace();
        }
	}
	
	public static Map<String, LearningPath> cargarLearningPaths(Map<String, Usuario> usuarios) {
		Map<String, LearningPath> mapaLearningPaths = new HashMap<>();
		try (BufferedReader reader = new BufferedReader(new FileReader("learningPaths.csv"))) {
			
			String linea;
            while ((linea = reader.readLine()) != null) {
  
                String[] datos = linea.split(",");
                String correo = datos[0];
                String titulo = datos[1];
                String descripcion = datos[2];
                String objetivos = datos[3];
                String nivelDificultad = datos[4];
                LocalDate fechaCreacion = convertirFecha(datos[5]);
                LocalDate fechaUltModificacion = convertirFecha(datos[6]);
                LearningPath learningPath = new LearningPath(titulo,descripcion,objetivos,nivelDificultad);
                learningPath.setFechaCreacion(fechaCreacion);
                learningPath.setFechaCreacion(fechaUltModificacion);
                
                mapaLearningPaths.put(titulo, learningPath);
                
                Usuario profesor = (Profesor)usuarios.get(correo);
                
                ((Profesor) profesor).addLearningPath(learningPath);
                
            }
        } 
		catch (IOException e) {
            System.out.println("Ocurrió un error al cargar los learning paths.");
            e.printStackTrace();
        }
		return mapaLearningPaths;

	
	}
	public static LocalDate convertirFecha(String fecha) {
		
        LocalDate fechaConvertida = LocalDate.parse(fecha);
		return fechaConvertida;
		
		
	}
	
	
}

package usuarios;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import actividades.Actividad;
import actividades.Reseña;
import learningPath.LearningPath;

public class Estudiante extends Usuario {
    private List<LearningPath> learningPaths;
    private Actividad actividadActual;  // Para controlar la actividad en progreso

    public Estudiante(String nombre, String correo, String password) {
        super(nombre, correo, password);
        this.learningPaths = new ArrayList<>();
        this.actividadActual = null;
    }
    
    @Override
	public void menu() {
    	System.out.println("\n--- Menú de Estudiante ---");
        System.out.println("1. Inscribirse a un Learning Path");
        System.out.println("2. Ver mis Learning Paths");
        System.out.println("3. Ver actividades de un Learning Path");
        System.out.println("4. Iniciar actividad");
        System.out.println("5. Añadir reseña a una actividad");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    	
    }
    // Agregar un Learning Path al estudiante
    public void addLearningPath(LearningPath learningPath) {
        learningPaths.add(learningPath);
        System.out.println("Learning Path '" + learningPath.getTitulo() + "' agregado.");
    }

    // Mostrar todos los Learning Paths en los que el estudiante está inscrito
    public void mostrarLearningPathsInscritos() {
        if (learningPaths.isEmpty()) {
            System.out.println("No estás inscrito en ningún Learning Path.");
        } else {
            System.out.println("\n--- Mis Learning Paths ---");
            for (int i = 0; i < learningPaths.size(); i++) {
                LearningPath lp = learningPaths.get(i);
                System.out.println((i + 1) + ". " + lp.getTitulo() + " - " + lp.getDescripcion());
            }
        }
    }
    
    public void mostrarLearningPathsDisponibles(Map<String, LearningPath> mapaLearnigPaths) {

        if (mapaLearnigPaths.isEmpty()) {
            System.out.println("No hay Learning Paths disponibles en este momento.");
        } else {
            System.out.println("\n--- Learning Paths Disponibles ---");
            for (String key : mapaLearnigPaths.keySet()) {
                LearningPath lp = (LearningPath) mapaLearnigPaths.get(key);
                System.out.println("- " + lp.getTitulo() + ": " + lp.getDescripcion() + " (Dificultad: " + lp.getNivelDificultad() + ")");
            }
        }
        }

    // Iniciar una actividad
    public void iniciarActividad(Actividad actividad) {
        if (actividadActual != null) {
            System.out.println("Ya tienes una actividad en progreso. Completa esa primero.");
        } else {
            this.actividadActual = actividad;
            actividad.iniciar();
            System.out.println("Actividad '" + actividad.getDescripcion() + "' iniciada.");
        }
    }

    // Completar la actividad actual
    public void completarActividad() {
        if (actividadActual != null) {
            actividadActual.completar();
            System.out.println("Actividad '" + actividadActual.getDescripcion() + "' completada.");
            actividadActual = null;
        } else {
            System.out.println("No tienes ninguna actividad en progreso.");
        }
    }


    // Añadir una reseña y rating a una Actividad
    public void añadirReseñaAActividad(Actividad actividad, String comentario, int rating) {
        actividad.addReseña(new Reseña(comentario));
        actividad.addRating(rating);
        System.out.println("Reseña añadida a la Actividad '" + actividad.getDescripcion() + "'.");
    }

	public List<LearningPath> getLearningPaths() {
		return learningPaths;
	}

	public void setLearningPaths(List<LearningPath> learningPaths) {
		this.learningPaths = learningPaths;
	}

	public Actividad getActividadActual() {
		return actividadActual;
	}

	public void setActividadActual(Actividad actividadActual) {
		this.actividadActual = actividadActual;
	}

}
package tests;

import usuarios.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import actividades.*;
import learningPath.*;

public class ArchivoPrueba {

	public static void main(String[] args) {
		
		//Crear instancia de un profe
		Profesor profesor = new Profesor("Juan","j.uniandes","123");
		System.out.println("Se creó el profesor: "+profesor.getNombre()+" con el correo: "
		+profesor.getCorreo()+" y la clave: "+profesor.getPassword()+"\n");
		
		
		LearningPath learningpath = profesor.crearLearningPath("DPOO", "Aprender poo", "Aprender a diseñar y programar", "Intermedio");
		profesor.addLearningPath(learningpath);
		
		System.out.println("El profe crea el siguiente learningPath: \n");
		System.out.println("Título: "+learningpath.getTitulo());
		System.out.println("Descripción: "+learningpath.getDescripcion());
		System.out.println("Objetivos: "+learningpath.getObjetivos());
		System.out.println("Nivel dificultad: "+learningpath.getNivelDificultad()+"\n");
		
		
		
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        
        // Convertir el String a Date
       
        Date fechaConvertida = null;
		try {
			fechaConvertida = formato.parse("2024-11-10");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            
		//Añadir actividad al LearningPath
		Recurso recurso = profesor.crearRecurso("Lectura", "Leer el cap 1 de ...", "Facil", 20, fechaConvertida, true, "PDF");
		learningpath.addActividad(recurso);
		
		System.out.println("El profesor crea el Recurso: ");
		
		System.out.println("Descripción: "+ recurso.getDescripcion());
		System.out.println("Objetivo: "+ recurso.getObjetivo());
		System.out.println("Nivel Dificultad: "+ recurso.getNivelDificultad());
		System.out.println("Duración en mins: "+ recurso.getDuracion());
		System.out.println("Fecha límite sugerida: "+ recurso.getFechaLim());
		System.out.println("Obligatoria: "+ recurso.isObligatoria());
		System.out.println("Tipo de recurso: "+ recurso.getTipoRecurso());
		
		//TODO añadir los demás tipos de actividades
		
		
		
		//Creamos una instancia de estudiante
		
		Estudiante estudiante = new Estudiante("Pepito","p.perez","456");
		System.out.println("Se crea el estudiante: "+estudiante.getNombre()+" con el correo: "
				+estudiante.getCorreo()+" y la clave: "+estudiante.getPassword()+"\n");
		
		//Inscribimos el estudiante a un Learning Path
		estudiante.addLearningPath(learningpath);
	
		System.out.println("El estudiante se inscribe al LearningPath \n");
		System.out.println("El estudiante mira los Learning Paths inscritos\n");
		estudiante.mostrarLearningPathsInscritos();
		
		LearningPath learningpathSeleccionado = estudiante.getLearningPaths().get(0);
		
		System.out.println("El estudiante selecciona el Learning Path y mira sus actividades: \n");
		learningpathSeleccionado.mostrarActividades(); 
		
		//El estudiante selecciona una actividad
		Actividad actividadSelecionada = learningpathSeleccionado.getActividades().get(0);
		
		System.out.println("El estudiante selecciona la actividad y la inicia: ");
		estudiante.iniciarActividad(actividadSelecionada);
		System.out.println("El estudiante completa la actividad");
		estudiante.completarActividad();
		
		//vamos a añadir una reseña
		System.out.println("\nEl estudiante añade una reseña a la actividad y un rating");
		estudiante.añadirReseñaAActividad(actividadSelecionada, "Buena lectura", 4);
		
		System.out.println("\nLa reseña añadida fue: "+ actividadSelecionada.getReseñas().get(0).getCuerpo());
		System.out.println("\nEl rating añadido fue de 4 y el rating promedio de la actividad es: "+ actividadSelecionada.getRatingProm());
		System.out.println("");
		
		estudiante.añadirReseñaAActividad(actividadSelecionada, "No me gustó", 3);
		System.out.println("\nLa reseña añadida fue: "+ actividadSelecionada.getReseñas().get(1).getCuerpo());
		System.out.println("\nSe añade otra reseña rating promedio de la actividad es: "+ actividadSelecionada.getRatingProm());
		
		//Ahora el profesor verá las reseñas
		
		System.out.println("El profesor accede a su Learning Path, a sus actividades, escoge la primera, ve sus reseñas: \n");
		
		
		List<Reseña>listaResenias = profesor.getLearningpaths().get(0).getActividades().get(0).getReseñas();
		
		for(Reseña reseña : listaResenias) {
			System.out.println(reseña.getCuerpo());
		}
	}

}

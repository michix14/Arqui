package com.example.personal_trainner_mvc.Controllers;

import android.content.Context;
import android.util.Log;

import com.example.personal_trainner_mvc.Models.Cronograma.Cronograma;

import java.util.List;

public class CronogramaController {

    private Cronograma cronograma;

    // Constructor que inicializa el modelo Cronograma con el contexto
    public CronogramaController(Context context) {
        this.cronograma = new Cronograma(context);
    }

    // Método para crear un nuevo cronograma
    public void create(int rutinaId, int ejercicioId, String dia, int repeticiones, int series) {
        try {
            // Validar los datos antes de crear el cronograma
            cronograma.Validar(rutinaId, ejercicioId, dia, repeticiones, series);
            // Crear el registro en la base de datos
            cronograma.create();
        } catch (Exception e) {
            Log.e("Error rutina creada", "create: "+e.getMessage());;
            e.printStackTrace();  // Para obtener detalles del error
        }
    }

    // Método para buscar un cronograma por IDs de rutina y ejercicio
    public List <Cronograma> FindById(int rutinaId) {
        return cronograma.findByRutinaId(rutinaId);
    }

    // Método para obtener todos los cronogramas
    public List<Cronograma> FindAll() {
        return cronograma.FindAll();
    }

    // Método para actualizar un cronograma
    public void update(int rutinaId, int ejercicioId, String dia, int repeticiones, int series) {
        try {
            // Validar los datos antes de actualizar el cronograma
            cronograma.Validar(rutinaId, ejercicioId, dia, repeticiones, series);
            // Actualizar el registro en la base de datos
            cronograma.update();
        } catch (Exception e) {
            System.out.println("Ocurrió un error al validar los datos: " + e.getMessage());
            e.printStackTrace();  // Para obtener detalles del error
        }
    }

    // Método para eliminar un cronograma por IDs de rutina y ejercicio
    public void delete(int rutinaId, int ejercicioId) {
        try {
            cronograma.delete(rutinaId, ejercicioId);
        } catch (Exception e) {
            System.out.println("Ocurrió un error al intentar eliminar el cronograma: " + e.getMessage());
            e.printStackTrace();  // Para obtener detalles del error
        }
    }
}

package com.example.personal_trainner_mvc.Controllers;

import android.content.Context;
import com.example.personal_trainner_mvc.Models.Rutina.Rutina;
import java.util.List;

public class RutinaController {
    private Rutina rutina;

    // Constructor que inicializa el modelo
    public RutinaController(Context context) {
        this.rutina = new Rutina(context);
    }

    // Método para crear una nueva rutina
    public Rutina create(String formato, int suscripcion_id) {
        try {
            rutina.validar(formato, suscripcion_id);
            return rutina.create();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para buscar una rutina por su ID
    public Rutina findById(int id) {
        return rutina.findById(id);
    }

    // Método para obtener todas las rutinas
    public List<Rutina> findAll() {
        return rutina.findAll();
    }

    // Método para actualizar una rutina
    public void update(int id, String formato, int suscripcion_id) {
        try {
            rutina.setId(id);
            rutina.validar(formato, suscripcion_id);
            rutina.update();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar una rutina
    public void delete(int id) {
        rutina.delete(id);
    }
}

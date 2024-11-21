package com.example.personal_trainner_mvc.Controllers;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.personal_trainner_mvc.Models.Suscripcion.Suscripcion;
import com.example.personal_trainner_mvc.Views.suscripcion.VSuscripcionIndex;
import com.example.personal_trainner_mvc.estrategy.StrategyMensual;
import com.example.personal_trainner_mvc.estrategy.StrategySuscripcion;
import com.example.personal_trainner_mvc.estrategy.StrategyTrimestral;

import java.util.List;

public class SuscripcionController {

    private Context context;
    private Suscripcion suscripcion;

    // Constructor que inicializa el modelo y el contexto de la vista
    public SuscripcionController(Context context) {
        this.context = context;
        this.suscripcion = new Suscripcion(context);
    }

    // Método para crear una nueva suscripción
    public void createSuscripcion(int clienteId, String estrategiaSeleccionada, String fechaInicio) {
        try {
            StrategySuscripcion strategy;

            // Selección de estrategia
            switch (estrategiaSeleccionada) {
                case "Mensual":
                    strategy = new StrategyMensual();
                    break;
                case "Trimestral":
                    strategy = new StrategyTrimestral();
                    break;
                default:
                    throw new IllegalStateException("Estrategia no reconocida");
            }

            // Configurar y aplicar estrategia
            suscripcion.setClienteId(clienteId);
            suscripcion.setFechaInicio(fechaInicio);
            suscripcion.setStrategySuscripcion(strategy);
            suscripcion.aplicarEstrategia();

            // Calcular y establecer la fecha de fin usando la estrategia
            String fechaFin = strategy.calcularFechaFin(fechaInicio);
            suscripcion.setFechaFin(fechaFin);

            // Guardar en la base de datos
            suscripcion.create();

            Toast.makeText(context, "Suscripción creada con éxito", Toast.LENGTH_LONG).show();
            redirigir();
        } catch (Exception e) {
            Toast.makeText(context, "Error al crear la suscripción: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void redirigir() {
        Intent intent = new Intent(context, VSuscripcionIndex.class);
        context.startActivity(intent);
    }

    // Método para obtener todas las suscripciones
    public List<Suscripcion> findAll() {
        try {
            return suscripcion.findAll();
        } catch (Exception e) {
            Toast.makeText(context, "Error al obtener las suscripciones: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    // Método para buscar una suscripción por ID
    public Suscripcion findById(int id) {
        try {
            return suscripcion.findById(id);
        } catch (Exception e) {
            Toast.makeText(context, "Error al buscar la suscripción: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    // Método para eliminar una suscripción
    public void delete(int id) {
        try {
            suscripcion.delete(id);
            Toast.makeText(context, "Suscripción eliminada con éxito", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(context, "Error al eliminar la suscripción: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}

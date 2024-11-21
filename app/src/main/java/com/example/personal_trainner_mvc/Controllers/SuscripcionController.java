package com.example.personal_trainner_mvc.Controllers;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.personal_trainner_mvc.Models.Suscripcion.Suscripcion;
import com.example.personal_trainner_mvc.Views.suscripcion.VSuscripcionIndex;
import com.example.personal_trainner_mvc.estrategy.StrategyMensual;
import com.example.personal_trainner_mvc.estrategy.StrategySuscripcion;
import com.example.personal_trainner_mvc.estrategy.StrategyTrimestral;
import com.example.personal_trainner_mvc.Views.BotonesRutas;

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
    public void createSuscripcion(int clienteId, String estrategiaSeleccionada) {
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

            // Configurar y aplicar la estrategia
            suscripcion.setClienteId(clienteId);
            suscripcion.setStrategySuscripcion(strategy);
            suscripcion.aplicarEstrategia();

            // Guardar en la base de datos
            suscripcion.create();

            // Redirigir o mostrar mensaje de éxito
            Toast.makeText(context, "Suscripción creada con éxito", Toast.LENGTH_LONG).show();
            redirigir();
        } catch (Exception e) {
            // Manejar errores
            Toast.makeText(context, "Error al crear la suscripción: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    // Método para obtener todas las suscripciones
    public List<Suscripcion> FindAll() {
        try {
            return suscripcion.findAll();
        } catch (Exception e) {
            Toast.makeText(context, "Error al obtener las suscripciones: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    // Método para redirigir a otra vista
    private void redirigir() {
        Intent intent = new Intent(context, VSuscripcionIndex.class);
        context.startActivity(intent);
    }


    // Otros métodos CRUD
    public Suscripcion findById(int id) {
        return suscripcion.findById(id);
    }

    public void delete(int id) {
        suscripcion.delete(id);
    }
}

package com.example.personal_trainner_mvc.estrategy;

import com.example.personal_trainner_mvc.Models.Suscripcion.Suscripcion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StrategyTrimestral implements StrategySuscripcion {

    @Override
    public void aplicar(Suscripcion suscripcion) {
        suscripcion.setMonto(250);
        suscripcion.setDuracion("3 meses");
    }

    @Override
    public String calcularFechaFin(String fechaInicio) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // Crear un objeto Calendar basado en la fecha de inicio
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(fechaInicio));

            // Añadir 3 meses
            calendar.add(Calendar.MONTH, 3);

            // Devolver la nueva fecha en formato yyyy-MM-dd
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            // Lanzar excepción en caso de fecha inválida
            throw new IllegalArgumentException("Fecha de inicio inválida: " + e.getMessage());
        }
    }
}

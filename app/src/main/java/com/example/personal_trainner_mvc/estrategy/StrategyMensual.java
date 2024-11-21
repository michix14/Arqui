package com.example.personal_trainner_mvc.estrategy;



import com.example.personal_trainner_mvc.Models.Suscripcion.Suscripcion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StrategyMensual implements StrategySuscripcion {
    @Override
    public void aplicar(Suscripcion suscripcion) {
        suscripcion.setMonto(100);
        suscripcion.setDuracion("1 mes");
    }

    @Override
    public String calcularFechaFin(String fechaInicio) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(fechaInicio));
            calendar.add(Calendar.MONTH, 1); // Añade un mes
        } catch (ParseException e) {
            throw new IllegalArgumentException("Fecha de inicio inválida.");
        }
        return sdf.format(calendar.getTime());
    }
}

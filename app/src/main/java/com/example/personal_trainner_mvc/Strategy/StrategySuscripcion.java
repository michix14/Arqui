package com.example.personal_trainner_mvc.Strategy;

import com.example.personal_trainner_mvc.Models.Suscripcion.Suscripcion;

public interface StrategySuscripcion {
    void aplicar(Suscripcion suscripcion);
    String calcularFechaFin(String fechaInicio);
}

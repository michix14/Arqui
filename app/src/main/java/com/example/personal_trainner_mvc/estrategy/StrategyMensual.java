package com.example.personal_trainner_mvc.estrategy;

import com.example.personal_trainner_mvc.Models.Suscripcion.Suscripcion;

public class StrategyMensual implements StrategySuscripcion {
    @Override
    public void aplicar(Suscripcion suscripcion) {
        suscripcion.setMonto(100);
        suscripcion.setDuracion("1 mes");
    }
}

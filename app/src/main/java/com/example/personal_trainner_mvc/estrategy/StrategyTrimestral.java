package com.example.personal_trainner_mvc.estrategy;

import com.example.personal_trainner_mvc.Models.Suscripcion.Suscripcion;

public class StrategyTrimestral implements StrategySuscripcion {
    @Override
    public void aplicar(Suscripcion suscripcion) {
        suscripcion.setMonto(250);
        suscripcion.setDuracion("3 meses");
    }
}

package com.example.personal_trainner_mvc.Views;

import android.content.Context;
import android.content.Intent;

import com.example.personal_trainner_mvc.Views.Categoria.VCategoria;
import com.example.personal_trainner_mvc.Views.cliente.MainActivity;
import com.example.personal_trainner_mvc.Views.cliente.VClienteCreate;
import com.example.personal_trainner_mvc.Views.ejercicio.VEjercicioCreate;
import com.example.personal_trainner_mvc.Views.ejercicio.VEjercicioIndex;
import com.example.personal_trainner_mvc.Views.rutina.VRutinaIndex;
import com.example.personal_trainner_mvc.Views.suscripcion.VSuscripcionCreate;
import com.example.personal_trainner_mvc.Views.suscripcion.VSuscripcionIndex;

public class BotonesRutas {
    public static void BtnCliente(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
    public static void BtnSuscripcion(Context context) {
        Intent intent = new Intent(context, VSuscripcionIndex.class);
        context.startActivity(intent);
    }

    public static void BtnCrearSuscripcion(Context context) {
        Intent intent = new Intent(context, VSuscripcionCreate.class);
        context.startActivity(intent);
    }


    public static void BtnEjercicio(Context context) {
        Intent intent = new Intent(context, VEjercicioIndex.class);
        context.startActivity(intent);
    }


    public static void BtnRutina(Context context) {
        Intent intent = new Intent(context, VRutinaIndex.class);
        context.startActivity(intent);
    }








    public static void BtnCreateEjercicio(Context context) {
        Intent intent = new Intent(context, VEjercicioCreate.class);
        context.startActivity(intent);
    }


    public static void BtnClienteCreate(Context context) {
        Intent intent = new Intent(context, VClienteCreate.class);
        context.startActivity(intent);
    }


    public static void BtnCreateCEjercicio(Context context) {
        Intent intent = new Intent(context, VCategoria.class); // Cambia si necesitas otra actividad
        context.startActivity(intent);
    }

}

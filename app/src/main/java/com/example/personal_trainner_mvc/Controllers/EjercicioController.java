package com.example.personal_trainner_mvc.Controllers;

import android.content.Context;

import com.example.personal_trainner_mvc.Models.Cliente.Cliente;
import com.example.personal_trainner_mvc.Models.Ejercicio.Ejercicio;


import java.util.List;

public class EjercicioController {


    Context context;

    Cliente dcliente;

    private Ejercicio dejercicio;

    // Constructor que inicializa el modelo
    public EjercicioController(Context context) {
        this.dejercicio = new Ejercicio(context);
    }




    public void create(String nombre, String description, String link, int categoria_id) {
       dejercicio.Validar(nombre,description,link,categoria_id);
       dejercicio.create();

    }


    public Ejercicio FindById(int id) {
        return dejercicio.FindById(id);
    }

    public List<Ejercicio> FindAll() {
        return dejercicio.FindAll();
    }




    public void update(String nombre, String description, String link, int categoria_id) {
        dejercicio.Validar(nombre,description,link, categoria_id);
        dejercicio.update();
    }

    public void delete(int id) {
        dejercicio.delete(id);
    }









}

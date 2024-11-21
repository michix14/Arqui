package com.example.personal_trainner_mvc.Controllers;

import android.content.Context;

import com.example.personal_trainner_mvc.Models.Categoria.Categoria;
import com.example.personal_trainner_mvc.Models.Cliente.Cliente;


import java.util.List;

public class CategoriaController {


    Context context;

    Cliente dcliente;

    private Categoria categoria;

    // Constructor que inicializa el modelo
    public CategoriaController(Context context) {
        this.categoria = new Categoria(context);
    }

    public void create(String nombre, String descripcion) {
        try {
            categoria.Validar(nombre,descripcion);
            categoria.create();
        } catch (Exception e) {
            System.out.println("Ocurrió un error al validar los datos: " + e.getMessage());
            e.printStackTrace();  // Para obtener detalles del error
        }
    }


    public Categoria FindById(int id) {
        return categoria.FindById(id);
    }

    public List<Categoria> FindAll() {
        return categoria.FindAll();
    }


    public void update(int id,String nombre, String descripcion) {
        try {
            categoria.Validar(nombre, descripcion);
            categoria.create();
        } catch (Exception e) {
            System.out.println("Ocurrió un error al validar los datos: " + e.getMessage());
            e.printStackTrace();  // Para obtener detalles del error
        }

     }

    public void delete(int id) {
        categoria.delete(id);
    }





}

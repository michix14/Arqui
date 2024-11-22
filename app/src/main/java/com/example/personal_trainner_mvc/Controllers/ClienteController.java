package com.example.personal_trainner_mvc.Controllers;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.personal_trainner_mvc.Models.Cliente.Cliente;
import com.example.personal_trainner_mvc.Views.cliente.MainActivity;
import com.example.personal_trainner_mvc.Views.cliente.VClienteCreate;
import com.example.personal_trainner_mvc.Views.cliente.VClienteShow;

import java.util.List;

public class ClienteController {

    private Context context;
    private Cliente cliente;
    private VClienteCreate vClienteCreate;
    private MainActivity mainActivity;
    private VClienteShow vClienteShow;

    // Constructor que inicializa el modelo
    public ClienteController(Context context) {
        this.context = context;
        this.cliente = new Cliente(context);
    }

    // Crear cliente y redirigir
    public void create(String nombre, int celular, double peso, double estatura, String direccion, int edad) {
        try {
            // Validar datos
            cliente.Validar(nombre, celular, peso, estatura, edad, direccion);

            // Guardar cliente
            cliente.create();

            // Mostrar mensaje de éxito
            Toast.makeText(context, "Se agregó el cliente correctamente", Toast.LENGTH_LONG).show();

            // Redirigir a MainActivity
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Necesario si el contexto no es una actividad
            context.startActivity(intent);

        } catch (Exception e) {
            // Mostrar mensaje de error
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace(); // Para depuración
        }
    }

    // Buscar cliente por ID
    public Cliente FindById(int id) {
        return cliente.FindById(id);
    }

    // Obtener todos los clientes
    public List<Cliente> FindAll() {
        return cliente.FindAll();
    }

    // Actualizar cliente
    public void update(int id, String nombre, int celular, double peso, double estatura, String direccion, int edad) {
        try {
            cliente.Validar(nombre, celular, peso, estatura, edad, direccion);
            cliente.update(id);
            Toast.makeText(context, "Se edito el cliente correctamente", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Necesario si el contexto no es una actividad
            context.startActivity(intent);

        } catch (Exception e) {
            Toast.makeText(context, "Error al actualizar cliente: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    // Eliminar cliente
    public void delete(int id) {
        try {
            cliente.delete(id); // Llama al método de eliminación del modelo

            // Mostrar mensaje de confirmación
            Toast.makeText(context, "Cliente eliminado correctamente", Toast.LENGTH_SHORT).show();

            // Redirigir a MainActivity
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Necesario si el contexto no es una actividad
            context.startActivity(intent);

        } catch (Exception e) {
            // Mostrar mensaje de error
            Toast.makeText(context, "Error al eliminar cliente: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


}

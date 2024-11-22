package com.example.personal_trainner_mvc.Command;

import com.example.personal_trainner_mvc.Models.Cliente.Cliente;

public class UpdateClienteCommand implements Command {
    private Cliente receiver; // Modelo como Receiver
    private int id;
    private String nombre;
    private int celular;
    private double peso;
    private double estatura;
    private String direccion;
    private int edad;

    public UpdateClienteCommand(Cliente receiver, int id, String nombre, int celular, double peso, double estatura, String direccion, int edad) {
        this.receiver = receiver; // Se pasa el modelo
        this.id = id;
        this.nombre = nombre;
        this.celular = celular;
        this.peso = peso;
        this.estatura = estatura;
        this.direccion = direccion;
        this.edad = edad;
    }

    @Override
    public void execute() {
        try {
            // Validar y actualizar el cliente en el modelo
            receiver.Validar(nombre, celular, peso, estatura, edad, direccion);
            receiver.update(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el cliente con ID " + id + ": " + e.getMessage(), e);
        }
    }
}

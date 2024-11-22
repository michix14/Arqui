package com.example.personal_trainner_mvc.Command;

import com.example.personal_trainner_mvc.Models.Cliente.Cliente;

public class CreateClienteCommand implements Command {

    private Cliente receiver; // El modelo (Receiver)
    private String nombre;
    private int celular;
    private double peso;
    private double estatura;
    private String direccion;
    private int edad;

    public CreateClienteCommand(Cliente receiver, String nombre, int celular, double peso, double estatura, String direccion, int edad) {
        this.receiver = receiver;
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
            // Validar y crear cliente en el modelo (Receiver)
            receiver.Validar(nombre, celular, peso, estatura, edad, direccion);
            receiver.create();
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el cliente: " + e.getMessage(), e);
        }
    }
}

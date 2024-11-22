package com.example.personal_trainner_mvc.Command;

import com.example.personal_trainner_mvc.Models.Cliente.Cliente;

public class DeleteClienteCommand implements Command {
    private Cliente receiver; // Modelo como Receiver
    private int id;

    public DeleteClienteCommand(Cliente receiver, int id) {
        this.receiver = receiver; // Se pasa el modelo
        this.id = id;
    }

    @Override
    public void execute() {
        try {
            // Ejecutar la operación en el modelo
            receiver.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el cliente con ID " + id + ": " + e.getMessage(), e);
        }
    }
}

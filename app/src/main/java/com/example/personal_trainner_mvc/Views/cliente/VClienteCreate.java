package com.example.personal_trainner_mvc.Views.cliente;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personal_trainner_mvc.Command.CommandInvoker;
import com.example.personal_trainner_mvc.Command.CreateClienteCommand;
import com.example.personal_trainner_mvc.Controllers.ClienteController;
import com.example.personal_trainner_mvc.Models.Cliente.Cliente;
import com.example.primerp_arqui_entrenador_java.R;
import com.example.personal_trainner_mvc.Views.BotonesRutas;

public class VClienteCreate extends AppCompatActivity {

    private EditText editTextNombre, editTextCelular, editTextPeso, editTextEstatura, editTextEdad, editTextDireccion;
    private ClienteController clienteController; // Receiver
    private ImageButton buttonEjercicio, buttonComida, buttonCliente;
    private Button buttonBack, buttonGuardar;
    private CreateClienteCommand createClienteCommand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_create);

        // Inicializar los campos y botones
        editTextNombre = findViewById(R.id.editTextCreateClienteNombre);
        editTextCelular = findViewById(R.id.editTextCClienteCelular);
        editTextPeso = findViewById(R.id.editTextCClientePeso);
        editTextEstatura = findViewById(R.id.editTextCreateClienteEstatura);
        editTextDireccion = findViewById(R.id.editTextCreateClienteDireccion);
        editTextEdad = findViewById(R.id.editTextCClienteEdad);
        buttonGuardar = findViewById(R.id.buttonGuardar);
        buttonCliente = findViewById(R.id.buttonCliente);
        buttonEjercicio = findViewById(R.id.buttonEjercicio);
        buttonBack = findViewById(R.id.buttonBack);

        // Inicializar el controlador (Receiver)
        clienteController = new ClienteController(VClienteCreate.this);

        // Evento para guardar el cliente
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CreateC(); // Ejecutar el comando de creación
                } catch (Exception e) {
                    Toast.makeText(VClienteCreate.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace(); // Para depuración
                }
            }
        });

        // Botón para regresar
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Botones de navegación
        buttonCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BotonesRutas.BtnCliente(v.getContext());
            }
        });

        buttonEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BotonesRutas.BtnEjercicio(v.getContext());
            }
        });
    }

    // Método para ejecutar el comando de creación
    public void CreateC() {
        try {
            // Obtener los valores del formulario

            String nombre = editTextNombre.getText().toString();
            int celular = Integer.parseInt(editTextCelular.getText().toString());
            double peso = Double.parseDouble(editTextPeso.getText().toString());
            double estatura = Double.parseDouble(editTextEstatura.getText().toString());
            String direccion = editTextDireccion.getText().toString();
            int edad = Integer.parseInt(editTextEdad.getText().toString());

            // Validar campos obligatorios
            if (nombre.isEmpty() || direccion.isEmpty()) {
                throw new IllegalArgumentException("Los campos Nombre y Dirección son obligatorios.");
            }

            // Inicializar el modelo (Receiver)
            Cliente clienteReceiver = new Cliente(this);

            // Crear el comando concreto (CreateClienteCommand)
            CreateClienteCommand createCommand = new CreateClienteCommand(
                    clienteReceiver, nombre, celular, peso, estatura, direccion, edad
            );

            // Inicializar el invoker y configurar el comando
            CommandInvoker invoker = new CommandInvoker();
            invoker.setCommand(createCommand);

            // Ejecutar el comando usando el invoker
            invoker.executeCommand();Toast.makeText(this, "Se agrego el cliente con exito", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor, ingresa valores numéricos válidos en los campos correspondientes.", Toast.LENGTH_LONG).show();
        } catch (IllegalArgumentException e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error inesperado: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}

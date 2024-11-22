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
import com.example.personal_trainner_mvc.Command.DeleteClienteCommand;
import com.example.personal_trainner_mvc.Command.UpdateClienteCommand;
import com.example.personal_trainner_mvc.Models.Cliente.Cliente;
import com.example.personal_trainner_mvc.Views.BotonesRutas;
import com.example.primerp_arqui_entrenador_java.R;

public class VClienteShow extends AppCompatActivity {

    private Button buttonGuardar, buttonEliminar, buttonSuscripcion;
    private ImageButton buttonCliente, buttonEjercicio;
    private EditText nombreTextView, celularTextView, edadTextView, pesoTextView, alturaTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_show);

        // Obtener referencias de los EditTexts
        nombreTextView = findViewById(R.id.textViewNombre);
        edadTextView = findViewById(R.id.textViewEdad);
        pesoTextView = findViewById(R.id.peso);
        alturaTextView = findViewById(R.id.altura);
        celularTextView = findViewById(R.id.editTextCClienteCelular);
        buttonGuardar = findViewById(R.id.buttonGuardar);
        buttonEliminar = findViewById(R.id.buttonEliminar);

        // Obtener el ID del cliente del Intent
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        // Inicializar el modelo (Receiver)
        Cliente clienteReceiver = new Cliente(this);

        // Cargar los datos del cliente
        Cliente cliente = clienteReceiver.FindById(id);
        if (cliente != null) {
            // Establecer los valores en los EditTexts
            nombreTextView.setText(cliente.getNombre());
            edadTextView.setText(String.valueOf(cliente.getEdad()));
            pesoTextView.setText(String.valueOf(cliente.getPeso()));
            alturaTextView.setText(String.valueOf(cliente.getEstatura()));
            celularTextView.setText(String.valueOf(cliente.getCelular()));
        } else {
            Toast.makeText(this, "Cliente no encontrado.", Toast.LENGTH_LONG).show();
        }

        // Configurar el botón de guardar (Update)
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Obtener los valores actualizados
                    String nuevoNombre = nombreTextView.getText().toString().trim();
                    int nuevaEdad = Integer.parseInt(edadTextView.getText().toString());
                    double nuevoPeso = Double.parseDouble(pesoTextView.getText().toString());
                    double nuevaAltura = Double.parseDouble(alturaTextView.getText().toString());
                    int nuevoCelular = Integer.parseInt(celularTextView.getText().toString());

                    // Crear el comando concreto (UpdateClienteCommand)
                    UpdateClienteCommand updateCommand = new UpdateClienteCommand(
                            clienteReceiver, cliente.getId(), nuevoNombre, nuevoCelular, nuevoPeso, nuevaAltura, cliente.getDireccion(), nuevaEdad
                    );

                    // Usar el Invoker para ejecutar el comando
                    CommandInvoker invoker = new CommandInvoker();
                    invoker.setCommand(updateCommand);
                    invoker.executeCommand();

                    // Mostrar mensaje de éxito
                    Toast.makeText(VClienteShow.this, "Cliente actualizado correctamente.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(VClienteShow.this, MainActivity.class);
                    startActivity(intent);
                } catch (NumberFormatException e) {
                    Toast.makeText(VClienteShow.this, "Por favor, ingresa valores numéricos válidos.", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(VClienteShow.this, "Error al actualizar cliente: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        // Configurar el botón de eliminar (Delete)
        buttonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Crear el comando concreto (DeleteClienteCommand)
                    DeleteClienteCommand deleteCommand = new DeleteClienteCommand(clienteReceiver, id);

                    // Usar el Invoker para ejecutar el comando
                    CommandInvoker invoker = new CommandInvoker();
                    invoker.setCommand(deleteCommand);
                    invoker.executeCommand();

                    // Mostrar mensaje de éxito y regresar a la pantalla principal
                    Toast.makeText(VClienteShow.this, "Cliente eliminado correctamente.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(VClienteShow.this, MainActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(VClienteShow.this, "Error al eliminar cliente: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        // Configurar otros botones
        buttonSuscripcion = findViewById(R.id.buttonCSuscripcion);
        buttonSuscripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BotonesRutas.BtnSuscripcion(v.getContext());
            }
        });

        buttonCliente = findViewById(R.id.buttonCliente);
        buttonCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BotonesRutas.BtnCliente(v.getContext());
            }
        });

        buttonEjercicio = findViewById(R.id.buttonEjercicio);
        buttonEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BotonesRutas.BtnEjercicio(v.getContext());
            }
        });
    }
}

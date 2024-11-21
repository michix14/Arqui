package com.example.personal_trainner_mvc.Views.cliente;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personal_trainner_mvc.Controllers.ClienteController;
import com.example.personal_trainner_mvc.Models.Cliente.Cliente;
import com.example.personal_trainner_mvc.Views.BotonesRutas;
import com.example.primerp_arqui_entrenador_java.R;

public class VClienteShow extends AppCompatActivity {

    private Button buttonGuardar, buttonEliminar, buttonSuscripcion;
    private ImageButton buttonCliente, buttonEjercicio;
    private EditText nombreTextView, celularTextView, edadTextView, pesoTextView, alturaTextView;

    private ClienteController clienteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_show);

        // Inicializar el controlador
        clienteController = new ClienteController(this);

        // Obtener referencias de los EditTexts
        nombreTextView = findViewById(R.id.textViewNombre);
        edadTextView = findViewById(R.id.textViewEdad);
        pesoTextView = findViewById(R.id.peso);
        alturaTextView = findViewById(R.id.altura);
        celularTextView = findViewById(R.id.editTextCClienteCelular);
        buttonGuardar = findViewById(R.id.buttonGuardar);
        buttonEliminar = findViewById(R.id.buttonEliminar); // Nuevo botón para eliminar

        // Obtener el ID del cliente del Intent
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        // Cargar los datos del cliente utilizando el controlador
        Cliente cliente = clienteController.FindById(id);
        if (cliente != null) {
            // Establecer los valores en los EditTexts
            nombreTextView.setText(cliente.getNombre());
            edadTextView.setText(String.valueOf(cliente.getEdad()));
            pesoTextView.setText(String.valueOf(cliente.getPeso()));
            alturaTextView.setText(String.valueOf(cliente.getEstatura()));
            celularTextView.setText(String.valueOf(cliente.getCelular()));
        }

        // Configurar el botón de guardar
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores actualizados
                String nuevoNombre = nombreTextView.getText().toString();
                int nuevaEdad = Integer.parseInt(edadTextView.getText().toString());
                double nuevoPeso = Double.parseDouble(pesoTextView.getText().toString());
                double nuevaAltura = Double.parseDouble(alturaTextView.getText().toString());
                int nuevoCelular = Integer.parseInt(celularTextView.getText().toString());

                clienteController.update(cliente.getId(), nuevoNombre, nuevoCelular, nuevoPeso, nuevaAltura, cliente.getDireccion(), nuevaEdad);

                // Mostrar mensaje de confirmación
                Toast.makeText(VClienteShow.this, "Cliente actualizado correctamente", Toast.LENGTH_LONG).show();
            }
        });

        // Configurar el botón de eliminar
        buttonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clienteController.delete(id); // Llamar al método de eliminación
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

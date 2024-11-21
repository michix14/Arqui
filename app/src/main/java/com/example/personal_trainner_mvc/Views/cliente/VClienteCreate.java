package com.example.personal_trainner_mvc.Views.cliente;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personal_trainner_mvc.Controllers.ClienteController;
import com.example.primerp_arqui_entrenador_java.R;
import com.example.personal_trainner_mvc.Views.BotonesRutas;

public class VClienteCreate extends AppCompatActivity {

    private EditText editTextNombre, editTextCelular, editTextPeso, editTextEstatura, editTextEdad, editTextDireccion;
    private ClienteController clienteController;
    private ImageButton buttonEjercicio, buttonComida, buttonCliente;
    private Button buttonBack, buttonGuardar;

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

        // Evento para guardar el cliente
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CreateC(); // Crear cliente
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

    // Llamar al controlador para crear cliente
    public void CreateC() {
        String nombre = editTextNombre.getText().toString();
        int celular = Integer.parseInt(editTextCelular.getText().toString());
        double peso = Double.parseDouble(editTextPeso.getText().toString());
        double estatura = Double.parseDouble(editTextEstatura.getText().toString());
        String direccion = editTextDireccion.getText().toString();
        int edad = Integer.parseInt(editTextEdad.getText().toString());

        clienteController = new ClienteController(VClienteCreate.this);
        clienteController.create(nombre, celular, peso, estatura, direccion, edad);
    }
}

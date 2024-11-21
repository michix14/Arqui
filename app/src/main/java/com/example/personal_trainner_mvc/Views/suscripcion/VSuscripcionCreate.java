package com.example.personal_trainner_mvc.Views.suscripcion;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personal_trainner_mvc.Controllers.ClienteController;
import com.example.personal_trainner_mvc.Controllers.SuscripcionController;
import com.example.personal_trainner_mvc.Models.Cliente.Cliente;
import com.example.primerp_arqui_entrenador_java.R;

import java.util.List;

public class VSuscripcionCreate extends AppCompatActivity {

    private Spinner clienteSpinner, estrategiaSpinner;
    private Button buttonGuardar;
    private SuscripcionController suscripcionController;
    private ClienteController clienteController;
    private List<Cliente> clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suscripcion_create);

        // Inicializar el controlador con el contexto actual
        suscripcionController = new SuscripcionController(this);

        // Referencias a los elementos de la UI
        clienteSpinner = findViewById(R.id.spinnerCliente);
        estrategiaSpinner = findViewById(R.id.spinnerEstrategia);
        buttonGuardar = findViewById(R.id.buttonGuardar);

        // Cargar datos en los Spinners
        cargarClientes();
        cargarEstrategias();

        // Configurar el evento del botón Guardar
        buttonGuardar.setOnClickListener(view -> guardarSuscripcion());
    }

    // Cargar clientes en el Spinner
    private void cargarClientes() {
        clienteController = new ClienteController(this);
        clientes = clienteController.FindAll();
        ArrayAdapter<Cliente> clienteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, clientes);
        clienteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clienteSpinner.setAdapter(clienteAdapter);
    }

    // Cargar estrategias en el Spinner
    private void cargarEstrategias() {
        ArrayAdapter<String> estrategiaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                new String[]{"Mensual", "Trimestral"});
        estrategiaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estrategiaSpinner.setAdapter(estrategiaAdapter);
    }

    // Guardar suscripción
    private void guardarSuscripcion() {
        Cliente clienteSeleccionado = (Cliente) clienteSpinner.getSelectedItem();
        String estrategiaSeleccionada = estrategiaSpinner.getSelectedItem().toString();

        if (clienteSeleccionado == null || estrategiaSeleccionada.isEmpty()) {
            throw new IllegalArgumentException("Cliente o estrategia no seleccionados.");
        }

        // Llamar al controlador para manejar la lógica
        suscripcionController.createSuscripcion(clienteSeleccionado.getId(), estrategiaSeleccionada);
    }
}

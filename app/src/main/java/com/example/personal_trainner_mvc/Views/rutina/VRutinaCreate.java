package com.example.personal_trainner_mvc.Views.rutina;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personal_trainner_mvc.Controllers.RutinaController;
import com.example.personal_trainner_mvc.Controllers.SuscripcionController;
import com.example.personal_trainner_mvc.Models.Suscripcion.Suscripcion;
import com.example.personal_trainner_mvc.Views.BotonesRutas;
import com.example.primerp_arqui_entrenador_java.R;

import java.util.List;

public class VRutinaCreate extends AppCompatActivity {

    private EditText formato;
    private Spinner suscripcion_id;
    private Button buttonGuardar;
    private RutinaController rutinaController;
    private SuscripcionController suscripcionController;
    private List<Suscripcion> suscripciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutina_create);

        // Referencias a los elementos de la UI
        formato = findViewById(R.id.editTextFormato);
        suscripcion_id = findViewById(R.id.spinnerSuscripcion);
        buttonGuardar = findViewById(R.id.buttonGuardarRutina);

        // Cargar suscripciones en el Spinner
        suscripcionController = new SuscripcionController(VRutinaCreate.this);
        suscripciones = suscripcionController.FindAll();
        ArrayAdapter<Suscripcion> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, suscripciones);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        suscripcion_id.setAdapter(adaptador);

        // Configurar el evento del botón Guardar
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CreateRutina();
                    Toast.makeText(VRutinaCreate.this, "Rutina creada con éxito", Toast.LENGTH_LONG).show();
                    BotonesRutas.BtnRutina(VRutinaCreate.this); // Redirige a la vista de rutinas
                } catch (Exception e) {
                    Toast.makeText(VRutinaCreate.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace(); // Imprimir el error en el log para depuración
                    Log.e("ErrorGuardarRutina", "Error al guardar la rutina", e);
                }
            }
        });
    }

    /* Método para crear la rutina */
    public void CreateRutina() {
        String formato = this.formato.getText().toString();
        Suscripcion suscripcionSeleccionada = (Suscripcion) suscripcion_id.getSelectedItem();
        int suscripcionId = suscripcionSeleccionada.getId();

        if (suscripcionSeleccionada != null) {
            Log.d("SpinnerSelection", "Suscripción Seleccionada: " + suscripcionSeleccionada.toString());
        } else {
            Log.d("SpinnerSelection", "Ninguna suscripción seleccionada.");
        }

        rutinaController = new RutinaController(VRutinaCreate.this);
        rutinaController.create(formato, suscripcionId);
    }
}

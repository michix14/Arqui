package com.example.personal_trainner_mvc.Views.cronograma;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personal_trainner_mvc.Controllers.CronogramaController;
import com.example.personal_trainner_mvc.Controllers.EjercicioController;
import com.example.personal_trainner_mvc.Controllers.RutinaController;
import com.example.personal_trainner_mvc.Models.Ejercicio.Ejercicio;
import com.example.personal_trainner_mvc.Models.Rutina.Rutina;
import com.example.primerp_arqui_entrenador_java.R;

import java.util.ArrayList;
import java.util.List;

public class VCronogramaCreate extends AppCompatActivity {

    private Spinner spinnerRutina, spinnerEjercicio;
    private EditText editTextDia, editTextRepeticiones, editTextSeries;
    private Button buttonAgregarEjercicio, buttonGuardar;
    private RutinaController rutinaController;
    private EjercicioController ejercicioController;
    private CronogramaController cronogramaController;
    private List<Rutina> rutinas;
    private List<Ejercicio> ejercicios;

    // Lista para almacenar ejercicios seleccionados
    private List<Ejercicio> ejerciciosAgregados;
    private ArrayAdapter<Ejercicio> listaAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronograma_create);

        // Obtener el ID de la rutina pasado como parámetro
        int rutinaId = getIntent().getIntExtra("rutina_id", -1);

        // Verificar si se recibió un ID de rutina válido
        if (rutinaId == -1) {
            Toast.makeText(this, "No se recibió un ID de rutina válido", Toast.LENGTH_LONG).show();
            finish(); // Finaliza la actividad si no se recibe un ID válido
            return;
        }

        // Inicialización de vistas y controladores
        // Inicialización de vistas
        spinnerRutina = findViewById(R.id.spinnerRutina);
        spinnerEjercicio = findViewById(R.id.spinnerEjercicio);
        editTextDia = findViewById(R.id.editTextDia);
        editTextRepeticiones = findViewById(R.id.editTextRepeticiones);
        editTextSeries = findViewById(R.id.editTextSeries);
        buttonAgregarEjercicio = findViewById(R.id.buttonAgregarEjercicio);
        buttonGuardar = findViewById(R.id.buttonGuardar);
        // Inicializar la lista de ejercicios agregados
        ejerciciosAgregados = new ArrayList<>();
        listaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ejerciciosAgregados);


        // Inicialización de controladores
        rutinaController = new RutinaController(VCronogramaCreate.this);
        ejercicioController = new EjercicioController(VCronogramaCreate.this);
        cronogramaController = new CronogramaController(VCronogramaCreate.this);
        // Cargar datos iniciales
        cargarEjercicios();

        // Cargar las rutinas y seleccionar la correspondiente
        cargarRutinas(rutinaId);

        // Configurar el evento de agregar ejercicio
        buttonAgregarEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("Llega algo aqui", "onClick: " );
                agregarEjercicio(rutinaId);
            }
        });

        // Configurar el evento del botón Guardar
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Toast.makeText(VCronogramaCreate.this, "Cronograma guardado con éxito", Toast.LENGTH_LONG).show();
                    finish(); // Regresar a la vista anterior
                } catch (Exception e) {
                    Toast.makeText(VCronogramaCreate.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace(); // Imprimir el error en el log para depuración
                    Log.e("ErrorGuardarCronograma", "Error al guardar el cronograma", e);
                }
            }
        });
    }


    private void cargarRutinas(int rutinaId) {
        rutinas = rutinaController.findAll();

        ArrayAdapter<Rutina> adaptadorRutinas = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, rutinas);
        adaptadorRutinas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRutina.setAdapter(adaptadorRutinas);

        // Seleccionar automáticamente la rutina correspondiente
        for (int i = 0; i < rutinas.size(); i++) {
            if (rutinas.get(i).getId() == rutinaId) {
                spinnerRutina.setSelection(i);
                break;
            }
        }

        // Desactivar el spinner si solo se trabaja con una rutina
        spinnerRutina.setEnabled(false);
    }


    private void cargarEjercicios() {
        ejercicios = ejercicioController.FindAll();
        ArrayAdapter<Ejercicio> adaptadorEjercicios = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ejercicios);
        adaptadorEjercicios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEjercicio.setAdapter(adaptadorEjercicios);
    }

    private void agregarEjercicio(int rutinaId) {
        Ejercicio ejercicioSeleccionado = (Ejercicio) spinnerEjercicio.getSelectedItem();
        if (ejercicioSeleccionado != null) {
            int repeticiones = Integer.parseInt(editTextRepeticiones.getText().toString());
            int series = Integer.parseInt(editTextSeries.getText().toString());

            // Agregar el ejercicio a la lista
            ejerciciosAgregados.add(ejercicioSeleccionado);

            // Guardar el ejercicio en la base de datos
            String dia = editTextDia.getText().toString();
            cronogramaController.create(rutinaId, ejercicioSeleccionado.getId(), dia, repeticiones, series);

            // Limpiar los campos
            editTextRepeticiones.setText("");
            editTextSeries.setText("");

            // Notificar al adaptador que los datos han cambiado
            listaAdapter.notifyDataSetChanged();

            Toast.makeText(this, "Ejercicio agregado y guardado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Seleccione un ejercicio", Toast.LENGTH_SHORT).show();
        }
    }



    private void crearCronograma() {
        int rutinaId = ((Rutina) spinnerRutina.getSelectedItem()).getId();
        String dia = editTextDia.getText().toString();

        for (Ejercicio ejercicio : ejerciciosAgregados) {
            int ejercicioId = ejercicio.getId();
            int repeticiones = Integer.parseInt(editTextRepeticiones.getText().toString());
            int series = Integer.parseInt(editTextSeries.getText().toString());
            cronogramaController.create(rutinaId, ejercicioId, dia, repeticiones, series);
        }
    }
}

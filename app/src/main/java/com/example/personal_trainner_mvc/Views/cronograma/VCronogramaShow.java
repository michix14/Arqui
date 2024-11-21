package com.example.personal_trainner_mvc.Views.cronograma;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personal_trainner_mvc.Models.Cronograma.Cronograma;
import com.example.primerp_arqui_entrenador_java.R;

import java.util.List;

public class VCronogramaShow extends AppCompatActivity {

    private ListView listViewCronogramas;
    private ArrayAdapter<Cronograma> adapter;
    private Cronograma cronogramaModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronograma_show);

        // Inicializar vistas
        listViewCronogramas = findViewById(R.id.listViewCronogramas);

        // Inicializar el modelo Cronograma
        cronogramaModel = new Cronograma(this);

        // Obtener el ID de la rutina pasado como parámetro
        int rutinaId = getIntent().getIntExtra("rutina_id", -1);

        // Verificar si se recibió un ID de rutina válido
        if (rutinaId == -1) {
            Toast.makeText(this, "No se recibió un ID de rutina válido", Toast.LENGTH_LONG).show();
            finish(); // Finaliza la actividad si no se recibe un ID válido
            return;
        }

        // Cargar los cronogramas asociados a la rutina
        cargarCronogramas(rutinaId);
    }

    private void cargarCronogramas(int rutinaId) {
        try {
            // Obtener la lista de cronogramas asociados a la rutina
            List<Cronograma> cronogramas = cronogramaModel.findByRutinaId(rutinaId);

            if (cronogramas.isEmpty()) {
                Toast.makeText(this, "No hay cronogramas para esta rutina", Toast.LENGTH_LONG).show();
            }

            // Configurar el adaptador para mostrar los cronogramas en el ListView
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cronogramas);
            listViewCronogramas.setAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(this, "Error al cargar cronogramas: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("ErrorCargarCronogramas", "Error al cargar cronogramas", e);
        }
    }
}

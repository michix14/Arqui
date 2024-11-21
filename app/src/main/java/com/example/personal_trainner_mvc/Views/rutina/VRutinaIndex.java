package com.example.personal_trainner_mvc.Views.rutina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personal_trainner_mvc.Controllers.RutinaController;
import com.example.personal_trainner_mvc.Models.Rutina.Rutina;
import com.example.personal_trainner_mvc.Views.cronograma.VCronogramaCreate;
import com.example.personal_trainner_mvc.Views.cronograma.VCronogramaShow;
import com.example.personal_trainner_mvc.Views.suscripcion.VSuscripcionIndex;
import com.example.primerp_arqui_entrenador_java.R;

import java.util.List;

public class VRutinaIndex extends AppCompatActivity {

    private RutinaController rutinaController;
    private List<Rutina> listaRutinas;
    private ListView listViewRutina;
    private Button buttonCreateRutina;
    private int suscripcionId;  // Variable para almacenar el ID de la suscripción

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutina_index);

        listViewRutina = findViewById(R.id.listViewRutina);
        buttonCreateRutina = findViewById(R.id.buttonCrearRutina);

        // Obtener el ID de la suscripción pasado como parámetro
        suscripcionId = getIntent().getIntExtra("id", -1);

        // Inicializar el controlador y obtener la lista de rutinas
        rutinaController = new RutinaController(this);
        listaRutinas = rutinaController.findAll(); // Aquí podrías filtrar por `suscripcionId` si es necesario

        // Configurar el adaptador para el ListView
        ArrayAdapter<Rutina> adapter = new ArrayAdapter<Rutina>(this, R.layout.activity_item_rutina, R.id.btnRutina, listaRutinas) {

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                final Rutina rutina = listaRutinas.get(position);

                // Configurar el botón de rutina
                Button btnRutina = view.findViewById(R.id.btnRutina);
                btnRutina.setText("Formato: " + rutina.getFormato() + "\n Suscripción ID: " + rutina.getSuscripcion_id());

                // Configurar la acción al hacer clic en el botón de rutina
                btnRutina.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(VRutinaIndex.this, VCronogramaShow.class);
                        intent.putExtra("rutina_id", rutina.getId()); // Pasar el ID de la rutina seleccionada
                        startActivity(intent);
                    }
                });

                // Configurar el botón de eliminación
                Button btnEliminar = view.findViewById(R.id.btnEliminarRutina);  // Asegúrate de tener este botón en tu layout XML
                btnEliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RutinaController controlador = new RutinaController(v.getContext());
                        controlador.delete(rutina.getId());
                        listaRutinas.remove(position); // Eliminar de la lista local
                        notifyDataSetChanged();  // Notificar al adaptador que los datos han cambiado
                    }
                });

                // Configurar el botón de agregar detalles
                Button btnAgregarDetalles = view.findViewById(R.id.btnAgregarDetalles);
                btnAgregarDetalles.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(VRutinaIndex.this, VCronogramaCreate.class);
                        intent.putExtra("rutina_id", rutina.getId()); // Pasar el ID de la rutina seleccionada
                        startActivity(intent);  // Navegar a la vista de agregar detalles
                    }
                });

                return view;
            }

        };

        listViewRutina.setAdapter(adapter);

        // Click para crear una nueva rutina
        buttonCreateRutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VRutinaIndex.this, VRutinaCreate.class);
                intent.putExtra("suscripcion_id", suscripcionId);
                startActivity(intent);
            }
        });
    }
}

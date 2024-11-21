package com.example.personal_trainner_mvc.Views.suscripcion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personal_trainner_mvc.Controllers.SuscripcionController;
import com.example.personal_trainner_mvc.Models.Suscripcion.Suscripcion;
import com.example.personal_trainner_mvc.Views.BotonesRutas;
import com.example.personal_trainner_mvc.Views.rutina.VRutinaIndex;
import com.example.primerp_arqui_entrenador_java.R;

import java.util.List;

public class VSuscripcionIndex extends AppCompatActivity {

    private SuscripcionController suscripcionController;
    private List<Suscripcion> listaSuscripciones;
    private ListView listViewSuscripcion;
    private Button buttonCreateSuscripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suscripcion_index);

        listViewSuscripcion = findViewById(R.id.listViewSuscripcion);
        buttonCreateSuscripcion = findViewById(R.id.buttonCrearSuscripcion);

        // Inicializar el controlador y obtener la lista de suscripciones
        suscripcionController = new SuscripcionController(this);
        listaSuscripciones = suscripcionController.FindAll();

        // Configurar el adaptador para el ListView
        ArrayAdapter<Suscripcion> adapter = new ArrayAdapter<Suscripcion>(this, R.layout.activity_item_suscripcion, R.id.btnSuscripcion, listaSuscripciones) {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                // Configurar el botón de suscripción
                Button btnSuscripcion = view.findViewById(R.id.btnSuscripcion);

                final Suscripcion suscripcion = listaSuscripciones.get(position);
                btnSuscripcion.setText("Monto: " + suscripcion.getMonto() + "\n Duración: " + suscripcion.getDuracion());

                // Configurar la acción al hacer clic en el botón de suscripción
                btnSuscripcion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(VSuscripcionIndex.this, VRutinaIndex.class);
                        intent.putExtra("id", suscripcion.getId());
                        startActivity(intent);
                    }
                });

                // Configurar el botón de eliminación
                Button btnEliminar = view.findViewById(R.id.btnEliminarSuscripcion);  // Asegúrate de tener este botón en tu layout XML
                btnEliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SuscripcionController controlador = new SuscripcionController(v.getContext());
                        controlador.delete(suscripcion.getId());
                        listaSuscripciones.remove(position); // Eliminar de la lista local
                        notifyDataSetChanged();  // Notificar al adaptador que los datos han cambiado
                    }
                });

                return view;
            }
        };

        listViewSuscripcion.setAdapter(adapter);

        // Click para crear una nueva suscripción
        buttonCreateSuscripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BotonesRutas.BtnCrearSuscripcion(v.getContext());
            }
        });
    }
}

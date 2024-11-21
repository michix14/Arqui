package com.example.personal_trainner_mvc.Views.ejercicio;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personal_trainner_mvc.Controllers.EjercicioController;
import com.example.personal_trainner_mvc.Models.Ejercicio.Ejercicio;
import com.example.primerp_arqui_entrenador_java.R;
import com.example.personal_trainner_mvc.Views.BotonesRutas;

import java.util.List;

public class VEjercicioIndex extends AppCompatActivity {

    private ImageButton buttonCliente, buttonEjercicio, buttonComida, buttonCComida;

    private EjercicioController ejercicioController;
    private List<Ejercicio> listaClientes;
    private ListView listViewEjercicio;
    private Button buttonCreateEjercicio, btncreateCategoria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_index);

        buttonCliente = findViewById(R.id.buttonCliente);

        //buttonCComida =findViewById(R.id.buttonCComida);
        btncreateCategoria = findViewById(R.id.BAddCEjercicio);
        listViewEjercicio = findViewById(R.id.listViewEjercicio);
        ejercicioController = new EjercicioController(this);
        listaClientes = ejercicioController.FindAll();
        buttonCreateEjercicio = findViewById(R.id.buttonCrearEjercicio);


        ArrayAdapter<Ejercicio> adapter = new ArrayAdapter<Ejercicio>(this, R.layout.activity_item_ejercicio, R.id.btnCEjercicio, listaClientes) {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                Button btnEjercicio = view.findViewById(R.id.btnCEjercicio);
                final Ejercicio cliente = listaClientes.get(position);
                btnEjercicio.setText(cliente.getNombre() + "\n");
                btnEjercicio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Aquí puedes manejar el clic, por ejemplo, mostrar un mensaje o abrir una nueva actividad
                        //       Toast.makeText(VEjercicioIndex.this, "Cliente seleccionado: " + cliente.getNombre() + cliente.getEmail() + cliente.getEdad(), Toast.LENGTH_SHORT).show();
                        BotonesRutas.BtnClienteCreate(v.getContext());
                    }
                });

                Button btnEliminar = view.findViewById(R.id.btnCEDesabilitar);  // Asegúrate de tener este botón en tu layout XML
                btnEliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EjercicioController controlador = new EjercicioController(v.getContext());
                        controlador.delete(cliente.getId());
                        listaClientes.remove(position); // Eliminar de la lista local
                        notifyDataSetChanged();  // Notificar al adaptador que los datos han cambiado
                    }
                });

                return view;
            }
        };

        listViewEjercicio.setAdapter(adapter);

        // Clicks Barra Inferior
        buttonCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BotonesRutas.BtnCliente(v.getContext());
            }
        });

        buttonCreateEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BotonesRutas.BtnCreateEjercicio(v.getContext());
            }
        });


        btncreateCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BotonesRutas.BtnCreateCEjercicio(v.getContext());
            }
        });


    }


}

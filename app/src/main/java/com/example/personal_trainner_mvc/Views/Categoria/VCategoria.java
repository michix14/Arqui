package com.example.personal_trainner_mvc.Views.Categoria;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personal_trainner_mvc.Controllers.CategoriaController;
import com.example.personal_trainner_mvc.Models.Categoria.Categoria;
import com.example.primerp_arqui_entrenador_java.R;
import com.example.personal_trainner_mvc.Views.BotonesRutas;

import java.util.List;

public class VCategoria extends AppCompatActivity {


    private ImageButton buttonCliente,buttonEjercicio,buttonComida;

   Button guardar,btnupdateState ;
    private EditText editTextNombre;
    private EditText editTextDescripcion;
    private ListView listViewcategoria;
    private List<Categoria> listacategoria;
  CategoriaController categoriaEjercicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_ejercicio);
        guardar = findViewById(R.id.buttonCreateCEjercicio);
         editTextNombre=findViewById(R.id.EcreateNombreCEjercicio);
         editTextDescripcion=findViewById(R.id.EcreateDescripcionCEjercicio);

         buttonCliente=findViewById(R.id.buttonCliente);
         buttonEjercicio=findViewById(R.id.buttonEjercicio);
         listViewcategoria = findViewById(R.id.listViewCategoriaEjercicio);
        categoriaEjercicio = new CategoriaController(this);
        listacategoria = categoriaEjercicio.FindAll();

        ArrayAdapter<Categoria> adapter = new ArrayAdapter<Categoria>(this, R.layout.activity_item_categoria_ejercicio, R.id.btnCejercicio,listacategoria) {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                Button btnCliente = view.findViewById(R.id.btnCejercicio);
                Button btnUpdateState = view.findViewById(R.id.btnCEDesabilitar);
                final Categoria cliente = listacategoria.get(position);
                btnCliente.setText(   cliente.getNombre()+"\n");

                btnUpdateState.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //categoriaEjercicio.update(cliente.getId());
                        listacategoria.clear();
                        listacategoria.addAll(categoriaEjercicio.FindAll());
                        notifyDataSetChanged();
                    }
                });

                Button btnEliminar = view.findViewById(R.id.btnCEDesabilitar);  // Asegúrate de tener este botón en tu layout XML
                btnEliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CategoriaController controlador = new CategoriaController(v.getContext());
                        controlador.delete(cliente.getId());
                        listacategoria.remove(position); // Eliminar de la lista local
                        notifyDataSetChanged();  // Notificar al adaptador que los datos han cambiado
                    }
                });

                return view;
            }
        };

        listViewcategoria.setAdapter(adapter);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CreateC(adapter);
                   // Toast.makeText(VCategoria.this, "Succes : " + "Categoria guardada con exito", Toast.LENGTH_SHORT).show();
                    /*
                    buttonCliente.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            BotonesRutas.BtnCliente(v.getContext());
                        }
                    });*/
                } catch (Exception e) {
                    Toast.makeText(VCategoria.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace(); // Imprimir el error en el log para depuración
                    Log.e("ErrorGuardarCategoria", "Error al guardar la categoria", e);
                }
            }
        });







        // Clicks Barra Inferior
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
    public void CreateC(ArrayAdapter<Categoria> adapter){

        String nombre=editTextNombre.getText().toString();
        String descripcion=editTextDescripcion.getText().toString();
        if (!nombre.isEmpty()) {
            categoriaEjercicio.create(nombre,descripcion);
            // Actualiza la lista después de crear la nueva categoría
            listacategoria.clear();
            listacategoria.addAll(categoriaEjercicio.FindAll()); // Recarga los datos
            adapter.notifyDataSetChanged(); // Notifica al adaptador que los datos han cambiado

            Toast.makeText(this, "Categoria agregada con exito", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No puede haber campos vacios", Toast.LENGTH_SHORT).show();
        }
    }





}

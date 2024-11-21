package com.example.personal_trainner_mvc.Views.ejercicio;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personal_trainner_mvc.Controllers.CategoriaController;
import com.example.personal_trainner_mvc.Controllers.EjercicioController;
import com.example.personal_trainner_mvc.Models.Categoria.Categoria;
import com.example.primerp_arqui_entrenador_java.R;
import com.example.personal_trainner_mvc.Views.BotonesRutas;

import java.util.List;

public class VEjercicioCreate extends AppCompatActivity {


    private EditText nombre, descripcion, link ;

    private Spinner categoria_id;
    private Button buttonGuardar;
    private EjercicioController ejercicioController;

    private CategoriaController categoriaController;

    private List<Categoria> categoria;

    private ImageButton buttonCliente,buttonEjercicio;

    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_create);


        nombre = findViewById(R.id.editTextCENombre);
        descripcion = findViewById(R.id.editTextCEDescripcion);
        link = findViewById(R.id.editTextCEvideourl);
        categoria_id = findViewById(R.id.editTextCEgrupomuscularid);



        buttonGuardar = findViewById(R.id.buttonGuardar);
        buttonEjercicio = findViewById(R.id.buttonEjercicio);
        buttonCliente= findViewById(R.id.buttonCliente);



        categoriaController = new CategoriaController(VEjercicioCreate.this);
        categoria = categoriaController.FindAll();
        ArrayAdapter<Categoria> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoria);
        //adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoria_id.setAdapter(adaptador);


        // Configuramos el evento del botón
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    CreateC();
                    Toast.makeText(VEjercicioCreate.this, "Error: " + "ejercicio creado con exito", Toast.LENGTH_LONG).show();
                    BotonesRutas.BtnEjercicio(VEjercicioCreate.this);
                } catch (Exception e) {
                    Toast.makeText(VEjercicioCreate.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace(); // Imprimir el error en el log para depuración
                    Log.e("ErrorGuardarEjercicio", "Error al guardar el ejercicio", e);
                }
            }
        });



        /*---------------------------------------------------*/
        //botones
        buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BotonesRutas.BtnEjercicio(v.getContext());
            }
        });

        buttonCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BotonesRutas.BtnCliente(v.getContext());
            }
        });

    }

    /* llamar al controller*/
    public void CreateC(){

        String nombre=this.nombre.getText().toString();
        String descripcion=this.descripcion.getText().toString();
        String link = this.link.getText().toString();
       Categoria categoriaid = (Categoria) categoria_id.getSelectedItem();
        int categoria_id = categoriaid.getId();


        if (categoriaid != null) {
            Log.d("SpinnerSelection", "GrupoMuscular Seleccionado: " + categoriaid.toString()+" :" +categoria_id);
        } else {
            Log.d("SpinnerSelection", "Ningún GrupoMuscular seleccionado.");
        }

        ejercicioController= new EjercicioController(VEjercicioCreate.this);
        ejercicioController.create(nombre, descripcion,link,categoria_id);

    }


}

package com.example.personal_trainner_mvc.Views.cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.personal_trainner_mvc.Controllers.ClienteController;
import com.example.personal_trainner_mvc.Models.Cliente.Cliente;
import com.example.personal_trainner_mvc.Views.BotonesRutas;
import com.example.primerp_arqui_entrenador_java.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    // private UsuarioController usuarioController;
    private ImageButton buttonCliente, buttonEjercicio, buttonComida;

    private ClienteController clienteController;
    private List<Cliente> listaClientes;
    private ListView listViewClientes;
    private Button buttonCreateCliente, btncrearrutinaejercicio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonCliente = findViewById(R.id.buttonCliente);
        buttonEjercicio = findViewById(R.id.buttonEjercicio);
        listViewClientes = findViewById(R.id.listViewClientes);
        clienteController = new ClienteController(this);
        listaClientes = clienteController.FindAll();
        buttonCreateCliente = findViewById(R.id.buttonCrearCliente);


        ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>(this, R.layout.activity_cliente_item, R.id.btnCliente, listaClientes) {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                Button btnCliente = view.findViewById(R.id.btnCliente);
                final Cliente cliente = listaClientes.get(position);
                btnCliente.setText("nombre: " + cliente.getNombre() + "\n" +"celular: " + cliente.getCelular() + "\n" + "edad : " + cliente.getEdad() + " años");
                btnCliente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, VClienteShow.class);
                        intent.putExtra("id", cliente.getId());
                        startActivity(intent);
                    }
                });

                return view;
            }
        };

        listViewClientes.setAdapter(adapter);


        buttonCreateCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BotonesRutas.BtnClienteCreate(v.getContext());
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
}
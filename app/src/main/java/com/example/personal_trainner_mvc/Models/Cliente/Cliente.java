package com.example.personal_trainner_mvc.Models.Cliente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.personal_trainner_mvc.conexion.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String nombre;
    private double peso;
    private double estatura;
    private String direccion;
    private int celular;
    private int edad;


    private static final String TABLE_NAME = "cliente";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_CELULAR = "celular";
    private static final String COLUMN_PESO = "peso";
    private static final String COLUMN_ESTATURA = "estatura";
    private static final String COLUMN_DIRECCION = "direccion";
    private static final String COLUMN_EDAD = "edad";


    private SQLiteDatabase db;

    public Cliente(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }


    public void Validar(String nombre, int celular, double peso, double estatura, int edad, String direccion) throws IllegalArgumentException {
        // Validar que el nombre no sea nulo o vacío
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Campo de nombre vacio");
        }



        // Si todas las validaciones pasan, asignar los valores a los atributos de la clase
        this.nombre = nombre;
        this.celular = celular;
        this.peso = peso;
        this.estatura = estatura;
        this.edad = edad;
        this.direccion = direccion;

    }

    public Cliente(int id, String nombre, double peso, double estatura, String direccion, int celular, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.peso = peso;
        this.estatura = estatura;
        this.direccion = direccion;
        this.celular = celular;
        this.edad = edad;
    }


// Métodos CRUD

    public void create() {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, this.nombre);
        values.put(COLUMN_PESO, this.peso);
        values.put(COLUMN_ESTATURA, this.estatura);
        values.put(COLUMN_CELULAR, this.celular);
        values.put(COLUMN_DIRECCION, this.direccion);
        values.put(COLUMN_EDAD, this.edad);
        db.insert(TABLE_NAME, null, values);
    }

    // Método para buscar un cliente por su ID
    public Cliente FindById(int id) {
        Cursor cursor = db.query(TABLE_NAME, new String[]{
                        COLUMN_ID, COLUMN_NOMBRE, COLUMN_PESO, COLUMN_ESTATURA, COLUMN_CELULAR,COLUMN_DIRECCION,
                        COLUMN_EDAD},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            this.id = cursor.getInt(0);
            this.nombre = cursor.getString(1);
            this.peso = cursor.getDouble(2);
            this.estatura = cursor.getDouble(3);
            this.celular = cursor.getInt(4);
            this.direccion= cursor.getString(5);
            this.edad = cursor.getInt(6);
            cursor.close();
        }
        return this;
    }

    public Cliente() {
    }

    // Método para obtener todos los clientes
    public List<Cliente> FindAll() {
        List<Cliente> clientes = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, new String[]{
                        COLUMN_ID, COLUMN_NOMBRE, COLUMN_PESO, COLUMN_ESTATURA, COLUMN_CELULAR,COLUMN_DIRECCION,
                        COLUMN_EDAD},
                null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Cliente cliente = new Cliente();
                cliente.id = cursor.getInt(0);
                cliente.nombre = cursor.getString(1);
                cliente.peso = cursor.getDouble(2);
                cliente.estatura = cursor.getDouble(3);
                cliente.celular = cursor.getInt(4);
                cliente.direccion = cursor.getString(5);
                cliente.edad = cursor.getInt(6);

                clientes.add(cliente);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return clientes;
    }

    // Procedimiento para actualizacion de datos del cliente
    public void update(int id) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, this.nombre);
        values.put(COLUMN_PESO, this.peso);
        values.put(COLUMN_ESTATURA, this.estatura);
        values.put(COLUMN_CELULAR, this.celular);
        values.put(COLUMN_DIRECCION, this.direccion);
        values.put(COLUMN_EDAD, this.edad);
        db.update(TABLE_NAME, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }

    // Método para eliminar un cliente
    public void delete(int id) {

        db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }
}
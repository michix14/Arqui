package com.example.personal_trainner_mvc.Models.Categoria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.personal_trainner_mvc.conexion.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class Categoria {


    @Override
    public String toString() {
        return nombre ;
    }

    private  int id;

    private String nombre;
    private String descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    private static final String TABLE_NAME = "categoria";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_DESCRIPCION = "descripcion";


    private SQLiteDatabase db;

    public Categoria(Context context)
    {
        DBHelper dbHelper = new DBHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }

    public Categoria() {

    }

    public void Validar(String nombre, String descripcion) throws IllegalArgumentException {
        // Validar que el nombre no sea nulo o vacío
        if (nombre == null || nombre.trim().isEmpty()) {throw new IllegalArgumentException("El nombre no puede estar vacío.");}
        if (descripcion == null || descripcion.trim().isEmpty()) {throw new IllegalArgumentException("La descripcion no puede estar vacía.");}
        this.nombre = nombre;
        this.descripcion = descripcion;

    }


    public void create() {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, this.nombre);
        values.put(COLUMN_DESCRIPCION, this.descripcion);
        db.insert(TABLE_NAME, null, values);
    }

    // Método para buscar un ejercicio por su ID
    public Categoria FindById(int id) {
        Cursor cursor = db.query(TABLE_NAME, new String[]{
                        COLUMN_ID, COLUMN_NOMBRE,COLUMN_DESCRIPCION},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            this.id = cursor.getInt(0);
            this.nombre = cursor.getString(1);
            this.descripcion= cursor.getString(2);
            cursor.close();
        }
        return this;
    }

    // Método para obtener todos los ejercicios
    public List<Categoria> FindAll() {
        List<Categoria> categorias = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, new String[]{
                        COLUMN_ID, COLUMN_NOMBRE,COLUMN_DESCRIPCION },
                null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Categoria categoria = new Categoria();
                categoria.id = cursor.getInt(0);
                categoria.nombre = cursor.getString(1);
                categoria.descripcion = cursor.getString(2);
                categorias.add(categoria);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return categorias;
    }

    // Método para actualizar un ejercicio
    public void update() {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, this.nombre);
        values.put(COLUMN_DESCRIPCION, this.descripcion);
        db.update(TABLE_NAME, values, COLUMN_ID + "=?", new String[]{String.valueOf(this.id)});
    }

    // Método para eliminar un ejercicio
    public void delete(int id) {
        SQLiteDatabase db = this.db;
        String whereClause = COLUMN_ID + "=?";
        String[] whereArgs = {String.valueOf(id)};
        db.delete(TABLE_NAME, whereClause, whereArgs);
    }
}

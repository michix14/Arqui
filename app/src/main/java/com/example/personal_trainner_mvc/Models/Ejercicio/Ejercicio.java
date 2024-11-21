package com.example.personal_trainner_mvc.Models.Ejercicio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.personal_trainner_mvc.conexion.DBHelper;

import java.util.ArrayList;
import java.util.List;


public class Ejercicio {

    @Override
    public String toString() {
        return nombre;
    }

    private  int id;
    private String nombre ;
    private String descripcion;
    private String link;
    private int categoria_id;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    private static final String TABLE_NAME = "ejercicio";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_DESCRIPCION = "descripcion";
    private static final String COLUMN_LINK = "link";
    private static final String COLUMN_CATEGORIA_ID = "categoria_id";
    private SQLiteDatabase db;

    public Ejercicio(Context context)
    {

        DBHelper dbHelper = new DBHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }

    public Ejercicio() {

    }

    // Métodos CRUD

    // Método para crear un nuevo ejercicio


    public void Validar(String nombre, String descripcion, String link, int categoria_id) throws IllegalArgumentException {
        // Validar que el nombre no sea nulo o vacío
        if (nombre == null || nombre.trim().isEmpty()) {throw new IllegalArgumentException("El nombre no puede estar vacío.");}

        // Validar que la descripción no sea nula o vacía
        if (descripcion == null || descripcion.trim().isEmpty()) {throw new IllegalArgumentException("La descripción no puede estar vacía.");}


        if (link == null || link.trim().isEmpty()) {
            throw new IllegalArgumentException("El URL del video o imagen no puede estar vacío.");
        }

        // Validar que el tipo de comida sea válido (por ejemplo, un valor positivo)
        if (categoria_id <= 0) {
            throw new IllegalArgumentException("La categoria debe ser un valor positivo.");
        }
        // Si todas las validaciones pasan, asignar los valores a los atributos de la clase
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.link = link;
        this.categoria_id = categoria_id;

    }


    public void create() {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, this.nombre);
        values.put(COLUMN_DESCRIPCION, this.descripcion);
        values.put(COLUMN_LINK,this.link);
        values.put(COLUMN_CATEGORIA_ID,this.categoria_id);
        db.insert(TABLE_NAME, null, values);
    }

    // Método para buscar un ejercicio por su ID
    public Ejercicio FindById(int id) {
        Cursor cursor = db.query(TABLE_NAME, new String[]{
                        COLUMN_ID, COLUMN_NOMBRE, COLUMN_DESCRIPCION, COLUMN_LINK,COLUMN_CATEGORIA_ID, },
                COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            this.id = cursor.getInt(0);
            this.nombre = cursor.getString(1);
            this.descripcion = cursor.getString(2);
            this.link = cursor.getString(3);
            this.categoria_id = cursor.getInt(4);
            cursor.close();
        }
        return this;
    }

    // Método para obtener todos los ejercicios
    public List<Ejercicio> FindAll() {
        List<Ejercicio> ejercicios = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, new String[]{
                        COLUMN_ID, COLUMN_NOMBRE, COLUMN_DESCRIPCION, COLUMN_LINK, COLUMN_CATEGORIA_ID,},
                null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Ejercicio ejercicio = new Ejercicio();
                ejercicio.id = cursor.getInt(0);
                ejercicio.nombre = cursor.getString(1);
                ejercicio.descripcion = cursor.getString(2);
                ejercicio.link = cursor.getString(3);
                ejercicio.categoria_id = cursor.getInt(4);
                ejercicios.add(ejercicio);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return ejercicios;
    }

    // Método para actualizar un ejercicio
    public void update() {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, this.nombre);
        values.put(COLUMN_DESCRIPCION, this.descripcion);
        values.put(COLUMN_LINK, this.link);
        values.put (COLUMN_CATEGORIA_ID, this.categoria_id);

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

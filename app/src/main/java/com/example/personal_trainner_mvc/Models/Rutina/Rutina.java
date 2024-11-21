package com.example.personal_trainner_mvc.Models.Rutina;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.personal_trainner_mvc.conexion.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class Rutina {
    private int id;
    private String formato;
    private int suscripcion_id;

    @Override
    public String toString() {
        return  "" + id ;
    }

    private static final String TABLE_NAME = "Rutina";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FORMATO = "formato";
    private static final String COLUMN_SUSCRIPCION_ID = "suscripcion_id";

    private SQLiteDatabase db;

    // Constructor con contexto para inicializar la base de datos
    public Rutina(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }

    // Constructor vacío
    public Rutina() {}

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public int getSuscripcion_id() {
        return suscripcion_id;
    }

    public void setSuscripcion_id(int suscripcion_id) {
        this.suscripcion_id = suscripcion_id;
    }

    // Método para validar los datos antes de guardarlos
    public void validar(String formato, int suscripcion_id) throws IllegalArgumentException {
        if (formato == null || formato.trim().isEmpty()) {
            throw new IllegalArgumentException("El formato no puede estar vacío.");
        }

        if (suscripcion_id <= 0) {
            throw new IllegalArgumentException("El ID de suscripción no es válido.");
        }

        this.formato = formato;
        this.suscripcion_id = suscripcion_id;
    }

    // Método para crear una nueva rutina
    public Rutina create() {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FORMATO, this.formato);
        values.put(COLUMN_SUSCRIPCION_ID, this.suscripcion_id);

        long newRowId = db.insert(TABLE_NAME, null, values);
        if (newRowId != -1) {
            Cursor cursor = db.query(TABLE_NAME, null, "id = ?", new String[]{String.valueOf(newRowId)}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                Rutina rutina = new Rutina();
                rutina.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                rutina.setFormato(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FORMATO)));
                rutina.setSuscripcion_id(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SUSCRIPCION_ID)));
                cursor.close();
                return rutina;
            }
        }
        return null;
    }

    // Método para buscar una rutina por su ID
    public Rutina findById(int id) {
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_FORMATO, COLUMN_SUSCRIPCION_ID},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            this.id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            this.formato = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FORMATO));
            this.suscripcion_id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SUSCRIPCION_ID));
            cursor.close();
        }
        return this;
    }

    // Método para obtener todas las rutinas
    public List<Rutina> findAll() {
        List<Rutina> rutinas = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_FORMATO, COLUMN_SUSCRIPCION_ID},
                null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Rutina rutina = new Rutina();
                rutina.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                rutina.setFormato(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FORMATO)));
                rutina.setSuscripcion_id(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SUSCRIPCION_ID)));
                rutinas.add(rutina);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return rutinas;
    }

    // Método para actualizar una rutina
    public void update() {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FORMATO, this.formato);
        values.put(COLUMN_SUSCRIPCION_ID, this.suscripcion_id);

        db.update(TABLE_NAME, values, COLUMN_ID + "=?", new String[]{String.valueOf(this.id)});
    }

    // Método para eliminar una rutina
    public void delete(int id) {
        db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }
}

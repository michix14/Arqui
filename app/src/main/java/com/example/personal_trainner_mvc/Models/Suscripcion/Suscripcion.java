package com.example.personal_trainner_mvc.Models.Suscripcion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.personal_trainner_mvc.conexion.DBHelper;
import com.example.personal_trainner_mvc.estrategy.StrategySuscripcion;

import java.util.ArrayList;
import java.util.List;

public class Suscripcion {

    private int id;
    private int monto;
    private String duracion;
    private int cliente_id;

    @Override
    public String toString() {
        return ""+cliente_id;
    }

    private static final String TABLE_NAME = "Suscripcion";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_MONTO = "monto";
    private static final String COLUMN_DURACION = "duracion";
    private static final String COLUMN_CLIENTE_ID = "cliente_id";

    private SQLiteDatabase db;

    //Referencia a estrategia
    private StrategySuscripcion strategySuscripcion;
    public void setStrategySuscripcion(StrategySuscripcion strategySuscripcion) {
        this.strategySuscripcion = strategySuscripcion;
    }

    // Aplicar la estrategia para calcular monto y duración
    public void aplicarEstrategia() {
        if (strategySuscripcion == null) {
            throw new IllegalStateException("No se ha configurado una estrategia.");
        }
        strategySuscripcion .aplicar(this);
    }
    // Constructor
    public Suscripcion(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }

    public Suscripcion() {
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public int getClienteId() {
        return cliente_id;
    }

    public void setClienteId(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    // Métodos CRUD

    // Crear una nueva suscripción
    public void create() {
        ContentValues values = new ContentValues();
        Log.d("cadena", this.duracion);
        values.put(COLUMN_MONTO, this.monto);
        values.put(COLUMN_DURACION, this.duracion);
        values.put(COLUMN_CLIENTE_ID, this.cliente_id);
        db.insert(TABLE_NAME, null, values);
    }

    // Buscar suscripción por ID
    public Suscripcion findById(int id) {
        Cursor cursor = db.query(TABLE_NAME, new String[]{
                        COLUMN_ID, COLUMN_MONTO, COLUMN_DURACION, COLUMN_CLIENTE_ID},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            this.id = cursor.getInt(0);
            this.monto = cursor.getInt(1);
            this.duracion = cursor.getString(2);
            this.cliente_id = cursor.getInt(3);
            cursor.close();
        }
        return this;
    }

    // Obtener todas las suscripciones
    public List<Suscripcion> findAll() {
        List<Suscripcion> suscripciones = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, new String[]{
                        COLUMN_ID, COLUMN_MONTO, COLUMN_DURACION, COLUMN_CLIENTE_ID},
                null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Suscripcion suscripcion = new Suscripcion();
                suscripcion.id = cursor.getInt(0);
                suscripcion.monto = cursor.getInt(1);
                suscripcion.duracion = cursor.getString(2);
                suscripcion.cliente_id = cursor.getInt(3);
                suscripciones.add(suscripcion);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return suscripciones;
    }

    // Actualizar una suscripción
    public void update() {
        ContentValues values = new ContentValues();
        values.put(COLUMN_MONTO, this.monto);
        values.put(COLUMN_DURACION, this.duracion);
        values.put(COLUMN_CLIENTE_ID, this.cliente_id);
        db.update(TABLE_NAME, values, COLUMN_ID + "=?", new String[]{String.valueOf(this.id)});
    }

    // Eliminar una suscripción
    public void delete(int id) {
        db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }
}

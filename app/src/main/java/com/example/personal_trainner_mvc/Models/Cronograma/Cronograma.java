package com.example.personal_trainner_mvc.Models.Cronograma;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.personal_trainner_mvc.Models.Ejercicio.Ejercicio;
import com.example.personal_trainner_mvc.conexion.DBHelper;
import com.itextpdf.commons.actions.contexts.IContext;

import java.util.ArrayList;
import java.util.List;

public class Cronograma {

    private int rutinaId;
    private int ejercicioId;
    private String dia;
    private int repeticiones;
    private int series;

    private static final String TABLE_NAME = "Cronograma";
    private static final String COLUMN_RUTINA_ID = "rutina_id";
    private static final String COLUMN_EJERCICIO_ID = "ejercicio_id";
    private static final String COLUMN_DIA = "dia";
    private static final String COLUMN_REPETICIONES = "repeticiones";
    private static final String COLUMN_SERIES = "series";

    private SQLiteDatabase db;

    // Constructor con contexto para inicializar la base de datos
    public Cronograma(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }

    // Constructor vacío
    public Cronograma() {
    }

    // Getters y Setters
    public int getRutinaId() {
        return rutinaId;
    }

    public void setRutinaId(int rutinaId) {
        this.rutinaId = rutinaId;
    }

    public int getEjercicioId() {
        return ejercicioId;
    }

    public void setEjercicioId(int ejercicioId) {
        this.ejercicioId = ejercicioId;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public String toString() {

        return
                "rutina:" + rutinaId +
                ", ejercicio: " + ejercicioId +
                ", dia: '" + dia + '\'' +
                ", repeticiones: " + repeticiones +
                ", series: " + series;
    }

    // Validar los datos antes de crear o actualizar un cronograma
    public void Validar(int rutinaId, int ejercicioId, String dia, int repeticiones, int series) throws IllegalArgumentException {
        if (rutinaId <= 0) throw new IllegalArgumentException("El ID de la rutina debe ser un valor positivo.");
        if (ejercicioId <= 0) throw new IllegalArgumentException("El ID del ejercicio debe ser un valor positivo.");
        if (dia == null || dia.trim().isEmpty()) throw new IllegalArgumentException("El día no puede estar vacío.");
        if (repeticiones <= 0) throw new IllegalArgumentException("Las repeticiones deben ser un valor positivo.");
        if (series <= 0) throw new IllegalArgumentException("Las series deben ser un valor positivo.");

        this.rutinaId = rutinaId;
        this.ejercicioId = ejercicioId;
        this.dia = dia;
        this.repeticiones = repeticiones;
        this.series = series;
    }

    // Método para crear un nuevo cronograma
    public void create() {
        ContentValues values = new ContentValues();
        values.put(COLUMN_RUTINA_ID, this.rutinaId);
        values.put(COLUMN_EJERCICIO_ID, this.ejercicioId);
        values.put(COLUMN_DIA, this.dia);
        values.put(COLUMN_REPETICIONES, this.repeticiones);
        values.put(COLUMN_SERIES, this.series);
        db.insert(TABLE_NAME, null, values);
    }

    // Método para buscar un cronograma por IDs de rutina y ejercicio
    public List<Cronograma> findByRutinaId(int rutinaId) {
        List<Cronograma> cronogramas = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, new String[]{
                        COLUMN_RUTINA_ID, COLUMN_EJERCICIO_ID, COLUMN_DIA, COLUMN_REPETICIONES, COLUMN_SERIES},
                COLUMN_RUTINA_ID + "=?",
                new String[]{String.valueOf(rutinaId)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Cronograma cronograma = new Cronograma();
                cronograma.rutinaId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_RUTINA_ID));
                cronograma.ejercicioId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EJERCICIO_ID));
                cronograma.dia = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DIA));
                cronograma.repeticiones = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_REPETICIONES));
                cronograma.series = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SERIES));
                cronogramas.add(cronograma);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return cronogramas;
    }

    // Método para obtener todos los cronogramas
    public List<Cronograma> FindAll() {
        List<Cronograma> cronogramas = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, new String[]{
                        COLUMN_RUTINA_ID, COLUMN_EJERCICIO_ID, COLUMN_DIA, COLUMN_REPETICIONES, COLUMN_SERIES},
                null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Cronograma cronograma = new Cronograma();
                cronograma.rutinaId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_RUTINA_ID));
                cronograma.ejercicioId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EJERCICIO_ID));
                cronograma.dia = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DIA));
                cronograma.repeticiones = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_REPETICIONES));
                cronograma.series = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SERIES));
                cronogramas.add(cronograma);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return cronogramas;
    }

    // Método para actualizar un cronograma
    public void update() {
        ContentValues values = new ContentValues();
        values.put(COLUMN_DIA, this.dia);
        values.put(COLUMN_REPETICIONES, this.repeticiones);
        values.put(COLUMN_SERIES, this.series);

        db.update(TABLE_NAME, values,
                COLUMN_RUTINA_ID + "=? AND " + COLUMN_EJERCICIO_ID + "=?",
                new String[]{String.valueOf(this.rutinaId), String.valueOf(this.ejercicioId)});
    }

    // Método para eliminar un cronograma
    public void delete(int rutinaId, int ejercicioId) {
        db.delete(TABLE_NAME,
                COLUMN_RUTINA_ID + "=? AND " + COLUMN_EJERCICIO_ID + "=?",
                new String[]{String.valueOf(rutinaId), String.valueOf(ejercicioId)});
    }
}

package com.example.personal_trainner_mvc.conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "personal_trainner.db";
    private static final int DATABASE_VERSION = 4;

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        // Habilitar claves foráneas
        db.execSQL("PRAGMA foreign_keys = ON;");
    }


    private static final String CREATE_TABLE_CLIENTE =
            "CREATE TABLE Cliente (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT NOT NULL," +
                    "peso REAL," +
                    "estatura REAL," +
                    "celular INTEGER," +
                    "direccion TEXT," +
                    "edad INTEGER" +
                    ");";

    private static final String CREATE_TABLE_SUSCRIPCION =
            "CREATE TABLE Suscripcion (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Monto INTEGER," +
                    "Duracion TEXT NOT NULL," +
                    "cliente_id INTEGER," +
                    "FOREIGN KEY (cliente_id) REFERENCES Cliente(id) " +
                    "ON UPDATE CASCADE ON DELETE CASCADE" +
                    ");";

    private static final String CREATE_TABLE_RUTINA =
            "CREATE TABLE Rutina (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "formato TEXT NOT NULL," +
                    "suscripcion_id INTEGER," +
                    "FOREIGN KEY (suscripcion_id) REFERENCES Suscripcion(id) " +
                    "ON UPDATE CASCADE ON DELETE CASCADE" +
                    ");";

    private static final String CREATE_TABLE_EJERCICIO =
            "CREATE TABLE Ejercicio (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT NOT NULL," +
                    "descripcion TEXT," +
                    "link TEXT," +
                    "categoria_id INTEGER," +
                    "FOREIGN KEY (categoria_id) REFERENCES Categoria(id) " +
                    "ON UPDATE CASCADE ON DELETE SET NULL" +
                    ");";

    private static final String CREATE_TABLE_CATEGORIA =
            "CREATE TABLE Categoria (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT NOT NULL," +
                    "descripcion TEXT" +
                    ");";

    private static final String CREATE_TABLE_CRONOGRAMA =
            "CREATE TABLE Cronograma (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "rutina_id INTEGER," +
                    "ejercicio_id INTEGER," +
                    "dia TEXT," +
                    "repeticiones INTEGER," +
                    "series INTEGER," +
                    "FOREIGN KEY (rutina_id) REFERENCES Rutina(id) " +
                    "ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (ejercicio_id) REFERENCES Ejercicio(id) " +
                    "ON UPDATE CASCADE ON DELETE CASCADE" +
                    ");";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CLIENTE);
        db.execSQL(CREATE_TABLE_SUSCRIPCION);
        db.execSQL(CREATE_TABLE_RUTINA);
        db.execSQL(CREATE_TABLE_EJERCICIO);
        db.execSQL(CREATE_TABLE_CATEGORIA);
        db.execSQL(CREATE_TABLE_CRONOGRAMA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Cronograma");
        db.execSQL("DROP TABLE IF EXISTS Ejercicio");
        db.execSQL("DROP TABLE IF EXISTS Categoria");
        db.execSQL("DROP TABLE IF EXISTS Rutina");
        db.execSQL("DROP TABLE IF EXISTS Suscripcion");
        db.execSQL("DROP TABLE IF EXISTS Cliente");
        onCreate(db);
    }
    }



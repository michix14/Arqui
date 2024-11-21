package com.example.personal_trainner_mvc.Views.rutina;


import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.personal_trainner_mvc.Controllers.EjercicioController;
import com.example.personal_trainner_mvc.Models.Ejercicio.Ejercicio;


import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class GenerarPdf {

    private Context context;

    private Ejercicio ejercicio;
    private EjercicioController ejerciciocon;

    public GenerarPdf(Context context) {
        this.context = context;
    }

   /* public void generarPdf(DRutinaEjercicio rutina, List<DDetalleRutinaEjercicio> detalles, EjercicioController ejercicio) {
        if (checkPermission()) {
            PdfDocument pdfDocument = new PdfDocument();
            Paint paint = new Paint();

            // Título y descripción
            String tituloText = "Rutina de Ejercicio: " + rutina.getDescripcion();
            String descripcionText = "Duración: " + rutina.getDuracion() + " minutos\n" +
                    "Fecha: " + rutina.getFecha() + "\n";

            PdfDocument.PageInfo paginaInfo = new PdfDocument.PageInfo.Builder(816, 1054, 1).create();
            PdfDocument.Page pagina1 = pdfDocument.startPage(paginaInfo);
            Canvas canvas = pagina1.getCanvas();

            // Dibujar título
            paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            paint.setTextSize(20);
            canvas.drawText(tituloText, 10, 150, paint);

            // Dibujar descripción
            paint.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            paint.setTextSize(14);
            String[] arrDescripcion = descripcionText.split("\n");
            int y = 200;
            for (String line : arrDescripcion) {
                canvas.drawText(line, 10, y, paint);
                y += 15;
            }

            // Agregar detalles
            canvas.drawText("Detalles de la Rutina:", 10, y, paint);
            y += 20;

            for (DDetalleRutinaEjercicio detalle : detalles) {
               Ejercicio ejercicioblucle=ejercicio .FindById(detalle.getEjercicioid());
                String detalleText = "Ejercicio nombre: " + ejercicioblucle.getNombre() + ", " +
                        "\n"+ "Ejercicio Descripcion :"+ ejercicioblucle.getDescripcion() +  " \n"+
                        "\n"+ "Ejercicio Video url :"+ ejercicioblucle.getLink() +  " \n"+
                        "------------------------------";
                // Dividir el texto en líneas
                String[] lines = detalleText.split("\n");
                for (String line : lines) {
                    canvas.drawText(line, 10, y, paint);
                    y += 15; // Mover hacia abajo para la siguiente línea
                }

            }

            pdfDocument.finishPage(pagina1);

            // Guardar PDF usando MediaStore
            try {
                String fileName = "Rutina_" + System.currentTimeMillis() + ".pdf";

                // Crear ContentValues
                ContentValues values = new ContentValues();
                values.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
                values.put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf");
                values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS);

                // Insertar el archivo en MediaStore
                Uri uri = context.getContentResolver().insert(MediaStore.Files.getContentUri("external"), values);

                if (uri != null) {
                    try (OutputStream outputStream = context.getContentResolver().openOutputStream(uri)) {
                        pdfDocument.writeTo(outputStream);
                        Toast.makeText(context, "Se creó el PDF correctamente", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                pdfDocument.close();
            }
        } else {
            requestPermissions();
        }
    }
    private boolean checkPermission() {
        int permission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return permission == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
    }

    public void handlePermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 200) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "Permiso concedido", Toast.LENGTH_LONG).show();
                //generarPdf(); // Llamar a generarPdf nuevamente si es necesario
            } else {
                Toast.makeText(context, "Permiso denegado", Toast.LENGTH_LONG).show();
            }
        }
    }*/
}

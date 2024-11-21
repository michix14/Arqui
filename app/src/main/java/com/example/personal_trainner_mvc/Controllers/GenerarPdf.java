
package com.example.personal_trainner_mvc.Controllers;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.example.personal_trainner_mvc.Controllers.EjercicioController;

import com.example.personal_trainner_mvc.Models.Ejercicio.Ejercicio;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.layout.Document;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.File;
import java.util.List;

    public class GenerarPdf {}

       // private Context context;

        //public GenerarPdf(Context context) {
          //  this.context = context;
        //}

       /* public void generarPdfYCompartir(DRutinaEjercicio rutina, List<DDetalleRutinaEjercicio> detalles, EjercicioController ejercicioController) {
            String fileName = "Rutina_" + System.currentTimeMillis() + ".pdf";
            File filePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), fileName);

            try {
                PdfWriter writer = new PdfWriter(filePath.getAbsolutePath());
                PdfDocument pdfDoc = new PdfDocument(writer);
                Document document = new Document(pdfDoc);

                // Agregar logo (Ajustar según tu logo)
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.user2); // Reemplaza 'tu_logo' por el nombre de tu logo en drawable
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] logoBytes = stream.toByteArray();
                ImageData logoData = ImageDataFactory.create(logoBytes);
                Image logo = new Image(logoData).scaleToFit(50, 50);
                document.add(logo);

                // Agregar título principal
               document.add(new Paragraph("Rutina diaria".toUpperCase())
                        .setFontSize(18).setBold().setTextAlignment(TextAlignment.CENTER));
                //document.add(new Paragraph("Nombre: ______________________________").setFontSize(12));
                document.add(new Paragraph("Fecha de la rutina "+ rutina.getFecha()  )
                        .setFontSize(12));

                // Agregar Fases

                document.add(new Paragraph(""+rutina.getDescripcion())
                        .setFontSize(14).setBold().setFontColor(ColorConstants.BLUE));
                document.add(new Paragraph("Duracion  aproximada : "+rutina.getDuracion() + " minutos")
                        .setFontSize(14).setBold().setFontColor(ColorConstants.RED));

                // Iterar sobre los días y agregar detalles de la rutina
                for (int dia = 1; dia <= detalles.size(); dia++) {
                    DDetalleRutinaEjercicio detalle = detalles.get(dia - 1);
                    Ejercicio ejercicio = ejercicioController.FindById(detalle.getEjercicioid());

                    // Título del día
                    document.add(new Paragraph("Ejercicio " + dia + ": " + ejercicio.getNombre())
                            .setFontSize(14).setBold().setFontColor(ColorConstants.BLUE));

                    // Detalles del ejercicio
                    document.add(new Paragraph("Descripción: " + ejercicio.getDescripcion()).setFontSize(12));
                    document.add(new Paragraph("Series: " + detalle.getSeries() + " x Repeticiones: " + detalle.getRepeticiones())
                            .setFontSize(12));

                    // Añadir video e imagen como enlaces (opcional)
                    if (ejercicio.getLink() != null) {
                        Text videoUrl = new Text("Video: ")
                                .setFontColor(ColorConstants.BLUE).setUnderline()
                                .setAction(PdfAction.createURI(ejercicio.getLink()));
                        document.add(new Paragraph().add(videoUrl));
                    }

                    document.add(new Paragraph("------------------------------"));
                }

                // Cerrar el documento
                document.close();
                pdfDoc.close();
                writer.close();

                // Mostrar mensaje de confirmación
                Toast.makeText(context, "PDF creado correctamente en: " + filePath.getAbsolutePath(), Toast.LENGTH_LONG).show();

                 Llamar a la función para compartir el PDF
                compartirPdf(filePath);

            } catch (Exception e) {
                Log.e("erroralcrear", "crearPdf: ",e );
                e.printStackTrace();
            }
        }


        Método para compartir el PDF
        private void compartirPdf(File pdfFile) {
            try {
                Uri pdfUri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", pdfFile);

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("application/pdf");
                shareIntent.putExtra(Intent.EXTRA_STREAM, pdfUri);
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                 Abrir el selector de apps para compartir
                context.startActivity(Intent.createChooser(shareIntent, "Compartir PDF usando"));
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("erroralcompartir", "compartirPdf: ",e );
                Toast.makeText(context, "Error al intentar compartir el PDF", Toast.LENGTH_LONG).show();
            }
        } */
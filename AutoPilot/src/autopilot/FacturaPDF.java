/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autopilot;

/**
 *
 * @author María Cajas 
 */

import java.awt.Color;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;
public class FacturaPDF {

    public static void generar(Factura factura, File archivo)
        throws Exception {

    Document documento = new Document(
            PageSize.A4, 40, 40, 40, 40
    );

    try (FileOutputStream salida =
                 new FileOutputStream(archivo)) {

        PdfWriter.getInstance(documento, salida);
        documento.open();

        try {
            Font titulo = new Font(
                    Font.HELVETICA, 22, Font.BOLD,
                    new Color(0, 51, 153)
            );

            Font normal = new Font(
                    Font.HELVETICA, 12, Font.NORMAL
            );

            Font encabezadoTabla = new Font(
                    Font.HELVETICA, 11, Font.BOLD,
                    Color.WHITE
            );

            Font totalNegrita = new Font(
                    Font.HELVETICA, 16, Font.BOLD,
                    new Color(0, 51, 153)
            );

            Paragraph nombreAgencia =
                    new Paragraph("AUTOPILOT", titulo);
            documento.add(nombreAgencia);

            Paragraph textoFactura =
                    new Paragraph("FACTURA", titulo);
            textoFactura.setAlignment(Element.ALIGN_CENTER);
            documento.add(textoFactura);

            documento.add(new Paragraph(
                    "Número: " + factura.getNumero(), normal
            ));
            documento.add(new Paragraph(
                    "Fecha: " + factura.getFecha(), normal
            ));
            documento.add(new Paragraph(
                    "Cliente: " + factura.getCliente(), normal
            ));
            documento.add(new Paragraph(
                    "Usuario: " + factura.getUsuario(), normal
            ));
            documento.add(new Paragraph("Estado: PAGADA", normal));
            documento.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{4, 1.2f, 2, 2});

            agregarCelda(tabla, "Descripción", encabezadoTabla, true);
            agregarCelda(tabla, "Cantidad", encabezadoTabla, true);
            agregarCelda(
                    tabla, "Precio unitario", encabezadoTabla, true
            );
            agregarCelda(tabla, "Importe", encabezadoTabla, true);

            agregarCelda(
                    tabla, factura.getDescripcion(), normal, false
            );
            agregarCelda(
                    tabla,
                    String.valueOf(factura.getCantidad()),
                    normal,
                    false
            );
            agregarCelda(
                    tabla,
                    formatoDinero(factura.getPrecioUnitario()),
                    normal,
                    false
            );
            agregarCelda(
                    tabla,
                    formatoDinero(factura.getTotal()),
                    normal,
                    false
            );

            documento.add(tabla);
            documento.add(new Paragraph(" "));

            Paragraph total = new Paragraph(
                    "TOTAL PAGADO: "
                            + formatoDinero(factura.getTotal()),
                    totalNegrita
            );
            total.setAlignment(Element.ALIGN_RIGHT);
            documento.add(total);

            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(
                    "Gracias por su compra.", normal
            ));

        } finally {
            if (documento.isOpen()) {
                documento.close();
            }
        }
    }
 }
    private static void agregarCelda(
        PdfPTable tabla,
        String texto,
        Font fuente,
        boolean encabezado) {

    PdfPCell celda = new PdfPCell(
            new com.lowagie.text.Phrase(texto, fuente)
    );

    celda.setPadding(8);

    if (encabezado) {
        celda.setBackgroundColor(new Color(0, 51, 153));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    }

    tabla.addCell(celda);
}

    private static String formatoDinero(double cantidad) {
        return String.format(Locale.US, "Q %,.2f", cantidad);
    }
}
    


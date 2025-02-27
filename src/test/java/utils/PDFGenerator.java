package utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PDFGenerator {



    public static void pdfGenerator(String header, String fileName){

        Document document = new Document();
        String pdf_path = fileName;
        String pdf_title = header;
        List<String> headers = new ArrayList<String>();
        headers.add("Header");


        try{

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf_path));

            document.open();

            document.add(new Paragraph(pdf_title));

            PdfPTable table = new PdfPTable(5);

            table.setWidthPercentage(110);
            table.setSpacingBefore(12);
            table.setSpacingAfter(12);
            float [] colWidth = {2,2,2,2,2};
            table.setWidths(colWidth);

            for (int i=0; i<headers.size();i++){
                PdfPCell cellHeader = new PdfPCell(new Paragraph("    "+headers.get(i)));
                table.addCell(cellHeader);

            }



            document.add(table);

            document.close();

            writer.close();

        }

        catch(Exception e){
            e.printStackTrace();
        }


    }







    public static void pdfGeneratorRowsAndCellsWithList(String header, List <String> list, String fileName, List<String> headers){

        Document document = new Document();
        String pdf_path = fileName;
        String pdf_title = header;
        String logo_path = "src/test/resources/sammple.jpg";




        try{

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf_path));

            document.open();

            document.add(new Paragraph("                                     "+pdf_title));

            PdfPTable table = new PdfPTable(headers.size());
            table.setWidthPercentage(110);
            table.setSpacingBefore(25);
            table.setSpacingAfter(25);
            float [] colWidth = {2,2,2,2,2};
            table.setWidths(colWidth);



            for(int i=0;i<headers.size();i++) {

                PdfPCell cell1 = new PdfPCell(new Phrase(headers.get(i)));
                table.addCell(cell1);

            }

            table.setHeaderRows(list.size());

            for(int i=0;i<list.size();i++ ) {



            }
            document.add(table);

            document.add(Image.getInstance(logo_path));
            document.close();

            writer.close();

        }

        catch(Exception e){
            e.printStackTrace();
        }


    }


    public static String getPdfContent(String url) throws  IOException {

        URL pdfURL=new URL(url);

        InputStream is=pdfURL.openStream();

        BufferedInputStream bis=new BufferedInputStream(is);

        PDDocument doc=PDDocument.load(bis);

        int pages=doc.getNumberOfPages();

      

        PDFTextStripper strip=new PDFTextStripper();

        strip.setStartPage(1);

         strip.setEndPage(2);

         String stripText=strip.getText(doc);

        System.out.println("stripText = " + stripText);

  doc.close();

 return stripText;

}




}
package com.mycompany.reporting;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.mycompany.model.Country;
import jakarta.servlet.http.HttpServletResponse;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FilesExporter  {
    
    public void setResponseHeader(HttpServletResponse response, String contentType,
           String extension, String prefix) throws IOException{
            
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timeStamp = dateFormat.format(new Date());
        String fileName = prefix + timeStamp + extension;

        response.setContentType(contentType);

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + fileName;
        response.setHeader(headerKey,headerValue);
    }

    public  void exportToCSV(List<Country> listCountries, HttpServletResponse response) throws IOException{
        setResponseHeader(response, "text/csv", ".csv","Country Master_");

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        String [] csvHeader = {"ID", "Country"};
        String [] fieldMapping = {"id","name"};

        csvWriter.writeHeader(csvHeader);
        for (Country country: listCountries){
            csvWriter.write(country, fieldMapping);
        }
        csvWriter.close();
    }

    public void exportToPDF(List<Country> listCountries, HttpServletResponse response) throws DocumentException, IOException{
        setResponseHeader(response, "application/pdf", ".pdf","Country Master_");

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLACK);
        Paragraph para = new Paragraph("Country Master List", font);
        para.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(para);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(18);

        writeCountryHeader(table);
        writeCountryData(table,listCountries);

        document.add(table);
        document.close();
    }

    private void writeCountryData(PdfPTable table, List<Country> listCountries) {
        for (Country country: listCountries){
            table.addCell(String.valueOf(country.getId()));
            table.addCell(country.getName());
        }
    }

    private void writeCountryHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.DARK_GRAY);
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Country", font));
        table.addCell(cell);

    }
}

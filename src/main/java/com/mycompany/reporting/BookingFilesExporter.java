package com.mycompany.reporting;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.mycompany.model.Booking;
import jakarta.servlet.http.HttpServletResponse;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingFilesExporter {

    public void setResponseHeader(HttpServletResponse response, String contentType,
                                  String extension, String prefix) throws IOException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timeStamp = dateFormat.format(new Date());
        String fileName = prefix + timeStamp + extension;

        response.setContentType(contentType);

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + fileName;
        response.setHeader(headerKey, headerValue);
    }


    public void exportToCSV(List<Booking> listAppointments, HttpServletResponse response) throws IOException {
        setResponseHeader(response, "text/csv", ".csv", "Appointment List_");

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"ID", "Date", "Time", "Country Name", "Job Type"};
        String[] fieldMapping = {"id", "date", "time", "country", "jobTypes"};
        csvWriter.writeHeader(csvHeader);
        for (Booking booking : listAppointments) {
            csvWriter.write(booking, fieldMapping);
        }
        csvWriter.close();
    }

    public void exportToPDF(List<Booking> listAppointments, HttpServletResponse response) throws DocumentException, IOException{
        setResponseHeader(response, "application/pdf", ".pdf","Appointment Details_");

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLACK);
        Paragraph para = new Paragraph("Appointment Details", font);
        para.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(para);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(18);

        writeBookingHeader(table);
        writeBookingData(table,listAppointments);

        document.add(table);
        document.close();
    }

    private void writeBookingData(PdfPTable table, List<Booking> listAppointments) {
        for (Booking booking: listAppointments){
            table.addCell(String.valueOf(booking.getId()));
            table.addCell(booking.getDate().toString());
            table.addCell(booking.getTime());
            table.addCell(booking.getCountry().getName());
            table.addCell(booking.getJobTypes().getName());
        }
    }

    private void writeBookingHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.DARK_GRAY);
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID",font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Time", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Country", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Job Type", font));
        table.addCell(cell);

    }
}
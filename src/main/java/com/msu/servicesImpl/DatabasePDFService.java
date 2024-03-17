package com.msu.servicesImpl;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.msu.entities.CourseDetails;

public class DatabasePDFService {
	
	
	

	public static ByteArrayInputStream coursepdfreport(List<CourseDetails> coursedetails) {
		
		
		Document doc = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		PdfWriter.getInstance(doc, out);
		doc.open();
		Font fontHeader = FontFactory.getFont(FontFactory.TIMES_BOLD, 22);
		Paragraph para = new Paragraph("Course structure", fontHeader);
		para.setAlignment(Element.ALIGN_CENTER);
		doc.add(para);
		doc.add(Chunk.NEWLINE);
		
		PdfPTable table = new PdfPTable(5);
		Stream.of("course_id","course_number", "crn", "course_name", "cmputer_required").forEach(headerTitle ->{
			PdfPCell header = new PdfPCell();
			Font headFont = FontFactory.getFont(FontFactory.TIMES_BOLD);
			header.setBackgroundColor(Color.cyan);
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(headerTitle, headFont));
            table.addCell(header);
		});;
		
		for(CourseDetails courses : coursedetails) {
			PdfPCell idcell = new PdfPCell(new Phrase(String.valueOf(courses.getCourseId())));
			idcell.setPaddingLeft(4);
            idcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            idcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(idcell);
            
            PdfPCell coursenum = new PdfPCell(new Phrase(String.valueOf((courses.getCourseNumber()))));
            coursenum.setPaddingLeft(4);
            coursenum.setVerticalAlignment(Element.ALIGN_MIDDLE);
            coursenum.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(coursenum);
            
            PdfPCell crn = new PdfPCell(new Phrase(String.valueOf((courses.getCrn()))));
            crn.setPaddingLeft(4);
            crn.setVerticalAlignment(Element.ALIGN_MIDDLE);
            crn.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(crn);
            
            PdfPCell coursename = new PdfPCell(new Phrase(courses.getCourseName()));
            coursename.setPaddingLeft(4);
            coursename.setVerticalAlignment(Element.ALIGN_MIDDLE);
            coursename.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(coursename);
            
            boolean isComputerRequired = courses.isComputerRequired();
            String computerRequiredText = Boolean.toString(isComputerRequired);
            
            PdfPCell computerreq = new PdfPCell(new Phrase(computerRequiredText));
            computerreq.setPaddingLeft(4);
            computerreq.setVerticalAlignment(Element.ALIGN_MIDDLE);
            computerreq.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(computerreq);
            
            
		}
		doc.add(table);
		doc.close();
		

		
		return new ByteArrayInputStream(out.toByteArray());
	}

}

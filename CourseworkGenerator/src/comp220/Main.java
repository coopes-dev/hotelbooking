package comp220;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import rst.pdfbox.layout.elements.Document;
import rst.pdfbox.layout.elements.Paragraph;

public class Main {
	public static final String CREATED_PDF = "labtest.pdf";
	
	public static void writeToPDF1(String title,Vector <String> content) {
		int offset=0;
		int count=0;
		try {
            PDDocument pdfDoc = new PDDocument();
            while (true) {
            PDPage firstPage = new PDPage();
            // add page to the PDF document
            pdfDoc.addPage(firstPage);
            // For writing to a page content stream
            try(PDPageContentStream cs = new PDPageContentStream(pdfDoc, firstPage)){
                cs.beginText();
                count=0;
                // setting font family and font size
                cs.setFont(PDType1Font.HELVETICA, 12);
                // color for the text
                cs.setNonStrokingColor(Color.BLACK);
                // starting position
                cs.newLineAtOffset(20, 750);
                while (offset<content.size()) {
                	if (title.length()>0) {
                		cs.setFont(PDType1Font.HELVETICA_BOLD, 13);
                        cs.showText(title);
                        cs.newLineAtOffset(0,-18);
                        title="";	
                	} else {
                		cs.setFont(PDType1Font.HELVETICA, 12);
                	}
                    count++;
                	//System.out.println("Writing line "+content.get(offset));
                	String text=""+""+content.get(offset).replaceAll("\t", " ");
                	text=text.replace("\n"," ");
                   cs.showText(text);
                   cs.newLineAtOffset(0,-14);
                   offset++;
                   if (count>=48) break;
                }
                cs.endText();
            }
              if (offset==content.size()) {
            	  break;
              }
            }
            // save PDF document
            pdfDoc.save(CREATED_PDF);
            pdfDoc.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	public static void writeToPDF(String title,Vector <String> content) {
		Document document = new Document(40, 50, 40, 60);
        Paragraph para=new Paragraph();
        try {
			para.addText(title,14,PDType1Font.HELVETICA);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        document.add(para);
        for (int idx=0;idx<content.size();idx++) {
        	String line=content.get(idx);
        	para=new Paragraph();
        	try {
        		String text=""+""+content.get(idx).replaceAll("\t", " ");
            	text=text.replace("\n"," ");
            	
				para.setLineSpacing(1.5f);
            	para.addText(text,12,PDType1Font.HELVETICA);
				
				document.add(para);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        OutputStream outputStream=null;
		try {
			outputStream = new FileOutputStream(CREATED_PDF);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        	try {
				document.save(outputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	try {
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

     }
	
	
	private static final String CourseWorkDir285="C:\\comp220_1920\\comp285_cw1";
	private static final String CourseWorkDir220="C:\\comp220_1920\\comp220_cw1";

	
	
	private static boolean searchForID(String id) {
		String courseDir=CourseWorkDir285;
		boolean found=checkDir(courseDir,id);
		if (found) return(true);
		found=checkDir(CourseWorkDir220,id);
		if (found) return(true);
		return(false);
	}

	private static boolean checkDir(String courseDir,String idLook) {
		File file=new File(courseDir);
		String files[]=file.list();
		idLook=idLook.trim();
		for (int idx=0;idx<files.length;idx++) {
			File studentDir=new File(courseDir+"\\"+files[idx]);
			String sfiles[]=studentDir.list();
			if (sfiles==null) {
				continue;
			}
			if ((sfiles.length!=1) && (!files[idx].equals("extras"))) {
//				System.err.println("Could not determine zip file.... or zip file missing please clean up "+files[idx]);
//				System.exit(1);
				continue;
			}
			String zipName=studentDir+"\\"+sfiles[0];
			int zipPos=zipName.indexOf(".zip");
			if (zipPos==-1) {  // no zip found... don't bother
				continue;
			}
			String id=zipName.substring(zipPos-9,zipPos);
			if (id.equals(idLook)) {
				return(true);
			}
		}
		return(false);
	}
	
	
	public static void main(String[] args) {
	
		String content [] = {"This","is some","text","line zzzzz dfdf dfdf dfdf " };
		// TODO Auto-generated method stub
		BufferedReader reader =null;
		String line=null;
		boolean skip=false;
		try {
			reader=new BufferedReader(new FileReader("students.csv"));
			line = reader.readLine();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (line!=null) {
			int commaPos=line.indexOf(",");
			if (commaPos!=-1) {
				String id=line.substring(0,commaPos);
				id=id.trim();
				if (Main.searchForID(id)) {
					System.out.println("Found.."+id);
					try {
						line=reader.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					continue;
				}
				if (skip) {
					return;
				}
				Specification spec=new Specification(id);
			}
			try {
				line=reader.readLine();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

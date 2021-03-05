package comp220;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipWriter {
	private ZipOutputStream zipOS;
	public ZipWriter(String name) {
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream("labtest\\"+name+".zip");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		zipOS = new ZipOutputStream(fos);
	}

	public void writeToZipFile(String path,String content) {
		ZipEntry zipEntry = new ZipEntry(path); 
		
		try {
			zipOS.putNextEntry(zipEntry);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		byte[] bytes = new byte[1024]; 
		int length;
		ByteArrayInputStream  bais=new ByteArrayInputStream(content.getBytes());
		try {
			while ((length = bais.read(bytes)) >= 0) { 
				zipOS.write(bytes, 0, length); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			zipOS.closeEntry();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void copeToZipFile(String path,String sourcePath) {
		ZipEntry zipEntry = new ZipEntry(path); 
		
		try {
			zipOS.putNextEntry(zipEntry);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		byte[] bytes = new byte[1024]; 
		int length;
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(sourcePath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			while ((length = fis.read(bytes)) >= 0) { 
				zipOS.write(bytes, 0, length); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			zipOS.closeEntry();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void close() {
		try {
			zipOS.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

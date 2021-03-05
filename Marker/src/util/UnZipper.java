package util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UnZipper {
	public static String find(String fileName, String zipFile, String prefix) {
		return ("");
	}

	public static void unzipAll(String dest,String zipFileName) {
		try {
			ZipFile zipFile = new ZipFile(zipFileName);
			Enumeration zipEntries = zipFile.entries();
		    // First make all the directories...
			while (zipEntries.hasMoreElements()) {
				
		        String entryFileName = ((ZipEntry) zipEntries.nextElement()).getName();
		        ZipEntry zipEntry = zipFile.getEntry(entryFileName);
			    InputStream inputStream = zipFile.getInputStream(zipEntry);
			    if (zipEntry.isDirectory()) {
			    
			    	File newDir=new File(dest+"\\"+entryFileName);
			    	//System.err.println("Creating dir "+newDir.getAbsolutePath());
			    	newDir.mkdirs();
			    }
			}
			zipEntries = zipFile.entries();
			while (zipEntries.hasMoreElements()) {
		        String entryFileName = ((ZipEntry) zipEntries.nextElement()).getName();
		        ZipEntry zipEntry = zipFile.getEntry(entryFileName);
			    InputStream inputStream = zipFile.getInputStream(zipEntry);
			    if (zipEntry.isDirectory()) continue;
			    FileOutputStream fos=null;
			    try {
			    	//System.err.println("Creating file "+dest+"\\"+entryFileName);
			    	File fullFile=new File(dest+"\\"+entryFileName);
			    	fullFile.getParentFile().mkdirs();
			    	//System.err.println("Parent is "+fullFile.getParent());
			    	fos=new FileOutputStream(dest+"\\"+entryFileName);
			       
			    } catch (Exception exc3) {
			    	exc3.printStackTrace();
			    	continue;	// this one failed... do the rest
			    }
				byte buffer[]=new byte[1024];
				int len=0;
				while  (  (len=inputStream.read(buffer))!=-1) {
					fos.write(buffer,0,len);
				}
				fos.close();
			  }
			
		}catch( Exception exc1)
	{
		exc1.printStackTrace();
	};

	}

	public static boolean zipCopy(String fileName, String source, String dest, String zipFileName, String id) {
		try {
			ZipFile zipFile = new ZipFile(zipFileName);
			ZipEntry zipEntry = zipFile.getEntry(source + "/" + fileName);
			if (zipEntry == null) {
				Enumeration zipEntries = zipFile.entries();
				while (zipEntries.hasMoreElements()) {
					String entryFileName = ((ZipEntry) zipEntries.nextElement()).getName();
					// some wierd stuff in MACOSX directories
					if (entryFileName.indexOf("__MACOSX") == -1) {
						if (entryFileName.indexOf(fileName) != -1) {
							zipEntry = zipFile.getEntry(entryFileName);
						}
					}
				}
			}
			if (zipEntry == null) {
				System.err.println("Could not find .." + source + "/" + fileName);
				return (false);
			}
			InputStream inputStream = zipFile.getInputStream(zipEntry);
			FileOutputStream fos = new FileOutputStream(dest + "\\" + fileName);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			String line = br.readLine();
			while (line != null) {
				if (line.indexOf("package") != -1) {
					line = "package labtest" + id + ";";
					// System.out.println(line);
					bw.write(line + "\n");
					line = "import labtest.RegistrationHelper;";
					// System.out.println(line);
					bw.write(line + "\n");

				}
				// System.out.println(line);
				line=line.replace('£','#'); // issue with £ symbol on some platforms
				bw.write(line + "\n");
				line = br.readLine();
			}
			bw.close();
			fos.close();
			return (true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (false);
		} // make unzipper from file name

	}

}

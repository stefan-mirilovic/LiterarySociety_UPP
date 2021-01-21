package upp.demo.helper;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class FileHelper {


	public void saveFile(String base64) throws IOException {
		File currentDirFile = new File(".");
		String filePath = currentDirFile.getAbsolutePath();

		int length = 10;
		boolean useLetters = true;
		boolean useNumbers = false;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		String base64pdf = base64.split(",")[1];
		byte[] pdfBytes = Base64.getDecoder().decode(base64pdf);
		String path = filePath + generatedString+".pdf";
		Files.createDirectories(Paths.get("/pdf"));
		File file = new File(path);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(pdfBytes);
		System.out.println("PDF File Saved");
		fos.close();
	}
}

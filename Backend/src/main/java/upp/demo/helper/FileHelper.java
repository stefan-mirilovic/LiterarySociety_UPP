package upp.demo.helper;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import upp.demo.enumeration.DocumentStatus;
import upp.demo.enumeration.RoleEnum;
import upp.demo.model.Book;
import upp.demo.model.User;
import upp.demo.repository.BookRepository;
import upp.demo.repository.UserRepository;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class FileHelper {

	private final UserRepository userRepository;
	private final BookRepository bookRepository;
	static String filePath="pdf/";

	public void saveFile(String base64,String ownerEmail) throws IOException {

		int length = 10;
		boolean useLetters = true;
		boolean useNumbers = false;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		String base64pdf = base64.split(",")[1];
		byte[] pdfBytes = Base64.getDecoder().decode(base64pdf);
		String path = filePath + generatedString+".pdf";
		Files.createDirectories(Paths.get("pdf"));
		File file = new File(path);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(pdfBytes);
		System.out.println("PDF File Saved");
		fos.close();

		List<User> editors = userRepository.findAllByRole(RoleEnum.EDITOR);
		User owner = userRepository.findByEmail(ownerEmail);
		Book textDocument = new Book();
		textDocument.setOwnerEmail(owner.getEmail());
		textDocument.setPublished(false);
		textDocument.setEditors(editors);
		textDocument.setDocumentStatus(DocumentStatus.TEXT_PENDING);
		textDocument.setDocumentPath(path);
		Book book=bookRepository.save(textDocument);
	}

	public String load(String path) throws IOException {
		byte[] fileContent = FileUtils.readFileToByteArray(new File(path));
		String base64 = Base64.getEncoder().encodeToString(fileContent);
		return base64;
	}


}

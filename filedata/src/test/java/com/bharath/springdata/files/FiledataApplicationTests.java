package com.bharath.springdata.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bharath.springdata.files.entities.Image;
import com.bharath.springdata.files.repos.ImageRepository;

import javassist.bytecode.ByteArray;

@SpringBootTest
class FiledataApplicationTests {
	
	@Autowired
	ImageRepository repository;

	@Test
	public void testImageSave() throws IOException {
		Image image = new Image();
		image.setId(1);
		image.setName("guidehound");
		
		File file = new File("C:\\Users\\laptop\\Desktop\\guidehound.jpg");
		byte fileContent[] = new byte[(int)(file.length())];
		InputStream is = new FileInputStream(file);
		is.read(fileContent);
		image.setData(fileContent);
		repository.save(image);
		is.close();
	}
	
	@Test
	public void testReadImage() {
		Image image = repository.findById(1L).get();
		File file = new File("C:\\temp\\"+image.getName());
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(image.getData());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}

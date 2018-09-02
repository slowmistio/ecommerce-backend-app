package com.luanoliveira.cursomc.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.luanoliveira.cursomc.services.exceptions.FileException;

@Service
public class ImageService {

	public BufferedImage getJPGImagemFromFile(MultipartFile multipartFile) {

		String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		if (!extension.equals("png") && !extension.equals("jpg")) {
			throw new FileException("Somente imagens PNG e JPG são permitidas");
		}

		try {
			BufferedImage img = ImageIO.read(multipartFile.getInputStream());
			if (extension.equals("png")) {
				img = pngToJPG(img);
			}
			return img;
		} catch (IOException e) {
			throw new FileException("Erro ao ler o arquivo");
		}

	}

	public BufferedImage pngToJPG(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getHeight(), img.getWidth(), BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}

	public InputStream getInputStream(BufferedImage img, String extension) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (IOException e) {
			throw new FileException("Erro ao ler o arquivo");
		}
	}

}

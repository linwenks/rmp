package com.rmp.common.util;

import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;

public class ImgUtil {

	public static void main(String[] args) throws IOException {
		Thumbnails.of("images/test.jpg").size(200, 300).toFile("C:/image_200x300.jpg");
	}
}

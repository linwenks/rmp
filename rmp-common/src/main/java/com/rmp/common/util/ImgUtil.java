package com.rmp.common.util;

import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;

public class ImgUtil {
	
	public static final String SIZE_10x10 = "10x10";
	public static final String SIZE_20x20 = "20x20";
	public static final String SIZE_50x50 = "50x50";
	
	

	public static void main(String[] args) throws IOException {
		Thumbnails.of("images/test.jpg").size(200, 300).toFile("C:/image_200x300.jpg");
	}
}

package com.rmp.api.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.ImmutableMap;
import com.rmp.api.base.util.BaseUtil;
import com.rmp.api.util.constant.Constant;

public class HeadPicUtil extends BaseUtil {
	
	/**
	 * 获取 头像
	 * @param headPic
	 * @return
	 */
	public static Map<String, Object> getHeadPic(String headPic) {
		headPic = StringUtils.trim(headPic);

		boolean isMove = false;
		String headPicNew = headPic;
		String headPicOld = headPic;
		if (headPic.startsWith(Constant.imgDomain())) {
			headPic = headPic.replaceAll(Constant.imgDomain(), "");
			if (headPic.startsWith(Constant.UPLOAD_TMP)) {
				isMove = true;
				headPicOld = headPic;
				headPic = headPic.replaceAll(Constant.UPLOAD_TMP, "");
				headPicNew = headPic;
			}
		}
		
		return ImmutableMap.of("isMove", isMove, "headPicNew", headPicNew, "headPicOld", headPicOld);
	}
	
	/**
	 * 移动头像
	 * @param move
	 * @throws IOException 
	 */
	public static void moveHeadPic(String headPicNew, String headPicOld) throws IOException {
		Files.createDirectories(Paths.get(Constant.uploadTopPath() + headPicNew.substring(0, headPicNew.lastIndexOf("/"))));
		Files.move(Paths.get(Constant.uploadTopPath() + headPicOld), Paths.get(Constant.uploadTopPath() + headPicNew));    //移动文件（即复制并删除源文件）
	}
}
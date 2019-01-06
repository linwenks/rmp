package com.rmp.auto.action.day;

import java.util.Date;

import com.rmp.auto.base.action.BaseAction;
import com.rmp.auto.model.UserRemindBean;
import com.rmp.common.util.DateUtil;

public class UserRemindDayAction extends BaseAction {

	public static void main(String[] args) {
		init();

		Date nowDate = DateUtil.now();
		int nowYmd = Integer.parseInt(DateUtil.formatDate(nowDate, DateUtil.yyyyMMdd));
		/*
		Date nowDate1 = DateUtil.changeDays(nowDate, 1);
		int ymd1 = Integer.parseInt(DateUtil.formatDate(nowDate1, DateUtil.yyyyMMdd));
		*/
		userRemindService.exe("truncate", null);
		/*
		// 提前
		userRemindService.exe("insertBy1ToYmd", UserRemindBean.builder().advanceDate(nowYmd).build());
		userRemindService.exe("insertBy1ToMd", UserRemindBean.builder().advanceDate(nowYmd).build());
		userRemindService.exe("insertBy1ToD", UserRemindBean.builder().advanceDate(nowYmd).build());
		userRemindService.exe("insertBy1ToW", UserRemindBean.builder().advanceDate(nowYmd).build());
		
		// 当天
		userRemindService.exe("insertBy1ToYmdNow", UserRemindBean.builder().advanceDate(ymd1).build());
		userRemindService.exe("insertBy1ToMdNow", UserRemindBean.builder().advanceDate(ymd1).build());
		userRemindService.exe("insertBy1ToDNow", UserRemindBean.builder().advanceDate(ymd1).build());
		userRemindService.exe("insertBy1ToWNow", UserRemindBean.builder().advanceDate(ymd1).build());
		*/
		
		int maxDay = 6;
		
		for (int i=0; i<=maxDay; i++) {
			
			Date nowDateTmp = DateUtil.changeDays(nowDate, i);
			int ymdTmp = Integer.parseInt(DateUtil.formatDate(nowDateTmp, DateUtil.yyyyMMdd));
			
			for (int j=0; i+j<=maxDay; j++) {
				userRemindService.exe("insertBy1ToYmd", UserRemindBean.builder().advanceDate(ymdTmp).advanceDay(j).build());
				userRemindService.exe("insertBy1ToMd", UserRemindBean.builder().advanceDate(ymdTmp).advanceDay(j).build());
				userRemindService.exe("insertBy1ToD", UserRemindBean.builder().advanceDate(ymdTmp).advanceDay(j).build());
				userRemindService.exe("insertBy1ToW", UserRemindBean.builder().advanceDate(ymdTmp).advanceDay(j).build());
				
				userRemindService.exe("insertBy2", UserRemindBean.builder().advanceDate(nowYmd).advanceDay(i).build());
				userRemindService.exe("insertBy3", UserRemindBean.builder().advanceDate(ymdTmp).advanceDay(j).build());
			}
			
//			userRemindService.exe("insertBy1ToYmd", UserRemindBean.builder().advanceDate(nowYmd).advanceDay(i).build());
//			userRemindService.exe("insertBy1ToMd", UserRemindBean.builder().advanceDate(nowYmd).advanceDay(i).build());
//			userRemindService.exe("insertBy1ToD", UserRemindBean.builder().advanceDate(nowYmd).advanceDay(i).build());
//			userRemindService.exe("insertBy1ToW", UserRemindBean.builder().advanceDate(nowYmd).advanceDay(i).build());
			
//			userRemindService.exe("insertBy2", UserRemindBean.builder().advanceDate(nowYmd).advanceDay(i).build());
//			userRemindService.exe("insertBy3", UserRemindBean.builder().advanceDate(ymdTmp).advanceDay(i).build());
		}
	}
}
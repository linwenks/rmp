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
		int nowMd = Integer.parseInt(DateUtil.formatDate(nowDate, DateUtil.MMdd));
		int nowD = Integer.parseInt(DateUtil.formatDate(nowDate, DateUtil.dd));
		int nowW = DateUtil.getWeek(nowDate);
		
		Date nowDate1 = DateUtil.changeDays(nowDate, 1);
		int ymd1 = Integer.parseInt(DateUtil.formatDate(nowDate1, DateUtil.yyyyMMdd));
		int md1 = Integer.parseInt(DateUtil.formatDate(nowDate1, DateUtil.MMdd));
		int d1 = Integer.parseInt(DateUtil.formatDate(nowDate1, DateUtil.dd));
		int w1 = DateUtil.getWeek(nowDate1);
		
		userRemindService.exe("insertBy1ToYmd", UserRemindBean.builder().advanceDate(nowYmd).ymd(nowYmd));
		userRemindService.exe("insertBy1ToMd", UserRemindBean.builder().advanceDate(nowYmd).ymd(nowMd));
		userRemindService.exe("insertBy1ToD", UserRemindBean.builder().advanceDate(nowYmd).ymd(nowD));
		userRemindService.exe("insertBy1ToW", UserRemindBean.builder().advanceDate(nowYmd).ymd(nowW));
		
		userRemindService.exe("insertBy1ToYmdNow", UserRemindBean.builder().advanceDate(ymd1).ymd(ymd1));
		userRemindService.exe("insertBy1ToMdNow", UserRemindBean.builder().advanceDate(ymd1).ymd(md1));
		userRemindService.exe("insertBy1ToDNow", UserRemindBean.builder().advanceDate(ymd1).ymd(d1));
		userRemindService.exe("insertBy1ToWNow", UserRemindBean.builder().advanceDate(ymd1).ymd(w1));
		
		userRemindService.exe("insertBy2", UserRemindBean.builder().advanceDate(nowYmd).ymd(nowD));
		userRemindService.exe("insertBy2Now", UserRemindBean.builder().advanceDate(ymd1).ymd(d1));
		userRemindService.exe("insertBy3", UserRemindBean.builder().advanceDate(nowYmd).ymd(nowW));
		userRemindService.exe("insertBy3Now", UserRemindBean.builder().advanceDate(ymd1).ymd(w1));
		userRemindService.exe("insertBy4", UserRemindBean.builder().advanceDate(nowYmd).ymd(nowW));
		userRemindService.exe("insertBy4Now", UserRemindBean.builder().advanceDate(ymd1).ymd(w1));
	}
}

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
		
		int advanceDay5 = 5;
		Date nowDate5 = DateUtil.changeDays(nowDate, 5);
		int ymd5 = Integer.parseInt(DateUtil.formatDate(nowDate5, DateUtil.yyyyMMdd));
		
		userRemindService.exe("truncate", null);
		
		userRemindService.exe("insertBy1ToYmd", UserRemindBean.builder().advanceDate(nowYmd).ymd(nowYmd).build());
		userRemindService.exe("insertBy1ToMd", UserRemindBean.builder().advanceDate(nowYmd).md(nowMd).build());
		userRemindService.exe("insertBy1ToD", UserRemindBean.builder().advanceDate(nowYmd).d(nowD).build());
		userRemindService.exe("insertBy1ToW", UserRemindBean.builder().advanceDate(nowYmd).w(nowW).build());
		
		userRemindService.exe("insertBy1ToYmdNow", UserRemindBean.builder().advanceDate(ymd1).ymd(ymd1).build());
		userRemindService.exe("insertBy1ToMdNow", UserRemindBean.builder().advanceDate(ymd1).md(md1).build());
		userRemindService.exe("insertBy1ToDNow", UserRemindBean.builder().advanceDate(ymd1).d(d1).build());
		userRemindService.exe("insertBy1ToWNow", UserRemindBean.builder().advanceDate(ymd1).w(w1).build());
		
		
		userRemindService.exe("insertBy2", UserRemindBean.builder().advanceDate(ymd5).ymd(ymd5).advanceDay(advanceDay5).build());
		userRemindService.exe("insertBy3", UserRemindBean.builder().advanceDate(ymd5).ymd(ymd5).advanceDay(advanceDay5).build());
		userRemindService.exe("insertBy4", UserRemindBean.builder().advanceDate(ymd5).ymd(ymd5).advanceDay(advanceDay5).build());
		
		int advanceDay0 = 0;
		userRemindService.exe("insertBy2", UserRemindBean.builder().advanceDate(nowYmd).ymd(nowYmd).advanceDay(advanceDay0).build());
		userRemindService.exe("insertBy3", UserRemindBean.builder().advanceDate(nowYmd).ymd(nowYmd).advanceDay(advanceDay0).build());
		userRemindService.exe("insertBy4", UserRemindBean.builder().advanceDate(nowYmd).ymd(nowYmd).advanceDay(advanceDay0).build());
	}
}

package com.rmp.api.util;

import lombok.Getter;

@Getter
public enum MsgEnum {
	
	MSG_00002("00002", "系统繁忙，请稍候再试！"),
	MSG_00003("00003", "数据为空"),
	MSG_00004("00004", "数据异常"),
	MSG_00005("00005", "数据转换JSON异常"),
	MSG_00006("00006", "token为空"),
	MSG_00007("00007", "token不存在"),
	MSG_00008("00008", "数据已更新，请重试"),    // redis事务失败
	MSG_00009("00009", "版本号为空"),
	MSG_00010("00010", "数据已跟新"),
	
	MSG_00020("00020", "上传文件失败：文件（{0}）大于{1}"),
	MSG_00021("00021", "上传文件失败：文件（{0}）后缀名不符合{1}"),
	MSG_00022("00022", "上传文件保存失败"),
	
	// 用户
	MSG_01000("01000", "账号为空"),
	MSG_01001("01001", "账号格式错误"),
	MSG_01002("01002", "账号不存在"),
	MSG_01016("01016", "账号未注册"),
	MSG_01017("01017", "账号已删除"),
	
	MSG_01031("01031", "账号已注册"),
	MSG_01032("01032", "账号sessionKey为空"),
	MSG_01033("01033", "解析手机失败"),
	MSG_01034("01034", "微信未绑定手机"),
	
	MSG_01003("01003", "密码为空"),
	MSG_01004("01004", "密码为{0}到{1}位之间"),
	MSG_01005("01005", "密码格式错误"),
	MSG_01006("01006", "密码错误"),
	
	MSG_01007("01007", "验证码为空"),
	MSG_01008("01008", "验证码错误"),
	MSG_01009("01009", "账号已注册，请登录"),
//	MSG_01010("01010", "账号已停用"),
	MSG_01011("01011", "验证码为空"),
	MSG_01012("01012", "验证码错误"),
	MSG_01013("01013", "一定时间内请勿重复发送验证码，请稍后再试"),
	MSG_01014("01014", "手机号为空"),
	MSG_01015("01015", "手机号格式错误"),
	
	MSG_01018("01018", "昵称为空"),
	MSG_01019("01019", "昵称为{0}到{1}位之间"),
	MSG_01020("01020", "头像为空"),
	MSG_01021("01021", "手机号为空"),
	MSG_01022("01022", "手机号格式错误"),
	MSG_01023("01023", "手机号已注册"),
	
	// 工作
	MSG_01024("01024", "公司大于{0}字"),
	MSG_01025("01025", "职位大于{0}字"),
	MSG_01026("01026", "公司地址大于{0}字"),
	MSG_01027("01027", "公司手机号格式错误"),
	
	MSG_01028("01028", "姓名为空"),
	MSG_01029("01029", "姓名大于{0}字"),
	MSG_01030("01030", "地址大于{0}字"),
	
	
	// 用户 客户
	MSG_02000("02000", "姓名为空"),
	MSG_02001("02001", "姓名大于{0}字"),
	MSG_02002("02002", "手机号为空"),
	MSG_02003("02003", "手机号格式错误"),
	MSG_02004("02004", "手机号已存在"),
	MSG_02009("02009", "地址大于{0}字"),
	
	// 工作
	MSG_02010("02010", "公司大于{0}字"),
	MSG_02011("02011", "职位大于{0}字"),
	MSG_02012("02012", "公司地址大于{0}字"),
	MSG_02013("02013", "公司手机号格式错误"),
	
	// 关系
	MSG_02005("02005", "关系为空"),
	MSG_02006("02006", "亲密为空"),
	MSG_02007("02007", "重要为空"),
	
	// 
	MSG_02008("02008", "备注大于{0}字"),
	
	// 家庭
	MSG_02014("02014", "姓名为空"),
	MSG_02015("02015", "姓名大于{0}字"),
	MSG_02016("02016", "手机号格式错误"),
	MSG_02017("02017", "地址大于{0}字"),
	MSG_02018("02018", "关系为空"),
	MSG_02019("02019", "年龄小于0"),
	
	// 纪念日
	MSG_02020("02020", "名称为空"),
	MSG_02021("02021", "名称大于{0}字"),
	MSG_02022("02022", "发生类型为空"),
	MSG_02023("02023", "发生日期为空"),
	MSG_02024("02024", "提前类型为空"),
	
	// 维护
	MSG_02025("02025", "维护为空"),
	;
	
	private String key;
    private String value;
    
    private MsgEnum(String key, String value) {
    	this.key = key;
    	this.value = value;
    }
}
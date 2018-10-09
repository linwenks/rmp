package com.rmp.api.base.config;

public class ApidocConfig {
	
	/**
	 * @apiDefine group_example 示例
	 */
	/**
	 * @apiDefine group_index 首页
	 */
	/**
	 * @apiDefine group_member 用户
	 */
	/**
	 * @apiDefine group_article 新闻
	 */
	/**
	 * @apiDefine group_master 师徒
	 */
	/**
	 * @apiDefine group_task 任务
	 */
	/**
	 * @apiDefine group_my 个人中心
	 */
	
	/**
	 * @apiDefine defaultHeader
	 * @apiParam (Header) {Object} header 公共参数
	 * @apiParam (Header) {String} [header.token] token
	 * @apiParam (Header) {String} header.adChannelTag 渠道
	 * @apiParam (Header) {String} header.appVersion app版本
	 * @apiParam (Header) {String{13..}} header.deviceId 设备ID
	 * @apiParam (Header) {String} header.manufactures 品牌
	 * @apiParam (Header) {String} header.model 型号
	 * @apiParam (Header) {String} header.androidversion 安卓版本
	 * @apiParam (Header) {String{12..18}} header.mac mac地址
	 * @apiParam (Header) {Integer{1-2}=1,2} header.system=1 系统<br/>1:android<br/>2:ios
	 */
	/**
	 * @apiDefine defaultQueryPage
	 * @apiParam (QueryPage) {Object} queryPage 分页 bean
     * @apiParam (QueryPage) {Integer{1..}} queryPage.targetPage 目标页
	 */
	
	
	/**
	 * @apiDefine defaultError
	 * @apiError (200) {String[]} msgList 消息list
	 * @apiError (200) {Object} msg 消息
	 * @apiError (200) {String} msg.code 数字编号
	 * @apiError (200) {String} msg.des 消息
	 * @apiError (200) {String} state 状态（0:成功 1:失败）
	 * @apiError (200) {Object} dataMap 数据
	 * 
	 * @apiErrorExample {json} 失败返回-示例:
                 {"msgList":[{"code":"00003","des":"数据为空"}],"msg":{"code":"00003","des":"数据为空"},"state":"1","dataMap":{}}
	 */
	
	/**
     * @api {post} /example 公共示例
     * @apiDescription 示例
     * @apiName example
     * @apiGroup group_example
     * @apiVersion 1.0.0
     * 
     * @apiUse defaultHeader
     * @apiUse defaultQueryPage
     * 
     * @apiParamExample {json} 请求-示例: 
			{"header":{"token":"6e428093e8104f379c484faa9715a1b8","adChannelTag":"chy_5","appVersion":"20","deviceId":"99000939101350","manufactures":"Xiaomi","model":"MI6","androidversion":"8.0.0","mac":"00:ec:0a:d6:96:65","system":1},"queryPage":{"targetPage":1}}
     *
     * @apiUse defaultError
     * 
     * @apiSuccess (dataMap) {Object} queryPage 分页
	 * @apiSuccess (dataMap) {Integer} queryPage.pageSize 每页记录数
	 * @apiSuccess (dataMap) {Integer} queryPage.pageCount 总页数
	 * @apiSuccess (dataMap) {Integer} queryPage.recordCount 总记录数
	 * @apiSuccess (dataMap) {Integer} queryPage.targetPage 目标页
	 * 
	 * @apiSuccessExample {json} 成功返回-示例:
	 * 		{"header":{"token":"6e428093e8104f379c484faa9715a1b8","adChannelTag":"chy_5","appVersion":"20","deviceId":"99000939101350","manufactures":"Xiaomi","model":"MI6","androidversion":"8.0.0","mac":"00:ec:0a:d6:96:65","system":1},"msgList":[],"msg":{},"state":"0","dataMap":{"data":{},"queryPage":{"pageSize":20,"pageCount":17,"recordCount":331,"targetPage":1}}}
     */
}

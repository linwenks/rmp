package com.rmp.api.base.config;

/**
 * apidoc -i D:\git-linw\rmp\rmp-api -o D:\apidoc\rmp-api-doc
 * 
 * @author linw
 *
 */
public class ApidocConfig {
	
	/**
	 * @apiDefine group_example 示例
	 */
	/**
	 * @apiDefine group_index 首页
	 */
	/**
	 * @apiDefine group_user 用户
	 */
	
	/**
	 * @apiDefine defaultHeader
	 * @apiParam (Header) {Object} header 公共参数
	 * @apiParam (Header) {String} [header.token] token
	 */
	/**
	 * @apiDefine defaultQueryPage
	 * @apiParam (QueryPage) {Object} queryPage 分页 bean
     * @apiParam (QueryPage) {Integer{1..}} queryPage.targetPage 目标页
	 */
	
	
	/**
	 * @apiDefine defaultError
	 * @apiError (200) {String[]} msgs 消息
	 * @apiError (200) {Object} msg 消息
	 * @apiError (200) {String} msg.code 数字编号
	 * @apiError (200) {String} msg.des 消息
	 * @apiError (200) {String=0,1} state 状态<br/>0:成功<br/>1:失败
	 * @apiError (200) {Object} data 数据
	 * 
	 * @apiErrorExample {json} 失败返回-示例:
                 {"msgs":[{"code":"00003","des":"数据为空"}],"msg":{"code":"00003","des":"数据为空"},"state":"1","data":{}}
	 */
	
	/**
     * @api {post} /example 示例
     * @apiDescription 示例
     * @apiName example
     * @apiGroup group_example
     * @apiVersion 1.0.0
     * 
     * @apiUse defaultHeader
     * @apiUse defaultQueryPage
     * 
     * @apiParamExample {json} 请求-示例: 
			{"header":{"token":"6e428093e8104f379c484faa9715a1b8"},"queryPage":{"targetPage":1}}
     *
     * @apiUse defaultError
     * 
     * @apiSuccess (data) {Object} queryPage 分页
	 * @apiSuccess (data) {Integer} queryPage.pageSize 每页记录数
	 * @apiSuccess (data) {Integer} queryPage.pageCount 总页数
	 * @apiSuccess (data) {Integer} queryPage.recordCount 总记录数
	 * @apiSuccess (data) {Integer} queryPage.targetPage 目标页
	 * 
	 * @apiSuccessExample {json} 成功返回-示例:
	 * 		{"header":{"token":"6e428093e8104f379c484faa9715a1b8"},"msgs":[],"msg":{},"state":"0","data":{"queryPage":{"pageSize":20,"pageCount":17,"recordCount":331,"targetPage":1}}}
     */
}

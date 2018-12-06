define({ "api": [
  {
    "type": "post",
    "url": "/api/customer/customer/config",
    "title": "客户 配置",
    "description": "<p>客户 配置</p>",
    "name": "customer_customer_config",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "importanceCodeList",
            "description": "<p>关系 code list</p>"
          },
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "intimacyCodeList",
            "description": "<p>亲密 code list</p>"
          },
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "relationshipCodeList",
            "description": "<p>重要 code list</p>"
          },
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "tagCodeList",
            "description": "<p>标签 code list 暂无</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"intimacyCodeList\":[{\"id\":14,\"key\":\"0\",\"value\":\"不详\",\"pid\":13,\"sort\":0},{\"id\":15,\"key\":\"1\",\"value\":\"很亲近\",\"pid\":13,\"sort\":1},{\"id\":16,\"key\":\"2\",\"value\":\"一般亲近\",\"pid\":13,\"sort\":2},{\"id\":17,\"key\":\"3\",\"value\":\"正常交往\",\"pid\":13,\"sort\":3},{\"id\":18,\"key\":\"4\",\"value\":\"点头之交\",\"pid\":13,\"sort\":4}],\"relationshipCodeList\":[{\"id\":4,\"key\":\"0\",\"value\":\"其他\",\"pid\":3,\"sort\":0},{\"id\":5,\"key\":\"1\",\"value\":\"家人\",\"pid\":3,\"sort\":1},{\"id\":6,\"key\":\"2\",\"value\":\"亲戚\",\"pid\":3,\"sort\":2},{\"id\":7,\"key\":\"3\",\"value\":\"朋友\",\"pid\":3,\"sort\":3},{\"id\":8,\"key\":\"4\",\"value\":\"同学\",\"pid\":3,\"sort\":4},{\"id\":9,\"key\":\"5\",\"value\":\"同事\",\"pid\":3,\"sort\":5},{\"id\":10,\"key\":\"6\",\"value\":\"客户\",\"pid\":3,\"sort\":6},{\"id\":11,\"key\":\"7\",\"value\":\"熟人\",\"pid\":3,\"sort\":7},{\"id\":12,\"key\":\"8\",\"value\":\"陌生人\",\"pid\":3,\"sort\":8}],\"importanceCodeList\":[{\"id\":20,\"key\":\"0\",\"value\":\"不重要\",\"pid\":19,\"sort\":0},{\"id\":21,\"key\":\"1\",\"value\":\"重要\",\"pid\":19,\"sort\":1},{\"id\":22,\"key\":\"2\",\"value\":\"非常重要（vip）\",\"pid\":19,\"sort\":2}]}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/customer/delete",
    "title": "客户 删除",
    "description": "<p>客户 删除</p>",
    "name": "customer_customer_delete",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "CustomerBean": [
          {
            "group": "CustomerBean",
            "type": "Object",
            "optional": false,
            "field": "customerBean",
            "description": "<p>客户 bean</p>"
          },
          {
            "group": "CustomerBean",
            "type": "Long",
            "optional": false,
            "field": "customerBean.id",
            "description": "<p>ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerBean\":{\"id\":1}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/customer/get",
    "title": "客户 查询",
    "description": "<p>客户 查询</p>",
    "name": "customer_customer_get",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "CustomerBean": [
          {
            "group": "CustomerBean",
            "type": "Object",
            "optional": false,
            "field": "customerBean",
            "description": "<p>客户 bean</p>"
          },
          {
            "group": "CustomerBean",
            "type": "Long",
            "optional": false,
            "field": "customerBean.id",
            "description": "<p>ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerBean\":{\"id\":2}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "customerBean",
            "description": "<p>客户 bean</p>"
          },
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "customerJobBean",
            "description": "<p>客户 工作 bean</p>"
          },
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "customerFamilyBeanList",
            "description": "<p>客户 家庭 bean list</p>"
          },
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "customerHobbyBean",
            "description": "<p>客户 兴趣爱好 bean</p>"
          },
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "customerMaintainBean",
            "description": "<p>客户 维护设置 bean</p>"
          },
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "customerMemorialDayBeanList",
            "description": "<p>客户 纪念日 bean list</p>"
          },
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "customerProblemBean",
            "description": "<p>客户 可能问题 bean</p>"
          },
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "customerRelationBean",
            "description": "<p>客户 关系 bean</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"customerHobbyBean\":{\"interestKeyList\":[\"1\"],\"dietKeyList\":[\"1\",\"3\"],\"tasteKeyList\":[\"4\"],\"interestValueList\":[\"美食\"],\"dietValueList\":[\"川湘菜\",\"粤菜\"],\"tasteValueList\":[\"苦\"],\"interest\":\"1\",\"diet\":\"1,3\",\"taste\":\"4\"},\"customerFamilyBeanList\":[{\"areaNameAll\":\"江苏省泰州市\",\"relationshipValue\":\"母亲\",\"id\":2,\"relationship\":2,\"realName\":\"xxx\",\"birthday\":20100101,\"phone\":15111111111,\"areaId\":321200,\"address\":\"ttt\"}],\"customerMemorialDayBeanList\":[{\"occurTypeValue\":\"1次\",\"advanceTypeValue\":\"1天\",\"id\":2,\"name\":\"ttttt2\",\"occurType\":1,\"occurDate\":20181030,\"advanceType\":1},{\"occurTypeValue\":\"1次\",\"advanceTypeValue\":\"1天\",\"id\":1,\"name\":\"ttttt2\",\"occurType\":1,\"occurDate\":20181030,\"advanceType\":1}],\"customerJobBean\":{\"areaNameAll\":\"江苏省泰州市\",\"industryValue\":\"互联网/电子商务/网游\",\"positionValue\":\"IT管理\",\"industry\":2,\"companyName\":\"aaaa\",\"departmentName\":\"bbb\",\"position\":3,\"phone\":15111111111,\"areaId\":321200,\"address\":\"aaaaaaaaaaaaaa\"},\"customerProblemBean\":{\"healthKeyList\":[\"1\"],\"lifeKeyList\":[\"1\",\"3\"],\"healthValueList\":[\"心脏病\"],\"lifeValueList\":[\"资金缺乏\",\"事业发展\"],\"health\":\"1\",\"life\":\"1,3\",\"remark\":\"xxxxxxxxxTTT\"},\"customerRelationBean\":{\"relationshipValue\":\"其他\",\"intimacyValue\":\"不详\",\"importanceValue\":\"不重要\",\"relationship\":0,\"intimacy\":0,\"importance\":0},\"customerBean\":{\"areaNameAll\":\"江苏省泰州市\",\"realName\":\"ss\",\"phone\":15111111112,\"sex\":1,\"birthday\":20100101,\"headPic\":\"https://img.rmp.com/img/head_pic/default.jpg\",\"areaId\":321200,\"address\":\"ttt\",\"vip\":0}}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/customer/list",
    "title": "客户 列表查询",
    "description": "<p>客户 列表查询</p>",
    "name": "customer_customer_list",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "CustomerBean": [
          {
            "group": "CustomerBean",
            "type": "Object",
            "optional": false,
            "field": "customerBean",
            "description": "<p>客户 bean</p>"
          },
          {
            "group": "CustomerBean",
            "type": "String",
            "optional": false,
            "field": "customerBean.realName",
            "description": "<p>真实姓名</p>"
          },
          {
            "group": "CustomerBean",
            "type": "String",
            "optional": false,
            "field": "customerBean.phone",
            "description": "<p>手机</p>"
          }
        ],
        "CustomerRelationBean": [
          {
            "group": "CustomerRelationBean",
            "type": "Object",
            "optional": false,
            "field": "customerBean.customerRelationBean",
            "description": "<p>客户 关系 bean</p>"
          },
          {
            "group": "CustomerRelationBean",
            "type": "Integer",
            "optional": false,
            "field": "customerBean.customerRelationBean.relationship",
            "description": "<p>重要</p>"
          },
          {
            "group": "CustomerRelationBean",
            "type": "Integer",
            "optional": false,
            "field": "customerBean.customerRelationBean.intimacy",
            "description": "<p>亲密</p>"
          },
          {
            "group": "CustomerRelationBean",
            "type": "Integer",
            "optional": false,
            "field": "customerBean.customerRelationBean.importance",
            "description": "<p>关系</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerBean\":{\"realName\":\"s\",\"phone\":15111111112,\"customerRelationBean\":{\"relationship\":0,\"intimacy\":0,\"importance\":0}}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "letters",
            "description": "<p>字母 list</p>"
          },
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "groups",
            "description": "<p>客户 字母分组 list</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "groups.groupName",
            "description": "<p>分组名称</p>"
          },
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "groups.users",
            "description": "<p>客户 bean list</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "groups.users.id",
            "description": "<p>客户ID</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "groups.users.realName",
            "description": "<p>真实姓名</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "groups.users.phone",
            "description": "<p>手机</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "groups.users.sex",
            "description": "<p>性别<br/>0:女<br/>1:男</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "groups.users.headPic",
            "description": "<p>头像</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "groups.users.areaId",
            "description": "<p>区域ID</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "groups.users.areaNameAll",
            "description": "<p>区域全称</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "groups.users.address",
            "description": "<p>地址</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "groups.users.pinyin",
            "description": "<p>拼音</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "groups.users.pinyinFirst",
            "description": "<p>拼音 首字母</p>"
          },
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "groups.users.customerRelationBean",
            "description": "<p>客户 关系 bean</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "groups.users.customerRelationBean.importance",
            "description": "<p>关系</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "groups.users.customerRelationBean.importanceValue",
            "description": "<p>关系 值</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "groups.users.customerRelationBean.intimacy",
            "description": "<p>亲密</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "groups.users.customerRelationBean.intimacyValue",
            "description": "<p>亲密 值</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "groups.users.customerRelationBean.relationship",
            "description": "<p>重要</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "groups.users.customerRelationBean.relationshipValue",
            "description": "<p>重要 值</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"groups\":[{\"groupName\":\"s\",\"users\":[{\"pinyinFirst\":\"s\",\"id\":6,\"realName\":\"ss\",\"pinyin\":\"ss\",\"phone\":15111111115,\"headPic\":\"https://img.rmp.com/img/head_pic/default.jpg\"},{\"customerRelationBean\":{\"relationshipValue\":\"其他\",\"intimacyValue\":\"不详\",\"importanceValue\":\"不重要\",\"relationship\":0,\"intimacy\":0,\"importance\":0},\"pinyinFirst\":\"s\",\"id\":2,\"realName\":\"ss\",\"pinyin\":\"ss\",\"phone\":15111111112,\"sex\":1,\"birthday\":20100101,\"headPic\":\"https://img.rmp.com/img/head_pic/default.jpg\",\"address\":\"ttt\"}]},{\"groupName\":\"t\",\"users\":[{\"pinyinFirst\":\"t\",\"id\":5,\"realName\":\"ttt\",\"pinyin\":\"ttt\",\"phone\":15111111113,\"sex\":0,\"birthday\":20100101,\"headPic\":\"https://img.rmp.com/xxx/pic.jpg\",\"address\":\"aaaaaaaaaaaaaa\"},{\"pinyinFirst\":\"t\",\"id\":4,\"realName\":\"ttt\",\"pinyin\":\"ttt\",\"phone\":15111111111,\"sex\":0,\"headPic\":\"https://img.rmp.com/xxx/pic.jpg\",\"address\":\"aaaaaaaaaaaaaa\"}]},{\"groupName\":\"d\",\"users\":[{\"pinyinFirst\":\"d\",\"id\":8,\"realName\":\"ddd\",\"pinyin\":\"ddd\",\"phone\":13658327488,\"headPic\":\"https://img.rmp.com/img/head_pic/default.jpg\"}]}],\"letters\":[\"d\",\"s\",\"t\"]}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/customer/save",
    "title": "客户 新增",
    "description": "<p>客户 新增</p>",
    "name": "customer_customer_save",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "CustomerBean": [
          {
            "group": "CustomerBean",
            "type": "Object",
            "optional": false,
            "field": "customerBean",
            "description": "<p>客户 bean</p>"
          },
          {
            "group": "CustomerBean",
            "type": "String",
            "optional": false,
            "field": "customerBean.realName",
            "description": "<p>真实姓名</p>"
          },
          {
            "group": "CustomerBean",
            "type": "Long",
            "optional": false,
            "field": "customerBean.phone",
            "description": "<p>手机</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerBean\":{\"realName\":\"ss\",\"phone\":\"15111111111\"}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/customer/update",
    "title": "客户 修改",
    "description": "<p>客户 修改</p>",
    "name": "customer_customer_update",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "CustomerBean": [
          {
            "group": "CustomerBean",
            "type": "Object",
            "optional": false,
            "field": "customerBean",
            "description": "<p>客户 bean</p>"
          },
          {
            "group": "CustomerBean",
            "type": "Long",
            "optional": false,
            "field": "customerBean.id",
            "description": "<p>ID</p>"
          },
          {
            "group": "CustomerBean",
            "type": "String",
            "optional": false,
            "field": "customerBean.realName",
            "description": "<p>真实姓名</p>"
          },
          {
            "group": "CustomerBean",
            "type": "Long",
            "optional": false,
            "field": "customerBean.phone",
            "description": "<p>手机</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerBean\":{\"id\":1,\"realName\":\"aa\",\"phone\":\"15111111116\"}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/customer/updateHeadPic",
    "title": "客户 修改 头像",
    "description": "<p>客户 修改 头像</p>",
    "name": "customer_customer_updateHeadPic",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "UserBean": [
          {
            "group": "UserBean",
            "type": "Object",
            "optional": false,
            "field": "customerBean",
            "description": "<p>用户 bean</p>"
          },
          {
            "group": "UserBean",
            "type": "String",
            "optional": false,
            "field": "customerBean.id",
            "description": "<p>客户ID</p>"
          },
          {
            "group": "UserBean",
            "type": "String",
            "optional": false,
            "field": "customerBean.headPic",
            "description": "<p>头像</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerBean\":{\"id\":1,\"headPic\":\"http://47.94.5.205/tmp/customer/head_pic/20181127/1114000029789874659.jpg\"}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"customerBean\":{\"headPic\":\"http://47.94.5.205/customer/head_pic/20181127/1114000029789874659.jpg\"}}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/customer/uploadHeadPic",
    "title": "客户 上传 头像",
    "description": "<p>客户 上传 头像 表单提交</p>",
    "name": "customer_customer_uploadHeadPic",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "Form": [
          {
            "group": "Form",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Form",
            "type": "File",
            "optional": false,
            "field": "headPicFile",
            "description": "<p>头像文件</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"customerBean\":{\"headPic\":\"http://47.94.5.205/customer/head_pic/20181127/1114000029789874659.jpg\"}}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/family/config",
    "title": "客户 家庭 配置",
    "description": "<p>客户 家庭 配置</p>",
    "name": "customer_family_config",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "relationshipCodeList",
            "description": "<p>关系 code list</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "relationshipCodeList.key",
            "description": "<p>键</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "relationshipCodeList.value",
            "description": "<p>值</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"relationshipCodeList\":[{\"id\":145,\"key\":\"1\",\"value\":\"父亲\",\"pid\":144,\"sort\":0},{\"id\":146,\"key\":\"2\",\"value\":\"母亲\",\"pid\":144,\"sort\":0},{\"id\":147,\"key\":\"3\",\"value\":\"老公\",\"pid\":144,\"sort\":0},{\"id\":148,\"key\":\"4\",\"value\":\"老婆\",\"pid\":144,\"sort\":0},{\"id\":149,\"key\":\"5\",\"value\":\"儿子\",\"pid\":144,\"sort\":0},{\"id\":150,\"key\":\"6\",\"value\":\"女儿\",\"pid\":144,\"sort\":0}]}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerFamilyController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/family/delete",
    "title": "客户 家庭 删除",
    "description": "<p>客户 家庭 删除</p>",
    "name": "customer_family_delete",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "CustomerFamilyBean": [
          {
            "group": "CustomerFamilyBean",
            "type": "Object",
            "optional": false,
            "field": "customerFamilyBean",
            "description": "<p>家庭 bean</p>"
          },
          {
            "group": "CustomerFamilyBean",
            "type": "Long",
            "optional": false,
            "field": "customerFamilyBean.id",
            "description": "<p>ID</p>"
          },
          {
            "group": "CustomerFamilyBean",
            "type": "Long",
            "optional": false,
            "field": "customerFamilyBean.customerId",
            "description": "<p>客户ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerFamilyBean\":{\"id\":1,\"customerId\":2}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerFamilyController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/family/get",
    "title": "客户 家庭 查询",
    "description": "<p>客户 家庭 查询</p>",
    "name": "customer_family_get",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "CustomerFamilyBean": [
          {
            "group": "CustomerFamilyBean",
            "type": "Object",
            "optional": false,
            "field": "customerFamilyBean",
            "description": "<p>家庭 bean</p>"
          },
          {
            "group": "CustomerFamilyBean",
            "type": "Long",
            "optional": false,
            "field": "customerFamilyBean.id",
            "description": "<p>ID</p>"
          },
          {
            "group": "CustomerFamilyBean",
            "type": "Long",
            "optional": false,
            "field": "customerFamilyBean.customerId",
            "description": "<p>客户ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerFamilyBean\":{\"id\":1,\"customerId\":2}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "customerFamilyBean",
            "description": "<p>家庭 bean</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "customerFamilyBean.id",
            "description": "<p>ID</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "customerFamilyBean.relationship",
            "description": "<p>关系</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerFamilyBean.relationshipValue",
            "description": "<p>关系 值</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerFamilyBean.realName",
            "description": "<p>真实姓名</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "customerFamilyBean.birthday",
            "description": "<p>生日</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "customerFamilyBean.phone",
            "description": "<p>手机</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "customerFamilyBean.areaId",
            "description": "<p>区域</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerFamilyBean.areaNameAll",
            "description": "<p>区域全名</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerFamilyBean.address",
            "description": "<p>地址</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"customerFamilyBean\":{\"areaNameAll\":\"江苏省泰州市\",\"relationshipValue\":\"母亲\",\"id\":1,\"relationship\":2,\"realName\":\"xxx\",\"birthday\":20100101,\"phone\":15111111111,\"areaId\":321200}}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerFamilyController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/family/list",
    "title": "客户 家庭 列表 查询",
    "description": "<p>客户 家庭 查询</p>",
    "name": "customer_family_list",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "CustomerFamilyBean": [
          {
            "group": "CustomerFamilyBean",
            "type": "Object",
            "optional": false,
            "field": "customerFamilyBean",
            "description": "<p>家庭 bean</p>"
          },
          {
            "group": "CustomerFamilyBean",
            "type": "Long",
            "optional": false,
            "field": "customerFamilyBean.customerId",
            "description": "<p>客户ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerFamilyBean\":{\"customerId\":2}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "customerFamilyBeanList",
            "description": "<p>家庭 bean list</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "customerFamilyBeanList.id",
            "description": "<p>ID</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "customerFamilyBeanList.relationship",
            "description": "<p>关系</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerFamilyBeanList.relationshipValue",
            "description": "<p>关系 值</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerFamilyBeanList.realName",
            "description": "<p>真实姓名</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "customerFamilyBeanList.birthday",
            "description": "<p>生日</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "customerFamilyBeanList.phone",
            "description": "<p>手机</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "customerFamilyBeanList.areaId",
            "description": "<p>区域</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerFamilyBeanList.areaNameAll",
            "description": "<p>区域全名</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerFamilyBeanList.address",
            "description": "<p>地址</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"customerFamilyBeanList\":[{\"areaNameAll\":\"江苏省泰州市\",\"relationshipValue\":\"母亲\",\"id\":2,\"relationship\":2,\"realName\":\"xxx\",\"birthday\":20100101,\"phone\":15111111111,\"areaId\":321200,\"address\":\"ttt\"},{\"areaNameAll\":\"江苏省泰州市\",\"relationshipValue\":\"母亲\",\"id\":1,\"relationship\":2,\"realName\":\"xxx\",\"birthday\":20100101,\"phone\":15111111111,\"areaId\":321200}]}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerFamilyController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/family/save",
    "title": "客户 家庭 添加",
    "description": "<p>客户 家庭 添加</p>",
    "name": "customer_family_save",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "CustomerFamilyBean": [
          {
            "group": "CustomerFamilyBean",
            "type": "Object",
            "optional": false,
            "field": "customerFamilyBean",
            "description": "<p>家庭 bean</p>"
          },
          {
            "group": "CustomerFamilyBean",
            "type": "Long",
            "optional": false,
            "field": "customerFamilyBean.customerId",
            "description": "<p>客户ID</p>"
          },
          {
            "group": "CustomerFamilyBean",
            "type": "Integer",
            "optional": false,
            "field": "customerFamilyBean.relationship",
            "description": "<p>关系</p>"
          },
          {
            "group": "CustomerFamilyBean",
            "type": "String",
            "optional": false,
            "field": "customerFamilyBean.realName",
            "description": "<p>真实姓名</p>"
          },
          {
            "group": "CustomerFamilyBean",
            "type": "Integer",
            "optional": true,
            "field": "customerFamilyBean.birthday",
            "description": "<p>生日</p>"
          },
          {
            "group": "CustomerFamilyBean",
            "type": "Long",
            "optional": true,
            "field": "customerFamilyBean.phone",
            "description": "<p>手机</p>"
          },
          {
            "group": "CustomerFamilyBean",
            "type": "Long",
            "optional": true,
            "field": "customerFamilyBean.areaId",
            "description": "<p>区域</p>"
          },
          {
            "group": "CustomerFamilyBean",
            "type": "String",
            "optional": true,
            "field": "customerFamilyBean.address",
            "description": "<p>地址</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerFamilyBean\":{\"customerId\":2,\"realName\":\"xxx\",\"phone\":15111111111,\"relationship\":2,\"birthday\":20100101,\"areaId\":321200,\"address\":\"ttt\"}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "customerFamilyBean",
            "description": "<p>家庭 bean</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "customerFamilyBeanList.id",
            "description": "<p>ID</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "customerFamilyBeanList.relationship",
            "description": "<p>关系</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerFamilyBeanList.relationshipValue",
            "description": "<p>关系 值</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerFamilyBeanList.realName",
            "description": "<p>真实姓名</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "customerFamilyBeanList.birthday",
            "description": "<p>生日 yyyyMMdd</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "customerFamilyBeanList.phone",
            "description": "<p>手机</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "customerFamilyBeanList.areaId",
            "description": "<p>区域</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerFamilyBeanList.areaNameAll",
            "description": "<p>区域全名</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerFamilyBeanList.address",
            "description": "<p>地址</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"customerFamilyBean\":{\"areaNameAll\":\"江苏省泰州市\",\"relationshipValue\":\"母亲\",\"id\":2,\"relationship\":2,\"realName\":\"xxx\",\"birthday\":20100101,\"phone\":15111111111,\"areaId\":321200,\"address\":\"ttt\"}}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerFamilyController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/family/update",
    "title": "客户 家庭 修改",
    "description": "<p>客户 家庭 修改</p>",
    "name": "customer_family_update",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "CustomerFamilyBean": [
          {
            "group": "CustomerFamilyBean",
            "type": "Object",
            "optional": false,
            "field": "customerFamilyBean",
            "description": "<p>家庭 bean</p>"
          },
          {
            "group": "CustomerFamilyBean",
            "type": "Long",
            "optional": false,
            "field": "customerFamilyBean.id",
            "description": "<p>ID</p>"
          },
          {
            "group": "CustomerFamilyBean",
            "type": "Long",
            "optional": false,
            "field": "customerFamilyBean.customerId",
            "description": "<p>客户ID</p>"
          },
          {
            "group": "CustomerFamilyBean",
            "type": "Integer",
            "optional": false,
            "field": "customerFamilyBean.relationship",
            "description": "<p>关系</p>"
          },
          {
            "group": "CustomerFamilyBean",
            "type": "String",
            "optional": false,
            "field": "customerFamilyBean.realName",
            "description": "<p>真实姓名</p>"
          },
          {
            "group": "CustomerFamilyBean",
            "type": "Integer",
            "optional": true,
            "field": "customerFamilyBean.birthday",
            "description": "<p>生日 yyyyMMdd</p>"
          },
          {
            "group": "CustomerFamilyBean",
            "type": "Long",
            "optional": true,
            "field": "customerFamilyBean.phone",
            "description": "<p>手机</p>"
          },
          {
            "group": "CustomerFamilyBean",
            "type": "Long",
            "optional": true,
            "field": "customerFamilyBean.areaId",
            "description": "<p>区域</p>"
          },
          {
            "group": "CustomerFamilyBean",
            "type": "String",
            "optional": true,
            "field": "customerFamilyBean.address",
            "description": "<p>地址</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerFamilyBean\":{\"id\":1,\"customerId\":2,\"realName\":\"xxx\",\"phone\":15111111111,\"relationship\":2,\"birthday\":20100101,\"areaId\":321200,\"address\":\"ttt\"}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerFamilyController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/hobby/config",
    "title": "客户 兴趣爱好 配置",
    "description": "<p>客户 兴趣爱好 配置</p>",
    "name": "customer_hobby_config",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "interestCodeList",
            "description": "<p>兴趣 code list</p>"
          },
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "dietCodeList",
            "description": "<p>饮食 code list</p>"
          },
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "tasteCodeList",
            "description": "<p>口味 code list</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"dietCodeList\":[{\"id\":69,\"key\":\"1\",\"value\":\"川湘菜\",\"pid\":68,\"sort\":1},{\"id\":70,\"key\":\"2\",\"value\":\"江浙菜\",\"pid\":68,\"sort\":2},{\"id\":71,\"key\":\"3\",\"value\":\"粤菜\",\"pid\":68,\"sort\":3},{\"id\":72,\"key\":\"4\",\"value\":\"北方菜\",\"pid\":68,\"sort\":4},{\"id\":73,\"key\":\"5\",\"value\":\"日韩料理\",\"pid\":68,\"sort\":5},{\"id\":74,\"key\":\"6\",\"value\":\"西餐\",\"pid\":68,\"sort\":6},{\"id\":75,\"key\":\"7\",\"value\":\"东南亚菜\",\"pid\":68,\"sort\":7},{\"id\":76,\"key\":\"8\",\"value\":\"火锅\",\"pid\":68,\"sort\":8},{\"id\":77,\"key\":\"9\",\"value\":\"海鲜\",\"pid\":68,\"sort\":9},{\"id\":78,\"key\":\"10\",\"value\":\"素食\",\"pid\":68,\"sort\":10},{\"id\":79,\"key\":\"11\",\"value\":\"烧烤\",\"pid\":68,\"sort\":11},{\"id\":80,\"key\":\"12\",\"value\":\"甜点\",\"pid\":68,\"sort\":12}],\"interestCodeList\":[{\"id\":48,\"key\":\"1\",\"value\":\"美食\",\"pid\":47,\"sort\":1},{\"id\":49,\"key\":\"2\",\"value\":\"旅游\",\"pid\":47,\"sort\":2},{\"id\":50,\"key\":\"3\",\"value\":\"美容美发\",\"pid\":47,\"sort\":3},{\"id\":51,\"key\":\"4\",\"value\":\"购物\",\"pid\":47,\"sort\":4},{\"id\":52,\"key\":\"5\",\"value\":\"按摩温泉\",\"pid\":47,\"sort\":5},{\"id\":53,\"key\":\"6\",\"value\":\"影视\",\"pid\":47,\"sort\":6},{\"id\":54,\"key\":\"7\",\"value\":\"运动\",\"pid\":47,\"sort\":7},{\"id\":55,\"key\":\"8\",\"value\":\"汽车\",\"pid\":47,\"sort\":8},{\"id\":56,\"key\":\"9\",\"value\":\"家居装饰\",\"pid\":47,\"sort\":9},{\"id\":57,\"key\":\"10\",\"value\":\"宠物\",\"pid\":47,\"sort\":10},{\"id\":58,\"key\":\"11\",\"value\":\"KTV\",\"pid\":47,\"sort\":11},{\"id\":59,\"key\":\"12\",\"value\":\"社交\",\"pid\":47,\"sort\":12},{\"id\":60,\"key\":\"13\",\"value\":\"养生\",\"pid\":47,\"sort\":13},{\"id\":61,\"key\":\"14\",\"value\":\"投资理财\",\"pid\":47,\"sort\":14},{\"id\":62,\"key\":\"15\",\"value\":\"营销\",\"pid\":47,\"sort\":15},{\"id\":63,\"key\":\"16\",\"value\":\"IT互联网\",\"pid\":47,\"sort\":16},{\"id\":64,\"key\":\"17\",\"value\":\"演出\",\"pid\":47,\"sort\":17},{\"id\":65,\"key\":\"18\",\"value\":\"外语学习\",\"pid\":47,\"sort\":18},{\"id\":66,\"key\":\"19\",\"value\":\"体验游戏\",\"pid\":47,\"sort\":19},{\"id\":67,\"key\":\"20\",\"value\":\"网络游戏\",\"pid\":47,\"sort\":20}],\"tasteCodeList\":[{\"id\":82,\"key\":\"1\",\"value\":\"甜\",\"pid\":81,\"sort\":1},{\"id\":83,\"key\":\"2\",\"value\":\"辣\",\"pid\":81,\"sort\":2},{\"id\":84,\"key\":\"3\",\"value\":\"酸\",\"pid\":81,\"sort\":3},{\"id\":85,\"key\":\"4\",\"value\":\"苦\",\"pid\":81,\"sort\":4}]}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerHobbyController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/hobby/get",
    "title": "客户 兴趣爱好 查询",
    "description": "<p>客户 兴趣爱好 查询</p>",
    "name": "customer_hobby_get",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "CustomerHobbyBean": [
          {
            "group": "CustomerHobbyBean",
            "type": "Object",
            "optional": false,
            "field": "customerHobbyBean",
            "description": "<p>兴趣爱好 bean</p>"
          },
          {
            "group": "CustomerHobbyBean",
            "type": "Long",
            "optional": false,
            "field": "customerHobbyBean.customerId",
            "description": "<p>客户ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerHobbyBean\":{\"customerId\":2}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "customerHobbyBean",
            "description": "<p>兴趣爱好 bean</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerFamilyBean.interest",
            "description": "<p>兴趣</p>"
          },
          {
            "group": "data",
            "type": "SysCodeBean",
            "optional": false,
            "field": "customerFamilyBean.interestCodeList",
            "description": "<p>兴趣 code list</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerFamilyBean.diet",
            "description": "<p>饮食</p>"
          },
          {
            "group": "data",
            "type": "SysCodeBean",
            "optional": false,
            "field": "customerFamilyBean.dietCodeList",
            "description": "<p>饮食 code list</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerFamilyBean.taste",
            "description": "<p>口味</p>"
          },
          {
            "group": "data",
            "type": "SysCodeBean",
            "optional": false,
            "field": "customerFamilyBean.tasteCodeList",
            "description": "<p>口味 code list</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"customerHobbyBean\":{\"interestCodeList\":[{\"key\":\"1\",\"value\":\"美食\"}],\"dietCodeList\":[{\"key\":\"1\",\"value\":\"川湘菜\"},{\"key\":\"3\",\"value\":\"粤菜\"}],\"tasteCodeList\":[{\"key\":\"4\",\"value\":\"苦\"}],\"interest\":\"1\",\"diet\":\"1,3\",\"taste\":\"4\"}}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerHobbyController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/hobby/update",
    "title": "客户 兴趣爱好 修改",
    "description": "<p>客户 兴趣爱好 修改</p>",
    "name": "customer_hobby_update",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "customerHobbyBean",
            "description": "<p>兴趣爱好 bean</p>"
          },
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "customerFamilyBean.customerId",
            "description": "<p>客户ID</p>"
          },
          {
            "group": "data",
            "type": "SysCodeBean",
            "optional": true,
            "field": "customerFamilyBean.interestCodeList",
            "description": "<p>兴趣 code list</p>"
          },
          {
            "group": "data",
            "type": "SysCodeBean",
            "optional": true,
            "field": "customerFamilyBean.dietCodeList",
            "description": "<p>饮食 code list</p>"
          },
          {
            "group": "data",
            "type": "SysCodeBean",
            "optional": true,
            "field": "customerFamilyBean.tasteCodeList",
            "description": "<p>口味 code list</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerHobbyBean\":{\"customerId\":2,\"interestCodeList\":[{\"key\":\"1\"}],\"dietCodeList\":[{\"key\":\"1\"},{\"key\":\"3\"}],\"tasteCodeList\":[{\"key\":\"4\"}]}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerHobbyController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/job/config",
    "title": "客户 工作 配置",
    "description": "<p>客户 工作 配置</p>",
    "name": "customer_job_config",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "industryCodeList",
            "description": "<p>行业 code list</p>"
          },
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "positionCodeList",
            "description": "<p>职位 code list</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"industryCodeList\":[{\"id\":88,\"key\":\"1\",\"value\":\"计算机硬软件\",\"pid\":87,\"sort\":1},{\"id\":89,\"key\":\"2\",\"value\":\"互联网/电子商务/网游\",\"pid\":87,\"sort\":2},{\"id\":90,\"key\":\"3\",\"value\":\"IT管理\",\"pid\":87,\"sort\":3},{\"id\":91,\"key\":\"4\",\"value\":\"通信\",\"pid\":87,\"sort\":4},{\"id\":92,\"key\":\"5\",\"value\":\"电子/电器/半导体\",\"pid\":87,\"sort\":5},{\"id\":93,\"key\":\"6\",\"value\":\"财务/审计/税务\",\"pid\":87,\"sort\":6},{\"id\":94,\"key\":\"7\",\"value\":\"金融/投资\",\"pid\":87,\"sort\":7},{\"id\":95,\"key\":\"8\",\"value\":\"银行/保险\",\"pid\":87,\"sort\":8},{\"id\":96,\"key\":\"9\",\"value\":\"工程/机械\",\"pid\":87,\"sort\":9},{\"id\":97,\"key\":\"10\",\"value\":\"能源/原材料\",\"pid\":87,\"sort\":10},{\"id\":98,\"key\":\"11\",\"value\":\"汽车及零配件制造\",\"pid\":87,\"sort\":11},{\"id\":99,\"key\":\"12\",\"value\":\"汽车销售服务\",\"pid\":87,\"sort\":12},{\"id\":100,\"key\":\"13\",\"value\":\"服装/纺织\",\"pid\":87,\"sort\":13},{\"id\":101,\"key\":\"14\",\"value\":\"轻工产品制造\",\"pid\":87,\"sort\":14},{\"id\":102,\"key\":\"15\",\"value\":\"食品生产\",\"pid\":87,\"sort\":15},{\"id\":103,\"key\":\"16\",\"value\":\"贸易\",\"pid\":87,\"sort\":16},{\"id\":104,\"key\":\"17\",\"value\":\"物流/仓储\",\"pid\":87,\"sort\":17},{\"id\":105,\"key\":\"18\",\"value\":\"生物/制药\",\"pid\":87,\"sort\":18},{\"id\":106,\"key\":\"19\",\"value\":\"化工\",\"pid\":87,\"sort\":19},{\"id\":107,\"key\":\"20\",\"value\":\"医院/医疗/护理\",\"pid\":87,\"sort\":20},{\"id\":108,\"key\":\"21\",\"value\":\"广告媒体\",\"pid\":87,\"sort\":21},{\"id\":109,\"key\":\"22\",\"value\":\"市场/营销\",\"pid\":87,\"sort\":22},{\"id\":110,\"key\":\"23\",\"value\":\"影视\",\"pid\":87,\"sort\":23},{\"id\":111,\"key\":\"24\",\"value\":\"编辑出版\",\"pid\":87,\"sort\":24},{\"id\":112,\"key\":\"25\",\"value\":\"艺术/设计\",\"pid\":87,\"sort\":25},{\"id\":113,\"key\":\"26\",\"value\":\"建筑与装潢\",\"pid\":87,\"sort\":26},{\"id\":114,\"key\":\"27\",\"value\":\"房地产开发\",\"pid\":87,\"sort\":27},{\"id\":115,\"key\":\"28\",\"value\":\"房地产销售与中介\",\"pid\":87,\"sort\":28},{\"id\":116,\"key\":\"29\",\"value\":\"物业\",\"pid\":87,\"sort\":29},{\"id\":117,\"key\":\"30\",\"value\":\"人力资源\",\"pid\":87,\"sort\":30},{\"id\":118,\"key\":\"31\",\"value\":\"咨询/顾问\",\"pid\":87,\"sort\":31},{\"id\":119,\"key\":\"32\",\"value\":\"律师/法务\",\"pid\":87,\"sort\":32},{\"id\":120,\"key\":\"33\",\"value\":\"教师/培训\",\"pid\":87,\"sort\":33},{\"id\":121,\"key\":\"34\",\"value\":\"科研\",\"pid\":87,\"sort\":34},{\"id\":122,\"key\":\"35\",\"value\":\"餐饮服务\",\"pid\":87,\"sort\":35},{\"id\":123,\"key\":\"36\",\"value\":\"酒店旅游\",\"pid\":87,\"sort\":36},{\"id\":124,\"key\":\"37\",\"value\":\"美容保健\",\"pid\":87,\"sort\":37},{\"id\":125,\"key\":\"38\",\"value\":\"百货零售\",\"pid\":87,\"sort\":38},{\"id\":126,\"key\":\"39\",\"value\":\"交通运输\",\"pid\":87,\"sort\":39},{\"id\":127,\"key\":\"40\",\"value\":\"家政/生活服务\",\"pid\":87,\"sort\":40},{\"id\":128,\"key\":\"41\",\"value\":\"政府/公务员\",\"pid\":87,\"sort\":41},{\"id\":129,\"key\":\"42\",\"value\":\"翻译\",\"pid\":87,\"sort\":42},{\"id\":130,\"key\":\"43\",\"value\":\"农林牧渔\",\"pid\":87,\"sort\":43},{\"id\":131,\"key\":\"44\",\"value\":\"印刷包装\",\"pid\":87,\"sort\":44},{\"id\":132,\"key\":\"45\",\"value\":\"运动健身\",\"pid\":87,\"sort\":45},{\"id\":133,\"key\":\"46\",\"value\":\"休闲娱乐\",\"pid\":87,\"sort\":46},{\"id\":134,\"key\":\"47\",\"value\":\"其他\",\"pid\":87,\"sort\":47}],\"positionCodeList\":[{\"id\":136,\"key\":\"1\",\"value\":\"工薪族\",\"pid\":135,\"sort\":1},{\"id\":137,\"key\":\"2\",\"value\":\"个体户\",\"pid\":135,\"sort\":2},{\"id\":138,\"key\":\"3\",\"value\":\"企业主\",\"pid\":135,\"sort\":3},{\"id\":139,\"key\":\"4\",\"value\":\"学生\",\"pid\":135,\"sort\":4},{\"id\":140,\"key\":\"5\",\"value\":\"公务员\",\"pid\":135,\"sort\":5},{\"id\":141,\"key\":\"6\",\"value\":\"自由职业\",\"pid\":135,\"sort\":6},{\"id\":142,\"key\":\"7\",\"value\":\"无业\",\"pid\":135,\"sort\":7}]}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerJobController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/job/get",
    "title": "客户 工作 查询",
    "description": "<p>客户 工作 查询</p>",
    "name": "customer_job_get",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "CustomerJobBean": [
          {
            "group": "CustomerJobBean",
            "type": "Object",
            "optional": false,
            "field": "customerJobBean",
            "description": "<p>工作 bean</p>"
          },
          {
            "group": "CustomerJobBean",
            "type": "Long",
            "optional": true,
            "field": "customerJobBean.customerId",
            "description": "<p>客户ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerJobBean\":{\"customerId\":2}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "customerBean",
            "description": "<p>客户 bean</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerBean.realName",
            "description": "<p>真实姓名</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "customerBean.phone",
            "description": "<p>手机</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "customerBean.sex",
            "description": "<p>性别<br/>0:女<br/>1:男</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerBean.headPic",
            "description": "<p>头像</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "customerBean.areaId",
            "description": "<p>区域ID</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerBean.areaNameAll",
            "description": "<p>区域全称</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerBean.address",
            "description": "<p>地址</p>"
          },
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "customerJobBean",
            "description": "<p>工作 bean</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "customerJobBean.industry",
            "description": "<p>行业</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerJobBean.industryValue",
            "description": "<p>行业 值</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerJobBean.companyName",
            "description": "<p>公司</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerJobBean.departmentName",
            "description": "<p>部门</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "customerJobBean.position",
            "description": "<p>职位</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerJobBean.positionValue",
            "description": "<p>职位 值</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerJobBean.phone",
            "description": "<p>手机</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "customerJobBean.areaId",
            "description": "<p>区域ID</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerJobBean.areaNameAll",
            "description": "<p>区域全称</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerJobBean.address",
            "description": "<p>地址</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"customerJobBean\":{\"areaNameAll\":\"江苏省泰州市\",\"industryValue\":\"互联网/电子商务/网游\",\"positionValue\":\"工薪族\",\"industry\":2,\"companyName\":\"aaaa\",\"departmentName\":\"bbb\",\"position\":1,\"phone\":15111111111,\"areaId\":321200,\"address\":\"aaaaaaaaaaaaaa\"},\"customerBean\":{\"areaNameAll\":\"江苏省泰州市\",\"realName\":\"ttt\",\"phone\":15111111111,\"sex\":0,\"headPic\":\"/xxx/pic.jpg\",\"areaId\":321200,\"address\":\"aaaaaaaaaaaaaa\"}}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerJobController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/job/update",
    "title": "客户 工作 修改",
    "description": "<p>客户 工作 修改</p>",
    "name": "customer_job_update",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "CustomerJobBean": [
          {
            "group": "CustomerJobBean",
            "type": "Object",
            "optional": false,
            "field": "customerBean",
            "description": "<p>客户 bean</p>"
          },
          {
            "group": "CustomerJobBean",
            "type": "String",
            "optional": false,
            "field": "customerBean.realName",
            "description": "<p>真实姓名</p>"
          },
          {
            "group": "CustomerJobBean",
            "type": "Long",
            "optional": false,
            "field": "customerBean.phone",
            "description": "<p>手机</p>"
          },
          {
            "group": "CustomerJobBean",
            "type": "Integer",
            "optional": true,
            "field": "customerBean.sex",
            "description": "<p>性别<br/>0:女<br/>1:男</p>"
          },
          {
            "group": "CustomerJobBean",
            "type": "String",
            "optional": true,
            "field": "customerBean.headPic",
            "description": "<p>头像</p>"
          },
          {
            "group": "CustomerJobBean",
            "type": "Long",
            "optional": true,
            "field": "customerBean.areaId",
            "description": "<p>区域ID</p>"
          },
          {
            "group": "CustomerJobBean",
            "type": "String",
            "optional": true,
            "field": "customerBean.address",
            "description": "<p>地址</p>"
          },
          {
            "group": "CustomerJobBean",
            "type": "Object",
            "optional": false,
            "field": "customerJobBean",
            "description": "<p>工作 bean</p>"
          },
          {
            "group": "CustomerJobBean",
            "type": "Integer",
            "optional": true,
            "field": "customerJobBean.customerId",
            "description": "<p>客户ID</p>"
          },
          {
            "group": "CustomerJobBean",
            "type": "Integer",
            "optional": true,
            "field": "customerJobBean.industry",
            "description": "<p>行业</p>"
          },
          {
            "group": "CustomerJobBean",
            "type": "String",
            "optional": true,
            "field": "customerJobBean.companyName",
            "description": "<p>公司</p>"
          },
          {
            "group": "CustomerJobBean",
            "type": "String",
            "optional": true,
            "field": "customerJobBean.departmentName",
            "description": "<p>部门</p>"
          },
          {
            "group": "CustomerJobBean",
            "type": "Integer",
            "optional": true,
            "field": "customerJobBean.position",
            "description": "<p>职位</p>"
          },
          {
            "group": "CustomerJobBean",
            "type": "String",
            "optional": true,
            "field": "customerJobBean.phone",
            "description": "<p>手机</p>"
          },
          {
            "group": "CustomerJobBean",
            "type": "Long",
            "optional": true,
            "field": "customerJobBean.areaId",
            "description": "<p>区域ID</p>"
          },
          {
            "group": "CustomerJobBean",
            "type": "String",
            "optional": true,
            "field": "customerJobBean.address",
            "description": "<p>地址</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerBean\":{\"realName\":\"ttt\",\"phone\":15111111111,\"sex\":0,\"birthday\":20100101,\"headPic\":\"/xxx/pic.jpg\",\"areaId\":321200,\"address\":\"aaaaaaaaaaaaaa\"},\"customerJobBean\":{\"customerId\":4,\"industry\":2,\"companyName\":\"aaaa\",\"departmentName\":\"bbb\",\"position\":3,\"phone\":\"15111111111\",\"areaId\":321200,\"address\":\"aaaaaaaaaaaaaa\"}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "customerBean",
            "description": "<p>客户 bean</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "customerBean.id",
            "description": "<p>ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"customerBean\":{\"id\":4}}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerJobController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/maintain/config",
    "title": "客户 维护设置 配置",
    "description": "<p>客户 维护设置 配置</p>",
    "name": "customer_maintain_config",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "maintainCodeList",
            "description": "<p>维护设置 code list</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"maintainCodeList\":[{\"id\":168,\"key\":\"0\",\"value\":\"手动\",\"pid\":167,\"sort\":0},{\"id\":169,\"key\":\"1\",\"value\":\"自动\",\"pid\":167,\"sort\":1}]}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerMaintainController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/maintain/get",
    "title": "客户 维护设置 查询",
    "description": "<p>客户 维护设置 查询</p>",
    "name": "customer_maintain_get",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "CustomerMaintainBean": [
          {
            "group": "CustomerMaintainBean",
            "type": "Object",
            "optional": false,
            "field": "customerMaintainBean",
            "description": "<p>工作 bean</p>"
          },
          {
            "group": "CustomerMaintainBean",
            "type": "Long",
            "optional": false,
            "field": "customerMaintainBean.customerId",
            "description": "<p>客户ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerMaintainBean\":{\"customerId\":2}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "customerMaintainBean",
            "description": "<p>客户 bean</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "customerMaintainBean.maintain",
            "description": "<p>维护设置</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerMaintainBean.maintainValue",
            "description": "<p>维护设置 值</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"customerMaintainBean\":{\"maintainValue\":\"手动\",\"maintain\":0}}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerMaintainController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/maintain/update",
    "title": "客户 维护设置 修改",
    "description": "<p>客户 维护设置 修改</p>",
    "name": "customer_maintain_update",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "CustomerMaintainBean": [
          {
            "group": "CustomerMaintainBean",
            "type": "Object",
            "optional": false,
            "field": "customerMaintainBean",
            "description": "<p>工作 bean</p>"
          },
          {
            "group": "CustomerMaintainBean",
            "type": "Long",
            "optional": false,
            "field": "customerMaintainBean.customerId",
            "description": "<p>客户ID</p>"
          },
          {
            "group": "CustomerMaintainBean",
            "type": "Integer",
            "optional": false,
            "field": "customerMaintainBean.maintain",
            "description": "<p>维护</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerMaintainBean\":{\"maintain\":0}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerMaintainController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/memorialDay/config",
    "title": "客户 纪念日 配置",
    "description": "<p>客户 纪念日 配置</p>",
    "name": "customer_memorialDay_config",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "occurTypeCodeList",
            "description": "<p>发生类型 code list</p>"
          },
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "advanceTypeCodeList",
            "description": "<p>提前类型 code list</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"advanceTypeCodeList\":[{\"id\":159,\"key\":\"1\",\"value\":\"1天\",\"pid\":158,\"sort\":0},{\"id\":160,\"key\":\"2\",\"value\":\"2天\",\"pid\":158,\"sort\":0},{\"id\":161,\"key\":\"3\",\"value\":\"3天\",\"pid\":158,\"sort\":0},{\"id\":162,\"key\":\"4\",\"value\":\"5天\",\"pid\":158,\"sort\":0},{\"id\":163,\"key\":\"5\",\"value\":\"1周\",\"pid\":158,\"sort\":0},{\"id\":164,\"key\":\"6\",\"value\":\"2周\",\"pid\":158,\"sort\":0},{\"id\":165,\"key\":\"7\",\"value\":\"1月\",\"pid\":158,\"sort\":0}],\"occurTypeCodeList\":[{\"id\":153,\"key\":\"1\",\"value\":\"1次\",\"pid\":152,\"sort\":0},{\"id\":154,\"key\":\"2\",\"value\":\"每年\",\"pid\":152,\"sort\":0},{\"id\":155,\"key\":\"3\",\"value\":\"每月\",\"pid\":152,\"sort\":0},{\"id\":157,\"key\":\"4\",\"value\":\"每周\",\"pid\":152,\"sort\":0}]}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerMemorialDayController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/memorialDay/delete",
    "title": "客户 纪念日 删除",
    "description": "<p>客户 纪念日 删除</p>",
    "name": "customer_memorialDay_delete",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "CustomerMemorialDayBean": [
          {
            "group": "CustomerMemorialDayBean",
            "type": "Object",
            "optional": false,
            "field": "customerMemorialDayBean",
            "description": "<p>家庭 bean</p>"
          },
          {
            "group": "CustomerMemorialDayBean",
            "type": "Long",
            "optional": false,
            "field": "customerMemorialDayBean.id",
            "description": "<p>ID</p>"
          },
          {
            "group": "CustomerMemorialDayBean",
            "type": "Long",
            "optional": false,
            "field": "customerMemorialDayBean.customerId",
            "description": "<p>客户ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerMemorialDayBean\":{\"id\":1,\"customerId\":2}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerMemorialDayController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/memorialDay/get",
    "title": "客户 纪念日 查询",
    "description": "<p>客户 纪念日 查询</p>",
    "name": "customer_memorialDay_get",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "CustomerMemorialDayBean": [
          {
            "group": "CustomerMemorialDayBean",
            "type": "Object",
            "optional": false,
            "field": "customerMemorialDayBean",
            "description": "<p>家庭 bean</p>"
          },
          {
            "group": "CustomerMemorialDayBean",
            "type": "Long",
            "optional": false,
            "field": "customerMemorialDayBean.id",
            "description": "<p>ID</p>"
          },
          {
            "group": "CustomerMemorialDayBean",
            "type": "Long",
            "optional": false,
            "field": "customerMemorialDayBean.customerId",
            "description": "<p>客户ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerMemorialDayBean\":{\"id\":1,\"customerId\":2}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "customerMemorialDayBean",
            "description": "<p>家庭 bean</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "customerMemorialDayBean.id",
            "description": "<p>ID</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerMemorialDayBean.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "customerMemorialDayBean.occurType",
            "description": "<p>发生类型</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerMemorialDayBean.occurTypeValue",
            "description": "<p>发生类型 值</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "customerMemorialDayBean.occurDate",
            "description": "<p>关系日期</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "customerMemorialDayBean.advanceType",
            "description": "<p>提前类型</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerMemorialDayBean.advanceTypeValue",
            "description": "<p>提前类型 值</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"customerMemorialDayBean\":{\"occurTypeValue\":\"1次\",\"advanceTypeValue\":\"1天\",\"id\":1,\"name\":\"ttttt2\",\"occurType\":1,\"occurDate\":20181030,\"advanceType\":1}}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerMemorialDayController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/memorialDay/list",
    "title": "客户 纪念日 列表 查询",
    "description": "<p>客户 纪念日 查询</p>",
    "name": "customer_memorialDay_list",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "CustomerMemorialDayBean": [
          {
            "group": "CustomerMemorialDayBean",
            "type": "Object",
            "optional": false,
            "field": "customerMemorialDayBean",
            "description": "<p>纪念日 bean</p>"
          },
          {
            "group": "CustomerMemorialDayBean",
            "type": "Long",
            "optional": false,
            "field": "customerMemorialDayBean.customerId",
            "description": "<p>客户ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerMemorialDayBean\":{\"customerId\":2}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "customerMemorialDayBeanList",
            "description": "<p>家庭 bean list</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "customerMemorialDayBeanList.id",
            "description": "<p>ID</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerMemorialDayBeanList.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "customerMemorialDayBeanList.occurType",
            "description": "<p>发生类型</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerMemorialDayBeanList.occurTypeValue",
            "description": "<p>发生类型 值</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "customerMemorialDayBeanList.occurDate",
            "description": "<p>关系日期<br/>occurType=1 yyyyMMdd 年月日<br/>occurType=2 MMdd 月日<br/>occurType=3 {1-31}天<br/>occurType=4 {1-7}周</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "customerMemorialDayBeanList.advanceType",
            "description": "<p>提前类型</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerMemorialDayBeanList.advanceTypeValue",
            "description": "<p>提前类型 值</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"customerMemorialDayBeanList\":[{\"occurTypeValue\":\"1次\",\"advanceTypeValue\":\"1天\",\"id\":2,\"name\":\"ttttt2\",\"occurType\":1,\"occurDate\":20181030,\"advanceType\":1},{\"occurTypeValue\":\"1次\",\"advanceTypeValue\":\"1天\",\"id\":1,\"name\":\"ttttt2\",\"occurType\":1,\"occurDate\":20181030,\"advanceType\":1}]}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerMemorialDayController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/memorialDay/save",
    "title": "客户 纪念日 添加",
    "description": "<p>客户 纪念日 添加</p>",
    "name": "customer_memorialDay_save",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "CustomerMemorialDayBean": [
          {
            "group": "CustomerMemorialDayBean",
            "type": "Object",
            "optional": false,
            "field": "customerMemorialDayBean",
            "description": "<p>家庭 bean</p>"
          },
          {
            "group": "CustomerMemorialDayBean",
            "type": "Long",
            "optional": false,
            "field": "customerMemorialDayBean.customerId",
            "description": "<p>客户ID</p>"
          },
          {
            "group": "CustomerMemorialDayBean",
            "type": "String",
            "optional": false,
            "field": "customerMemorialDayBean.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "CustomerMemorialDayBean",
            "type": "Integer",
            "optional": false,
            "field": "customerMemorialDayBean.occurType",
            "description": "<p>发生类型</p>"
          },
          {
            "group": "CustomerMemorialDayBean",
            "type": "Integer",
            "optional": false,
            "field": "customerMemorialDayBean.occurDate",
            "description": "<p>关系日期</p>"
          },
          {
            "group": "CustomerMemorialDayBean",
            "type": "Integer",
            "optional": false,
            "field": "customerMemorialDayBean.advanceType",
            "description": "<p>提前类型</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerMemorialDayBean\":{\"customerId\":2,\"name\":\"ttttt\",\"occurType\":1,\"occurDate\":20181030,\"advanceType\":1}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "customerMemorialDayBean",
            "description": "<p>家庭 bean</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "customerMemorialDayBean.id",
            "description": "<p>ID</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerMemorialDayBean.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "customerMemorialDayBean.occurType",
            "description": "<p>发生类型</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerMemorialDayBean.occurTypeValue",
            "description": "<p>发生类型 值</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "customerMemorialDayBean.occurDate",
            "description": "<p>关系日期</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "customerMemorialDayBean.advanceType",
            "description": "<p>提前类型</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerMemorialDayBean.advanceTypeValue",
            "description": "<p>提前类型 值</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"customerMemorialDayBean\":{\"occurTypeValue\":\"1次\",\"advanceTypeValue\":\"1天\",\"id\":1,\"name\":\"ttttt\",\"occurType\":1,\"occurDate\":20181030,\"advanceType\":1}}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerMemorialDayController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/memorialDay/update",
    "title": "客户 纪念日 修改",
    "description": "<p>客户 纪念日 修改</p>",
    "name": "customer_memorialDay_update",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "CustomerMemorialDayBean": [
          {
            "group": "CustomerMemorialDayBean",
            "type": "Object",
            "optional": false,
            "field": "customerMemorialDayBean",
            "description": "<p>家庭 bean</p>"
          },
          {
            "group": "CustomerMemorialDayBean",
            "type": "Long",
            "optional": false,
            "field": "customerMemorialDayBean.id",
            "description": "<p>ID</p>"
          },
          {
            "group": "CustomerMemorialDayBean",
            "type": "Long",
            "optional": false,
            "field": "customerMemorialDayBean.customerId",
            "description": "<p>客户ID</p>"
          },
          {
            "group": "CustomerMemorialDayBean",
            "type": "String",
            "optional": false,
            "field": "customerMemorialDayBean.name",
            "description": "<p>名称</p>"
          },
          {
            "group": "CustomerMemorialDayBean",
            "type": "Integer",
            "optional": false,
            "field": "customerMemorialDayBean.occurType",
            "description": "<p>发生类型</p>"
          },
          {
            "group": "CustomerMemorialDayBean",
            "type": "Integer",
            "optional": false,
            "field": "customerMemorialDayBean.occurDate",
            "description": "<p>关系日期</p>"
          },
          {
            "group": "CustomerMemorialDayBean",
            "type": "Integer",
            "optional": false,
            "field": "customerMemorialDayBean.advanceType",
            "description": "<p>提前类型</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerMemorialDayBean\":{\"id\":1,\"customerId\":2,\"name\":\"ttttt\",\"occurType\":1,\"occurDate\":20181030,\"advanceType\":1}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerMemorialDayController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/problem/config",
    "title": "客户 可能问题 配置",
    "description": "<p>客户 可能问题 配置</p>",
    "name": "customer_problem_config",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "healthCodeList",
            "description": "<p>健康 code list</p>"
          },
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "lifeCodeList",
            "description": "<p>生活 code list</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"healthCodeList\":[{\"id\":25,\"key\":\"1\",\"value\":\"心脏病\",\"pid\":24,\"sort\":1},{\"id\":26,\"key\":\"2\",\"value\":\"动脉硬化\",\"pid\":24,\"sort\":2},{\"id\":27,\"key\":\"3\",\"value\":\"高血压\",\"pid\":24,\"sort\":3},{\"id\":28,\"key\":\"4\",\"value\":\"高血脂\",\"pid\":24,\"sort\":4},{\"id\":29,\"key\":\"5\",\"value\":\"肠胃病\",\"pid\":24,\"sort\":5},{\"id\":30,\"key\":\"6\",\"value\":\"糖尿病\",\"pid\":24,\"sort\":6},{\"id\":31,\"key\":\"7\",\"value\":\"关节炎\",\"pid\":24,\"sort\":7},{\"id\":32,\"key\":\"8\",\"value\":\"肥胖症\",\"pid\":24,\"sort\":8},{\"id\":33,\"key\":\"9\",\"value\":\"胆结石\",\"pid\":24,\"sort\":9},{\"id\":34,\"key\":\"10\",\"value\":\"肾病\",\"pid\":24,\"sort\":10},{\"id\":35,\"key\":\"11\",\"value\":\"精神问题\",\"pid\":24,\"sort\":11},{\"id\":36,\"key\":\"12\",\"value\":\"脸部痘痕\",\"pid\":24,\"sort\":12},{\"id\":37,\"key\":\"13\",\"value\":\"五官瑕疵\",\"pid\":24,\"sort\":13}],\"lifeCodeList\":[{\"id\":39,\"key\":\"1\",\"value\":\"资金缺乏\",\"pid\":38,\"sort\":1},{\"id\":40,\"key\":\"2\",\"value\":\"寻找工作\",\"pid\":38,\"sort\":2},{\"id\":41,\"key\":\"3\",\"value\":\"事业发展\",\"pid\":38,\"sort\":3},{\"id\":42,\"key\":\"4\",\"value\":\"感情困扰\",\"pid\":38,\"sort\":4},{\"id\":43,\"key\":\"5\",\"value\":\"子女学习\",\"pid\":38,\"sort\":5},{\"id\":44,\"key\":\"6\",\"value\":\"法律问题\",\"pid\":38,\"sort\":6},{\"id\":45,\"key\":\"7\",\"value\":\"税务\",\"pid\":38,\"sort\":7}]}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerProblemController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/problem/get",
    "title": "客户 可能问题 查询",
    "description": "<p>客户 可能问题 查询</p>",
    "name": "customer_problem_get",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "CustomerProblemBean": [
          {
            "group": "CustomerProblemBean",
            "type": "Object",
            "optional": false,
            "field": "customerProblemBean",
            "description": "<p>可能问题 bean</p>"
          },
          {
            "group": "CustomerProblemBean",
            "type": "Long",
            "optional": false,
            "field": "customerProblemBean.customerId",
            "description": "<p>客户ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerProblemBean\":{\"customerId\":2}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "customerProblemBean",
            "description": "<p>可能问题 bean</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerProblemBean.health",
            "description": "<p>健康</p>"
          },
          {
            "group": "data",
            "type": "SysCodeBean",
            "optional": false,
            "field": "customerProblemBean.healthCodeList",
            "description": "<p>健康 code list</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerProblemBean.life",
            "description": "<p>生活</p>"
          },
          {
            "group": "data",
            "type": "SysCodeBean",
            "optional": false,
            "field": "customerProblemBean.lifeCodeList",
            "description": "<p>生活 code list</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerProblemBean.remark",
            "description": "<p>备注</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"customerProblemBean\":{\"healthValueList\":[{\"key\":\"1\",\"value\":\"心脏病\"}],\"lifeValueList\":[{\"key\":\"1\",\"value\":\"资金缺乏\"},{\"key\":\"3\",\"value\":\"事业发展\"}],\"health\":\"1\",\"life\":\"1,3\",\"remark\":\"xxxxxxxxxTTT\"}}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerProblemController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/problem/update",
    "title": "客户 可能问题 修改",
    "description": "<p>客户 可能问题 修改</p>",
    "name": "customer_problem_update",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "customerProblemBean",
            "description": "<p>可能问题 bean</p>"
          },
          {
            "group": "data",
            "type": "SysCodeBean",
            "optional": true,
            "field": "customerProblemBean.healtCodeList",
            "description": "<p>健康</p>"
          },
          {
            "group": "data",
            "type": "SysCodeBean",
            "optional": true,
            "field": "customerProblemBean.lifeCodeList",
            "description": "<p>生活</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": true,
            "field": "customerProblemBean.remark",
            "description": "<p>备注</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerProblemBean\":{\"customerId\":2,\"healthValueList\":[{\"key\":\"1\"}],\"lifeValueList\":[{\"key\":\"1\"},{\"key\":\"3\"}],\"remark\":\"xxxxxxxxxTTT\"}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerProblemController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/relation/config",
    "title": "客户 关系 配置",
    "description": "<p>客户 关系 配置</p>",
    "name": "customer_relation_config",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "importanceCodeList",
            "description": "<p>关系 code list</p>"
          },
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "intimacyCodeList",
            "description": "<p>亲密 code list</p>"
          },
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "relationshipCodeList",
            "description": "<p>重要 code list</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"intimacyCodeList\":[{\"id\":14,\"key\":\"0\",\"value\":\"不详\",\"pid\":13,\"sort\":0},{\"id\":15,\"key\":\"1\",\"value\":\"很亲近\",\"pid\":13,\"sort\":1},{\"id\":16,\"key\":\"2\",\"value\":\"一般亲近\",\"pid\":13,\"sort\":2},{\"id\":17,\"key\":\"3\",\"value\":\"正常交往\",\"pid\":13,\"sort\":3},{\"id\":18,\"key\":\"4\",\"value\":\"点头之交\",\"pid\":13,\"sort\":4}],\"relationshipCodeList\":[{\"id\":4,\"key\":\"0\",\"value\":\"其他\",\"pid\":3,\"sort\":0},{\"id\":5,\"key\":\"1\",\"value\":\"家人\",\"pid\":3,\"sort\":1},{\"id\":6,\"key\":\"2\",\"value\":\"亲戚\",\"pid\":3,\"sort\":2},{\"id\":7,\"key\":\"3\",\"value\":\"朋友\",\"pid\":3,\"sort\":3},{\"id\":8,\"key\":\"4\",\"value\":\"同学\",\"pid\":3,\"sort\":4},{\"id\":9,\"key\":\"5\",\"value\":\"同事\",\"pid\":3,\"sort\":5},{\"id\":10,\"key\":\"6\",\"value\":\"客户\",\"pid\":3,\"sort\":6},{\"id\":11,\"key\":\"7\",\"value\":\"熟人\",\"pid\":3,\"sort\":7},{\"id\":12,\"key\":\"8\",\"value\":\"陌生人\",\"pid\":3,\"sort\":8}],\"importanceCodeList\":[{\"id\":20,\"key\":\"0\",\"value\":\"不重要\",\"pid\":19,\"sort\":0},{\"id\":21,\"key\":\"1\",\"value\":\"重要\",\"pid\":19,\"sort\":1},{\"id\":22,\"key\":\"2\",\"value\":\"非常重要（vip）\",\"pid\":19,\"sort\":2}]}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerRelationController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/relation/get",
    "title": "客户 关系 查询",
    "description": "<p>客户 关系 查询</p>",
    "name": "customer_relation_get",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "CustomerRelationBean": [
          {
            "group": "CustomerRelationBean",
            "type": "Object",
            "optional": false,
            "field": "customerRelationBean",
            "description": "<p>工作 bean</p>"
          },
          {
            "group": "CustomerRelationBean",
            "type": "Long",
            "optional": false,
            "field": "customerRelationBean.customerId",
            "description": "<p>客户ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerRelationBean\":{\"customerId\":2}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "customerRelationBean",
            "description": "<p>客户 bean</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "customerRelationBean.importance",
            "description": "<p>关系</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerRelationBean.importanceValue",
            "description": "<p>关系 值</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "customerRelationBean.intimacy",
            "description": "<p>亲密</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerRelationBean.intimacyValue",
            "description": "<p>亲密 值</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "customerRelationBean.relationship",
            "description": "<p>重要</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "customerRelationBean.relationshipValue",
            "description": "<p>重要 值</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"customerRelationBean\":{\"relationshipValue\":\"其他\",\"intimacyValue\":\"不详\",\"importanceValue\":\"不重要\",\"relationship\":0,\"intimacy\":0,\"importance\":0}}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerRelationController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/api/customer/relation/update",
    "title": "客户 关系 修改",
    "description": "<p>客户 关系 修改</p>",
    "name": "customer_relation_update",
    "group": "group_customer",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "CustomerRelationBean": [
          {
            "group": "CustomerRelationBean",
            "type": "Object",
            "optional": false,
            "field": "customerRelationBean",
            "description": "<p>工作 bean</p>"
          },
          {
            "group": "CustomerRelationBean",
            "type": "Long",
            "optional": false,
            "field": "customerRelationBean.customerId",
            "description": "<p>客户ID</p>"
          },
          {
            "group": "CustomerRelationBean",
            "type": "Integer",
            "optional": false,
            "field": "customerRelationBean.importance",
            "description": "<p>关系</p>"
          },
          {
            "group": "CustomerRelationBean",
            "type": "Integer",
            "optional": false,
            "field": "customerRelationBean.intimacy",
            "description": "<p>亲密</p>"
          },
          {
            "group": "CustomerRelationBean",
            "type": "Integer",
            "optional": false,
            "field": "customerRelationBean.relationship",
            "description": "<p>重要</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"customerRelationBean\":{\"customerId\":2,\"relationship\":0,\"intimacy\":0,\"importance\":0}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/customer/CustomerRelationController.java",
    "groupTitle": "客户"
  },
  {
    "type": "post",
    "url": "/example",
    "title": "示例",
    "description": "<p>示例</p>",
    "name": "example",
    "group": "group_example",
    "version": "1.0.0",
    "parameter": {
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"6e428093e8104f379c484faa9715a1b8\"},\"queryPage\":{\"targetPage\":1}}",
          "type": "json"
        }
      ],
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "Object",
            "optional": false,
            "field": "header",
            "description": "<p>公共参数</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": true,
            "field": "header.token",
            "description": "<p>token</p>"
          }
        ],
        "QueryPage": [
          {
            "group": "QueryPage",
            "type": "Object",
            "optional": false,
            "field": "queryPage",
            "description": "<p>分页 bean</p>"
          },
          {
            "group": "QueryPage",
            "type": "Integer",
            "size": "1..",
            "optional": false,
            "field": "queryPage.targetPage",
            "description": "<p>目标页</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "queryPage",
            "description": "<p>分页</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "queryPage.pageSize",
            "description": "<p>每页记录数</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "queryPage.pageCount",
            "description": "<p>总页数</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "queryPage.recordCount",
            "description": "<p>总记录数</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "queryPage.targetPage",
            "description": "<p>目标页</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"6e428093e8104f379c484faa9715a1b8\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"queryPage\":{\"pageSize\":20,\"pageCount\":17,\"recordCount\":331,\"targetPage\":1}}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/base/config/ApidocConfig.java",
    "groupTitle": "示例",
    "error": {
      "fields": {
        "200": [
          {
            "group": "200",
            "type": "String[]",
            "optional": false,
            "field": "msgs",
            "description": "<p>消息</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "msg",
            "description": "<p>消息</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "msg.code",
            "description": "<p>数字编号</p>"
          },
          {
            "group": "200",
            "type": "String",
            "optional": false,
            "field": "msg.des",
            "description": "<p>消息</p>"
          },
          {
            "group": "200",
            "type": "String",
            "allowedValues": [
              "0",
              "1"
            ],
            "optional": false,
            "field": "state",
            "description": "<p>状态<br/>0:成功<br/>1:失败</p>"
          },
          {
            "group": "200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "失败返回-示例:",
          "content": "{\"msgs\":[{\"code\":\"00003\",\"des\":\"数据为空\"}],\"msg\":{\"code\":\"00003\",\"des\":\"数据为空\"},\"state\":\"1\",\"data\":{}}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "post",
    "url": "/api/msg/phoneMsg/sendRegister",
    "title": "注册 发送短信",
    "description": "<p>注册 发送短信</p>",
    "name": "msg_phoneMsg_sendRegister",
    "group": "group_user",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "PhoneMsgBean": [
          {
            "group": "PhoneMsgBean",
            "type": "Object",
            "optional": false,
            "field": "phoneMsgBean",
            "description": "<p>手机短信 bean</p>"
          },
          {
            "group": "PhoneMsgBean",
            "type": "Long",
            "optional": false,
            "field": "phoneMsgBean.phone",
            "description": "<p>手机号</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"phoneMsgBean\":{\"phone\":15123815000}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "sendIntervalTime",
            "description": "<p>发送间隔时间（秒）</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"sendIntervalTime\":60}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/msg/PhoneMsgController.java",
    "groupTitle": "用户"
  },
  {
    "type": "post",
    "url": "/api/msg/phoneMsg/sendRetrievePwd",
    "title": "找回密码 发送短信",
    "description": "<p>找回密码 发送短信</p>",
    "name": "msg_phoneMsg_sendRetrievePwd",
    "group": "group_user",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "PhoneMsgBean": [
          {
            "group": "PhoneMsgBean",
            "type": "Object",
            "optional": false,
            "field": "phoneMsgBean",
            "description": "<p>手机短信 bean</p>"
          },
          {
            "group": "PhoneMsgBean",
            "type": "Long",
            "optional": false,
            "field": "phoneMsgBean.phone",
            "description": "<p>手机号</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"phoneMsgBean\":{\"phone\":15123815000}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "sendIntervalTime",
            "description": "<p>发送间隔时间（秒）</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"sendIntervalTime\":60}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/msg/PhoneMsgController.java",
    "groupTitle": "用户"
  },
  {
    "type": "post",
    "url": "/api/msg/phoneMsg/sendUpdatePhone",
    "title": "修改 手机 发送短信",
    "description": "<p>修改 手机 发送短信</p>",
    "name": "msg_phoneMsg_sendUpdatePhone",
    "group": "group_user",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "PhoneMsgBean": [
          {
            "group": "PhoneMsgBean",
            "type": "Object",
            "optional": false,
            "field": "phoneMsgBean",
            "description": "<p>手机短信 bean</p>"
          },
          {
            "group": "PhoneMsgBean",
            "type": "Long",
            "optional": false,
            "field": "phoneMsgBean.phone",
            "description": "<p>手机号</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"phoneMsgBean\":{\"phone\":15123815000}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "sendIntervalTime",
            "description": "<p>发送间隔时间（秒）</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"sendIntervalTime\":60}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/msg/PhoneMsgController.java",
    "groupTitle": "用户"
  },
  {
    "type": "post",
    "url": "/api/user/hobby/config",
    "title": "兴趣爱好 配置",
    "description": "<p>兴趣爱好 配置</p>",
    "name": "user_hobby_config",
    "group": "group_user",
    "version": "1.0.0",
    "parameter": {
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "interestCodeList",
            "description": "<p>兴趣 code list</p>"
          },
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "dietCodeList",
            "description": "<p>饮食 code list</p>"
          },
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "tasteCodeList",
            "description": "<p>口味 code list</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"dietCodeList\":[{\"id\":69,\"key\":\"1\",\"value\":\"川湘菜\",\"pid\":68,\"sort\":1},{\"id\":70,\"key\":\"2\",\"value\":\"江浙菜\",\"pid\":68,\"sort\":2},{\"id\":71,\"key\":\"3\",\"value\":\"粤菜\",\"pid\":68,\"sort\":3},{\"id\":72,\"key\":\"4\",\"value\":\"北方菜\",\"pid\":68,\"sort\":4},{\"id\":73,\"key\":\"5\",\"value\":\"日韩料理\",\"pid\":68,\"sort\":5},{\"id\":74,\"key\":\"6\",\"value\":\"西餐\",\"pid\":68,\"sort\":6},{\"id\":75,\"key\":\"7\",\"value\":\"东南亚菜\",\"pid\":68,\"sort\":7},{\"id\":76,\"key\":\"8\",\"value\":\"火锅\",\"pid\":68,\"sort\":8},{\"id\":77,\"key\":\"9\",\"value\":\"海鲜\",\"pid\":68,\"sort\":9},{\"id\":78,\"key\":\"10\",\"value\":\"素食\",\"pid\":68,\"sort\":10},{\"id\":79,\"key\":\"11\",\"value\":\"烧烤\",\"pid\":68,\"sort\":11},{\"id\":80,\"key\":\"12\",\"value\":\"甜点\",\"pid\":68,\"sort\":12}],\"interestCodeList\":[{\"id\":48,\"key\":\"1\",\"value\":\"美食\",\"pid\":47,\"sort\":1},{\"id\":49,\"key\":\"2\",\"value\":\"旅游\",\"pid\":47,\"sort\":2},{\"id\":50,\"key\":\"3\",\"value\":\"美容美发\",\"pid\":47,\"sort\":3},{\"id\":51,\"key\":\"4\",\"value\":\"购物\",\"pid\":47,\"sort\":4},{\"id\":52,\"key\":\"5\",\"value\":\"按摩温泉\",\"pid\":47,\"sort\":5},{\"id\":53,\"key\":\"6\",\"value\":\"影视\",\"pid\":47,\"sort\":6},{\"id\":54,\"key\":\"7\",\"value\":\"运动\",\"pid\":47,\"sort\":7},{\"id\":55,\"key\":\"8\",\"value\":\"汽车\",\"pid\":47,\"sort\":8},{\"id\":56,\"key\":\"9\",\"value\":\"家居装饰\",\"pid\":47,\"sort\":9},{\"id\":57,\"key\":\"10\",\"value\":\"宠物\",\"pid\":47,\"sort\":10},{\"id\":58,\"key\":\"11\",\"value\":\"KTV\",\"pid\":47,\"sort\":11},{\"id\":59,\"key\":\"12\",\"value\":\"社交\",\"pid\":47,\"sort\":12},{\"id\":60,\"key\":\"13\",\"value\":\"养生\",\"pid\":47,\"sort\":13},{\"id\":61,\"key\":\"14\",\"value\":\"投资理财\",\"pid\":47,\"sort\":14},{\"id\":62,\"key\":\"15\",\"value\":\"营销\",\"pid\":47,\"sort\":15},{\"id\":63,\"key\":\"16\",\"value\":\"IT互联网\",\"pid\":47,\"sort\":16},{\"id\":64,\"key\":\"17\",\"value\":\"演出\",\"pid\":47,\"sort\":17},{\"id\":65,\"key\":\"18\",\"value\":\"外语学习\",\"pid\":47,\"sort\":18},{\"id\":66,\"key\":\"19\",\"value\":\"体验游戏\",\"pid\":47,\"sort\":19},{\"id\":67,\"key\":\"20\",\"value\":\"网络游戏\",\"pid\":47,\"sort\":20}],\"tasteCodeList\":[{\"id\":82,\"key\":\"1\",\"value\":\"甜\",\"pid\":81,\"sort\":1},{\"id\":83,\"key\":\"2\",\"value\":\"辣\",\"pid\":81,\"sort\":2},{\"id\":84,\"key\":\"3\",\"value\":\"酸\",\"pid\":81,\"sort\":3},{\"id\":85,\"key\":\"4\",\"value\":\"苦\",\"pid\":81,\"sort\":4}]}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/user/UserHobbyController.java",
    "groupTitle": "用户"
  },
  {
    "type": "post",
    "url": "/api/user/hobby/get",
    "title": "兴趣爱好 查询",
    "description": "<p>兴趣爱好 查询</p>",
    "name": "user_hobby_get",
    "group": "group_user",
    "version": "1.0.0",
    "parameter": {
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "userHobbyBean",
            "description": "<p>兴趣爱好 bean</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userFamilyBean.interest",
            "description": "<p>兴趣</p>"
          },
          {
            "group": "data",
            "type": "SysCodeBean",
            "optional": false,
            "field": "userFamilyBean.interestCodeList",
            "description": "<p>兴趣 code list</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userFamilyBean.diet",
            "description": "<p>饮食</p>"
          },
          {
            "group": "data",
            "type": "SysCodeBean",
            "optional": false,
            "field": "userFamilyBean.dietCodeList",
            "description": "<p>饮食 code list</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userFamilyBean.taste",
            "description": "<p>口味</p>"
          },
          {
            "group": "data",
            "type": "SysCodeBean",
            "optional": false,
            "field": "userFamilyBean.tasteCodeList",
            "description": "<p>口味 code list</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"userHobbyBean\":{\"interestCodeList\":[{\"key\":\"1\",\"value\":\"美食\"}],\"dietCodeList\":[{\"key\":\"1\",\"value\":\"川湘菜\"},{\"key\":\"3\",\"value\":\"粤菜\"}],\"tasteCodeList\":[{\"key\":\"4\",\"value\":\"苦\"}],\"interest\":\"1\",\"diet\":\"1,3\",\"taste\":\"4\"}}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/user/UserHobbyController.java",
    "groupTitle": "用户"
  },
  {
    "type": "post",
    "url": "/api/user/hobby/update",
    "title": "兴趣爱好 修改",
    "description": "<p>兴趣爱好 修改</p>",
    "name": "user_hobby_update",
    "group": "group_user",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "userHobbyBean",
            "description": "<p>兴趣爱好 bean</p>"
          },
          {
            "group": "data",
            "type": "SysCodeBean",
            "optional": true,
            "field": "userHobbyBean.interestCodeList",
            "description": "<p>兴趣 code list</p>"
          },
          {
            "group": "data",
            "type": "SysCodeBean",
            "optional": true,
            "field": "userHobbyBean.dietCodeList",
            "description": "<p>饮食 code list</p>"
          },
          {
            "group": "data",
            "type": "SysCodeBean",
            "optional": true,
            "field": "userHobbyBean.tasteCodeList",
            "description": "<p>口味 code list</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"userHobbyBean\":{\"interestCodeList\":[{\"key\":\"1\"}],\"dietCodeList\":[{\"key\":\"1\"},{\"key\":\"3\"}],\"tasteCodeList\":[{\"key\":\"4\"}]}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/user/UserHobbyController.java",
    "groupTitle": "用户"
  },
  {
    "type": "post",
    "url": "/api/user/job/config",
    "title": "工作 配置",
    "description": "<p>客户 工作 配置</p>",
    "name": "user_job_config",
    "group": "group_user",
    "version": "1.0.0",
    "parameter": {
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "industryCodeList",
            "description": "<p>行业 code list</p>"
          },
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "positionCodeList",
            "description": "<p>职位 code list</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"industryCodeList\":[{\"id\":88,\"key\":\"1\",\"value\":\"计算机硬软件\",\"pid\":87,\"sort\":1},{\"id\":89,\"key\":\"2\",\"value\":\"互联网/电子商务/网游\",\"pid\":87,\"sort\":2},{\"id\":90,\"key\":\"3\",\"value\":\"IT管理\",\"pid\":87,\"sort\":3},{\"id\":91,\"key\":\"4\",\"value\":\"通信\",\"pid\":87,\"sort\":4},{\"id\":92,\"key\":\"5\",\"value\":\"电子/电器/半导体\",\"pid\":87,\"sort\":5},{\"id\":93,\"key\":\"6\",\"value\":\"财务/审计/税务\",\"pid\":87,\"sort\":6},{\"id\":94,\"key\":\"7\",\"value\":\"金融/投资\",\"pid\":87,\"sort\":7},{\"id\":95,\"key\":\"8\",\"value\":\"银行/保险\",\"pid\":87,\"sort\":8},{\"id\":96,\"key\":\"9\",\"value\":\"工程/机械\",\"pid\":87,\"sort\":9},{\"id\":97,\"key\":\"10\",\"value\":\"能源/原材料\",\"pid\":87,\"sort\":10},{\"id\":98,\"key\":\"11\",\"value\":\"汽车及零配件制造\",\"pid\":87,\"sort\":11},{\"id\":99,\"key\":\"12\",\"value\":\"汽车销售服务\",\"pid\":87,\"sort\":12},{\"id\":100,\"key\":\"13\",\"value\":\"服装/纺织\",\"pid\":87,\"sort\":13},{\"id\":101,\"key\":\"14\",\"value\":\"轻工产品制造\",\"pid\":87,\"sort\":14},{\"id\":102,\"key\":\"15\",\"value\":\"食品生产\",\"pid\":87,\"sort\":15},{\"id\":103,\"key\":\"16\",\"value\":\"贸易\",\"pid\":87,\"sort\":16},{\"id\":104,\"key\":\"17\",\"value\":\"物流/仓储\",\"pid\":87,\"sort\":17},{\"id\":105,\"key\":\"18\",\"value\":\"生物/制药\",\"pid\":87,\"sort\":18},{\"id\":106,\"key\":\"19\",\"value\":\"化工\",\"pid\":87,\"sort\":19},{\"id\":107,\"key\":\"20\",\"value\":\"医院/医疗/护理\",\"pid\":87,\"sort\":20},{\"id\":108,\"key\":\"21\",\"value\":\"广告媒体\",\"pid\":87,\"sort\":21},{\"id\":109,\"key\":\"22\",\"value\":\"市场/营销\",\"pid\":87,\"sort\":22},{\"id\":110,\"key\":\"23\",\"value\":\"影视\",\"pid\":87,\"sort\":23},{\"id\":111,\"key\":\"24\",\"value\":\"编辑出版\",\"pid\":87,\"sort\":24},{\"id\":112,\"key\":\"25\",\"value\":\"艺术/设计\",\"pid\":87,\"sort\":25},{\"id\":113,\"key\":\"26\",\"value\":\"建筑与装潢\",\"pid\":87,\"sort\":26},{\"id\":114,\"key\":\"27\",\"value\":\"房地产开发\",\"pid\":87,\"sort\":27},{\"id\":115,\"key\":\"28\",\"value\":\"房地产销售与中介\",\"pid\":87,\"sort\":28},{\"id\":116,\"key\":\"29\",\"value\":\"物业\",\"pid\":87,\"sort\":29},{\"id\":117,\"key\":\"30\",\"value\":\"人力资源\",\"pid\":87,\"sort\":30},{\"id\":118,\"key\":\"31\",\"value\":\"咨询/顾问\",\"pid\":87,\"sort\":31},{\"id\":119,\"key\":\"32\",\"value\":\"律师/法务\",\"pid\":87,\"sort\":32},{\"id\":120,\"key\":\"33\",\"value\":\"教师/培训\",\"pid\":87,\"sort\":33},{\"id\":121,\"key\":\"34\",\"value\":\"科研\",\"pid\":87,\"sort\":34},{\"id\":122,\"key\":\"35\",\"value\":\"餐饮服务\",\"pid\":87,\"sort\":35},{\"id\":123,\"key\":\"36\",\"value\":\"酒店旅游\",\"pid\":87,\"sort\":36},{\"id\":124,\"key\":\"37\",\"value\":\"美容保健\",\"pid\":87,\"sort\":37},{\"id\":125,\"key\":\"38\",\"value\":\"百货零售\",\"pid\":87,\"sort\":38},{\"id\":126,\"key\":\"39\",\"value\":\"交通运输\",\"pid\":87,\"sort\":39},{\"id\":127,\"key\":\"40\",\"value\":\"家政/生活服务\",\"pid\":87,\"sort\":40},{\"id\":128,\"key\":\"41\",\"value\":\"政府/公务员\",\"pid\":87,\"sort\":41},{\"id\":129,\"key\":\"42\",\"value\":\"翻译\",\"pid\":87,\"sort\":42},{\"id\":130,\"key\":\"43\",\"value\":\"农林牧渔\",\"pid\":87,\"sort\":43},{\"id\":131,\"key\":\"44\",\"value\":\"印刷包装\",\"pid\":87,\"sort\":44},{\"id\":132,\"key\":\"45\",\"value\":\"运动健身\",\"pid\":87,\"sort\":45},{\"id\":133,\"key\":\"46\",\"value\":\"休闲娱乐\",\"pid\":87,\"sort\":46},{\"id\":134,\"key\":\"47\",\"value\":\"其他\",\"pid\":87,\"sort\":47}],\"positionCodeList\":[{\"id\":136,\"key\":\"1\",\"value\":\"工薪族\",\"pid\":135,\"sort\":1},{\"id\":137,\"key\":\"2\",\"value\":\"个体户\",\"pid\":135,\"sort\":2},{\"id\":138,\"key\":\"3\",\"value\":\"企业主\",\"pid\":135,\"sort\":3},{\"id\":139,\"key\":\"4\",\"value\":\"学生\",\"pid\":135,\"sort\":4},{\"id\":140,\"key\":\"5\",\"value\":\"公务员\",\"pid\":135,\"sort\":5},{\"id\":141,\"key\":\"6\",\"value\":\"自由职业\",\"pid\":135,\"sort\":6},{\"id\":142,\"key\":\"7\",\"value\":\"无业\",\"pid\":135,\"sort\":7}]}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/user/UserJobController.java",
    "groupTitle": "用户"
  },
  {
    "type": "post",
    "url": "/api/user/job/get",
    "title": "工作 查询",
    "description": "<p>工作 查询</p>",
    "name": "user_job_get",
    "group": "group_user",
    "version": "1.0.0",
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "userBean",
            "description": "<p>客户 bean</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userBean.realName",
            "description": "<p>真实姓名</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "userBean.phone",
            "description": "<p>手机</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "userBean.sex",
            "description": "<p>性别<br/>0:女<br/>1:男</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userBean.headPic",
            "description": "<p>头像</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "userBean.areaId",
            "description": "<p>区域ID</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userBean.areaNameAll",
            "description": "<p>区域全称</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userBean.address",
            "description": "<p>地址</p>"
          },
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "userJobBean",
            "description": "<p>工作 bean</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "userJobBean.industry",
            "description": "<p>行业</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userJobBean.industryValue",
            "description": "<p>行业 值</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userJobBean.companyName",
            "description": "<p>公司</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userJobBean.departmentName",
            "description": "<p>部门</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "userJobBean.position",
            "description": "<p>职位</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userJobBean.positionValue",
            "description": "<p>职位 值</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userJobBean.phone",
            "description": "<p>手机</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "userJobBean.areaId",
            "description": "<p>区域ID</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userJobBean.areaNameAll",
            "description": "<p>区域全称</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userJobBean.address",
            "description": "<p>地址</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"userJobBean\":{\"areaNameAll\":\"江苏省泰州市\",\"industryValue\":\"互联网/电子商务/网游\",\"positionValue\":\"工薪族\",\"industry\":2,\"companyName\":\"aaaa\",\"departmentName\":\"bbb\",\"position\":1,\"phone\":15111111111,\"areaId\":321200,\"address\":\"aaaaaaaaaaaaaa\"},\"userBean\":{\"areaNameAll\":\"江苏省泰州市\",\"realName\":\"ttt\",\"phone\":15111111111,\"sex\":0,\"headPic\":\"/xxx/pic.jpg\",\"areaId\":321200,\"address\":\"aaaaaaaaaaaaaa\"}}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/user/UserJobController.java",
    "groupTitle": "用户"
  },
  {
    "type": "post",
    "url": "/api/user/job/update",
    "title": "工作 修改",
    "description": "<p>工作 修改</p>",
    "name": "user_job_update",
    "group": "group_user",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "UserJobBean": [
          {
            "group": "UserJobBean",
            "type": "Object",
            "optional": false,
            "field": "userBean",
            "description": "<p>客户 bean</p>"
          },
          {
            "group": "UserJobBean",
            "type": "String",
            "optional": false,
            "field": "userBean.realName",
            "description": "<p>真实姓名</p>"
          },
          {
            "group": "UserJobBean",
            "type": "Integer",
            "optional": true,
            "field": "userBean.sex",
            "description": "<p>性别<br/>0:女<br/>1:男</p>"
          },
          {
            "group": "UserJobBean",
            "type": "String",
            "optional": true,
            "field": "userBean.headPic",
            "description": "<p>头像</p>"
          },
          {
            "group": "UserJobBean",
            "type": "Long",
            "optional": true,
            "field": "userBean.areaId",
            "description": "<p>区域ID</p>"
          },
          {
            "group": "UserJobBean",
            "type": "String",
            "optional": true,
            "field": "userBean.address",
            "description": "<p>地址</p>"
          },
          {
            "group": "UserJobBean",
            "type": "Object",
            "optional": false,
            "field": "userJobBean",
            "description": "<p>工作 bean</p>"
          },
          {
            "group": "UserJobBean",
            "type": "Integer",
            "optional": true,
            "field": "userJobBean.industry",
            "description": "<p>行业</p>"
          },
          {
            "group": "UserJobBean",
            "type": "String",
            "optional": true,
            "field": "userJobBean.companyName",
            "description": "<p>公司</p>"
          },
          {
            "group": "UserJobBean",
            "type": "String",
            "optional": true,
            "field": "userJobBean.departmentName",
            "description": "<p>部门</p>"
          },
          {
            "group": "UserJobBean",
            "type": "Integer",
            "optional": true,
            "field": "userJobBean.position",
            "description": "<p>职位</p>"
          },
          {
            "group": "UserJobBean",
            "type": "String",
            "optional": true,
            "field": "userJobBean.phone",
            "description": "<p>手机</p>"
          },
          {
            "group": "UserJobBean",
            "type": "Long",
            "optional": true,
            "field": "userJobBean.areaId",
            "description": "<p>区域ID</p>"
          },
          {
            "group": "UserJobBean",
            "type": "String",
            "optional": true,
            "field": "userJobBean.address",
            "description": "<p>地址</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"userBean\":{\"realName\":\"ttt\",\"sex\":0,\"birthday\":20100101,\"headPic\":\"/xxx/pic.jpg\",\"areaId\":321200,\"address\":\"aaaaaaaaaaaaaa\"},\"userJobBean\":{\"userId\":4,\"industry\":2,\"companyName\":\"aaaa\",\"departmentName\":\"bbb\",\"position\":3,\"phone\":\"15111111111\",\"areaId\":321200,\"address\":\"aaaaaaaaaaaaaa\"}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/user/UserJobController.java",
    "groupTitle": "用户"
  },
  {
    "type": "post",
    "url": "/api/user/remind/list",
    "title": "提醒 列表 查询",
    "description": "<p>提醒 列表 查询</p>",
    "name": "user_remind_list",
    "group": "group_user",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "UserRemindBean": [
          {
            "group": "UserRemindBean",
            "type": "Object",
            "optional": false,
            "field": "userRemindBean",
            "description": "<p>提醒 bean</p>"
          },
          {
            "group": "UserRemindBean",
            "type": "Integer",
            "optional": false,
            "field": "userRemindBean.advanceDate",
            "description": "<p>时间 yyyyMMdd</p>"
          }
        ],
        "CustomerRelationBean": [
          {
            "group": "CustomerRelationBean",
            "type": "Object",
            "optional": false,
            "field": "customerRelationBean",
            "description": "<p>客户关系 bean</p>"
          },
          {
            "group": "CustomerRelationBean",
            "type": "Integer",
            "optional": true,
            "field": "customerRelationBean.importance",
            "description": "<p>关系<br/>1:重要</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"userRemindBean\":{\"advanceDate\":20181125,\"customerRelationBean\":{\"importance\":1}}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "List",
            "optional": false,
            "field": "userRemindBeanList",
            "description": "<p>提醒 bean list</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "userRemindBeanList.advanceDay",
            "description": "<p>提前 天</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "userRemindBeanList.advanceDayStr",
            "description": "<p>提前 天</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "userRemindBeanList.remindDate",
            "description": "<p>提醒 时间 yyyyMMdd</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userRemindBeanList.content",
            "description": "<p>内容</p>"
          },
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "userRemindBeanList.customerBean",
            "description": "<p>客户 bean</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userRemindBeanList.customerRelationBean",
            "description": "<p>客户关系 bean</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"userRemindBeanList\":[{\"content\":\"客户生日,亲戚生日\",\"customerBean\":{\"id\":2,\"realName\":\"ss\",\"sex\":1,\"headPic\":\"https://img.rmp.com/img/head_pic/default.jpg\"},\"customerRelationBean\":{\"relationshipValue\":\"其他\",\"relationship\":0},\"advanceDayStr\":\"0天后\",\"advanceDate\":20181125,\"advanceDay\":0,\"remindDate\":20181125},{\"content\":\"客户生日\",\"customerBean\":{\"id\":5,\"realName\":\"ttt\",\"sex\":0,\"headPic\":\"https://img.rmp.com/xxx/pic.jpg\"},\"advanceDayStr\":\"0天后\",\"advanceDate\":20181125,\"advanceDay\":0,\"remindDate\":20181125},{\"content\":\"ttttt4,ttttt3,ttttt2,ttttt1\",\"customerBean\":{\"id\":2,\"realName\":\"ss\",\"sex\":1,\"headPic\":\"https://img.rmp.com/img/head_pic/default.jpg\"},\"customerRelationBean\":{\"relationshipValue\":\"其他\",\"relationship\":0},\"advanceDayStr\":\"明天\",\"advanceDate\":20181125,\"advanceDay\":1,\"remindDate\":20181126},{\"content\":\"亲戚生日\",\"customerBean\":{\"id\":2,\"realName\":\"ss\",\"sex\":1,\"headPic\":\"https://img.rmp.com/img/head_pic/default.jpg\"},\"customerRelationBean\":{\"relationshipValue\":\"其他\",\"relationship\":0},\"advanceDayStr\":\"5天后\",\"advanceDate\":20181125,\"advanceDay\":5,\"remindDate\":20181130},{\"content\":\"客户生日\",\"customerBean\":{\"id\":4,\"realName\":\"ttt\",\"sex\":0,\"headPic\":\"https://img.rmp.com/xxx/pic.jpg\"},\"advanceDayStr\":\"5天后\",\"advanceDate\":20181125,\"advanceDay\":5,\"remindDate\":20181130}]}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/user/UserRemindController.java",
    "groupTitle": "用户"
  },
  {
    "type": "post",
    "url": "/api/user/user/bindWxPhone",
    "title": "绑定 微信手机",
    "description": "<p>绑定 微信手机</p>",
    "name": "user_user_bindWxPhone",
    "group": "group_user",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "WxPhoneNumberReqBean": [
          {
            "group": "WxPhoneNumberReqBean",
            "type": "Object",
            "optional": false,
            "field": "wxPhoneNumberReqBean",
            "description": "<p>微信手机 bean</p>"
          },
          {
            "group": "WxPhoneNumberReqBean",
            "type": "String",
            "optional": false,
            "field": "wxPhoneNumberReqBean.encryptedData",
            "description": ""
          },
          {
            "group": "WxPhoneNumberReqBean",
            "type": "String",
            "optional": false,
            "field": "wxPhoneNumberReqBean.iv",
            "description": ""
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"b1e00042ab8a4296aa62c09b28a3c547\"},\"wxPhoneNumberReqBean\":{\"encryptedData\":\"xxxxxxxxxxxxxxx\",\"iv\":\"yyyyyyyyyyyy\"}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "userBean",
            "description": "<p>用户 bean</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "userBean.phone",
            "description": "<p>手机号</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"b1e00042ab8a4296aa62c09b28a3c547\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"userBean\":{\"phone\":\"15100000000\"}}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/user/UserController.java",
    "groupTitle": "用户"
  },
  {
    "type": "post",
    "url": "/api/user/user/get",
    "title": "查询",
    "description": "<p>查询</p>",
    "name": "user_user_get",
    "group": "group_user",
    "version": "1.0.0",
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "userBean",
            "description": "<p>客户 bean</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userBean.realName",
            "description": "<p>真实姓名</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "userBean.phone",
            "description": "<p>手机</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "optional": false,
            "field": "userBean.sex",
            "description": "<p>性别<br/>0:女<br/>1:男</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userBean.headPic",
            "description": "<p>头像</p>"
          },
          {
            "group": "data",
            "type": "Long",
            "optional": false,
            "field": "userBean.areaId",
            "description": "<p>区域ID</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userBean.areaNameAll",
            "description": "<p>区域全称</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userBean.address",
            "description": "<p>地址</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"userBean\":{\"areaNameAll\":\"江苏省泰州市\",\"realName\":\"ttt\",\"phone\":15111111111,\"sex\":0,\"headPic\":\"/xxx/pic.jpg\",\"areaId\":321200,\"address\":\"aaaaaaaaaaaaaa\"}}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/user/UserController.java",
    "groupTitle": "用户"
  },
  {
    "type": "post",
    "url": "/api/user/user/initialize",
    "title": "初始化",
    "description": "<p>初始化</p>",
    "name": "user_user_initialize",
    "group": "group_user",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "UserBean": [
          {
            "group": "UserBean",
            "type": "Object",
            "optional": false,
            "field": "userBean",
            "description": "<p>用户 bean</p>"
          },
          {
            "group": "UserBean",
            "type": "String",
            "optional": false,
            "field": "userBean.jsCode",
            "description": "<p>微信jsCode</p>"
          },
          {
            "group": "UserBean",
            "type": "String",
            "optional": false,
            "field": "userBean.nickName",
            "description": "<p>昵称</p>"
          },
          {
            "group": "UserBean",
            "type": "String",
            "optional": false,
            "field": "userBean.headPic",
            "description": "<p>头像</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"userBean\":{\"nickName\":\"哈哈\",\"headPic\":\"http://wx.com/tx.jpg\",\"jsCode\":\"aaaaaa\"}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "userBean",
            "description": "<p>用户 bean</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userBean.token",
            "description": "<p>token</p>"
          },
          {
            "group": "data",
            "type": "Integer",
            "allowedValues": [
              "0",
              "1"
            ],
            "optional": false,
            "field": "userBean.status",
            "description": "<p>状态<br/>0:未注册<br/>1:已注册</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"b1e00042ab8a4296aa62c09b28a3c547\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"userBean\":{\"token\":\"b1e00042ab8a4296aa62c09b28a3c547\",\"status\":0}}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/user/UserController.java",
    "groupTitle": "用户"
  },
  {
    "type": "post",
    "url": "/api/user/user/login",
    "title": "登录",
    "description": "<p>登录</p>",
    "name": "user_user_login",
    "group": "group_user",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "UserBean": [
          {
            "group": "UserBean",
            "type": "Object",
            "optional": false,
            "field": "userBean",
            "description": "<p>用户 bean</p>"
          },
          {
            "group": "UserBean",
            "type": "String",
            "optional": false,
            "field": "userBean.loginName",
            "description": "<p>登录名</p>"
          },
          {
            "group": "UserBean",
            "type": "String",
            "optional": false,
            "field": "userBean.loginPwd",
            "description": "<p>登录密码</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"userBean\":{\"loginName\":\"15123815032\",\"loginPwd\":\"123456\"}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "userBean",
            "description": "<p>用户 bean</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userBean.token",
            "description": "<p>token</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userBean.nickName",
            "description": "<p>昵称</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userBean.headPic",
            "description": "<p>头像</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"a074f3b898d24b5285b96a93f85d9edf\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"userBean\":{\"nickName\":\"ttt\",\"headPic\":\"http://wx.com/tx.jpg\",\"token\":\"a074f3b898d24b5285b96a93f85d9edf\"}}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/user/UserController.java",
    "groupTitle": "用户"
  },
  {
    "type": "post",
    "url": "/api/user/user/register",
    "title": "注册",
    "description": "<p>注册</p>",
    "name": "user_user_register",
    "group": "group_user",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "UserBean": [
          {
            "group": "UserBean",
            "type": "Object",
            "optional": false,
            "field": "userBean",
            "description": "<p>用户 bean</p>"
          },
          {
            "group": "UserBean",
            "type": "String",
            "size": "11",
            "optional": false,
            "field": "userBean.loginName",
            "description": "<p>登录名</p>"
          },
          {
            "group": "UserBean",
            "type": "String",
            "size": "6..15",
            "optional": false,
            "field": "userBean.loginPwd",
            "description": "<p>登录密码</p>"
          }
        ],
        "PhoneMsgBean": [
          {
            "group": "PhoneMsgBean",
            "type": "Object",
            "optional": false,
            "field": "phoneMsgBean",
            "description": "<p>手机短信 bean</p>"
          },
          {
            "group": "PhoneMsgBean",
            "type": "String",
            "size": "6",
            "optional": false,
            "field": "phoneMsgBean.code",
            "description": "<p>随机码</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"b1e00042ab8a4296aa62c09b28a3c547\"},\"userBean\":{\"loginName\":\"15123815032\",\"loginPwd\":\"123456\"},\"phoneMsgBean\":{\"code\":\"666666\"}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "data": [
          {
            "group": "data",
            "type": "Object",
            "optional": false,
            "field": "userBean",
            "description": "<p>用户 bean</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userBean.token",
            "description": "<p>token</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userBean.nickName",
            "description": "<p>昵称</p>"
          },
          {
            "group": "data",
            "type": "String",
            "optional": false,
            "field": "userBean.headPic",
            "description": "<p>头像</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"b1e00042ab8a4296aa62c09b28a3c547\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"userBean\":{\"nickName\":\"ttt\",\"headPic\":\"http://wx.com/tx.jpg\",\"token\":\"b1e00042ab8a4296aa62c09b28a3c547\"}}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/user/UserController.java",
    "groupTitle": "用户"
  },
  {
    "type": "post",
    "url": "/api/user/user/retrievePwd",
    "title": "找回密码",
    "description": "<p>找回密码</p>",
    "name": "user_user_retrievePwd",
    "group": "group_user",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "UserBean": [
          {
            "group": "UserBean",
            "type": "Object",
            "optional": false,
            "field": "userBean",
            "description": "<p>用户 bean</p>"
          },
          {
            "group": "UserBean",
            "type": "String",
            "optional": false,
            "field": "userBean.loginName",
            "description": "<p>登录账号</p>"
          },
          {
            "group": "UserBean",
            "type": "String",
            "optional": false,
            "field": "userBean.loginPwd",
            "description": "<p>登录密码</p>"
          }
        ],
        "PhoneMsgBean": [
          {
            "group": "PhoneMsgBean",
            "type": "Object",
            "optional": false,
            "field": "phoneMsgBean",
            "description": "<p>手机短信 bean</p>"
          },
          {
            "group": "PhoneMsgBean",
            "type": "String",
            "optional": false,
            "field": "phoneMsgBean.code",
            "description": "<p>随机码</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"userBean\":{\"loginName\":\"15123815032\",\"loginPwd\":\"123456\"},\"phoneMsgBean\": {\"code\":\"777777\"}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/user/UserController.java",
    "groupTitle": "用户"
  },
  {
    "type": "post",
    "url": "/api/user/user/updateHeadPic",
    "title": "修改 头像",
    "description": "<p>修改 头像</p>",
    "name": "user_user_updateHeadPic",
    "group": "group_user",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "UserBean": [
          {
            "group": "UserBean",
            "type": "Object",
            "optional": false,
            "field": "userBean",
            "description": "<p>用户 bean</p>"
          },
          {
            "group": "UserBean",
            "type": "String",
            "optional": false,
            "field": "userBean.headPic",
            "description": "<p>头像</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"userBean\":{\"headPic\":\"http://47.94.5.205/tmp/user/head_pic/20181127/1114000029789874659.jpg\"}}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"userBean\":{\"headPic\":\"http://47.94.5.205/user/head_pic/20181127/1114000029789874659.jpg\"}}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/user/UserController.java",
    "groupTitle": "用户"
  },
  {
    "type": "post",
    "url": "/api/user/user/updateNickName",
    "title": "修改 昵称",
    "description": "<p>修改 昵称</p>",
    "name": "user_user_updateNickName",
    "group": "group_user",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "UserBean": [
          {
            "group": "UserBean",
            "type": "Object",
            "optional": false,
            "field": "userBean",
            "description": "<p>用户 bean</p>"
          },
          {
            "group": "UserBean",
            "type": "String",
            "optional": false,
            "field": "userBean.nickName",
            "description": "<p>昵称</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"userBean\":{\"nickName\":\"xxx\"}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/user/UserController.java",
    "groupTitle": "用户"
  },
  {
    "type": "post",
    "url": "/api/user/user/updatePhone",
    "title": "修改 手机",
    "description": "<p>修改 昵称</p>",
    "name": "user_user_updatePhone",
    "group": "group_user",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "UserBean": [
          {
            "group": "UserBean",
            "type": "Object",
            "optional": false,
            "field": "userBean",
            "description": "<p>用户 bean</p>"
          },
          {
            "group": "UserBean",
            "type": "Long",
            "optional": false,
            "field": "userBean.phone",
            "description": "<p>手机</p>"
          }
        ],
        "PhoneMsgBean": [
          {
            "group": "PhoneMsgBean",
            "type": "Object",
            "optional": false,
            "field": "phoneMsgBean",
            "description": "<p>手机短信 bean</p>"
          },
          {
            "group": "PhoneMsgBean",
            "type": "String",
            "size": "6",
            "optional": false,
            "field": "phoneMsgBean.code",
            "description": "<p>随机码</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "请求-示例: ",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"userBean\":{\"phone\":15111111111},\"phoneMsgBean\":{\"code\":\"666666\"}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/user/UserController.java",
    "groupTitle": "用户"
  },
  {
    "type": "post",
    "url": "/api/user/user/uploadHeadPic",
    "title": "上传 头像",
    "description": "<p>上传 头像 表单提交</p>",
    "name": "user_user_uploadHeadPic",
    "group": "group_user",
    "version": "1.0.0",
    "parameter": {
      "fields": {
        "Form": [
          {
            "group": "Form",
            "type": "String",
            "optional": false,
            "field": "token",
            "description": "<p>token</p>"
          },
          {
            "group": "Form",
            "type": "File",
            "optional": false,
            "field": "headPicFile",
            "description": "<p>头像文件</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "成功返回-示例:",
          "content": "{\"header\":{\"token\":\"2661f2cac9754c98873aa9ce431b8012\"},\"msgs\":[],\"msg\":{},\"state\":\"0\",\"data\":{\"userBean\":{\"headPic\":\"http://47.94.5.205/user/head_pic/20181127/1114000029789874659.jpg\"}}}",
          "type": "json"
        }
      ]
    },
    "filename": "D:/git-linw/rmp/rmp-api/src/main/java/com/rmp/api/controller/user/UserController.java",
    "groupTitle": "用户"
  }
] });

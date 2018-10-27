define({ "api": [
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
  }
] });
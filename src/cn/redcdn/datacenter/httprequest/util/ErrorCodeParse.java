package cn.redcdn.datacenter.httprequest.util;

/*
 * 服务器返回的错误码解析。
 *  CANCEL:errorCode = -1;
 *  NO_REQUEST_DATA:errorCode = -2;
 *  JSON_ERROR:errorCode = -3;
 *  PARAMS_INIT_ERROR:errorCode = -4;
 *  OTHER_REASON:errorCode = -100;
 */

public class ErrorCodeParse {

  private static String getHttpInternalError(int code) {
    String result = "";

    switch (code) {
    case -1:
      result = "操作已取消";
      break;
    case -2:
      result = "服务器无数据返回";
      break;
    case -3:
      result = "服务器返回数据解析错误";
      break;
    case -4:
      result = "参数初始化错误";
      break;
    case -100:
      result = "服务器未知错误";
      break;
    }
    return result;
  }

  private static String getPCError(int code) {
    String result = "";
    switch (code) {
    case 1000:
      result = "终端类型无效";
      break;
    case 1001:
      result = "手机号码无效";
      break;
    case 1003:
      result = "手机号码没有注册";
      break;
    case 1005:
      result = "密码无效";
      break;
    case 1007:
      result = "访问网盘失败";
      break;
    case 1008:
      result = "访问网盘接口失败";
      break;
    case 1010:
      result = "IP地址为空";
      break;
    case 1011:
      result = "终端登陆人数达到上限";
      break;
    case 1012:
      result = "内部错误";
      break;
    case 1016:
      result = "令牌失效";
      break;
    case 1017:
      result = "内部错误";
      break;
    case 1018:
      result = "商品数量参数错误";
      break;
    case 1019:
      result = "无效的商品类型";
      break;
    case 1021:
      result = "商品物理文件不存在";
      break;
    case 1022:
      result = "商品编号错误";
      break;
    case 1026:
      result = "商品标签为空";
      break;
    case 1030:
      result = "商品分页号错误";
      break;
    case 1036:
      result = "商品ID为空";
      break;
    case 1037:
      result = "商品已收藏";
      break;
    case 1038:
      result = "该商品没有购买";
      break;
    case 1039:
      result = "连续剧（音乐、课件）分级索引无效";
      break;
    case 1040:
      result = "未找到终端类型对应的物理文件";
      break;
    case 1044:
      result = "无效的搜索域";
      break;
    default:
      break;
    }
    return result;
  }

  public static String getLoginError(int code) {
    String result = "";
    if (code < 0) {
      result = getHttpInternalError(code);
    } else if (code >= 1000) {
      result = getPCError(code);
    }
    switch (code) {
    case 2:
      result = "账号为空";
      break;
    case 3:
      result = "密码为空";
      break;
    case 4:
      result = "终端类型错误";
      break;
    case 5:
      result = "IP为空";
      break;
    case 6:
      result = "用户名或密码错误";
      break;
    case 7:
      result = "终端登录人数达到上限";
      break;
    case 8:
      result = "网盘系统参数错误";
      break;
    case 9:
      result = "登录网盘失败";
      break;
    case 10:
      result = "其他错误";
      break;
    default:
      break;
    }

    return result;
  }

  public static String getLogoutError(int code) {
    String result = "";

    switch (code) {
    case 1:
      result = "失败";
      break;
    case 2:
      result = "Token为空";
      break;
    default:
      break;
    }

    return result;
  }

  public static String getCategoryDataError(int code) {
    String result = "";

    if (code < 0) {
      result = getHttpInternalError(code);
    } else if (code >= 1000) {
      result = getPCError(code);
    } else {
      switch (code) {

      }
    }

    return result;
  }

  public static String getObtainGoodsDetailError(int code) {
    String result = "";
    if (code < 0) {
      result = getHttpInternalError(code);
    } else if (code >= 1000) {
      result = getPCError(code);
    } else {
      switch (code) {
      case 1:
        result = "Terminal 错误";
        break;
      case 2:
        result = "商品不存在";
        break;
      case 3:
        result = "商品物理文件不存在";
        break;
      case 5:
        result = "其它错误";
        break;
      default:
        break;
      }
    }

    return result;
  }

  public static String getSearchError(int code) {
    String result = "";
    if (code < 0) {
      result = getHttpInternalError(code);
    } else if (code >= 1000) {
      result = getPCError(code);
    } else {
      switch (code) {
      case 2:
        result = "终端类型错误";
        break;
      case 3:
        result = "Domain 参数错误";
        break;
      case 4:
        result = "Token 为空";
        break;
      case 5:
        result = "Type 错误";
        break;
      case 6:
        result = "关键字为空";
        break;
      case 7:
        result = "PageNo 错误";
        break;
      case 8:
        result = "PageSize 错误";
        break;
      case 9:
        result = "Token 错误或已失效";
        break;
      case 10:
        result = "其他错误";
        break;
      default:
        break;
      }
    }

    return result;
  }

  public static String getPurchaseGoodsError(int code) {
    String result = "";
    if (code < 0) {
      result = getHttpInternalError(code);
    } else if (code >= 1000) {
      result = getPCError(code);
    } else {
      switch (code) {
      case 1:
        result = "Token错误";
        break;
      case 2:
        result = "商品格式错误";
        break;
      case 3:
        result = "手机号错误";
        break;
      default:
        break;
      }
    }

    return result;
  }

  public static String getObtainPurchasedDataError(int code) {
    String result = "";
    if (code < 0) {
      result = getHttpInternalError(code);
    } else if (code >= 1000) {
      result = getPCError(code);
    } else {
      switch (code) {
      case 1:
        result = "Token错误";
        break;
      case 2:
        result = "商品类型为空";
        break;
      case 3:
        result = "商品类型未能正确匹配";
        break;
      case 4:
        result = "页索引或页大小错误";
        break;
      case 5:
        result = "其他错误";
        break;
      default:
        break;
      }
    }

    return result;
  }

  public static String getObtainFavouriteDataError(int code) {
    String result = "";
    if (code < 0) {
      result = getHttpInternalError(code);
    } else if (code >= 1000) {
      result = getPCError(code);
    } else {
      switch (code) {
      case 1:
        result = "Token错误";
        break;
      case 2:
        result = "商品类型为空";
        break;
      case 3:
        result = "商品类型未能正确匹配";
        break;
      case 4:
        result = "页索引或页大小错误";
        break;
      case 5:
        result = "其他错误";
        break;
      default:
        break;
      }
    }

    return result;
  }

  public static String getAddFavouriteError(int code) {
    String result = "";
    if (code < 0) {
      result = getHttpInternalError(code);
    } else if (code >= 1000) {
      result = getPCError(code);
    } else {
      switch (code) {
      case 1:
        result = "失败";
        break;
      case 2:
        result = "令牌错误或已失效";
        break;
      case 3:
        result = "商品ID为空";
        break;
      case 4:
        result = "商品已收藏";
        break;
      case 5:
        result = "其他错误";
        break;
      default:
        break;
      }
    }

    return result;
  }

  public static String getDeleteFavouriteError(int code) {
    String result = "";
    if (code < 0) {
      result = getHttpInternalError(code);
    } else if (code >= 1000) {
      result = getPCError(code);
    } else {
      switch (code) {
      case 1:
        result = "Token错误";
        break;
      case 2:
        result = "商品类型为空";
        break;
      case 3:
        result = "商品类型未能正确匹配";
        break;
      case 4:
        result = "页索引或页大小错误";
        break;
      case 5:
        result = "其他错误";
        break;
      default:
        break;
      }
    }

    return result;
  }

  public static String getObtainPlayHistoryDataError(int code) {
    String result = "";
    if (code < 0) {
      result = getHttpInternalError(code);
    } else if (code >= 1000) {
      result = getPCError(code);
    } else {
      switch (code) {

      default:
        break;
      }
    }

    return result;
  }

  public static String getObtainVideoUrlError(int code) {
    String result = "";
    if (code < 0) {
      result = getHttpInternalError(code);
    } else if (code >= 1000) {
      result = getPCError(code);
    } else {
      switch (code) {
      case 1:
        result = "商品ID为空";
        break;
      case 2:
        result = "终端令牌错误或已失效";
        break;
      case 3:
        result = "商品未购买";
        break;
      case 4:
        result = "获取播放url系统参数失败";
        break;
      case 5:
        result = "分集index为空";
        break;
      case 6:
        result = "未找到对应的播放文件";
        break;
      case 7:
        result = "未能正确匹配终端类型";
        break;
      case 8:
        result = "视频文件信息不完整";
        break;
      case 9:
        result = "不是专辑的商品";
        break;
      case 10:
        result = "其他错误";
        break;
      default:
        break;
      }
    }

    return result;
  }

  public static String getRegistError(int code) {
    String result = "";
    if (code < 0) {
      result = getHttpInternalError(code);
    } else if (code >= 1000) {
      result = getPCError(code);
    } else {
      switch (code) {
      case 1:
        result = "失败";
        break;
      case 2:
        result = "手机号码错误";
        break;
      case 3:
        result = "密码为空";
        break;
      case 4:
        result = "验证码为空";
        break;
      case 5:
        result = "网盘系统参数错误";
        break;
      case 6:
        result = "登录网盘失败";
        break;
      case 7:
        result = "验证码错误";
        break;
      case 8:
        result = "手机号已注册";
        break;
      case 9:
        result = "创建网盘账号错误";
        break;
      case 10:
        result = "其他错误";
        break;
      default:
        break;
      }
    }

    return result;
  }

  public static String getResetPasswordError(int code) {
    String result = "";
    if (code < 0) {
      result = getHttpInternalError(code);
    } else if (code >= 1000) {
      result = getPCError(code);
    } else {
      switch (code) {
      case 1:
        result = "失败";
        break;
      case 2:
        result = "终端类型错误";
        break;
      case 3:
        result = "版本号错误";
        break;
      case 4:
        result = "版本系统参数错误";
        break;
      default:
        break;
      }
    }

    return result;
  }

  public static String getCheckTerminalVersionError(int code) {
    String result = "";
    if (code < 0) {
      result = getHttpInternalError(code);
    } else if (code >= 1000) {
      result = getPCError(code);
    } else {
      switch (code) {
      case 1:
        result = "失败";
        break;
      case 2:
        result = "终端类型错误";
        break;
      case 3:
        result = "版本号错误";
        break;
      case 4:
        result = "版本系统参数错误";
        break;
      default:
        break;
      }
    }

    return result;
  }
}

package cn.redcdn.datacenter.httprequest;

import org.json.JSONObject;

public abstract class BusinessGetDataAdaptor implements HttpRequestDelegate {

  private HttpgetDataCenter mHttpgetDataCenter;
  private Parser parser;

  public BusinessGetDataAdaptor() {
    mHttpgetDataCenter = new HttpgetDataCenter();
    mHttpgetDataCenter.setHttpRequestDelegate(this);
  }

  public void cancel() {
    mHttpgetDataCenter.cancel();
  }

  public int exec(String url, String param) {
    mHttpgetDataCenter.setURL(url);
    mHttpgetDataCenter.setParams(param);
    return mHttpgetDataCenter.obtainData();
  }

  public void httpRequestFinished(String result) {
    parser = new Parser(result){
      @Override
      protected void onParseSuccess() {
        try {
          int headVaule = parser.getHeadValue();
          if (0 == headVaule) {
            onSuccess(parser.getBodyObject());
          } else {
            onFail(headVaule);
          }
        } catch (Exception e) {
          //invalidate json object
          onFail(ConstConfig.JSON_INVALID);
        }
      }

      @Override
      protected void onParseFail() {
        //invalidate json object
        onFail(ConstConfig.JSON_INVALID);
      }
    };
    parser.parse();
  }

  public void httpRequestFailed(int valueCode) {
    onFail(valueCode);
  }

  //返回后台管理服务器接口内容以JSON格式保存
  public abstract void onSuccess(JSONObject bodyObject);

  //valueCode取值如下：
  //>0 网络错误以及json格式错误
  //<0 后台管理服务器接口业务逻辑错误代码
  public abstract void onFail(int valueCode);
}
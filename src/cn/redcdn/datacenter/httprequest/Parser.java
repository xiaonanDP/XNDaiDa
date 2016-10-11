package cn.redcdn.datacenter.httprequest;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Parser {
  private final String tag = Parser.class.getName();
  
  private JSONObject jObject;
  private String content;

  public Parser(String content) {
    this.content = content;
  }

  //may be JSONObject parser block ui thread
  public int parse() {
    
    try {
      jObject = new JSONObject(content);
    } catch (JSONException e) {
      onParseFail();
      return 0;
    }
    onParseSuccess();
    return 0;
  }

  public void cancel() {
  }
  
  public int getHeadValue() throws Exception {
    JSONObject resultObject = jObject.getJSONObject("result");
    if (null != resultObject) {
      return resultObject.getInt("rc");
    }

    throw new JSONException("invalidate json format");
  }

  public JSONObject getBodyObject() {
    JSONObject resultObject = null;
    try {
      resultObject = jObject.getJSONObject("response");
    } catch (JSONException e) {
    }
    return resultObject;
  }

  protected abstract void onParseSuccess();
  
  protected abstract void onParseFail();
}
package cn.redcdn.datacenter.httprequest;

public interface HttpRequestDelegate {

  public void httpRequestFailed(int codeValue);

  public void httpRequestFinished(String result);
}
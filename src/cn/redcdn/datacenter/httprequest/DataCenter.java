package cn.redcdn.datacenter.httprequest;

public abstract class DataCenter {

  public DataCenter() {

  }

  public abstract int obtainData();

  public abstract void cancel();
}
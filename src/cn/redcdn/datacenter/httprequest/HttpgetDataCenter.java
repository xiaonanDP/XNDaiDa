package cn.redcdn.datacenter.httprequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class HttpgetDataCenter extends DataCenter {
  final static String tag = HttpgetDataCenter.class.getName();

  private HttpRequestDelegate mHttpRequestDelegate;

  private String mUrlString;

  private String mParam;

  private HttpGet mHttpGet;

//  private CustomAsyncTask mCustomAsyncTask;

  private ExecStatus state = ExecStatus.STOP;

  public HttpgetDataCenter() {
  }

  public void setHttpRequestDelegate(HttpRequestDelegate delegate) {
    mHttpRequestDelegate = delegate;
  }

  public void setURL(String url) {
    mUrlString = url;
  }

  public void setParams(String param) {
    mParam = param;
  }

@Override
public int obtainData() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public void cancel() {
	// TODO Auto-generated method stub
	
}

  /**
  @Override
  public int obtainData() {

    if (null == mUrlString || null == mParam) {
      return -1;
    }

    if (ExecStatus.EXECUTING == state) {
      return -2;
    }

    try {
      String stringUrl = mUrlString + "?"+"value=" + mParam;

      mHttpGet = new HttpGet(stringUrl);

    } catch (IllegalArgumentException e) {
      mHttpGet = null;
    }

    if (null == mHttpGet) {
      return -3;
    }

    mCustomAsyncTask = new CustomAsyncTask();

    state = ExecStatus.EXECUTING;
    return 0;
  }

  public void cancel() {

    if (ExecStatus.STOP == state) {
      return;
    }

    state = ExecStatus.STOP;

    mHttpGet.abort();
    mCustomAsyncTask.cancel(true);
  }

  class ResponseEntry {
    public int code;
    public Object content;
  };

  class CustomAsyncTask extends AsyncTask<HttpGet, Integer, ResponseEntry> {

    @Override
    protected void onPostExecute(ResponseEntry entry) {
      super.onPostExecute(entry);

      CustomLog.d(tag, "async task onPostExecute");
      // async task backgroud thread exit while cancel function be called
      // may be duplicate TODO
      if (ExecStatus.STOP == state) {
        CustomLog.w(tag, "current state STOP in onPostExecute method");
        return;
      }

      state = ExecStatus.STOP;

      if (0 != entry.code) {
        mHttpRequestDelegate.httpRequestFailed(entry.code);
      } else {
        mHttpRequestDelegate.httpRequestFinished((String) entry.content);
      }
    }

    // function onCancelled not be called in android 2.3.3,
    // but in android 4.1.1 will be. why?
    // TODO
    @Override
    protected void onCancelled(ResponseEntry entry) {
      CustomLog.w(tag, "async task onCancelled");
    }

    @Override
    protected ResponseEntry doInBackground(HttpGet... params) {
      CustomLog.d(tag, "async task background thread run");

      int responseErrorCode = ConstConfig.HTTP_NETWORK_ERROR;
      HttpResponse response = null;
      try {
        HttpParams httpParams = new BasicHttpParams();
        int timeoutConnection = 30000, rstimeout = 30000; // 30 seconds
        HttpConnectionParams.setConnectionTimeout(httpParams, timeoutConnection);
        HttpConnectionParams.setSoTimeout(httpParams, rstimeout);

        //AndroidHttpClient
        
        response = new DefaultHttpClient(httpParams).execute(params[0]);
      } catch (ClientProtocolException e) {
        CustomLog.e(tag, "http client execute fail, ClientProtocolException:" + e.getMessage());
      } catch (IllegalStateException e) {
        CustomLog.e(tag, "http client execute fail, IllegalStateException:" + e.getMessage());
      } catch (ConnectTimeoutException e) {
        CustomLog.e(tag, "http client execute fail, ConnectTimeoutException:" + e.getMessage());
      } catch (SocketException e) {
        CustomLog.e(tag, "http client execute fail, SocketException:" + e.getMessage());
      } catch (SocketTimeoutException e) {
        CustomLog.e(tag, "http client execute fail, SocketTimeoutException:" + e.getMessage());
      } catch (IOException e) {
        CustomLog.e(tag, "http client execute fail, IOException:" + e.getMessage());
      }

      if (null != response) {
        responseErrorCode = ConstConfig.WEB_SERVER_ERROR;
        
        StatusLine status = response.getStatusLine();
        if (null != status) {
          if (200 == status.getStatusCode()) { // status ok
            InputStream input = null;
            try {
              input = response.getEntity().getContent();
            } catch (Exception e) {
              CustomLog.w(tag,
                  "get http response content fail,message:" + e.getMessage());
            }

            String bodyString = getResponseBody(input);
            if (null != bodyString) {
              ResponseEntry entry = new ResponseEntry();
              entry.code = 0;
              entry.content = bodyString;
              return entry;
            }
          }
        }
      }

      CustomLog.e(tag, "http response invalidate in async task");

      ResponseEntry entry = new ResponseEntry();
      entry.code = responseErrorCode;
      return entry;
    }

    private String getResponseBody(InputStream stream) {
      try {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(stream));
        for (String s = reader.readLine(); s != null; s = reader.readLine()) {
          builder.append(s);
        }

        return builder.toString();
      } catch (IOException e) {
        CustomLog.e(tag, "read input stream fail, message:" + e.getMessage());
      }

      return null;
    }
  }
  */
  
}
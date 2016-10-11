package cn.redcdn.datacenter.httprequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpConnection;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

enum ExecStatus {
	EXECUTING, STOP
}

public class HttppostDataCenter {
	final static String tag = HttppostDataCenter.class.getName();

	private String mUrlString;

	private HashMap<String,String> mParam;
	
	private String stringParam;

	public String getStringParam() {
		return stringParam;
	}

	public void setStringParam(String stringParam) {
		this.stringParam = stringParam;
	}

	private HttpPost mHttpPost;

	public HttppostDataCenter() {
	}

	public void setURL(String url) {
		mUrlString = url;
	}

	public void setParams(HashMap<String,String> param) {
		mParam = param;
	}

	private HttpClient httpClient;
	
	private String getResponseBody(InputStream stream) {
		try {
			StringBuilder builder = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					stream));
			for (String s = reader.readLine(); s != null; s = reader.readLine()) {
				builder.append(s);
			}

			return builder.toString();
		} catch (IOException e) {
		}

		return null;
	}

	public String doPost() {

		if (null == mUrlString || null == mParam) {
			return "-1";
		}

		List<NameValuePair> nameValuePairArrayList = new ArrayList<NameValuePair>();  
        // 将传过来的参数填充到List<NameValuePair>中  
        if (mParam != null && !mParam.isEmpty()) {  
            for (Map.Entry<String, String> entry : mParam.entrySet()) {  
                nameValuePairArrayList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));  
            }  
        }  
        
        UrlEncodedFormEntity entity = null; 
		try {
			entity = new UrlEncodedFormEntity(nameValuePairArrayList, "UTF-8");
			mHttpPost = new HttpPost(mUrlString);
			mHttpPost.setEntity(entity);
			mHttpPost.setEntity(new StringEntity(stringParam,HTTP.UTF_8));
			mHttpPost.setHeader("Accept", "*/*");
			mHttpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded");
			mHttpPost.setHeader("Referer", "app:/SiShenKuaiYong.swf");
//			mHttpPost.setHeader("Accept-Language", "zh-cn");
//			mHttpPost.setHeader("Accept-Encoding", "gzip, deflate");
			mHttpPost.setHeader("x-flash-version", "15,0,0,195");
			mHttpPost.setHeader("User-Agent", "Mozilla/5.0 (iOS; U; zh-Hans) AppleWebKit/533.19.4 (KHTML, like Gecko) AdobeAIR/15.0");
			
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			mHttpPost = null;
		}

		int responseErrorCode = ConstConfig.HTTP_NETWORK_ERROR;
		HttpResponse response = null;
		try {
			
			if(httpClient == null){
				HttpParams httpParams = new BasicHttpParams();
				int timeoutConnection = 30000, rstimeout = 30000; // 30 seconds
				HttpConnectionParams.setConnectionTimeout(httpParams,
						timeoutConnection);
				HttpConnectionParams.setSoTimeout(httpParams, rstimeout);
				httpClient = new DefaultHttpClient(httpParams);
//				System.out.println("create HttpClient !!!");
			}
			response = httpClient.execute(mHttpPost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			responseErrorCode = ConstConfig.HTTP_ARGUMENT_ERROR;
		} catch (IllegalStateException e) {
			System.out.println(tag
					+ "http client execute fail, IllegalStateException:"
					+ e.getMessage());
		} catch (ConnectTimeoutException e) {
			System.out.println(tag
					+ "http client execute fail, ConnectTimeoutException:"
					+ e.getMessage());
		} catch (SocketException e) {
			System.out.println(tag
					+ "http client execute fail, SocketException:"
					+ e.getMessage());
		} catch (SocketTimeoutException e) {
			System.out.println(tag
					+ "http client execute fail, SocketTimeoutException:"
					+ e.getMessage());
		} catch (IOException e) {
			System.out.println(tag + "http client execute fail, IOException:"
					+ e.getMessage());
		} catch (IllegalArgumentException e) {
			responseErrorCode = ConstConfig.HTTP_ARGUMENT_ERROR;
			System.out.println(tag
					+ "http client execute fail, IllegalArgumentException:"
					+ e.getMessage());
		}

		if (null != response) {
			responseErrorCode = ConstConfig.WEB_SERVER_ERROR;

			StatusLine status = response.getStatusLine();
			if (null != status) {
				if (200 == status.getStatusCode()) {// status ok
					InputStream input = null;
					try {
						input = response.getEntity().getContent();
					} catch (Exception e) {
						System.out.println(tag
								+ "get http response content fail,message:"
								+ e.getMessage());
					}

					String bodyString = getResponseBody(input);
					if (null != bodyString) {
						return bodyString;
					}
				}else{
					return "wrong status : "+status.getStatusCode();
				}
			}
		}

		return "";
	}

}
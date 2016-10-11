package cn.redcdn.datacenter.httprequest;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

public class MySSLSocketFactory extends SSLSocketFactory {
  SSLContext sslContext = SSLContext.getInstance("TLS");

  public MySSLSocketFactory(KeyStore truststore)
      throws NoSuchAlgorithmException, KeyManagementException,
      KeyStoreException, UnrecoverableKeyException {
    super(truststore);

    TrustManager tm = new X509TrustManager() {
      public void checkClientTrusted(X509Certificate[] chain, String authType)
          throws CertificateException {
      }

      public void checkServerTrusted(X509Certificate[] chain, String authType)
          throws CertificateException {
      }

      public X509Certificate[] getAcceptedIssuers() {
        return null;
      }
    };

    sslContext.init(null, new TrustManager[] { tm }, null);
  }

  @Override
  public Socket createSocket(Socket socket, String host, int port,
      boolean autoClose) throws IOException, UnknownHostException {
    return sslContext.getSocketFactory().createSocket(socket, host, port,
        autoClose);
  }

  @Override
  public Socket createSocket() throws IOException {
    return sslContext.getSocketFactory().createSocket();
  }

  public static HttpClient getNewHttpClient() {
    try {
       //信任所有证书
       KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
       trustStore.load(null, null);
       SSLSocketFactory sf = new MySSLSocketFactory(trustStore);

//      KeyStore trustStore = KeyStore.getInstance("BKS");
//
//      InputStream ins = context.getAssets().open("server_trust.keystore"); // 下载的证书放到项目中的assets目录中
//      trustStore.load(ins, "redcdn".toCharArray());
//
//      SSLSocketFactory sf = new SSLSocketFactory(trustStore);

      sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

      HttpParams params = new BasicHttpParams();
      HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
      HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

      SchemeRegistry registry = new SchemeRegistry();
      registry.register(new Scheme("http", PlainSocketFactory
          .getSocketFactory(), 80));
      registry.register(new Scheme("https", sf, 443));

      ClientConnectionManager ccm = new ThreadSafeClientConnManager(params,
          registry);

      return new DefaultHttpClient(ccm, params);
    } catch (Exception e) {
      e.printStackTrace();
      return new DefaultHttpClient();
    }
  }

  /**
   * HttpsURLConnection 方式
   * 
   * use:
   * 
   * HttpURLConnection http = null;
   * 
   * if (url.getProtocol().toLowerCase().equals("https")) { trustAllHosts();
   * HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
   * https.setHostnameVerifier(DO_NOT_VERIFY); http = https; } else { http =
   * (HttpURLConnection) url.openConnection(); }
   */
  // always verify the host - dont check for certificate
  final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
    public boolean verify(String hostname, SSLSession session) {
      return true;
    }
  };

  /**
   * Trust every server - dont check for any certificate
   */
  public static void trustAllHosts() {
    // Create a trust manager that does not validate certificate chains
    TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
      public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[] {};
      }

      public void checkClientTrusted(X509Certificate[] chain, String authType)
          throws CertificateException {
      }

      public void checkServerTrusted(X509Certificate[] chain, String authType)
          throws CertificateException {
      }
    } };

    // Install the all-trusting trust manager
    try {
      SSLContext sc = SSLContext.getInstance("TLS");
      sc.init(null, trustAllCerts, new java.security.SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

package com.redcdn.test;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import cn.redcdn.datacenter.httprequest.HttppostDataCenter;

public class FrameTest extends Frame implements ActionListener {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  Button cutButton;
  
  TextField secondText;
  TextField timeText;
  Label label;
  static int timeDuration = 0;

  static List<Customer> customerList = null;
  static Map<String, Server> serverMap = null;

  public FrameTest() {
    super("IOS 区兄弟们，搞起！");

    setSize(500, 280);
    addWindowListener(new TestAdapter());
    
    Panel toolbar = new Panel();
    toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
    
    cutButton = new Button("begin");
    cutButton.addActionListener(this);
    toolbar.add(cutButton);

    secondText = new TextField(8);
    secondText.setText("17");
    toolbar.add(secondText);

    label = new Label("second/time");
    toolbar.add(label);

    label = new Label("LanDaCount :      ");
    toolbar.add(label);

    timeText = new TextField(6);
    timeText.setText("31");
    toolbar.add(timeText);

    Label label_ = new Label("minutes closed");
    toolbar.add(label_);
    add(toolbar, BorderLayout.NORTH);
    
    
    
  }
  
  static int interval = 0;

  public void actionPerformed(ActionEvent ae) {
	  beginWork();
  }

  private void beginWork() {
    cutButton.setEnabled(false);
    interval = new Integer(secondText.getText()).intValue();
    if (interval < 1 || interval > 65)
      interval = 61;

    timeDuration = new Integer(timeText.getText()).intValue();
    if (timeDuration < 10)
      timeDuration = 30;

    MyThread m = new MyThread();
    new Thread(m).start();
    
//    NiaoLongThread niaoLongThread = new NiaoLongThread();
//    new Thread(niaoLongThread).start();

    System.out.println("...." + interval);
  }

  public static void main(String args[]) {
    FrameTest tf1 = new FrameTest();
    tf1.setVisible(true);
  }

  static int landaCount = 0;

  public void doLanda(String host, int port, List<String> connectArray) {
    // landa
    for (int i = 0; i < connectArray.size(); i++) {
      String loginStr = (String) connectArray.get(i);
      try {
        InetSocketAddress remote = new InetSocketAddress(host, port);
        SocketChannel sc = SocketChannel.open();
        sc.connect(remote);
        if (sc.finishConnect())
          System.out.println("connected...");

        List<String> cmdList = new ArrayList<String>();
        cmdList.add(loginStr);
        cmdList.add("0000001400020300AE855A17827227F5418585C085BBA323");
        cmdList.add("0000001400030300D6855A17827227F5418585C085BB25E6");
        cmdList.add("0000001400040700C8855A17827227F5418585C085BB55E7");
        cmdList
            .add("0000002500051C00C8855A17827227CCAA29EB291BF2F87E8FF2C278F28F23D1834185E8BBD628A9D2");
        cmdList.add("0000001400060600AE855A17827227F5418585C085BBA327");
        cmdList.add("0000001400071B00C8855A17827227F5418585C085BB55E4");
        cmdList.add("0000001400081B00C8855A17827227F5418585C085BB55EB");
        cmdList.add("0000001400091800C8855A17827227F5418585C085BB55EA");
        cmdList.add("00000014000A1B0076855A17827227F5418585C085BBD84A");

        if (landaCount == 0) {
          // canzhan
          cmdList
              .add("0000001C000B1B00AE855A1782722742295B29B4D169D7E341859A83763DCFD1");
        } else {

          if (interval > 30)
            // canzhan
            cmdList
                .add("0000001C000B1B00AE855A1782722742295B29B4D169D7E341859A83763DCFD1");
          else
            // fuhuo
            cmdList
                .add("0000001C000B1B00AE855A1782722742295B29B4D169D7F541859AF5761FAC96");
        }

        for (int j = 0; j < cmdList.size(); j++) {
          String cmdStr = cmdList.get(j);
          ByteBuffer bb = ByteBuffer.wrap(hexStringToByteArray(cmdStr));
          sc.write(bb);
        }
        sc.close();

      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  public void doLanda(String host, int port, String loginStr) {
    try {
      InetSocketAddress remote = new InetSocketAddress(host, port);
      SocketChannel sc = SocketChannel.open();
      sc.connect(remote);

      List<String> cmdList = new ArrayList<String>();
      cmdList
          .add("000000740001020076855A17827227FE3FC216AA252946F28FD12816E759238FD7A56616DEA680B425E78FD6966CE75D285DE5EE5D28715DE5B98DC48B553B55F03655DDD78DCC4055567AA0AE6969DC7A42425540915FD7D78D724C8BD54C8B6436FEF6A370116E05F38F325F39BB4B4BC85CEB940273BB");
      cmdList.add("0000001400020300AE855A17827227F5418585C085BBA323");
      cmdList.add("0000001400030300D6855A17827227F5418585C085BB25E6");
      cmdList.add("0000001400040700C8855A17827227F5418585C085BB55E7");

      cmdList
          .add("0000002500051C00C8855A17827227CCAA29EB291BF2F87E8FF2C278F28F23D1834185E8BBD628A9D2");
      cmdList.add("0000001400060600AE855A17827227F5418585C085BBA327");
      cmdList.add("0000001400071800C8855A17827227F5418585C085BB55E4");
      cmdList.add("0000001400081B00C8855A17827227F5418585C085BB55EB");
      cmdList.add("0000001400091B00C8855A17827227F5418585C085BB55EA");
      cmdList.add("00000014000A1B0076855A17827227F5418585C085BBD84A");

      if (landaCount == 0) {
        // canzhan
        cmdList
            .add("0000001C000B1B00AE855A1782722742295B29B4D169D7E341859A83763DCFD1");
      } else {

        if (interval > 30)
          // canzhan
          cmdList
              .add("0000001C000B1B00AE855A1782722742295B29B4D169D7E341859A83763DCFD1");
        else
          // fuhuo
          cmdList
              .add("0000001C000B1B00AE855A1782722742295B29B4D169D7F541859AF5761FAC96");
      }

      for (int j = 0; j < cmdList.size(); j++) {
        String cmdStr = cmdList.get(j);
        ByteBuffer bb = ByteBuffer.wrap(hexStringToByteArray(cmdStr));
        sc.write(bb);
      }
      sc.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void doPeiEn(String host, int port, List<String> connectArray) {
    for (int i = 0; i < connectArray.size(); i++) {
      String loginStr = (String) connectArray.get(i);
      doPeiEn(host, port, loginStr);
    }
  }

  public void doPeiEn(String host, int port, String loginStr) {
    try {
      InetSocketAddress remote = new InetSocketAddress(host, port);
      SocketChannel sc = SocketChannel.open();
      sc.connect(remote);
      if (sc.finishConnect())
        System.out.println("connected...");

      List<String> cmdList = new ArrayList<String>();
      cmdList.add(loginStr);
      cmdList.add("0000001400020300AE855A17827227F5418585C085BBA323");
      cmdList.add("0000001400030300D6855A17827227F5418585C085BB25E6");
      cmdList.add("0000001400040700C8855A17827227F5418585C085BB55E7");
      cmdList
          .add("0000002500051C00C8855A17827227CCAA29EB291BF2F87E8FF2C278F28F23D1834185E8BBD628A9D2");
      cmdList.add("0000001400060600AE855A17827227F5418585C085BBA327");
      cmdList
          .add("0000001400071700C8855A17827227F5418585C085BB55E40000001400081300C8855A17827227F5418585C085BB55EB0000001400091A00C8855A17827227F5418585C085BB55EA00000024000A1400C8855A17827227CC3E29EB8FF2291BF23F89F31BB62927274185DB77226430EA");
      cmdList.add("00000014000B1D00C8855A17827227F5418585C085BB55E8");
      cmdList.add("00000014000C1200C8855A17827227F5418585C085BB55EF");
      cmdList.add("00000014000D1B00C8855A17827227F5418585C085BB55EE");
      cmdList.add("00000014000E1B00C8855A17827227F5418585C085BB55ED");
      cmdList.add("00000014000F1B0076855A17827227F5418585C085BBD84F");

      if (landaCount == 0) {
        // canzhan
        cmdList
            .add("0000001C00101B00AE855A1782722742295B29B4D169D7E341859A83763DCFCA");
      } else {

        if (interval > 30)
          // canzhan
          cmdList
              .add("0000001C00101B00AE855A1782722742295B29B4D169D7E341859A83763DCFCA");
        else
          // fuhuo
          cmdList
              .add("0000001C00101B00AE855A1782722742295B29B4D169D7F541859AF5761FAC8D");

      }

      for (int j = 0; j < cmdList.size(); j++) {
        String cmdStr = cmdList.get(j);
        ByteBuffer bb = ByteBuffer.wrap(hexStringToByteArray(cmdStr));
        sc.write(bb);
      }

      sc.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void doAndroidPeiEn(String host, int port, List<String> connectArray) {
    for (int i = 0; i < connectArray.size(); i++) {
      String loginStr = (String) connectArray.get(i);
      doAndroidPeiEn(host, port, loginStr);
    }
  }

  public void doAndroidPeiEn(String host, int port, String loginStr) {
    try {
      InetSocketAddress remote = new InetSocketAddress(host, port);
      SocketChannel sc = SocketChannel.open();
      sc.connect(remote);
      if (sc.finishConnect())
        System.out.println("connected...");

      List<String> cmdList = new ArrayList<String>();
      cmdList.add(loginStr);
      cmdList.add("0000001400020400C8855A17827227F5418585C085BB55E1");
      cmdList.add("0000001400030300AE855A17827227F5418585C085BBA322");
      cmdList.add("0000001400040300D6855A17827227F5418585C085BB25E1");
      cmdList.add("0000001400050700C8855A17827227F5418585C085BB55E6");
      cmdList
          .add("0000002500061C00C8855A17827227CCAA29EB291BF2F87E8FF2C278F28F23D1834185E8BBD628A9D1");
      cmdList.add("0000001400070600AE855A17827227F5418585C085BBA326");
      cmdList.add("0000001400081700C8855A17827227F5418585C085BB55EB");
      cmdList.add("0000001400091300C8855A17827227F5418585C085BB55EA");
      cmdList.add("00000014000A1A00C8855A17827227F5418585C085BB55E9");
      cmdList.add("00000014000B14005D855A17827227F5418585C085BB4841");
      cmdList
          .add("00000024000C1400C8855A17827227CC3E29EB8FF2291BF23F89F31BB62927274185DB77226430EC");
      cmdList.add("00000014000D0300FE855A17827227F5418585C085BB15DD");
      cmdList.add("00000014000E1C005D855A17827227F5418585C085BB4844");
      cmdList.add("00000014000F0800C8855A17827227F5418585C085BB55EC");
      cmdList.add("0000001400101600C8855A17827227F5418585C085BB55F3");
      cmdList.add("0000001400111200C8855A17827227F5418585C085BB55F2");
      cmdList.add("0000001400121B00C8855A17827227F5418585C085BB55F1");
      cmdList.add("0000001400131B00C8855A17827227F5418585C085BB55F0");
      cmdList.add("0000001400141B0076855A17827227F5418585C085BBD854");

      if (landaCount == 0) {
        // canzhan
        cmdList
            .add("0000001C00151B00AE855A1782722742295B29B4D169D7E341859A83763DCFCF");
      } else {

        if (interval > 30)
          // canzhan
          cmdList
              .add("0000001C00151B00AE855A1782722742295B29B4D169D7E341859A83763DCFCF");
        else
          // fuhuo
          cmdList
              .add("0000001C00151B00AE855A1782722742295B29B4D169D7F541859AF5761FAC88");
      }

      for (int j = 0; j < cmdList.size(); j++) {
        String cmdStr = cmdList.get(j);
        ByteBuffer bb = ByteBuffer.wrap(hexStringToByteArray(cmdStr));
        sc.write(bb);
      }

      sc.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void doDingShang(String host, int port, List<String> connectArray) {
	  
	  for (int i = 0; i < connectArray.size(); i++) {
			String loginStr = (String) connectArray.get(i);
			try {
				InetSocketAddress remote = new InetSocketAddress(host, port);
				SocketChannel sc = SocketChannel.open();
				sc.connect(remote);
				if (sc.finishConnect()) {
					System.out.println("connected...");
				}

				sc.configureBlocking(false);

				List<String> cmdList = new ArrayList<String>();
				cmdList.add(loginStr);
				cmdList.add("0000001400020300AE855A17827227F5418585C085BBA323");
				cmdList.add("0000001400030300D6855A17827227F5418585C085BB25E6");
				cmdList.add("0000001400040700C8855A17827227F5418585C085BB55E7");
				cmdList.add("0000002500051C00C8855A17827227CCAA29EB291BF2F87E8FF2C278F28F23D1834185E8BBD628A9D2");
				cmdList.add("0000001400060600AE855A17827227F5418585C085BBA327");
				cmdList.add("0000001400071700C8855A17827227F5418585C085BB55E40000001400081300C8855A17827227F5418585C085BB55EB0000001400091A00C8855A17827227F5418585C085BB55EA00000024000A1400C8855A17827227CC3E29EB8FF2291BF23F89F31BB62927274185DB77226430EA");
				cmdList.add("00000014000B1D00C8855A17827227F5418585C085BB55E8");
				cmdList.add("00000014000C0500C8855A17827227F5418585C085BB55EF00000014000D0600C8855A17827227F5418585C085BB55EE00000014000E1600C8855A17827227F5418585C085BB55ED");
				cmdList.add("00000014000F1200C8855A17827227F5418585C085BB55EC");
				cmdList.add("0000001400101B00C8855A17827227F5418585C085BB55F3");
				cmdList.add("0000001400111B00C8855A17827227F5418585C085BB55F2");
				cmdList.add("0000001400121B0076855A17827227F5418585C085BBD852");

				if (landaCount == 0) {
					// canzhan
					cmdList.add("0000001C00131B00AE855A1782722742295B29B4D169D7E341859A83763DCFC9");
				} else {
					if (interval > 30)
						// canzhan
						cmdList.add("0000001C00131B00AE855A1782722742295B29B4D169D7E341859A83763DCFC9");
					else
						// fuhuo
						cmdList.add("0000001C000B1B00AE855A1782722742295B29B4D169D7F541859AF5761FAC96");
				}

				for (int j = 0; j < cmdList.size(); j++) {
					String cmdStr = cmdList.get(j);
					ByteBuffer bb = ByteBuffer
							.wrap(hexStringToByteArray(cmdStr));
					sc.write(bb);
				}
				sc.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
  }
  
  public void doNiaoLong(String host, int port, List<String> connectArray) {
	    for (int i = 0; i < connectArray.size(); i++) {
	      String loginStr = (String) connectArray.get(i);
	      try {
	        InetSocketAddress remote = new InetSocketAddress(host, port);
	        SocketChannel sc = SocketChannel.open();
	        sc.connect(remote);
	        if (sc.finishConnect()) {
	          System.out.println("connected...");
	        }

	        List<String> cmdList = new ArrayList<String>();
	        cmdList.add(loginStr);
	        cmdList.add("0000001400020300AE855A17827227F5418585C085BBA323");
	        cmdList.add("0000001400030300D6855A17827227F5418585C085BB25E6");
	        cmdList.add("0000001400040700C8855A17827227F5418585C085BB55E7");
	        cmdList.add("0000002500051C00C8855A17827227CCAA29EB291BF2F87E8FF2C278F28F23D1834185E8BBD628A9D2");
	        cmdList.add("0000001400060600AE855A17827227F5418585C085BBA327");
	        cmdList.add("0000001400071800C8855A17827227F5418585C085BB55E4");
	        cmdList.add("0000001400081B00C8855A17827227F5418585C085BB55EB");
	        cmdList.add("0000001400091B00C8855A17827227F5418585C085BB55EA");
	        cmdList.add("00000014000A2600C8855A17827227F5418585C085BB55E9");
	        cmdList.add("00000014000B2600C8855A17827227F5418585C085BB55E8");
	        cmdList.add("00000014000C260076855A17827227F5418585C085BBD84C");
	        cmdList.add("00000014000D2600AE855A17827227F5418585C085BBA32C");
	        
	        for (int j = 0; j < cmdList.size(); j++) {
	          String cmdStr = cmdList.get(j);
	          ByteBuffer bb = ByteBuffer.wrap(hexStringToByteArray(cmdStr));
	          sc.write(bb);
	        }
	        sc.close();

	      } catch (IOException e) {
	        e.printStackTrace();
	      }
	    }

	  }
  
  public static String sendRequest(String url, String stringParams) {

		HashMap<String, String> params = new HashMap<String, String>();
		HttppostDataCenter postDataCenter = new HttppostDataCenter();
		postDataCenter.setURL(url);
		postDataCenter.setParams(params);
		postDataCenter.setStringParam(stringParams);
		String a = postDataCenter.doPost();
		return a;
	}
  
public void doSanGuoBoss(String host, int port, ArrayList<String> loginStrs) {
	
	String loginStr = loginStrs.get(0);
	String loginStrB = loginStrs.get(1);
	try {
		InetSocketAddress remote = new InetSocketAddress(host, port);
		SocketChannel sc = SocketChannel.open();
		
		sc.connect(remote);
		if (sc.finishConnect()) {
			System.out.println("connected...");
		}
		sc.configureBlocking(false);
		
		List<String> cmdList = new ArrayList<String>();
		cmdList.add(loginStr);
		cmdList.add(loginStrB);
		cmdList.add("180003FF140061637469766974795F6974656D73203530373800");
		cmdList.add("140004FF10006F6E6C696E6574696D6520696E666F00");
		cmdList.add("0A0005FF0600626F61726400");
		cmdList.add("1F0006FF1B0072616E6B4C6973742067657452616E6B4C69737444617461203000");
		cmdList.add("0E0007FF0A0077626F73732037203200");
		cmdList.add("0C0008FF080077626F7373203100");
		cmdList.add("0E0009FF0A0077626F73732032203200");
		cmdList.add("0E000AFF0A0077626F73732037203200");
		cmdList.add("0E000BFF0A0077626F73732033203200");
		cmdList.add("0E000CFF0A0077626F73732034203200");
		cmdList.add("0F000DFF0B00636F707920626C6F636B00");
		cmdList.add("0E000EFF0A0077626F73732037203200");
		cmdList.add("0C000FFF080077626F7373203100");
		cmdList.add("0E0010FF0A0077626F73732032203200");
		cmdList.add("0E0011FF0A0077626F73732037203200");

		for (int j = 0; j < cmdList.size(); j++) {
			String cmdStr = cmdList.get(j);
			
			ByteBuffer bb = ByteBuffer.wrap(hexStringToByteArray(cmdStr));
			sc.write(bb);
			
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		sc.close();

	} catch (IOException e) {
		e.printStackTrace();
	}
	
  }

public void doSanGuoJunTuanBoss(String host, int port, ArrayList<String> loginStrs) {
	
	String loginStr = loginStrs.get(0);
	String loginStrB = loginStrs.get(1);
	try {
		InetSocketAddress remote = new InetSocketAddress(host, port);
		SocketChannel sc = SocketChannel.open();
		
		sc.connect(remote);
		if (sc.finishConnect()) {
			System.out.println("connected...");
		}
		sc.configureBlocking(false);
		
		List<String> cmdList = new ArrayList<String>();
		cmdList.add(loginStr);
		cmdList.add(loginStrB);
		cmdList.add("180003FF140061637469766974795F6974656D73203530373800");
		cmdList.add("140004FF10006F6E6C696E6574696D6520696E666F00");
		cmdList.add("0A0005FF0600626F61726400");
		cmdList.add("1F0006FF1B0072616E6B4C6973742067657452616E6B4C69737444617461203000");
		cmdList.add("120007FF0E0061726D7920626F7373696E666F00");
		cmdList.add("180008FF140061726D7920626173652031313730303030303800");
		cmdList.add("0E0009FF0A0061726D7920746F646F00");
		cmdList.add("13000AFF0F0061726D7920626F7373666967687400");
		cmdList.add("0F000BFF0B00636F707920626C6F636B00");
		cmdList.add("12000CFF0E0061726D7920626F7373696E666F00");
		cmdList.add("18000DFF140061726D7920626173652031313730303030303800");
		cmdList.add("0E000EFF0A0061726D7920746F646F00");
		
		
		for (int j = 0; j < cmdList.size(); j++) {
			String cmdStr = cmdList.get(j);
			
			ByteBuffer bb = ByteBuffer.wrap(hexStringToByteArray(cmdStr));
			sc.write(bb);
			
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		sc.close();

	} catch (IOException e) {
		e.printStackTrace();
	}
	
  }
  
public void doSanGuoZhaoMu(String host, int port, ArrayList<String> loginStrs) {
	
	// 120.24.82.223
	String loginStr = loginStrs.get(0);
	String loginStrB = loginStrs.get(1);
	try {
		InetSocketAddress remote = new InetSocketAddress(host, port);
		SocketChannel sc = SocketChannel.open();
		
		sc.connect(remote);
		if (sc.finishConnect()) {
			System.out.println("connected...");
		}
		sc.configureBlocking(false);
		
		List<String> cmdList = new ArrayList<String>();
		cmdList.add(loginStr);
		cmdList.add(loginStrB);
		
		cmdList.add("180003FF140061637469766974795F6974656D73203530373800");
		cmdList.add("140004FF10006F6E6C696E6574696D6520696E666F00");
		cmdList.add("0A0005FF0600626F61726400");
		cmdList.add("1F0006FF1B0072616E6B4C6973742067657452616E6B4C69737444617461203000");

		for (int j = 0; j < cmdList.size(); j++) {
			String cmdStr = cmdList.get(j);
			
			ByteBuffer bb = ByteBuffer.wrap(hexStringToByteArray(cmdStr));
			sc.write(bb);
			
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		sc.close();

	} catch (IOException e) {
		e.printStackTrace();
	}
	
  }

public static String byteBufferToString(ByteBuffer buffer) {
	CharBuffer charBuffer = null;
	try {
		Charset charset = Charset.forName("UTF-8");
		CharsetDecoder decoder = charset.newDecoder();
		charBuffer = decoder.decode(buffer);
		buffer.flip();
		return charBuffer.toString();
	} catch (Exception ex) {
		ex.printStackTrace();
		return null;
	}
}

private static String getToken(String JSONText) {
	JSONTokener jsonTokener = new JSONTokener(JSONText);
	JSONObject studentJSONObject;
	String tokenStr = null;
	try {
		studentJSONObject = (JSONObject)jsonTokener.nextValue();
		tokenStr = studentJSONObject.getString("token");
	} catch (JSONException e) {
//		 e.printStackTrace();
	}
	return tokenStr;
}

private static String getValue(String JSONText,String paramKey) {
	JSONTokener jsonTokener = new JSONTokener(JSONText);
	JSONObject studentJSONObject;
	String tokenStr = null;
	try {
		studentJSONObject = (JSONObject)jsonTokener.nextValue();
		JSONObject dataObject = studentJSONObject.getJSONObject("data");
		tokenStr = dataObject.getString(paramKey);
		
	} catch (JSONException e) {
//		 e.printStackTrace();
	}
	return tokenStr;
}

  public byte[] hexStringToByteArray(String s) {
    int len = s.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
      data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
          .digit(s.charAt(i + 1), 16));
    }
    return data;
  }

  public static int checkTime(int beginHour,int beginMinute,int duration){
	  
	    GregorianCalendar currentTime = new GregorianCalendar();
	    currentTime.setTime(new Date());
	    
	    int hour = currentTime.get(Calendar.HOUR_OF_DAY);
	    int minute = currentTime.get(Calendar.MINUTE);
	    
	    if(hour < beginHour || (hour == beginHour && minute < beginMinute)){
	      return 0;
	    }
	    
	    int hours = hour - beginHour;
	    int totalMinutes = hours*60 - beginMinute + minute;
	    if(totalMinutes < duration){
	      return 1;
	    }else{
	      return -1;
	    }
	    
	  }

  class MyThread implements Runnable {
    public void run() {
      System.out.println("MyThread begin....");
      try {
        do {
          doXiaonancheLanDa();
          label.setText("LanDaCount : " + landaCount);
          Thread.currentThread().sleep(interval*1000);
        } while (true);

      } catch (Exception e) {
        e.printStackTrace();
      }
      System.out.println("MyThread end....");
    }
  }
  
  class NiaoLongThread implements Runnable {
	    public void run() {
	      System.out.println("NiaoLongThread begin....");
	      try {
	        do {
	          doXiaonancheNiaoLong();
	          label.setText("LanDaCount : " + landaCount);
	          Thread.currentThread().sleep(20000);
	        } while (true);

	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	      System.out.println("NiaoLongThread end....");
	    }
	  }

  /**
   * login
   * 
   * @return
   */

  // ================================= ����

  /**
   * 9
   * 
   * @return
   */
  public List<String> getShiYi_login() {
    List<String> returnList = new ArrayList<String>();
    // ���� ʧ�� nuo006 lizhixiang1108
    // 9 112.124.21.177 8500
    returnList
        .add("000000730001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA69823EF33E59A5A56C3F23F20125F232AA160D5009B4350845E0E015806D9860D46ECBE7E478F7CF66902E3ED220D231F43BEED036C6F4F4360F203EDD45B67A3B9658B63BA545A120D0293E2D1285C2451F89AF92");
    return returnList;
  }

  /**
   * 5
   * 
   * @return
   */

  public List<String> getLangMan_login() {
    List<String> returnList = new ArrayList<String>();
    // ���� ���� yezi1999 654321
    // 5 112.124.21.177 8100
    returnList
        .add("000000750001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696C3016FA9580666666FE3F23F20125F232AA160D5009B42E0845E0E015806D9860D46ECBE7E478F7CF66902E3E4519F4CBB680D040930FDD19400F29DE58A13B4C4C40EE46463E3B80DED236EE2B12851FCB53F6A2FE");
    return returnList;
  }

  public List<String> getRenCai_login() {
    List<String> returnList = new ArrayList<String>();
    // ���� �˲� xy2000lovely59 2571411
    // 5 112.124.21.177 8100
    returnList
        .add("0000007B0001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA6935FA438002A5A50FB678253E5B80B3FE3F23F20125F232AA160D5009B42E0845E0E015806D9860D46ECBE7E478F7CF66902E3ED24C023E310F4C3696E0194CEEB696F2D293C6F4409646D00FF4400F3BA5F245601285CE1820F570EE");
    return returnList;
  }

  public List<String> getJunWei_login() {
    List<String> returnList = new ArrayList<String>();
    // 5�� ��ί��ϯ erduo121 123150
    // 5 112.124.21.177 8100
    returnList
        .add("000000740001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696C16F28F463E598002FE3F23F20125F232AA160D5009B42E0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2DD58A10F31F485B45CEEE6CF58755CEEFB5C4B4BCF132DBB101050775CA4E776853C5753015792");
    return returnList;
  }

  public List<String> getYeYuan_login() {
    List<String> returnList = new ArrayList<String>();
    // 5�� YeYuan a82032461 wd970121
    // 5 112.124.21.177 8100
    returnList
        .add("000000760001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696C2366A502A5D70245E7FE3F23F20125F232AA160D5009B42E0845E0E015806D9860D46ECBE7E478F7CF66902E3E45E0193B937AA5D0314C90D2C631939646190F0F31C67A90A129D24CA5A1EEB69C1285387C1FDE23B7");
    return returnList;
  }

  public List<String> getJieMo_login() {
    List<String> returnList = new ArrayList<String>();
    // 5�� ��ĩ 13877002760 412969000
    // 5 112.124.21.177 8100
    returnList
        .add("0000007E0001020076855A179AE545EBF420FE8593E37E71FDCB44D69F24C1466BC63135BBBF081C2AA5CC5C67820D0E02FFB6A4BA7B92FA4D0BAAB30FE61CFBAE234EDCDE96200ABEA42459247492AAB282F8CA55907B5460C5023E1C2DA513A5C24A94F0244DBAE88E6ED509ED2C46D021EDC95CA3B751BB98A208524C94AFD0E3");
    return returnList;
  }

  public List<String> getYouLeMei_login() {
    List<String> returnList = new ArrayList<String>();
    // 5�� ������ xufeng520 7511463520
    // 5 112.124.21.177 8100
    returnList
        .add("000000730001020076855A1722B0F79A4820A285C6EFBB0CF2292DB0532B5F90B87A48DCA40CB0DDE39C8E0D9C361ACA691FDDB1CDDF2D6E90B85B98FE395A01E8DD2758D4D679009D7F595459D3AFAB1160B15996613C79112C64728A3364122A8693BAAA4E77809D8F68C5494001F50873D85381051C");
    return returnList;
  }

  /**
   * 6
   * 
   * @return
   */
  public List<String> getXiaoTao_login() {
    List<String> returnList = new ArrayList<String>();
    // 6�� С�� wt1614 wutao123
    // 6 112.124.21.177 8000
    returnList
        .add("000000730001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA6982F2784580E780123F23F20125F232AA160D5009B4600845E0E015806D9860D46ECBE7E478F7CF66902E3ED290D03B7A409320DE3EF4C693C67A201936400FEED04093DD45D27A96E045A12D128591C91F4180C4");
    return returnList;
  }

  public List<String> getXiangShou_login() {
    List<String> returnList = new ArrayList<String>();
    // 6�� ������� zoz102 5201314
    // 6 112.124.21.177 8000
    returnList
        .add("000000730001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA6982FA956D0280A55F3F23F20125F232AA160D5009B4600845E0E015806D9860D46ECBE7E478F7CF66902E3ED23B4CC64C3102D0C693CBA13B293E40961929A1363B3629D02958A1364C317AC412853E0E1F2C2FE0");
    return returnList;
  }

  /**
   * 12
   * 
   * @return
   */
  public List<String> getDuC_login() {
    List<String> returnList = new ArrayList<String>();
    // 12�� ��c liu135903221 520hxl
    // 12 112.124.21.177 8800
    returnList
        .add("000000790001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA693536B629DE80D7B380A5D70202FE3F23F20125F232AA160D5009B4610845E0E015806D9860D46ECBE7E478F7CF66902E3ED202F2A12919DD3E93F48019C6A5A13B31F420F2D0F47AA5B69645D2EEA19329B11285272D94A267ED");
    return returnList;
  }

  /**
   * 29
   * 
   * @return
   */
  public List<String> getDuC_login29() {
    List<String> returnList = new ArrayList<String>();
    // 29�� ��c kuangxiongq 520hxl
    // 29 112.124.31.200 8100
    returnList
        .add("000000770001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696CAA3F1B36256D8502B75DB163056395116372E705E19E1FD5C5671623C2783EF30D69123FC216AA252946F28FD128164545B38045DEA5A6028FD70280CA7F7FA54516E716D72302B425E7A6A625238341858EEE200E5A3A");
    return returnList;
  }

  /**
   * 2
   * 
   * @return
   */
  public List<String> getMuMu_login() {
    List<String> returnList = new ArrayList<String>();
    // 2�� ĿĻ beckszwf23 zwf123456
    // 2 112.124.52.173 8000
    returnList
        .add("000000770001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696C1B8F1BC23F1E0167E7028C3F23F20125F232AA160D5009B42B0845E0E015806D9860D46ECBE7E478F7CF66902E3E45B6933631DD3E40EED27A7A404C0F40F4DDD2F420DE4558D07A31A5D2EEDEB62D12855A3194C01045");
    return returnList;
  }

  /**
   * 1
   * 
   * @return
   */
  public List<String> getLiangEr_login() {
    List<String> returnList = new ArrayList<String>();
    // һ�� ���� qaz544383246 xxq5201314
    // 1 112.124.52.173 8100
    returnList
        .add("000000780001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA693529163002DE4545CAA5D702456C3F23F20125F232AA160D5009B40A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45A131F44CCB3E0246D20F7AA53ECBA10F93CBA1C620D00F3D43CB5819F4292E1285D1A694028D5F");
    return returnList;
  }

  /**
   * 11
   * 
   * @return
   */
  public List<String> getMerryXmas_login() {
    List<String> returnList = new ArrayList<String>();
    // 11�� MerryXmas lorl30 6011066
    // 11 112.124.21.177 8700
    returnList
        .add("000000730001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA69823EB6789545D7A23F23F20125F232AA160D5009B4E60845E0E015806D9860D46ECBE7E478F7CF66902E3E45193BF4EEB696E03E4CEE58193680190FA558D24CA5E0DEF23EF4DD19021996B112856FC853A8DF0F");
    return returnList;
  }

  // =============================== HY

  /**
   * 19
   * 
   * @return
   */
  public List<String> getDuC_loginHY() {
    List<String> returnList = new ArrayList<String>();
    // 19�� ��c amp12345678 520hxl
    // 19 121.199.16.65 8400
    returnList
        .add("000000770001020076855A1722B0F79AC3A5A28537FBE83FBFBF44B05C646045CC50EE0E849F39C6BA2F2B02349E83323BD0600265DF61C51F620670BB288F4D572BE0F9EB2FB7C4A455BBA81C15D47F34F8D48BA08A8BF328A1E40D39B19B0B48A97E6314BD60501FD8D94721221087BF22D78ADC0A3E53E9B3E9");
    return returnList;
  }

  // ================================= HZ

  /**
   * 12
   * 
   * @return
   */
  public List<String> getDuC_loginHZ() {
    List<String> returnList = new ArrayList<String>();
    // 12�� ��c liu1359032211 520hxl
    // 12 112.124.2.215 8000
    returnList
        .add("0000007A0001020076855A178272274223FA90468F4283F5FE3FC216AA252946F28FD12880D7A52480B416DE1602CA162345DE80E723A666B4161BE7DE451680E78002B4123F9095AA3E78B636AA6935C2B629DE80D7B380A5D702028042671623C2783EF30D82A23F23F20125F232AA160DFB57FC611285F3F594451127");
    return returnList;
  }

  // ======================== HY LOGINS

  public List<String> getExcuser5y_login() {
    List<String> returnList = new ArrayList<String>();
    // 2�� Excuser5y zxcccc2 tiffany
    // 2 121.199.12.211 8000
    returnList
        .add("000000700001020076855A179AB025EB37A5A28593B9CF5D8C8C2149C1CE40A6CF8EEF3F66028F7A5B9CDF465A78A6B1172B6772B023ECBCBA076915E9E3213519FF8D6D16F90C875FDA02C3CE1D65F530AD1ACB6CEB7719BDE206F89D85397793496919B322A2802707FD7AFBA008F153D1A30B");
    return returnList;
  }

  public List<String> getDongNiDD_login() {
    List<String> returnList = new ArrayList<String>();
    // 2�� ����ܵ� hanarain67 540118
    // 2 121.199.12.211 8000
    returnList
        .add("000000760001020076855A1722B0B09AF4A57E22C022BEE0669FEB12746E378244F55FD32C3F4248C33BFE458C284522549BC35D6331510411AF3370242C787DAA178FBE1574370D905CC9EF67E7D3C2C70A284FB3A33F29C51E4A7D2B086C8A45AC7A2CB7DCC616E75D1E2C2B23F1C9EEEF52AE5B0E94BB1D07");
    return returnList;
  }

  public List<String> getShangXin_login() {
    List<String> returnList = new ArrayList<String>();
    // 1 �� ���ĵ��˱������ zandy002 2014123
    // 1 121.199.25.76 8000
    returnList
        .add("000000750001020076855A17827227FE3F23F20125F232AA160D196A32736EBF90085915EEE7082F1B7F0145787540C6C649FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED220F2B6C60FDD58A1F402453E31C67A31400FF49645581936937A360F3B4C3148128534111FBC37C3");
    return returnList;
  }

  public List<String> getHeiZi_login() {
    List<String> returnList = new ArrayList<String>();
    // 35�� ������Ҳ wswzw52077 2225273adam
    // 35 112.124.6.243 8000
    returnList
        .add("000000770001020076855A17827227FE3F23F20125F232AA160D196A8FFB6EBF90085915EEE7082F1B66916E91F83B4C0FC63B9CFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45F246D020D27A3690D23B9390D290F2463E20B67A80B640360F2045B629A140CC1285F7899426CF31");
    return returnList;
  }

  public List<String> getHaoGe_login() {
    List<String> returnList = new ArrayList<String>();
    // 2�� �Ƹ� q707425402 q623123048
    // 2 121.199.12.211 8000
    returnList
        .add("000000770001020076855A17827227FE3F23F20125F232AA160D196A32A66EBF90085915EEE7082F1B66933BC63B7A0F4C7AC649FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45D00F80D00FC6803E40EEA129F23EC636A5B64090E0E0A129B63693314C20B69C1285C024531D74A2");
    return returnList;
  }

  public List<String> getWoBuYuan_login() {
    List<String> returnList = new ArrayList<String>();
    // 2�� �Ҳ�Ը����һ���� zandy1986 112233
    // 2 121.199.12.211 8000
    returnList
        .add("000000760001020076855A17827227FE3F23F20125F232AA160D196A32A66EBF90085915EEE7082F1B6E01457875409340F456FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED24CC6EE19F4C6CBDE45D02919CBB64C7A0FEEF2B6C690F245A1403BF436F4022E1285ADF9534CD38D");
    return returnList;
  }

  public List<String> get5217_login() {
    List<String> returnList = new ArrayList<String>();
    // 2�� 5217 goldfash 1997225pp
    // 2 121.199.12.211 8000
    returnList
        .add("000000760001020076855A17827227FE3F23F20125F232AA160D196A32A66EBF90085915EEE7082F1B6E01457875409340F456FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED24CC6EE19F4C6CBDE45D02919CBB64C7A0FEEF2B6C690F245A1403BF436F4022E1285ADF9534CD38D");
    return returnList;
  }

  public List<String> getBuZhuangBi_login() {
    List<String> returnList = new ArrayList<String>();
    // 42�� ��װ�� 419879700 tianqi
    // 42 112.124.37.109 8100
    returnList
        .add("000000760001020076855A17827227FE3F23F20125F232AA160D196A8F256EBF90085915EEE7082F1B317A9340F43B403BC648FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2DDD20F4C4CF436EEB693293EEEA17ADD58B63B0219A5193102D240DDD0DDF250128578331F067358");
    return returnList;
  }

  public List<String> getSoul_login() {
    List<String> returnList = new ArrayList<String>();
    // 2�� soul zxcccc6 sos123456
    // 2 121.199.12.211 8000
    returnList
        .add("000000720001020076855A17827227FE3F23F20125F232AA160D196A32A66EBF90085915EEE7082F6015F8964B47366E05F38F325F39BB4BA36C3535BC25BC8F31E19863E7B7BC5F258F6311193D1B11F0B729296C3E357EB7113611F05F059A16F21116B75635F05F3E16F236B7BBC8E39F94744E49");
    return returnList;
  }

  public List<String> getShuiNi_login() {
    List<String> returnList = new ArrayList<String>();
    // 2�� ˮ����� kding09 llj122103106184llx
    // 2 121.199.12.211 8000
    returnList
        .add("000000730001020076855A1722B02AEBC320A285C693BBEB576AC6ACBFE3442FFF2B19F49DA23C2DE2E64DDF09A332C2D38FCEC36425DDBD4C4F707154CD241757F257E1053F63C8D4158E315D45E3569B291BADE90F25B0AD02B665D6857FDA19B67452509CE7B0976F644198590390DC3FDE1FBDDC40");
    return returnList;
  }

  public List<String> getEdward_loginHY() {
    List<String> returnList = new ArrayList<String>();
    // 31�� Edward PP58244464 19811122
    // 31 112.124.5.172 8000
    returnList
        .add("0000007E0001020076855A1722B02AEBF420A285C6EF803D17C39F534E19AB3FB1832F997A297F5CEBC41911B718A33F7C8D36CB463AF7A0E181581DB2B2199175211378FA06632268C818478CF2E94E2766448BCC17921A533634842EE93F30684EE0F50C7A2F0B5698EB455A8F5DD4ADF39E22D94D799343CCE9AE892520A99D7B");
    return returnList;
  }

  public List<String> getSXiaoHao_login() {
    List<String> returnList = new ArrayList<String>();
    // 2�� sС�� fqm1239 qwaszx
    // 2 121.199.12.211 8000
    returnList
        .add("000000740001020076855A17827227FE3F23F20125F232AA160D196A32A66EBF90085915EEE7082F6008FAA562930F31F0FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45D0904646D220B6C636DD3EF4F4F44C7AF43629453E7A02E0E046B629D296A1981285D91C53B4C772");
    return returnList;
  }

  public List<String> getHaoGePengYou_login() {
    List<String> returnList = new ArrayList<String>();
    // 5�� �Ƹ����� a89681272 a89681251
    // 5 121.199.44.71 8100
    returnList
        .add("000000750001020076855A1722B025EBC320A28593EFE18CD5CEC4630F8A4725674056A5BB8C22E783B7699BCD84F470BAB04200D3D40E2680556D613B5BFF1378FA572C4DCB6DD33022DCDBB00F316C838718A7A0EFAC1901D335EF78C2434B93FA77C9C7B0918793E0D6492DD98A30A604D6E7091F3BEAB0");
    return returnList;
  }

  public List<String> getZhiZunBao_login() {
    List<String> returnList = new ArrayList<String>();
    // 26�� ���� jy8760125 8760125
    // 26 112.124.2.232 8100
    returnList
        .add("000000760001020076855A17827227FE3F23F20125F232AA160D196A8F8F6EBF90085915EEE7082F1BCB7D40F43B36C6930FCCFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45A1F47A36C6DD19363BDD46B631F440407A404C963E963E40C631A5194C9019491285D4B51F2EBEE8");
    return returnList;
  }

  public List<String> getHuYa_login() {
    List<String> returnList = new ArrayList<String>();
    // 54�� ���� zwmboss37 123456
    // 54 112.124.55.42 8100
    returnList
        .add("000000760001020076855A17827227FE3F23F20125F232AA160D196A95166EBF90085915EEE7082F1B6EF8DD5946156E31319CFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED236DD194031C6C6A5D0203E36A5A1310F4036F431F4CBA14029A1961980463E561285B82694AE400A");
    return returnList;
  }

  public List<String> getZhangYiFan_login() {
    List<String> returnList = new ArrayList<String>();
    // 55�� ��һ�� zhangzifan4 123456
    // 55 112.124.55.42 8000
    returnList
        .add("000000780001020076855A17827227FE3F23F20125F232AA160D196A95CD6EBF90085915EEE7082F1B91013C45782101E7F245AA98FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2CB46E0199380D27A939046197A314C3BDDD280D00F3696D2310F400F3602DE2B1285C8B920DECB02");
    return returnList;
  }

  public List<String> getPity_login() {
    List<String> returnList = new ArrayList<String>();
    // 85�� pity pitt2012 123456
    // 85 115.29.163.149 8300
    returnList
        .add("000000730001020076855A1722B02A0C98A57E85370C40C35397E912C7750E9B1274F6EF7A759F512B73A09D990B7C8D1633BB09D53B290B8B0670C16E719592802E871A3C5BE9886A9A0801C0A4B7768339D51795B8F9E62A780D1A56B78CF497F7610D5D49C6530A72164828C6B4E39244EB942FC90A");
    return returnList;
  }

  public List<String> getADai_login() {
    List<String> returnList = new ArrayList<String>();
    // 19�� ���� kk424515 5653285fsc
    // 19 121.199.16.65 8400
    returnList
        .add("000000740001020076855A1722B02A0CC3A5A2853776F0C3E049423CA889D23D525F19AFCA5F7A979DE1E929B2D05EEDA8DE95842994F658A19EDCFB74B419251B583888AB8EF147EB7EEA7DE37DB96197C595BE59B5160B8E0E2C38BD426B80A236D3D665553D20CB77724E969A64E811EAD9C053946E47");
    return returnList;
  }

  public List<String> getHNan_login() {
    List<String> returnList = new ArrayList<String>();
    // 58�� H�� h41508323 111111
    // 58 112.124.55.5 8100
    returnList
        .add("000000750001020076855A1722B02AEBC320A285C6EFBBEB0E6AC6AC437FD5F93E26807D1E952A4A3480FF34B2C5F39C5BA4D1EBF663C56CBD06EA153E5E6F842536A6E63E15C68B38CDE51596B9F3D04080C5E7E24F503C1E2B9546309FA4F449251D47B8D194A2BBC6AA48DFCE4974779B85DBB91F81BCD9");
    return returnList;
  }

  public List<String> getJBan_login() {
    List<String> returnList = new ArrayList<String>();
    // 54�� � zwmboss41 123456789a
    // 54 112.124.55.42 8100
    returnList
        .add("000000760001020076855A17827227FE3F23F20125F232AA160D196A95166EBF90085915EEE7082F1B6EF8DD5946156E317AEFFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED240A5F25846D280A12046D2C60258B640DDD0313620D036404080D24096A1F42D1285B34E94157EB2");
    return returnList;
  }

  public List<String> getJJ_login() {
    List<String> returnList = new ArrayList<String>();
    // 17�� JJ jjhyork 111111
    // 17 121.199.16.65 8000
    returnList
        .add("000000740001020076855A17827227FE3F23F20125F232AA160D196A8FC06EBF90085915EEE7082F6008F3F3D8021590E6FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2C629D2EE3E2945B60F367A0FCBF219291920D09302D280B690B6369619A5E02E1285D1EF9429D41A");
    return returnList;
  }

  public List<String> getJiaJu_login() {
    List<String> returnList = new ArrayList<String>();
    // 6�� �Ҿ� humiao2008 sarol1023
    // 6 121.199.44.71 8000
    returnList
        .add("000000750001020076855A179A7A2AEBF420A285C6EF803D95B0AEEC947F5FD3062510A92AC5850BCAE3E93099DF5E31E03687CC4F3AA03A29581B5267AB2F44C5926EF9BC744CC47EC23316FC794E304C2C2B5A661ED91AF65EE54DEE3852E5F15A2CA2C8E48D6CDD770C44B13C6547E811EACCDE53D2D65C");
    return returnList;
  }

  public List<String> getXiaoQi_login() {
    List<String> returnList = new ArrayList<String>();
    // 30�� С�� yzqaaabbbb 591247
    // 30 112.124.5.172 8100
    returnList
        .add("000000740001020076855A17827227FE3F23F20125F232AA160D196A8F236EBF90085915EEE7082F1B66D5F8A54545452C476E05F38F325F39BB4BA36C3535BC25BC8F31E19863E7B7BC5F258F6311193D05369A3516F016F2601B48CCF75BFB8B5B75A613327B3900C9320DF8738B4B3C85407B94B6C9DF");
    return returnList;
  }

  public List<String> getCP_login() {
    List<String> returnList = new ArrayList<String>();
    // 37�� CP cpalfn1 111111
    // 37 112.124.6.243 8300
    returnList
        .add("000000740001020076855A17827227FE3F23F20125F232AA160D196A8FBE6EBF90085915EEE7082F6008392045D9F2AAEFFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED24CA5D27A310F0258D2EEA13631A5E0B690F2D03BA51936DD3E0F31400FF43BF0128567DD53DB5D46");
    return returnList;
  }

  public List<String> getGG_login() {
    List<String> returnList = new ArrayList<String>();
    // 84�� GG gghh17 111111
    // 84 115.29.163.149 8200
    returnList
        .add("000000730001020076855A17827227FE3F23F20125F232AA160D196A01976EBF90085915EEE7082F6059E4E43CE5939CFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2F40F402946DEDEE0D280DEF2B67A0F3BDDE058199658D090D20F31DD194C93CC1285FD985324D238");
    return returnList;
  }

  public List<String> getGuangMing_login() {
    List<String> returnList = new ArrayList<String>();
    // 43�� ���� bright2657 123158888
    // 43 112.124.37.109 8000
    returnList
        .add("000000770001020076855A17827227FE3F23F20125F232AA160D196A8F516EBF90085915EEE7082F1B805B90E7E4D87A0F364C9CFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED24C0219403B29D2A5D290B6EE199645D09646A13640403BC6C60F29D296A1020A12855BAA94CDB931");
    return returnList;
  }

  public List<String> getLEE_login() {
    List<String> returnList = new ArrayList<String>();
    // 44�� LEE narutoli 20140213
    // 44 112.124.37.109 8200
    returnList
        .add("000000750001020076855A17827227FE3F23F20125F232AA160D196A8FAC6EBF90085915EEE7082F1BA578F74366EE08D935FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45E0B6C696E0DEA13140363B364C96B693A5B63B4090F23E4C7A3B0F3B293E409C128526E3949631A2");
    return returnList;
  }

  public List<String> getFengShao_login() {
    List<String> returnList = new ArrayList<String>();
    // 4�� ���� a654032374 ho19911002ho
    // 4 121.199.44.72 8000
    returnList
        .add("000000760001020076855A1722B02AEBF420A285C6EFA2EBA7B0AEEC94F351A203997AC0E17E9FB0875BF93AE93F7C8D36805D2112EA2B14DEDFBCD99B61521A2F17E679BA186537C370D1A869449C986E7ACCE9FC1CF00E7D6D8CF1C2CF53418E9F82C630191CE8494EE4FCCD468C57B4D1E2EA2AF71FB2368C");
    return returnList;
  }

  public List<String> getFGY_login() {
    List<String> returnList = new ArrayList<String>();
    // 88�� fgy112233 232932
    // 88 115.29.168.214 8200
    returnList
        .add("000000750001020076855A17827227FE3F23F20125F232AA160D196A01E86EBF90085915EEE7082F1BCBF2214093930F0F312DFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED23B20B629DE58B63129F21941B41377E64B2D774BE6A413BB13EED98A7710E77685576453B61AB4");
    return returnList;
  }

  public List<String> getAK_login() {
    List<String> returnList = new ArrayList<String>();
    // 35�� AK lanwan 1234567
    // 35 112.124.6.243 8000
    returnList
        .add("000000720001020076855A17827227FE3F23F20125F232AA160D196A8FFB6EBF90085915EEE7082F6059D9456DDD452FFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED24C9393A53E93CB1990B6A519FE0F8F163A161666B4B380F780DE45A52516274185CBFE53CD9DDB");
    return returnList;
  }

  public List<String> getPiCong_login() {
    List<String> returnList = new ArrayList<String>();
    // 74�� ƨ�� picong110 qq754159665
    // 74 115.29.168.108 8100
    returnList
        .add("000000730001020076855A1722B0F79A4820A285C6EFBB0CF229E1975F3729C74FF4916A5C0CC9BC8B8929BD570C5406BA436CA3BA840530FC68119FB7235873F606B4006FA8A9624E05BB07DA1347001ECDF575C266D19065D83D45DBA5C394662238B8FB5E187FBC12F6FC9B34AB20882E0994A2CA83");
    return returnList;
  }

  public List<String> getDaBao_login() {
    List<String> returnList = new ArrayList<String>();
    // 55�� �� daboss64 111111
    // 55 112.124.55.42 8000
    returnList
        .add("000000740001020076855A17827227FE3F23F20125F232AA160D196A95CD6EBF90085915EEE7082F1BA5584546156E313698FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2364C02F23E368046D036F43B20A1C6DD58B6DD2CB44BCF10CF588ABB2D10457685C98694EE2690");
    return returnList;
  }

  public List<String> getAiFei_login() {
    List<String> returnList = new ArrayList<String>();
    // 36�� ���� lovefeifei005 123456
    // 36 112.124.6.243 8200
    returnList
        .add("000000780001020076855A17827227FE3F23F20125F232AA160D196A8FD46EBF90085915EEE7082F1BDBD91529DEF2DE6C497E7E163905F38F325F39BB4BA36C3535BC25BC8F31E19863E7B7BC5F258F6311193D35562916163529294056F0609AF07EF036F2569A9A60356C3EF0363E161B115F4BC8D9662057603F");
    return returnList;
  }

  public List<String> getSun_login() {
    List<String> returnList = new ArrayList<String>();
    // 41�� �� 544543441 yuan15200623936
    // 41 112.124.52.68 8300
    returnList
        .add("000000750001020076855A17827227FE3F23F20125F232AA160D196A8F056EBF90085915EEE7082F1B314C7A7A4C7A317A7AEFFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2F4317A0246A1A5A140A558DE19F490A1C63BC6EEB63696224308B690D0402D1285A5EC1F142568");
    return returnList;
  }

  public List<String> getZuoShou_login() {
    List<String> returnList = new ArrayList<String>();
    // 30�� ���� hwf7238960 7238960
    // 30 112.124.2.232 8600
    returnList
        .add("000000770001020076855A17827227FE3F23F20125F232AA160D196A8F236EBF90085915EEE7082F1B80D8DD3E3B0F31F4403648FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED23631369393F43B0FDD3E4C93407A36DD3E20A1C64C023E36CB3E7A96F2A120501285F5D81FA3F889");
    return returnList;
  }

  public List<String> getRZ_login() {
    List<String> returnList = new ArrayList<String>();
    // 11�� RZ ren3717991146 46013018130
    // 11 121.199.5.229 8200
    returnList
        .add("000000770001020076855A179AF145EBC3A57E8537D4E84B0FC6EFF714C6A2D06F0F250171CDCC00CF027B72FBFF656DBF244F3856B2D4158B6DFEE6B2C555ACDF340701194DB96F494897858DF9423095FA451F7825E057B360B2B710395301AD964B20F43BAC9CDAD3362968E967A22BAED4025936F294F48D12");
    return returnList;
  }

  /**
   * mine
   */
  public void doSelfLanDa() {
    doLanda("112.124.21.177", 8800, get12_login());
    doLanda("112.124.41.88", 8100, get13_login());
    doLanda("112.124.41.88", 8000, get14_login());
    doLanda("112.124.41.88", 8200, get15_login());
    doLanda("112.124.41.88", 8300, get16_login());
    doLanda("112.124.55.30", 8100, get17_login());
  }

  /**
   * DAI
   */

  public void doDai9LanDa() {
    doLanda("112.124.21.177", 8500, getShiYi_login());
  }

  public void doDai5LanDa() {
    doLanda("112.124.21.177", 8100, getRenCai_login());
    doLanda("112.124.21.177", 8100, getJunWei_login());
    doLanda("112.124.21.177", 8100, getLangMan_login());
    // doLanda("112.124.21.177",8100,getYeYuan_login());
    // doLanda("112.124.21.177",8100,getJieMo_login());
    doLanda("112.124.21.177", 8100, getYouLeMei_login());
  }

  public void doDai6LanDa() {
    // doLanda("112.124.21.177",8000,getXiangShou_login());
  }

  public void doDai12LanDa() {
  }

  public void doDai1LanDa() {
  }

  public void doDai2LanDa() {
    doLanda("112.124.52.173", 8000, getMuMu_login());
  }

  public void doDai11LanDa() {
    doLanda("112.124.21.177", 8700, getMerryXmas_login());
  }

  public void doDai19PeiEn() {
    doPeiEn("121.199.50.216", 8000, getDuC_loginHY());
  }

  public void doDai12DingShang() {
    doPeiEn("112.124.2.215", 8000, getDuC_loginHZ());
  }

  public List<String> getLingFan2Dui_login() {
    List<String> returnList = new ArrayList<String>();
    // ���� �㷬�� ray317 85641305
    // 2 112.124.52.173 8000
    returnList
        .add("000000730001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA6982F28F3080D780653F23F20125F232AA160D5009B42B0845E0E015806D9860D46ECBE7E478F7CF66902E3ED296D080A1EEE03E0F36F420A1203EDD4558A196D07A4CF4314C90DEE0DEDEA1C41285FC0B53CBA5AA");
    return returnList;
  }

  public List<String> getAShui_login() {
    List<String> returnList = new ArrayList<String>();
    // 8�� ��ˮ richie88 584520a
    // 8 112.124.21.177 8300
    returnList
        .add("000000750001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696C299516C20F16B37FA23F23F20125F232AA160D5009B4C30845E0E015806D9860D46ECBE7E478F7CF66902E3ED2DDA1965845D07AC67A0F90D093DDE0B6312945D093C631C6C690F2D0934C36CC1285A57253E1BCFB");
    return returnList;
  }

  public List<String> getXuSheng_loginHY() {
    List<String> returnList = new ArrayList<String>();
    // 11�� ������� yao723955 yao
    // 11 121.199.5.229 8200
    returnList
        .add("000000750001020076855A17827227FE3F23F20125F232AA160D196A32516EBF90085915EEE7082F1B6E0245B23B0F31404CCCFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED290B6CBB6317A4C80453E7A934C937AC6C6C6A5B693CB19404CBB90CCCC9C8E478523EA531282D1");
    return returnList;
  }

  public List<String> getXuSheng_login() {
    List<String> returnList = new ArrayList<String>();
    // 1�� ������� M.0003 yao
    // 1 112.124.52.173 8100
    returnList
        .add("000000730001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA69829BD0E7A5A5A58C3F23F20125F232AA160D5009B40A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2EE3E3190B67ADD5858B6F4CBD280F2D2EEA19646D24C3B0246B631310258A19812851ECA1F4833E2");
    return returnList;
  }

  public List<String> getFengHuang_login() {
    List<String> returnList = new ArrayList<String>();
    // 11�� ��� 519233019 yt1500135
    // 11 112.124.21.177 8700
    returnList
        .add("000000760001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696CD7DE668002D7D7A566FE3F23F20125F232AA160D5009B4E60845E0E015806D9860D46ECBE7E478F7CF66902E3ED2F47A0FDDD0F4DD190FA5D07A363B7AA5D23BF4EED280D2937AC6363620B6C6CC1285319E3DE66D4B");
    return returnList;
  }

  public List<String> getShuiJun_login() {
    List<String> returnList = new ArrayList<String>();
    // 2�� ˮ�� xinxin127 880902
    // 2 121.199.12.211 8000
    returnList
        .add("000000730001020076855A17827227FE3F23F20125F232AA160D196A32A66EBF90085915EEE7082F1B6E96E71D497E60363905F38F325F39BB4BA36C3535BC25BC8F31E19863E7B7BC5F258F6311193D3E567E7E60605F3E1B0556405F9A1660054804F5253C6CE735EE6CAE35CFEEBA850E365303C6FA");
    return returnList;
  }

  public List<String> getYingXiaoDui_login() {
    List<String> returnList = new ArrayList<String>();
    // 13�� �С�� aa734452462aa 13518061311
    // 16�� �С�� c18608959830 13518061311
    // 16 112.124.41.88 8300
    returnList
        .add("000000770001020076855A1722B02AEBC320A285C6EFBBEB0ECE0035158565EDE0B5583EC4CF8DC0A5F35C7C8269A0FB18E92A7EF9071621A20782E97DF26296F14A449DCEB8AC930767A077DC42B31A946B8BBB387310044C8415B117B0A699652FCF1F17A285936D1C9610A131A51B03C6D6A2EACB051FB26C70");
    return returnList;
  }

  public List<String> getZiMu_login() {
    List<String> returnList = new ArrayList<String>();
    // 30�� ��Ļ 434368640 zxj19900620
    // 30 112.124.31.200 8000
    returnList
        .add("000000760001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696CD745D745D73AA5E745A23F23F20125F232AA160D50090F030845E0E015806D9860D46ECBE7E478F7CF66902E3ED24CF4023EA5194C9046D2A545D002E03E02D04CEEE0E0E0D00F7ADDD0023E7AF01285649653166783");
    return returnList;
  }

  public List<String> getDiDiao_login() {
    List<String> returnList = new ArrayList<String>();
    // 8�� �͵� yjn014 907523 pc�汾
    // 8 112.124.21.177 8300
    returnList
        .add("000000710001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA6982FA3695E7A580123F23F20125F232AA160D5009B4C30845E0E015806D9860D46ECBE7E478F7CF66902E3E45F2D240F4407A0F0FC6EE3EC6C69390D2F431409320D2FE4916361B1111364BC80F053D934BF9");
    return returnList;
  }

  public List<String> getXiaoWu_login() {
    List<String> returnList = new ArrayList<String>();
    // 27�� С�� xushijie12 qwerty
    // 27 112.124.27.101 8200
    returnList
        .add("000000770001020076855A1782722742671623C2783EF30D6CFA90F2C20F369516DE805F3F23F20125F232AA160D50090F136ECBE7E478F7CF66902E3ED2DDD07AA5D00F203E0FA5F2B640364C20D0DDA129A1409045197AEEB629A1960AFDB38BCF900A630A736EBF90085915EEE7082F602B1285CFBA94362512");
    return returnList;
  }

  public List<String> getXiaoWu_login10() {
    List<String> returnList = new ArrayList<String>();
    // 10�� С�� whzlaiao1 9266
    // 10�� С�� whzlaiao8 9266
    // 10 112.124.27.101 8200
    returnList
        .add("000000770001020076855A1782722742671623C2783EF30D6CFA90F2C20F369516DE805F3F23F20125F232AA160D50090F136ECBE7E478F7CF66902E3ED2DDD07AA5D00F203E0FA5F2B640364C20D0DDA129A1409045197AEEB629A1960AFDB38BCF900A630A736EBF90085915EEE7082F602B1285CFBA94362512");
    return returnList;
  }

  public List<String> get12_login() {
    List<String> returnList = new ArrayList<String>();
    // 12 112.124.21.177 8800
    returnList
        .add("000000770001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696CFA0F1636AA253625C2B4E23F23F20125F232AA160D5009B4610845E0E015806D9860D46ECBE7E478F7CF66902E3ED2F43B363693C6DD58D07A3696DE453EA55858A10F96A13B4CF44C3129F23EEEB112852D4E94A3BD45");
    return returnList;
  }

  public List<String> get13_login() {
    List<String> returnList = new ArrayList<String>();
    // 10 112.124.41.88 8100
    returnList
        .add("000000770001020076855A1782722742671623C2783EF30D6CFA0F1636AA253625C2B4E23F23F20125F232AA160D5009B45E6ECBE7E478F7CF66902E3ED2F43B363693C6DD58D07A3696DE453EA55858A10F96A13B4CF44C3129F23EEEB1FDB38BCF900A630A736EBF90085915EEE7082F602B1285669B94648500");
    return returnList;
  }

  public List<String> get14_login() {
    List<String> returnList = new ArrayList<String>();
    // 10 112.124.41.88 8000
    returnList
        .add("000000770001020076855A1782722742671623C2783EF30D6CFA0F1636AA253625C2B4E23F23F20125F232AA160D5009B4676ECBE7E478F7CF66902E3ED2F43B363693C6DD58D07A3696DE453EA55858A10F96A13B4CF44C3129F23EEEB1FDB38BCF900A630A736EBF90085915EEE7082F602B1285B3CB94A38B50");
    return returnList;
  }

  public List<String> get15_login() {
    List<String> returnList = new ArrayList<String>();
    // 10 112.124.41.88 8200
    returnList
        .add("000000760001020076855A1722B02AEBC4A5A285C64CEF534E4E7B8D1F3237ABF37845E681340E446229A16A5C9482DF2D86FD28B1CCF5BD0752D848A54413B6C7CF7C4018B09E701539FA91BB061EF526BB919481C8866F0A201D496449BB9ECB2F4DBD2CFDDBF8E80B56524391758C0D7445D9B37494E98946");
    return returnList;
  }

  public List<String> get16_login() {
    List<String> returnList = new ArrayList<String>();
    // 10 112.124.41.88 8300
    returnList
        .add("000000750001020076855A1722B0FDEBE5207E85C012BE7EB693C8C553A3760B6711FB31667EF7F361B9E01B1D35359B21CAE709B4A6EB1ECA085D47AE8A5939B8D325E9295A524FC3ABDF637B538FB512C37082A2EF8C942C6F4949057A7F8EA9B5E07831D2FBE213693BACD1351398C769DCB39994527301");
    return returnList;
  }

  public List<String> get17_login() {
    List<String> returnList = new ArrayList<String>();
    // 10 112.124.55.30 8100
    returnList
        .add("000000760001020076855A1722B02AEBC4A5A285C6CCEF534EC67BEC94B97BA7D0262D1B097DE46E25F6835A602FECD1C27ED8502018E06E19DFFB2ABBCB150FA7CF9440ACA1DC52BCAF6D671CAD90197432DA64E096F2DA12FE2F496298891B38F2CCD54EB41805BB5689E6955113E2CAB8A3AECAC89455E94D");
    returnList
        .add("000000770001020076855A1722B045EBC3A57E853722BE4BE3CEEF0068EF7701B60F25EFD13BD771C7F57672DEDDA878384FAB50FD2095C2D0B518C80DE355C057A4F92ADB26C5AD6B91AB95E67B818FAD8B04F10D2C46A1550F673D05CFDD970E22984E3CB103C2F8935C82342E6D4C9687A4BC9F791994BF7544");
    return returnList;
  }

  public List<String> get39_loginHZ() {
    List<String> returnList = new ArrayList<String>();
    // xiaonanche
    // no.39 112.124.31.220 8100
    returnList
        .add("000000760001020076855A1722B0B09AC3A57E853722BEC9C6D82F49E40CB1EEB22311FBF771BCC61E1643BB43527F5F09C71881EFF004CDC7EAFEA2D6239457A4F92FD34F6AB5086570BEFB758C0DABEDEDB9B19DBB242EB17B818FB5E2C370E53C201B4D5F42C812F1490D03BCB7BDDD7B3985B78720D4FE78");
    return returnList;
  }

  public List<String> getTuTu_login() {
    List<String> returnList = new ArrayList<String>();
    // һ�� ���� qq2410427 ����xiaobaitu0000
    // 1 112.124.52.173 8100
    returnList
        .add("000000760001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696C3F2980024580A54502653F23F20125F232AA160D5009B40A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45453EA5A1A5B6363120D093EEB6404CF4A558F2B6CBF2F2F2197A0F937ACB455012851242530E4E14");
    return returnList;
  }

  public List<String> getLinLin_login2() {
    List<String> returnList = new ArrayList<String>();
    // 2�� ���� chizhifeng 6521
    // 2�� 112.124.52.173 8000
    returnList
        .add("000000770001020076855A1782722742671623C2783EF30D6C1BC20F30950F16253E25BD23FA90468F4283F5FE3FC216AA252946F28FD128168059B4DEE702E7E716E78F456766B40216DECAB4DE3A164559E71616F78024A23F9095AA3E78B636AA69695F3F23F20125F232AA160D5009B42B128516F39424A7AF");
    return returnList;
  }

  public List<String> getLinLin_login30() {
    List<String> returnList = new ArrayList<String>();
    // 30�� ���� xiaobaitu0028 12345678
    // 30�� 112.124.31.200 8000
    returnList
        .add("0000007A0001020076855A1782722742671623C2783EF30D351E0F1636678F362946DEA5A5D5A23F23F20125F232AA160D50090F236ECBE7E478F7CF66902E3E453E3B93DD3EA55819319380B6EEA193C64C3631F4360F02A1F496A13129F2462BFDB38BCF900A630A736EBF90085915EEE7082F602B1285813E200818AA");
    return returnList;
  }

  public List<String> getFengZhong_login() {
    List<String> returnList = new ArrayList<String>();
    // 12�� ����֮�� sdu555 20130505
    // 12�� 112.124.21.177 8800
    returnList
        .add("000000730001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA6982F22346DEDEDEE23F23F20125F232AA160D5009B4610845E0E015806D9860D46ECBE7E478F7CF66902E3ED229D090DE58DE4645E0D0C690DE45B67A3B313B31937ACBD23196B6EE3E3136481285F9F81FD3049B");
    return returnList;
  }

  public List<String> getLingFan5Dui_login() {
    List<String> returnList = new ArrayList<String>();
    // 5��: �㷬�� mayunbj 123456
    // 5 112.124.21.177 8100
    returnList
        .add("000000740001020076855A1782722742671623C2783EF30D82AA1B30293E25955F3F23F20125F232AA160D5009B4BE6ECBE7E478F7CF66902E3ED296B6201940CBDED0F4363BCBD2F44C31930F36314C8046E0D22045A1F4409650FDB38BCF900A630A736EBF90085915EEE7082F602B1285CB6B53484584");
    return returnList;
  }

  public List<String> getLiuNian_login() {
    List<String> returnList = new ArrayList<String>();
    // 6�� ���� derkear50 781376
    // 6 112.124.21.177 8000
    returnList
        .add("000000750001020076855A1722B02AEBC320A22293EFBB8C7944B01F79D6FED2D16740164E62EFA8E3BA87DD1B1D82B6A06DA797613A02B3E84360DF244CB9D5B91255C8F7B7079FC1C66DFEE6C19BCE4698574201A4B38ADE05E4BB6F86A97F8E98CB0720C4A8B0A3B603BCAB1D6A63006C59115D5336C808");
    return returnList;
  }

  public List<String> getBingHan_login() {
    List<String> returnList = new ArrayList<String>();
    // 6�� ���� xy157607 123456291635
    // 6 112.124.21.177 8000
    returnList
        .add("000000720001020076855A1782722742671623C2783EF30D6C30438080DE59E7A5653F23F20125F232AA160D5009B41B6ECBE7E478F7CF66902E3ED28019407A02D23640404C400F29F2D0A519DD4619C65F41161660357E053905F38F325F39BB4BB15D326F28BC2502BCBC312DBBC8D3F81F5ABE7A");
    return returnList;
  }

  public List<String> getLanSeMiFan_login() {
    List<String> returnList = new ArrayList<String>();
    // 3�� ��ɫ�׷� lzy19840316 316606
    // 3 112.124.52.173 8200
    returnList
        .add("000000780001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696CAA5BF8806666A545A5D7806C3F23F20125F232AA160D5009B4500845E0E015806D9860D46ECBE7E478F7CF66902E3ED2933B36C680DEF2E03EEE45D0DD58DED0400FDD45B696585819C67A29190FCB60128592B494888C1B");
    return returnList;
  }

  public List<String> getZiYe_login() {
    List<String> returnList = new ArrayList<String>();
    // 2����Ҷ(��) 15063426 9343668211
    // 2�� 112.124.52.173 8000
    returnList
        .add("000000740001020076855A1782722742671623C2783EF30D6C8080DEA5E7D745026C3F23F20125F232AA160D5009B4A66ECBE7E478F7CF66902E3ED236363B80E0DEB680D24C4CF4963E7AA5B67A4C9380F2DEB6BB900DAF4A0AF4B8137311CC0FA10F90CDC0CC14E67CF05014F18E7A47850FF01F856399");
    return returnList;
  }

  public List<String> getShaoNian_login() {
    List<String> returnList = new ArrayList<String>();
    // 1�� ���� q2251134 2251134q
    // 1 112.124.52.173 8100
    returnList
        .add("000000750001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696C29800202DE8080D7123F23F20125F232AA160D5009B40A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED296D2A5D0F4313193F43BF493312958F2E0F23E02F219DDE0A1963E3B40CB450A12854E851FC191AB");
    return returnList;
  }

  public List<String> getJieXuan6_login() {
    List<String> returnList = new ArrayList<String>();
    // 6����� derkear40 821112l870319
    // 6 112.124.21.177 8000
    returnList
        .add("000000760001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696C23A6F295231B290245A23F23F20125F232AA160D5009B4600845E0E015806D9860D46ECBE7E478F7CF66902E3ED27A31402946D09390B6932058DED002D0314093A5D28058B6933BC6930F903EF01285C03353157BA4");
    return returnList;
  }

  public List<String> getJieXuan22_login() {
    List<String> returnList = new ArrayList<String>();
    // 22��һ��С�� L668388302 821112l870319
    // 22 112.124.55.30 8600
    returnList
        .add("000000770001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696C9B45E73AA5CA7FA5D7A55F3F23F20125F232AA160D50090F560845E0E015806D9860D46ECBE7E478F7CF66902E3ED293933B36803E933120D020B6362045E0D0CBDEB620F25858D0CBF2D280DE455012857ED353DF9847");
    return returnList;
  }

  public List<String> getEdward_login() {
    List<String> returnList = new ArrayList<String>();
    // 18 112.124.55.30 8000

    // 18�� edward ghh0011 qqqqqq
    returnList
        .add("000000720001020076855A179A8E2AEBD4A5A28593800A3FE1E1D3961F06EA4FDB2B7EA8ED7B2E737280EB82F591D0DF5D73932EBB28DA4C8E8960B6F120DFC9FB66D6C0FDECA63B52461A14F87A8956ACB402051A14AEA8C3A6AD4A2C9CA19519B5DFB280E51D93201BB3B7FA2AA2786AA9531F034A");
    // 18�� edward ghh0063 qqqqqq
    returnList
        .add("000000730001020076855A1722B0B09A9820A285C6EFBB53E96A2A3C9C7DBB843457FB26BEC94727D469DE5E36BE9E1633431C1496E1F3D1EBB6743F45D9331EAB27FAAE28D53B29B2BBF8B215E718CD6CF625E167EB31FB8E8582024EF170E3E4A28B54864782163B96739911D22E0EC8F9B15320ADF4");
    // 18�� edward yy12000 qqqqqq
    returnList
        .add("000000730001020076855A1722B02AEBC320A285C6EFBBEB576AC6AC8C11C33DFF2B00D186CBFE10E2E6E94B9482DF9C4B2EC0D3543DBADAD326AB8A9B40F89CC2B7E18593FDEC2F5AF3866EAF7D706F1E7B9416574FA5E9D3E849B0C683B8BC4AA0D74BBC043316D723C8F71FB3DF2ADD42681F8DA5FD");

    return returnList;
  }

  public List<String> getXiaoLou_login() {
    List<String> returnList = new ArrayList<String>();
    // 29�� С¥ kkooff1997 520888
    // 29 112.124.31.200 8100
    returnList
        .add("000000770001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696C3EC2C2AA6725E7666680653F23F20125F232AA160D50090FF60845E0E015806D9860D46ECBE7E478F7CF66902E3E45A1801920A129D00F0F4029D293F4023E31DDA13640364C20B6404C4C934C02B11285D17553D5E130");
    return returnList;
  }

  public List<String> getTangJia_login() {
    List<String> returnList = new ArrayList<String>();
    // 29�� �Ƽ����� 18751909541 19860712
    // 29 112.124.31.200 8100
    returnList
        .add("000000770001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696C5966A559DE66807F80DE45FE3F23F20125F232AA160D50090FF60845E0E015806D9860D46ECBE7E478F7CF66902E3E45A13620D23129199336CBE243F3A17A36313120D0EEA190197AEEA17A404C2D1285FE6B1FA0F0E3");
    return returnList;
  }

  public List<String> getWoNiu_login() {
    List<String> returnList = new ArrayList<String>();
    // 6�� ��ţ 344789069 lsk1421356
    // 6 112.124.21.177 8000
    returnList
        .add("000000760001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696CD7D74545247F80A53AFE3F23F20125F232AA160D5009B4600845E0E015806D9860D46ECBE7E478F7CF66902E3ED23693DDD09393F429D2DDE0A14C20F258B60F4CDD46D002B6F40F3620F2D29660128506571F40C5F9");
    return returnList;
  }

  public List<String> getLaoS_login() {
    List<String> returnList = new ArrayList<String>();
    // 19�� ��s sq7323849 lulu1314
    // 19 112.124.55.30 8200
    returnList
        .add("000000750001020076855A1722B01A9AE520A285C0DAFBFEA8B4B096D623CA46E4A67ABD7F9076B6C834A8B6A55036543EC42C55AD57E7696F1B9BBE741EB1DFB51956D7FDE119EF3FCF1803B75468533F98DB8FBD3587A3291E1DE8884862E3ED6F2D4425E36985C52133A7C47D407D13319A5CA5539411A1");
    return returnList;
  }

  public List<String> getJieAi_login() {
    List<String> returnList = new ArrayList<String>();
    // 4�� �䰮 zyl31008 123456
    // 4 112.124.52.173 8300
    returnList
        .add("000000750001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696C30F83645D780A57FA23F23F20125F232AA160D5009B4B10845E0E015806D9860D46ECBE7E478F7CF66902E3ED290D2A53E7AF40F932958D236F40F31312019F43B0F36A545A1C60F96B63136561285559F1F97994D");
    return returnList;
  }

  public List<String> getWoXin_login() {
    List<String> returnList = new ArrayList<String>();
    // 17�� �������� 1401313 1
    // 17 112.124.55.30 8100
    returnList
        .add("000000730001020076855A1722B02AEBC320A22293EFBB8C7944B0D46420D426E42B7BED07E1473E48A88AF90DDD50549BC3BBCE62CBD3E84378262166B9D53D4586E1E1BE2B6772DE211AC1B1457AE1033BF58A0717574A9F35A05F291BD6440ECA94827E805582C521EB49AF03636EC8CFD33D531DD2");
    return returnList;
  }

  public List<String> getYiHu_login() {
    List<String> returnList = new ArrayList<String>();
    // 19�� һ�� Frankcao Frankcao
    // 19 112.124.55.30 8200
    returnList
        .add("000000750001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696CEDF38F36DF232336653F23F20125F232AA160D50090FE16ECBE7E478F7CF66902E3ED2360FCBD27AC6369393901996B67A0FF4EE45D0369380D036C64C90B696190FF00845E0E015806D9860B112857756531FD08A");
    return returnList;
  }

  public List<String> getAJ_login() {
    List<String> returnList = new ArrayList<String>();
    // һ�� ����ʹ jzdiws1314 ws121314 pp������
    // 1 112.124.52.173 8100
    returnList
        .add("0000007E0001020076855A1722B02AEBF420A285C6E37E61B54E0065D3CEC7F9D56EE4E95F3A2283CB471C11820BA0167C9E36A501BCAF9BBE741ED47C18BF4B297651D6206756562725A735173CF1BC542D36F614D4E9FCF0996D48668F57667A4772302884AEF296EDBCF62C785FE83325628A45FF74352A469B9F5DA994864EBB");
    return returnList;
  }

  public List<String> getDestiny_login() {
    List<String> returnList = new ArrayList<String>();
    // 9�� Destiny 18725722223 love9527
    // 9 112.124.21.177 8500
    returnList
        .add("000000760001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696C5966A55902DE597676698D724C8BD54C8B6436FEF6C96180B163E7B7BC5F258F6311193D0535362905169A365656053E3E1B1B29111B11F2F25F6C9AF03E3660291B36B7646C3535BC25BC8F31E1BBC87E741F3A904A");
    return returnList;
  }

  public List<String> getChiEr_login() {
    List<String> returnList = new ArrayList<String>();
    // һ�� �ն� 148911 651115
    // 1 112.124.52.173 8100
    returnList
        .add("000000730001020076855A1782722742671623C2783EF30D82DE80F77F8080FE3F23F20125F232AA160D5009B4736ECBE7E478F7CF66902E3ED2C6A5D00F3631F4A53E96A1C63B0F3B7A2045D0DDD020DEE0DE19C64C3131F456FDB38BCF900A630A736EBF90085915EEE7082F602B1285F63E3D88434A");
    return returnList;
  }

  public List<String> getNiBaTuRan_login() {
    List<String> returnList = new ArrayList<String>();
    // һ�� ���ͻȻ mflsuper 748159263
    // 1 112.124.52.173 8100
    returnList
        .add("000000750001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696C361BDF463FF2B4F25F3F23F20125F232AA160D5009B4736ECBE7E478F7CF66902E3E45453E204658D29645F2E0453E02E0A10F93801940C640DD3E93A545463E367AF00845E0E015806D9860B11285ED9820EB24E5");
    return returnList;
  }

  public List<String> getYiXing_login() {
    List<String> returnList = new ArrayList<String>();
    // 10��������ɰ yglsren 16396bin pp������
    // 10 112.124.21.177 8600
    returnList
        .add("0000007C0001020076855A17E2A145EB76DAA285C6EFCD90810C5D927BE230E09840262D33D16990413AF14B7C8269A050891050653A0F7490C28E6E8AB5AC94B359744115C41D18144E0898A269ADE6CE25D334DFD3A384EB0F3B70D70442CA0E68CBADE27458E39790CCEE9247E428E896461257A2030BD708AEC12072892F");
    return returnList;
  }

  public List<String> getShiGuang_login() {
    List<String> returnList = new ArrayList<String>();
    // 8�� ʱ�� qq24281191 liuna520
    // 8 112.124.21.177 8300
    returnList
        .add("000000760001020076855A1722B045EBC3A57E8537D4E84BE3CEA2EC611ED6989C0A2620DF89F87E1B322C49626ABB929D1ACAE7B8CDBE5BDE8E6B66A0251B58AB88AB1C8637851DAF65E4D462FE1052C91549393D39178EA7F9355B5301AD00B4CE22ACAACBD485EB00E4EC47E79316129355C8F3DE53BF8824");
    return returnList;
  }

  // =======================������������������������������������������=����������==== IOS

  public List<String> getMing_login() {
    List<String> returnList = new ArrayList<String>();
    // 2�� ڤ aa7878748 q623123048 pp������
    // 2 121.199.12.211 8000
    returnList
        .add("0000007C0001020076855A1722B02AEBF420A285C6E35CEBA7B0AE6D6ACA3765C5CE253676638D2080CC5C14C51AA0B1E7BC964CC26B3E6B7340D36BB84C9DAB7D6040F891BB37AE17C4E7EBE3D049177CAAFE69FD06EF3517DF3A40CBDBDFFD69C2A03C5704F20D9353287E4362414B305D868DAACE805DDADC440F20BF9709");
    return returnList;
  }

  public List<String> getNongFu_login() {
    List<String> returnList = new ArrayList<String>();
    // 33 112.124.5.172 8300

    // 33�� ũ�� supper88 5201314 pp������
    returnList
        .add("0000007E0001020076855A1722B01A9AF4A5A285C0FB32CDEE22B1D9BFCFFD14AADA3F5041A328E83F7FD2A12A56A1CD16B074BC8B36C26F27396202FFEA18BCF0DC04D8B5A8784753B0DB6E96E8BFD0AE11CBA19C52D21C84369294F20371E1B23BBA0FD7D16A468344F6BB5E1650CD3F1EE3198CDC7E2B3A11061D92F6946AB933");

    // 33�� ũ�� aaaa1049 5201314
    returnList
        .add("000000730001020076855A17827227FE3F23F20125F232AA160D196A8F736EBF90085915EEE7082F1BA512477E9A16293905F38F325F39BB4BA36C3535BC25BC8F31E19863E7B7BC5F258F6311193DB75611F29A365616054011359A6C3EF23E29603E56359A16563E367E6C60059ABBC8BA051FB26773");

    return returnList;
  }

  public List<String> getYolanda_login() {
    List<String> returnList = new ArrayList<String>();
    // 22�� Yolanda 11545256789 910819
    // 22 121.199.53.202 8100
    returnList
        .add("000000780001020076855A17827227FE3F23F20125F232AA160D196A8F166EBF90085915EEE7082F1B3B93934C7A4C0F4C363BF4F0FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45D20F7A9329B6EE46A120D20FC60219C6EE45197A29E03E364C96193196B60F491285E379538CA44E");
    return returnList;
  }

  public List<String> getWuNai_login() {
    List<String> returnList = new ArrayList<String>();
    // 15�� ���� holmeslupin 123456
    // 15 121.199.15.89 8300
    returnList
        .add("000000780001020076855A17827227FE3F23F20125F232AA160D196A327C6EBF90085915EEE7082F1BDD3C08D959B3CB8A6620E72FFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45193190F2A13BA53E3B404C360FA5B6314C363180D24C3102453E930F7A4C40CC1285852194319F5E");
    return returnList;
  }

  public List<String> getCYX_login() {
    List<String> returnList = new ArrayList<String>();
    // 42�� cyx cyx1202 6728319
    // 42 112.124.37.109 8100
    returnList
        .add("000000740001020076855A17827227FE3F23F20125F232AA160D196A8F256EBF90085915EEE7082F600839D5F4930FC649FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED27A80D240203E317A29D293CBA1409658F2A13636CB19C6F40F0FC6F496A1DD2B1285467F1F66F0D5");
    return returnList;
  }

  public List<String> getLiuYue_login() {
    List<String> returnList = new ArrayList<String>();
    // 19�� ����ʮ�� jy205tat jy5537932yd876 pp�ͻ���
    // 19 121.199.50.216 8000
    returnList
        .add("0000007C0001020076855A17E27A45EBF4A57E8537D4F80266AE7192B027FDBB0F0F25EFB75CCC0041394672FB91635E20AF6E89570DBE745B4FDF02A099DADDFAE41378FAE9E4C86CE3983C1FEFCBDC82B83628AB077071E1DF218FB2166B2290A8A428F0E72CACC6978A9CE718E8E2A1D4D9FF0935D2E070AEC02094337543");
    return returnList;
  }

  public List<String> getFengYuan_login() {
    List<String> returnList = new ArrayList<String>();
    // 15�� ��Դ fengyuaobo1 123456
    // 15 121.199.15.89 8300
    returnList
        .add("000000770001020076855A17827227FE3F23F20125F232AA160D196A327C6EBF90085915EEE7082F1BDDF2DE7821D580450846B2EFFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2A5190F3129A10F310F9396A193964558D2407A93F4A5A136EE45E20F594527418506FF944C2AF2");
    return returnList;
  }

  public List<String> getDaZ_login() {
    List<String> returnList = new ArrayList<String>();
    // 9�� ��z z7015757 123456
    // 9 121.199.36.80 8000
    returnList
        .add("000000740001020076855A1722B02A0CC3A5A28537765F4353976F0A4938D62F999783AD51574A960BFEECBE997D6DCE1633903A3A9C2925CF06A3213677A3E62536A609243A6BCEBA626ABE7A628FB34D98A0CF8BF33AF1676FC5A29D284EB696EB4841316C856F32AD570F7A80910DBEEA53E71F363FC9");
    return returnList;
  }

  public List<String> getXueSheng_login() {
    List<String> returnList = new ArrayList<String>();
    // 15�� ѧ�� liwenrong 135246
    // 15 121.199.15.89 8300
    returnList
        .add("000000750001020076855A1722B02AEBC320A285C6EFBBEB0E6AC6ACF84860AC7C40296EBBF0AD24FB7F9BFB99DF5ECBB7AD95FECC9495F36D3EC99B9EF3558D105066C9BD08F0A5C71F53BEBFBEF861BBC98AF8A4056FBAC0092A217A42B8B38647B79C30FFE9D21BF9C83FBF090D984806D6D5E394ABB7F4");
    return returnList;
  }

  public List<String> getMichael_login() {
    List<String> returnList = new ArrayList<String>();
    // 15�� michael MichaelNO1 xcm123456 PP�汾
    // 15 121.199.15.89 8300
    returnList
        .add("0000007E0001020076855A179A952AEBF420A285C6EF803D17711D91F4A41A5D7553FF2BC8795CEBC41911823AA0266D93E0AACB463A3A91E11ED1A44DB219DD75E41378FA066C132F8C0FD6206A03499D852836391FB338B004C5926908989BB1DD9CB89BC9A986B0EABD487E4981CAE38FEF242BB24F7243CCE9C8711B947310B6");
    return returnList;
  }

  public List<String> getMaLong_login() {
    List<String> returnList = new ArrayList<String>();
    // 15�� ���� melon619 474747 PP�汾
    // 15 121.199.15.89 8300
    returnList
        .add("0000007D0001020076855A179ADF45EBF420FE8593EF807EA79300056EB0C1C6F1F7D94541081C2AA5CC5C2FC539DDD4FF52E6657BAD8168812457149915BE210E74CA73132E6593EB662AA7DE85765C9F8D798ECC1773619C4C9253DE03D83A3B7EA418FFEB2D4F48EAE3D52CDE5FD167F6CD9264E70E9CB1A2D9971A94EB063D");
    return returnList;
  }

  public List<String> getZWM_login() {
    List<String> returnList = new ArrayList<String>();
    // 54�� zwm zwmboss17 123456
    // 54 112.124.55.42 8100
    returnList
        .add("000000750001020076855A17827227FE3F23F20125F232AA160D196A95166EBF90085915EEE7082F1B6EF8DD5946156E31939CFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED236F44002A1CBB64031A5B6293E3629A140360F29E0A14C40314040A2B4BBE776857BC553211F41");
    return returnList;
  }

  public List<String> getMuki_login() {
    List<String> returnList = new ArrayList<String>();
    // 75�� muki flower999muki 19900402mm
    // 75 115.29.168.108 8000
    returnList
        .add("0000007A0001020076855A17827227FE3F23F20125F232AA160D196A95516EBF90085915EEE7082F1BDBF2D915DDB30F4040022480FD35FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E4546E0B6F4EE197A80B693CBD229D2203E90F2E045D2360F310F3B293E360FA5601285053EDA92DB31");
    return returnList;
  }

  public List<String> getJingYe_login() {
    List<String> returnList = new ArrayList<String>();
    // 1�� ��Ұ ccc1211211221 123456
    // 1 121.199.25.76 8000
    returnList
        .add("000000760001020076855A17827227FE3F23F20125F232AA160D196A32736EBF90085915EEE7082F1BDBE0E019930FA256567E3905F38F325F39BB4BA36C3535BC25BC8F31E19863E7B7BC5F258F6311193D3E60B716605660299A7E11165F60290505F02929165FF21B40367E5F1B7EB756BBC8CFAC5356ABBE");
    return returnList;
  }

  public List<String> getJieXian_login() {
    List<String> returnList = new ArrayList<String>();
    // 42�� ���� 541134756 15967639262
    // 42 112.124.37.109 8100
    returnList
        .add("000000750001020076855A1722B02AEBC320A285C6EFBBEB0E6AC6780EF0F4965E7C83CA7FBBEBC4FBF7F93AA0266DCE160847A95FEA2B623D1A52C95BC33892511ED4017F13A4FDC3A4CE62BB74DABA7E7B9275B623B8C9F3313F7739CFA5FBC7C6D8679894C7839C275AD85D2B489CEC721DE0B15346DEDC");
    return returnList;
  }

  public List<String> getShu_login() {
    List<String> returnList = new ArrayList<String>();
    // 42�� �� tr130814130604 123456 pp�ͻ���
    // 42 112.124.37.109 8100
    returnList
        .add("0000007D0001020076855A179A7AE4EBF420FE854EEF807EA74EC646ED7215AAD235BB53DD7EC393C007DDF6BD6FFEE99AB291A5F705EA73152680FF9965F79BB587D86695AA13DBF22E0ABF04C1D0CB689B467A3103DB64EEB56F430D3DB864F2C8906F4A02C6E2738EA2936D46DAE086E7D13A9374D3A5E78E88A04F206BC606");
    return returnList;
  }

  public List<String> getShouZhuo_login() {
    List<String> returnList = new ArrayList<String>();
    // 4�� ���� tr130529145459 qwerty pp�汾
    // 4 121.199.44.72 8000
    returnList
        .add("0000007D0001020076855A179A8EFDEB76DAA28593EFA23233F4C405319AE5AC46E42B0089A5FEC40DFB82F7DD268A935E21D1E20E82EC7289FA59A3A38F6B3D6BD218A71B1F12DDC4F7A2A52D4E606B78535F73B2ABB911689BFC403B9F7D6324372DE020E3665B7AE7E0024E32DF1223588A2EB228CFD447ECAE11349419747E");
    return returnList;
  }

  public List<String> getAJun_login() {
    List<String> returnList = new ArrayList<String>();
    // 50�� ���� pp58923870 ����hyh6520eee Pp�汾
    // 50 112.124.44.11 8100
    returnList
        .add("0000007E0001020076855A179A951A9AE520A285C0DAFBFEAD0AC446401F09C8E6023597184D12D4C6F4CB95E150F3057A3E7DFCCB2CB3F1B503A70AE56A6B7B1713FFD0840B0A5E41C3BF2C3D2D059ABFFA217F709B904FEB07AAAAF46B4A73FC9AB2F6CEAA615DF9500FFAB6FBEB46309CEF27FDA16768DD0CDADC834820EB6E0A");
    return returnList;
  }

  public List<String> getWanXiang_login() {
    List<String> returnList = new ArrayList<String>();
    // 42�� ���� iopnm013 466844711zsx ���ð汾
    // 42 112.124.37.109 8100
    returnList
        .add("000000750001020076855A17827227FE3F23F20125F232AA160D196A8F256EBF90085915EEE7082F1BA5E715207862C6932DFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45A13136CB1940803E4040963E29D2A53E3BC6363B31F480E03EEE4546B631C6CC128569C65303972F");
    return returnList;
  }

  public List<String> getXueCha_login() {
    List<String> returnList = new ArrayList<String>();
    // 42�� Ѫ�� bloodx 123456 �ֻ���ð��ʺ�
    // 42 112.124.37.109 8100
    returnList
        .add("000000720001020076855A17827227FE3F23F20125F232AA160D196A8F256EBF90085915EEE7082F605946D9080875C4FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2203E0F29D2C620A19646F23E20192019F402A13D90E3F6830D07F5F5E386B04785713753BA94DA");
    return returnList;
  }

  public List<String> getQueDian_login() {
    List<String> returnList = new ArrayList<String>();
    // 20�� ȱ�� 266201234 88888 ���ð汾
    // 20 121.199.15.89 8100
    returnList
        .add("000000750001020076855A1722B02AEBC320A285C6EFBBEB0ECE004D418C09D8F1997A203F9A61E83A83B718A33F7C8D1608903A3A55E1F691A9BD9B807C5B141757F2178512E19F883D64B2EED38FE4E8569B901B0927AA545F17DA307CD8D45967B63CE310EFEFC6FA9D6D9CCDD37F1243A4E21C3DCEEE4F");
    return returnList;
  }

  public List<String> getFengYa_login() {
    List<String> returnList = new ArrayList<String>();
    // 42�� ���� ljnkfc 12345678 �ֻ�汾
    // 42 112.124.37.109 8100
    returnList
        .add("000000730001020076855A17827227FE3F23F20125F232AA160D196A8F256EBF90085915EEE7082F6059D9F378FDF250FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45D080D293403196B636C6400F7A0F7ADD58A17A20E03E8045F2193B7ADD3E409C128559D953B4557A");
    return returnList;
  }

  public List<String> getShenJi_login() {
    List<String> returnList = new ArrayList<String>();
    // 1�� ��� shengxijian5 58886308 ���ð汾
    // 1 121.199.25.76 8000
    returnList
        .add("000000770001020076855A1722B02A0CF4A5A285378C8FEBA7B07123DC83B04FF03DFF2B7A275BF32D0F772E4DDF09A30C189E22A0655249336616B2946424B35EA417F8FD70614CA7938A9E95633DE56F7E44953817B5DB3B421AAFDE2F337A4F2C2B54DAD48FF747832C56560A5049782A8FC40822F6942EF5C0");
    return returnList;
  }

  public List<String> getZuoXu_login() {
    List<String> returnList = new ArrayList<String>();
    // 30 112.124.5.172 8100

    // 30�� ���� zuoxu13264 zuoxu13264
    returnList
        .add("000000770001020076855A17827227FE3F23F20125F232AA160D196A8F236EBF90085915EEE7082F1B66F880158B4C93310F3698FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E4545D202D280D22919963E20D0EE4645583E7A3BDD3E4C023E203E0F9331DDD0981285661E94C2E7A3");
    // 30�� ���� dkw8888 zuoxu13264
    returnList
        .add("000000720001020076855A17827227FE3F23F20125F232AA160D196A8F236EBF90085915EEE7082F6008581A3B76476E05F38F325F39BB4BA36C3535BC25BC8F31E19863E7B7BC5F258F6311193D3E36F216F01B7E11B7115F6C6C35111B3E60365F9A601B35F2F00536F0F2297EBBC859D853AF51A8");

    return returnList;
  }

  public List<String> getGuaiWeiDou_login() {
    List<String> returnList = new ArrayList<String>();
    // 30 112.124.5.172 8100
    // 30�� ��ζ�� Jackysophia 19820424
    returnList
        .add("000000780001020076855A17827227FE3F23F20125F232AA160D196A8F236EBF90085915EEE7082F1B3BF345E01AD5CB15203CE70AFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E453E31901993F402193136C63B4C0F4C7A3BF49393EED002199393930F96451949128504A4944430B7");

    return returnList;
  }

  public List<String> getYouShen_login() {
    List<String> returnList = new ArrayList<String>();
    // 6 121.199.44.72 8400
    // 6�� ���� �ʺ�gong454613042 ����1234321
    returnList
        .add("000000790001020076855A1722B01A9AF4A5A285C0FB32CDEE22B125F66ABD3D35F0A693589CF2656493A12A4B63392DC4708EA650B842716F43AA8D52B79EF255729239F90B8D3420F404C42208D21922AC753F6EC7A0661E9F3A62407F94E8E999D62FE0690F45179BF63B02719626DD8B9A4EB6F808C2AF94E26CC0");

    return returnList;
  }

  public List<String> getGuDan_login() {
    List<String> returnList = new ArrayList<String>();
    // 42 112.124.37.109 8100
    // 42�� �µ� �˺�zxbarbara ����232450605 pp�汾
    returnList
        .add("0000007D0001020076855A1722B02AEBF420A285C6EF803D17C39F920D6704760CAC7C83F6AD453D981C11B718A33F7C8D36CB2CB3285244749EA964DC27B3B3E91EE462966D431F07A517C3220B1FE11D81E8DF112F40C5BFB555014C2F8E6F0A966EFD72C7A9B6AC03C8852BAE17FEC3DAE8E7EC19A460E849080BA820E760B5");

    return returnList;
  }

  public List<String> getNanYin_ios() {
    List<String> returnList = new ArrayList<String>();
    // 9 121.199.36.80 8600
    // 9�� ���� 276462943 ����141516 pp�ͻ���
    returnList
        .add("0000007E0001020076855A179A7A2AEBF420A285C6EF80EEC71D535AB159ADF3B903997A307FDE3D98272560AA061AA0B1C20D1C735060C53E57AA5BFD9921FB759907DFB3CEE713618C7738C6C114121ECE23FE693325B7B8196F3AC0ABB19BC7FBEFFF4ECBECE5D425044B76D9EE6E64E022C40A081B4AC08F6B8528F420754FE2");
    return returnList;
  }

  public List<String> getYaBa_ios() {
    List<String> returnList = new ArrayList<String>();
    // 30 112.124.5.172 8100
    // 30�� �ư� tr130725190943 051322 pp�汾
    returnList
        .add("0000007D0001020076855A1722B045EBF420FE8593EF807EA74EC681FCA7BCC846D8D94547151C2A4A885BECE1E4268A6A21895F07D9DCA2088F53C2B2BEC73BFCFA2634C5564EA266C82F239F47EB7E1025165B1FB3DF2AA7C5BE69082D426B278C7EDA186862BE6C174A6C3FF9F9B0D1D597AD8B68BDCB0CDADC69EE20E013F5");
    return returnList;
  }

  public List<String> getChuHe_ios() {
    List<String> returnList = new ArrayList<String>();
    // 2 121.199.12.211 8000
    // 2�� ��� 381644970 1997225pp
    returnList
        .add("000000740001020076855A17827227FE3F23F20125F232AA160D196A32A66EBF90085915EEE7082F1B3131F493367A7A403B48FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2204545463EC647974BA450104B8AE12D2D137710D975CF10E6BB8A50CFA57685F2781F6435ED");
    return returnList;
  }

  public List<String> getLuLu_ios() {
    List<String> returnList = new ArrayList<String>();
    // 30 112.124.5.172 8100
    // 30�� ³³ fbigkgm 513029
    returnList
        .add("000000740001020076855A17827227FE3F23F20125F232AA160D196A8F236EBF90085915EEE7082F6008F246E7E4FDE426FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45DEB602B63B7AEEF2194CF493F40F93903EA5D2F43193DDA129E0A1DD58D0800A128592A853D3AE98");
    return returnList;
  }

  public List<String> getReal_ios() {
    List<String> returnList = new ArrayList<String>();
    // 27 112.124.2.232 8000
    // 27�� REAL yz000030 5201314yaner
    returnList
        .add("000000720001020076855A17827227FE3F23F20125F232AA160D196A8F136EBF90085915EEE7082F1B7FD5958547569A6E05F38F325F39BB4BA36C3535BC25BC8F31E19863E7B7BC5F258F6311193D601B3EF2B75F35F2160560481682CF7A66CCF5B797E5F525D8B9253C5DD880BA85CB3653CBCD38");
    return returnList;
  }

  public List<String> getTianLiang_ios() {
    List<String> returnList = new ArrayList<String>();
    // 31 112.124.5.172 8000
    // 31�� ���� tianliang173 nha1234560 ����
    returnList
        .add("000000770001020076855A17827227FE3F23F20125F232AA160D196A8F926EBF90085915EEE7082F1BD5EEE745789F49B79A3E603905F38F325F39BB4BA36C3535BC25BC8F31E19863E7B7BC5F258F6311193D1BF0F06C3E3EF205405605166CF2F0295F6C607E7E606035299A7E3E05B729354BC89A25208BD1D4");
    return returnList;
  }

  public List<String> getManYue_ios() {
    List<String> returnList = new ArrayList<String>();
    // 70 112.124.44.100 8100
    // 70�� ���� ���� xxlfhby39 986364
    returnList.add("");
    return returnList;
  }

  public List<String> getWeiLi53_ios() {
    List<String> returnList = new ArrayList<String>();
    // 53�� 112.124.44.11 8300

    // 53�� κ�� pp�˺� wlei,����123456
    returnList
        .add("0000007E0001020076855A179ADF1A9AF4A5A285C0FB32CDEE22B18ABFFDEAC22B25772A266498CDE3D863C3BB63392DC4806D7482CF36C26F273926DDA0EA4A924084FA26349BD0C8DB7F532744D796E9B7447BC70BACA70EA574C9B906617F99DA5866AF1C41F683C69C6F199DB1825C488B9B6CEA799343CCE9AE1122942ADBD1");
    return returnList;
  }

  public List<String> getWeiLi32_ios() {
    List<String> returnList = new ArrayList<String>();
    // 32�� 112.124.5.172 8200

    // 32�� κ�� pp�˺� wlei,����123456
    returnList
        .add("0000007F0001020076855A17827227FE3F23F20125F232AA160D196A8F2A6EBF90085915EEE7082F1BB70FC64C0FC69331C63BC60F93364C4CBBB4106AD977D9BD269E2E7FF7476518A8BD58F6CC8C75D5E0107713105C5C50A4E15CBB7710502DA8E1A885B358FBCF105850BBEE104B5C288A34CCFE585745020276858FA294290CD6");
    return returnList;
  }

  public List<String> getZZ_ios() {
    List<String> returnList = new ArrayList<String>();
    // new2�� 121.199.25.76 8200
    // 2�� ����eastdz 33591799 Zz
    returnList
        .add("000000730001020076855A17827227FE3F23F20125F232AA160D196A32A66EBF90085915EEE7082F6059DEF76EEE7532FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2CBE03E96454658D20FF4367ACBA120B640903E0F80D03140EE58B6EE58D03BC41285E481531A4648");
    return returnList;
  }

  public List<String> getYuXin_ios() {
    List<String> returnList = new ArrayList<String>();
    // 45�� 112.124.37.109 8300

    // 45�� ���� T24412830. q25257758
    returnList
        .add("000000740001020076855A17827227FE3F23F20125F232AA160D196A8F5E6EBF90085915EEE7082F1BCB7A0F7A7A930FF43148FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45D0369619A5E249351B6C6CB729113EF036351111606036163611F06C3E9ABBC88F6F53433CED");
    return returnList;
  }

  public List<String> getXianLin_ios() {
    List<String> returnList = new ArrayList<String>();
    // 70�� 112.124.44.100 8100

    // ios70 ���� �ʺ�xian0231 qwerty
    returnList
        .add("000000750001020076855A17827227FE3F23F20125F232AA160D196A951B6EBF90085915EEE7082F1B7F96E745AAC60F31EFFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED20F3BCB3E93F436EEB67A9019C6C6DD3E2045D2C64C90DE3E7A4096DE46B6292E1285B94E53487254");
    return returnList;
  }

  public List<String> getFKP_ios() {
    List<String> returnList = new ArrayList<String>();
    // 64�� 112.124.30.233 8200

    // ios64�� fkp 13605556920 fkp123
    returnList
        .add("000000780001020076855A17827227FE3F23F20125F232AA160D196A952A6EBF90085915EEE7082F1B3B933136C64C4C4C36400F48FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45D0F4360F938019963EC63BCBD236A519C63636933B363B7A295819403B40CB601285E3AA1F1495A0");
    return returnList;
  }

  public List<String> getAKP_ios() {
    List<String> returnList = new ArrayList<String>();
    // 35�� 112.124.6.243 8000

    // ��35���� AKP l2893939 ����1231123
    returnList
        .add("000000730001020076855A17827227FE3F23F20125F232AA160D196A8FFB6EBF90085915EEE7082F1BA5F10FF440BB473905F38F325F39BB4BA36C3535BC25BC8F31E19863E7B7BC5F258F6311193D359A1B166C3E052911295635B7F01B35359AB72956B75F6060F060563E6C6C1BBBC8DEEA5392FC34");
    return returnList;
  }

  public List<String> getKouSiSi_ios() {
    List<String> returnList = new ArrayList<String>();
    // 45�� 112.124.37.109 8300

    // ����45�� ��˼˼ wsh014 ����561825
    returnList
        .add("000000730001020076855A17827227FE3F23F20125F232AA160D196A8F5E6EBF90085915EEE7082F602491CBE5C69398FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45D27A3BEE1929DE46D0EEDED296463E3690A193EE4619C6F4360245A1A5D2DD2B1285770153F65B27");
    return returnList;
  }

  public List<String> getFengJian_ios() {
    List<String> returnList = new ArrayList<String>();
    // 27�� 112.124.2.232 8000

    // ios27 ��� saveluodan1 1234567
    returnList
        .add("000000780001020076855A17827227FE3F23F20125F232AA160D196A8F136EBF90085915EEE7082F1B91CBF729DE8A80085845AAEFFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2DDF2A17A7A314C36CBD23640A5B620464646DE3E409329D0DDD240C60F96D049128564B22005CF7D");
    return returnList;
  }

  public List<String> getTaoBao062_ios() {
    List<String> returnList = new ArrayList<String>();
    // 35�� 112.124.6.243 8000

    // ƻ����� 35�� taobao062 taobao062 88888888
    returnList
        .add("000000760001020076855A17827227FE3F23F20125F232AA160D196A8FFB6EBF90085915EEE7082F1B6EEE45084645B2C63649FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2930F3140F4F431364C4C93C6CBF246DEDEDEE019C6EED20F933190D09045A15612850E2753159018");
    return returnList;
  }

  public List<String> getSam_login() {
    List<String> returnList = new ArrayList<String>();
    // 6 121.199.44.71 8000
    // ���� 6�� sam samuel77 111111
    returnList
        .add("0000006F0001020076855A179AE52A0C4820A28537986AE361470883EEC71F74A629B3B1C05C7DBAAA064F218BBBBA2EE8AC72635A5725EC9111143D03EFCD6AD5035AF517C88F796CD85F8BF4C9EB19D9FBCECED72F46E7B6F7C70F5586A5B5619376291FC458A57FB560E294CDF8537655B5");

    return returnList;
  }

  public List<String> getLanDiao_login() {
    List<String> returnList = new ArrayList<String>();
    // ���� 1�� ���� 13822144118 abcd1234
    // 1 121.199.25.76 8000
    returnList
        .add("000000780001020076855A17827227FE3F23F20125F232AA160D196A32736EBF90085915EEE7082F1B3B9331F40F0F937A7A9393C4FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2C6310F3B404CDDD0360219F44040F429A17A31F480199320464619319336202E128571D01F343F04");
    return returnList;
  }

  public List<String> getXie_31_7_ios() {
    List<String> returnList = new ArrayList<String>();
    // 31 112.124.2.232 8700

    // ���� 31�� Ы xc03845532 xc670823xc ��û��Ǯ��
//    returnList
//        .add("000000770001020076855A17827227FE3F23F20125F232AA160D196A8F926EBF90085915EEE7082F1B669619C631F47A4C4C3149FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45D2EEDEA1CBD2C67ADDD00FF431A53ECBD03B29D07A4CCB4646A13602B631312D128587EB53F5C7F5");
    // ���� 31�� ���� yz000095 926494 ��û��Ǯ��
//    returnList
//        .add("000000720001020076855A17827227FE3F23F20125F232AA160D196A8F926EBF90085915EEE7082F1B7FD5958547291B3905F38F325F39BB4BA36C3535BC25BC8F31E19863E7B7BC5F258F6311193D355F3E9A5F3E111660F2F26C3E16489C4CEE71E5FE3C35EE3CE566FEBC805DBA85DD2153E72BE0");
    // ���� 31�� ���� ff97979 2451312 ��û��Ǯ��
//    returnList
//        .add("000000720001020076855A17827227FE3F23F20125F232AA160D196A8F926EBF90085915EEE7082F6008F23E403D473905F38F325F39BB4BA36C3535BC25BC8F31E19863E7B7BC5F258F6311193D6C356C609A5605119A1B1129167E163E3E7E16F256F2299A6C359A3E055F6C054BC8A45B1FC619FE");
    // ͬ���� 31�� ��ҹ qq244957989 gbsbeck
    returnList
        .add("000000780001020076855A17827227FE3F23F20125F232AA160D196A8F926EBF90085915EEE7082F1B917F930F7A7A404C3B40F4F0FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E4545D2360F4C0F36803EF436CBB62046D03B31DDD036DDD24CEE5846F2D231F42D12852B965399B2E3");
    // ���� 31�� èè felonmax 332335
//    returnList
//        .add("000000750001020076855A17827227FE3F23F20125F232AA160D196A8F926EBF90085915EEE7082F1BA5F2DED9087859F7C4FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45D0DDB6407AF40246453EDDD220F2D20FC64090B6F4CBD20F3180D23B36CBB65612850A9C94D4BB74");

    return returnList;
  }

  public List<String> getLiuYi_login() {
    List<String> returnList = new ArrayList<String>();
    // ios���� 13�� ���� zzh3318 3332485
    // ios13 121.199.5.229 8400
    returnList
        .add("000000740001020076855A17827227FE3F23F20125F232AA160D196A325E6EBF90085915EEE7082F6015F801E5313193C4FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED29019A5192058A17A0FF4DD46D220193B29463EC693C602F2F21920DED290582E128545CE537D77D2");
    return returnList;
  }

  public List<String> getXuZuoNan_login() {
    List<String> returnList = new ArrayList<String>();
    // PP�ͻ���32�� ������ JM0303 555655
    // 32 112.124.5.172 8200
    returnList
        .add("0000007F0001020076855A179A7A45EBF420FE8593EF807EA74EC622CD3364148CF504A67E6694E52080154C5A29AAD005CEA4920847B36C152B94C9A799BDBEC7CD7CB3C513429A9B985C56AD49475C8EEAB7582925E48F35B817BC3A98ADA5B64BB65A8FD2713246FA17CB7459CBC4C34AB0F009AEFF5C619247ECAE695894952C70");
    return returnList;
  }

  public List<String> getCorner_login() {
    List<String> returnList = new ArrayList<String>();
    // 32�� ���� corner lwjacyy33 123456
    // 32 112.124.2.232 8800
    returnList
        .add("000000760001020076855A17827227FE3F9095AA3E78B636AA696CC246AA8F161E3080D79E23FA90468F4283F5FE3FC216AA252946F28FD128667F1680DEDED57FA5801645CAB4A602E7B43AB4021BA62580E7D745E7DED7024E671623C2783EF30D695F3F23F20125F232AA160D196A8F001285654A53DFE985");
    return returnList;
  }

  public List<String> getXinChengJing_login() {
    List<String> returnList = new ArrayList<String>();
    // ios1 �³Ǿ� 77612892 123456
    // 1 121.199.25.76 8000
    returnList
        .add("000000740001020076855A1722B0B09AF4A57E85374B7BDD179FE8FB982D379C18D95B5645901DF0951B12694565054459564E650B6D9D257D812D40DE20D13D9BB7609F8515F618C59950794684CBA1372FCF978B56FEE52258254D35FCEF6AC696C77D33A755F8B534E6DFFFC16BC855C8618F1F3C81C3");
    return returnList;
  }

  public List<String> getALiuLiu_login() {
    List<String> returnList = new ArrayList<String>();
    // ios47 ������ a6639899 12345
    // 47 112.124.26.242 8000
    returnList
        .add("000000750001020076855A17827227FE3F9095AA3E78B636AA696C1680E7E7CA667F664223FA90468F4283F5FE3FC216AA252946F28FD1288067F7A57F1602A5E74523B3A51BCA16DEDED7258045161625258FF7A545E7A6C5671623C2783EF30D695F3F23F20125F232AA160D196A8F14128575B41F0628E7");
    return returnList;
  }

  public List<String> getBoBan_login() {
    List<String> returnList = new ArrayList<String>();
    // ios���� ���� huihuihuiooo240 8008208820dhc
    // 5 121.199.44.71 8100
    returnList
        .add("000000770001020076855A17827227FE3F9095AA3E78B636AA6935AA903E98BF5555A03C6CAE814C32F08B973FBABA8D7236D3917AF0408BB9D7F1F53C35B9E73A80E7696C35CF66F56380DF66FE6CC3EE637A4C97E7DF80BC9771DC7A42425540915FD7778D724C8BD54C8B6436FEF69A6DE14BC8807E96A50D86");
    return returnList;
  }

  public List<String> swantsxssj_ios() {
    List<String> returnList = new ArrayList<String>();
    // ����һ�� �ֿ� swantsxssj qwerasdf
    // 1 218.244.141.167 8100
    returnList
        .add("000000760001020076855A1722B02AEBC4A5A285C687E80E48C3618EEA372CFA53C92B199DA14C7A9D043F0F3A5A656D44A8A7C60CECBA550FAA9ECBB0883988F89E1336BE88E1B9AD0A09AC1A02CA8565165B1ED41B6958150C0695F59EFE742FED096C8B8AECD75A24D3DA7E0D6F3F8E492CDCC59C20CD7A8B");
    return returnList;
  }

  public List<String> jingYin_ios() {
    List<String> returnList = new ArrayList<String>();
    // ����81 ���� PP���ְ� jiangjie425 10712conan
    // 81 115.29.172.238 8300
    returnList
        .add("0000007F0001020076855A17827227FE3FC216AA252946F28FD128168F80808FF766B423E759021B0259672523A6E780B416A6E723027FA50216258D671623C2783EF30DB78002A5DE02A580D780A50245A57640BA778E8E3B5BE258D42BCD56373C056395116372E705E1D7910CFE3F9095AA3E78B636AA6969694185960020622855");
    return returnList;
  }

  public List<String> dongBian_ios() {
    List<String> returnList = new ArrayList<String>();
    // iOS 48�� ���� east26 13579
    // 48 112.124.26.242 8200
    returnList
        .add("000000720001020076855A1722B0730CF4A57E85C00CD894A4E52F92CFE6DF7F14849740EE5688318F102BF16FCD84F4708EB6B13FE98CFFFE539915BB9217843E8F7D888C04CA8135A01689ED81C497A7309E6193DF73D223E2EA8A2D8C8C954BCBC8E8771758926CF5A89BCAC81A08DE9553B6AC32");
    return returnList;
  }

  public List<String> huanDan_ios() {
    List<String> returnList = new ArrayList<String>();
    // tr130608234550 ����123456 17��pp�汾 ����
    // 17 121.199.16.65 8000
    returnList
        .add("0000007E0001020076855A179A36B09A76DAA285C6EFBB53091D691E0CEADF5720DDFA7A3029BB53C4FBE069FB9DD1E2DB2236807DC5FB68608E77FB14B00E80A072B32D5F0FD444585749F56CAE4F5B04467599B37B2F47EBB0E16AA2931EC20D3708BD70A2104C15366F95575B9152A01DDF71E9212F17E69D069F520920344F8A");
    return returnList;
  }

  public List<String> saki_ios() {
    List<String> returnList = new ArrayList<String>();
    // 53 liu4232374
    // 53 112.124.44.11 8300
    returnList
        .add("000000760001020076855A17827227FE3F23F20125F232AA160D196A95B96EBF90085915EEE7082F1B80D93A4C7A0F310F313B98FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2C6A5A12945D03629D0EEF2D290D2360FF47AF43196D293F4363B3B479083B6478588F8537C207B");
    return returnList;
  }

  // ========================================================================================
  // ANDROID
  // ========================================================================================
  // ANDROID
  // ========================================================================================
  // ANDROID
  // ========================================================================================
  // ANDROID

  public List<String> getAndroidCeShi_login() {
    List<String> returnList = new ArrayList<String>();
    // 63 112.124.6.241 8000
    // 63�� 1103205709@qq.com 835714842a ��׿ 360
    returnList
        .add("0000007D0001020076855A1722B0E4EBC320BB8593EFBBD61FDB4456EB880847A904A65CCCAA1C7B9320D7531E6206FC70E92A504F06AC063CA18152A43E1AF65E865BF5FF58857737BA9785832761E710E4E4284AACE2FF9FFD3563C4F5C7D36C688900E63CFCC4BC7265594FF479F63C59339A57CA5C0E754C9428F294048011");

    return returnList;
  }

  public List<String> getQiDai_android() {
    List<String> returnList = new ArrayList<String>();
    // ��׿7�� 121.199.16.64 8000

    // ��׿7�� �ڴ� �ֻ��˺� nqsd123 ���� 346776921
    returnList
        .add("000000730001020076855A1722B0FDEB9820A28593EF8790667F4E96768B7BD3EE27EFB0C047DB4EBD447430E4C699626616CC6E584365DA146BCEED444AC4730C9122F9444193275C76D3216672BF91BD5C28724FDB63268F63C4ED3CF2D2EA99158AC3A93E5EF89C246434C41C45B59FEB5E94770283");

    // ��׿7�� �����˺�160442132 ����346776921
    returnList
        .add("0000007B0001020076855A1722B025EBF420A28593EFBB8CA7F42218CE881C5F4D2567838E46BB8CC450119B4453DFAE64FC807DE0EA1C93CFCC193A91D5CD38ADE9DCFC9E466BD2ACA71B789D761E4EDA76C4A12D761021FCA46071D5996E4220CB27EBE68ADE0D00BB6E20E2098237CEAA7A3B2FD76634EADA132002EE95");

    return returnList;
  }

  public List<String> getLianMeng_android() {
    List<String> returnList = new ArrayList<String>();
    // 18�� 121.199.50.218 8100

    // 18�� ���� �ֻ��ChesterLee 54102549
    returnList
        .add("000000760001020076855A1722B01A9AF4A5A285C0E0E83FF0C4F4832FBBF15FF28BD19FED3B9FCDE3ACBD5D83CB9D8BBD2189570DD0B50734139B61DF351AF792507118FF3AFC0CA799150D722F4656C2B19253E28705B51832FB9EBBEC5335B4F4DDD977CBF24797F02FD504C8F7C7D0554EDCF1D020093294");
    return returnList;
  }

  public List<String> getLiuSha_android() {
    List<String> returnList = new ArrayList<String>();
    // 11�� 121.199.41.124 8100

    // ��׿11�� ��ɳ �ֻ�汾 �˺�:heavry ����:505729
    returnList
        .add("000000730001020076855A178272274223FA90468F4283F5FE3FC216AA252946F28FD12816E78045A5A61B3AA516800266A5DE80B3808F452316D7A5DEDE1B162523DE24FE3F9095AA3E78B636AA69823EB41B29F3F8FE3F23F20125F232AA160D19BC75100845E0E015806D9860C312856152536E3543");
    return returnList;
  }

  public List<String> getAiYaGe_android() {
    List<String> returnList = new ArrayList<String>();
    // 53�� 121.199.42.188 8300

    // ��׿53�� ��ѽ�� �ֻ�汾�˺�zhuyinjll ���� asdfghjkl
    returnList
        .add("000000760001020076855A17827227FE3FC216AA252946F28FD12880E7B4161BD780D566A5A5E7593AA51B028002DE805945D745801B0245DE8F02E23F23F20125F232AA160D19BCBE2EFDB38BCF900A630A736EBF90085915EEE7082F1B6E01D86602E778F3D9610845E0E015806D9860631285192D53D2610C");
    return returnList;
  }

  public List<String> getTiTang_android() {
    List<String> returnList = new ArrayList<String>();
    // 15�� 121.199.30.207 8600

    // 15�� ���� ��׿�ֻ� jingqia999 6488969
    returnList
        .add("000000770001020076855A17827227FE3FC216AA252946F28FD12880238F45B4F7B4252325021B66A5A6A645B425F716DEDEE72523803A1625A6D7E23F23F20125F232AA160D19BC7584FDB38BCF900A630A736EBF90085915EEE7082F1B80F3E77821A5E7D24040F00845E0E015806D9860631285A896DA58348A");
    return returnList;
  }

  public List<String> getYuTou_android() {
    List<String> returnList = new ArrayList<String>();
    // 13�� 121.199.30.207 8000

    // 13�� ��ͷ �ֻ㰲׿ fish456 fish920105
    returnList
        .add("000000740001020076855A1782722742671623C2783EF30D8267DF29C2A545DE6C3F9095AA3E78B636AA69695F3F23F20125F232AA160D19BC75346ECBE7E478F7CF66902E3ED2CBA140F4A545B690A1DDB620DEA120D096D07A937A903EA5F2D2933131C6EE50FDB38BCF900A630A0A1285A69B5315C451");
    return returnList;
  }

  public List<String> getHuoShen_android() {
    List<String> returnList = new ArrayList<String>();
    // 7�� 121.199.8.236 8600

    // ���� �ֻ� joseph35361514 841052 ����
//    returnList
//        .add("0000007A0001020076855A17827227FE3F23F20125F232AA160D19BC75CD6EBF90085915EEE7082F1BD7F315CBB320E5314C3136934C9398FDB38BCF900A631243DAE7E478F7CF66902E3E45D23602D07A4C40367ACBE0A13B293ECB3E3602D236293EF493F402A10F9331480845E0E015806D9860B11285F25020E24BEC");

    // ���� �ֻ� �˺ţ�zy0717���룺123456������
//    returnList
//        .add("000000720001020076855A1722B0250CE520FE8537984EF4A2796A9677E204CAD946541844739A78A8153A9BE1E437183B4A55929D0C6BC0F75EC2EEF4257E6E9C00A3E82D96E80FC846729C35B2D4948B24F4E570703DC5D0E29C4588B7AD682166078AAAFDCFE0B848431FB35118DC53E994342D53");

    // ���� �ֻ� �ʺ����붼��13417017744 С��
//    returnList
//        .add("000000770001020076855A17827227FE3F23F20125F232AA160D19BC75CD6EBF90085915EEE7082F1B3B93317A933BC6933B3B7A98FDB38BCF900A631243DBE7E478F7CF66902E3ED24C369045A13BF4CBE019EED0CBF2F219C629E0581936DDD029D2310F29DED09C0845E0E015806D9860B112850AEF943FA200");

    // ���� �ֻ� viva725 13231266 ľҶ
//    returnList
//        .add("000000720001020076855A1722B0B09A9820A285C6EFBB53646A2A3CE397D3865827FB625D2FAE3A96139CD14FC859D8137BAA64A6C03F773931A5C2A5EBFC04AB42302A185556B46E9F6F8C1693D9895892829D91151A758AA590DAD3B0A3E62536A6ADDE346A213885457C5BB458DCA39D942C386B");

    // ���� �ֻ� weilan1206 weilan0607 С��
    returnList
        .add("000000770001020076855A17827227FE3FC216AA252946F28FD1288002A5678F4523451B8F45A6DEF716E7E7D71B1B3A80DED566A5DED78F80E702FE3F23F20125F232AA160D19BC759CFDB38BCF900A630A736EBF90085915EEE7082F1B66DDDEE7D945AA930FC6560845E0E015806D9860631285CBAC94BECC61");

    // ���� ���� 160972620 ��153560������
//    returnList
//        .add("0000007D0001020076855A179ADF2AEBF420A285C6EF803D17C36199B0FD29B995D2262DD1335CEBE83A409DF1A32994BF281488C08263D11839C57470A35382019BFB511ED414C856A92776AE9D7444D223D9EEF8518679A4643341749B54A3737FC7F4E95CFFE12D837920D5B8E556B0A8CC96D2F44E31419BAE671120A1A61E");

    return returnList;
  }

  public List<String> getWeiXiu_android() {
    List<String> returnList = new ArrayList<String>();
    // 46�� 112.124.39.103 8100

    // ��׿�ֻ�46�� Ψ�� xiu111111 19950819
    returnList
        .add("000000710001020076855A17827227FE3F23F20125F232AA160D19BCBE236EBF90085915EEE7082F1B6E963A4CA27E6E05F38F325F39BB0007E7B7BC5F258F6311193DF205F2299A365F6C1B1B05F05F60B756F24040297E16F03E7E165F9A7E403605646C3535BC25BC8F31E1BBC84A34537F4232");
    return returnList;
  }

  public List<String> getJunYou_android() {
    List<String> returnList = new ArrayList<String>();
    // 9�� 121.199.8.236 8000

    // 9��9�� ���� 36797956 147369
    returnList
        .add("0000007B0001020076855A1722B02AEBF420A285C6EFBBEBD34E00595A781A4704D2262DB34F7E9FB0C007D3684DDF9FCB6977D124576DDDB53FE95FE04798C6A3F6EE20D1499798C93E41534B9133CF15CF7CDF0E64DC4D3591AC18D8CCFFB5B54AD866F867853AE5A37EC2D4165390B00A2675A7A94AD8DC58B694DC98F1");
    return returnList;
  }

  public List<String> getFengFei_android() {
    List<String> returnList = new ArrayList<String>();
    // 9�� 121.199.8.236 8000

    // �ֻ㰲׿9�� FengFei 13192218250 123456
    returnList
        .add("000000780001020076855A17827227FE3FC216AA252946F28FD1286680D7808FD74516DED7B4D5A5A5A6D71B2545A51BDEE745DE5966161BDEB4A66C3F23F20125F232AA160D19BC75F0FDB38BCF900A630A736EBF90085915EEE7082F1B3B933193400F0F93F40F4C480845E0E015806D9860631285DBB053F53864");
    return returnList;
  }

  public List<String> getTuanTan_android() {
    List<String> returnList = new ArrayList<String>();
    // 18�� 121.199.50.218 8100

    // ��׿18�� ��̸ �ֻ�� x501400404 123456789
//    returnList.add("000000750001020076855A1722B045EBE5207E853712D47EB6EFC89B94B9310702B5582EC31BA1C0437E5B6F6E887D7454A8196E249D5EA7398C74E0D27CB0746A4E10CEACD30AC955DC1CFBC21E804B8213888C066132213A3B40323803C8AEA676B4006CBC3F66B149855DA1E12F40A7E3DCBAF6535405BA");
    // ��׿18�� ��̸ ���� 213813186 123456789
//    returnList.add("0000007C0001020076855A179ADF45EBF4A57E853722BE4B17007B68E3241F10CF3C782A3406BF7EEE0FCF49F1ECF2AD6E6B8EB63667D226CEB387E5BD74CB578FC76D338449851F5ACD00C25DDB1D89884A1A249213B3E5EE541EDD10EA782495FF5EF72658DB2C003A9F1319682D28B4DAD9FE76444632A3C80C04947A1D93");
    // ��׿18�� ��̸ �ֻ� j520ang35 527527
    returnList.add("000000740001020076855A1782722742671623C2783EF30D6CC202DE02B4362559D7C523FA90468F4283F5FE3FC216AA252946F28FD1288067DEB402D7E72380DE59451BA6238FDEA5E724808002451B6641C4806C3C8DC48B553B55F03655DDD7778D724C8BD54C8B6436FEF6E47C50BBC879BB53AEA391");
    return returnList;
  }

  public List<String> getXiaoXieYanShu_android() {
    List<String> returnList = new ArrayList<String>();
    // 124�� 112.124.25.245 8200

    // 124����д���� qq2293131090 QWERTY
    returnList
        .add("0000007D0001020076855A179A8EF7EBF420A28593E37E71FDCB6A4B3F9FD9661F6BF09E98B7EF902DF3DE889C15C5FC99F9A92102685B6D1757F2E904859300D45D52BA084FC6AE10B1C5E502BDED556B1427B62FE8C7E29D62C805970C7E3C0ED524B6A72570B0E9C647003B2B83300B86E993BC242CA98F5B9ABA31206751B4");
    return returnList;
  }

  public List<String> getLiShang_android() {
    List<String> returnList = new ArrayList<String>();
    // 15�� 121.199.32.76 8000

    // ��׿15�� ���� ���οͻ��� 207552712 59714841
    returnList
        .add("0000007E0001020076855A17827227FE3FC216AA252946F28FD1281623CA8025250223161BB3B48F80D7A559A545DED7454580D7D7801B02DE80CA4E23FA90468F4283F5FE3F9095AA3E78B636AA6905DE0202A580D7A5DE02D780808002809F019E671623C2783EF30D82A23F23F20125F232AA160D19BC75841285BF83940AF226");
    return returnList;
  }

  public List<String> getALin_android() {
    List<String> returnList = new ArrayList<String>();
    // 2�� 121.199.41.123 8100

    // ��׿2�� ���� �ֻ��˺� alinna 12356
    returnList
        .add("000000720001020076855A1722B025EBC320A28593EF8029208E22D92A55369D352D0AEB9AA61F36491B1DBDF7DD48544B7D296B7DBF73951FEE08F058157C62F2810F948E0E0CF94B2BE3D3FF321FAD4D1B5376370CCC8555172FE506AD46515B397FAF6AE650F7A1BBDFB6BEA49B856BA153D0AD6F");
    return returnList;
  }

  public List<String> getQingShi_android() {
    List<String> returnList = new ArrayList<String>();
    // 9�� 121.199.8.236 8000

    // ��׿�ֻ�9�� ��ʲ zw2436c 123456
    returnList
        .add("000000730001020076855A1722B0FDEBE5207E85C073BE7EB6EF00E641E6F0F7D92B29ABF8BB0C2E71E9FD5103D0AED3210DED8D0DA7FC0E86D067B2627E4589314C84070B0ABCEC5256E981BAC3280DF3248FFF599B5EB67E5C78E776831637FBEE7891EEBA5897002B2DAB921A083908788553E3D103");
    return returnList;
  }

  public List<String> getCongQian_android() {
    List<String> returnList = new ArrayList<String>();
    // 32�� 121.199.50.217 8200

    // �ֻ� 32�� ��׿ ��ǰ wenjiannba8888 nbanba
    returnList
        .add("000000780001020076855A17827227FE3F23F20125F232AA160D19BCBE376EBF90085915EEE7082F1BCADDDE78F3E745787846D276476E05F38F325F39BB005C02B7BC5F258F6311193D6029F0F21B6056B711561105051116369AF2F03E36B7113E9A1129F236361B11A36C3535BC25BC8F31E1BBC8C0A3DA4BFCDA");
    return returnList;
  }

  public List<String> getDaiLu_android() {
    List<String> returnList = new ArrayList<String>();
    // 9�� 121.199.8.236 8000

    // ��׿9�� 9�� ��³ 164261027 110222
    returnList
        .add("0000007D0001020076855A179ADF2AEBF420A285C6EF803D17C36199B074194F903DFF2B19C0A53D413A75BAC19E3AA048C2FF8EA63E74F018F00B191CAA70FF72BBAB731A4DAE29D6655DC2A21E8C938255EFAD682166F8614621796A43DC213B017071F099A5276583D665346F56374D3A2CD6316CE0FC30E9C8E789943A6235");
    return returnList;
  }

  public List<String> getREV_android() {
    List<String> returnList = new ArrayList<String>();
    // 18�� 121.199.50.218 8100

    // ��׿�ֻ�18�� Rev j520ang29 15178575575
    returnList
        .add("000000750001020076855A1722B025EBC320A28593E35C8C79CEC463C64ED93F74F027BBA81C77C44BD2D34421F674B20BFD9B978B5742F83E098563C90039827CE2872E027413029D71E1154976DB49C2C39A0D04190070A231C2421C6708D43AA6FB251B58388E0429D9DFFDA1F1C1074C9A4C8C53269B37");
    return returnList;
  }

  public List<String> getChongWu_android() {
    List<String> returnList = new ArrayList<String>();
    // 7�� 121.199.16.64 8000

    // �ֻ㰲׿���� chongwu65 kjl123456
    returnList
        .add("000000750001020076855A1722B02AEBF420A285C6EFBBEBD34E002FCCA20DE4B558275D25C5200BCAE3FF9CAA06AE9C453510CEAB226011ADD9A3EFDD079955C2E581A90B3161EF929EBBCD50B000BC472290FA45E974B6223AF185D299B17C3BF359C7B735ABAD56441A838FDD1362BA6BC8BEE094628DAE");
    return returnList;
  }

  public List<String> getFuZhu_android() {
    List<String> returnList = new ArrayList<String>();
    // 9�� 121.199.8.236 8000

    // ��׿�ֻ� 9�� ���� �˺ţ�jueyue112 jueyue112
    returnList
        .add("000000740001020076855A1722B0B09A9820A285C6E33FDDD3891D5C89DA5C5A132BFBA9452D3DCD34A517E6D1FB48E7A93C99CBAA5B7A29D034CE0141BFA4DBE769F298E928E9753D11CC2E172B1D7245291452394C539D4939A5B6B0DBB72AB2392CC8AB75D96830BA542AFD6335DAF3920D4694601C99");
    return returnList;
  }

  public List<String> getHeiMaoJingZhang_android() {
    List<String> returnList = new ArrayList<String>();
    // 47�� 112.124.39.103 8000

    // �ֻ㰲׿47�� ��è���� pangxianer1 1234567
    returnList
        .add("000000780001020076855A1782722742671623C2783EF30D6C78B436256D0F163625F202FE3F9095AA3E78B636AA69697223FA90468F4283F5FE3F23F20125F232AA160D19BCBE926ECBE7E478F7CF66902E3ED2F4F40F0F3120DEB696F2E0B6F436804545D23BCBB6EE45DED20FEEA140C6F42D1285B718200370F6");
    return returnList;
  }

  public List<String> getZhuYin_android() {
    List<String> returnList = new ArrayList<String>();
    // 32�� 121.199.50.217 8200

    // ��32 ���� zhuyinjll asdfghjkl
    returnList
        .add("000000760001020076855A1782722742671623C2783EF30D6C1E9590FA3636DF95B68D23FA90468F4283F5FE3F9095AA3E78B636AA69695F3F23F20125F232AA160D19BCBE376ECBE7E478F7CF66902E3ED2362045DE19930F40F4C6363B36F480B6930F4C933B7A317A9380B67A4C90B6CC1285EA175366A6A4");
    return returnList;
  }

  public List<String> getFaCai_android() {
    List<String> returnList = new ArrayList<String>();
    // 12�� 121.199.50.217 8200

    // ��12 ���� chiuandy andy123456
    returnList
        .add("000000760001020076855A1782722742671623C2783EF30D6C1E9590FA3636DF95B68D23FA90468F4283F5FE3F9095AA3E78B636AA69695F3F23F20125F232AA160D19BCBE376ECBE7E478F7CF66902E3ED2362045DE19930F40F4C6363B36F480B6930F4C933B7A317A9380B67A4C90B6CC1285EA175366A6A4");
    return returnList;
  }

  public List<String> getFengYun_android() {
    List<String> returnList = new ArrayList<String>();
    // ��12 ���� 15363525 xp7886138
    // 12 121.199.30.207 8100
    returnList
        .add("000000750001020076855A1782722742671623C2783EF30D6C8080DED7E7D7DE02E23F23F20125F232AA160D19BC75BA6ECBE7E478F7CF66902E3E45A136A53EF44C3BA5A1933BEE583E4C4C0FEEF219C6CB3ECBB60F3B3BCBD23B976EBF90085915EEE7082F602BFDB38BCF900A630A0A1285DCC81F842B5F");
    return returnList;
  }

  public List<String> getXuZe_android() {
    List<String> returnList = new ArrayList<String>();
    // xuzep8 13864981336 ��׿�ٷ� 14������
    // 14 121.199.30.207 8500
    returnList
        .add("000000730001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA6982FA90FA8FF27FA23F23F20125F232AA160D19BC75236ECBE7E478F7CF66902E3ED2368019F40F3B7A0F90B640EED2A5D090E058D220B6CBDE3EA5194020D23B902E0845E0E015806D9860B112851FFB53ECB536");
    return returnList;
  }

  public List<String> getXiaoHai_android() {
    List<String> returnList = new ArrayList<String>();
    // �� 18 С�� punk199 112233
    // 18 121.199.50.218 8100
    returnList
        .add("000000700001020076855A179AF4450C4820FE85C05644437BB063BB50C531A6E8A7655F5B5580DBCD620778FC7D07F4961C3CEE65DDC783B5747DFEFDC878C619F2C5BC8C04EDE662463C935B3A1CAB236F2E7178654A2E93119A3C47BC6C218666B3DA5297635C8198799698A0E842533742B9");
    return returnList;
  }

  public List<String> getZuoXuan_android() {
    List<String> returnList = new ArrayList<String>();
    // ��52�� ���� ���� 229177080 huangguotang
    // 52 121.199.42.188 8200
    returnList
        .add("0000007C0001020076855A1722B045EBF420FE8593E37E71FDCB442233C18A75B1D235BB64A07EC393C007DD9C625770210DFE6567B92F40C51B0BC62C29DB37E19A30AD7374888CB22F8E6FAFBB19163D29734CBBC425A51A988603429AF2806F8A5908477BADAF68AF2F3DAAB2B2BEC7FA9907AAB30F3564AEBFB6207B30D3");
    return returnList;
  }

  public List<String> get5_3IN1_ios() {
    List<String> returnList = new ArrayList<String>();

    // 5 121.199.44.72 8300

    // iOS5�� �˺�366538327 ����115491855 ��û��Ǯ��
//    returnList
//        .add("000000750001020076855A17827227FE3F9095AA3E78B636AA696CD7D7E7E7DECAA5D702BD23FA90468F4283F5FE3FC216AA252946F28FD12816A6A64524A545161623D7DEA5A6E7A502B3A5A57F8080A6457F8025E7591BDE4E9F9542269E2E7F452C658A8C2E758CA118A87F875AA1954785BF481FAC6278");

    // iOS5 �˺�circle88388 ����19880624 ��û��Ǯ��
    returnList
        .add("000000780001020076855A17827227FE3F9095AA3E78B636AA696C67C2298FC2A6B37FA5CA7F4E23FA90468F4283F5FE3FC216AA252946F28FD12880B4CA7FB4801B3A80A5B4DE1BDEDE456702238059A602451BA61B02D516E71682671623C2783EF30D695F3F23F20125F232AA160D196A322E1285A601940393DC");
    // iOS5�� �˺�366538365����6281120112 ��û��Ǯ��
//    returnList
//        .add("000000740001020076855A1722B025EBC320A28593E3295D4FEDB0D4EECBA0F6E4A6E74B771CD4B05631D3E1847D8A6A2186342726B5862166F860BC53CDED5327DCA9B0A64628CCFA8308B19BDFC5FE718BD002C827CD82E80F46855A5DAD2C64978BAD9D1E9F287FA0FBFC582F62C17BAF43021FC3F399");

    return returnList;
  }

  public List<String> getWenWan_2IN1_ios() {
    List<String> returnList = new ArrayList<String>();

    // 5 121.199.44.72 8300

    // 5����� ���� Ibanez ����871104
//    returnList.add("000000700001020076855A1722B02AEB37A5A285C6B9CE0049717CC4C074EDD30CFBB3CF9FFA305FEC01546F0CE786774CC288BCB00ED9BC0DE8AC67BA09E316B1C0614F1F1C35A6B5D139204C6CBBEE9A1B5DF57F8B1E40DE8529BFE67660EC8A5D02119FECD4DD17E0881597557AD6534B7165");

    // 5����� ���� sweetpet ����4652092
    returnList.add("000000740001020076855A17827227FE3F9095AA3E78B636AA696C293F671BF246B4F28D23FA90468F4283F5FE3FC216AA252946F28FD1286616CA80D7E7DE67E76716D566A5E73A166680453A801B028FE75945768F2DECD977D9BD269E2E7F452C658A8C2E758CA118A87F875AA19547855D645374E076");

    return returnList;
  }
  
  public List<String> getGongJi_ios() {
		List<String> returnList = new ArrayList<String>();
		// cxm8058 hxujk12 ios4 快用 公鸡
		// 4 121.199.44.72 8000
		returnList
				.add("000000730001020076855A1722B025EBC320A28593EFA28C7944B0D449C7F97F04A65C9EB71CB193564ADD34AAC546A068BB607CD409D2090D5208FAC186511378FA792B0322F4AEBAE0799EB02D466CB3FAAD186B1399DE701BA121E89C2B50124784DA9677F5B01D7379E5C703CC3622808B536627AC");
		return returnList;
	}

  public List<String> getXuJia_ios() {
		List<String> returnList = new ArrayList<String>();
		// ios47 徐佳 a4560071 12345
		// 47 112.124.26.242 8000
		returnList
				.add("000000750001020076855A17827227FE3F23F20125F232AA160D196A8F7C6EBF90085915EEE7082F1BA5D27A4C36C6C63BEFFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2EEE0A17A29F2A13B93DDDEE03E0F4C96E03EF4312946A1939646D0CB58E0D2481285022D5361F1DD");
		return returnList;
	}
  
  public List<String> getWuMing_ios() {
		List<String> returnList = new ArrayList<String>();
		// IOS快用版 54区 无名 fz1191 102030
		// 54 112.124.55.42 8100
		returnList
				.add("000000730001020076855A17827227FE3F23F20125F232AA160D196A95166EBF90085915EEE7082F6059FA95939340EFFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED23B90A180D00245F2E0D220A1939331CB45193629D24C3BDD3EC631CBF258586012855FDC53E755AE");
		return returnList;
	}
  
  public List<String> getChiTu_ios() {
		List<String> returnList = new ArrayList<String>();
		// 苹果PP版 100区 赤土 24452000 qwe456
		// 100 115.29.184.169 8200
		returnList
				.add("0000007B0001020076855A179A36B09AF4A57E8537D4E8C9661D53B80015F738984D50415031BFDDB4C0B473F1833FA848A44E1CD21B6DA078406227DC365E1B96A2FE2C2DE1D2AF5C1B8CD049C8CE6626CC0613790820A5F489D697BA8B0F28BBC7381BD6701DFA21FA4FD5814D7CB884FA4D07AAB35A54884CF720021893");
		return returnList;
	}
  
  public List<String> getDiZi_landa() {
		List<String> returnList = new ArrayList<String>();
		// 22区  majun0126  m654321
		// 22 112.124.27.168 8100
		returnList
				.add("000000740001020076855A1722B02AEBC320A285C6E35CEB0ECE002F49754103997A30DF179397A5F3BB7C8269A050E7C7D94C8EC93E589EEED00CC1100EE43159209E1032874C53EA2C8FD1F0D89A7C7E0ED7B06C3FF59FB2FD6A17FF859A008992A77A9FECFEE53879C7AC180D4AB1A21D972A944BDFFA");
		return returnList;
	}
  
  public void doXiaonancheNiaoLong() {
	  
	  if(checkTime(21,20,21) == 1){
		  doNiaoLong("121.199.15.9",8400,get39_loginHZ());
		  landaCount++;  
	  }
  }
  
  public void doXiaonancheLanDa() {
	  
	  // xiaonanche 00000aaaaa 107 qu 
	  doSanGuoJunTuanBoss("115.28.87.62",8004,getLoginStrs("510001FF4D004C4F47494E20302E302E31203720313137203130343834343530203132332031343334323836363736206333343635363362616234356166656264623666346135646264653238663637203000","1D0002FF1900454E5445522031303438343435302031313730303436333600"));
	  
	  landaCount++;
//	int i = checkTime(20,40,61); 
//    if(i == 1){
//    	doDingShang("121.199.15.9",8400,get39_loginHZ());
//        landaCount++;
//    }
    
  }
  
  public ArrayList<String> getLoginStrs(String loginStr,String loginStrA){
	  
	  ArrayList<String> loginStrs =  new ArrayList<String>();
	  loginStrs.add(loginStr);
	  loginStrs.add(loginStrA);
	  return loginStrs;
  }

}
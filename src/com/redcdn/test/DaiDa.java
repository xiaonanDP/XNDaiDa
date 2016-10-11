package com.redcdn.test;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaiDa extends Frame implements ActionListener {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Button cutButton;  
	TextField secondText;
	TextField timeText;
	Label label;
	
	Button checkButton;
	TextField passwordText;
	TextField dateText;
	TextArea resultArea; 
	
	static int timeDuration = 0;
	
	List<Customer> lanDaCustomers = null;
	Map<String,Server> lanDaServers = null;
	
	List<Customer> dingShangCustomers = null;
	Map<String,Server> dingShangServers = null;
	
	List<Customer> iosCustomerList = null;
	Map<String,Server> iosServerMap = null;
	
	List<Customer> androidCustomerList = null;
	Map<String,Server> androidServerMap = null;
	
	List<Customer> qRenCustomers = null;
	Map<String,Server> qRenServers = null;
	
	static int groupSize = 1;
	static String ACTION_CHECK_SELF = "ACTION_CHECK_SELF";
	static String ACTION_BEGIN = "ACTION_BEGIN";
	
	public DaiDa() {
		super("ios DaiDa");
		
		setSize(500, 280);
		addWindowListener(new TestAdapter());
	  
		Panel toolbar = new Panel();
		toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));

		cutButton = new Button("BEGIN");
		cutButton.setActionCommand(ACTION_BEGIN);
		cutButton.addActionListener(this);
		toolbar.add(cutButton);
	    
	    secondText = new TextField(8);
	    secondText.setText("16");
	    toolbar.add(secondText);
	    
	    label = new Label("second");
	    toolbar.add(label);
	    
	    label = new Label("LanDaCount :      ");
	    toolbar.add(label);
	    
	    timeText = new TextField(6);
	    timeText.setText("61");
	    toolbar.add(timeText);
	    
	    Label label_ = new Label("minutes close.");
	    toolbar.add(label_);
	    
	    add(toolbar, BorderLayout.NORTH);
	    
	    Panel backGround = new Panel();
	    backGround.setLayout(new FlowLayout(FlowLayout.LEFT));
	    checkButton = new Button("check");
	    checkButton.addActionListener(this);
	    checkButton.setActionCommand(ACTION_CHECK_SELF);
	    backGround.add(checkButton);
	    
	    passwordText = new TextField(10);
	    passwordText.setText("");
	    backGround.add(passwordText);
	    
	    dateText = new TextField(10);
	    dateText.setText("");
	    backGround.add(dateText);
	    
	    resultArea = new TextArea(200,300);
	    backGround.add(resultArea);
	    
	    add(backGround, BorderLayout.CENTER);
	}
	
	public static void main(String args[]) {
		  DaiDa tf1 = new DaiDa();
		  tf1.setVisible(true);
	  }
	
	public void checkSelf(String timeStr){
		initLanDaCustomers();
		System.out.println(timeStr);
		  resultArea.setText("time: "+timeStr+"\n");
		  
		  for(int i = 0 ; i < lanDaCustomers.size() ; i++){
			  Customer customer = (Customer)lanDaCustomers.get(i);
			  int timeA = customer.getClientType();
			  if(timeA <= Integer.valueOf(timeStr).intValue()){
				  resultArea.append(customer.print());
			  }
		  }  	  
	  }
	
	public void checkAll(String timeStr){
		
		initIOSCustomers();
	    initANDROIDCustomers();
	    initQRenCustomers();
	    
		resultArea.setText("time: "+timeStr+"\n");
		resultArea.append("ios ======\n");
		for(int i = 0 ; i < iosCustomerList.size() ; i++){
			  Customer customer = (Customer)iosCustomerList.get(i);
			  
			  if(!"NAN".equals(customer.getContactor())){
				  int timeA = customer.getClientType();
				  if(timeA <= Integer.valueOf(timeStr).intValue()){
					  resultArea.append(customer.print());
				  }
			  }else{
//				  resultArea.append(customer.print());
			  }
			  
		  }
		resultArea.append("android ======\n");
		for(int i = 0 ; i < androidCustomerList.size() ; i++){
			  Customer customer = (Customer)androidCustomerList.get(i);
			  
			  if(!"NAN".equals(customer.getContactor())){
				  int timeA = customer.getClientType();
				  if(timeA <= Integer.valueOf(timeStr).intValue()){
					  resultArea.append(customer.print());
				  }
			  }else{
//				  resultArea.append(customer.print());
			  }
			  
		  }
		resultArea.append("ios q ban ======\n");
		for(int i = 0 ; i < qRenCustomers.size() ; i++){
			  Customer customer = (Customer)qRenCustomers.get(i);
			  //resultArea.append(customer.print());
			  
			  int timeA = customer.getClientType();
			  if(timeA <= Integer.valueOf(timeStr).intValue()){
				  resultArea.append(customer.print());
			  }
		  }
		
	}
	
  static int interval = 0;
  public void actionPerformed(ActionEvent ae) {
      
	if(ae.getActionCommand().equals(ACTION_CHECK_SELF)){
		String password = passwordText.getText();
		String dateStr = dateText.getText();
		if("5188".equals(password)){
			checkSelf(dateStr);
		}else{
			checkAll(dateStr);
		}
		
	}else if(ae.getActionCommand().equals(ACTION_BEGIN)){
		
		cutButton.setEnabled(false);
	    interval = new Integer( secondText.getText()).intValue();
	    if (interval < 1 || interval > 65)
	      interval = 61;
	    
	    timeDuration = new Integer( timeText.getText()).intValue();
	    if (timeDuration < 10)
	      timeDuration = 30;
	    
	    // init customers
	    initIOSCustomers();
	    initIOSServers();
	    initANDROIDCustomers();
	    initANDROIDServers();
	    
	    initLanDaCustomers();
	    initLanDaServers();
	    initDingShangCustomers();
	    initDingShangServers();
	    
	    initQRenCustomers();
	    initQRenServers();
	    
	    // ios peien
	    for(int i=0 ; i<iosCustomerList.size(); i += groupSize){
	      MyThread m0 = new MyThread(i);
	      new Thread(m0).start();
	      try{
	        Thread.sleep(100);
	      }catch(Exception e){
	        e.printStackTrace();
	      }
	    }
	    
	    // android peien
	    for(int i=0 ; i<androidCustomerList.size(); i += groupSize){
	      MyThread m0 = new MyThread(1000+i);
	      new Thread(m0).start();
	      try{
	        Thread.sleep(100);
	      }catch(Exception e){
	        e.printStackTrace();
	      }
	    }
	    
	    // si shen
	    for(int i=0 ; i<lanDaCustomers.size(); i += groupSize){
	      LanDaThread m0 = new LanDaThread(i);
	      new Thread(m0).start();
	      try{
	        Thread.sleep(100);
	      }catch(Exception e){
	        e.printStackTrace();
	      }
	    }
	    
	    // hai zei
	    for(int i=0 ; i<dingShangCustomers.size(); i += groupSize){
	      DingShangThread m0 = new DingShangThread(i);
	      new Thread(m0).start();
	      try{
	        Thread.sleep(100);
	      }catch(Exception e){
	        e.printStackTrace();
	      }
	    }
	    
	    // qRen peien
	    for(int i=0 ; i<qRenCustomers.size(); i += groupSize){
	        QRenThread m0 = new QRenThread(i);
	        new Thread(m0).start();
	        try{
	          Thread.sleep(100);
	        }catch(Exception e){
	          e.printStackTrace();
	        }
	      }
	    
	    System.out.println("...."+interval);
	}else{
		
	}  
	  
  }
  
  public void doPeiEn(int threadNumber){
	  
    if(threadNumber < 1000){
    	if(checkTime(20,39,30) == 0)
    	      return;
    	doIOSPeiEn(threadNumber);
    }else{
    	if(checkTime(20,39,30) == 0)
    	  return;
    	doANDROIDPeiEn(threadNumber);
    }
    
    if(threadNumber == 0 || threadNumber == 1000){
      label.setText("LanDaCount : "+landaCount);
      
      if(landaCount > 10000){
        landaCount = 0;
      }else{
        landaCount++;
      }
    }
    
  }
	
	static int landaCount = 0;
	public void doLanda(String host,int port,String loginStr)
	{
			try{
		        InetSocketAddress remote = new InetSocketAddress(host, port);  
            SocketChannel sc = SocketChannel.open();  
            sc.connect(remote);  
            
            List<String> cmdList = new ArrayList<String>();
            cmdList.add(loginStr);
		        cmdList.add("0000001400020300AE855A17827227F5418585C085BBA323");
		        cmdList.add("0000001400030300D6855A17827227F5418585C085BB25E6");
		        cmdList.add("0000001400040700C8855A17827227F5418585C085BB55E7");
		        cmdList.add("0000002500051C00C8855A17827227CCAA29EB291BF2F87E8FF2C278F28F23D1834185E8BBD628A9D2");
		        cmdList.add("0000001400060600AE855A17827227F5418585C085BBA327");
		        cmdList.add("0000001400071B00C8855A17827227F5418585C085BB55E4");
		        cmdList.add("0000001400081B00C8855A17827227F5418585C085BB55EB");
		        cmdList.add("0000001400091800C8855A17827227F5418585C085BB55EA");
		        cmdList.add("00000014000A1B0076855A17827227F5418585C085BBD84A");
		        
		        if (landaCount == 0) {
		  	      //canzhan
		        	cmdList.add("0000001C000B1B00AE855A1782722742295B29B4D169D7E341859A83763DCFD1");
		  	    }else{
		  	      
		  	      if (interval > 30)
		  	        //canzhan
		  	        cmdList.add("0000001C000B1B00AE855A1782722742295B29B4D169D7E341859A83763DCFD1");
		  	      else
		  	        //fuhuo
		  	        cmdList.add("0000001C000B1B00AE855A1782722742295B29B4D169D7F541859AF5761FAC96");
		  	    }
		        
		        for(int j = 0; j< cmdList.size() ; j++){
		        	String cmdStr = cmdList.get(j);
		        	ByteBuffer bb = ByteBuffer.wrap(hexStringToByteArray(cmdStr));
		        	sc.write(bb);
		        }
		        sc.close();
		        
			} catch (IOException e) {  
			  e.printStackTrace();  
	    }
	  
	}
	
	public void doPeiEn(String host,int port,String loginStr)
  {
      try{
            InetSocketAddress remote = new InetSocketAddress(host, port);  
            SocketChannel sc = SocketChannel.open();  
            sc.connect(remote);  
            
            List<String> cmdList = new ArrayList<String>();
            cmdList.add(loginStr);
            cmdList.add("0000001400020300AE855A17827227F5418585C085BBA323");
            cmdList.add("0000001400030300D6855A17827227F5418585C085BB25E6");
            cmdList.add("0000001400040700C8855A17827227F5418585C085BB55E7");
            cmdList.add("0000002500051C00C8855A17827227CCAA29EB291BF2F87E8FF2C278F28F23D1834185E8BBD628A9D2");
            cmdList.add("0000001400060600AE855A17827227F5418585C085BBA327");
            cmdList.add("0000001400071700C8855A17827227F5418585C085BB55E40000001400081300C8855A17827227F5418585C085BB55EB0000001400091A00C8855A17827227F5418585C085BB55EA00000024000A1400C8855A17827227CC3E29EB8FF2291BF23F89F31BB62927274185DB77226430EA");
            cmdList.add("00000014000B1D00C8855A17827227F5418585C085BB55E8");
            cmdList.add("00000014000C1200C8855A17827227F5418585C085BB55EF");
            cmdList.add("00000014000D1B00C8855A17827227F5418585C085BB55EE");
            cmdList.add("00000014000E1B00C8855A17827227F5418585C085BB55ED");
            cmdList.add("00000014000F1B0076855A17827227F5418585C085BBD84F");
            
            if (landaCount == 0) {
              //canzhan
              cmdList.add("0000001C00101B00AE855A1782722742295B29B4D169D7E341859A83763DCFCA");
            }else{
              
              if (interval > 30)
                //canzhan
              cmdList.add("0000001C00101B00AE855A1782722742295B29B4D169D7E341859A83763DCFCA");
              else
                //fuhuo
              cmdList.add("0000001C00101B00AE855A1782722742295B29B4D169D7F541859AF5761FAC8D");
            }
            
            for(int j = 0; j< cmdList.size() ; j++){
              String cmdStr = cmdList.get(j);
              ByteBuffer bb = ByteBuffer.wrap(hexStringToByteArray(cmdStr));
              sc.write(bb);
            }
            
            sc.close();
      } catch (IOException e) {  
              e.printStackTrace();  
      }
    
  }
	
	public void doAndroidPeiEn(String host,int port,String loginStr)
  {
      try{
            InetSocketAddress remote = new InetSocketAddress(host, port);  
            SocketChannel sc = SocketChannel.open();  
            sc.connect(remote);  
            
            List<String> cmdList = new ArrayList<String>();
            cmdList.add(loginStr);
            cmdList.add("0000001400020400C8855A17827227F5418585C085BB55E1");
            cmdList.add("0000001400030300AE855A17827227F5418585C085BBA322");
            cmdList.add("0000001400040300D6855A17827227F5418585C085BB25E1");
            cmdList.add("0000001400050700C8855A17827227F5418585C085BB55E6");
            cmdList.add("0000002500061C00C8855A17827227CCAA29EB291BF2F87E8FF2C278F28F23D1834185E8BBD628A9D1");
            cmdList.add("0000001400070600AE855A17827227F5418585C085BBA326");
            cmdList.add("0000001400081700C8855A17827227F5418585C085BB55EB");
            cmdList.add("0000001400091300C8855A17827227F5418585C085BB55EA");
            cmdList.add("00000014000A1A00C8855A17827227F5418585C085BB55E9");
            cmdList.add("00000014000B14005D855A17827227F5418585C085BB4841");
            cmdList.add("00000024000C1400C8855A17827227CC3E29EB8FF2291BF23F89F31BB62927274185DB77226430EC");
            cmdList.add("00000014000D0300FE855A17827227F5418585C085BB15DD");
            cmdList.add("00000014000E1C005D855A17827227F5418585C085BB4844");
            cmdList.add("00000014000F0800C8855A17827227F5418585C085BB55EC");
            cmdList.add("0000001400101600C8855A17827227F5418585C085BB55F3");
            cmdList.add("0000001400111200C8855A17827227F5418585C085BB55F2");
            cmdList.add("0000001400121B00C8855A17827227F5418585C085BB55F1");
            cmdList.add("0000001400131B00C8855A17827227F5418585C085BB55F0");
            cmdList.add("0000001400141B0076855A17827227F5418585C085BBD854");
            
            if (landaCount == 0) {
              //canzhan
              cmdList.add("0000001C00151B00AE855A1782722742295B29B4D169D7E341859A83763DCFCF");
            }else{
              
              if (interval > 30)
                //canzhan
              cmdList.add("0000001C00151B00AE855A1782722742295B29B4D169D7E341859A83763DCFCF");
              else
                //fuhuo
              cmdList.add("0000001C00151B00AE855A1782722742295B29B4D169D7F541859AF5761FAC88");
            }
            
            for(int j = 0; j< cmdList.size() ; j++){
              String cmdStr = cmdList.get(j);
              ByteBuffer bb = ByteBuffer.wrap(hexStringToByteArray(cmdStr));
              sc.write(bb);
            }
            
            sc.close();
      } catch (IOException e) {  
        e.printStackTrace();  
      }
  }
	
	public void doDingShang(String host,int port,String loginStr)
  {
	  try{
      InetSocketAddress remote = new InetSocketAddress(host, port);  
          SocketChannel sc = SocketChannel.open();  
          sc.connect(remote);  
          
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
          cmdList.add("00000014000A1B0076855A17827227F5418585C085BBD84A");

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
          
          for(int j = 0; j< cmdList.size() ; j++){
            String cmdStr = cmdList.get(j);
            ByteBuffer bb = ByteBuffer.wrap(hexStringToByteArray(cmdStr));
            sc.write(bb);
          }
          sc.close();
          
    } catch (IOException e) {  
      e.printStackTrace();  
    }

  }
	
	public void doAndroidDingShang(String host,int port,String loginStr)
	  {
		  try{
	      InetSocketAddress remote = new InetSocketAddress(host, port);  
	          SocketChannel sc = SocketChannel.open();  
	          sc.connect(remote);  
	          
	          List<String> cmdList = new ArrayList<String>();
	          cmdList.add(loginStr);
	          cmdList.add("0000001400020300AE855A17827227F5418585C085BBA323");
	          cmdList.add("0000001400030300D6855A17827227F5418585C085BB25E6");
	          cmdList.add("000000140004250041855A17827227F5418585C085BBD323");
	          cmdList.add("0000001400050700C8855A17827227F5418585C085BB55E6");
	          cmdList.add("0000002500061C00C8855A17827227CCAA29EB291BF2F87E8FF2C278F28F23D1834185E8BBD628A9D1");
	          cmdList.add("0000001400070600AE855A17827227F5418585C085BBA326");
	          cmdList.add("0000001400081B00C8855A17827227F5418585C085BB55EB");
	          cmdList.add("0000001400091B00C8855A17827227F5418585C085BB55EA");
	          cmdList.add("00000014000A1800C8855A17827227F5418585C085BB55E9");

	          cmdList.add("00000014000B1B0076855A17827227F5418585C085BBD84B");
	          
	          if (landaCount == 0) {
	            // canzhan
	            cmdList.add("0000001C000C1B00AE855A1782722742295B29B4D169D7E341859A83763DCFD6");
	          } else {
	            if (interval > 30)
	              // canzhan
	              cmdList.add("0000001C000C1B00AE855A1782722742295B29B4D169D7E341859A83763DCFD6");
	            else
	              // fuhuo
	              cmdList.add("0000001C000C1B00AE855A1782722742295B29B4D169D7F541859AF5761FAC91");
	          }
	          
	          for(int j = 0; j< cmdList.size() ; j++){
	            String cmdStr = cmdList.get(j);
	            ByteBuffer bb = ByteBuffer.wrap(hexStringToByteArray(cmdStr));
	            sc.write(bb);
	          }
	          sc.close();
	          
	    } catch (IOException e) {  
	      e.printStackTrace();  
	    }

	  }
	
	public byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	public static int checkTime(int beginHour,int beginMinute,int duration){
		
	int beginMonth = 11;
	  
    GregorianCalendar currentTime = new GregorianCalendar();
    currentTime.setTime(new Date());
    
//    int month = currentTime.get(Calendar.MONTH)+1;
//    if( month != beginMonth){
//      return 0;
//    }
    
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
      return 0;
    }
    
  }
	
	class MyThread implements Runnable {
	  
	  private int threadNumber = 0;
	  public int getThreadNumber() {
      return threadNumber;
    }
    public void setThreadNumber(int threadNumber) {
      this.threadNumber = threadNumber;
    }

    public MyThread(int threadNumber){
	    super();
	    this.threadNumber = threadNumber;
	  }
	  
    public void run() {
      
        try {
          do{
            doPeiEn(threadNumber);
            Thread.sleep(15500);
          }while(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
       
	}
  
class LanDaThread implements Runnable {
    
    private int threadNumber = 0;
    public int getThreadNumber() {
      return threadNumber;
    }
    public void setThreadNumber(int threadNumber) {
      this.threadNumber = threadNumber;
    }
    
    public LanDaThread(int threadNumber){
      super();
      this.threadNumber = threadNumber;
    }
    
    public void run() {
        try {
          do{
            doLanDa(threadNumber);
            Thread.sleep(11*1000);
          }while(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class QRenThread implements Runnable {
    
    private int threadNumber = 0;
    public int getThreadNumber() {
      return threadNumber;
    }
    public void setThreadNumber(int threadNumber) {
      this.threadNumber = threadNumber;
    }
    
    public QRenThread(int threadNumber){
      super();
      this.threadNumber = threadNumber;
    }
    
    public void run() {
        try {
          do{
            doQRenPeiEn(threadNumber);
            Thread.sleep(15500);
          }while(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
 
class DingShangThread implements Runnable {
    
    private int threadNumber = 0;
    public int getThreadNumber() {
      return threadNumber;
    }
    public void setThreadNumber(int threadNumber) {
      this.threadNumber = threadNumber;
    }
    
    public DingShangThread(int threadNumber){
      super();
      this.threadNumber = threadNumber;
    }
    
    public void run() {
        try {
          do{
            doDingShang(threadNumber);
            Thread.sleep(16*1000);
          }while(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
	
	/**
   * ===== ======== ===================================== =================================== ===== landa
   */

  public List<Customer> initLanDaCustomers(){
    
    if(lanDaCustomers == null){
      lanDaCustomers = new ArrayList<Customer>(10);
      
      lanDaCustomers.add(new Customer("chizhifeng",161103,"2","","000000770001020076855A1782722742671623C2783EF30D6C1BC20F30950F16253E25BD23FA90468F4283F5FE3FC216AA252946F28FD128168059B4DEE702E7E716E78F456766B40216DECAB4DE3A164559E71616F78024A23F9095AA3E78B636AA69695F3F23F20125F232AA160D5009B42B128516F39424A7AF"));  // qq: 378234249
      lanDaCustomers.add(new Customer("519233019",161103,"11","","000000760001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696CD7DE668002D7D7A566FE3F23F20125F232AA160D5009B4E60845E0E015806D9860D46ECBE7E478F7CF66902E3ED2F47A0FDDD0F4DD190FA5D07A363B7AA5D23BF4EED280D2937AC6363620B6C6CC1285319E3DE66D4B"));  // qq: 378234249
      lanDaCustomers.add(new Customer("mayunbj",161103,"5","","000000740001020076855A1782722742671623C2783EF30D82AA1B30293E25955F3F23F20125F232AA160D5009B4BE6ECBE7E478F7CF66902E3ED296B6201940CBDED0F4363BCBD2F44C31930F36314C8046E0D22045A1F4409650FDB38BCF900A630A736EBF90085915EEE7082F602B1285CB6B53484584"));  // qq: 378234249
//      lanDaCustomers.add(new Customer("a82032431",140529,"4","000000760001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696C2366A502A5D70245D7FE3F23F20125F232AA160D5009B4D46ECBE7E478F7CF66902E3ED293313B293E963E934C36360F404C80E0B67AEEB6314C0FF4025858F2D293F4980845E0E015806D9860B112853B791FA50510"));  // qq:745633154  
//      lanDaCustomers.add(new Customer("t705833039",140701,"24","0000007D0001020076855A179AF41A9AF4A5A285C04BDA65A62A8D464033B808980235205257E83F7F3CF12A16E5A516D60E68BBD0580262D8EDE4125A766647374AC86B14DE517DAA1728F2E9F5B55590F7941BD3F0F01DF3D564EE5396E51646E15704CDEF44E57D197521CF33BC0EDCA0BD73FC21A12A469BAEE5B4941A5174"));  // qq: 705833039
//      lanDaCustomers.add(new Customer("18772270777",140615,"24","0000007E0001020076855A179A8E2AEBF420A285C6E37E61B54E005ED3BA243093D87845518C9A9F7B1911B76264BB7CA828101E5E272EE9F8F2628B9F7F47AD9C04DDFB649FA06EDB3A58C9D939184DEA99B087A3FB43A7F0D360AE132F829C2D9491CB96D677157F71942CE492908E7926A867FFDB10C08ACCE9C8A55B2010F3A4"));  // qq : 1287003238
//      lanDaCustomers.add(new Customer("jizaman",140716,"8","000000730001020076855A1722B02AEB37A5A285C6B9CF9FEBEB6B98692C13AC780A1B1A2E8F66D58F7A69DD8EDF36FE587DC62D5A1C7A7DD0087F2A90EC23412269726A2548ED5F6FB672BFCD9FA886B3071F791E265FFA4C824A9319BA63B8728C352F0018723779EF2768BC707D191DA73E94EBE87D"));  // qq : 52160761
//      lanDaCustomers.add(new Customer("soledad1",140715,"4","000000740001020076855A1782722742671623C2783EF30D6C29C2AAA61BA616454223FA90468F4283F5FE3FC216AA252946F28FD128668045E7021645B48FF7B425E780B4AE571B7E29F29A7EF2561160163535F0B15D326F28BC2502BCBC312D9863056395116372E705E19E1F110D4185197853835685"));  // qq : 745633154
//      lanDaCustomers.add(new Customer("nuo006",140718,"9","000000730001020076855A1782722742671623C2783EF30D823EF33E59A5A58223FA90468F4283F5FE3FC216AA252946F28FD12880B480CAA559A6DEE77F7FA5E702B4E76716024524B4A60259161645B4DE25E78C3F9095AA3E78B636AA69695F3F23F20125F232AA160D5009B435128528E31F68AF44"));  // qq : 635765938
//      lanDaCustomers.add(new Customer("xiaoniunjj",140722,"9","000000750001020076855A1782722742671623C2783EF30D6CFA0F1636AADF293EDF957223FA90468F4283F5FE3FC216AA252946F28FD1286616D767418328DFCC975DD88025056C6CAE35CC66FE71057135976925E5B98DC48B553B55F03655DDD7778D724C8BD54C8B6436FEF6C961804BC8FB73943B8A4C"));  // qq: 745427754
//      lanDaCustomers.add(new Customer("tr130804063815",140801,"1","0000007D0001020076855A1722B045EBF4A57E8537227B7EA7C6EF65A5D51945C7400A2620F33190AEA62EA849626ABB928E33A5106C8AB97040A7A1E542C82C96CD0DA8CEAE72729D347CB3BD1304B0C7A3FB6E541B041BE4DAF05E4F37E876E89E430D765FE3D4843377A7F2A78494F15454DD958A59535645EA91F694D5C139"));  // qq : 1484427886
//      lanDaCustomers.add(new Customer("w297320615",140729,"24","000000760001020076855A1722B025EBC320A2859393BB8C79442A0E2F954795D5D945E58A1CB1E3BA4C848EE55A02B27DA49D8B28C967B1156786D6EF94335B77555D0D3E2D31729C7F04FE645E5C68B305AE4854FD38B3C87F5C3DF8D2C61D0EFEF3C558262D6CEA2BF293124A329979810AE41E391F06B7DE"));  // qq : 297320615
//      lanDaCustomers.add(new Customer("lv57454565",140802,"13","000000760001020076855A1782722742671623C2783EF30D6C3E46E7DE5945DE45DEE7C523FA90468F4283F5FE3FC216AA252946F28FD12816B380021B1B806780678F8016E745021680B4456723E7A5247F7F85068F23123F9095AA3E78B636AA69695F3F23F20125F232AA160D5009B426128562F453FF2B7A"));  // qq: 57454565
//      lanDaCustomers.add(new Customer("xy2000lovely71",140905,"13","0000007B0001020076855A1782722742671623C2783EF30D35FA438002A5A50FB678253E5B80594223FA90468F4283F5FE3FC216AA252946F28FD128808002A52416A64516451B02D516CA7F801BDE02DED716E7DE7F80A52523453AA23F9095AA3E78B636AA69695F3F23F20125F232AA160D5009B4261285B1AB947007F1"));  // qq : 396151201
//      lanDaCustomers.add(new Customer("tkiiifreddy",141101,"41","000000780001020076855A1782722742671623C2783EF30D6C78B6C2363616F38F1BA65B4223FA90468F4283F5FE3FC216AA252946F28FD12816E7F71645A51B80A58FD780238F1B45CA1602238F024559D724A580A54502A6123F9095AA3E78B636AA69695F3F23F20125F232AA160D50090F3512850ED1205931F1"));  // qq : 1106024520
//      lanDaCustomers.add(new Customer("57454565",141203,"13","000000750001020076855A1782722742671623C2783EF30D6C80DE5945DE45DEE7C523FA90468F4283F5FE3FC216AA252946F28FD12880A525458F1B1BF7B4D7A5B4D71B803AB4D72325DE80A51B23802345D7D725A68C3F9095AA3E78B636AA69695F3F23F20125F232AA160D5009B426128532B11FC58D16"));  // qq: 57454565
//      lanDaCustomers.add(new Customer("18710781550",150316,"41","271846345","000000780001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696C5966A55980A524A580DEDEA23F23F20125F232AA160D50090F056ECBE7E478F7CF66902E3E45192919C629A1C67ACBD27A31EE3E93C6EED220D04029D24CF43B31293EF4EE600845E0E015806D9860B11285A2FD1F3906CF"));   
      lanDaCustomers.add(new Customer("wxzy0117",160318,"4","667193","000000750001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696C296D43F880A58080653F23F20125F232AA160D5009B4D46ECBE7E478F7CF66902E3ED280D2400F9046583EF44C3129199393EED0904545B63B31A5583E3B0F7AC6EE2E0845E0E015806D9860B11285A4A55305AD49"));  
      lanDaCustomers.add(new Customer("a7242622",160201,"1","313756795","000000750001020076855A17827227FE3F9095AA3E78B636AA696C168059024502E70272671623C2783EF30D695F3FC216AA252946F28FD12816DE8FE7230259CAB4458F028FA6258F1645D766A545A502D7A580DE8FA666168C3F23F20125F232AA160D5009B40AFDB38BCF900A630A0A1285B7DA1FF6060D"));   
//      lanDaCustomers.add(new Customer("lkf275899",150401,"5","267129628","000000750001020076855A17827227FE3F9095AA3E78B636AA696CC2B623E70259B37F6642671623C2783EF30D695F3FC216AA252946F28FD1288002A68FB3B4B3A58F02671BD7B4A68045B4D58025D780A6CA164559AE07255F3F23F20125F232AA160D5009B42EFDB38BCF900A630A0A12855C4453ED4D22"));  
//      lanDaCustomers.add(new Customer("lzy19840316",150123,"3","125410489","000000780001020076855A17827227FE3F9095AA3E78B636AA696CAA5BF8806666A545A5D78082671623C2783EF30D695F3FC216AA252946F28FD128808059E7A51B1B2523E7A616DE67A61BB380026716D5B4A6A6D7A54525D702236C3F23F20125F232AA160D5009B450FDB38BCF900A630A0A12850D7994A010B8"));  
//      lanDaCustomers.add(new Customer("15063426",150410,"2","313756795","000000740001020076855A1782722742671623C2783EF30D6C8080DEA5E7D745026C3F23F20125F232AA160D5009B4A66ECBE7E478F7CF66902E3ED236363B80E0DEB680D24C4CF4963E7AA5B67A4C9380F2DEB6BB900DAF4A0AF4B8137311CC0FA10F90CDC0CC14E67CF05014F18E7A47850FF01F856399"));  
//      lanDaCustomers.add(new Customer("zyl31008",150313,"4","2461957334","000000750001020076855A17827227FE3F9095AA3E78B636AA696C30F83645D780A57F4E671623C2783EF30D695F3FC216AA252946F28FD128808F8016E7F7A5028025A6803AA502D7D7B4CAA55902E7161645A5D5B402D7E76C3F23F20125F232AA160D5009B4B1FDB38BCF900A630A0A1285AB551FE8B8DC"));         
//      lanDaCustomers.add(new Customer("zyl31008",150313,"42","2461957334","000000750001020076855A17827227FE3F9095AA3E78B636AA696C30F83645D780A57F4E671623C2783EF30D695F3FC216AA252946F28FD128808F8016E7F7A5028025A6803AA502D7D7B4CAA55902E7161645A5D5B402D7E76C3F23F20125F232AA160D50090F63FDB38BCF900A630A0A128533841F4ACE50"));        
//      lanDaCustomers.add(new Customer("sephirothgao",150514,"22","2510907595","000000780001020076855A1722B0F79AC3A5A285374BDA65C0C06E835D6C7EAD8F772B53BCC865ECD8706CD1107E35B6576266959C1678F097FB7022AA3C45B6F7AEA9A23C4FFBEDD18F17E70FA14326B660D71EB78C81AE4E4EE43659656B4AC39B3F415A10C455BA88CFFADCDC9E8C08F4939075DCB2312023558E"));        
//      lanDaCustomers.add(new Customer("a2437233018",150426,"41","289870743","0000007E0001020076855A1722B02AEBF420A285C6E37E61B54E005E5AABD5404F0353FFB17B5D5CEBE8715BECDE4D8E26E0E7569530FA9907AAB3CE3AAE85680D79483AE3D212C2B746494C21EDFFA55188F69370F6D32EDD5F113742503C08E32BA2E12D7A5DD8A9631911748AADAAEC646B338ED3F9F27E8F5B08C6A794B85EA0"));        
//      lanDaCustomers.add(new Customer("935874381",150420,"42","935874381","000000760001020076855A17827227FE3F9095AA3E78B636AA696CCA80D7B3A55945CAA542671623C2783EF30D695F3FC216AA252946F28FD128807F6616A6F780CAA525E7598F234545DE25A666B445804545E702E766B4DEE76C3F23F20125F232AA160D50090F63FDB38BCF900A630A0A1285B6531FD47341"));        
      lanDaCustomers.add(new Customer("221316081",160126,"40","365662496","000000760001020076855A17827227FE3F9095AA3E78B636AA696CD7020280D780E77FA542671623C2783EF30D695F3FC216AA252946F28FD12880DE2380A5A68F8F2345B366B4A6024525DEB3B4D7DEA64523D5161BD7A52502FE3F23F20125F232AA160D50090FC3FDB38BCF900A630A0A1285B2091FC10CAE"));        

      
//    lanDaCustomers.add(new Customer("caonimei09",140628,"1035","000000750001020076855A1722B0B09AC3A57E853722BEC9C6C3AC4CE40C7320DD50CF0203B15520BA77B987E52736401A7945F220955063FF3CB49E6064E739C3D3ACE9C5D37994ED9F6D4B955BF5BD66E3CF48F7D837716A6CCFBB4416FC2F939B7104331ED5A60DB6CA07CE010CB3459E1D219694C252A1"));  // qq: 574144524 
//    lanDaCustomers.add(new Customer("1960523772",140720,"1050","000000760001020076855A1722B02AEBF420A285C6EFBBEBA7B0AEF908F5A67B3DFF2BFB0965E52AA55107556008BCF2C2163A8974FF803D613221F6F264E836128F99689889228C8E162BEE5A00E75F9BD89A1A643D78F398CCC6A78B68D251C5B7708A81756B19ACA73E17FB4D7189028E55C8D46E94900ED4"));  // qq : 2381993232
//    lanDaCustomers.add(new Customer("ztrjlve4",140630,"1002","000000740001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696C30014695954625DE123F23F20125F232AA160D50EAAA2B0845E0E015806D9860D46ECBE7E478F7CF66902E3ED24040CBD27A93803E80F2B636964546D24CEEA136293E0F9019292290F0837A4785130294B85476"));  //qq : 574144524
//    lanDaCustomers.add(new Customer("18622613059",140723,"1028","0000007F0001020076855A179AE525EBF420A28593EF802917B15D038D27BB37F9D570A545A0A81CB1E3A84CD3D7FC363ACE25FCCB2B2B03C20DFEE8216EE45178FA95E776BB41C7D3088B394AEA226DD77259EC21428DBC215BFADD07CEFD35CD762726454B6145AADB6D8C44B3DB4016D2C97FB5FE110774A757A208653A20AFD733"));  // qq : 1500244647
//    lanDaCustomers.add(new Customer("18202818026",140710,"1052","0000007B0001020076855A1722B02AEBF420A285C6E37E61B54E00AC5A33220DC5CE257E40608D2080ABFAEC59DC3624CBE73E9EB9071F745E74B585B0AE92C63CE376944927DC43CCB6BB24F791FB81C7E088EA06CAB504D4090D74259927966E5FFE360AF6560A058385301D36DA79BE8E0B4AA43353369484BD949FB776"));  // qq : 290607121
//    lanDaCustomers.add(new Customer("ch18261526195",140718,"1031","000000780001020076855A17827227FE3FC216AA252946F28FD128661623022525CA1602B3B43AA567D5A580DEDE1BE7B466808FD7458045E759168C3F9095AA3E78B636AA693523C2A566A502E780228325058D724C8BD54C8B6436FEF6A37072A36C3535BC25BC8F31316E05F38F325F39BB4B4BC8861A9441FD1B"));  // qq : 47446851
//    lanDaCustomers.add(new Customer("1960523772",140808,"1047","000000760001020076855A17827227FE3FC216AA252946F28FD128163AA5D7162523A645E78F3A16D5B42380E7DE8FA61BA66616D76723DED716DE5F3F9095AA3E78B636AA696CDE6680E7A5DE02D759597223FA90468F4283415C2E13B9F613FE1450F402C1924DEA8357578886922CA4A43C85597D945841C1"));  // qq : 2381993232
//    lanDaCustomers.add(new Customer("zzg034",140801,"1039","000000730001020076855A1782722742671623C2783EF30D82FAF88F59A5D7123F9095AA3E78B636AA69697223FA90468F4283F5FE3FC216AA252946F28FD12880A53AA5DE452545D7DE253A1616A6D7DE1616B3B4DE2380A6D5A5B4F7B4A602E23F23F20125F232AA160D50EA78B81285D83B53CBD2F6"));  // qq : 40118114
//    lanDaCustomers.add(new Customer("1960523772",140808,"1050","000000760001020076855A1722B02AEBF420A285C6EFBBEBA7B0AEF908F5A67B3DFF2BFB0965E52AA55107556008BCF2C2163A8974FF803D613221F6F264E836128F99689889228C8E162BEE5A00E75F9BD89A1A643D78F398CCC6A78B68D251C5B7708A81756B19ACA73E17FB4D7189028E55C8D46E94900ED4"));  // qq : 2381993232
//    lanDaCustomers.add(new Customer("fs320282",140807,"1034","0000007C0001020076855A179AF445EBE5207E8537DABE7EF5CEC6E779318EA6E1BCF032417D8390B9EB5BE9E6DC296886A9BDD68209D224CE2142DF70CD6B9253DE9B71A13F2941D836DDD49C6CC0F6E23CB120EA89564B9FA3ED1BC22D0B17CAAD4CAF6841A247A52076AE22771C2C5525AB3A9B40F8A99E9FC4559466AA97"));  // qq : 1134147818
//    lanDaCustomers.add(new Customer("wslongjun2",140811,"1061","0000007E0001020076855A17E2DF45EBF420FED64EE37E71FDCB440C2414E9BFA50EF09EE2D2A5479A758EA13A5AE7AA4064A77C62636C61C0CA3B4219857FAE800044C84B7D9A5B75F618865BF5C9C00B306DE18AEDFD88EA144D9951C52347B3A8AC9ECAD0906DC11B2A2F3A99E5E082E4FB53E4FD9F7E56A5631F060994870482"));  //qq : 394177622
//    lanDaCustomers.add(new Customer("248529924",140824,"1031","0000007B0001020076855A179ADF45EBF4A57E8537D4E84B40007B68E3093B77F995250162A9C0D65FF5117A68644B6800ABAA9A27A0F6517588433138F0D57A410027C291D436A2CB82BA6E6C5A844AEF095E579E14977C8B0B19671454DB4D66335178FA175D9664B6AEBA41CD9E8546B82B391FB351188859EA2086A33C"));  // qq: 1290409039
//    lanDaCustomers.add(new Customer("18388408090",140901,"1061","0000007C0001020076855A1722B0FDEB76DAA28593EFBB0CCA7F4EA2CAE7553D8F6783A2BA71DA87E19D315A8C689EFBA067F2E868DD92DD1EB33D99152FFC06D875391DF626A2A07AA099650CD212A42791DA0D669841B94A8D703DA62C7B62D214FF9DFC42B685E1AECEC64B1DD2747F4416E070211B5B7A947C0E94F5A8DD"));  //qq :  644822140
//    lanDaCustomers.add(new Customer("huanle2b",140902,"1023","0000007E0001020076855A1722B0E4EBF420BB8593E37E71FDCB44563FB22F373D740CAC718D7D0DA25C8F6024EC2784A1701C7350FAD05EA7398CE412856C203CF058BA96B504363FA7F2A70AB7A3521A2F6FF9F27E8F50E40825CEAC6CE47C21515F33B49C099DC85DE92EF031BB5A2A4938587AE12F409BA66BC85C8120E4975F"));  // qq: 1655799714
//    lanDaCustomers.add(new Customer("18759257037",140905,"1056","0000007C0001020076855A1722B025EBF420A28593E37E63AD8DC471EDFD7E15891935BB324C7FA21046DE216059BC29995956E8C73BFCFA2634C59885D62A51A5A2D3335A6A809E3A283D98C0749E679EACC992151505A7C5BE6908CBDFE0A156B417507FC47C7613EE8BA1FA479D0AAC6EA1CC17731A914908D0E5945FC55F"));  // qq : 610615561
//    lanDaCustomers.add(new Customer("6887580",140910,"1026","0000007E0001020076855A1722B02AEBF420A285C6EFBBEBD34E00185AFBCA9A75AC158DA1297FBBEBC419118268555068898E1695819BD24A3DF4E932227626988411560F7B5A29662AEB451EAFF219812968F6743E8A5454D7EC18474B750425E93A2F17E69920C6564E273144C837CDCD44DC16469BD89AC74C94E5AFDAE47F38"));  // qq : 290607121      
//    lanDaCustomers.add(new Customer("q310195003",140925,"1039","000000770001020076855A1782722742671623C2783EF30D6CF280D780A56680DEA5A59E23FA90468F4283F5FE3FC216AA252946F28FD12866161645161B3A8080025959A61B16DE80A545CAA5591BB3A5E7238FF7A5D7235F3F23F20125F232AA160D50EA784F6EBF90085915EEE7082F602B128539EB53902EE9"));  // qq: 310195003
//    lanDaCustomers.add(new Customer("258823075",141001,"1056","0000007C0001020076855A1722B02AEBF420A285C6E37E61B54E005E5A51AD5D72D226BB98A144A236F610DC8E14A38C18A8FB5024D35E94B359FFAD854847ADC8D5499DC976A50BFFCACBD70DFFD822E820AC5BFE989C6D999872946527E6830CCD27FC617AA65B05165B68C7CEC3FBFB4D2970102FB2B21EDC7A4020F4E5EF"));  // qq: 347518898
//    lanDaCustomers.add(new Customer("hexiyu30",140926,"1013","000000750001020076855A17827227FE3F9095AA3E78B636AA696C36B4FA0F3029DED74E671623C2783EF30D695F3F23F20125F232AA160D50EAAA5E6ECBE7E478F7CF66902E3E45B631A5194C930F4C96DEB6404040934C364093A53E364C4CA5D080A1CBA1932DFDB38BCF900A630A0A128583BA534DC612"));  // qq: 574144524
//    lanDaCustomers.add(new Customer("13136253316",140927,"1031","0000007D0001020076855A17E2A1F79AE520A28537DAFBFEAD0AC4454055AED51650A630BD6BE1FE1C478290ABF53A365FC510A4D9DEB61A595C2E02AA342F1728F2E9AFC89C22ED479D8D15199F68C29165065E6C6BA232BBFFC4602698120FD9FE26E8E12D4BE59C3646A758C73EAB6AA31E59831E5EF90EC0086F15DA809B89"));  //qq: 1290409039
//    lanDaCustomers.add(new Customer("270080499",140918,"1053","000000750001020076855A1722B02AEBF420A285C6E37E61B54E002F4E79CAC5CE257E1A879A9FB087B0D3596B50688927BB3E5802629210A9A574155A7EB0C78B7CD21823BD826233F67C58CA1E83940DB864882223FE0A850FAA7AA5586E8D01495D392F9D09C7398C745CD1685C9F69369420AC531B5C25"));  // qq : 270080499
//    lanDaCustomers.add(new Customer("chenjin02",141004,"1059","000000760001020076855A1782722742671623C2783EF30D6C23C2B43EDF9536E7A55F3FC216AA252946F28FD12880A52316252323E716CA6616D78FDEE702A5D7B3A525802525451BD72523D702123F9095AA3E78B636AA69695F3F23F20125F232AA160D50EA6D10FDB38BCF900A630A0A12858F01940F7292"));  // qq : 3396244
//    lanDaCustomers.add(new Customer("51889923",141001,"1059","0000007E0001020076855A1722B045EBF4A57E8537D4F80266AE7192B0278AADB70391415877E84BB4EFE696EE53E5AA5A5D0982F5A855815CAC693B05ABD89B40F8E4E7855A1DB437B4BB47BDC6AF78705C527B1992C4206D2BE444F4DD680B175FC9BAECFE96F2D59C8BB4FD0724C3AB3A99D592DFC764DC4D9E1D322F948B2B62"));  // qq : 627637509
//    lanDaCustomers.add(new Customer("13148886693",141012,"1061","0000007e0001020076855a1722b01a9af4a5a285c04bda65a62a8d454ac2bd688f772ad9f1ba3f7fd863c34a2db13e2db170a8fb4360523a2f17e69196482a93baaecbe1a5bfca6e43214d5c60c33a912a3ff63dbf8afe4f90fe692c00dd5aa73e0c4ad084a32ea39262bc707050bc74a51be8cd90758866da67721dbddb2047ed3e"));  // qq : 379770197            
//    lanDaCustomers.add(new Customer("18755278028",141014,"1006","0000007e0001020076855a1722b045ebf420fe8593e37e71fdcb44563f14c15bc631a10bbb64a2e520bb8f6024ec2784a170a8fb435e68aad78f7d572a85e893cf127ecb66170a7b25e3afac1f6fcfb53bb239b225dbeaea24e0eaba1d35a207782f5c8ed23a3b29dbbde4e5107210910d711938f3a5ef754a9632081d17945b3513"));  // qq : 564631011               
//    lanDaCustomers.add(new Customer("lidianji013",141011,"1051","000000770001020076855A1722B02AEBF420A285C6EFBBEBD393AE9F1500A27D5352F027FBCA27E60C277B1BEAB714A38C18168A875819A6772326BC5EC7C5091CCA2CF9D2AAEBA5936A79521897ED88032BA548D898CB7AACACAC6CA58F9B48E1CA3BD3A9C57AEF65AD24E315114D7589B61743088D70DA8C3E9D"));  // qq: 84548354
//    lanDaCustomers.add(new Customer("11062507",141011,"1006","000000740001020076855A17827227FE3F23F20125F232AA160D50EAAA60FDB38BCF900A6312909C508426E11113CC959E2DF0F68384F5F5F007862783CCF59CF510F0F0278627E3F683AF2D07AF9C4AF077CDC0CC14E67CF05014F1774A4A4AE327F507E3C2142D9C9C7C105EF48EA14785F2FF1F528431"));  // qq: 937415570
//    lanDaCustomers.add(new Customer("13672044053",140924,"1055","876403552","0000007b0001020076855a1722b0fdebf4a57e85c0d4f80266ae71ebb00759460f6967c41182bf7eee0f1b08248d279cb170e9b06ebeed65ff4218706b1f260fcd244004a9416a3ce385419708222fcb0f1980b58e6e2f9d09c7398cab39de158f87e632b8a649b4f579782b6c41e198b6c3c08f59ba4102eacbe7208348e7"));  //qq : 
//    lanDaCustomers.add(new Customer("18105039679",141022,"1031","0000007D0001020076855A1722B02AEBF420A285C6EFBBEBD34E00185AFBAD46B1D8788648581CC3E390DEDC8E14A38C184C96D37394F2C228FEC967418E7FA90A6C76F17EC8E4F7DF51D5B69622FFCCEC3F696DA0A3DCB575A09E498F476B5C6B1918A71BFCA248A27E2A8568761E7D71681E45BD88CA462E64AE894720A6E764"));  //qq: 1134147818
//    lanDaCustomers.add(new Customer("18940990647",141014,"1052","0000007B0001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA69055902A5A5D702A58045A50241403784000A4489270E9283A6867301E6D8FB1332D1F0B10A8611D5F7502AD5E0C00D10D4138F138FF03986F87B2B45C0C70773FB0773A0270CDF557458552BB6B6A375C95887295685B8CD20E2DA7E"));  // qq : 454983006
      lanDaCustomers.add(new Customer("zxpcuole1991",160622,"1013","392844342","0000007F0001020076855A1722B0F79AE520A28537DAD4FEAD0AC44540DDC1E316F9C80271D3F4544996A20D3DD1A468F6FB5759569504404D4053C9219C155290B2FC5AC0959151FFD0840BD25941204125A5AB499D7467B74127AAAAF46B4A8B65F6C63F00D156BB4FC62E86E62B47F48CB6F715578F9B904F0B9B9F8318944E63A5"));   
//      lanDaCustomers.add(new Customer("wujie12",150914,"1034","365078794","000000740001020076855A17827227FE3FC216AA252946F28FD128160202022545CAA5232516D78067E7D78FD78080258FD5808080A58F1645DE02E23F23F20125F232AA160D50EA78A66EBF90085915EEE7082F60159180F3E7D09349FDB38BCF900A630A0A0845E0E015806D9860C312854EF853C0C00A")); 
//    lanDaCustomers.add(new Customer("13217560077",141021,"1034","0000007D0001020076855A1722B02AEBF420A285C6EFBB40669F1DDDE5DF818B93E4E95F71F98E44A236F65CEA82AAA025188EB66EFADD0B061A3221696A2ACE10BBDDE83645BA9268C4DA126F5F4977A3AD1BD84581FAA7D00E86D09247C66CBF9FBA484B2DA56AA023E02421ADFD6E1BD33CE99038F9707C36C81ADB9487F512"));  //qq : 825640201
//    lanDaCustomers.add(new Customer("1730811231@qq.com",141110,"1023","0000007F0001020076855A1722B02AEBF420A285C6E37E61B54E0018D3D529AF7BF199F4D819F6FA8D20BB8F6015EC1899A1501E5E6592625742A89F4144479DB098C829DB65918A924DFA578ACC19B73C28FDF8CE4B822724BBC85831455A13472BD9ACE8565D317DC9D06F29F22E30672726E9F60E67EC556B334C94F6992065B739")); 
//    lanDaCustomers.add(new Customer("cransc",141104,"1044","000000730001020076855a17827227fe3f9095aa3e78b636aa69821b3f8f36f3239e23fa90468f4283f542671623c2783ef30d695f3fc216aa252946f28fd12816e71b80a524a545a6d74559b42523451623a61bd7b43ab425d7ded7d7e73a66fe3f23f20125f232aa160d50ea78611285315253df7e52"));  //qq : 2879283109       
//    lanDaCustomers.add(new Customer("nasen26",141109,"1002","000000730001020076855A1722B02AEBC320A22293E35C8C7944B0D4FE2E79B36586BAA3BB8C1F10C86024EC27E425B27DA4D040CA75C363AF500231DBBC2ED3A658779D2EB6EF6C6F03DC89C19277F1B85B3D7621411666B53C391EA42114F487F779BD93E50D53876A9A1E96B9F375DCB44053F15269"));  //qq : 115020694 
//    lanDaCustomers.add(new Customer("263909176",141101,"1047","0000007a0001020076855a179a0ff79ae520a28537dad4fe6eb48d2cd5b0c16941c777f793bc039076d29f51198ee6d1f64295283550b28b61ce13dfc555b5a64d713ae7c11e2ff6f11e835446b160260f75e3c422492d20e58ea241e7d9847c1714ff4cfc046047933c0dc698d2acea3e36ff68e5dbbcae8c5994eaf8f2"));  // qq :  1421186589 
//    lanDaCustomers.add(new Customer("rye269",141102,"1042","000000730001020076855A17827227FE3FC216AA252946F28FD128160259E71680DEB3A5B380E7D5B4A6D725D780671680B4DE2523DEE7E7D767F74223FA90468F4283F542671623C2783EF30D82F2F816DE023AFE3F9095AA3E78B636AA6982A23F23F20125F232AA160D50EA78631285A9F01FF9C3A9"));  // qq : 825623079
//  lanDaCustomers.add(new Customer("heyottoman",141202,"1002","0000007E0001020076855A1722B02AEBF420A285C6EFBBEBA7B0AEAA6AD55DADB16952123AB1D044A25C8F6015EC1899A1500DB9D8A00E822A463A2574251ABDEDE9DCF6E7F1E12F409BA176CB5DCEC63CBF099D73474ACA347CB3BD1366D774888C81AC6CE46DF6DDC042EF48E5412FA47CB6C9160212F026BE6BC83762201DF1CE"));  // qq : 1205838253
//  lanDaCustomers.add(new Customer("rc861018",141122,"1008","0000007E0001020076855A1722B02AEBF420A285C6E37E61B54E00185AFBCA2226D859EA0D34315CEBE8715BEC59DC3624CBE7569530FA9907AAB3CEE7761647AD76E8412F657E8CC419CF21753328DB88EA24734D21ECBF4C2B693074F9E9E3CA55293B7077E906F5ECF4E7F067B5DA167ACB78EE2CFA2545BF05EAA508208BE44B"));  // qq : 1320579644 
//  lanDaCustomers.add(new Customer("wujie27",150119,"1034","000000710001020076855A1722B0B02C4820FE85C0986AE361EB3F8B27B9F1772CFA68BBCFECD007E6D70BF6FC69DE5B8DB64ABBDADDA3F22DCDA2CE80C6A5CA11A46238236E34E1C8110C754207FD8A2E9E2C6A769C6B132114C5D98E3C2794AEC781D574C0FF69F658F6EF463C08CC1E1FC1DBB5"));  // qq : 290607121
//  lanDaCustomers.add(new Customer("18755278028",141118,"1001","0000007D0001020076855A1722B025EBF420A28593E37E63AD8DC469EDE7361622F6E42B8D65E5E8A5CC5C68B7E6C98CB2E62789BC45CAD8EB392085DA4A40477E8C60CA7ACFB56F30883DE4DF3EBFB3C2D4E105E135820FCCB899714102FECBBF0E70B507D866017828DC0381D9DA4C8452D970ACA662BC242B08CC0394CFDF3E"));  //qq: 564631011  
//    lanDaCustomers.add(new Customer("465770815",150117,"1044","1290409039","000000730001020076855A17E20F250C4820FE8537984473638C9297EC35F2FDA6911671906026D424F927B85810C54C9A0D61F1DF82BBBD06C9628EB2227B7798A72D24CD8323CC46D81A03517B19122641C597676184259FD524EB5634204C3DB9853FDCE734169122BD6F500C7542AEDECE1F04E2FB"));   
//    lanDaCustomers.add(new Customer("392844342@qq.com",150104,"1049","554116882","0000007d0001020076855a1722b01a9af4a5a285c04bda65a62a8dda4a54552b82f0a6938dcdef5d876ecb49c73156fb5a68bbd02b57af74a7e987c89c22d348a571484a123e0517d89b40f84d2d60c53e57f2dfec52746055f9e8c31127f3708fff659d26decbaa24d1f4803e891b98c8e4a3bba5894441fb6bc8abf594ef3d55"));   
//    lanDaCustomers.add(new Customer("1551338818@qq.com",150123,"1003","1016361540","0000007E0001020076855A1722B02AEBF420A285C6EFBB8317C39F848DD1FB3451038E612D33D55CEBC47B4C64A4EA9E519C4BBAE7068A595356890B88FE81510574A69249C0675CEF2DA6812C692D412ACCC397A96D3414B3BD130412C876C41DA582413AD96FBBAAAFD0B59DADDF86706B4F1D9129A9F333813E9490CB20910BF3"));    
//    lanDaCustomers.add(new Customer("dcairui34",141229,"1009","420256738","000000750001020076855A17827227FE3F23F20125F232AA160D50EAAA056EBF90085915EEE7082F1BCB58E0453A4380B73198FDB38BCF900A631243D7E7E478F7CF66902E3ED27A02F2DE3E360F0F9045453E400FC640933BCB4619A545194C4CCBD03BF44C9C0845E0E015806D9860B11285CC1C94BBE3D0"));   
//    lanDaCustomers.add(new Customer("zzlovecc1314",141221,"1049","327102081","0000007D0001020076855A1722B025EBF420A28593EF802917B15D238DAF270CB9F6847A16A3BB8CC41117D3D7FC363A64FCCB2CB3A1AD7AB50F0D64DC4D0633067DB5CD63915C857F4143C889DA20AC9D23EC0460ACDA3F04FF332526C3176F3A44B251CC09410C7DD67708358D9B2705130F58D8C3D6FDFDFEEAFBA394F415E5"));   
//    lanDaCustomers.add(new Customer("851224",150901,"1034","85179617","000000730001020076855A17827227FE3F23F20125F232AA160D50EA78A66ECBE7E478F7CF66902E3ED23B20F2B631F4963E80d22945E03E96B620B6023E407A31F49329E046B63636CCFDB38BCF900A630A736EBF90085915EEE7082F6062F44C930F0F980845E0E015806D986063128547271F612359"));   
//    lanDaCustomers.add(new Customer("asdnaaa",150107,"1041","2294747586","000000720001020076855a1722b02aebc320a22293e37e5dcd6e4e968c2a33b5ee9ec371e58be3d6870ea4689d3ad3e4de4912daccf359e08b534b481e7a9472e41f6b6b25e9426f3ff22e29fd4fb7f3d84f166f4bc42453afb83b87ce2ad2ef60d20d519e227dde80932445cca2721d5a0094cb4238"));   
//    lanDaCustomers.add(new Customer("yhdzz02",141229,"1034","","000000740001020076855a17827227fe3f9095aa3e78b636aa69826d36b45bf802a572671623c2783ef30d695f3fc216aa252946f28fd128808045a68fa60202b48fb380d7e7e723de45590202671bde3a8045801602caa5a23f23f20125f232aa160d50ea782bfdb38bcf900a630a0a1285a3dd535f21e4"));   
//    lanDaCustomers.add(new Customer("yuzhenze123",150122,"1042","876403552","000000770001020076855a1782722742671623c2783ef30d6c6d29fa95b43e7d8fde80028c3f23f20125f232aa160d50ea78256ebf90085915eee7082f602bfdb38bcf900a630a736ecbe7e478f7cf66902e3e453e3b2946b693ddd080b6c6c6a558f258a196f23ef4f4ee46dee0e0220f02184185ec11dad18bda"));                   
//    lanDaCustomers.add(new Customer("ele5201314",141230,"1039","477967848","000000770001020076855A17827227FE3FC216AA252946F28FD1288002DEB416E7A502593A7FA5E7B4F7A559A5D7B4801BA6D7808FD71B1B25028F7223FA90468F4283F5FE3F9095AA3E78B636AA696C1B3EA6DEDE02A580D780123F23F20125F232AA160D50EA78B80845E0E015806D9860C312858D8D53EDFDB0"));   
//    lanDaCustomers.add(new Customer("870956989@qq.com",141220,"1034","745340789","0000007E0001020076855A179ADFFDEB76DAA28593E37EA1AF0DB045871C8DBDF6847A85FDBB0CB036D58EAB5AE7AA8B6DEAB7294C3552D82F17E6DD3C482A93D68CA7A97FC83F4EC317497C7A24795308B280B774449C98C0512124CBAD77C9A15FC3B3C12AC2BF92B83A2B4A2BB9EFD567399C83C2FF6871537A1D3F8120040854")); 
//    lanDaCustomers.add(new Customer("wdd005",141217,"1054","000000710001020076855A17827227FE3FC216AA252946F28FD12866A5DE45A5591B0266667FA5020224B445D7A580A5A522577E1B1129350529A36C3535BC25BC8F315ADF05114802F422D4A339A35850A3C98787F4A15BD4D15BD4EC504629BC542E898A34CCFE58574502027685B4FF3DD5A079"));        
//      lanDaCustomers.add(new Customer("1290409039@qq.com",150901,"1031","1290409039","0000007E0001020076855A179A95F79AE520A28537DAD4FE6EB48D4540DDC1A2040235E147FC12D4200E820FABF53A365F248AE7C35C59F61DDF116FC55251D64D7997D8233FAA5C523F3094466D4DAC85C4C6DA4E7AC21ADEEE7F8485B08264F6B5072166F8685AD741259DF4989742C4E654622D11AB0B62BA6BC82F7A94918403"));   
//      lanDaCustomers.add(new Customer("a727687451",150409,"1048","727687451","0000007D0001020076855A1722B025EBF420A28593E37E63AD8DC4056EE67B1F9875D945A0D6E5E84A90DEFC8EE6C98CB2E62789EC45CAD8EBC95885A55C442297FA12A7074A9EAACE885FE8A7C5FB6BCBDBDF1A3A375DD632D7353C1460CA0DB042AF46ACE5DB43B852E652D8CFBC70245BFA245EA475BEC95FEAB16820D74079"));   
//      lanDaCustomers.add(new Customer("asr6552514",150228,"1045","937415570","000000770001020076855A17827227FE3F9095AA3E78B636AA696C1B293F02E7DEDE02DE80123FC216AA252946F28FD12866B4D74580A6E78045802345233AB445F780B43A80E780E767CA161B23A602A5C5671623C2783EF30D695F3F23F20125F232AA160D50EA7826FDB38BCF900A630A0A1285BBB3535B3EE1"));       
//      lanDaCustomers.add(new Customer("11062507",150301,"1006","937415570","000000740001020076855A17827227FE3F23F20125F232AA160D50EAAA60FDB38BCF900A6312909C508426E11113CC959E2DF0F68384F5F5F007862783CCF59CF510F0F0278627E3F683AF2D07AF9C4AF08E142D9C9C7C105EF4774A4A4AE327F507E33FCDC0CC14E67CF05014F18EA14785EEBC1F52F860"));  
//      lanDaCustomers.add(new Customer("hxb0018",150326,"1001","553075788","000000730001020076855A1722B02AEBC320A285C693BBEB0E6AC6AC8C5DD603997A306E663D413A838268E9E7B27216AB2B537190181ED5C1925D48AD07BDD52E108FF678A9A2C149BD66D7C17411ACE9F530D9359C56A49F13B3F74A7E4A0BE44EABF1CEC3285C2083AC5ED187B1A21D41731F27E362"));   
//      lanDaCustomers.add(new Customer("13774271510",150420,"1041","100318168","0000007E0001020076855A179AA145EBF4A57E8537D4F80266AE71FC00F5F107E8AC92C45F62D44BB4EFE69688E5AA5A5D3B5782F53B3FE95F212EA964A063740774669213B3BD4B85919FEED3C12CAE72B1272426741B6BC0F75E377E5DD9B65D11030D3C372CD2E5F6C9607EDDB61A0B5C60E0589EAD4CAF9FBCAE9C8520CF4304"));     
//      lanDaCustomers.add(new Customer("tt9900184",150308,"1041","253878551","000000750001020076855A17827227FE3F9095AA3E78B636AA696C3F46F76680A5A566A58D671623C2783EF30D695F3FC216AA252946F28FD12866A559A6458F3AA53AA58F8059B4026745A5D72201231B8F4516A6CAB42345FE3F23F20125F232AA160D50EA7835FDB38BCF900A630A0A1285557953C70487"));  
//      lanDaCustomers.add(new Customer("hps0999",151002,"1041","83264150","000000730001020076855A1722B02AEBC320A285C6EF803D6A4E002FE3B36374D3589AF434E0FE0FC46015F9AAA063E7F86C795FC149B245DDDC5E4CDEAFB6B3C3A63FD84B91B35377BC2CA29D62643DC65C41EE44CBE53BD2193146DAED8D553EF29EEE5221B9C34921BC4D945BA658AFC19C1F352E12"));   
//      lanDaCustomers.add(new Customer("2liutxl",151126,"1038","290607121","000000740001020076855A17827227FE3F9095AA3E78B636AA69825995B629F25B0F8D671623C2783EF30D695F3F23F20125F232AA160D50EA781B6ECBE7E478F7CF66902E3ED2903E9329F2B6A5A14CEEA120A17A3BEEA140F49380B680D28045D23B7A40C656FDB38BCF900A630A0A1285693D533A6CD6"));   
      lanDaCustomers.add(new Customer("cy336336",160814,"1051","386216777","0000007D0001020076855A1722B01A9AF4A5A285C04BDA65A62A8D46CE817294465041B83F184276FDD51F7AD7878E2991593E06E8369B90717CE485982293C87232AD2CC20EB7BC3A93CB10CE521CB59E9683CDFFBFBB4BCD0C2CFB2DCBC9548BF940FC4053C9FDEAEAFFF6AA4A8046D1B5347CA94213D3A66BC8CFC820D61B16"));    
//      lanDaCustomers.add(new Customer("15206192140",150801,"1023","1655799714","0000007D0001020076855A179A0F45EBF420FED64EEFBBD6DBCBD3BB3F081D0C8904463D3E25C95CD6B0C007911B689EFB0464FF27C08201A73CA784B786C133D6A3B58EBA1FB814B1529D97AA42913142859412A06F1DAA9F50C6D2F0A58E097D6D1728F2E9E8483CE11800E386576E23DA84C9FB063479D7061D00CE948E919D"));   
//      lanDaCustomers.add(new Customer("qxs004",151101,"1023","34111572","000000730001020076855A17827227FE3F9095AA3E78B636AA6982F23090D7A5A5123FC216AA252946F28FD128808F3A8059231B8F2525A6F7806745E78016A680D580B3803A807FB4E745B4CA4E23FA90468F4283F5FE3F23F20125F232AA160D50EA789C0845E0E015806D98602B12853ECE530C5350"));   
//      lanDaCustomers.add(new Customer("13382312033",150301,"1060","8686380","0000007C0001020076855A1722B02AEBF420A285C6EFBBEBD34E00AC5A4FF67593D87845513BBBEBC47B4C5ACE7C555068898E1695AFC573C9044FD3BECBF5B5EF5AEBCCF60F4293A6560B106C1E0331AF40B24068C588E9DC03A64DD83A7395990E74CA7313D065416A4703854585EF1D3718C239A7A94AD8DC20CA941090B0"));   
//      lanDaCustomers.add(new Customer("25391301.",141204,"1003","","000000760001020076855A1782722742671623C2783EF30D6CD702DECA8080D7A54C8223FA90468F4283F5FE3F9095AA3E78B636AA69695F3FC216AA252946F28FD12880E723801B8F4523F71645453A16E725458FDEA5A6162325CA8002D71BE7D725123F23F20125F232AA160D50EAAA501285A8ED5320C086"));  // qq: 452748143 tuhaolin
      lanDaCustomers.add(new Customer("307852888",160301,"1061","503835829","0000007C0001020076855A1782722742671623C2783EF30D05800202A58045A5DEA5DE800280E702458D23FA90468F4283F5FE3F9095AA3E78B636AA69695F3FC216AA252946F28FD128801BE7E74502D72480C806E745B4252366B48F4545D74580B402D723231B026C3F23F20125F232AA160D50EA6DF61285DA6C536F3A70"));   
      lanDaCustomers.add(new Customer("sbwsbw1017",160112,"1060","321364288","000000740001020076855A1782722742671623C2783EF30D6CF223019F9671AE7128814C32F08B973FBABA8D7236D3917AF0408BB9D7F19735B905B7CCCFB963B9E73CE72869E5EEBCB9AE35EE05283C63FE692059FB6EBF90085915EEE7082F60A66ECBB34329B3904DE7B1D1DC215CBA856C0A94E33B16"));  
//      lanDaCustomers.add(new Customer("15239097599",150827,"1043","975143047","0000007E0001020076855A1722B025EBF420A28593EF802917B15D038D271CAA48256740E3FDBB83981C1182C791FBAA727482EE4CC2A7E5C7C2386AFD7C070B9B4F741ED492DE48D248D62AFD37A493DBBD7889FB0673793D4C841458B5D8F79174AD2C2C685CD208C41B3F11D5E39DA29AF7581064AB513DB1A2EA886A94B716CD"));   
//      lanDaCustomers.add(new Customer("b4920492",150708,"1022","814495462","000000720001020076855A17827227FE3F23F20125F232AA160D50EA7856FDB38BCF900A6312909C508426E11113CC959E4AEF838607F587AF1007508383F550F6F527CC9CF64A870D83E3E384CC2DF0CC77CDC0CC14E67CF05014F1772DF58386F5857A552BB6B6A375C95887CD56852021537ECFAD"));   
//      lanDaCustomers.add(new Customer("18051090946",150820,"1023","814495462","0000007E0001020076855A1722B045EBF420FE8593EFBBD6DBCB447E26AA617CF5D3FE031C76DE7EE8BC256024EC2784A1704CEED39214F203C3639175974BC4B34103915D25E12520E1FFA8501EDF87D392644F6473BCF9521A67C5E7CB01DAD38E6BD0ACA71B92A2C696BB5DC821898E353C7A28165B94C7B458DC1BDF949DAA94"));   
//      lanDaCustomers.add(new Customer("pizishihu",150820,"1023","814495462","0000007D0001020076855A179AB045EBF4A57E8537227B7EA7C6EF187FCBCA6C2792B0493B4CD44BB4F880D4551C14EC8C6EA4BA28F0CC069279E43C85DA5AC45DBF2F3656EAAFC79BFB741EA1533BA8A775A784BD82BD74A49B33E67E8E6EF20832D3215B21D5F1508C4C586490452C1B4697EC05C000B4309E9F1D532061E030"));   
//      lanDaCustomers.add(new Customer("13382312033",150408,"1031","8686380","0000007C0001020076855A1782722742671623C2783EF30D05DE0202A580D780A50280808045A5229605814C32F08B973FBABA8D724C8BD54C8B6436FEF6A37072B163E7B7BC5F258F6311193DB7F0165F601B601129166C35B7485C05169A1B361B165F6C05F26C1B292935B15D326F28BC2502BCBC312DBBC875A994D24636"));   
//      lanDaCustomers.add(new Customer("13983273095",150212,"1009","269839782","0000007f0001020076855a1722b01a9ae520a285c0dad4fead0ac445403d3d9e057936fe128d6c734130e703911bd7df29c968105031387db189c09e7deddb8fb8d2fd49f9f93e76a16563d7bbd1b711c0874cb2ff4cfc641bc8bf762b4eccf6238bd70cfa31e7ff02e40779a0ea70e014c3f47a31de7a312fd7cb18ea5f5994ecb9b2"));     
//      lanDaCustomers.add(new Customer("3061725749@qq.com",150304,"1026","290607121","0000007f0001020076855a179af445ebf420fe8593e37e71fdcb44563f141dfebb211c63008c7ec3e3885bf9c7915018d2915956169143e45178fa6f2cc8fec4b95fc67b978a18c39287c57371b246ec0b972107c118b8f487d51dd2db6993c590e20ef3ca97549770d58408b2ab2d03230de3045ff675a7b3bc6c52c8b77f94a808e1"));         
//      lanDaCustomers.add(new Customer("15921595778",150401,"1009","619363896","0000007d0001020076855a179a36f79af4a5a28537fbe83ff0c496699391b2483311270cf5dcb20f22add3cb49c7d54b3aefa44e1cd216546f305b6d01b5f690ae11b40a2bda9f20831d4403a2024f9894b78ba7d00e86d084a2c63ccb1d12093f8b7191536c73510b24adcc6862802d6b656b49fc5d94cab287dc13c3da6c4bdb"));        
//      lanDaCustomers.add(new Customer("abcdefgsishen",150411,"1021","1028959420","0000007a0001020076855a17827227fe3f23f20125f232aa160d50ea78ccfdb38bcf900a630a0a0845e0e015806d981bdb4546e058def221cb3acb3cde676ebf90085915eee7082f601b6ecbe7e478f7cf66902e3ed29646b6317a4093dd19ee19c6c6c60fc64c3102d24002d231408046a1dde05850128529acdadaa5e6"));    
//      lanDaCustomers.add(new Customer("waywy19721218",150411,"1026","35914051","000000790001020076855a1782722742671623c2783ef30d353f6730296d80668059028002664e23fa90468f4283f5fe3fc216aa252946f28fd128168f2325258fe766b4e74545de4516d785f8e723f77fa525168002d7026666a23f9095aa3e78b636aa69695f3f23f20125f232aa160d50ea7832128534e994159efb"));               
//      lanDaCustomers.add(new Customer("lelouchaicc7",151124,"1001","546232225","000000770001020076855a17827227fe3f9095aa3e78b636aa693536a63eb6781bc2b4361623d7bd23fa90468f4283f5fe3fc216aa252946f28fd12880595902e7678fd7caa5b44545b4b3a522ec11f236f29a9af235f2056c35a36c3535bc25bc8f312d9863056395116372e705e19eb228f54185106e20cd0aa3"));   
//      lanDaCustomers.add(new Customer("15570894814@163.com",151109,"1031","1342822566","0000007D0001020076855A1722B0F79AE520A28537DAD4FEAD0AC44540DDC1BB7150A6294E73971249A503916059DF2939E6277BB56FF80D3DE4DF18EE9310609A3D12CBEE6ACE49C713D82BD6B6B2718FA743714DA248A280009F8AE43F63D48AD084525970FA6AA7E9C7284DAC1D35A206B7AB1A6040F8CC721DFE6820D4029B"));    
//      lanDaCustomers.add(new Customer("15022587538@163.com",150316,"1054","876403552","0000007E0001020076855A179A952AEBF420A285C6E37E61B54E00185A19D1B0F4D2262D9A4F161DE871868268555068D2E0AAA5013ACAE9F8FF5BD47CF285FB37C4486C13BD2C455A3AA6FDCFE4F6A355A353275184B152FC97B2D123C1AE12285624A50A8A9C1FD48C9E4E206173FC21ECBF4CB105F874F9E9CF88DB4920168D95"));  
      lanDaCustomers.add(new Customer("dieinggame39",160112,"1052","974629001","000000780001020076855A1782722742671623C2783EF30D3516B6163E36256767361BDECA4223FA90468F4283F5FE3F9095AA3E78B636AA69695F3FC216AA252946F28FD1288080B40245D7DED76723234524A57F8002B4E74525E77F4102567E7E1B2935B163056395116372E705E19EB2F30D4185AD5C948A8FD1"));  
//      lanDaCustomers.add(new Customer("GALAXYN39006",150319,"1057","2504035768","0000007D0001020076855A179AA145EBF4A57E8537D4E84B1700DA6DE33A51102D0A262059A9C0D6F0951B12911C14EC8C6EC579FC6EF2B25D244FA19250200D7A7ACE86EBCB5D755D11F52CB67869BB33F89B73511EA18477856AAE4E4BA277EE64086E1BD3460EDFDF8DBCFF706F0795B845AC03F78175FB0B88DE4620EF4C8E"));   
//      lanDaCustomers.add(new Customer("18523969183",150915,"1019","1976430674","000000800001020076855A17827227FE3F9095AA3E78B636AA69B7D702A5A5D702A580D77F8080800202CAA502DEF780FE3FC216AA252946F28FD128808F4545B4457FB48F66A5E7D7B38501DE02801623231B2380DEB4AE011B6C3F23F20125F232AA160D50EA782DFDB38BCF900A630A0A0845E0E015806D98602B12858D2094DC74C8"));     
//      lanDaCustomers.add(new Customer("xuguang666",150311,"1038","14647540","000000760001020076855A1722B02AEBF420A285C6E37E61B54E00642F8FF27BAC7C83CA1A5D8D20805D2DDC8E149BF2C27ED8021ACC67865BF5FF080E04EFD828C2B9F678DFFDD29B091CA420F684E69956A080002A4605053693797F888D722CE439C90ED524E65888302847F562C7E8207DDCB0F720BA0DC5"));   
//      lanDaCustomers.add(new Customer("wxcmemory",150327,"1048","564631011","000000740001020076855A1722B0F79AC3A5A222C0FBE83FBFB48DD48B6C2D6E36EE0FDB4D0FB1FFCEA50016353E025D54B3B8897413253061F499610A8447984DC476451CA17413DD76700DA0C8AF05B61135FE38C263E7D5CE62A7931DED8F28BC52681F1EF8F7DE9E1250C590D01328AE08E520014966"));    
//      lanDaCustomers.add(new Customer("87529281",151029,"1030","290607121","000000750001020076855A1782722742671623C2783EF30D6C66A559DED580D5A54223FA90468F4283F5FE3F9095AA3E78B636AA69695F3FC216AA252946F28FD128166680A58F1B250202E7E745241680D71BD7DE674567D71B23CA8067E7A5D57FFE3F23F20125F232AA160D50EA780312852B9B1F2D5295"));   
//      lanDaCustomers.add(new Customer("jj13011615566",151101,"1007","138686081","0000007A0001020076855A17827227FE3F9095AA3E78B636AA6935C2950280D7A58080E780DEDEE76C3FC216AA252946F28FD1281680163A80D7B423D7168059B4CAA5A60245F7A58F3A804502D7B4808023D7A5FE3F23F20125F232AA160D50EAAAB8FDB38BCF900A630A0A0845E0E015806D98602B1285B4C65332423C"));       
//      lanDaCustomers.add(new Customer("303032561@qq.com",150711,"1014","303032561","0000007F0001020076855A179A0F2AEBF420A285C6EFBBEBD34E7BAACED1006066AC084E657E934FE52A4ACA4C64A4EA9E51ED0369D6DE81EE78F074FB671454C7BF09264A4C15DFD88E8847F90B37D38C503CD9BA576367BB5E84050F85DAE78469C2A7434F67EBC6FD7FFC76CDE3BA614D31D13BCFE45178FA07D888D5C320D0FD7D"));   
//      lanDaCustomers.add(new Customer("guiyuan043",150813,"1014","1058892329","000000770001020076855A1782722742671623C2783EF30D6C1B783E30291B36E7A5458C3FC216AA252946F28FD128807F16A680A5D75925DEE72545A64502DE24B4D7E7E7B480A5258FA623807F66FE3F9095AA3E78B636AA69695F3F23F20125F232AA160D50EAAA2FFDB38BCF900A630A0A12858FB794868065"));   
//      lanDaCustomers.add(new Customer("18222722386",150320,"1028","876403552","0000007E0001020076855A1722B02AEBF420A285C6EFBBEBA7B0AE54A5192241E0E91D6C3A7F4ACB479A751BEA82AAA063E7B7294C105284523A6AF9527436A3BBB877DA4C40AB8A6040F891CDC8370B37C3488C76FC93E07F188F5BC7A9B45B9D6F1E80CA84C533BB8FF6CDA54D279E4EED4C7ED692BB912F3729DC60B82075CDCB"));   
//      lanDaCustomers.add(new Customer("a315815777",150221,"1047","315815777","0000007D0001020076855A179A0F1A9AF4A5A285C0FB32CDEE22B1F1BF39BC658F772A6128BA3F6ADAF12A87E52736766DA0BC8B8C882F233A119F95FBB5D7D3D276CB5DCEA23D4E46419FB6D9BC046FEF7375F9B6879BFC4A04B89B58E9BBAFAD9AB42C328A4EF936EFDAA1EA28B083D7817881627923146D43945BA220132B48"));     
      lanDaCustomers.add(new Customer("chaoerfu",160110,"1046","1134147818","000000740001020076855a1722b02aebc320a285c693bbeb0ea51c26850144d3583e8f5edf2a9d3093d3596b5068f916ab2b5383cf10633d72a447c97d068f741c114bc55fd98af19a30ca43b0314e7e5a6c27e722158bbc7ddb50eb012bd5a0ed64f27efaa206e8dc2008b77da333fb55c8c1fc534ea522"));   
//      lanDaCustomers.add(new Customer("lourongwen83",150224,"1017","321364288","0000007F0001020076855A1722B02AEBF420A285C6E37E61B54E0018D3D51ABECCD8676A222700A144A25C8FA4EA82AAA063E7E5433640061AB5CD638447E377C64B3D84B0C848D444273E6DF4FF0B8B5412782C3151ECC4B619C6FE68EEEC3242EEA14E3E01C726B536EC646BE4BAA3D38EC311D9EB7BAF7538C3E9AE5850DA5EF520"));   
//      lanDaCustomers.add(new Customer("15923787520",150325,"1046","2504035768","0000007D0001020076855A1722B0F79AE520A28537DAD4FEAD0AC4453BDD1D47050EF0CE9A67971249A503D56059DF2939E6277B036F3080C2C2575D88A9B071562D479A04CC45C533BF3A9177E6D50E70B507D866016541A547174A376DB00738C428064EDC8DDC90F9AD6B0636B2D2865F722345CA3AEB46A194B40720B0E900"));   
//      lanDaCustomers.add(new Customer("jhz521",150215,"1021","394010639","000000720001020076855A17827227FE3FC216AA252946F28FD12816E702D580A51BD7B48F80A5D745162323B3A524A5A6AED5356016296C0516985D326F28BC2502BCBC315A3A02F816607EB163056395116372E705E19EB25DC523FA90468F4283F542671623C2783EF30D69694185D8911F43D538"));   
//      lanDaCustomers.add(new Customer("zzhh1014",150215,"1057","394010639","000000750001020076855A1782722742671623C2783EF30D6C30F8950FA580A5808D23FA90468F4283F5FE3FC216AA252946F28FD1281625D7E76725D5B4F716DE1BF780594525D7E7A5A61BA6236680678F02668002E23F9095AA3E78B636AA69695F3F23F20125F232AA160D50EA6DF012853BD89490BFB6"));   
//      lanDaCustomers.add(new Customer("sj212555314",150402,"1001","2273332166","0000007E0001020076855A1722B025EBF420A28593E37E63AD8DC4056EE62AC6D504A636CC2F0DDA80050791605938FBE9FCA50CB357526692132E095D8523AE5C375A96BBC7D2B29098CA623F780725551B5501ECE9089E456BDBB7209817951A11B2E0B6E01335E5F0E773EEE64FF461E6C4BC13785C6BCC21CBC88CD620F0B2D1"));   
      lanDaCustomers.add(new Customer("pihuo110",160203,"1014","30541052","000000750001020076855A1782722742671623C2783EF30D6C290F36903E5980804E23FA90468F4283F5FE3F9095AA3E78B636AA69695F3FC216AA252946F28FD12880D7A523458016D7D71BD72325021BF7B48059597F80D7B4D7A6A63A16026680653F23F20125F232AA160D50EAAA2F1285FCDB53882CE2"));     
//      lanDaCustomers.add(new Customer("13960715582",150411,"1062","1461339130","0000007D0001020076855A1722B0E4EBF420BB8593E37E71FDCB444762549F87FDB5EE1CF48A0DA25C8F60355EEC27E46354A9217964A29584E6995F856CBF222556355866D8FB0BC92EF68F9DD93AA6FF25218A5ADC1FB2924DFAE923463A7518B5C76D8BBD64E13D609002CB2F8D8CD9ADD2E7EB6369E84730DCA75E94B5152E"));    
//      lanDaCustomers.add(new Customer("a315815777",150405,"1040","315815777","0000007D0001020076855A179A0F1A9AF4A5A285C0FBE83F9CC4F469939CC1C0022CDB9CE76EC6BA65D995D7878E296A958A7745D24DBB2EA20E5B2F1C69D1F062CE696EABE0BB16C0DDD62F65C47E46DCE632460EDAFC4968A94DA099AA3C9E3B62D742B676CB5D8E4B44E3C2DACBB44DF959705E94B3595B7A1DE0D120FB02CF"));  
//      lanDaCustomers.add(new Customer("3086474585@qq.com",151228,"1045","577198409","0000007C0001020076855A179AF4FDEB76DAA28593EFBB3233F4C47131F448AECC847A6ADEFE5D27F6DEFC8EE6C98C3E74DF957AFC53E436C30C15A6686D811D051ED283A15C63AE541136BA1A49BDEF40CBE94265EF11C3B913C8E1C08E5CD38378D301ED68A3D3A3AF0BC9670C5D6972A669A43A60C2E052C8CE0A2094F7B4"));  
//      lanDaCustomers.add(new Customer("yingg39",150321,"1006","416605486","0000007F0001020076855A179AF11A9AF4A5A285C0FBE834CCFEFE53C39E55606DCF0D9AEDDC4739C6BA03C33177E6455D91813E5D6C51C9B95C74AA555C30602DC43C46E417DE7444C2A2C4EB494A5F2BF270098170F023F170779221E4AB90FA6F6376930CCFD67873DCAE27A907B614D28627041645CA21EB4DF794FB3894CE251F"));  
//      lanDaCustomers.add(new Customer("feizhui521",150722,"1067","277505912","0000007C0001020076855A179ADF45EB76DAA285C6A29A225EE13BF304AD00D41ED35827228390413A579E882867BCF3DB6B2DA63E54E94ADD3446E910AE1716CCB0F16369254770CEE029122BF0E5022EC747A49B67D44DD4AFDE9470540D88A6EAB57374B718768518C8E4965AE190E3BF6C8A4C62D76673086F8853C13EB7"));  
//      lanDaCustomers.add(new Customer("feizhui521@126.com",150721,"1023","277505912","0000007F0001020076855A1722B02AEBF420A285C6E37E61B54E0018D3D529AF7BF199F4D819F6FA8D20BB8F6015EC1899A1501E5E6592625742A89F4144479DB098C829DB6591D9BCF8C5FB1301FB1436845350AB73D4AABB591AA14790B003B0B7E8B4DDE05F83CDC69274A36695B8112726E9F60E67EC556B334C940FDA20655CC7"));  
//      lanDaCustomers.add(new Customer("13954242125",150415,"1065","228856745","0000007C0001020076855A179ADF45EBF4A57E85374B7B7EA7C6EFAC7F1703734C0A26B97372BF7EEE901B12158D86A0753B88B77E26A269AB38A55EBAED56EECE193C02BCE8B5D0AB3A6040F8E019FADD07BB745E2865F5A6962D0FC551E99FD2CCE73958F14E2B333014D2E541E359A768A70BA6BD541D4E851C6294E8EB62"));    
//      lanDaCustomers.add(new Customer("wxxwanmj",150329,"1031","876403552","000000730001020076855A1722B0730C9820A285C098EF754D96DD2B9705BC19A68366DA7E402D30913EFC87AAD554D060025E3196C551E1AAC2C494743D2C12C6236E797B24984E8957377E9E83C9E1F67F34B2B28323A6AC95DAD433217591FE2F218DF0B10775136F4D3345382FE9AE0CC09411187F"));   
      lanDaCustomers.add(new Customer("13119509072",160511,"1068","1121243278","0000007D0001020076855A1722B025EBF420A28593EF802917B15D038D2727A5EB29745FE7C569E1471C1182C70E806D7274BC8B369284BE8ED2359228B5FEF55D5345394A4F4E1B312CA1B44BDDDBBC81A7D0BA86BE7C2CC65FBF22BA41CAA0BCED183A8ADCA66B7372EDDCA858FFAC26FB5B63989303DB6634EAE33920260FAB"));   
//      lanDaCustomers.add(new Customer("13647899010.",150901,"1021","1743160261","0000007F0001020076855A179A9545EBF420FE8593E37E71FDCB44563FC1BCEA755653F419A264457EC47B4C5AE6FC363A31F9F268BB2EC924E9F8E4628BD95D378CEFA2D2FB839ED1270B9D55297B681BD3F2E06776DC5A86BA1A5A374D6E77AAD2E0BFF5348694862F9CC252544D952424A590A92C459DFAEDFF11EA390994771985"));  
//      lanDaCustomers.add(new Customer("luoyuei",150304,"1049","321364288","000000740001020076855A1782722742671623C2783EF30D82AA463E6D291B3E4223FA90468F4283F5FE3F9095AA3E78B636AA69695F3FC216AA252946F28FD12880A51B8F4567CA801B23D724A58025458F452323F780A68024A567E77F8023E7123F23F20125F232AA160D50EA6DEF1285C1B694E09933"));   
//      lanDaCustomers.add(new Customer("buqu030",151215,"1001","1976430674","000000720001020076855A1722B0F79AB120A28593E38890667FE312976417DB2B008280FEC484E33B8E3E9CA170061CB38C202E2DC9389573F578A491E7191045891CDE17CD47A3CF1F568F74CED62FB8625FEEDCB351366EBB75D6187A238625E0CF0FF389964FB4286A15BDB8319F3A6A532726E2"));         
      lanDaCustomers.add(new Customer("sj539787928",160114,"1049","974629001","0000007D0001020076855A1722B0FD5FF420A28593B08990F0F4C405CF51D77F0A4035CBA44DEBB1BF98CB396059BC29995956E82113FF9D840B0A352285E893D66692A293DAD2BB2FFC5AA2E8C7F7FF831ABD44DCDCFC07DF2FF4FF6873FC80E3E19AA5E94BF26D5E8F862A26302CD64AADE9F4FC6221EDC95FEA9B9D94C63E21"));        
//      lanDaCustomers.add(new Customer("2263433496@qq.com",150327,"1011","2263433496","0000007F0001020076855A1722B045EBF420FE8593E37E71FDCB447E654DA0BF56B8E498006F0BBB981C1182C7915018F974E50F7A9D061AB5CD5D15F346B3F0BF75BE85D6E2234790B555017B9460649C77E8BDB32490C83DC41DD2F063CE56FB624173044C4D4C84E04FEAC1F1B4B2FCF4C631F22925E48FBD65AD761957949E1AC4"));        
//      lanDaCustomers.add(new Customer("116245180",150603,"1068","1042632316","0000007D0001020076855A1722B02AEBF420A285C6E35CEBA7B0AE6DCEC709F23E5704257EF483CB479A758EB26480157236CB5B7D1D7B847538197685124EC44749B47E3802B68A590801D1B5347CB3BD13D3A13DA3FBA008E6CE3EBAD929FEA0F568B78F5988EEADB68FEE613FCB28B474251ABDCBA0EAC3E9AE8BAF2002B098"));        
//      lanDaCustomers.add(new Customer("2385749331@qq.com",150906,"1068","2385749331","0000007C0001020076855A1722B045EBC3A57E8537D43202C693DA6DCE9E2D5C6A0F25F8806EBE1C770F98F815F9B7648F5479FCD36FC75CFD6955A89B4F511EA14F2C85D69837A2EFC8471CA4524FD33C0EAA7D6BB89D54A3BE83FDB93B5FD90C361212C9CD183D146FC6C9E827DAB497442B46D0D880183E94D28094EB049A"));        
//      lanDaCustomers.add(new Customer("248108690",150407,"1068","1970005288","0000007D0001020076855A1722B0B09AF4A57E8537D4F8964C4D5AF50C88CAAB66F935A6EFA61FCC9F2C57057AD7568E013B9BC3897488FEAF613221779EBE494A2C056F2BD4EFD7490997EB829144BB42B7CC7469C23D69E48F3A651D85974853EF47F186B1503B25383ACACD63E4394FD3F20EDF51706BA99E1DDC87208C5C74"));        
//      lanDaCustomers.add(new Customer("77889951",150927,"1036","381884695","000000740001020076855A1722B02AEBF420A285C6E37E61B5E37E9400F62EA7D2262D3ED55CEBC41911B7B2D31899A1500D806583AF51A7B5105F72D537BBD1548FC345AFF26D8FFF05F7704959BA479CFF97F02C72DD3E4DC89FE30E0EFE70D2B5AF21660767AFE26B324D8F4D941587DCD1D41FE38DC7"));        
//      lanDaCustomers.add(new Customer("a435852506",150412,"1051","435852506","000000760001020076855A17827227FE3F9095AA3E78B636AA696C1B8045D7B3A5DE02DEA56C3FC216AA252946F28FD1281680B4B3B42523F780A625025902D7DED702D580A541F02B77D68C279EF65BF4A15BD4D15BD4EC504629BC54E2288A34CCFE585745026FD977D9BD269E2E7F454576856F101F9547E0"));        
//      lanDaCustomers.add(new Customer("redckx900927",150327,"1028","2263433496","000000790001020076855A1782722742671623C2783EF30D35298F1BA6C21E7F80A57F8002653F23F20125F232AA160D50EA78BA6ECBE7E478F7CF66902E3ED2C6A5A1F44C20E019F4EE46F2F23EC63193404C20D2939320DEDE46D27ACBF250FDB38BCF900A630A736EBF90085915EEE7082F602B1285999C20E2F2C0"));        
//      lanDaCustomers.add(new Customer("465770773",150601,"1044","277505912","000000740001020076855A17827227FE3F23F20125F232AA160D50EA7861FDB38BCF900A630A0A0845E0E015806D981B317A364C3B3BC63B3BE16ECBE7E478F7CF66902E3E4519021996D240A5D0963EDDB6400FDDD03B0F90E0A1A5A13B9320D2764905985D326F28BC2502BCBC3131BBC8EEE9533FF4B7"));        
//      lanDaCustomers.add(new Customer("kyu813",150801,"1014","277505912","000000730001020076855A1782722742671623C2783EF30D823E1E29B3A5808C3F9095AA3E78B636AA69695F3F23F20125F232AA160D50EAAA676ECBE7E478F7CF66902E3ED2A5A13BCB19403B4C31CBB6930F4C80F2D2934C0F40313B7AF4930F7A7A40022BFDB38BCF900A630A0A1285BC933DB7F18E"));        
//      lanDaCustomers.add(new Customer("wlx848381664",150905,"1015","290607121","000000790001020076855A1782722742671623C2783EF30D3529AA5B7FA5F7A5CAA580E7E7123F23F20125F232AA160D50EAAA7C6ECBE7E478F7CF66902E3E45E019937A933B40293EDD19F4803EF4DDD029D29045F2D04C4C3693F43BDD3E2DFDB38BCF900A630A736EBF90085915EEE7082F602B1285491F94F397F4"));        
//      lanDaCustomers.add(new Customer("13187211874",150816,"1009","1454234696","0000007C0001020076855A179AB045EB76DAA285C6E37EA14F0DB0E787F6D4B19D405E2DA9657EB1E3B5CB8268555068D25086D7815B6BD2ACA73E8112202064852CC1A0E82F5A57259D5E9D0B2EA7BD741912287CAB7FE6E0156140F556510621D2E1DEABE789C842B4A6E1BF97E3F68544B4A25D943A3B361DCBFBDA1D3F39"));        
//     lanDaCustomers.add(new Customer("18295678405",151001,"1009","365078794","0000007C0001020076855A179AB045EB76DAA285C6EFBB321AF4C4183129630CFA756DA56E1ABB0C8DD5F14BEA82AAA063C2163A897DCE25D334C970A39218C91D3E3545AFD2DC04D8B5271D47A2A2A04898EA4D7BACDD1AB34D1353E63221F50148FE288383901249CB853D6C7AE8D98D524D6C9BFA9A0B3E1DFFDBDA6C2BE8"));            
//     lanDaCustomers.add(new Customer("1572483286@qq.com",150901,"1048","2403137299","0000007C0001020076855A179AB025EBF420A28593E37E63AD8DC4716E1F90B7F44035BB991CB1931686B7AB5AE7AA8B549A3C6CF3B3093875BE885E8535765B2ADC7BD78534872B0397AA5F6D14540E28DA6621AD884BB719B69A562BFA91F838FD58DC0F0E7390D9663F61041894605E494D13FA60D5A53A94856494B6F4F1"));            
//     lanDaCustomers.add(new Customer("15021438368",151101,"1014","303032561","000000800001020076855A1722B0F79AE520A28537DAFB3F04962269CFC9DCF7F7DF29EFE2964EB212D4C6F4CB95D719293A42AFBBD09014346BBFCCFC0BC92EF68F816D4737471CE3453550445813B606710651063C89706B339B0A8419243A8FEA69945536BB4AADC27FE22A0363AE7FA4AF3D9CCDA056C536FA544C94581BDAC897D6"));            
//     lanDaCustomers.add(new Customer("mac198810",160101,"1013","974629001","000000760001020076855A1782722742671623C2783EF30D6CC21B16D766667FA5804E23FA90468F4283F5FE3F9095AA3E78B636AA69695F3FC216AA252946F28FD1288025250280A545DEDEA5678067D71B45678F45D5B41BA6801645A502D78FD7B3FE3F23F20125F232AA160D50EAAA261285937953CD6BA3"));            
//     lanDaCustomers.add(new Customer("15570894814",151210,"1009","1134147818","0000007C0001020076855A179AB045EB76DAA285C6E37EA14F0DB0E787F6D4B19D405E2DA9657EB1E3B5CB8268555068D25086D7FD83810BA4AD2F85C8B09FB464BD6C0E7A35510621D2E1DEABE789C842B4A6E1BF97E3F68544B4A25D943A3B3BF09B2E9B13D152A3929E55538F3545815B6BD2ACA73EAF361D9B5CDA1D9A42"));            
     
     lanDaCustomers.add(new Customer("gaomingqd",160225,"1029","2232063965","0000007E0001020076855A179A3645EBF4A57E8537D4E84B17007BAAE3F73311DE3C1D2A0F891FCC004150FEECA4888E51CE5ADFA921A492A3926EC7A8DC5F695F4738162B65328F00A6FB772D2AE2B1400AD40634100812C6B4483C7623B4BBC2FD268D869B73511EA15292083E6E28526DB5341AC59B6A6B4F33942595DAD97F9E"));
     
     
    }
    return lanDaCustomers;
  }
  
  public Map<String,Server> initLanDaServers(){
    
    if(lanDaServers == null){
      lanDaServers = new HashMap<String,Server>(10);
      
      // ios servers
      lanDaServers.put("1",new Server("112.124.27.168",8200));
      lanDaServers.put("2",new Server("112.124.27.168",8200));
      lanDaServers.put("3",new Server("112.124.27.168",8200));
      lanDaServers.put("4",new Server("112.124.27.168",8200));
      lanDaServers.put("5",new Server("112.124.27.168",8200));
      lanDaServers.put("8",new Server("112.124.27.168",8200));
      lanDaServers.put("9",new Server("112.124.27.168",8200));
      lanDaServers.put("11",new Server("112.124.27.168",8200));
      lanDaServers.put("13",new Server("112.124.41.88",8100));
      lanDaServers.put("22",new Server("112.124.27.168",8100));
      lanDaServers.put("24",new Server("112.124.55.30",8800));
      lanDaServers.put("40",new Server("112.124.27.168",8100));
      lanDaServers.put("41",new Server("112.124.27.168",8300));
      lanDaServers.put("42",new Server("112.124.27.168",8400));
      
      
      // android servers
      lanDaServers.put("1001",new Server("112.124.6.30",8100));
      lanDaServers.put("1002",new Server("112.124.6.30",8100));
      lanDaServers.put("1003",new Server("112.124.6.30",8100));
      lanDaServers.put("1006",new Server("112.124.6.30",8100));
      lanDaServers.put("1007",new Server("112.124.6.30",8100));
      lanDaServers.put("1008",new Server("112.124.6.30",8100));
      lanDaServers.put("1009",new Server("112.124.6.30",8100));
      lanDaServers.put("1011",new Server("112.124.6.30",8100));
      lanDaServers.put("1013",new Server("112.124.6.30",8100));
      lanDaServers.put("1014",new Server("112.124.6.30",8100));
      lanDaServers.put("1015",new Server("112.124.6.30",8100));
      lanDaServers.put("1017",new Server("112.124.6.30",8100));
      lanDaServers.put("1019",new Server("112.124.6.30",8100));
      lanDaServers.put("1021",new Server("112.124.6.30",8400));
      lanDaServers.put("1022",new Server("112.124.6.30",8400));
      lanDaServers.put("1023",new Server("112.124.6.30",8400));
      lanDaServers.put("1026",new Server("112.124.6.30",8400));
      lanDaServers.put("1028",new Server("112.124.6.30",8400));
      lanDaServers.put("1029",new Server("112.124.6.30",8400));
      lanDaServers.put("1030",new Server("112.124.6.30",8400));
      lanDaServers.put("1031",new Server("112.124.6.30",8400));
      lanDaServers.put("1034",new Server("112.124.6.30",8400));
      lanDaServers.put("1035",new Server("112.124.6.30",8400));
      lanDaServers.put("1036",new Server("112.124.6.30",8400));
      lanDaServers.put("1038",new Server("112.124.6.30",8400));
      lanDaServers.put("1039",new Server("112.124.6.30",8400));
      lanDaServers.put("1040",new Server("112.124.6.30",8400));
      lanDaServers.put("1041",new Server("112.124.6.30",8500));
      lanDaServers.put("1042",new Server("112.124.6.30",8500));
      lanDaServers.put("1043",new Server("112.124.6.30",8500));
      lanDaServers.put("1044",new Server("112.124.6.30",8500));
      lanDaServers.put("1045",new Server("112.124.6.30",8500));
      lanDaServers.put("1046",new Server("112.124.6.30",8500));
      lanDaServers.put("1047",new Server("112.124.6.30",8500));
      lanDaServers.put("1048",new Server("112.124.6.30",8500));
      lanDaServers.put("1049",new Server("112.124.6.30",8500));
      lanDaServers.put("1050",new Server("112.124.6.30",8500));
      lanDaServers.put("1051",new Server("112.124.6.30",8500));
      lanDaServers.put("1052",new Server("112.124.6.30",8500));
      lanDaServers.put("1053",new Server("112.124.6.30",8500));
      lanDaServers.put("1054",new Server("112.124.6.30",8500));
      lanDaServers.put("1055",new Server("112.124.6.30",8500));
      lanDaServers.put("1056",new Server("112.124.6.30",8500));
      lanDaServers.put("1057",new Server("112.124.6.30",8500));
      lanDaServers.put("1059",new Server("112.124.6.30",8500));
      lanDaServers.put("1060",new Server("112.124.6.30",8500));
      lanDaServers.put("1061",new Server("112.124.6.30",8500));
      lanDaServers.put("1062",new Server("112.124.6.30",8500));
      lanDaServers.put("1065",new Server("112.124.6.30",8500));
      lanDaServers.put("1067",new Server("112.124.6.30",8500));
      lanDaServers.put("1068",new Server("112.124.6.30",8600));
      
    }
    return lanDaServers;
  }
  
  public void doLanDa(int threadNumber){
    
    if(checkTime(12,30,31) == 0){
      return;
    }
    
    if(threadNumber == 0 || threadNumber == 1000){
      label.setText("LanDaCount : "+landaCount);
      
      if(landaCount > 10000){
        landaCount = 0;
      }else{
        landaCount++;
      }
    }
    
    for(int i = 0; i < groupSize; i++){
      int customerIndex = threadNumber + i;
      if(customerIndex == (lanDaCustomers.size() - 1)){
        System.out.println(" landa customer *****: "+customerIndex);
      }
      
      if(customerIndex < lanDaCustomers.size()){
        Customer cus = (Customer)lanDaCustomers.get(customerIndex);
        Server server = (Server)lanDaServers.get(cus.getReginCode());
        doLanda(server.getIp(),server.getPort(),cus.getLoginStr());
      }
    }
  }
	
  /**
   * ===== ======== ===================================== =================================== ===== ios peien
   */

  public List<Customer> initIOSCustomers(){
    
    if(iosCustomerList == null){
      iosCustomerList = new ArrayList<Customer>(10);
      
//      iosCustomerList.add(new Customer("wp198812918",151213,"109","1974624874","000000780001020076855A17827227FE3F9095AA3E78B636AA696C7878A566667FA580D580664E671623C2783EF30D697223FA90468F4283F5FE3FC216AA252946F28FD128160202D7DE45D7E716CA1680DE238F3AB41BE766A5D759A5B48F2380238F8F02FE3F23F20125F232AA160D196A012612855D4D9453C279"));
      iosCustomerList.add(new Customer("wmeku_",160113,"13","286537632","000000730001020076855A17827227FE3F9095AA3E78B636AA6982F2AA1B3E3F60BD671623C2783EF30D697223FA90468F4283F5FE3FC216AA252946F28FD1281623A6E74516E7E7DEB4E7254567DE8F452580D7021B23A68045B445668059238C3F23F20125F232AA160D196A32261285CEF353CAD0B3"));
      iosCustomerList.add(new Customer("edoteam26",160115,"40","1019821950","000000760001020076855A17827227FE3F9095AA3E78B636AA696C231BB678A61B36DE0282671623C2783EF30D697223FA90468F4283F5FE3FC216AA252946F28FD1281680CAB44559592523E780B3B4B31625027FB480458FD72523DE5925451BE7458C3F23F20125F232AA160D196A8FC31285021994FFFEBB"));          
      iosCustomerList.add(new Customer("jr1103",160117,"83","187135206","000000730001020076855A17827227FE3F9095AA3E78B636AA69823E01028080A59E671623C2783EF30D697223FA90468F4283F5FE3FC216AA252946F28FD12816A6E7D7D7D5A5DE7F16231B02B316238F80D7164502E766B4DEF78002B4028F5F3F23F20125F232AA160D196A012D12850A971FF7B3F8"));        
      iosCustomerList.add(new Customer("gujin34",160127,"78","1821358957","000000740001020076855A17827227FE3F9095AA3E78B636AA698267783E9536E7D78D671623C2783EF30D697223FA90468F4283F5FE3FC216AA252946F28FD128161B80DE59678F161BDED7CA80D57F66B41B250267232525D51625E716DE45A6123F23F20125F232AA160D196A952F12856B5B94CFDB15"));   
//      iosCustomerList.add(new Customer("hhnn385",151224,"91","286537632","000000740001020076855A17827227FE3F9095AA3E78B636AA6982AA0F0FDFE7CAA5C5671623C2783EF30D697223FA90468F4283F5FE3FC216AA252946F28FD1288080020202A523A68080247F1602D7DEA60223028F1BD580164580E71B3AA5DE123F23F20125F232AA160D196A01101285CAD01F4C6430"));   
      iosCustomerList.add(new Customer("ijing19",160117,"93","187135206","000000720001020076855A1722B0250C4820A285C0564473638C92973A1D3FFDB49819BB6CCF45FBED30C2D0460E6D9050CB9081450D7E91F49AE5F816D1B8635EB4DDF38DA6F55D8E7A2B0B7FF9964EC3F0C078A87034D7F2801338C26EFB86087D9DFDEF2023BFA44AF458EA150A6B179C53A1168F"));   
//      iosCustomerList.add(new Customer("alexaiyug",151214,"55","1019821950","000000750001020076855A1722B0B09AC3A57E8537D4E8C9C6D82F2911F587E9D1F095EED6D4073595EE47CA833FA8CE4FAB4B242B818B54BB1038533367BC59262D35A9550AC382FB6223038B3B5CBCE354281BA1D7AF630B7211BE04984D852085455F98847FEAF29825BBDD3EE564FEBC9F66AF94890B10"));     
      iosCustomerList.add(new Customer("jjhyork",160328,"17","99849991","000000740001020076855A17827227FE3F23F20125F232AA160D196A8FC06EBF90085915EEE7082F6008F3F3D8021590E6FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2C629D2EE3E2945B60F367A0FCBF219291920D09302D280B690B6369619A5E02E1285D1EF9429D41A")); // qian 15*3     
      iosCustomerList.add(new Customer("sxx250",160201,"96","1178971731","000000730001020076855A17827227FE3F9095AA3E78B636AA6982F21E43A502DE4E671623C2783EF30D697223FA90468F4283F5FE3FC216AA252946F28FD128168F238FE780B423E7A5D723457F6680CA66B48FA6258016E7A6A602F716D724FE3F23F20125F232AA160D196A01001285F4E7533A8502"));        

      
      //shuijun
//      iosCustomerList.add(new Customer("east26",151121,"48","NAN","000000720001020076855A1722B0730CF4A57E85C00CD894A4E52F92CFE6DF7F14849740EE5688318F102BF16FCD84F4708EB6B13FE98CFFFE539915BB9217843E8F7D888C04CA8135A01689ED81C497A7309E6193DF73D223E2EA8A2D8C8C954BCBC8E8771758926CF5A89BCAC81A08DE9553B6AC32")); // qian 15  
      
      
      if(true){
    	  return iosCustomerList;
      }
      
      // shuijun free
//    iosCustomerList.add(new Customer("humiao2008",140527,"6","","000000750001020076855A179A7A2AEBF420A285C6EF803D95B0AEEC947F5FD3062510A92AC5850BCAE3E93099DF5E31E03687CC4F3AA03A29581B5267AB2F44C5926EF9BC744CC47EC23316FC794E304C2C2B5A661ED91AF65EE54DEE3852E5F15A2CA2C8E48D6CDD770C44B13C6547E811EACCDE53D2D65C"));    
//    iosCustomerList.add(new Customer("bloodx",140527,"42","","000000720001020076855A17827227FE3F23F20125F232AA160D196A8F256EBF90085915EEE7082F605946D9080875C4FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2203E0F29D2C620A19646F23E20192019F402A13D90E3F6830D07F5F5E386B04785713753BA94DA"));    
//    iosCustomerList.add(new Customer("541134756",140527,"42","","000000750001020076855A1722B02AEBC320A285C6EFBBEB0E6AC6780EF0F4965E7C83CA7FBBEBC4FBF7F93AA0266DCE160847A95FEA2B623D1A52C95BC33892511ED4017F13A4FDC3A4CE62BB74DABA7E7B9275B623B8C9F3313F7739CFA5FBC7C6D8679894C7839C275AD85D2B489CEC721DE0B15346DEDC"));    
//    iosCustomerList.add(new Customer("narutoli",140527,"44","","000000750001020076855A17827227FE3F23F20125F232AA160D196A8FAC6EBF90085915EEE7082F1BA578F74366EE08D935FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45E0B6C696E0DEA13140363B364C96B693A5B63B4090F23E4C7A3B0F3B293E409C128526E3949631A2"));    
//    iosCustomerList.add(new Customer("flower999muki",140527,"75","","0000007A0001020076855A17827227FE3F23F20125F232AA160D196A95516EBF90085915EEE7082F1BDBF2D915DDB30F4040022480FD35FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E4546E0B6F4EE197A80B693CBD229D2203E90F2E045D2360F310F3B293E360FA5601285053EDA92DB31"));  
//    iosCustomerList.add(new Customer("ljnkfc",140527,"42","","000000730001020076855A17827227FE3F23F20125F232AA160D196A8F256EBF90085915EEE7082F6059D9F378FDF250FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45D080D293403196B636C6400F7A0F7ADD58A17A20E03E8045F2193B7ADD3E409C128559D953B4557A"));  
//    iosCustomerList.add(new Customer("fbigkgm",140527,"30","","000000740001020076855A17827227FE3F23F20125F232AA160D196A8F236EBF90085915EEE7082F6008F246E7E4FDE426FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45DEB602B63B7AEEF2194CF493F40F93903EA5D2F43193DDA129E0A1DD58D0800A128592A853D3AE98"));  
//    iosCustomerList.add(new Customer("wsh014",140527,"45","","000000730001020076855A17827227FE3F23F20125F232AA160D196A8F5E6EBF90085915EEE7082F602491CBE5C69398FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45D27A3BEE1929DE46D0EEDED296463E3690A193EE4619C6F4360245A1A5D2DD2B1285770153F65B27"));  
//    iosCustomerList.add(new Customer("saveluodan1",140527,"27","","000000780001020076855A17827227FE3F23F20125F232AA160D196A8F136EBF90085915EEE7082F1B91CBF729DE8A80085845AAEFFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2DDF2A17A7A314C36CBD23640A5B620464646DE3E409329D0DDD240C60F96D049128564B22005CF7D"));  
//    iosCustomerList.add(new Customer("fjhdzh",140527,"5","","000000730001020076855A17827227FE3F9095AA3E78B636AA69821BDF95B45B954E23FA90468F4283F5FE3FC216AA252946F28FD12866A5E7E745D7D702A5DE8FA63A16B380D723DE80457F16CA1645E7DE022502809E671623C2783EF30D695F3F23F20125F232AA160D196A322E1285CF481F0DC151"));  
//    iosCustomerList.add(new Customer("186611",140527,"4","","000000730001020076855A17827227FE3F9095AA3E78B636AA6982DE66A5E7E7804223FA90468F4283F5FE3FC216AA252946F28FD1288045DE8FE7A58FE7B3B4D7F7B42502A6D723DED745CA1645DE1B23458F6666809E671623C2783EF30D695F3F23F20125F232AA160D196A32B112854FF03DAB17BD"));  
//    iosCustomerList.add(new Customer("JM0303",140527,"32","","0000007F0001020076855A179A7A45EBF420FE8593EF807EA74EC622CD3364148CF504A67E6694E52080154C5A29AAD005CEA4920847B36C152B94C9A799BDBEC7CD7CB3C513429A9B985C56AD49475C8EEAB7582925E48F35B817BC3A98ADA5B64BB65A8FD2713246FA17CB7459CBC4C34AB0F009AEFF5C619247ECAE695894952C70"));
//    iosCustomerList.add(new Customer("xxlfhby39",140901,"70","NAN","000000750001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696C1E430FA6DFB4F880CA42671623C2783EF30D69123FC216AA252946F28FD1288045801B3A1680F780453AB4E7252380B48FA6451B80DE025941324B8A75130C658A8C2E758CA118A87F875AC58E47851FE994EFACC7")); 
//    iosCustomerList.add(new Customer("lovefeifei005",140527,"36","NAN","000000780001020076855A17827227FE3F23F20125F232AA160D196A8FD46EBF90085915EEE7082F1BDBD91529DEF2DE6C497E7E163905F38F325F39BB4BA36C3535BC25BC8F31E19863E7B7BC5F258F6311193D35562916163529294056F0609AF07EF036F2569A9A60356C3EF0363E161B115F4BC8D9662057603F"));  
//    iosCustomerList.add(new Customer("Jackysophia",140527,"30","NAN","000000780001020076855A17827227FE3F23F20125F232AA160D196A8F236EBF90085915EEE7082F1B3BF345E01AD5CB15203CE70AFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E453E31901993F402193136C63B4C0F4C7A3BF49393EED002199393930F96451949128504A4944430B7"));  
//    iosCustomerList.add(new Customer("iopnm013",140527,"42","NAN","000000750001020076855A17827227FE3F23F20125F232AA160D196A8F256EBF90085915EEE7082F1BA5E715207862C6932DFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45A13136CB1940803E4040963E29D2A53E3BC6363B31F480E03EEE4546B631C6CC128569C65303972F"));  

      
//      iosCustomerList.add(new Customer("shengxijian5",140527,"1","NAN","000000770001020076855A1722B02A0CF4A5A285378C8FEBA7B07123DC83B04FF03DFF2B7A275BF32D0F772E4DDF09A30C189E22A0655249336616B2946424B35EA417F8FD70614CA7938A9E95633DE56F7E44953817B5DB3B421AAFDE2F337A4F2C2B54DAD48FF747832C56560A5049782A8FC40822F6942EF5C0"));  
      iosCustomerList.add(new Customer("ccc1211211221",140527,"1","NAN","000000760001020076855A17827227FE3F23F20125F232AA160D196A32736EBF90085915EEE7082F1BDBE0E019930FA256567E3905F38F325F39BB4BA36C3535BC25BC8F31E19863E7B7BC5F258F6311193D3E60B716605660299A7E11165F60290505F02929165FF21B40367E5F1B7EB756BBC8CFAC5356ABBE"));  
//      iosCustomerList.add(new Customer("liwenrong",140527,"15","NAN","000000750001020076855A1722B02AEBC320A285C6EFBBEB0E6AC6ACF84860AC7C40296EBBF0AD24FB7F9BFB99DF5ECBB7AD95FECC9495F36D3EC99B9EF3558D105066C9BD08F0A5C71F53BEBFBEF861BBC98AF8A4056FBAC0092A217A42B8B38647B79C30FFE9D21BF9C83FBF090D984806D6D5E394ABB7F4"));    
//      iosCustomerList.add(new Customer("MichaelNO1",140527,"15","NAN","0000007E0001020076855A179A952AEBF420A285C6EF803D17711D91F4A41A5D7553FF2BC8795CEBC41911823AA0266D93E0AACB463A3A91E11ED1A44DB219DD75E41378FA066C132F8C0FD6206A03499D852836391FB338B004C5926908989BB1DD9CB89BC9A986B0EABD487E4981CAE38FEF242BB24F7243CCE9C8711B947310B6"));  
      iosCustomerList.add(new Customer("jy8760125",140527,"26","NAN","000000760001020076855A17827227FE3F23F20125F232AA160D196A8F8F6EBF90085915EEE7082F1BCB7D40F43B36C6930FCCFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45A1F47A36C6DD19363BDD46B631F440407A404C963E963E40C631A5194C9019491285D4B51F2EBEE8"));  
//      iosCustomerList.add(new Customer("tr130725190943",140527,"30","NAN","0000007D0001020076855A1722B045EBF420FE8593EF807EA74EC681FCA7BCC846D8D94547151C2A4A885BECE1E4268A6A21895F07D9DCA2088F53C2B2BEC73BFCFA2634C5564EA266C82F239F47EB7E1025165B1FB3DF2AA7C5BE69082D426B278C7EDA186862BE6C174A6C3FF9F9B0D1D597AD8B68BDCB0CDADC69EE20E013F5"));  
      iosCustomerList.add(new Customer("yzqaaabbbb",140527,"30","NAN","000000740001020076855A17827227FE3F23F20125F232AA160D196A8F236EBF90085915EEE7082F1B66D5F8A54545452C476E05F38F325F39BB4BA36C3535BC25BC8F31E19863E7B7BC5F258F6311193D05369A3516F016F2601B48CCF75BFB8B5B75A613327B3900C9320DF8738B4B3C85407B94B6C9DF"));    
      iosCustomerList.add(new Customer("tianliang173",140527,"31","NAN","000000770001020076855A17827227FE3F23F20125F232AA160D196A8F926EBF90085915EEE7082F1BD5EEE745789F49B79A3E603905F38F325F39BB4BA36C3535BC25BC8F31E19863E7B7BC5F258F6311193D1BF0F06C3E3EF205405605166CF2F0295F6C607E7E606035299A7E3E05B729354BC89A25208BD1D4"));  
      iosCustomerList.add(new Customer("wswzw52077",140527,"35","NAN","000000770001020076855A17827227FE3F23F20125F232AA160D196A8FFB6EBF90085915EEE7082F1B66916E91F83B4C0FC63B9CFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45F246D020D27A3690D23B9390D290F2463E20B67A80B640360F2045B629A140CC1285F7899426CF31"));    
      iosCustomerList.add(new Customer("tr130814130604",140527,"42","NAN","0000007D0001020076855A179A7AE4EBF420FE854EEF807EA74EC646ED7215AAD235BB53DD7EC393C007DDF6BD6FFEE99AB291A5F705EA73152680FF9965F79BB587D86695AA13DBF22E0ABF04C1D0CB689B467A3103DB64EEB56F430D3DB864F2C8906F4A02C6E2738EA2936D46DAE086E7D13A9374D3A5E78E88A04F206BC606"));  
      iosCustomerList.add(new Customer("zxbarbara",140527,"42","NAN","0000007D0001020076855A1722B02AEBF420A285C6EF803D17C39F920D6704760CAC7C83F6AD453D981C11B718A33F7C8D36CB2CB3285244749EA964DC27B3B3E91EE462966D431F07A517C3220B1FE11D81E8DF112F40C5BFB555014C2F8E6F0A966EFD72C7A9B6AC03C8852BAE17FEC3DAE8E7EC19A460E849080BA820E760B5"));    
      iosCustomerList.add(new Customer("pp58923870",140527,"50","NAN","0000007E0001020076855A179A951A9AE520A285C0DAFBFEAD0AC446401F09C8E6023597184D12D4C6F4CB95E150F3057A3E7DFCCB2CB3F1B503A70AE56A6B7B1713FFD0840B0A5E41C3BF2C3D2D059ABFFA217F709B904FEB07AAAAF46B4A73FC9AB2F6CEAA615DF9500FFAB6FBEB46309CEF27FDA16768DD0CDADC834820EB6E0A"));  
//      iosCustomerList.add(new Customer("zwmboss17",140527,"54","NAN","000000750001020076855A17827227FE3F23F20125F232AA160D196A95166EBF90085915EEE7082F1B6EF8DD5946156E31939CFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED236F44002A1CBB64031A5B6293E3629A140360F29E0A14C40314040A2B4BBE776857BC553211F41"));   
      iosCustomerList.add(new Customer("daboss64",140527,"55","NAN","000000740001020076855A17827227FE3F23F20125F232AA160D196A95CD6EBF90085915EEE7082F1BA5584546156E313698FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2364C02F23E368046D036F43B20A1C6DD58B6DD2CB44BCF10CF588ABB2D10457685C98694EE2690"));    
      iosCustomerList.add(new Customer("jy205tat",140527,"19","NAN","0000007C0001020076855A17E27A45EBF4A57E8537D4F80266AE7192B027FDBB0F0F25EFB75CCC0041394672FB91635E20AF6E89570DBE745B4FDF02A099DADDFAE41378FAE9E4C86CE3983C1FEFCBDC82B83628AB077071E1DF218FB2166B2290A8A428F0E72CACC6978A9CE718E8E2A1D4D9FF0935D2E070AEC02094337543"));  
      iosCustomerList.add(new Customer("13822144118",140527,"1","NAN","000000780001020076855A17827227FE3F23F20125F232AA160D196A32736EBF90085915EEE7082F1B3B9331F40F0F937A7A9393C4FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2C6310F3B404CDDD0360219F44040F429A17A31F480199320464619319336202E128571D01F343F04"));  
//      iosCustomerList.add(new Customer("oncity1360",140527,"5","NAN","000000770001020076855A17827227FE3F9095AA3E78B636AA696C3EAA25C2295B8080D7E74E23FA90468F4283F5FE3FC216AA252946F28FD12816DE80805916458FA6D73A66A58FD745023AA5F7B48F1B45B402E725D7DE672342671623C2783EF30D695F3F23F20125F232AA160D196A322E1285F2D394DE5380"));  
      iosCustomerList.add(new Customer("1248248",140527,"5","NAN","000000720001020076855A17827227FE3F9095AA3E78B636AA6982598002F78596814C32F08B973FBABA8D7236D3917AF0408BB9D7AC3AEEDF805DAE256C5DAEC3CCCCF5286C25E5CFF57169693CAE82CFFEC3F5D880DC7A42425540915FD7778D724C8BD54C8B6436FEF69A6DE14BC8685A3DDC3B41"));  
      iosCustomerList.add(new Customer("skyline8",140527,"1","NAN","000000750001020076855A17827227FE3F9095AA3E78B636AA696C29C21E36B63625B34E23FA90468F4283F5FE3FC216AA252946F28FD12880A545D72545D7F780E7E766B4B380A5D7A625F7A50223E7CAA545A5D7B3667F42671623C2783EF30D695F3F23F20125F232AA160D196A320A128582291FB6A9F6"));  
      iosCustomerList.add(new Customer("huihuihuiooo240",140527,"5","NAN","000000770001020076855A17827227FE3F9095AA3E78B636AA6935AA903E98BF5555A03C6CAE814C32F08B973FBABA8D7236D3917AF0408BB9D7F1F53C35B9E73A80E7696C35CF66F56380DF66FE6CC3EE637A4C97E7DF80BC9771DC7A42425540915FD7778D724C8BD54C8B6436FEF69A6DE14BC8807E96A50D86"));  
//      iosCustomerList.add(new Customer("7723903",140527,"71","NAN","000000730001020076855A17827227FE3F9095AA3E78B636AA698259595902CA80A59E23FA90468F4283F5FE3FC216AA252946F28FD128808023D7598F1625DE808016D745A5A5028FE7F7A51B41573E36561B36369A646C3535BC25BC8F312D9863056395116372E705E1D791571841857C873D75194C"));  
//      iosCustomerList.add(new Customer("276462943",140527,"9","NAN","0000007E0001020076855A179A7A2AEBF420A285C6EF80EEC71D535AB159ADF3B903997A307FDE3D98272560AA061AA0B1C20D1C735060C53E57AA5BFD9921FB759907DFB3CEE713618C7738C6C114121ECE23FE693325B7B8196F3AC0ABB19BC7FBEFFF4ECBECE5D425044B76D9EE6E64E022C40A081B4AC08F6B8528F420754FE2"));  
      iosCustomerList.add(new Customer("jiangjie425",140527,"81","NAN","0000007F0001020076855A17827227FE3FC216AA252946F28FD128168F80808FF766B423E759021B0259672523A6E780B416A6E723027FA50216258D671623C2783EF30DB78002A5DE02A580D780A50245A57640BA778E8E3B5BE258D42BCD56373C056395116372E705E1D7910CFE3F9095AA3E78B636AA6969694185960020622855"));  
      iosCustomerList.add(new Customer("a53521985",140527,"81","NAN","0000007F0001020076855A17827227FE3FC216AA252946F28FD128666666B4233AA51B45A53A808F8FB316E7D5A5DEA6DEA61B801B8045DE7F168042671623C2783EF30DB78002A5DE02A580D78041835D3C05712569E728814C32F08B973FBABF0A5BD4D15BD4EC504629A492220C65FEC5BD9E26F59EBDF7F745768572FF204BE10A"));  
//      iosCustomerList.add(new Customer("hhnn128",140527,"81","NAN","000000730001020076855A17827227FE3F23F20125F232AA160D196A01C06EBF90085915EEE7082F60083C3C78AA930FC4FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E4545F23E96A131291902D020193B0219CB19EED2C693C69619403D433B3690B11285346853BC1FCD"));  
      iosCustomerList.add(new Customer("aaaa1049",140527,"33","NAN","000000730001020076855A17827227FE3F23F20125F232AA160D196A8F736EBF90085915EEE7082F1BA512477E9A16293905F38F325F39BB4BA36C3535BC25BC8F31E19863E7B7BC5F258F6311193DB75611F29A365616054011359A6C3EF23E29603E56359A16563E367E6C60059ABBC8BA051FB26773"));  
      iosCustomerList.add(new Customer("taobao062",140527,"35","NAN","000000760001020076855A17827227FE3F23F20125F232AA160D196A8FFB6EBF90085915EEE7082F1B6EEE45084645B2C63649FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2930F3140F4F431364C4C93C6CBF246DEDEDEE019C6EED20F933190D09045A15612850E2753159018"));  
//      iosCustomerList.add(new Customer("radish2007",140527,"71","NAN","000000770001020076855A17827227FE3F9095AA3E78B636AA696CF28F16B629C2A502A5A5BD23FA90468F4283F5FE3FC216AA252946F28FD1281625E767E7D7A545E7D7E7806766A5458067E7D5160245D78FB380252380B48F72671623C2783EF30D695F3F23F20125F232AA160D196A95B8128553DD94F345C1")); 
      
      // self
//      iosCustomerList.add(new Customer("5253649",140726,"53","","0000007D0001020076855A179A36F79AE520A28537DAD4FEAD0AF405CF7415DA3750A6965A9212D4200E820F69C91E1612B28559560713DF7D8E992C38AC6F2D677A09CF584BD3C2D23646C3D8A2856A5C2B17341A99DAE198C900858C93E169C3BC3C544F72BFC2F0C5DF6F29A3575504D30AB5AF2166F8B3E9AE12C020FFA6B2"));  // qq:282846232  
//      iosCustomerList.add(new Customer("fanjinhao",140525,"80","","000000760001020076855A17827227FE3F23F20125F232AA160D196A01376EBF90085915EEE7082F1BCBF24578F3E7783C4514FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED20F314C90D080F2F2D07A7A93021920197A313BCB19314096584658D29302F2501285911394C10605"));  // qq:563718239
//      iosCustomerList.add(new Customer("liu4232374",140901,"53","","000000760001020076855A17827227FE3F23F20125F232AA160D196A95B96EBF90085915EEE7082F1B80D93A4C7A0F310F313B98FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2C6A5A12945D03629D0EEF2D290D2360FF47AF43196D293F4363B3B479083B6478588F8537C207B"));  // qq:891639902
//      iosCustomerList.add(new Customer("zxw111",140825,"53","","0000007E0001020076855A179A3645EBF420FE8593EFBB1CAD44CE463B64C69FC1CEA67E757CE5202D78C70E29AAC54604D3CA595607E9CC181BFFE29D592F9D3A63C60DB0B43AC5CBF138C86C984EBC829C57AF0BA7E9839A13AEC2A2BB6A77DB76F40A703F86C0ACB6D5F73BDF57E9DC440E2574B39213D0136BC817E09455D54E"));  // qq:282846232
//      iosCustomerList.add(new Customer("dfkt00",141226,"85","","000000720001020076855A17827227FE3F23F20125F232AA160D196A01B96EBF90085915EEE7082F605958F21A7AC648FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45D036314040310F31317AEED23BEE194C9380B680F25845B6C620462290F00F47853C8B53BBA4E3"));  //qq:543330111
      
      // shui jun 50
//      iosCustomerList.add(new Customer("100966107",140525,"66","","000000740001020076855A1722B0B09A9820A285C6EFBB53E96A2A3C44928CC9073C2700D06AA236329E95D53F7D6E50CA6C06EE992B26FEE6BDB8D0B0D178DE2E292ED217C8BBC7FDE9BE8E34495371905E1ED5731817029D63A62347801E88A5FC4B6504D9E92E299708A25FC65903721DACB0537D12C8"));  
//      iosCustomerList.add(new Customer("As13390323333",140910,"86","","0000007F0001020076855A179A361A9AF4A5A285C0FBE83FF0C496059333D903A6B65F66CE7FAA0F22D702EB83299E830C5AAE910D80B7E7B232D3FA5BE4757F491885832B0A6BA99D426523CB63C40606705FE8338F249F76AC4856B4EB2B0398F31A73B221B6E3DC75CA26CA25031AAA70DDE4132162840BF79D5585D63B202FD778"));  
//      iosCustomerList.add(new Customer("452944822",140901,"62","","000000760001020076855A17827227FE3F23F20125F232AA160D196A95236EBF90085915EEE7082F1B317A4C0F407A7AF40F49FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED240CBE04645B6317A31A51902463E7A96D029B6CB3E360FC6CBDEE058D23B7ACC1285E8985308CC0D")); 
//      iosCustomerList.add(new Customer("864884667",140801,"42","","000000750001020076855A1722B02AEBC320A285C6EFBBEB0ECE007C5A96C4ED6D99CF7B041E8DC04A8C80FFF6546FE2B84BD9D15CB6A66F95CBCD7087991108B3BD1366004EC8A047AA2B884F72434C3CA8B92F409BE313FC250461EFFC31FA6696FB2304C9C87F813EC29F978EB491E19B9FB42953B723DE"));  
//      iosCustomerList.add(new Customer("7723905",141001,"71","","000000740001020076855A17827227FE3F23F20125F232AA160D196A954F6EBF90085915EEE7082F60B23B3B0F3140C6CCFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45DEDEDEA1C62919364C310F9046583E364CF47A40DDA14CDD3E80D0EE58B67AEF1285FE451FC6BAF0"));    
//      iosCustomerList.add(new Customer("7723901",141101,"71","","000000740001020076855A17827227FE3F23F20125F232AA160D196A954F6EBF90085915EEE7082F60B23B3B0F3140C6EFFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED240360F90E0DE199619364096F2D09393A53E0246D23B9046DE1940F43B29F26012851DE71FA3DB14")); 
      iosCustomerList.add(new Customer("newzjs20",150804,"8","","000000750001020076855A17827227FE3F23F20125F232AA160D196A32716EBF90085915EEE7082F1BA578B391017D310F48FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED290B63B930FEE3E96A1DD194C3B31203E9646D23B96A13180190245A120D0292E128587E5941D5557"));    
//      iosCustomerList.add(new Customer("barton910",150510,"77","","000000750001020076855A1722B0B09AF4A57E8537D4F86F4A94DDD54960524A847EC67E90CB3F95C3BB393F83299E06FE21A48AEFD7F76D31D8D08DEA3BB7F1D3FAFC66F1D3299E17E7F4EE0824021C44463679092F7117177D65B21B72C784C82B439289C57530982DE76C4829CD4BA2E52C609453C6E6F0"));     
//      iosCustomerList.add(new Customer("yptan1711",150205,"77","","000000750001020076855A1722B01A9A98A57E85C0D4E846BFBF0F96EB9439278527CBE0B0BFFEEE0FBB7AC7A92833FE915E598EB82959DFD9F2D054081A719592802E873370C09553216864737B172DF683295E345139E7C750D35FCA05E376E3565D563DA0AA828548AEB51FB1EB2C5EBC76F601944664BD"));      
//      iosCustomerList.add(new Customer("lzy00kat0044",150601,"22","","000000780001020076855A17827227FE3F23F20125F232AA160D196A8F166EBF90085915EEE7082F1B028AF840C620FDF77AC6C67A98FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED28045D040360FF402D2DD46B602461929E0A14C0FA558D0934CEEE0193D903647858DD5201F956E"));       
//      iosCustomerList.add(new Customer("zhu381789643yu",150701,"72","","0000007B0001020076855A17827227FE3F23F20125F232AA160D196A95716EBF90085915EEE7082F1BCA01D84C31F4933BF440367A6ED5CCFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED24C7ADDE0E0D0903E0F310FDD197A933636A5D24020D231930F31DDA1C6F436C41285FEEA94EA1174"));  
//      iosCustomerList.add(new Customer("fff909045",150601,"20","","000000750001020076855A1722B02AEBF420A285C6EF803D17C39FA047CF1EC6D22680A4AD6244A236F65C14C51AA0D4189EA16EB89BE579BE1088C17D740BEA9B73743A2397D5AADCD65DEA617287362225F9ACA4AD5774FFD73D61D5FFC6E4422EDDCC2EBB8348C64DD132F0A2F097E8287616D05378C561"));     
//      iosCustomerList.add(new Customer("nazsmlove",150523,"19","","000000760001020076855A17827227FE3F23F20125F232AA160D196A8FE16EBF90085915EEE7082F1BCB78F7F8CB59D915292EFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2CB3EC63B29D231C690A13180A1A5D09302B6F4402046A1DDB631400F02A196601285F71E942ED707")); 
      
      // shui jun 70
//    iosCustomerList.add(new Customer("q21223211",140605,"21","","000000760001020076855A17827227FE3F23F20125F232AA160D196A8FB96EBF90085915EEE7082F1B6E930F930F0F310F93EFFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED231EEE0D2DD46F2B6C6F4F480B63B7A29F23E36EEB6F490583E903E3602F2462E128508145398A190"));      
//    iosCustomerList.add(new Customer("ccx19870810",141001,"33","","000000780001020076855A17827227FE3F23F20125F232AA160D196A8F736EBF90085915EEE7082F1BDDE039F49340F43BC6F49348FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2C64C8046A136203EF429B6A558F2F2A14C4C40C64CA5D23180D0930246B631CC1285F03853AAAAEB"));  
//    iosCustomerList.add(new Customer("hc3718541",141001,"35","","000000760001020076855A17827227FE3F23F20125F232AA160D196A8FFB6EBF90085915EEE7082F1BCB3C19313B93F44C7AEFFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E455819DDE0A10F20A1C6CBD03B0F36F44C36F40F7A29D0F43193C6963E29D00FF012858F7C1F7555A7"));  
      iosCustomerList.add(new Customer("alexaiyug",150703,"55","","000000750001020076855A1722B0B09AC3A57E8537D4E8C9C6D82F2911F587E9D1F095EED6D4073595EE47CA833FA8CE4FAB4B242B818B54BB1038533367BC59262D35A9550AC382FB6223038B3B5CBCE354281BA1D7AF630B7211BE04984D852085455F98847FEAF29825BBDD3EE564FEBC9F66AF94890B10"));     
//    iosCustomerList.add(new Customer("goodwangyue16",140925,"7","","000000790001020076855A1722B01BEBF420A2D693E37E77A7F42225780D05297FD3EE270C7C475D4BE722EAAFC539DDB1549AD8021D8DFD24F4BE1402280E733E1403864BECAFE8C311AC320A74C70E18CAF0F3B33F9362E0CB6FEB530D8989B170F97277E125F69D4484B687EFD9423440DC52FD430819E0DAC8C0B8"));   
//    iosCustomerList.add(new Customer("ff9797",141001,"72","","000000720001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA69821B253A802480653FC216AA252946F28FD128166616D7162545AEF0272B862BD62BBAA6D69E1C9E2B278EF69E1CA6A886BAF4A15BD4D15BD4EC504629A492C96AD977D9BD269E2E7FF7A57685E6DF1F8D3BBC"));  
//    iosCustomerList.add(new Customer("jerrin6",140901,"83","","0000007F0001020076855A1722B025EBF420A28593E37E63AD8DC405ED59E58AF4D5186A8527CFC30DDA80CC5C2FC539DDB1549AD86EC0569B904F235C9CD68049CED28B5962E318B3B3E9F8E46296673CEA0ED82A47B32874281ABD9B556B334C8414B1E9D2E13F8C463961719CC31C4286A93DDD760A2C4E8302721D9EE194C12739"));
//    iosCustomerList.add(new Customer("xiongsheng",150102,"9","","000000760001020076855A17827227FE3F23F20125F232AA160D196A32056EBF90085915EEE7082F1B6676B4A86518CF9E75288A34CCFE585745026FD977D9BD269E2E7FF7476518A8BD58F6CC8C75D5E05C5177132D8A4BE1EE51A84BD95C5CA82DBBE677A8CF50CF10E613135C505113A57685312694AADD9B"));  
//    iosCustomerList.add(new Customer("bluevagina",141201,"25","","0000007F0001020076855A179A36F79AF4A5A28537FBE83FF0C496E23C93FFFCC90478E72CD56F944276FD02EB83299E830C57AECD62E68D2E53E0CA21F8B8FB37478B0CD684DDA26326A244D3FE9D51EF9B3D2675043919F376DA56EBDDA5792B3D6C6E9208FA8EC23D759D249D74F0B770094BCA13847575F0FADEEAB7479491BC8F"));   
//    iosCustomerList.add(new Customer("fx226909",141121,"12","","000000750001020076855A17827227FE3F9095AA3E78B636AA696C167DA502023A807F4223FA90468F4283F542671623C2783EF30D695F3FC216AA252946F28FD1288002457F8059451B027F6616D78F25802545A5D7E7B42523D516D7B38023D7E7653F23F20125F232AA160D196A32611285FA301F2DD7A2"));    
//    iosCustomerList.add(new Customer("a8850097111",141017,"10","","000000770001020076855A1722B02AEBF420A285C6EF803DCEB0AE9F15F7F32044D3582733AF9A9F7B7B4C6429B2C5F39C453A10C5E7DB4B75237A3B03DBD350110C85E0968B330E74B37313D03533DF3A40CB79CE514E56EFB7AEC451CBF4B1E1C415E949AFA9F300241AD324D374FBA854AA4308956C1FAB23A4"));   
//    iosCustomerList.add(new Customer("linhuaqing",141227,"30","","000000770001020076855A1782722742671623C2783EF30D6C3EB636DF901B29363625653F9095AA3E78B636AA69695F3FC216AA252946F28FD12866161645666680A58FE71BDE3A80E72480E725A645DE67F766B40225B316D723FE3F23F20125F232AA160D196A8F03FDB38BCF900A630A0A1285F62920FEFD95"));  
//    iosCustomerList.add(new Customer("baicai123",141104,"93","","000000750001020076855A1722B02AEBC320A285C693BBEB0E6AC6AC37CFA62FFF4945F31A2DE4473EC41B14C51AA0D418E92A502FCCD8803FFBE69ACB761AB62FA269E028B71D05C9AA9323DA6B8E37C84087C835E5F5FF7F4D00333B806C72BCD4A8F0A0A6348E81792600881BBF5E8F6BC805C3948EF111")); 
//    iosCustomerList.add(new Customer("gxfbfbngd5",141201,"79","","000000770001020076855A1782722742671623C2783EF30D6C1B6DB4258F2595256745E23F9095AA3E78B636AA69695F3FC216AA252946F28FD12880A559D7DED745E725DE168F4516028067DE668067D7A56723667FB43A8045E75F3F23F20125F232AA160D196A9514FDB38BCF900A630A0A128586819444B92A")); 
//      iosCustomerList.add(new Customer("lsss42",150507,"40","","000000730001020076855A1782722742671623C2783EF30D823E463F3FD7455F3F9095AA3E78B636AA69695F3FC216AA252946F28FD1288059B4DE80A6B37FB48FD759028FDE45A645A5A645256680B316022545B416DEFE3F23F20125F232AA160D196A8FC3FDB38BCF900A630A0A128531B753653BC4"));  
//    iosCustomerList.add(new Customer("uyuyu43",141218,"8","","000000720001020076855A17827227FE3F23F20125F232AA160D196A32716EBF90085915EEE7082F6015665F471B563905F38F325F39BB4BA36C3535BC25BC8F31E19863E7B7BC5F258F6311193D05369A11293E29F0369AF2F0F2167E2935604011053E3E0516F2F2605F357E6C4BC81B2E53B8C762"));  
//    iosCustomerList.add(new Customer("h15aaaa",141218,"77","","000000720001020076855A17827227FE3F23F20125F232AA160D196A955E6EBF90085915EEE7082F6008E5938012476E05F38F325F39BB4BA36C3535BC25BC8F31E19863E7B7BC5F258F6311193DB77E6056607E5F601BB7F005167E29F26CB756605F05291B1B60F00505603660BBC8BA33537CC1B2")); 
//    iosCustomerList.add(new Customer("hlovep1",141221,"62","","000000740001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA6982AA0FB67825F2A542671623C2783EF30D69123FC216AA252946F28FD128801B16D7E745E7DEB44567E73A8080E7D7238F45D759D7E71BB3A525E725E71B6C3F23F20125F232AA160D196A95031285C2F253888AD1"));  
      
//      iosCustomerList.add(new Customer("yeguang1985",150501,"79","","000000780001020076855A17827227FE3F23F20125F232AA160D196A957C6EBF90085915EEE7082F1B9102DE21804578339340F4CCFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED290D2F440407AF4C64C3629F23EF4A5B629D2C620F2F2D2963E3B933696453EEF128517E094F1A468"));  
      iosCustomerList.add(new Customer("nikestore10",150801,"35","","000000780001020076855A17827227FE3F23F20125F232AA160D196A8FFB6EBF90085915EEE7082F1BDD78E7FDB36EEE1590D09348FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45E0A10F7A0F29F2464619EEE0D002A120193636313BA5B680A102D23B93C6319C1285526520B9987C"));   
//      iosCustomerList.add(new Customer("hjxlovezw",150602,"34","","000000760001020076855A17827227FE3F23F20125F232AA160D196A8FA66EBF90085915EEE7082F1BCB3C7D96D91529B3F89CFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED2CB19F4F440DDD0400F29E03EA53E3B80D040C6F47A2046B60FDDE03E02D2802B128599F994640A1B"));        
//      iosCustomerList.add(new Customer("401200866",150201,"31","","000000760001020076855A17827227FE3F23F20125F232AA160D196A8F926EBF90085915EEE7082F1B317AC6930FC6C6F43656FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45A19619C690D00FCB4558F2B67ADDE0E0D236C64C314CC67A403B3B36DD3EC6561285024F1F61C8A7"));  
//      iosCustomerList.add(new Customer("dxz138597777",150301,"71","","000000770001020076855A17827227FE3F23F20125F232AA160D196A954F6EBF90085915EEE7082F1B02758B959331F44C403D476E05F38F325F39BB4BA36C3535BC25BC8F31E19863E7B7BC5F258F6311193D357E9A5F60B7F07E9A1B3E1B365F351B1B36292916169A56169A1B3516051B114BC81A7A53795B5E"));      
//      iosCustomerList.add(new Customer("peter9386",150701,"20","","000000750001020076855A1722B02AEBC320A285C6EFA2EB0ECE004D4173FBE0B55827AEAB1C71B0300B823AA05E7C8D1662F31A6CC14962F09B9BEA1760AB24A686D0316098CB0325CEF9FE8E235B9FA84C0301BAAB21A00195B0F5924EA044A5699849D8941C4B20D97EC21B860E52A2D09A4AAE1F061BF7"));   
//      iosCustomerList.add(new Customer("Jyf52013",150701,"8","","000000740001020076855A1722B0730C9820A285C098EF432F47788B505188ADF0A2180CC3ED50291751A739DBD454BEDE6C9F40B1809F90DC674699CCB5CC713291338863F9CF40DEFC50CDA1E1829167183691A2ACE1D8DFD9DA14D343BF709ED88C8561D5E70C304B0E0BF1C8754CF621CB611FCCCCB8"));     
//      iosCustomerList.add(new Customer("bj051603",150303,"21","","000000750001020076855A17827227FE3F23F20125F232AA160D196A8FB96EBF90085915EEE7082F1BA546DFC64C9336C62DFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E45D00F3196D24C80D09619293EC67ADD3ECBF2E019EEB629E0A1EE453E31EE58601285E740538B5DFF"));  
//      iosCustomerList.add(new Customer("tr130517114027",150518,"8","","0000007E0001020076855A179A362AEBF420A285C6EFBB8317C361998B244FF6F2B5CE257EF483CB47D65BF93AA0266D93C9C67ED81092A392A0B210CEDF8F37A0CA20D66C2944BC3D825FEFB675F569F0351D7BAF75381985A43776BA61B4F35157AFCC21590843EA46A7F2A710828DBCE423AA3494B3BD1366340831C29406905F"));   
//      iosCustomerList.add(new Customer("jzc870813",150201,"25","","000000750001020076855A17827227FE3F23F20125F232AA160D196A8F116EBF90085915EEE7082F1BCB7D0119F43BC6F4932DFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED23B0F2946D20F93A512434CEE3E4C0F36314C2945DEB6C64C0FF44096A193CC1285C0E51F7B08A0"));        
//      iosCustomerList.add(new Customer("13313319060",150219,"71","","0000007F0001020076855A179ADF1A9AF4A5A285C04BDA65A62A8D4640A3C2645EA8837EED321F4276FD79CB49F72D63464E9B68BBB1176EE45178FA952CB3D49AD68092A9971856F15A7BD186ACA76F809E09185C98C0749E5E9E74A6BDAA70A8B56F434C1DC2579D8DC14F02854700965A826C73BFDE1AD08BA9729F42A994601536"));  
      iosCustomerList.add(new Customer("zzmmzz1",150803,"74","","000000740001020076855A17827227FE3F23F20125F232AA160D196A95256EBF90085915EEE7082F6015F8015924F895EFFDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3ED24031EEB6DDE0F24558D2F429DEB6363B021929A180D202D2C6F4EEE03E4CF4561285EA1C94FF3B19"));      
//      iosCustomerList.add(new Customer("z841935717",150415,"99","","000000760001020076855A178272274223FA90468F4283F542671623C2783EF30D6CFAD5A5456680D7DE5980653F9095AA3E78B636AA6969123FC216AA252946F28FD12866802580453A161BE7E76745231B25C8F880251602B445DE7FB4D7B402E7456C3F23F20125F232AA160D196A0150128593B053262591"));        
//      iosCustomerList.add(new Customer("AaronH8921",150523,"49","","000000770001020076855A178272274223FA90468F4283F542671623C2783EF30D6CA9162995AABC7F7F8002FE3F9095AA3E78B636AA6969123FC216AA252946F28FD1288080B3A5B3B402B466164545B4D5A50280B480CA80D74523F766A567028F23D5FE3F23F20125F232AA160D196A95EF1285A73053FF0E00"));        
//      iosCustomerList.add(new Customer("guguship",150124,"74","","000000750001020076855A178272274223FA90468F4283F542671623C2783EF30D6C16781B78F2C20F29A23F9095AA3E78B636AA6969123FC216AA252946F28FD1288067D716E7B4DEA6CAA51B02A502248080591BDE25161BB316A6802380450202E23F23F20125F232AA160D196A956312851DAC9448E141"));    
//      iosCustomerList.add(new Customer("haloua",150530,"10","","000000730001020076855A178272274223FA90468F4283F542671623C2783EF30D823EB436B6781BFE3F9095AA3E78B636AA6969123FC216AA252946F28FD128667F7F801B16458F02B4661623DE25028F8FB3802480670202B4D70280B316168C3F23F20125F232AA160D196A3263128515C85348B3C2"));   
//      iosCustomerList.add(new Customer("pmc123456789",150701,"35","","0000007E0001020076855A179A3645EBF420FE8593EFBB1CAD44CE129C3B4E52F214D235BB7694E520807C17D3E184DF0531F9C768BBD0D3466D5CE9013094CCE796BF6965359A4D74086CA5D875EF023EA33D83870BA7E90C9EEB8EFC56AE0241EBECE3422125BB4C73D58729AAF023B2B2D113CF215178FA07FADC769F20696D57"));        
//      iosCustomerList.add(new Customer("yunhanny",150701,"10","","000000770001020076855A17827227FE3F9095AA3E78B636AA696CFA293EDFB436DF7D80598D23FA90468F4283F5FE3FC216AA252946F28FD1288045A62316E74545591B25D759E7B3A580CAB480B41B02452345E7A5D5A5E7E7A23F23F20125F232AA160D196A32630845E0E015806D98602B12855E1794785D15"));      
//      iosCustomerList.add(new Customer("td5201314",150115,"10","","000000750001020076855A1722B02AEBF420A22293EFBB8CED8DC463FE43F24F0E5FC3E5F17FA236ED175A29AAC546E9844BCC33C69F6473BCC57059AE99F4B690A91B97FF688BFA42E6944F44356B44EA095B2186C724E907A6AD1C99262E9DEF25016C0D7708788856475A8E58797E41A976E89453B3492D"));    
//      iosCustomerList.add(new Customer("ck00002",150301,"6","","000000720001020076855A17827227FE3F23F20125F232AA160D196A3260FDB38BCF900A630A0A0845E0E015806D986008E0AB8547569863E7B7BC5F258F6311193D6C351BB7403629F2F23E053E0556351B3E7E056C9A35B711051B603629162935B15D326F28BC2502BCBC3131BBC8709653EB18B7"));       
//      iosCustomerList.add(new Customer("a2147414",150601,"91","","0000007D0001020076855A179AA1455FF420A285C6B08990F0F4C4593194EE3D6699E5F439CDEB61E8444789A0F6546FE2793EEBD8DB1228332562189FFC00B83028FC8EA3EA3DC533A49A8F50187D2F1728F2E9E3D786788624915308B27D196F21C0B2E6CE5D4098294D37E89C718FC8E8132D00877AA92CBF88D55594C1B586"));      
//      iosCustomerList.add(new Customer("linghangqi",150627,"12","","000000770001020076855A1782722742671623C2783EF30D6C3EB63625AAB436257836FE3FC216AA252946F28FD12880A525D78FDEA580A6161B8025D7E780CAA58FD7A5803AB4021B234523D58067123F9095AA3E78B636AA69695F3F23F20125F232AA160D196A3261FDB38BCF900A630A0A12850EF8940493BD"));      
//      iosCustomerList.add(new Customer("shmily1314",150627,"12","","000000770001020076855A1782722742671623C2783EF30D6CF2C20F3E365B8080D780123F9095AA3E78B636AA69695F3FC216AA252946F28FD128804545A5E74559D780DE59D5A516234523258F8F23028045D7028FDE1625F7B46C3F23F20125F232AA160D196A3261FDB38BCF900A630A0A1285E87953DCC826"));    
//      iosCustomerList.add(new Customer("happy037",150127,"79","","000000750001020076855A1782722742671623C2783EF30D6C36B429904380A5D7653FC216AA252946F28FD12880458FD51680D7238F3AA5A5B380B4E78F23E759A5A5025967026780A6028F23123F23F20125F232AA160D196A957C6EBF90085915EEE7082F602BFDB38BCF900A630A0A12850CD6539504CD"));      
//      iosCustomerList.add(new Customer("10020pp",150604,"55","","000000740001020076855A17827227FE3F9095AA3E78B636AA69825980A5A50290904E671623C2783EF30D695F3FC216AA252946F28FD128804559458F1602248067F7B40202E7D7DEA5A666A5F71602B4E7D58059241623123F23F20125F232AA160D196A959CFDB38BCF900A630A0A1285D5A11FB98956"));      
//      iosCustomerList.add(new Customer("crazy23",150701,"6","","000000740001020076855A1782722742671623C2783EF30D82673F8F30F880028C3F9095AA3E78B636AA69695F3FC216AA252946F28FD1286616252545DE7FB4A68FD78F232316F78023D7457FA5D716232580456725B380FE3F23F20125F232AA160D196A3260FDB38BCF900A630A0A12853C4F94946DBD"));     
//      iosCustomerList.add(new Customer("kk89054",150701,"15","","000000730001020076855A1722B0B09A9820A285C6E33FDDD3B0C3BA9742D54DFA7AC6795C83413A8B39D51E3F7D44A8AF986F49A38FD4F8C7C37BA9214DCB54397A9CF575AA6E777C3F77E0F058415DD64F59987F2D19C662224A1FD363A7A2D3A6A06C6789DC5594D0936D7F6DB1A278A1F91FEFD2DC"));  
//      iosCustomerList.add(new Customer("zhangshun33",150601,"43","","000000780001020076855A17827227FE3F9095AA3E78B636AA696C6D95B4362578C2903EE7D79E671623C2783EF30D695F3FC216AA252946F28FD128161680E7028F45A524A52516E7B4D780CAB4A62545161B23A60245E723E7E7D5FE3F23F20125F232AA160D196A8FE6FDB38BCF900A630A0A128591C3200A2DD8"));    
//      iosCustomerList.add(new Customer("quiss99",150306,"23","","0000007E0001020076855A1722B0FDEB76DAA28593E37EA1AF0D2AF7187FA4D63B004035BBF4F1C04727F6DE53DF28A746A06810A1587A3170D742E28D1CBF9AA220C49B0EA953D0E6527D2F17E6DD3EA3FDCB16E843F2BCD9BCD8A8BC54E14C3414F256B83A91E6B3E38F4E8F207C20F9CE9C2CD73C19E88EFD5FEA82D494FCB12D"));    
      iosCustomerList.add(new Customer("zhoucan18",150706,"45","","000000760001020076855A1782722742671623C2783EF30D6C1E950F781B2336E766A23F9095AA3E78B636AA69695F3FC216AA252946F28FD128162380A5D7CA16E7A5F7A52323A68F8F45A502CAA5D70224164580D78FD702A5653F23F20125F232AA160D196A8F26FDB38BCF900A630A0A1285BA0353BD9232"));   
//      iosCustomerList.add(new Customer("srjwdwd100",150112,"57","","000000770001020076855A1782722742671623C2783EF30D6CF23F95016746674580A5A23F9095AA3E78B636AA69695F3FC216AA252946F28FD12880A53A668016A6D5A580A58FD7452516D76666168F45A545B416DE250280DECAA23F23F20125F232AA160D196A95F0FDB38BCF900A630A0A1285E1E99402B64F"));  
//      iosCustomerList.add(new Customer("nanituo",150501,"101","","0000007D0001020076855A179ADFF79AE520A28537DAFBFEAD0AF4E7CFEA5591C3DF165FB0C33C32489BB00AF16FCD509639DCE92A6054EE14EE2F3D1ABCE68E1B067DCACD639923376CBB5D4A479CC87674EC36A9781B0971DB1064926B74888CB2ADA6E99996787AA2453F8E1944691AD6CFE0360AE4E9CE4C1DCB1320E37770"));  
//      iosCustomerList.add(new Customer("453180078",150607,"43","","000000760001020076855A1782722742671623C2783EF30D6CD745DED766A5A5A524A23F9095AA3E78B636AA69695F3FC216AA252946F28FD12880801B1602E759D516A6D5B4A6800280DEB3A57F80DE02238F451BCA6680A5A5FE3F23F20125F232AA160D196A8FE6FDB38BCF900A630A0A128539AA1FD7A763")); 
      iosCustomerList.add(new Customer("bbmmbb49",150829,"77","","000000750001020076855A17827227FE3F9095AA3E78B636AA696C168F953E1B8F02F742671623C2783EF30D695F3FC216AA252946F28FD12816DE59B42323A63A80D71B02247F80DEB4803A8025CA16E7451B45458F1616236C3F23F20125F232AA160D196A9526FDB38BCF900A630A0A1285E7F99434D768"));  
//      iosCustomerList.add(new Customer("lzh380572937",150428,"57","","000000790001020076855A17827227FE3F9095AA3E78B636AA6935365B95A5CAA5A5DE59D580D7BD671623C2783EF30D695F3FC216AA252946F28FD12816B3B423E759247F66161BD5808FE7163AA58F16E72416CAB4456616DE1680E7FE3F23F20125F232AA160D196A95F0FDB38BCF900A630A0A128587A09449BCB8"));    
//      iosCustomerList.add(new Customer("zymnewyaya",150205,"90","","000000770001020076855A1782722742671623C2783EF30D6CFAF8363E25F26D163016FE3F9095AA3E78B636AA69695F3FC216AA252946F28FD12816A625D7F7A5A6A6D7B4661645E7A567251645B425028002A6F716168025E73AA23F23F20125F232AA160D196A0132FDB38BCF900A630A0A1285AAFFDABA0A2B"));   
//      iosCustomerList.add(new Customer("fnnfy825",150609,"101","","0000007C0001020076855A179ADFFDEBF4A57E85C0D4F80266AE71AAC681A09CCA3CD92A77D790AE2C024672E19C6305C6ABB2CB50A346E95FE465534D7C50941781FA2634A7D2AE0E441276EE4849129DAEC53BB517A7A5B61AF3609EB2DFFA11B2BE04464540A1C7CC76D192DDC3E3486ECB91A860AE6D9E9FDEFA94938291"));    
//      iosCustomerList.add(new Customer("dun68",150111,"23","","0000007F0001020076855A179A36F79AF4A5A28537FB32CDEE22D40C27BFBCC1F1AD90504121049F39C603539F458C2845223B85592327883299976D581D145489FC66E41378FA176CDB2FEB0FD4AC85EDBAE8E11D9C11AB0B24E5EE541EDD10EAC0812E521E83783D3AAEB5BBD55A92641CDA58929871F513D841BCAEBE402009661C"));  
//      iosCustomerList.add(new Customer("chenanford",150615,"58","","000000760001020076855A1722B0B09AF4A57E8537227BDD179F2F492AE8FE176A695FF53DA46FC61E3BFE458C28452209E5CEF483E940FCB9551214EE2030341D4843E6ED15A4F9B79AD6BDF4175376FFE76F62C9848F6045A898A39C679CDA893656D18F0972C40D587435331333EAEA65709FD18820850B2D"));    
      iosCustomerList.add(new Customer("chris33",150801,"6","","000000720001020076855A1722B02AEBC320A2229393BB8C7944B0A64496A1D167404E544AFBFE10C860F16FCD84F47017235015427C1E45D93382F3F7A29BC80F51F8E9D2EC7358668F505F62FE0677307CDDE38B8AD9C41FF2FD48B809EBD84F271D25B0FEF6B8C51F083EC986A9AE172A5373BD24"));      
//      iosCustomerList.add(new Customer("tr130619084821",150508,"19","","0000007D0001020076855A179A3645EB76DAA285C6EFCD90810C5D920D24637FB040262D2E517EB1E334C26429B2C5F391AFF8506582928B68D20B4F8A54AA398AB5AC94B35974D3A2E44E2C47AAEFE86F45203DCBBD99B30D8AD44D71C9D774C05460F1A88378D2BD0F5995FE0C110122E147589FEBFD5E41BC9FB900205723AD"));      
//      iosCustomerList.add(new Customer("no1tmac24",150315,"12","","000000750001020076855A1722B02AEBC4A5A285C64C2D53C67A710840C47BC5CE2536AD191053CE10D1454DDF09EC0C2779FC6E2F6A951339FB62CBCE0CCBDD81480F740574F9F98A76B845AF7D54D6795D198AF1E14A53FFCFCBBD127B98868F9719BDC0110CB6C7EE7CF05E4F1EC1822723EEAD943FFA21"));  
      iosCustomerList.add(new Customer("090250151",150703,"50","","000000750001020076855A17827227FE3F9095AA3E78B636AA696CD77F80A502DEA580DE42671623C2783EF30D697223FA90468F4283F5FE3FC216AA252946F28FD1288024666680C8018024B4DEA5B466B480E7D7D723E7E7E745B4B3B425D7A6D7A23F23F20125F232AA160D196A9549128503F91F1DAEE0"));  
//      iosCustomerList.add(new Customer("zzx1073x",150201,"53","","000000740001020076855A17827227FE3F23F20125F232AA160D196A95B96EBF90085915EEE7082F1B7FF8F8F493C63B6EC4FDB38BCF900A630A0A0845E0E015806D9860D46ECBE7E478F7CF66902E3E4545224390F219DDF24619293E363B2045D2A558F2E0DEE01980A13B4C7A93EF128574D59426D18B")); 
//      iosCustomerList.add(new Customer("sl2317888",150404,"13","","0000007E0001020076855A17827227FE3F23F20125F232AA160D196A325E6EBF90085915EEE7082F1BB70FC64C0FC69331C6360F40C693470F8080244E671623C2783EF30D698D23FA90468F4283415CEF508426E11113CC959E2D4AF507F0278683879C2D764C354AE34A0A270DCC4A83CC879C4A0DCC839C95478537C29475B90E"));    
//      iosCustomerList.add(new Customer("wt68851789",150326,"17","","000000760001020076855A1722B02AEBC320A285C6E35CEB0E4A7EF971208CECCE2536D5301C71B0307DA73AE91E998BE7C78A36C29BBE58628B9468ECDADDA7BBA8FD209D3A49AFDBE947717DCCE06F34372377E4F147984679A947E52D4AB347AF6F4E29136A849CE0497872588AAB70627D0820E8947CAABD"));   
//      iosCustomerList.add(new Customer("gujin34",150626,"78","","000000740001020076855A17827227FE3F9095AA3E78B636AA698267783E9536E7D78D671623C2783EF30D697223FA90468F4283F5FE3FC216AA252946F28FD128161B80DE59678F161BDED7CA80D57F66B41B250267232525D51625E716DE45A6123F23F20125F232AA160D196A952F12856B5B94CFDB15"));   
//      iosCustomerList.add(new Customer("yf6315359",150701,"58","","0000007E0001020076855A179A36F79AE520A28537DAFBFEAD0AF405CF529F5684ED50EE76526C7341301D03DDF6BD95FE8928C59BC35C08297CF6AC92798209A45E5B541757F2E907E302488CF446172C0FA4797AC91E518679A45A685F74A9D6A3BE83AB989B330D6F2BF24BE67E41726466C28D724990BF8ABFDC0507944AEC22"));     
      iosCustomerList.add(new Customer("whj5816895",150703,"43","","0000007F0001020076855A179A36F79AF4A5A28537FB32CDEE22D4A8378054C131B62616C453F1206531D282208E833FA848A44EE9B056AACF6DCF2691AD1454863B664D07DFB3313CC4AC44415D5A2097AC03A320459D2180AB9AF933B90EBC1D6F21B4ABDE19BC80AEB60228EFA21FD68C08F763EE7DAC910C3B361DBAE394148CB8"));       
//      iosCustomerList.add(new Customer("qyy0211",150617,"104","","000000730001020076855A1722B0B012D4A5A285932BA335AE9F618096B0263DBE838CA457EF7FF4932E79B98D21806983757F98CEC4BDA3ADB2B1F001610D47612FDDB66A32414669830935615AA26F34B5316295B4FD34A8D29D762048D22EE90B05CC798CFD402B4C39EC7091E75285364B530720AA"));        
//      iosCustomerList.add(new Customer("piece36",150527,"13","","000000730001020076855A1722B02AEBC320A285C693803D80609F9283A2B0697C40291A25E52AA5F35C14D0099BE2C2ED2102E64CB3CACD6353CA360FAD5385F75375F9ACAA5E6CCECC0274DC8C4A617F88519F45B1A1A8490D96656C0E678276978876183519FB23F667A8BD54C11159EC129412E90C"));       
      iosCustomerList.add(new Customer("edoteam26",150827,"40","","000000760001020076855A17827227FE3F9095AA3E78B636AA696C231BB678A61B36DE0282671623C2783EF30D697223FA90468F4283F5FE3FC216AA252946F28FD1281680CAB44559592523E780B3B4B31625027FB480458FD72523DE5925451BE7458C3F23F20125F232AA160D196A8FC31285021994FFFEBB"));          
//      iosCustomerList.add(new Customer("CHERRY18015520220",150630,"105","","0000007E0001020076855A17827227FE3F9095AA3E78B636AA6905ADB5449A0C328066A5A580DEDE02A502024E671623C2783EF30D697223FA90468F4283F5FE3FC216AA252946F28FD12816E7CA7FA5A68FD7A6E7B3A5D78F1B254545806723231BE7B4E7A580D5801B168C3F23F20125F232AA160D196A013512858AD320146C96"));      
//      iosCustomerList.add(new Customer("mdkl111",150430,"100","","0000007B0001020076855A1722B025EBC4A5A2859380DA4FC6F4D4AA4E99C28AC6F0352D8FACB7B1F24B32ECE184DF056A92082D2A991B696836581E0BE48F3A5E6A7EDCBA53C885C977BA2486FF62F60BF7B8ABBC7D7AAD4331587772A22B20367294CB954286965C2BB184BB31A78B94D27423335414362C3482943C7CBD"));        
//      iosCustomerList.add(new Customer("846491001",150630,"110","","000000740001020076855A1722B02AEBC320A285C693803D6A93AE942AFBA22F997A297F86CBFE10E21B14D009A30C184C20D326AB8A9B40F84CC8DD29D845825D0507ACA76F7B0972355AF3A3A315063715C243989374C95E049F35CDD82238119754D90C468512FB358950C7AD3FC36C594BA55308A26C"));       
//      iosCustomerList.add(new Customer("sxx216",150529,"96","","000000730001020076855A17827227FE3F9095AA3E78B636AA6982F21E43A5028082671623C2783EF30D695F3F23F20125F232AA160D196A0100FDB38BCF900A630A736ECBE7E478F7CF66902E3E45B6363BDDD24080DEB629D07A0F0FCB3EA53EC6310FC64CF4A51931CB45A1C69812850C8F1F66A3FD"));        
//      iosCustomerList.add(new Customer("TYTY39",150701,"99","","000000720001020076855A17827227FE3F9095AA3E78B636AA69829A2B7E2B80CA42671623C2783EF30D697223FA90468F4283F5FE3FC216AA252946F28FD1288016F780B4DEA5E7D74559A58F45A6801B02B43A1645F7A524A52480765FA6F4A15BD4D15BD4EC504629A49258E776851C891F4D5548"));        
      iosCustomerList.add(new Customer("edoteam59",150704,"44","","000000760001020076855A17827227FE3F9095AA3E78B636AA696C231BB678A61B36DEB342671623C2783EF30D697223FA90468F4283F5FE3FC216AA252946F28FD1288025A61BD56666A5A54559A6D766A5022416E725DE0202590280A6805916D7DE6C3F23F20125F232AA160D196A8F6112855C07532E2D01"));         
      iosCustomerList.add(new Customer("maomaonehaha6",150801,"92","","0000007D0001020076855A179AF11A9AF4A5A285C0FB32CDEE22B118BF08C189B653B4CE7F670F22D779CB9069D13FA8486221C9125DA7C3898C7298E070FA9AC9D74E9A4A9F5CC4D29DE9251168816D4D076857110BFFD73DA43591A85FD867DA8CA4C6A1AC01CECD3764B27916E34C8F55DA9962021FB2E664C83456948F8337"));         
//      iosCustomerList.add(new Customer("jr1103",160115,"83","","000000730001020076855A17827227FE3F9095AA3E78B636AA69823E01028080A59E671623C2783EF30D697223FA90468F4283F5FE3FC216AA252946F28FD12816A6E7D7D7D5A5DE7F16231B02B316238F80D7164502E766B4DEF78002B4028F5F3F23F20125F232AA160D196A012D12850A971FF7B3F8"));         
      
    }
    return iosCustomerList;
  }
  
  public Map<String,Server> initIOSServers(){
    
    if(iosServerMap == null){
      iosServerMap = new HashMap<String,Server>(10);
      
      iosServerMap.put("1",new Server("112.124.31.220",8100));
      iosServerMap.put("2",new Server("112.124.31.220",8100));
      iosServerMap.put("4",new Server("112.124.31.220",8100));
      iosServerMap.put("5",new Server("112.124.31.220",8100));
      iosServerMap.put("6",new Server("112.124.31.220",8100));
      iosServerMap.put("7",new Server("112.124.31.220",8100));
      iosServerMap.put("8",new Server("112.124.31.220",8100));
      iosServerMap.put("9",new Server("112.124.31.220",8100));
      iosServerMap.put("10",new Server("112.124.31.220",8100));
      iosServerMap.put("12",new Server("112.124.31.220",8100));
      iosServerMap.put("13",new Server("112.124.31.220",8100));
      iosServerMap.put("15",new Server("112.124.31.220",8100));
      iosServerMap.put("17",new Server("112.124.31.220",8100));
      iosServerMap.put("19",new Server("112.124.31.220",8100));
      iosServerMap.put("20",new Server("112.124.31.220",8100)); 
      iosServerMap.put("21",new Server("112.124.31.220",8200)); 
      iosServerMap.put("22",new Server("112.124.31.220",8200)); 
      iosServerMap.put("23",new Server("112.124.31.220",8200)); 
      iosServerMap.put("25",new Server("112.124.31.220",8200)); 
      iosServerMap.put("26",new Server("112.124.31.220",8200));
      iosServerMap.put("27",new Server("112.124.31.220",8200));
      iosServerMap.put("30",new Server("112.124.31.220",8200));
      iosServerMap.put("31",new Server("112.124.31.220",8200));
      iosServerMap.put("32",new Server("112.124.31.220",8200));
      iosServerMap.put("33",new Server("112.124.31.220",8200));
      iosServerMap.put("34",new Server("112.124.31.220",8200));
      iosServerMap.put("35",new Server("112.124.31.220",8200));
      iosServerMap.put("36",new Server("112.124.31.220",8200));
      iosServerMap.put("40",new Server("112.124.31.220",8200)); 
      iosServerMap.put("42",new Server("112.124.31.220",8300));
      iosServerMap.put("43",new Server("112.124.31.220",8300));
      iosServerMap.put("44",new Server("112.124.31.220",8300));
      iosServerMap.put("45",new Server("112.124.31.220",8300));
      iosServerMap.put("48",new Server("112.124.31.220",8300));
      iosServerMap.put("49",new Server("112.124.31.220",8300));
      iosServerMap.put("50",new Server("112.124.31.220",8300));
      iosServerMap.put("53",new Server("112.124.31.220",8300));
      iosServerMap.put("54",new Server("112.124.31.220",8300));
      iosServerMap.put("55",new Server("112.124.31.220",8300));
      iosServerMap.put("57",new Server("112.124.31.220",8300));
      iosServerMap.put("58",new Server("112.124.31.220",8300));
      iosServerMap.put("62",new Server("121.199.36.80",8200));
      iosServerMap.put("66",new Server("121.199.36.80",8200));
      iosServerMap.put("70",new Server("121.199.36.80",8200));
      iosServerMap.put("71",new Server("121.199.36.80",8200));
      iosServerMap.put("72",new Server("121.199.36.80",8200));
      iosServerMap.put("74",new Server("121.199.36.80",8200));
      iosServerMap.put("75",new Server("121.199.36.80",8200));
      iosServerMap.put("77",new Server("121.199.36.80",8200));
      iosServerMap.put("78",new Server("121.199.36.80",8200));
      iosServerMap.put("79",new Server("121.199.36.80",8200));
      iosServerMap.put("80",new Server("121.199.36.80",8200));
      iosServerMap.put("81",new Server("121.199.36.80",8300));
      iosServerMap.put("83",new Server("121.199.36.80",8300));
      iosServerMap.put("85",new Server("121.199.36.80",8300));
      iosServerMap.put("86",new Server("121.199.36.80",8300));
      iosServerMap.put("90",new Server("121.199.36.80",8300));
      iosServerMap.put("91",new Server("121.199.36.80",8300));
      iosServerMap.put("92",new Server("121.199.36.80",8300));
      iosServerMap.put("93",new Server("121.199.36.80",8300));
      iosServerMap.put("96",new Server("121.199.36.80",8300));
      iosServerMap.put("99",new Server("121.199.36.80",8300));
      iosServerMap.put("100",new Server("121.199.36.80",8300));
      iosServerMap.put("101",new Server("121.199.36.80",8300));
      iosServerMap.put("104",new Server("121.199.36.80",8300));
      iosServerMap.put("105",new Server("121.199.36.80",8300));
      iosServerMap.put("109",new Server("121.199.36.80",8300));
      iosServerMap.put("110",new Server("121.199.36.80",8300));
      
    }
    return iosServerMap;
  }
  
  public void doIOSPeiEn(int index){
    
    for(int i = 0; i < groupSize; i++){
      int customerIndex = index + i;
      if(customerIndex == (iosCustomerList.size() - 1)){
        System.out.println(" ios customer *****: "+customerIndex);
      }
      
      if(customerIndex < iosCustomerList.size()){
        Customer cus = (Customer)iosCustomerList.get(customerIndex);
        Server server = (Server)iosServerMap.get(cus.getReginCode());
        doPeiEn(server.getIp(),server.getPort(),cus.getLoginStr());
      }
    }
  }
  
  /**
   * ===== ======== ===================================== =================================== ===== android peien
   */

  public List<Customer> initANDROIDCustomers(){
    
    if(androidCustomerList == null){
      androidCustomerList = new ArrayList<Customer>(10);
      
   // self   
//    androidCustomerList.add(new Customer("297181800",150312,"111","321364288","0000007B0001020076855A1722B01A9AF4A5A285C0FB32CDEE22B1D9E3C328571835F0A696EA3F0F22D779CB498DE16384708EA61B55F95FBA753625173A9B40F899C2E7E141419F37F3FC91E095D81C880C381A5DF32AADF56B238C335F090F364574CD5F97AC9EE3C93A87581DB2B2AD2C46B33AADE44308730894C3DA74"));  
//    androidCustomerList.add(new Customer("chiuandy",150811,"12","2385749331","000000720001020076855A17827227FE3F23F20125F232AA160D19BC751CFDB38BCF900A6312909C508426E11113CC959E2DF6270DCC10F027878727F0D683A4E6BBE6A4E1EEA4CFA8FB10E1BBCF506FD977D9BD269E2E7F2C7718F5F6779E75B90C65FEC5BD9E26F59EBDF789457685139A94AA5385"));  
//    androidCustomerList.add(new Customer("irisonciel",151103,"166","321364288","0000007E0001020076855A178272274223FA90468F4283F542671623C2783EF30D055902A5A54502A58045A54580450276EC36B163056395116372E705E1B5A06C6C3FC216AA252946F28FD12880801B1BD5A5D7DE59808FE7A51645E7F780DE16E78FDE8FD72580D767D58080123F9095AA3E78B636AA69690D41858B3394E70984"));  
//    androidCustomerList.add(new Customer("15207590993",151112,"74","974629001","0000007D0001020076855A179A0F45EBF4A57E8537D4E84B40007BAAE3F7698A58879141504E8B7EEEBEB4122F825104448239B05667C778C726A0ABFF998A2AB2212BE7C1C27096FF0B07AA0FF0F6A5222F5C3C365E49C6CE50F4898497B7021EFA4D07AAB331C39F201DB44749B44E2B0AE59211F851F99D50942BCE94F4328D"));
    androidCustomerList.add(new Customer("h13216351158",160113,"166","605988951","000000780001020076855A17827227FE3FC216AA252946F28FD128161B23028F238F251B661680D7DE8059672516D56680D745B402455902DE2316E23F9095AA3E78B636AA693536A580D70280AE01E780B3A23F23F20125F232AA160D19BC1B56FDB38BCF900A630A0A0845E0E015806D9860601285575694F31C7D"));
//    androidCustomerList.add(new Customer("asdfg31",160118,"6","512043039","000000720001020076855A17827227FE3FC216AA252946F28FD12880A5F7A545168F02F7A5D7A545E7D78FCA85E8E763B9053C6C693C3C69DF978DC48B553B55F03655DDD7DC7A72CC66ED6971814C32F08B973FBABADC7A42425540915FD7D78D724C8BD54C8B6436FEF6E47C95BBC8ADA01F3E5E50"));
    androidCustomerList.add(new Customer("728887808@qq.com",160201,"74","704033163","0000007D0001020076855A179A0F45EBF4A57E8537D4E84B40007BAAE3F7698A58879141504E8B7EEEBEB4122F825104448239B05667C778C726A0ABFF998A2AB2212BE7C1C27096FF0B07AA0FF0F6A5222F5C3C365E49C6CE50F4898497B7D526CC063479E45A856F489848494184BBE0560B7D2F1728F29D5094C9F894F4DC1E"));
//    androidCustomerList.add(new Customer("4384555",151213,"93","847161703","000000730001020076855A17827227FE3F23F20125F232AA160D19BCD026FDB38BCF900A6312909C508426E11113CC959E4AE383EFF50DF687109CCC4A87F0F0F59C0783F69C9C83502DF610272710F507B6142D9C9C7C105EF48E6883870D83070701CDC0CC14E67CF05014F18EA14785EE9153D1E799"));
    androidCustomerList.add(new Customer("15019817878",160315,"150","603383738","0000007B0001020076855A179A0FE4EBF420FE854EEFBBD6DBCBD3A21E549F13FD0E5FBC0C7AA5479EA66061BCCDC2A75988AFA582FD38D93821CFB754686155A701C23D4F53BEE9A819FC34505E126A1686798E80F25FDD77830398A6765678C7AA9D257DB5E8851648567A495D4108613A7A31CA17FAE7FD4AE294AB1B6D"));
    androidCustomerList.add(new Customer("masheng2913@163.com",160316,"177","5741983","0000007D0001020076855A179A0F45EBF4A57E8537D4E84B40007B78E383A9C4A475697CC416048B7EEE0FCF49F19B4F36448239B05678D224CEB36A0DC184D6A3ED5818DC4F812EE9A88365926850210F3DCBD46F7ACC0AA010735A60A2D1A00774669213B3BD85442C4E1276BF2F6C4ECC03CC28AB0724C255856D3820C1B34C"));
    androidCustomerList.add(new Customer("juan43",160404,"148","745893736","000000730001020076855A17827227FE3FC216AA252946F28FD128800216E71BA602801616B380A523E767DE1623E7021B2302E7451BE7028FD7F74E671623C2783EF30D823E011B36E7458C3F23F20125F232AA160D19BC60B1FDB38BCF900A630A736EBF90085915EEE7082F6060128539ED53DAE4F5"));
//    androidCustomerList.add(new Customer("18057081997",160111,"99","512043039","0000007E0001020076855A179AB01A9AF4A5A285C0FB32CDEE22B1F1BF39BC1D0502AEAE2028E03C65310A82201CDF13F98D21895F6CEA7463D8A207869B4F741ED484DE48D248D6DEE1ED17516149073E6D5F529DE1FCD33535BCEB91D10C4862412B2771EAB05FCDF4465FD0FAEDE0432EAB61AB3CAAE5883BA11D0FD9203E4B68"));
    androidCustomerList.add(new Customer("18057081997",160111,"106","512043039","0000007D0001020076855A179A0FFDEB76DAA28593EFBB0CCA7F316931366A6C256D0D6944ADA5FEC44BFB828D91F62152951877DE15E1FC296279049915DF7A14DA1943FA3821C5F70C383A21892C4537D3162C6AB9BF93724A35DDB0453E67F92F4D0768E9E3C89C22ED4AB0697FD29F973B188B99CA7B60E9AED286201755D6"));
    androidCustomerList.add(new Customer("13120993109",160123,"194","847161703","0000007C0001020076855A1722B0FDEB76DAA28593E37EA1AF0DB0458764C2E8F9A63641D671DA2DFB9D5ACBAAD1A774DF013F2DBCCBE9CC5E7DB33A17CBD8A902FC9F6CE3AE9B72EC3D3A603F6707682F03BCE3F0599058D454166F124AAE61B94FB198DA77E60BA12E4D358912E8A77A687274F3BD54AA3208F15A20A02768"));
    androidCustomerList.add(new Customer("asdfg47",160130,"6","443264228","000000730001020076855A1722B02AEBC320A285C693803D6A4E002F49435A9B583ED5291CC393294AA0CD14D09FCBD8DB7B15CD034011B6D5615C724D3953A2DF50E6A0F98FA012EA115AC1DC5EAC0969C0E8CE10B57EA7F5F0A4762338A23E06F4E8BB562BFC72836B0430633CFBEC851B44949618FB"));
    androidCustomerList.add(new Customer("asd555yan",160130,"140","674433653","0000007C0001020076855A1722B0F7EBF420A28593EFBB1CAD444E3CCDE0B428D1D32536C93D1C2AA55E17CB6486DCF61A541B3A89E6CEDA0E7DA3D36BFF27C91D75B6BB7040DC04D8B52778B052BBDD29A2202A322C85333494608E1E836D2D0054C83C11B61132447A2EFF00D54730165A779E99240DFAA3AE89EFDAD756D7"));

    
    // shuijiun
//    androidCustomerList.add(new Customer("狂八佐助",151120,"100","","123456m"));
//    androidCustomerList.add(new Customer("狂八佐助",151120,"97","","123456m"));
//    androidCustomerList.add(new Customer("狂八佐助",151120,"83","","123456m"));
    
    //100区:id:小纲手 ☆  队伍15级 密码是133456m  360 
    //97区:id:掉进深坑里  队伍13级
    //83区:id:织田信长  队伍13级 
    
    if(true){
    	return androidCustomerList;
    }
    
      // shui jun free
//    androidCustomerList.add(new Customer("pangxianer1",140527,"47","NAN","000000780001020076855A1782722742671623C2783EF30D6C78B436256D0F163625F202FE3F9095AA3E78B636AA69697223FA90468F4283F5FE3F23F20125F232AA160D19BCBE926ECBE7E478F7CF66902E3ED2F4F40F0F3120DEB696F2E0B6F436804545D23BCBB6EE45DED20FEEA140C6F42D1285B718200370F6"));  
//    androidCustomerList.add(new Customer("chiuandy",140527,"12","NAN","000000730001020076855A178272274223FA90468F4283F5FE3F23F20125F232AA160D19BC75BA6ECBE7E478F7CF66902E3E45F23EF490DE583E313136EE9A7AD7D745D7597FB4D7B41B3A80F7A5B4E7E23F9095AA3E78B636AA696C16C20F291B36255B42671623C2783EF30D82834185B41F94AA41BB"));  
//    androidCustomerList.add(new Customer("160442132",140525,"7","NAN","0000007B0001020076855A1722B025EBF420A28593EFBB8CA7F42218CE881C5F4D2567838E46BB8CC450119B4453DFAE64FC807DE0EA1C93CFCC193A91D5CD38ADE9DCFC9E466BD2ACA71B789D761E4EDA76C4A12D761021FCA46071D5996E4220CB27EBE68ADE0D00BB6E20E2098237CEAA7A3B2FD76634EADA132002EE95")); 
//    androidCustomerList.add(new Customer("36797956",140525,"9","NAN","0000007B0001020076855A1722B02AEBF420A285C6EFBBEBD34E00595A781A4704D2262DB34F7E9FB0C007D3684DDF9FCB6977D124576DDDB53FE95FE04798C6A3F6EE20D1499798C93E41534B9133CF15CF7CDF0E64DC4D3591AC18D8CCFFB5B54AD866F867853AE5A37EC2D4165390B00A2675A7A94AD8DC58B694DC98F1")); 
      androidCustomerList.add(new Customer("164261027",140525,"9","NAN","0000007D0001020076855A179ADF2AEBF420A285C6EF803D17C36199B074194F903DFF2B19C0A53D413A75BAC19E3AA048C2FF8EA63E74F018F00B191CAA70FF72BBAB731A4DAE29D6655DC2A21E8C938255EFAD682166F8614621796A43DC213B017071F099A5276583D665346F56374D3A2CD6316CE0FC30E9C8E789943A6235")); 
//      androidCustomerList.add(new Customer("zhuyinjll",140525,"53","NAN","000000760001020076855A17827227FE3FC216AA252946F28FD12880E7B4161BD780D566A5A5E7593AA51B028002DE805945D745801B0245DE8F02E23F23F20125F232AA160D19BCBE2EFDB38BCF900A630A736EBF90085915EEE7082F1B6E01D86602E778F3D9610845E0E015806D9860631285192D53D2610C")); 
      androidCustomerList.add(new Customer("heavry",140527,"11","NAN","000000730001020076855A178272274223FA90468F4283F5FE3FC216AA252946F28FD12816E78045A5A61B3AA516800266A5DE80B3808F452316D7A5DEDE1B162523DE24FE3F9095AA3E78B636AA69823EB41B29F3F8FE3F23F20125F232AA160D19BC75100845E0E015806D9860C312856152536E3543"));  
      androidCustomerList.add(new Customer("ChesterLee",140527,"18","NAN","000000760001020076855A1722B01A9AF4A5A285C0E0E83FF0C4F4832FBBF15FF28BD19FED3B9FCDE3ACBD5D83CB9D8BBD2189570DD0B50734139B61DF351AF792507118FF3AFC0CA799150D722F4656C2B19253E28705B51832FB9EBBEC5335B4F4DDD977CBF24797F02FD504C8F7C7D0554EDCF1D020093294"));  
//      androidCustomerList.add(new Customer("xiu111111",140527,"46","NAN","000000710001020076855A17827227FE3F23F20125F232AA160D19BCBE236EBF90085915EEE7082F1B6E963A4CA27E6E05F38F325F39BB0007E7B7BC5F258F6311193DF205F2299A365F6C1B1B05F05F60B756F24040297E16F03E7E165F9A7E403605646C3535BC25BC8F31E1BBC84A34537F4232"));  
//      androidCustomerList.add(new Customer("nqsd123",140527,"7","NAN","000000730001020076855A1722B0FDEB9820A28593EF8790667F4E96768B7BD3EE27EFB0C047DB4EBD447430E4C699626616CC6E584365DA146BCEED444AC4730C9122F9444193275C76D3216672BF91BD5C28724FDB63268F63C4ED3CF2D2EA99158AC3A93E5EF89C246434C41C45B59FEB5E94770283"));
//      androidCustomerList.add(new Customer("jingqia999",141026,"15","NAN","000000770001020076855A17827227FE3FC216AA252946F28FD12880238F45B4F7B4252325021B66A5A6A645B425F716DEDEE72523803A1625A6D7E23F23F20125F232AA160D19BC7584FDB38BCF900A630A736EBF90085915EEE7082F1B80F3E77821A5E7D24040F00845E0E015806D9860631285A896DA58348A"));       
      androidCustomerList.add(new Customer("weilan1206",141026,"7","NAN","000000770001020076855A17827227FE3FC216AA252946F28FD1288002A5678F4523451B8F45A6DEF716E7E7D71B1B3A80DED566A5DED78F80E702FE3F23F20125F232AA160D19BC759CFDB38BCF900A630A736EBF90085915EEE7082F1B66DDDEE7D945AA930FC6560845E0E015806D9860631285CBAC94BECC61")); 
//      androidCustomerList.add(new Customer("160972620",141026,"7","NAN","0000007D0001020076855A179ADF2AEBF420A285C6EF803D17C36199B0FD29B995D2262DD1335CEBE83A409DF1A32994BF281488C08263D11839C57470A35382019BFB511ED414C856A92776AE9D7444D223D9EEF8518679A4643341749B54A3737FC7F4E95CFFE12D837920D5B8E556B0A8CC96D2F44E31419BAE671120A1A61E")); 
//      androidCustomerList.add(new Customer("xuzep8",141026,"14","NAN","000000730001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA6982FA90FA8FF27FA23F23F20125F232AA160D19BC75236ECBE7E478F7CF66902E3ED2368019F40F3B7A0F90B640EED2A5D090E058D220B6CBDE3EA5194020D23B902E0845E0E015806D9860B112851FFB53ECB536"));  
//      androidCustomerList.add(new Customer("zw2436c",141026,"9","NAN","000000730001020076855A1722B0FDEBE5207E85C073BE7EB6EF00E641E6F0F7D92B29ABF8BB0C2E71E9FD5103D0AED3210DED8D0DA7FC0E86D067B2627E4589314C84070B0ABCEC5256E981BAC3280DF3248FFF599B5EB67E5C78E776831637FBEE7891EEBA5897002B2DAB921A083908788553E3D103"));  
//      androidCustomerList.add(new Customer("zhuyinjll",140901,"32","NAN","000000750001020076855A17827227FE3F23F20125F232AA160D19BCBE48FDB38BCF900A6312909C508426E11113CC959E4A27EF2D10874AF5860DE327AF270D10F54AF5074AAF8387834A10F58307CCF501CDC0CC14E67CF05014F177CD2E73135050262E35E5142D9C9C7C105EF48EA1478520E753913541")); 
      
      // shui jun 35
//    androidCustomerList.add(new Customer("203402076",140901,"12","","0000007C0001020076855A1722B045EBE5207E8537DAD47EB6930059ED24F73CB03126B98C45E2717B96EE06938A9BFBFF37549EA129E9534F27693B7D2418A475BE749B53EFE39B534B3C029F5774C9B94BAC5E74714891D172294DA6E1AE077E2C46C496AE35794F525B6B92CAF95252CDEE75AD4C0DD9BC9F002094DFE8A3")); 
//    androidCustomerList.add(new Customer("a4242096",141026,"43","","000000750001020076855A1782722742671623C2783EF30D6C1680450245027F808223FA90468F4283F5FE3FC216AA252946F28FD1286666A51BD7E7CAB4DEE780670280A53A802580E71B162545D725DE805923A6D7653F9095AA3E78B636AA69695F3F23F20125F232AA160D19BCBE10128544EB1FC94D3C"));   
//    androidCustomerList.add(new Customer("cqx134",150109,"87","","000000730001020076855A17827227FE3F23F20125F232AA160D19BCD0B80845E0E015806D986059397FF49331976ECBE7E478F7CF66902E3E45DE58D229B620190FA5D2407AC60FA5D029E0DED090D2DDA1294546583E317A2DFDB38BCF900A630A736EBF90085915EEE7082F60B1128580AC536667FE"));          
//    androidCustomerList.add(new Customer("wanhong2001",150105,"90","","000000770001020076855A1782722742671623C2783EF30D6C786736DF0FAA255902A5A5FE3FC216AA252946F28FD1288002E7A5E7D7591BDE59597F16A6D6408E5BF6BA9E8E8C7746D6A8F6779ED63B5BE258D42BCD5656F4A15BD4D15BD4EC50462974990E2C65FEC5BD9E26F59EBDF74545768596DE53AB478B"));    
//      androidCustomerList.add(new Customer("nan1888888888",150601,"11","","000000730001020076855A17827227FE3FC216AA252946F28FD12880E7251BE7456702A61625D516A6A623DEDEDE80231B2302DE801B80D5A58FDE4223FA90468F4283F5FE3F9095AA3E78B636AA6935C22536E76648C88D724C8BD54C8B6436FEF6E47CF8A36C3535BC25BC8F31CBBBC82BAC207C7554"));  
      androidCustomerList.add(new Customer("wwwwenw",150811,"98","","000000720001020076855A1782722742671623C2783EF30D82789FC44C91578D724C8BD54C8B6436FEF6E47CC39863E7B7BC5F258F6311193DB7F0115629365F051B5FF2161B9A35113629B740362936F016F07E11165FB711B15D326F28BC2502BCBC312D6E05F38F325F39BB4B4BC82D2F20208FBB"));     
//      androidCustomerList.add(new Customer("mei_tesibangwei",150127,"84","","0000007F0001020076855A179AF425EBF420A28593E37E63AD8DC4696E4B1C67ABD16C540DA8A55C8CE8715BEC4492DFFFFFC94F784C13DF7D66B9D59196482A93BAE508CB681CB62FE4EA13752347B37518A8CA5EF63A4F0437364825422B93CBF4A29303458B744A6A650C7B0439B635176E9939FC05DDEAEA59898593C02039BCCC"));  
      androidCustomerList.add(new Customer("18935303890",150901,"57","","0000007D0001020076855A1722B0E4EBF420FE854EE37E71FDCB440C826F88CE3A56847AE3C95CD6B0C007DD9C625770521CD26E5E7DAA1728F2B5978516487EC8E42032D9A2897DCA24CA0B23525454CE9D5590F7681BA7588D7A70C60FF97773AA0A27F56FF44D85DF30ED49DF112440A7AF4C676889A58E55C858A794985823"));   
      androidCustomerList.add(new Customer("jubao35381",150802,"31","","0000007D0001020076855A1722B0FDEBF4A57E85C0227B7EA793DAF1E3F7B89D1BAC23C49C1D90AE2C8F057A8D6E8CFD549AD810EE980ECC1E7841E32C9396205F3EDD82270D1614FCF5C095045B21051AC55ADCDC773ABDAC0A8866CF6DB6D4421A64A1B7832247A13960E50C048B1824105E7DAA1728F2E9CFDC8D7694DC9D57"));  
//      androidCustomerList.add(new Customer("42668995@qq.com",150601,"123","","0000007C0001020076855A179AB0250CF4A5A285C0765F8F1A440A71EDE6B66DADF9A6EE142B414546B7B9DD9C62576D54606C6CFD405DF93440F9BCFF49213B7D549FDA150EC76C3019C42CE0D840072141AEBF828305233F9CFF56A743B8AA8F8FFAEEBB2CEA23784E3698542388F516874C84FA2634A3C688600E2005AD3B"));   
//      androidCustomerList.add(new Customer("qq595439478",150624,"132","","0000007C0001020076855A179A3645EBF4A57E8537227B7EA7C6EFAC7FDB2320ED42580FA69A953711393F7289A0631F81C859569E3775D3FA5BE49637976A12A085D3BB232CBA887D93E35651CF081A9DFC04C2FA2978F651B870146D79B34D5CF977876D461445282D506BCC45B0E420978BDF15F39C8602089354208778B8"));  
//      androidCustomerList.add(new Customer("cody198819",150427,"21","","000000770001020076855A17827227FE3FC216AA252946F28FD1288023E7E723E7674580D7A5DE457F16801B23D7E7238F0266B40280D767E723D54E671623C2783EF30D6C1BC2675B8066667FA5664223FA90468F4283F5FE3F9095AA3E78B636AA69696C3F23F20125F232AA160D19BC2E2E1285069653F8894D"));   
//      androidCustomerList.add(new Customer("fOrest_imba",150528,"15","","000000780001020076855A17827227FE3FC216AA252946F28FD128804502598F80E7457F1625DE59A6B316A61B16D7DE8F1680251BCA80D7163A808D671623C2783EF30D6C67BC788FF23F2BAA361B8FFE3F9095AA3E78B636AA69696C3F23F20125F232AA160D19BC7584FDB38BCF900A630A0A1285AD74DA9A1A35"));  
//      androidCustomerList.add(new Customer("32886144",150124,"47","","000000740001020076855a17827227fe3f23f20125f232aa160d19bcbe84fdb38bcf900a6312909c508426e11113cc959e2df02dcc4a87078327f5ccf627e386eff5f5ef2df0cc4aaf869c9cf527aff5870f142d9c9c7c105ef4774a87f50d0d274a8396cdc0cc14e67cf05014f18ea147851f5b1f7acc35"));   
//      androidCustomerList.add(new Customer("13801664433",150627,"87","","0000007c0001020076855a1722b0e4ebf420fe854ee37e71fdcb44563f5461e85ed92f0d47995cd6b09111828d91f6741568bb60740b74b39213b36cc8a5c80276f2a28977ee32d8a01aa8b0bb242e181bff5ef762ce504b9e2d6b79379eefa541cc374772c922eaa6e02421ad7457e6ceacd39253bdb2c127ebef8f94d1d95b"));    
//      androidCustomerList.add(new Customer("751392184",150628,"137","","000000720001020076855a1722b01a9a4820fe85c6efbb0cf229e14112418fd945a0927eb1e3844aed5962d5701ea747fd40104fe412ce87fe624dcb8d752f13a4afb043ced81fa3eecf5f931aa29550f3d1139ad8e2c30a0485918526fee67074db6ef379f6a2ca69198f1b58058f8831c4539a87fd"));        
//      androidCustomerList.add(new Customer("15812896535",150601,"68","","0000007c0001020076855a1722b045eb76daa285c6efbb321af4c41831291a63617578456e9f1ce8a5749cec8988dffc5086d7afdfe9c20e7d388d5282e0ea1ca8b6bb7040dc04d8b5276747374784ddc676d2e79f974451ff6680ad3eb50c668cea0dc82b4dc0128f48da9c2c25567adf6b2d97e9fc1cb39e1d0dee94799221"));     
//      androidCustomerList.add(new Customer("13570809693",150115,"137","","0000007d0001020076855a17827227fe3f23f20125f232aa160d19bcb3116ebf90085915eee7082f1b330fc6c6310fc6937ac6a2496056f21bf23e3905f38f325f39bb007902b7bc5f258f6311193d9a291660055f35b74011297e3e40299a7e3e36f029f06036b7114056293560f2646c3535bc25bc8f31e1bbc84fd8200911e1"));          
//      androidCustomerList.add(new Customer("aaaa19950816",150601,"50","","000000770001020076855a17827227fe3f23f20125f232aa160d19bcbea66ebf90085915eee7082f1b0212477ef2f21b9a297e3e646c3535bc25bc8f31e19863e7b7bc5f258f6311193d9a35299af2f0f005607e567e11167e9a051b112929f005f23e057e601b5f35b76e05f38f325f39bb4b4bc840a4949257e5"));      
      androidCustomerList.add(new Customer("15150612830",150723,"108","","0000007f0001020076855a179adff7ebf420a28593efbb4066c3ae148d5709b45b69c1e57a85929ac393cd5bec8255f209ff52389fd1ca55907ba7f40e4dbcfec80aa74282a12ad53c64b13da158d627d32769c9f73b6f6b6aa3923e019bfb511ed41447c65fbfd4cf12c16391ae5c6433eed38f1f718fa9781b09069fc91020c90607"));               
//      androidCustomerList.add(new Customer("13909404191",150425,"158","","0000007D0001020076855A179A7A45EBF420FE8593E37E71E6CEC6DACD8D26B26FF1847A376BBBD67B7B4C5A6E84DFFBB26A799243B3F65E865BF5C9AEFA9F938EDCD9D80DE89F0782E99E0E672FBCEC21D35586D009E843F2E43A6874D1E051855D47E597480096603FDB5784717EE3A2F17D7CF6D5BD7767729FD0379420F9EF"));             
//      androidCustomerList.add(new Customer("bushushi",150301,"139","","000000730001020076855A17827227FE3FC216AA252946F28FD1288024B4168FA680CAB48FE76780DEB4A6DE80F78080B3B4DECAA55945CA80A5D5A23F9095AA3E78B636AA696C1601F2C2858B16814C32F08B973FBABF2A5BD4D15BD4EC504629749934BCD977D9BD269E2E7FF7457685641694ADF029"));  
//      androidCustomerList.add(new Customer("49498375",150401,"4","","000000740001020076855A17827227FE3FC216AA252946F28FD12866A5D702DE45802545B4A602DE66A52323416F3E56F2B77E9A059AF011167E9863056395116372E705E1B5A0468D671623C2783EF30D6C80F780F766A5D759E23F9095AA3E78B636AA69824E23FA90468F4283F5F54185DF4A1F4A735F"));  
      androidCustomerList.add(new Customer("jojo32",150901,"47","","000000720001020076855A17827227FE3F23F20125F232AA160D19BCBE84FDB38BCF900A630A736EBF90085915EEE7082F6059F308F3B231B46ECBE7E478F7CF66902E3ED2C68019903E903E403BC67AC696DEB696E0120F59E73A168FA602D71B8F028D671623C2783EF30D696941855C0C53ED21C9"));   
      androidCustomerList.add(new Customer("jesus_x@163.com",150825,"177","","0000007B0001020076855A1722B0F7EBF420A28593EFBBD6DBCB6A22231E099F767D7C831EA70DBB981C1182F1A34FC2991877D1706F30DD033FE981B6C5E6A5BA7AFE27FB50284856427A6CD450A73F6BF26B739DB770095042E7DBA475C29129A743717C2CC612BF618547B612A84A24FC92C7CC34B373EA3F2220C8F6BE"));     
//      androidCustomerList.add(new Customer("18717193305",150608,"162","","0000007C0001020076855A179A0F45EBF4A57E8537D4E84B40C82B4D8B7D6C836256FF2C8FC3B102EFB977D4DD030945E560BDD6E74DD47C8B6257FDA0998A2AFC4F35E7C13F7010E91E83656A5022D8AAD51E29413D494727E8E3162D5A3B66E41378FA1746859CAE644712BB3F0093AAF1CC28AB0B24C25585BA2C948F8794"));    
//      androidCustomerList.add(new Customer("176517882@qq.com",150609,"178","","0000007F0001020076855A17827227FE3FC216AA252946F28FD128801BA68FDE1B8FA6E7A5E75959F7B425164502D78F3A16A6CAB48FDEA6E7258F5F3F9095AA3E78B636AA69055902A5A5D702A58045A5E780D780A5DE45A5028D23FA90468F428341105013B9F613FE1450F4D5BDE65FEA8357578886922CA4A43C853F3D20C2F39A"));  
//      androidCustomerList.add(new Customer("test0008",150601,"23","","000000720001020076855A1722B02A0C76457E85370C40B133C3B4085FC72E92FFB6F01B11F9B034403064CB15C53350C9890D4CAC71ABBE556B8EA4253266A141BDF458450FE245F616EE65C2ACEE0674E7EA024BD685E07ED224CE13A36A6B1B63A01689AD3F9166081AD0FC9109EA784A942B1E57"));       
//      androidCustomerList.add(new Customer("214485070",150701,"20","","0000007A0001020076855A1722B0FDEBF4A57E85C0D4F88BF81FBB571C81B3609C1E9C4139A3BF7EEE0F1B126182130491593E8E1FC57375141004A7103CBDB4CC4B7681846F53EE76A2478B7F64E0FF7995F556748674CA9213B35D720C66029620C8F7B20F1FA6B2A65458EBB2B2440AA217FB1A1F3E94A7EB209A1135"));  
      androidCustomerList.add(new Customer("xuyongguo58",150926,"144","","000000770001020076855A1722B045EB9820A28537A67EB18C5EC03B59564857C5217A30D55F32693E0C8E52E654D5ADA886B11769B4EF5DD8551089BB6645AF2238AC9C60B19952CB9A483A54B39B0769E97303CC425229EA8FDAFED58122DFE858D146D003A02067D262624FB35A271CBA47EC85D1C82067F9D2")); 
//      androidCustomerList.add(new Customer("15527048964",150428,"127","","0000007B0001020076855A1722B045EBE5207E8537DABE7EB69300ACF5B3B02AF50425966179E27189D4B4D0F19B51197459568EA7404D409D571555231FC5ABA41D8E6E09B726B58BD02353205A37767A42E59C47C3ABDF3A5708103151BF585FDAE9A7DAC67EE7C547EF6C8A1B962944C59C9B433313BC9F08AC94B3E22A"));     
//      androidCustomerList.add(new Customer("lx56388",150508,"5","","000000720001020076855A1722B0B09AC3A57E8537D4E8C9C6C3AC8C89BFA8DC02A6D112C3962A11504145CDF106853BB3B8F9423F95FA45817DA30F589EF5526B8E86EE06C9FB571BA1D781FE219708458015EAA9183B5F26F883188C204E85C491720BBB498D9A53A03708125F109F6F271F8B8261"));  
      androidCustomerList.add(new Customer("0909327",150802,"100","","000000740001020076855A178272274223FA90468F4283F5FE3FC216AA252946F28FD128805923DE2345D7DE80A6E73A1645A68F230266168F16E71BDED51616D76680679E671623C2783EF30D82597F807F80D702653F9095AA3E78B636AA6982A23F23F20125F232AA160D19BCDE9812851C34537EF799"));  
      androidCustomerList.add(new Customer("LOL终结者",150702,"136","","0000007D0001020076855A179A0FFDEBF4A57E85C0D4E84B40007B61E3B3B867330A35BF80DE90AE2C0C46068D0EF2AD5A82E7C35CB2404D40AFC9E41FB2FFEF21244A4C15BCD87A99DED2624414DDD8BDFE6C8AD0235348868996CE313FE7566D3414B3BD13D3B18529C8DB2047CE906448BD2D11338F62BA6B85EDF79417683D"));        
//      androidCustomerList.add(new Customer("njzxlee1112",150508,"197","","000000770001020076855A1722B045EBC3A57E8537D4F802C6C6EF7BB2C6EF850B7CC45669C0F8F0206CA272C7FF5EBDFCE2750274E1969E71E115DB51AB2C0DC6F627AC9D691D03508738777C3C80E25F753C276B565452E9A5AE3769D7A60B95DAD4FA3EC257D556B00899085E6F2E225D754C9483F220290BBE"));  
//      androidCustomerList.add(new Customer("306073729",150610,"168","","0000007C0001020076855A178272274223FA90468F4283F5FE3F23F20125F232AA160D19BC1BC40845E0E015806D981BD20F0FC6937AC64CC60FC6C693404CC6C06ECBE7E478F7CF66902E3ED22945B64CF431C629E04619CBA14020D0F4939396A1EE3E3B4C7A0F969A0FA23F9095AA3E78B636AA69696941855B6094D76CDF"));   
//      androidCustomerList.add(new Customer("13427596435",150514,"68","","0000007D0001020076855A1722B02AEBF420A285C6EFBB8317C39F848DD12622CFAC7C839DD5BBEBC47B4C64CB15C5335086343AA0BABDA6463A2EACC5F709D25E0FC42B33D58569EB843F46BA14FA3B12D82FC83E7D2418A475BEFFCA85F5C82CC8B8D4EEBD2BF42B62B3A7A9CED819CF1575B28FDBA0EA236BC842D894E94060"));     
//      androidCustomerList.add(new Customer("gan19890823",150620,"119","","000000770001020076855A1722B0F7EBF420A28593E37E71FDCBA564A4CCD7D9566B5F283DD90DA210F3DE88CDEADF33701C73501B171A9B40F8991830056AB00E59E62142326BA29D0B7EC2C89355851329EF70787FCC776325ED000591DA73FD0762653A3AA0E184584FAA7C53A3AB229F754C94E7D394460341"));       
      androidCustomerList.add(new Customer("xingqu154",150708,"154","","000000740001020076855A179A0F45EBC320FE8593EF807EA5ED4E96A264A2F1847A4E9F9A717B6BE8828DDD263AFF826CF05BE4B778B7CD4082919836AC8A903115DF346994CCA3DF7482A8D1B844D2D612669F7F0B47D26EE305406A5649C74CC2D07306B51FD69CE9C074249213C5C43302A9539599C7"));      
//      androidCustomerList.add(new Customer("zcl981111",150608,"17","","000000740001020076855A17827227FE3FC216AA252946F28FD1288002A5162523D7D5808023DE6702668016253A80D7D7B43AB4458FD7672566B4E23F9095AA3E78B636AA696C1E8FC2F766A54176DC7A42425540915FD7D78D724C8BD54C8B6436FEF6E47C4B3905F38F325F39BB4B4BC8AEAE532DFC3A"));   
//      androidCustomerList.add(new Customer("halin87",150608,"17","","000000730001020076855A1722B0250CC3A5A285C0D6E88C278D9665B69F5B3FE42B568297AEC7118EE10E6E927D74B2387E3135C5E5D5421B6BDCE3B2117CB3C513EDEA4EE726EC0D64AB41FD48532B06BE1034B53674B0AD2C38E0413D72FEA8C71F213C03C2F1E26C0516CC3D0EC4A42A1953403A06"));     
      androidCustomerList.add(new Customer("15207590993",151024,"74","","0000007D0001020076855A179A0F45EBF4A57E8537D4E84B40007BAAE3F7698A58879141504E8B7EEEBEB4122F825104448239B05667C778C726A0ABFF998A2AB2212BE7C1C27096FF0B07AA0FF0F6A5222F5C3C365E49C6CE50F4898497B7021EFA4D07AAB331C39F201DB44749B44E2B0AE59211F851F99D50942BCE94F4328D"));     
//      androidCustomerList.add(new Customer("15884387881",150608,"108","","0000007D0001020076855A1722B045EB76DAA285C6E37EA14F0DB0E787AD1982E325C10D83A2D71CE8A53D18A09CB2D5092710D046A84CB5DBD31976298CEFA20048A5EFBC973AA0BA2F35112735DCF688F3A3646B4F3E7D2418A475BEFFA551C94CCBB53FE9F4D78A4E3D5A9703666A0C7FD57F26CE64C2DA7D08643794B7627F"));    
//      androidCustomerList.add(new Customer("狂八佐助",150609,"57","","0000007B0001020076855A179AF4FD0C7645A285C076F083247F0F467A274D25669BEE02A6BAA60D963AFBEC4492DF92C968104FE13821B631166B22B1975D9E47B949BCF92F6CFC0CF21226288F74B63968C7643CC8A5C802765F76D4BA03FE849D94FF4CFC911D7D1725A713DF81BCE436E953773528819F01642098A296"));       
//      androidCustomerList.add(new Customer("zhouxilu@sina.com",150609,"138","","0000007E0001020076855A179A8E45EBF4A57E8537227B7EA7C6EF187FCB465BC50A26BF4EDB90AE2C5705985AA4B28361E6AFBB60744A1A249213B3F185A5226A3570C8E464B7C197AFE2FF329295D5C1A0B2AFF22410E9A883AA56898E472D48456C11F63DFB8CDABF0B7A501D2B39C7B3AB33906D1E86A58E558534B0209EAA49"));      
      androidCustomerList.add(new Customer("nightjar",150715,"3","","000000740001020076855A1722B02AEBF420A22293EFBB8CA7F480CD974ACF6ED3EE27C67C7A974A1C5BEC4453DFAE64FCA501B7251EEEFD717CE507E5A0D69C257F88CAE12B307C484E72467ADF0A110774E2E4D0F0946FEED7A4245E3540F89DA8C3B24E51399499154D723C16C311061D5F7B944E106C")); 
//      androidCustomerList.add(new Customer("18971081048",150619,"137","","0000007D0001020076855A17827227FE3F23F20125F232AA160D19BCB3F0FDB38BCF900A6312909C508426E11113CC959E4AE30D079CF02D9CF6F6830D4AF62786E34A27F6F50A87F584F6F027F5508787DACDC0CC14E67CF05014F1A8AFF5E3E387F5E34A83E3477ABA8EF6A8F69E552BB6B6A375C95887CD5685263220FFB954"));    
  
//      androidCustomerList.add(new Customer("xb446293791",150625,"28","","000000780001020076855A17827227FE3FC216AA252946F28FD1288067A6DE8045B4A680B3807F8002808025DEB4DE1B232545DEA5238F1B1B45236C3F23F20125F232AA160D19BC2E610845E0E015806D981B9196B67A7A360F40313B40C06EBF90085915EEE7082F60C3FDB38BCF900A630A0A1285E36094D69AB0"));  
//      androidCustomerList.add(new Customer("a3555555",150630,"3","","000000700001020076855A171FB02A0C4820A28537986AC061470883F65162925FA8515AB469B3CE89E47D9F413ECCE26C2CF9E81021F38459BE5A1802586972BF6EF104694E60F9D367EE15223567E84DF2791FDE01226B071FE808EB0CDB98346D4000F0D5D79D8EE7F5C2199F78341F2D7963"));   
      androidCustomerList.add(new Customer("YINZHIKAI",151005,"3","","000000760001020076855A17827227FE3F9095AA3E78B636AA696C03D3D3636F6AD3ADD342671623C2783EF30D695F3F23F20125F232AA160D19BC752DFDB38BCF900A630A736ECBE7E478F7CF66902E3ED29393F4DD463EF44040C6DD46DEDE197A023E903E80D2C693310F317AF42019C41285923153FAA6BD")); 
      androidCustomerList.add(new Customer("13385990760",150705,"138","","0000007C0001020076855A1722B0FDEB76DAA28593EFBB0CCA7F4EA2CAE57ED525F9A67E15DEFEF427F6DE8E3AC9F274B205352D957444C0E8CE214D13BB9F65C971BDC8D8C85E3D17A992455A9512F687C8A0D57354B17CB154DE1499152FFC6E08524321B65D76A2C4F7379E078CE8E7B7E0CF4C2FD7CF5FEAA35594366E86"));     
      androidCustomerList.add(new Customer("mycwudi",150705,"100","","0000007C0001020076855A1722B0FDEB76DAA28593E37EA1AF0DB045879EAEA49021C321501F2B7BA27EDF5C1DBDB304FF68103EA6B9AB9261E46C48D937BA7A492B2B4740729221E4AB90FA17E59C08D8DFF556AB57EABD88F370827009A951C9B94B74AAB5767A9048BFA65062BBC69F547192002EF5004ADCEC0494F68131"));  
      androidCustomerList.add(new Customer("wangkk39",150710,"93","","000000740001020076855A17827227FE3F23F20125F232AA160D19BCD026FDB38BCF900A630A0A0845E0E015806D981B7FDD4578E4FDAB31116EBF90085915EEE7082F601B6ECBE7E478F7CF66902E3ED229DEDED093937A293E9396A131C696F2E0E0F219367A8046E0120F16021B0D4185A0C82008518D"));   
//      androidCustomerList.add(new Customer("mcvip99",150610,"15","","000000720001020076855A1722B01A9A4820A28593EFBB0C2929E14180537BED5FE5BD80FEC4E0D231392D69E048542BF6B63CFA19D035EF674ED89F376ADFBF1B6D16CD372F122EB0FC46E4E1C9204FB428323E52E4F2FC2E9CE33028DACCE75EFCC78D8F1AB3578AD52E108F9EC46B04C7536ADB01"));  
      androidCustomerList.add(new Customer("174699238@qq.com",150829,"88","","0000007C0001020076855A1722B0F7EBF420A28593EFBB1CAD444EE7CDEBCEA6AFEDBC7836B1FD9AC3E390DE88CDEA7D1A541B3A897DB56DB5AC0AB7883B186B60C2354581B7AB1A6040F8214148BBC6AC1D00DDD4E8973218C239C7A9E1389D6FF8DDCA1EB51C6E9836F8444F9073C0C8EE0E57768C1248F5DC883420C4987F"));  
      androidCustomerList.add(new Customer("15938258464",150729,"180","","0000007B0001020076855A1722B02AEBF420A22293EFBB8CA7F4225EA5895218E31624847A7E2E5C8CC47B66EC4484DFA9B2A4BA829D55011724F604B88C2EA68B16DA401B4137D3C041A822460EAD1434B2B3BD13042EC8AABF858D37412767AF1BC767A70A148AB2C102A86868DBBB289DC91E51F99D10AE1A929453358C"));  
      androidCustomerList.add(new Customer("sj207624955",150730,"147","","0000007B0001020076855A1722B0F7EBF420A28593A29AAE1F3D6432AFBDB0DA1F6B5FAE62630DA27E758E2F3809E0528450CC33CFEACF08950E4D88D91A2E181B69548B549825A2D6B6904589E18BDF29EB2B349189D68442FD0E028F1909686DD78F7D0E778547475E1DEBDDDB942B5558452421EBC74C9475D02051CE65"));   
//      androidCustomerList.add(new Customer("kd04291",150606,"87","","0000007D0001020076855A1722B0F7EBF420A28593EFBB1CAD444E3CCD2FC33256B8557E2821531C2AA5CC10889C15C58174E5433677A73CA70A62B7700994C5CE60D675361AD05EA7398CFF2A8561E3D96ABBF20E664827DFAAE8FF688BBDCC83E190EEBD3111446367731E02F7EF694E9BAD779E79261EB3E9AEE1FA20ECDBD3"));    
//      androidCustomerList.add(new Customer("15237161366@163.com",150626,"73","","0000007C0001020076855A179A0F45EBF4A57E8537D4E84B40007BAAE3898758C78E9941398EBF7EEE901B122F3824318D8E28C34B59C768C762A0FD1FB280CE8A59AD9EB2A3736E541BA75F5EA2A6E7126D8FF0F4B8461B6C5D979B9048827DD018A7398C742A85E86A42C8DD5A560076C2188C5B74F99D50941AF820486521"));    
//      androidCustomerList.add(new Customer("scl87015570",150701,"165","","0000007B0001020076855A1782722742671623C2783EF30D055902A5A54502A580C80A37A800F70A7544890773FB0773A0270CDF84688B3B5BE258D42BCD5656F422D4A339A35850A3C98716F4A150F9C92B5875D45B8703D6F68ED65B9EA6F677371C0D078B5BD4831311F050D18B0A5BD18BC91050453C8540369450FA29"));   
      androidCustomerList.add(new Customer("a4242096",150707,"43","","000000750001020076855A17827227FE3FC216AA252946F28FD1286666A51BD7E7CAB4DEE780670280A53A802580E71B162545D725DE805923A6D7BD671623C2783EF30D6C1680450245027F806C3F23F20125F232AA160D19BCBE10FDB38BCF900A630A736EBF90085915EEE7082F606012857D7D1FFF9F27"));     
      
    }
    return androidCustomerList;
  }
  
  public Map<String,Server> initANDROIDServers(){
    
    if(androidServerMap == null){
      androidServerMap = new HashMap<String,Server>(10);
      
      androidServerMap.put("3",new Server("115.29.166.188",8100));
      androidServerMap.put("4",new Server("115.29.166.188",8100));
      androidServerMap.put("5",new Server("115.29.166.188",8100));
      androidServerMap.put("6",new Server("115.29.166.188",8200));
      androidServerMap.put("7",new Server("115.29.166.188",8200));
      androidServerMap.put("9",new Server("115.29.166.188",8200));
      androidServerMap.put("11",new Server("115.29.166.188",8200));
      androidServerMap.put("12",new Server("115.29.166.188",8200));
      androidServerMap.put("14",new Server("115.29.166.188",8200));
      androidServerMap.put("15",new Server("115.29.166.188",8200));
      androidServerMap.put("17",new Server("115.29.166.188",8200));
      androidServerMap.put("18",new Server("115.29.166.188",8200));
      androidServerMap.put("20",new Server("115.29.166.188",8200)); 
      androidServerMap.put("21",new Server("115.29.166.188",8300));
      androidServerMap.put("23",new Server("115.29.166.188",8300));
      androidServerMap.put("28",new Server("115.29.166.188",8300));
      androidServerMap.put("29",new Server("115.29.166.188",8300));
      androidServerMap.put("31",new Server("115.29.166.188",8300));
      androidServerMap.put("32",new Server("115.29.166.188",8300));
      androidServerMap.put("43",new Server("115.29.166.188",8400));
      androidServerMap.put("46",new Server("115.29.166.188",8400));
      androidServerMap.put("47",new Server("115.29.166.188",8400));
      androidServerMap.put("50",new Server("115.29.166.188",8400));
      androidServerMap.put("53",new Server("115.29.166.188",8400));
      androidServerMap.put("57",new Server("115.29.166.188",8400));
      androidServerMap.put("68",new Server("115.29.166.188",8500));
      androidServerMap.put("73",new Server("115.29.166.188",8500));
      androidServerMap.put("74",new Server("115.29.166.188",8500));
      androidServerMap.put("84",new Server("115.29.166.188",8600));
      androidServerMap.put("87",new Server("115.29.166.188",8600));
      androidServerMap.put("88",new Server("115.29.166.188",8600));
      androidServerMap.put("90",new Server("115.29.166.188",8600));
      androidServerMap.put("93",new Server("115.29.166.188",8600));
      androidServerMap.put("98",new Server("115.29.166.188",8600));
      androidServerMap.put("99",new Server("115.29.166.188",8600));
      androidServerMap.put("100",new Server("115.29.166.188",8600));
      androidServerMap.put("106",new Server("121.199.8.236",8200));
      androidServerMap.put("108",new Server("121.199.8.236",8200));
      androidServerMap.put("111",new Server("121.199.8.236",8200));
      androidServerMap.put("119",new Server("121.199.8.236",8200));
      androidServerMap.put("123",new Server("121.199.8.236",8300));
      androidServerMap.put("127",new Server("121.199.8.236",8300));
      androidServerMap.put("132",new Server("121.199.8.236",8300));
      androidServerMap.put("136",new Server("121.199.8.236",8300));
      androidServerMap.put("137",new Server("121.199.8.236",8300));
      androidServerMap.put("138",new Server("121.199.8.236",8300));
      androidServerMap.put("139",new Server("121.199.8.236",8300));
      androidServerMap.put("140",new Server("121.199.8.236",8300));
      androidServerMap.put("144",new Server("121.199.8.236",8400));
      androidServerMap.put("147",new Server("121.199.8.236",8400));
      androidServerMap.put("148",new Server("121.199.8.236",8400));
      androidServerMap.put("150",new Server("121.199.8.236",8400));
      androidServerMap.put("154",new Server("121.199.8.236",8400));
      androidServerMap.put("158",new Server("121.199.8.236",8400));
      androidServerMap.put("162",new Server("121.199.8.236",8500));
      androidServerMap.put("165",new Server("121.199.8.236",8500));
      androidServerMap.put("166",new Server("121.199.8.236",8500));
      androidServerMap.put("168",new Server("121.199.8.236",8500));
      androidServerMap.put("177",new Server("121.199.8.236",8500));
      androidServerMap.put("178",new Server("121.199.8.236",8500));
      androidServerMap.put("180",new Server("121.199.8.236",8500));
      androidServerMap.put("194",new Server("121.199.8.236",8700));
      androidServerMap.put("197",new Server("121.199.8.236",8700));
      
    }
    return androidServerMap;
  }
  
  public void doANDROIDPeiEn(int index){
  
    index = index - 1000;
    for(int i = 0; i < groupSize; i++){
      int customerIndex = index + i;
      if(customerIndex == (androidCustomerList.size() - 1)){
        System.out.println(" android customer =====: "+customerIndex);
      }
      
      if(customerIndex < androidCustomerList.size()){
        Customer cus = (Customer)androidCustomerList.get(customerIndex);
        Server server = (Server)androidServerMap.get(cus.getReginCode());
        doAndroidPeiEn(server.getIp(),server.getPort(),cus.getLoginStr());
      }
    }
  }
  
  
  /**
   * ===== ======== ===================================== =================================== ===== qRen
   */

  public List<Customer> initQRenCustomers(){
	  
	  if(qRenCustomers == null){
		  qRenCustomers = new ArrayList<Customer>(10);
		  if(true){
			  return qRenCustomers;
		  }
		  // ios
		  
//		  qRenCustomers.add(new Customer("nian520524",150115,"22","","000000770001020076855A17827227FE3FC216AA252946F28FD12880CAA559021B23E71B251BD7596616238059247FA580231B45021B1680B46666FE3F23F20125F232AA160D86B24AF1142D9C9C7C105EF4771026502D1807F5E307F596CDC0CC14E67CF05014F18EB0B8137311CC0FA10F0F478502FD532619B5"));       
		  qRenCustomers.add(new Customer("gemini_cz",150715,"31","","000000750001020076855A17827227FE3F9095AA3E78B636AA696C23673E3E36DF56671E5F3F23F20125F232AA160D86B2CEC2142D9C9C7C105EF48EEECD9C508426E11113CC959E2D07848727E387830787864AF0F583834A9C9C9C107EF5A4CF5C50E6105C10578A34CCFE58574502027685B87D5384B1D3"));          
//		  qRenCustomers.add(new Customer("aidswcs9527",150515,"24","","000000780001020076855A17827227FE3F9095AA3E78B636AA696C673616463F673FCA80DE02653F23F20125F232AA160D86B2CEC6142D9C9C7C105EF48EEECD9C508426E11113CC959E4AEF4A4AAF504AAF0DF027AF83F510838307504A870D842707E386E327AF871036B8137311CC0FA10F0F47851E5953CCC978")); 
//		  qRenCustomers.add(new Customer("xqccfy",150115,"29","","000000730001020076855A17827227FE3F9095AA3E78B636AA6982FA901623237DFE3F23F20125F232AA160D86B2CE95142D9C9C7C105EF48EEECD9C508426E11113CC959E2D07EFF507EFF00783F5860707864AAFCCCC9C27E3F5F04AF0CC27F0F02787F08EB8137311CC0FA10F0F47854D45537CE90A"));  
//		  qRenCustomers.add(new Customer("882187",150516,"25","","000000710001020076855A1722B02AEBD4A5A28593B9CF5D968C2147D4D040A6F5A88E6765134B58AF6AAA821CFCF38F7BEB0374164DD6FBFB7D51FDF8356477925510C1B239D6BD72E2F0364CF9EAC840A1EE18A5D6D2CA6C8031BF81A8C7BBE3388574C79C3C7AF0847AC047DA4DFC1E1F35652A"));   
		  qRenCustomers.add(new Customer("xuzhuohot",150816,"2","","000000760001020076855A1782722742671623C2783EF30D6C1E90FA95903EAA0F78123F9095AA3E78B636AA69695F3F23F20125F232AA160D86B24358CD9C508426E11113CC959E2DF5EFF527AF5010F62787AFEF8310CC4A07F5842D2D87831007F50ACCCC4AF68EB8137311CC0FA10F0F4785F48E201BF915"));   
//		  qRenCustomers.add(new Customer("westxf",150527,"12","","000000730001020076855A1782722742671623C2783EF30D82F267F23F5BB46C3F9095AA3E78B636AA69695F3F23F20125F232AA160D86B24A96CD9C508426E11113CC959E4AE3872D9CCCF0F50707E307F58483E3F510278787F01087E34A1007E39C8786F4B8137311CC0FA10F0F478558581F15D5AE"));     
//		  qRenCustomers.add(new Customer("sadspread",150116,"30","","000000740001020076855A1722B01BEBBAA5A28593B9CF5D8C8C214972BC3A66899A4BE76CF9720BD11372D6C2821CFCF38FD1EB2CC1EFCD6A1797BF5A47AE500F57F730036FBD4F01377E8A67DBE7D2A7534046301864D2BA8CF26780B3ABB41FB5A7A5131E61990B07CFF054EEC090754D215C942E7087"));  
//		  qRenCustomers.add(new Customer("sqdnh1314",150317,"24","","000000750001020076855A1722B02A5FC420FE8593B086E1CE8B2265127BE350D986996A15F747D3C31B59CEDD339370E92A4360DF1A66B9D5B9A92FA37560CAF896EAEF14469288C1F1E2E890ACAFD505DEFA4B17B09018D50376BBD5FE65614E864042E55A280E76C309BB6AABBB0EDA7A2C491294731D97"));  
		  qRenCustomers.add(new Customer("jphang",150807,"47","","000000730001020076855A17827227FE3F9095AA3E78B636AA69823E010FB43625653F23F20125F232AA160D86B2BBC2142D9C9C7C105EF48EEECD9C508426E11113CC959E2D83509C4A4AF58686834AE350F5EF4ACC87104A4AF69C4A50F6F0F5AF508786B6B8137311CC0FA10F0F47854D90534A8E6D"));    
//		  qRenCustomers.add(new Customer("b186163",150504,"26","","000000730001020076855A1782722742671623C2783EF30D82670266A5E780E78C3F9095AA3E78B636AA69695F3F23F20125F232AA160D86B2CEEECD9C508426E11113CC959E4A869C8786EFF5AF83072D2D27F0834AEFF527F510CCF50D86F67E807A02E00AFDB38BCF900A630A0A1285AB401FE217B7")); 
		  qRenCustomers.add(new Customer("alee00",150708,"12","","000000720001020076855A1722B02A0C9820FE85C056B0D445666A96101C7904EEEC3A7C47358EAA8BA436C9790D54D09A6CFDC5F2262D6CC1F41D738F89204FB4699C08C1F166A2E84D058D2ECB7210BF9C98A07F888917675F569AC201D1652221B523E64D0FC2099AFCB1BF275621701D3DFA1F78"));  
		  qRenCustomers.add(new Customer("dzysharp",150730,"60","","000000750001020076855A17827227FE3F9095AA3E78B636AA696C165BF829C2B42901A23F23F20125F232AA160D86B22DF4142D9C9C7C105EF48EEECD9C508426E11113CC959E2DF6109CF68387501027844A07F02787101007EF9C4A9C4A07874A102783F0078EB8137311CC0FA10F0F4785795620D60090"));    
//		  qRenCustomers.add(new Customer("nick3693",150612,"13","","000000710001020076855A179AB02A0C4820A28537566AC0614708118611F1925FCA3FDA0A8E07D21D0E670830508BF572BF7F6142C4BDB3097FA6E48F120A3FCF191955C952958598B7D9842BCE346840A60FD90BED78E220CA319A08AB4A37E0432386BB2E4F8F916C50C445B5C8975753EECCBC"));   
		  qRenCustomers.add(new Customer("zxzjd321",150912,"61","","000000750001020076855A17827227FE3FC216AA252946F28FD1281625162502806723E716803A8024A559A580CAB43AA50259801B80A64525B3B4E23F23F20125F232AA160D86B22D95142D9C9C7C105EF477E1BE732E2E8387F590CDC0CC14E67CF05014F18EB0B8137311CC0FA10F0F4785EB7B53D54E1D"));  
//		  qRenCustomers.add(new Customer("qqqqqq48286818",150612,"17","","000000760001020076855A17827227FE3F9095AA3E78B636AA6935F241AE6C5D3C5DE75D715D8D724C8BD54C8B6436FEF6B560F6552BB6B6A375C9588716F4A150F9C92B5875D45B87038C9EA62727778E86A6A6F6D69E86A6BAA84677868E37F7806C3C35CCB9E705814C32F08B973FBABABA852C98201D9EBE"));  
		  qRenCustomers.add(new Customer("hds1983",150714,"31","","000000740001020076855A17827227FE3F9095AA3E78B636AA6982AAB446D76666A58C3F23F20125F232AA160D86B2CEC2142D9C9C7C105EF48EEECD9C508426E11113CC959E4AAFF0F02D4A0DF0CC4A8307849CF50DCC0727F6CC07F50A1083F5862D104A83C6B8137311CC0FA10F0F478501CB5394EA83"));  
		  
	  }
	  return qRenCustomers;
  }
  
  public Map<String,Server> initQRenServers(){
	    
	    if(qRenServers == null){
	    	qRenServers = new HashMap<String,Server>(10);
	      
	    	// ios servers
	    	
	    	qRenServers.put("2",new Server("121.199.5.164",8100));
	    	qRenServers.put("12",new Server("121.199.5.164",8200));
	    	qRenServers.put("13",new Server("121.199.5.164",8200));
	    	qRenServers.put("17",new Server("121.199.5.164",8200));
	    	qRenServers.put("22",new Server("121.199.5.164",8200));
	    	qRenServers.put("24",new Server("121.199.5.164",8200));
	    	qRenServers.put("25",new Server("121.199.5.164",8200));
	    	qRenServers.put("26",new Server("121.199.5.164",8200));
	    	qRenServers.put("29",new Server("121.199.5.164",8200));
	    	qRenServers.put("30",new Server("121.199.5.164",8200));
	    	qRenServers.put("31",new Server("121.199.5.164",8300));
	    	qRenServers.put("47",new Server("121.199.5.164",8300));
	    	qRenServers.put("60",new Server("121.199.5.164",8300));
	    	qRenServers.put("61",new Server("121.199.5.164",8400));
	    	
	    }
	    return qRenServers;
  }
  
  public void doQRenPeiEn(int index){
	  	if(checkTime(20,40,61) == 0){
	      return;
	    }
	    
	    if(index == 0){
	      label.setText("LanDaCount : "+landaCount);
	      
	      if(landaCount > 10000){
	        landaCount = 0;
	      }else{
	        landaCount++;
	      }
	    }
	    
	    for(int i = 0; i < groupSize; i++){
	      int customerIndex = index + i;
	      if(customerIndex == (qRenCustomers.size() - 1)){
	        System.out.println(" QRen customer =====: "+customerIndex+1);
	      }
	      
	      if(customerIndex < qRenCustomers.size()){
	        Customer cus = (Customer)qRenCustomers.get(customerIndex);
	        String reginCode = cus.getReginCode();
	        Server server = (Server)qRenServers.get(reginCode);
	        if( Integer.valueOf(reginCode) < 1000){
	        	doIosQRenPeiEn(server.getIp(),server.getPort(),cus.getLoginStr());
	        }else{
	        	doIosQRenPeiEn(server.getIp(),server.getPort(),cus.getLoginStr());
	        }
	       
	      }
	    }
	  }
  
  public void doIosQRenPeiEn(String host,int port,String loginStr)
  {
      try{
            InetSocketAddress remote = new InetSocketAddress(host, port);  
            SocketChannel sc = SocketChannel.open();  
            sc.connect(remote);  
            if(sc.finishConnect()){
            	System.out.println(" doIosQRenPeiEn connected...");
            }
            
            List<String> cmdList = new ArrayList<String>();
            cmdList.add(loginStr);
            cmdList.add("0000001400020400C8855A17827227F5418585C085BB55E1");
            cmdList.add("0000001400030300AE855A17827227F5418585C085BBA3220000001400040300D6855A17827227F5418585C085BB25E1");
            cmdList.add("0000001400050700C8855A17827227F5418585C085BB55E6");
            cmdList.add("0000002500061C00C8855A17827227CCAA29EB291BF2F87E8FF2C278F28F23D1834185E8BBD628A9D1");
            cmdList.add("000000140007250041855A17827227F5418585C085BBD320");
            cmdList.add("0000001400080600AE855A17827227F5418585C085BBA329");
            cmdList.add("0000001400091700C8855A17827227F5418585C085BB55EA00000014000A1300C8855A17827227F5418585C085BB55E900000014000B1A00C8855A17827227F5418585C085BB55E800000024000C1400C8855A17827227CC3E29EB8FF2291BF23F89F31BB62927274185DB77226430EC00000014000D2100C8855A17827227F5418585C085BB55EE00000014000E14005D855A17827227F5418585C085BB484400000014000F1C005D855A17827227F5418585C085BB4845000000140010130022855A17827227F5418585C085BBA8560000001400110300FE855A17827227F5418585C085BB15C1");
            
            cmdList.add("0000001400120800C8855A17827227F5418585C085BB55F1");
            cmdList.add("0000001400131600C8855A17827227F5418585C085BB55F0");
            cmdList.add("0000001400141D00C8855A17827227F5418585C085BB55F70000001400150500C8855A17827227F5418585C085BB55F60000001400160600C8855A17827227F5418585C085BB55F5");
            cmdList.add("0000001400171200C8855A17827227F5418585C085BB55F4");
            cmdList.add("0000001400181900C8855A17827227F5418585C085BB55FB");
            cmdList.add("0000001400191800C8855A17827227F5418585C085BB55FA");
            cmdList.add("00000014001A1B00C8855A17827227F5418585C085BB55F9");
            cmdList.add("00000014001B1B00C8855A17827227F5418585C085BB55F8");
            cmdList.add("00000014001C1B0076855A17827227F5418585C085BBD85C");
            
            if (landaCount == 0) {
              //canzhan
              cmdList.add("0000001C001D1B00AE855A1782722742295B29B4D169D7E341859A83763DCFC7");
            }else{
              
              if (interval > 30)
                //canzhan
              cmdList.add("0000001C001D1B00AE855A1782722742295B29B4D169D7E341859A83763DCFC7");
              else
                //fuhuo
              cmdList.add("0000001C001D1B00AE855A1782722742295B29B4D169D7F541859AF5761FAC80");
            }
            
            for(int j = 0; j< cmdList.size() ; j++){
              String cmdStr = cmdList.get(j);
              ByteBuffer bb = ByteBuffer.wrap(hexStringToByteArray(cmdStr));
              sc.write(bb);
            }
            
            sc.close();
      } catch (IOException e) {  
              e.printStackTrace();  
      }
    
  }
  
  /**
   * ===== ======== ===================================== =================================== ===== dingshang
   */

  public List<Customer> initDingShangCustomers(){
    
    if(dingShangCustomers == null){
    	dingShangCustomers = new ArrayList<Customer>(10);
      
    	// ios users
//    	dingShangCustomers.add(new Customer("qqq346819402",141107,"9","","000000790001020076855A17827227FE3F9095AA3E78B636AA693529292980D7453AA5668045A55F3F23F20125F232AA160DFB57FC350845E0E015806D9860A66ECBE7E478F7CF66902E3ED293C631F4367AF4CBB64C0F29B629A136360F36DD3E93C6DDD07A9336F429D298FDB38BCF900A630A0A128549AB53764EAE"));       
    	//  密码  iOS快用59
//    	dingShangCustomers.add(new Customer("aa289929526",150301,"59","","000000780001020076855A1782722742671623C2783EF30D6C671680D57F6680D580DE026C3F23F20125F232AA160DFB5799136EBF90085915EEE7082F602BFDB38BCF900A630A736ECBE7E478F7CF66902E3ED240C640407A29D0DDDE3E3B3B313B7A3B20F219CB3E0F80454558B63631CBB6CC12854F8B537D0AEE"));      
    
    	// android users
    	
    	// 13977764442，密码12354669  360版本  安卓海贼 
//    	dingShangCustomers.add(new Customer("13977764442",150514,"1026","","0000007E0001020076855A179A7A2AEBF420A285C6E37E61B54E0018D3D57F29B0697C8344631CC393875B45DCF162A32CB8E57ED8027D66AC3875BEE4E085EDE31296EF71E8D545EF199521A899259DE7776A84264804BCEA791326CE8C00E43CD2E02DF2DFE7DF7529F1AA70E49E5E268090A98F36C76D4F944C1D6A5720A6125A"));       
    	// haizei 13701180657 zq402200.34  11区 qq: 1121243278  360  fu: 10
//    	dingShangCustomers.add(new Customer("13701180657",151014,"1011","1121243278","0000007D0001020076855A1722B0B09A76DAA285C6E37E2F1A611D956A3ADFF48B67F345479CDABAA5749CF7DA7D3A2C18A8FB5026D31894B359FF6685F5C82C769C93DAEF70CE3E13424F4213BCDC527436C5E7EE238F50B3FC513DE6FEFF39D19EC60D7B423B50C9DAEE65B08A7E4B003AE47ACCE99D1CFC5B083CE520418B85"));       
    	
    }
    return dingShangCustomers;
  }
  
  public Map<String,Server> initDingShangServers(){
    
    if(dingShangServers == null){
    	dingShangServers = new HashMap<String,Server>(10);
      
    	// ios servers
    	dingShangServers.put("9",new Server("121.199.29.165",8200));
    	dingShangServers.put("39",new Server("112.124.31.220",8100));
    	dingShangServers.put("59",new Server("115.29.175.117",8500));
    	
    	// android servers
    	dingShangServers.put("1011",new Server("112.124.6.242",8300));
    	dingShangServers.put("1026",new Server("112.124.6.242",8300));
    	
    }
    return dingShangServers;
  }
  
  public void doDingShang(int threadNumber){
    
    if(checkTime(20,15,61) == 0){
      return;
    }
    
    if(threadNumber == 0 || threadNumber == 1000){
      label.setText("LanDaCount : "+landaCount);
      
      if(landaCount > 10000){
        landaCount = 0;
      }else{
        landaCount++;
      }
    }
    
    for(int i = 0; i < groupSize; i++){
      int customerIndex = threadNumber + i;
      if(customerIndex == (dingShangCustomers.size() - 1)){
        System.out.println(" dingShang customer *****: "+customerIndex);
      }
      
      if(customerIndex < dingShangCustomers.size()){
        Customer cus = (Customer)dingShangCustomers.get(customerIndex);
        Server server = (Server)dingShangServers.get(cus.getReginCode());
        if(cus.getReginCode().length() >= 4){
        	doAndroidDingShang(server.getIp(),server.getPort(),cus.getLoginStr());
        }else{
        	doDingShang(server.getIp(),server.getPort(),cus.getLoginStr());
        } 
      }
    }
    
  }  
  
}
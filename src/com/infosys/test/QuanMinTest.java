package com.infosys.test;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import org.json.*;

import com.redcdn.test.Server;
import com.redcdn.test.StringUtils;
import com.redcdn.test.TestAdapter;

import cn.redcdn.datacenter.httprequest.HttppostDataCenter;

public class QuanMinTest extends Frame implements ActionListener {

	private static final long serialVersionUID = 1L;
	Button cutButton;
	TextField secondText;
	TextField timeText;
	Label label;
	static int timeDuration = 0;
	
	List<QuanMinUser> quanMinUsers = null;
	List<QuanMinUser> quanMinCustomers = null;

	List<SanGuoUser> sanGuoUsers = null;
	Map<String,Server> sanGuoServers = null;
	
	public QuanMinTest() {
		super("QuanMinTest！");

		setSize(500, 80);
		addWindowListener(new TestAdapter());

		Panel toolbar = new Panel();
		toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));

		cutButton = new Button("开始");
		cutButton.addActionListener(this);
		toolbar.add(cutButton);

		secondText = new TextField(8);
		secondText.setText("1");
		toolbar.add(secondText);

		label = new Label("秒每次;");
		toolbar.add(label);

		label = new Label("LanDaCount :      ");
		toolbar.add(label);

		timeText = new TextField(6);
		timeText.setText("1000");
		toolbar.add(timeText);

		Label label_ = new Label("分钟后关闭.");
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

		initQuanMinUser();
		for(int i=0 ; i<quanMinUsers.size(); i += 1){
			MyThread m = new MyThread(i);
			new Thread(m).start();
			try{
		        Thread.sleep(200);
		      }catch(Exception e){
		        e.printStackTrace();
		      }  
		}
		
		for(int i=0 ; i<quanMinCustomers.size(); i += 1){
			DaiDaThread m = new DaiDaThread(i);
			new Thread(m).start();
			try{
		        Thread.sleep(200);
		      }catch(Exception e){
		        e.printStackTrace();
		      }  
		}
		
		initSanGuoUser();
		initSanGuoServers();
		for(int i=0 ; i<sanGuoUsers.size(); i += 1){
			SanGuoThread m = new SanGuoThread(i);
			new Thread(m).start();
			try{
		        Thread.sleep(200);
		      }catch(Exception e){
		        e.printStackTrace();
		      }  
		}

		System.out.println("...." + interval);
	}

	static int landaCount = 0;

	class MyThread implements Runnable {

		private int threadNumber = 0;

		public int getThreadNumber() {
			return threadNumber;
		}

		public void setThreadNumber(int threadNumber) {
			this.threadNumber = threadNumber;
		}

		public MyThread(int threadNumber) {
			super();
			this.threadNumber = threadNumber;
		}

		public void run() {
			System.out.println("begin....");
			try {
				doQuanMinLanDa(this.threadNumber);
				System.exit(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("end....");
		}
	}
	
	class DaiDaThread implements Runnable {

		private int threadNumber = 0;

		public int getThreadNumber() {
			return threadNumber;
		}

		public void setThreadNumber(int threadNumber) {
			this.threadNumber = threadNumber;
		}

		public DaiDaThread(int threadNumber) {
			super();
			this.threadNumber = threadNumber;
		}

		public void run() {
			System.out.println("begin....");
			try {
				doQuanMinDaiDa(this.threadNumber);
				System.exit(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("end....");
		}
	}
	
	class SanGuoThread implements Runnable {

		private int threadNumber = 0;

		public int getThreadNumber() {
			return threadNumber;
		}

		public void setThreadNumber(int threadNumber) {
			this.threadNumber = threadNumber;
		}

		public SanGuoThread(int threadNumber) {
			super();
			this.threadNumber = threadNumber;
		}

		public void run() {
			System.out.println("begin....");
			try {
				do{
					if(checkTime(21,0,30) == 1){
						SanGuoUser user = sanGuoUsers.get(this.threadNumber);
						Server server = sanGuoServers.get(user.getReginCode());
						doSanGuoBoss(server.getIp(),server.getPort(),user.getLoginStrA(),user.getLoginStrB());
					}
					Thread.sleep(1000*14);
		          }while(true);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("end....");
		}
	}

	public static void main(String args[]) {

		QuanMinTest tf1 = new QuanMinTest();
		tf1.setVisible(true);
	}

	private void initQuanMinUser(){
		
		if(this.quanMinUsers == null){
			this.quanMinUsers = new ArrayList<QuanMinUser>();
			quanMinUsers.add(new QuanMinUser("46","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("45","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("43","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("42","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("41","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("40","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("39","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("38","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("37","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("36","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("35","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("34","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("33","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("32","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("31","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("30","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("29","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("28","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("27","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("26","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("25","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("24","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("23","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("22","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("21","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("20","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("19","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("18","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("17","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("16","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("15","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("14","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("13","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("12","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("11","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("10","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("9","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("8","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("7","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("6","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("5","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("4","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("3","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("2","kuaiyong_s551179ffa6b65"));
			quanMinUsers.add(new QuanMinUser("1","kuaiyong_s551179ffa6b65"));
			
			// xiaonanche1
			quanMinUsers.add(new QuanMinUser("13","kuaiyong_s5539e25c1e356"));
			// xiaonanche2  
			quanMinUsers.add(new QuanMinUser("13","kuaiyong_s5539e3a76e7be"));
			// xiaonanche3  
			quanMinUsers.add(new QuanMinUser("13","kuaiyong_s5539e49093ae8"));
			// xiaonanche4  
			quanMinUsers.add(new QuanMinUser("13","kuaiyong_s5539e4f8d80d5"));
			// xiaonanche5
			quanMinUsers.add(new QuanMinUser("13","kuaiyong_s5539e564a5747"));
			
		}
		
		if(this.quanMinCustomers == null){
			this.quanMinCustomers = new ArrayList<QuanMinUser>();
			
			// 349666019 95270000  qq : 1358998413 150603
//			quanMinCustomers.add(new QuanMinUser("13","uc2_349666019")); // qian 30
//			quanMinCustomers.add(new QuanMinUser("49","kuaiyong2_s55b1a914c08b3",150910,"ac552187466_309108688"));  
			// 账号   密码cs6552514 梦想死神，安卓360版本，1区 qq：1678245727
			quanMinCustomers.add(new QuanMinUser("1","360n2_1150010885",151211,"mxsszyyx@yeah.net_1678245727"));  
			
		}
	}
	
public void initSanGuoUser(){
		
	if(this.sanGuoUsers == null){
		this.sanGuoUsers = new ArrayList<SanGuoUser>();
		
		sanGuoUsers.add(new SanGuoUser("xiaonanche",150630,"46","","500001FF4C004C4F47494E20302E302E312037203536203130343834343530203132332031343334323030363232203864306639383439323939393861343739336162616534306137666438346239203000","1C0002FF1800454E54455220313034383434353020363230313336313300"));
		sanGuoUsers.add(new SanGuoUser("xiaonanche",150630,"107","","510001FF4D004C4F47494E20302E302E31203720313137203130343834343530203132332031343334323836363736206333343635363362616234356166656264623666346135646264653238663637203000","1D0002FF1900454E5445522031303438343435302031313730303436333600"));
		sanGuoUsers.add(new SanGuoUser("xiaonanche",150630,"139","","510001FF4D004C4F47494E20302E302E31203720313439203130343834343530203132332031343334373734303733203836386633333836366438636333643665653563666139663731653565383162203000","1D0002FF1900454E5445522031303438343435302031343930303530333400"));
		sanGuoUsers.add(new SanGuoUser("xiaonanche",150630,"140","","510001FF4D004C4F47494E20302E302E31203720313530203130343834343530203132332031343334373833323632203966313535386237356462633738313839393533343934393963653634633763203000","1D0002FF1900454E5445522031303438343435302031353030303236373100"));
		sanGuoUsers.add(new SanGuoUser("xiaonanche",150630,"136","","510001FF4D004C4F47494E20302E302E31203720313436203130343834343530203132332031343334383034303431206566333636376163336636653061323535386639313666343364333336356431203000","1D0002FF1900454E5445522031303438343435302031343630303534303700"));
		sanGuoUsers.add(new SanGuoUser("xiaonanche1",150630,"136","","510001FF4D004C4F47494E20302E302E31203720313436203131313136383432203132332031343334383033383437206163346131333163623837343531313464626230366138366238356361343633203000","1D0002FF1900454E5445522031313131363834322031343630303534313100"));
		sanGuoUsers.add(new SanGuoUser("xiaonanche2",150630,"136","","510001FF4D004C4F47494E20302E302E31203720313436203131313137303930203132332031343334383033393833203564393065636334633965373236636665303133376430646439366233623335203000","1D0002FF1900454E5445522031313131373039302031343630303534313600"));
		sanGuoUsers.add(new SanGuoUser("xiaonanche",150630,"141","","510001FF4D004C4F47494E20302E302E31203720313531203130343834343530203132332031343334383837353434206662356235376265623531633332626331663030643964383531666366346138203000","1D0002FF1900454E5445522031303438343435302031353130303138303700"));
		
	}
}

public void initSanGuoServers(){
    
    if(sanGuoServers == null){
    	sanGuoServers = new HashMap<String,Server>(10);
      
    	sanGuoServers.put("46",new Server("120.24.82.223",8006));
    	sanGuoServers.put("107",new Server("115.28.87.62",8004));
    	sanGuoServers.put("136",new Server("121.201.1.78",8002));
    	sanGuoServers.put("139",new Server("121.201.1.78",8003));
    	sanGuoServers.put("140",new Server("103.255.200.18",8002));
    	sanGuoServers.put("141",new Server("120.24.166.23",8003));
    	
      }
}
	
	public void doQuanMinLanDa(int threadNumber) {
		
		QuanMinUser user = this.quanMinUsers.get(threadNumber);
		String regin = user.getRegionNumber();
		String session = user.getSessionId();
		
		// 42.62.16.199  42.62.16.203
		int lanDaInterval = 61;
		int sleepInterval = 0;
		int liLianInterval = 5;
		int dateIndex = -3;
		int loginIndex = -1;
		while (true) {
			sleepInterval = 60;
			
			if(checkTime(2,05,10) == 1){
				
				String lingV1 = "http://s"+regin+".ss.tuziyouxi.com/cmd.php?moduleId=19&actId=2&ts=1396885714163&_session="+session+"&";
				sendRequest(lingV1, "data={\"level\": 3}");
				String lingV2 = "http://s"+regin+".ss.tuziyouxi.com/cmd.php?moduleId=19&actId=2&ts=1396885714163&_session="+session+"&";
				sendRequest(lingV2, "data={\"level\": 10}");
				String lingV2FuLi = "http://s"+regin+".ss.tuziyouxi.com/cmd.php?moduleId=37&actId=2&ts=1396885927959&_session="+session+"&";
				sendRequest(lingV2FuLi, "data={\"index\": \"0\",\"huodongId\": \""+getDateString(dateIndex)+"70\"}");
				
				String lingJiang = "http://s"+regin+".ss.tuziyouxi.com/cmd.php?moduleId=28&actId=2&ts=1429539332730&_session="+session+"&";
				sendRequest(lingJiang, "data={\"type\": \"total\", \"index\": 0,\"huoDongId\": \""+getDateString(dateIndex)+"\"}");
				String xuYuan = "http://s"+regin+".ss.tuziyouxi.com/cmd.php?moduleId=25&actId=3&ts=1429539702978&_session="+session+"&";
				sendRequest(xuYuan, "data=%7B%7D");
				String teXun = "http://s"+regin+".ss.tuziyouxi.com/cmd.php?moduleId=22&actId=2&ts=1429540016728&_session="+session+"&";
				sendRequest(teXun, "data=%7B%7D");
				
				label.setText("dateIndex : " + dateIndex);
				if(dateIndex < 1){
					dateIndex += 1;
				}
			}else if(checkTime(12,0,5) == 1 || checkTime(18,0,5) == 1){
				String lingTiLi = "http://s"+regin+".ss.tuziyouxi.com/cmd.php?moduleId=18&actId=2&ts=1429610560315&_session="+session+"&";
				sendRequest(lingTiLi, "data=%7B%7D");
				
			}else if(checkTime(3,10,60) == 1 
					|| checkTime(13,05,60) == 1 
					|| checkTime(18,15,60) == 1){

				if(loginIndex < 0){
					loginIndex += 1;
					String login = "http://s"+regin+".ss.tuziyouxi.com/cmd.php?moduleId=2&actId=2&ts=1429836036303&_session="+session+"&";  																											 
					String aa = "data={\"account\": null,\"signature\": \"{\"err\":0,\"msg\":\"\\u767b\\u5f55\\u6210\\u529f\",\"openid\":\""+session+"\",\"sig\":\"d0666a34f95e60dcf08882b5236cf2ff\"}\",\"server_id\": "+regin+",\"extra\": {},\"promotion\": null}";
					String a = sendRequest(login, aa);
					String loginB = "http://s"+regin+".ss.tuziyouxi.com/cmd.php?moduleId=28&actId=1&ts=1429837048587&_session="+session+"&";  																											 
					String b = sendRequest(loginB, "data={\"isQueryResource\": false}");
					String loginD = "http://s"+regin+".ss.tuziyouxi.com/cmd.php?moduleId=3&actId=3&ts=1429838845664&_session="+session+"&";  																											 
					String d = sendRequest(loginD, "data=%7B%7D");
					String loginC = "http://s"+regin+".ss.tuziyouxi.com/cmd.php?moduleId=3&actId=6&ts=1429838122766&_session="+session+"&";  																											 
					String c = sendRequest(loginC, "data=%7B%7D");
				}
				
				String lingJiang = "http://s"+regin+".ss.tuziyouxi.com/cmd.php?moduleId=28&actId=2&ts=1429539332730&_session="+session+"&";
				sendRequest(lingJiang, "data={\"type\": \"total\", \"index\": 0,\"huoDongId\": \""+getDateString(dateIndex)+"\"}");
				
				String liaoLi = "http://s"+regin+".ss.tuziyouxi.com/cmd.php?moduleId=7&actId=2&ts=1396879194264&_session="+session+"&";
				sendRequest(liaoLi, "data={\"itemid\": 25010001,\"data\": null}");
				
				String liLian = "http://s"+regin+".ss.tuziyouxi.com/cmd.php?moduleId=4&actId=4&ts=1429540167095&_session="+session+"&";
				String a = sendRequest(liLian, "data={\"mission\": 0,\"chapter\": 1}");
				System.out.println(a);
				
				sleepInterval = liLianInterval;
				label.setText("liLianInterval : " + liLianInterval);
			}else if(checkTime(20,0,38) == 1){
				String urlChaXue = "http://s"+regin+".ss.tuziyouxi.com/cmd.php?moduleId=27&actId=2&ts="
						+ getDateString("") + "&_session="+session+"&";
				String jsonXue = sendRequest(urlChaXue, "data=%7B%7D");

				int curHp = getBossCurHp(jsonXue);
				System.out.println("bossCurHp: " + curHp);
				label.setText("bossCurHp : " + curHp);
				if(curHp > 0){
					if (curHp < 500000) {
						lanDaInterval = 14;
					}
//					if (lanDaInterval > 1 || curHp <= 20000) {
						String urlFuHuo = "http://s"+regin+".ss.tuziyouxi.com/cmd.php?moduleId=27&actId=3&ts="
								+ getDateString("")
								+ "&_session="+session+"&";
						sendRequest(urlFuHuo,
								"data=%7B%0A%20%20%22type%22%3A%20%221%22%0A%7D");
//					}
				}else{
					lanDaInterval = 61;
				}
				sleepInterval = lanDaInterval;
				
			}else{
				lanDaInterval = 61;
				label.setText("bossCurHp : dead");
				if(dateIndex >= 0){
					dateIndex = -2;
				}
				if(loginIndex >= 0){
					loginIndex = -1;
				}
			}
			
			try {
				Thread.currentThread().sleep(sleepInterval * 1000);
			} catch (InterruptedException e) {
				// e.printStackTrace();
			}
		}

	}

public void doQuanMinDaiDa(int threadNumber) {
		
		QuanMinUser user = this.quanMinCustomers.get(threadNumber);
		String regin = user.getRegionNumber();
		String session = user.getSessionId();
		
		// 42.62.16.199  42.62.16.203
		int sleepInterval = 60;
		int dateIndex = -3;
		int loginIndex = -1;
		while (true) {
			sleepInterval = 60;
			
			if(checkTime(2,05,10) == 1){
				
//				String lingJiang = "http://s"+regin+".ss.tuziyouxi.com/cmd.php?moduleId=28&actId=2&ts=1429539332730&_session="+session+"&";
//				sendRequest(lingJiang, "data={\"type\": \"total\", \"index\": 0,\"huoDongId\": \""+getDateString(dateIndex)+"\"}");
				String xuYuan = "http://s"+regin+".ss.tuziyouxi.com/cmd.php?moduleId=25&actId=3&ts=1429539702978&_session="+session+"&";
				sendRequest(xuYuan, "data=%7B%7D");
				String teXun = "http://s"+regin+".ss.tuziyouxi.com/cmd.php?moduleId=22&actId=2&ts=1429540016728&_session="+session+"&";
				sendRequest(teXun, "data=%7B%7D");
				
				label.setText("dateIndex : " + dateIndex);
				if(dateIndex < 1){
					dateIndex += 1;
				}
			}else if(checkTime(12,0,5) == 1 || checkTime(18,0,5) == 1){
				String lingTiLi = "http://s"+regin+".ss.tuziyouxi.com/cmd.php?moduleId=18&actId=2&ts=1429610560315&_session="+session+"&";
				sendRequest(lingTiLi, "data=%7B%7D");
				
			}else if(checkTime(20,0,60) == 1){
				sleepInterval = 16;
				
				String urlFuHuo = "http://s"+regin+".ss.tuziyouxi.com/cmd.php?moduleId=27&actId=3&ts="
						+ getDateString("")
						+ "&_session="+session+"&";
				sendRequest(urlFuHuo,"data=%7B%0A%20%20%22type%22%3A%20%221%22%0A%7D");	
			}else{
				label.setText("bossCurHp : dead");
				if(dateIndex >= 0){
					dateIndex = -2;
				}
				if(loginIndex >= 0){
					loginIndex = -1;
				}
			}
			
			try {
				Thread.currentThread().sleep(sleepInterval * 1000);
			} catch (InterruptedException e) {
				// e.printStackTrace();
			}
		}

	}
	
	public int checkTime(int beginHour,int beginMinute,int duration){
		
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
	      return 0;
	    }
	  }
	
	public String getDateString(int index){
		
		GregorianCalendar currentTime = new GregorianCalendar();
	    currentTime.setTime(new Date());
	    int month = currentTime.get(Calendar.MONTH)+1;
	    int day = currentTime.get(Calendar.DAY_OF_MONTH);
	    if(index < 0){
	    	day += index;
	    }
	    String dateStr = ""+currentTime.get(Calendar.YEAR);
	    if(month < 10){
	    	dateStr += "0"+month;
	    }else{
	    	dateStr += month;
	    }
	    
	    if(day < 10){
	    	dateStr += "0"+day;
	    }else{
	    	dateStr += day;
	    }
	    
		return dateStr;
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

	public static long getDateString(String date) {
		Date dateA = new Date();
		return dateA.getTime();
	}

	private static int getBossCurHp(String JSONText) {
		JSONTokener jsonTokener = new JSONTokener(JSONText);
		JSONArray array;
		JSONObject studentJSONObject;
		String bossMaxHp = null;
		String bossCurHp = null;
		try {
			array = (JSONArray) jsonTokener.nextValue();
			if (array != null) {
				studentJSONObject = array.getJSONObject(0);
				JSONObject dataObj = (JSONObject) studentJSONObject.get("data");
				bossMaxHp = dataObj.getString("bossMaxHp");
				bossCurHp = dataObj.getString("bossCurHp");
			}
		} catch (JSONException e) {
			// e.printStackTrace();
		}
		if (bossCurHp != null) {
			return new Integer(bossCurHp).intValue();
		}
		return -1;
	}
	
	public void doSanGuoBoss(String host, int port, String loginStrA,String loginStrB) {
		try {
			InetSocketAddress remote = new InetSocketAddress(host, port);
			SocketChannel sc = SocketChannel.open();
			sc.connect(remote);
			if (sc.finishConnect()) {
//				System.out.println("connected...");
			}
			sc.configureBlocking(false);
			
			List<String> cmdList = new ArrayList<String>();
			cmdList.add(loginStrA);
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
				
				ByteBuffer bb = ByteBuffer.wrap(StringUtils.hexStringToByteArray(cmdStr));
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

}

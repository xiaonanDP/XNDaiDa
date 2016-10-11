package com.dianping.test;

import cn.redcdn.datacenter.httprequest.HttppostDataCenter;
import com.infosys.test.QuanMinUser;
import com.infosys.test.SanGuoUser;
import com.redcdn.test.Server;
import com.redcdn.test.StringUtils;
import com.redcdn.test.TestAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.List;

public class LongZhuTest extends Frame implements ActionListener {

	private static final long serialVersionUID = 1L;
	Button cutButton;
	TextField secondText;
	TextField timeText;
	Label label;
	static int timeDuration = 0;

	List<QuanMinUser> quanMinCustomers = null;

	public LongZhuTest() {
		super("LongZhuDaiDa！");

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
		
		for(int i=0 ; i<quanMinCustomers.size(); i += 1){
			DaiDaThread m = new DaiDaThread(i);
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

	public static void main(String args[]) {

		LongZhuTest tf1 = new LongZhuTest();
		tf1.setVisible(true);
	}

	private void initQuanMinUser(){
		
		if(this.quanMinCustomers == null){
			this.quanMinCustomers = new ArrayList<QuanMinUser>();

			//98  120.132.77.173
			//117 120.132.77.173
			quanMinCustomers.add(new QuanMinUser("117","kuaiyong_s57d94296eea0b",160922,"330434980"));

			// mzky2327  3266585     98区，快用 qq:121114123
			quanMinCustomers.add(new QuanMinUser("98","kuaiyong_s57b6f76a97b69",161023,"121114123"));
			// y891223   3266585，一样的快用，98区 qq:373132213
			quanMinCustomers.add(new QuanMinUser("98","kuaiyong_s57b6cd511bcbc",161023,"373132213"));
			// ios longzhu  rr365787   123456  57 qq: 614040711
			quanMinCustomers.add(new QuanMinUser("57","kuaiyong_s557110a3d2052",160930,"614040711"));

			// jiuyou longzhu 420690547  13432169259123 81  qq: 1219421451
//			quanMinCustomers.add(new QuanMinUser("81","uc_1474397353",160930,"1219421451"));

		}
	}


public void doQuanMinDaiDa(int threadNumber) {
		
		QuanMinUser user = this.quanMinCustomers.get(threadNumber);
		String regin = user.getRegionNumber();
		String session = user.getSessionId();
		

		int sleepInterval = 60;
		int dateIndex = -3;
		int loginIndex = -1;
		while (true) {
			sleepInterval = 60;
			
			if(checkTime(2,05,10) == 1){
				
				String lingJiang = "http://s"+regin+".lz.tuziyouxi.com/cmd.php?moduleId=28&actId=2&ts=1429539332730&_session="+session+"&";
				sendRequest(lingJiang, "data={\"type\": \"total\", \"index\": 0,\"huoDongId\": \""+getDateString(dateIndex)+"\"}");
				String xuYuan = "http://s"+regin+".lz.tuziyouxi.com/cmd.php?moduleId=25&actId=3&ts=1429539702978&_session="+session+"&";
				sendRequest(xuYuan, "data=%7B%7D");
				String teXun = "http://s"+regin+".lz.tuziyouxi.com/cmd.php?moduleId=22&actId=2&ts=1429540016728&_session="+session+"&";
				sendRequest(teXun, "data=%7B%7D");
				
				label.setText("dateIndex : " + dateIndex);
				if(dateIndex < 1){
					dateIndex += 1;
				}
			}else if(checkTime(12,0,5) == 1 || checkTime(18,0,5) == 1){
//				sleepInterval = 5;
				String lingTiLi = "http://s"+regin+".lz.tuziyouxi.com/cmd.php?moduleId=18&actId=2&ts=1429610560315&_session="+session+"&";
				sendRequest(lingTiLi, "data=%7B%7D");

//				String liaoLi = "http://s"+regin+".lz.tuziyouxi.com/cmd.php?moduleId=7&actId=2&ts=1396879194264&_session="+session+"&";
//				sendRequest(liaoLi, "data={\"itemid\": 25010001,\"data\": null}");

//				String liLian = "http://s"+regin+".lz.tuziyouxi.com/cmd.php?moduleId=4&actId=4&ts=1429540167095&_session="+session+"&";
//				String a = sendRequest(liLian, "data={\"mission\": 0,\"chapter\": 6}");
//				System.out.println(a);
				
			}else if(checkTime(12,30,600) == 1){
				String urlChaXue = "http://s"+regin+".lz.tuziyouxi.com/cmd.php?moduleId=27&actId=2&ts="
						+ getDateString("") + "&_session="+session+"&";
				String jsonXue = sendRequest(urlChaXue, "data=%7B%7D");

				int curHp = getBossCurHp(jsonXue);
				System.out.println("bossCurHp: " + curHp);
				if(curHp > 0){
					label.setText("bossCurHp : " + curHp +" of "+regin);
					sleepInterval = 16;
					String urlFuHuo = "http://s"+regin+".lz.tuziyouxi.com/cmd.php?moduleId=27&actId=3&ts="
							+ getDateString("")
							+ "&_session="+session+"&";
					String b = sendRequest(urlFuHuo,"data=%7B%0A%20%20%22type%22%3A%20%221%22%0A%7D");
					System.out.println(b);
				}else{
					sleepInterval = 610;
				}

			}else{
//				label.setText("bossCurHp : dead");
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

}

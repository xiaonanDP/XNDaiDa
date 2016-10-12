package com.dianping.test;

import cn.redcdn.datacenter.httprequest.HttppostDataCenter;
import com.infosys.test.QuanMinUser;
import com.redcdn.test.TestAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LongZhuTest extends Frame implements ActionListener {

	private static final long serialVersionUID = 1L;
	Button cutButton;
	Label label;
	JTextField freeNumberTextField;
	int freeCustomerNumber;
	
	JButton fileButton;
	JTextArea resultArea;
	
	List<QuanMinUser> longZhuCustomers = null;
	private String customerString = "";

	private HashMap<String,String> bossHPMap = new HashMap<String,String>();
	
	public LongZhuTest() {
		super("LongZhuDaiDa！");

		setSize(500, 300);
		addWindowListener(new TestAdapter());

		Panel toolbar = new Panel();
		toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));

		cutButton = new Button("开始");
		cutButton.addActionListener(this);
		toolbar.add(cutButton);

		freeNumberTextField = new JTextField(1);
		toolbar.add(freeNumberTextField);
		
		label = new Label("LanDaCount :      ");
		toolbar.add(label);

		fileButton = new JButton("选择文件...");
		fileButton.addActionListener(this);
		toolbar.add(fileButton);

		JScrollPane backGround = new JScrollPane();

		resultArea = new JTextArea(200,300);
		backGround.setViewportView(resultArea);

		add(backGround, BorderLayout.CENTER);

		add(toolbar, BorderLayout.NORTH);

	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == cutButton) {
			beginWork();
		} else if (ae.getSource() == fileButton) {
			chooseFile();
		}
	}
	
	public void chooseFile() {

		JFileChooser jfc=new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY );
		jfc.showDialog(new JLabel(), "选择");
		File file=jfc.getSelectedFile();

		String filePath = file.getAbsolutePath();
		resultArea.setText(filePath+" ====== Job Start \n");

		try {
			customerString = new Scanner(new File(file.getAbsolutePath())).useDelimiter("\\Z").next();
			resultArea.append(customerString);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			resultArea.append("Cannot Read File Error !!! Please Contact xiaonanche@qq.com");
		} catch (Exception e) {
			e.printStackTrace();
			resultArea.append(" File Content Error !!! Please Contact xiaonanche@qq.com");
		}

		resultArea.append("\n ====== Job Done");
	}
	
	private void initialCustomerFromString(String customerString) {
		if(this.longZhuCustomers == null){
			this.longZhuCustomers = new ArrayList<QuanMinUser>();

			String[] customers = customerString.split("\n");
			for (int i = 0; i< customers.length; i++) {
				String customerStr = customers[i];
				String[] customerFields = customerStr.split(",");
				if (customerFields.length != 6) {
					continue;
				} else {
					String region = customerFields[2].trim();
					String sessionId = customerFields[3].trim();
					String qqNumber = customerFields[5].trim();
					longZhuCustomers.add(new QuanMinUser(region,sessionId,0,qqNumber));
				}
			}

		}
	}

	private void beginWork() {
		cutButton.setEnabled(false);
		
		freeCustomerNumber = new Integer(freeNumberTextField.getText()).intValue(); 
		
		initialCustomerFromString(customerString);
		
		for(int i=0 ; i<longZhuCustomers.size(); i += 1){
			DaiDaThread m = new DaiDaThread(i);
			new Thread(m).start();
			try{
		        Thread.sleep(200);
		      }catch(Exception e){
		        e.printStackTrace();
		      }  
		}

		System.out.println(".... start");
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
				doLongZhuShuaShui(this.threadNumber);
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
		
		if(this.longZhuCustomers == null){
			this.longZhuCustomers = new ArrayList<QuanMinUser>();

			// 120.132.77.173
			longZhuCustomers.add(new QuanMinUser("117","kuaiyong_s57d94296eea0b",160922,"330434980"));
			// mzky2327  3266585     98区，快用 qq:121114123
			longZhuCustomers.add(new QuanMinUser("98","kuaiyong_s57b6f76a97b69",161023,"121114123"));
			// ios longzhu  rr365787   123456  57 qq: 614040711
			longZhuCustomers.add(new QuanMinUser("57","kuaiyong_s557110a3d2052",160930,"614040711"));
			
			// y891223   3266585，一样的快用，98区 qq:373132213
			longZhuCustomers.add(new QuanMinUser("98","kuaiyong_s57b6cd511bcbc",161023,"373132213"));
			// 113 pyw990901628 qwer1234 优趣版本  qq: 1013135714
//			longZhuCustomers.add(new QuanMinUser("113","pyw_45b68174e0020b50f0129f8405eb9526",161009,"1013135714"));
			// 113区 BaiDu ssjia2000 abc123456 qq:346577928 
//			longZhuCustomers.add(new QuanMinUser("113","baidu_1923458579",161009,"346577928"));
			
			// jiuyou longzhu 420690547  13432169259123 81  qq: 1219421451
//			longZhuCustomers.add(new QuanMinUser("81","uc_1474397353",160930,"1219421451"));

		}
	}


public void doLongZhuShuaShui(int threadNumber) {
		
		QuanMinUser user = this.longZhuCustomers.get(threadNumber);
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
					updateBossHPMap(regin,curHp+"");
					if (threadNumber < freeCustomerNumber) {
						sleepInterval = 61;
					} else {
						sleepInterval = 16;
					}
					
					String urlFuHuo = "http://s"+regin+".lz.tuziyouxi.com/cmd.php?moduleId=27&actId=3&ts="
							+ getDateString("")
							+ "&_session="+session+"&";
					String b = sendRequest(urlFuHuo,"data=%7B%0A%20%20%22type%22%3A%20%221%22%0A%7D");
					System.out.println(b);
				}else{
					sleepInterval = 610;
				}

			}else{
				if(dateIndex >= 0){
					dateIndex = -2;
				}
				if(loginIndex >= 0){
					loginIndex = -1;
				}
			}
			
			try {
				landaCount++;
				label.setText(landaCount+"");
				Thread.currentThread().sleep(sleepInterval * 1000);
			} catch (InterruptedException e) {
			}
		}

	}
	
	private void updateBossHPMap(String region, String bossHP) {
		bossHPMap.put(region, bossHP);
		resultArea.setText("Boss HP Status ====== : \n");
		Iterator keyIterator = bossHPMap.keySet().iterator(); 
		while (keyIterator.hasNext()) {
			String regionStr = (String)keyIterator.next();
			String hpStr = bossHPMap.get(regionStr);
			resultArea.append(regionStr + " : "+hpStr+"\n");
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

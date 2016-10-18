package com.dianping.test;

import java.awt.*;
import java.awt.event.*;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.redcdn.test.TestAdapter;

import cn.redcdn.datacenter.httprequest.HttppostDataCenter;

public class LuTools extends Frame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Button cutButton;
	TextField secondText;
	TextField timeText;
	Label label;
	static int timeDuration = 0;

	static Map<String, SocketChannel> channelMap;

	public LuTools() {
		super("Lu 999 614040711 ，搞起！");

		channelMap = new HashMap<String, SocketChannel>();

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
		if (timeDuration < 1)
			timeDuration = 1;

		MyThread m = new MyThread();
		new Thread(m).start();

		System.out.println("...." + interval);
	}

	public static void main(String args[]) {
		LuTools tf1 = new LuTools();
		tf1.setVisible(true);
	}

	static int landaCount = 0;

	/**
	 * ============================================================
	 * @param s
	 * @return
	 */
	public byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
					.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	class MyThread implements Runnable {
		public void run() {
			System.out.println("begin....");
			try {
				do {
					doXiaonancheLanDa();
					label.setText("LanDaCount : " + landaCount);
					Thread.currentThread().sleep((interval * 1000));
				} while (true);

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("end....");
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
	
public void doQuanMinDaiDa(String regin , String session) {

	String liLian = "http://s"+regin+".ss.tuziyouxi.com/cmd.php?moduleId=4&actId=4&ts=1429540167095&_session="+session+"&";
	String a = sendRequest(liLian, "data={\"mission\": 7,\"chapter\": 25}");
	System.out.println(a);
}

public void doQuanMinLiaoLi(String regin , String session) {
	
	String liaoLi = "http://s"+regin+".ss.tuziyouxi.com/cmd.php?moduleId=7&actId=2&ts=1396879194264&_session="+session+"&";
	String a = sendRequest(liaoLi, "data={\"itemid\": 25010001,\"data\": null}");
	System.out.println(a);	
}

	/**
	 * MengXiang HuoYing
	 */

	public void doMengXiangHuoYingLiLian(String regin , String session , String mission , String chapter) {

		String liLian = "http://s"+regin+".hy.youai.tuziyouxi.com/cmd.php?moduleId=4&actId=4&ts=1429540167095&_session="+session+"&";
		String a = sendRequest(liLian, "data={\"mission\": "+mission+",\"chapter\": "+chapter+"}");
//		System.out.println(a);
	}

	public void doMengXiangHuoYingLiaoLi(String regin , String session) {

		String liaoLi = "http://s"+regin+".hy.youai.tuziyouxi.com/cmd.php?moduleId=7&actId=2&ts=1396879194264&_session="+session+"&";
		String a = sendRequest(liaoLi, "data={\"itemid\": 25010001,\"data\": null}");
		System.out.println(a);
	}

	/**
	 * WuKong ChuanQi Long Zhu
	 */

	public void doLongZhuLiLian(String regin , String session , String mission , String chapter) {

		String liLian = "http://s"+regin+".lz.tuziyouxi.com/cmd.php?moduleId=4&actId=4&ts=1429540167095&_session="+session+"&";
		String a = sendRequest(liLian, "data={\"mission\": "+mission+",\"chapter\": "+chapter+"}");
		System.out.println(a);
	}

	public void doLongZhuLiaoLi(String regin , String session) {

		String liaoLi = "http://s"+regin+".lz.tuziyouxi.com/cmd.php?moduleId=7&actId=2&ts=1396879194264&_session="+session+"&";
		String a = sendRequest(liaoLi, "data={\"itemid\": 25010001,\"data\": null}");
		System.out.println(a);
	}

	/**
	 * 
	 * @param regin
	 * @param session
	 * @param itemid  kaixue:23080098  qingdian:23080101
	 * @param data null
	 */
	public void doLongZhuLiBao(String regin , String session, String itemid , String data) {

		String liLian = "http://s"+regin+".lz.tuziyouxi.com/cmd.php?moduleId=7&actId=2&ts=1475364673361&_session="+session+"&";
		String a = sendRequest(liLian, "data={\"itemid\": "+itemid+",\"data\": "+data+"}");
		System.out.println(a);
	}
	
	public void doLongZhuYiZhi10(String regin , String session, int targetYiZhi, int targetLevel) {
		doLongZhuYiZhiIntensify(regin,session,targetYiZhi,targetLevel);
		if (targetYiZhi > 0) {
			for (int i = 0; i< 50; i++) {
				String liLian = "http://s"+regin+".lz.tuziyouxi.com/cmd.php?moduleId=32&actId=5&ts=1475549813651&_session="+session+"&";
				String a = sendRequest(liLian, "data={\"type\": 10}");
				System.out.println(a);
				try {
					Thread.currentThread().sleep((interval * 100));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void doLongZhuYiZhiIntensify(String regin , String session , int targetYiZhi, int targetLevel) {

		// check YiZhi
		String liLian = "http://s"+regin+".lz.tuziyouxi.com/cmd.php?moduleId=32&actId=1&ts=1475551037281&_session="+session+"&";
		String a = sendRequest(liLian, "data=%7B%7D");
		System.out.println(a);
		String loserStr = getAllLoser(a,targetYiZhi,200,targetLevel);
		System.out.println(loserStr);
		
		if (targetYiZhi <= 0 || loserStr.length() <= 0) {
			return;
		}
		// intensify YiZhi
		String intensify = "http://s"+regin+".lz.tuziyouxi.com/cmd.php?moduleId=32&actId=2&ts=1475550477435&_session="+session+"&";
		String b = sendRequest(intensify, "data={\"uid\": "+targetYiZhi+",\"teamMember\": -1,\"consumes\": ["+loserStr+"],\"memberPos\": -1}");
		System.out.println(b);
	}
	
	private static String getAllLoser(String JSONText,int target,int maxCount, int targetLevel) {
		JSONTokener jsonTokener = new JSONTokener(JSONText);
		JSONArray array;
		JSONObject studentJSONObject;
		StringBuffer result = new StringBuffer("");
		int count = 0;
		try {
			array = (JSONArray) jsonTokener.nextValue();
			if (array != null) {
				studentJSONObject = array.getJSONObject(0);
				JSONObject dataObj = (JSONObject) studentJSONObject.get("data");
				JSONArray spiritsArray = (JSONArray) dataObj.get("spirits");
				for (int i = 0; i < spiritsArray.length(); i++) {
					JSONObject yiZhiJSONObject = (JSONObject) spiritsArray.getJSONObject(i);
					int uid = yiZhiJSONObject.getInt("uid");
					int exp = yiZhiJSONObject.getInt("exp");
					int level = yiZhiJSONObject.getInt("level");
					int itemid = yiZhiJSONObject.getInt("itemid");
					int teamMember = yiZhiJSONObject.getInt("teamMember");
					// itemid > 26030000   Eat A:itemid > 26020000
					// && itemid > 26020000 && itemid < 26050000
					if (count < maxCount && target != uid && level == 1 && exp <= 0 && teamMember == -1) {
						count++;
						result.append(uid).append(",");
					}
					if (target == uid) {
						if (level >= targetLevel) {
							System.exit(0);
						} else {
							System.out.println(" target "+uid+" level ======: "+level);
						}
					}
					
				}
			}
		} catch (JSONException e) {
			// e.printStackTrace();
		}
		String resultStr = result.toString();
		if (resultStr.length() > 0) {
			return resultStr.substring(0, resultStr.length()-1);
		} else {
			return resultStr;
		}
		
	}
	
	/**
	 * 
	 */
	public void doXiaonancheLanDa() {

// ***************** mengxiang huoying ****************

		// 账号：15771879505 密码：1341338010 39区 烟花寂凉 对了。梦想火影，iOS快用 qq:1341338010 fu 200
		/*
		doMengXiangHuoYingLiLian("39","kuaiyong_s542ffb663ab1f","0","27");
		doMengXiangHuoYingLiLian("39","kuaiyong_s542ffb663ab1f","1","27");
		doMengXiangHuoYingLiLian("39","kuaiyong_s542ffb663ab1f","2","27");
		doMengXiangHuoYingLiLian("39","kuaiyong_s542ffb663ab1f","3","27");
		doMengXiangHuoYingLiLian("39","kuaiyong_s542ffb663ab1f","4","27");
		doMengXiangHuoYingLiLian("39","kuaiyong_s542ffb663ab1f","5","27");
		doMengXiangHuoYingLiLian("39","kuaiyong_s542ffb663ab1f","7","25");
		doMengXiangHuoYingLiLian("39","kuaiyong_s542ffb663ab1f","8","25");
		doMengXiangHuoYingLiLian("39","kuaiyong_s542ffb663ab1f","9","25");
		doMengXiangHuoYingLiLian("39","kuaiyong_s542ffb663ab1f","10","25");
		 */
		//		doMengXiangHuoYingLiaoLi("39","kuaiyong_s542ffb663ab1f");


// ***************** LongZhu **************** 120.132.77.173
		
		// ios wukongchuanqi  rr365787   123456  57 qq: 614040711
		/* 
		doLongZhuLiLian("57", "kuaiyong_s557110a3d2052", "4", "26");
		doLongZhuLiLian("57", "kuaiyong_s557110a3d2052", "5", "26");
		doLongZhuLiLian("57", "kuaiyong_s557110a3d2052", "7", "26");
		doLongZhuLiLian("57", "kuaiyong_s557110a3d2052", "8", "26");
		doLongZhuLiLian("57", "kuaiyong_s557110a3d2052", "9", "26");
		doLongZhuLiLian("57", "kuaiyong_s557110a3d2052", "10", "26");
		doLongZhuLiLian("57", "kuaiyong_s557110a3d2052", "11", "26");
		doLongZhuLiLian("57", "kuaiyong_s557110a3d2052", "12", "26");
		doLongZhuLiLian("57", "kuaiyong_s557110a3d2052", "0", "27");
		doLongZhuLiLian("57", "kuaiyong_s557110a3d2052", "1", "27");
		doLongZhuLiLian("57", "kuaiyong_s557110a3d2052", "2", "27");
		 */
		
		// 113 pyw990901628 qwer1234 优趣版本  qq: 1013135714 chapter:复活F:27
//		doLongZhuLiLian("113", "pyw_45b68174e0020b50f0129f8405eb9526", "1", "27");
		
		// 113区 BaiDu ssjia2000 abc123456 qq:346577928 
//		doLongZhuLiLian("113", "baidu_1923458579", "2", "27");
		
		//  cc2855155  3266585 116 快用 qq: 121114123 
//		doLongZhuLiLian("116", "kuaiyong_s57eb435aed328", "1", "27");
//		doLongZhuLiLian("116", "kuaiyong_s57eb435aed328", "2", "27");
//		doLongZhuLiLian("116", "kuaiyong_s57eb435aed328", "14", "27");
		
        
		
//		doLongZhuLiaoLi("116", "kuaiyong_s57eb435aed328");

// ***************** LongZhu GuiDao ****************
		//账号zz2855155  3266585 56 qu session:kuaiyong_s57bab91594bc0
		
		// y891223   3266585，一样的快用，98区 qq:373132213  session:kuaiyong_s57b6cd511bcbc
		

		doLongZhuYiZhi10("116","kuaiyong_s57eb435aed328",2005,11);

		
// ***************** LongZhu LiBao ****************
		
		// kaixue:23080098  qingdian:23080101
//		doLongZhuLiBao("98", "kuaiyong_s57b6cd511bcbc", "23080098", "null");
//		doLongZhuLiBao("98", "kuaiyong_s57b6cd511bcbc", "23080101", "null");

		
// ***************** quan min ****************
		// 安卓91平台 6区 账号qxs1219 密码159357  qq : 1655799714  
//		doQuanMinDaiDa("6","380253849"); 
//		doQuanMinLiaoLi("6","380253849");
		
		landaCount++;
		if(landaCount >= timeDuration){
			System.exit(0);
		}
	}

}

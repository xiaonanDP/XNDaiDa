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

import cn.redcdn.datacenter.httprequest.HttppostDataCenter;

import com.infosys.test.QuanMinUser;

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
		if (timeDuration < 10)
			timeDuration = 30;

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
	 * SI SHEN
	 * ============================================================================
	 */
	public void doSiShen_IOS(String host, int port, String loginStr) {
		
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
				cmdList.add("0000002500051C00C8855A17827227CCAA29EB291BF2F87E8FF2C278F28F23D1834185E8BBD628A9D2");
				cmdList.add("000000140006210041855A17827227F5418585C085BBD321");
				cmdList.add("0000001400070400C8855A17827227F5418585C085BB55E4");
				
				// asanjinglianci
				cmdList.add("000000280008040041855A178272274267C2B42990A6F2830A4EAA3E293FC236AA690D0D41852532D671B34B");
				cmdList.add("0000001400090400C8855A17827227F5418585C085BB55EA");
				// xiumubaizai
				cmdList.add("00000028000A040041855A178272274267C2B42990A6F2830A4EAA3E293FC236AA690D2741852549D6E8D44C");
				cmdList.add("00000014000B0400C8855A17827227F5418585C085BB55E8");
				// qilinsitianshilang
				cmdList.add("00000028000C040041855A178272274267C2B42990A6F2830A4EAA3E293FC236AA690D834185251CD62A3EEE");
				cmdList.add("00000014000D0400C8855A17827227F5418585C085BB55EE");
				
				// wanxianshu huifurenlei2
//				cmdList.add("000000280008040041855A178272274267C2B42990A6F283128DAA3E293FC236AA698D0D41852507D6AC5982");
				// huifurenlei1
//				cmdList.add("00000028000A040041855A178272274267C2B42990A6F283128DAA3E293FC236AA698D274185254AD6BA3E85");
				// chadutaihu
//				cmdList.add("00000028000C040041855A178272274267C2B42990A6F283128DAA3E293FC236AA698D8341852527D6D4D427");
				// chongzhuzhanyue2
//				cmdList.add("000000280008040041855A178272274267C2B42990A6F2831282AA3E293FC236AA698D0D418525C4D6A69018");
				// nakeluwaer
//				cmdList.add("000000280008040041855A178272274267C2B42990A6F28312BDAA3E293FC236AA698DE34185251CD6BA46DE");
				// masikulin
//				cmdList.add("000000280008040041855A178272274267C2B42990A6F28312BDAA3E293FC236AA698DE34185251CD6BA46DE");
				
				
				for (int j = 0; j < cmdList.size(); j++) {
					String cmdStr = cmdList.get(j);
					ByteBuffer bb = ByteBuffer
							.wrap(hexStringToByteArray(cmdStr));
					sc.write(bb);
				}
				sc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void chiSiShen_IOS(String host, int port, String loginStr) {
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
			cmdList.add("0000002500051C00C8855A17827227CCAA29EB291BF2F87E8FF2C278F28F23D1834185E8BBD628A9D2");
			cmdList.add("000000140006210041855A17827227F5418585C085BBD321");
			cmdList.add("000000260007070076855A17827227423E29A63E3E160D866BE408EFE758F7EE0A0A1285B4A1D6398993");
			for (int j = 0; j < cmdList.size(); j++) {
				String cmdStr = cmdList.get(j);
				ByteBuffer bb = ByteBuffer
						.wrap(hexStringToByteArray(cmdStr));
				sc.write(bb);
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
}

	public void doSiShen_ANDROID(String host, int port, String loginStr) {
		
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
			cmdList.add("000000140004210041855A17827227F5418585C085BBD323");
			cmdList.add("0000001400050700C8855A17827227F5418585C085BB55E6");
			cmdList.add("0000002500061C00C8855A17827227CCAA29EB291BF2F87E8FF2C278F28F23D1834185E8BBD628A9D1");
			cmdList.add("0000001400070400C8855A17827227F5418585C085BB55E4");
			// huifurenlei2
//			cmdList.add("000000280008040041855A178272274267C2B42990A6F283128DAA3E293FC236AA698D0D41852507D6AC5982");
			// chongzhuzhanyue2
//			cmdList.add("000000280008040041855A178272274267C2B42990A6F2831282AA3E293FC236AA698D0D418525C4D6A69018");
			// huobanyuxi3
			cmdList.add("000000280008040041855A178272274267C2B42990A6F283128DAA3E293FC236AA690DD1418525CDD6376227");
			// tuxishihunjie3
//			cmdList.add("000000280008040041855A178272274267C2B42990A6F28312C5AA3E293FC236AA698D0D418525ADD6B4DDE7");
			// tuxishihunjie2
//			cmdList.add("000000280008040041855A178272274267C2B42990A6F28312C5AA3E293FC236AA698D0D418525ADD6B4DDE7");
			//asanjinglianci2
//			cmdList.add("000000280008040041855A1782722742AA3E293FC236AA690D8D67C2B42990A6F2830AE341852593D6717C39");
			//xiumubaizai
//			cmdList.add("000000280009040041855A1782722742AA3E293FC236AA690D9E67C2B42990A6F2830AE34185257AD6E88114");
			//qilinsitianshilang
//			cmdList.add("00000028000A040041855A1782722742AA3E293FC236AA690D7267C2B42990A6F2830AE34185256ED62A5F95");
			//xiumuluqiya 
//			cmdList.add("00000028000B040041855A1782722742AA3E293FC236AA690D4267C2B42990A6F2830AE341852530D637B25A");
			//asanjinglianci1
//			cmdList.add("00000028000C040041855A1782722742AA3E293FC236AA690D4E67C2B42990A6F2830AE3418525BFD6080BCC");
			//nakeluwaer
//			cmdList.add("000000280008040041855A178272274267C2B42990A6F28312BDAA3E293FC236AA698DE34185251CD6BA46DE");
			// masikulin
//			cmdList.add("000000280009040041855A178272274267C2B42990A6F28312BDAA3E293FC236AA690D1841852598D6D4FC19");
			// jingshangzhiji
//			cmdList.add("000000280008040041855A178272274267C2B42990A6F2830A4EAA3E293FC236AA698DF5418525CCD6B4DE64");
			
			// suifeng
//			cmdList.add("000000280008040041855A1782722742AA3E293FC236AA698DC567C2B42990A6F2830AF54185164DD61B12BB");
			// daqiantianxiqiandai
//			cmdList.add("000000280009040041855A1782722742AA3E293FC236AA698D8D67C2B42990A6F2830AF541851602D6163472");
			// sishenxiguan2
//			cmdList.add("00000028000A040041855A1782722742AA3E293FC236AA698D9E67C2B42990A6F2830AF541851677D625A99B");
			// sishenxiguan1
//			cmdList.add("00000028000B040041855A1782722742AA3E293FC236AA690DBD67C2B42990A6F2830AF5418525B5D6ACAB52");
			// jingshangzhiji
//			cmdList.add("00000028000C040041855A1782722742AA3E293FC236AA698D4267C2B42990A6F2830AE34185167ED6B438FB");
			
			
			for (int j = 0; j < cmdList.size(); j++) {
				String cmdStr = cmdList.get(j);
				ByteBuffer bb = ByteBuffer
						.wrap(hexStringToByteArray(cmdStr));
				sc.write(bb);
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
}
	
	
public void chiSiShen_ANDROID(String host, int port, String loginStr) {
		
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
			cmdList.add("000000140004210041855A17827227F5418585C085BBD323");
			cmdList.add("0000001400050700C8855A17827227F5418585C085BB55E6");
			cmdList.add("0000002500061C00C8855A17827227CCAA29EB291BF2F87E8FF2C278F28F23D1834185E8BBD628A9D1");
			cmdList.add("000000260007070076855A17827227423E29A63E3E160D866BE408EFE758F7EE0A0A1285B4A1D6398993");
	
			for (int j = 0; j < cmdList.size(); j++) {
				String cmdStr = cmdList.get(j);
				ByteBuffer bb = ByteBuffer
						.wrap(hexStringToByteArray(cmdStr));
				sc.write(bb);
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
}

	/**
	 * HUO YING
	 * ============================================================================
	 */
	
	public void doHuoYing_ANDROID(String host, int port, String loginStr) {
		
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
			cmdList.add("0000002500061C00C8855A17827227CCAA29EB291BF2F87E8FF2C278F28F23D1834185E8BBD628A9D1");
			cmdList.add("000000140007250041855A17827227F5418585C085BBD320");
			cmdList.add("0000001400080400C8855A17827227F5418585C085BB55EB");
			
			// yiingfenshen3
//			cmdList.add("00000028000C040041855A1782722742AA3E293FC236AA698D4267C2B42990A6F2830AE34185167ED6B438FB");
			// yiingfenshen2
//			cmdList.add("00000028000D040041855A1782722742AA3E293FC236AA698D4E67C2B42990A6F2830AE341851685D6ACFFFC");
			// yiingfenshen1
//			cmdList.add("00000028000E040041855A1782722742AA3E293FC236AA690DBD67C2B42990A6F2830AE3418525D3D6BA14D0");
			
			// shengmingzhili3
			cmdList.add("000000280009040041855A178272274267C2B42990A6F2830A72AA3E293FC236AA698D0D418525D5D61BE2C5");
			// shengmingzhili2
//			cmdList.add("00000028000A040041855A178272274267C2B42990A6F2830A72AA3E293FC236AA698D274185257FD61685C3");
			// shengmingzhili1
//			cmdList.add("00000028000B040041855A178272274267C2B42990A6F2830A72AA3E293FC236AA698D834185251ED6256C81");
			
			// ningcizhisi3
//			cmdList.add("00000028000C040041855A178272274267C2B42990A6F2830A72AA3E293FC236AA698DF541852530D68FB3E2");
			// ningcizhisi2
//			cmdList.add("00000028000D040041855A1782722742AA3E293FC236AA698D4E67C2B42990A6F2830A8341851641D6A6A211");
			// ningcizhisi1
//			cmdList.add("00000028000E040041855A1782722742AA3E293FC236AA690DBD67C2B42990A6F2830A8341852564D6B4E09C");
			// shiweifuhuo3
//			cmdList.add("000000280009040041855A178272274267C2B42990A6F2831282AA3E293FC236AA698D0D418525C4D6A69019");
			// shiweifuhuo2
//			cmdList.add("00000028000A040041855A178272274267C2B42990A6F2831282AA3E293FC236AA698D2741852548D6B4F71F");
			// shiweifuhuo1
//			cmdList.add("00000028000B040041855A178272274267C2B42990A6F2831282AA3E293FC236AA698D8341852588D6AC3F64");
			// sishenmianju3
//			cmdList.add("000000280009040041855A1782722742AA3E293FC236AA698D8D67C2B42990A6F2830AF541851602D6163472");
			// sishenmianju2
//			cmdList.add("00000028000A040041855A1782722742AA3E293FC236AA698D9E67C2B42990A6F2830AF541851677D625A99B");
			// sishenmianju1
//			cmdList.add("00000028000B040041855A1782722742AA3E293FC236AA698D7267C2B42990A6F2830AF54185165CD68F4AF7");
			// mudunchagan3
//			cmdList.add("00000028000C040041855A1782722742AA3E293FC236AA690DC567C2B42990A6F2830A834185254ED6BACA56");
			// mudunchagan2
//			cmdList.add("000000280009040041855A1782722742AA3E293FC236AA690D8D67C2B42990A6F2830A834185254CD6D4A0DD");
			// mianjunan3
//			cmdList.add("000000280009040041855A1782722742AA3E293FC236AA698D8D67C2B42990A6F2830AF541851602D6163472");
			// mianjunan2
//			cmdList.add("00000028000A040041855A1782722742AA3E293FC236AA698D9E67C2B42990A6F2830AF541851677D625A99B");
			// mianjunan1
//			cmdList.add("00000028000B040041855A1782722742AA3E293FC236AA698D7267C2B42990A6F2830AF54185165CD68F4AF7");
			// xuanwozudi3
//			cmdList.add("00000028000C040041855A1782722742AA3E293FC236AA698D4267C2B42990A6F2830AF5418516EBD6A6877C");
			// xuanwozudi2
//			cmdList.add("00000028000D040041855A178272274267C2B42990A6F2830A42AA3E293FC236AA698DE341852584D6B49BEE");
			// juedefenshen3
//			cmdList.add("000000280009040041855A178272274267C2B42990A6F2830A42AA3E293FC236AA690DD14185252DD6D47CC1");
			// juedefenshen2
//			cmdList.add("00000028000A040041855A178272274267C2B42990A6F2830A42AA3E293FC236AA690D0D418525F6D697B624");
			// juedefenshen1
//			cmdList.add("00000028000B040041855A178272274267C2B42990A6F2830A42AA3E293FC236AA690D27418525CCD671B1E6");
			// suitubengsan3
//			cmdList.add("00000028000C040041855A178272274267C2B42990A6F2830A42AA3E293FC236AA690D83418525F0D6E8D2A6");
			// suitubengsan2
//			cmdList.add("00000028000D040041855A178272274267C2B42990A6F2830A42AA3E293FC236AA690DF5418525EFD62A2D81");
			// suitubengsan1
//			cmdList.add("00000028000E040041855A178272274267C2B42990A6F2830A42AA3E293FC236AA690DE341852503D637A407");
									
			
			for (int j = 0; j < cmdList.size(); j++) {
				String cmdStr = cmdList.get(j);
				ByteBuffer bb = ByteBuffer
						.wrap(hexStringToByteArray(cmdStr));
				sc.write(bb);
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
}
	
public void doHuoYing_IOS(String host, int port, String loginStr) {
		
		try {
			InetSocketAddress remote = new InetSocketAddress(host, port);
			SocketChannel sc = SocketChannel.open();
			sc.connect(remote);
			if (sc.finishConnect())
				System.out.println("connected...");

			List<String> cmdList = new ArrayList<String>();
			cmdList.add(loginStr);
			cmdList.add("0000001400020400C8855A17827227F5418585C085BB55E1");
			cmdList.add("0000001400030300AE855A17827227F5418585C085BBA3220000001400040300D6855A17827227F5418585C085BB25E1");
			cmdList.add("0000001400050700C8855A17827227F5418585C085BB55E6");
			cmdList.add("0000002500061C00C8855A17827227CCAA29EB291BF2F87E8FF2C278F28F23D1834185E8BBD628A9D1");
			cmdList.add("000000140007250041855A17827227F5418585C085BBD320");
			cmdList.add("0000001400080400C8855A17827227F5418585C085BB55EB");
			// juefenshen3
//			cmdList.add("000000280009040041855A1782722742AA3E293FC236AA690DC567C2B42990A6F2830AF5418525FCD6D485B1");
			// juefenshen2
//			cmdList.add("00000028000B040041855A1782722742AA3E293FC236AA690D8D67C2B42990A6F2830AF541852540D697A37B");
			// juefenshen1
//			cmdList.add("00000028000D040041855A1782722742AA3E293FC236AA690D9E67C2B42990A6F2830AF54185258ED6713E97");
			// shengmingzhili3
//			cmdList.add("000000280009040041855A1782722742AA3E293FC236AA698D8D67C2B42990A6F2830A8341851680D61B37D6");
			// shengmingzhili2
//			cmdList.add("00000028000B040041855A1782722742AA3E293FC236AA698D9E67C2B42990A6F2830A8341851690D6166359");
			// shengmingzhili1
//			cmdList.add("00000028000D040041855A1782722742AA3E293FC236AA698D7267C2B42990A6F2830A834185163DD6254955");
			// ningcizhisi3
//			cmdList.add("00000028000F040041855A1782722742AA3E293FC236AA698D4267C2B42990A6F2830A83418516A2D68F4E5B");
			// mianjunan3
//			cmdList.add("000000280009040041855A178272274267C2B42990A6F2830A4EAA3E293FC236AA698D0D41852510D6258CA0");
			// mianjunan2
//			cmdList.add("00000028000B040041855A178272274267C2B42990A6F2830A4EAA3E293FC236AA698D274185252DD68FEBA7");
			// mianjunan1
//			cmdList.add("00000028000D040041855A178272274267C2B42990A6F2830A4EAA3E293FC236AA698D83418525F6D6A60105");
			// yingfenshen3
//			cmdList.add("00000028000F040041855A178272274267C2B42990A6F2830A4EAA3E293FC236AA698DF5418525CCD6B4DE63");
			// sishenmianju3
			cmdList.add("000000280009040041855A1782722742AA3E293FC236AA698D8D67C2B42990A6F2830AF541851602D6163472");
			cmdList.add("00000014000A0400C8855A17827227F5418585C085BB55E9");
			// sishenmianju2
			cmdList.add("00000028000B040041855A1782722742AA3E293FC236AA698D9E67C2B42990A6F2830AF541851677D625A99A");
			cmdList.add("00000014000C0400C8855A17827227F5418585C085BB55EF");
			// sishenmianju1
			cmdList.add("00000028000D040041855A1782722742AA3E293FC236AA698D7267C2B42990A6F2830AF54185165CD68F4AF1");
			cmdList.add("00000014000E0400C8855A17827227F5418585C085BB55ED");
			// xuanwozudi3
			cmdList.add("00000028000F040041855A1782722742AA3E293FC236AA698D4267C2B42990A6F2830AF5418516EBD6A6877F");
			cmdList.add("0000001400100400C8855A17827227F5418585C085BB55F3");
			// xuanwozudi2
			cmdList.add("000000280011040041855A1782722742AA3E293FC236AA698D4E67C2B42990A6F2830AF541851676D6B4A1A9");
			cmdList.add("0000001400120400C8855A17827227F5418585C085BB55F1");
			
			// xuanwozudi1
//			cmdList.add("000000280013040041855A178272274267C2B42990A6F2830A42AA3E293FC236AA690D184185259CD6ACE0BA");
			// jue@fenshen3
//			cmdList.add("000000280009040041855A178272274267C2B42990A6F2830A42AA3E293FC236AA690DD14185252DD6D47CC1");
			// moxiangliliang6
//			cmdList.add("000000280009040041855A178272274267C2B42990A6F2830A4EAA3E293FC236AA690DD141852556D697B0CB");
			// moxiangliliang5
//			cmdList.add("00000028000B040041855A178272274267C2B42990A6F2830A4EAA3E293FC236AA690D0D41852532D671B348");
			// moxiangliliang4
//			cmdList.add("00000028000D040041855A178272274267C2B42990A6F2830A4EAA3E293FC236AA690D2741852549D6E8D44B");
			// moxiangliliang3
//			cmdList.add("00000028000F040041855A178272274267C2B42990A6F2830A4EAA3E293FC236AA690D834185251CD62A3EED");
			// moxiangliliang2
//			cmdList.add("000000280011040041855A178272274267C2B42990A6F2830A4EAA3E293FC236AA690DF541852598D637E197");
			// moxiangliliang1
//			cmdList.add("000000280013040041855A178272274267C2B42990A6F2830A4EAA3E293FC236AA690DE3418525C4D6081D8B");
			// qingjiaodiren3
//			cmdList.add("000000280015040041855A178272274267C2B42990A6F28312BDAA3E293FC236AA698D0D41852503D68F968F");
			// qingjiaodiren2
//			cmdList.add("000000280017040041855A178272274267C2B42990A6F28312BDAA3E293FC236AA698D2741852556D6A6D1CA");
			// qingjiaodiren1
//			cmdList.add("000000280019040041855A178272274267C2B42990A6F28312BDAA3E293FC236AA698D8341852532D6B4B283");
			
			
			
//			cmdList.add("0000001400140400C8855A17827227F5418585C085BB55F7");
//			cmdList.add("0000001400160400C8855A17827227F5418585C085BB55F5");
//			cmdList.add("0000001400180400C8855A17827227F5418585C085BB55FB");
//		    cmdList.add("00000014001A0400C8855A17827227F5418585C085BB55F9");
			for (int j = 0; j < cmdList.size(); j++) {
				String cmdStr = cmdList.get(j);
				ByteBuffer bb = ByteBuffer
						.wrap(hexStringToByteArray(cmdStr));
				sc.write(bb);
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
}

public void chiHuoYing_ANDROID(String host, int port, String loginStr) {
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
		cmdList.add("0000002500061C00C8855A17827227CCAA29EB291BF2F87E8FF2C278F28F23D1834185E8BBD628A9D1");
		cmdList.add("000000140007250041855A17827227F5418585C085BBD320");
		cmdList.add("000000260008070076855A17827227423E29A63E3E160D866BE408EFE758F7EE0A0A1285B4A1D639899C");
		
		for (int j = 0; j < cmdList.size(); j++) {
			String cmdStr = cmdList.get(j);
			ByteBuffer bb = ByteBuffer
					.wrap(hexStringToByteArray(cmdStr));
			sc.write(bb);
		}
		sc.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

public void chiHuoYing_IOS(String host, int port, String loginStr) {
	try {
		InetSocketAddress remote = new InetSocketAddress(host, port);
		SocketChannel sc = SocketChannel.open();
		sc.connect(remote);
		if (sc.finishConnect())
			System.out.println("connected...");

		List<String> cmdList = new ArrayList<String>();
		cmdList.add(loginStr);
		cmdList.add("0000001400020400C8855A17827227F5418585C085BB55E1");
		cmdList.add("0000001400030300AE855A17827227F5418585C085BBA3220000001400040300D6855A17827227F5418585C085BB25E1");
		cmdList.add("0000001400050700C8855A17827227F5418585C085BB55E6");
		cmdList.add("0000002500061C00C8855A17827227CCAA29EB291BF2F87E8FF2C278F28F23D1834185E8BBD628A9D1");
		cmdList.add("000000140007250041855A17827227F5418585C085BBD320");
		cmdList.add("000000260008070076855A17827227423E29A63E3E160D866BE408EFE758F7EE0A0A1285B4A1D639899C");
		
		for (int j = 0; j < cmdList.size(); j++) {
			String cmdStr = cmdList.get(j);
			ByteBuffer bb = ByteBuffer
					.wrap(hexStringToByteArray(cmdStr));
			sc.write(bb);
		}
		sc.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}


/**
 * HAI ZEI
 * ============================================================================
 */
public void doHaiZei_IOS(String host, int port, String loginStr) {
	
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
			cmdList.add("0000002500051C00C8855A17827227CCAA29EB291BF2F87E8FF2C278F28F23D1834185E8BBD628A9D2");
			cmdList.add("0000001400060400C8855A17827227F5418585C085BB55E5");
			
			// yanjiusuoguanli4
			cmdList.add("000000280007040041855A1782722742AA3E293FC236AA698D8D67C2B42990A6F2830A8341851680D61B37D8");
			
			for (int j = 0; j < cmdList.size(); j++) {
				String cmdStr = cmdList.get(j);
				ByteBuffer bb = ByteBuffer
						.wrap(hexStringToByteArray(cmdStr));
				sc.write(bb);
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
}

public void chiHaiZei_IOS(String host, int port, String loginStr) {
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
		cmdList.add("000000140004250041855A17827227F5418585C085BBD323");
		cmdList.add("0000001400050700C8855A17827227F5418585C085BB55E6");
		cmdList.add("000000260007070076855A178272274216A629A6F5423E29A63E3E160D866BE408EF12854D61D6391915");
		
		for (int j = 0; j < cmdList.size(); j++) {
			String cmdStr = cmdList.get(j);
			ByteBuffer bb = ByteBuffer
					.wrap(hexStringToByteArray(cmdStr));
			sc.write(bb);
		}
		sc.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

public void doHaiZei_Android(String host, int port, String loginStr) {
	
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
		cmdList.add("000000140004250041855A17827227F5418585C085BBD323");
		cmdList.add("0000001400050700C8855A17827227F5418585C085BB55E6");
		cmdList.add("0000002500061C00C8855A17827227CCAA29EB291BF2F87E8FF2C278F28F23D1834185E8BBD628A9D1");
		cmdList.add("0000001400070400C8855A17827227F5418585C085BB55E4");
		
		// neo haijunganbu3
		cmdList.add("000000280008040041855A1782722742AA3E293FC236AA69124E67C2B42990A6F2830AE3418516E3D667FE3A");
		// neo haijunganbu2
		cmdList.add("000000280009040041855A1782722742AA3E293FC236AA698DBD67C2B42990A6F2830AE3418516B9D6239E71");
		// neo haijunganbu1
//		cmdList.add("00000028000A040041855A178272274267C2B42990A6F2830A4EAA3E293FC236AA698D6941852584D61B5046");
		
		for (int j = 0; j < cmdList.size(); j++) {
			String cmdStr = cmdList.get(j);
			ByteBuffer bb = ByteBuffer
					.wrap(hexStringToByteArray(cmdStr));
			sc.write(bb);
		}
		sc.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

public void chiHaiZei_Android(String host, int port, String loginStr) {
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
	cmdList.add("000000140004250041855A17827227F5418585C085BBD323");
	cmdList.add("0000001400050700C8855A17827227F5418585C085BB55E6");
	cmdList.add("000000260007070076855A178272274216A629A6F5423E29A63E3E160D866BE408EF12854D61D6391915");
	
	for (int j = 0; j < cmdList.size(); j++) {
		String cmdStr = cmdList.get(j);
		ByteBuffer bb = ByteBuffer
				.wrap(hexStringToByteArray(cmdStr));
		sc.write(bb);
	}
	sc.close();
} catch (IOException e) {
	e.printStackTrace();
}
}

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
 * MaFu
 * @param host
 * @param port
 * @param loginStr
 */

public void doHuoYing_MaFu(String host, int port, String loginStr) {
	
	try {
		InetSocketAddress remote = new InetSocketAddress(host, port);
		SocketChannel sc = SocketChannel.open();
		sc.connect(remote);
		if (sc.finishConnect())
			System.out.println("connected...");

		List<String> cmdList = new ArrayList<String>();
		cmdList.add(loginStr);
		cmdList.add("0000001400020400C8855A17827227F5418585C085BB55E1");
		cmdList.add("0000001400030300AE855A17827227F5418585C085BBA3220000001400040300D6855A17827227F5418585C085BB25E1");
		cmdList.add("0000001400050700C8855A17827227F5418585C085BB55E6");
		cmdList.add("0000002500061C00C8855A17827227CCAA29EB291BF2F87E8FF2C278F28F23D1834185E8BBD628A9D1");
		cmdList.add("0000001400070400C8855A17827227F5418585C085BB55E4");
		
		// sishenmianju3
//		cmdList.add("000000280008040041855A178272274267C2B42990A6F2830A42AA3E293FC236AA698D0D418525A8D6160F0A");
//		cmdList.add("0000001400090400C8855A17827227F5418585C085BB55EA");
		// sishenmianju2
//		cmdList.add("00000028000A040041855A178272274267C2B42990A6F2830A42AA3E293FC236AA698D27418525CFD62508CB");
//		cmdList.add("00000014000B0400C8855A17827227F5418585C085BB55E8");
		// sishenmianju1
//		cmdList.add("00000028000C040041855A178272274267C2B42990A6F2830A42AA3E293FC236AA698D834185258BD68F6B8A");
//		cmdList.add("00000014000D0400C8855A17827227F5418585C085BB55EE");
		// mianjunan3
//		cmdList.add("00000028000E040041855A178272274267C2B42990A6F2830A4EAA3E293FC236AA698D0D41852510D6258CA7");
//		cmdList.add("00000014000F0400C8855A17827227F5418585C085BB55EC");
		// juefenshen3
//		cmdList.add("000000280008040041855A178272274267C2B42990A6F28312C5AA3E293FC236AA698D0D418525ADD6B4DDE7");
//		cmdList.add("0000001400090400C8855A17827227F5418585C085BB55EA");
		
		cmdList.add("000000280008040041855A178272274267C2B42990A6F283129EAA3E293FC236AA690DE3418525BAD6DB3251");
		cmdList.add("0000001400090400C8855A17827227F5418585C085BB55EA");
		cmdList.add("00000028000A040041855A178272274267C2B42990A6F283129EAA3E293FC236AA690DF5418525B4D6FDF8B5");
		cmdList.add("00000014000B0400C8855A17827227F5418585C085BB55E8");
		cmdList.add("00000028000C040041855A178272274267C2B42990A6F283129EAA3E293FC236AA690D834185258FD6DD4711");
		cmdList.add("00000014000D0400C8855A17827227F5418585C085BB55EE");
		cmdList.add("00000028000E040041855A178272274267C2B42990A6F283129EAA3E293FC236AA690D2741852516D6E42454");
		cmdList.add("00000014000F0400C8855A17827227F5418585C085BB55EC");
		cmdList.add("000000280010040041855A178272274267C2B42990A6F283129EAA3E293FC236AA690D0D41852523D64D630D");
		cmdList.add("0000001400110400C8855A17827227F5418585C085BB55F2");
		cmdList.add("000000280012040041855A178272274267C2B42990A6F283129EAA3E293FC236AA690DD1418525C0D608EA8A");
		cmdList.add("0000001400130400C8855A17827227F5418585C085BB55F0");
		
		/*
		cmdList.add("000000280008040041855A178272274267C2B42990A6F2830DBDAA3E293FC236AA690DE341858FCAD63F195F");
		cmdList.add("0000001400090400C8855A17827227F5418585C085BB55EA");
		cmdList.add("00000028000A040041855A178272274267C2B42990A6F2830DBDAA3E293FC236AA690DF541858F6ED678D3BB");
		cmdList.add("00000014000B0400C8855A17827227F5418585C085BB55E8");
		cmdList.add("00000028000C040041855A178272274267C2B42990A6F2830DBDAA3E293FC236AA690D8341858F79D6A5FF26");
		cmdList.add("00000014000D0400C8855A17827227F5418585C085BB55EE");
		cmdList.add("00000028000E040041855A178272274267C2B42990A6F2830DBDAA3E293FC236AA690D2741858F91D6459C63");
		cmdList.add("00000014000F0400C8855A17827227F5418585C085BB55EC");
		cmdList.add("000000280010040041855A178272274267C2B42990A6F2830DBDAA3E293FC236AA690DD141858FC6D6E7C9D4");
		cmdList.add("0000001400110400C8855A17827227F5418585C085BB55F2");
		cmdList.add("000000280012040041855A178272274267C2B42990A6F2830DBDAA3E293FC236AA690D6941858FF4D6807674");
		cmdList.add("0000001400130400C8855A17827227F5418585C085BB55F0");
		cmdList.add("000000280014040041855A178272274267C2B42990A6F2830DBDAA3E293FC236AA690D1841858F7AD6DE1535");
		cmdList.add("0000001400150400C8855A17827227F5418585C085BB55F6");
		cmdList.add("000000280016040041855A178272274267C2B42990A6F2830DBDAA3E293FC236AA698DE341858F8ED6D7AFF1");
		cmdList.add("0000001400170400C8855A17827227F5418585C085BB55F4");
		cmdList.add("000000280018040041855A178272274267C2B42990A6F2838D4EAA3E293FC236AA690DE341858FF4D6789817");
		cmdList.add("0000001400190400C8855A17827227F5418585C085BB55FA");
		cmdList.add("00000028001A040041855A178272274267C2B42990A6F2838D4EAA3E293FC236AA690DF541858F7AD6A508AD");
		cmdList.add("00000014001B0400C8855A17827227F5418585C085BB55F8");
		cmdList.add("00000028001C040041855A178272274267C2B42990A6F2838D4EAA3E293FC236AA690D8341858F8ED645D7CF");
		cmdList.add("00000014001D0400C8855A17827227F5418585C085BB55FE");
		*/
		
		for (int j = 0; j < cmdList.size(); j++) {
			String cmdStr = cmdList.get(j);
			ByteBuffer bb = ByteBuffer
					.wrap(hexStringToByteArray(cmdStr));
			sc.write(bb);
		}
		sc.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

public void chiHuoYing_MaFu(String host, int port, String loginStr) {
	try {
		InetSocketAddress remote = new InetSocketAddress(host, port);
		SocketChannel sc = SocketChannel.open();
		sc.connect(remote);
		if (sc.finishConnect())
			System.out.println("connected...");

		List<String> cmdList = new ArrayList<String>();
		cmdList.add(loginStr);
		cmdList.add("0000001400020400C8855A17827227F5418585C085BB55E1");
		cmdList.add("0000001400030300AE855A17827227F5418585C085BBA3220000001400040300D6855A17827227F5418585C085BB25E1");
		cmdList.add("0000001400050700C8855A17827227F5418585C085BB55E6");
		cmdList.add("0000002500061C00C8855A17827227CCAA29EB291BF2F87E8FF2C278F28F23D1834185E8BBD628A9D1");
		cmdList.add("000000260007070076855A178272274216A629A6F5423E29A63E3E160D866BE408EF12854D61D6391915");
		
		for (int j = 0; j < cmdList.size(); j++) {
			String cmdStr = cmdList.get(j);
			ByteBuffer bb = ByteBuffer
					.wrap(hexStringToByteArray(cmdStr));
			sc.write(bb);
		}
		sc.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

	/**
	 * 
	 */
	public void doXiaonancheLanDa() {

		// ***************** mengxiang huoying ****************

		// 账号：15771879505 密码：1341338010 39区 烟花寂凉 对了。梦想火影，iOS快用 qq:1341338010 fu 200
		/*
		doMengXiangHuoYingLiLian("39","kuaiyong_s542ffb663ab1f","0","21");
		doMengXiangHuoYingLiLian("39","kuaiyong_s542ffb663ab1f","1","21");
		doMengXiangHuoYingLiLian("39","kuaiyong_s542ffb663ab1f","2","21");
		doMengXiangHuoYingLiLian("39","kuaiyong_s542ffb663ab1f","3","21");
		doMengXiangHuoYingLiLian("39","kuaiyong_s542ffb663ab1f","4","21");
		doMengXiangHuoYingLiLian("39","kuaiyong_s542ffb663ab1f","5","21");
		doMengXiangHuoYingLiLian("39","kuaiyong_s542ffb663ab1f","7","21");
		doMengXiangHuoYingLiLian("39","kuaiyong_s542ffb663ab1f","8","21");
		doMengXiangHuoYingLiLian("39","kuaiyong_s542ffb663ab1f","9","21");
		doMengXiangHuoYingLiLian("39","kuaiyong_s542ffb663ab1f","10","21");
		*/

		//		doMengXiangHuoYingLiaoLi("39","kuaiyong_s542ffb663ab1f");


		// ***************** LongZhu ****************

		// ios wukongchuanqi  rr365787   123456  57 qq: 614040711
		/* */
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


//		doLongZhuLiaoLi("57", "kuaiyong_s557110a3d2052");


// ***************** quan min ****************
		// 安卓91平台 6区 账号qxs1219 密码159357  qq : 1655799714  
//		doQuanMinDaiDa("6","380253849"); 
		
		
//		doQuanMinLiaoLi("6","380253849");
// ***************** hai zei ****************		
		// 账号：Gordonagua18  密码：220101 14区 快用的 qq: 2857484368
//		doHaiZei_IOS("121.199.15.9",8100,"000000790001020076855A1782722742671623C2783EF30D35EDAA788FB6AA2516781B8066A23F23F20125F232AA160DFB57FC676EBF90085915EEE7082F602BFDB38BCF900A630A736ECBE7E478F7CF66902E3ED20246D23B80A17A319645B6F40F36407A29A17A3680D0EEB63131407AF429A1EF1285D3F49404798D");			
		// element2601 gyywhy  360平台 36区
//		doHaiZei_Android("112.124.6.242",8400,"0000007C0001020076855A1722B045EBF4A57E8537D4F802CFAE7184B08362CF6E3C782AAF5FC0D65F42A843947209DDC4FD595C152E182E990BCA59FE10B77EF32077BA6E8342DF71E1CB761E7ACE2430143070F01C5468BBBCD2BE9C45D92BF7A7B3ABBC41376C20C86C83850C00656A68C76D9D257D9D50083F2394265153"); 			
		
		
//		chiHaiZei_IOS("121.199.15.9",8100,"000000790001020076855A1782722742671623C2783EF30D35EDAA788FB6AA2516781B8066A23F23F20125F232AA160DFB57FC676EBF90085915EEE7082F602BFDB38BCF900A630A736ECBE7E478F7CF66902E3ED20246D23B80A17A319645B6F40F36407A29A17A3680D0EEB63131407AF429A1EF1285D3F49404798D");			
				
// ***************** si shen ****************		
		// 账号sephirothgao，密码vivi1234，ios22区 qq: 2510907595 
//		doSiShen_IOS("112.124.27.168",8100,"000000780001020076855A1722B0F79AC3A5A285374BDA65C0C06E835D6C7EAD8F772B53BCC865ECD8706CD1107E35B6576266959C1678F097FB7022AA3C45B6F7AEA9A23C4FFBEDD18F17E70FA14326B660D71EB78C81AE4E4EE43659656B4AC39B3F415A10C455BA88CFFADCDC9E8C08F4939075DCB2312023558E");			
		// 360平台65区  账号13954242125  密码142857 qq: 228856745 
//		doSiShen_ANDROID("112.124.103.233",8500,"0000007C0001020076855A179A9545EBF4A57E85374B7B7EA7C6EFAC7F1703734C0A26B97372BF7EEE901B12158D86A0753BA0B77EB5D0AB3A6040F839028B69EE2AAA530A5065D551C94CA5B51E9E3F0FF097A50ADF24D3678B4618572503B06CFD1A86EA0B7C25DBEAEAA53818D3A5C8CDE856C76DE59C4E858A1694E81E9D");		
		//安卓 乐汇  25391301.        6521  3qu
//		doSiShen_ANDROID("112.124.6.30",8100,"000000760001020076855A1782722742671623C2783EF30D6CD702DECA8080D7A54C8223FA90468F4283F5FE3F9095AA3E78B636AA69695F3FC216AA252946F28FD12880E723801B8F4523F71645453A16E725458FDEA5A6162325CA8002D71BE7D725123F23F20125F232AA160D50EAAA501285A8ED5320C086");				
		
		// ios 41  112.124.27.168 8300 账号a2437233018 密码542397 qq: 289870743 
//		doSiShen_IOS("112.124.27.168",8300,"0000007E0001020076855A1722B02AEBF420A285C6E37E61B54E005E5AABD5404F0353FFB17B5D5CEBE8715BECDE4D8E26E0E7569530FA9907AAB3CE3AAE85680D79483AE3D212C2B746494C21EDFFA55188F69370F6D32EDD5F113742503C08E32BA2E12D7A5DD8A9631911748AADAAEC646B338ED3F9F27E8F5B08C6A794B85EA0");
		// 账号feizhui521 wd3109002 67区 91平台 qq: 277505912 
//		doSiShen_ANDROID("112.124.6.30",8500,"0000007C0001020076855A1722B02AEBF420A285C6A2A4EBA7B0AE6D6ACA5D2940D87845F3A2E52A4ACA4C64A4149E5E9C5065B3CCAAA2842E02E6D35E94B359FFDA85448C933CC4612E76D602740A090A743EB770090274FFD75361FEFFDFC8C777954E82D0F75898ACD33D9D4997B60DE0288F6B4A7167061DAAD453A3CD6F");		
		// 乐汇平台，账号qxs004，密码159357258，23区，打2841经验的
//		doSiShen_ANDROID("112.124.6.30",8400,"000000730001020076855A1782722742671623C2783EF30D82F23090D7A5A5123F9095AA3E78B636AA69695F3F23F20125F232AA160D50EA78CD6ECBE7E478F7CF66902E3ED2903E403BCBDE46F2F258A140DDA13693A558D20F404C403640C6963E7A2019C4FDB38BCF900A630A0A1285BB6C530CCE3B");
//		91平台  49区  sj539787928 wolovemajuan666  qq: 365078794  lu 999
//		doSiShen_ANDROID("112.124.6.30",8500,"0000007E0001020076855A179A361A9AF4A5A285C0FBE83FF0C4960593B878B5222616C43FDC2065318D44A2AB7F4B3AC6A44E9AD81084C5343954F0D3A1858ED076692DD2EC0EA89C65D319194A002768A81B17219B40F84DE085204169B02D22C8E45F60F933B19F9B673591F0CD25DDA0992F3C6193B517B33408B2D3947A1197");		
		// 2liutxl   3656151    安卓乐汇38区 qq:290607121
//		doSiShen_ANDROID("112.124.6.30",8400,"000000740001020076855A17827227FE3F9095AA3E78B636AA69825995B629F25B0F8D671623C2783EF30D695F3FC216AA252946F28FD128808FE7802525021645DEA645B4454559A6F766A5801B021B801B168059F780A56C3F23F20125F232AA160D50EA7860FDB38BCF900A630A0A1285BBCE533A3395");		
		//IOS  chizhifeng  40237022   qq: 378234249  2qu 打下任帝王里那个2713的就可以了
//		doSiShen_IOS("112.124.27.168",8200,"000000770001020076855A17827227FE3F9095AA3E78B636AA696C1BC20F30950F16253E25BD671623C2783EF30D695F3FC216AA252946F28FD128168059B4DEE702E7E716E78F456766B40216DECAB4DE3A164559E71616F78024A23F23F20125F232AA160D5009B42BFDB38BCF900A630A0A1285357D94241F95");			
		// android 23 feizhui521@126.com  wd3109002  360   qq: 277505912
//		doSiShen_ANDROID("112.124.6.30",8400,"0000007F0001020076855A1722B02AEBF420A285C6E37E61B54E0018D3D529AF7BF199F4D819F6FA8D20BB8F6015EC1899A1501E5E6592625742A89F4144479DB098C829DB6591D93A8AAC9EADB9D2E78E3EAF45768F7F696A66A25F670398BB657F5E3A25FF25218A5ADCDCAF21A013C52346BCF8C5FB1301FB144C948EBD20656B6B");		
		// 账号kyu813  密码wd02140521 14 qq: 277505912
//		doSiShen_ANDROID("112.124.6.30",8100,"000000730001020076855A1782722742671623C2783EF30D823E1E29B3A5808C3F9095AA3E78B636AA69695F3F23F20125F232AA160D50EAAA676ECBE7E478F7CF66902E3ED2A5A13BCB19403B4C31CBB6930F4C80F2D2934C0F40313B7AF4930F7A7A40022BFDB38BCF900A630A0A1285BC933DB7F18E");		
		// 15206192140  5802311849  360  23 qu 
//		doSiShen_ANDROID("112.124.6.30",8400,"0000007D0001020076855A179A0F45EBF420FED64EEFBBD6DBCBD3BB3F081D0C8904463D3E25C95CD6B0C007911B689EFB0464FF27C08201A73CA784B786C133D6A3B58EBA1FB814B1529D97AA42913142859412A06F1DAA9F50C6D2F0A58E097D6D1728F2E9E8483CE11800E386576E23DA84C9FB063479D7061D00CE948E919D");		
		// 帐号2385749331@qq.com 密码qq960707 68区 91平台 qq: 2385749331 
//		doSiShen_ANDROID("112.124.6.30",8600,"0000007C0001020076855A1722B045EBF4A57E853722BE4B1700DA6DE328CD3B9D3C782A77DE90AE2C57057A685A5C7C8D1E94CD5AFBFF9DF60BECD9764812DA5F97F585C4EC36C38810A654CD314CFC076291ADB27C65CA559040AD05EA0A50963A20966738C4B74F686E360F30412B4703F0113325BC9F709F4079947EC673");		
		// 15239097599  suxingyu@1203 360 43区 qq:975143047 
//		doSiShen_ANDROID("112.124.6.30",8500,"0000007E0001020076855A1722B025EBF420A28593EF802917B15D038D271CAA48256740E3FDBB83981C1182C791FBAA727482EE4CC2A7E5C7C2386AFD7C070B9B4F741ED492DE48D248D62AFD37A493DBBD7889FB0673793D4C841458B5D8F79174AD2C2C685CD208C41B3F11D5E39DA29AF7581064AB513DB1A2EA886A94B716CD");		
		// 18280119488   gyywhy  9qu 360平台  qq: 554353735  lu 999
//		doSiShen_ANDROID("112.124.6.30",8100,"0000007C0001020076855A179AB045EB76DAA285C6E37EA14F0DB0E787F6D4B19D405E2DA9657EB1E3B5CB8268555068D25086D7815B6BD2ACA73E8112202064852CC1A0E82F5A57259D5E9D0B2EA7BD741912287CAB7FE6E0156140F556510621D2E1DEABE789C842B4A6E1BF97E3F68544B4A25D943A3B361DCBFBDA1D3F39");		
		// cy336336  wolovemajuan 91平台  51区 qq : 386216777 
//		doSiShen_ANDROID("112.124.6.30",8500,"0000007D0001020076855A1722B01A9AF4A5A285C04BDA65A62A8D46CE817294465041B83F184276FDD51F7AD7878E2991593E06E8369B90717CE485982293C87232AD2CC20EB7BC3A93CB10CE521CB59E9683CDFFBFBB4BCD0C2CFB2DCBC9548BF940FC4053C9FDEAEAFFF6AA4A8046D1B5347CA94213D3A66BC8CFC820D61B16");
		//		sbwsbw1017   ccas5312  官方  60 qu  qq: 321364288 
//		doSiShen_ANDROID("112.124.6.30",8500,"000000740001020076855A1782722742671623C2783EF30D6CF223019F9671AE71288DC48B553B55F03655DDD7778D7236D3917AF0408BB9D7F19735B905B7CCCFB963B9E73CE72869E5EEBCB9AE35EE05283C63FE69205950FDB38BCF900A630A736ECBB34329B3904DE7B1D1DC215CBA8597B794E31C41");
		//  九游 307852888  luwenlong  61    qq: 503835829
//		doSiShen_ANDROID("112.124.6.30",8500,"0000007C0001020076855A1782722742671623C2783EF30D05800202A58045A5DEA5DE800280E702458D23FA90468F4283F5FE3F9095AA3E78B636AA69695F3FC216AA252946F28FD128801BE7E74502D72480C806E745B4252366B48F4545D74580B402D723231B026C3F23F20125F232AA160D50EA6DF61285DA6C536F3A70");
		// "qxs004" 密码159357258  乐汇 ,151001,"1023"  34111572
//		doSiShen_ANDROID("112.124.6.30",8400,"000000730001020076855A17827227FE3F9095AA3E78B636AA6982F23090D7A5A5123FC216AA252946F28FD128808F3A8059231B8F2525A6F7806745E78016A680D580B3803A807FB4E745B4CA4E23FA90468F4283F5FE3F23F20125F232AA160D50EA789C0845E0E015806D98602B12853ECE530C5350");		
		// pihuo110密码worinima安卓14区 qq: 30541052 
//		doSiShen_ANDROID("112.124.6.30",8100,"000000750001020076855A1782722742671623C2783EF30D6C290F36903E5980804E23FA90468F4283F5FE3F9095AA3E78B636AA69695F3FC216AA252946F28FD12880D7A523458016D7D71BD72325021BF7B48059597F80D7B4D7A6A63A16026680653F23F20125F232AA160D50EAAA2F1285FCDB53882CE2");		
		// 13119509072 密码  1121243278 360  68 qu  qq: 1121243278  
//		doSiShen_ANDROID("112.124.6.30",8600,"0000007D0001020076855A1722B025EBF420A28593EF802917B15D038D2727A5EB29745FE7C569E1471C1182C70E806D7274BC8B369284BE8ED2359228B5FEF55D5345394A4F4E1B312CA1B44BDDDBBC81A7D0BA86BE7C2CC65FBF22BA41CAA0BCED183A8ADCA66B7372EDDCA858FFAC26FB5B63989303DB6634EAE33920260FAB");		
		
		
//		chiSiShen_IOS("112.124.27.168",8200,"000000770001020076855A17827227FE3F9095AA3E78B636AA696C1BC20F30950F16253E25BD671623C2783EF30D695F3FC216AA252946F28FD128168059B4DEE702E7E716E78F456766B40216DECAB4DE3A164559E71616F78024A23F23F20125F232AA160D5009B42BFDB38BCF900A630A0A1285357D94241F95");
//		chiSiShen_ANDROID("112.124.6.30",8100,"000000750001020076855A1782722742671623C2783EF30D6C290F36903E5980804E23FA90468F4283F5FE3F9095AA3E78B636AA69695F3FC216AA252946F28FD12880D7A523458016D7D71BD72325021BF7B48059597F80D7B4D7A6A63A16026680653F23F20125F232AA160D50EAAA2F1285FCDB53882CE2");
// ***************** huo ying *****************		
		// jy8760125 zw8760125 快用 26 区  6500 体力 
//		doHuoYing_IOS("112.124.2.232",8100,"000000760001020076855A17827227FE3F9095AA3E78B636AA696CC2F866A559E7A58002C5671623C2783EF30D697223FA90468F4283F5FE3FC216AA252946F28FD12816F7A545E7A567D7E759678F02CA7F6680F780B3B43AB43A80A5D716D7DE8FD75F3F23F20125F232AA160D196A8F3212858E151F357278");		
		// iOS12 同步推 linghan g q i   shmily1314179520 
//		doHuoYing_IOS("121.199.5.229",8300,"000000770001020076855A17827227FE3F9095AA3E78B636AA696C3EB63625AAB43625783642671623C2783EF30D697223FA90468F4283F5FE3FC216AA252946F28FD12880A525D78FDEA580A6161B8025D7E780CAA58FD7A5803AB4021B234523D58067123F23F20125F232AA160D196A3261128557A294045B08");		
		// PP助手，50区  PP58923870  密码hyh6520eee 
//		doHuoYing_IOS("112.124.44.11",8100,"0000007E0001020076855A179A951A9AE520A285C0DAFBFEAD0AC446401F09C8E6023597184D12D4C6F4CB95E150F3057A3E7DFCCB2B46880E8273495D747C2F40C5E2B0204A769F9EB9AE4A0BD09D067DB5CD633D4C8414B152D28B91A499F2D2146C6540E70A51353A3D11E1E0DA3E34A7F721697442A5A0EA90DC0A6E20EBA71E"); 		
		//ios 5  同步推  账号1248248   密码10086  这个账号 还有个刷历练 12 个 999
//		doHuoYing_IOS("121.199.44.72",8300,"000000720001020076855A17827227FE3F9095AA3E78B636AA6982598002F78596DC7A42425540915FD7778D724C8BD54C8B6436FEF69A6DE13905F38F325F39BB4BB163E7B7BC5F258F6311193D3EF029B7297E291B297E7E1111F03E1B299A35F09A6060607E5635117EF06040BBC8DFDA3DDCCDDC"); 		
		// 1区，帐号skyline8，密码qyd1221985
//		doHuoYing_IOS("218.244.141.167",8100,"000000750001020076855A17827227FE3F9095AA3E78B636AA696C29C21E36B63625B34E671623C2783EF30D695F3F23F20125F232AA160D196A320AFDB38BCF900A630A736ECBE7E478F7CF66902E3ED2C67A3129A1317A4036369396D040C631EEF2A1F40FCB3E31F47AC6314C40F4F01285BE0F1FB610C0");		
		// 账号:zhouxilu@sina.com 密码:2416233 安卓360版本 138区神斑 
//		doHuoYing_ANDROID("115.29.163.193",8100,"0000007E0001020076855A179A8E45EBF4A57E8537227B7EA7C6EF187FCB465BC50A26BF4EDB90AE2C5705985AA4B28361E6AFBB60744A1A249213B3F185A5226A3570C8E464B7C197AFE2FF329295D5C1A0B2AFF22410E9A883AA56898E472D48456C11F63DFB8CDABF0B7A501D2B39C7B3AB33906D1E86A58E558534B0209EAA49"); 
		// 安卓乐汇  weilan1206    密码123789  这个历练单子 7 区
//		doHuoYing_ANDROID("121.199.8.236",8600,"000000770001020076855A17827227FE3FC216AA252946F28FD1288002A5678F4523451B8F45A6DEF716E7E7D71B1B3A80DED566A5DED78F80E702FE3F9095AA3E78B636AA696CF2673E36A636E78002A582671623C2783EF30D696C3F23F20125F232AA160D19BC759CFDB38BCF900A630A0A128594F994733ED1"); 
		// 账号174699238@qq.com  092666  安卓88 360 
//		doHuoYing_ANDROID("115.29.168.145",8700,"0000007D0001020076855A1722B045EB76DAA285C6E37EA14F0DB0E7878C6E2E8786BD78361C4F9AB1448DCB828255F621E7B7873B17AB7D6040F8E49F48BBC6AC1D00DDD4E89732AFF3AA956954076D49250F300C26B15F22E2378B9FD9E312C8B4AFB7DC9D88785755DCAF3A88BABDB62D6CBD3BB5DB04B055C8641420882DA4");  
		// 256198686  19891013wangkai 安卓9游 100区 
//		doHuoYing_ANDROID("115.29.163.147",8200,"0000007D0001020076855A1722B045EBF4A57E8537D4F80266AE71FCC692FDC71BACDCC603047AD44BB4C0109653F1243139389FE7CE8ABE8160896D13847575F0A74B89411D485D0EEE372DB6A713B2135446ADB2B23EA7F90A391FBEA7E28B7FA2FA28C01FFF9C3FC8B1F5C38619313530D9EB7B3D3919C255C8723E20874D84");  
		// 账号306073729  密码1611797385 168  安卓9游  2000多点体力
//		doHuoYing_ANDROID("121.199.5.163",8300,"0000007B0001020076855A17827227FE3FC216AA252946F28FD12880251602B3A5D7A525238FD723F780B4B3A58066B445A6E759DE45D5B4AE02985D326F28BC2502BCBC31FE60567E7E1B7E169A567E7E7EF21B9A7E3905F38F325F39BB00D6A88C2E758CA118A87F04A377B0142D9C9C7C105EF48E8E4785039894D79308");   
		// 账号密码都是halin87 安卓17   乐汇版
//		doHuoYing_ANDROID("115.29.166.188",8200,"000000730001020076855A17827227FE3F23F20125F232AA160D19BCE20FDF16AA252946F28FD12880028F80DECA80D516A6D5A580B423DED7A580238F16251BD7232523E7B480169E23FA90468F4283F542671623C2783EF30D82AAB436B6363AA5653F9095AA3E78B636AA6982834185DAED5331EC8C");    
		// 账号fOrest_imba   密码 82571250  安卓官方  15
//		doHuoYing_ANDROID("115.29.166.188",8200,"000000780001020076855A17827227FE3F23F20125F232AA160D19BC7584FDB38BCF900A630A736EBF90085915EEE7082F1BDD3E1590B36EEE4DE75946736ECBE7E478F7CF66902E3ED27A0F3B90D2367AC602F2D03BEED00258DE45194C9045D229DE194031A53E40980845E0E015806D98606012854791DA9A4FE3");   
		//  123区  42668995@qq.com  密码kjl18659339558  360登陆的
//		doHuoYing_ANDROID("121.199.8.236",8300,"0000007C0001020076855A179AB0FDEB76DAA28593EFBB3233F4C47131027E7FAFF9A67E14B0A23C2DFB9D5ACB6DD16D541B3AC0A4EC636413BC8DBC21AAD3533D77DA4C3552D82F17E6DD4BFAD24A7F5CDCDD3F4EFA1706FAB1F7E7B62537260DF2EB8729B17E109B3DF285A8A1C5F0FFDD1CCCE9FC1CCC729F670B2073796C");     
		// 帐号18935303890 密342845 安卓  57区  金色闪光   360 
//		doHuoYing_ANDROID("115.29.166.188",8400,"0000007C0001020076855A1722B0E4EBF420FE854EEFBB1CAD444EFEA8DF52B0184BE42BB06F807EC47B4CD34492DF3354A4BA8234C794C7249CEBB2B2864C2124CBBB82FCFA9907AAB3312B852D854B760E37FE6720729D5529F7681BA7DE8D5094000AB74F8B92963E0F42B1FF85F6FBF9BB2675AF75510BDCCD60944921F1");       
		// lzy00kat0044  密码hw11097730  iOS同步推22区  8000左右体力加拉面的  四尺阳阵 死神面具3
//		doHuoYing_IOS("112.124.31.220",8200,"000000780001020076855A17827227FE3F9095AA3E78B636AA6935365BF880A50F232945A5A5458D671623C2783EF30D697223FA90468F4283F5FE3FC216AA252946F28FD128801B16B380E7D57F1680678FD5168FD7252345DE0216A6DE80DEA623D79F0C5D658A8C2E758CA118A87F875A588E4785AD4220A49F56");       
		// 账号42668995@qq.com 大区安卓123   360账号  密码kjl 18659339558 7000体力
//		doHuoYing_ANDROID("121.199.8.236",8300,"0000007C0001020076855A179AB025EBF420A28593E37E63AD8DC471EDDF6A7D4F0E5FE518A57A473ECC1053C20B3962701C0A2B2E177D9B400114CCCC13A6A55FDC9C84B04BF72EAC9BFFD942596DC7C45851A5DA18B913F262E385ED9CC795B6B1122F2388E31687B468CF74A6BDAA70C9EC45CA21AD888308B360201B87A1");    
		// 安卓360，31区阴阳遁，13519635381，密码jvbao35381，刷生命之力123    6000多 体力  单
//		doHuoYing_ANDROID("115.29.166.188",8300,"0000007F0001020076855A179A361A9AC3A5A285C0FB32CD372AB0BB8681C2E31650A68BBCD90A6331CE83A2E5D1FAD3B6A44EA9215CB2A3FB6EA7F0D3F23949B5B088B580A4D6E8ADAFB30DC8388B24AF4BCB987926A86D41E3479320205F1BADF4D03714C2773B5BC15042B457AAEC6FA3EB82779BBE511ED40B9BAEA39720769B0B");       
		// 360版本 安卓123区账号42668995@qq.com  密码kjl18659339558  刷关卡生命之力3先刷满2遍.再刷生命之力2也刷2遍，最后刷生命之力1  7000多体力
//		doHuoYing_ANDROID("121.199.8.236",8300,"0000007C0001020076855A179A0FFDEB76DAA28593EFBB0CCA7F317131027E7FAFF9A67E14B0A23C2D0ADE53C20B191A6DA0075FBC63641BA0FAA36A6B33D9E97EEB8F044F68F25660A69276797E0B89E36946F59330BD4AC8A5F23F2604825542DCCED8B57767BA0B805C301CB8F12291E41CCCE99D1CB9721D63E420BE82E2");       
		// 206718126   262162 死神面具3  九游安卓版本  qq: 441891858
//		doHuoYing_ANDROID("112.124.6.30",8600,"0000007D0001020076855A1722B025EBF420A28593EF802917B15D038D2727A5EB29745FE7C569E1471C1182C70E806D7274BC8B369284BE8ED2359228B5FEF55D5345394A4F4E1B312CA1B44BDDDBBC81A7D0BA86BE7C2CC65FBF22BA41CAA0BCED183A8ADCA66B7372EDDCA858FFAC26FB5B63989303DB6634EAE33920260FAB");		
		// 版本:91  账号: wu1212111 密码: HSYbobby26 183qu  qq: 847161703 
//		doHuoYing_ANDROID("121.199.8.236",8600,"0000007C0001020076855A1722B0F7EBF420A28593EFBBD6DBCB6A121B248248840B157A3DC583BB41715BEC82553F18B2E735D561D324D374B6265468CAACC9B953186521B1CC21BD2D353C9F46203B49985E972A9490DFEE14D21990D17C21E4AB90FA06587648AC44C847D2FAEB69495EFC70AD9D7B60E9AE630C94170013"); 		
		// 安卓31区 账号13519635381  密码jvbao35381  360版本  刷生命之力1，2，3
//		doHuoYing_ANDROID("115.29.166.188",8300,"0000007D0001020076855A1722B0FDEBF4A57E85C0227B7EA793DAF1E3F7B89D1BAC23C49C1D90AE2C8F057A8D6E8CFD549AD8026D3414B3BD13D3B18529C81341978B50183B2956F9B7E67E8F50E40832D3D858FF1B3F96B2A178848CF33790C2C73D34E365E41AD0045B21051AC55ADCDCA8A6E843ACF9E9CFDC8E7A94DC2543"); // qian shuijun 60       
		// xiu111111 yyl828283  46区 安卓官方 宇智波掌门 qq: 1115308186 
//		doHuoYing_ANDROID("115.29.166.188",8400,"000000720001020076855A17827227FE3F23F20125F232AA160D19BCBE030845E0E015806D981B6E963A4CA27E9863E7B7BC5F258F6311193DF205F2299A365F6C1B1B05F05F60B756F24040297E16F03E7E165F9A7E403605985D326F28BC2502BCBC31E16E05F38F325F39BB4B4BC84A1D537FC589");        

		
//		chiHuoYing_IOS("115.29.166.188",8400,"000000720001020076855A17827227FE3F23F20125F232AA160D19BCBE030845E0E015806D981B6E963A4CA27E9863E7B7BC5F258F6311193DF205F2299A365F6C1B1B05F05F60B756F24040297E16F03E7E165F9A7E403605985D326F28BC2502BCBC31E16E05F38F325F39BB4B4BC84A1D537FC589");

// ***************** Ma Fu *****************		
		// "fb_10204367214171254",160403,"12"  12332100  qq: 416152791 
//		doHuoYing_MaFu("103.10.158.115",8300,"0000007E0001020076855A1722B02AEBF420A285C6E37E619D7AAE54A5FBCA47D3CE257E29638D20BB8F60786A3A88F450868C06D0AB3A6040F891E1857F47174861E266CA4917A35008D861A649282007741F6FBFCA6F430D04B8C5F228D7C200D9D3B03F894589B19750114C5AC5B40782DC9E8878F9646BB87A94A70820C575A2");        

		// 243137286   1234567890  16区 
//		doHuoYing_MaFu("103.10.158.25",8400,"0000007D0001020076855A1722B02AEBF420A285C6EF803D17C37791E5F5FBF65F03997A30D1453D41715BEC22889D67CBE778070AAACEDDF3D19221E4AB90FA06DF0C85F3C458203800910945984472C70BAC1F0E0274C9B953AC65FF2E9C2BEF93C948FD0523D2C97A2DA219A6270BADF968F924F0A8545430DC17DA20018DED");        

		// qq6532310 6532310 17 qq : 245795286
//		doHuoYing_MaFu("103.10.158.87",8100,"0000007E0001020076855A17E2F4250CF4A5A285C0765863ADDAF028FEA8A3563E4035B410508F8C77581B75D96AD592E8422188A2AE14FA1C9247B3D06F01F22E29AC41854CA5DA3617239E1DFA1598BBDF0AE6044C8484E066D8D59178C26CF7A1CEF479EF7769047E5913B318E882E1A7CDB2CD942B79AA700188EC192036E8F5");        

		// 501541454  wobugaosuni 17 qq: 1341338010 
//		doHuoYing_MaFu("103.10.158.87",8100,"0000007F0001020076855A1722B02A0CE520A28537984EDAE0CC72FD0C3879105A042532450BECC09A8ECC99BF14A7D9DBE723837267680DFEE8A7FA9907AAB30FE6AE8561BF1F4A7F0E77EEE4A4282C459D3AEDE0433C0825CE1869C9D914874E66132120D638D8A6D2BCC4CC0FD43AFA064EB59EB50FAB6A6B4FA19442AA20266302");
		
//		chiHuoYing_MaFu("103.10.158.87",8100,"0000007E0001020076855A17E2F4250CF4A5A285C0765863ADDAF028FEA8A3563E4035B410508F8C77581B75D96AD592E8422188A2AE14FA1C9247B3D06F01F22E29AC41854CA5DA3617239E1DFA1598BBDF0AE6044C8484E066D8D59178C26CF7A1CEF479EF7769047E5913B318E882E1A7CDB2CD942B79AA700188EC192036E8F5");

		
		landaCount++;
		if(landaCount > timeDuration){
			System.exit(0);
		}
	}

}

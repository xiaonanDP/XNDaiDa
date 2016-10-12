package com.redcdn.test;

//The BasicWindowMonitor Class is also needed and is frequently used in other later examples:

import java.awt.event.*;
import java.awt.Window;

public class TestAdapter extends WindowAdapter {

	public void windowClosing(WindowEvent e) {
	  Window w = e.getWindow();
	  w.setVisible(false);
	  w.dispose();
	  System.exit(0);
	}
	
	
	
	public static void main(String args[]) {
		
	  String str = "";
	  str = "000000770001020076855A178272274223FA90468F4283F5FE3F9095AA3E78B636AA696CFA0F1636AA253625C2B4E23F23F20125F232AA160D5009B4610845E0E015806D9860D46ECBE7E478F7CF66902E3ED2F43B363693C6DD58D07A3696DE453EA55858A10F96A13B4CF44C3129F23EEEB112852D4E94A3BD45";
	  str = "000000770001020076855A1782722742671623C2783EF30D6CFA0F1636AA253625C2B4E23F23F20125F232AA160D5009B45E6ECBE7E478F7CF66902E3ED2F43B363693C6DD58D07A3696DE453EA55858A10F96A13B4CF44C3129F23EEEB1FDB38BCF900A630A736EBF90085915EEE7082F602B1285669B94648500";
	  str = "000000770001020076855A1782722742671623C2783EF30D6CFA0F1636AA253625C2B4E23F23F20125F232AA160D5009B4676ECBE7E478F7CF66902E3ED2F43B363693C6DD58D07A3696DE453EA55858A10F96A13B4CF44C3129F23EEEB1FDB38BCF900A630A736EBF90085915EEE7082F602B1285B3CB94A38B50";
	  str = "000000760001020076855A1722B02AEBC4A5A285C64CEF534E4E7B8D1F3237ABF37845E681340E446229A16A5C9482DF2D86FD28B1CCF5BD0752D848A54413B6C7CF7C4018B09E701539FA91BB061EF526BB919481C8866F0A201D496449BB9ECB2F4DBD2CFDDBF8E80B56524391758C0D7445D9B37494E98946";
	  str = "000000750001020076855A1722B0FDEBE5207E85C012BE7EB693C8C553A3760B6711FB31667EF7F361B9E01B1D35359B21CAE709B4A6EB1ECA085D47AE8A5939B8D325E9295A524FC3ABDF637B538FB512C37082A2EF8C942C6F4949057A7F8EA9B5E07831D2FBE213693BACD1351398C769DCB39994527301";
	  
	  str = "000000760001020076855A1722B02AEBC4A5A285C6CCEF534EC67BEC94B97BA7D0262D1B097DE46E25F6835A602FECD1C27ED8502018E06E19DFFB2ABBCB150FA7CF9440ACA1DC52BCAF6D671CAD90197432DA64E096F2DA12FE2F496298891B38F2CCD54EB41805BB5689E6955113E2CAB8A3AECAC89455E94D";
	  str = "000000770001020076855A1722B045EBC3A57E853722BE4BE3CEEF0068EF7701B60F25EFD13BD771C7F57672DEDDA878384FAB50FD2095C2D0B518C80DE355C057A4F92ADB26C5AD6B91AB95E67B818FAD8B04F10D2C46A1550F673D05CFDD970E22984E3CB103C2F8935C82342E6D4C9687A4BC9F791994BF7544";
	
	  
	  System.out.println(str);
	
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
	
}
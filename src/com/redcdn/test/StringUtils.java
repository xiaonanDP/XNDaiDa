package com.redcdn.test;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 16进制值与String/Byte之间的转换
 * @author JerryLi
 * @email lijian@dzs.mobi
 * @data 2011-10-16
 * */
public class StringUtils
{
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
					  //"31343333383636333833203265663637306361613134643931663035353566376638313162396461666333203000"
		String hexStr = "5D0001FF59004C4F47494E20302E302E31203539203133392034353231313231363434343330323631353437203132332031343333393531343436203861343530376331353161663864656334393034613435666662613630613861203000";
		String string = hexStr2Str(hexStr);
		System.out.println(string);

		System.out.println(getPasswordString("1433866383","2ef670caa14d91f0555f7f811b9dafc3"));
		
	}
	
	public static String getPasswordString(String time,String token){
		
		String stringA = ""+time+" "+token+" 0   ";
		String hexStrA = str2HexStr(stringA);
		hexStrA = hexStrA.substring(0, hexStrA.length()-4);
		return hexStrA;
	}
	
	public static String printHexString( byte[] b) {
		String a = "";
		  for (int i = 0; i < b.length; i++) { 
		    String hex = Integer.toHexString(b[i] & 0xFF); 
		    if (hex.length() == 1) { 
		      hex = '0' + hex; 
		    }
		   
		    a = a+hex;
		  } 
		  
		       return a;
		}
	
	private final static char[] mChars = "0123456789ABCDEF".toCharArray();
	private final static String mHexStr = "0123456789ABCDEF";  
	/** 
	 * 检查16进制字符串是否有效
	 * @param sHex String 16进制字符串
	 * @return boolean
	 */  
	public static boolean checkHexStr(String sHex){  
    	String sTmp = sHex.toString().trim().replace(" ", "").toUpperCase(Locale.US);
    	int iLen = sTmp.length();
    	
    	if (iLen > 1 && iLen%2 == 0){
    		for(int i=0; i<iLen; i++)
    			if (!mHexStr.contains(sTmp.substring(i, i+1)))
    				return false;
    		return true;
    	}
    	else
    		return false;
    }
	
	/** 
	 * 字符串转换成十六进制字符串
	 * @param str String 待转换的ASCII字符串
	 * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
	 */  
    public static String str2HexStr(String str){  
        StringBuilder sb = new StringBuilder();
        byte[] bs = str.getBytes();  
        
        for (int i = 0; i < bs.length; i++){  
            sb.append(mChars[(bs[i] & 0xFF) >> 4]);  
            sb.append(mChars[bs[i] & 0x0F]);
//            sb.append(' ');
        }  
        return sb.toString().trim();  
    }
    
    /** 
     * 十六进制字符串转换成 ASCII字符串
	 * @param str String Byte字符串
	 * @return String 对应的字符串
     */  
    public static String hexStr2Str(String hexStr){  
    	hexStr = hexStr.toString().trim().replace(" ", "").toUpperCase(Locale.US);
        char[] hexs = hexStr.toCharArray();  
        byte[] bytes = new byte[hexStr.length() / 2];  
        int iTmp = 0x00;;  

        for (int i = 0; i < bytes.length; i++){  
        	iTmp = mHexStr.indexOf(hexs[2 * i]) << 4;  
        	iTmp |= mHexStr.indexOf(hexs[2 * i + 1]);  
            bytes[i] = (byte) (iTmp & 0xFF);  
        }  
        return new String(bytes);  
    }
    
    /**
     * bytes转换成十六进制字符串
     * @param b byte[] byte数组
     * @param iLen int 取前N位处理 N=iLen
     * @return String 每个Byte值之间空格分隔
     */
	public static String byte2HexStr(byte[] b, int iLen){
        StringBuilder sb = new StringBuilder();
        for (int n=0; n<iLen; n++){
        	sb.append(mChars[(b[n] & 0xFF) >> 4]);
        	sb.append(mChars[b[n] & 0x0F]);
            sb.append(' ');
        }
        return sb.toString().trim().toUpperCase(Locale.US);
    }
    
    /**
     * bytes字符串转换为Byte值
     * @param src String Byte字符串，每个Byte之间没有分隔符(字符范围:0-9 A-F)
     * @return byte[]
     */
	public static byte[] hexStr2Bytes(String src){
    	/*对输入值进行规范化整理*/
    	src = src.trim().replace(" ", "").toUpperCase(Locale.US);
    	//处理值初始化
    	int m=0,n=0;
        int iLen=src.length()/2; //计算长度
        byte[] ret = new byte[iLen]; //分配存储空间
        
        for (int i = 0; i < iLen; i++){
            m=i*2+1;
            n=m+1;
            ret[i] = (byte)(Integer.decode("0x"+ src.substring(i*2, m) + src.substring(m,n)) & 0xFF);
        }
        return ret;
    }

    /**
     * String的字符串转换成unicode的String
     * @param strText String 全角字符串
     * @return String 每个unicode之间无分隔符
     * @throws Exception
     */
    public static String strToUnicode(String strText)
    	throws Exception
    {
        char c;
        StringBuilder str = new StringBuilder();
        int intAsc;
        String strHex;
        for (int i = 0; i < strText.length(); i++){
            c = strText.charAt(i);
            intAsc = (int) c;
            strHex = Integer.toHexString(intAsc);
            if (intAsc > 128)
            	str.append("\\u");
            else // 低位在前面补00
            	str.append("\\u00");
            str.append(strHex);
        }
        return str.toString();
    }
    
    /**
     * unicode的String转换成String的字符串
     * @param hex String 16进制值字符串 （一个unicode为2byte）
     * @return String 全角字符串
     * @see CHexConver.unicodeToString("\\u0068\\u0065\\u006c\\u006c\\u006f")
     */
    public static String unicodeToString(String hex){
        int t = hex.length() / 6;
        int iTmp = 0;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < t; i++){
            String s = hex.substring(i * 6, (i + 1) * 6);
            // 将16进制的string转为int
            iTmp = (Integer.valueOf(s.substring(2, 4), 16) << 8) | Integer.valueOf(s.substring(4), 16);
            // 将int转换为字符
            str.append(new String(Character.toChars(iTmp)));
        }
        return str.toString();
    }
    
    /*  
	// 42.96.249.58
	String loginURL = "http://passport.yayawan.com/apiv2/login?";
	String a = sendRequest(loginURL,"data=a59cddac81f39064afa75f2baa502c0540ad0f228b30632162816e0188ebd84c42ef6f6f23fb5b4a4f204eb64f7dcfc6d54c073aa8cb440c9be9d65e08695ea5471960487a221c8c707dfe9ecd7dadf8a3e3d31c99c76a628ec115a2b2253a66c1a531c67be8ee309ee7255a135180df02cad2dd500c5d60b95552d5471e097a");
	
	System.out.println(a);
	String tokenStr = getToken(a);
	System.out.println("token: "+tokenStr);
	
	// 121.201.1.81
	String verifyURL = "http://api.uzone8.com/sgwww/yayawan/android/loginVerify";
	String b = sendRequest(verifyURL,"uid=4521121644430261547&token="+tokenStr+"&version=48632");
	String create_time = getValue(b,"create_time");
	System.out.println("create_time:"+create_time);
	String key = getValue(b,"key");
	System.out.println("key:"+key);
	
	// 120.24.84.217
	
	String passwordStr = StringUtils.getPasswordString(create_time, key);
	loginStr = "5D0001FF59004C4F47494E20302E302E312035392031333920343532313132313634343433303236313534372031323320"+passwordStr;
	*/
    
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
          data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
              .digit(s.charAt(i + 1), 16));
        }
        return data;
      }
    
    public static int checkTime(int beginHour,int beginMinute,int duration){
		
    	int beginMonth = 6;
    	  
        GregorianCalendar currentTime = new GregorianCalendar();
        currentTime.setTime(new Date());
        
        int month = currentTime.get(Calendar.MONTH)+1;
        if( month != beginMonth){
          return 0;
        }
        
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
}
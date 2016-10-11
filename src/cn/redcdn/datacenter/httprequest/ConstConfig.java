package cn.redcdn.datacenter.httprequest;

public class ConstConfig {

  public static String webDomain = "";
  // public static String webDomain = "192.168.1.190:8080/meeting";
  public static String httpsScheme = "http://";// https
  public static String httpScheme = "http://";

  // 各个接口对应的网址
  public static String getAuthUrl() {
    return httpsScheme + webDomain + "/Login.aspx";
  }

  public static String getRefillTokenUrl() {
    return httpScheme + webDomain + "/ExtendToken.aspx";
  }

  public static String getQueryAccountInfoUrl() {
    return httpScheme + webDomain + "/GetAccountInfo.aspx";
  }

  public static String getModofyAccountInfoUrl() {
    return httpScheme + webDomain + "/ModifyAccountInfo.aspx";
  }

  public static String getQueryMeetingListByDayUrl() {
    return httpScheme + webDomain + "/GetDayMeetings.aspx";
  }

  public static String getQueryMeetingListByMonthUrl() {
    return httpScheme + webDomain + "/GetMonthSchedules.aspx";
  }

  public static String getQueryMeetingByRunningNowUrl() {
    return httpScheme + webDomain + "/GetNowMeetings.aspx";
  }

  public static String getQueryMeetingDetailInfoUrl() {
    return httpScheme + webDomain + "/GetMeetingDetail.aspx";
  }

  public static String getQueryRunningAndCreateByMeUrl() {
    return httpsScheme + webDomain + "/GetMyMeetings.aspx";
  }

  /*
   * public static String getQueryMessageInfoUrl(){ return httpScheme +
   * webDomain + "/meetingMessageInfo.aspx"; }
   * 
   * public static String getUpdateMessageStateUrl(){ return httpScheme +
   * webDomain + "/updateMessage.aspx"; }
   */

  public static String getCreateMeetingUrl() {
    return httpsScheme + webDomain + "/CreateMeeting.aspx";
  }

  public static String getEditMeetingUrl() {
    return httpsScheme + webDomain + "/EditMeeting.aspx";
  }

  public static String getDeleteMeetingUrl() {
    return httpScheme + webDomain + "/DeleteMeeting.aspx";
  }

  public static String getAddPartnerUrl() {
    return httpScheme + webDomain + "/ModifyMeetingInviters.aspx";
  }

  public static String getSetMeetingPasswordUrl() {
    return httpsScheme + webDomain + "/SetMeetingPassword.aspx";
  }

  public static String getMeetingPassUrl() {
    return httpScheme + webDomain + "/GetMeetingPasspard.aspx";
  }

  public static String getMeetingPasswordUrl() {
    return httpsScheme + webDomain + "/GetMeetingPassword.aspx";
  }

  public static String getQueryContactsUrl() {
    return httpsScheme + webDomain + "/GetContacts.aspx";
  }

  public final static String PHONE_ID = "phoneId";
  public final static String CHANNELSOFT_TOKEN = "terminalToken";
  public final static String ROLE = "role";

  public final static String TOKEN = "token";
  public final static String EFFECTIVE_TIME = "effectiveTime";
  public final static String SECRET_WORD = "key";

  public final static String PHONE_NAME = "name";
  public final static String VALID_DATE = "validitydata";
  public final static String PORTRAIT = "faceUrl";
  public final static String PORTRAIT_NAME = "fileName";
  public final static String PORTRAIT_DATA = "faceData";
  public final static String ORGANIZATION = "company";

  public final static String YEAR = "year";
  public final static String MONTH = "month";
  public final static String DAY = "day";

  public final static String MEETING_ID = "meetingId";
  public final static String MEETING_START_TIME = "beginDateTime";
  public final static String MEETING_END_TIME = "endDateTime";
  public final static String MEETING_NAME = "meetingName";
  public final static String MEETING_ORGANIZER = "adminUser";
  public final static String MEETING_ORGANIZER_CONTACT = "userContact";
  public final static String MEETING_IS_SECRIT = "isOpen";
  public final static String MEETING_PARTNER = "invotedUsers";
  public final static String MEETING_PASSWORD = "password";
  public final static String MEETING_TYPE = "meetingType";
  public final static String MEETING_STAT = "meetingStatus";
  public final static String MEETING_IS_INVITE = "isInvited";
  public final static String MEETING_IS_CONTROL = "isControl";
  public final static String MEETING_PASSPORT = "beacon";
  public final static String MEETING_REAL_START_TIME = "realBeginDateTime";
  public final static String MEETING_REAL_END_TIME = "realEndDateTime";

  /*
   * public final static String MESSAGE_ID = "messageId"; public final static
   * String MESSAGE_TYPE = "messageType"; public final static String
   * MESSAGE_CONTENT = "messageContent"; public final static String MESSAGE_TIME
   * = "messageTime";
   * 
   * public final static String MESSAGE_LIST = "messageList";
   */

  public final static String DAYS = "days";
  public final static String MEETING_LISTS = "data";

  // BussnessDataAdaptor error code
  public final static int JSON_INVALID = 1;
  public final static int HTTP_ARGUMENT_ERROR = 2;
  public final static int HTTP_NETWORK_ERROR = 3;
  public final static int WEB_SERVER_ERROR = 4;

  // 角色枚举值
  public final static int ROLE_MEETING_HALL = 1; // 会场
  public final static int ROLE_MEETING_GUESTS = 2; // 嘉宾
  public final static int ROLE_MEETING_AUDIENCE = 3; // 观众

  // 会议类型枚举值
  public final static int MEETING_TYPE_RESERVATION = 2; // 预约会议
  public final static int MEETING_TYPE_NOW = 1; // 即时会议

  // 会议状态枚举值
  public final static int MEETING_STATUS_NO_START = 1; // 会议未开始
  public final static int MEETING_STATUS_STARTED = 2; // 会议已开始
  public final static int MEETING_STATUS_STOPPED = 3; // 会议已停止

  public final static String TEST_PHONE_ID = "70005632";
  public final static String TEST_TOKEN = "c707a59d-639c-4d6d-82e8-55c74a5b43c9";
}

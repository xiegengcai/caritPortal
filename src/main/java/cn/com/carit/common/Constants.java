package cn.com.carit.common;



public class Constants {
	public static final String CHARACTER_ENCODING_UTF8="utf-8";
	public static final int PAGE_SIZE=10;
	public static final int STATUS_VALID=1;
	public static final int STATUS_INVALID=0;
	public static final int STATUS_LOCKED=2;
	public static final String DATE_TIME_FORMATTER="yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMATTER="yyyy-MM-dd";
	public static final String DATE_SHORT_FORMATTER="yyyy-M-d";
	public static final String DATE_MONTH_DATE="M-d";
	public static final String ADMIN_USER="adminUser";
	public static final String PORTAL_USER="portalUser";
	public static final String PASSWORD_ERROR_COUNT="passwordErrorCount";
	/**密码最多错误5次限制登录*/
	public static final int MAX_PWD_ERROR_COUNT=5;
	public static final String USER_ALL_MOUDLE="userAllMoudle";
	public static final String ANSWER_CODE="answerCode";
	/**应用截图*/
	public static final int IMAGE_TYPE_DEFAULT=0;
	/**应用图标*/
	public static final int IMAGE_TYPE_ICON=1;
	/**用户头像*/
	public static final int IMAGE_TYPE_PHOTO=2;
	
	public static final String BASE_PATH_ICON="icons/";
	public static final String BASE_PATH_IMAGE="images/";
	public static final String BASE_PATH_APK="apks/";
	public static final String BASE_PATH_PHOTOS="photos/";
	
	public static final String LOCAL_EN="en";
	public static final String LOCAL_CN="cn";
}

package cn.com.carit.portal.bean;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import cn.com.carit.common.jackjson.CustomDateTimeSerializer;

/**
 * 用户表
 */
public class AdminUser extends BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 电子邮件地址
	 */
	private String email;
	
	/**
	 * 密码 MD5Util.md5Hex(MD5Util.md5Hex(password){email}disturbStr)
	 * md5(md5(pwd)+email+干扰串)
	 */
	@JsonIgnore
	private String password;
	
	/**
	 * 昵称
	 */
	private String nickName;


	/**
	 * 用户真实姓名
	 */
	private String realName;

	/**
	 * 性别 0：女 1：男 2：保密
	 */
	private Byte gender;


	/**
	 * 手机
	 */
	private Long mobile;

	/**
	 * 办公电话
	 */
	private String officePhone;

	/**
	 * 上次登录时间
	 */
	private Date lastLoginTime;

	/**
	 * 上次登录IP地址
	 */
	private String lastLoginIp;

	/**
	 * 备注
	 */
	private String remark;
	
	@JsonIgnore
	private Date lastLoginTimeStart;
	
	@JsonIgnore
	private Date lastLoginTimeEnd;
	
	/**封装表单角色信息*/
	@JsonIgnore
	private String roles;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword()  {
		return password;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Byte getGender() {
		return gender;
	}
	public void setGender(Byte gender) {
		this.gender = gender;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getLastLoginTimeStart() {
		return lastLoginTimeStart;
	}
	public void setLastLoginTimeStart(Date lastLoginTimeStart) {
		this.lastLoginTimeStart = lastLoginTimeStart;
	}
	public Date getLastLoginTimeEnd() {
		return lastLoginTimeEnd;
	}
	public void setLastLoginTimeEnd(Date lastLoginTimeEnd) {
		this.lastLoginTimeEnd = lastLoginTimeEnd;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
}
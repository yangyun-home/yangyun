package com.qyhl.tpsb.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**     
 * method：SysUserEntity  
 * author：Qiu
 * time：2019-4-2 
 * @version 1.0.0
 */
@TableName("tb_user")
public class SysUserEntity extends BaseInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@TableId
    private Long userid;
    private String username;
    private String password;
    private String phone;
    private String phonecode;
    private String userPhoto;
    private Integer deleted;
    private Integer type;
    @TableField(exist=false)
    private String verifyCode;
    
    private String signName;
    
    private String remark;
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhonecode() {
		return phonecode;
	}
	public void setPhonecode(String phonecode) {
		this.phonecode = phonecode;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getSignName() {
		return signName;
	}
	public void setSignName(String signName) {
		this.signName = signName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    

}

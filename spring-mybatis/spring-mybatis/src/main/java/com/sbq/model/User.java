package com.sbq.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.ibatis.type.Alias;

//表名默认使用类名,驼峰转下划线(只对大写字母进行处理),如UserInfo默认对应的表名为user_info。
//表名可以使用@Table(name = "tableName")进行指定,对不符合第一条默认规则的可以通过这种方式指定表名.
//字段默认和@Column一样,都会作为表字段,表字段默认为Java对象的Field名字驼峰转下划线形式.
//可以使用@Column(name = "fieldName")指定不符合第3条规则的字段名
//使用@Transient注解可以忽略字段,添加该注解的字段不会作为表字段使用.
//建议一定是有一个@Id注解作为主键的字段,可以有多个@Id注解的字段作为联合主键.
//默认情况下,实体类中如果不存在包含@Id注解的字段,所有的字段都会作为主键字段进行使用(这种效率极低).
//实体类可以继承使用,可以参考测试代码中的tk.mybatis.mapper.model.UserLogin2类.
//由于基本类型,如int作为实体类字段时会有默认值0,而且无法消除,所以实体类中建议不要使用基本类型.
//@NameStyle注解，用来配置对象名/字段和表名/字段之间的转换方式，该注解优先于全局配置style，可选值：
//
//normal:使用实体类名/属性名作为表名/字段名
//camelhump:这是默认值，驼峰转换为下划线形式
//uppercase:转换为大写
//lowercase:转换为小写

@Alias("User")
@Table(name = "t_account")
@Entity
public class User {

	@Id
	@Column(name = "int_id")
	@GeneratedValue(generator = "JDBC")
	private int int_id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;
	
	@Column(name = "dept_id")
	private int dept_id;
	
	/**
	 * 冗余字段需加注解@Transient
	 */
	@Transient
	@Column(name = "dept_name")
	private String dept_name;
	
	public User() {
	}

	public int getInt_id() {
		return int_id;
	}

	public void setInt_id(int int_id) {
		this.int_id = int_id;
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
	
	
	
	public int getDept_id()
	{
		return dept_id;
	}

	public void setDept_id(int dept_id)
	{
		this.dept_id = dept_id;
	}

	public String getDept_name()
	{
		return dept_name;
	}

	public void setDept_name(String dept_name)
	{
		this.dept_name = dept_name;
	}

	public String toString() {
		return "User[id=" + int_id + " , username=" + username + " , password=" + password + "]";
	}

}

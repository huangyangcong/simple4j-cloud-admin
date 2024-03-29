/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package com.simple4j.system.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * UserDTO
 *
 * @author hyc
 */
@Data
@ColumnWidth(25)
@HeadRowHeight(20)
@ContentRowHeight(18)
public class UserExcelImport implements Serializable {

	private static final long serialVersionUID = 1L;

	@ExcelIgnore
	@ExcelProperty("用户编号")
	private String id;

	@ColumnWidth(15)
	@ExcelProperty("租户编号")
	private String tenantId;

	@ColumnWidth(15)
	@ExcelProperty("账户")
	private String account;

	@ColumnWidth(10)
	@ExcelProperty("昵称")
	private String name;

	@ColumnWidth(10)
	@ExcelProperty("姓名")
	private String realName;

	@ExcelProperty("邮箱")
	private String email;

	@ColumnWidth(15)
	@ExcelProperty("手机")
	private String phone;

	@ExcelProperty("角色名称")
	private List<String> roleName;

	@ExcelProperty("部门名称")
	private List<String> deptName;

	@ExcelProperty("岗位名称")
	private List<String> postName;

	@ColumnWidth(20)
	@ExcelProperty("生日")
	private Date birthday;
}

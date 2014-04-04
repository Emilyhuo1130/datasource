package com.ucs.tdc.interFace;

import java.util.List;

import com.ucs.tdc.pojo.AdminInfo;
import com.ucs.tdc.pojo.ProjectInfo;

public interface TestInterFace {

	List<AdminInfo> getAlladminInfo();

	boolean adduser(AdminInfo info);

	boolean deleteinfoByID(AdminInfo info);

	List<AdminInfo> getAlladminInfoById(int id);

	boolean updaeinfo(AdminInfo info);

	List<ProjectInfo> do_find_Test_Projects(String projectName, String starTime, String endTime);

	
}

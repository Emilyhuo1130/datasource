package com.ucs.tdc.interFace;

import java.util.List;

import com.ucs.tdc.pojo.HistoryTaskList;
import com.ucs.tdc.pojo.TaskParticulars;
import com.ucs.tdc.pojo.TaskParticularsInfoResponse;
import com.ucs.tdc.pojo.UserInfo;
import com.ucs.tdc.pojo.WeekTaskInfoResponse;
import com.ucs.tdc.pojo.Weektask;

public interface Task {

	Boolean do_addTasks(List<TaskParticulars> list);

	Boolean do_verifyPW(String userName, String password);

	Boolean do_modifyPW(String userName, String password);

	int do_findTaskspage(int pageSize, String projectName, int fromWeek, int toWeek, String userName);

	List<TaskParticulars> do_findTasks(String projectName, int fromWeek, int toWeek, String yaer,int pageSize,
			int page, String userName);

	List<String> do_select_AllUser();

	List<Weektask> showThisWeekTask(int weekNo, String year, String userAccount, int page, int pageSize);

	Boolean do_updateNextWeekTask(Weektask task);

	Boolean do_addNextWeekTask(Weektask task);

	HistoryTaskList do_findHitoryTask(String startTime, String endTime,
			String userName, int page, int pageSize);

	UserInfo do_getMyUserInfo(String userAccount);

	Boolean do_modifyMyInfo(UserInfo oldinfo);

	TaskParticulars do_findHitoryTaskById(int id);
	TaskParticulars do_findHitoryTaskByIdNoModify(int id);
	Boolean do_updateHitoryTask(TaskParticulars infonew);

	Boolean do_deleteHistoryMission(TaskParticulars infoold);

	Weektask do_findNextWeekTaskById(int id);

	Integer do_geWeekTaskPageCount(int weekNo, String year, String userAccount,
			int pageSize);

	Boolean do_deleteWeekTask(Weektask info);

	WeekTaskInfoResponse do_select_AllUserHistoryTasks(int startweekNo, int endweekNo, String userName,
			int page, int pageSize);

	TaskParticularsInfoResponse do_select_AllUserHistoryWeekTasks(String userName,
			int page, int pageSize, String starTime, String endTime);
	
}

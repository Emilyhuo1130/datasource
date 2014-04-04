
package com.ucs.rcm.pdrdataUtils;

import org.apache.log4j.Logger;

import com.ucs.rcm.business.WarningManager;
import com.ucs.rcm.reqobj.ReqgetstationFaultTree;

public class ChoseFaultType {
	static Logger log = Logger.getLogger(WarningManager.class);
	public static String getFaultType(ReqgetstationFaultTree req) {
		// TODO Auto-generated method stub
		String type=null;
		log.warn("*****************"+req.getEquipmentId()+"*******************");
		if(req.getEquipmentId().substring(6,11).equals("08.01")){
			/**道岔    道岔存在特殊性，数据直接从卡斯柯那边数据库获取
			 * 后期修改增加一个id*/
			 type=GetpointInfo.get_08_01_Info(req.getEquipmentId());
			 return type;
		}else if(req.getEquipmentId().substring(6,11).equals("08.02")){
			/**信号机*/
			type=GetpointInfo.get_08_02_Info(req.getEquipmentId());
			 return type;
		}else if(req.getEquipmentId().substring(6,11).equals("03.36")){
			/**隧道风机通风系统*/
			 type=GetpointInfo.get_03_36_Info(req.getEquipmentId());
			 return type;
		}else if(req.getEquipmentId().substring(6,11).equals("03.04")){
			/**给排水系统*/
			type=GetpointInfo.get_03_04_Info(req.getEquipmentId());
			return type;
		}else if(req.getEquipmentId().substring(6,11).equals("02.24")){
			/**高/低  压开 馈线 关柜*/
			type=GetpointInfo.get_02_24_Info(req.getEquipmentId());
			return type;
		}else if(req.getEquipmentId().substring(6,12).equals("14.101")){
			/**触网 ***/
			type=GetpointInfo.get_14_101_Info(req.getEquipmentId());
			return type;
		}else if(req.getEquipmentId().substring(6,12).equals("13.100")){
			/**轨道**/
			type=GetpointInfo.get_13_100_Info(req.getEquipmentId());
			return type;
		}else if(req.getEquipmentId().substring(6,12).equals("16.300")){
			/**桥梁**/
			type=GetpointInfo.get_16_300_Info(req.getEquipmentId());
			return type;
		}else if(req.getEquipmentId().substring(6,12).equals("15.200")){
			/**隧道***/
			type=GetpointInfo.get_15_20_Info(req.getEquipmentId());
			return type;
			
		}else if(req.getEquipmentId().substring(6,11).equals("08.05")){
			
			return null;
		}else if(req.getEquipmentId().substring(6,11).equals("08.05")){
			
			return null;
		}else if(req.getEquipmentId().substring(6,11).equals("08.05")){
			
			return null;
		}else{
			return "未匹配到组件，组件不存在";
		}
		
		
		
		
	}

}

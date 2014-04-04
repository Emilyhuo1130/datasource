package com.ucs.rcm.reqobj;

public class ReqgetFaultTree {
		
	
		private String systemName;//所属系统
		private String equipmentName;//资产名
		private String subCom;//部件名
		private String faulttype;//故障类型
		

		public String getFaulttype() {
			return faulttype;
		}

		public void setFaulttype(String faulttype) {
			this.faulttype = faulttype;
		}

		public String getSystemName() {
			return systemName;
		}

		public void setSystemName(String systemName) {
			this.systemName = systemName;
		}

		public String getEquipmentName() {
			return equipmentName;
		}

		public void setEquipmentName(String equipmentName) {
			this.equipmentName = equipmentName;
		}

		public String getSubCom() {
			return subCom;
		}

		public void setSubCom(String subCom) {
			this.subCom = subCom;
		}
		
}

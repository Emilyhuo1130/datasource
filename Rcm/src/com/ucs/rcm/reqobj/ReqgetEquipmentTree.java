package com.ucs.rcm.reqobj;

public class ReqgetEquipmentTree {
		private String reqString=null;
		private String systemName=null;
		private String equipmentName=null;
		private String subCom=null;
		
		public String getEquipmentName() {
			return equipmentName;
		}

		public void setEquipmentName(String equipmentName) {
			this.equipmentName = equipmentName;
		}

		public String getSystemName() {
			return systemName;
		}

		public void setSystemName(String systemName) {
			this.systemName = systemName;
		}

		

		public String getSubCom() {
			return subCom;
		}

		public void setSubCom(String subCom) {
			this.subCom = subCom;
		}

		public String getReqString() {
			return reqString;
		}

		public void setReqString(String reqString) {
			this.reqString = reqString;
		}
		
}

package com.ucs.rcm.reqobj;

/**
 * @author kathy
 *
 */
public class RequpdateFaultInfo {
	private int id =0;
	private String fultDescription=null;//故障描述
	private String influence=null;//运营影响
	private String faultCause=null;//故障原因
	private String maintenancePolicy=null;//维修策略
	
	public String getFultDescription() {
		return fultDescription;
	}

	public void setFultDescription(String fultDescription) {
		this.fultDescription = fultDescription;
	}

	public String getInfluence() {
		return influence;
	}

	public void setInfluence(String influence) {
		this.influence = influence;
	}

	public String getFaultCause() {
		return faultCause;
	}

	public void setFaultCause(String faultCause) {
		this.faultCause = faultCause;
	}

	public String getMaintenancePolicy() {
		return maintenancePolicy;
	}

	public void setMaintenancePolicy(String maintenancePolicy) {
		this.maintenancePolicy = maintenancePolicy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}

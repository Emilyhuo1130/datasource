package com.ucs.rcm.pojo;



/**
 * Warning entity. @author MyEclipse Persistence Tools
 */
public class Warning implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Integer id;
	private String warningId;
	private String equipmentId;
	private String equipmentname;
	private String systemName;
	private String equipmentDescription;
	private String lineNo;
	private String stationName;
	private String warningType;
	private String warningTypeLevel;
	private String warninginfo;
	private String warningDate;
	private String statments;
	private String fromuser;

	// Constructors

	/** default constructor */
	public Warning() {
	}

	public Warning(Integer id,String warningType, String warningTypeLevel) {
		super();
		this.id = id;
		this.warningType = warningType;
		this.warningTypeLevel = warningTypeLevel;
	}

	public Warning(String equipmentDescription, String warningType) {
		super();
		this.equipmentDescription = equipmentDescription;
		this.warningType = warningType;
	}

	/** full constructor */
	public Warning(String warningId, String equipmentId, String equipmentname,
			String systemName, String equipmentDescription, String lineNo,
			String stationName, String warningType, String warningTypeLevel,
			String warninginfo, String warningDate, String statments,
			String fromuser) {
		this.warningId = warningId;
		this.equipmentId = equipmentId;
		this.equipmentname = equipmentname;
		this.systemName = systemName;
		this.equipmentDescription = equipmentDescription;
		this.lineNo = lineNo;
		this.stationName = stationName;
		this.warningType = warningType;
		this.warningTypeLevel = warningTypeLevel;
		this.warninginfo = warninginfo;
		this.warningDate = warningDate;
		this.statments = statments;
		this.fromuser = fromuser;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWarningId() {
		return this.warningId;
	}

	public void setWarningId(String warningId) {
		this.warningId = warningId;
	}

	public String getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentname() {
		return this.equipmentname;
	}

	public void setEquipmentname(String equipmentname) {
		this.equipmentname = equipmentname;
	}

	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getEquipmentDescription() {
		return this.equipmentDescription;
	}

	public void setEquipmentDescription(String equipmentDescription) {
		this.equipmentDescription = equipmentDescription;
	}

	public String getLineNo() {
		return this.lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public String getStationName() {
		return this.stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getWarningType() {
		return this.warningType;
	}

	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}

	public String getWarningTypeLevel() {
		return this.warningTypeLevel;
	}

	public void setWarningTypeLevel(String warningTypeLevel) {
		this.warningTypeLevel = warningTypeLevel;
	}

	public String getWarninginfo() {
		return this.warninginfo;
	}

	public void setWarninginfo(String warninginfo) {
		this.warninginfo = warninginfo;
	}

	public String getWarningDate() {
		return this.warningDate;
	}

	public void setWarningDate(String string) {
		this.warningDate = string;
	}

	public String getStatments() {
		return this.statments;
	}

	public void setStatments(String statments) {
		this.statments = statments;
	}

	public String getFromuser() {
		return this.fromuser;
	}

	public void setFromuser(String fromuser) {
		this.fromuser = fromuser;
	}

}
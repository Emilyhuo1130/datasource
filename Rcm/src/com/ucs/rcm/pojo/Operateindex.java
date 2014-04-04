package com.ucs.rcm.pojo;

import java.lang.String;

/**
 * Operateindex entity. @author MyEclipse Persistence Tools
 */

public class Operateindex implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String lineNo;
	private String healthindex;
	private String leveloneCount;
	private String leveltwoCount;
	private String levelthreeCount;
	private String levelforeCount;
	private String updatetime;

	// Constructors

	/** default constructor */
	public Operateindex() {
	}

	/** full constructor */
	public Operateindex(String lineNo, String healthindex,
			String leveloneCount, String leveltwoCount, String levelthreeCount,
			String levelforeCount, String updatetime) {
		this.lineNo = lineNo;
		this.healthindex = healthindex;
		this.leveloneCount = leveloneCount;
		this.leveltwoCount = leveltwoCount;
		this.levelthreeCount = levelthreeCount;
		this.levelforeCount = levelforeCount;
		this.updatetime = updatetime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLineNo() {
		return this.lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public String getHealthindex() {
		return this.healthindex;
	}

	public void setHealthindex(String healthindex) {
		this.healthindex = healthindex;
	}

	public String getLeveloneCount() {
		return this.leveloneCount;
	}

	public void setLeveloneCount(String leveloneCount) {
		this.leveloneCount = leveloneCount;
	}

	public String getLeveltwoCount() {
		return this.leveltwoCount;
	}

	public void setLeveltwoCount(String leveltwoCount) {
		this.leveltwoCount = leveltwoCount;
	}

	public String getLevelthreeCount() {
		return this.levelthreeCount;
	}

	public void setLevelthreeCount(String levelthreeCount) {
		this.levelthreeCount = levelthreeCount;
	}

	public String getLevelforeCount() {
		return this.levelforeCount;
	}

	public void setLevelforeCount(String levelforeCount) {
		this.levelforeCount = levelforeCount;
	}

	public String getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

}
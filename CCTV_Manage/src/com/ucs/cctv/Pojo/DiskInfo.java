package com.ucs.cctv.Pojo;



/**
 * DiskInfoId entity. @author MyEclipse Persistence Tools
 */

public class DiskInfo  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private int id;
     private String diskName;//磁盘名称
     private Integer diskSize;//磁盘容量
     private double diskSurplusCapacity;//剩余容量


    public Integer getId() {
        return this.id;
    }
    

	public void setId(int id) {
		this.id = id;
	}

	public void setId(Integer id) {
        this.id = id;
    }

    public String getDiskName() {
        return this.diskName;
    }
    
    public void setDiskName(String diskName) {
        this.diskName = diskName;
    }

    public Integer getDiskSize() {
        return this.diskSize;
    }
    
    public void setDiskSize(Integer diskSize) {
        this.diskSize = diskSize;
    }

    public double getDiskSurplusCapacity() {
        return this.diskSurplusCapacity;
    }
    
    public void setDiskSurplusCapacity(double diskSurplusCapacity) {
        this.diskSurplusCapacity = diskSurplusCapacity;
    }

}
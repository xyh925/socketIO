package com.xys.pojo;

public class Medical {
	
	private Integer ID;
	private Integer Hosp_Dr;
	private String Descripts;
	private String IP;
	private String IdentifyCode;
	private Integer Purpose_Dr;
	private Integer DataID;
	private String StarDate;
	private String StopDate;
	private String CreateDate;
	private Integer CreateUser_Dr;
	
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Integer getHosp_Dr() {
		return Hosp_Dr;
	}
	public void setHosp_Dr(Integer hosp_Dr) {
		Hosp_Dr = hosp_Dr;
	}
	public String getDescripts() {
		return Descripts;
	}
	public void setDescripts(String descripts) {
		Descripts = descripts;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getIdentifyCode() {
		return IdentifyCode;
	}
	public void setIdentifyCode(String identifyCode) {
		IdentifyCode = identifyCode;
	}
	public Integer getPurpose_Dr() {
		return Purpose_Dr;
	}
	public void setPurpose_Dr(Integer purpose_Dr) {
		Purpose_Dr = purpose_Dr;
	}
	public Integer getDataID() {
		return DataID;
	}
	public void setDataID(Integer dataID) {
		DataID = dataID;
	}
	public String getStarDate() {
		return StarDate;
	}
	public void setStarDate(String starDate) {
		StarDate = starDate;
	}
	public String getStopDate() {
		return StopDate;
	}
	public void setStopDate(String stopDate) {
		StopDate = stopDate;
	}
	public String getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}
	public Integer getCreateUser_Dr() {
		return CreateUser_Dr;
	}
	public void setCreateUser_Dr(Integer createUser_Dr) {
		CreateUser_Dr = createUser_Dr;
	}
	public Medical(Integer iD, Integer hosp_Dr, String descripts, String iP, String identifyCode, Integer purpose_Dr,
			Integer dataID, String starDate, String stopDate, String createDate, Integer createUser_Dr) {
		super();
		ID = iD;
		Hosp_Dr = hosp_Dr;
		Descripts = descripts;
		IP = iP;
		IdentifyCode = identifyCode;
		Purpose_Dr = purpose_Dr;
		DataID = dataID;
		StarDate = starDate;
		StopDate = stopDate;
		CreateDate = createDate;
		CreateUser_Dr = createUser_Dr;
	}
	public Medical() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Medical [ID=" + ID + ", Hosp_Dr=" + Hosp_Dr + ", Descripts=" + Descripts + ", IP=" + IP
				+ ", IdentifyCode=" + IdentifyCode + ", Purpose_Dr=" + Purpose_Dr + ", DataID=" + DataID + ", StarDate="
				+ StarDate + ", StopDate=" + StopDate + ", CreateDate=" + CreateDate + ", CreateUser_Dr="
				+ CreateUser_Dr + "]";
	}
	
	
	

}

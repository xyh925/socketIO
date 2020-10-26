package com.xys.server;

import java.util.List;
import com.xys.pojo.Medical;


public interface MedicalServer {

	List<Medical> selectInfo(String type, String purpose_Dr, String dataID);

}

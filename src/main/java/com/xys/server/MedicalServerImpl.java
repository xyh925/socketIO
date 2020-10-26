package com.xys.server;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xys.dao.SelectSendUser;
import com.xys.pojo.Medical;

@Service
public class MedicalServerImpl implements MedicalServer {

	@Override
	public List<Medical> selectInfo(String type, String purpose_Dr, String dataID) {
		SelectSendUser selectSendUser = new SelectSendUser();
		return selectSendUser.selectInfo(type, purpose_Dr, dataID);
	}

}

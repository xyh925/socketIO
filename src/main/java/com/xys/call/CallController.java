package com.xys.call;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corundumstudio.socketio.SocketIOClient;
import com.xys.pojo.Medical;
import com.xys.server.MedicalServer;

import net.sf.json.JSONObject;

@RestController
public class CallController {
	
	@Autowired
	private MedicalServer medicalServer;
	
	@RequestMapping(value="/selectMedicalInfo",method=RequestMethod.POST)
	public JSONObject hello(@RequestBody Object data) {
		JSONObject json  = JSONObject.fromObject(data); 
		String sessionID=json.get("sessionID").toString(); 
		SocketIOClient sendClient = SocketEvent.clientMap.get(sessionID);
		JSONObject result=new JSONObject();
		if(sendClient!=null) {
			sendClient.sendEvent("respondMessage",json.toString());
			result.put("errorCode", "0");
			result.put("errorMessage", "发送成功");
		}else {
			result.put("errorCode", "-1");
			result.put("errorMessage", "用户未登陆");
		}
		return result;
	}

}

package com.xys.call;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.xys.dao.SelectSendUser;
import com.xys.pojo.ClientUser;
import com.xys.pojo.Medical;

import net.sf.json.JSONObject;

@Component
public class SocketEvent {
	
    public static SocketIOServer socketIoServer;

    @Autowired
    public  SocketEvent(SocketIOServer server) {
        this.socketIoServer = server;
    }
	 public static Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();
	//客户端连上socket服务器时执行此事件
	    
	 @OnConnect
	    public void onConnect(SocketIOClient client) {
		 	String remoteAddress = client.getRemoteAddress().toString();
			String IP=remoteAddress.substring(1, remoteAddress.indexOf(":"));
			String Port=remoteAddress.substring(remoteAddress.indexOf(":")+1, remoteAddress.length());
			Map params = client.getHandshakeData().getUrlParams();
			String sessionID="";
			if(params.get("sessionID")!=null) {
				ArrayList sessionArr=(ArrayList)params.get("sessionID");
				if(sessionArr.size()>0) {
					sessionID=sessionArr.get(0).toString();
				}
				
			}
			if(sessionID!="") {
				clientMap.put(sessionID, client);
			}else {
				clientMap.put(IP, client);
			}
			System.out.println("======>连接成功"+client.getRemoteAddress()+"-----"+sessionID);
	    }
	 
	//客户端断开socket服务器时执行此事件	 
	    @OnDisconnect
	    public void onDisconnect(SocketIOClient client) {
	        String remoteAddress = client.getRemoteAddress().toString(); 
			String IP=remoteAddress.substring(1, remoteAddress.indexOf(":"));
			clientMap.remove(IP);
			client.disconnect();
			System.out.println("======>断开成功"+client.getRemoteAddress());
	    }
	    //服务器响应叫号事件
	    @OnEvent(value="callNumber")
	    public void callNumber(SocketIOClient client, String data, AckRequest ackRequest) {
	    	JSONObject object = JSONObject.fromObject(data);
	    	if(!("").equals(object.get("locID")) || !("").equals(object.get("roomID"))) {
	    		SelectSendUser selectSendUsr = new SelectSendUser();
	    		List<Medical> list = selectSendUsr.selectInfo(object.get("type").toString(),object.get("locID").toString(),object.get("roomID").toString());
	    		if(list.size()==0) {
	    			client.sendEvent("response", "未获取到配置信息,请联系管理员。");
	    		}
	    		for (Medical medical : list) {
	    			SocketIOClient sendClient = clientMap.get(medical.getIP());
	    			if(sendClient !=null) {
	    				sendClient.sendEvent("callNumber", data);
	    			}else {
	    				client.sendEvent("response", "接收端尚未创立连接");
	    			}
	    			
				}
	    	}
	    }
	    //服务器响应消息事件
	    @OnEvent(value="Message")
	    public void Message(SocketIOClient client, String data, AckRequest ackRequest) {
	    	System.out.println("client data --->"+data);
	    	JSONObject object = JSONObject.fromObject(data);
	    	if(!("").equals(object.get("locID")) || !("").equals(object.get("roomID"))) {
	    		SelectSendUser selectSendUsr = new SelectSendUser();
	    		List<Medical> list = selectSendUsr.selectInfo(object.get("type").toString(),object.get("locID").toString(),object.get("roomID").toString());
	    		if(list.size()==0) {
	    			client.sendEvent("response", "未获取到配置信息,请联系管理员。");
	    		} 
	    		for (Medical medical : list) {
	    			SocketIOClient sendClient = clientMap.get(medical.getIP());
	    			if(sendClient !=null) {
	    				sendClient.sendEvent("Message", data);
	    			}else {
	    				client.sendEvent("response", "接收端尚未创立连接");
	    			}
	    			
				}
	    		SocketIOClient sendClient = clientMap.get("172.18.13.33");
	    		sendClient.sendEvent("response", "测试123");
	    	}
	    }


}

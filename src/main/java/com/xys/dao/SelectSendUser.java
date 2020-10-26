package com.xys.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intersys.jdbc.CacheDriver; 
import com.xys.pojo.Medical;
 
public class SelectSendUser { 
	
	public List<Medical> selectInfo(String type,String locID,String roomID) {
		
		List<Medical> list = new ArrayList<Medical>();
		
		Connection conn = null ;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.intersys.jdbc.CacheDriver");
			String url ="jdbc:Cache://172.18.100.86:1972/BDHEALTH";
			String username = "_system";
			String password = "sys";
			conn = DriverManager.getConnection(url,username,password);;
			st = conn.prepareStatement("SELECT a.ID,a.Hosp_Dr,a.Descripts,a.IP,a.IdentifyCode,a.Purpose_Dr,a.DataID FROM SQLUser.HB_DeviceIPBind a,HB_DevicePurpose b WHERE a.Purpose_Dr = b.ID AND a.StopDate IS NULL AND b.Code = ? And a.DataID in (?,?) ");
			st.setString(1, type);
			st.setString(2, locID);
			st.setString(3, roomID);
			rs = st.executeQuery();
			System.out.println(rs.toString());
			while(rs.next())
			{
				Medical medical = new Medical();
				medical.setID(rs.getInt(1));
				medical.setHosp_Dr(rs.getInt(2));
				medical.setDescripts(rs.getString(3));
				medical.setIP(rs.getString(4));
				medical.setIdentifyCode(rs.getString(5));
				medical.setPurpose_Dr(rs.getInt(6));
				medical.setDataID(rs.getInt(7));
				list.add(medical);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		} finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  list;
	}
}

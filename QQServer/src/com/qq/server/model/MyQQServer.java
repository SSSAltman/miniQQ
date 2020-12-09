/*
 * QQ服务器，他在监听，等待某个qq客户端连接
 * */
package com.qq.server.model;

import java.util.*;
import java.net.*;
import java.nio.Buffer;
import java.sql.*;
import javax.swing.*;
import javax.xml.ws.handler.MessageContext;

import com.qq.common.Message;
import com.qq.common.User;

import java.io.*;

public class MyQQServer {
    
	public static void main(String[]args) {
		
	}
	
	public MyQQServer() {
		
		try {
			System.out.println("服务器在9999号端口进行监听");
			//在9999号监听
			ServerSocket ss=new ServerSocket(9999);
			ServerSocket sss=new ServerSocket(3456);
			//阻塞等待连接
			while(true) {
				
				Socket s=ss.accept();
				
			//接收客户端发来的登陆验证消息
				
					ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
					User u=(User)ois.readObject();
			
			
			//System.out.println("服务器接收到用户名  "+u.getUseId()+"\n"+"服务器接收到密码"+u.getPasswd());
			
			
			//接下来进行数据库的验证
			String account1 =u.getUseId();
			//输入你的账号
			String passwords =u.getPasswd();
			String account2 = " ", account3 = " ";//account2是数据库里的密码
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/liangshanheros2?useSSL=true&serverTimezone=GMT";
			String user = "root";
			String password = "Lyshangqhua000";
			String sqlStr = "select passward from 7c where number = '"+account1+"'";
				PreparedStatement ps = null;
				Connection ct = null;
				ResultSet rs = null;
				try {
					Class.forName(driver);
				    ct = DriverManager.getConnection(url, user, password);
				    ps = ct.prepareStatement(sqlStr);
				    rs = ps.executeQuery();
				    while(rs.next()) {
				    	//从数据库里取出真正的密码
				    	account2 = rs.getString(1);
				    }
				}catch(Exception e1){
					e1.printStackTrace();
				}finally {
					try {
						if(rs != null) rs.close();
						if(ps != null) ps.close();
						if(ct != null) ct.close();
						
					}catch(Exception e1) {
						e1.printStackTrace();
					}finally {	
						
					}
				}
			
			
			//作为返回的对象
			Message m = new Message();
			//返回对象
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			
			//进行判断账号和密码是否正确
			if(u.getPasswd().equals(account2)) {
				
				//返回成功登陆的信息
				m.setMesType("1");
				m.setSender(u.getUseId());
				oos.writeObject(m);
				
				
				//单开一个线程，让该线程与客户端保持通讯,并且把线程加入到管理类中
				SerConClientThread sect=new SerConClientThread(s);
				ManageClientThread.addClientThread(u.getUseId(), sect);
				//启动和客户端交流的线程
				sect.start();
				
				
				//单开一个线程,让该线程来不断判断客户端对文件的不同需求
				JudgeFileThread jge = new JudgeFileThread(sss, u);
				jge.start();
				System.out.println("判断线程已经启动");
				//System.out.print("已经返回确认值");
			}else {
				//返回失败登陆的消息
				m.setMesType("2");
				oos.writeObject(m);
				//System.out.print("已经返回确认值");
				//关闭连接
				s.close();
				
			}
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

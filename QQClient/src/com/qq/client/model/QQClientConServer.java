/*
 * 这是客户端连接服务器的后台
 * */
package com.qq.client.model;

import java.awt.*;
import javax.swing.*;

import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.Message;
import com.qq.common.User;

import java.net.*;
import java.io.*;

public class QQClientConServer {

	public Socket s;
	
	//发送第一次请求
	public boolean sendLoginInfoToServer(Object o) {
		boolean b=false;
		try {
			//连接客户端
			s=new Socket("192.168.124.1", 9999);
			//发送对象
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			//接受对象
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			Message ms=(Message)ois.readObject();
			//这里就是验证用户登录的地方
			//System.out.println("接收到对象");
			//判断是否登录成功
			if(ms.getMesType().equals("1")) {
				//System.out.println("判断完毕可以打开新界面");
				//创建一个该QQ号和服务器保持连接的线程
				ClientConServerThread ccst =new ClientConServerThread(s,ms.getSender());
				//启动该通讯线程
				ccst.start();
				ManageClientConServerThread.addClientConServerThread
				(((User)o).getUseId(), ccst);
				b=true;
				}
			else {
				s.close();
			}
			} catch (Exception e) {
			e.printStackTrace();
		}finally{
			}
		return b;
	}
	public void SendInfoToServer(Object o) {
		/*try {
			Socket s=new Socket("192.168.124.1", 9999);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}*/
	}
}

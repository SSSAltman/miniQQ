/*
 *这是客户端和服务器保持通信的线程
 * */
package com.qq.client.model;

import java.util.*;

import com.qq.client.tools.ManageClientVote;
import com.qq.client.tools.ManageQQChat;
import com.qq.client.tools.ManageQQNotice;
import com.qq.client.view.*;
import com.qq.client.view.QQChat;
import com.qq.common.Message;

import java.io.ObjectInputStream;
import java.net.*;

public class ClientConServerThread extends Thread{

	int yes=0;
	int no=0;
	
	private Socket s;  
	private String owner;
	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	//构造函数
	public ClientConServerThread(Socket s,String owner) {
		this.s=s;
		this.owner=owner;
	}
	
	public void run() 
	{
		while(true) 
		{
			//不停的读取从服务器端发来的消息
			try {
				
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				//System.out.println("服务器收到消息: "+m.getSender()+" 给 "+m.getGetter()+" 内容是 "+m.getCon());
				if(m.getGetter().equals("七班群聊")) {
					//把从服务器获得的消息，显示到应该显示的聊天界面
					//System.out.println("这个的owner是"+this.owner);
					QQChat qqChat=ManageQQChat.getQQChat(this.owner+" "+"七班群聊");
					if(qqChat==null) {
						System.out.println("null");
					}
					//显示
					qqChat.showMessage(m);
				}else if(m.getGetter().equals("公告")) {
					//把从服务器获得的消息,显示到公告界面
					System.out.println("接收到公告消息，准备打开公告界面");
					QQNotice qqnotice=ManageQQNotice.getQQNotice("自己");
					System.out.println("发信人是"+m.getSender());
					if(qqnotice==null) {
						System.out.println("null");
					}
					//显示
					qqnotice.showMessage(m);
				}
				else if(m.getGetter().equals("投票")) {
					//取出投票对象
					ClientVote client=ManageClientVote.getClientVote("投票");
					this.yes=0;
					this.no=0;
					client.showMessage(m);
				}
				else if(m.getGetter().equals("赞成")) {
					//取出投票对象
					System.out.println("接收到赞成票");
					this.yes++;
					m.setSender(yes+"");
					ClientVote client=ManageClientVote.getClientVote("投票");
					client.showYesMen(m);
				}
				else if(m.getGetter().equals("反对")) {
					//取出投票对象
					System.out.println("接收到反对票");
					this.no++;
					m.setSender(no+"");
					ClientVote client=ManageClientVote.getClientVote("投票");
					client.showNoMen(m);
				}
				else {
				
				//把从服务器获得的消息，显示到应该显示的聊天界面
				QQChat qqChat=ManageQQChat.getQQChat(m.getGetter()+" "+m.getSender());
				//显示
				qqChat.showMessage(m);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

package com.qq.server.model;

import java.util.*;

import com.qq.common.User;

import java.net.*;
import java.nio.channels.ServerSocketChannel;
import java.io.*;


public class JudgeFileThread extends Thread {

	ServerSocket server;//建一个新的服务器连接
	Socket s;
	User u;
	public JudgeFileThread(ServerSocket sss,User u) {
		//连接客户端
	  	  try {
			this.server = sss;
			this.u=u;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	//在该线程里面循环判断
	public void run() {
		try {
			while(true) {
				s=this.server.accept();
				//接受来自客户端的申请消息
				InputStreamReader isr=new InputStreamReader(s.getInputStream());
				BufferedReader br=new BufferedReader(isr);
				String info = br.readLine();
				
				System.out.println("在判断线程里面收到"+info);
				
				if(info.equals("上传")) {
					System.out.println("接收到一个上传请求");
					
					FileInPutStreamThread cect=new FileInPutStreamThread(s);
					ManageFileThread.addFileThread(this.u.getUseId(), cect);
					
					//文件接受线程打开
					cect.start();
					System.out.println("文件接受线程打开");
				}
				else {
					System.out.println("接受到下载请求"+info);
					FileOutPutStreamThread dect=new FileOutPutStreamThread(s, info);
					
					//文件发回去线程打开
					dect.start();
					System.out.println("文件发回去线程打开");
					
					
				}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

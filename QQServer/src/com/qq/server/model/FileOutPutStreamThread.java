/*
 * 下载客户端文件的线程
 * 当接收到下载文件请求时就打开这个线程
 * */
package com.qq.server.model;

import java.net.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFileChooser;

import com.qq.common.Message;

import java.io.*;
import java.math.RoundingMode;

public class FileOutPutStreamThread extends Thread {

	private FileInputStream fis;
	private DataOutputStream dos;
	Socket s;               //套接字，用来发送文件
	String FileName;        //定义文件名
	
	public FileOutPutStreamThread(Socket ss,String FileName) {
		this.s=ss;
		this.FileName=FileName;
	}
	
	public void run() {
		try {
			
			File file = new File("D:\\5555\\"+this.FileName);
			
			
			
			if(file.exists()) {
				fis = new FileInputStream(file);
				dos = new DataOutputStream(s.getOutputStream());
				
				
				//发送文件名和长度
				dos.writeUTF(file.getName());
				dos.flush();
				dos.writeLong(file.length());
				dos.flush();
				
				// 开始传输文件  
                System.out.println("======== 客户端开始传输文件 ========");  
                byte[] bytes = new byte[1024];  
                int length = 0;  
                long progress = 0;  
                while((length = fis.read(bytes, 0, bytes.length)) != -1) {  
                    dos.write(bytes, 0, length);  
                    dos.flush();  
                    progress += length;  
                    System.out.print("| " + (100*progress/file.length()) + "% |");  
                }  
                System.out.println();  
                System.out.println("======== 服务器文件传输成功 ========");  
			}
			else {
				System.out.println("客户端要求的某个文件不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


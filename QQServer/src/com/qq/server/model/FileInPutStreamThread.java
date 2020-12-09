/*
 接收客户端文件的线程
 * 当接收到上传文件请求时就打开这个线程
 * */
package com.qq.server.model;

import java.net.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;

import com.qq.common.Message;

import java.io.*;
import java.math.RoundingMode;

public class FileInPutStreamThread extends Thread   {

	private static FileOutputStream fos;  //将接收到的文件写入电脑
    private FileInputStream in;     //读取穿送过来的数据文件
    private static DataInputStream dis;
    //Socket socket;          //套接字，用来接受文件
    Socket s;               //套接字，用来接受文件
    private static DecimalFormat df = null; 
    //ServerSocket server;    //建一个新的服务器连接
    static {  
        // 设置数字格式，保留一位有效小数  
        df = new DecimalFormat("#0.0");  
        df.setRoundingMode(RoundingMode.HALF_UP);  
        df.setMinimumFractionDigits(1);  
        df.setMaximumFractionDigits(1);  
    }  
    
    public FileInPutStreamThread(Socket sss) {
    	
    	//连接客户端
  	  try {
		this.s = sss;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    }
	public void run() {
		File directory;    //接收到的文件保存的位置
	      
	    //处理客户端的请求
	      try {
	    	while(true) {
	    	  System.out.println("新的端口已经打开"); 
	    	  System.out.println("新的连接已经搭建");
	    	  
	    	  //用dis读取文件的基本属性
	    	  dis = new DataInputStream(s.getInputStream());
	    	  
	    	  //读取文件的基本属性
	    	  String fileName = dis.readUTF();  
	          long fileLength = dis.readLong();  
	          
	    	  //指定好写入文件的位置，用fos写到文件夹里
	    	  directory = new File("D:\\5555");
	    	  if(!directory.exists()) {  
	              directory.mkdir();  
	          }  
	    	  File file = new File(directory.getAbsolutePath() + File.separatorChar + fileName);  
	          fos = new FileOutputStream(file);


	          //接收数据
	          byte[] bytes = new byte[1024];  
	          int length = 0; 
	          int count =0;
	          while((length = dis.read(bytes, 0, bytes.length)) != -1) {  
	              fos.write(bytes, 0, length);  
	              fos.flush();
	              count++;
	              if(count==fileLength/1024) {
	            	  break;
	              }
	          }  
	          System.out.println("======== 文件接收成功 [File Name：" + fileName + "] [Size：" + getFormatFileSize(fileLength) + "] ========");
	    	  }
	    	  } catch (Exception e) {
	          e.printStackTrace();
	     }
	      finally {  
	         try {  
	             if(fos != null)  
	                 fos.close();  
	             if(dis != null)  
	                 dis.close();  
	         } catch (Exception e) {}  
	     }  
	     
		
	}
	 private String getFormatFileSize(long length) {  
	      double size = ((double) length) / (1 << 30);  
	      if(size >= 1) {  
	          return df.format(size) + "GB";  
	      }  
	      size = ((double) length) / (1 << 20);  
	      if(size >= 1) {  
	          return df.format(size) + "MB";  
	      }  
	      size = ((double) length) / (1 << 10);  
	      if(size >= 1) {  
	          return df.format(size) + "KB";  
	      }  
	      return length + "B";  
	  }  
}

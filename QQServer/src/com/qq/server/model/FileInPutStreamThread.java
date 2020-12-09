/*
 ���տͻ����ļ����߳�
 * �����յ��ϴ��ļ�����ʱ�ʹ�����߳�
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

	private static FileOutputStream fos;  //�����յ����ļ�д�����
    private FileInputStream in;     //��ȡ���͹����������ļ�
    private static DataInputStream dis;
    //Socket socket;          //�׽��֣����������ļ�
    Socket s;               //�׽��֣����������ļ�
    private static DecimalFormat df = null; 
    //ServerSocket server;    //��һ���µķ���������
    static {  
        // �������ָ�ʽ������һλ��ЧС��  
        df = new DecimalFormat("#0.0");  
        df.setRoundingMode(RoundingMode.HALF_UP);  
        df.setMinimumFractionDigits(1);  
        df.setMaximumFractionDigits(1);  
    }  
    
    public FileInPutStreamThread(Socket sss) {
    	
    	//���ӿͻ���
  	  try {
		this.s = sss;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    }
	public void run() {
		File directory;    //���յ����ļ������λ��
	      
	    //����ͻ��˵�����
	      try {
	    	while(true) {
	    	  System.out.println("�µĶ˿��Ѿ���"); 
	    	  System.out.println("�µ������Ѿ��");
	    	  
	    	  //��dis��ȡ�ļ��Ļ�������
	    	  dis = new DataInputStream(s.getInputStream());
	    	  
	    	  //��ȡ�ļ��Ļ�������
	    	  String fileName = dis.readUTF();  
	          long fileLength = dis.readLong();  
	          
	    	  //ָ����д���ļ���λ�ã���fosд���ļ�����
	    	  directory = new File("D:\\5555");
	    	  if(!directory.exists()) {  
	              directory.mkdir();  
	          }  
	    	  File file = new File(directory.getAbsolutePath() + File.separatorChar + fileName);  
	          fos = new FileOutputStream(file);


	          //��������
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
	          System.out.println("======== �ļ����ճɹ� [File Name��" + fileName + "] [Size��" + getFormatFileSize(fileLength) + "] ========");
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

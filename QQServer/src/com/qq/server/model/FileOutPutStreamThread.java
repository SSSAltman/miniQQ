/*
 * ���ؿͻ����ļ����߳�
 * �����յ������ļ�����ʱ�ʹ�����߳�
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
	Socket s;               //�׽��֣����������ļ�
	String FileName;        //�����ļ���
	
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
				
				
				//�����ļ����ͳ���
				dos.writeUTF(file.getName());
				dos.flush();
				dos.writeLong(file.length());
				dos.flush();
				
				// ��ʼ�����ļ�  
                System.out.println("======== �ͻ��˿�ʼ�����ļ� ========");  
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
                System.out.println("======== �������ļ�����ɹ� ========");  
			}
			else {
				System.out.println("�ͻ���Ҫ���ĳ���ļ�������");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


package com.qq.client.view;

import java.util.*;
import java.awt.*;
import javax.swing.*;

import com.qq.client.tools.ManageClientConServerThread;
import com.qq.client.tools.ManageQQChat;
import com.qq.common.Message;

import java.awt.event.*;
import java.net.*;
import java.security.acl.Owner;
import java.text.DecimalFormat;
import java.io.*;
import java.math.RoundingMode;

public class ManageFile extends JFrame implements ActionListener,MouseListener{

	JButton jb1;
	JLabel jb2;
	JScrollPane jsp1;
	JPanel jp1,jp2,jp3;
	String ownerID;
	private static ObjectOutputStream  oos;
	private FileInputStream fis;
	private DataOutputStream dos;
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
	
	public static void main(String[]args) {
		
	} 
	
    public ManageFile(String owner) {
    	
    	try { // 使用Windows的界面风格
			   UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			   e.printStackTrace();
		}
    	
    	this.ownerID=owner;
    	
    	jb1=new JButton("文件上传");
    	jb2=new JLabel("文件下载双击文件名");
    	jp2=new JPanel(new GridLayout(15, 1,4,4));
    	jp3=new JPanel();
    	
    	//建立一个数组，用来储存文件名
    	String arr[]= {"601048877.jpg","Lancer.png","爆豪胜己.jpg",
    			"捕获.PNG","黑红壁纸.jpg","街道.jpg",
    			"楷书1.jpg","骷髅.jpg",
    			"平成时代.jpg","日向.jpg",
    			"贞德.jpg","201800301058_李霆泷_305.docx",
    			"u=3269538440,1704947521&fm=26&gp=0.jpg","老鼠走迷宫.PNG","UML1_1.png",
    			"201800301119.jpg"};               
    	
    	//初始化一个文件列表
    	JLabel []jbls=new JLabel[arr.length];
    	//给每一个标签加上监听器
    	for(int i=0;i<jbls.length;i++) {
    		jbls[i]=new JLabel(arr[i]);
    		jbls[i].addMouseListener(this);
    		jp2.add(jbls[i]);
    	}
    
    	//添加监听器
    	jb1.addActionListener(this);
    	
    	
    	jp3.add(jb1);
    	jp3.add(jb2);
    	
    	jsp1=new JScrollPane(jp2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	jp1=new JPanel(new BorderLayout());
    	
    	jp1.add(jsp1,"West");
    	jp1.add(jp3);
    	
    	this.add(jp1);
    	this.setTitle(this.ownerID+" 的文件界面");
    	this.setVisible(true);
    	this.setSize(800,600);
    }

	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb1) {
			
			try {
				//新建一个Socket连接
				Socket s=new Socket ("127.0.0.1",3456);
				//发送文件上传请求
				PrintWriter pw=new PrintWriter(s.getOutputStream(),true);
				pw.println("上传");
				
				JFileChooser jfc=new JFileChooser("D:\\");
				jfc.showOpenDialog(null);
				File file=jfc.getSelectedFile();
				
				
				
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
	                System.out.println("======== 客户端文件传输成功 ========");  
				}
				
				} catch (Exception e2) {
				e2.printStackTrace();
			}
			finally {  
	            try {  
	                if(fis != null)  
	                    fis.close();  
	                if(dos != null) {}
	                    dos.close();  
	                //s.close();  
	            } catch (Exception e3) {}  
	        } 
		}
		
	}

	//鼠标双击标签
	public void mouseClicked(MouseEvent arg0) {
		//如果点击文件要求下载
		if(arg0.getClickCount()==2) {
			//得到该好友的编号
			String FileName=((JLabel)arg0.getSource()).getText();
			System.out.println("你想要从服务器下载"+FileName);
			System.out.println("准备发送请求");
			try {
				
				//File directory;    //接收到的文件保存的位置
				//新建一个Socket连接
				Socket d=new Socket ("127.0.0.1",3456);
				//发送文件下载请求，直接把文件名发过去
				PrintWriter pw=new PrintWriter(d.getOutputStream(),true);
				pw.println(FileName);
				
				//准备在这里接收文件

		    	  System.out.println("新的端口已经打开"); 
		    	  System.out.println("新的连接已经搭建");
		    	  
		    	  //用dis读取文件的基本属性
		    	  dis = new DataInputStream(d.getInputStream());
		    	  
		    	  //读取文件的基本属性
		    	  String fileName = dis.readUTF();  
		          long fileLength = dis.readLong();  
		          
		    	  //指定好写入文件的位置，用fos写到文件夹里
//		    	  directory = new File("D:\\5556");
//		    	  if(!directory.exists()) {  
//		              directory.mkdir();  
//		          } 
		          JFileChooser jfc=new JFileChooser("D:\\");
					jfc.showOpenDialog(null);
					File file=jfc.getSelectedFile();
//		    	  File file = new File(directory.getAbsolutePath() + File.separatorChar + fileName);  
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
		          System.out.println("======== 客户端文件接收成功 [File Name：" + fileName + "] [Size：" + getFormatFileSize(fileLength) + "] ========");
				}
					
					catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

	//鼠标进入
	public void mouseEntered(MouseEvent arg0) {
		//鼠标进入时就改变颜色
		JLabel jl = (JLabel)arg0.getSource();
		jl.setForeground(Color.GREEN);
		
	}

	//鼠标退出
	public void mouseExited(MouseEvent arg0) {
		//鼠标退出时就变回原来的颜色
		JLabel jl = (JLabel)arg0.getSource();
		jl.setForeground(Color.BLACK);
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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

/*
 * 功能:是服务器和某个客户端的通信线程
 * */
package com.qq.server.model;

import java.net.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;

import com.qq.common.Message;

import java.io.*;
import java.math.RoundingMode;

public class SerConClientThread extends Thread {

	Socket s;
	int yes=0;
	int no=0;
	
	public SerConClientThread(Socket s) {
		//把服务器和客户端的连接赋值给s
		this.s=s;
		}
	
	
	public void run() {
		
		while(true) {
			
			//这里该线程就可以不停接收客户端的消息
			try {
				
				//System.out.println("准备接受对象");
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
	    		Message m=(Message)ois.readObject();

				System.out.println("一个对象接收完毕");
				
				//判断这个对象是不是群聊
				if(m.getGetter().equals("七班群聊")) {
					//得到所有在线的人的线程
                    HashMap hm= ManageClientThread.hm;
                    Iterator it=hm.keySet().iterator();
                    while(it.hasNext())
                    {
                        //取出在线人的id
                        String onLineUserId=it.next().toString();
                        if (onLineUserId.equals(m.getSender())){

                        }else {
                            try {
                                ObjectOutputStream oos=new ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
                                oos.writeObject(m);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }
				}
				//判断这个对象是不是公告
				else if(m.getGetter().equals("公告")) {
					//得到所有在线的人的线程
                    HashMap hm= ManageClientThread.hm;
                    Iterator it=hm.keySet().iterator();
                    while(it.hasNext())
                    {
                        //取出在线人的id
                        String onLineUserId=it.next().toString();
                        if (onLineUserId.equals(m.getSender())){

                        }else {
                            try {
                         //对所有在线人发送公告对象   	
                                ObjectOutputStream oos=new ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
                                oos.writeObject(m);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }
				}
				//判断这个对象是不是投票
				else if(m.getGetter().equals("投票")) {
					System.out.println("服务器接受到了来自"+m.getSender()+" 的投票申请   "+" 内容是 "+m.getCon());

//					//得到所有在线的人的线程
//                    HashMap hm= ManageClientThread.hm;
//                    Iterator it=hm.keySet().iterator();
//                    while(it.hasNext())
//                    {
//                        //取出在线人的id
//                        String onLineUserId=it.next().toString();
//                        if (onLineUserId.equals(m.getSender())){
//
//                        }else {
//                            try {
//                         //对所有在线人发送投票对象   	
//                                ObjectOutputStream oos=new ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
//                                oos.writeObject(m);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                    }
					//得到所有在线的人的线程
                    HashMap hm= ManageClientThread.hm;
                    Iterator it=hm.keySet().iterator();
                    while(it.hasNext())
                    {
                        //取出在线人的id
                        String onLineUserId=it.next().toString();
                        if (onLineUserId.equals(m.getSender())){

                        }else {
                            try {
                                ObjectOutputStream oos=new ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
                                oos.writeObject(m);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }
				}
				else if(m.getGetter().equals("赞成")) {
					yes++;
					//把对象的Sender改为赞成票数
					m.setSender(yes+"");
//					//得到所有在线的人的线程
//                    HashMap hm= ManageClientThread.hm;
//                    Iterator it=hm.keySet().iterator();
//                    while(it.hasNext())
//                    {
//                        //取出在线人的id
//                        String onLineUserId=it.next().toString();
//                        if (onLineUserId.equals(m.getSender())){
//
//                        }else {
//                            try {
//                         //对所有在线人发送投票对象   	
//                                ObjectOutputStream oos=new ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
//                                oos.writeObject(m);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                    }
//                    //给自己发一个对象
//    				//发给自己消息
//    				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
//    				oos.writeObject(m);
					//得到所有在线的人的线程
                    HashMap hm= ManageClientThread.hm;
                    Iterator it=hm.keySet().iterator();
                    while(it.hasNext())
                    {
                        //取出在线人的id
                        String onLineUserId=it.next().toString();
                       
                            try {
                                ObjectOutputStream oos=new ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
                                oos.writeObject(m);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        

                    }
				}
				else if(m.getGetter().equals("反对")) {
					no++;
					//把对象的Sender改为反对票数
					m.setSender(no+"");
//					//得到所有在线的人的线程
//                    HashMap hm= ManageClientThread.hm;
//                    Iterator it=hm.keySet().iterator();
//                    while(it.hasNext())
//                    {
//                        //取出在线人的id
//                        String onLineUserId=it.next().toString();
//                        if (onLineUserId.equals(m.getSender())){
//
//                        }else {
//                            try {
//                         //对所有在线人发送投票对象   	
//                                ObjectOutputStream oos=new ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
//                                oos.writeObject(m);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                    }
//                  //给自己发一个对象
//    				//发给自己消息
//    				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
//    				oos.writeObject(m);
					//得到所有在线的人的线程
                    HashMap hm= ManageClientThread.hm;
                    Iterator it=hm.keySet().iterator();
                    while(it.hasNext())
                    {
                        //取出在线人的id
                        String onLineUserId=it.next().toString();
                         
                            try {
                                ObjectOutputStream oos=new ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
                                oos.writeObject(m);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        

                    }
				}
				else {
				//一会完成转发
				//取得接收人的通讯线程
				SerConClientThread sc=ManageClientThread.getClientThread(m.getGetter());
				//发给接收人消息
				ObjectOutputStream oos=new ObjectOutputStream(sc.s.getOutputStream());
				oos.writeObject(m);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//System.out.println("要经历过这个finally");
			}
			
		}
		
	}
}

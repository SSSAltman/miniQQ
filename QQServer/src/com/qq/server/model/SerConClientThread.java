/*
 * ����:�Ƿ�������ĳ���ͻ��˵�ͨ���߳�
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
		//�ѷ������Ϳͻ��˵����Ӹ�ֵ��s
		this.s=s;
		}
	
	
	public void run() {
		
		while(true) {
			
			//������߳̾Ϳ��Բ�ͣ���տͻ��˵���Ϣ
			try {
				
				//System.out.println("׼�����ܶ���");
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
	    		Message m=(Message)ois.readObject();

				System.out.println("һ������������");
				
				//�ж���������ǲ���Ⱥ��
				if(m.getGetter().equals("�߰�Ⱥ��")) {
					//�õ��������ߵ��˵��߳�
                    HashMap hm= ManageClientThread.hm;
                    Iterator it=hm.keySet().iterator();
                    while(it.hasNext())
                    {
                        //ȡ�������˵�id
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
				//�ж���������ǲ��ǹ���
				else if(m.getGetter().equals("����")) {
					//�õ��������ߵ��˵��߳�
                    HashMap hm= ManageClientThread.hm;
                    Iterator it=hm.keySet().iterator();
                    while(it.hasNext())
                    {
                        //ȡ�������˵�id
                        String onLineUserId=it.next().toString();
                        if (onLineUserId.equals(m.getSender())){

                        }else {
                            try {
                         //�����������˷��͹������   	
                                ObjectOutputStream oos=new ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
                                oos.writeObject(m);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }
				}
				//�ж���������ǲ���ͶƱ
				else if(m.getGetter().equals("ͶƱ")) {
					System.out.println("���������ܵ�������"+m.getSender()+" ��ͶƱ����   "+" ������ "+m.getCon());

//					//�õ��������ߵ��˵��߳�
//                    HashMap hm= ManageClientThread.hm;
//                    Iterator it=hm.keySet().iterator();
//                    while(it.hasNext())
//                    {
//                        //ȡ�������˵�id
//                        String onLineUserId=it.next().toString();
//                        if (onLineUserId.equals(m.getSender())){
//
//                        }else {
//                            try {
//                         //�����������˷���ͶƱ����   	
//                                ObjectOutputStream oos=new ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
//                                oos.writeObject(m);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                    }
					//�õ��������ߵ��˵��߳�
                    HashMap hm= ManageClientThread.hm;
                    Iterator it=hm.keySet().iterator();
                    while(it.hasNext())
                    {
                        //ȡ�������˵�id
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
				else if(m.getGetter().equals("�޳�")) {
					yes++;
					//�Ѷ����Sender��Ϊ�޳�Ʊ��
					m.setSender(yes+"");
//					//�õ��������ߵ��˵��߳�
//                    HashMap hm= ManageClientThread.hm;
//                    Iterator it=hm.keySet().iterator();
//                    while(it.hasNext())
//                    {
//                        //ȡ�������˵�id
//                        String onLineUserId=it.next().toString();
//                        if (onLineUserId.equals(m.getSender())){
//
//                        }else {
//                            try {
//                         //�����������˷���ͶƱ����   	
//                                ObjectOutputStream oos=new ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
//                                oos.writeObject(m);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                    }
//                    //���Լ���һ������
//    				//�����Լ���Ϣ
//    				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
//    				oos.writeObject(m);
					//�õ��������ߵ��˵��߳�
                    HashMap hm= ManageClientThread.hm;
                    Iterator it=hm.keySet().iterator();
                    while(it.hasNext())
                    {
                        //ȡ�������˵�id
                        String onLineUserId=it.next().toString();
                       
                            try {
                                ObjectOutputStream oos=new ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
                                oos.writeObject(m);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        

                    }
				}
				else if(m.getGetter().equals("����")) {
					no++;
					//�Ѷ����Sender��Ϊ����Ʊ��
					m.setSender(no+"");
//					//�õ��������ߵ��˵��߳�
//                    HashMap hm= ManageClientThread.hm;
//                    Iterator it=hm.keySet().iterator();
//                    while(it.hasNext())
//                    {
//                        //ȡ�������˵�id
//                        String onLineUserId=it.next().toString();
//                        if (onLineUserId.equals(m.getSender())){
//
//                        }else {
//                            try {
//                         //�����������˷���ͶƱ����   	
//                                ObjectOutputStream oos=new ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
//                                oos.writeObject(m);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                    }
//                  //���Լ���һ������
//    				//�����Լ���Ϣ
//    				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
//    				oos.writeObject(m);
					//�õ��������ߵ��˵��߳�
                    HashMap hm= ManageClientThread.hm;
                    Iterator it=hm.keySet().iterator();
                    while(it.hasNext())
                    {
                        //ȡ�������˵�id
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
				//һ�����ת��
				//ȡ�ý����˵�ͨѶ�߳�
				SerConClientThread sc=ManageClientThread.getClientThread(m.getGetter());
				//������������Ϣ
				ObjectOutputStream oos=new ObjectOutputStream(sc.s.getOutputStream());
				oos.writeObject(m);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//System.out.println("Ҫ���������finally");
			}
			
		}
		
	}
}

package com.qq.client.view;
/*QQ�ͻ��˵�½����
 * */
import javax.swing.*;

import com.qq.client.model.QQClientUser;
import com.qq.common.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class QqClientLogin extends JFrame implements ActionListener{

	//������Ҫ�����
	JLabel jbl1;
	
	//�в���Ҫ�����
	//�в�����JPanel����һ��ѡ�
	JTabbedPane jtp;
	JPanel jp2,jp3,jp4;
	JLabel jp2_jbl1,jp2_jbl2,jp2_jbl3,jp2_jbl4;
	JButton jp2_jb1;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1,jp2_jcb2;
	
	//�ϲ���Ҫ�����
	JPanel jp1;
	
	JButton jp1_jb1,jp1_jb2,jp1_jb3;
	
	public static void main(String[] args) {
		
       new QqClientLogin();
	}
	
	public QqClientLogin() {
		
		//������
		jbl1=new JLabel(new ImageIcon("Image/Lancer1.0.png"));
        
		//�����в�
		jp2=new JPanel(new GridLayout(3,3));
		
		jp2_jbl1=new JLabel("QQ����",JLabel.CENTER);
		jp2_jbl2=new JLabel("QQ����",JLabel.CENTER);
		jp2_jbl3=new JLabel("��������",JLabel.CENTER);
		jp2_jbl3.setForeground(Color.blue);
		jp2_jbl4=new JLabel("���뱣��",JLabel.CENTER);
		jp2_jb1=new JButton("���");
		jp2_jtf=new JTextField();
		jp2_jpf=new JPasswordField();
		jp2_jcb1=new JCheckBox("�����½");
		jp2_jcb2=new JCheckBox("��ס����");
		
		//�������
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jtf);
		jp2.add(jp2_jb1);
		jp2.add(jp2_jbl2);
		jp2.add(jp2_jpf);
		jp2.add(jp2_jbl3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jbl4);
		
		//����ѡ�����
		jtp = new JTabbedPane();
		jtp.add("QQ����", jp2);
		jp3 = new JPanel();
		jtp.addTab("�ֻ�����", jp3);
		jp4 = new JPanel();
		jtp.add("�����ʼ�", jp4);
		
		//�����ϲ�
		jp1=new JPanel();
		jp1_jb1=new JButton("��½");
		//������½������
		jp1_jb1.addActionListener(this);
		jp1_jb2=new JButton("ȡ��");
		jp1_jb3=new JButton("��");
		
		//������ť����
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		
		//��Ӽ��̼�����ʵ�����ı����ﰴ��Enter֮�������ת�������
				jp2_jtf.addKeyListener(new KeyAdapter() {
			    	  public void keyPressed(KeyEvent e) {
			    		  if(e.getKeyChar()==KeyEvent.VK_ENTER) {
			        			jp2_jpf.requestFocus();
			        		}
			    	  }
			      });
		//��Ӽ��̼�����ʵ����������ﰴ��Enter֮����Ե�½
		jp2_jpf.addKeyListener(new KeyAdapter() {
	      	public void keyPressed(KeyEvent e) {
	      		if(e.getKeyChar()==KeyEvent.VK_ENTER) {
	      			jp1_jb1.doClick();
	      		}
	      	}
	      });
		
		this.add(jtp, "Center");
		this.add(jp1,"South");
		this.add(jbl1,"North");
		this.setSize(550,350);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//����û������½
		if(e.getSource()==jp1_jb1) {
			QQClientUser qqClientUser = new QQClientUser();
			User u = new User();
			u.setUseId(jp2_jtf.getText().trim());
			u.setPasswd(new String(jp2_jpf.getPassword()));
			
			//��֤��¼�Ƿ�ɹ�
			if(qqClientUser.checkUser(u)) {
				new NewQQFriendList(u.getUseId());
				//�ر��������
				this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(this,"�û��������������");
			}
			
		}
		
	}

}

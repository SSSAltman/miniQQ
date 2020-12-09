package com.qq.client.view;
/*QQ客户端登陆界面
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

	//东部需要的组件
	JLabel jbl1;
	
	//中部需要的组件
	//中部三个JPanel，有一个选项卡
	JTabbedPane jtp;
	JPanel jp2,jp3,jp4;
	JLabel jp2_jbl1,jp2_jbl2,jp2_jbl3,jp2_jbl4;
	JButton jp2_jb1;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1,jp2_jcb2;
	
	//南部需要的组件
	JPanel jp1;
	
	JButton jp1_jb1,jp1_jb2,jp1_jb3;
	
	public static void main(String[] args) {
		
       new QqClientLogin();
	}
	
	public QqClientLogin() {
		
		//处理北部
		jbl1=new JLabel(new ImageIcon("Image/Lancer1.0.png"));
        
		//处理中部
		jp2=new JPanel(new GridLayout(3,3));
		
		jp2_jbl1=new JLabel("QQ号码",JLabel.CENTER);
		jp2_jbl2=new JLabel("QQ密码",JLabel.CENTER);
		jp2_jbl3=new JLabel("忘记密码",JLabel.CENTER);
		jp2_jbl3.setForeground(Color.blue);
		jp2_jbl4=new JLabel("密码保护",JLabel.CENTER);
		jp2_jb1=new JButton("清除");
		jp2_jtf=new JTextField();
		jp2_jpf=new JPasswordField();
		jp2_jcb1=new JCheckBox("隐身登陆");
		jp2_jcb2=new JCheckBox("记住密码");
		
		//加入组件
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jtf);
		jp2.add(jp2_jb1);
		jp2.add(jp2_jbl2);
		jp2.add(jp2_jpf);
		jp2.add(jp2_jbl3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jbl4);
		
		//创建选项卡窗口
		jtp = new JTabbedPane();
		jtp.add("QQ号码", jp2);
		jp3 = new JPanel();
		jtp.addTab("手机号码", jp3);
		jp4 = new JPanel();
		jtp.add("电子邮件", jp4);
		
		//处理南部
		jp1=new JPanel();
		jp1_jb1=new JButton("登陆");
		//建立登陆监听器
		jp1_jb1.addActionListener(this);
		jp1_jb2=new JButton("取消");
		jp1_jb3=new JButton("向导");
		
		//三个按钮加入
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		
		//添加键盘监听，实现在文本框里按下Enter之后可以跳转到密码框
				jp2_jtf.addKeyListener(new KeyAdapter() {
			    	  public void keyPressed(KeyEvent e) {
			    		  if(e.getKeyChar()==KeyEvent.VK_ENTER) {
			        			jp2_jpf.requestFocus();
			        		}
			    	  }
			      });
		//添加键盘监听，实现在密码框里按下Enter之后可以登陆
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
		//如果用户点击登陆
		if(e.getSource()==jp1_jb1) {
			QQClientUser qqClientUser = new QQClientUser();
			User u = new User();
			u.setUseId(jp2_jtf.getText().trim());
			u.setPasswd(new String(jp2_jpf.getPassword()));
			
			//验证登录是否成功
			if(qqClientUser.checkUser(u)) {
				new NewQQFriendList(u.getUseId());
				//关闭这个界面
				this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(this,"用户名或者密码错误");
			}
			
		}
		
	}

}

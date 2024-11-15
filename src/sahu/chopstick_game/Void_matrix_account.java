package sahu.chopstick_game;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.border.MatteBorder;
import javax.swing.JPanel;
import java.awt.ComponentOrientation;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;
import java.awt.Insets;
import javax.swing.DropMode;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.border.SoftBevelBorder;

import org.apache.commons.mail.EmailException;

import javax.swing.border.CompoundBorder;
import java.awt.SystemColor;
import javax.swing.UIManager;

@SuppressWarnings({"unused","deprecation"})
public class Void_matrix_account implements MouseListener,Runnable,ActionListener{

	public JFrame vma_frm;
	
	public JPanel signup_pnl,login_pnl,pag_pnl,main_pnl;
	
	private JLabel vmalbl,albl,vmimg,
				   lblcano,lblUnm,lblpwd,lblGmail,lblshowkindinfo,lblAge,lblClickOnLogin,
	 			   siieo_lbl,unmlogin,pwdlogin,
	 			   gias,pname,waitlbl,waitlbl2,waitlbl3;
	
	private JButton signupbtn,createbtnSignup,
	  				loginbtn,letsgologinbtn,
	  				pagbtn,pagletsgobtn;
	  				
	private JTextField txtunmSignup, txtgmailSignup, txtagesignup,
	 				   txtunmlogin,
	 				   txtnmpag;
	
	private JPasswordField txtpwdSignup,txtpwdlogin;

	public String nnm,npwd,ngml,nage,
				  lognm,logpwd, u,p,
				  pagname,
				  sgunm,sgpwd,sggm,sgag;
	
	public boolean up=false,email=true,log=true,gap=true;
	public static boolean pag=false;
	
	BufferedWriter pl=null,w=null,l=null,set=null;
	
	public Connection c;
	public Statement st;
	
	public void connectToDB() {
		String url="jdbc:mysql://db4free.net:3306/stickchop?useSSL=false";
		String usr="voidmatrix";
		String s="voidmatrix2002";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c= DriverManager.getConnection(url,usr,s);
			st=c.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(signup_pnl, 
					"So Sorry, some errors occurred\nBut you can still play\nErr: "+e.getMessage(),
					"DBConnection failure", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void datasignup() {
		try {
			PreparedStatement p=c.prepareStatement("insert into ChopStick_Players values(?,?,?,?,?,?)");
			p.setString(1, sgunm);
			p.setString(2, sgpwd);
			p.setString(3, sggm);
			p.setInt(4, Integer.parseInt(sgag));
			p.setInt(5, 0);
			p.setInt(6, 0);
			p.execute();
			w=new BufferedWriter(new FileWriter("won.txt"));
			l=new BufferedWriter(new FileWriter("lost.txt"));
			w.write(0);
			l.write(0);
			l.close();
			w.close();
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(signup_pnl, 
				"So Sorry, some errors occurred\nBut you can still play\nErr: "+e.getMessage(), 
				"Failed to sign up", JOptionPane.WARNING_MESSAGE);
			try {
				w=new BufferedWriter(new FileWriter("won.txt"));
				l=new BufferedWriter(new FileWriter("lost.txt"));
				w.write(0);
				l.write(0);
				l.close();
				w.close();
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void datalogin() {
		try {	
			ResultSet rs=st.executeQuery("select * from signuChopStick_Players");
			while(rs.next()){
				if(lognm.equals(rs.getString(1)) && logpwd.equals(rs.getString(2))) {
					up=true;
					pl=new BufferedWriter(new FileWriter("player.txt"));
					w=new BufferedWriter(new FileWriter("won.txt"));
					l=new BufferedWriter(new FileWriter("lost.txt"));
					pl.write(lognm);
					w.write(rs.getInt(5));
					l.write(rs.getInt(6));
					pl.close();
					w.close();
					l.close();
					break;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(login_pnl, 
					"So Sorry, some errors occurred\nBut you can still play\nErr: "+e.getMessage(), 
					 "Failed to login", JOptionPane.ERROR_MESSAGE);
			try {
				pl=new BufferedWriter(new FileWriter("player.txt"));
				w=new BufferedWriter(new FileWriter("won.txt"));
				l=new BufferedWriter(new FileWriter("lost.txt"));
				pl.write(lognm);
				w.write(0);
				l.write(0);
				pl.close();
				w.close();
				l.close();
				vma_frm.dispose();
				c.close();
				ChopStick cs=new ChopStick();
				cs.name.setText(lognm);
				cs.main_frm.setVisible(true);
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			
		}
		if(!up) {
			JOptionPane.showMessageDialog(login_pnl,
										  "Invalid Username or Password","Error Logging in",
										  JOptionPane.ERROR_MESSAGE);
		}
		else {
			vma_frm.dispose();
			try {
				c.close();
				ChopStick cs=new ChopStick();
				cs.name.setText(lognm);
				cs.main_frm.setVisible(true);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void datapag() {
		try {
			pl=new BufferedWriter(new FileWriter("player.txt"));
			w=new BufferedWriter(new FileWriter("won.txt"));
			l=new BufferedWriter(new FileWriter("lost.txt"));
			w.write(0);
			l.write(0);
			pl.write(pagname);
			pl.close();
			w.close();
			l.close();
			PreparedStatement p=c.prepareStatement("insert into pag values(?)");
			p.setString(1, pagname);
			p.execute();
			
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(pag_pnl, e.getMessage()+"\nbut You can still play",
					                     "Connection Failure", JOptionPane.ERROR_MESSAGE);
			try {
				vma_frm.dispose();
				ChopStick cs=new ChopStick();
				cs.name.setText(pagname);
				cs.main_frm.setVisible(true);
				ChopStick.t2=false;
				set=new BufferedWriter(new FileWriter("settings.txt"));
				set.close();
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	
	public void lemmesendemail() throws Exception {
		email=true;
		while(email) {
			Thread.sleep(1000);
		}
		Mails.sendgreet(sggm, sgunm, sgpwd);
		Mails.Activity(sgunm);
		lemmesendemail();
	}
	
	@Override
	public void run() {
		try {
			connectToDB();
			lemmesendemail();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Void_matrix_account() {
		initialize();
		new Thread(this).start();
	}

	private void initialize() {
		vma_frm= new JFrame();
		vma_frm.setResizable(false);
		vma_frm.setForeground(Color.BLACK);
		vma_frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo_F.png")));
		vma_frm.setBackground(Color.BLACK);
		vma_frm.setFont(new Font("Dialog", Font.BOLD, 20));
		vma_frm.setTitle("ChopStick");
		vma_frm.setBounds(150, 20, 1250, 785);
		vma_frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vma_frm.getContentPane().setLayout(null);
		
		main_pnl = new JPanel();
		main_pnl.setBackground(new Color(0, 255, 0));
		main_pnl.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		main_pnl.setBounds(0, 0, 319, 748);
		vma_frm.getContentPane().add(main_pnl);
		main_pnl.setLayout(null);
		
		signupbtn = new JButton("Sign Up");
		signupbtn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		signupbtn.addMouseListener(this);
		signupbtn.setForeground(new Color(0, 0, 51));
		signupbtn.addActionListener(this);
		signupbtn.setFocusable(false);
		signupbtn.setBackground(new Color(255, 153, 153));
		signupbtn.setFont(new Font("Tahoma", Font.BOLD, 25));
		signupbtn.setBounds(83, 278, 146, 55);
		main_pnl.add(signupbtn);
		
		loginbtn = new JButton("Log In");
		loginbtn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		loginbtn.addMouseListener(this);
		loginbtn.setForeground(new Color(0, 0, 51));
		loginbtn.addActionListener(this);
		loginbtn.setFocusable(false);
		loginbtn.setFont(new Font("Tahoma", Font.BOLD, 25));
		loginbtn.setBackground(new Color(255, 153, 153));
		loginbtn.setBounds(83, 389, 146, 55);
		main_pnl.add(loginbtn);
		
		pagbtn = new JButton("Play As Guest");
		pagbtn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pagbtn.addMouseListener(this);
		pagbtn.setForeground(new Color(0, 0, 51));
		pagbtn.addActionListener(this);
		pagbtn.setFocusable(false);
		pagbtn.setFont(new Font("Tahoma", Font.BOLD, 25));
		pagbtn.setBackground(new Color(255, 153, 153));
		pagbtn.setBounds(48, 500, 212, 55);
		main_pnl.add(pagbtn);
		
		vmalbl = new JLabel("Void_Matrix[:]");
		vmalbl.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		vmalbl.setForeground(new Color(0, 0, 204));
		vmalbl.setFont(new Font("Tahoma", Font.BOLD, 35));
		vmalbl.setHorizontalAlignment(SwingConstants.CENTER);
		vmalbl.setBounds(20, 140, 274, 56);
		main_pnl.add(vmalbl);
		
		albl = new JLabel("Account");
		albl.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		albl.setHorizontalAlignment(SwingConstants.CENTER);
		albl.setForeground(new Color(0, 0, 204));
		albl.setFont(new Font("Tahoma", Font.BOLD, 35));
		albl.setBounds(60, 199, 197, 43);
		main_pnl.add(albl);
		
		vmimg = new JLabel();
		vmimg.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		vmimg.setBounds(104, 42, 101, 96);
		vmimg.setIcon(new ImageIcon(getClass().getResource("team1.png")));
		main_pnl.add(vmimg);
		
		signup_pnl = new JPanel();
		signup_pnl.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		signup_pnl.setBackground(new Color(255, 255, 51));
		signup_pnl.setBounds(318, 0, 918, 748);
		vma_frm.getContentPane().add(signup_pnl);
		signup_pnl.setLayout(null);
		
		lblcano = new JLabel("Create A New One");
		lblcano.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblcano.setHorizontalAlignment(SwingConstants.CENTER);
		lblcano.setForeground(new Color(0, 0, 51));
		lblcano.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblcano.setBounds(300, 30, 313, 71);
		signup_pnl.add(lblcano);
		
		lblUnm = new JLabel("Username\t  \t  :");
		lblUnm.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblUnm.setBackground(Color.LIGHT_GRAY);
		lblUnm.setOpaque(true);
		lblUnm.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnm.setForeground(new Color(0, 0, 51));
		lblUnm.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblUnm.setBounds(10, 131, 228, 54);
		signup_pnl.add(lblUnm);
		
		lblpwd = new JLabel("Password\t    :");
		lblpwd.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblpwd.setBackground(Color.LIGHT_GRAY);
		lblpwd.setOpaque(true);
		lblpwd.setHorizontalAlignment(SwingConstants.CENTER);
		lblpwd.setForeground(new Color(0, 0, 51));
		lblpwd.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblpwd.setBounds(10, 244, 228, 54);
		signup_pnl.add(lblpwd);
		
		lblGmail = new JLabel("Gmail Address\t  :");
		lblGmail.setOpaque(true);
		lblGmail.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblGmail.setBackground(Color.LIGHT_GRAY);
		lblGmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblGmail.setForeground(new Color(0, 0, 51));
		lblGmail.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblGmail.setBounds(10, 365, 228, 54);
		signup_pnl.add(lblGmail);
		
		lblAge = new JLabel("age\t      :");
		lblAge.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblAge.setOpaque(true);
		lblAge.setBackground(Color.LIGHT_GRAY);
		lblAge.setHorizontalAlignment(SwingConstants.CENTER);
		lblAge.setForeground(new Color(0, 0, 51));
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblAge.setBounds(10, 480, 228, 54);
		signup_pnl.add(lblAge);
		
		txtpwdSignup = new JPasswordField();
		txtpwdSignup.setColumns(30);
		txtpwdSignup.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		txtpwdSignup.setForeground(new Color(51, 255, 0));
		txtpwdSignup.setFont(new Font("Tahoma", Font.BOLD, 25));
		txtpwdSignup.setBounds(237, 245, 528, 54);
		signup_pnl.add(txtpwdSignup);
		
		txtunmSignup = new JTextField();
		txtunmSignup.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtunmSignup.setFont(new Font("Tahoma", Font.BOLD, 25));
		txtunmSignup.setForeground(new Color(0, 255, 0));
		txtunmSignup.setBounds(237, 131, 611, 54);
		signup_pnl.add(txtunmSignup);
		txtunmSignup.setColumns(40);
		
		txtgmailSignup = new JTextField();
		txtgmailSignup.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtgmailSignup.setFont(new Font("Tahoma", Font.BOLD, 25));
		txtgmailSignup.setForeground(new Color(0, 255, 0));
		txtgmailSignup.setColumns(50);
		txtgmailSignup.setBounds(237, 365, 671, 54);
		signup_pnl.add(txtgmailSignup);
		
		txtagesignup = new JTextField();
		txtagesignup.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtagesignup.setForeground(new Color(0, 255, 0));
		txtagesignup.setFont(new Font("Tahoma", Font.BOLD, 25));
		txtagesignup.setColumns(10);
		txtagesignup.setBounds(237, 481, 213, 54);
		signup_pnl.add(txtagesignup);
		
		lblshowkindinfo = new JLabel("enter a valid gmail address for recieving mails");
		lblshowkindinfo.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		lblshowkindinfo.setOpaque(true);
		lblshowkindinfo.setBackground(Color.BLUE);
		lblshowkindinfo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblshowkindinfo.setForeground(Color.GREEN);
		lblshowkindinfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblshowkindinfo.setBounds(237, 418, 671, 30);
		signup_pnl.add(lblshowkindinfo);
		
		createbtnSignup = new JButton("Create");
		createbtnSignup.addActionListener(this);
		createbtnSignup.addMouseListener(this);
		createbtnSignup.setBorder(new BevelBorder(BevelBorder.RAISED, 
								  new Color(0, 255, 0), new Color(0, 255, 51), 
								  new Color(0, 255, 102), new Color(0, 255, 153)));
		createbtnSignup.setFocusable(false);
		createbtnSignup.setBackground(UIManager.getColor("Button.light"));
		createbtnSignup.setForeground(new Color(0, 0, 51));
		createbtnSignup.setFont(new Font("Tahoma", Font.BOLD, 30));
		createbtnSignup.setBounds(393, 604, 160, 62);
		signup_pnl.add(createbtnSignup);
		
		lblClickOnLogin = new JLabel("Click on 'Log In' if you already have an account ");
		lblClickOnLogin.setOpaque(true);
		lblClickOnLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblClickOnLogin.setForeground(Color.YELLOW);
		lblClickOnLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblClickOnLogin.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		lblClickOnLogin.setBackground(Color.BLUE);
		lblClickOnLogin.setBounds(214, 718, 507, 30);
		signup_pnl.add(lblClickOnLogin);
		
		waitlbl = new JLabel("...Wait...");
		waitlbl.setVisible(false);
		waitlbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		waitlbl.setHorizontalAlignment(SwingConstants.CENTER);
		waitlbl.setBounds(437, 668, 86, 30);
		signup_pnl.add(waitlbl);
		
		login_pnl = new JPanel();
		login_pnl.setVisible(false);
		login_pnl.setBackground(Color.BLUE);
		login_pnl.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		login_pnl.setBounds(318, 0, 918, 748);
		vma_frm.getContentPane().add(login_pnl);
		login_pnl.setLayout(null);
		
		waitlbl2 = new JLabel("...Wait...");
		waitlbl2.setForeground(Color.YELLOW);
		waitlbl2.setVisible(false);
		waitlbl2.setFont(new Font("Tahoma", Font.BOLD, 20));
		waitlbl2.setHorizontalAlignment(SwingConstants.CENTER);
		waitlbl2.setBounds(433, 546, 86, 30);
		login_pnl.add(waitlbl2);
		
		siieo_lbl = new JLabel("Sign In Into Existing One");
		siieo_lbl.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		siieo_lbl.setFont(new Font("Tahoma", Font.BOLD, 35));
		siieo_lbl.setForeground(new Color(255, 204, 0));
		siieo_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		siieo_lbl.setBounds(179, 144, 579, 84);
		login_pnl.add(siieo_lbl);
		
		unmlogin = new JLabel("Username  :");
		unmlogin.setOpaque(true);
		unmlogin.setBackground(Color.LIGHT_GRAY);
		unmlogin.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		unmlogin.setFont(new Font("Tahoma", Font.BOLD, 30));
		unmlogin.setForeground(new Color(0, 0, 0));
		unmlogin.setHorizontalAlignment(SwingConstants.CENTER);
		unmlogin.setBounds(79, 293, 195, 57);
		login_pnl.add(unmlogin);
		
		pwdlogin = new JLabel("Password :");
		pwdlogin.setOpaque(true);
		pwdlogin.setBackground(Color.LIGHT_GRAY);
		pwdlogin.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pwdlogin.setHorizontalAlignment(SwingConstants.CENTER);
		pwdlogin.setForeground(new Color(0, 0, 0));
		pwdlogin.setFont(new Font("Tahoma", Font.BOLD, 30));
		pwdlogin.setBounds(79, 390, 195, 57);
		login_pnl.add(pwdlogin);
		
		txtpwdlogin = new JPasswordField();
		txtpwdlogin.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		txtpwdlogin.setForeground(new Color(51, 255, 0));
		txtpwdlogin.setFont(new Font("Tahoma", Font.BOLD, 30));
		txtpwdlogin.setColumns(30);
		txtpwdlogin.setBounds(284, 390, 540, 57);
		login_pnl.add(txtpwdlogin);
		
		txtunmlogin = new JTextField();
		txtunmlogin.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		txtunmlogin.setForeground(new Color(51, 255, 0));
		txtunmlogin.setFont(new Font("Tahoma", Font.BOLD, 30));
		txtunmlogin.setBounds(284, 293, 540, 57);
		login_pnl.add(txtunmlogin);
		txtunmlogin.setColumns(40);
		
		letsgologinbtn = new JButton("Let's Go");
		letsgologinbtn.addActionListener(this);
		letsgologinbtn.addMouseListener(this);
		letsgologinbtn.setBorder(new BevelBorder(BevelBorder.RAISED, 
												 new Color(0, 255, 0), new Color(0, 255, 51), 
												 new Color(0, 255, 102), new Color(0, 255, 153)));
		letsgologinbtn.setFont(new Font("Tahoma", Font.BOLD, 30));
		letsgologinbtn.setBackground(UIManager.getColor("Button.light"));
		letsgologinbtn.setForeground(new Color(0, 0, 0));
		letsgologinbtn.setBounds(384, 486, 171, 57);
		login_pnl.add(letsgologinbtn);
		
		pag_pnl = new JPanel();
		pag_pnl.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		pag_pnl.setVisible(false);
		pag_pnl.setLayout(null);
		pag_pnl.setBackground(new Color(0, 255, 255));
		pag_pnl.setBounds(318, 0, 918, 748);
		vma_frm.getContentPane().add(pag_pnl);
		
		waitlbl3 = new JLabel("...Wait...");
		waitlbl3.setForeground(Color.YELLOW);
		waitlbl3.setVisible(false);
		waitlbl3.setFont(new Font("Tahoma", Font.BOLD, 20));
		waitlbl3.setHorizontalAlignment(SwingConstants.CENTER);
		waitlbl3.setBounds(418, 464, 86, 30);
		pag_pnl.add(waitlbl3);
		
		gias = new JLabel("Give It A Shot");
		gias.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		gias.setForeground(new Color(0, 0, 51));
		gias.setFont(new Font("Tahoma", Font.BOLD, 35));
		gias.setHorizontalAlignment(SwingConstants.CENTER);
		gias.setBounds(203, 115, 497, 88);
		pag_pnl.add(gias);
		
		pname = new JLabel("Player Name");
		pname.setOpaque(true);
		pname.setBackground(Color.LIGHT_GRAY);
		pname.setForeground(new Color(0, 0, 51));
		pname.setFont(new Font("Tahoma", Font.BOLD, 25));
		pname.setHorizontalAlignment(SwingConstants.CENTER);
		pname.setBounds(352, 244, 213, 51);
		pag_pnl.add(pname);
		
		txtnmpag = new JTextField();
		txtnmpag.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtnmpag.setForeground(new Color(51, 255, 0));
		txtnmpag.setFont(new Font("Tahoma", Font.BOLD, 25));
		txtnmpag.setBounds(235, 305, 431, 51);
		pag_pnl.add(txtnmpag);
		txtnmpag.setColumns(10);
		
		pagletsgobtn = new JButton("Let's Go");
		pagletsgobtn.addMouseListener(this);
		pagletsgobtn.addActionListener(this);
		pagletsgobtn.setForeground(new Color(0, 0, 51));
		pagletsgobtn.setFocusable(false);
		pagletsgobtn.setBorder(new BevelBorder(BevelBorder.LOWERED, 
											   new Color(0, 255, 0), new Color(0, 255, 51), 
											   new Color(0, 255, 102), new Color(0, 255, 153)));
		pagletsgobtn.setBackground(UIManager.getColor("Button.light"));
		pagletsgobtn.setFont(new Font("Tahoma", Font.BOLD, 30));
		pagletsgobtn.setBounds(379, 397, 155, 57);
		pag_pnl.add(pagletsgobtn);
		
		vma_frm.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==signupbtn) {
			signup_pnl.setVisible(true);
			login_pnl.setVisible(false);
			pag_pnl.setVisible(false);
		}
		if(e.getSource()==createbtnSignup) {
			sgunm=txtunmSignup.getText();
			sgpwd=txtpwdSignup.getText();
			sggm=txtgmailSignup.getText();
			sgag=txtagesignup.getText();
			if((sgunm.length()==0) || (sgpwd.length()==0) || 
			   (sggm.length()==0) || (sgag.length()==0)) {
				JOptionPane.showMessageDialog(signup_pnl, 
											  "Please fill all the fields", "Short Name", 
											   JOptionPane.ERROR_MESSAGE);
			}
			else if(sgpwd.length()<5) {
				JOptionPane.showMessageDialog(signup_pnl, 
											  "Password Length Must Be At Least 5 Characters", 
											  "Short Password", JOptionPane.ERROR_MESSAGE);
			}
			else {
				waitlbl.setVisible(true);
				datasignup();
				email=false;
				JOptionPane.showMessageDialog(signup_pnl, "Created Successfully");
				signup_pnl.setVisible(false);
				login_pnl.setVisible(true);
				txtunmSignup.setText(null);
				txtpwdSignup.setText(null);
				txtgmailSignup.setText(null);
				txtagesignup.setText(null);
				waitlbl.setVisible(false);
			}
		}
		
		if(e.getSource()==loginbtn) {
			signup_pnl.setVisible(false);
			login_pnl.setVisible(true);
			pag_pnl.setVisible(false);
		}
		if(e.getSource()==letsgologinbtn) {
			waitlbl2.setVisible(true);
			lognm=txtunmlogin.getText();
			logpwd=txtpwdlogin.getText();
			if(lognm.length()==0 || logpwd.length()==0) {
				JOptionPane.showMessageDialog(pag_pnl, "Please fill both of the fields", 
						                      "null field", JOptionPane.ERROR_MESSAGE);
			}
			else {
				ChopStick.t2=false;
				datalogin();
				try {
					set=new BufferedWriter(new FileWriter("settings.txt"));
					set.close();
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
				waitlbl2.setVisible(false);
			}
			
		}
		
		if(e.getSource()==pagbtn) {
			signup_pnl.setVisible(false);
			login_pnl.setVisible(false);
			pag_pnl.setVisible(true);
		}
		if(e.getSource()==pagletsgobtn) {
			waitlbl3.setVisible(true);
			pagname=txtnmpag.getText();
			if(pagname.length()==0) {
				JOptionPane.showMessageDialog(pag_pnl, "Field can not be empty", "Short Name", 
												JOptionPane.WARNING_MESSAGE);
			}
			else {
				datapag();
				pag=true;
				try {
					vma_frm.dispose();
					ChopStick cs=new ChopStick();
					cs.name.setText(pagname);
					cs.main_frm.setVisible(true);
					ChopStick.t2=false;
					set=new BufferedWriter(new FileWriter("settings.txt"));
					set.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				waitlbl3.setVisible(false);
			}
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==signupbtn) {
			signupbtn.setBackground(UIManager.getColor("Button.light"));
		}
		if(e.getSource()==loginbtn) {
			loginbtn.setBackground(UIManager.getColor("Button.light"));
		}
		if(e.getSource()==pagbtn) {
			pagbtn.setBackground(UIManager.getColor("Button.light"));
		}
		if(e.getSource()==createbtnSignup) {
			createbtnSignup.setBackground(Color.LIGHT_GRAY);
		}
		if(e.getSource()==letsgologinbtn) {
			letsgologinbtn.setBackground(Color.LIGHT_GRAY);
		}
		if(e.getSource()==pagletsgobtn) {
			pagletsgobtn.setBackground(Color.LIGHT_GRAY);
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource()==signupbtn) {
			signupbtn.setBackground(new Color(255, 153, 153));
		}
		if(e.getSource()==loginbtn) {
			loginbtn.setBackground(new Color(255, 153, 153));
		}
		if(e.getSource()==pagbtn) {
			pagbtn.setBackground(new Color(255, 153, 153));
		}
		if(e.getSource()==createbtnSignup) {
			createbtnSignup.setBackground(UIManager.getColor("Button.light"));
		}
		if(e.getSource()==letsgologinbtn) {
			letsgologinbtn.setBackground(UIManager.getColor("Button.light"));
		}
		if(e.getSource()==pagletsgobtn) {
			pagletsgobtn.setBackground(UIManager.getColor("Button.light"));
		}
		
	}
	
	
	
}

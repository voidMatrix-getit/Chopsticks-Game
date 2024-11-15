package sahu.chopstick_game;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import org.apache.commons.mail.EmailException;
import javax.swing.JToggleButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.UIManager;
import java.awt.SystemColor;

import java.sql.*;

@SuppressWarnings("unused")
public class Settings implements MouseListener,ActionListener,Runnable{
	
	private JFrame set_frm;
	
	private JLabel bg_lbl,
				   pwc_lbl_ch,
				   comc_lbl,
				   pvp_lbl_ch,
				   pvpc_lbl,
				   pwc_lbl_ch_1,
				   pwc_lbl_ch_2,
				   pwc_lbl_ch_3,
				   pwc_lbl_ch_4,
				   pla_pnl_lbl_c,
				   com_pnl_lbl_c,
				   pla1_pnl_lbl_c,
				   pla2_pnl_lbl_c,
				   bg_pnl,
				   bg_pnl_1,
				   bg_pnl_2,
				   audio;
				   
	private JButton	combgch_btn,
					cpvp_btn,
					btn_pla_pnl_ch,
					btn_com_pnl_ch,
					btn_pnl_pla1_ch,
					btn_pla2_pnl_ch,
					back,
					res_def,
					deletacc_btn;
	 
	private JToggleButton oo;
	
	public boolean bgpwc,bgpvp,bgpnlpla,bgpnlcom,bgpnlpla1,bgpnlpla2,del;
	public Color b1,b2,pl,cr,p1,p2;
	
	static final char c1[]={102,0,0},c2[]={30,144,255};
	
	BufferedWriter w=null;
	BufferedReader r=null;
	
	public Connection c;
	public Statement st;
	
	public void deleteac() throws Exception{
		del=true;
		while(del) {
			Thread.sleep(1000);
		}
		String url="jdbc:mysql://db4free.net:3306/stickchop?useSSL=false";
		String usr="voidmatrix";
		String s="voidmatrix2002";
		try {
			r=new BufferedReader(new FileReader("player.txt"));
			w=new BufferedWriter(new FileWriter("player.txt"));
			if(Void_matrix_account.pag) {
				w.flush();
				r.close();
				w.close();
			}
			else {
				Class.forName("com.mysql.cj.jdbc.Driver");
				c= DriverManager.getConnection(url,usr,s);
				st=c.createStatement();
				st.executeUpdate("delete from signup where uname='"+r.readLine()+"'");
				r.close();
				w.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(set_frm, 
					"So Sorry, some errors occurred\nCheck your Internet Connection\nErr: "+e.getMessage(),
					"Account wasn't deleted", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	@Override
	public void run() {
		try {
			deleteac();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Settings() {
		try {
			w=new BufferedWriter(new FileWriter("settings.txt"));
			r=new BufferedReader(new FileReader("settings.txt"));
			initialize();
			new Thread(this).start();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private void initialize() throws Exception{
		set_frm= new JFrame();
		set_frm.getContentPane().setFocusable(false);
		set_frm.getContentPane().setBackground(Color.LIGHT_GRAY);
		set_frm.setResizable(false);
		set_frm.setForeground(Color.BLACK);
		set_frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo_F.png")));
		set_frm.setBackground(Color.BLACK);
		set_frm.setFont(new Font("Dialog", Font.BOLD, 20));
		set_frm.setTitle("Settings");
		set_frm.setBounds(150, 20, 1250, 785);
		set_frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		set_frm.getContentPane().setLayout(null);
		
		bg_lbl = new JLabel("Background Color");
		bg_lbl.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		bg_lbl.setFont(new Font("Tahoma", Font.BOLD, 35));
		bg_lbl.setForeground(Color.BLACK);
		bg_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		bg_lbl.setBounds(385, 10, 343, 68);
		set_frm.getContentPane().add(bg_lbl);
		
		pwc_lbl_ch = new JLabel("While Playing Against Computer  :");
		pwc_lbl_ch.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		pwc_lbl_ch.setBackground(null);
		pwc_lbl_ch.setHorizontalAlignment(SwingConstants.CENTER);
		pwc_lbl_ch.setForeground(Color.BLACK);
		pwc_lbl_ch.setFont(new Font("Tahoma", Font.BOLD, 25));
		pwc_lbl_ch.setBounds(126, 90, 478, 41);
		set_frm.getContentPane().add(pwc_lbl_ch);
		
		pvp_lbl_ch = new JLabel("While Playing Against Player  :");
		pvp_lbl_ch.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		pvp_lbl_ch.setHorizontalAlignment(SwingConstants.CENTER);
		pvp_lbl_ch.setForeground(Color.BLACK);
		pvp_lbl_ch.setFont(new Font("Tahoma", Font.BOLD, 25));
		pvp_lbl_ch.setBackground((Color) null);
		pvp_lbl_ch.setBounds(126, 174, 478, 41);
		set_frm.getContentPane().add(pvp_lbl_ch);
		
		bg_pnl = new JLabel("Panel Background Color");
		bg_pnl.setHorizontalAlignment(SwingConstants.CENTER);
		bg_pnl.setForeground(Color.BLACK);
		bg_pnl.setFont(new Font("Tahoma", Font.BOLD, 35));
		bg_pnl.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		bg_pnl.setBounds(351, 266, 445, 68);
		set_frm.getContentPane().add(bg_pnl);
		
		bg_pnl_1 = new JLabel("While Playing Against Computer");
		bg_pnl_1.setHorizontalTextPosition(SwingConstants.CENTER);
		bg_pnl_1.setHorizontalAlignment(SwingConstants.CENTER);
		bg_pnl_1.setForeground(Color.BLACK);
		bg_pnl_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		bg_pnl_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bg_pnl_1.setBounds(52, 364, 496, 48);
		set_frm.getContentPane().add(bg_pnl_1);
		
		bg_pnl_2 = new JLabel("While Playing Against Player");
		bg_pnl_2.setHorizontalTextPosition(SwingConstants.CENTER);
		bg_pnl_2.setHorizontalAlignment(SwingConstants.CENTER);
		bg_pnl_2.setForeground(Color.BLACK);
		bg_pnl_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		bg_pnl_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bg_pnl_2.setBounds(666, 364, 470, 48);
		set_frm.getContentPane().add(bg_pnl_2);
		
		pwc_lbl_ch_1 = new JLabel("Player Panel  :");
		pwc_lbl_ch_1.setHorizontalAlignment(SwingConstants.CENTER);
		pwc_lbl_ch_1.setForeground(Color.BLACK);
		pwc_lbl_ch_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		pwc_lbl_ch_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		pwc_lbl_ch_1.setBackground((Color) null);
		pwc_lbl_ch_1.setBounds(52, 433, 226, 41);
		set_frm.getContentPane().add(pwc_lbl_ch_1);
		
		pwc_lbl_ch_2 = new JLabel("Computer Panel  :");
		pwc_lbl_ch_2.setHorizontalAlignment(SwingConstants.CENTER);
		pwc_lbl_ch_2.setForeground(Color.BLACK);
		pwc_lbl_ch_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		pwc_lbl_ch_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		pwc_lbl_ch_2.setBackground((Color) null);
		pwc_lbl_ch_2.setBounds(52, 500, 226, 41);
		set_frm.getContentPane().add(pwc_lbl_ch_2);
		
		pwc_lbl_ch_3 = new JLabel("Player1 Panel  :");
		pwc_lbl_ch_3.setHorizontalAlignment(SwingConstants.CENTER);
		pwc_lbl_ch_3.setForeground(Color.BLACK);
		pwc_lbl_ch_3.setFont(new Font("Tahoma", Font.BOLD, 25));
		pwc_lbl_ch_3.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		pwc_lbl_ch_3.setBackground((Color) null);
		pwc_lbl_ch_3.setBounds(666, 433, 219, 41);
		set_frm.getContentPane().add(pwc_lbl_ch_3);
		
		pwc_lbl_ch_4 = new JLabel("Player2 Panel  :");
		pwc_lbl_ch_4.setHorizontalAlignment(SwingConstants.CENTER);
		pwc_lbl_ch_4.setForeground(Color.BLACK);
		pwc_lbl_ch_4.setFont(new Font("Tahoma", Font.BOLD, 25));
		pwc_lbl_ch_4.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		pwc_lbl_ch_4.setBackground((Color) null);
		pwc_lbl_ch_4.setBounds(666, 500, 219, 41);
		set_frm.getContentPane().add(pwc_lbl_ch_4);
		
		comc_lbl = new JLabel();
		comc_lbl.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		comc_lbl.setOpaque(true);
		comc_lbl.setBounds(821, 83, 78, 48);
		set_frm.getContentPane().add(comc_lbl);
		
		pvpc_lbl = new JLabel();
		pvpc_lbl.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		pvpc_lbl.setOpaque(true);
		pvpc_lbl.setBounds(821, 166, 78, 49);
		set_frm.getContentPane().add(pvpc_lbl);
		
		pla_pnl_lbl_c = new JLabel();
		pla_pnl_lbl_c.setOpaque(true);
		pla_pnl_lbl_c.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		pla_pnl_lbl_c.setBounds(480, 433, 44, 41);
		set_frm.getContentPane().add(pla_pnl_lbl_c);
		
		com_pnl_lbl_c = new JLabel();
		com_pnl_lbl_c.setOpaque(true);
		com_pnl_lbl_c.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		com_pnl_lbl_c.setBounds(480, 500, 44, 41);
		set_frm.getContentPane().add(com_pnl_lbl_c);
		
		pla1_pnl_lbl_c = new JLabel();
		pla1_pnl_lbl_c.setOpaque(true);
		pla1_pnl_lbl_c.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		pla1_pnl_lbl_c.setBounds(1071, 433, 44, 41);
		set_frm.getContentPane().add(pla1_pnl_lbl_c);
		
		pla2_pnl_lbl_c = new JLabel();
		pla2_pnl_lbl_c.setOpaque(true);
		pla2_pnl_lbl_c.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		pla2_pnl_lbl_c.setBounds(1071, 500, 44, 41);
		set_frm.getContentPane().add(pla2_pnl_lbl_c);
		
		if(!r.ready()) {
			w.write(c1); w.write(c1);
			for(int i=0;i<4;i++) {
				w.write(c2);
			}
			w.close();
			r.close();
			comc_lbl.setBackground(new Color(102, 0, 0));
			pvpc_lbl.setBackground(new Color(102, 0, 0));
			pla_pnl_lbl_c.setBackground(new Color(30,144,255));
			com_pnl_lbl_c.setBackground(new Color(30,144,255));
			pla1_pnl_lbl_c.setBackground(new Color(30,144,255));
			pla2_pnl_lbl_c.setBackground(new Color(30,144,255));
		}
		else {
			w.close();
			comc_lbl.setBackground(new Color(r.read(), r.read(), r.read()));
			pvpc_lbl.setBackground(new Color(r.read(), r.read(), r.read()));
			pla_pnl_lbl_c.setBackground(new Color(r.read(),r.read(),r.read()));
			com_pnl_lbl_c.setBackground(new Color(r.read(),r.read(),r.read()));
			pla1_pnl_lbl_c.setBackground(new Color(r.read(),r.read(),r.read()));
			pla2_pnl_lbl_c.setBackground(new Color(r.read(),r.read(),r.read()));
			r.close();
		}
		
		res_def = new JButton("Restore Defaults");
		res_def.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		res_def.addActionListener(this);
		res_def.addMouseListener(this);
		res_def.setForeground(new Color(0, 0, 51));
		res_def.setFont(new Font("Tahoma", Font.BOLD, 30));
		res_def.setFocusable(false);
		res_def.setBackground(UIManager.getColor("Button.light"));
		res_def.setBounds(433, 690, 296, 48);
		set_frm.getContentPane().add(res_def);
		
		audio = new JLabel("Sound  :");
		audio.setHorizontalAlignment(SwingConstants.CENTER);
		audio.setForeground(Color.BLACK);
		audio.setFont(new Font("Tahoma", Font.BOLD, 35));
		audio.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		audio.setBounds(408, 591, 211, 68);
		set_frm.getContentPane().add(audio);
		
		combgch_btn = new JButton("Choose");
		combgch_btn.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		combgch_btn.addActionListener(this);
		combgch_btn.addMouseListener(this);
		combgch_btn.setFocusable(false);
		combgch_btn.setForeground(new Color(0, 0, 51));
		combgch_btn.setBackground(UIManager.getColor("Button.light"));
		combgch_btn.setFont(new Font("Tahoma", Font.BOLD, 30));
		combgch_btn.setBounds(633, 88, 146, 41);
		set_frm.getContentPane().add(combgch_btn);
		
		cpvp_btn = new JButton("Choose");
		cpvp_btn.addActionListener(this);
		cpvp_btn.addMouseListener(this);
		cpvp_btn.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		cpvp_btn.setForeground(new Color(0, 0, 51));
		cpvp_btn.setFont(new Font("Tahoma", Font.BOLD, 30));
		cpvp_btn.setFocusable(false);
		cpvp_btn.setBackground(UIManager.getColor("Button.light"));
		cpvp_btn.setBounds(633, 172, 146, 41);
		set_frm.getContentPane().add(cpvp_btn);
		
		btn_pla_pnl_ch = new JButton("Choose");
		btn_pla_pnl_ch.addActionListener(this);
		btn_pla_pnl_ch.addMouseListener(this);
		btn_pla_pnl_ch.setForeground(new Color(0, 0, 51));
		btn_pla_pnl_ch.setFont(new Font("Tahoma", Font.BOLD, 30));
		btn_pla_pnl_ch.setFocusable(false);
		btn_pla_pnl_ch.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		btn_pla_pnl_ch.setBackground(UIManager.getColor("Button.light"));
		btn_pla_pnl_ch.setBounds(304, 431, 137, 41);
		set_frm.getContentPane().add(btn_pla_pnl_ch);
		
		btn_com_pnl_ch = new JButton("Choose");
		btn_com_pnl_ch.addActionListener(this);
		btn_com_pnl_ch.addMouseListener(this);
		btn_com_pnl_ch.setForeground(new Color(0, 0, 51));
		btn_com_pnl_ch.setFont(new Font("Tahoma", Font.BOLD, 30));
		btn_com_pnl_ch.setFocusable(false);
		btn_com_pnl_ch.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		btn_com_pnl_ch.setBackground(UIManager.getColor("Button.light"));
		btn_com_pnl_ch.setBounds(304, 500, 137, 41);
		set_frm.getContentPane().add(btn_com_pnl_ch);
		
		btn_pnl_pla1_ch = new JButton("Choose");
		btn_pnl_pla1_ch.addActionListener(this);
		btn_pnl_pla1_ch.addMouseListener(this);
		btn_pnl_pla1_ch.setForeground(new Color(0, 0, 51));
		btn_pnl_pla1_ch.setFont(new Font("Tahoma", Font.BOLD, 30));
		btn_pnl_pla1_ch.setFocusable(false);
		btn_pnl_pla1_ch.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		btn_pnl_pla1_ch.setBackground(UIManager.getColor("Button.light"));
		btn_pnl_pla1_ch.setBounds(907, 433, 137, 41);
		set_frm.getContentPane().add(btn_pnl_pla1_ch);
		
		btn_pla2_pnl_ch = new JButton("Choose");
		btn_pla2_pnl_ch.addActionListener(this);
		btn_pla2_pnl_ch.addMouseListener(this);
		btn_pla2_pnl_ch.setForeground(new Color(0, 0, 51));
		btn_pla2_pnl_ch.setFont(new Font("Tahoma", Font.BOLD, 30));
		btn_pla2_pnl_ch.setFocusable(false);
		btn_pla2_pnl_ch.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		btn_pla2_pnl_ch.setBackground(UIManager.getColor("Button.light"));
		btn_pla2_pnl_ch.setBounds(907, 498, 137, 41);
		set_frm.getContentPane().add(btn_pla2_pnl_ch);
		
		back = new JButton("Back");
		back.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		back.addActionListener(this);
		back.addMouseListener(this);
		back.setFocusable(false);
		back.setFont(new Font("Tahoma", Font.BOLD, 30));
		back.setForeground(new Color(0, 0, 51));
		back.setBackground(UIManager.getColor("Button.light"));
		back.setBounds(10, 690, 137, 48);
		set_frm.getContentPane().add(back);
		
		oo = new JToggleButton("On");
		oo.setBackground(UIManager.getColor("Button.light"));
		oo.setForeground(Color.black);
		oo.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		oo.setSelected(true);
		oo.setFocusable(false);
		oo.addMouseListener(this);
		oo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(oo.isSelected()) {
					oo.setText("On");
					ChopStick.c.start();
					ChopStick.c.loop(100);
					
				}
				else {
					oo.setText("Off");
					ChopStick.c.stop();
				}
			}
		});
		oo.setFont(new Font("Tahoma", Font.BOLD, 25));
		oo.setBounds(629, 591, 115, 68);
		set_frm.getContentPane().add(oo);
		
		deletacc_btn = new JButton("Delete This Account");
		deletacc_btn.setForeground(new Color(0, 0, 51));
		deletacc_btn.setFont(new Font("Tahoma", Font.BOLD, 30));
		deletacc_btn.setFocusable(false);
		deletacc_btn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		deletacc_btn.setBackground(SystemColor.controlHighlight);
		deletacc_btn.setBounds(907, 690, 319, 48);
		set_frm.getContentPane().add(deletacc_btn);
		
		set_frm.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==combgch_btn) {
			bgpwc=true;
			Color c=JColorChooser.showDialog(null, "Pick A Color", new Color(102,0,0));
			comc_lbl.setBackground(c);
		}
		
		if(e.getSource()==cpvp_btn) {
			bgpvp=true;
			Color c=JColorChooser.showDialog(null, "Pick A Color", new Color(102,0,0));
			pvpc_lbl.setBackground(c);
		}
		
		if(e.getSource()==btn_pla_pnl_ch) {
			bgpnlpla=true;
			Color c=JColorChooser.showDialog(null, "Pick A Color", new Color(30,144,255));
			pla_pnl_lbl_c.setBackground(c);
		}
		
		if(e.getSource()==btn_com_pnl_ch) {
			bgpnlcom=true;
			Color c=JColorChooser.showDialog(null, "Pick A Color", new Color(30,144,255));
			com_pnl_lbl_c.setBackground(c);
		}
		
		if(e.getSource()==btn_pnl_pla1_ch) {
			bgpnlpla1=true;
			Color c=JColorChooser.showDialog(null, "Pick A Color", new Color(30,144,255));
			pla1_pnl_lbl_c.setBackground(c);
		}
		
		if(e.getSource()==btn_pla2_pnl_ch) {
			bgpnlpla2=true;
			Color c=JColorChooser.showDialog(null, "Pick A Color", new Color(30,144,255));
			pla2_pnl_lbl_c.setBackground(c);
		}
		
		if(e.getSource()==back) {
			set_frm.dispose();
			try {
				new ChopStick().main_frm.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		if(e.getSource()==res_def) {
			bgpwc=false;
			bgpvp=false;
			bgpnlpla=false;
			bgpnlcom=false;
			bgpnlpla1=false;
			bgpnlpla2=false;
			comc_lbl.setBackground(new Color(102, 0, 0));
			pvpc_lbl.setBackground(new Color(102, 0, 0));
			pla_pnl_lbl_c.setBackground(new Color(30,144,255));
			com_pnl_lbl_c.setBackground(new Color(30,144,255));
			pla1_pnl_lbl_c.setBackground(new Color(30,144,255));
			pla2_pnl_lbl_c.setBackground(new Color(30,144,255));
		}
		
		try{
			w=new BufferedWriter(new FileWriter("settings.txt"));
			char C1[]={(char)comc_lbl.getBackground().getRed(),
					   (char)comc_lbl.getBackground().getGreen(),
					   (char)comc_lbl.getBackground().getBlue()};
			char C2[]={(char)pvpc_lbl.getBackground().getRed(),
					   (char)pvpc_lbl.getBackground().getGreen(),
					   (char)pvpc_lbl.getBackground().getBlue()};
			char C3[]={(char)pla_pnl_lbl_c.getBackground().getRed(),
					   (char)pla_pnl_lbl_c.getBackground().getGreen(),
					   (char)pla_pnl_lbl_c.getBackground().getBlue()};
			char C4[]={(char)com_pnl_lbl_c.getBackground().getRed(),
					   (char)com_pnl_lbl_c.getBackground().getGreen(),
					   (char)com_pnl_lbl_c.getBackground().getBlue()};
			char C5[]={(char)pla1_pnl_lbl_c.getBackground().getRed(),
					   (char)pla1_pnl_lbl_c.getBackground().getGreen(),
					   (char)pla1_pnl_lbl_c.getBackground().getBlue()};
			char C6[]={(char)pla2_pnl_lbl_c.getBackground().getRed(),
					   (char)pla2_pnl_lbl_c.getBackground().getGreen(),
					   (char)pla2_pnl_lbl_c.getBackground().getBlue()};
			w.write(C1);
			w.write(C2);
			w.write(C3);
			w.write(C4);
			w.write(C5);
			w.write(C6);
			w.close();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		
		if(e.getSource()==deletacc_btn) {
			del=false;
			JOptionPane.showConfirmDialog(set_frm, "Deleted Successfully");
			set_frm.dispose();
			new Void_matrix_account();
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
		if(e.getSource()==combgch_btn) {
			combgch_btn.setBackground(Color.black);
			combgch_btn.setForeground(Color.white);
		}
		
		if(e.getSource()==cpvp_btn) {
			cpvp_btn.setBackground(Color.black);
			cpvp_btn.setForeground(Color.white);
		}
		
		if(e.getSource()==btn_pla_pnl_ch) {
			btn_pla_pnl_ch.setBackground(Color.black);
			btn_pla_pnl_ch.setForeground(Color.white);
		}
		
		if(e.getSource()==btn_com_pnl_ch) {
			btn_com_pnl_ch.setBackground(Color.black);
			btn_com_pnl_ch.setForeground(Color.white);
		}
		
		if(e.getSource()==btn_pnl_pla1_ch) {
			btn_pnl_pla1_ch.setBackground(Color.black);
			btn_pnl_pla1_ch.setForeground(Color.white);
		}
		
		if(e.getSource()==btn_pla2_pnl_ch) {
			btn_pla2_pnl_ch.setBackground(Color.black);
			btn_pla2_pnl_ch.setForeground(Color.white);
		}
		
		if(e.getSource()==back) {
			back.setBackground(Color.black);
			back.setForeground(Color.white);
		}
		
		if(e.getSource()==res_def) {
			res_def.setBackground(Color.black);
			res_def.setForeground(Color.white);
		}
		
		if(e.getSource()==oo) {
			oo.setBackground(Color.black);
			oo.setForeground(Color.white);
		}
		
		if(e.getSource()==deletacc_btn) {
			deletacc_btn.setBackground(Color.black);
			deletacc_btn.setForeground(Color.white);
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		if(e.getSource()==combgch_btn) {
			combgch_btn.setBackground(UIManager.getColor("Button.light"));
			combgch_btn.setForeground(Color.black);
		}
		
		if(e.getSource()==cpvp_btn) {
			cpvp_btn.setBackground(UIManager.getColor("Button.light"));
			cpvp_btn.setForeground(Color.black);
		}
		
		if(e.getSource()==btn_pla_pnl_ch) {
			btn_pla_pnl_ch.setBackground(UIManager.getColor("Button.light"));
			btn_pla_pnl_ch.setForeground(Color.black);
		}
		
		if(e.getSource()==btn_com_pnl_ch) {
			btn_com_pnl_ch.setBackground(UIManager.getColor("Button.light"));
			btn_com_pnl_ch.setForeground(Color.black);
		}
		
		if(e.getSource()==btn_pnl_pla1_ch) {
			btn_pnl_pla1_ch.setBackground(UIManager.getColor("Button.light"));
			btn_pnl_pla1_ch.setForeground(Color.black);
		}
		
		if(e.getSource()==btn_pla2_pnl_ch) {
			btn_pla2_pnl_ch.setBackground(UIManager.getColor("Button.light"));
			btn_pla2_pnl_ch.setForeground(Color.black);
		}
		
		if(e.getSource()==back) {
			back.setBackground(UIManager.getColor("Button.light"));
			back.setForeground(Color.black);
		}
		
		if(e.getSource()==res_def) {
			res_def.setBackground(UIManager.getColor("Button.light"));
			res_def.setForeground(Color.black);
		}
		
		if(e.getSource()==oo) {
			oo.setBackground(UIManager.getColor("Button.light"));
			oo.setForeground(Color.black);
		}
		
		if(e.getSource()==deletacc_btn) {
			deletacc_btn.setBackground(UIManager.getColor("Button.light"));
			deletacc_btn.setForeground(Color.black);
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

}

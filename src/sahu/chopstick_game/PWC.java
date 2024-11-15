package sahu.chopstick_game;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.border.CompoundBorder;

import org.apache.commons.mail.EmailException;

import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;

import java.util.Random;
import java.util.Vector;

import javax.swing.JTextArea;

@SuppressWarnings("unused")
public class PWC implements ActionListener,MouseListener,Runnable{

	private JFrame pwc_frm;
	
	private JButton quit_btn,
					p_l_btn,
					p_r_btn,
					cp_r_btn,
					cp_l_btn,
					split_btn,
					attack_btn,
					done_btn;
	
	private JPanel l_pnl,
					r_pnl,
					main_pnl;
	
	private JLabel p_l_fist,
					p_l_five,
					p_r_fist,
					p_l_one,
					p_l_two,
					p_l_three,
					p_l_four,
					p_r_five,
					p_r_three,
					p_r_four,
					p_r_two,
					p_r_one,
					cp_r_fist,
					cp_r_five,
					cp_r_four,
					cp_r_three,
					cp_r_two,
					cp_r_one,
					cp_l_fist,
					cp_l_five,
					cp_l_four,
					cp_l_three,
					cp_l_one,
					cp_l_two,
					w_l;
	
	private JTextArea msg;

	Random r=new Random();
	
	private boolean running=true,turn,attack,split,p_l,p_r,t=true;
	
	private Vector<Integer> p_l_h=new Vector<Integer>(5),
					p_r_h=new Vector<Integer>(5),
					cp_l_h=new Vector<Integer>(5),
					cp_r_h=new Vector<Integer>(5);
	
	public int cp_ch,
				cp_ch_p_h,
				cp_ch_c_h,
				i,cp1,cp2,prpr,prpl,p,
				cph,rc,prcpl,prcpr,x,wcount,lcount;
	
	public Connection c;
	public Statement st;
	
	BufferedReader br=null;
	BufferedWriter bw=null;
	
	public void delay(int n) {
    	try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
	
	public PWC() throws Exception{
		br=new BufferedReader(new FileReader("settings.txt"));
		initialize();
		Thread t=new Thread(this);
		t.start();
	}
	
	@Override
	public void run() {
		delay(1000);
		try {
			Gameloop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private synchronized void initialize() throws Exception{
		pwc_frm = new JFrame();
		pwc_frm.setTitle("Playing With Computer");
		pwc_frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo_F.png")));
		pwc_frm.setResizable(false);
		pwc_frm.setBounds(150, 20, 1250, 785);
		pwc_frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pwc_frm.setUndecorated(true);
		pwc_frm.getContentPane().setLayout(null);
		
		quit_btn = new JButton("Quit");
		quit_btn.addActionListener(this);
		quit_btn.setForeground(new Color(51, 0, 153));
		quit_btn.setFont(new Font("Tahoma", Font.BOLD, 30));
		quit_btn.setBackground(new Color(255, 204, 0));
		quit_btn.setBounds(10, 721, 138, 56);
		quit_btn.setFocusable(false);
		quit_btn.addMouseListener(this);
		pwc_frm.getContentPane().add(quit_btn);
		
		r_pnl = new JPanel();
		r_pnl.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, 
												new Color(0, 0, 128), new Color(0, 0, 205), 
												new Color(0, 0, 139), new Color(0, 0, 255)), 
										   new BevelBorder(BevelBorder.LOWERED, 
												new Color(0, 0, 205), new Color(0, 0, 255), 
												new Color(0, 0, 128), new Color(0, 0, 139))));
		
		r_pnl.setBounds(670, 50, 516, 447);
		pwc_frm.getContentPane().add(r_pnl);
		r_pnl.setLayout(null);
		
		cp_r_btn = new JButton("Right");
		cp_r_btn.setForeground(new Color(0, 255, 0));
		cp_r_btn.addActionListener(this);
		cp_r_btn.setEnabled(false);
		cp_r_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		cp_r_btn.setBackground(new Color(0, 0, 204));
		cp_r_btn.setBounds(319, 100, 114, 45);
		cp_r_btn.setFocusable(false);
		cp_r_btn.addMouseListener(this);
		r_pnl.add(cp_r_btn);
		
		cp_l_btn = new JButton("Left");
		cp_l_btn.setForeground(new Color(0, 255, 0));
		cp_l_btn.addActionListener(this);
		cp_l_btn.setEnabled(false);
		cp_l_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		cp_l_btn.setBackground(new Color(51, 51, 153));
		cp_l_btn.setBounds(319, 291, 114, 45);
		cp_l_btn.setFocusable(false);
		cp_l_btn.addMouseListener(this);
		r_pnl.add(cp_l_btn);
		
		cp_r_fist = new JLabel("");
		cp_r_fist.setIcon(new ImageIcon(getClass().getResource("cp_r_fist.png")));
		cp_r_fist.setHorizontalAlignment(SwingConstants.CENTER);
		cp_r_fist.setBounds(105, 61, 130, 128);
		cp_r_fist.setVisible(false);
		r_pnl.add(cp_r_fist);
		
		cp_r_five = new JLabel("");
		cp_r_five.setIcon(new ImageIcon(getClass().getResource("cp_r_five.png")));
		cp_r_five.setHorizontalAlignment(SwingConstants.CENTER);
		cp_r_five.setBounds(105, 61, 130, 128);
		cp_r_five.setVisible(false);
		r_pnl.add(cp_r_five);
		
		cp_r_four = new JLabel("");
		cp_r_four.setIcon(new ImageIcon(getClass().getResource("cp_r_four.png")));
		cp_r_four.setHorizontalAlignment(SwingConstants.CENTER);
		cp_r_four.setBounds(105, 61, 130, 128);
		cp_r_four.setVisible(false);
		r_pnl.add(cp_r_four);
		
		cp_r_three = new JLabel("");
		cp_r_three.setIcon(new ImageIcon(getClass().getResource("cp_r_three.png")));
		cp_r_three.setHorizontalAlignment(SwingConstants.CENTER);
		cp_r_three.setBounds(105, 61, 130, 128);
		cp_r_three.setVisible(false);
		r_pnl.add(cp_r_three);
		
		cp_r_two = new JLabel("");
		cp_r_two.setIcon(new ImageIcon(getClass().getResource("cp_r_two.png")));
		cp_r_two.setHorizontalAlignment(SwingConstants.CENTER);
		cp_r_two.setBounds(105, 61, 130, 128);
		cp_r_two.setVisible(false);
		r_pnl.add(cp_r_two);
		
		cp_r_one = new JLabel("");
		cp_r_one.setIcon(new ImageIcon(getClass().getResource("cp_r_one.png")));
		cp_r_one.setHorizontalAlignment(SwingConstants.CENTER);
		cp_r_one.setBounds(105, 61, 130, 128);
		r_pnl.add(cp_r_one);
		
		cp_l_fist = new JLabel("");
		cp_l_fist.setIcon(new ImageIcon(getClass().getResource("cp_l_fist.png")));
		cp_l_fist.setHorizontalAlignment(SwingConstants.CENTER);
		cp_l_fist.setBounds(105, 252, 130, 128);
		cp_l_fist.setVisible(false);
		r_pnl.add(cp_l_fist);
		
		cp_l_five = new JLabel("");
		cp_l_five.setIcon(new ImageIcon(getClass().getResource("cp_l_five.png")));
		cp_l_five.setHorizontalAlignment(SwingConstants.CENTER);
		cp_l_five.setBounds(105, 252, 130, 128);
		cp_l_five.setVisible(false);
		r_pnl.add(cp_l_five);
		
		cp_l_four = new JLabel("");
		cp_l_four.setIcon(new ImageIcon(getClass().getResource("cp_l_four.png")));
		cp_l_four.setHorizontalAlignment(SwingConstants.CENTER);
		cp_l_four.setBounds(105, 252, 130, 128);
		cp_l_four.setVisible(false);
		r_pnl.add(cp_l_four);
		
		cp_l_three = new JLabel("");
		cp_l_three.setIcon(new ImageIcon(getClass().getResource("cp_l_three.png")));
		cp_l_three.setHorizontalAlignment(SwingConstants.CENTER);
		cp_l_three.setBounds(105, 252, 130, 128);
		cp_l_three.setVisible(false);
		r_pnl.add(cp_l_three);
		
		cp_l_two = new JLabel("");
		cp_l_two.setIcon(new ImageIcon(getClass().getResource("cp_l_two.png")));
		cp_l_two.setHorizontalAlignment(SwingConstants.CENTER);
		cp_l_two.setBounds(105, 252, 130, 128);
		cp_l_two.setVisible(false);
		r_pnl.add(cp_l_two);
		
		cp_l_one = new JLabel("");
		cp_l_one.setIcon(new ImageIcon(getClass().getResource("cp_l_one.png")));
		cp_l_one.setHorizontalAlignment(SwingConstants.CENTER);
		cp_l_one.setBounds(105, 252, 130, 128);
		r_pnl.add(cp_l_one);
		
		l_pnl = new JPanel();
		l_pnl.setLayout(null);
		l_pnl.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, 
												new Color(0, 0, 128), new Color(0, 0, 205), 
												new Color(0, 0, 139), new Color(0, 0, 255)), 
										   new BevelBorder(BevelBorder.LOWERED, 
												new Color(0, 0, 205), new Color(0, 0, 255), 
												new Color(0, 0, 128), new Color(0, 0, 139))));
		l_pnl.setBounds(50, 50, 516, 447);
		pwc_frm.getContentPane().add(l_pnl);
		
		p_l_btn = new JButton("Left");
		p_l_btn.setForeground(new Color(0, 255, 0));
		p_l_btn.addActionListener(this);
		p_l_btn.setEnabled(false);
		p_l_btn.setBackground(new Color(51, 51, 153));
		p_l_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		p_l_btn.setBounds(85, 100, 114, 45);
		p_l_btn.setFocusable(false);
		p_l_btn.addMouseListener(this);
		l_pnl.add(p_l_btn);
		
		p_r_btn = new JButton("Right");
		p_r_btn.setForeground(new Color(0, 255, 0));
		p_r_btn.addActionListener(this);
		p_r_btn.setEnabled(false);
		p_r_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		p_r_btn.setBackground(new Color(0, 0, 204));
		p_r_btn.setBounds(85, 291, 114, 45);
		p_r_btn.setFocusable(false);
		p_r_btn.addMouseListener(this);
		l_pnl.add(p_r_btn);
		
		p_l_fist = new JLabel("");
		p_l_fist.setIcon(new ImageIcon(getClass().getResource("p_l_fist.png")));
		p_l_fist.setHorizontalAlignment(SwingConstants.CENTER);
		p_l_fist.setBounds(299, 61, 130, 128);
		p_l_fist.setVisible(false);
		l_pnl.add(p_l_fist);
		
		p_l_five = new JLabel("");
		p_l_five.setIcon(new ImageIcon(getClass().getResource("p_l_five.png")));
		p_l_five.setHorizontalAlignment(SwingConstants.CENTER);
		p_l_five.setBounds(299, 61, 130, 128);
		p_l_five.setVisible(false);
		l_pnl.add(p_l_five);
		
		p_l_four = new JLabel("");
		p_l_four.setIcon(new ImageIcon(getClass().getResource("p_l_four.png")));
		p_l_four.setHorizontalAlignment(SwingConstants.CENTER);
		p_l_four.setBounds(299, 61, 130, 128);
		p_l_four.setVisible(false);
		l_pnl.add(p_l_four);
		
		p_l_three = new JLabel("");
		p_l_three.setIcon(new ImageIcon(getClass().getResource("p_l_three.png")));
		p_l_three.setHorizontalAlignment(SwingConstants.CENTER);
		p_l_three.setBounds(299, 61, 130, 128);
		p_l_three.setVisible(false);
		l_pnl.add(p_l_three);
		
		p_l_two = new JLabel("");
		p_l_two.setIcon(new ImageIcon(getClass().getResource("p_l_two.png")));
		p_l_two.setHorizontalAlignment(SwingConstants.CENTER);
		p_l_two.setBounds(299, 61, 130, 128);
		p_l_two.setVisible(false);
		l_pnl.add(p_l_two);
		
		p_l_one = new JLabel("");
		p_l_one.setIcon(new ImageIcon(getClass().getResource("p_l_one.png")));
		p_l_one.setHorizontalAlignment(SwingConstants.CENTER);
		p_l_one.setBounds(299, 61, 130, 128);
		l_pnl.add(p_l_one);
		
		p_r_fist = new JLabel("");
		p_r_fist.setIcon(new ImageIcon(getClass().getResource("p_r_fist.png")));
		p_r_fist.setHorizontalAlignment(SwingConstants.CENTER);
		p_r_fist.setBounds(299, 252, 130, 128);
		p_r_fist.setVisible(false);
		l_pnl.add(p_r_fist);
		
		p_r_five = new JLabel("");
		p_r_five.setIcon(new ImageIcon(getClass().getResource("p_r_five.png")));
		p_r_five.setHorizontalAlignment(SwingConstants.CENTER);
		p_r_five.setBounds(299, 252, 130, 128);
		p_r_five.setVisible(false);
		l_pnl.add(p_r_five);
		
		p_r_four = new JLabel("");
		p_r_four.setIcon(new ImageIcon(getClass().getResource("p_r_four.png")));
		p_r_four.setHorizontalAlignment(SwingConstants.CENTER);
		p_r_four.setBounds(299, 252, 130, 128);
		p_r_four.setVisible(false);
		l_pnl.add(p_r_four);
		
		p_r_three = new JLabel("");
		p_r_three.setIcon(new ImageIcon(getClass().getResource("p_r_three.png")));
		p_r_three.setHorizontalAlignment(SwingConstants.CENTER);
		p_r_three.setBounds(299, 252, 130, 128);
		p_r_three.setVisible(false);
		l_pnl.add(p_r_three);
		
		p_r_two = new JLabel("");
		p_r_two.setIcon(new ImageIcon(getClass().getResource("p_r_two.png")));
		p_r_two.setHorizontalAlignment(SwingConstants.CENTER);
		p_r_two.setBounds(299, 252, 130, 128);
		p_r_two.setVisible(false);
		l_pnl.add(p_r_two);
		
		p_r_one = new JLabel("");
		p_r_one.setIcon(new ImageIcon(getClass().getResource("p_r_one.png")));
		p_r_one.setHorizontalAlignment(SwingConstants.CENTER);
		p_r_one.setBounds(299, 252, 130, 128);
		l_pnl.add(p_r_one);
		
		JLabel player_lbl = new JLabel("Player");
		player_lbl.setForeground(new Color(255, 215, 0));
		player_lbl.setFont(new Font("Tahoma", Font.BOLD, 35));
		player_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		player_lbl.setBounds(197, 0, 182, 50);
		pwc_frm.getContentPane().add(player_lbl);
		
		JLabel player_lbl_1 = new JLabel("Computer");
		player_lbl_1.setHorizontalAlignment(SwingConstants.CENTER);
		player_lbl_1.setForeground(new Color(255, 215, 0));
		player_lbl_1.setFont(new Font("Tahoma", Font.BOLD, 35));
		player_lbl_1.setBounds(848, 0, 182, 50);
		pwc_frm.getContentPane().add(player_lbl_1);
		
		JLabel vs_lbl = new JLabel("Vs");
		vs_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		vs_lbl.setForeground(new Color(255, 215, 0));
		vs_lbl.setFont(new Font("Tahoma", Font.BOLD, 30));
		vs_lbl.setBounds(576, 261, 84, 40);
		pwc_frm.getContentPane().add(vs_lbl);
		
		main_pnl = new JPanel();
		main_pnl.setLayout(null);
		main_pnl.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, 
													new Color(0, 0, 128), new Color(0, 0, 205), 
													new Color(0, 0, 139), new Color(0, 0, 255)), 
											  new BevelBorder(BevelBorder.LOWERED, 
													new Color(0, 0, 205), new Color(0, 0, 255), 
													new Color(0, 0, 128), new Color(0, 0, 139))));
		main_pnl.setBackground(new Color(30, 144, 255));
		main_pnl.setBounds(50, 521, 1136, 183);
		pwc_frm.getContentPane().add(main_pnl);
		
		msg = new JTextArea("Game Started");
		msg.setWrapStyleWord(true);
		msg.setForeground(Color.YELLOW);
		msg.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 32));
		msg.setBounds(10, 10, 1116, 163);
		msg.setVisible(true);
		msg.setEditable(false);
		msg.setOpaque(false);
		main_pnl.add(msg);
		
		attack_btn = new JButton("Attack");
		attack_btn.setBackground(new Color(51, 102, 102));
		attack_btn.setFocusable(false);
		attack_btn.addActionListener(this);
		attack_btn.setForeground(new Color(255, 255, 51));
		attack_btn.setFont(new Font("Tahoma", Font.BOLD, 30));
		attack_btn.setBounds(675, 90, 157, 59);
		attack_btn.setVisible(false);
		attack_btn.addMouseListener(this);
		msg.add(attack_btn);
		
		split_btn = new JButton("Split");
		split_btn.setBackground(new Color(51, 102, 102));
		split_btn.setFocusable(false);
		split_btn.addActionListener(this);
		split_btn.setFont(new Font("Tahoma", Font.BOLD, 30));
		split_btn.setForeground(new Color(255, 255, 51));
		split_btn.setBounds(291, 90, 157, 59);
		split_btn.setVisible(false);
		split_btn.addMouseListener(this);
		msg.add(split_btn);
		
		done_btn = new JButton("Done");
		done_btn.setForeground(new Color(255, 255, 51));
		done_btn.setBackground(new Color(51, 102, 102));
		done_btn.addActionListener(this);
		done_btn.setFont(new Font("Tahoma", Font.BOLD, 30));
		done_btn.setBounds(493, 90, 139, 59);
		done_btn.setFocusable(false);
		done_btn.setVisible(false);
		done_btn.addMouseListener(this);
		msg.add(done_btn);
		
		w_l = new JLabel("");
		w_l.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 45));
		w_l.setOpaque(true);
		w_l.setHorizontalAlignment(SwingConstants.CENTER);
		w_l.setForeground(Color.YELLOW);
		w_l.setBackground(Color.BLACK);
		w_l.setBounds(382, 10, 371, 86);
		w_l.setVisible(false);
		main_pnl.add(w_l);
		
		JPanel player_pnl = new JPanel();
		player_pnl.setBounds(208, 0, 161, 50);
		pwc_frm.getContentPane().add(player_pnl);
		player_pnl.setLayout(null);
		player_pnl.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, 
													new Color(0, 0, 128), new Color(0, 0, 205), 
													new Color(0, 0, 139), new Color(0, 0, 255)), 
											    new BevelBorder(BevelBorder.LOWERED, 
											        new Color(0, 0, 205), new Color(0, 0, 255), 
											        new Color(0, 0, 128), new Color(0, 0, 139))));
		player_pnl.setBackground(new Color(30, 144, 255));
		
		JPanel com_pnl = new JPanel();
		com_pnl.setLayout(null);
		com_pnl.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, 
												new Color(0, 0, 128), new Color(0, 0, 205), 
												new Color(0, 0, 139), new Color(0, 0, 255)), 
											 new BevelBorder(BevelBorder.LOWERED, 
												new Color(0, 0, 205), new Color(0, 0, 255), 
												new Color(0, 0, 128), new Color(0, 0, 139))));
		com_pnl.setBackground(new Color(30, 144, 255));
		com_pnl.setBounds(834, 0, 208, 50);
		pwc_frm.getContentPane().add(com_pnl);
		
		JPanel vs_pnl = new JPanel();
		vs_pnl.setLayout(null);
		vs_pnl.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, 
												new Color(0, 0, 128), new Color(0, 0, 205), 
												new Color(0, 0, 139), new Color(0, 0, 255)), 
											new BevelBorder(BevelBorder.LOWERED, 
												new Color(0, 0, 205), new Color(0, 0, 255), 
												new Color(0, 0, 128), new Color(0, 0, 139))));
		vs_pnl.setBackground(new Color(30, 144, 255));
		vs_pnl.setBounds(564, 261, 108, 40);
		pwc_frm.getContentPane().add(vs_pnl);
		
		if(!br.ready()) {
			pwc_frm.getContentPane().setBackground(new Color(102, 0, 0));
			l_pnl.setBackground(new Color(30, 144, 255));
			r_pnl.setBackground(new Color(30, 144, 255));
			br.close();
		}
		else {
			pwc_frm.getContentPane().setBackground(new Color(br.read(), br.read(), br.read()));
			br.skip(3);
			l_pnl.setBackground(new Color(br.read(), br.read(), br.read()));
			r_pnl.setBackground(new Color(br.read(), br.read(), br.read()));
			br.close();
		}
		pwc_frm.setVisible(true);
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
		
		if(e.getSource()==quit_btn) {
			quit_btn.setBackground(Color.black);
		}
		if(e.getSource()==split_btn) {
			split_btn.setBackground(Color.black);
		}
		if(e.getSource()==attack_btn) {
			attack_btn.setBackground(Color.black);
		}
		if(e.getSource()==done_btn) {
			done_btn.setBackground(Color.black);
		}
		if(e.getSource()==p_l_btn) {
			p_l_btn.setBackground(Color.black);
		}
		if(e.getSource()==p_r_btn) {
			p_r_btn.setBackground(Color.black);
		}
		if(e.getSource()==cp_l_btn) {
			cp_l_btn.setBackground(Color.black);
		}
		if(e.getSource()==cp_r_btn) {
			cp_r_btn.setBackground(Color.black);
		}
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		
		if(e.getSource()==quit_btn) {
			quit_btn.setBackground(new Color(255, 204, 0));
		}
		if(e.getSource()==split_btn) {
			split_btn.setBackground(new Color(51, 102, 102));
		}
		if(e.getSource()==attack_btn) {
			attack_btn.setBackground(new Color(51, 102, 102));
		}
		if(e.getSource()==done_btn) {
			done_btn.setBackground(new Color(51, 102, 102));
		}
		if(e.getSource()==p_l_btn) {
			p_l_btn.setBackground(new Color(51, 51, 153));
		}
		if(e.getSource()==p_r_btn) {
			p_r_btn.setBackground(new Color(0, 0, 204));
		}
		if(e.getSource()==cp_l_btn) {
			cp_l_btn.setBackground(new Color(51, 51, 153));
		}
		if(e.getSource()==cp_r_btn) {
			cp_r_btn.setBackground(new Color(0, 0, 204));
		}
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==quit_btn) {
				int Q=JOptionPane.showConfirmDialog(pwc_frm, "Sure want to quit?", "Quit", 
												JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(Q==0) {
					pwc_frm.dispose();
					try {
						new ChopStick().main_frm.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
			
			if(e.getSource()==split_btn) {
				split=true;
				p_r_btn.setEnabled(true);
				p_l_btn.setEnabled(true);
				cp_l_btn.setEnabled(false);
				cp_r_btn.setEnabled(false);
				attack_btn.setVisible(false);
				split_btn.setVisible(false);
				msg.setText("Click on your either of the hand-buttons[right or left]\n"
						+ "For splitting the fingers by one,\n"
						+ "(One finger will be increased on the clicked hand-button,\n"
						+ "hence it will decrease one finger on the opposite)");
				prpr=p_l_h.size();
				prpl=p_r_h.size();
				p=prpr+prpl;
			}
			
			if(e.getSource()==attack_btn) {
				attack=true;
				if(p_l_h.size()==0) {
					p_l_btn.setEnabled(false);
					p_r_btn.setEnabled(true);
				}
				else if(p_r_h.size()==0) {
					p_r_btn.setEnabled(false);
					p_l_btn.setEnabled(true);
				}
				else {
					p_r_btn.setEnabled(true);
					p_l_btn.setEnabled(true);
				}
				attack_btn.setVisible(false);
				split_btn.setVisible(false);
				msg.setText("First click on either of your hand-buttons[right or left]\n"
							+ "and then computer's for the attack");
			}
			
			
			if(e.getSource()==p_l_btn&&split) {
				msg.setText(".....Splitting.....");
				
				if(p_l_h.size()>=4)
					JOptionPane.showMessageDialog(l_pnl, 
							"You can't kill your own hand!,\nIn splitting decrement of fingers isn't allowed", 
							"Fingers' Decrement", JOptionPane.WARNING_MESSAGE);
				else if(p_r_h.size()==0) {
					JOptionPane.showMessageDialog(l_pnl, 
											"No fingers left to transfer\nYou got a fist on right hand", 
											"No fingers", JOptionPane.WARNING_MESSAGE);
				}
				else {
					p_l_h.add(1);
					p_r_h.remove(p_r_h.lastElement());
					if(p_l_h.size()==0) {
				    	p_l_0();
				    }
				    else if(p_l_h.size()==1) {
				    	p_l_1();
				    }
				    else if(p_l_h.size()==2) {
				    	p_l_2();
				    }
				    else if(p_l_h.size()==3) { 
				    	p_l_3();
				    }
				    else if(p_l_h.size()==4) { 
				    	p_l_4();
				    }
					
					if(p_r_h.size()==0) {
						p_r_0();
				    }
				    else if(p_r_h.size()==1) {
				    	p_r_1();
				    }
				    else if(p_r_h.size()==2) {
				    	p_r_2();
				    }
				    else if(p_r_h.size()==3) { 
				    	p_r_3();
				    }
				    else if(p_r_h.size()==4) { 
				    	p_r_4();
				    }
				}
				done_btn.setVisible(true);
			}
			if(e.getSource()==p_r_btn&&split) {
				msg.setText(".....Splitting.....");
				
				if(p_r_h.size()>=4)
					JOptionPane.showMessageDialog(l_pnl, 
							"You can't kill your own hand!,\nIn splitting decrement of fingers isn't allowed", 
							"Fingers' Decrement", JOptionPane.WARNING_MESSAGE);
				else if(p_l_h.size()==0) {
					JOptionPane.showMessageDialog(l_pnl, 
							"you already got a fist on left hand!", "Fist on left",  
							JOptionPane.WARNING_MESSAGE);
				}
				else {
					p_r_h.add(1);
					p_l_h.remove(p_l_h.lastElement());
					
					if(p_r_h.size()==0) {
						p_r_0();
				    }
				    else if(p_r_h.size()==1) {
				    	p_r_1();
				    }
				    else if(p_r_h.size()==2) {
				    	p_r_2();
				    }
				    else if(p_r_h.size()==3) { 
				    	p_r_3();
				    }
				    else if(p_r_h.size()==4) { 
				    	p_r_4();
				    }
					
					if(p_l_h.size()==0) {
				    	p_l_0();
				    }
				    else if(p_l_h.size()==1) {
				    	p_l_1();
				    }
				    else if(p_l_h.size()==2) {
				    	p_l_2();
				    }
				    else if(p_l_h.size()==3) { 
				    	p_l_3();
				    }
				    else if(p_l_h.size()==4) { 
				    	p_l_4();
				    }
					
					
				}
				done_btn.setVisible(true);
			}
			
			if(e.getSource()==done_btn) {
				if(p_l_h.size()==prpr&&p_r_h.size()==prpl || p_l_h.size()==prpl&&p_r_h.size()==prpr) {
					JOptionPane.showMessageDialog(l_pnl, "Splitting is same as previos State!", 
												  "Identical states", JOptionPane.WARNING_MESSAGE);
				}
				else {
					p_r_btn.setEnabled(false);
					p_l_btn.setEnabled(false);
					cp_r_btn.setEnabled(false);
					cp_l_btn.setEnabled(false);
					done_btn.setVisible(false);
					split=false;
					t=false;
				}
					
			}
			
			
			if(e.getSource()==p_l_btn&&attack) {
				if(cp_r_h.size()==0) {
					cp_r_btn.setEnabled(false);
					cp_l_btn.setEnabled(true);
				}
				else if(cp_l_h.size()==0) {
					cp_l_btn.setEnabled(false);
					cp_r_btn.setEnabled(true);
				}
				else {
					cp_r_btn.setEnabled(true);
					cp_l_btn.setEnabled(true);
				}
				msg.setText(".....Your left hand.....");
				p_l=true;
				p_r=false;
			}
			if(e.getSource()==p_r_btn&&attack) {
				if(cp_r_h.size()==0) {
					cp_r_btn.setEnabled(false);
					cp_l_btn.setEnabled(true);
				}
				else if(cp_l_h.size()==0) {
					cp_l_btn.setEnabled(false);
					cp_r_btn.setEnabled(true);
				}
				else {
					cp_r_btn.setEnabled(true);
					cp_l_btn.setEnabled(true);
				}
				msg.setText(".....Your right hand.....");
				p_r=true;
				p_l=false;
			}
			
			if(e.getSource()==cp_l_btn&&p_l) {
				msg.setText(".....Your left hand -> Computer's left hand.....");
				
				p_l_btn.setEnabled(false);
				p_r_btn.setEnabled(false);
				cp_l_btn.setEnabled(false);
				cp_r_btn.setEnabled(false);
				
				x=p_l_h.size();
			    for(i=0;i<x;i++) {
			    	if(cp_l_h.size()==5)
			    		cp_l_h.removeAllElements();
			    	cp_l_h.add(1);
			    }
			    if(cp_l_h.size()==5) {
		    		cp_l_h.removeAllElements();
			    }
				
				t=false;
			}
			if(e.getSource()==cp_r_btn&&p_l) {
				msg.setText(".....Your left hand -> Computer's right hand.....");
				
				p_l_btn.setEnabled(false);
				p_r_btn.setEnabled(false);
				cp_l_btn.setEnabled(false);
				cp_r_btn.setEnabled(false);
				
				x=p_l_h.size();
			    for(i=0;i<x;i++) {
			    	if(cp_r_h.size()==5)
			    		cp_r_h.removeAllElements();
			    	cp_r_h.add(1);
			    }
			    if(cp_r_h.size()==5) {
		    		cp_r_h.removeAllElements();
			    }
				
				t=false;
			}
			if(e.getSource()==cp_l_btn&&p_r) {
				msg.setText(".....Your right hand -> Computer's left hand.....");
				
				p_l_btn.setEnabled(false);
				p_r_btn.setEnabled(false);
				cp_l_btn.setEnabled(false);
				cp_r_btn.setEnabled(false);
				
				x=p_r_h.size();
			    for(i=0;i<x;i++) {
			    	if(cp_l_h.size()==5)
			    		cp_l_h.removeAllElements();
			    	cp_l_h.add(1);
			    }
			    if(cp_l_h.size()==5) {
		    		cp_l_h.removeAllElements();
			    }
				
				t=false;
			}
			if(e.getSource()==cp_r_btn&&p_r) {
				msg.setText(".....Your right hand -> Computer's right hand.....");
				
				p_l_btn.setEnabled(false);
				p_r_btn.setEnabled(false);
				cp_l_btn.setEnabled(false);
				cp_r_btn.setEnabled(false);
				
				x=p_r_h.size();
			    for(i=0;i<x;i++) {
			    	if(cp_r_h.size()==5)
			    		cp_r_h.removeAllElements();
			    	cp_r_h.add(1);
			    }
			    if(cp_r_h.size()==5) {
		    		cp_r_h.removeAllElements();
			    }
			    
				t=false;
			}
		
	}
	
	public void loading() {
		String s=".";
		for(int i=0;i<5;i++) {
			msg.setText(s);
			s+=".";
			delay(150);
		}
	}
	
	public synchronized void Gameloop() throws InterruptedException {
		p_l_h.add(1);
		p_r_h.add(1);
		cp_l_h.add(1);
		cp_r_h.add(1);
			delay(1000);
			loading();
			msg.setText("...Randomly Choosing the turn!...");
			delay(2000);
			loading();
			if(r.nextInt(2)==1) {
				msg.setText("Computer's turn");
				delay(1000);
				turn=true;
			}
			else {
				msg.setText("Player's turn");
				delay(1000);
				turn=false;
			}
			
			while(running) {
				if(cp_l_h.isEmpty() && cp_r_h.isEmpty()) {
					pla_won();
				}
				else if(p_l_h.isEmpty() && p_r_h.isEmpty())	{
					com_won();
				}
				else {
					if(turn) {
						Com_turn();
						if(cp_l_h.isEmpty() && cp_r_h.isEmpty()) {
							pla_won();
						}
						else if(p_l_h.isEmpty() && p_r_h.isEmpty())	{
							com_won();
						}
						else {
							turn=false;
						}
						repaint();
					}
					else {
						Pla_turn();
						while(t) {
							delay(1000);
						}
						Com_upd();
						if(cp_l_h.isEmpty() && cp_r_h.isEmpty()) {
							pla_won();
						}
						else if(p_l_h.isEmpty() && p_r_h.isEmpty())	{
							com_won();
						}
						else {
							turn=true;
							t=true;
						}
						repaint();
						
					}
				}
			}
			
		} 
	
	public void Com_upd() {
		
		loading();
		
		if(cp_l_h.size()==0) {
	    	cp_l_5_0();
	    }
	    else if(cp_l_h.size()==1) {
	    	cp_l_1();
	    }
	    else if(cp_l_h.size()==2) {
	    	cp_l_2();
	    }
	    else if(cp_l_h.size()==3) { 
	    	cp_l_3();
	    }
	    else if(cp_l_h.size()==4) { 
	    	cp_l_4();
	    }
		
		
		if(cp_r_h.size()==0) {
			cp_r_5_0();
	    }
	    else if(cp_r_h.size()==1) {
	    	cp_r_1();
	    }
	    else if(cp_r_h.size()==2) {
	    	cp_r_2();
	    }
	    else if(cp_r_h.size()==3) { 
	    	cp_r_3();
	    }
	    else if(cp_r_h.size()==4) { 
	    	cp_r_4();
	    }
	}
	
	public void com_won() {
		loading();
		
		cp_l_btn.setVisible(false);
		cp_r_btn.setVisible(false);
		p_l_btn.setVisible(false);
		p_r_btn.setVisible(false);
		msg.setVisible(false);
		w_l.setVisible(true);
		w_l.setText("(: You Lost :)");
		delay(1000);
		
		int O=JOptionPane.showConfirmDialog(pwc_frm, "want to play again ?", "play with computer", 
										  JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE , null);
		if(JOptionPane.YES_OPTION==O) {
			running=false;
			pwc_frm.dispose();
			try {
				new PWC();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(JOptionPane.NO_OPTION==O) {
			running=false;
			pwc_frm.dispose();
			try {
				new ChopStick().main_frm.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			running=false;
		}
		
		if(Void_matrix_account.pag) {
			lcount=0;
			lcount++;
			try {
				bw=new BufferedWriter(new FileWriter("lost.txt"));
				bw.write(lcount);
				bw.close();
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			String url="jdbc:mysql://db4free.net:3306/stickchop?useSSL=false";
			String usr="voidmatrix";
			String s="voidmatrix2002";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				c= DriverManager.getConnection(url,usr,s);
				st=c.createStatement();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int l=0;
			String name=null;
			try {
				br=new BufferedReader(new FileReader("player.txt"));
				name=br.readLine();
				br.close();
				ResultSet rs=st.executeQuery("select *from signup where uname='"+name+"'");
				rs.next();
				l=rs.getInt(6);
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			lcount=l;
			lcount++;
			try {
				PreparedStatement p=c.prepareStatement("update signup set="+lcount+" where uname='"+name+"'");
				bw=new BufferedWriter(new FileWriter("lost.txt"));
				bw.write(lcount);
				bw.close();
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void pla_won()  {
		loading();
		
		cp_l_btn.setVisible(false);
		cp_r_btn.setVisible(false);
		p_l_btn.setVisible(false);
		p_r_btn.setVisible(false);
		msg.setVisible(false);
		w_l.setVisible(true);
		w_l.setText("(: You Won :)");
		delay(1000);
		int O=JOptionPane.showConfirmDialog(pwc_frm, "want to play again ?", "play with computer", 
										  JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE , null);
		if(JOptionPane.YES_OPTION==O) {
			running=false;
			pwc_frm.dispose();
			try {
				new PWC();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(JOptionPane.NO_OPTION==O) {
			running=false;
			pwc_frm.dispose();
			try {
				new ChopStick().main_frm.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			running=false;
		}
		if(Void_matrix_account.pag) {
			wcount=0;
			wcount++;
			try {
				bw=new BufferedWriter(new FileWriter("won.txt"));
				bw.write(wcount);
				bw.close();
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			String url="jdbc:mysql://db4free.net:3306/matrixvoid02?useSSL=false";
			String usr="chopstick02";
			String s="15112002Chop";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				c= DriverManager.getConnection(url,usr,s);
				st=c.createStatement();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int w=0;
			String name=null;
			try {
				br=new BufferedReader(new FileReader("player.txt"));
				name=br.readLine();
				br.close();
				ResultSet rs=st.executeQuery("select *from ChopStick_Players where pname='"+name+"'");
				rs.next();
				w=rs.getInt(5);
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			wcount=w;
			wcount++;
			try {
				PreparedStatement p=c.prepareStatement("update ChopStick_Players set="+wcount+" where pname='"+name+"'");
				bw=new BufferedWriter(new FileWriter("won.txt"));
				bw.write(wcount);
				bw.close();
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void Com_turn() {
		loading();
		
		r_pnl.setBackground(Color.white);
		delay(500);
		r_pnl.setBackground(new Color(30, 144, 255));
		
		msg.setText("Computer is making the move");
		delay(1000);
		
		if(cp_l_h.size()==1&&cp_r_h.size()==0)
		{
			loading();
			msg.setText("Computer chose to attack");
			delay(1000);
			if(p_l_h.isEmpty()) {
				loading();
				msg.setText("Computer's left hand -> Your right hand");
				delay(3000);
				C_L_R_HIT();
			}
			else if(p_r_h.isEmpty()) {
				loading();
				msg.setText("Computer's left hand -> Your left hand");
				delay(3000);
				C_L_L_HIT();
			}
			else {
				cp_ch_p_h=r.nextInt(2);//p : 0=l , 1=r
				if(cp_ch_p_h==0)
				{
					loading();
					msg.setText("Computer's left hand -> Your left hand");
					delay(3000);
					C_L_L_HIT();
				}
				else 
				{
					loading();
					msg.setText("Computer's left hand -> Your right hand");
					delay(3000);
					C_L_R_HIT();
				}
			}
		}
		
		else if(cp_l_h.size()==0&&cp_r_h.size()==1)
		{
			loading();
			msg.setText("Computer chose to attack");
			delay(1000);
			if(p_l_h.isEmpty()) {
				loading();
				msg.setText("Computer's right hand -> Your right hand");
				delay(3000);
				C_R_R_HIT();
			}
			else if(p_r_h.isEmpty()) {
				loading();
				msg.setText("Computer's right hand -> Your left hand");
				delay(3000);
				C_R_L_HIT();
			}
			else {
				cp_ch_p_h=r.nextInt(2);//p : 0=l , 1=r
				if(cp_ch_p_h==0)
				{
					loading();
					msg.setText("Computer's right hand -> Your left hand");
					delay(3000);
					C_R_L_HIT();
				}
				else 
				{
					loading();
					msg.setText("Computer's right hand -> Your right hand");
					delay(3000);
					C_R_R_HIT();
				}
			}
		}
		
		else if(cp_l_h.size()+cp_r_h.size()>4) {
			cp_ch_c_h=r.nextInt(2);//c : 0=l , 1=r
			cp_ch_p_h=r.nextInt(2);//p : 0=l , 1=r
			loading();
			msg.setText("Computer chose to attack");
			delay(1000);
			
			if(p_l_h.isEmpty()) {
				if(cp_ch_c_h==0)
				{
					loading();
					msg.setText("Computer's left hand -> Your right hand");
					delay(3000);
					C_L_R_HIT();
				}
				else {
					loading();
					msg.setText("Computer's right hand -> Your right hand");
					delay(3000);
					C_R_R_HIT();
				}
			}
			else if(p_r_h.isEmpty()) {
				if(cp_ch_c_h==0)
				{
					loading();
					msg.setText("Computer's left hand -> Your left hand");
					delay(3000);
					C_L_L_HIT();
				}
				else {
					loading();
					msg.setText("Computer's right hand -> Your left hand");
					delay(3000);
					C_R_L_HIT();
				}
			}
			else {
				if(cp_ch_c_h==0 && cp_ch_p_h==0)
				{
					loading();
					msg.setText("Computer's left hand -> Your left hand");
					delay(3000);
					C_L_L_HIT();
				}
				else if(cp_ch_c_h==0 && cp_ch_p_h==1)
				{
					loading();
					msg.setText("Computer's left hand -> Your right hand");
					delay(3000);
					C_L_R_HIT();
				}
				else if(cp_ch_c_h==1 && cp_ch_p_h==0)
				{
					loading();
					msg.setText("Computer's right hand -> Your left hand");
					delay(3000);
					C_R_L_HIT();
				}
				else if(cp_ch_c_h==1 && cp_ch_p_h==1)
				{
					loading();
					msg.setText("Computer's right hand -> Your right hand");
					delay(3000);
					C_R_R_HIT();
				}
			
			}
		}
		else {
			
			cp_ch=r.nextInt(4);//0=a , 1=s , 2=a, 3=s
			
			if(cp_ch==0 || cp_ch==2)
			{
				cp_ch_c_h=r.nextInt(2);//c : 0=l , 1=r 
				cp_ch_p_h=r.nextInt(2);//p : 0=l , 1=r 
				loading();
				msg.setText("Computer chose to attack");
				delay(1000);
				
				if(cp_l_h.isEmpty()) {
					if(p_l_h.isEmpty()) {
						loading();
						msg.setText("Computer's right hand -> Your right hand");
						delay(3000);
						C_R_R_HIT();
					}
					else if(p_r_h.isEmpty()) {
						loading();
						msg.setText("Computer's right hand -> Your left hand");
						delay(3000);
						C_R_L_HIT();
					}
					else {
						cp_ch_p_h=r.nextInt(2);//p : 0=l , 1=r
						if(cp_ch_p_h==0)
						{
							loading();
							msg.setText("Computer's right hand -> Your left hand");
							delay(3000);
							C_R_L_HIT();
						}
						else 
						{
							loading();
							msg.setText("Computer's right hand -> Your right hand");
							delay(3000);
							C_R_R_HIT();
						}
					}
				}
				else if(cp_r_h.isEmpty()) {
					if(p_l_h.isEmpty()) {
						loading();
						msg.setText("Computer's left hand -> Your right hand");
						delay(3000);
						C_L_R_HIT();
					}
					else if(p_r_h.isEmpty()) {
						loading();
						msg.setText("Computer's left hand -> Your left hand");
						delay(3000);
						C_L_L_HIT();
					}
					else {
						cp_ch_p_h=r.nextInt(2);//p : 0=l , 1=r
						if(cp_ch_p_h==0)
						{
							loading();
							msg.setText("Computer's left hand -> Your left hand");
							delay(3000);
							C_L_L_HIT();
						}
						else 
						{
							loading();
							msg.setText("Computer's left hand -> Your right hand");
							delay(3000);
							C_L_R_HIT();
						}
					}
				}
				else if(p_l_h.isEmpty()) {
					if(cp_ch_c_h==0)
					{
						loading();
						msg.setText("Computer's left hand -> Your right hand");
						delay(3000);
						C_L_R_HIT();
					}
					else {
						loading();
						msg.setText("Computer's right hand -> Your right hand");
						delay(3000);
						C_R_R_HIT();
					}
				}
				else if(p_r_h.isEmpty()) {
					if(cp_ch_c_h==0)
					{
						loading();
						msg.setText("Computer's left hand -> Your left hand");
						delay(3000);
						C_L_L_HIT();
					}
					else {
						loading();
						msg.setText("Computer's right hand -> Your left hand");
						delay(3000);
						C_R_L_HIT();
					}
				}
				else {
					if(cp_ch_c_h==0 && cp_ch_p_h==0)
					{
						loading();
						msg.setText("Computer's left hand -> Your left hand");
						delay(3000);
						C_L_L_HIT();
					}
					else if(cp_ch_c_h==0 && cp_ch_p_h==1)
					{
						loading();
						msg.setText("Computer's left hand -> Your right hand");
						delay(3000);
						C_L_R_HIT();
					}
					else if(cp_ch_c_h==1 && cp_ch_p_h==0)
					{
						loading();
						msg.setText("Computer's right hand -> Your left hand");
						delay(3000);
						C_R_L_HIT();
					}
					else if(cp_ch_c_h==1 && cp_ch_p_h==1)
					{
						loading();
						msg.setText("Computer's right hand -> Your right hand");
						delay(3000);
						C_R_R_HIT();
					}	
				}
				
			}
			
			else 
			{
				loading();
				msg.setText("Computer chose to split");
				delay(1000);
				do {
					prcpl=cp_l_h.size();
					prcpr=cp_r_h.size();
					cp1=cp_l_h.size()+cp_r_h.size();
					cph=r.nextInt(2);//0=L,1=R
					rc=r.nextInt(cp1+1);
					cp2=cp1-rc;
					if(cph==0)
					{
						cp_l_h.removeAllElements();
						cp_r_h.removeAllElements();
						for(i=0;i<rc;i++)
						{
							cp_l_h.add(1);
						}
						for(i=0;i<cp2;i++)
						{
							cp_r_h.add(1);
						}
						loading();
					}
					else if(cph==1)
					{
						cp_l_h.removeAllElements();
						cp_r_h.removeAllElements();
						for(i=0;i<rc;i++)
						{
							cp_r_h.add(1);
						}
						for(i=0;i<cp2;i++)
						{
							cp_l_h.add(1);
						}
						loading();
					}
				}
				while(cp_l_h.size()==cp_r_h.size() || cp_l_h.size()==prcpr&&cp_r_h.size()==prcpl || cp_l_h.size()==prcpl || cp_r_h.size()==prcpr);
				
				if(cp_l_h.size()==0) {
			    	cp_l_0();
			    }
			    else if(cp_l_h.size()==1) {
			    	cp_l_1();
			    }
			    else if(cp_l_h.size()==2) {
			    	cp_l_2();
			    }
			    else if(cp_l_h.size()==3) { 
			    	cp_l_3();
			    }
			    else if(cp_l_h.size()==4) { 
			    	cp_l_4();
			    }
				
				
				if(cp_r_h.size()==0) {
					cp_r_0();
			    }
			    else if(cp_r_h.size()==1) {
			    	cp_r_1();
			    }
			    else if(cp_r_h.size()==2) {
			    	cp_r_2();
			    }
			    else if(cp_r_h.size()==3) { 
			    	cp_r_3();
			    }
			    else if(cp_l_h.size()==4) { 
			    	cp_r_4();
			    }
				
			}
		}
	}
		
	
	 //computer-hit	
		public void C_L_L_HIT()
		{
			x=cp_l_h.size();
		    for(i=0;i<x;i++) {
		    	if(p_l_h.size()==5) {
		    		p_l_h.removeAllElements();
		    		p_l_5_0();
		    	}
		    	p_l_h.add(1);
		    }
		    if(p_l_h.size()==5) {
	    		p_l_h.removeAllElements();
		    } 	
		    
		    if(p_l_h.size()==0) {
		    	p_l_5_0();
		    }
		    else if(p_l_h.size()==1) {
		    	p_l_1();
		    }
		    else if(p_l_h.size()==2) {
		    	p_l_2();
		    }
		    else if(p_l_h.size()==3) { 
		    	p_l_3();
		    }
		    else if(p_l_h.size()==4) {
		    	p_l_4();
		    }
		}
		
		public void C_L_R_HIT()
		{
			x=cp_l_h.size();
		    for(i=0;i<x;i++) {
		    	if(p_r_h.size()==5) {
		    		p_r_h.removeAllElements();
		    		p_r_5_0();
		    	}
		    	p_r_h.add(1);
		    }
		    if(p_r_h.size()==5) {
	    		p_r_h.removeAllElements();
		    }
		    
		    if(p_r_h.size()==0) {
		    	p_r_5_0();
		    }
		    else if(p_r_h.size()==1) {
		    	p_r_1();
		    }
		    else if(p_r_h.size()==2) {
		    	p_r_2();
		    }
		    else if(p_r_h.size()==3) {  
		    	p_r_3();
		    }
		    else if(p_r_h.size()==4) {
		    	p_r_4();
		    }
		}
		
		public void C_R_L_HIT()
		{
			x=cp_r_h.size();
		    for(i=0;i<x;i++) {
		    	if(p_l_h.size()==5) {
		    		p_l_h.removeAllElements();
		    		p_l_5_0();
		    	}
		    	p_l_h.add(1);
		    }
		    if(p_l_h.size()==5) {
	    		p_l_h.removeAllElements();
		    }
		    
		    if(p_l_h.size()==0) {
		    	p_l_5_0();
		    }
		    else if(p_l_h.size()==1) {
		    	p_l_1();
		    }
		    else if(p_l_h.size()==2) {
		    	p_l_2();
		    }
		    else if(p_l_h.size()==3) { 
		    	p_l_3();
		    }
		    else if(p_l_h.size()==4) {
		    	p_l_4();
		    }
		}
		
		public void C_R_R_HIT()
		{
			x=cp_r_h.size();
			
		    for(i=0;i<x;i++) {
		    	if(p_r_h.size()==5) {
		    		p_r_h.removeAllElements();
		    		p_r_5_0();
		    	}
		    	p_r_h.add(1);
		    }
		    if(p_r_h.size()==5) {
	    		p_r_h.removeAllElements();
		    }
		    
		    if(p_r_h.size()==0) {
		    	p_r_5_0();	    	
		    }
		    else if(p_r_h.size()==1) {
		    	p_r_1();
		    }
		    else if(p_r_h.size()==2) {
		    	p_r_2();
		    }
		    else if(p_r_h.size()==3) {  
		    	p_r_3();
		    }
		    else if(p_r_h.size()==4) {
		    	p_r_4();
		    	
		    }
		}
		
		
	public void Pla_turn() {
		
		loading();
		l_pnl.setBackground(Color.white);
		delay(500);
		l_pnl.setBackground(new Color(30, 144, 255));
		
		if(p_l_h.size()==0&&p_r_h.size()==1) {
			msg.setText(".....You can only attack.....");
			attack_btn.setVisible(true);
			split_btn.setVisible(true);
			split_btn.setEnabled(false);
		}
		else if(p_r_h.size()==0&&p_l_h.size()==1) {
			msg.setText(".....You can only attack.....");
			attack_btn.setVisible(true);
			split_btn.setVisible(true);
			split_btn.setEnabled(false);
		}
		else {
			msg.setText("What do you wanna do ?");
			attack_btn.setVisible(true);
			split_btn.setVisible(true);
			split_btn.setEnabled(true);
		}
		
	}
	
	public void P_L_L_H() {
		
	}
	
	public void P_L_R_H() {
		
	}

	public void P_R_L_H() {
	
	}
	
	public void P_R_R_H() {
		
	}

	
	//cp_l_fingers
	public void cp_l_0() {
		cp_l_one.setVisible(false);
		cp_l_two.setVisible(false);
		cp_l_three.setVisible(false);
		cp_l_four.setVisible(false);
		cp_l_five.setVisible(false);
		cp_l_fist.setVisible(true);
	}
	
	public void cp_l_1() {
		cp_l_one.setVisible(true);
		cp_l_two.setVisible(false);
		cp_l_three.setVisible(false);
		cp_l_four.setVisible(false);
		cp_l_five.setVisible(false);
		cp_l_fist.setVisible(false);
	}
	
	public void cp_l_2() {
		cp_l_one.setVisible(false);
		cp_l_two.setVisible(true);
		cp_l_three.setVisible(false);
		cp_l_four.setVisible(false);
		cp_l_five.setVisible(false);
		cp_l_fist.setVisible(false);
	}
	
	public void cp_l_3() {
		cp_l_one.setVisible(false);
		cp_l_two.setVisible(false);
		cp_l_three.setVisible(true);
		cp_l_four.setVisible(false);
		cp_l_five.setVisible(false);
		cp_l_fist.setVisible(false);
	}
	
	public void cp_l_4() {
		cp_l_one.setVisible(false);
		cp_l_two.setVisible(false);
		cp_l_three.setVisible(false);
		cp_l_four.setVisible(true);
		cp_l_five.setVisible(false);
		cp_l_fist.setVisible(false);
	}
	
	public void cp_l_5_0() {
		cp_l_one.setVisible(false);
		cp_l_two.setVisible(false);
		cp_l_three.setVisible(false);
		cp_l_four.setVisible(false);
		cp_l_five.setVisible(true);
		delay(1000);
		cp_l_five.setVisible(false);
		cp_l_fist.setVisible(true);
	}
	
	
	//cp_r_fingers
	public void cp_r_0() {
		cp_r_one.setVisible(false);
		cp_r_two.setVisible(false);
		cp_r_three.setVisible(false);
		cp_r_four.setVisible(false);
		cp_r_five.setVisible(false);
		cp_r_fist.setVisible(true);
	}
	
	public void cp_r_1() {
		cp_r_one.setVisible(true);
		cp_r_two.setVisible(false);
		cp_r_three.setVisible(false);
		cp_r_four.setVisible(false);
		cp_r_five.setVisible(false);
		cp_r_fist.setVisible(false);
	}
	
	public void cp_r_2() {
		cp_r_one.setVisible(false);
		cp_r_two.setVisible(true);
		cp_r_three.setVisible(false);
		cp_r_four.setVisible(false);
		cp_r_five.setVisible(false);
		cp_r_fist.setVisible(false);
	}
	
	public void cp_r_3() {
		cp_r_one.setVisible(false);
		cp_r_two.setVisible(false);
		cp_r_three.setVisible(true);
		cp_r_four.setVisible(false);
		cp_r_five.setVisible(false);
		cp_r_fist.setVisible(false);
	}
	
	public void cp_r_4() {
		cp_r_one.setVisible(false);
		cp_r_two.setVisible(false);
		cp_r_three.setVisible(false);
		cp_r_four.setVisible(true);
		cp_r_five.setVisible(false);
		cp_r_fist.setVisible(false);
	}
	
	public void cp_r_5_0() {
		cp_r_one.setVisible(false);
		cp_r_two.setVisible(false);
		cp_r_three.setVisible(false);
		cp_r_four.setVisible(false);
		cp_r_five.setVisible(true);
		delay(1000);
		cp_r_five.setVisible(false);
		cp_r_fist.setVisible(true);
	}
	
	
	//p_l_fingers
	public void p_l_0() {
		p_l_one.setVisible(false);
		p_l_two.setVisible(false);
		p_l_three.setVisible(false);
		p_l_four.setVisible(false);
		p_l_five.setVisible(false);
		p_l_fist.setVisible(true);
	}
	
	public void p_l_1() {
		p_l_one.setVisible(true);
		p_l_two.setVisible(false);
		p_l_three.setVisible(false);
		p_l_four.setVisible(false);
		p_l_five.setVisible(false);
		p_l_fist.setVisible(false);
	}
	
	public void p_l_2() {
		p_l_one.setVisible(false);
		p_l_two.setVisible(true);
		p_l_three.setVisible(false);
		p_l_four.setVisible(false);
		p_l_five.setVisible(false);
		p_l_fist.setVisible(false);
	}
	
	public void p_l_3() {
		p_l_one.setVisible(false);
		p_l_two.setVisible(false);
		p_l_three.setVisible(true);
		p_l_four.setVisible(false);
		p_l_five.setVisible(false);
		p_l_fist.setVisible(false);
	}
	
	public void p_l_4() {
		p_l_one.setVisible(false);
		p_l_two.setVisible(false);
		p_l_three.setVisible(false);
		p_l_four.setVisible(true);
		p_l_five.setVisible(false);
		p_l_fist.setVisible(false);
	}
	
	public void p_l_5_0() {
		p_l_one.setVisible(false);
		p_l_two.setVisible(false);
		p_l_three.setVisible(false);
		p_l_four.setVisible(false);
		p_l_five.setVisible(true);
		delay(1000);
		p_l_five.setVisible(false);
		p_l_fist.setVisible(true);
	}
	
	
	//p_r_fingers
	public void p_r_0() {
		p_r_one.setVisible(false);
		p_r_two.setVisible(false);
		p_r_three.setVisible(false);
		p_r_four.setVisible(false);
		p_r_five.setVisible(false);
		p_r_fist.setVisible(true);
	}
	
	public void p_r_1() {
		p_r_one.setVisible(true);
		p_r_two.setVisible(false);
		p_r_three.setVisible(false);
		p_r_four.setVisible(false);
		p_r_five.setVisible(false);
		p_r_fist.setVisible(false);
	}
	
	public void p_r_2() {
		p_r_one.setVisible(false);
		p_r_two.setVisible(true);
		p_r_three.setVisible(false);
		p_r_four.setVisible(false);
		p_r_five.setVisible(false);
		p_r_fist.setVisible(false);
	}
	
	public void p_r_3() {
		p_r_one.setVisible(false);
		p_r_two.setVisible(false);
		p_r_three.setVisible(true);
		p_r_four.setVisible(false);
		p_r_five.setVisible(false);
		p_r_fist.setVisible(false);
	}
	
	public void p_r_4() {
		p_r_one.setVisible(false);
		p_r_two.setVisible(false);
		p_r_three.setVisible(false);
		p_r_four.setVisible(true);
		p_r_five.setVisible(false);
		p_r_fist.setVisible(false);
	}
	
	public void p_r_5_0() {
		p_r_one.setVisible(false);
		p_r_two.setVisible(false);
		p_r_three.setVisible(false);
		p_r_four.setVisible(false);
		p_r_five.setVisible(true);
		delay(1000);
		p_r_five.setVisible(false);
		p_r_fist.setVisible(true);
	}
	
	public void repaint() {
		loading();
		
		if(cp_l_h.size()==0) {
	    	cp_l_5_0();
	    }
	    else if(cp_l_h.size()==1) {
	    	cp_l_1();
	    }
	    else if(cp_l_h.size()==2) {
	    	cp_l_2();
	    }
	    else if(cp_l_h.size()==3) { 
	    	cp_l_3();
	    }
	    else if(cp_l_h.size()==4) { 
	    	cp_l_4();
	    }
		
		
		if(cp_r_h.size()==0) {
			cp_r_5_0();
	    }
	    else if(cp_r_h.size()==1) {
	    	cp_r_1();
	    }
	    else if(cp_r_h.size()==2) {
	    	cp_r_2();
	    }
	    else if(cp_r_h.size()==3) { 
	    	cp_r_3();
	    }
	    else if(cp_r_h.size()==4) { 
	    	cp_r_4();
	    }
		
		
		if(p_l_h.size()==0) {
	    	p_l_5_0();
	    }
	    else if(p_l_h.size()==1) {
	    	p_l_1();
	    }
	    else if(p_l_h.size()==2) {
	    	p_l_2();
	    }
	    else if(p_l_h.size()==3) { 
	    	p_l_3();
	    }
	    else if(p_l_h.size()==4) { 
	    	p_l_4();
	    }
		
		
		if(p_r_h.size()==0) {
			p_r_5_0();
	    }
	    else if(p_r_h.size()==1) {
	    	p_r_1();
	    }
	    else if(p_r_h.size()==2) {
	    	p_r_2();
	    }
	    else if(p_r_h.size()==3) { 
	    	p_r_3();
	    }
	    else if(p_r_h.size()==4) { 
	    	p_r_4();
	    }
	}
}
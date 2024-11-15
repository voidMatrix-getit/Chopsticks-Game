package sahu.chopstick_game;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
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
public class PVP implements ActionListener,MouseListener,Runnable{

	private JFrame pvp_frm;
	
	private JButton quit_btn,
					p1_l_btn,
					p1_r_btn,
					p2_r_btn,
					p2_l_btn,
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
	
	private boolean running=true,turn,p1_attack,p1_split,p2_attack,p2_split,attack,split,p1_l,p1_r,p2_l,p2_r,t;
	
	private Vector<Integer> p1_l_h=new Vector<Integer>(5),
					p1_r_h=new Vector<Integer>(5),
					p2_l_h=new Vector<Integer>(5),
					p2_r_h=new Vector<Integer>(5);
	
	@SuppressWarnings("unused")
	public int i,cp1,cp2,prp1l,prp1r,
				cph,rc,prp2l,prp2r,x;
	
	
	BufferedReader br=null;
	
	public void delay(int n) {
    	try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
	
	public PVP() throws Exception{
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
		pvp_frm= new JFrame();
		pvp_frm.setTitle("Player Vs Player");
		pvp_frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo_F.png")));
		pvp_frm.setResizable(false);
		pvp_frm.setBounds(150, 20, 1250, 785);
		pvp_frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pvp_frm.setUndecorated(true);
		pvp_frm.getContentPane().setLayout(null);
		
		quit_btn = new JButton("Quit");
		quit_btn.addActionListener(this);
		quit_btn.setForeground(new Color(51, 0, 153));
		quit_btn.setFont(new Font("Tahoma", Font.BOLD, 30));
		quit_btn.setBackground(new Color(255, 204, 0));
		quit_btn.setBounds(10, 721, 138, 56);
		quit_btn.setFocusable(false);
		quit_btn.addMouseListener(this);
		pvp_frm.getContentPane().add(quit_btn);
		
		r_pnl = new JPanel();
		r_pnl.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, 
													new Color(0, 0, 128), new Color(0, 0, 205), 
													new Color(0, 0, 139), new Color(0, 0, 255)), 
										   new BevelBorder(BevelBorder.LOWERED, 
												    new Color(0, 0, 205), new Color(0, 0, 255), 
												    new Color(0, 0, 128), new Color(0, 0, 139))));
		r_pnl.setBounds(670, 50, 516, 447);
		pvp_frm.getContentPane().add(r_pnl);
		r_pnl.setLayout(null);
		
		p2_r_btn = new JButton("Right");
		p2_r_btn.setForeground(new Color(0, 255, 0));
		p2_r_btn.addActionListener(this);
		p2_r_btn.setEnabled(false);
		p2_r_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		p2_r_btn.setBackground(new Color(0, 0, 204));
		p2_r_btn.setBounds(319, 100, 114, 45);
		p2_r_btn.setFocusable(false);
		p2_r_btn.addMouseListener(this);
		r_pnl.add(p2_r_btn);
		
		p2_l_btn = new JButton("Left");
		p2_l_btn.setForeground(new Color(0, 255, 0));
		p2_l_btn.addActionListener(this);
		p2_l_btn.setEnabled(false);
		p2_l_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		p2_l_btn.setBackground(new Color(51, 51, 153));
		p2_l_btn.setBounds(319, 291, 114, 45);
		p2_l_btn.setFocusable(false);
		p2_l_btn.addMouseListener(this);
		r_pnl.add(p2_l_btn);
		
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
		pvp_frm.getContentPane().add(l_pnl);
		
		p1_l_btn = new JButton("Left");
		p1_l_btn.setForeground(new Color(0, 255, 0));
		p1_l_btn.addActionListener(this);
		p1_l_btn.setEnabled(false);
		p1_l_btn.setBackground(new Color(51, 51, 153));
		p1_l_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		p1_l_btn.setBounds(85, 100, 114, 45);
		p1_l_btn.setFocusable(false);
		p1_l_btn.addMouseListener(this);
		l_pnl.add(p1_l_btn);
		
		p1_r_btn = new JButton("Right");
		p1_r_btn.setForeground(new Color(0, 255, 0));
		p1_r_btn.addActionListener(this);
		p1_r_btn.setEnabled(false);
		p1_r_btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		p1_r_btn.setBackground(new Color(0, 0, 204));
		p1_r_btn.setBounds(85, 291, 114, 45);
		p1_r_btn.setFocusable(false);
		p1_r_btn.addMouseListener(this);
		l_pnl.add(p1_r_btn);
		
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
		
		JLabel lplayer_lbl = new JLabel("Player1");
		lplayer_lbl.setForeground(new Color(255, 215, 0));
		lplayer_lbl.setFont(new Font("Tahoma", Font.BOLD, 35));
		lplayer_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lplayer_lbl.setBounds(197, 0, 193, 50);
		pvp_frm.getContentPane().add(lplayer_lbl);
		
		JLabel rplayer_lbl = new JLabel("Player2");
		rplayer_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		rplayer_lbl.setForeground(new Color(255, 215, 0));
		rplayer_lbl.setFont(new Font("Tahoma", Font.BOLD, 35));
		rplayer_lbl.setBounds(848, 0, 182, 50);
		pvp_frm.getContentPane().add(rplayer_lbl);
		
		JLabel vs_lbl = new JLabel("Vs");
		vs_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		vs_lbl.setForeground(new Color(255, 215, 0));
		vs_lbl.setFont(new Font("Tahoma", Font.BOLD, 30));
		vs_lbl.setBounds(576, 261, 84, 40);
		pvp_frm.getContentPane().add(vs_lbl);
		
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
		pvp_frm.getContentPane().add(main_pnl);
		
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
		
		JPanel lplayer_pnl = new JPanel();
		lplayer_pnl.setBounds(197, 0, 193, 50);
		pvp_frm.getContentPane().add(lplayer_pnl);
		lplayer_pnl.setLayout(null);
		lplayer_pnl.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, 
													new Color(0, 0, 128), new Color(0, 0, 205), 
													new Color(0, 0, 139), new Color(0, 0, 255)), 
												 new BevelBorder(BevelBorder.LOWERED, 
													new Color(0, 0, 205), new Color(0, 0, 255), 
													new Color(0, 0, 128), new Color(0, 0, 139))));
		lplayer_pnl.setBackground(new Color(30, 144, 255));
		
		JPanel player_r_pnl = new JPanel();
		player_r_pnl.setLayout(null);
		player_r_pnl.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, 
													new Color(0, 0, 128), new Color(0, 0, 205), 
													new Color(0, 0, 139), new Color(0, 0, 255)), 
												  new BevelBorder(BevelBorder.LOWERED, 
												    new Color(0, 0, 205), new Color(0, 0, 255), 
												    new Color(0, 0, 128), new Color(0, 0, 139))));
		player_r_pnl.setBackground(new Color(30, 144, 255));
		player_r_pnl.setBounds(834, 0, 208, 50);
		pvp_frm.getContentPane().add(player_r_pnl);
		
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
		pvp_frm.getContentPane().add(vs_pnl);
		
		if(!br.ready()) {
			pvp_frm.getContentPane().setBackground(new Color(102, 0, 0));
			r_pnl.setBackground(new Color(30, 144, 255));
			l_pnl.setBackground(new Color(30, 144, 255));
			br.close();
		}
		else {
			br.skip(3);
			pvp_frm.getContentPane().setBackground(new Color(br.read(), br.read(), br.read()));
			br.skip(6);
			r_pnl.setBackground(new Color(br.read(), br.read(), br.read()));
			l_pnl.setBackground(new Color(br.read(), br.read(), br.read()));
		}
		
		pvp_frm.setVisible(true);
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
		if(e.getSource()==p1_l_btn) {
			p1_l_btn.setBackground(Color.black);
		}
		if(e.getSource()==p1_r_btn) {
			p1_r_btn.setBackground(Color.black);
		}
		if(e.getSource()==p2_l_btn) {
			p2_l_btn.setBackground(Color.black);
		}
		if(e.getSource()==p2_r_btn) {
			p2_r_btn.setBackground(Color.black);
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
		if(e.getSource()==p1_l_btn) {
			p1_l_btn.setBackground(new Color(51, 51, 153));
		}
		if(e.getSource()==p1_r_btn) {
			p1_r_btn.setBackground(new Color(0, 0, 204));
		}
		if(e.getSource()==p2_l_btn) {
			p2_l_btn.setBackground(new Color(51, 51, 153));
		}
		if(e.getSource()==p2_r_btn) {
			p2_r_btn.setBackground(new Color(0, 0, 204));
		}
		
	}
	

	public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==quit_btn) {
				int Q=JOptionPane.showConfirmDialog(pvp_frm, "Sure want to quit?", "Quit", 
												JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(Q==0) {
					pvp_frm.dispose();
					try {
						new ChopStick().main_frm.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
			
			if(e.getSource()==split_btn) {
				split=true;
				if(p1_split) {
					p1_r_btn.setEnabled(true);
					p1_l_btn.setEnabled(true);
					p2_l_btn.setEnabled(false);
					p2_r_btn.setEnabled(false);
					p1_attack=false;
					p1_l=false;
					p1_r=false;
					p2_l=false;
					p2_r=false;
					prp1l=p1_l_h.size();
					prp1r=p1_r_h.size();
				}
				else if(p2_split) {
					p2_r_btn.setEnabled(true);
					p2_l_btn.setEnabled(true);
					p1_l_btn.setEnabled(false);
					p1_r_btn.setEnabled(false);
					p2_attack=false;
					p1_l=false;
					p1_r=false;
					p2_l=false;
					p2_r=false;
					prp2l=p1_l_h.size();
					prp2r=p1_r_h.size();
				}
				attack_btn.setVisible(false);
				split_btn.setVisible(false);
				msg.setText("Click on your either of the hand-buttons[right or left]\n"
						+ "For splitting the fingers by one,\n"
						+ "(One finger will be increased on the clicked hand-button,\n"
						+ "hence it will decrease one finger on the opposite)");
				
			}
			
			if(e.getSource()==attack_btn) {
				attack=true;
				p1_split=false;
				p2_split=false;
				if(p1_attack) {
					if(p1_l_h.size()==0) {
						p1_l_btn.setEnabled(false);
						p1_r_btn.setEnabled(true);
					}
					else if(p1_r_h.size()==0) {
						p1_r_btn.setEnabled(false);
						p1_l_btn.setEnabled(true);
					}
					else {
						p1_r_btn.setEnabled(true);
						p1_l_btn.setEnabled(true);
					}
				}
				else if(p2_attack) {
					if(p2_l_h.size()==0) {
						p2_l_btn.setEnabled(false);
						p2_r_btn.setEnabled(true);
					}
					else if(p2_r_h.size()==0) {
						p2_r_btn.setEnabled(false);
						p2_l_btn.setEnabled(true);
					}
					else {
						p2_r_btn.setEnabled(true);
						p2_l_btn.setEnabled(true);
					}
				}
				
				attack_btn.setVisible(false);
				split_btn.setVisible(false);
				msg.setText("First click on either of your hand-buttons[right or left]\n"
							+ "and then opponent's for the attack");
			}
			
			
			if(e.getSource()==p1_l_btn&&p1_split) {
				msg.setText(".....Splitting.....");
				
				if(p1_l_h.size()>=4)
					JOptionPane.showMessageDialog(l_pnl,
							"You can't kill your own hand!,\nIn splitting decrement of fingers isn't allowed", 
							"Fingers' Decrement", JOptionPane.WARNING_MESSAGE);
				else if(p1_r_h.size()==0) {
					JOptionPane.showMessageDialog(l_pnl, 
							"No fingers left to transfer\nYou got a fist on right hand", 
							"No more fingers", JOptionPane.WARNING_MESSAGE);
				}
				else {
					p1_l_h.add(1);
					p1_r_h.removeElement(p1_r_h.lastElement());
					p1_split();
				}
				done_btn.setVisible(true);
			}
			if(e.getSource()==p1_r_btn&&p1_split) {
				msg.setText(".....Splitting.....");
				
				if(p1_r_h.size()>=4)
					JOptionPane.showMessageDialog(l_pnl, 
							"You can't kill your own hand!,\nIn splitting decrement of fingers isn't allowed", 
							"Fingers' Decrement", JOptionPane.WARNING_MESSAGE);
				else if(p1_l_h.size()==0) {
					JOptionPane.showMessageDialog(l_pnl, 
							"No fingers left to transfer\nYou got a fist on left hand", 
							"No more fingers", JOptionPane.WARNING_MESSAGE);
				}
				else {
					p1_r_h.add(1);
					p1_l_h.removeElement(p1_l_h.lastElement());
					p1_split();
				}
				done_btn.setVisible(true);
			}
			
			if(e.getSource()==p2_l_btn&&p2_split) {
				msg.setText(".....Splitting.....");
				
				if(p2_l_h.size()>=4)
					JOptionPane.showMessageDialog(r_pnl, 
							"You can't kill your own hand!,\nIn splitting decrement of fingers isn't allowed", 
							"Fingers' Decrement", JOptionPane.WARNING_MESSAGE);
				else if(p2_r_h.size()==0) {
					JOptionPane.showMessageDialog(r_pnl, 
							"No fingers left to transfer\nYou got a fist on right hand", 
							"No more fingers", JOptionPane.WARNING_MESSAGE);
				}
				else {
					p2_l_h.add(1);
					p2_r_h.removeElement(p2_r_h.lastElement());
					p2_split();
				}
				done_btn.setVisible(true);
			}
			if(e.getSource()==p2_r_btn&&p2_split) {
				msg.setText(".....Splitting.....");
				
				if(p2_r_h.size()>=4)
					JOptionPane.showMessageDialog(r_pnl, 
							"You can't kill your own hand!,\nIn splitting decrement of fingers isn't allowed", 
							"Fingers' Decrement", JOptionPane.WARNING_MESSAGE);
				else if(p2_l_h.size()==0) {
					JOptionPane.showMessageDialog(r_pnl, 
							"No fingers left to transfer\nYou got a fist on left hand", "No more fingers", 
							JOptionPane.WARNING_MESSAGE);
				}
				else {
					p2_r_h.add(1);
					p2_l_h.removeElement(p2_l_h.lastElement());
					p2_split();
				}
				done_btn.setVisible(true);
			}
			
			
			if(e.getSource()==done_btn) {
				if(p1_split) {
					if(p1_l_h.size()==prp1r&&p1_r_h.size()==prp1l || p1_l_h.size()==prp1l&&p1_r_h.size()==prp1r) {
						JOptionPane.showMessageDialog(l_pnl, "Splitting is same as previous State!", 
								"Identical states", JOptionPane.WARNING_MESSAGE);
					}
					else {
						p1_r_btn.setEnabled(false);
						p1_l_btn.setEnabled(false);
						p2_r_btn.setEnabled(false);
						p2_l_btn.setEnabled(false);
						done_btn.setVisible(false);
						p1_split=false;
						p2_split=false;
						p1_attack=false;
						p2_attack=false;
						
						split=false;
						if(t)
							t=false;
						else
							t=true;
					}
				}
				else if(p2_split) {
					if(p2_l_h.size()==prp2r&&p2_r_h.size()==prp2l || p2_l_h.size()==prp2l&&p2_r_h.size()==prp2r) {
						JOptionPane.showMessageDialog(r_pnl, "Splitting is same as previous State!", 
								"Identical states", JOptionPane.WARNING_MESSAGE);
					}
					else {
						p1_r_btn.setEnabled(false);
						p1_l_btn.setEnabled(false);
						p2_r_btn.setEnabled(false);
						p2_l_btn.setEnabled(false);
						done_btn.setVisible(false);
						p1_split=false;
						p2_split=false;
						p1_attack=false;
						p2_attack=false;
						
						split=false;
						if(t)
							t=false;
						else
							t=true;
					}
				}	
			}
			
			
			if(e.getSource()==p1_l_btn&&p1_attack) {
				if(p2_r_h.size()==0) {
					p2_r_btn.setEnabled(false);
					p2_l_btn.setEnabled(true);
				}
				else if(p2_l_h.size()==0) {
					p2_l_btn.setEnabled(false);
					p2_r_btn.setEnabled(true);
				}
				else {
					p2_r_btn.setEnabled(true);
					p2_l_btn.setEnabled(true);
				}
				msg.setText(".....Player1's left hand.....");
				p1_l=true;
				p1_r=false;
			}
			if(e.getSource()==p1_r_btn&&p1_attack) {
				if(p2_r_h.size()==0) {
					p2_r_btn.setEnabled(false);
					p2_l_btn.setEnabled(true);
				}
				else if(p2_l_h.size()==0) {
					p2_l_btn.setEnabled(false);
					p2_r_btn.setEnabled(true);
				}
				else {
					p2_r_btn.setEnabled(true);
					p2_l_btn.setEnabled(true);
				}
				msg.setText(".....Player1's right hand.....");
				p1_r=true;
				p1_l=false;
			}
			
			if(e.getSource()==p2_l_btn&&p2_attack) {
				if(p1_r_h.size()==0) {
					p1_r_btn.setEnabled(false);
					p1_l_btn.setEnabled(true);
				}
				else if(p1_l_h.size()==0) {
					p1_l_btn.setEnabled(false);
					p1_r_btn.setEnabled(true);
				}
				else {
					p1_r_btn.setEnabled(true);
					p1_l_btn.setEnabled(true);
				}
				msg.setText(".....Player2's left hand.....");
				p2_l=true;
				p2_r=false;
			}
			if(e.getSource()==p2_r_btn&&p2_attack) {
				if(p1_r_h.size()==0) {
					p1_r_btn.setEnabled(false);
					p1_l_btn.setEnabled(true);
				}
				else if(p1_l_h.size()==0) {
					p1_l_btn.setEnabled(false);
					p1_r_btn.setEnabled(true);
				}
				else {
					p1_r_btn.setEnabled(true);
					p1_l_btn.setEnabled(true);
				}
				msg.setText(".....Player2's right hand.....");
				p2_r=true;
				p2_l=false;
			}
			
			//player1 hit
			if(e.getSource()==p2_l_btn&&p1_l) {
				if(p1_attack) {
					msg.setText(".....Player1's left hand -> Player2's left hand.....");
					
					p1_l_btn.setEnabled(false);
					p1_r_btn.setEnabled(false);
					p2_l_btn.setEnabled(false);
					p2_r_btn.setEnabled(false);
					
					P1_L_L_HIT();
					
					p1_split=false;
					p2_split=false;
					p1_attack=false;
					p2_attack=false;
					attack=false;
					t=false;
				}
				
			}
			if(e.getSource()==p2_r_btn&&p1_l) {
				if(p1_attack) {
					msg.setText(".....Player1's left hand -> Player2's right hand.....");
					
					p1_l_btn.setEnabled(false);
					p1_r_btn.setEnabled(false);
					p2_l_btn.setEnabled(false);
					p2_r_btn.setEnabled(false);
					
					P1_L_R_HIT();
					
					p1_split=false;
					p2_split=false;
					p1_attack=false;
					p2_attack=false;
					attack=false;
					t=false;
				}
				
			}
			if(e.getSource()==p2_l_btn&&p1_r) {
				if(p1_attack) {
					msg.setText(".....Player1's right hand -> Player2's left hand.....");
					
					p1_l_btn.setEnabled(false);
					p1_r_btn.setEnabled(false);
					p2_l_btn.setEnabled(false);
					p2_r_btn.setEnabled(false);
					
					P1_R_L_HIT();
					
					p1_split=false;
					p2_split=false;
					p1_attack=false;
					p2_attack=false;
					attack=false;
					t=false;
				}
				
			}
			if(e.getSource()==p2_r_btn&&p1_r) {
				if(p1_attack) {
					msg.setText(".....Player1's right hand -> Player2's right hand.....");
					
					p1_l_btn.setEnabled(false);
					p1_r_btn.setEnabled(false);
					p2_l_btn.setEnabled(false);
					p2_r_btn.setEnabled(false);
					
					P1_R_R_HIT();
					
					p1_split=false;
					p2_split=false;
					p1_attack=false;
					p2_attack=false;
					attack=false;
					t=false;
				}
				
			}
			
			//player2 hit
			if(e.getSource()==p1_l_btn&&p2_l) {
				if(p2_attack) {
					msg.setText(".....Player2's left hand -> Player1's left hand.....");
					
					p1_l_btn.setEnabled(false);
					p1_r_btn.setEnabled(false);
					p2_l_btn.setEnabled(false);
					p2_r_btn.setEnabled(false);
					
					P2_L_L_HIT();
					
					p1_split=false;
					p2_split=false;
					p1_attack=false;
					p2_attack=false;
					attack=false;
					t=true;
				}
				
			}
			if(e.getSource()==p1_r_btn&&p2_l) {
				if(p2_attack) {
					msg.setText(".....Player2's left hand -> Player1's right hand.....");
					
					p1_l_btn.setEnabled(false);
					p1_r_btn.setEnabled(false);
					p2_l_btn.setEnabled(false);
					p2_r_btn.setEnabled(false);
					
					P2_L_R_HIT();
					
					p1_split=false;
					p2_split=false;
					p1_attack=false;
					p2_attack=false;
					attack=false;
					t=true;
				}
				
			}
			if(e.getSource()==p1_l_btn&&p2_r) {
				if(p2_attack) {
					msg.setText(".....Player2's right hand -> Player2's left hand.....");
					
					p1_l_btn.setEnabled(false);
					p1_r_btn.setEnabled(false);
					p2_l_btn.setEnabled(false);
					p2_r_btn.setEnabled(false);
					
					P2_R_L_HIT();
					
					p1_split=false;
					p2_split=false;
					p1_attack=false;
					p2_attack=false;
					attack=false;
					t=true;
				}
				
			}
			if(e.getSource()==p1_r_btn&&p2_r) {
				if(p2_attack) {
					msg.setText(".....Player2's right hand -> Player1's right hand.....");
					
					p1_l_btn.setEnabled(false);
					p1_r_btn.setEnabled(false);
					p2_l_btn.setEnabled(false);
					p2_r_btn.setEnabled(false);
					
					P2_R_R_HIT();
					
					p1_split=false;
					p2_split=false;
					p1_attack=false;
					p2_attack=false;
					attack=false;
					t=true;
				}
				
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
		p1_l_h.add(1);
		p1_r_h.add(1);
		p2_l_h.add(1);
		p2_r_h.add(1);
			delay(1000);
			loading();
			msg.setText("...Randomly Choosing the turn!...");
			delay(2000);
			loading();
			if(r.nextInt(2)==0) {
				loading();
				turn=true;
			}
			else {
				loading();
				turn=false;
			}
			
			while(running) {
				if(p1_l_h.isEmpty() && p1_r_h.isEmpty()) {
					p2_won();
				}
				else if(p2_l_h.isEmpty() && p2_r_h.isEmpty())	{
					p1_won();
				}
				else {
					if(turn) {
						p1_turn();
						t=true;
						while(t) {
							delay(1000);
						}
						repaint();
						if(p1_l_h.isEmpty() && p1_r_h.isEmpty()) {
							p2_won();
						}
						else if(p2_l_h.isEmpty() && p2_r_h.isEmpty())	{
							p1_won();
						}
						else {
							turn=false;
						}
						
					}
					else {
						p2_turn();
						t=false;
						while(!t) {
							delay(1000);
						}
						repaint();
						if(p1_l_h.isEmpty() && p1_r_h.isEmpty()) {
							p2_won();
						}
						else if(p2_l_h.isEmpty() && p2_r_h.isEmpty())	{
							p1_won();
						}
						else {
							turn=true;
						}
						
						
					}
				}
			}		
		} 
	
		
	public void p1_won() {
		loading();
		p2_l_btn.setVisible(false);
		p2_r_btn.setVisible(false);
		p1_l_btn.setVisible(false);
		p1_r_btn.setVisible(false);
		msg.setVisible(false);
		w_l.setVisible(true);
		w_l.setText("(: Player1 won! :)");
		delay(1000);
		int O=JOptionPane.showConfirmDialog(pvp_frm, "want to play again ?", "Two players", 
											JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE);
		if(JOptionPane.YES_OPTION==O) {
			running=false;
			pvp_frm.dispose();
			try {
				new PWC();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(JOptionPane.NO_OPTION==O) {
			running=false;
			pvp_frm.dispose();
			try {
				new ChopStick().main_frm.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			running=false;
		}
	}
	
	public void p2_won() {
		loading();
		p2_l_btn.setVisible(false);
		p2_r_btn.setVisible(false);
		p1_l_btn.setVisible(false);
		p1_r_btn.setVisible(false);
		msg.setVisible(false);
		w_l.setVisible(true);
		w_l.setText("(: Player2 Won! :)");
		delay(1000);
		int O=JOptionPane.showConfirmDialog(pvp_frm, "want to play again ?", "Two players", 
										JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE , null);
		if(JOptionPane.YES_OPTION==O) {
			running=false;
			pvp_frm.dispose();
			try {
				new PWC();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(JOptionPane.NO_OPTION==O) {
			running=false;
			pvp_frm.dispose();
			try {
				new ChopStick().main_frm.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			running=false;
		}
	}
	
	
		//player1
		public void P1_L_L_HIT() {
			x=p1_l_h.size();
		    for(i=0;i<x;i++) {
		    	if(p2_l_h.size()==5) {
		    		p2_l_h.removeAllElements();
		    		cp_l_5_0();
		    	}
		    	p2_l_h.add(1);
		    }
		    if(p2_l_h.size()==5) {
	    		p2_l_h.removeAllElements();
		    } 	
		    
		    if(p2_l_h.size()==1) {
		    	cp_l_1();
		    }
		    else if(p2_l_h.size()==2) {
		    	cp_l_2();
		    }
		    else if(p2_l_h.size()==3) { 
		    	cp_l_3();
		    }
		    else if(p2_l_h.size()==4) {
		    	cp_l_4();
		    }
		}
	
		public void P1_L_R_HIT() {
			x=p1_l_h.size();
		    for(i=0;i<x;i++) {
		    	if(p2_r_h.size()==5) {
		    		p2_r_h.removeAllElements();
		    		cp_r_5_0();
		    	}
		    	p2_r_h.add(1);
		    }
		    if(p2_r_h.size()==5) {
	    		p2_r_h.removeAllElements();
		    } 	
		    
		    if(p2_r_h.size()==1) {
		    	cp_r_1();
		    }
		    else if(p2_r_h.size()==2) {
		    	cp_r_2();
		    }
		    else if(p2_r_h.size()==3) { 
		    	cp_r_3();
		    }
		    else if(p2_r_h.size()==4) {
		    	cp_r_4();
		    }
		}

		public void P1_R_L_HIT() {
			x=p1_r_h.size();
		    for(i=0;i<x;i++) {
		    	if(p2_l_h.size()==5) {
		    		p2_l_h.removeAllElements();
		    		cp_l_5_0();
		    	}
		    	p2_l_h.add(1);
		    }
		    if(p2_l_h.size()==5) {
	    		p2_l_h.removeAllElements();
		    } 	
		    
		    if(p2_l_h.size()==1) {
		    	cp_l_1();
		    }
		    else if(p2_l_h.size()==2) {
		    	cp_l_2();
		    }
		    else if(p2_l_h.size()==3) { 
		    	cp_l_3();
		    }
		    else if(p2_l_h.size()==4) {
		    	cp_l_4();
		    }
		}
	
		public void P1_R_R_HIT() {
			x=p1_r_h.size();
		    for(i=0;i<x;i++) {
		    	if(p2_r_h.size()==5) {
		    		p2_r_h.removeAllElements();
		    		cp_r_5_0();
		    	}
		    	p2_r_h.add(1);
		    }
		    if(p2_r_h.size()==5) {
	    		p2_r_h.removeAllElements();
		    } 	
		    
		    if(p2_r_h.size()==1) {
		    	cp_r_1();
		    }
		    else if(p2_r_h.size()==2) {
		    	cp_r_2();
		    }
		    else if(p2_r_h.size()==3) { 
		    	cp_r_3();
		    }
		    else if(p2_r_h.size()==4) {
		    	cp_r_4();
		    }
		}
	
		//player2
		public void P2_L_L_HIT()
		{
			x=p2_l_h.size();
		    for(i=0;i<x;i++) {
		    	if(p1_l_h.size()==5) {
		    		p1_l_h.removeAllElements();
		    		p_l_5_0();
		    	}
		    	p1_l_h.add(1);
		    }
		    if(p1_l_h.size()==5) {
	    		p1_l_h.removeAllElements();
		    } 	
		    
		    if(p1_l_h.size()==1) {
		    	p_l_1();
		    }
		    else if(p1_l_h.size()==2) {
		    	p_l_2();
		    }
		    else if(p1_l_h.size()==3) { 
		    	p_l_3();
		    }
		    else if(p1_l_h.size()==4) {
		    	p_l_4();
		    }
		}
		
		public void P2_L_R_HIT()
		{
			x=p2_l_h.size();
		    for(i=0;i<x;i++) {
		    	if(p1_r_h.size()==5) {
		    		p1_r_h.removeAllElements();
		    		p_r_5_0();
		    	}
		    	p1_r_h.add(1);
		    }
		    if(p1_r_h.size()==5) {
	    		p1_r_h.removeAllElements();
		    }
		    
		    
		    if(p1_r_h.size()==1) {
		    	p_r_1();
		    }
		    else if(p1_r_h.size()==2) {
		    	p_r_2();
		    }
		    else if(p1_r_h.size()==3) {  
		    	p_r_3();
		    }
		    else if(p1_r_h.size()==4) {
		    	p_r_4();
		    }
		}
		
		public void P2_R_L_HIT()
		{
			x=p2_r_h.size();
		    for(i=0;i<x;i++) {
		    	if(p1_l_h.size()==5) {
		    		p1_l_h.removeAllElements();
		    		p_l_5_0();
		    	}
		    	p1_l_h.add(1);
		    }
		    if(p1_l_h.size()==5) {
	    		p1_l_h.removeAllElements();
		    }
		    
		    
		    if(p1_l_h.size()==1) {
		    	p_l_1();
		    }
		    else if(p1_l_h.size()==2) {
		    	p_l_2();
		    }
		    else if(p1_l_h.size()==3) { 
		    	p_l_3();
		    }
		    else if(p1_l_h.size()==4) {
		    	p_l_4();
		    }
		}
		
		public void P2_R_R_HIT()
		{
			x=p2_r_h.size();
			
		    for(i=0;i<x;i++) {
		    	if(p1_r_h.size()==5) {
		    		p1_r_h.removeAllElements();
		    		p_r_5_0();
		    	}
		    	p1_r_h.add(1);
		    }
		    if(p1_r_h.size()==5) {
	    		p1_r_h.removeAllElements();
		    }
		    
		    if(p1_r_h.size()==1) {
		    	p_r_1();
		    }
		    else if(p1_r_h.size()==2) {
		    	p_r_2();
		    }
		    else if(p1_r_h.size()==3) {  
		    	p_r_3();
		    }
		    else if(p1_r_h.size()==4) {
		    	p_r_4();
		    	
		    }
		}
		
	public void p1_turn() {
		p1_attack=true;
		p1_split=true;
		
		loading();
		msg.setText(".....Player1's turn.....");
		delay(1000);
		
		l_pnl.setBackground(Color.white);
		delay(500);
		l_pnl.setBackground(new Color(30, 144, 255));
		
		if(p1_l_h.size()==0&&p1_r_h.size()==1) {
			msg.setText(".....You can only attack.....");
			attack_btn.setVisible(true);
			split_btn.setVisible(true);
			split_btn.setEnabled(false);
		}
		else if(p1_r_h.size()==0&&p1_l_h.size()==1) {
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
	
	public void p2_turn() {
		p2_attack=true;
		p2_split=true;
		
		loading();
		msg.setText(".....Player2's turn.....");
		delay(1000);
		
		r_pnl.setBackground(Color.white);
		delay(500);
		r_pnl.setBackground(new Color(30, 144, 255));
		
		if(p2_l_h.size()==0&&p2_r_h.size()==1) {
			msg.setText(".....You can only attack.....");
			attack_btn.setVisible(true);
			split_btn.setVisible(true);
			split_btn.setEnabled(false);
		}
		else if(p2_r_h.size()==0&&p2_l_h.size()==1) {
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
	
	public void p1_split() {

		if(p1_l_h.size()==0) {
	    	p_l_0();
	    }
	    else if(p1_l_h.size()==1) {
	    	p_l_1();
	    }
	    else if(p1_l_h.size()==2) {
	    	p_l_2();
	    }
	    else if(p1_l_h.size()==3) { 
	    	p_l_3();
	    }
	    else if(p1_l_h.size()==4) { 
	    	p_l_4();
	    }
		
		if(p1_r_h.size()==0) {
			p_r_0();
	    }
	    else if(p1_r_h.size()==1) {
	    	p_r_1();
	    }
	    else if(p1_r_h.size()==2) {
	    	p_r_2();
	    }
	    else if(p1_r_h.size()==3) { 
	    	p_r_3();
	    }
	    else if(p1_r_h.size()==4) { 
	    	p_r_4();
	    }
	}
	
	public void p2_split() {
		if(p2_l_h.size()==0) {
	    	cp_l_0();
	    }
	    else if(p2_l_h.size()==1) {
	    	cp_l_1();
	    }
	    else if(p2_l_h.size()==2) {
	    	cp_l_2();
	    }
	    else if(p2_l_h.size()==3) { 
	    	cp_l_3();
	    }
	    else if(p2_l_h.size()==4) { 
	    	cp_l_4();
	    }
		
		if(p2_r_h.size()==0) {
			cp_r_0();
	    }
	    else if(p2_r_h.size()==1) {
	    	cp_r_1();
	    }
	    else if(p2_r_h.size()==2) {
	    	cp_r_2();
	    }
	    else if(p2_r_h.size()==3) { 
	    	cp_r_3();
	    }
	    else if(p2_r_h.size()==4) { 
	    	cp_r_4();
	    }
	}
	//p2_l_fingers
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
	
	
	//p2_r_fingers
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
		
		if(p2_l_h.size()==0) {
	    	cp_l_5_0();
	    }
	    else if(p2_l_h.size()==1) {
	    	cp_l_1();
	    }
	    else if(p2_l_h.size()==2) {
	    	cp_l_2();
	    }
	    else if(p2_l_h.size()==3) { 
	    	cp_l_3();
	    }
	    else if(p2_l_h.size()==4) { 
	    	cp_l_4();
	    }
		
		
		if(p2_r_h.size()==0) {
			cp_r_5_0();
	    }
	    else if(p2_r_h.size()==1) {
	    	cp_r_1();
	    }
	    else if(p2_r_h.size()==2) {
	    	cp_r_2();
	    }
	    else if(p2_r_h.size()==3) { 
	    	cp_r_3();
	    }
	    else if(p2_r_h.size()==4) { 
	    	cp_r_4();
	    }
		
		
		if(p1_l_h.size()==0) {
	    	p_l_5_0();
	    }
	    else if(p1_l_h.size()==1) {
	    	p_l_1();
	    }
	    else if(p1_l_h.size()==2) {
	    	p_l_2();
	    }
	    else if(p1_l_h.size()==3) { 
	    	p_l_3();
	    }
	    else if(p1_l_h.size()==4) { 
	    	p_l_4();
	    }
		
		
		if(p1_r_h.size()==0) {
			p_r_5_0();
	    }
	    else if(p1_r_h.size()==1) {
	    	p_r_1();
	    }
	    else if(p1_r_h.size()==2) {
	    	p_r_2();
	    }
	    else if(p1_r_h.size()==3) { 
	    	p_r_3();
	    }
	    else if(p1_r_h.size()==4) { 
	    	p_r_4();
	    }
	}
}
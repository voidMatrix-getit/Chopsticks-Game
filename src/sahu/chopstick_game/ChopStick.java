package sahu.chopstick_game;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.MediaTracker;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;

import org.apache.commons.mail.EmailException;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;



@SuppressWarnings({ "unused", "removal" })
public class ChopStick implements MouseListener,Runnable,ActionListener,KeyListener {

	public JFrame main_frm;
	
	public JButton pwc_btn,
	 			   pvp_btn,
	 			   Howtoplay,
	 			   Settings,
	 			  Aboutgame,
	 			   feedback,
	 			   profile,
	 			   signout;
	
	public JLabel name,
				  playerpic,
				  main_lbl;
	public static JLabel vm;
	
	public boolean fb;//send feedback
	public static boolean t2=true;//label text animation
	
	public String s;//feedback string
	
	static BufferedReader won=null,lost=null,player=null;
	static BufferedWriter player0=null,wset;
	
    public static Clip c=null;
    
	public static void main(String[] args) throws Exception{
					
					
					
					Launch l=new Launch();
					try {
						ChopStick.bgmusic();
					}catch(Exception e) {
						JOptionPane.showMessageDialog(l, e.getMessage());
					}
					Thread.sleep(10);
					l.setVisible(true);
					wset=new BufferedWriter(new FileWriter("settings.txt"));
					l.prog();
					Thread.sleep(10);
					l.dispose();
					File f=new File("player.txt");
					if(f.exists()) {
						t2=false;
						new ChopStick().main_frm.setVisible(true);
					}
					else {
						new Void_matrix_account();
					}
					while(t2) {
						Thread.sleep(1);
					}
					
					paint();
	}
	
	public static void bgmusic() throws Exception{
		InputStream f=ChopStick.class.getResourceAsStream("We Got What You Want.wav");
		InputStream bf=new BufferedInputStream(f);
		AudioInputStream ai=AudioSystem.getAudioInputStream(bf);
		c=AudioSystem.getClip();
		c.open(ai);
		c.start();
		c.loop(100);
	}
	
	static void paint() throws Exception{
		Random r=new Random();
		while(true) {
			int n1=r.nextInt(256);
			int n2=r.nextInt(256);
			int n3=r.nextInt(256);
			vm.setForeground(new Color(n1,n2,n3));
			Thread.sleep(100);
		}
	}
	
	public ChopStick() throws Exception {
		initialize();
		new Thread(this).start();
		
	}
	
	@Override
	public void run() {
		Send();
	}
	
	public void Send() {
		fb=true;
		while(fb) {
			try { Thread.sleep(1000); } catch(Exception e) { e.printStackTrace(); }
		}
		try {
			Mails.getFeedBack(s,name.getText());
			Send();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initialize() throws Exception {
		main_frm = new JFrame();
		main_frm.setResizable(false);
		main_frm.getContentPane().setBackground(new Color(153, 102, 255));
		main_frm.getContentPane().setLayout(null);
		main_frm.setForeground(Color.BLACK);
		main_frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo_F.png")));
		main_frm.setBackground(Color.BLACK);
		main_frm.setFont(new Font("Dialog", Font.BOLD, 20));
		main_frm.setTitle("ChopStick");
		main_frm.setBounds(150, 20, 1250, 785);
		main_frm.getContentPane().setFocusable(true);
		main_frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pwc_btn = new JButton("Play Against Computer");
		pwc_btn.addActionListener(this);
		pwc_btn.addMouseListener(this);
		pwc_btn.addKeyListener(this);
		pwc_btn.setToolTipText("by clicking on this you will have the game started playing against computer");
		pwc_btn.setForeground(new Color(0, 0, 0));
		pwc_btn.setFont(new Font("Tahoma", Font.BOLD, 20));
		pwc_btn.setBackground(UIManager.getColor("Button.light"));
		pwc_btn.setBounds(98, 76, 253, 76);
		pwc_btn.setFocusable(false);
		pwc_btn.setBorder(new BevelBorder(BevelBorder.RAISED, 
										  new Color(0, 153, 255), new Color(0, 255, 255), 
										  new Color(51, 153, 255), new Color(51, 255, 255)));
		main_frm.getContentPane().add(pwc_btn);
		
		pvp_btn = new JButton("Two Players");
		pvp_btn.addActionListener(this);
		pvp_btn.addMouseListener(this);
		pvp_btn.addKeyListener(this);
		pvp_btn.setToolTipText("by clicking on this two players can play against each other");
		pvp_btn.setForeground(new Color(0, 0, 0));
		pvp_btn.setFont(new Font("Tahoma", Font.BOLD, 20));
		pvp_btn.setBackground(UIManager.getColor("Button.light"));
		pvp_btn.setBounds(98, 336, 253, 76);
		pvp_btn.setFocusable(false);
		pvp_btn.setBorder(new BevelBorder(BevelBorder.RAISED, 
										  new Color(0, 153, 255), new Color(0, 255, 255), 
										  new Color(51, 153, 255), new Color(51, 255, 255)));
		main_frm.getContentPane().add(pvp_btn);
		
		Howtoplay = new JButton("How To Play");
		Howtoplay.addActionListener(this);
		Howtoplay.addMouseListener(this);
		Howtoplay.addKeyListener(this);
		Howtoplay.setToolTipText("give a look at rules for better understanding of gameplay");
		Howtoplay.setForeground(Color.BLACK);
		Howtoplay.setFont(new Font("Tahoma", Font.BOLD, 20));
		Howtoplay.setFocusable(false);
		Howtoplay.setBorder(new BevelBorder(BevelBorder.RAISED, 
											new Color(0, 153, 255), new Color(0, 255, 255), 
											new Color(51, 153, 255), new Color(51, 255, 255)));
		Howtoplay.setBackground(UIManager.getColor("Button.light"));
		Howtoplay.setBounds(98, 591, 253, 76);
		main_frm.getContentPane().add(Howtoplay);
		
		feedback = new JButton("Feedback");
		feedback.addActionListener(this);
		feedback.addMouseListener(this);
		feedback.addKeyListener(this);
		feedback.setForeground(Color.BLACK);
		feedback.setFont(new Font("Tahoma", Font.BOLD, 20));
		feedback.setFocusable(false);
		feedback.setBorder(new BevelBorder(BevelBorder.RAISED, 
										   new Color(0, 153, 255), new Color(0, 255, 255), 
										   new Color(51, 153, 255), new Color(51, 255, 255)));
		feedback.setBackground(UIManager.getColor("Button.light"));
		feedback.setBounds(878, 591, 253, 76);
		feedback.setToolTipText("let me know what i can improve");
		main_frm.getContentPane().add(feedback);
		
		Settings = new JButton("Settings");
		Settings.addActionListener(this);
		Settings.addMouseListener(this);
		Settings.addKeyListener(this);
		Settings.setForeground(Color.BLACK);
		Settings.setFont(new Font("Tahoma", Font.BOLD, 20));
		Settings.setFocusable(false);
		Settings.setBorder(new BevelBorder(BevelBorder.RAISED, 
										   new Color(0, 153, 255), new Color(0, 255, 255), 
										   new Color(51, 153, 255), new Color(51, 255, 255)));
		Settings.setBackground(UIManager.getColor("Button.light"));
		Settings.setBounds(878, 76, 253, 76);
		main_frm.getContentPane().add(Settings);
		
		Aboutgame = new JButton("About Game");
		Aboutgame.addActionListener(this);
		Aboutgame.addMouseListener(this);
		Aboutgame.addKeyListener(this);
		Aboutgame.setToolTipText("let's get your hands dirty in exploring this game");
		Aboutgame.setForeground(Color.BLACK);
		Aboutgame.setFont(new Font("Tahoma", Font.BOLD, 20));
		Aboutgame.setFocusable(false);
		Aboutgame.setBorder(new BevelBorder(BevelBorder.RAISED, 
										    new Color(0, 153, 255), new Color(0, 255, 255), 
										    new Color(51, 153, 255), new Color(51, 255, 255)));
		Aboutgame.setBackground(UIManager.getColor("Button.light"));
		Aboutgame.setBounds(877, 336, 253, 76);
		main_frm.getContentPane().add(Aboutgame);
		
		profile = new JButton("Profile");
		profile.addActionListener(this);
		profile.addMouseListener(this);
		profile.addKeyListener(this);
		profile.setForeground(Color.BLACK);
		profile.setFont(new Font("Tahoma", Font.BOLD, 20));
		profile.setFocusable(false);
		profile.setBorder(new BevelBorder(BevelBorder.RAISED, 
										  new Color(0, 153, 255), new Color(0, 255, 255), 
										  new Color(51, 153, 255), new Color(51, 255, 255)));
		profile.setBackground(UIManager.getColor("Button.light"));
		profile.setBounds(485, 444, 253, 76);
		profile.setToolTipText("let's see what you're up to");
		main_frm.getContentPane().add(profile);
		
		signout = new JButton("Sign Out");
		signout.addActionListener(this);
		signout.addMouseListener(this);
		signout.addKeyListener(this);
		signout.setToolTipText("");
		signout.setForeground(Color.BLACK);
		signout.setFont(new Font("Tahoma", Font.BOLD, 20));
		signout.setFocusable(false);
		signout.setBorder(new BevelBorder(BevelBorder.RAISED, 
										  new Color(0, 153, 255), new Color(0, 255, 255), 
										  new Color(51, 153, 255), new Color(51, 255, 255)));
		signout.setBackground(UIManager.getColor("Button.light"));
		signout.setBounds(485, 30, 253, 76);
		main_frm.getContentPane().add(signout);
		
		player=new BufferedReader(new FileReader("player.txt"));
		name = new JLabel(player.readLine());
		player.close();
		name.setFont(new Font("Tahoma", Font.BOLD, 25));
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setBounds(354, 196, 502, 86);
		main_frm.getContentPane().add(name);
		
		playerpic = new JLabel();
		playerpic.setIcon(new ImageIcon(getClass().getResource("user3.png")));
		playerpic.setHorizontalAlignment(SwingConstants.CENTER);
		playerpic.setBounds(502, 261, 212, 182);
		main_frm.getContentPane().add(playerpic);
		
		vm = new JLabel("Void_Matrix[:]");
		vm.setFont(new Font("Tahoma", Font.BOLD, 27));
		vm.setForeground(Color.BLACK);
		vm.setHorizontalAlignment(SwingConstants.CENTER);
		vm.setBounds(485, 659, 263, 61);
		main_frm.getContentPane().add(vm);
		
		main_lbl = new JLabel();
		main_lbl.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		main_lbl.setForeground(new Color(153, 0, 102));
		main_lbl.setIcon(new ImageIcon(getClass().getResource("chopstick_main_cs.jpg")));
		main_lbl.setBounds(0, 0, 1236, 750);
		main_lbl.addMouseListener(this);
		main_frm.getContentPane().add(main_lbl);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==pwc_btn)
		{
			main_frm.dispose();
			try {
				new PWC();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		if(e.getSource()==pvp_btn)
		{
			main_frm.dispose();
			try {
				new PVP();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		if(e.getSource()==Howtoplay)
		{
			main_frm.dispose();
			new How_To_Play();
		}
		
		if(e.getSource()==Settings)
		{
			main_frm.dispose();
			new Settings();
		}
		
		if(e.getSource()==Aboutgame)
		{
			main_frm.dispose();
			new About_Game();
		}
		
		if(e.getSource()==feedback)
		{
			s=JOptionPane.showInputDialog(feedback, 
					"Tell us about your experience\n or any suggetion", "Feedback",
					JOptionPane.QUESTION_MESSAGE);
			fb=false;
		}
		
		if(e.getSource()==profile)
		{
			try {
				won=new BufferedReader(new FileReader("won.txt"));
				lost=new BufferedReader(new FileReader("lost.txt"));
				int iw=(int)won.read(),il=(int)lost.read(),it=iw+il;
				String w=String.valueOf(iw),l=String.valueOf(il),t=String.valueOf(it);
				JOptionPane.showMessageDialog(profile, name.getText()+
						"\n(Against Computer)\nWon: "+w+"\nLost: "+l+"\nTotal: "+t, "Profile", 
						JOptionPane.INFORMATION_MESSAGE);
				won.close();
				lost.close();
			} catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		
		if(e.getSource()==signout)
		{
			int x=JOptionPane.showConfirmDialog(main_frm, 
					"Are You Sure You wanna Sign Out!", "Sign Out", 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(x==0) {
				main_frm.dispose();
				new Void_matrix_account();
			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
		if(e.getSource()==pwc_btn)
		{
			pwc_btn.setBackground(Color.black);
			pwc_btn.setForeground(Color.green);
		}
		if(e.getSource()==pvp_btn)
		{
			pvp_btn.setBackground(Color.black);
			pvp_btn.setForeground(Color.green);
		}
		if(e.getSource()==Howtoplay)
		{
			Howtoplay.setBackground(Color.black);
			Howtoplay.setForeground(Color.green);
		}
		if(e.getSource()==Settings)
		{
			Settings.setBackground(Color.black);
			Settings.setForeground(Color.green);
		}
		if(e.getSource()==Aboutgame)
		{
			Aboutgame.setBackground(Color.black);
			Aboutgame.setForeground(Color.green);
		}
		if(e.getSource()==profile)
		{
			profile.setBackground(Color.black);
			profile.setForeground(Color.green);
		}
		if(e.getSource()==feedback)
		{
			feedback.setBackground(Color.black);
			feedback.setForeground(Color.green);
		}
		if(e.getSource()==signout)
		{
			signout.setBackground(Color.black);
			signout.setForeground(Color.green);
		}
	}


	@Override
	public void mouseExited(MouseEvent e) {
		
		if(e.getSource()==pwc_btn)
		{
			pwc_btn.setBackground(UIManager.getColor("Button.light"));
			pwc_btn.setForeground(Color.black);
		}
		if(e.getSource()==pvp_btn)
		{
			pvp_btn.setBackground(UIManager.getColor("Button.light"));
			pvp_btn.setForeground(Color.black);
		}
		if(e.getSource()==Howtoplay)
		{
			Howtoplay.setBackground(UIManager.getColor("Button.light"));
			Howtoplay.setForeground(Color.black);
		}
		if(e.getSource()==Settings)
		{
			Settings.setBackground(UIManager.getColor("Button.light"));
			Settings.setForeground(Color.black);
		}
		if(e.getSource()==Aboutgame)
		{
			Aboutgame.setBackground(UIManager.getColor("Button.light"));
			Aboutgame.setForeground(Color.black);
		}
		if(e.getSource()==profile)
		{
			profile.setBackground(UIManager.getColor("Button.light"));
			profile.setForeground(Color.black);
		}
		if(e.getSource()==feedback)
		{
			feedback.setBackground(UIManager.getColor("Button.light"));
			feedback.setForeground(Color.black);
		}
		if(e.getSource()==signout)
		{
			signout.setBackground(UIManager.getColor("Button.light"));
			signout.setForeground(Color.black);
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
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
}

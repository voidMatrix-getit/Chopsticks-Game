package sahu.chopstick_game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.apache.commons.mail.EmailException;

import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;

@SuppressWarnings({ "serial", "unused" })
public class How_To_Play extends JFrame implements MouseListener,ActionListener{
	private JPanel p1,p2,p3,p4,p5;
	
	private JButton n1,n2,n3,n4,
					back,back2,
					pr1,pr2,pr3,pr4;
	
	private JLabel l1,l2,l3,l4,l5,
				   cnt1,cnt2,cnt3,cnt4,cnt5;
	
	private JTextPane t1,t2,t3,t4,t5;
	
	public How_To_Play() {
		setResizable(false);
		setForeground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo_F.png")));
		setBackground(Color.BLACK);
		setFont(new Font("Dialog", Font.BOLD, 20));
		setTitle("How To Play");
		setBounds(150, 20, 1250, 785);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		p1 = new JPanel();
		p1.setBackground(new Color(255, 204, 255));
		p1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		p1.setBounds(0, 0, 1236, 748);
		getContentPane().add(p1);
		p1.setLayout(null);
		
		n1 = new JButton("Next");
		n1.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.BLACK));
		n1.addMouseListener(this);
		n1.addActionListener(this);
		n1.setFocusable(false);
		n1.setFont(new Font("Dialog", Font.BOLD, 30));
		n1.setForeground(Color.black);
		n1.setBackground(UIManager.getColor("Button.light"));
		n1.setBounds(1098, 694, 128, 49);
		p1.add(n1);
		
		l1 = new JLabel();
		l1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		l1.setIcon(new ImageIcon(getClass().getResource("rule1.jpg")));
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBounds(234, 0, 732, 455);
		p1.add(l1);
		
		t1 = new JTextPane();
		t1.setBorder(new EtchedBorder(EtchedBorder.RAISED, 
									  UIManager.getColor("Button.focus"), 
									  UIManager.getColor("Button.foreground")));
		t1.setEditable(false);
		t1.setText("This official set of rules is called rollover where five fingers are "
				+ "subtracted should a hand's sum exceeds 5 as described further.\r\n"
				+ "Each player begins with one finger raised on each hand. "
				+ "After the first player turns proceed clockwise.\r\n"
				+ "On a player's turn, they must either attack or split. "
				+ "There are two types of splits, transfers and divisions.");
		t1.setBackground(UIManager.getColor("Button.highlight"));
		t1.setForeground(SystemColor.textText);
		t1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		t1.setBounds(10, 456, 1216, 234);
		p1.add(t1);
		
		back = new JButton("Back");
		back.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.BLACK));
		back.addMouseListener(this);
		back.addActionListener(this);
		back.setForeground(Color.black);
		back.setFont(new Font("Dialog", Font.BOLD, 30));
		back.setFocusable(false);
		back.setBackground(UIManager.getColor("Button.light"));
		back.setBounds(10, 694, 128, 49);
		p1.add(back);
		
		cnt1 = new JLabel("1");
		cnt1.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		cnt1.setOpaque(true);
		cnt1.setFont(new Font("Tahoma", Font.BOLD, 30));
		cnt1.setForeground(Color.white);
		cnt1.setBackground(Color.BLACK);
		cnt1.setHorizontalAlignment(SwingConstants.CENTER);
		cnt1.setBounds(556, 692, 98, 54);
		p1.add(cnt1);
	
		p2 = new JPanel();
		p2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		p2.setBackground(new Color(255, 204, 255));
		p2.setBounds(0, 0, 1236, 748);
		getContentPane().add(p2);
		p2.setLayout(null);
		
		cnt2 = new JLabel("2");
		cnt2.setOpaque(true);
		cnt2.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		cnt2.setFont(new Font("Tahoma", Font.BOLD, 30));
		cnt2.setForeground(Color.WHITE);
		cnt2.setBackground(Color.black);
		cnt2.setHorizontalAlignment(SwingConstants.CENTER);
		cnt2.setBounds(556, 692, 98, 54);
		p2.add(cnt2);
		
		pr1 = new JButton("Previous");
		pr1.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.BLACK));
		pr1.addMouseListener(this);
		pr1.addActionListener(this);
		pr1.setFocusable(false);
		pr1.setFont(new Font("Dialog", Font.BOLD, 30));
		pr1.setForeground(Color.black);
		pr1.setBackground(UIManager.getColor("Button.light"));
		pr1.setBounds(10, 694, 159, 49);
		p2.add(pr1);
		
		n2 = new JButton("Next");
		n2.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.BLACK)); 
		n2.addMouseListener(this);
		n2.addActionListener(this);
		n2.setFocusable(false);
		n2.setFont(new Font("Dialog", Font.BOLD, 30));
		n2.setForeground(Color.black);
		n2.setBackground(UIManager.getColor("Button.light"));
		n2.setBounds(1098, 694, 128, 49);
		p2.add(n2);
		
		l2 = new JLabel();
		l2.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		l2.setBounds(234, 0, 732, 472);
		l2.setIcon(new ImageIcon(getClass().getResource("rule2.jpg")));
		p2.add(l2);
		
		t2 = new JTextPane();
		t2.setBorder(new EtchedBorder(EtchedBorder.RAISED, 
				  UIManager.getColor("Button.focus"), 
				  UIManager.getColor("Button.foreground")));
		t2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		t2.setText("To attack, a player uses one of their live hands to strike an opponent's live hand."
				+ " The number of fingers on the opponent's struck hand will increase by the number of fingers on the hand used to strike.\r\n"
				+ "You will then take "
				+ "turns going back and forth. On each turn, one player will use one hand to tap one of their opponent's hand.\r\n");
		t2.setForeground(SystemColor.textText);
		t2.setBackground(UIManager.getColor("Button.highlight"));
		t2.setEditable(false);
		t2.setBounds(10, 475, 1216, 214);
		p2.add(t2);
		
		p3 = new JPanel();
		p3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		p3.setBackground(new Color(255, 204, 255));
		p3.setBounds(0, 0, 1236, 748);
		getContentPane().add(p3);
		p3.setLayout(null);
		
		cnt3 = new JLabel("3");
		cnt3.setOpaque(true);
		cnt3.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		cnt3.setFont(new Font("Tahoma", Font.BOLD, 30));
		cnt3.setForeground(Color.WHITE);
		cnt3.setBackground(new Color(0, 0, 0));
		cnt3.setHorizontalAlignment(SwingConstants.CENTER);
		cnt3.setBounds(556, 692, 98, 54);
		p3.add(cnt3);
		
		pr2 = new JButton("Previous");
		pr2.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.BLACK));
		pr2.addMouseListener(this);
		pr2.addActionListener(this);
		pr2.setFocusable(false);
		pr2.setFont(new Font("Dialog", Font.BOLD, 30));
		pr2.setForeground(Color.black);
		pr2.setBackground(UIManager.getColor("Button.light"));
		pr2.setBounds(10, 694, 149, 49);
		p3.add(pr2);
		
		n3 = new JButton("Next");
		n3.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.BLACK));
		n3.addMouseListener(this);
		n3.addActionListener(this);
		n3.setFocusable(false);
		n3.setFont(new Font("Dialog", Font.BOLD, 30));
		n3.setForeground(Color.black);
		n3.setBackground(UIManager.getColor("Button.light"));
		n3.setBounds(1098, 694, 128, 49);
		p3.add(n3);
		
		l3 = new JLabel();
		l3.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		l3.setBounds(234, 0, 732, 469);
		l3.setIcon(new ImageIcon(getClass().getResource("rule3.jpg")));
		p3.add(l3);
		
		t3 = new JTextPane();
		t3.setBorder(new EtchedBorder(EtchedBorder.RAISED, 
				  UIManager.getColor("Button.focus"), 
				  UIManager.getColor("Button.foreground")));
		t3.setText("A hand is live if it has at least one finger, and this is indicated by raising at least one finger. "
				+ "\r\nIf any hand of any player reaches exactly five fingers, then the hand is dead.\r\n"
				+ "If you go over 5 you subtract the sum of all of the numbers by 5\r\n"
				+ "Continue playing until one player has lost both of their hands. \r\n");
		t3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		t3.setForeground(SystemColor.textText);
		t3.setBackground(UIManager.getColor("Button.highlight"));
		t3.setEditable(false);
		t3.setBounds(10, 475, 1216, 213);
		p3.add(t3);
		
		p4 = new JPanel();
		p4.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		p4.setBackground(new Color(255, 204, 255));
		p4.setBounds(0, 0, 1236, 748);
		getContentPane().add(p4);
		p4.setLayout(null);
		
		cnt4 = new JLabel("4");
		cnt4.setOpaque(true);
		cnt4.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		cnt4.setFont(new Font("Tahoma", Font.BOLD, 30));
		cnt4.setForeground(Color.WHITE);
		cnt4.setBackground(new Color(0, 0, 0));
		cnt4.setHorizontalAlignment(SwingConstants.CENTER);
		cnt4.setBounds(556, 692, 98, 54);
		p4.add(cnt4);
		
		pr3 = new JButton("Previous");
		pr3.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.BLACK));
		pr3.addMouseListener(this);
		pr3.addActionListener(this);
		pr3.setFocusable(false);
		pr3.setFont(new Font("Dialog", Font.BOLD, 30));
		pr3.setForeground(Color.black);
		pr3.setBackground(UIManager.getColor("Button.light"));
		pr3.setBounds(10, 694, 149, 49);
		p4.add(pr3);
		
		n4 = new JButton("Next");
		n4.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.BLACK));
		n4.addMouseListener(this);
		n4.addActionListener(this);
		n4.setFocusable(false);
		n4.setFont(new Font("Dialog", Font.BOLD, 30));
		n4.setForeground(Color.black);
		n4.setBackground(UIManager.getColor("Button.light"));
		n4.setBounds(1098, 694, 128, 49);
		p4.add(n4);
		
		l4 = new JLabel();
		l4.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		l4.setHorizontalAlignment(SwingConstants.CENTER);
		l4.setBounds(234, 0, 732, 473);
		l4.setIcon(new ImageIcon(getClass().getResource("rule4.jpg")));
		p4.add(l4);
		
		t4 = new JTextPane();
		t4.setBorder(new EtchedBorder(EtchedBorder.RAISED, 
				  UIManager.getColor("Button.focus"), 
				  UIManager.getColor("Button.foreground")));
		t4.setText("If a hand has zero fingers, the hand is dead, and this is indicated by "
				+ "raising zero fingers (i.e. a closed fist).\r\n"
				+ "A player with two dead hands is eliminated from the game.\r\n"
				+ "The goal is to be the last one standing with at least one hand left still alive.\r\n"
				+ "A player wins once all opponents are eliminated.");
		t4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		t4.setForeground(SystemColor.textText);
		t4.setBackground(UIManager.getColor("Button.highlight"));
		t4.setEditable(false);
		t4.setBounds(10, 475, 1216, 214);
		p4.add(t4);
		
		p5 = new JPanel();
		p5.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		p5.setBackground(new Color(255, 204, 255));
		p5.setBounds(0, 0, 1236, 748);
		getContentPane().add(p5);
		p5.setLayout(null);
		
		cnt5 = new JLabel("5");
		cnt5.setOpaque(true);
		cnt5.setBorder(new LineBorder(new Color(255, 255, 255), 4, true));
		cnt5.setFont(new Font("Tahoma", Font.BOLD, 30));
		cnt5.setForeground(Color.WHITE);
		cnt5.setBackground(new Color(0, 0, 0));
		cnt5.setHorizontalAlignment(SwingConstants.CENTER);
		cnt5.setBounds(556, 692, 98, 54);
		p5.add(cnt5);
		
		pr4 = new JButton("Previous");
		pr4.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.BLACK));
		pr4.addMouseListener(this);
		pr4.addActionListener(this);
		pr4.setFocusable(false);
		pr4.setFont(new Font("Dialog", Font.BOLD, 30));
		pr4.setForeground(Color.black);
		pr4.setBackground(UIManager.getColor("Button.light"));
		pr4.setBounds(10, 694, 149, 49);
		p5.add(pr4);
		
		l5 = new JLabel();
		l5.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		l5.setHorizontalAlignment(SwingConstants.CENTER);
		l5.setBounds(234, 0, 732, 414);
		l5.setIcon(new ImageIcon(getClass().getResource("rule5.jpg")));
		p5.add(l5);
		
		t5 = new JTextPane();
		t5.setBorder(new EtchedBorder(EtchedBorder.RAISED, 
				  UIManager.getColor("Button.focus"), 
				  UIManager.getColor("Button.foreground")));
		t5.setText("To transfer, a player strikes their own two hands together, "
				+ "and transfers raised fingers from one hand to the other as desired.\r\n "
				+ "However, a player cannot transfer fingers to make a hand have more than 4 fingers.\r\n"
				+ "If a player has a dead hand, the player can divide the fingers between the other hand "
				+ "and the dead hand by transferring fingers from the other hand to the dead hand.");
		t5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		t5.setForeground(SystemColor.textText);
		t5.setBackground(UIManager.getColor("Button.highlight"));
		t5.setEditable(false);
		t5.setBounds(10, 416, 1216, 275);
		p5.add(t5);

		back2 = new JButton("Back");
		back2.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.BLACK));
		back2.addMouseListener(this);
		back2.addActionListener(this);
		back2.setForeground(Color.black);
		back2.setFont(new Font("Dialog", Font.BOLD, 30));
		back2.setFocusable(false);
		back2.setBackground(UIManager.getColor("Button.light"));
		back2.setBounds(1098, 694, 128, 49);
		p5.add(back2);
		
		p1.setVisible(true);
		p2.setVisible(false);
		p3.setVisible(false);
		p4.setVisible(false);
		p5.setVisible(false);
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==back || e.getSource()==back2) {
			this.dispose();
			try {
				new ChopStick().main_frm.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		if(e.getSource()==n1 || e.getSource()==pr2) {
			p1.setVisible(false);
			p2.setVisible(true);
			p3.setVisible(false);
			p4.setVisible(false);
			p5.setVisible(false);
		}
		if(e.getSource()==pr1) {
			p1.setVisible(true);
			p2.setVisible(false);
			p3.setVisible(false);
			p4.setVisible(false);
			p5.setVisible(false);
		}
		if(e.getSource()==n2 || e.getSource()==pr3) {
			p1.setVisible(false);
			p2.setVisible(false);
			p3.setVisible(true);
			p4.setVisible(false);
			p5.setVisible(false);
		}
		if(e.getSource()==n3 || e.getSource()==pr4) {
			p1.setVisible(false);
			p2.setVisible(false);
			p3.setVisible(false);
			p4.setVisible(true);
			p5.setVisible(false);
		}
		if(e.getSource()==n4) {
			p1.setVisible(false);
			p2.setVisible(false);
			p3.setVisible(false);
			p4.setVisible(false);
			p5.setVisible(true);
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
		if(e.getSource()==n1) {
			n1.setBackground(Color.lightGray);
		}
		if(e.getSource()==n2) {
			n2.setBackground(Color.lightGray);
		}
		if(e.getSource()==n3) {
			n3.setBackground(Color.lightGray);
		}
		if(e.getSource()==n4) {
			n4.setBackground(Color.lightGray);
		}
		
		if(e.getSource()==pr1) {
			pr1.setBackground(Color.lightGray);
		}
		if(e.getSource()==pr2) {
			pr2.setBackground(Color.lightGray);
		}
		if(e.getSource()==pr3) {
			pr3.setBackground(Color.lightGray);
		}
		if(e.getSource()==pr4) {
			pr4.setBackground(Color.lightGray);
		}
		
		if(e.getSource()==back) {
			back.setBackground(Color.lightGray);
		}
		if(e.getSource()==back2) {
			back2.setBackground(Color.lightGray);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		if(e.getSource()==n1) {
			n1.setBackground(UIManager.getColor("Button.light"));
		}
		if(e.getSource()==n2) {
			n2.setBackground(UIManager.getColor("Button.light"));
		}
		if(e.getSource()==n3) {
			n3.setBackground(UIManager.getColor("Button.light"));
		}
		if(e.getSource()==n4) {
			n4.setBackground(UIManager.getColor("Button.light"));
		}
		
		if(e.getSource()==pr1) {
			pr1.setBackground(UIManager.getColor("Button.light"));
		}
		if(e.getSource()==pr2) {
			pr2.setBackground(UIManager.getColor("Button.light"));
		}
		if(e.getSource()==pr3) {
			pr3.setBackground(UIManager.getColor("Button.light"));
		}
		if(e.getSource()==pr4) {
			pr4.setBackground(UIManager.getColor("Button.light"));
		}
		
		if(e.getSource()==back) {
			back.setBackground(UIManager.getColor("Button.light"));
		}
		if(e.getSource()==back2) {
			back2.setBackground(UIManager.getColor("Button.light"));
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

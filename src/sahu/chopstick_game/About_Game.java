package sahu.chopstick_game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.mail.EmailException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EtchedBorder;

@SuppressWarnings({ "serial", "unused" })
public class About_Game extends JFrame implements ActionListener {
	private JButton back;
	
	public About_Game() {
		getContentPane().setForeground(Color.ORANGE);
		getContentPane().setBackground(Color.BLACK);
		setResizable(false);
		setForeground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo_F.png")));
		setBackground(Color.BLACK);
		setFont(new Font("Dialog", Font.BOLD, 20));
		setTitle("About Game");
		setBounds(150, 20, 1250, 785);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		back = new JButton("back");
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				back.setBackground(Color.black);
				back.setForeground(Color.yellow);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				back.setBackground(UIManager.getColor("Button.light"));
				back.setForeground(Color.black);
			}
		});
		back.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.BLACK));
		back.setFocusable(false);
		back.setForeground(Color.black);
		back.setBackground(UIManager.getColor("Button.light"));
		back.addActionListener(this);
		back.setFont(new Font("Tahoma", Font.BOLD, 30));
		back.setBounds(10, 692, 138, 52);
		getContentPane().add(back);
		
		JTextPane txtpnChopstickshandGame = new JTextPane();
		txtpnChopstickshandGame.setSelectionColor(new Color(0, 0, 0));
		txtpnChopstickshandGame.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.BLACK, Color.YELLOW));
		txtpnChopstickshandGame.setFont(new Font("Tahoma", Font.BOLD, 30));
		txtpnChopstickshandGame.setText("\t\t\t\t\t Chopsticks (hand game)\r\n\tChopsticks is a hand game for two or more players, in which players extend a number of fingers from each hand and transfer those scores by taking turns to tap one hand against another.\r\n \r\n\tChopsticks is an example of a \"combinatorial game\", and is solved in the sense that with perfect play, an optimal strategy from any point is known. Chopsticks is a game of strategy as well as basic math.\r\n\r\n\tIt has roots in Japan and can also called Finger Chess, Swords, Split, Magic Fingers, Chinese Fingers, Cherries, Sticks, and Twiddly Dinks. Though there are many variations of rules and different names, the overall theory and spirit of the game remains the same.\r\n\r\n\t-> About This Game(Version :- Plutonium-0.3SNL)\r\nDeveloped By :- Sahil N Lalani\t\tStudio :- Void_Matrix[:]\r\nDeveloped in :- JAVA\t\t\t\tPlatform :- PC, Laptop\r\ngenre :- Combinatorial Game\t\tReleased On :- July 11, 2022");
		txtpnChopstickshandGame.setEditable(false);
		txtpnChopstickshandGame.setForeground(Color.YELLOW);
		txtpnChopstickshandGame.setBackground(Color.BLACK);
		txtpnChopstickshandGame.setBounds(10, 10, 1216, 678);
		getContentPane().add(txtpnChopstickshandGame);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		try {
			new ChopStick().main_frm.setVisible(true);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}

}

package sahu.chopstick_game;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

@SuppressWarnings({ "serial", "unused" })

public class Launch extends JFrame {
	
	private JProgressBar load_pb;
	private JLabel Lnch,
	 			   voidmatrixlbl,
	 			   sahil,
	 			   cs_lbl,
	 			   s_lbl,
	 			   vm_lbl,
	 			   launch_lbl;
	private JLabel Version;

	public Launch() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo_F.png")));
		setTitle("ChopStick");
		getContentPane().setBackground(new Color(204, 204, 255));
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 20, 1250, 785);
		
		voidmatrixlbl = new JLabel();
		voidmatrixlbl.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		voidmatrixlbl.setBounds(1010, 187, 105, 82);
		voidmatrixlbl.setIcon(new ImageIcon(getClass().getResource("team1.png")));
		getContentPane().add(voidmatrixlbl);
		
		sahil = new JLabel("sAhiL lALaNi");
		sahil.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sahil.setHorizontalAlignment(SwingConstants.CENTER);
		sahil.setForeground(new Color(102, 0, 0));
		sahil.setFont(new Font("Tahoma", Font.BOLD, 40));
		sahil.setBounds(457, 0, 340, 90);
		getContentPane().add(sahil);
		
		load_pb = new JProgressBar();
		load_pb.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		load_pb.setFont(new Font("Tahoma", Font.BOLD, 30));
		load_pb.setForeground(new Color(153, 0, 102));
		load_pb.setStringPainted(true);
		load_pb.setBounds(294, 528, 689, 62);
		getContentPane().add(load_pb);
		
		cs_lbl = new JLabel("ChopStick");
		cs_lbl.setBorder(null);
		cs_lbl.setForeground(new Color(102, 0, 51));
		cs_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		cs_lbl.setFont(new Font("Tahoma", Font.BOLD, 90));
		cs_lbl.setBounds(395, 249, 468, 175);
		getContentPane().add(cs_lbl);
		
		s_lbl = new JLabel("Studio");
		s_lbl.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(s_lbl);
		s_lbl.setForeground(new Color(102, 42, 0));
		s_lbl.setFont(new Font("Tahoma", Font.BOLD, 30));
		s_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		s_lbl.setBounds(989, 136, 144, 51);
		
		Lnch = new JLabel("...Launching...");
		Lnch.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Lnch.setForeground(new Color(153, 0, 0));
		Lnch.setHorizontalAlignment(SwingConstants.CENTER);
		Lnch.setFont(new Font("Tahoma", Font.BOLD, 15));
		Lnch.setBounds(562, 592, 139, 32);
		getContentPane().add(Lnch);
		
		vm_lbl = new JLabel("Void_Matrix[:]\r\n");
		vm_lbl.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(vm_lbl);
		vm_lbl.setForeground(new Color(102, 0, 0));
		vm_lbl.setHorizontalAlignment(SwingConstants.CENTER);
		vm_lbl.setFont(new Font("Tahoma", Font.BOLD, 40));
		vm_lbl.setBounds(894, 53, 332, 82);
		
		Version = new JLabel("Plutonium-0.3SNL");
		Version.setHorizontalAlignment(SwingConstants.CENTER);
		Version.setForeground(new Color(102, 0, 0));
		Version.setFont(new Font("Tahoma", Font.BOLD, 40));
		Version.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		Version.setBounds(10, 699, 380, 39);
		getContentPane().add(Version);
		
		launch_lbl = new JLabel();
		launch_lbl.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		launch_lbl.setHorizontalTextPosition(JLabel.CENTER);
		launch_lbl.setIcon(new ImageIcon(getClass().getResource("csm.jpg")));
		launch_lbl.setVerticalAlignment(SwingConstants.TOP);
		launch_lbl.setForeground(new Color(0, 0, 255));
		launch_lbl.setFont(new Font("Tahoma", Font.BOLD, 99));
		launch_lbl.setBounds(0, 0, 1238, 750);
		getContentPane().add(launch_lbl);
	
	}
	
	public void prog() throws Exception
	{
		int n=0;
		while(n<=100)
		{
			load_pb.setValue(n);
			n+=1;
			Thread.sleep(20);
		}
	}
}

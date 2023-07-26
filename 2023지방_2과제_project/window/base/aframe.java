package base;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;


public class aframe extends JFrame implements WindowListener{

	public static JPanel pc,mp,np,ep,cp,wp,sp,p1,p2,p3,p4,p5,p10;
	public static String n = BorderLayout.NORTH;
	public static String s = BorderLayout.SOUTH;
	public static String w = BorderLayout.WEST;
	public static String e = BorderLayout.EAST;
	public static String c = BorderLayout.CENTER;
	public static DefaultTableModel dtm;
	public static DefaultTableCellRenderer cell = new DefaultTableCellRenderer();
	public static SimpleDateFormat datef = new SimpleDateFormat("yyyy-mm-dd");
	public static SimpleDateFormat timef = new SimpleDateFormat("hh:mm:ss");
	public static DecimalFormat def1 = new DecimalFormat("#,##0");
	public static DecimalFormat def2 = new DecimalFormat("#,##0");
	public static Resultset rs;
	public static String genrep[] = "총류,철학,종교,사회과학,자연과학,기술과학,예술,언어,문학,역사".split(",");
	
	
	public void fs(String f) {
		setTitle(f);
		setDefaultCloseOperation(2);
		
		Image img = Toolkit.getDefaultToolkit().getImage("datafiles/logo.png");
		setIconImage(img);
		
		add(pc = new JPanel(new BorderLayout()));
		
		pc.add(np = new JPanel(new BorderLayout()),n);
		pc.add(wp = new JPanel(new BorderLayout()),w);
		pc.add(cp = new JPanel(new BorderLayout()),c);
		pc.add(ep = new JPanel(new BorderLayout()),e);
		pc.add(sp = new JPanel(new BorderLayout()),s);
		addWindowListener(this);
		cell.setHorizontalAlignment(0);
	}
	public void sz (JComponent c, int x, int y) {
		c.setPreferredSize(new Dimension(x,y));
	}
	public void ft(JComponent c, int x, int i) {
		c.setFont(new Font("맑은 고딕",x,i));
	}
	public void line(JComponent c, Color col) {
		c.setBorder(new LineBorder(col));
	}
	public void emp(JComponent c, int t, int l, int b, int r) {
		c.setBorder(new EmptyBorder(t, l, b, r));
	}
	public void sh() {
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void shp() {
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void errorMsg(String msg) {
		JOptionPane.showConfirmDialog(null, msg, "경고", 0);
	}
	public void infoMsg(String msg) {
		JOptionPane.showConfirmDialog(null, msg, "정보", 1);
	}
	public void jpgup(JLabel c, String data, int w, int h) {
		c.setIcon(new ImageIcon(new ImageIcon("datafiles/"+data+".jpg").getImage().getScaledInstance(w, h, 0)));
	}
	public void pngup(JLabel c, String data, int w, int h) {
		c.setIcon(new ImageIcon(new ImageIcon("datafiles/"+data+".jpg").getImage().getScaledInstance(w, h, 0)));
	}
	public ImageIcon blobup(InputStream is, int w, int h) {
		try {
			Image img = ImageIO.read(is);
			img = img.getScaledInstance(w, h, 0);
			return new ImageIcon(img);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	public Integer rei(String s) {
		return Integer.parseInt(s);
	}
	public boolean cnum(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	public boolean cdate(String s) {
		try {
			if (datef.format(datef.parse(s)).equals(s)) {
				return true;
			}
		} catch (ParseException e) {
			// TODO: handle exception
		}
		return false;
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}

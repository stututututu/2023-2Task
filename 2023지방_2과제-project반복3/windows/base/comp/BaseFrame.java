package base.comp;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import design.IDesign;

public abstract class BaseFrame extends JFrame implements IDesign {

	private BaseFrame PreFrame;

	public BasePanel jpCenter = new BasePanel();
	public BasePanel jpTop = new BasePanel();
	public BasePanel jpBottom = new BasePanel();
	public BasePanel jpLeft = new BasePanel();
	public BasePanel jpRight = new BasePanel();

	private ImageIcon logo;

	public void BaseFrame(String title, int w, int d, BaseFrame PreFrame) {
		// TODO Auto-generated constructor stub
		setComp();
		setDeisgn();
		events();
		
		this.PreFrame = PreFrame;
		super.add(jpCenter, BorderLayout.CENTER);
		super.add(jpTop, BorderLayout.NORTH);
		super.add(jpBottom, BorderLayout.SOUTH);
		super.add(jpLeft, BorderLayout.WEST);
		super.add(jpRight, BorderLayout.EAST);

		super.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				close();
			}
		});

		super.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				super.componentResized(e);
				System.out.println(getSize());
			}
		});

		logo = new ImageIcon("./datafiles/logo.png");
		super.setTitle(title);
		super.setSize(w, d);
		super.setLocationRelativeTo(null);
		super.setIconImage(logo.getImage());
		super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		super.setVisible(true);
	}

	public BaseFrame close() {
		// TODO Auto-generated method stub
		if (PreFrame == null) {
			System.exit(0);
		}
		this.dispose();
		PreFrame.setVisible(true);
		PreFrame.setState(JFrame.NORMAL);
		return this;

	}

	public BaseFrame refresh() {
		super.validate();
		super.repaint();
		return this;
	};
}

package base.comp;

import java.awt.BorderLayout;import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import design.IDesign;

public abstract class BaseFrame extends JFrame implements IDesign{

	public BasePanel jpCenter = new BasePanel();
	public BasePanel jpTop = new BasePanel();
	public BasePanel jpBottom = new BasePanel();
	public BasePanel jpLeft = new BasePanel();
	public BasePanel jpRight = new BasePanel();
	
	
	public void BaseFrame(String title, int w, int d) {
	
		// TODO Auto-generated constructor stub
		setComp();
		setDesign();
		events();
		
		
		
		super.add(jpCenter, BorderLayout.CENTER);
		super.add(jpTop, BorderLayout.NORTH);
		super.add(jpBottom, BorderLayout.SOUTH);
		super.add(jpLeft, BorderLayout.WEST);
		super.add(jpRight, BorderLayout.EAST);
		
		super.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				System.out.println(getSize());
				super.componentResized(e);
			}
		
		});
		
		super.setSize(w, d);
		super.setTitle(title);
		super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		super.setLocationRelativeTo(null);
		super.setVisible(true);
		
		
		
	}
	
}

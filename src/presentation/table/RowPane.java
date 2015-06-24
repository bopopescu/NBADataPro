package presentation.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.common.PhotoLabel;
import presentation.main.Mainframe;
import presentation.match.MatchAnalyseTopPanel;

public class RowPane extends JPanel implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private JLabel photoLabel;
	private JLabel indexLabel;
	protected int height;
	
	/**
	 * Create the panel.
	 */
	public RowPane(int index, boolean hasIndex,int width,int h) {
		if((index%2)==0)
			this.setBackground(Color.white);
		else
			this.setBackground(new Color(245,245,245));
		height = h;
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setHgap(10);
		flowLayout.setAlignment(FlowLayout.LEADING);
		setPreferredSize(new Dimension(width, height));
		if(hasIndex){
			setIndex(index);
		}
		
		this.addMouseListener(this);
	//	this.setBounds(0, 0, TablePane.width, TablePane.height);
	}
	
	public void setPhotoLabel(String photo,int w){
		photoLabel = new PhotoLabel(new Dimension(w, height));
		((PhotoLabel) photoLabel).setImage(new ImageIcon(photo).getImage());
		add(photoLabel);
	}
	
	
	private void setIndex(int index){
		indexLabel = new JLabel(index+"");
		indexLabel.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
		indexLabel.setHorizontalAlignment(JLabel.CENTER);
		indexLabel.setPreferredSize(new Dimension(30,height));
		this.add(indexLabel);
		
	}
	

	
	public void addData(String data,int width){		
		JLabel block = new JLabel(data,JLabel.CENTER);
		block.setPreferredSize(new Dimension(width, height));
		block.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				
				Component[] labels =  getComponents();
				Mainframe.getFrame().changeContent(new MatchAnalyseTopPanel(Integer.parseInt(((JLabel) labels[2]).getText())));
			}
		
		});
		this.add(block);
	}
	
	
	public void addDatas(ArrayList<String> datas,ArrayList<Integer> w){
		for(int i=0;i<datas.size();i++){
			addData(datas.get(i),w.get(i));
		}
	}
	public void addDatas_pic(ArrayList<String> datas,ArrayList<Integer> w){
		setPhotoLabel(datas.get(0),w.get(0));
		for(int i=1;i<datas.size();i++){
			addData(datas.get(i),w.get(i));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		RowPane.this.setBorder(null);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		RowPane.this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		RowPane.this.setBorder(null);
	}


}

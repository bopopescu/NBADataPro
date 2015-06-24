package presentation.team.vs;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.common.Season;
import presentation.common.SeasonComboBox;
import presentation.common.VSLegend;
import presentation.player.vs.VSContentPanel;
import presentation.team.teamDetail.TeamMiddlePanel;

public class TeamVSContentPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public VSContentPanel vsContentPanel;
	TeamMiddlePanel observer;

	SeasonComboBox seasonComboBox;//赛季选择
	JLabel commitSeason;//提交赛季
	static int season;
	
	ArrayList<String> itemsNeedAdd = new ArrayList<String>();
	ArrayList<Double> avg1 = new ArrayList<Double>();
	ArrayList<Double> avg2 = new ArrayList<Double>();
	
	
	public TeamVSContentPanel(ArrayList<String>itemsNeedAdd,
			ArrayList<Double> team,ArrayList<Double> avg){
		this.setLayout(null);
		this.setBounds(30, 300, 1280,360);
		this.setBackground(Color.WHITE);
		this.itemsNeedAdd = itemsNeedAdd;
		this.avg1 = team;
		this.avg2 = avg;
		setSeasonComboBox();
		setLegend();
		setVSContentPanel();
		this.setVisible(true);
	}
	
	public void registerObserver(TeamMiddlePanel o){
		observer = o;
	}

	public int setSeasonAttri(){
		int resultSeason = 0;
		String seasonSelected = (String) seasonComboBox.getSelectedItem();
		switch(seasonSelected){
			case"赛季":resultSeason = 2014;break;
			case"2014-2015":resultSeason = 2014;break;
			case"2013-2014":resultSeason = 2013;break;
			case"2012-2013":resultSeason = 2012;break;
			case"2011-2012":resultSeason = 2011;break;
			case"2010-2011":resultSeason = 2010;break;
			case"2009-2010":resultSeason = 2009;break;
			default:break;
		}
		return resultSeason;
	}
		
	public void setSeasonComboBox(){
		seasonComboBox = new SeasonComboBox();
		seasonComboBox.setlocation(new Point(1020,30));
		this.add(seasonComboBox);
		
		commitSeason = new JLabel("commit");
		commitSeason.setForeground(Color.GRAY);
		commitSeason.setFont(new Font("Dialog",0,18));
		commitSeason.setBounds(1070,55,100,50);
		commitSeason.addMouseListener(new SeasonCommitListener());
		this.add(commitSeason);
		this.updateUI();
	} 
	
	private void setLegend(){
		VSLegend legend = new VSLegend();
		legend.setLocation(1030, 100);
		this.add(legend);
	}
	
	class SeasonCommitListener implements MouseListener{
		int times = 0;
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			times ++;
			if(times%2 == 1){
				commitSeason.setFont(new Font("Dialog",1,15));
			}else{
				commitSeason.setFont(new Font("Dialog",1,18));
			}
			observer.updateVS(setSeasonAttri());
			Season.SEASON = setSeasonAttri();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			commitSeason.setCursor(new Cursor(Cursor.HAND_CURSOR));	
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void setVSContentPanel(){
		vsContentPanel = new VSContentPanel(itemsNeedAdd,avg1,avg2,false,new Point(0,0));
		this.add(vsContentPanel);
		repaint();
	}
	
	public void updateVSContentPanel(ArrayList<Double> avg1,
			ArrayList<Double> avg2,boolean isPlayer,Point p){
		if(vsContentPanel != null){
			this.remove(vsContentPanel);
		}
		vsContentPanel = new VSContentPanel(itemsNeedAdd,avg1,avg2,false,new Point(0,0));
		this.add(vsContentPanel);
		System.out.println("仅仅更新 对比条目");
	}
	
	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setLayout(null);
		jf.setSize(1280,700);
		jf.setLocationRelativeTo(null);
//		jf.add(new TeamVSContentPanel());
		jf.setVisible(true);
	}



}

package presentation.statistics.playerKing;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import blservice.playerblservice.PlayerBLService;
import businesslogic.playerbl.PlayerBL_stub;
import presentation.common.SelectLabel;

public class EveryDay_PlayerKingOptionsPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	SelectLabel ScoreButton;//得分
	SelectLabel REBButton;//篮板
	SelectLabel AssitButton;//场均助攻
	SelectLabel BSButton;//场均盖帽
	SelectLabel StealButton;//场均抢断
	
	public String sortBy = "avgPoint";
	public String Date;
	
	ArrayList<SelectLabel> selectLabelGroups = new ArrayList<SelectLabel>();

	PlayerKingContentPanel playerKingContentPanel;

	public EveryDay_PlayerKingOptionsPanel(){
		this.setLayout(null);
		this.setBounds(0, 105,1130,430);
		this.setBackground(Color.WHITE);
		SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd");//设置日期格式
		Date = df.format(new java.util.Date());
		
		setScoreButton();
		setREBButton();
		setAssitButton();
		setBSButton();
		setStealButton();
		setPlayerKingContentPanel();
	}
	
	public void setOptionsSelectedGroups(SelectLabel s){
		selectLabelGroups.add(ScoreButton);
		selectLabelGroups.add(REBButton);
		selectLabelGroups.add(AssitButton);
		selectLabelGroups.add(BSButton);
		selectLabelGroups.add(StealButton);

		for(SelectLabel sl : selectLabelGroups){
			if(sl != s){
				sl.setBackground(Color.black);;
			}
		}
	
	}
	
	public void setPlayerKingContentPanel(){
		setOptionsSelectedGroups(AssitButton);
		if(playerKingContentPanel!=null)
			remove(playerKingContentPanel);
		setVisible(false);
		PlayerBLService playerbl = new PlayerBL_stub();
		System.out.println("PlayerKing"+sortBy+Date);
		playerKingContentPanel = new PlayerKingContentPanel(playerbl.DailyKing(5, sortBy,Date));
		this.add(playerKingContentPanel);
		setVisible(true);
		repaint();
		
	}


	public void setScoreButton(){
		Point location = new Point(0,0);
		Point size = new Point(225,50);
		ScoreButton = new SelectLabel("得分",location,size);

		ScoreButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				
				setOptionsSelectedGroups(ScoreButton);
				sortBy = "avgPoint";
				setPlayerKingContentPanel();
			}
		});

		this.add(ScoreButton);

	}
	
	public void setREBButton(){
		Point location = new Point(226,0);
		Point size = new Point(225,50);
		REBButton = new SelectLabel("篮板",location,size);

		REBButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				setOptionsSelectedGroups(REBButton);
				sortBy = "avgRebound";
				setPlayerKingContentPanel();
			}
		});

		this.add(REBButton);
	}
	
	public void setAssitButton(){
		Point location = new Point(452,0);
		Point size = new Point(225,50);
		AssitButton = new SelectLabel("助攻",location,size);

		AssitButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				setOptionsSelectedGroups(AssitButton);
				sortBy = "avgAssist";
				setPlayerKingContentPanel();
			}
		});

		this.add(AssitButton);

	}
	public void setBSButton(){
		Point location = new Point(678,0);
		Point size = new Point(225,50);
		BSButton = new SelectLabel("盖帽",location,size);

		BSButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				setOptionsSelectedGroups(BSButton);
				sortBy = "avgBlockShot";
				setPlayerKingContentPanel();
				setVisible(true);
			}
		});

		this.add(BSButton);

	}
	
	public void setStealButton(){
		Point location = new Point(904,0);
		Point size = new Point(225,50);
		StealButton = new SelectLabel("抢断",location,size);

		StealButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				setOptionsSelectedGroups(StealButton);
				sortBy = "avgSteal";
				setPlayerKingContentPanel();
			}
		});

		this.add(StealButton);

	}

	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setLayout(null);
		jf.setSize(1280,700);
		jf.setLocationRelativeTo(null);
		jf.add(new EveryDay_PlayerKingOptionsPanel());
		jf.setVisible(true);
	}


	
	
}

package presentation.player;

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

import blservice.playerblservice.PlayerBLService;
import blservice.playerblservice.sortParam;
import businesslogic.playerbl.PlayerBL;
import businesslogic.playerbl.PlayerBL_stub;
import presentation.common.ListType;
import presentation.common.PanelMotion;
import presentation.common.PullDownMenu;
import presentation.common.SeasonComboBox;
import presentation.common.SelectLabel;
import presentation.player.playerDetail.FilterLabel;
import presentation.table.TablePane;
import presentation.table.playerTablePanel;
import vo.playervo.PlayerVO;

public class PlayerDataList  extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel titleLabel;
	JLabel buttonsBGLabel;
	
	PullDownMenu menu;
	FilterLabel filter;
	
	PanelMotion upMotion;
	PanelMotion downMotion;
	
	SeasonComboBox seasonComboBox;//赛季选择
	JLabel commitSeason;//提交赛季
	static int season = 2014;

	public SelectLabel basicInfoButton;//信息
	public SelectLabel NormalInfoButton;//普通数据
	public SelectLabel AvgNormalInfoButton;//平均普通数据
	public SelectLabel HighInfoButton;//高阶数据
	
	PlayerBLService playerbl = new PlayerBL();
	
	Color entered = new Color(30,80,140);
	Color pressed = new Color(42,109,183);
	Color exicted= new Color(26,70,122);

	TablePane BasicInfoTable;
	TablePane NormalInfoTable;
	TablePane HighInfoTable;
	
	ArrayList<SelectLabel> selectLabelGroups = new ArrayList<SelectLabel>();
	
	ArrayList<sortParam> sortBy = new ArrayList<sortParam>();
	ArrayList<String> position = new ArrayList<String>();
	ArrayList<String> league = new ArrayList<String>();
	
	public PlayerDataList(){
		this.setLayout(null);
		this.setBounds(0,125,1280,545);
		this.setBackground(Color.WHITE);
		setTitle();
		setSeasonComboBox();
		setButtonsBGLabel();
		
		basicInfoButton.setBackground(pressed);
		basicInfoButton.isSelected = true;
		
		sortBy.add(new sortParam("point",true));
		position.add("F");position.add("C");position.add("G");
		league.add("W");league.add("E");
		setBasicInfoTablePanel();

		setMotion();
		setFilterButton();
	}
	
	private void setMotion(){
		ArrayList<JPanel> panels = new ArrayList<JPanel>();
		if(BasicInfoTable != null){
			panels.add(BasicInfoTable);
		}
		if(NormalInfoTable != null){
			panels.add(NormalInfoTable);
		}
		if(HighInfoTable != null){
			panels.add(HighInfoTable);
		}
		upMotion = new PanelMotion(panels, 60);
		downMotion = new PanelMotion(panels, 150);
	}
	
	public void setSelectedGroups(SelectLabel s){
		selectLabelGroups.clear();
		selectLabelGroups.add(basicInfoButton);
		selectLabelGroups.add(NormalInfoButton);
		selectLabelGroups.add(AvgNormalInfoButton);
		selectLabelGroups.add(HighInfoButton);
		for(SelectLabel sl : selectLabelGroups){
			if(sl != s){
				sl.setBackground(exicted);
				sl.isSelected = false;
			}else{
				sl.setBackground(pressed);
				sl.isSelected = true;
			}
		}
	}
	
	public void setAttri(){
		String leagueChosen = (String) filter.leagueCombo.getSelectedItem();
		String positionChosen = (String) filter.positionCombo.getSelectedItem();
		String fst = (String) filter.firstCombo.getSelectedItem();
		String sec = (String) filter.secondCombo.getSelectedItem();
		String third = (String) filter.thirdCombo.getSelectedItem();
		switch(leagueChosen){
			case"全部":league.clear();league.add("E"); league.add("W");break;
			case"东部联盟":league.clear();league.add("E");break;
			case"西部联盟":league.clear();league.add("W");break;
			default:break;
		}
		switch(positionChosen){
			case"全部":position.clear();position.add("F"); position.add("C");position.add("G");break;
			case"前锋":position.clear();position.add("F");break;
			case"中锋":position.clear();position.add("C");break;
			case"后卫":position.clear();position.add("G");break;
			default:break;
		}
		switch(fst){
			case"第一优先级":sortBy.clear();sortBy.add(new sortParam("avgPoint",true));break;
			case"场均助攻":sortBy.clear();sortBy.add(new sortParam("avgAssist",true));break;
			case"场均盖帽":sortBy.clear();sortBy.add(new sortParam("avgBlockShot",true));break;
			case"场均防守":sortBy.clear();sortBy.add(new sortParam("avgDefend",true));break;
			case"场均失误":sortBy.clear();sortBy.add(new sortParam("avgFault",true));break;
			case"场均犯规":sortBy.clear();sortBy.add(new sortParam("avgFoul",true));break;
			case"上场时间":sortBy.clear();sortBy.add(new sortParam("avgMinute",true));break;
			case"进攻":	 sortBy.clear();sortBy.add(new sortParam("avgOffend",true));break;
			case"场均篮板":sortBy.clear();sortBy.add(new sortParam("avgRebound",true));break;
			case"场均得分":sortBy.clear();sortBy.add(new sortParam("avgPoint",true));break;
			case"场均抢断":sortBy.clear();sortBy.add(new sortParam("avgSteal",true));break;
			case"GMSC效率值":sortBy.clear();sortBy.add(new sortParam("gmSc",true));break;
			case"三分命中率":sortBy.clear();sortBy.add(new sortParam("three",true));break;
			case"投篮命中率":sortBy.clear();sortBy.add(new sortParam("shot",true));break;
			case"罚球命中率":sortBy.clear();sortBy.add(new sortParam("penalty",true));break;
			default:break;
		}
			
		switch(sec){
			case"第二优先级":break;
			case"场均助攻":sortBy.clear();sortBy.add(new sortParam("avgAssist",true));break;
			case"场均盖帽":sortBy.clear();sortBy.add(new sortParam("avgBlockShot",true));break;
			case"场均防守":sortBy.clear();sortBy.add(new sortParam("avgDefend",true));break;
			case"场均失误":sortBy.clear();sortBy.add(new sortParam("avgFault",true));break;
			case"场均犯规":sortBy.clear();sortBy.add(new sortParam("avgFoul",true));break;
			case"上场时间":sortBy.clear();sortBy.add(new sortParam("avgMinute",true));break;
			case"进攻":	 sortBy.clear();sortBy.add(new sortParam("avgOffend",true));break;
			case"场均篮板":sortBy.clear();sortBy.add(new sortParam("avgRebound",true));break;
			case"场均得分":sortBy.clear();sortBy.add(new sortParam("avgPoint",true));break;
			case"场均抢断":sortBy.clear();sortBy.add(new sortParam("avgSteal",true));break;
			case"GMSC效率值":sortBy.clear();sortBy.add(new sortParam("gmSc",true));break;
			case"三分命中率":sortBy.clear();sortBy.add(new sortParam("three",true));break;
			case"投篮命中率":sortBy.clear();sortBy.add(new sortParam("shot",true));break;
			case"罚球命中率":sortBy.clear();sortBy.add(new sortParam("penalty",true));break;
			default:break;
		}
		
		switch(third){
			case"第三优先级":break;
			case"场均助攻":sortBy.clear();sortBy.add(new sortParam("avgAssist",true));break;
			case"场均盖帽":sortBy.clear();sortBy.add(new sortParam("avgBlockShot",true));break;
			case"场均防守":sortBy.clear();sortBy.add(new sortParam("avgDefend",true));break;
			case"场均失误":sortBy.clear();sortBy.add(new sortParam("avgFault",true));break;
			case"场均犯规":sortBy.clear();sortBy.add(new sortParam("avgFoul",true));break;
			case"上场时间":sortBy.clear();sortBy.add(new sortParam("avgMinute",true));break;
			case"进攻":	 sortBy.clear();sortBy.add(new sortParam("avgOffend",true));break;
			case"场均篮板":sortBy.clear();sortBy.add(new sortParam("avgRebound",true));break;
			case"场均得分":sortBy.clear();sortBy.add(new sortParam("avgPoint",true));break;
			case"场均抢断":sortBy.clear();sortBy.add(new sortParam("avgSteal",true));break;
			case"GMSC效率值":sortBy.clear();sortBy.add(new sortParam("gmSc",true));break;
			case"三分命中率":sortBy.clear();sortBy.add(new sortParam("three",true));break;
			case"投篮命中率":sortBy.clear();sortBy.add(new sortParam("shot",true));break;
			case"罚球命中率":sortBy.clear();sortBy.add(new sortParam("penalty",true));break;
			default:break;
		}
	}

	public void setTitle(){
		titleLabel = new JLabel(" 球员列表",JLabel.LEADING);
		titleLabel.setFont(new Font("Dialog",1,20));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBackground(entered);
		titleLabel.setOpaque(true);
		titleLabel.setBounds(0,0,1280,60);
		this.add(titleLabel);
		this.updateUI();
		
		filter = new FilterLabel();
		filter.setLocation(0, 55);
		this.add(filter);
		filter.setVisible(false);
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
		seasonComboBox.setlocation(new Point(720,10));
		titleLabel.add(seasonComboBox);
		
		commitSeason = new JLabel("commit");
		commitSeason.setForeground(Color.WHITE);
		commitSeason.setFont(new Font("Dialog",0,18));
		commitSeason.setBounds(900,5,100,50);
		commitSeason.addMouseListener(new SeasonCommitListener());
		titleLabel.add(commitSeason);
		this.updateUI();
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
			PlayerDataList.season = setSeasonAttri();
			
			if(basicInfoButton.isSelected){
				if(BasicInfoTable!=null)
					PlayerDataList.this.remove(BasicInfoTable);
				setBasicInfoTablePanel(); 
			}
			if(NormalInfoButton.isSelected){
				if(NormalInfoTable!=null)
					PlayerDataList.this.remove(NormalInfoTable);
				setNormalInfoTablePanel(false);
			}
			if(AvgNormalInfoButton.isSelected){
				if(NormalInfoTable!=null)
					PlayerDataList.this.remove(NormalInfoTable);
				setNormalInfoTablePanel(true); 
			}
			if(HighInfoButton.isSelected){
				if(HighInfoTable!=null)
					PlayerDataList.this.remove(HighInfoTable);
				setHighInfoTablePanel();
			}
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
	
	private void setFilterButton(){
		JLabel filterButton = new JLabel("筛选",JLabel.LEADING);
		filterButton.setBounds(1220, 10, 80, 40);
		filterButton.setBackground(entered);
		filterButton.setForeground(Color.WHITE);
		filterButton.setFont(new Font("Dialog",1,20));
		filterButton.setOpaque(true);
		filterButton.addMouseListener(new MouseListener() {
			boolean selected = false;
			@Override
			public void mouseReleased(MouseEvent e) {
				if(selected == true){
					setMotion();
					upMotion.upMove();
					filterButton.setText("筛选");
					filter.setVisible(false);
					
					setAttri();
					setVisible(false);
					
					if(basicInfoButton.isSelected){
						setSelectedGroups(basicInfoButton);
						PlayerDataList.this.remove(BasicInfoTable);
						setBasicInfoTablePanel(); 
					}
					if(NormalInfoButton.isSelected){
						setSelectedGroups(NormalInfoButton);
						PlayerDataList.this.remove(NormalInfoTable);
						setNormalInfoTablePanel(false);
					}
					if(AvgNormalInfoButton.isSelected){
						setSelectedGroups(AvgNormalInfoButton);
						PlayerDataList.this.remove(NormalInfoTable);
						setNormalInfoTablePanel(true);
					}
					if(HighInfoButton.isSelected){
						setSelectedGroups(HighInfoButton);
						PlayerDataList.this.remove(HighInfoTable);
						setHighInfoTablePanel();
					}
					setMotion();	
					setVisible(true);
					repaint();	
					
				}else{
					setMotion();
					downMotion.downMove();
					filterButton.setText("确定");
					filter.setVisible(true);
				}
				selected = !selected;
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		titleLabel.add(filterButton);
	
	}
	
	public void setBasicInfoButton(){
		Point p1 = new Point(5,5);
		Point p2 = new Point(40,30);
		basicInfoButton = new SelectLabel("信息",p1,p2,entered,pressed,exicted);
		basicInfoButton.setForeground(Color.WHITE);
		basicInfoButton.setBackground(exicted);
		basicInfoButton.setOpaque(true);
		basicInfoButton.addMouseListener(new BasicInfoButtonListener());
		buttonsBGLabel.add(basicInfoButton);
	}
	
	class BasicInfoButtonListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			setVisible(false);
			setButtonsBGLabel();
			basicInfoButton.isSelected = true;
			basicInfoButton.setBackground(pressed);
			setSelectedGroups(basicInfoButton);
			if(BasicInfoTable!=null)
				PlayerDataList.this.remove(BasicInfoTable);
			if(NormalInfoTable!=null)
				PlayerDataList.this.remove(NormalInfoTable);
			if(HighInfoTable!=null)
				PlayerDataList.this.remove(HighInfoTable);
			setBasicInfoTablePanel(); 
				
			setVisible(true);
			repaint();	
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
	}

	public void setNormalInfoButton(){
		Point p1 = new Point(50,5);
		Point p2 = new Point(40,30);
		NormalInfoButton = new SelectLabel("基础",p1,p2,entered,pressed,exicted);
		NormalInfoButton.setForeground(Color.WHITE);
		NormalInfoButton.setBackground(exicted);
		NormalInfoButton.setOpaque(true);
		NormalInfoButton.addMouseListener(new NormalInfoButtonListener());
		buttonsBGLabel.add(NormalInfoButton);
	}

	public class NormalInfoButtonListener implements MouseListener{
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
//			PlayerKingPanel.this.remove(TitleLabel);
//			PlayerKingPanel.this.remove(everyDay_PlayerKingOptionsPanel);

			setVisible(false);
			setButtonsBGLabel();
			NormalInfoButton.isSelected = true;
			NormalInfoButton.setBackground(pressed);
			setSelectedGroups(NormalInfoButton);
			
			if(BasicInfoTable!=null)
				PlayerDataList.this.remove(BasicInfoTable);
			if(NormalInfoTable!=null)
				PlayerDataList.this.remove(NormalInfoTable);
			if(HighInfoTable!=null)
				PlayerDataList.this.remove(HighInfoTable);
			setNormalInfoTablePanel(false); 
			
			setVisible(true);
			repaint();	
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	
	}
	
	public void setAvgNormalInfoButton(){
		Point p1 = new Point(100,5);
		Point p2 = new Point(40,30);
		AvgNormalInfoButton = new SelectLabel("场均",p1,p2,entered,pressed,exicted);
		AvgNormalInfoButton.setForeground(Color.WHITE);
		AvgNormalInfoButton.setBackground(exicted);
		AvgNormalInfoButton.setOpaque(true);
		AvgNormalInfoButton.addMouseListener(new AvgNormalInfoButtonListener());
		buttonsBGLabel.add(AvgNormalInfoButton);
	}
	
	public class AvgNormalInfoButtonListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {

			setVisible(false);
			setButtonsBGLabel();
			AvgNormalInfoButton.isSelected = true;
			AvgNormalInfoButton.setBackground(pressed);
			setSelectedGroups(AvgNormalInfoButton);
			
			if(BasicInfoTable!=null)
				PlayerDataList.this.remove(BasicInfoTable);
			if(NormalInfoTable!=null)
				PlayerDataList.this.remove(NormalInfoTable);
			if(HighInfoTable!=null)
				PlayerDataList.this.remove(HighInfoTable);
			setNormalInfoTablePanel(true); 
			
			setVisible(true);
			repaint();	
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	
	}
	
	public void setHighInfoButton(){
		Point p1 = new Point(150,5);
		Point p2 = new Point(40,30);
		HighInfoButton = new SelectLabel("高阶",p1,p2,entered,pressed,exicted);
		HighInfoButton.setForeground(Color.WHITE);
		HighInfoButton.setBackground(exicted);
		HighInfoButton.setOpaque(true);
		HighInfoButton.addMouseListener(new HighInfoButtonListener());
		buttonsBGLabel.add(HighInfoButton);
	}
	
	public class HighInfoButtonListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
//			PlayerKingPanel.this.remove(TitleLabel);
//			PlayerKingPanel.this.remove(everyDay_PlayerKingOptionsPanel);

			setVisible(false);
			setButtonsBGLabel();
			HighInfoButton.isSelected = true;
			HighInfoButton.setBackground(pressed);
			setSelectedGroups(HighInfoButton);
			if(BasicInfoTable!=null)
				PlayerDataList.this.remove(BasicInfoTable);
			if(NormalInfoTable!=null)
				PlayerDataList.this.remove(NormalInfoTable);
			if(HighInfoTable!=null)
				PlayerDataList.this.remove(HighInfoTable);
			setHighInfoTablePanel(); 
			
			setVisible(true);
			repaint();	
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	
	}
	
	public void setButtonsBGLabel(){
		buttonsBGLabel = new JLabel();
		buttonsBGLabel.setBackground(exicted);
		buttonsBGLabel.setOpaque(true);
		buttonsBGLabel.setBounds(1005,10,195,40);
		setBasicInfoButton();
		setNormalInfoButton();
		setAvgNormalInfoButton();
		setHighInfoButton();
		titleLabel.add(buttonsBGLabel,0);
	}

	public void setBasicInfoTablePanel(){
//		for(int i = 0; i<league.size();i++){
//			System.out.println(league.get(i));
//		}
		ArrayList<PlayerVO> vo = playerbl.filterInfo(sortBy,position,league,0,100,season);
//		System.out.println("********参数 "+sortBy+" "+position+" "+league+" "+season);
		PlayerVO2List v2l = new PlayerVO2List();
		ArrayList<ArrayList<String>> datas = v2l.basicInfo(vo);
			
		String[] header = {"","名称","所属球队","联盟","号码","球员位置","球员年龄","身高","体重","毕业学校"};
		ArrayList<Integer> wid = new ArrayList<Integer>();
		wid.add(70);wid.add(150);wid.add(100);wid.add(100);wid.add(100);wid.add(100);wid.add(100);wid.add(100);wid.add(100);wid.add(200);
		
		BasicInfoTable = new playerTablePanel(datas,header,wid,0,60,1280,450,50,true,false,season);
		BasicInfoTable.setBackground(Color.BLACK);
		this.add(BasicInfoTable);
//		System.out.println("table: "+ BasicInfoTable);
		
//		System.out.println("**player list vo data is null?"+(datas==null));
	}
		
	public void setNormalInfoTablePanel(boolean isAvg){
		ArrayList<PlayerVO> vo = new ArrayList<PlayerVO>();
		ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();			
		PlayerVO2List v2l = new PlayerVO2List();
		ListType tableType = ListType.avg;
		if(isAvg){
			vo = playerbl.filterNormalAvg(sortBy,position,league,0,100,season);
			datas = v2l.avgNormalData(vo);
		}else{
			tableType = ListType.normal;
			vo = playerbl.filterNormal(sortBy,position,league,0,100,season);
			datas = v2l.normalData(vo);
		}

		String[] tbHead = {"","姓名","首发","效率","上场次数","分钟","％","三分％","罚球％","进攻",
					"防守","篮板","助攻","抢断","盖帽","失误","犯规","得分"};
			
		ArrayList<Integer> wid = new ArrayList<Integer>();
		wid.add(70);wid.add(150);wid.add(50);wid.add(50);wid.add(50);wid.add(50);wid.add(50);wid.add(50);wid.add(50);
		wid.add(50);wid.add(50);wid.add(50);wid.add(50);wid.add(50);wid.add(50);wid.add(50);wid.add(50);wid.add(50);
			
		NormalInfoTable = new playerTablePanel(datas,tbHead,wid,0,60,1280,450,50,true,true,season);
		NormalInfoTable.type = tableType;
		this.add(NormalInfoTable);
	}
	
	public void setHighInfoTablePanel(){
		ArrayList<PlayerVO> vo = playerbl.filterHigh(sortBy,position,league,0,100,season);
		PlayerVO2List v2l = new PlayerVO2List();
		ArrayList<ArrayList<String>> datas = v2l.highData(vo);
		
		String[] tbHead = {"","姓名","助攻率","盖帽率","抢断率","防守篮板率","进攻篮板率","失误率","使用率","GmSc",
				"真实命中率","篮板率","投篮效率"};
//		String[] tbHead = {"","姓名","助攻率","盖帽率","抢断率","防守篮板率","进攻篮板率","失误率","使用率","GmSc",
//					"真实命中率","篮板率","投篮效率","ORPM","DRPM","RPM","WAR","VALUE"};
			
		ArrayList<Integer> wid = new ArrayList<Integer>();
		wid.add(70);wid.add(150);wid.add(50);wid.add(50);wid.add(50);wid.add(80);wid.add(80);wid.add(60);wid.add(50);wid.add(50);
		wid.add(80);wid.add(80);wid.add(80);

//		wid.add(70);wid.add(140);wid.add(50);wid.add(50);wid.add(50);wid.add(70);wid.add(70);wid.add(50);wid.add(50);wid.add(50);
//		wid.add(70);wid.add(50);wid.add(70);
//		wid.add(40);wid.add(40);wid.add(40);wid.add(40);wid.add(40);
			
		HighInfoTable = new playerTablePanel(datas,tbHead,wid,0,60,1280,450,50,true,true,season);
		HighInfoTable.type = ListType.high;

		this.add(HighInfoTable);
	}

	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setLayout(null);
		jf.setSize(1280,700);
		jf.setLocationRelativeTo(null);
		jf.add(new PlayerDataList());
		jf.setVisible(true);
	}

}

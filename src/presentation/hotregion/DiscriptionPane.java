package presentation.hotregion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;






//import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import blservice.playerblservice.PlayerBLService;
import blservice.teamblservice.TeamBLService;
import businesslogic.playerbl.PlayerBL;
import businesslogic.teambl.TeamBL;
import presentation.common.SeasonComboBox;
import presentation.hotregion.DiscriptionPane.OptionPane;
import presentation.main.Mainframe;
import vo.HotZoneVO;
import vo.playervo.PlayerVO;

public class DiscriptionPane extends JPanel implements RegionObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Color unselected = new Color(0, 0, 0);
	public static Color selected = new Color(50, 50, 50);
	
	MouseListener map;
	
	private HotZoneVO zoneSeason;
	private HotZoneVO zoneLast;
	
//	private JLabel popUpLabel;
//	private Popup popfUp;
	private boolean isPlayer;
	private int id;
	private int season = 14;
	private boolean isPerformance = true;
	private boolean isPreseason = true;
	
	private DataOptionLabel dataOptionLabel;
	private OptionPane optionsPane;
	private ContentPane content;
	private HotZoneGlassLabel glass;	//数字
	
	
	
	public DiscriptionPane(int id, boolean isPlayer, int season){
		this.id = id;
		this.isPlayer = isPlayer;
		this.season = season;
		initialise();
	}
	
	private void initialise(){
		this.setLayout(null);
		this.setSize(760, 455);
		setGlass();
		setGameOption();
		setTitle();
		setContent();

		refresh();
		
		
	}
	
	public void refresh(){
		ArrayList<HotZoneVO> vos = new ArrayList<HotZoneVO>();
		if(isPlayer){
			PlayerBLService bl = new PlayerBL();
			vos = bl.getHotZone(id, isPerformance, isPreseason, season);
			System.out.println("球员热区");
		}else{
			TeamBLService bl = new TeamBL();
			vos = bl.getHotZone(id, isPerformance, isPreseason, season);
			System.out.println("球队热区");
		}
		zoneSeason = vos.get(0);
		zoneLast = vos.get(1);
//		zoneSeason = new HotZone_Stub().getHotZoneVO(id, isPerformance, isPreseason, season);
//		zoneLast = new HotZone_Stub().getHotZoneVO(id, isPerformance, isPreseason, season);;
		changeContent(0);
		updateGlass();
		this.repaint();
	}

	
	private void setTitle(){
		JPanel titlePane = new JPanel();
		titlePane.setLayout(null);
		titlePane.setBackground(new Color(44, 44, 120));
		titlePane.setBounds(0, 0, 760, 40);
		this.add(titlePane);
		
		dataOptionLabel = new DataOptionLabel();
		dataOptionLabel.setLocation(600, 0);
		titlePane.add(dataOptionLabel);
	
	}
	
	private void setGameOption(){
		optionsPane = new OptionPane();
		this.add(optionsPane);
	}
	
	private void setGlass(){
		glass = new HotZoneGlassLabel();
		glass.setLocation(0, 290);
		Mainframe.getFrame().getPopupContainer().add(glass);
	}

	private void updateGlass(){
		ArrayList<Double> values = new ArrayList<Double>();
		if(!isPerformance){
			values.add((double) (100*zoneSeason.zone1D/zoneSeason.zone1N));
			values.add((double) (100*zoneSeason.zone2D/zoneSeason.zone2N));
			values.add((double) (100*zoneSeason.zone3D/zoneSeason.zone3N));
			values.add((double) (100*zoneSeason.zone4D/zoneSeason.zone4N));
			values.add((double) (100*zoneSeason.zone5D/zoneSeason.zone5N));
			values.add((double) (100*zoneSeason.zone6D/zoneSeason.zone6N));
			values.add((double) (100*zoneSeason.zone7D/zoneSeason.zone7N));
			values.add((double) (100*zoneSeason.zone8D/zoneSeason.zone8N));
			values.add((double) (100*zoneSeason.zone9D/zoneSeason.zone9N));
			values.add((double) (100*zoneSeason.zone10D/zoneSeason.zone10N));
			values.add((double) (100*zoneSeason.zone11D/zoneSeason.zone11N));
			values.add((double) (100*zoneSeason.zone12D/zoneSeason.zone12N));
			values.add((double) (100*zoneSeason.zone13D/zoneSeason.zone13N));
			values.add((double) (100*zoneSeason.zone14D/zoneSeason.zone14N));
		}else{
			values.add((double) (100*zoneSeason.zone1N/zoneSeason.zone1D));
			values.add((double) (100*zoneSeason.zone2N/zoneSeason.zone2D));
			values.add((double) (100*zoneSeason.zone3N/zoneSeason.zone3D));
			values.add((double) (100*zoneSeason.zone4N/zoneSeason.zone4D));
			values.add((double) (100*zoneSeason.zone5N/zoneSeason.zone5D));
			values.add((double) (100*zoneSeason.zone6N/zoneSeason.zone6D));
			values.add((double) (100*zoneSeason.zone7N/zoneSeason.zone7D));
			values.add((double) (100*zoneSeason.zone8N/zoneSeason.zone8D));
			values.add((double) (100*zoneSeason.zone9N/zoneSeason.zone9D));
			values.add((double) (100*zoneSeason.zone10N/zoneSeason.zone10D));
			values.add((double) (100*zoneSeason.zone11N/zoneSeason.zone11D));
			values.add((double) (100*zoneSeason.zone12N/zoneSeason.zone12D));
			values.add((double) (100*zoneSeason.zone13N/zoneSeason.zone13D));
			values.add((double) (100*zoneSeason.zone14N/zoneSeason.zone14D));
		}
		
		
		glass.changeValue(values);
	}
	
	private void setContent(){
		content = new ContentPane();
		this.add(content);
	}
	
	public void changeContent(int index){
		if(index == 8){
			index = 9;
		}else if(index == 9){
			index = 8;
		}
		glass.makeBig(index);
		
		String shotPlace = null;
		int seasonD = 1;
		int seasonN = 1;
		int latestD = 1;
		int latestN = 1;
		
		boolean total = false;
		switch(index){
		case 0:
			total = true;
			shotPlace = optionsPane.getCurrentSelection() + " 总计";
			break;
		case 1:
			shotPlace = "左侧24英尺以外";
			seasonD = zoneSeason.zone1D;
			seasonN = zoneSeason.zone1N;
			latestD = zoneLast.zone1D;
			latestN = zoneLast.zone1N;
			break;
		case 2:
			shotPlace = "左侧16-24英尺";
			seasonD = zoneSeason.zone2D;
			seasonN = zoneSeason.zone2N;
			latestD = zoneLast.zone2D;
			latestN = zoneLast.zone2N;
			break;
		case 3:
			shotPlace = "左侧8-16英尺";
			seasonD = zoneSeason.zone3D;
			seasonN = zoneSeason.zone3N;
			latestD = zoneLast.zone3D;
			latestN = zoneLast.zone3N;
			break;
		case 4:
			shotPlace = "8英尺以内";
			seasonD = zoneSeason.zone4D;
			seasonN = zoneSeason.zone4N;
			latestD = zoneLast.zone4D;
			latestN = zoneLast.zone4N;
			break;
		case 5:
			shotPlace = "右侧8-16英尺";
			seasonD = zoneSeason.zone5D;
			seasonN = zoneSeason.zone5N;
			latestD = zoneLast.zone5D;
			latestN = zoneLast.zone5N;
			break;
		case 6:
			shotPlace = "右侧16-24英尺";
			seasonD = zoneSeason.zone6D;
			seasonN = zoneSeason.zone6N;
			latestD = zoneLast.zone6D;
			latestN = zoneLast.zone6N;
			break;
		case 7:
			shotPlace = "右侧24英尺以外";
			seasonD = zoneSeason.zone7D;
			seasonN = zoneSeason.zone7N;
			latestD = zoneLast.zone7D;
			latestN = zoneLast.zone7N;
			break;
		case 8:
			shotPlace = "中间8-16英尺";
			seasonD = zoneSeason.zone8D;
			seasonN = zoneSeason.zone8N;
			latestD = zoneLast.zone8D;
			latestN = zoneLast.zone8N;
			break;
		case 9:
			shotPlace = "左侧居中16-24英尺";
			seasonD = zoneSeason.zone9D;
			seasonN = zoneSeason.zone9N;
			latestD = zoneLast.zone9D;
			latestN = zoneLast.zone9N;
			break;
		case 10:
			shotPlace = "中间16-24英尺";
			seasonD = zoneSeason.zone10D;
			seasonN = zoneSeason.zone10N;
			latestD = zoneLast.zone10D;
			latestN = zoneLast.zone10N;
			break;
		case 11:
			shotPlace = "右侧居中16-24英尺";
			seasonD = zoneSeason.zone11D;
			seasonN = zoneSeason.zone11N;
			latestD = zoneLast.zone11D;
			latestN = zoneLast.zone11N;
			break;
		case 12:
			shotPlace = "左侧居中24英尺以外";
			seasonD = zoneSeason.zone12D;
			seasonN = zoneSeason.zone12N;
			latestD = zoneLast.zone12D;
			latestN = zoneLast.zone12N;
			break;
		case 13:
			shotPlace = "中间24英尺以外";
			seasonD = zoneSeason.zone13D;
			seasonN = zoneSeason.zone13N;
			latestD = zoneLast.zone13D;
			latestN = zoneLast.zone13N;
			break;
		case 14:
			shotPlace = "右侧居中24英尺以外";
			seasonD = zoneSeason.zone14D;
			seasonN = zoneSeason.zone14N;
			latestD = zoneLast.zone14D;
			latestN = zoneLast.zone14N;
			break;
		default:
			total = true;
			break;
		}
//		System.out.println(shotPlace);
		
		
		if(content!=null){
			content.changeShotPlace(shotPlace);
		
			if(total){
				int sD = zoneSeason.zone1D + zoneSeason.zone2D + zoneSeason.zone3D + zoneSeason.zone4D +
						zoneSeason.zone5D + zoneSeason.zone6D + 
						zoneSeason.zone7D + zoneSeason.zone8D + zoneSeason.zone9D + zoneSeason.zone10D + 
						zoneSeason.zone11D + zoneSeason.zone12D + zoneSeason.zone13D + zoneSeason.zone14D;
				
				int sN = zoneSeason.zone1N + zoneSeason.zone2N + zoneSeason.zone3N + zoneSeason.zone4N +
						zoneSeason.zone5N + zoneSeason.zone6N + 
						zoneSeason.zone7N + zoneSeason.zone8N + zoneSeason.zone9N + zoneSeason.zone10N + 
						zoneSeason.zone11N + zoneSeason.zone12N + zoneSeason.zone13N + zoneSeason.zone14N;
				
				int lD = zoneLast.zone1D + zoneLast.zone2D + zoneLast.zone3D + zoneLast.zone4D +
						zoneLast.zone5D + zoneLast.zone6D + 
						zoneLast.zone7D + zoneLast.zone8D + zoneLast.zone9D + zoneLast.zone10D + 
						zoneLast.zone11D + zoneLast.zone12D + zoneLast.zone13D + zoneLast.zone14D;
				
				int lN = zoneLast.zone1N + zoneLast.zone2N + zoneLast.zone3N + zoneLast.zone4N +
						zoneLast.zone5N + zoneLast.zone6N + 
						zoneLast.zone7N + zoneLast.zone8N + zoneLast.zone9N + zoneLast.zone10N + 
						zoneLast.zone11N + zoneLast.zone12N + zoneLast.zone13N + zoneLast.zone14N;
				if(!isPerformance){
					content.changeSeasonPresentation(sD, sN);
					content.changeLatestPresentation(lD, lN);
				}else{
					content.changeSeasonPresentation(sN, sD);
					content.changeLatestPresentation(lN, lD);
				}
			}else{
				if(!isPerformance){
					content.changeSeasonPresentation(seasonD, seasonN);
					content.changeLatestPresentation(latestD, latestN);
				}else{
					content.changeSeasonPresentation(seasonN, seasonD);
					content.changeLatestPresentation(latestN, latestD);
				}
			}
		
			
		}
	}
	
	class OptionPane extends JLabel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		GameOption preseason;
		GameOption regular;
		
		private String currentSelection = "季后赛";
		
		OptionPane(){
			this.setBounds(0, 40, 760, 60);
//			this.setOpaque(true);
			this.setBackground(Color.orange);
			this.setLayout(new GridLayout(1, 2, 5, 0));
			
			setOptions();
		}
		
		private void setOptions(){
			preseason = new GameOption("2014-2015 季后赛");
			preseason.setOptionName("季后赛");
			preseason.setObserver(this);
			preseason.setSelect();
			
			regular = new GameOption("2014-2015 常规赛");
			regular.setOptionName("常规赛");
			regular.setObserver(this);
			
			preseason.setObserver(this);
			regular.setObserver(this);
			
			this.add(preseason);
			this.add(regular);
		}
		
		public void selectOption(GameOption select){
			preseason.setUnselect();
			regular.setUnselect();
			select.setSelect();
			
			currentSelection = select.getOptionName();
			
			if(currentSelection.equals("季后赛")){
				isPreseason = true;
			}else{
				isPreseason = false;
			}
			
			refresh();
			
			content.setCurrentPresent(select.getOptionName());
		}
		
		public String getCurrentSelection(){
			return currentSelection;
		}
		
	}

	class ContentPane extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private String currentPresent = "季后赛";
		
		private BigText shotPlaceName;
		
		private BigText seasonPreInRate;
		private MiddleText seasonPreInDivision;
		
		private BigText latestPreInRate;
		private MiddleText latestPreInDivision;

		ContentPane(){
			this.setBounds(0, 110, 760, 345);
			this.setLayout(null);
			
			setShotPlace();
			setSeasonPresentation();
			setLatestPresentation();
			if(isPlayer){
				setPhoto();
			}
		}
		
		private void setPhoto(){
			PhotoLabel photo = new PhotoLabel(new Dimension(150, 300));
			photo.setSize(300, 400);
			PlayerBLService bl = new PlayerBL();
			PlayerVO vo = bl.findPlayerData(id, season);
			
			photo.setImage("actionPNG/"+vo.name+".png");
//			photo.setOpaque(true);
//			photo.setBackground(Color.ORANGE);
			photo.setLocation(500, -100);
			this.add(photo);
			photo.repaint();
			photo.setVisible(true);
			
//			JLabel photo = new JLabel();
//			photo.setBounds(200, 100, 150, 300);
//			Image image = null;
//			try {
//				image = ImageIO.read(new File("Aaron Brooks.png"));
//				
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			ImageIcon icon = new ImageIcon(image);
//			
//			photo.setIcon(icon);
//			this.add(photo);
		}
		
		public void setCurrentPresent(String s){
			currentPresent = s;
			shotPlaceName.setText(s + " 总计");
		}
		
		private void setShotPlace(){
			SmallText shotPlace = new SmallText("投篮区域");
			shotPlace.setBounds(30, 20, 80, 20);
			this.add(shotPlace);
			
			shotPlaceName = new BigText();
			shotPlaceName.setBounds(30, 45, 300, 40);
			shotPlaceName.setText(currentPresent + " 总计"); 
			this.add(shotPlaceName);
		}
		
		private void setSeasonPresentation(){
			SmallText seasonPresentation = new SmallText("赛季表现");
			seasonPresentation.setBounds(30, 130, 80, 20);
			this.add(seasonPresentation);
			
			seasonPreInRate = new BigText();
			seasonPreInRate.setBounds(30, 150, 100, 40);
			seasonPreInRate.setText("99.9" + "%");
			this.add(seasonPreInRate);
			
			seasonPreInDivision = new MiddleText();
			seasonPreInDivision.setBounds(30, 185, 100, 30);
			seasonPreInDivision.setText("99" + "/" + "100");
			this.add(seasonPreInDivision);
		}
		
		private void setLatestPresentation(){

			SmallText latestPresentation = new SmallText("近5场表现");
			latestPresentation.setBounds(200, 130, 80, 20);
			this.add(latestPresentation);

			latestPreInRate = new BigText();
			latestPreInRate.setBounds(200, 150, 100, 40);
			latestPreInRate.setText("99.9" + "%");
			this.add(latestPreInRate);
			
			latestPreInDivision = new MiddleText();
			latestPreInDivision.setBounds(200, 185, 100, 30);
			latestPreInDivision.setText("99" + "/" + "100");
			this.add(latestPreInDivision);
		}

		public void changeShotPlace(String s){
			shotPlaceName.setText(s);
		}
		
		public void changeSeasonPresentation(int a, int b){
			seasonPreInDivision.setText(a+"/"+b);
			double temp = (double)(a*100/b);
			if(temp>100){
				seasonPreInRate.setText("Data Error");
			}else{
				seasonPreInRate.setText(temp+"%");
			}
		}
		
		public void changeLatestPresentation(int a, int b){
			latestPreInDivision.setText(a+"/"+b);
			double temp = (double)(a*100/b);
			if(temp>100){
				latestPreInRate.setText("Data Error");
			}else{
				latestPreInRate.setText(temp+"%");
			}
		}
	
	
	}

	
	class DataOptionLabel extends JLabel implements MouseListener{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private String currentSelect = "表现";

		JLabel presentButton;
		JLabel distributeButton;
		
		public DataOptionLabel() {
			this.setSize(105, 35);
			this.setOpaque(true);
			this.setBackground(new Color(24, 24, 70));
			this.setLayout(null);
			this.addMouseListener(this);
			
			setPresent();
			setDistribute();
			
			selectLeft();
		}
		
		//表现
		private void setPresent(){
			presentButton = new JLabel();
			presentButton.setText("表现");
			presentButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
			presentButton.setForeground(Color.white);
			presentButton.setBackground(new Color(44, 44, 120));
//			presentButton.setOpaque(true);
			presentButton.setHorizontalAlignment(JLabel.CENTER);
			presentButton.setBounds(5, 5, 45, 25);
			this.add(presentButton);
			
		}
		
		//分布
		private void setDistribute(){
			distributeButton = new JLabel();
			distributeButton.setText("分布");
			distributeButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
			distributeButton.setForeground(Color.white);
			distributeButton.setBackground(new Color(44, 44, 120));
			distributeButton.setHorizontalAlignment(JLabel.CENTER);
			distributeButton.setBounds(55, 5, 45, 25);
//			distributeButton.setOpaque(true);
			this.add(distributeButton);
		}
		
		private void selectLeft(){
			presentButton.setOpaque(true);
			distributeButton.setOpaque(false);
			this.currentSelect = "表现";
			DiscriptionPane.this.isPerformance = true;
			System.out.println("left");
			DiscriptionPane.this.refresh();
			this.repaint();
		}
		
		private void selectRight(){
			presentButton.setOpaque(false);
			distributeButton.setOpaque(true);
			this.currentSelect = "分布";
			DiscriptionPane.this.isPerformance = false;
			System.out.println("right");
			DiscriptionPane.this.refresh();
			this.repaint();
		}
		
		public String getSelectedOption(){
			return currentSelect;
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
			int x = e.getX();
//			System.out.println(x + "   "+this.getWidth()/2);
			if(x < this.getWidth()/2){
				selectLeft();
			}else{
				selectRight();
			}
			refresh();
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



	//季前赛还是常规赛
	class GameOption extends JLabel implements MouseListener{
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		OptionPane observer;
		String optionName;
		
		private boolean isSelected = false;
		
		
		public GameOption(String text){
			this.setBackground(Color.BLACK);
			this.setForeground(Color.WHITE);
			this.setOpaque(true);
			this.setFont(new Font("微软雅黑", Font.BOLD, 18));
			this.setHorizontalAlignment(JLabel.CENTER);
			this.setText(text);
			
			this.addMouseListener(this);
		}
		
		public void setOptionName(String s){
			this.optionName = s;
		}
		
		public String getOptionName(){
			return optionName;
		}
		
		public void setObserver(OptionPane observer){
			this.observer = observer;
		}
	
		public void setUnselect(){
			this.setBackground(unselected);
			isSelected = false;
		}
		
		public void setSelect(){
			this.setBackground(selected);
			isSelected = true;
		}
		
		public void mouseClicked(MouseEvent e) {
			
		}
	
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void mouseReleased(MouseEvent e) {
			observer.selectOption(this);
			setSelect();
		}
	
		@Override
		public void mouseEntered(MouseEvent e) {
			if(!isSelected){
				this.setBackground(selected);
			}
		}
	
		@Override
		public void mouseExited(MouseEvent e) {
			if(!isSelected){
				this.setBackground(unselected);
			}
		}
		
	}
}
class SmallText extends JTextField{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SmallText(String text) {
		this.setOpaque(false);
		this.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		this.setEditable(false);
		this.setBorder(null);
		this.setText(text);
	}
}

class MiddleText extends JTextField{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MiddleText() {
		this.setOpaque(false);
		this.setFont(new Font("微软雅黑", Font.PLAIN + Font.BOLD, 19));
		this.setEditable(false);
		this.setForeground(new Color(120, 120, 120));
		this.setBorder(null);
	}
}

class BigText extends JTextField{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BigText() {
		this.setOpaque(false);
		this.setFont(new Font("微软雅黑", Font.BOLD, 32));
		this.setForeground(Color.DARK_GRAY);
		this.setEditable(false);
		this.setBorder(null);
	}
}
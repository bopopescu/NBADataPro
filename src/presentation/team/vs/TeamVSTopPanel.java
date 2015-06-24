package presentation.team.vs;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import blservice.teamblservice.TeamBLService;
import businesslogic.teambl.TeamBL;
import businesslogic.teambl.TeamBL_stub;
import presentation.common.PhotoLabel;
import presentation.team.teamDetail.TeamMiddlePanel;
import vo.teamvo.TeamVO;

public class TeamVSTopPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel localTeamPhotoLabel;
	JLabel anotherTeamPhotoLabel;
	JLabel VSUpLabel;
	JLabel VSDownLabel;
	JLabel localInfoGroupsLabel;
	JLabel anotherInfoGroupsLabel;
	
	JLabel chooseList;
	
	TeamMiddlePanel observer;
	
	JLabel searchLabel;
	JLabel settingLabel;
	
	JTextField searchText;

	Color lightest = new Color(46,110,180);
	Color light = new Color(33,82,138);
	Color darkest = new Color(29,72,121);
	
	public String msg;
	JPanel main;
	
	public TeamVO anotherVO = new TeamVO();
	TeamVO localVO;
	
	public TeamVSTopPanel(JPanel middle,TeamVO localteam){
		this.setLayout(null);
		this.setBounds(0, 0, 1280,197);
		this.setBackground(Color.WHITE);
		this.localVO = localteam;
		
		setAttri();
		setLocalTeamPhotoLabel();
		setAnotherTeamPhotoLabel();
		setVSUpLabel();
		setVSDownLabel();
		setLocalInfoGroupsLabel();
		setAnotherInfoGroupsLabel();
		
		setSearchLabel();
		searchLabel.setVisible(false);
		this.main = middle;
	}
	
	public void setAttri(){
		anotherVO.teamName = "球队";
		anotherVO.photo = "nba";
	}

	public void setLocalTeamPhotoLabel (){
		localTeamPhotoLabel = new PhotoLabel(new ImageIcon("teamsPNG/" + localVO.photo + ".png").getImage());
		System.out.println(localVO.teamName);
		localTeamPhotoLabel.setHorizontalAlignment(JLabel.RIGHT);
		localTeamPhotoLabel.setBounds(300,25,230,115);
		localTeamPhotoLabel.setBackground(Color.WHITE);
		localTeamPhotoLabel.setOpaque(true);
		localTeamPhotoLabel.setVisible(true);
		this.add(localTeamPhotoLabel);
	}
	
	public void setAnotherTeamPhotoLabel (){
		anotherTeamPhotoLabel = new PhotoLabel(new ImageIcon("teamsPNG/" + anotherVO.photo + ".png").getImage());
		anotherTeamPhotoLabel.setHorizontalAlignment(JLabel.RIGHT);
		anotherTeamPhotoLabel.setBounds(730,20,230,115);
		anotherTeamPhotoLabel.setBackground(Color.WHITE);
		anotherTeamPhotoLabel.setOpaque(true);
		anotherTeamPhotoLabel.setVisible(true);
		this.add(anotherTeamPhotoLabel);
	}

	public void setLocalInfoGroupsLabel(){
		localInfoGroupsLabel = new JLabel();
		localInfoGroupsLabel.setBounds(0,145,535,55);
		localInfoGroupsLabel.setOpaque(true);
		localInfoGroupsLabel.setBackground(light);

		JLabel playerName = new JLabel(localVO.location,JLabel.RIGHT);
		playerName.setFont(new Font("Dialog",1,13));
		playerName.setForeground(Color.WHITE);
		playerName.setBounds(335,5,180,15);
		localInfoGroupsLabel.add(playerName);
		
		JLabel playerPos = new JLabel(localVO.teamName,JLabel.RIGHT);
		playerPos.setFont(new Font("Dialog",1,18));
		playerPos.setForeground(Color.WHITE);
		playerPos.setBounds(415,20,100,30);
		localInfoGroupsLabel.add(playerPos);

		this.add(localInfoGroupsLabel);
	}
	
	public void setAnotherInfoGroupsLabel(){
		System.out.println("143652387890987654");

		anotherInfoGroupsLabel = new JLabel();
		anotherInfoGroupsLabel.setBounds(715,145,565,55);
		anotherInfoGroupsLabel.setOpaque(true);
		anotherInfoGroupsLabel.setBackground(light);
		
		JLabel anotherName = new JLabel(anotherVO.location,JLabel.LEADING);
		anotherName.setFont(new Font("Dialog",1,13));
		anotherName.setForeground(Color.WHITE);
		anotherName.setBounds(15,5,180,15);
		anotherInfoGroupsLabel.add(anotherName);
		
		JLabel anotherPos = new JLabel(anotherVO.teamName);
		anotherPos.setFont(new Font("Dialog",1,18));
		anotherPos.setForeground(Color.WHITE);
		anotherPos.setBounds(15,20,180,30);
		anotherInfoGroupsLabel.add(anotherPos);
		settingLabel();
		this.add(anotherInfoGroupsLabel);
	}
	
	public void settingLabel(){
		settingLabel = new PhotoLabel(new ImageIcon("setting.png").getImage());
		settingLabel.setBounds(525,13,25,25);
		settingLabel.setBackground(light);
		settingLabel.setOpaque(true);
		settingLabel.setVisible(true);
		settingLabel.addMouseListener(new MouseAdapter()
		{	
			int select = 0;
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				select += 1;
				if(select%2 == 1){
					searchLabel.setVisible(true);
				}else{
					searchLabel.setVisible(false);
				}
			}
		});
		anotherInfoGroupsLabel.add(settingLabel);
	}

	public void setSearchLabel(){
		searchLabel = new JLabel();
		searchLabel.setBounds(950,100,330,45);
		searchLabel.setBackground(Color.LIGHT_GRAY);
		searchLabel.setOpaque(true);

		searchText = new JTextField();
		searchText.setBounds(5, 5, 340, 35);
//		searchText.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				main.setVisible(false);
//				System.out.println(searchText.getText());
//				msg = searchText.getText();
////				System.out.println("AAAAAAAAAAA" + msg);
//				ArrayList<TeamVO> list = new ArrayList<TeamVO>();
//				TeamBLService pds = new TeamBL_stub();
//				list = pds.findTeams(msg);
//				setChooseList(list);
//				main.add(chooseList,0);
//				main.setVisible(true);
//			}
//		});
		searchText.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				main.setVisible(false);
				System.out.println(searchText.getText());
				msg = searchText.getText();
//				System.out.println("AAAAAAAAAAA" + msg);
				ArrayList<TeamVO> list = new ArrayList<TeamVO>();
//				TeamBLService pds = new TeamBL_stub();
				TeamBLService pds = new TeamBL();

				list = pds.findTeams(msg);
				setChooseList(list);
				main.add(chooseList,0);
				main.setVisible(true);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		searchLabel.add(searchText);
		this.add(searchLabel,0);
		
	}
	
	public void registerObserver(TeamMiddlePanel o){
		observer = o;
	}
	
	public void setChooseList(ArrayList<TeamVO> list){
		chooseList = new JLabel();
		chooseList.setBounds(950, 145, 330, 50*list.size());
		chooseList.setLayout(new GridLayout(list.size(),1,0,0));
		chooseList.setBackground(Color.LIGHT_GRAY);
		chooseList.setOpaque(true);
		for(int i = 0; i < list.size(); i++){
			JLabel item = new JLabel();
			item.setSize(280, 50);
			item.setBackground(Color.WHITE);
			item.setOpaque(true);

			JLabel name = new JLabel(list.get(i).teamName,JLabel.LEADING);
			name.setBounds(100,0,100,50);
			name.setForeground(darkest);
			name.setBackground(Color.white);
			name.setOpaque(true);
			item.add(name);
			
			JLabel photo = new PhotoLabel(new ImageIcon("teamsPNG/" + list.get(i).photo + ".png").getImage());
			photo.setBounds(10,0,80,50);
			photo.setBackground(Color.WHITE);
			photo.setOpaque(true);
			photo.setVisible(true);
			item.add(photo);
			
			name.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					TeamVSTopPanel.this.setVisible(false);
//					TeamBLService tds = new TeamBL_stub();
					TeamBLService tds = new TeamBL();

					anotherVO = tds.findTeamInfo(name.getText(),observer.season); 
					main.setVisible(false);
					
					if(anotherTeamPhotoLabel != null){
						remove(anotherTeamPhotoLabel);
					}
					if(localTeamPhotoLabel != null){
//						remove(localTeamPhotoLabel);
					}
					if(anotherInfoGroupsLabel.getParent() != null){
						remove(anotherInfoGroupsLabel);
					}
//					remove(anotherInfoGroupsLabel);
					setAnotherInfoGroupsLabel();
					
					
					System.out.println("wtf=========="+name.getText());
					
					setAnotherTeamPhotoLabel();
					chooseList.setVisible(false);
					TeamVSTopPanel.this.setVisible(true);
					TeamVSTopPanel.this.repaint();

					ArrayList<Double> local = new ArrayList<Double>();
					ArrayList<Double> another = new ArrayList<Double>();
					local.add(localVO.avgPoint);local.add(localVO.avgRebound);local.add(localVO.avgAssist);
					local.add(localVO.three);local.add(localVO.penalty);
					another.add(anotherVO.avgPoint);another.add(anotherVO.avgRebound);another.add(anotherVO.avgAssist);
					another.add(anotherVO.three);another.add(anotherVO.penalty);
					
//					main.setVisible(false);
//					main.remove(main.getComponentAt(new Point(30,300)));
					if(observer.digramItemSelected == "演化历程"){
						observer.updateEvolutionOPP(name.getText());
					}else{
						((TeamMiddlePanel) main).teamVSContentPanel.updateVSContentPanel(local,another,false,new Point(0,0));
					}
					main.setVisible(true);

					
					
					
					System.out.println(observer.season);
					

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
			
			chooseList.add(item);
		}
		chooseList.setVisible(true);
//		this.add(chooseList,0);
//		main.add(chooseList,0);

	}


	public void setVSUpLabel(){
		VSUpLabel = new JLabel("球队对比",JLabel.CENTER);
		VSUpLabel.setFont(new Font("Dialog",1,30));
		VSUpLabel.setForeground(Color.GRAY);
		VSUpLabel.setBounds(535,35,180,60);
		this.add(VSUpLabel);
	}

	public void setVSDownLabel(){
		VSDownLabel = new JLabel("VS",JLabel.CENTER);
		VSDownLabel.setFont(new Font("Dialog",1,30));
		VSDownLabel.setForeground(Color.WHITE);
		VSDownLabel.setBackground(lightest);
		VSDownLabel.setOpaque(true);
		VSDownLabel.setBounds(535,145,180,55);
		this.add(VSDownLabel);
	}
	
	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setLayout(null);
		jf.setSize(1280,700);
		jf.setLocationRelativeTo(null);
//		jf.add(new TeamVSTopPanel());
		jf.setVisible(true);
	}

}

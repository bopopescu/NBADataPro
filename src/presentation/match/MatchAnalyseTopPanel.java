package presentation.match;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import blservice.matchblservice.MatchBLService;
import businesslogic.matchbl.MatchBL;
import presentation.common.PhotoLabel;
import presentation.main.Mainframe;
import presentation.team.teamDetail.TeamMiddlePanel;
import vo.matchvo.MatchVO;

public class MatchAnalyseTopPanel extends MatchPanel{

	public MatchAnalyseTopPanel(int matchID) {
		super(matchID);
		// TODO Auto-generated constructor stub
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public void setContent(){
		String[] msg = {vo.homeTeam.location+" "+vo.homeTeam.name,"teamsPNG/"+vo.homeTeam.photo+".png",
				vo.guestTeam.location+" "+vo.guestTeam.name,"teamsPNG/"+vo.guestTeam.photo+".png"};
		matchDataAnalysePanel = new MatchDataAnalysePanel(vo.homeTeamPlayer,vo.guestTeamPlayer,msg);
		this.add(matchDataAnalysePanel);
		repaint();
	}



}

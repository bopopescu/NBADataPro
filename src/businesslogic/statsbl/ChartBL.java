package businesslogic.statsbl;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.math3.stat.StatUtils;

import data.playerdata.PlayerData;
import data.teamdata.TeamData;
import dataservice.playerdataservice.PlayerDataService;
import dataservice.playerdataservice.PlayerData_stub;
import dataservice.teamdataservice.TeamDataService;
import dataservice.teamdataservice.TeamData_stub;
import po.stats.EvolutionPO;
import po.stats.FiveUnitPO;
import po.stats.OffDefPO;
import po.stats.OnOffPO;
import po.stats.ShotAnlsPO;
import po.stats.TurnoverAndFoulPO;
import po.stats.WLProfilesPO;
import vo.AttriOption;
import vo.playervo.PlayerStatsVO;
import vo.teamvo.TeamWLProfilesVO;
import vo.teamvo.Top5statsVO;
import blservice.chartsblservice.ChartBLService;

public class ChartBL implements ChartBLService{

//	TeamDataService tds = new TeamData_stub();
	TeamDataService tds = new TeamData();
	PlayerDataService pds = new PlayerData();

	
	
	@Override
	public ArrayList<JPanel> shootingAnls_t(int teamID, int season) {
		ArrayList<JPanel> result = new ArrayList<JPanel>();
		Charts chart = new Charts();
		String[] sections =  new String[]{"Jump","Close","Dunk","Tips"};
		ShotAnlsPO[] po = new ShotAnlsPO[]{};
			po = tds.shootAnalysis(teamID, season);
			
		if(po==null){
			for(int i=0;i<5;i++)
				result.add(new JPanel());
			return result;
		}
		
		JPanel chart1 = chart.OffDefColumn(new double[][]{po[0].eFG,po[1].eFG}, "Shooting Details --Effective Field Goal", sections);
		JPanel chart2 = chart.OffDefColumn(new double[][]{po[0].Ast,po[1].Ast}, "Shooting Details --Assist", sections);
		JPanel chart3 = chart.OffDefColumn(new double[][]{po[0].Pts,po[1].Pts}, "Shooting Details --Points", sections);
		JPanel chart4 = chart.PieChart(po[0].Att, sections, "Offense Shooting Details");
		JPanel chart5 = chart.PieChart(po[1].Att, sections, "Defense Shooting Details");
		
		result.add(chart1);//eFG 攻防对比
		result.add(chart2);//Ast 攻防对比
		result.add(chart3);//Pts 攻防对比
		result.add(chart4);//进攻投篮pie
		result.add(chart5);//防守投篮pie
		return result;
	}

	@Override
	public ArrayList<JPanel> shotClockAnls_t(int teamID, int season) {
		ArrayList<JPanel> result = new ArrayList<JPanel>();
		Charts chart = new Charts();
		String[] sections =  new String[]{"0-10","11-15","16-20","21+"};
		ShotAnlsPO[] po = new ShotAnlsPO[]{};
			po = tds.shotClockAnls(teamID, season);
		
		if(po==null){
			for(int i=0;i<5;i++)
				result.add(new JPanel());
			return result;
		}
			
		JPanel chart1 = chart.OffDefColumn(new double[][]{po[0].eFG,po[1].eFG}, "Shot Clock --Effective Field Goal", sections);
		JPanel chart2 = chart.OffDefColumn(new double[][]{po[0].Ast,po[1].Ast}, "Shot Clock --Assist", sections);
		JPanel chart3 = chart.OffDefColumn(new double[][]{po[0].Pts,po[1].Pts}, "Shot Clock --Points", sections);
		JPanel chart4 = chart.PieChart(po[0].Att, sections, "Offense Shot Clock");
		JPanel chart5 = chart.PieChart(po[1].Att, sections, "Defense Shot Clock");
		
		result.add(chart1);//eFG 攻防对比
		result.add(chart2);//Ast 攻防对比
		result.add(chart3);//Pts 攻防对比
		result.add(chart4);//进攻投篮pie
		result.add(chart5);//防守投篮pie
		return result;
	}

	@Override
	public ArrayList<JPanel> shootingAnls_p(int playerID, int season,boolean isClutch) {
		PlayerDataService pds = new PlayerData_stub();

		ArrayList<JPanel> result = new ArrayList<JPanel>();
		Charts chart = new Charts();
		String[] sections =  new String[]{"Jump","Close","Dunk","Tips"};
		ShotAnlsPO po = new ShotAnlsPO();
			if(isClutch)
				po = pds.shootAnalysis(playerID, season);
			else
				po = pds.shootAnalysis_clutch(playerID, season);
		
			if(po==null){
				for(int i=0;i<5;i++)
					result.add(new JPanel());
				return result;
			}
			
		String[] rowkeys = new String[]{""};
		JPanel chart1 = chart.posPER(new double[][]{po.eFG}, "Shooting Details --Effective Field Goal",rowkeys, sections);
		JPanel chart2 = chart.posPER(new double[][]{po.Ast}, "Shooting Details --Assist",rowkeys, sections);
		JPanel chart3 = chart.posPER(new double[][]{po.Pts}, "Shooting Details --Points",rowkeys, sections);
		JPanel chart4 = chart.posPER(new double[][]{po.Blk}, "Shooting Details --Block",rowkeys, sections);
		JPanel chart5 = chart.PieChart(po.Att, sections, "Shooting Details");
		
		result.add(chart1);//eFG 攻防对比
		result.add(chart2);//Ast 攻防对比
		result.add(chart3);//Pts 攻防对比
		result.add(chart4);//Blk 攻防对比
		result.add(chart5);//投篮pie
		return result;
	}

	@Override
	public ArrayList<JPanel> shotClockAnls_p(int playerID, int season,boolean isClutch) {
		PlayerDataService pds = new PlayerData_stub();

		ArrayList<JPanel> result = new ArrayList<JPanel>();
		Charts chart = new Charts();
		String[] sections =  new String[]{"0-10","11-15","16-20","21+"};
		ShotAnlsPO po = new ShotAnlsPO();
			if(isClutch)
				po = pds.shotClockAnls(playerID, season);
			else
				po = pds.shotClockAnls_clutch(playerID, season);
			if(po==null){
				for(int i=0;i<5;i++)
					result.add(new JPanel());
				return result;
			}
			
		String[] rowkeys = new String[]{""};
		JPanel chart1 = chart.posPER(new double[][]{po.eFG}, "Shot Clock --Effective Field Goal",rowkeys, sections);
		JPanel chart2 = chart.posPER(new double[][]{po.Ast}, "Shot Clock --Assist",rowkeys, sections);
		JPanel chart3 = chart.posPER(new double[][]{po.Pts}, "Shot Clock --Points",rowkeys, sections);
		JPanel chart4 = chart.posPER(new double[][]{po.Blk}, "Shot Clock --Block",rowkeys, sections);
		JPanel chart5 = chart.PieChart(po.Att, sections, "Shot Clock");
		
		result.add(chart1);//eFG 攻防对比
		result.add(chart2);//Ast 攻防对比
		result.add(chart3);//Pts 攻防对比
		result.add(chart4);//Blk 攻防对比
		result.add(chart5);//投篮pie
		return result;
	}

	
	@Override
	public ArrayList<JPanel> off_def(int teamID, int season) {
		ArrayList<JPanel> result = new ArrayList<JPanel>();
		Charts chart = new Charts();
		OffDefPO po = tds.off_defStats(teamID, season);
		if(po==null){
			for(int i=0;i<2;i++)
				result.add(new JPanel());
			return result;
		}
		String[] columnkeys1 = new String[]{"Pts","Poss","pPts"};
		String[] columnkeys2 = new String[]{"FG%","eFG%","FT","oReb","dReb","T/O","Block%","Blocks/foul"};
		JPanel chart1 = chart.OffDefColumn(new double[][]{po.off_notPct,po.def_notPct},"Team Offense-Deffense", columnkeys1);
		JPanel chart2 = chart.OffDefColumn(new double[][]{po.off_pct,po.def_pct},"Team Offense-Deffense", columnkeys2);

		result.add(chart1);//not pct
		result.add(chart2);//pct
		return result;
	}

	@Override
	public ArrayList<JPanel> turnover_fouls(int teamID, int season) {
		ArrayList<JPanel> result = new ArrayList<JPanel>();
		TurnoverAndFoulPO po = tds.turnover_fouls(teamID, season);
		if(po==null)
			return null;
		
		Charts chart = new Charts();
		String[] t_sections =  new String[]{"Off. Foul","Bad Pass","Ball Handling","Other"};
		String[] f_sections =  new String[]{"Shooting","Personal","Loose Ball","Other"};
		JPanel chart1 = chart.PieChart(po.t_own, t_sections, "Turnovers --Team");
		JPanel chart2 = chart.PieChart(po.t_opp, t_sections, "Turnovers --Opponent");
		JPanel chart3 = chart.PieChart(po.f_own, f_sections, "Fouls --Team");
		JPanel chart4 = chart.PieChart(po.f_opp, f_sections, "Fouls --Opponent");
		
		result.add(chart1);//己方失误
		result.add(chart2);//对方失误
		result.add(chart3);//己方犯规
		result.add(chart4);//对方犯规

		return result;
	}

	@Override
	public Top5statsVO top5manGroup(int teamID, int season, AttriOption attri, boolean isTeam) {
		Top5statsVO vo = new Top5statsVO();
		FiveUnitPO po = new FiveUnitPO();
		if(isTeam)
			po = tds.top5manGroup(teamID, season, attri);
		else
			po = pds.top5manGroup(teamID, season, attri);
		if(po==null)
			return null;
		double[] data = po.datas;
		Charts chart = new Charts();
		String[] columnkeys = new String[]{"1","2","3","4","5","6","7","8","9","10"};
		vo.chart = chart.posPER(new double[][]{data}, "Top Five Man Floor Units", new String[]{""}, columnkeys);
		vo.names = po.unitsName;
		return vo;
	}

	@Override
	public ArrayList<JPanel> position(int teamID, int season, AttriOption attri, boolean isTeam) {
		TeamDataService tds = new TeamData_stub();
		PlayerDataService pds = new PlayerData_stub();
		ArrayList<JPanel> result = new ArrayList<JPanel>();
		double[][] datas = new double[][]{};
		if(isTeam)
			datas = tds.position(teamID, season, attri);
		else
			datas = pds.position(teamID, season, attri);
		if(datas==null)
			return null;
		Charts chart = new Charts();
		for(int i=0;i<datas[1].length;i++)
			datas[1][i] = -datas[1][i];
		String[] columnkeys =  new String[]{"PG","SG","SF","PF","C"};
		JPanel chart1 = chart.posPER(new double[][]{datas[0],datas[1]}, "Team Production by Position",
				new String[]{"Own","Opp"},columnkeys); 
		JPanel chart2 = chart.posPER(new double[][]{datas[2]}, "Net Production by Position", new String[]{"Net"}, columnkeys);
		result.add(chart1);	//own-opp对比
		result.add(chart2);	//net
		return result;
	}

	@Override
	public JPanel evolution(ArrayList<String> names,String attri,boolean isTeam) {
		TeamDataService tds = new TeamData_stub();
		PlayerDataService pds = new PlayerData_stub();
		EvolutionPO po = new EvolutionPO();
		if(isTeam)
			po = tds.envolution(names, attri);
		else
			po = pds.envolution(names, attri);
		
		if(po==null)
			return new JPanel();
		
		int size = po.datas.get(0).length;
		System.out.print(size);
		Charts chart = new Charts();
		ArrayList<double[][]> ldd = new ArrayList<double[][]>();
		for(int i=0;i<po.datas.size();i++){
			
			double[] value = new double[size];
			double[] variance = new double[size];
			for(int j=0;j<size;j++){
				value[j] = StatUtils.mean(po.datas.get(i)[j]);
				variance[j] = StatUtils.populationVariance(po.datas.get(i)[j])/value[j];
			}
			ldd.add(new double[][]{po.years,value,variance});
		}
		JPanel result = chart.bubbleChart(ldd, names, size);
		return result;
	}

	@Override
	public ArrayList<JPanel> on_off(int playerID, int season) {
		ArrayList<JPanel> result = new ArrayList<JPanel>();
		OnOffPO po = pds.on_off(playerID, season);
		
		if(po==null){
			for(int i=0;i<9;i++)
				result.add(new JPanel());
			return result;
		}
		
		Charts chart = new Charts();
		JPanel chart1 = chart.OnOff(new double[][]{po.pPts_on,po.pPts_off,po.pPts_net}, "Pts per 100 Poss", new String[]{"Off","Def","Net"});
		result.add(chart1);
		JPanel chart2 = chart.OnOff(new double[][]{po.Pts_on,po.Pts_off,po.Pts_net}, "Points", new String[]{"Scored","Allowed","Net"});
		result.add(chart2);
		JPanel chart3 = chart.OnOff(new double[][]{po.FG_on,po.FG_off,po.FG_net}, "Effective FG%", new String[]{"Own","Opponent"});
		result.add(chart3);
		JPanel chart4 = chart.OnOff(new double[][]{po.Ast_on,po.Ast_off,po.Ast_net}, "Assist Field Goals", new String[]{"Own","Opponent"});
		result.add(chart4);
		JPanel chart5 = chart.OnOff(new double[][]{po.Blk_on,po.Blk_off,po.Blk_net}, "Shots Blocked", new String[]{"Own","Opponent"});
		result.add(chart5);
		JPanel chart6 = chart.OnOff(new double[][]{po.reb_on,po.reb_off,po.reb_net}, "Rebounding", new String[]{"Off","Def","Net"});
		result.add(chart6);
		JPanel chart7 = chart.OnOff(new double[][]{po.FTM_on,po.FTM_off,po.FTM_net}, "Free Throws Made", new String[]{"Own Made","Own Attemp","Opp Made","Opp Attemp"});
		result.add(chart7);
		JPanel chart8 = chart.OnOff(new double[][]{po.turnover_on,po.turnover_off,po.turnover_net}, "Turnovers", new String[]{"Off","Def","Net"});
		result.add(chart8);
		JPanel chart9 = chart.OnOff(new double[][]{po.foul_on,po.foul_off,po.foul_net}, "Fouls", new String[]{"Committed","Drawn","Net"});
		result.add(chart9);
		return result;
	}

	@Override
	public PlayerStatsVO turnovers(int playerID, int season, boolean isClutch) {
		PlayerDataService pds = new PlayerData_stub();
		PlayerStatsVO vo = new PlayerStatsVO();
		double[] datas = new double[]{};
		if(isClutch)
			datas = pds.turnovers_clutch(playerID, season);
		else
			datas = pds.turnovers(playerID, season);
		
		if(datas==null){
			vo.chart = new JPanel();
			vo.data = new double[5];
			return vo;
		}
		
		Charts chart = new Charts();
		vo.chart = chart.PieChart(new double[]{datas[0],datas[1],datas[2],datas[3]},
				new String[]{"Offensive Fouls","Bad Passes","Ball Handling","Others"}, "Turnovers and Ball Handling");
		vo.data = datas;
		return vo;
	}

	@Override
	public PlayerStatsVO passing(int playerID, int season, boolean isClutch) {
		PlayerDataService pds = new PlayerData_stub();
		PlayerStatsVO vo = new PlayerStatsVO();
		double[] datas = new double[]{};
		if(isClutch)
			datas = pds.passing_clutch(playerID, season);
		else
			datas = pds.passing(playerID, season);
		
		if(datas==null){
			vo.chart = new JPanel();
			vo.data = new double[9];
			return vo;
		}
		
		Charts chart = new Charts();
		vo.chart = chart.PieChart(new double[]{datas[0],datas[1],datas[2],datas[3]},
				new String[]{"3-Pt Ast","Jump Ast","Close Ast","Dunk Ast"}, "Passing Stats");
		vo.data = datas;
		return vo;
	}

	@Override
	public PlayerStatsVO shotBlock(int playerID, int season,boolean isClutch) {
		PlayerDataService pds = new PlayerData_stub();
		PlayerStatsVO vo = new PlayerStatsVO();
		double[] datas = new double[]{};
		if(isClutch)
			datas = pds.shotBlock_clutch(playerID, season);
		else
			datas = pds.shotBlock(playerID, season);
		if(datas==null){
			vo.chart = new JPanel();
			vo.data = new double[12];
			return vo;
		}
		
		Charts chart = new Charts();
		vo.chart = chart.PieChart(new double[]{datas[0],datas[1],datas[2],datas[3]},
				new String[]{"Offensive Fouls","Bad Passes","Ball Handling","Others"}, "Shot Blocking ");
		vo.data = datas;
		return vo;
	}

	
	@Override
	public TeamWLProfilesVO wl_Profile(int teamID, int season) {
		TeamDataService tds = new TeamData_stub();
		TeamWLProfilesVO vo = new TeamWLProfilesVO();
		WLProfilesPO po = tds.wl_Profile(teamID, season);
		
		if(po==null){
			vo.chart = new JLabel();
			vo.good = new double[11];
			vo.average = new double[11];
			vo.poor = new double[11];
			return vo;
		}
		
		Charts chart = new Charts();
		double min1 = StatUtils.min(po.winp_good)-0.05;
		double min2 = StatUtils.min(po.winp_average)-0.05;
		double min3 = StatUtils.min(po.winp_poor)-0.05;
		double shrinkage = StatUtils.min(new double[]{min1,min2,min3,0.4});
		if(shrinkage<0)
			shrinkage = 0;
		
		vo.chart = chart.radarChart(new double[][]{po.winp_good,po.winp_average,po.winp_poor},shrinkage);
		vo.good = po.net_good;
		vo.average = po.net_average;
		vo.poor = po.net_poor;
		
		return vo;
	}

	
}

package businesslogic.teambl;

import blservice.teamblservice.TeamBLService;
import businesslogic.playerbl.PlayerBL;
import businesslogic.statsbl.Compare;
import data.teamdata.TeamData;
import dataservice.teamdataservice.TeamDataService;
import dataservice.teamdataservice.TeamData_stub;
import org.apache.commons.math3.stat.inference.TestUtils;
import po.playerpo.PlayerPO;
import po.teampo.TeamDataPO;
import po.teampo.TeamInfoPO;
import vo.HotZoneVO;
import vo.playervo.PlayerVO;
import vo.teamvo.HotTeamsVO;
import vo.teamvo.TeamVO;

import java.util.ArrayList;
import java.util.Collections;

public class TeamBL implements TeamBLService{
	TeamDataService tds = new TeamData();
	TeamDataService teamDataService = new TeamData();

	@Override
	public ArrayList<HotTeamsVO> hotTeams(int num, String sortBy, int season) {
//		teamDataService.findTeamDataList()
//		ArrayList<String> arrayList = new ArrayList<>();
//		arrayList.add(sortBy);
//		ArrayList<TeamDataPO> teamDataPOs = teamDataService.sortTeamList(arrayList,sortBy,season,true);
		return null;
	}

	@Override
	public TeamVO findTeamInfo(int teamId,int season) {
		TeamInfoPO teamInfoPO = teamDataService.findTeamInfo(teamId);
		if (teamInfoPO == null)
			teamInfoPO = new TeamInfoPO();
		TeamDataPO teamDataPO = teamDataService.findTeamData(teamId,season);
		if (teamDataPO == null)
			teamDataPO = new TeamDataPO();
		return change(teamDataPO,teamInfoPO);
	}

	@Override
	public TeamVO findTeamInfo(String teamName,int season) {
		TeamInfoPO teamInfoPO = teamDataService.findTeamInfo(teamName);
		if (teamInfoPO == null)
			teamInfoPO = new TeamInfoPO();
		TeamDataPO teamDataPO = teamDataService.findTeamData(teamName,season);
		if (teamDataPO == null)
			teamDataPO = new TeamDataPO();
		return change(teamDataPO,teamInfoPO);
	}

	@Override
	public ArrayList<TeamVO> findTeams(String msg) {
		ArrayList<TeamInfoPO> teamInfoPOs = teamDataService.findTeamInfoList(msg);
		ArrayList<TeamVO> teamVOs = new ArrayList<>();
		for (int i = 0; i < teamInfoPOs.size(); i++){
			teamVOs.add(change(null,teamInfoPOs.get(i)));
		}
		return teamVOs;
	}

	@Override
	public ArrayList<TeamVO> findTeamNormal(int season) {
		return findTeam(season);
	}

	@Override
	public ArrayList<TeamVO> findTeamNormalAvg(int season) {
		return findTeam(season);
	}

	@Override
	public ArrayList<TeamVO> findTeamBasic() {
		ArrayList<TeamInfoPO> teamInfoPOs = teamDataService.findTeamInfoList();
		ArrayList<TeamVO> teamVOs = new ArrayList<>();
		for (int i = 0; i < teamInfoPOs.size(); i++){
			teamVOs.add(change(null,teamInfoPOs.get(i)));
		}
		return teamVOs;
	}

	@Override
	public ArrayList<TeamVO> findTeamHigh(int season) {
		return findTeam(season);
	}

	private ArrayList<TeamVO> findTeam(int season){
		ArrayList<TeamDataPO> teamDataPOs = teamDataService.findTeamDataList(season);
		ArrayList<TeamInfoPO> teamInfoPOs = teamDataService.findTeamInfoList();
		return change(teamDataPOs,teamInfoPOs);
	}

	private ArrayList<TeamVO> change(ArrayList<TeamDataPO> teamDataPOs,ArrayList<TeamInfoPO> teamInfoPOs){
		ArrayList<TeamVO> teamVOs = new ArrayList<>();
		for(int i =0; i < teamDataPOs.size();i++){
			TeamDataPO teamDataPO = teamDataPOs.get(i);
			for(int j = 0 ;j < teamInfoPOs.size();j++){
				TeamInfoPO teamInfoPO = teamInfoPOs.get(j);
				if (teamInfoPO.id == teamDataPO.id){
					teamVOs.add(change(teamDataPO,teamInfoPO));
				}
			}
		}
		return teamVOs;
	}

	@Override
	public ArrayList<TeamVO> sortTeamNormal(int num, String sortBy,
			boolean desc, int season) {
		return sortTeam(num,sortBy,desc,season);
	}

	private ArrayList<TeamVO> sortTeam(int num, String sortBy, boolean desc, int season) {
		ArrayList<TeamDataPO> teamDataPOs = teamDataService.sortTeamList(changeSortBy(sortBy),desc,season);
		ArrayList<TeamInfoPO> teamInfoPOs = teamDataService.findTeamInfoList();
		return change(teamDataPOs,teamInfoPOs);
	}

	private TeamVO change(TeamDataPO teamDataPO,TeamInfoPO teamInfoPO){
		TeamVO teamVO = new TeamVO();
		if (teamDataPO == null){
			teamDataPO = new TeamDataPO();
		}
		if (teamInfoPO == null){
			teamInfoPO = new TeamInfoPO();
		}
		teamVO.id = teamDataPO.id;
		teamVO.photo = teamInfoPO.photo;
		teamVO.teamName = teamInfoPO.teamName;
		teamVO.abridge = teamInfoPO.abridge;
		teamVO.location = teamInfoPO.location;
		teamVO.league = teamInfoPO.league;
		teamVO.division = teamInfoPO.division;
		teamVO.homeCourt = teamInfoPO.homeCourt;
		teamVO.foundTime = teamInfoPO.foundTime;
		teamVO.assist = teamDataPO.assist;
		teamVO.blockShot = teamDataPO.BS;
		teamVO.defendRebound = teamDataPO.DRebound;
		teamVO.fault = teamDataPO.turnover;
		teamVO.foul = teamDataPO.foul;
		teamVO.numOfGame = teamDataPO.matchNum;
		teamVO.offendRebound = teamDataPO.ORebound;
		teamVO.penalty = teamDataPO.FTP;
		teamVO.point = teamDataPO.score;
		teamVO.rebound = teamDataPO.rebound;
		teamVO.shot = teamDataPO.FGP;
		teamVO.steal = teamDataPO.steal;
		teamVO.three = teamDataPO.TPSP;
		teamVO.avgAssist = teamDataPO.Avgassist;
		teamVO.avgBlockShot = teamDataPO.AvgBS;
		teamVO.avgDefendRebound = teamDataPO.AvgDRebound;
		teamVO.avgFault = teamDataPO.Avgturnover;
		teamVO.avgFoul = teamDataPO.Avgfoul;
		teamVO.avgOffendRebound = teamDataPO.AvgORebound;
		teamVO.avgPoint = teamDataPO.Avgscore;
		teamVO.avgRebound = teamDataPO.Avgrebound;
		teamVO.avgSteal = teamDataPO.Avgsteal;
		teamVO.assistEfficient = teamDataPO.assistP;
		teamVO.defendEfficient = teamDataPO.DRtg;
		teamVO.defendReboundEfficient = teamDataPO.DREBP;
		teamVO.offendEfficient = teamDataPO.ORtg;
		teamVO.offendReboundEfficient = teamDataPO.OREBP;
		teamVO.offendRound = teamDataPO.leg;
		teamVO.stealEfficient = teamDataPO.stealP;
		teamVO.winRate = teamDataPO.WinP;
		return teamVO;
	}

	private String changeSortBy(String sortBy) {
		switch (sortBy){
			case "id":
				return "id";
			case "teamName":
				return "teamName";
			case "abridge":
				return "abridge";
			case "assist":
				return "assist";
			case "blockShot":
				return "BS";
			case "defendRebound":
				return "DRebound";
			case "fault":
				return "turnover";
			case "foul":
				return "foul";
			case "numOfGame":
				return "matchNum";
			case "offendRebound":
				return "ORebound";
			case "penalty":
				return "FTP";
			case "point":
				return "score";
			case "rebound":
				return "rebound";
			case "shot":
				return "FGP";
			case "steal":
				return "steal";
			case "three":
				return "TPSP";
			case "avgAssist":
				return "Avgassist";
			case "avgBlockShot":
				return "AvgBS";
			case "avgDefendRebound":
				return "AvgDRebound";
			case "avgFault":
				return "Avgturnover";
			case "avgFoul":
				return "Avgfoul";
			case "avgOffendRebound":
				return "AvgORebound";
			case "avgPoint":
				return "Avgscore";
			case "avgRebound":
				return "Avgrebound";
			case "avgSteal":
				return "Avgsteal";
			case "assistEfficient":
				return "assistP";
			case "defendEfficient":
				return "DRtg";
			case "defendReboundEfficient":
				return "DREBP";
			case "offendEfficient":
				return "ORtg";
			case "offendReboundEfficient":
				return "OREBP";
			case "offendRound":
				return "leg";
			case "stealEfficient":
				return "stealP";
			case "winRate":
				return "WinP";
		}
		return "";
	}

	@Override
	public ArrayList<TeamVO> sortTeamNormalAvg(int num, String sortBy,
			boolean desc, int season) {
		return sortTeam(num,sortBy,desc,season);
	}

	@Override
	public ArrayList<TeamVO> sortTeamHigh(int num, String sortBy, boolean desc,
			int season) {
		return sortTeam(num,sortBy,desc,season);
	}

	@Override
	public ArrayList<PlayerVO> teamMemberList(int teamID,int season) {
		ArrayList<PlayerVO> arrayList = new PlayerBL().getTeamMemberList(teamID,season);
		return arrayList;
	}

	@Override
	public TeamVO avgLeague(ArrayList<String> attributes, char league,
			int season) {
		TeamDataPO po = teamDataService.getAvgLeague(league,season);
		return change(po,null);
	}

	@Override
	public ArrayList<HotZoneVO> getHotZone(int playerId, boolean isPerformance, boolean isPreSeason, int season) {
		int matchType = 2;
		if (isPreSeason){
			matchType = 4;
		}
		return teamDataService.getHotZone(playerId,isPerformance,matchType,season);
	}

	@Override
	public int[] teamVS(int team1id, int team2id, int season,
			ArrayList<String> attris) {
		if(attris.size()!=5)
			return null;
		int[] result = new int[10];
		Compare cmp = new Compare(0.2);
		for(int i=0;i<5;i++){
			double[] p1 = tds.specDataOfSeason(team1id, attris.get(i), season);
			double[] p2 = tds.specDataOfSeason(team2id, attris.get(i), season);
			System.out.print("p1[0] "+p1[0]+"p2[0] "+p2[0]);
			result[2*i] = cmp.compareE(p1, p2);
			result[2*i+1] = cmp.compareVar(p1, p2);
		}
		return result;
	}

	@Override
	public ArrayList<ArrayList<Integer>> memberRank(ArrayList<Integer> players,int season) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<double[]> playersData = new ArrayList<double[]>();
		ArrayList<Integer> ranks = new ArrayList<Integer>();
		ArrayList<Integer> playersRanks = new ArrayList<Integer>();
		
		Compare cmp = new Compare(0.05);
		for(int i=0;i<players.size();i++){
			double[] player = tds.net_everyMember(players.get(i),season);
			if(player!=null&&player.length>=2)
				playersData.add(player);
			else
				players.set(i, -1);
		}
		while(players.contains(-1)){
			players.remove(new Integer(-1));
		}
		if(playersData.size()<2||(!TestUtils.oneWayAnovaTest(playersData, 0.05)))
			return null;
		
		for(int i=0;i<playersData.size();i++){
			int playerrank=0;
			for(int j=0;j<playersData.size();j++){
				playerrank += cmp.compareE(playersData.get(j), playersData.get(i));
			}
			
			playersRanks.add(playerrank);
			if(!ranks.contains(playerrank))
				ranks.add(playerrank);
		}
		
		Collections.sort(ranks);
		for(int i=0;i<ranks.size();i++){
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for(int j = 0;j<playersRanks.size();j++){
				if(playersRanks.get(j) == ranks.get(i))
					temp.add(players.get(j));
			}
			result.add(temp);
		}
		
		return result;
	}

}

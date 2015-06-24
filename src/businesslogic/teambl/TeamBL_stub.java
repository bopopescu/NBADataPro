package businesslogic.teambl;

import java.util.ArrayList;

import javax.swing.JPanel;

import blservice.teamblservice.TeamBLService;
import vo.HotZoneVO;
import vo.playervo.PlayerVO;
import vo.teamvo.HotTeamsVO;
import vo.teamvo.TeamVO;
import vo.teamvo.Top5statsVO;

public class TeamBL_stub implements TeamBLService{

	@Override
	public ArrayList<HotTeamsVO> hotTeams(int num, String sortBy,int season) {
		ArrayList<HotTeamsVO>  result = new ArrayList<HotTeamsVO>();
		HotTeamsVO vo = new HotTeamsVO();
		vo.name = "Hawks";
		vo.league = 'E';
		vo.photo = "ATL";
		vo.location = "Atlanta";
		vo.value = 39;
		
		result.add(vo);
		result.add(vo);
		result.add(vo);
		result.add(vo);
		result.add(vo);
			
		return result;
	}

	public TeamVO findTeamInfo(int teamId) {
		TeamVO vo = new TeamVO();
		vo.teamName = "Hawks";
		vo.league = 'E';
		vo.photo = "ATL";
		vo.location = "Atlanta";
		return vo;
	}

	@Override
	public TeamVO findTeamInfo(String teamName,int season) {
		TeamVO vo = new TeamVO();
		vo.teamName = "Hawks";
		vo.league = 'E';
		vo.photo = "ATL";
		vo.location = "Atlanta";
		vo.avgAssist = 4.2;
		vo.avgPoint = 12.3;
		return vo;
	}

	@Override
	public ArrayList<TeamVO> findTeams(String msg) {
		ArrayList<TeamVO>  result = new ArrayList<TeamVO>();
		TeamVO vo = new TeamVO();
		vo.teamName = "Hawks";
		vo.location = "Atlanta";
		vo.photo = "ATL";
		for(int i = 0;i < 10;i++){
			result.add(vo);
		}
		return result;
	}

	@Override
	public ArrayList<TeamVO> findTeamNormal(int season) {
		ArrayList<TeamVO> vol = new ArrayList<TeamVO>();
		TeamVO vo = new TeamVO();
		vo.photo = "ATL";
		vo.location = "***";
		vo.teamName = "***";
		vol.add(vo);
		return vol;
	}

	@Override
	public ArrayList<TeamVO> findTeamNormalAvg(int season) {
		ArrayList<TeamVO> vol = new ArrayList<TeamVO>();
		TeamVO vo = new TeamVO();
		vo.photo = "ATL";
		vo.location = "***";
		vo.teamName = "***";
		vol.add(vo);
		return vol;
	}

	@Override
	public ArrayList<TeamVO> findTeamBasic() {
		ArrayList<TeamVO> vol = new ArrayList<TeamVO>();
		TeamVO vo = new TeamVO();
		vo.photo = "ATL";
		vo.location = "***";
		vo.teamName = "***";
		vol.add(vo);
		return vol;
	}

	@Override
	public ArrayList<TeamVO> findTeamHigh(int season) {
		ArrayList<TeamVO> vol = new ArrayList<TeamVO>();
		TeamVO vo = new TeamVO();
		vo.photo = "ATL";
		vo.location = "***";
		vo.teamName = "***";
		vol.add(vo);
		return vol;
	}

	@Override
	public ArrayList<TeamVO> sortTeamNormal(int num, String sortBy, boolean desc,int season) {
		ArrayList<TeamVO> vol = new ArrayList<TeamVO>();
		TeamVO vo = new TeamVO();
		vo.photo = "BKN";
		vo.location = "***";
		vo.teamName = "***";
		vol.add(vo);
		return vol;
	}

	@Override
	public ArrayList<TeamVO> sortTeamNormalAvg(int num, String sortBy,
			boolean desc,int season) {
		ArrayList<TeamVO> vol = new ArrayList<TeamVO>();
		TeamVO vo = new TeamVO();
		vo.photo = "BKN";
		vo.location = "***";
		vo.teamName = "***";
		vol.add(vo);
		return vol;
	}

	@Override
	public ArrayList<TeamVO> sortTeamHigh(int num, String sortBy, boolean desc,int season) {
		ArrayList<TeamVO> vol = new ArrayList<TeamVO>();
		TeamVO vo = new TeamVO();
		vo.photo = "BKN";
		vo.location = "***";
		vo.teamName = "***";
		vol.add(vo);
		return vol;
	}

	@Override
	public ArrayList<PlayerVO> teamMemberList(int teamID,int season) {
		ArrayList<PlayerVO> vol = new ArrayList<PlayerVO>();
		PlayerVO vo = new PlayerVO();
		vo.id = 1;
		vo.photo = "Aaron Brooks";
		vo.name = "Aaron Brooks";
		vo.teamName = "***";
		PlayerVO vo2 = new PlayerVO();
		vo2.id = 2;
		vo2.photo = "Aaron Brooks";
		vo2.name = "Aaron Brooks";
		vo2.teamName = "***";
		PlayerVO vo3 = new PlayerVO();
		vo3.id = 3;
		vo3.photo = "Aaron Brooks";
		vo3.name = "Aaron Brooks";
		vo3.teamName = "***";
		vol.add(vo);
		vol.add(vo2);
		vol.add(vo3);
		return vol;
	}

	@Override
	public TeamVO avgLeague(ArrayList<String> attributes, char league,int season) {
		TeamVO vo = new TeamVO();
		vo.avgPoint = 55.5;
		vo.avgRebound = 39.2;
		vo.avgAssist = 15.7;
		vo.three = 0.25;
		vo.penalty = 0.66;
		return vo;
	}

	public HotZoneVO getHotZone(int playerId, boolean isPerformance,
			boolean isPreSeason) {
		// TODO Auto-generated method stub
		return null;
	}

	public HotZoneVO getHotZone(int teamId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] teamVS(int team1id, int team2id, int season,
			ArrayList<String> attris) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ArrayList<Integer>> memberRank(ArrayList<Integer> players,
			int season) {
		ArrayList<ArrayList<Integer>> ranks = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> rank1 = new ArrayList<Integer>(); rank1.add(1); ranks.add(rank1);
		ArrayList<Integer> rank2 = new ArrayList<Integer>(); rank2.add(2); ranks.add(rank2);
		ArrayList<Integer> rank3 = new ArrayList<Integer>(); rank3.add(3); ranks.add(rank3);

		return ranks;
	}

	@Override
	public TeamVO findTeamInfo(int teamId, int season) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<HotZoneVO> getHotZone(int teamId, boolean isPerformance,
			boolean isPreSeason, int season) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

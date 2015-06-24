package businesslogic.matchbl;

import java.util.ArrayList;

import blservice.matchblservice.MatchBLService;
import vo.matchvo.MatchContentPlayerVO;
import vo.matchvo.MatchVO;
import vo.teamvo.HotTeamsVO;

public class MatchBL_stub implements MatchBLService{

	@Override
	public ArrayList<MatchContentPlayerVO> FindRecentMatches_p(
			String playerName) {
		ArrayList<MatchContentPlayerVO> result = new ArrayList<MatchContentPlayerVO>();
		MatchContentPlayerVO vo1 = new MatchContentPlayerVO();
		vo1.date = "2014-1-1";
		result.add(vo1);
		MatchContentPlayerVO vo2 = new MatchContentPlayerVO();
		vo2.date = "2015-1-1";
		result.add(vo2);
		
		return result;
	}

	@Override
	public ArrayList<MatchVO> FindRecentMatches_t(String teamName) {
		ArrayList<MatchVO> vol = new ArrayList<MatchVO>();
		MatchVO vo = new MatchVO();
		HotTeamsVO ht = new HotTeamsVO();
		HotTeamsVO gt = new HotTeamsVO();
		ht.name = "ATL";
		gt.name = "BKN";
		vo.date = "2015-5-5";
		vo.homeTeam = ht;
		vo.guestTeam = gt;
		
		
		ArrayList<MatchContentPlayerVO> hop = new ArrayList<MatchContentPlayerVO>();
		MatchContentPlayerVO vo1 = new MatchContentPlayerVO();
		vo1.date = "2014-1-1";
		hop.add(vo1);
		ArrayList<MatchContentPlayerVO> hot = new ArrayList<MatchContentPlayerVO>();
		MatchContentPlayerVO vo2 = new MatchContentPlayerVO();
		vo2.date = "2014-1-1";
		hot.add(vo2);
		vo.homeTeamPlayer = hop;
		vo.guestTeamPlayer = hot;
		vol.add(vo);
		
		return vol;
	}

	@Override
	public MatchVO findMatch(int matchID) {
		MatchVO vo = new MatchVO();
		HotTeamsVO ht = new HotTeamsVO();
		HotTeamsVO gt = new HotTeamsVO();
		ht.name = "ATL";
		gt.name = "BKN";
		vo.date = "2015-5-5";
		vo.homeTeam = ht;
		vo.guestTeam = gt;
		vo.date = "2015-5-5";		
		ArrayList<MatchContentPlayerVO> hop = new ArrayList<MatchContentPlayerVO>();
		MatchContentPlayerVO vo1 = new MatchContentPlayerVO();
		vo1.date = "2014-1-1";
		hop.add(vo1);
		ArrayList<MatchContentPlayerVO> hot = new ArrayList<MatchContentPlayerVO>();
		MatchContentPlayerVO vo2 = new MatchContentPlayerVO();
		vo2.date = "2014-1-1";
		hot.add(vo2);
		vo.homeTeamPlayer = hop;
		vo.guestTeamPlayer = hot;
		return vo;
	}

	@Override
	public ArrayList<MatchVO> findByDate(String start, String end) {
		ArrayList<MatchVO> vol = new ArrayList<MatchVO>();
		MatchVO vo = new MatchVO();
		HotTeamsVO ht = new HotTeamsVO();
		HotTeamsVO gt = new HotTeamsVO();
		ht.name = "ATL";
		gt.name = "BKN";
		vo.date = "2015-5-5";
		vo.homeTeam = ht;
		vo.guestTeam = gt;
		ArrayList<MatchContentPlayerVO> hop = new ArrayList<MatchContentPlayerVO>();
		MatchContentPlayerVO vo1 = new MatchContentPlayerVO();
		vo1.date = "2014-1-1";
		hop.add(vo1);
		ArrayList<MatchContentPlayerVO> hot = new ArrayList<MatchContentPlayerVO>();
		MatchContentPlayerVO vo2 = new MatchContentPlayerVO();
		vo2.date = "2014-1-1";
		hot.add(vo2);
		vo.homeTeamPlayer = hop;
		vo.guestTeamPlayer = hot;
		vol.add(vo);
		
		
		return vol;
	}

	@Override
	public ArrayList<MatchContentPlayerVO> findByDP(String start, String end,
			String playerName) {
		ArrayList<MatchContentPlayerVO> result = new ArrayList<MatchContentPlayerVO>();
		MatchContentPlayerVO vo1 = new MatchContentPlayerVO();
		vo1.date = "2014-1-1";
		result.add(vo1);
		MatchContentPlayerVO vo2 = new MatchContentPlayerVO();
		vo2.date = "2015-1-1";
		result.add(vo2);
		
		return result;
	}

	@Override
	public ArrayList<MatchVO> findByDT(String start, String end, String teamName) {
		ArrayList<MatchVO> vol = new ArrayList<MatchVO>();
		MatchVO vo = new MatchVO();
		HotTeamsVO ht = new HotTeamsVO();
		HotTeamsVO gt = new HotTeamsVO();
		ht.name = "ATL";
		gt.name = "BKN";
		vo.date = "2015-5-5";
		vo.homeTeam = ht;
		vo.guestTeam = gt;
		ArrayList<MatchContentPlayerVO> hop = new ArrayList<MatchContentPlayerVO>();
		MatchContentPlayerVO vo1 = new MatchContentPlayerVO();
		vo1.date = "2014-1-1";
		hop.add(vo1);
		ArrayList<MatchContentPlayerVO> hot = new ArrayList<MatchContentPlayerVO>();
		MatchContentPlayerVO vo2 = new MatchContentPlayerVO();
		vo2.date = "2014-1-1";
		hot.add(vo2);
		vo.homeTeamPlayer = hop;
		vo.guestTeamPlayer = hot;
		vol.add(vo);
		
		
		return vol;
	}

	@Override
	public ArrayList<MatchVO> findRecent20() {
		ArrayList<MatchVO> vol = new ArrayList<MatchVO>();
		MatchVO vo = new MatchVO();
		HotTeamsVO ht = new HotTeamsVO();
		HotTeamsVO gt = new HotTeamsVO();
		ht.name = "ATL";
		gt.name = "BKN";
		vo.date = "2015-5-5";
		vo.homeTeam = ht;
		vo.guestTeam = gt;
		ArrayList<MatchContentPlayerVO> hop = new ArrayList<MatchContentPlayerVO>();
		MatchContentPlayerVO vo1 = new MatchContentPlayerVO();
		vo1.date = "2014-1-1";
		hop.add(vo1);
		ArrayList<MatchContentPlayerVO> hot = new ArrayList<MatchContentPlayerVO>();
		MatchContentPlayerVO vo2 = new MatchContentPlayerVO();
		vo2.date = "2014-1-1";
		hot.add(vo2);
		vo.homeTeamPlayer = hop;
		vo.guestTeamPlayer = hot;
		vol.add(vo);
		
		
		return vol;
	}

	@Override
	public ArrayList<MatchContentPlayerVO> findBySP(int season,
			String playerName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MatchVO> findBySeason(int season) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MatchVO> findByST(int season, String teamName) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

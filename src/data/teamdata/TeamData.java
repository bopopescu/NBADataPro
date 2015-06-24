package data.teamdata;

import data.database.JDBCUtils;
import dataservice.teamdataservice.TeamDataService;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import po.stats.*;
import po.teampo.TeamDataPO;
import po.teampo.TeamInfoPO;
import vo.AttriOption;
import vo.HotZoneVO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by chenghao on 15/6/14.
 */
public class TeamData implements TeamDataService {

    @Override
    public TeamInfoPO findTeamInfo(int TeamId) {
        String sql = "select * from team where tid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            Object[] objects = queryRunner.query(sql, new ArrayHandler(),
                    TeamId);
            TeamInfoPO infoPO = getTeamInfoPO(objects);
            return infoPO;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public TeamInfoPO findTeamInfo(String teamName) {
        String sql = "select * from team where team_name_en=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            Object[] objects = queryRunner.query(sql, new ArrayHandler(),
                    teamName.trim());
            TeamInfoPO infoPO = getTeamInfoPO(objects);
            return infoPO;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();

        list.add("matchNum");
        list.add("FG");
        list.add("FGA");
        list.add("FGP");
        list.add("TPS");
        list.add("TPSA");
        list.add("TPSP");
        list.add("FTM");
        list.add("FTA");
        list.add("FTP");
        list.add("BS");
        list.add("assist");
        list.add("assistP");
        list.add("rebound");
        list.add("OREBP");
        list.add("DREBP");
        list.add("ORebound");
        list.add("DRebound");
        list.add("steal");
        list.add("stealP");
        list.add("turnover");
        list.add("foul");
        list.add("score");
        list.add("WinP");
        list.add("leg");
        list.add("ORtg");
        list.add("DRtg");
        list.add("AvgFG");
        list.add("AvgFGA");
        list.add("AvgTPS");
        list.add("AvgTPSA");
        list.add("AvgFTM");
        list.add("AvgFTA");
        list.add("AvgBS");
        list.add("Avgassist");
        list.add("Avgrebound");
        list.add("AvgORebound");
        list.add("AvgDRebound");
        list.add("Avgsteal");
        list.add("Avgturnover");
        list.add("Avgfoul");
        list.add("Avgscore");
        list.add("Avgleg");
        new TeamData().findTeamDataList(2014);
    }

    @Override
    public TeamDataPO findTeamData(int TeamId, int season) {
        String sql = "select * from teamdatapo" + season + " where tid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            Object[] objects = queryRunner.query(sql, new ArrayHandler(),
                    TeamId);
            TeamDataPO teamDataPO = getTeamDataPO(objects);
            return teamDataPO;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private TeamDataPO getTeamDataPO(Object[] objects) {
        TeamDataPO po = new TeamDataPO();
        int i = 0;
        po.id = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        po.matchNum = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        po.FG = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        po.FGA = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        po.FGP = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.TPS = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        po.TPSA = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        po.TPSP = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.FTM = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        po.FTA = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        po.FTP = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.BS = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        po.assist = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        po.assistP = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.rebound = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        po.OREBP = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.DREBP = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.ORebound = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        po.DRebound = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        po.steal = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        po.stealP = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.turnover = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        po.foul = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        po.score = objects[i++] == null ? -1 : Integer.parseInt(String.valueOf(objects[i - 1]));
        po.WinP = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.leg = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.ORtg = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.DRtg = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.AvgFG = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.AvgFGA = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.AvgTPS = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.AvgTPSA = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.AvgFTM = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.AvgFTA = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.AvgBS = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.Avgassist = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.Avgrebound = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.AvgORebound = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.AvgDRebound = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.Avgsteal = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.Avgturnover = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.Avgfoul = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.Avgscore = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        po.Avgleg = objects[i++] == null ? -1 : Double.parseDouble(String.valueOf(objects[i - 1]));
        return po;
    }

    @Override
    public TeamDataPO findTeamData(String teamName, int season) {
        String sql = "select teamdatapo.* from teamdatapo" + season + " as teamdatapo,team where team.team_name_en=? and teamdatapo.tid=team.tid";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            Object[] objects = queryRunner.query(sql, new ArrayHandler(),
                    teamName);
            TeamDataPO teamDataPO = getTeamDataPO(objects);
            return teamDataPO;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<TeamInfoPO> findTeamInfoList() {
        String sql = "select * from team where tid<=30";
        ArrayList<TeamInfoPO> arrayList = new ArrayList<TeamInfoPO>();
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            List<Object[]> resultList = queryRunner.query(sql,
                    new ArrayListHandler());
            for (int i = 0; i < resultList.size(); i++) {
                Object[] objects = resultList.get(i);
                TeamInfoPO infoPO = getTeamInfoPO(objects);
                arrayList.add(infoPO);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return arrayList;
    }

    @Override
    public ArrayList<TeamInfoPO> findTeamInfoList(String msg) {
        String sql = "select * from team where tid<=30 and (abbr like ? or team_name_en like ? or team_name_ch like ?)";
        String condition = "%" + msg + "%";
        ArrayList<TeamInfoPO> arrayList = new ArrayList<TeamInfoPO>();
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            List<Object[]> resultList = queryRunner.query(sql,
                    new ArrayListHandler(), condition, condition, condition);
            for (int i = 0; i < resultList.size(); i++) {
                Object[] objects = resultList.get(i);
                TeamInfoPO infoPO = getTeamInfoPO(objects);
                arrayList.add(infoPO);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return arrayList;
    }

    @Override
    public ArrayList<TeamDataPO> findTeamDataList(int season) {
        String sql = "select * from teamdatapo" + season;
        ArrayList<TeamDataPO> arrayList = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            List<Object[]> resultList = queryRunner.query(sql,
                    new ArrayListHandler());
            for (int i = 0; i < resultList.size(); i++) {
                Object[] objects = resultList.get(i);
                TeamDataPO po = getTeamDataPO(objects);
                arrayList.add(po);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return arrayList;
    }

    @Override
    public ArrayList<TeamDataPO> sortTeamList(String sortBy, boolean order, int season) {
        String desc = "";
        if (order) {
            desc = " desc";
        }
        String sql = "select * from teamdatapo" + season + " order by " + sortBy + desc;
        ArrayList<TeamDataPO> arrayList = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            List<Object[]> resultList = queryRunner.query(sql,
                    new ArrayListHandler());
            for (int i = 0; i < resultList.size(); i++) {
                Object[] objects = resultList.get(i);
                TeamDataPO po = getTeamDataPO(objects);
                arrayList.add(po);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return arrayList;
    }

//    public ArrayList<TeamDataPO> sortTeamList(ArrayList<String> list,
//                                              String mainProperty,int season, boolean order) {
//        String sql = getSql(list);
//        sql += " group by a.tid order by " + mainProperty;
//        if (order == true) {
//            sql += " desc";
//        }
//        ArrayList<TeamDataPO> arrayList = new ArrayList<TeamDataPO>();
//        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
//        try {
//            List<Object[]> resultList = queryRunner.query(sql,
//                    new ArrayListHandler());
//            for (int i = 0; i < resultList.size(); i++) {
//                Object[] objects = resultList.get(i);
//                TeamDataPO dataPO = getTeamDataPO(list, objects);
//                arrayList.add(dataPO);
//            }
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return arrayList;
//    }

//    private String queryHandel(ArrayList<String> list,
//                               TeamMaterialSql materialSql) {
//        if (list.size() == 0 || list == null) {
//            return "*";
//        }
//        String query = "";
//        for (String string : list) {
//            query += getQuery(string, materialSql);
//        }
//        query = "a.tid," + query;
//        query = query.substring(0, query.length() - 1);
//        return query;
//    }
//
//    private static String[] querys = Query.teamQuerys;
//
//    private String getQuery(String string, TeamMaterialSql materialSql) {
//        switch (string) {
//            case "matchNum":
//                new GamesplayedHandel().addMaterial(materialSql);
//                return querys[0];
//            case "FG":
//                new ThrowinnumHandel().addMaterial(materialSql);
//                return querys[1];
//            case "FGA":
//                new ThrowallnumHandel().addMaterial(materialSql);
//                return querys[2];
//            case "FGP":
//                new ThrowinrateHandel().addMaterial(materialSql);
//                return querys[3];
//            case "TPS":
//                new Throw3innumHandel().addMaterial(materialSql);
//                return querys[4];
//            case "TPSA":
//                new Throw3allnumHandel().addMaterial(materialSql);
//                return querys[5];
//            case "TPSP":
//                new Throw3inrateHandel().addMaterial(materialSql);
//                return querys[6];
//            case "FTM":
//                new PenaltyinnumHandel().addMaterial(materialSql);
//                return querys[7];
//            case "FTA":
//                new PenaltyallnumHandel().addMaterial(materialSql);
//                return querys[8];
//            case "FTP":
//                new PenaltyinrateHandel().addMaterial(materialSql);
//                return querys[9];
//            case "BS":
//                new BlocknumHandel().addMaterial(materialSql);
//                return querys[10];
//            case "assist":
//                new HelpattnumHandel().addMaterial(materialSql);
//                return querys[11];
//            case "assistP":
//                new HelpeffiHandel().addMaterial(materialSql);
//                return querys[12];
//            case "rebound":
//                new AllbasnumHandel().addMaterial(materialSql);
//                return querys[13];
//            case "reboundP":
//                return querys[14];
//            case "ORebound":
//                new AttbaseffiHandel().addMaterial(materialSql);
//                return querys[15];
//            case "DRebound":
//                new DefbaseffiHandel().addMaterial(materialSql);
//                return querys[16];
//            case "steal":
//                new InterpnumHandel().addMaterial(materialSql);
//                return querys[17];
//            case "stealP":
//                new InterpeffiHandel().addMaterial(materialSql);
//                return querys[18];
//            case "turnover":
//                new MistakenumHandel().addMaterial(materialSql);
//                return querys[19];
//            case "foul":
//                new FoulnumHandel().addMaterial(materialSql);
//                return querys[20];
//            case "score":
//                new ScorenumHandel().addMaterial(materialSql);
//                return querys[21];
//            case "WinP":
//                new WinPHandel().addMaterial(materialSql);
//                return "t.WinP,";
//            case "leg":
//                new AttaroundHandel().addMaterial(materialSql);
//                return querys[23];
//            case "ORtg":
//                new AtteffiHandel().addMaterial(materialSql);
//                return querys[24];
//            case "DRtg":
//                new DefeffiHandel().addMaterial(materialSql);
//                return querys[25];
//            case "OREBP":
//                new AttbaseffiHandel().addMaterial(materialSql);
//                return querys[26];
//            case "DREBP":
//                new DefeffiHandel().addMaterial(materialSql);
//                return querys[27];
//            case "AvgFG":
//                new ThrowinnumHandel().addMaterial(materialSql);
//                return querys[28];
//            case "AvgFGA":
//                new ThrowallnumHandel().addMaterial(materialSql);
//                return querys[29];
//            case "AvgTPS":
//                new Throw3innumHandel().addMaterial(materialSql);
//                return querys[30];
//            case "AvgTPSA":
//                new Throw3allnumHandel().addMaterial(materialSql);
//                return querys[31];
//            case "AvgFTM":
//                new PenaltyinnumHandel().addMaterial(materialSql);
//                return querys[32];
//            case "AvgFTA":
//                new PenaltyallnumHandel().addMaterial(materialSql);
//                return querys[33];
//            case "AvgBS":
//                new BlocknumHandel().addMaterial(materialSql);
//                return querys[34];
//            case "Avgassist":
//                new HelpattnumHandel().addMaterial(materialSql);
//                return querys[35];
//            case "Avgrebound":
//                new AllbasnumHandel().addMaterial(materialSql);
//                return querys[36];
//            case "AvgORebound":
//                new AttbaseffiHandel().addMaterial(materialSql);
//                return querys[37];
//            case "AvgDRebound":
//                new DefbaseffiHandel().addMaterial(materialSql);
//                return querys[38];
//            case "Avgsteal":
//                new InterpnumHandel().addMaterial(materialSql);
//                return querys[39];
//            case "Avgturnover":
//                new MistakenumHandel().addMaterial(materialSql);
//                return querys[40];
//            case "Avgfoul":
//                new FoulnumHandel().addMaterial(materialSql);
//                return querys[41];
//            case "Avgscore":
//                new ScorenumHandel().addMaterial(materialSql);
//                return querys[42];
//            case "Avgleg":
//                new AttaroundHandel().addMaterial(materialSql);
//                return querys[43];
//            default:
//                break;
//        }
//        return "";
//    }
//
//    private String getSql(ArrayList<String> list) {
//        return getSql(list, null);
//    }
//
//    private String getSql(ArrayList<String> list, String wheres) {
//        TeamMaterialSql materialSql = new TeamMaterialSql();
//        String query = queryHandel(list, materialSql).trim();
//        ArrayList<String> arrayList = materialSql.getWheres();
//        if (wheres != null) {
//            arrayList.add(wheres);
//        }
//        String where = "";
//        if (arrayList.size() > 0) {
//            where = "where " + where;
//            where += arrayList.get(0);
//            for (int i = 1; i < arrayList.size(); i++) {
//                where += " and " + arrayList.get(i);
//            }
//        }
//        String from = materialSql.getMaterial().trim();
//
//        String sql = "select " + query + " from " + from + " " + where;
//        return sql;
//    }
//
//    private TeamDataPO getTeamDataPO(ArrayList<String> list, Object[] objects) {
//        TeamDataPO dataPO = new TeamDataPO();
//        dataPO.id = Integer.parseInt(String.valueOf(objects[0]));
//        for (int j = 0; j < list.size(); j++) {
//            setValue(dataPO, list.get(j), objects[j + 1]);
//        }
//        return dataPO;
//    }

//    private void setValue(TeamDataPO dataPO, String key, Object value) {
//        switch (key) {
//
//            case "matchNum":
//                dataPO.matchNum = value == null ? -11 : Integer.parseInt(String
//                        .valueOf(value));
//                return;
//            case "FG":
//                dataPO.FG = value == null ? -11 : Integer.parseInt(String
//                        .valueOf(value));
//                return;
//            case "FGA":
//                dataPO.FGA = value == null ? -11 : Integer.parseInt(String
//                        .valueOf(value));
//                return;
//            case "FGP":
//                dataPO.FGP = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            case "TPS":
//                dataPO.TPS = value == null ? -11 : Integer.parseInt(String
//                        .valueOf(value));
//                return;
//            case "TPSA":
//                dataPO.TPSA = value == null ? -11 : Integer.parseInt(String
//                        .valueOf(value));
//                return;
//            case "TPSP":
//                dataPO.TPSP = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            case "FTM":
//                dataPO.FTM = value == null ? -11 : Integer.parseInt(String
//                        .valueOf(value));
//                return;
//            case "FTA":
//                dataPO.FTA = value == null ? -11 : Integer.parseInt(String
//                        .valueOf(value));
//                return;
//            case "FTP":
//                dataPO.FTP = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            case "BS":
//                dataPO.BS = value == null ? -11 : Integer.parseInt(String
//                        .valueOf(value));
//                return;
//            case "assist":
//                dataPO.assist = value == null ? -11 : Integer.parseInt(String
//                        .valueOf(value));
//                return;
//            case "assistP":
//                dataPO.assistP = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            case "rebound":
//                dataPO.rebound = value == null ? -11 : Integer.parseInt(String
//                        .valueOf(value));
//                return;
//            case "ORebound":
//                dataPO.ORebound = value == null ? -11 : Integer.parseInt(String
//                        .valueOf(value));
//                return;
//            case "DRebound":
//                dataPO.DRebound = value == null ? -11 : Integer.parseInt(String
//                        .valueOf(value));
//                return;
//            case "steal":
//                dataPO.steal = value == null ? -11 : Integer.parseInt(String
//                        .valueOf(value));
//                return;
//            case "stealP":
//                dataPO.stealP = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            case "turnover":
//                dataPO.turnover = value == null ? -11 : Integer.parseInt(String
//                        .valueOf(value));
//                return;
//            case "foul":
//                dataPO.foul = value == null ? -11 : Integer.parseInt(String
//                        .valueOf(value));
//                return;
//            case "score":
//                dataPO.score = value == null ? -11 : Integer.parseInt(String
//                        .valueOf(value));
//                return;
//            case "leg":
//                dataPO.leg = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            case "ORtg":
//                dataPO.ORtg = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            case "DRtg":
//                dataPO.DRtg = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            case "WinP":
//                dataPO.WinP = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            case "OREBP":
//                dataPO.OREBP = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            case "DREBP":
//                dataPO.DREBP = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            case "AvgFG":
//                dataPO.AvgFG = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            case "AvgFGA":
//                dataPO.AvgFGA = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            case "AvgTPS":
//                dataPO.AvgTPS = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            case "AvgTPSA":
//                dataPO.AvgTPSA = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            case "AvgFTM":
//                dataPO.AvgFTM = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            case "AvgFTA":
//                dataPO.AvgFTA = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            case "AvgBS":
//                dataPO.AvgBS = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            case "Avgassist":
//                dataPO.Avgassist = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            case "Avgrebound":
//                dataPO.Avgrebound = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            case "AvgORebound":
//                dataPO.AvgORebound = value == null ? -11 : Double
//                        .parseDouble(String.valueOf(value));
//                return;
//            case "AvgDRebound":
//                dataPO.AvgDRebound = value == null ? -11 : Double
//                        .parseDouble(String.valueOf(value));
//                return;
//            case "Avgsteal":
//                dataPO.Avgsteal = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            case "Avgturnover":
//                dataPO.Avgturnover = value == null ? -11 : Double
//                        .parseDouble(String.valueOf(value));
//                return;
//            case "Avgfoul":
//                dataPO.Avgfoul = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            case "Avgscore":
//                dataPO.Avgscore = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            case "Avgleg":
//                dataPO.Avgleg = value == null ? -11 : Double.parseDouble(String
//                        .valueOf(value));
//                return;
//            default:
//                break;
//        }
//    }

    private TeamInfoPO getTeamInfoPO(Object[] objects) {
        TeamInfoPO po = new TeamInfoPO(Integer.parseInt(String
                .valueOf(objects[0])), String.valueOf(objects[2]),
                String.valueOf(objects[3]), String.valueOf(objects[2]),
                String.valueOf(objects[5]), String.valueOf(objects[7]).charAt(0),
                String.valueOf(objects[8]), String.valueOf(objects[9]),
                Integer.parseInt(String.valueOf(objects[10])));
        return po;
    }

    @Override
    public double[] net_everyMember(int playerID, int season) {
        String sql = "select plusminus from playerscore" + season + " where pid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            List<Object[]> lists =
                    queryRunner.query(sql, new ArrayListHandler(), playerID);
            double[] res = new double[lists.size()];
            for (int i = 0; i < lists.size(); i++) {
                res[i] = lists.get(i)[0] == null ? 0 : Double.parseDouble(String.valueOf(lists.get(i)[0]));
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new double[0];
    }

    @Override
    public double[] specDataOfSeason(int teamID, String attri, int season) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select sum(" + changeVOAttri(attri) + ") from playerscore" + season + " where tid=? group by mid";
        try {
            List<Object[]> lists =
                    queryRunner.query(sql, new ArrayListHandler(), teamID);
            double[] res = new double[lists.size()];
            for (int i = 0; i < lists.size(); i++) {
                res[i] = lists.get(i)[0] == null ? 0 : Double.parseDouble(String.valueOf(lists.get(i)[0]));
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String changeVOAttri(String attri) {

        return null;
    }

    @Override
    public FiveUnitPO top5manGroup(int teamID, int season, AttriOption attri) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String query = "";
        String table = "";
        switch (attri) {
            case fiveMan_close:
                query = "close";
                table = "team_top_five_man_floor_units_details";
                break;
            case fiveMan_dClose:
                query = "dclose";
                table = "team_top_five_man_floor_units_details";
                break;
            case fiveMan_def:
                query = "def";
                table = "team_top_five_man_floor_units";
                break;
            case fiveMan_efg:
                query = "efg";
                table = "team_top_five_man_floor_units_details";
                break;
            case fiveMan_efga:
                query = "efga";
                table = "team_top_five_man_floor_units_details";
                break;
            case fiveMan_fta:
                query = "fta";
                table = "team_top_five_man_floor_units_details";
                break;
            case fiveMan_lose:
                query = "l";
                table = "team_top_five_man_floor_units";
                break;
            case fiveMan_min:
                query = "min";
                table = "team_top_five_man_floor_units";
                break;
            case fiveMan_off:
                query = "off";
                table = "team_top_five_man_floor_units";
                break;
            case fiveMan_plusMinus:
                query = "plus_minus";
                table = "team_top_five_man_floor_units";
                break;
            case fiveMan_reb:
                query = "reb";
                table = "team_top_five_man_floor_units_details";
                break;
            case fiveMan_to:
                query = "t_o";
                table = "team_top_five_man_floor_units_details";
                break;
            case fiveMan_win:
                query = "w";
                table = "team_top_five_man_floor_units";
                break;
            case fiveMan_winPct:
                query = "win_per";
                table = "team_top_five_man_floor_units";
                break;
            default:
                return null;
        }
        String sql = "select year,tid,sharp,unit," + query + " from " + table + " where tid=? and year=? order by sharp ";
        FiveUnitPO fiveUnitPO = new FiveUnitPO();
        fiveUnitPO.unitsName = new String[10];
        fiveUnitPO.datas = new double[10];
        try {
            List<Object[]> lists = queryRunner.query(sql, new ArrayListHandler(), teamID, season);
            for (int i = 0; i < 10; i++) {
                Object[] objects = lists.get(i);
                fiveUnitPO.unitsName[i] = objects[3] == null ? null : String.valueOf(objects[3]);
                fiveUnitPO.datas[i] = objects[4] == null ? -1 : Double.parseDouble(String.valueOf(objects[4]));
            }
            return fiveUnitPO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public double[][] position(int teamID, int season, AttriOption attri) {
        String query = "";
        switch (attri) {
            case position_ast:
                query = "ast";
                break;
            case position_blk:
                query = "blk";
                break;
            case position_efg:
                query = "efg_per";
                break;
            case position_fga:
                query = "fga";
                break;
            case position_fta:
                query = "fta";
                break;
            case position_ifg:
                query = "ifg";
                break;
            case position_per:
                query = "per";
                break;
            case position_pf:
                query = "pf";
                break;
            case position_pts:
                query = "pts";
                break;
            case position_reb:
                query = "reb";
                break;
            case position_to:
                query = "t_o";
                break;
            default:
                return null;
        }
        String sql = "select team_production_by_position.year,team_production_by_position.tid,team_production_by_position.position " + ",team_production_by_position." + query + " ,opponent_production_by_position." + query + " ,net_production_by_position." + query + " from team_production_by_position,opponent_production_by_position,net_production_by_position where " +
                "team_production_by_position.tid=? and team_production_by_position.year=? and team_production_by_position.tid=opponent_production_by_position.tid and team_production_by_position.year=opponent_production_by_position.year " +
                "and team_production_by_position.position=opponent_production_by_position.position and team_production_by_position.tid=net_production_by_position.tid and team_production_by_position.year=net_production_by_position.year" +
                " and team_production_by_position.position=net_production_by_position.position";
        System.out.println(sql);
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        double[][] res = new double[3][5];
        try {
            List<Object[]> lists = queryRunner.query(sql, new ArrayListHandler(), teamID, season);
            for (int i = 0; i < 3; i++) {
                ;
                for (int j = 3; j < lists.size(); j++) {
                    Object[] objects = lists.get(i);
                    res[i][j-3] = objects[j] == null ? -1 : Double.parseDouble(String.valueOf(objects[j]));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < res.length; i++) {
            double[] ress = res[i];
            for (int j = 0; j < ress.length; j++) {
                System.out.print(ress[j] + " ");
            }
            System.out.println();
        }
        return res;
    }

    @Override
    public ShotAnlsPO[] shootAnalysis(int teamID, int season) {
        String sql = "select * from team_shooting_details where tid=? and year=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        ShotAnlsPO[] res = new ShotAnlsPO[2];
        res[0] = new ShotAnlsPO();
        res[1] = new ShotAnlsPO();
        res[0].Att = new double[4];
        res[0].eFG = new double[4];
        res[0].Ast = new double[4];
        res[0].Pts = new double[4];
        res[1].Att = new double[4];
        res[1].eFG = new double[4];
        res[1].Ast = new double[4];
        res[1].Pts = new double[4];
        try {
            List<Object[]> lists = queryRunner.query(sql, new ArrayListHandler(), teamID, season);
            for (int i = 0; i < 4; i++) {
                Object[] objects = lists.get(i);
                res[0].Att[i] = Double.parseDouble(String.valueOf(objects[3]));
                res[0].eFG[i] = Double.parseDouble(String.valueOf(objects[4]));
                res[0].Ast[i] = Double.parseDouble(String.valueOf(objects[5]));
                res[0].Pts[i] = Double.parseDouble(String.valueOf(objects[6]));
                res[1].Att[i] = Double.parseDouble(String.valueOf(objects[7]));
                res[1].eFG[i] = Double.parseDouble(String.valueOf(objects[8]));
                res[1].Ast[i] = Double.parseDouble(String.valueOf(objects[9]));
                res[1].Pts[i] = Double.parseDouble(String.valueOf(objects[10]));
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ShotAnlsPO[] shotClockAnls(int teamID, int season) {
        String sql = "select * from team_shot_clock_usage where tid=? and year=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        ShotAnlsPO[] res = new ShotAnlsPO[2];
        res[0] = new ShotAnlsPO();
        res[1] = new ShotAnlsPO();
        res[0].Att = new double[4];
        res[0].eFG = new double[4];
        res[0].Ast = new double[4];
        res[0].Pts = new double[4];
        res[1].Att = new double[4];
        res[1].eFG = new double[4];
        res[1].Ast = new double[4];
        res[1].Pts = new double[4];
        try {
            List<Object[]> lists = queryRunner.query(sql, new ArrayListHandler(), teamID, season);
            for (int i = 0; i < lists.size(); i++) {
                Object[] objects = lists.get(i);
                res[0].Att[i] = Double.parseDouble(String.valueOf(objects[3]));
                res[0].eFG[i] = Double.parseDouble(String.valueOf(objects[4]));
                res[0].Ast[i] = Double.parseDouble(String.valueOf(objects[5]));
                res[0].Pts[i] = Double.parseDouble(String.valueOf(objects[6]));
                res[1].Att[i] = Double.parseDouble(String.valueOf(objects[7]));
                res[1].eFG[i] = Double.parseDouble(String.valueOf(objects[8]));
                res[1].Ast[i] = Double.parseDouble(String.valueOf(objects[9]));
                res[1].Pts[i] = Double.parseDouble(String.valueOf(objects[10]));
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TurnoverAndFoulPO turnover_fouls(int teamID, int season) {
        String sql = "select * from turnovers_fouls where tid=? and year=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        TurnoverAndFoulPO res = new TurnoverAndFoulPO();
        res.f_opp = new double[4];
        res.f_own = new double[4];
        res.t_opp = new double[4];
        res.t_own = new double[4];
        try {
            List<Object[]> lists = queryRunner.query(sql, new ArrayListHandler(), teamID, season);
            Object[] objects = lists.get(0);
            res.t_own[0] = String.valueOf(objects[3]) == null ? 0 : Double.parseDouble(String.valueOf(objects[3]));
            res.t_own[1] = String.valueOf(objects[4]) == null ? 0 : Double.parseDouble(String.valueOf(objects[4]));
            res.t_own[2] = String.valueOf(objects[5]) == null ? 0 : Double.parseDouble(String.valueOf(objects[5]));
            res.t_own[3] = String.valueOf(objects[6]) == null ? 0 : Double.parseDouble(String.valueOf(objects[6]));
            res.t_opp[0] = String.valueOf(objects[7]) == null ? 0 : Double.parseDouble(String.valueOf(objects[7]));
            res.t_opp[1] = String.valueOf(objects[8]) == null ? 0 : Double.parseDouble(String.valueOf(objects[8]));
            res.t_opp[2] = String.valueOf(objects[9]) == null ? 0 : Double.parseDouble(String.valueOf(objects[9]));
            res.t_opp[3] = String.valueOf(objects[10]) == null ? 0 : Double.parseDouble(String.valueOf(objects[10]));
            objects = lists.get(1);
            res.f_own[0] = String.valueOf(objects[3]) == null ? 0 : Double.parseDouble(String.valueOf(objects[3]));
            res.f_own[1] = String.valueOf(objects[4]) == null ? 0 : Double.parseDouble(String.valueOf(objects[4]));
            res.f_own[2] = String.valueOf(objects[5]) == null ? 0 : Double.parseDouble(String.valueOf(objects[5]));
            res.f_own[3] = String.valueOf(objects[6]) == null ? 0 : Double.parseDouble(String.valueOf(objects[6]));
            res.f_opp[0] = String.valueOf(objects[7]) == null ? 0 : Double.parseDouble(String.valueOf(objects[7]));
            res.f_opp[1] = String.valueOf(objects[8]) == null ? 0 : Double.parseDouble(String.valueOf(objects[8]));
            res.f_opp[2] = String.valueOf(objects[9]) == null ? 0 : Double.parseDouble(String.valueOf(objects[9]));
            res.f_opp[3] = String.valueOf(objects[10]) == null ? 0 : Double.parseDouble(String.valueOf(objects[10]));
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OffDefPO off_defStats(int teamID, int season) {
        String sql = "select * from team_statistics where tid=? and year=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        OffDefPO res = new OffDefPO();
        res.off_pct = new double[8];
        res.def_pct = new double[8];
        res.off_notPct = new double[3];
        res.def_notPct = new double[3];
        try {
            List<Object[]> lists = queryRunner.query(sql, new ArrayListHandler(), teamID, season);
            Object[] objects = lists.get(0);
            res.off_pct[0] = String.valueOf(objects[6]) == null ? 0 : Double.parseDouble(String.valueOf(objects[6]));
            res.off_pct[1] = String.valueOf(objects[7]) == null ? 0 : Double.parseDouble(String.valueOf(objects[7]));
            res.off_pct[2] = String.valueOf(objects[9]) == null ? 0 : Double.parseDouble(String.valueOf(objects[9]));
            res.off_pct[3] = String.valueOf(objects[10]) == null ? 0 : Double.parseDouble(String.valueOf(objects[10]));
            res.off_pct[4] = String.valueOf(objects[11]) == null ? 0 : Double.parseDouble(String.valueOf(objects[11]));
            res.off_pct[5] = String.valueOf(objects[12]) == null ? 0 : Double.parseDouble(String.valueOf(objects[12]));
            res.off_notPct[0] = String.valueOf(objects[3]) == null ? 0 : Double.parseDouble(String.valueOf(objects[3]));
            res.off_notPct[1] = String.valueOf(objects[4]) == null ? 0 : Double.parseDouble(String.valueOf(objects[4]));
            res.off_notPct[2] = String.valueOf(objects[5]) == null ? 0 : Double.parseDouble(String.valueOf(objects[5]));
            objects = lists.get(1);
            res.def_pct[0] = String.valueOf(objects[6]) == null ? 0 : Double.parseDouble(String.valueOf(objects[6]));
            res.def_pct[1] = String.valueOf(objects[7]) == null ? 0 : Double.parseDouble(String.valueOf(objects[7]));
            res.def_pct[2] = String.valueOf(objects[9]) == null ? 0 : Double.parseDouble(String.valueOf(objects[9]));
            res.def_pct[3] = String.valueOf(objects[10]) == null ? 0 : Double.parseDouble(String.valueOf(objects[10]));
            res.def_pct[4] = String.valueOf(objects[11]) == null ? 0 : Double.parseDouble(String.valueOf(objects[11]));
            res.def_pct[5] = String.valueOf(objects[12]) == null ? 0 : Double.parseDouble(String.valueOf(objects[12]));
            res.def_notPct[0] = String.valueOf(objects[3]) == null ? 0 : Double.parseDouble(String.valueOf(objects[3]));
            res.def_notPct[1] = String.valueOf(objects[4]) == null ? 0 : Double.parseDouble(String.valueOf(objects[4]));
            res.def_notPct[2] = String.valueOf(objects[5]) == null ? 0 : Double.parseDouble(String.valueOf(objects[5]));
            sql = "select * from team_shot_blocking where tid=? and year=?";
            lists = queryRunner.query(sql, new ArrayListHandler(), teamID, season);
            objects = lists.get(0);
            res.def_pct[6] = String.valueOf(objects[5]) == null ? 0 : Double.parseDouble(String.valueOf(objects[5]));
            res.def_pct[7] = String.valueOf(objects[7]) == null ? 0 : Double.parseDouble(String.valueOf(objects[7]));
            objects = lists.get(1);
            res.off_pct[6] = String.valueOf(objects[5]) == null ? 0 : Double.parseDouble(String.valueOf(objects[5]));
            res.off_pct[7] = String.valueOf(objects[7]) == null ? 0 : Double.parseDouble(String.valueOf(objects[7]));
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public EvolutionPO envolution(ArrayList<String> names, String attri) {
//        String sql = "select sum(playerscore2014."+attri+"), sum(playerscore2013."+attri+") ,sum(playerscore2012."+attri+") ,sum(playerscore2011."+attri+") ,sum(playerscore2010."+attri+") ,sum(playerscore2009."+attri+" from playerscore2014,playerscore2013,playerscore2012,playerscore2011,playerscore2010,playerscore2009 where playerscore2014.tid=? and "
        EvolutionPO evolutionPO = new EvolutionPO();
        evolutionPO.datas = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            for (int n = 0; n < names.size(); n++) {
                String sql_1 = "select tid from team where team_name_en = ?";
                Object object = queryRunner.query(sql_1, new ScalarHandler("tid"), names.get(n));
                if (object == null){
                    continue;
                }
                double[][] data = new double[6][];
                int tid = Integer.parseInt(String.valueOf(object));
                String sql = "select matchinfo.seasonf,count(a.mid) from (select tid,mid from playerscore group by tid,mid where tid=?) as a,matchinfo where a.mid=matchinfo.mid group by matchinfo.seasonf";
                List<Object[]> list = queryRunner.query(sql, new ArrayListHandler(), tid);
                for (int i = 0;i<list.size();i++){
                    Object[] objects = list.get(i);
                    int season = Integer.parseInt(String.valueOf(objects[0]));
                    int count = Integer.parseInt(String.valueOf(objects[1]));
                    data[season-2009] = new double[count];
                 }
                sql = "select matchinfo.seasonf,a.attr from (select sum("+getSort(attri)+") as attr,mid from playerscore group tid,mid where tid = ?) as a,matchinfo where a.mid=matchinfo.mid order by matchinfo.seasonf";
                List<Object[]> list1 = queryRunner.query(sql, new ArrayListHandler(), tid);
                int[] points = new int[6];
                for (int i = 0;i<list.size();i++){
                    Object[] objects = list.get(i);
                    int season = Integer.parseInt(String.valueOf(objects[0]));
                    int count = Integer.parseInt(String.valueOf(objects[1]));
                    data[season-2009][points[season-2009]++] = count;
                }
                evolutionPO.datas.add(data);
            }
            return evolutionPO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    private String getSort(String attri) {

        switch (attri){
            case "id":
                return "id";
            case "teamName":
                return "";
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
        return null;
    }

    @Override
    public WLProfilesPO wl_Profile(int teamID, int season) {
        String sql = "select * from team_won_lost_profiles from where year=? and tid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            List<Object[]> list =
                    queryRunner.query(sql, new ArrayListHandler(), season, teamID);
            WLProfilesPO wlProfilesPO = new WLProfilesPO();
            wlProfilesPO.winp_average = new double[11];
            wlProfilesPO.winp_good = new double[11];
            wlProfilesPO.winp_poor = new double[11];
            wlProfilesPO.net_average = new double[11];
            wlProfilesPO.net_good = new double[11];
            wlProfilesPO.net_poor = new double[11];
            for (int i = 0; i < list.size(); i++) {
                wlProfilesPO.winp_average[i] = list.get(i)[9] == null ? 0 : Double.parseDouble(String.valueOf(list.get(i)[9]));
                wlProfilesPO.winp_good[i] = list.get(i)[5] == null ? 0 : Double.parseDouble(String.valueOf(list.get(i)[5]));
                wlProfilesPO.winp_poor[i] = list.get(i)[13] == null ? 0 : Double.parseDouble(String.valueOf(list.get(i)[13]));
                wlProfilesPO.net_average[i] = list.get(i)[10] == null ? 0 : Double.parseDouble(String.valueOf(list.get(i)[10]));
                wlProfilesPO.net_good[i] = list.get(i)[6] == null ? 0 : Double.parseDouble(String.valueOf(list.get(i)[6]));
                wlProfilesPO.net_poor[i] = list.get(i)[14] == null ? 0 : Double.parseDouble(String.valueOf(list.get(i)[14]));
            }
            return wlProfilesPO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TeamDataPO getAvgLeague(char league, int season) {
        String sql = "select sum(matchNum),sum(FG),sum(FGA),(1.0*sum(FG))/sum(FGA),sum(TPS),sum(TPSA),(1.0*sum(TPS))/sum(TPSA)),sum(),sum(),sum(),sum(),sum(),sum(),sum(),sum(),sum(),sum(),sum(),sum(),sum(),sum(),sum(), from teamdatapo" + season + " as teamdatapo,team where teamdatapo.tid=team.tid group by team.league";
        return null;
    }

    @Override
    public ArrayList<HotZoneVO> getHotZone(int teamId, boolean isPerformance, int matchType, int season) {
        if (isPerformance) {
            String sql = "select * from team_hot_zone where seasonf=? and match_type=? and tid=?";
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            ArrayList<HotZoneVO> arrayList = new ArrayList<>();
            try {
                List<Object[]> list =
                        queryRunner.query(sql, new ArrayListHandler(), season, matchType, teamId);
                for (int i = 0; i < list.size(); i++) {
                    Object[] objects = list.get(i);
                    HotZoneVO vo = new HotZoneVO();
                    int j = 4;
                    vo.zone4N = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone4D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone10N = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone10D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone13N = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone13D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone8N = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone8D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone2N = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone2D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone1N = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone1D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone3N = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone3D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone9N = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone9D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone12N = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone12D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone6N = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone6D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone7N = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone7D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone5N = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone5D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone11N = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone11D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone14N = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone14D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    arrayList.add(vo);
                }
                return arrayList;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String sql = "select c08Made , c1624Made , c24PlusMade , c816Made , l1624Made , l24PlusMade , l816Made , lc1624Made  , lc24PlusMade  , r1624Made  , r24PlusMade  , r816Made  , rc1624Made  , rc24PlusMade ,c08Made + c1624Made + c24PlusMade + c816Made + l1624Made + l24PlusMade + l816Made + lc1624Made  + lc24PlusMade  + r1624Made  + r24PlusMade  + r816Made  + rc1624Made  + rc24PlusMade from team_hot_zone where seasonf=? and match_type=? and tid=?";
            QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
            ArrayList<HotZoneVO> arrayList = new ArrayList<>();
            try {
                List<Object[]> list =
                        queryRunner.query(sql, new ArrayListHandler(), season, matchType, teamId);
                for (int i = 0; i < list.size(); i++) {
                    Object[] objects = list.get(i);
                    HotZoneVO vo = new HotZoneVO();
                    int j = 0;
                    vo.zone4N = objects[14] == null ? 0 : Integer.parseInt(String.valueOf(objects[14]));
                    vo.zone4D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone10N = vo.zone4N;
                    vo.zone10D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone13N = vo.zone4N;
                    vo.zone13D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone8N = vo.zone4N;
                    vo.zone8D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone2N = vo.zone4N;
                    vo.zone2D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone1N = vo.zone4N;
                    vo.zone1D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone3N = vo.zone4N;
                    vo.zone3D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone9N = vo.zone4N;
                    vo.zone9D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone12N = vo.zone4N;
                    vo.zone12D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone6N = vo.zone4N;
                    vo.zone6D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone7N = vo.zone4N;
                    vo.zone7D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone5N = vo.zone4N;
                    vo.zone5D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone11N = vo.zone4N;
                    vo.zone11D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    vo.zone14N = vo.zone4N;
                    vo.zone14D = objects[j++] == null ? 0 : Integer.parseInt(String.valueOf(objects[j - 1]));
                    arrayList.add(vo);
                }
                return arrayList;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}

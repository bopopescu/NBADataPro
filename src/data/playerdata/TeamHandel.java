package data.playerdata;

import po.Material;
import data.PlayerMaterialHandel;
import data.PlayerMaterialSql;

public class TeamHandel implements PlayerMaterialHandel {

	public void addMaterial(PlayerMaterialSql materialSql) {
		// TODO Auto-generated method stub
		materialSql.addMaterialPI(Material.Team);
	}

}

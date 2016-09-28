package dao;

import java.util.HashMap;
import java.util.List;

public interface IMenuListDao {
	public int insertMenuList(HashMap<String, Object> params);
	public int updateMenuList(HashMap<String, Object> params);
	public int deleteMenuList(String id);
	public HashMap<String, Object> selectOne(String id);
	public HashMap<String,Object> selectOnebyKeyNDate(HashMap<String, Object> params);
	public List<HashMap<String, Object>> selectAll();
	public HashMap<String, Object> selectOneToday(HashMap<String, Object> params);
	
}

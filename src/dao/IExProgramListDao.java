package dao;

import java.util.HashMap;
import java.util.List;

public interface IExProgramListDao {
	public int insertExProgramList(HashMap<String, Object> params);
	public int updateExProgramList(HashMap<String, Object> params);
	public int deleteExProgramList(String id);
	public HashMap<String, Object> selectOne(int exprogramlist_key);
	public HashMap<String, Object> selectOnebyKeyNDate(HashMap<String, Object> params);
	public List<HashMap<String, Object>> selectAll();
}

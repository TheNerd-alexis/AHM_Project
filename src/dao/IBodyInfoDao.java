package dao;

import java.util.HashMap;
import java.util.List;

public interface IBodyInfoDao {
	public int insertBodyInfo(HashMap<String, Object> params);
	public int updateBodyInfo(HashMap<String, Object> params);
	public int deleteBodyInfo(String id);
	public HashMap<String, Object> selectOne(String id);
	public HashMap<String, Object> selectOnebyKeyNDate(HashMap<String, Object> params);
	public HashMap<String, Object> selectWeightOne(HashMap<String, Object> params);
	public HashMap<String, Object> selectWeightTwo(HashMap<String, Object> params);
	public HashMap<String, Object> selectWeightThree(HashMap<String, Object> params);
	public HashMap<String, Object> selectWeightFour(HashMap<String, Object> params);
	public List<HashMap<String, Object>> selectAll();
}

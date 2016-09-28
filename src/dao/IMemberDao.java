package dao;

import java.util.HashMap;
import java.util.List;

public interface IMemberDao {
	public int insertMember(HashMap<String, Object> params);
	public int updateMember(HashMap<String, Object> params);
	public int deleteMember(String id);
	public HashMap<String, Object> selectOnebyId(String id);
	public HashMap<String, Object> selectOnebyKey(int member_key);
	public List<HashMap<String, Object>> selectAll();
}

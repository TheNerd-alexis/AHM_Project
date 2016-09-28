package dao;

import java.util.HashMap;
import java.util.List;

public interface IMessageDao {
	public void insertMessage(HashMap<String, Object> params);
	public int updateMessage(HashMap<String, Object> params);
	public int deleteMessage(String id);
	public HashMap<String, Object> selectOne(String id);
	//psh
	public HashMap<String, Object> selectOnebyKeyNDate(HashMap<String, Object> params);
	public List<HashMap<String, Object>> selectAll();
}

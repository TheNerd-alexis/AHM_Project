package dao;

import java.util.HashMap;
import java.util.List;

public interface ITrainerDao {
	public int insertTrainer(HashMap<String, Object> params);
	public int updateTrainer(HashMap<String, Object> params);
	public int deleteTrainer(String id);
	public HashMap<String, Object> selectOnebyId(String id);
	public HashMap<String, Object> selectOnebyKey(int trainer_key);
	public List<HashMap<String, Object>> selectAll();
}

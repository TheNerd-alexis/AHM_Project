package service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import commons.Constant;
import commons.Constant.Trainer;
import dao.IBodyInfoDao;
import dao.IExProgramListDao;
import dao.IMemberDao;
import dao.IMenuListDao;
import dao.IMessageDao;
import dao.ITrainerDao;

@Service
public class AhmService {

	@Autowired
	IMessageDao messageDao;
	@Autowired
	IMemberDao memberDao;
	@Autowired
	ITrainerDao trainerDao;
	@Autowired
	IMenuListDao menuListDao;
	@Autowired
	IExProgramListDao exProgramListDao;
	@Autowired
	IBodyInfoDao bodyInfoDao;

	public int login(HashMap<String, Object> params) {

		HashMap<String, Object> result;

		int i = Integer.parseInt((String) params.get("type"));

		switch (i) {

		case 1:
			result = memberDao.selectOnebyId((String) params.get(Constant.Member.MEMBER_ID));
			if (result != null) {
				if (((String) result.get(Constant.Member.MEMBER_PWD))
						.equals((String) params.get(Constant.Member.MEMBER_PWD)))
					// 로그인 성공
					return 1;
				else
					// 비밀번호 틀림
					return 3;
			} else
				// 아이디 없음
				return 0;

		case 2:
			result = trainerDao.selectOnebyId((String) params.get(Constant.Member.MEMBER_ID));
			if (result != null) {
				if (((String) result.get(Trainer.TRAINER_PWD)).equals((String) params.get(Constant.Member.MEMBER_PWD)))
					// 로그인 성공
					return 2;
				else
					// 비밀번호 틀림
					return 3;
			} else
				// 아이디 없음
				return 0;
		}
		// 기타 예외
		return -1;
	}

	public int join(HashMap<String, Object> params) {

		HashMap<String, Object> result;

		int i = Integer.parseInt((String) params.get("type"));

		switch (i) {

		case 1:

			result = memberDao.selectOnebyId((String) params.get("id"));
			if (result != null)
				return 2;
			else {
				if (params.get("pwd").equals(params.get("pwdCheck"))) {
					memberDao.insertMember(params);
					return 1;
				} else
					return 3;
			}

		case 2:

			result = trainerDao.selectOnebyId((String) params.get("id"));
			if (result != null)
				return 2;
			else {
				if (params.get("pwd").equals(params.get("pwdCheck"))) {
					trainerDao.insertTrainer(params);
					return 1;
				} else
					return 3;
			}
		}
		return -1;
	}

	public HashMap<String, Object> getMember(String member_id) {
		return memberDao.selectOnebyId(member_id);
	}

	public HashMap<String, Object> getTrainer(String trainer_id) {
		return trainerDao.selectOnebyId(trainer_id);
	}

	public HashMap<String, Object> getDayBodyInfo(HashMap<String, Object> params) {
		return bodyInfoDao.selectOnebyKeyNDate(params);
	}
	
	public HashMap<String, Object> getWeightOne(HashMap<String, Object> params) {
		return bodyInfoDao.selectWeightOne(params);
	}
	
	public HashMap<String, Object> getWeightTwo(HashMap<String, Object> params) {
		return bodyInfoDao.selectWeightTwo(params);
	}
	
	public HashMap<String, Object> getWeightThree(HashMap<String, Object> params) {
		return bodyInfoDao.selectWeightThree(params);
	}
	
	public HashMap<String, Object> getWeightFour(HashMap<String, Object> params) {
		return bodyInfoDao.selectWeightFour(params);
	}

	public HashMap<String, Object> getDayMenu(HashMap<String, Object> params) {
		return menuListDao.selectOnebyKeyNDate(params);
	}

	public HashMap<String, Object> getDayExercise(HashMap<String, Object> params) {
		return exProgramListDao.selectOnebyKeyNDate(params);
	}

	public HashMap<String, Object> getDayMessage(HashMap<String, Object> params) {
		return messageDao.selectOnebyKeyNDate(params);
	}

	public void writeMessage(HashMap<String, Object> params) {
		messageDao.insertMessage(params);
	}

	public void writeBodyInfo(HashMap<String, Object> params) {
		bodyInfoDao.insertBodyInfo(params);
	}

	public void writeDayMenu(HashMap<String, Object> params) {
		menuListDao.insertMenuList(params);
	}

	public void writeDayExercise(HashMap<String, Object> params) {
		exProgramListDao.insertExProgramList(params);
	}

	public void modifyBodyInfo(HashMap<String, Object> params) {
		bodyInfoDao.updateBodyInfo(params);
	}

	public void modifyDayExercise(HashMap<String, Object> params) {
		exProgramListDao.updateExProgramList(params);
	}

	public void modifyDayMenu(HashMap<String, Object> params) {
		menuListDao.updateMenuList(params);
	}

	public void modifyDayMessage(HashMap<String, Object> params) {
		messageDao.updateMessage(params);
	}

	public List<HashMap<String, Object>> getAllMember() {
		return memberDao.selectAll();
	}
	
}

package controller;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import commons.Constant.Member;
import commons.Constant.Trainer;
import service.AhmService;

@Controller
public class AhmController {
	@Autowired
	private AhmService ahmService;

	@RequestMapping("login.do")
	public String login(@RequestParam HashMap<String, Object> params, HttpSession session) {

		int result = ahmService.login(params);
		session.setAttribute("loginState", result);
		if (result == 1) {
			session.setAttribute(Member.MEMBER_ID, params.get(Member.MEMBER_ID));
			return "redirect:memberMain.do";
		} else if (result == 2) {
			session.setAttribute(Trainer.TRAINER_ID, params.get(Member.MEMBER_ID));
			return "redirect:trainerMainFirst.do";
		} else
			return "redirect:loginForm.do";
	}

	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:loginForm.do";
	}

	@RequestMapping("memberMain.do")
	public String memberMain(HttpSession session, Model model) {

		String member_id = (String) session.getAttribute(Member.MEMBER_ID);
		HashMap<String, Object> params = new HashMap<String, Object>();

		if (member_id != null) {

			HashMap<String, Object> result = ahmService.getMember(member_id);
			params.put(Member.MEMBER_KEY, result.get(Member.MEMBER_KEY));
			params.put("date", DateToString(new Date()));
			model.addAllAttributes(result);
			model.addAllAttributes(ahmService.getDayMenu(params));
			model.addAllAttributes(ahmService.getDayExercise(params));
			model.addAllAttributes(ahmService.getDayMessage(params));
			model.addAllAttributes(ahmService.getDayBodyInfo(params));
			model.addAllAttributes(ahmService.getWeightOne(params));
			model.addAllAttributes(ahmService.getWeightTwo(params));
			model.addAllAttributes(ahmService.getWeightThree(params));
			model.addAllAttributes(ahmService.getWeightFour(params));

			return "memberMain";
		} else {
			return "redirect:loginForm.do";
		}
	}

	@RequestMapping("trainerMainFirst.do")
	public String trainerMainFirst(HttpSession session, Model model) {

		String trianer_id = (String) session.getAttribute(Trainer.TRAINER_ID);
		if (trianer_id != null) {
			model.addAttribute("memberList", ahmService.getAllMember());
			return "trainerMainFirst";
		} else
			return "redirect:loginForm.do";
	}

	@RequestMapping("trainerMain.do")
	public String trainerMain(@RequestParam HashMap<String, Object> params, HttpSession session, Model model) {

		String member_id = (String) params.get(Member.MEMBER_ID);

		if (member_id != null) {
			session.setAttribute(Member.MEMBER_ID, member_id);
			HashMap<String, Object> result = ahmService.getMember(member_id);
			params.put(Member.MEMBER_KEY, result.get(Member.MEMBER_KEY));
			params.put("date", DateToString(new Date()));
			model.addAllAttributes(result);
			model.addAllAttributes(ahmService.getDayMenu(params));
			model.addAllAttributes(ahmService.getDayExercise(params));
			model.addAllAttributes(ahmService.getDayMessage(params));
			model.addAllAttributes(ahmService.getDayBodyInfo(params));
			model.addAllAttributes(ahmService.getWeightOne(params));
			model.addAllAttributes(ahmService.getWeightTwo(params));
			model.addAllAttributes(ahmService.getWeightThree(params));
			model.addAllAttributes(ahmService.getWeightFour(params));
			
		} else {
			member_id = (String) session.getAttribute(Member.MEMBER_ID);
			if (member_id != null) {
				HashMap<String, Object> result = ahmService.getMember(member_id);
				params.put(Member.MEMBER_KEY, result.get(Member.MEMBER_KEY));
				params.put("date", DateToString(new Date()));
				model.addAllAttributes(result);
				model.addAllAttributes(ahmService.getDayMenu(params));
				model.addAllAttributes(ahmService.getDayExercise(params));
				model.addAllAttributes(ahmService.getDayMessage(params));
				model.addAllAttributes(ahmService.getDayBodyInfo(params));
				model.addAllAttributes(ahmService.getWeightOne(params));
				model.addAllAttributes(ahmService.getWeightTwo(params));
				model.addAllAttributes(ahmService.getWeightThree(params));
				model.addAllAttributes(ahmService.getWeightFour(params));
			} else
				return "redirect:loginForm.do";
		}
		return "trainerMain";
	}

	@RequestMapping("loginForm.do")
	public String loginForm(HttpSession session) {
		if (session.getAttribute("loginState") != null) {
			if (session.getAttribute("loginState").toString().equals("1"))
				return "redirect:memberMain.do";
			else if (session.getAttribute("loginState").toString().equals("2"))
				return "redirect:trainerMainFirst.do";
		}
		return "loginForm";
	}

	@RequestMapping("joinForm.do")
	public String joinForm() {

		return "joinForm";
	}

	@RequestMapping("join.do")
	public String join(@RequestParam HashMap<String, Object> params) {

		if (ahmService.join(params) == 1) {
			System.out.println("회원가입성공");
			return "redirect:loginForm.do";
		} else if (ahmService.join(params) == 2) {
			System.out.println("회원가입실패 - 이미 사용하고 있는 아이디");
			return "redirect:joinForm.do";
		} else if (ahmService.join(params) == 3) {
			System.out.println("회원가입실패 - 비밀번호가 서로 같지 않음");
			return "redirect:joinForm.do";
		} else {
			System.out.println("이유몰라");
			return "redirect:joinForm.do";
		}
	}

	@RequestMapping("idCheck.do")
	public @ResponseBody Map<String, Object> idCheck(@RequestParam HashMap<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		if (ahmService.getMember(params.get("id").toString()) == null && ahmService.getTrainer(params.get("id").toString()) == null) {
			result.put("idCheckResult", false);
		} else {
			result.put("idCheckResult", true);
		}
		return result;
	}

	public String DateToString(Date date) {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
		String result = transFormat.format(date);
		return result;
	}

	@RequestMapping("dayBodyInfo.do")
	public @ResponseBody Map<String, Object> dayBodyInfo(@RequestParam HashMap<String, Object> params,
			HttpSession session) {

		HashMap<String, Object> result = new HashMap<>();
		String member_id = (String) session.getAttribute(Member.MEMBER_ID);
		result = ahmService.getMember(member_id);

		params.put(Member.MEMBER_KEY, result.get(Member.MEMBER_KEY));

		return Collections.singletonMap("dayBodyInfo", ahmService.getDayBodyInfo(params));
	}

	@RequestMapping("dayMenu.do")
	public @ResponseBody Map<String, Object> dayMenu(@RequestParam HashMap<String, Object> params,
			HttpSession session) {

		HashMap<String, Object> result = new HashMap<>();
		String member_id = (String) session.getAttribute(Member.MEMBER_ID);
		result = ahmService.getMember(member_id);

		params.put(Member.MEMBER_KEY, result.get(Member.MEMBER_KEY));

		return Collections.singletonMap("dayMenu", ahmService.getDayMenu(params));
	}

	@RequestMapping("dayExercise.do")
	public @ResponseBody Map<String, Object> dayExercise(@RequestParam HashMap<String, Object> params,
			HttpSession session) {

		HashMap<String, Object> result = new HashMap<>();
		String member_id = (String) session.getAttribute(Member.MEMBER_ID);
		result = ahmService.getMember(member_id);

		params.put(Member.MEMBER_KEY, result.get(Member.MEMBER_KEY));

		return Collections.singletonMap("dayExercise", ahmService.getDayExercise(params));
	}

	@RequestMapping("dayMessage.do")
	public @ResponseBody Map<String, Object> dayMessage(@RequestParam HashMap<String, Object> params,
			HttpSession session) {

		HashMap<String, Object> result = new HashMap<>();
		String member_id = (String) session.getAttribute(Member.MEMBER_ID);
		result = ahmService.getMember(member_id);

		params.put(Member.MEMBER_KEY, result.get(Member.MEMBER_KEY));

		return Collections.singletonMap("dayMessage", ahmService.getDayMessage(params));
	}

	@RequestMapping("writeMessage.do")
	public String writeMessage(@RequestParam HashMap<String, Object> params, HttpSession session) {

		String member_id = (String) session.getAttribute(Member.MEMBER_ID);
		HashMap<String, Object> memberResult = ahmService.getMember(member_id);
		params.put(Member.MEMBER_KEY, memberResult.get(Member.MEMBER_KEY));

		String trainer_id = (String) session.getAttribute(Trainer.TRAINER_ID);
		HashMap<String, Object> trainerResult = ahmService.getTrainer(trainer_id);
		params.put(Trainer.TRAINER_KEY, trainerResult.get(Trainer.TRAINER_KEY));

		ahmService.writeMessage(params);

		return "redirect:trainerMain.do";
	}

	@RequestMapping("writeBodyInfo.do")
	public String writeBodyinfo(@RequestParam HashMap<String, Object> params, HttpSession session) {

		String member_id = (String) session.getAttribute(Member.MEMBER_ID);
		HashMap<String, Object> memberResult = ahmService.getMember(member_id);
		params.put(Member.MEMBER_KEY, memberResult.get(Member.MEMBER_KEY));

		ahmService.writeBodyInfo(params);

		return "redirect:trainerMain.do";
	}

	@RequestMapping("writeDayMenu.do")
	public String writeDayMenu(@RequestParam HashMap<String, Object> params, HttpSession session) {

		String member_id = (String) session.getAttribute(Member.MEMBER_ID);
		HashMap<String, Object> memberResult = ahmService.getMember(member_id);
		params.put(Member.MEMBER_KEY, memberResult.get(Member.MEMBER_KEY));

		ahmService.writeDayMenu(params);

		return "redirect:trainerMain.do";
	}

	@RequestMapping("writeDayExercise.do")
	public String writeDayExercise(@RequestParam HashMap<String, Object> params, HttpSession session) {

		String member_id = (String) session.getAttribute(Member.MEMBER_ID);
		HashMap<String, Object> memberResult = ahmService.getMember(member_id);
		params.put(Member.MEMBER_KEY, memberResult.get(Member.MEMBER_KEY));

		ahmService.writeDayExercise(params);

		return "redirect:trainerMain.do";
	}

	@RequestMapping("modifyBodyInfo.do")
	public String modifyBodyInfo(@RequestParam HashMap<String, Object> params, HttpSession session) {
		ahmService.modifyBodyInfo(params);
		return "redirect:trainerMain.do";
	}

	@RequestMapping("modifyDayMenu.do")
	public String modifyDayMenu(@RequestParam HashMap<String, Object> params, HttpSession session) {
		ahmService.modifyDayMenu(params);
		return "redirect:trainerMain.do";
	}

	@RequestMapping("modifyDayMessage.do")
	public String modifyDayMessage(@RequestParam HashMap<String, Object> params, HttpSession session) {
		ahmService.modifyDayMessage(params);
		return "redirect:trainerMain.do";
	}

	@RequestMapping("modifyDayExercise.do")
	public String modifyDayExercise(@RequestParam HashMap<String, Object> params, HttpSession session) {
		ahmService.modifyDayExercise(params);
		return "redirect:trainerMain.do";
	}
	
}

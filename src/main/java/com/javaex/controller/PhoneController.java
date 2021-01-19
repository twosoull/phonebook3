package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value = "/phone")
public class PhoneController {
	// jsp/서블릿떄는 doGet()메소드 1개에 파라미터로 구분
	// 스프링은 메소드 단위 기능을 정의

	// 필드

	// 생성자

	// 메소드 G/S

	/** 메소드 일반 **/
	// 메소드 일반
	// 메소드 기능 1개씩 -->기능마다 url 부여

	// 등록폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("writeForm");

		return "/WEB-INF/views/writeForm.jsp";
	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("list");

		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personList = phoneDao.getPersonList();

		model.addAttribute("pList", personList);

		System.out.println(personList.toString());
		return "/WEB-INF/views/list.jsp";
	}

	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@RequestParam("name") String name, @RequestParam("hp") String hp,
			@RequestParam("company") String company) {
		System.out.println("write");
		System.out.println(name + "," + hp + ", " + company);

		PersonVo personVo = new PersonVo(name, hp, company);
		System.out.println(personVo);

		PhoneDao phoneDao = new PhoneDao();

		phoneDao.personInsert(personVo);
		return "redirect:/phone/list";
	}

	// 딜리트
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("id") int id) {
		System.out.println("delete");
		System.out.println(id);

		PhoneDao phoneDao = new PhoneDao();

		phoneDao.personDelete(id);

		return "redirect:/phone/list";
	}

	// 수정폼
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(@RequestParam("id") int id, Model model) {
		System.out.println("modifyForm");

		PhoneDao phoneDao = new PhoneDao();

		// dao를 통해서 데이터받기
		PersonVo personVo = phoneDao.getPerson(id);

		// 어트리뷰트
		model.addAttribute("personVo", personVo);

		return "/WEB-INF/views/modifyForm.jsp";
	}

	// 수정
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@RequestParam("name") String name, @RequestParam("hp") String hp,
			@RequestParam("company") String company, @RequestParam("personId") int id) {
		System.out.println("modify");

		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personUpdate(new PersonVo(id, name, hp, company));

		return "redirect:/phone/list";
	}
}

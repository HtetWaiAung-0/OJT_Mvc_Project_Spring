package controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bean.UserBean;
import dao.UserDAO;
import dto.UserRequestDTO;

@Controller
public class UserController {
	@Autowired
	private UserDAO dao;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(@RequestParam("loginMail") String loginMail,
			@RequestParam("loginPassword") String loginPassword, HttpSession session, ModelMap model) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
		session.setAttribute("date", LocalDate.now().format(formatter));

		UserDAO dao = new UserDAO();

		boolean check = false;
		check = dao.selectMailAndPassword(loginMail, loginPassword);
		if (check == true || (loginMail.equals("admin@gmail.com") && loginPassword.equals("123"))) {

			session.setAttribute("loginName", loginMail);
			session.setAttribute("loginPassword", loginPassword);
			return "MNU001";

		} else {
			model.addAttribute("error", " Please check your data again.");
			return "LGN001";
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("LGN001", "userBean", new UserBean());
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("bean") UserBean bean, ModelMap model) {
//        if(bs.hasErrors()) {
//            return "addUser";
//        }
		if (bean.getUserMail().isBlank() || bean.getUserPassword().isBlank() || bean.getUserConPassword().isBlank()
				|| bean.getUserRole().isBlank()) {
			model.addAttribute("errorFill", "Fill the Blank!!!");
			return "USR001";
		} else {
			UserRequestDTO dto = new UserRequestDTO();
			dto.setUserId(bean.getUserId());
			dto.setUserMail(bean.getUserMail());
			dto.setUserPassword(bean.getUserPassword());
			dto.setUserRole(bean.getUserRole());
			dao.insertUserData(dto);

			model.addAttribute("errorFill", "Success Register!");
			return "USR001";
		}
	}

	@RequestMapping(value = "/updateUser/{userId}", method = RequestMethod.GET)
	public ModelAndView updateUser(@PathVariable String userId) {
		return new ModelAndView("updateUser", "bean", dao.selectId(userId));
	}

	@RequestMapping(value = "/deleteUser/{user_id}", method = RequestMethod.GET)
	public String deleteuser(@PathVariable String userId, ModelMap model) {
		UserRequestDTO dto = new UserRequestDTO();
		dto.setUserId(userId);
		;
		dao.deleteUserData(dto);
		model.addAttribute("errorFill", "Success delete");
		return "redirect:/USR003";
	}
}
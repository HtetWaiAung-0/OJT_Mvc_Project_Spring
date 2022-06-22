package controller;

import java.text.SimpleDateFormat;
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

public class UserController {
	@Autowired
	private UserDAO dao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login() {
		return "Login";
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("bean") UserBean bean, ModelMap model) {
//		if(bs.hasErrors()) {
//			return "addUser";
//		}
		if (bean.getUserMail().isBlank() || bean.getUserPassword().isBlank() || bean.getUserConPassword().isBlank()
				|| bean.getUserRole().isBlank()) {
			model.addAttribute("errorFill", "Fill the Blank!!!");
			return "addUser";
		} else {
			UserRequestDTO dto = new UserRequestDTO();
			dto.setUserId(bean.getUserId());
			dto.setUserMail(bean.getUserMail());
			dto.setUserPassword(bean.getUserPassword());
			dto.setUserRole(bean.getUserRole());
			dao.insertUserData(dto);

			model.addAttribute("errorFill", "Success Register!");
			return "addUser";
		}
	}
	
	@RequestMapping(value="/setupUpdateUser/{user_id}", method=RequestMethod.GET)
	public ModelAndView setupUpdatebook(@PathVariable String userId) {
		UserRequestDTO dto=new UserRequestDTO();
		return new ModelAndView("updateUser","bean", dao.selectId(userId));
	}
}

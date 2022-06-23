package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import bean.StudentBean;
import bean.UserBean;
import dao.CourseDAO;
import dao.CourseStudentDAO;
import dao.StudentDAO;
import dto.CourseStudentRequestDTO;
import dto.CourseStudentResponseDTO;
import dto.StudentRequestDTO;
import dto.StudentResponseDTO;
import dto.UserRequestDTO;

@Controller
public class StudentController {
	
	 @Autowired
	    private StudentDAO dao;
	    private ServletContext servletContext;
	 @RequestMapping(value="/addStuPath", method=RequestMethod.GET)
	    public ModelAndView addStuPath() {
	        StudentBean stuBean = new StudentBean();

	        int i = dao.getId();
	        String finalCourseString = "STU" + String.format("%03d", i);
	        stuBean.setStuId(finalCourseString);
	        return new ModelAndView("BUD003","stuBean",stuBean);
	    }
	 @RequestMapping(value = "/updateStu" , method = RequestMethod.POST)
	 public String updateStu(@ModelAttribute("stuBean")StudentBean stuBean,ModelMap model) {
		 List<String> attendArray = stuBean.getStuAttend();
			
			
//			|| ststuBean.getStuPassword().equals("") || ststuBean.getStuConPassword().equals("")|| ststuBean.getStuRole().equals("")
			if (stuBean.getStuName().isBlank() ) {
				model.addAttribute("errorFill", "Fill the Blank!!!");
				model.addAttribute("ststuBean", stuBean);
				return "USR003.jsp";
			} else {
				StudentResponseDTO res = new StudentResponseDTO();
				StudentRequestDTO dto = new StudentRequestDTO();
				CourseStudentDAO csdao = new CourseStudentDAO();
				CourseStudentRequestDTO csdto = new CourseStudentRequestDTO();
				
				csdto.setStuId(stuBean.getStuId());
				csdao.deleteData(csdto);
				
				for(String a : attendArray ) {
					csdto.setStuId(stuBean.getStuId());
					csdto.setCourseName(a);
					csdao.insertCourseStudnetData(csdto);
				}
				dto.setStuId(stuBean.getStuId());
				dto.setStuName(stuBean.getStuName());
				dto.setStuDob(stuBean.getStuDob());
				dto.setStuGender(stuBean.getStuGender());
				dto.setStuPhone(stuBean.getStuPhone());
				dto.setStuEducation(stuBean.getStuEducation());
				
				dao.updateStudentData(dto);
				
				return "redirect:/SearchStudentController";

			}
	 }
	 
	 @RequestMapping(value = "/addStu", method = RequestMethod.POST)
		public String addStu(@ModelAttribute("stuBean") StudentBean stuBean, ModelMap model) {
//	        if(bs.hasErrors()) {
//	            return "addUser";
//	        }
		 
		 List<String> attendArray = stuBean.getStuAttend();
		 if (stuBean.getStuName().isBlank() || stuBean.getStuDob().isBlank() || stuBean.getStuGender().isBlank()||stuBean.getStuPhone().isBlank() ||stuBean.getStuEducation().isBlank()) {
				model.addAttribute("errorFill","Fill the Blank!!!" );
				model.addAttribute("stuBean", stuBean);
				return "";
			}else {
				StudentResponseDTO res = new StudentResponseDTO();
				StudentRequestDTO dto = new StudentRequestDTO();
				CourseStudentDAO csdao = new CourseStudentDAO();
				CourseStudentRequestDTO csdto = new CourseStudentRequestDTO();
				
				for(String a : attendArray ) {
					csdto.setStuId(stuBean.getStuId());
					csdto.setCourseName(a);
					csdao.insertCourseStudnetData(csdto);
				}
				dto.setStuId(stuBean.getStuId());
				dto.setStuName(stuBean.getStuName());
				dto.setStuDob(stuBean.getStuDob());
				dto.setStuGender(stuBean.getStuGender());
				dto.setStuPhone(stuBean.getStuPhone());
				dto.setStuEducation(stuBean.getStuEducation());
				dao.insertStudentData(dto);
				
				model.addAttribute("errorFill", "Success Add");			
				return "STU001.jsp";
				
			}
		}
	 
	 @RequestMapping(value = "/updateStuPage/{stuId}", method = RequestMethod.GET)
		public ModelAndView updateStuPage(@PathVariable String userId) {
			return new ModelAndView("updateUser", "stuBean", dao.selectId(userId));
		}
	 
	 @RequestMapping(value = "/deleteStu/{stuId}", method = RequestMethod.GET)
		public String deleteStu(@PathVariable String stuId, ModelMap model) {
		 
		 StudentRequestDTO dto = new StudentRequestDTO();
			CourseStudentDAO csdao = new CourseStudentDAO(); 
			CourseStudentRequestDTO csdto = new CourseStudentRequestDTO();
		 dto.setStuId(stuId);
			csdto.setStuId(stuId);
			dao.deleteStudnetData(dto);
			csdao.deleteData(csdto);
				model.addAttribute("errorFill", "Success delete");
				
				return "redirect:/SearchStudentController";
		}
	 
	 @RequestMapping(value = "/searchStuPage", method = RequestMethod.GET)
		public ModelAndView searchStuPage() {
			return new ModelAndView("STU003", "stuBean", new StudentBean());
		}
	 
	 @RequestMapping(value = "/searchStu", method = RequestMethod.POST)
		public String searchStu(@ModelAttribute("stuBean") StudentBean stuBean, ModelMap model) {
		 
			StudentDAO dao = new StudentDAO();
			CourseStudentDAO csdao = new CourseStudentDAO();
			
			String searchId = stuBean.getStuId();
			String searchName = stuBean.getStuName();
			String searchCourse = stuBean.getSearchCourse();
			
			List<CourseStudentResponseDTO> csList = new ArrayList<>();
	;		List<StudentResponseDTO> showList = new ArrayList<>();
			if (searchId.isBlank() && searchName.isBlank() && searchCourse.isBlank()) {
				showList = dao.selectAll();
				for(StudentResponseDTO a : showList) {
					List<String> clist = (List<String>) csdao.selectOne(a.getStuId());
					a.setStuAttend(clist);   
				}
				model.addAttribute("stuList", showList);
				return "STU003.jsp";
			} else {

				if (searchId.isBlank() && searchName.isBlank()) {
//					csList.add(csdao.selectOne(searchId));
					List<String> revList = (List<String>) csdao.selectReverse(searchCourse);
					for(String v : revList) {
						showList.add(dao.selectId(v));
					}
					for(StudentResponseDTO a : showList) {
						List<String> clist = (List<String>) csdao.selectOne(a.getStuId());
						a.setStuAttend(clist);   
					}
					
				} else if (searchId.isBlank() && searchCourse.isBlank()) {
					showList.add(dao.selectName(searchName));
					for(StudentResponseDTO a : showList) {
						List<String> clist = (List<String>) csdao.selectOne(a.getStuId());
						a.setStuAttend(clist);   
					}
					
				} else if (searchName.isBlank() && searchCourse.isBlank()) {
					showList.add(dao.selectId(searchId));
					for(StudentResponseDTO a : showList) {
						List<String> clist = (List<String>) csdao.selectOne(a.getStuId());
						a.setStuAttend(clist);   
					}
				} else if (searchName.isBlank()) {
					List<String> revList = (List<String>) csdao.selectReverse(searchCourse);
					for(String v : revList) {
						showList.add(dao.selectId(v));
					}
					showList.add(dao.selectId(searchId));
					for(StudentResponseDTO a : showList) {
						List<String> clist = (List<String>) csdao.selectOne(a.getStuId());
						a.setStuAttend(clist);   
					}
				} else if (searchId.isBlank()) {
					List<String> revList = (List<String>) csdao.selectReverse(searchCourse);
					for(String v : revList) {
						showList.add(dao.selectId(v));
					}
					showList.add(dao.selectName(searchName));
					for(StudentResponseDTO a : showList) {
						List<String> clist = (List<String>) csdao.selectOne(a.getStuId());
						a.setStuAttend(clist);   
					}
					
				} else if (searchCourse.isBlank()) {
					showList.add(dao.selectIdAndName(searchId, searchName));
					for(StudentResponseDTO a : showList) {
						List<String> clist = (List<String>) csdao.selectOne(a.getStuId());
						a.setStuAttend(clist);   
					}
				} else {
					List<String> revList = (List<String>) csdao.selectReverse(searchCourse);
					for(String v : revList) {
						showList.add(dao.selectId(v));
					}
					showList.add(dao.selectIdAndName(searchId, searchName));
					for(StudentResponseDTO a : showList) {
						List<String> clist = (List<String>) csdao.selectOne(a.getStuId());
						a.setStuAttend(clist);   
					}
				}

				model.addAttribute("stuList", showList);
				

				return "STU003.jsp";
			}
	 }
}

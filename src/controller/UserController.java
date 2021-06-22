package controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pojo.Provider;
import pojo.Role;
import pojo.User;
import service.provider.ProviderService;
import service.role.RoleService;
import service.user.UserService;
import tools.Constants;
import tools.PageSupport;

@Controller
@RequestMapping("/user")
public class UserController {
	private Logger logger = Logger.getLogger(UserController.class);

	@Resource
	private RoleService roleService;

	@Resource
	private UserService userService;

	@Resource
	private ProviderService providerService;

	@RequestMapping(value = "/login.html")
	public String login() {
		logger.debug("UserController welcome SMBMS==================");
		return "login";
	}

	@RequestMapping(value = "/logout.html")
	public String logout(HttpSession session) {
		// ���session
		session.removeAttribute(Constants.USER_SESSION);
		return "login";
	}

	@RequestMapping(value = "/dologin.html", method = RequestMethod.POST)
	public String doLogin(@RequestParam String userCode, @RequestParam String userPassword, HttpServletRequest request,
			HttpSession session) {
		logger.debug("doLogin====================================");
		// ����service�����������û�ƥ��
		User user = userService.login(userCode, userPassword);
		if (null != user) {// ��¼�ɹ�
			// ����session
			session.setAttribute(Constants.USER_SESSION, user);
			// ҳ����ת��frame.jsp)
			return "redirect:/user/main.html";
			// response.sendRedirect("jsp/frame.jsp");
		} else {
			// ҳ����ת��login.jsp��������ʾ��Ϣ
			request.setAttribute("error", "�û��������벻��ȷ");
			return "login";
		}
	}

	@RequestMapping(value = "/main.html")
	public String main(HttpSession session) {
		if (session.getAttribute(Constants.USER_SESSION) == null) {
			return "redirect:/user/login.html";
		}
		return "frame";
	}

	@RequestMapping(value = "/syserror.html")
	public String sysError() {
		return "syserror";
	}

	@RequestMapping(value = "/userlist.html")
	public String getUserList(Model model, @RequestParam(value = "queryname", required = false) String queryUserName,
			@RequestParam(value = "queryUserRole", required = false) String queryUserRole,
			@RequestParam(value = "pageIndex", required = false) String pageIndex) {
		logger.info("getUserList ---- > queryUserName: " + queryUserName);
		logger.info("getUserList ---- > queryUserRole: " + queryUserRole);
		logger.info("getUserList ---- > pageIndex: " + pageIndex);
		int _queryUserRole = 0;
		List<User> userList = null;
		// ����ҳ������
		int pageSize = Constants.pageSize;
		// ��ǰҳ��
		int currentPageNo = 1;

		if (queryUserName == null) {
			queryUserName = "";
		}
		if (queryUserRole != null && !queryUserRole.equals("")) {
			_queryUserRole = Integer.parseInt(queryUserRole);
		}

		if (pageIndex != null) {
			try {
				currentPageNo = Integer.valueOf(pageIndex);
			} catch (NumberFormatException e) {
				return "redirect:/user/syserror.html";
				// response.sendRedirect("syserror.jsp");
			}
		}
		// ����������
		int totalCount = userService.getUserCount(queryUserName, _queryUserRole);
		// ��ҳ��
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();
		// ������ҳ��βҳ
		if (currentPageNo < 1) {
			currentPageNo = 1;
		} else if (currentPageNo > totalPageCount) {
			currentPageNo = totalPageCount;
		}
		userList = userService.getUserList(queryUserName, _queryUserRole, currentPageNo, pageSize);
		model.addAttribute("userList", userList);
		List<Role> roleList = null;
		roleList = roleService.getRoleList();
		model.addAttribute("roleList", roleList);
		model.addAttribute("queryUserName", queryUserName);
		model.addAttribute("queryUserRole", queryUserRole);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentPageNo);
		return "userlist";
	}

	// �û�����
	@RequestMapping(value = "/useradd.html", method = RequestMethod.GET)
	public String addUser(@ModelAttribute("user") User user) {
		return "useradd";
	}

	@RequestMapping(value = "/useraddsave.html", method = RequestMethod.POST)
	public String addUserSave(User user, HttpSession session) {
		user.setCreatedBy(((User) session.getAttribute(Constants.USER_SESSION)).getId());
		user.setCreationDate(new Date());
		if (userService.add(user)) {
			return "redirect:/user/userlist.html";
		}
		return "useradd";
	}

	// �û��޸�
	@RequestMapping(value = "/usermodify.html", method = RequestMethod.GET)
	public String getUserById(@RequestParam String uid, Model model) {
		logger.debug("getUserById uid===================== " + uid);
		User user = userService.getUserById(uid);
		model.addAttribute(user);
		return "usermodify";
	}

	@RequestMapping(value = "/usermodifysave.html", method = RequestMethod.POST)
	public String modifyUserSave(User user, HttpSession session) {
		logger.debug("modifyUserSave userid===================== " + user.getId());
		user.setModifyBy(((User) session.getAttribute(Constants.USER_SESSION)).getId());
		user.setModifyDate(new Date());
		if (userService.modify(user)) {
			return "redirect:/user/userlist.html";
		}
		return "usermodify";
	}

	// REST��ʽ����
	@RequestMapping(value = "/view/{id} ", method = RequestMethod.GET)
	public String view(@PathVariable String id, Model model) {
		logger.debug("view id===================== " + id);
		User user = userService.getUserById(id);
		model.addAttribute(user);
		return "userview";
	}

	// ��Ӧ�̲�ѯ
	@RequestMapping(value = "/providerlist.html")
	public String getProviderList(Model model,
			@RequestParam(value = "queryProName", required = false) String queryProviderName,
			@RequestParam(value = "pageIndex", required = false) String pageIndex) {
		logger.info("getProviderList ---- > queryProviderName: " + queryProviderName);
		logger.info("getProviderList ---- > pageIndex: " + pageIndex);
		List<Provider> providerList = null;
		// ����ҳ������
		int pageSize = Constants.pageSize;
		// ��ǰҳ��
		int currentPageNo = 1;

		if (queryProviderName == null) {
			queryProviderName = "";
		}

		if (pageIndex != null) {
			try {
				currentPageNo = Integer.valueOf(pageIndex);
			} catch (NumberFormatException e) {
				return "redirect:/user/syserror.html";
				// response.sendRedirect("syserror.jsp");
			}
		}
		// ����������
		int totalCount = providerService.getProviderCount(queryProviderName);
		// ��ҳ��
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();
		// ������ҳ��βҳ
		if (currentPageNo < 1) {
			currentPageNo = 1;
		} else if (currentPageNo > totalPageCount) {
			currentPageNo = totalPageCount;
		}
		providerList = providerService.getProviderList(queryProviderName, currentPageNo, pageSize);
		model.addAttribute("providerList", providerList);
		model.addAttribute("queryUserName", queryProviderName);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentPageNo);
		return "providerlist";
	}

	// ��Ӧ������
	@RequestMapping(value = "/provideradd.html", method = RequestMethod.GET)
	public String addProvider(@ModelAttribute("provider") Provider provider) {
		return "provideradd";
	}

	@RequestMapping(value = "/provideraddsave.html", method = RequestMethod.POST)
	public String addProviderSave(Provider provider, HttpSession session) {
		provider.setCreatedBy(((User) session.getAttribute(Constants.USER_SESSION)).getId());
		provider.setCreationDate(new Date());
		if (providerService.add(provider)) {
			return "redirect:/user/providerlist.html";
		}
		return "provideradd";
	}

	// �޸�
	@RequestMapping(value = "/providermodify.html", method = RequestMethod.GET)
	public String getProviderById(@RequestParam String proid, Model model) {
		Provider provider = providerService.getProviderById(proid);
		model.addAttribute(provider);
		return "providermodify";
	}

	@RequestMapping(value = "/providermodifysave.html", method = RequestMethod.POST)
	public String modifyUserSave(Provider provider, HttpSession session) {
		provider.setModifyBy(((User) session.getAttribute(Constants.USER_SESSION)).getId());
		provider.setModifyDate(new Date());
		if (providerService.modify(provider)) {
			return "redirect:/user/providerlist.html";
		}
		return "providermodify";
	}

	// REST��ʽ����
	@RequestMapping(value = "/proview/{id} ", method = RequestMethod.GET)
	public String proView(@PathVariable String id, Model model) {
		Provider provider = providerService.getProviderById(id);
		model.addAttribute(provider);
		return "providerview";
	}

}

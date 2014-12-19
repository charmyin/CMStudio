package com.charmyin.cmstudio.basic.authorize.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.Validator;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ConcurrentAccessException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charmyin.cmstudio.basic.authorize.domain.Identity;
import com.charmyin.cmstudio.basic.authorize.form.LoginForm;
import com.charmyin.cmstudio.basic.authorize.form.RegistrationForm;
import com.charmyin.cmstudio.basic.authorize.service.IdentityService;
import com.charmyin.cmstudio.basic.authorize.service.OrganizationService;
import com.charmyin.cmstudio.basic.authorize.service.RoleService;
import com.charmyin.cmstudio.basic.authorize.service.UserInitService;
import com.charmyin.cmstudio.basic.authorize.service.UserService;
import com.charmyin.cmstudio.basic.authorize.vo.Menu;
import com.charmyin.cmstudio.basic.authorize.vo.Role;
import com.charmyin.cmstudio.basic.authorize.vo.Token;
import com.charmyin.cmstudio.basic.authorize.vo.User;
import com.charmyin.cmstudio.common.utils.JSRErrorUtil;
import com.charmyin.cmstudio.common.utils.MD5Util;
import com.charmyin.cmstudio.common.utils.UUIDGenerator;
import com.charmyin.cmstudio.tzyc._7s.vo.TestVORepository;
import com.charmyin.cmstudio.tzyc._7s.vo.TestVo;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.multitype.MultiTypeCaptchaService;

/**
 * Handle the login authority requests of this program
 * 
 * @author charmyin
 * 
 */
@Controller
public class IdentityController {
	


	@Resource
	private IdentityService identityService;
	
	@Resource
	private UserService userService;
	
	@Resource(name="userInitServiceDatabaseImpl")
	private UserInitService userInitService;
	
	@Resource
	private OrganizationService organizationService;

	@Resource
	private RoleService roleService;
	
	@Resource
	private MultiTypeCaptchaService captchaService;
	
	private Validator validator;
	
	
	private static final Logger logger = LoggerFactory.getLogger(IdentityController.class);

	/**
	 * Show the Login form If have loged in then go to the basic/index page
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "/", "/identity/login", "/identity" })
	public String login(Locale locale, Model model) {
		
		
		
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			return "basic/index";
		}
		logger.trace("Entering login");
		return "basic/authorize/login";
	}

	/**
	 * Shows the registration form.
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "/identity/registration" })
	public String registration(Locale locale, Model model) {
		logger.trace("Entering Registration");
		model.addAttribute("registration", new RegistrationForm());
		return "identity/registration";
		// return "basic/main/index";
	}

	/**
	 * Handles the submission from the registration form.
	 * 
	 * @param registration
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = { "/identity/register" })
	public String register(@Valid RegistrationForm registration, BindingResult result) {
		logger.trace("Entering Register");

		if (result.hasErrors()) {
			return "basic/main/index";
		}

		Identity identity = this.identityService.registerIdentity(registration);
		//
		// model.addAttribute("registration", registration);
		// model.addAttribute("identity", identity);
		// return "identity/register";
		return "basic/main/index";
	}
	
	
	/**
	 * Token登录验证接口，手机端登录
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = { "/login" })
	public @ResponseBody Map userLoginAuth(@Valid LoginForm loginForm, HttpServletRequest request, BindingResult result, Model model) {
		//使当前session失效
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser!=null)
		currentUser.logout();
		
		loginForm.setValidateCode(null);
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> authMap = authenticateUser(loginForm, request, result, model, true);
		if(authMap.get("status").equals("ok")){
			resultMap.put("status", "1");
			resultMap.put("msg", "登录成功");
			resultMap.put("userId", authMap.get("userId"));
			//通过获取companyId
			resultMap.put("companyId", authMap.get("coId"));
			List<Role> roleList = roleService.getRoleByOrganizationId(Integer.parseInt(authMap.get("orgId").toString()));
			//获取roleName
			if(roleList.size() > 0){
				resultMap.put("roleId", roleList.get(0).getName());		
			}else{
				resultMap.put("roleId", null);		
			}
			Hashtable<String, Token> userTokenMap = (Hashtable<String, Token>)request.getServletContext().getAttribute("userTokenMap");
			//String token = request.getSession().getId();
			//request.getSession().getLastAccessedTime();
			/*request.getSession().getServletContext().get*/
			//生成token
			String tokenId = UUIDGenerator.generate();
			Token token = new Token(tokenId, authMap.get("userId").toString(), new Date());
			userTokenMap.put(tokenId, token);
			resultMap.put("token", tokenId);
	 
			 Object menuListObj =  currentUser.getSession().getAttribute("menuList");
			 List<Menu> menuList=null;
			 if(menuListObj!=null){
				 menuList = (List<Menu>)menuListObj;
			 }
			 
			 StringBuilder menuStr = new StringBuilder();
			 for(Menu menu : menuList){
				 if(menu.getId()==67 || menu.getId()==70 || menu.getId()==71|| menu.getId()==72){
					 menuStr.append(menu.getOrderNumber()).append(",");
				 }
			 }
			 String menuStrResult = "";
			 if(menuStr.length()>0){
				 menuStrResult = menuStr.substring(0, menuStr.length()-1);
			 }
			 
			 resultMap.put("menu", menuStrResult);
			
		}else{
			resultMap.put("status", "0");
		}
		
		return resultMap;
	}

	/**
	 * Logs the user in, handles submission from the login form.
	 * 
	 * @param loginForm
	 * @param result
	 * @param model
	 * @param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = { "/identity/authenticate" })
	public @ResponseBody Map authenticateUser(@Valid LoginForm loginForm, HttpServletRequest request, BindingResult result, Model model, Boolean noMD5Encode) {
		
		logger.trace("A request has been received, and validate it's effectivity~");
		Map<String, Object> map = new HashMap<String, Object>();
		if (result.hasErrors()) {
			String errorInfo = JSRErrorUtil.getErrorString(result);
			map.put("status", "error");
			map.put("msg", errorInfo);
			return map;
		}

		logger.trace("Validate validate code~");
		// If the captcha field is already rejected
		  boolean validCaptcha = false;
		  try {
			  logger.debug("---------Session id for captcha---------"+request.getSession().getId());		  
			  validCaptcha = captchaService.validateResponseForID(request.getSession().getId(), loginForm.getValidateCode());
		  }
		  catch (CaptchaServiceException e) {
		      //should not happen, may be thrown if the id is not valid
			  logger.warn("validateCaptcha()", e);
		  }
		  //如果为手机端，绕过验证码
		  if(loginForm.getValidateCode()!=null && !validCaptcha){
			  map.put("status", "error");
			  map.put("msg", "验证码错误");
			  return map;
		  }
		
		logger.trace("Entering Authenticate");
		if(noMD5Encode==null){
			loginForm.setPassphrase(MD5Util.MD5(loginForm.getPassphrase()));
		}
		
		UsernamePasswordToken token = new UsernamePasswordToken(loginForm.getUsername(), loginForm.getPassphrase());

		// <Remember Me>built-in, just do this
		// TODO: Make this a user option instead of hard coded in.
		//token.setRememberMe(true);

		Subject currentUser = SecurityUtils.getSubject();
		// Authenticate the user
		authenticateUserByToken(currentUser, token, map);

		if (currentUser.isAuthenticated()) {
			// Set user's menu list to session
			map.put("status", "ok");
			//TODO First version do like this, store every logined user's menuList fully in session scope
			//Later I will save all the menu in menulist in application scope, then store the user's menu ids in session scope
			List<Menu> menuList = userInitService.getMenusByLoginId(loginForm.getUsername());
			User userInfo = userService.getUserByName(loginForm.getUsername());
			
			//COID
			int coId = organizationService.getOrganizationById(userInfo.getOrganizationId()).getParentId();
			
			userInfo.setCoId(coId);
			
			currentUser.getSession().setAttribute("userInfo", userInfo);
			currentUser.getSession().setAttribute("menuList", menuList);
			map.put("userId", userInfo.getId()+"");
			map.put("orgId", userInfo.getOrganizationId()+"");
			map.put("coId", coId+"");
			return map;
		}

		map.put("status", "error");

		return map;
	}

	/**
	 * Authenticate the login user, catch the exception, log and return it~
	 * 
	 * @param currentUser
	 * @param token
	 * @param resultMap
	 */
	private void authenticateUserByToken(Subject currentUser, UsernamePasswordToken token, Map<String, Object> resultMap) {
		try {
			currentUser.login(token);
			logger.info("AUTH SUCCESS");
		} catch (UnknownAccountException ue) {
			logger.info("AUTH MSSG: " + ue.getMessage());
			resultMap.put("msg", "用户名不存在！");
		} catch (IncorrectCredentialsException ice) {
			logger.info("AUTH MSSG: " + ice.getMessage());
			resultMap.put("msg", "用户名或密码错误!");
		} catch (LockedAccountException lae) {
			logger.info("AUTH MSSG: " + lae.getMessage());
			resultMap.put("msg", "当前账号已被锁定!请稍后再试！");
		} catch (ExpiredCredentialsException ece) {
			logger.info("AUTH MSSG: " + ece.getMessage());
			resultMap.put("msg", "当前用户密码已经过期，请及时更改！");
		} catch (ExcessiveAttemptsException eae) {
			logger.info("AUTH MSSG: " + eae.getMessage());
			resultMap.put("msg", "当前账号登录尝试过于频繁，请稍后再试！");
		} catch (ConcurrentAccessException cae) {
			logger.info("AUTH MSSG: " + cae.getMessage());
			resultMap.put("msg", "当前系统不允许多点登录，请先退出之前登录的系统！");
		} catch (AuthenticationException ae) {
			logger.warn("AUTH MSSG: " + ae.getMessage());
			resultMap.put("msg", "未知原因，登录失败！" + ae.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/identity/logout" })
	public String logout(Locale locale, Model model) {
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.logout();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("loginForm", new LoginForm());
		return "basic/authorize/login";
	}

	/**
	 * Show the Login form
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "/identity/unauthorized" })
	public String unauthorized(Locale locale, Model model) {
		logger.trace("Unauthorized user");
		return "basic/errorpage/unauthorized";
	}

	/**************Get Set*********************/
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public IdentityService getIdentityService() {
		return identityService;
	}

	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}

	public UserInitService getUserInitService() {
		return userInitService;
	}

	public void setUserInitService(UserInitService userInitService) {
		this.userInitService = userInitService;
	}

	public Validator getValidator() {
		return validator;
	}

	@Autowired
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public MultiTypeCaptchaService getCaptchaService() {
		return captchaService;
	}

	public void setCaptchaService(MultiTypeCaptchaService captchaService) {
		this.captchaService = captchaService;
	}
	
	
}

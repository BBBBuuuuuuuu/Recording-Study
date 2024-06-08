package membership;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;

public class BuyMembershipHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/memberShipForm.jsp";
	
	MembershipService mvshipService = new MembershipService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equalsIgnoreCase("get")) {
			return processForm(request, response);
		} else if (request.getMethod().equalsIgnoreCase("post")) {
			return processPost(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	public String processForm(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("authUser");
		if(user.getMonth() != null) {
			return "/WEB-INF/view/selectForm.jsp";
		}
		LoginVO loginInfo = mvshipService.selectByID(user.getId());
		request.getSession().setAttribute("loginInfo", loginInfo);
		request.setAttribute("loginInfo", loginInfo);
		return FORM_VIEW;
	}

	private String processPost(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors", errors);

		LoginVO loginInfo = (LoginVO) request.getSession().getAttribute("loginInfo");
		
		Boolean confirmPWD = mvshipService.checkPWD(loginInfo.getId(), request.getParameter("pwd"));
		if(!confirmPWD) {
			errors.put("notMatchPWD", true);
		}
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		String month = request.getParameter("month");
		MembershipInfo mvshipInfo = mvshipService.buyMembership(loginInfo, month);
		if(mvshipInfo == null) {
			errors.put("balance", true);
			return FORM_VIEW;
		}

		return "/WEB-INF/view/selectForm.jsp";
	}

}
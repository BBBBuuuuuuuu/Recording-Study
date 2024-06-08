package member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;

public class MainHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("authUser") == null) {
			return "/WEB-INF/view/Main.jsp";
		} else {
			return "/WEB-INF/view/Main1.jsp";
		}
	}

	
}

package comment;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleContentNotFoundException;
import article.service.ArticleData;
import article.service.ArticleNotFoundException;
import article.service.ReadArticleService;
import member.model.Member;
import mvc.command.CommandHandler;

public class CommentHandler implements CommandHandler {

	private final String VIEW_FORM = "/WEB-INF/view/readArticle.jsp";
	private ReadArticleService readService = new ReadArticleService();
	private CommentService comService = new CommentService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int articleNum = Integer.parseInt(req.getParameter("articleNum"));
		String id = req.getParameter("commentID");
		String pwd = req.getParameter("commentPWD");

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		try {
			ArticleData articleData = readService.getArticle(articleNum, false);
			req.setAttribute("articleData", articleData);
			String content = req.getParameter("content");

			if (id == null || id.isEmpty())
				errors.put("id", Boolean.TRUE);
			if (pwd == null || pwd.isEmpty())
				errors.put("pwd", Boolean.TRUE);
			if (content.length() == 0) {
				errors.put("comment", true);
			}

			Member checkID = comService.checkID(id, pwd);
			
			if (checkID == null && errors.get("id")==null && errors.get("pwd") == null) {
				errors.put("notMatch", true);
			}
			

			if (errors.isEmpty()) {
				CommentInfo comInfo = new CommentInfo(id, content, articleNum, new Date());
				comService.writeComment(comInfo);
			}

			List<CommentInfo> comments = comService.selectComment(articleNum);
			req.setAttribute("commentData", comments);

			if (!errors.isEmpty()) {
				return VIEW_FORM;
			}

		} catch (ArticleNotFoundException | ArticleContentNotFoundException e) {
			req.getServletContext().log("no article", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		return VIEW_FORM;
	}
}

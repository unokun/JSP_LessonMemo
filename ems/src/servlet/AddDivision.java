package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DivisionDao;

/**
 * Servlet implementation class AddDivision
 */
@WebServlet("/AddDivision")
public class AddDivision extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddDivision() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String next = "/WEB-INF/jsp/newDivision.jsp";
		request.getRequestDispatcher(next).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "";
		List<String> errors = new ArrayList<>();
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action").toString();
		if ("CANCEL".equals(action)) {
			String next = "/WEB-INF/jsp/newDivision.jsp";
			response.sendRedirect(next);
			return;
		}

		String id = request.getParameter("id");
		if (id == null || id.isEmpty()) {
			errors.add("部署IDが未入力です");
		}
		String name = request.getParameter("name");
		if (name == null || name.isEmpty()) {
			errors.add("部署名が未入力です");
		}
		ServletContext application = getServletContext();
		if (errors.isEmpty()) {
			DivisionDao dao = new DivisionDao();
			boolean result = dao.insert(id, name);
			message = result ? "部署の登録に成功しました。" : "部署の登録に失敗しました。";
		}
		application.setAttribute("message", message);
		application.setAttribute("errors", errors);
		String next = "/WEB-INF/jsp/result.jsp";
		request.getRequestDispatcher(next).forward(request, response);
	}
}

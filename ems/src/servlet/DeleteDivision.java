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
 * Servlet implementation class DeleteDivision
 */
@WebServlet("/DeleteDivision")
public class DeleteDivision extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDivision() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "";
		List<String> errors = new ArrayList<>();
		String id = request.getParameter("id").toString();
		if (id == null || id.isEmpty()) {
			errors.add("部署IDが未入力です");
		}
		ServletContext application = getServletContext();
		if (errors.isEmpty()) {
			DivisionDao dao = new DivisionDao();
			boolean result = dao.delete(id);
			message = result ? "部署の削除に成功しました。" : "部署の削除に失敗しました。";
		}
		application.setAttribute("message", message);
		application.setAttribute("errors", errors);
		String next = "/WEB-INF/jsp/result.jsp";
		request.getRequestDispatcher(next).forward(request, response);
	}

}

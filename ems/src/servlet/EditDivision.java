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
import model.Division;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditDivision")
public class EditDivision extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDivision() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id").toString();
		DivisionDao dao = new DivisionDao();
		Division division = dao.findById(id);
		ServletContext application = getServletContext();
		application.setAttribute("division", division);

		String next = "/WEB-INF/jsp/editDivision.jsp";
		request.getRequestDispatcher(next).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "";
		List<String> errors = new ArrayList<>();
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
//		if (id == null || id.isEmpty()) {
//			errors.add("部署IDが未入力です");
//		}
		String name = request.getParameter("name");
		if (name == null || name.isEmpty()) {
			errors.add("部署名が未入力です");
		}
		ServletContext application = getServletContext();
		if (errors.isEmpty()) {
			DivisionDao dao = new DivisionDao();
			boolean result = dao.update(id, name);
			message = result ? "部署の更新に成功しました。" : "部署の更新に失敗しました。";
		}
		application.setAttribute("message", message);
		application.setAttribute("errors", errors);
		String next = "/WEB-INF/jsp/result.jsp";
		request.getRequestDispatcher(next).forward(request, response);
	}
}

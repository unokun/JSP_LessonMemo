package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommonLogic;

@WebServlet("/SingleWord")
public class SingleWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SingleWordServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		// 実行するLogicクラスのインスタンスを取得する
		CommonLogic logic = null;
		// 遷移先のパスを設定
		String next = "/WEB-INF/jsp/";
		// トップ画面からの遷移の場合
		if (action == null) {
			next = next + "login.jsp";
		} else {
			// actionパラメータがnullでない場合
			try {
				// パラメータで渡されたクラスをロード
				Class clazz = Class.forName(action);
				// 該当クラスのインスタンスを取得
				logic = (CommonLogic) clazz.newInstance();
				// 該当クラスのロジックを実行
				String fileName = logic.execute(request, response);
				// 遷移先のパスとして文字列連結
				next = next + fileName;
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				// システムエラーとしてエラー画面を表示
				next = next + "error.jsp";
			}
		}
		// フォワード処理
		request.getRequestDispatcher(next).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGet()メソッドで実行
		doGet(request, response);
	}
}
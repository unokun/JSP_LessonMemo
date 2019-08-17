package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;

public class LoginLogic implements CommonLogic {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// リクエストパラメータの取得
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		// ログイン処理
		if (password.equals("P@$$")) {
			// ユーザ情報をセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("user", new User(userName, password));
			return "speech.jsp";
		} else {
			// ログイン失敗時のメッセージをリクエストスコープに保存
			request.setAttribute("loginFailure", "ログインに失敗しました。もう一度入力してください。");
			return "login.jsp";
		}
	}
}
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutLogic implements CommonLogic {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 既存のセッションスコープを取得
		HttpSession session = request.getSession();
		// セッションスコープを破棄
		session.invalidate();
		// ログアウト画面のファイルを返す
		return "logout.jsp";
	}

}
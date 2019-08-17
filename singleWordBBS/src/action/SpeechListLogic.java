package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.SingleWord;
import bean.User;

@SuppressWarnings("unchecked")
public class SpeechListLogic implements CommonLogic {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// ログイン有無のチェックのため、セッションスコープからユーザ情報を取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// ログインチェック
		if (user == null) {
			// ログインしていない場合（直接リクエスト）
			request.setAttribute("loginFailure", "ログイン画面を経由していません。ログイン処理から行ってください。");
			return "login.jsp";
		} else {
			// ログインしている場合
			// ひとことリストをアプリケーションスコープから取得
			ServletContext application = session.getServletContext();
			List<SingleWord> lists = (ArrayList<SingleWord>) application.getAttribute("lists");

			// リクエストパラメータ「comment」から値を取得
			String comment = request.getParameter("comment");

			if (comment != null) {
				if (comment.length() != 0) {
					// 投稿された「ひとこと」をインスタンスとして生成
					SingleWord word = new SingleWord(user.getUserName(), comment);
					// 生成した「ひとこと」インスタンスを「ひとこと」リストの先頭に挿入
					lists.add(0, word);
				} else {
					// エラーメッセージをリクエストスコープに保存
					request.setAttribute("errorMessage", "ひとこと発言してください。");
				}
			}

			// アプリケーションスコープに「ひとこと」リストを登録
			application.setAttribute("lists", lists);
			// 新規発言も含めて再表示
			return "speechList.jsp";
		}
	}
}
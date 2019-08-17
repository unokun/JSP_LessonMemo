package action;
 
import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import bean.SingleWord;
import bean.User;
 
public class SpeechLogic implements CommonLogic {
 
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    // ログイン有無のチェックのため、セッションスコープからユーザ情報を取得
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("user");
 
    // ログインチェック
    if( user == null ){
      // ログインしていない場合（直接リクエスト）
      request.setAttribute("loginFailure", "ログイン画面を経由していません。ログイン処理から行ってください。");
      return "login.jsp";
    } else {
      // ログインしている場合
      // ひとことリストをアプリケーションスコープから取得
      ServletContext application = session.getServletContext();
      List<SingleWord> lists = (ArrayList<SingleWord>) application.getAttribute("lists");
 
			// アプリケーションスコープからひとことリストが取得できなかったとき
			if (lists == null) {
				// ひとことリストを新規作成
				lists = new ArrayList<SingleWord>();
				// アプリケーションスコープに保存
				application.setAttribute("lists", lists);
			}
			return "speechList.jsp";
		}
	}
}
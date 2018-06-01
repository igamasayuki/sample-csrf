package com.sample;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 掲示板.
 * 
 * @author igamasayuki
 *
 */
@WebServlet("/BbsServlet")
public class BbsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 掲示板ページを表示する.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Article> articleList = (List<Article>) session
				.getAttribute("articleList");
		if (articleList == null) {
			articleList = new LinkedList<>();
			session.setAttribute("articleList", articleList);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/bbs.jsp");
		rd.forward(request, response);
	}

	/**
	 * 記事を投稿する.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// クロスサイトリクエストフォージェリ対策
		// リファラ(遷移元のWebページURL)情報取得
		// ここで遷移元が意図しないURLならエラーメッセージを出す
		String referer = request.getHeader("REFERER");
		System.out.println(referer);
		if (!"http://localhost:8080/cross-site-request-forgeries/BbsServlet".equals(referer)){
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/error.jsp");
			rd.forward(request, response);
			return;
		}
		
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String body = request.getParameter("body");

		Article article = new Article();
		article.setName(name);
		article.setBody(body);

		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Article> articleList = (List<Article>) session
				.getAttribute("articleList");
		articleList.add(article);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/bbs.jsp");
		rd.forward(request, response);
	}
}

package controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.SendResult;

public class MemberController extends HttpServlet
							  implements Servlet{

	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String requestURI=request.getRequestURI();	
		String contextPath=request.getContextPath();	
		String command=requestURI.substring(contextPath.length());	
		
		if(command.equals("/memAgree.mem")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/agree.jsp");
			dispatcher.forward(request, response);
		}else if (command.equals("/memRegist.mem")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberForm.jsp");
			dispatcher.forward(request, response);
		}else if (command.equals("/memJoin.mem")) {
			request.setCharacterEncoding("utf-8");
			MemberJoinPage action = new MemberJoinPage();
			action.memInsert(request);
			response.sendRedirect("main.sm");
		}else if(command.equals("/memList.mem")) {
			MemberListPage action = new MemberListPage();
			action.memList(request);
			response.setCharacterEncoding("utf-8");
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberList.jsp");
			dispatcher.include(request, response);//include나 forward 둘다 가능
		}else if(command.equals("/memInfo.mem")) {
			MemberInfoPage action = new MemberInfoPage();
			action.memInfo(request);
			request.setCharacterEncoding("utf-8");
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberInfo.jsp");
			dispatcher.include(request, response);
		}else if(command.equals("/memMod.mem")){
			MemberInfoPage action = new MemberInfoPage();
			action.memInfo(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memberModify.jsp");
			dispatcher.include(request, response);
		}else if(command.equals("/memModifyOk.mem")) {
			response.setCharacterEncoding("utf-8");
			//db로 다시 전달
			MemberModifyPage action = new MemberModifyPage();
			action.memUpdate(request);
			response.sendRedirect("/memList.mem");
		}else if(command.contentEquals("/memDel.mem")) {
			request.setCharacterEncoding("utf-8");
			MemberDeletePage action = new MemberDeletePage();
			action.memDel(request);
			response.sendRedirect("/memList.mem");
		}else if(command.equals("/myPage.mem")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memMyPage.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/memDetail.mem")) {
			MemberDetailPage action = new MemberDetailPage();
			action.memberDetail(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memDetail.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/memSujung.mem")) {
			MemberDetailPage action = new MemberDetailPage();
			action.memberDetail(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/memSujung.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/memSujungOk.mem")) {
			MemberUpdatePage action = new MemberUpdatePage();
			int i =action.memberUpdate(request);
			if(i == 1) {
				response.sendRedirect("memDetail.mem");
			}else {//비밀번호 틀렸을때
				response.sendRedirect("memSujung.mem");
			}
		}else if(command.equals("/memOut.mem")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/outPw.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/memOutOk.mem")) {//탈퇴페이지
			MemberOutPage action = new 	MemberOutPage();
			int i = 1;
			if(i==1) {
				response.sendRedirect("main.sm");
			}else {
				response.sendRedirect("memOut.sm");
			}
		}else if(command.equals("/memPwChange.mem")) {
			RequestDispatcher dispatcher =  request.getRequestDispatcher("member/pwchange.jsp");
			dispatcher.forward(request, response);
			
		}else if(command.equals("/pwChangeOk.mem")) {
			MemberPwConfirmpage action = new MemberPwConfirmpage();
			String path = action.pwConfirm(request);
			
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		}else if(command.equals("/ChangePw.mem")) {
			MemberPwChangePage action = new MemberPwChangePage();
			int i = action.pwChange(request);
			if(i == 1) {
				response.sendRedirect("main.sm");
			}else {
				RequestDispatcher dispatcher =
						request.getRequestDispatcher("member/pwChange.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		
		
	}
	



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
}

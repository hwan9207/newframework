package com.kh.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {
	
	// final 키워드 사용시 객체의 불변성을 보장할 수 있다.
	private final MemberService memberService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	/*
	@RequestMapping("login.do") // RequestMapping타입의 애노테이션을 붙임으로서 HandlerMapping 등록
	public void login() {
		log.info("로그인 요청을 보냈니?");
	}
	*/
	
	/**
	 * 1. 로그인
	 * 
	 * 로그인이란 무엇인가
	 * 
	 * 우리 팀이 생각하는 로그인 
	 * 
	 * 로그인의 대 전제
	 * 1) 회원가입이 성공적으로 수행되어 있어야 함
	 * 2) 회원가입시 사용자에게 id값과 password값을 입력받아야 함
	 * 
	 * 로그인 : 사용자가 회원가입 기능 사용 시 입력했던 id값과 password값을 입력하면 
	 * 		  유효성 검사를 거쳐 화면상에서 자바스크립트 정규표현식을 사용하여 
	 * 		  올바른 값이 입력되었는지 확인한 뒤 Controller -> Service -> Repository를 거쳐
	 * 		  DataBase에 id값과 password값을 WHERE 조건절의 비교대상으로 검사하여
	 * 		  조회된 결과가 존재할 시 회원의 정보를 반환받아 VO객체의 필드에 대입하여 Session영역에 저장해놓는 것
	 * 
	 *  화면상에서 정상적인 입력값을 입력하지 않았을 시 : 입력할 수 없는 특수문자, 이상한 문자 -> 자바스크립트를 통해 이벤트 실행 X
	 *  화면상에서 입력받을 때 조건 : 영어, 숫자 8자 이내 아이디를 받을거고 css를 이용해서 띄워줄 것 요소 조작 display 조건
	 *  
	 *  올바른 값을 입력했지만 DB에 값이 존재하지 않을 시 : NULL값을 반환해서 alert창 / 에러페이지
	 *  
	 *  로그인 기능 구현 시 필요한 화면의 개수 : memubar.jsp / errorPage.jsp
	 *  로그인 기능 구현 시 필요한 클래스 : MemberController, MemberServiceImpl, MemberRepository, Member
	 *  로그인 기능 구현 시 매핑 값 : login.do
	 *  로그인 기능 구현 시 사용할 메서드명 : login, selectMember, selectById, findById
	 *  
	 *  발생할 수 있는 예외 : ~~ 예외처리 ~~
	 *  담당자 : 홍길동
	 *  
	 *  참고사이트 : abc.com~ bbbb.com 이런식으로 구현할 것~~ 근데 얘네는 어쩌고가 이건 어찌한지 모름
	 */			
	
	
	/**
	 * Spring에서 Handler가 요청 시 전달값(Parameter)을 받는 방법
	 * 
	 * 1. HttpServletRequest을 이용해서 전달받기(기존의 JSP/Servlet방식)
	 * 
	 * 해당 핸들러의 매개변수로 HttpServletRequest타입을 작성해두면
	 * DispatcherServlet이 해당 메서드를 호출할 때 request객체를 전달해서 매개변수로 주입해줌!
	 * 
	 */

	/*
	@RequestMapping("login.do")
	public String login(HttpServletRequest request) {

		String userId = request.getParameter("id");
		String userPwd = request.getParameter("pwd");
		
		log.info("회원이 입력한 아이디 값 : {} ", userId);
		log.info("회원이 입력한 비밀번호 값 : {} ", userPwd);
		
		return "main";
	}
	*/
	
	
	/**
	 * 2. @RequestParam 애노테이션을 이용하는 방법
	 * 		request.getParameter("키값")로 value를 뽑아오는 역할을 대신해주는 애노테이션 
	 * 
	 * value / name 속성의 값으로 jsp에서 작성했던 name속성값을 적으면 알아서 해당 매개변수로 주입을 해줌
	 * 만약, 넘어온 값이 비어있는 형태라면 defaultValue 속성으로 기본값을 지정할 수 있음
	 */
	
	/*
	@RequestMapping("login.do")
	public String login(@RequestParam(value="id", defaultValue="aaa") String userId,
						@RequestParam(value="pwd") String userPwd) {
		log.info("회원이 입력한 아이디 값 : {} ", userId);
		log.info("회원이 입력한 비밀번호 값 : {} ", userPwd);
		
		return "main";
	}
	*/
	
	
	/**
	 * 3. RequestParam 애노테이션을 생략하는 방법
	 * 
	 * 단, 매개변수 식별자를 jsp의 name 속성값(요청시 전달하는 값의 키값)과 동일하게 작성해주어야만 자동으로 값이 주입
	 * 단점이라고 한다면 2. 에서 사용했던 defaultValue속성을 사용할 수 없음.
	 */
	
	/*
	@RequestMapping("login.do")
	public String login(String id, String pwd) {
		log.info("회원이 입력한 아이디 값 : {} ", id);
		log.info("회원이 입력한 비밀번호 값 : {} ", pwd);
		
		// 컨트롤러가 해야 할 일! -> 제어
		
		// 1. 데이터 가공 -> DTO
		// 데이터 가공을 해야하는 이유 두가지
		// 1) 업무적 관점 : 개발속도가 빨라지고 실수가 적어진다. 
		// 2) 기술적 관점 : sqlsession에서 값을 넘길 때 두개이상의 값을 넘길 수없기때문에, dto를 이용
		Member member = new Member();
		member.setUserId(id);
		member.setUserPwd(pwd);
		
		// 1.5. 서비스 호출
		
		
		// 2. 응답화면 지정
		return "main";
	}
	*/
	
	/**
	 * 4. 커맨드 객체 방식
	 * 
	 * **** 반드시 name속성값과 담고자하는 필드명이 동일해야함! + setter가 꼭 있어야함! + 기본생성자가 꼭 있어야함!
	 * 
	 * 해당 메서드의 매개변수로 요청 시 전달값을 담고자하는 클래스의 타입을 지정한 뒤
	 * 요청 시 전달값의 키값(jsp의 name속성값)을 클래스의 담고자하는 필드명과 동일하게 작성
	 * 
	 * 스프링 컨테이너가 해당 객체를 기본생성자로 생성한 후 내부적으로 setter메소드를 찾아서 요청 시
	 * 전달값을 해당 필드에 담아줌(setter injection)
	 */
	
//	@RequestMapping("login.do")
//	public String login(Member member) {
//		
//		log.info("가공된 멤버객체 : {} ", member);
//		// 여기까지가 가공 끝! --> 비즈니스 로직 호출
//		
//		Member loginMember = memberService.login(member);
//		
//		
//		log.info("멤버다 멤버 : {} ", loginMember);
//		
//		
//		return "main";
//	}
	
	/*
	 * Spring에서 제공하는 ModelAndView 타입을 사용하는 방법
	 * Model은 데이터를 key-value세트로 담을 수 있는 공간이라고 한다면
	 * View는 응답 뷰에 대한 정보를 담을 수 있는 공간
	 * Model객체와 View가 결합된 형태의 객체
	 */
	
	// 자바에서는 대입연산자를 기준으로 왼쪽과 오른쪽의 자료형이 같아야 함!
	// 자바에서는 동일한 타입의 값끼리만 연산이 가능함 => 연산의 결과도 동일한 타입이어야함
	
	// long == 8Byte;
	// float == 4Byte;
	
	// float f1 = 213L;
	
	// localhost/spring ==> ??
	// localhost/spring/member	=> 회원 관련
	
	// 회원 상세 조회 /detail.do
	//			  localhost/spring/member/10
	// @GetMapping
	// 회원 추가 : @PostMapping
	// 회원 삭제 : @DeleteMapping
	
	// localhost/spring/notice	=> 공지사항 관련
	// localhost/spring/board	=> 게시판 관련
	
	// REST방식의 URL 만들기
	// localhost/spring/member/12
//@GetMapping("/member/{id}")
//public void restTest(@PathVariable String id) {
//
//log.info("앞단에서 넘긴 값 : {}", id);
//}

	
	@PostMapping("login.do")
	public ModelAndView login(Member member,
							  ModelAndView mv,
							  HttpSession session) {
		Member loginUser = memberService.login(member);
		// 암호문에 포함되어 있는 Salt값을 판단해서 평문에 Salt값을 더해서 암호화를 반복하여 두 값이 같은지 비교
		
		if(loginUser != null && bCryptPasswordEncoder.matches(member.getUserPwd(), loginUser.getUserPwd())) {
			session.setAttribute("loginUser", loginUser);
			session.setAttribute("loginMSg", "로그인 성공");
			mv.setViewName("redirect:/");
		} else {
			mv.addObject("errorMsg", "로그인 실패 했습니다.").setViewName("common/errorPage");
		}
		return mv;  
	}
	
	@GetMapping("logout.do")
	public String logout(HttpSession session) {
		//세션 초기화	session.invalidate();  이거는 session에 내용 다 삭자하기 때문에 안좋다
		session.removeAttribute("loginUser");
		return "redirect:/";
	}
	
	@GetMapping("enroll.do")
    public String enrollForm() {
        return "member/enrollFrom";
    }
	
	//1. 한글애 깨지는 문제 => web.xml 스프링이 제공하는 인코딩 필터 등록
	//2. 비밀번호 암호화 => 
	@PostMapping("join.do")
	public String join(Member member, Model model) {
		String viewName = "";
		//log.info("회원가입 객체 : {}", member);
		//log.info("평문 : {}", member.getUserPwd());
		String encPwd = bCryptPasswordEncoder.encode(member.getUserPwd());
		log.info("암호화 : {}",encPwd);
		member.setUserPwd(encPwd);
		if(memberService.insert(member)>0) {
			viewName= "redirect:/";
		}else {
			model.addAttribute("errorMsg","회원가입실패");
			viewName = "common/errorPage";
		}
		return viewName;
	}
	
	@GetMapping("myPage.do")
	public String myPage() {
		return "member/myPage";
	}
	
	@PostMapping("update.do")
	public String update(Member member,
						HttpSession session,
						Model model) {
		log.info("수정 요청 실패 :{}",member);
		//String encPwd = bCryptPasswordEncoder.encode(member.getUserPwd());
		//member.setUserPwd(encPwd);
		Member currentUser = (Member) session.getAttribute("loginUser");
		
		 if (member.getUserPwd() != null && !member.getUserPwd().isEmpty()) {
		        String encPwd = bCryptPasswordEncoder.encode(member.getUserPwd());
		        member.setUserPwd(encPwd);
		    } else {
		        member.setUserPwd(currentUser.getUserPwd());
		    }
		
		if(memberService.update(member)>0) {
			
		/*	 
			포워딩 - 새로고침을 하면 컨트롤러를 계속 호출하게 되어 좋지 않다
				   유지보수가 힘들다 페이지명이 변경시 다 수작업으로 변경 해야함
				   
	    redirect - 그 호출 컨트롤러로 재호출하여 페이지에 직접지정을 안해줘도 되어 유지보수가 좋다
	       			ex) redirect:myPage.do
	       			
   			갱신된 화면 출력하려면 DB로부터 수정된 회원정보를 다시 조회해서 
   			sessionScope에 loginUser 라는 키값으로 덮어씌어줄것
   			
			model 메세지를 여기에 담으면 update.do는 myPage.do로 redirect 보내기 때문에
			alertMsg가 보내지지 않는다
			
			session은 session 날리기전 까지는 모든 jsp에서 사용 가능 model말고 session에 담자
		*/	
			
			
			Member loginUser = memberService.login(member);
			session.setAttribute("loginUser", loginUser);
			session.setAttribute("infoMsg", "정보수정 성공");
			
			return "redirect:myPage.do";
		}else {
			model.addAttribute("errorMsg", "정보 수정에 실패");
			return "common/errorPage";
		}
	}
	
	@PostMapping("delete.do")
	public String delete(Member member, HttpSession session, Model model) {
		/*매핑요청 받아서 왔고 탈퇴조건 만족 했는지 확인
		  매개변수 Member => userPwd : 사용자가 입력한 비밀번호 평문
		  암호화된 비밀번호는 DB,session에 있음
		  -> session의 loginUser 키값으로 저장되어 있는 Member객체의 userPwd 필드 : DB에 기록된 암호화 비번
		  
		  session에 대입할때 object 타입으로 set 되기 때문에 불러올때도 object 형태로 온다
		  
		  (Member)session.getAttribute("loginUser").getUserPwd();  이게 안되는 이유는 
		  앞에 내용이 먼저 member로 타입이 변환되어야 하는데 안됐기 때문 () 먼저 타입 변환을 해주고 .get 필요요소 가져오면 된다
		*/
		String plainPwd = member.getUserPwd();
		String encPwd = ((Member)session.getAttribute("loginUser")).getUserPwd(); 
		// 1.Member의 주소값
		// 2.Object 타입
		
		if(bCryptPasswordEncoder.matches(plainPwd, encPwd)){
			if(memberService.delete(member.getUserId())> 0) {
				session.setAttribute("alertMsg", "탈퇴 성공");
				session.removeAttribute("loginUser");
				return "redirect:/";
			}else {
				model.addAttribute("errorMsg", "회원 탈퇴 실패");
				return "common/errorPage";
			}
		} else {
			session.setAttribute("alertMsg", "비밀번호가 일치하지 않습니다");
			return "redirect:mypage.do";
		}
	}
	
	@ResponseBody
	@GetMapping("idCheck.do")
	public String checkId(String checkId) {
		return memberService.idCheck(checkId) > 0 ? "NNNNN" : "NNNNY";
	}
	
	@GetMapping("/pwCheck")
    @ResponseBody
    public String checkPw(@RequestParam("checkPwd") String checkPwd, HttpSession session) {
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser != null && bCryptPasswordEncoder.matches(checkPwd, loginUser.getUserPwd())) {
            return "success";
        } else {
            return "fail";
        }
    }
	
}



























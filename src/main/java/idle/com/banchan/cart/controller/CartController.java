package idle.com.banchan.cart.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import idle.com.banchan.cart.model.CartVO;
import idle.com.banchan.cart.service.CartService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CartController {
	
	@Autowired
	CartService service;
	
	@Autowired
	HttpSession session;
	
	
	@RequestMapping(value = "/c_selectAll.do", method = RequestMethod.GET)
	public String c_selectAll(CartVO vo,Model model) {
		log.info("/c_selectAll.do...{}",vo);
		
		List<CartVO> vos = service.selectAll(vo);
		log.info("vos:{}",vos);
		model.addAttribute("vos",vos);
		
		

		return "cart/selectAll";
	}
	
	
	@RequestMapping(value = "/c_insert.do", method = RequestMethod.GET)
	public String c_insert() {
		log.info("/c_insert.do...");

		
		return "cart/insert";
	}
	
	@RequestMapping(value = "/c_insertOK.do", method = RequestMethod.POST)
	public String c_insertOK(CartVO vo,Model model) {
		log.info("/c_insertOK.do...{}",vo);
		
		session.setAttribute("member_id", "user001"); // 테스트값. session 구현시 변경
		vo.setMember_id((String)session.getAttribute("member_id"));
		
		int result = service.insert(vo);
		log.info("result:{}",result);
		
		if(result==1) {
			return "redirect:c_selectAll.do?member_id="+vo.getMember_id();
		}else {
			return "redirect:pr_selectOne.do?num="+vo.getNum();
		}
		
	}
	
	@RequestMapping(value = "/c_deleteOK.do", method = RequestMethod.GET)
	public String c_deleteOK(CartVO vo) {
		log.info("/c_deleteOK.do...{}",vo);
		
		session.setAttribute("member_id", "user001"); // 테스트값. session 구현시 변경
		vo.setMember_id((String)session.getAttribute("member_id"));
		
		
		int result = service.delete(vo);
		log.info("result:{}",result);

			return "redirect:c_selectAll.do?member_id="+vo.getMember_id();
	}
	
}

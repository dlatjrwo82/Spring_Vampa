package com.vam.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vam.model.BoardVO;
import com.vam.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
    @Autowired
    private BoardService bservice;
    
//    게시판 목록 페이지 접속
    @GetMapping("/list")
    // => @RequestMapping(value="list", method=RequestMethod.GET)
    public void boardListGET(Model model) {        
        log.info("게시판 목록 페이지 진입");
        model.addAttribute("list", bservice.getList());
    }
    
    @GetMapping("/enroll")
    // => @RequestMapping(value="enroll", method=RequestMethod.GET)
    public void boardEnrollGET() {        
        log.info("게시판 등록 페이지 진입");
    }
    
    /* 게시판 등록 */
    @PostMapping("/enroll")
    public String boardEnrollPOST(BoardVO board, RedirectAttributes rttr) {
        log.info("BoardVO : " + board);
        rttr.addFlashAttribute("result", "enrol success");
        bservice.enroll(board);
        return "redirect:/board/list";
    }
    
    /* 게시판 조회 */
    @GetMapping("/get")
    public void boardGetPageGET(int bno, Model model) {
        model.addAttribute("pageInfo", bservice.getPage(bno));
    }
    
    /* 수정 페이지 이동 */
    @GetMapping("/modify")
    public void boardModifyGET(int bno, Model model) {
        model.addAttribute("pageInfo", bservice.getPage(bno));
    }
    
//    RedirectAttributes는 리다이렉트가 발생하기 전에 모든 플래시 속성을 세션에 복사한다. 
//    리다이렉션 이후에는 저장된 플래시 속성을 세션에서 모델로 이동시킨다. 
//    헤더에 파라미터를 붙이지 않기 때문에 URL에 노출되지 않는다.
//    https://blog.naver.com/allkanet72/220964699929
    /* 페이지 수정 */
    @PostMapping("/modify")
    public String boardModifyPOST(BoardVO board, RedirectAttributes rttr) {
        bservice.modify(board);
        rttr.addFlashAttribute("result", "modify success"); 
        return "redirect:/board/list";
    }
    
}

package com.mnu.sample.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
//예외를 처리하고 errors.htm으로 연결하는 방법
@ControllerAdvice
public class GlobalExceptionHandler {
	// CustomException 처리
    @ExceptionHandler(CustomException.class)
    public String handleCustomException(CustomException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error/errors"; // errors.html 또는 errors.jsp 연결 (템플릿 엔진 설정에 따름)
        //return "error/errorPopup"; // errorPopup.html 또는 errorPopup.jsp 연결 (템플릿 엔진 설정에 따름)
    }

    // 기타 모든 예외 처리 (선택 사항)
    @ExceptionHandler(Exception.class)
    public String handleAllException(Exception e, Model model) {
        model.addAttribute("errorMessage", "시스템 오류가 발생했습니다: " + e.getMessage());
        return "error/errors";
        //return "error/errorPopup";
    }
}
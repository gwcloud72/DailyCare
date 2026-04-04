package com.gwcloud.dailycare.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gwcloud.dailycare.dto.CowCreateRequest;
import com.gwcloud.dailycare.dto.CowResponse;
import com.gwcloud.dailycare.exception.DuplicateEarTagException;
import com.gwcloud.dailycare.service.CowService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/*
 * 타임리프 화면 전용 컨트롤러
 * /api/cows 는 JSON API 용도이고,
 * /cows 는 실제 화면을 띄우는 용도로 사용한다.
 */
@Controller
@RequiredArgsConstructor
public class CowPageController {

    private final CowService cowService;

    /*
     * 처음 접속하면 개체 목록 화면으로 이동
     */
    @GetMapping("/")
    public String home() {
        return "redirect:/cows";
    }

    /*
     * 개체 목록 화면
     */
    @GetMapping("/cows")
    public String list(Model model) {
        List<CowResponse> cows = cowService.findAll();
        model.addAttribute("cows", cows);
        return "cow/list";
    }

    /*
     * 개체 등록 화면
     */
    @GetMapping("/cows/new")
    public String createForm(Model model) {
        model.addAttribute("cowCreateRequest", new CowCreateRequest());
        return "cow/form";
    }

    /*
     * 개체 등록 처리
     * 검증 에러가 있으면 다시 등록 화면으로 보낸다.
     */
    @PostMapping("/cows")
    public String create(
            @Valid @ModelAttribute("cowCreateRequest") CowCreateRequest request,
            BindingResult bindingResult,
            Model model
    ) {

        if (bindingResult.hasErrors()) {
            return "cow/form";
        }

        try {
            cowService.create(request);
        } catch (DuplicateEarTagException e) {
            model.addAttribute("duplicateMessage", "이미 등록된 개체번호입니다.");
            return "cow/form";
        }

        return "redirect:/cows";
    }
}
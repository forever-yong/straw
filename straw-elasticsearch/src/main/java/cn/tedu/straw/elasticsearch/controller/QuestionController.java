package cn.tedu.straw.elasticsearch.controller;

import cn.tedu.straw.elasticsearch.service.IQuestionService;
import cn.tedu.straw.elasticsearch.vo.QuestionSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/questions")
public class QuestionController {

    @Autowired
    IQuestionService questionService;

    // http://localhost:8200/v1/questions/search?keyword=问题&page=1
    // http://localhost:/search/v1/questions/search?keyword=问题&page=1
    @GetMapping("/search")
    public Page<QuestionSearchVO> search(String keyword, Integer page){
        if (page==null||page<1){
            page=1;
        }
        return questionService.search(keyword, page);
    }
}

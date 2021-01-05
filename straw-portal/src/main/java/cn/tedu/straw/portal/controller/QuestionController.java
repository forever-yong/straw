package cn.tedu.straw.portal.controller;


import cn.tedu.straw.portal.dto.PostQuestionDTO;
import cn.tedu.straw.portal.ex.*;
import cn.tedu.straw.portal.model.Question;
import cn.tedu.straw.portal.security.LoginUserInfo;
import cn.tedu.straw.portal.service.IQuestionService;
import cn.tedu.straw.portal.util.R;
import cn.tedu.straw.portal.vo.QuestionSimpleListItemVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2020-10-26
 */
@RestController
@RequestMapping("/portal/questions")
public class QuestionController {
    @Autowired
    IQuestionService questionService;
    // http://localhost:8080/portal/questions/post?title=Title3&content=Content3&tagIds=5&tagIds=10&tagIds=17&teacherIds=2&teacherIds=5&teacherIds=9
      @RequestMapping("/post")
    public R post(@Valid PostQuestionDTO postQuestionDTO, BindingResult bindingResult, @AuthenticationPrincipal LoginUserInfo loginUserInfo){
          if(bindingResult.hasErrors()){
              String errorMessage = bindingResult.getFieldError().getDefaultMessage();
              throw new ParamValidationException(errorMessage);
          }
          Integer userId = loginUserInfo.getId();
          String userNickName= loginUserInfo.getNickName();
          questionService.post(postQuestionDTO,userId,userNickName);
          return R.ok();
      }

    @Value("${project.fileupload.question-image.base-dir}")
    String uploadBaseDir;
    @Value("${project.fileupload.question-image.max-size}")
    long uploadMaxSize;
    @Value("${project.fileupload.question-image.content-types}")
    List<String> contentTypes;

    // http://localhost:8080/portal/questions/upload-image
    @PostMapping("/upload-image")
    public R<String> uploadImage(MultipartFile imageFile) {
        // 检查上传的文件是否为空
        if (imageFile.isEmpty()) {
            throw new FileEmptyException("上传图片失败！请选择有效的图片文件！");
        }
        // 检查上传的文件大小
        if (imageFile.getSize() > uploadMaxSize) {
            throw new FileSizeException("上传图片失败！不允许上传超过" + uploadMaxSize / 1024 + "KB的文件！");
        }
        // 检查上传的文件类型
        if (!contentTypes.contains(imageFile.getContentType())) {
            throw new FileTypeException("上传图片失败！允许上传的图片类型有：" + contentTypes);
        }

        // 保存上传文件时使用的文件夹
        String yearAndMonth = DateTimeFormatter.ofPattern("yyyy/MM").format(LocalDate.now());
        File parent = new File(uploadBaseDir, yearAndMonth);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        // 保存上传文件时使用的文件名
        String filename = System.currentTimeMillis() + "-" + System.nanoTime();
        String originalFilename = imageFile.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(beginIndex);
        String child = filename + suffix;
        // 保存上传文件的File对象
        File dest = new File(parent, child);
        // 执行保存
        try {
            imageFile.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileUploadIOException("上传图片失败！服务器忙，请稍后再次尝试！");
        } catch (IllegalStateException e) {
            e.printStackTrace();
            throw new FileStateException("上传图片失败！原文件不可用，请检查原文件！");
        }
        // 响应
        String url = "http://localhost:8080/" + yearAndMonth + "/" + child;
        System.out.println("url=" + url);
        return R.ok(url);
    }

    // http://localhost:8080/portal/questions/most-hits
    @GetMapping("/most-hits")
    public R<List<QuestionSimpleListItemVO>> getMostHitsList() {
        return R.ok(questionService.getMostHitsList());
    }

    // http://localhost:8080/portal/questions/my
    @GetMapping("/my")
    public R<PageInfo<Question>> getQuestions(@AuthenticationPrincipal LoginUserInfo loginUserInfo,Integer page){
        if(page==null||page<1){
            page=1;
        }
        Integer userId = loginUserInfo.getId();
        PageInfo<Question> pageInfo = questionService.getMyQuestions(userId,page);
        return R.ok(pageInfo);
    }

    // http://localhost:8080/portal/questions/10
    @GetMapping("/{id}")
    public R<Question> getQuestionDetails(@PathVariable("id") Integer id) {
        Question question = questionService.getQuestionDetails(id);
        return R.ok(question);
    }
}

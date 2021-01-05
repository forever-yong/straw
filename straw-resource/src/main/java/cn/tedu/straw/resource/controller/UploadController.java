package cn.tedu.straw.resource.controller;

import cn.tedu.straw.commons.util.R;
import cn.tedu.straw.resource.ex.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/v1/upload")
public class UploadController {

    @Value("${project.fileupload.question-image.base-dir}")
    String uploadBaseDir;
    @Value("${project.fileupload.question-image.max-size}")
    long uploadMaxSize;
    @Value("${project.fileupload.question-image.content-types}")
    List<String> contentTypes;

    // http://localhost:8888/v1/upload/image
    // http://localhost/resource/v1/upload/image
    @PostMapping("/image")
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
        String url = "http://localhost/resource/" + yearAndMonth + "/" + child;
        System.out.println("url=" + url);
        return R.ok(url);
    }

}

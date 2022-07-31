package com.yanzhen.controller;

import com.yanzhen.utils.RequestUtils;
import com.yanzhen.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class UploadController {

    @PostMapping("/uploadFile")
    public Result upload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) throws IOException {
        //获取文件名
        System.out.println(multipartFile);
        String originalFilename = multipartFile.getOriginalFilename();
        //获取文件名后缀
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".")+1,originalFilename.length());

        //新的文件前缀
        String newFileNamePrefix = UUID.randomUUID().toString();
        //新的文件名
        String newFileName = newFileNamePrefix+"."+ext;
        //上传文件
        multipartFile.transferTo(new File("D:\\aaa\\blog-web-master\\src\\assets\\imgtest",newFileName));
        //最后返回的是一个可以访问的全路径
//        String basePath = RequestUtils.getBasePath(request)+"upload/"+newFileName;
        String basePath = newFileName;
        return Result.success(basePath);

    }
}

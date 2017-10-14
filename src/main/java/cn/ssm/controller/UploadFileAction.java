package cn.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by 28029 on 2017/10/12.
 */
@Controller
public class UploadFileAction {

    @RequestMapping("uploadfile")
    public String uploadPage(){
        return "testcontroller";
    }

    //这个比下面的快一倍
    @RequestMapping("upload_action")
   public String doUploadFile( HttpServletRequest request,HttpServletResponse response)
            throws IllegalStateException, IOException {

        //解析分部数据
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求，这个和JSP中的表单配置是有关系的
        if (multipartResolver.isMultipart(request)) {
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //记录上传过程起始时的时间，用来计算上传时间
                int pre = (int) System.currentTimeMillis();
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {
                        System.out.println(myFileName);
                        //重命名上传后的文件名
                        String fileName = "d" + file.getOriginalFilename();
                        //定义上传路径
                        String path = request.getSession().getServletContext().getRealPath("upload")+"/" + fileName;
                        File localFile = new File(path);
                        file.transferTo(localFile);
                    }
                }
                //记录上传该文件后的时间
                int finaltime = (int) System.currentTimeMillis();
                System.out.println(finaltime - pre);
            }

        }
        return "successed";
    }


    @RequestMapping("upload_action2")
    public String doUpladFile2(@RequestParam("file") MultipartFile file, HttpServletRequest request, ModelMap model){
        System.out.println("开始接时候接收文件");
        int pre = (int) System.currentTimeMillis();
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
//        String fileName = new Date().getTime()+".jpg";
        System.out.println(path);
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("message", "succecced upload file :"+fileName);
        //记录上传该文件后的时间
        int finaltime = (int) System.currentTimeMillis();
        System.out.println(finaltime - pre);
        return "successed";
    }

}

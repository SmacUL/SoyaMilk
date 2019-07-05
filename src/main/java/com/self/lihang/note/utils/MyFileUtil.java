package com.self.lihang.note.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyFileUtil {

    public static String getResourcesUrl(){

        String path = MyFileUtil.class.getResource("/").getPath();
        int tempindex = path.substring(0,path.length()-2).lastIndexOf("/");
        tempindex = path.substring(0,tempindex).lastIndexOf("/");
        String relaPath = path.substring(0,tempindex)+"/front";
        System.out.println(relaPath);
        return relaPath;
    }

    public static String saveFile(MultipartFile file,String path){

        String[] fileName = file.getOriginalFilename().split("\\.");
        String suffix = fileName[fileName.length-1];

        String fileSaveName = file.getName()+"-"+ System.currentTimeMillis() +"."+suffix;
            try {
            File outDir =new File(MyFileUtil.getResourcesUrl()+path);
            if(!outDir.exists())
                outDir.mkdir();
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(outDir,fileSaveName));
        } catch (
        IOException e) {
            e.printStackTrace();
        }
        return fileSaveName;
    }

    public static MultipartFile getFile(HttpServletRequest request){
        MultipartFile file=null;
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        // 获取上传的文件
        Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
        for(Map.Entry<String, MultipartFile> entry : fileMap.entrySet()){
            // 对文件进处理
            file=entry.getValue();
        }
        return file;
    }
    public static MultipartFile[] getFiles(HttpServletRequest request){
        List<MultipartFile> file=new ArrayList<MultipartFile>();
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        // 获取上传的文件
        Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
        for(Map.Entry<String, MultipartFile> entry : fileMap.entrySet()){
            // 对文件进处理
            file.add(entry.getValue());
        }
        return file.toArray(new MultipartFile[0]);
    }
}

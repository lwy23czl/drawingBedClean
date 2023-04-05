package cn.river;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class FileHandle {

    @Value("${directory}")
    private String directory;

    @Value("${urlRegex}")
    private String regex;

    List<String> list =new ArrayList();

    /**
     * 获取目录md文件列表内的所有图片url
     */
    public List<String> getFileList() throws IOException {
        File file = new File(directory);

        traverseDirectory(file);
        List<String> imgUrlList = getImgUrlList(list);
        return imgUrlList;
    }

    /**
     * 递归获取文件名
     * @param directory
     * @return
     */
    public List<String> traverseDirectory(File directory) {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    traverseDirectory(file);
                } else if (file.isFile() && file.getName().toLowerCase().endsWith(".md")) {
                    list.add(file.getAbsolutePath());
                }
            }
        }
        return list;
    }


    /**
     * 根据文件的绝对路径名，遍历获取文件，返回获取每个文件中的图片路径集合
     */
    public List<String> getImgUrlList(List<String> fileList) throws IOException {
        //创建集合
        List<String> urlList=new ArrayList<>();
        for (String fileName : fileList) {
            //创建字符输入流对象
            FileReader fileReader = new FileReader(fileName);
            //字符缓冲输入流
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //读取文件
            String line;
            while ((line=bufferedReader.readLine())!=null){
                Pattern compile = Pattern.compile(regex);
                Matcher matcher = compile.matcher(line);
                while (matcher.find()){
                    String url = matcher.group().replaceFirst("http://qn.lwy23czl.top/","");
                    urlList.add(url);
                }
            }
            bufferedReader.close();
            fileReader.close();
        }
        return urlList;
    }

}

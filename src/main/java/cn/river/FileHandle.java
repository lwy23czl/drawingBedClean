package cn.river;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileHandle {

    @Value("${directory}")
    private String directory;
    List<String> list =new ArrayList();

    /**
     * 获取目录md文件列表
     */
    public List<String> getFileList(){
        File file = new File(directory);
        traverseDirectory(file);
        return list;
    }

    /**
     * 递归获取文件名
     * @param directory
     * @return
     */
    private List<String> traverseDirectory(File directory) {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    traverseDirectory(file);
                } else if (file.isFile() && file.getName().toLowerCase().endsWith(".md")) {
                    // 处理md格式文件
                    System.out.println(file.getAbsolutePath());
                    list.add(file.getAbsolutePath());
                }
            }
        }
        return list;
    }
}

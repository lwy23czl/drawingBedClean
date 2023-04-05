package cn.river;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Component
public class Main {
    @Autowired
    private FileHandle fileHandle;
    @Autowired
    private QiniuUtil qiniuUtil;
    @Value("${qiniu.dir}")
    private String dir;

    @PostConstruct
    public void DelImg() throws IOException {
        //获取本地图片url列表
        List<String> fileList = fileHandle.getFileList();
        //获取图床上的图片列表
        List<String> qnList = qiniuUtil.listFileFromQiniu(dir);
        //取两个集合的差
        qnList.removeAll(fileList);
        //遍历，删除
        int count=0;
        if (qnList.size()>0){
            for (String url : qnList) {
                qiniuUtil.deleteFileFromQiniu(url);
                count++;
            }
        }
        System.out.println("总共删除"+count+"张图片");
    }
}

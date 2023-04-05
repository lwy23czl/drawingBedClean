package cn.river;

import com.qiniu.common.QiniuException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class DrawingBedCleanApplicationTests {

    @Autowired
    public FileHandle fileHandle;

    @Autowired
    public QiniuUtil qiniuUtil;

    @Test
    void contextLoads() {
    }

    @Test
    void testFileList() throws IOException {
//        List<String> fileList = fileHandle.getFileList();
//        System.out.println(fileList);
        List<String> list = fileHandle.traverseDirectory(new File("D:\\Desktop\\啥都有\\mdNote"));
        System.out.println(fileHandle.getImgUrlList(list));
    }

    @Test
    void listName() throws QiniuException {
        System.out.println(qiniuUtil.listFileFromQiniu("typora"));
        System.out.println(qiniuUtil.listFileFromQiniu("typora").size());
    }

    @Test
    void delImg(){
        qiniuUtil.deleteFileFromQiniu("typora/202304/03/233056-350081.png");
    }
}

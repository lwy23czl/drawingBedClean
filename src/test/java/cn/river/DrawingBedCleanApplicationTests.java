package cn.river;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class DrawingBedCleanApplicationTests {

    @Autowired
    public FileHandle fileHandle;

    @Test
    void contextLoads() {
    }

    @Test
    void testFileList() throws IOException {
        List<String> fileList = fileHandle.getFileList();
//        System.out.println(fileList);
        System.out.println(fileHandle.getImgUrlList(fileList));
    }

}

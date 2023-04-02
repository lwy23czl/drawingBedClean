package cn.river;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DrawingBedCleanApplicationTests {

    @Autowired
    public FileHandle fileHandle;

    @Test
    void contextLoads() {
    }

    @Test
    void testFileList(){
        fileHandle.getFileList();
    }

}

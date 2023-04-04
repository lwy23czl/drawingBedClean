package cn.river;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QiniuUtil {

    @Value("${qiniu.ak}")
    private   String accessKey;
    @Value("${qiniu.sk}")
    private   String secretKey;
    @Value("${qiniu.bucket}")
    private   String bucket;
    //删除文件
    public  void deleteFileFromQiniu(String fileName){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        String key = fileName;
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }

    /**
     * 获取七牛云存储空间指定文件夹下的文件列表
     * @param dir
     * @return
     */
    public  List<String> listFileFromQiniu(String dir) throws QiniuException {
        Configuration cfg = new Configuration(Zone.zone2());
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        String marker = null;
        List<String> listName=new ArrayList<>();
        do {
            FileListing listFiles = bucketManager.listFiles(bucket, dir, marker, 1000, null);
            FileInfo[] items = listFiles.items;
            for (FileInfo item : items) {
                listName.add(item.key);
            }
            if(listFiles.isEOF()){
                break;
            }
            marker= listFiles.marker;
        }while (true);
        return listName;
    }

}

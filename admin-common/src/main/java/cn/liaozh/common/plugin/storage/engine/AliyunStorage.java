package cn.liaozh.common.plugin.storage.engine;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import cn.liaozh.common.exception.OperateException;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Map;

/**
 * 阿里云存储
 */
public class AliyunStorage {

    /**
     * 存储配置
     */
    private final Map<String, String> config;

    /**
     * 构造方法
     */
    public AliyunStorage(Map<String, String> config) {
        this.config = config;
    }

    /**
     * 鉴权令牌
     *
     * @author fzr
     * @return String
     */
    public OSS ossClient() {
        String endpoint        = this.config.get("endpoint");
        String accessKeyId     = this.config.get("access_key");
        String accessKeySecret = this.config.get("secret_key");
       return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 上传文件
     *
     * @param multipartFile 文件对象
     * @param key 文件名称 20220331/11.png
     */
    public void upload(MultipartFile multipartFile, String key) {
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    this.config.get("bucket"), key,
                    new ByteArrayInputStream(multipartFile.getBytes())
            );
            this.ossClient().putObject(putObjectRequest);
        } catch (OSSException oe) {
            throw new OperateException(oe.getMessage());
        } catch (Exception ce) {
            throw new OperateException(ce.getMessage());
        } finally {
            if (this.ossClient() != null) {
                this.ossClient().shutdown();
            }
        }
    }

    public void delete(String key) {
        this.ossClient().deleteObject(this.config.get("bucket"), key);
    }

}

package com.macie.util;

import com.macie.common.CommonConstants;
import com.macie.config.ProjectDeployConfig;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Macie
 * @date 2021/10/10 -0:59
 */
public class ImageUploadUtil {
    /**
     * 存储图片
     * @param image
     * @param path
     * @return 存储位置的相对路径
     */
    public static String saveImage(MultipartFile image, String path, String imageName) {
        String userAvatarPath = null;
        if(image == null) {
            return null;
        }
        try {
            String imageSaveDir = ProjectDeployConfig.IMAGE_PATH + path;
            String avatarPath = imageSaveDir + "/" + imageName;
            File destFile = new File(avatarPath);
            int i = 1;
            // 文件名重复
            if (destFile.exists()) {
                imageName = i++ + imageName;
            }
            if(!destFile.getParentFile().exists()) {
                // 目录不存在则创建
                destFile.getParentFile().mkdirs();
            }
            image.transferTo(destFile);
            //返回相对路径
            userAvatarPath = CommonConstants.IMAGE_UPLOAD_PATH + path + "/" + imageName;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return userAvatarPath;
    }
}

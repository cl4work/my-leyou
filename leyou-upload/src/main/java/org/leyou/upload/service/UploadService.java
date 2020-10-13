package org.leyou.upload.service;

import org.leyou.upload.controller.UploadController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @auther ChenLei
 * @create 2020-10-12:16 PM
 */
@Service
public class UploadService {

    public static final List<String> IMAGE_TYPE = Arrays.asList("image/gif", "image/png");
    public static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    public String uploadImage(MultipartFile file) {

        String originalFilename = file.getOriginalFilename();
        //确定为图片类型
        String contentType = file.getContentType();
        if (!IMAGE_TYPE.contains(contentType)) {
            LOGGER.info("图片类型不合法：{}", originalFilename);
            return null;
        }
        try {
            //确定为图片内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                LOGGER.info("图片内容不合法{}", originalFilename);
                return null;
            }
            //保存到服务器
            File dir = new File("I:\\leyou\\image");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            file.transferTo(new File(dir, originalFilename));
            return "http://image.leyou.com/upload/" + originalFilename;
        } catch (IOException e) {
            LOGGER.info("服务器异常：{}", originalFilename);
            e.printStackTrace();
        }
        return null;

    }
}

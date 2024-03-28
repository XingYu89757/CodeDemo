package com.iric.qr_demo;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class QrCodeDemo {
    public static void main(String[] args) throws IOException {
        QrConfig qrConfig = new QrConfig(300,300);
        // 生成到本地
        QrCodeUtil.generate("https://www.baidu.com",qrConfig, FileUtil.file("d:/qrcode.jpg"));
        // 生成MultipartFile
        BufferedImage image = QrCodeUtil.generate("https://www.baidu.com", qrConfig);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // 将图像输出到输出流中。
        ImageIO.write(image, "jpeg", bos);
      //  MultipartFile multipartFile = new MockMultipartFile("test.jpg", "test.jpg", "", bos.toByteArray());

    }
}

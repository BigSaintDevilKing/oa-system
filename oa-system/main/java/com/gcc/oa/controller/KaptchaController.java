package com.gcc.oa.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @className: Kaptcha
 * @author: 小李探花
 * @date: 2022/1/26 21:36
 * @description: 验证码
 */
@RestController
public class KaptchaController extends BaseController {

    @Resource
    private DefaultKaptcha kaptcha;

    @GetMapping("/kaptcha")
    public void kaptcha() {
        byte[] kaptchaBytes;
        ByteArrayOutputStream byteArrOut = new ByteArrayOutputStream();
        String kaptchaText = kaptcha.createText();
        //把验证码存到Session中
        req.getSession().setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, kaptchaText);
        BufferedImage bufferedImage = kaptcha.createImage(kaptchaText);
        try {
            ImageIO.write(bufferedImage, "jpg", byteArrOut);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        //定义输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        kaptchaBytes = byteArrOut.toByteArray();
        resp.setHeader("Cache-Control", "no-store");
        resp.setHeader("Pragma", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/jpeg");
        try {
            ServletOutputStream servletOutputStream = resp.getOutputStream();
            servletOutputStream.write(kaptchaBytes);
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

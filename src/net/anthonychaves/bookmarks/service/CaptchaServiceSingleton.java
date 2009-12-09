package net.anthonychaves.bookmarks.service;
//this class came from http://forge.octo.com/jcaptcha/confluence/display/general/5+minutes+application+integration+tutorial

import com.octo.captcha.service.image.ImageCaptchaService;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;

public class CaptchaServiceSingleton {

    private static ImageCaptchaService instance = new DefaultManageableImageCaptchaService();

    public static ImageCaptchaService getInstance() {
        return instance;
    }
}
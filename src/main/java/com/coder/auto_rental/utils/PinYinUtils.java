package com.coder.auto_rental.utils;

import cn.hutool.extra.pinyin.PinyinUtil;
import org.springframework.stereotype.Component;

@Component
public class PinYinUtils {
    public static String getPinYin(String str) {
        return PinyinUtil.getFirstLetter(str, "").toUpperCase();
    }


}

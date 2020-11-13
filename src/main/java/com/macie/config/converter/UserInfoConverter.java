package com.macie.config.converter;

import com.macie.entity.UserInfo;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Macie
 * @date 2021/1/17 -17:30
 */
public class UserInfoConverter implements Converter<String, UserInfo> {
    @Override
    public UserInfo convert(String source) {
        if(source != null) {

        }
        return null;
    }
}

package com.whatakitty.ssm.web;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.lang.reflect.Type;

/**
 * Created with IntelliJ IDEA.
 *
 * @author xuqiang
 * @date 2018/04/21
 * @description
 **/
public class ToArrayDeserializer implements ObjectDeserializer {

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {

        return null;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }

}

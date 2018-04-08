package com.sccbv.demo;

import com.alibaba.fastjson.JSONReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 演示Easyui页面
 *
 * @author xuqiang
 * @date 2018/04/06
 * @description
 **/
@Controller
@RequestMapping("/demo")
public class ExampleController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public String first() {
        return "demo";
    }

    @ResponseBody
    @RequestMapping(value = "/datagrid_data", method = RequestMethod.POST)
    public Object datagridData(@Value("classpath:com/sccbv/demo/datagrid_data.json") Resource datagridData) throws IOException {
        return getMap(datagridData, OBJECT_TYPE.OBJECT);
    }

    @ResponseBody
    @RequestMapping(value = "/pg_data", method = RequestMethod.POST)
    public Object pgData(@Value("classpath:com/sccbv/demo/pg_data.json") Resource pgData) throws IOException {
        return getMap(pgData, OBJECT_TYPE.OBJECT);
    }

    @ResponseBody
    @RequestMapping(value = "/tree_data", method = RequestMethod.POST)
    public Object treeData(@Value("classpath:com/sccbv/demo/tree_data.json") Resource treeData) throws IOException {
        return getMap(treeData, OBJECT_TYPE.ARRAY);
    }

    @ResponseBody
    @RequestMapping(value = "/tree_grid_data", method = RequestMethod.POST)
    public Object treeGridData(@Value("classpath:com/sccbv/demo/tree_grid_data.json") Resource treeGridData) throws IOException {
        return getMap(treeGridData, OBJECT_TYPE.ARRAY);
    }

    private Object getMap(Resource resource, OBJECT_TYPE objectType) throws IOException {
        JSONReader reader = new JSONReader(new FileReader(resource.getFile().getAbsolutePath()));
        try {
            switch (objectType) {
                case ARRAY:
                    List<Map<String, Object>> result = new ArrayList<>();
                    reader.startArray();
                    while(reader.hasNext()) {
                        Map<String, Object> item = reader.readObject(Map.class);
                        result.add(item);
                    }
                    reader.endArray();
                    return result;
                case OBJECT:
                default:
                    return reader.readObject(Map.class);
            }
        } finally {
            reader.close();
        }
    }

    private enum OBJECT_TYPE {
        ARRAY,
        OBJECT
    }

}

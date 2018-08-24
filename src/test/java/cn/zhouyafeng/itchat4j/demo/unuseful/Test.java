package cn.zhouyafeng.itchat4j.demo.unuseful;

import cn.zhouyafeng.itchat4j.core.Core;
import cn.zhouyafeng.itchat4j.demo.demo2.ParamJsonModel;
import cn.zhouyafeng.itchat4j.demo.demo2.ResultModel;
import cn.zhouyafeng.itchat4j.utils.MyHttpClient;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Test {

    public MyHttpClient myHttpClient = Core.getInstance().getMyHttpClient();
    public String       apiKey       = "69a3298951ed4b7fbc55b73971c199b8";
    String url = "http://openapi.tuling123.com/openapi/api/v2";

    String testString = "{\n" +
            "\t\"reqType\":0,\n" +
            "    \"perception\": {\n" +
            "        \"inputText\": {\n" +
            "            \"text\": \"晚上回来吃饭\"\n" +
            "        },\n" +
            "        \"inputImage\": {\n" +
            "            \"url\": \"\"\n" +
            "        },\n" +
            "        \"selfInfo\": {\n" +
            "            \"location\": {\n" +
            "                \"city\": \"北京\",\n" +
            "                \"province\": \"北京\",\n" +
            "                \"street\": \"信息路\"\n" +
            "            }\n" +
            "        }\n" +
            "    },\n" +
            "    \"userInfo\": {\n" +
            "        \"apiKey\": \"cefc46bb55914364a6fb8f9544062714\",\n" +
            "        \"userId\": \"123456\"\n" +
            "    }\n" +
            "}";

    public static void main(String[] args) {
        Test test = new Test();
        test.testhttpPostWithJSON();
    }

    public String testhttpPostWithJSON()  {
        String result = "";
        int code = 5000;
//        ParamJsonModel paramJsonModel = JSON.parseObject(ParamJsonModel.modelJosn, ParamJsonModel.class);
        HttpEntity entity = myHttpClient.doPost(url, ParamJsonModel.modelJosn);
        try {
            result = EntityUtils.toString(entity, "UTF-8");
            ResultModel resultModel = JSON.parseObject(result, ResultModel.class);
            code = resultModel.getIntent().getCode();
            if (code==5000){
                result = "~ (*+﹏+*)~ 不知道你在说什么";
            }else{
                result = resultModel.getResults().get(0).getValues().getText();
            }

        } catch (IOException e) {
            e.printStackTrace();
            result = "发生错误:"+code+e.toString();
        }

        System.out.println(result);



        return result;
    }

}

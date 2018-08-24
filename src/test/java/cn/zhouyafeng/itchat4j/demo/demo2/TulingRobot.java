package cn.zhouyafeng.itchat4j.demo.demo2;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import cn.zhouyafeng.itchat4j.api.WechatTools;
import cn.zhouyafeng.itchat4j.utils.enums.URLEnum;
import jdk.internal.org.objectweb.asm.Handle;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.zhouyafeng.itchat4j.Wechat;
import cn.zhouyafeng.itchat4j.beans.BaseMsg;
import cn.zhouyafeng.itchat4j.core.Core;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import cn.zhouyafeng.itchat4j.utils.MyHttpClient;
import cn.zhouyafeng.itchat4j.utils.enums.MsgTypeEnum;
import cn.zhouyafeng.itchat4j.utils.tools.DownloadTools;

/**
 * 图灵机器人示例
 *
 * @author https://github.com/yaphone
 * @version 1.0
 * @date 创建时间：2017年4月24日 上午12:13:26
 */
public class TulingRobot implements IMsgHandlerFace {
    Logger       logger       = Logger.getLogger("TulingRobotTest");
    MyHttpClient myHttpClient = Core.getInstance().getMyHttpClient();
    //	String url = "http://www.tuling123.com/openapi/api";
    String       url          = "http://openapi.tuling123.com/openapi/api/v2";
    String       apiKey       = "69a3298951ed4b7fbc55b73971c199b8"; // 这里是我申请的图灵机器人API接口，每天只能5000次调用，建议自己去申请一个，免费的:)

    public File filePath = new File(this.getClass().getResource("/").getPath());

    private static Core core = Core.getInstance();

    boolean isSendMessage = true;

    boolean offGroupMsg = false;

    @Override
    public String textMsgHandle(BaseMsg msg) {

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String result = "";
        String text = msg.getText();
        if (text != null && !"".equals(text)) {
            if ("开启自动回复".equals(text)) {
                isSendMessage = true;
                return "已开启自动回复";
            } else if ("关闭自动回复".equals(text)) {
                isSendMessage = false;
                return "已关闭自动回复";
            } else if (text.equals("退出登陆")) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        WechatTools.logout();
                        System.exit(0);//停止jvm虚拟机
                    }
                }).start();
                return "退出登陆成功";
            } else if (text.equals("开启群聊回复")) {
                offGroupMsg = true;
                return "已开启群聊回复";
            } else if (text.equals("关闭群聊回复")) {
                offGroupMsg = false;
                return "已关闭群聊回复";
            }
        }

        if (!isSendMessage) {
            return "";
        }

        if (!offGroupMsg && msg.isGroupMsg()) {
            return "";
        }

        result = httpto_tuling(msg, text);


        return result;
    }

    public String httpto_tuling(BaseMsg msg, String text) {
        String result = "";
        int code = 5000;
        ParamJsonModel paramJsonModel = JSON.parseObject(ParamJsonModel.modelJosn, ParamJsonModel.class);
        paramJsonModel.getUserInfo().setUserId(Math.abs(msg.getFromUserName().substring(0, 8).hashCode()) + "");
        if (msg.getType().equals(MsgTypeEnum.TEXT.getType())) {
            paramJsonModel.setReqType(0);
            paramJsonModel.getPerception().getInputText().setText(text);
        } else if (msg.getType().equals(MsgTypeEnum.PIC.getType())) {
            paramJsonModel.setReqType(1);
            paramJsonModel.getPerception().getInputImage().setUrl(getUrlReal(msg));
        } else if (msg.getType().equals(MsgTypeEnum.VOICE.getType())) {
            paramJsonModel.setReqType(2);
            paramJsonModel.getPerception().getInputMedia().setUrl(getUrlReal(msg));
        } else if (msg.getType().equals(MsgTypeEnum.VIEDO.getType())) {
            paramJsonModel.setReqType(2);
            paramJsonModel.getPerception().getInputMedia().setUrl(getUrlReal(msg));
        } else {
            paramJsonModel.setReqType(0);
            paramJsonModel.getPerception().getInputText().setText("想你了");
        }

        HttpEntity entity = myHttpClient.doPost(url, JSON.toJSONString(paramJsonModel));
        try {
            result = EntityUtils.toString(entity, "UTF-8");
            ResultModel resultModel = JSON.parseObject(result, ResultModel.class);
            code = resultModel.getIntent().getCode();
            if (code == 5000) {
                result = "~ (*+﹏+*)~ 不知道你在说什么";
            } else {
                result = resultModel.getResults().get(0).getValues().getText();
            }

        } catch (IOException e) {
            e.printStackTrace();
            result = "error:" + code + e.toString();
        }

        return result;
    }

    private String getUrlReal(BaseMsg msg){
        String url = "";

        if (msg.getType().equals(MsgTypeEnum.PIC.getType())) {
            url = String.format(URLEnum.WEB_WX_GET_MSG_IMG.getUrl(), (String) core.getLoginInfo().get("url"));
        } else if (msg.getType().equals(MsgTypeEnum.VOICE.getType())) {
            url = String.format(URLEnum.WEB_WX_GET_VOICE.getUrl(), (String) core.getLoginInfo().get("url"));
        } else if (msg.getType().equals(MsgTypeEnum.VIEDO.getType())) {
            url = String.format(URLEnum.WEB_WX_GET_VIEDO.getUrl(), (String) core.getLoginInfo().get("url"));
            url = url+"?Range=bytes=0-";
        }
        url = url+"?"+"msgid="+msg.getNewMsgId()+"&"+"skey="+(String) core.getLoginInfo().get("skey");

        return url;
    }
    @Override
    public String picMsgHandle(BaseMsg msg) {
        return textMsgHandle(msg);
    }

    @Override
    public String voiceMsgHandle(BaseMsg msg) {
        return textMsgHandle(msg);
    }

    @Override
    public String viedoMsgHandle(BaseMsg msg) {
        return textMsgHandle(msg);
    }

    public static void main(String[] args) {
        IMsgHandlerFace msgHandler = new TulingRobot();

        Wechat wechat = new Wechat(msgHandler, ((TulingRobot) msgHandler).filePath.getPath());
        wechat.start();
    }

    @Override
    public String nameCardMsgHandle(BaseMsg msg) {
        // TODO Auto-generated method stub
        return textMsgHandle(msg);
    }

    @Override
    public void sysMsgHandle(BaseMsg msg) {
        // TODO Auto-generated method stub
    }

    @Override
    public String verifyAddFriendMsgHandle(BaseMsg msg) {
        // TODO Auto-generated method stub
        return textMsgHandle(msg);
    }

    @Override
    public String mediaMsgHandle(BaseMsg msg) {
        // TODO Auto-generated method stub
        return textMsgHandle(msg);
    }

}

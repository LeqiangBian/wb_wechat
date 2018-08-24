package cn.zhouyafeng.itchat4j.test;

import cn.zhouyafeng.itchat4j.Wechat;
import cn.zhouyafeng.itchat4j.api.MessageTools;
import cn.zhouyafeng.itchat4j.api.WechatTools;
import cn.zhouyafeng.itchat4j.beans.BaseMsg;
import cn.zhouyafeng.itchat4j.core.Core;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

public class TestTools implements IMsgHandlerFace {
    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(TestTools.class);
    public static void main(String[] args){
        TestTools msgHandler = new TestTools();
        Wechat wechat = new Wechat(msgHandler, MyPath.getProjectPath());
        wechat.start();

//        String userName1 = WechatTools.getUserNameByNickName("。");
//        String userName2 = WechatTools.getUserNameByNickName("柚子");
//        String userName3 = WechatTools.getUserNameByNickName("丑小鸭");
//        MessageTools.webWxSendMsg(3," ॣ ॣ ॣ", userName1,0);




    }

    public static void createRoom(){



    }

    @Override
    public String textMsgHandle(BaseMsg msg) {
        return null;
    }

    @Override
    public String picMsgHandle(BaseMsg msg) {
        return null;
    }

    @Override
    public String voiceMsgHandle(BaseMsg msg) {
        return null;
    }

    @Override
    public String viedoMsgHandle(BaseMsg msg) {
        return null;
    }

    @Override
    public String nameCardMsgHandle(BaseMsg msg) {
        return null;
    }

    @Override
    public void sysMsgHandle(BaseMsg msg) {

    }

    @Override
    public String verifyAddFriendMsgHandle(BaseMsg msg) {
        return null;
    }

    @Override
    public String mediaMsgHandle(BaseMsg msg) {
        return null;
    }
}

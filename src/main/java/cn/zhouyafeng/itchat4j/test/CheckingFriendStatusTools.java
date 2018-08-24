package cn.zhouyafeng.itchat4j.test;

import cn.zhouyafeng.itchat4j.Wechat;
import cn.zhouyafeng.itchat4j.api.MessageTools;
import cn.zhouyafeng.itchat4j.api.WechatTools;
import cn.zhouyafeng.itchat4j.beans.BaseMsg;
import cn.zhouyafeng.itchat4j.core.Core;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.logging.Logger;

public class CheckingFriendStatusTools implements IMsgHandlerFace {

    public static void main(String[] args) {
        checkFriendStatus();
    }

    private static int deleteCount = 0;
    private static int pullBlackCount = 0;
    static Logger logger = Logger.getLogger("CheckingFriendStatusTools");

    /**
     * 检测好友是否被删除
     */
    public static void checkFriendStatus() {
        IMsgHandlerFace msgHandler = new CheckingFriendStatusTools();
        Wechat wechat = new Wechat(msgHandler, MyPath.getProjectPath());
        wechat.start();
        List<JSONObject> contactList = Core.getInstance().getContactList();
        MessageTools.sendMsgById("获取所有好友,总人数:" + contactList.size() + "人", "filehelper");
        MessageTools.sendMsgById("开始检测", "filehelper");
        int v = (int) ((float) contactList.size() / (float) 5);
        if (v == 0) v = 1;
        for (int i = 0; i < contactList.size(); i++) {
            if (i % v == 0) {
                MessageTools.sendMsgById("已检测"+ (i/v)*20 +"%\n\n好友"+contactList.size()+"人\n\n删除我的人 "+deleteCount+" 个\n\n拉黑我的人 "+pullBlackCount+" 个", "filehelper");
            }
            MessageTools.sendMsgById(" ॣ ॣ ॣ", contactList.get(i).getString("UserName"));
        }
        MessageTools.sendMsgById("检测完成%\n\n好友"+contactList.size()+"人\n\n删除我的人 "+deleteCount+" 个\n\n拉黑我的人 "+pullBlackCount+" 个", "filehelper");
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

        if (msg.getContent().contains("开启了朋友验证，")) {
            deleteCount++;
            String nickName = WechatTools.getNickNameByUserName(msg.getFromUserName());
            MessageTools.sendMsgById("你被 \"" + nickName + "\" 删除", "filehelper");
        } else if (msg.getContent().contains("消息已发出，但被对方拒收了。")) {
            pullBlackCount++;
            String nickName = WechatTools.getNickNameByUserName(msg.getFromUserName());
            MessageTools.sendMsgById("你被 \"" + nickName + "\" 拉黑", "filehelper");
        }
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

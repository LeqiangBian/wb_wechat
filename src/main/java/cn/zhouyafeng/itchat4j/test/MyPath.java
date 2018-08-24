package cn.zhouyafeng.itchat4j.test;

import java.io.File;
import java.io.UnsupportedEncodingException;

public class MyPath {

    /**
     * 获取项目所在路径(不包含包括jar)
     *
     * @return
     */
    public static String getProjectPath() {
        String filePath = "1";
        try {
            java.net.URL url = MyPath.class.getProtectionDomain().getCodeSource()
                    .getLocation();

            filePath = java.net.URLDecoder.decode(url.getPath(), "utf-8");

            if (filePath.endsWith(".jar"))
                filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
            java.io.File file = new java.io.File(filePath);
            filePath = file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

    /**
     * 这个更建议一些吧
     * @return
     */
    private static String getJarPath() {
        String jarPath = "2";
        try {
            String jarWholePath = MyPath.class.getProtectionDomain().getCodeSource().getLocation().getFile();
            jarWholePath = java.net.URLDecoder.decode(jarWholePath, "UTF-8");

            jarPath = new File(jarWholePath).getParentFile().getAbsolutePath();
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.toString());
        }
        return jarPath;
    }

    public static void main(String[] args) {
        System.out.println("1 => " + getProjectPath());//⭐⭐⭐⭐⭐⭐⭐⭐
        System.out.println("2 => " + getJarPath());//⭐⭐⭐⭐⭐⭐⭐⭐
//        System.out.println("3  =>" + System.getProperty("java.class.path"));//jar包路径
//        System.out.println("4 =>" + new File(this.getClass().getResource("").getPath()));当前类路径,专门用来获取资源路径的
    }
}
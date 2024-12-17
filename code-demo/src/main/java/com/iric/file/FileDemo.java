package com.iric.file;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;

import java.io.File;

public class FileDemo {

    private static final String TEMP_DIR = "/yl/logs/deploy/java/yiling-pms-activity-provider/temp/";

    public static void main(String[] args) {
        String path =TEMP_DIR.concat(IdUtil.simpleUUID());
        //filePath = path + "/签到表.docx";

       String filePath = TEMP_DIR.concat(IdUtil.simpleUUID()) + "/签到表.docx";
        FileUtil.mkdir(path);

       deleteFolder(new File(path));
    }
    public static void deleteFolder(File folder) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) { // 一些JVM环境下listFiles可能返回null
                for (File file : files) {
                    deleteFolder(file); // 递归删除文件夹内的文件和子文件夹
                }
            }
        }
        if (!folder.delete()) {
            System.err.println("无法删除文件夹: " + folder.getAbsolutePath());
        }
    }

}

package com.yocn.seep.net.jsonviewer.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

import androidx.core.content.FileProvider;

public class Utils {
    private static Pattern urlPattern = Pattern.compile("^((https|http|ftp|rtsp|mms)?://)"
            + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@
            + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
            + "|" // 允许IP和DOMAIN（域名）
            + "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.
            + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名
            + "[a-z]{2,6})" // first level domain- .com or .museum
            + "(:[0-9]{1,4})?" // 端口- :80
            + "((/?)|" // a slash isn't required if there is no file name
            + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$");

    public static boolean isUrl(String str) {
        return urlPattern.matcher(str).matches();
    }

    public static void copyText(Context context, String text, String toastStr) {
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setPrimaryClip(ClipData.newPlainText(null, text));
        if (!TextUtils.isEmpty(toastStr)) {
            Toast.makeText(context, toastStr, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * get space by hierarchy
     */
    public static String getHierarchyStr(int hierarchy) {
        StringBuilder levelStr = new StringBuilder();
        for (int levelI = 0; levelI < hierarchy; levelI++) {
            levelStr.append("      ");
        }
        return levelStr.toString();
    }

    public static void shareString(final Context context, final String content) {
        int length = content.length();
        File rootDir = context.getExternalCacheDir();
        if (rootDir == null) {
            return;
        }
        final String path = rootDir.getAbsolutePath() + "/net_temp.txt";
        if (canSendByStringMode(length)) {
            new AsyncTask<String, Void, Void>() {
                @Override
                protected Void doInBackground(String... strings) {
                    saveAsFileWriter(path, content);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    shareFile(context, path);
                }
            }.execute();
        } else {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, content);
            shareIntent = Intent.createChooser(shareIntent, "Share Via String");
            context.startActivity(shareIntent);
        }
    }

    //the memory of a String is 40 + 2 * length
    //binder transport limit 1M - 8k, but in bundle sourceCode, only accept data size below 200K
    //http://androidxref.com/8.1.0_r33/xref/frameworks/base/core/jni/android_util_Binder.cpp#signalExceptionForError line:741
    private static boolean canSendByStringMode(int length) {
        return 40 + 2 * length > (200 * 1024);
    }

    private static void shareFile(Context context, String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            Intent share = new Intent(Intent.ACTION_SEND);
            Uri uri;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                uri = FileProvider.getUriForFile(context, "com.yocn.seep.fileprovider", file);
            } else {
                uri = Uri.fromFile(file);
            }

            share.putExtra(Intent.EXTRA_STREAM, uri);
            share.setType("*/*");
            share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            context.startActivity(Intent.createChooser(share, "Share Via File"));
        } else {
            Toast.makeText(context, "Share Fail, No such File.", Toast.LENGTH_SHORT).show();
        }
    }

    private static void saveAsFileWriter(String filePath, String content) {
        File oldFile = new File(filePath);
        if (oldFile.exists()) {
            boolean success = oldFile.delete();
        }
        FileWriter fwriter = null;
        try {
            fwriter = new FileWriter(filePath, false);
            fwriter.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (fwriter != null) {
                    fwriter.flush();
                    fwriter.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


}

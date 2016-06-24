package sword.ios.app.utils;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * Created by admin on 16/2/3.
 */
public class ImageCompare {

    public static void main(String[] args){
        compareImage(getImageStreamFromWeb("https://gw.alicdn.com/tfscom/TB1OMfAJFXXXXbfXXXXqVMCNVXX-400-400.jpg"),getImageStreamFromWeb("https://gw.alicdn.com/tfscom/TB1OMfAJFXXXXbfXXXXqVMCNVXX-400-400.jpg"));

    }

    private static InputStream getImageStreamFromWeb(String urlAddress) {
        URL url = null;
        HttpURLConnection conn = null;
        try {
            url = new URL(urlAddress);
            conn = (HttpURLConnection) url.openConnection();
            if (conn.getResponseCode() == 200) {
                InputStream in = conn.getInputStream();
                return in;
            } else {

            }
        } catch (Exception e) {
        }
        return null;
    }

    public static String[][] getPX(InputStream in) {
        int[] rgb = new int[3];

        BufferedImage bi = null;
        try {
            bi = ImageIO.read(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int width = bi.getWidth();
        int height = bi.getHeight();
        int minx = bi.getMinX();
        int miny = bi.getMinY();
        String[][] list = new String[width][height];
        for (int i = minx; i < width; i++) {
            for (int j = miny; j < height; j++) {
                int pixel = bi.getRGB(i, j);
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                list[i][j] = rgb[0] + "," + rgb[1] + "," + rgb[2];
            }
        }
        rgb = null;
        return list;

    }

    public static boolean compareImage(InputStream img1, InputStream img2) {
        // 分析图片相似度 begin
        String[][] list1 = getPX(img1);
        String[][] list2 = getPX(img2);
        int xiangsi = 0;
        int busi = 0;
        int i = 0, j = 0;
        for (String[] strings : list1) {
            if ((i + 1) == list1.length) {
                continue;
            }
            for (int m = 0; m < strings.length; m++) {
                try {
                    String[] value1 = list1[i][j].toString().split(",");
                    String[] value2 = list2[i][j].toString().split(",");
                    int k = 0;
                    for (int n = 0; n < value2.length; n++) {
                        if (Math.abs(Integer.parseInt(value1[k])
                                - Integer.parseInt(value2[k])) < 5) {
                            xiangsi++;
                        } else {
                            busi++;
                        }
                    }
                } catch (RuntimeException e) {
                    continue;
                }
                j++;
            }
            i++;
        }

        list1 = list2;
        list2 = list1;
        i = 0;
        j = 0;
        for (String[] strings : list1) {
            if ((i + 1) == list1.length) {
                continue;
            }
            for (int m = 0; m < strings.length; m++) {
                try {
                    String[] value1 = list1[i][j].toString().split(",");
                    String[] value2 = list2[i][j].toString().split(",");
                    int k = 0;
                    for (int n = 0; n < value2.length; n++) {
                        if (Math.abs(Integer.parseInt(value1[k])
                                - Integer.parseInt(value2[k])) < 5) {
                            xiangsi++;
                        } else {
                            busi++;
                        }
                    }
                } catch (RuntimeException e) {
                    continue;
                }
                j++;
            }
            i++;
        }
        String baifen = "";
        try {
            baifen = ((Double.parseDouble(xiangsi + "") / Double
                    .parseDouble((busi + xiangsi) + "")) + "");
            baifen = baifen.substring(baifen.indexOf(".") + 1, baifen
                    .indexOf(".") + 3);
        } catch (Exception e) {
            baifen = "0";
        }
        if (baifen.length() <= 0) {
            baifen = "0";
        }
        if (busi == 0) {
            baifen = "100";
        }

        list1 = null;
        list2 = null;

        if (!baifen.equals("100")) {
            return false;
        } else {
            return true;
        }

    }
}

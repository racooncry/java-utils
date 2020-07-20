package com.shenfeng.yxw.utils;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.imageio.ImageIO;

import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

/**
 * @Author yangxw
 * @Date 20/7/2020 上午11:50
 * @Description
 * @Version 1.0
 */
public class PDFUtil {
    public static void pdf2Pic(String pdfPath, String path) throws Exception {
        Document document = new Document();
        document.setFile(pdfPath);
        //缩放比例
        float scale = 2.5f;
        //旋转角度
        float rotation = 0f;

        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage image = (BufferedImage)
                    document.getPageImage(i, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
            RenderedImage rendImage = image;
            try {

                String imgName = UUID.randomUUID().toString() + ".png";
                System.out.println(imgName);
                File file = new File(path + imgName);
                ImageIO.write(rendImage, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.flush();
        }
        document.dispose();
    }

    public static void main(String[] args) throws Exception {
        String filePath = "D:\\yangxw\\work\\front\\yuncheng-internal-service\\yuncheng-internal-service\\src\\pages\\utils\\images\\爱康泰相关证件\\北京二类医疗器械备案-2020.6.18.pdf";
        pdf2Pic(filePath, "D:\\yangxw\\work\\front\\yuncheng-internal-service\\yuncheng-internal-service\\src\\pages\\utils\\images\\爱康泰相关证件\\");
    }
}

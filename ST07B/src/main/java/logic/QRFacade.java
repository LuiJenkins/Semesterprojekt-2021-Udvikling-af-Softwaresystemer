package logic;
import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.nio.Buffer;

public class QRFacade {
    public static Image GetQRCode(String data, int w, int h) throws WriterException,UnsupportedEncodingException {
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes("UTF-8"), "UTF-8"), BarcodeFormat.QR_CODE, w, h);
        return ImageEncoder(matrix,w,h);
    }
    private static Image ImageEncoder(BitMatrix mat,int w,int h) {
        WritableImage img = new WritableImage(w, h);
        PixelWriter pr = img.getPixelWriter();
        int pixels[] = new int[w * h];
        for (int x = 0; x < w;x++) {
            for(int y = 0; y < h;y++) {
                if (mat.get(x,y)) {
                    pixels[x+y*w] = 0xFF000000;
                } else {
                    pixels[x+y*w] = 0x00FFFFFF;
                }
            }
        }
        pr.setPixels(0,0, w, h, PixelFormat.getIntArgbInstance(),pixels,0, h);
        return img;
    }
}

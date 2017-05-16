package com.jitosoft.qrpay.presentation.util;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import io.reactivex.Observable;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class QRCodeGenerator {

    public static Observable<Bitmap> encodeAsBitmap(String data, int w, int h) throws WriterException {

        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, w, h, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }

        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return Observable.defer(() -> Observable.just(bitmap));
    }
}

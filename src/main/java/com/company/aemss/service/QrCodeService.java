package com.company.aemss.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

@Service
public class QrCodeService {

    public byte[] generateQrCode(String data, int width, int height) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            // Use default black and white colors
            MatrixToImageConfig config = new MatrixToImageConfig();

            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);

            // Convert to BufferedImage to ensure PNG creation
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, config);

            // Convert to byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qrImage, "png", baos);

            return baos.toByteArray();
        } catch (WriterException | IOException e) {
            throw new RuntimeException("Error generating QR code", e);
        }
    }

    // Optional: Method to convert byte array to base64 if needed
    public String convertToBase64(byte[] imageBytes) {
        return java.util.Base64.getEncoder().encodeToString(imageBytes);
    }

    // Optional: Method to customize QR code colors
    public byte[] generateQrCodeWithCustomColors(String data, int width, int height, int foregroundColor, int backgroundColor) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            // Custom color configuration
            MatrixToImageConfig config = new MatrixToImageConfig(foregroundColor, backgroundColor);

            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, width, height);

            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, config);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qrImage, "png", baos);

            return baos.toByteArray();
        } catch (WriterException | IOException e) {
            throw new RuntimeException("Error generating QR code", e);
        }
    }
}
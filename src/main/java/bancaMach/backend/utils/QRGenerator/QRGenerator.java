package bancaMach.backend.utils.QRGenerator;


import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class QRGenerator {

    public static String generateQRCodeImageAsBase64(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String base64 = Base64.getEncoder().encodeToString(byteArray);
        return base64;
    }

    public static void generateQRCodeImageFromBase64(String base64, String filePath) throws IOException {
        byte[] byteArray = Base64.getDecoder().decode(base64);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        BufferedImage image = ImageIO.read(byteArrayInputStream);
        ImageIO.write(image, "PNG", new File(filePath));
    }

    public static String decodeQRCode(String qrCodeString) throws NotFoundException {
        MultiFormatReader reader = new MultiFormatReader();
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(toBufferedImage(qrCodeString))));
        Map<DecodeHintType, Object> hints = new EnumMap<>(DecodeHintType.class);
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        hints.put(DecodeHintType.POSSIBLE_FORMATS, Collections.singleton(BarcodeFormat.QR_CODE));
        Result result = null;
        try {
            result = reader.decode(bitmap, hints);
            return result.getText();
        } catch (ReaderException e) {

        }
        return null;
    }

    private static BufferedImage toBufferedImage(String qrCodeString) {
        // Este método convierte el String en un objeto BufferedImage, que es requerido por zxing
        // La implementación depende de cómo fue convertido el QR code a String.
        // Aquí se muestra un ejemplo simple para un QR code que fue convertido utilizando Base64:
        try {
            byte[] imageBytes = Base64.getDecoder().decode(qrCodeString);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            return bufferedImage;
        } catch (IOException e) {
            throw new RuntimeException("No se pudo convertir el código QR a BufferedImage", e);
        }
    }
}

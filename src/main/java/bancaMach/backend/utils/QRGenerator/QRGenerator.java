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
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
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

    /**
     * Transforma una cadena en sha256
     * @param s cadena la cual se transformara en sha256
     * @return devuelve la cadena transformada
     */
    public static String sha256(String s)  {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(s.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : md.digest()) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            s = sb.toString();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return s;
    }

    /**
     * Recoge un array de bytes y los transforma a formato hexadecial
     * @param array array de bytes a transformar
     * @return devuelve la cadena tranformada del array de bytes
     */
    private static String toHexString(byte[] array){
        StringBuilder sb = new StringBuilder(array.length*2);
        for (byte b: array){
            int value = 0xFF & b;
            String toAppend = Integer.toHexString(value);
            sb.append(toAppend);
        }
        sb.setLength(sb.length()-1);

        return sb.toString();
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

package bancaMach.backend.utils.data_extract;

public class DataExtract {

    /**
     * Extract from a String given the elements between "_" and set them to a string array
     * @param data QR text
     * @return String array with data
     */
    public static String[] QRDataText(String data) {
        return new String[data.split("_").length];
    }
}

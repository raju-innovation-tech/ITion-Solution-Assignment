// -------------------- PROBLEM 2 --------------------
import java.io.*;
import java.net.*;

public class Problem2 {

    // Arrays for number to words conversion
    static String[] ones = {"", "One", "Two", "Three", "Four", "Five", "Six",
            "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen",
            "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    static String[] tens = {"", "", "Twenty", "Thirty", "Forty",
            "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    /*
     * Function to convert number into words (Indian system)
     * Example: 7823123 -> Seventy Eight Lakh Twenty Three Thousand...
     */
    public static String numberToWords(int num) {

        if (num == 0) return "Zero";

        String result = "";

        if (num >= 100000) {
            result += numberToWords(num / 100000) + " Lakh ";
            num %= 100000;
        }

        if (num >= 1000) {
            result += numberToWords(num / 1000) + " Thousand ";
            num %= 1000;
        }

        if (num >= 100) {
            result += numberToWords(num / 100) + " Hundred ";
            num %= 100;
        }

        if (num > 0) {
            if (num < 20)
                result += ones[num];
            else
                result += tens[num / 10] + " " + ones[num % 10];
        }

        return result.trim();
    }

    public static void main(String[] args) {

        try {
            // API URL
            String urlString = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=usd,inr,eur";

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            StringBuilder response = new StringBuilder();
            String line;

            // Read response
            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            br.close();

            String json = response.toString();

            // Extract INR value from JSON
            int index = json.indexOf("\"inr\":");
            int start = index + 6;
            int end = json.indexOf(",", start);

            int inrValue = (int) Double.parseDouble(json.substring(start, end));

            System.out.println("Bitcoin INR Price: " + inrValue);
            System.out.println("In Words: " + numberToWords(inrValue));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
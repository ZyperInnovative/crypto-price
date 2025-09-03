import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class BitcoinPriceFetcher {
    public static void main(String[] args) {
        try {
            // CoinGecko API endpoint for Bitcoin price in USD
            URL url = new URL("https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=usd");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read response
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
            );
            StringBuilder response = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            
            // Parse JSON
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject bitcoinData = jsonResponse.getJSONObject("bitcoin");
            double price = bitcoinData.getDouble("usd");
            
            System.out.printf("Bitcoin price: $%.2f%n", price);
            
        } catch (Exception e) {
            System.err.println("Error fetching Bitcoin price: " + e.getMessage());
        }
    }
}

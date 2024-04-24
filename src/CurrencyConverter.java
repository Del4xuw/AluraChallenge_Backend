import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Scanner;

public class CurrencyConverter {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/2d6af4d1befd1ed89df18315/latest/USD";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Sea bienvenido/a al Conversor de Moneda");
            System.out.println("1) Dolar ==> Peso argentino");
            System.out.println("2) Peso argentino ==> Dolar");
            System.out.println("3) Dolar ==> Real brasileño");
            System.out.println("4) Real brasileño ==> Dolar");
            System.out.println("5) Dolar ==> Peso colombiano");
            System.out.println("6) Peso colombiano ==> Dolar");
            System.out.println("7) Salir");
            System.out.print("Elija una opción válida: ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    convertUSDToARS(scanner);
                    break;
                case 2:
                    convertARSToUSD(scanner);
                    break;
                case 3:
                    convertUSDToBRL(scanner);
                    break;
                case 4:
                    convertBRLToUSD(scanner);
                    break;
                case 5:
                    convertUSDToCOP(scanner);
                    break;
                case 6:
                    convertCOPToUSD(scanner);
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, intente de nuevo.");
            }
        }
    }

    private static void convertUSDToARS(Scanner scanner) {
        System.out.print("Ingrese el valor que desea convertir: ");
        double amount = scanner.nextDouble();
        double rate = getExchangeRate("ARS");
        double convertedAmount = amount * rate;
        System.out.printf("El valor %.2f [USD] corresponde al valor de ==> %.2f [ARS]%n", amount, convertedAmount);
    }

    private static void convertARSToUSD(Scanner scanner) {
        System.out.print("Ingrese el valor que desea convertir: ");
        double amount = scanner.nextDouble();
        double rate = getExchangeRate("ARS");
        double convertedAmount = amount / rate;
        System.out.printf("El valor %.2f [ARS] corresponde al valor de ==> %.2f [USD]%n", amount, convertedAmount);
    }

    private static void convertUSDToBRL(Scanner scanner) {
        System.out.print("Ingrese el valor que desea convertir: ");
        double amount = scanner.nextDouble();
        double rate = getExchangeRate("BRL");
        double convertedAmount = amount * rate;
        System.out.printf("El valor %.2f [USD] corresponde al valor de ==> %.2f [BRL]%n", amount, convertedAmount);
    }

    private static void convertBRLToUSD(Scanner scanner) {
        System.out.print("Ingrese el valor que desea convertir: ");
        double amount = scanner.nextDouble();
        double rate = getExchangeRate("BRL");
        double convertedAmount = amount / rate;
        System.out.printf("El valor %.2f [BRL] corresponde al valor de ==> %.2f [USD]%n", amount, convertedAmount);
    }

    private static void convertUSDToCOP(Scanner scanner) {
        System.out.print("Ingrese el valor que desea convertir: ");
        double amount = scanner.nextDouble();
        double rate = getExchangeRate("COP");
        double convertedAmount = amount * rate;
        System.out.printf("El valor %.2f [USD] corresponde al valor de ==> %.2f [COP]%n", amount, convertedAmount);
    }

    private static void convertCOPToUSD(Scanner scanner) {
        System.out.print("Ingrese el valor que desea convertir: ");
        double amount = scanner.nextDouble();
        double rate = getExchangeRate("COP");
        double convertedAmount = amount / rate;
        System.out.printf("El valor %.2f [COP] corresponde al valor de ==> %.2f [USD]%n", amount, convertedAmount);
    }

    private static double getExchangeRate(String currency) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();
            JsonObject jsonObject = JsonParser.parseString(jsonData).getAsJsonObject();
            JsonObject ratesObject = jsonObject.getAsJsonObject("conversion_rates");
            return ratesObject.get(currency).getAsDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0.0;
    }
}
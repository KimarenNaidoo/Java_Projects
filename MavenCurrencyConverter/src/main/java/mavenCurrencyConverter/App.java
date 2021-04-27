package mavenCurrencyConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class App {

	public static void main(String[] args) throws IOException, ParseException {
		
		Boolean running = true;
		do {
			Map<Integer, String> currencyCodes = new HashMap<Integer, String>();
			
			currencyCodes.put(1, "USD");
			currencyCodes.put(2, "CAD");
			currencyCodes.put(3, "EUR");
			currencyCodes.put(4, "ZAR");
			currencyCodes.put(5, "JPY");
			
			Integer from, to;
			String fromCode, toCode;
			double amount;
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("Welcome to the Currency Converter");
			System.out.println("Currency converting from:");
			System.out.println("1: USD(United States Dollar) \t 2: CAD(Canadian Dollar) \t 3: EUR(Euro) \t 4: ZAR(South African Rand) \t 5: JPY(Japanese Yen)");
			from = scan.nextInt();
			while(from < 1 || from > 5) {
				System.out.println("Please enter a valid number");
				System.out.println("1: USD(United States Dollar) \t 2: CAD(Canadian Dollar) \t 3: EUR(Euro) \t 4: ZAR(South African Rand) \t 5: JPY(Japanese Yen)");
				from = scan.nextInt();
			}
			fromCode = currencyCodes.get(from);
			
			System.out.println("Currency converting to:");
			System.out.println("1: USD(United States Dollar) \t 2: CAD(Canadian Dollar) \t 3: EUR(Euro) \t 4: ZAR(South African Rand) \t 5: JPY(Japanese Yen)");
			to = scan.nextInt();
			while(from < 1 || from > 5) {
				System.out.println("Please enter a valid number");
				System.out.println("1: USD(United States Dollar) \t 2: CAD(Canadian Dollar) \t 3: EUR(Euro) \t 4: ZAR(South African Rand) \t 5: JPY(Japanese Yen)");
				to = scan.nextInt();
			}
			toCode = currencyCodes.get(to);
			
			System.out.println("Enter amount to convert:");
			amount = scan.nextDouble();
			
			sendHTTPGetRequest(fromCode, toCode, amount);
			
			System.out.println();
			System.out.println("Would you like to continue with another conversion?");
			System.out.println("1: Yes \t 2: No");
			if(scan.nextInt() != 1) {
				running = false;
			}
			System.out.println();
		}while(running);
		
		System.out.println("Thank you");
	}
	
	
	public static void sendHTTPGetRequest(String fromCode, String toCode, double amount) throws IOException, ParseException {
		String GET_URL = "https://api.ratesapi.io/api/latest?base=" + fromCode + "&symbols=" + toCode;
		DecimalFormat f = new DecimalFormat("00.00");
		
		URL url = new URL(GET_URL);
		HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
		httpURLConnection.setRequestMethod("GET");
		int responseCode = httpURLConnection.getResponseCode(); // retrieves 200 if everything is okay
		
		if(responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String inputLine;
			StringBuffer sb = new StringBuffer();
			
			while((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}
			//System.out.println(sb.toString());
			br.close();
			
			
			
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject)parser.parse(sb.toString());
			JSONObject rates =(JSONObject)obj.get("rates");
			String rate = rates.get(toCode).toString();
			Double exchangeRate = Double.parseDouble(rate);
			
			// OUTPUT
			double finalAmount = amount * exchangeRate;
			//System.out.println("Converting " + amount + " from " + fromCode + " to " + toCode + " is = " + f.format(finalAmount));
			//System.out.println()
			System.out.println(f.format(amount)+fromCode+" = "+f.format(finalAmount)+toCode);
			
		}else {
			System.out.println("GET request failed");
		}
		//String url_str = "https://api.exchangerate.host/convert?from=USD&to=EUR";

		
		
	}


}

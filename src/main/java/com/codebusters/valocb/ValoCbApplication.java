package com.codebusters.valocb;

import com.codebusters.valocb.model.*;
import com.codebusters.valocb.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ValoCbApplication {

	@Autowired
	private FileService fileService;

	public static void main(String[] args) {
		SpringApplication.run(ValoCbApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void initData() throws IOException {
		List<String[]> productRows = fileService.getRowsFromFileName("Product.csv", 5);
		List<String[]> pricesRows = fileService.getRowsFromFileName("Prices.csv", 4);
		List<String[]> forexRows = fileService.getRowsFromFileName("Forex.csv", 5);
		Map<String, Product> products = new HashMap<>();
		Map<String, Client> clients = new HashMap<>();
		Map<String, Portfolio> portfolios = new HashMap<>();

		Map<String, Forex> forexs = new HashMap<>();


		forexRows.forEach(forexRow -> {
			String sourceCurrency = forexRow[0].trim();
			String targetCurrency = forexRow[1].trim();
			Float exchangeRate = Float.valueOf(forexRow[2].replace("\"", "").trim());

			Forex forex = new Forex(sourceCurrency, targetCurrency, exchangeRate);
			forexs.putIfAbsent(forex.getKey(), forex);
			// forex.putIfAbsent(sourceCurrency, exchangeRate);
		});


		productRows.forEach(productRow -> {
			String productName = productRow[0];
			Product product = new Product(productName);
			String clientName = productRow[1];
			Client client = new Client(clientName);
			int quantity = Integer.parseInt(productRow[2].trim());

			clients.putIfAbsent(clientName, client);
			products.putIfAbsent(productName, product);
			Product targetProduct = products.get(productName);
			clients.get(client.getName()).addOwnedProduct(targetProduct, quantity);
		});

		pricesRows.forEach(pricesRow -> {
			String portfolioName = pricesRow[0];
			String productName = pricesRow[1];
			String currency = pricesRow[3];
			int priceAmount = Integer.parseInt(pricesRow[4].trim());
			Portfolio portfolio = new Portfolio(portfolioName);
			// TODO: find forex

			Price underlyingPrice = new Price(priceAmount, currency, forexs.get(currency));
			Underlying underlying = new Underlying(pricesRow[2], underlyingPrice);

			portfolios.putIfAbsent(portfolioName, portfolio);
			Product targetProduct = products.get(productName);
			targetProduct.addUnderlying(underlying);
			portfolios.get(portfolioName).addProduct(targetProduct);
		});

	}

}

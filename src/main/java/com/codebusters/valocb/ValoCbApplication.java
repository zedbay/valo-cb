package com.codebusters.valocb;

import com.codebusters.valocb.model.*;
import com.codebusters.valocb.service.CacheService;
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

    @Autowired
    private CacheService cacheService;

    public static void main(String[] args) {
        SpringApplication.run(ValoCbApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initData() throws IOException {

		fileService.getRowsFromFileName("Forex.csv", 5).forEach(forexRow -> {
            String sourceCurrency = forexRow[0].trim();
            String targetCurrency = forexRow[1].trim();
            String exchangeRateAsString = forexRow[2]
                    .replace(",", ".")
                    .replace("\"", "")
                    .trim();
            float exchangeRate = Float.parseFloat(exchangeRateAsString);
            Forex forex = new Forex(sourceCurrency, targetCurrency, exchangeRate);

            cacheService.cacheForex(forex);
        });

		fileService.getRowsFromFileName("Product.csv", 5).forEach(productRow -> {
            String productName = productRow[0];
            String clientName = productRow[1];
            float quantity = Float.parseFloat(productRow[2].trim());
            Product product = new Product(productName);
            Client client = new Client(clientName);

            cacheService.cacheClient(client);
            cacheService.cacheProduct(product);
            Product targetProduct = cacheService.getCachedProduct(productName);
            cacheService.getCachedClient(clientName).addOwnedProduct(targetProduct, quantity);
        });

		fileService.getRowsFromFileName("Prices.csv", 4).forEach(pricesRow -> {
            String portfolioName = pricesRow[0];
            String productName = pricesRow[1];
            String currency = pricesRow[3];
            String underlyingName = pricesRow[2];
            int priceAmount = Integer.parseInt(pricesRow[4].trim());
            Portfolio portfolio = new Portfolio(portfolioName);
            Price underlyingPrice = new Price(priceAmount, currency, cacheService.getCachedForex(currency));
            Underlying underlying = new Underlying(underlyingName, underlyingPrice);

            cacheService.cachePortfolio(portfolio);
            Product targetProduct = cacheService.getCachedProduct(productName);
            targetProduct.addUnderlying(underlying);
            cacheService.getPortfolio(portfolioName).addProduct(targetProduct);
        });

        fileService.writeToCsv(
                cacheService.getCachedPortfolio().stream().toList(),
                (Portfolio portfolio) -> portfolio.getName() + "," + portfolio.getPrice(),
                "PTF,price",
                "Reporting-portfolio.csv"
        );

        fileService.writeToCsv(
                cacheService.getCachedClients().stream().toList(),
                (Client client) -> client.getName() + "," + client.getCapital(),
                "Client,capital",
                "Reporting-client.csv"
        );

    }

}

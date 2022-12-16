package com.codebusters.valocb.service;

import com.codebusters.valocb.model.Client;
import com.codebusters.valocb.model.Forex;
import com.codebusters.valocb.model.Portfolio;
import com.codebusters.valocb.model.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class CacheServiceImpl implements CacheService {

    private final Map<String, Product> products = new HashMap<>();

    private final Map<String, Client> clients = new HashMap<>();

    private final Map<String, Portfolio> portfolios = new HashMap<>();

    private final Map<String, Forex> forexs = new HashMap<>();

    @Override
    public void cacheProduct(Product product) {
        products.putIfAbsent(product.getName(), product);
    }

    @Override
    public Product getCachedProduct(String productName) {
        return products.get(productName);
    }

    @Override
    public void cacheForex(Forex forex) {
        forexs.putIfAbsent(forex.getKey(), forex);
    }

    @Override
    public Forex getCachedForex(String forexName) {
        return forexs.get(forexName);
    }

    @Override
    public void cacheClient(Client client) {
        clients.putIfAbsent(client.getName(), client);
    }

    @Override
    public Client getCachedClient(String forexName) {
        return clients.get(forexName);
    }

    @Override
    public Set<Client> getCachedClients() {
        return new HashSet<>(clients.values());
    }

    @Override
    public void cachePortfolio(Portfolio portfolio) {
        portfolios.putIfAbsent(portfolio.getName(), portfolio);
    }

    @Override
    public Portfolio getPortfolio(String portfolioName) {
        return portfolios.get(portfolioName);
    }

    @Override
    public Set<Portfolio> getCachedPortfolio() {
        return new HashSet<>(portfolios.values());
    }
}

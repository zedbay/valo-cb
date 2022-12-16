package com.codebusters.valocb.service;

import com.codebusters.valocb.model.Client;
import com.codebusters.valocb.model.Forex;
import com.codebusters.valocb.model.Portfolio;
import com.codebusters.valocb.model.Product;

import java.util.Set;

public interface CacheService {

    void cacheProduct(Product product);

    Product getCachedProduct(String productName);

    void cacheForex(Forex forex);

    Forex getCachedForex(String forexName);

    void cacheClient(Client client);

    Client getCachedClient(String forexName);

    Set<Client> getCachedClients();

    void cachePortfolio(Portfolio portfolio);

    Portfolio getPortfolio(String portfolioName);

    Set<Portfolio> getCachedPortfolio();

}

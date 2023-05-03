package com.example.exchangeservice.controler;

import com.example.exchangeservice.model.CurrencyExchange;
import com.example.exchangeservice.repositories.CurrencyExchangeRepository;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeControler {
    private final CurrencyExchangeRepository currencyExchangeRepository;
    private final Environment environment;

    public CurrencyExchangeControler(CurrencyExchangeRepository currencyExchangeRepository, Environment environment) {
        this.currencyExchangeRepository = currencyExchangeRepository;
        this.environment = environment;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyExchange getCurrencyExchange(@PathVariable String from, @PathVariable String to) {
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchange;
    }
}

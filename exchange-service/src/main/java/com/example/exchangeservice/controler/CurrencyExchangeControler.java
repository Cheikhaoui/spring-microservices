package com.example.exchangeservice.controler;

import com.example.exchangeservice.model.CurrencyExchange;
import com.example.exchangeservice.repositories.CurrencyExchangeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
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
        String port = environment.getProperty("local.server.port");
        String host = environment.getProperty("HOSTNAME");
        String version ="v11";
        currencyExchange.setEnvironment(port+" "+version + " "+host);
        return currencyExchange;
    }
}

package com.example.currencyconversionservice.controler;

import com.example.currencyconversionservice.model.CurrencyConversion;
import com.example.currencyconversionservice.service.CurencyExchangeProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeControler {

    private final CurencyExchangeProxy curencyExchangeProxy;

    public CurrencyExchangeControler(CurencyExchangeProxy curencyExchangeProxy) {
        this.curencyExchangeProxy = curencyExchangeProxy;
    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity")
    CurrencyConversion getCurrencyConversion(@PathVariable String from , @PathVariable String to ,
                                             @RequestParam("quantity")BigDecimal quantity){
        CurrencyConversion currencyConversion = curencyExchangeProxy.getExchangeValue(from,to);
        return CurrencyConversion.builder().from(currencyConversion.getFrom()).to(currencyConversion.getTo())
                .environment(currencyConversion.getEnvironment()).conversionMultiple(currencyConversion.getConversionMultiple())
                .id(currencyConversion.getId()).quantity(quantity).totalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()))
                .build();
    }


}

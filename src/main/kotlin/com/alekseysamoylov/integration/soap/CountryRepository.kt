package com.alekseysamoylov.integration.soap

import org.springframework.stereotype.Component
import java.util.HashMap
import javax.annotation.PostConstruct


@Component
class CountryRepository {

  @PostConstruct
  fun initData() {
    val spain = Country()
    spain.setName("Spain")
    spain.setCapital("Madrid")
    spain.setCurrency(Currency.EUR)
    spain.setPopulation(46704314)

    countries.put(spain.getName(), spain)

    val poland = Country()
    poland.setName("Poland")
    poland.setCapital("Warsaw")
    poland.setCurrency(Currency.PLN)
    poland.setPopulation(38186860)

    countries.put(poland.getName(), poland)

    val uk = Country()
    uk.setName("United Kingdom")
    uk.setCapital("London")
    uk.setCurrency(Currency.GBP)
    uk.setPopulation(63705000)

    countries.put(uk.getName(), uk)
  }

  fun findCountry(name: String): Country? {
    return countries.get(name)
  }
}

private val countries = HashMap<String, Country>()

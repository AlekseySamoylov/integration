package com.alekseysamoylov.integration.soap

import org.springframework.ws.server.endpoint.annotation.Endpoint
import org.springframework.ws.server.endpoint.annotation.PayloadRoot
import org.springframework.ws.server.endpoint.annotation.RequestPayload
import org.springframework.ws.server.endpoint.annotation.ResponsePayload


@Endpoint
class CountryEndpoint(private val countryRepository: CountryRepository) {

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
  @ResponsePayload
  fun getCountry(@RequestPayload request: GetCountryRequest): GetCountryResponse {
    val response = GetCountryResponse()
    response.setCountry(countryRepository.findCountry(request.name))

    return response
  }

}

const val NAMESPACE_URI = "http://alekseysamoylov.com/countries"

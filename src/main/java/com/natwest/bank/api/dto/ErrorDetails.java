package com.natwest.bank.api.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Error")
public record ErrorDetails(int statusCode, String message) {

}

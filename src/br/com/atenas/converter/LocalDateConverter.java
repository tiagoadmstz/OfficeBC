/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atenas.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Tiago
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<String, LocalDate> {

    @Override
    public LocalDate convertToDatabaseColumn(String attribute) {
        return !Objects.equals("", attribute) || !Objects.equals(null, attribute) ? LocalDate.parse(attribute, DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null;
    }

    @Override
    public String convertToEntityAttribute(LocalDate dbData) {
        return !Objects.equals(null, dbData) ? dbData.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null;
    }

}

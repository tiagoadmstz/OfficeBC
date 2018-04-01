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
public class LocalDateConverter implements AttributeConverter<LocalDate, String> {

    @Override
    public String convertToDatabaseColumn(LocalDate attributte) {
        return !Objects.equals(null, attributte) ? attributte.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
    }

    @Override
    public LocalDate convertToEntityAttribute(String dbData) {
        return !Objects.equals("", dbData) && !Objects.equals(null, dbData) ? LocalDate.parse(dbData, DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null;
    }

}

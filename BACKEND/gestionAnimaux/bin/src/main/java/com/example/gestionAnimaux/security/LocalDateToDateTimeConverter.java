package com.example.gestionAnimaux.security;
import java.sql.Timestamp;
import java.time.LocalDate;


@jakarta.persistence.Converter(autoApply = true)
public class LocalDateToDateTimeConverter implements jakarta.persistence.AttributeConverter<LocalDate, Timestamp> {
    
    @Override
    public Timestamp convertToDatabaseColumn(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return Timestamp.valueOf(localDate.atStartOfDay());
    }

    @Override
    public LocalDate convertToEntityAttribute(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate();
    }
}
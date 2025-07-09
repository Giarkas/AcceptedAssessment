package com.example.AcceptedAssessment.converters;

import com.example.AcceptedAssessment.models.SportType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SportTypeConverter implements AttributeConverter<SportType, Integer> {


    @Override
    public Integer convertToDatabaseColumn(SportType sportType) {
        if (sportType == null) {
            return null;
        }

        return sportType.getValue();
    }


    @Override
    public SportType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        return SportType.fromId(dbData);
    }
}
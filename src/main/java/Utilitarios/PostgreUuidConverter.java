/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import java.util.UUID;
import javax.persistence.AttributeConverter;

/**
 *
 * @author luizf
 */
@javax.persistence.Converter(autoApply = true)
public class PostgreUuidConverter implements AttributeConverter<UUID, UUID> {

    @Override
    public UUID convertToDatabaseColumn(UUID x) {
        return x;
    }

    @Override
    public UUID convertToEntityAttribute(UUID y) {
        return y;
    }
}

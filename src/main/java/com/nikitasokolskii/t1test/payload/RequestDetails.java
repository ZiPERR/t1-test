package com.nikitasokolskii.t1test.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nikita Sokolskii
 */
@Data
@NoArgsConstructor
public class RequestDetails {
    @NotNull(message = "Input must not be null")
    private String input;
}

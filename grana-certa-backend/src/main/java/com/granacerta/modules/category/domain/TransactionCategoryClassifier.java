package com.granacerta.modules.category.domain;


import com.granacerta.modules.category.domain.enums.SystemCategoryCode;

import java.text.Normalizer;
import java.util.Locale;
import java.util.Optional;

public class TransactionCategoryClassifier {

    public Optional<SystemCategoryCode> classify(String description) {
        if (description == null || description.isBlank()) {
            return Optional.empty();
        }

        String normalized = normalize(description);


        if (normalized.contains("salario")) {
            return Optional.of(SystemCategoryCode.SALARY);
        }


        if (normalized.contains("condominio")) {
            return Optional.of(SystemCategoryCode.HOUSING);
        }


        if (normalized.contains("vivo servicos")
                || normalized.contains("vivo servico")
                || normalized.contains("vivo")) {
            return Optional.of(
                    SystemCategoryCode.TELECOMMUNICATIONS
            );
        }


        if (normalized.contains("eletrobras")) {
            return Optional.of(SystemCategoryCode.UTILITIES);
        }

        if (normalized.contains("SPOTIFY AB") || normalized.contains("NETFLIX.COM")) {
            return Optional.of(
                    SystemCategoryCode.DIGITAL_SUBSCRIPTIONS
            );
        }






        return Optional.empty();
    }

    private String normalize(String value) {
        return Normalizer.normalize(
                        value,
                        Normalizer.Form.NFD
                )
                .replaceAll("\\p{M}", "")
                .toLowerCase(Locale.ROOT)
                .trim();
    }
}
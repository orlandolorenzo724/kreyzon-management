package com.oldigitalsolutions.management.repository.specification;

import com.oldigitalsolutions.management.model.Prospect;
import com.oldigitalsolutions.management.utils.SearchCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ProspectSpecification implements Specification<Prospect> {

    private SearchCriteria searchCriteria;

    @Override
    public Predicate toPredicate(Root<Prospect> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (searchCriteria.getOperation().equalsIgnoreCase(">")) {
            if (root.get(searchCriteria.getKey()).getJavaType() == LocalDateTime.class) {
                LocalDateTime before = LocalDateTime.parse(searchCriteria.getValue().concat(" 00:00:00"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                return criteriaBuilder.greaterThanOrEqualTo(root.get(searchCriteria.getKey()), before);
            }

            return criteriaBuilder.greaterThanOrEqualTo(
                    root.<String> get(searchCriteria.getKey()), searchCriteria.getValue().toString());
        }

        else if (searchCriteria.getOperation().equalsIgnoreCase(">=")) {
            if (root.get(searchCriteria.getKey()).getJavaType() == LocalDateTime.class) {
                LocalDateTime before = LocalDateTime.parse(searchCriteria.getValue().concat(" 00:00:00"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                return criteriaBuilder.greaterThanOrEqualTo(root.get(searchCriteria.getKey()), before);
            }

            return criteriaBuilder.greaterThanOrEqualTo(
                    root.<String> get(searchCriteria.getKey()), searchCriteria.getValue().toString());
        }

        else if (searchCriteria.getOperation().equalsIgnoreCase("<")) {
            if (root.get(searchCriteria.getKey()).getJavaType() == LocalDateTime.class) {
                LocalDateTime after = LocalDateTime.parse(searchCriteria.getValue().concat(" 00:00:00"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                return criteriaBuilder.lessThanOrEqualTo(root.get(searchCriteria.getKey()), after);
            }

            return criteriaBuilder.lessThanOrEqualTo(
                    root.<String> get(searchCriteria.getKey()), searchCriteria.getValue().toString());
        }

        else if (searchCriteria.getOperation().equalsIgnoreCase("<=")) {
            if (root.get(searchCriteria.getKey()).getJavaType() == LocalDateTime.class) {
                LocalDateTime after = LocalDateTime.parse(searchCriteria.getValue().concat(" 00:00:00"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                return criteriaBuilder.lessThanOrEqualTo(root.get(searchCriteria.getKey()), after);
            }

            return criteriaBuilder.lessThanOrEqualTo(
                    root.<String> get(searchCriteria.getKey()), searchCriteria.getValue().toString());
        }

        else if (searchCriteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(searchCriteria.getKey()).getJavaType() == String.class) {
                return criteriaBuilder.like(
                        root.<String>get(searchCriteria.getKey()), "%" + searchCriteria.getValue() + "%");
            } else {
                return criteriaBuilder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue());
            }
        }


        return null;
    }
}


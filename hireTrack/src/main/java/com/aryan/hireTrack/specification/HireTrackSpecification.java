package com.aryan.hireTrack.specification;

import com.aryan.hireTrack.entity.HireTrack;
import com.aryan.hireTrack.entity.Status;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HireTrackSpecification {

    public static Specification<HireTrack> withFilters(Long userId, String applicationStatus, LocalDate dateFrom,
            LocalDate dateTo, String companyName, String jobRole, String jobType, String companyCountry, String companyState,
            String companyCity, Double minSalary, Double maxSalary, String currency) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            // always scope results to the logged-in user
            predicates.add(cb.equal(root.get("user").get("id"), userId));

            // applicationStatus is a Status enum -> parse the incoming string
            if (applicationStatus != null && !applicationStatus.isBlank()) {
                predicates.add(cb.equal(root.get("applicationStatus"), Status.valueOf(applicationStatus)));
            }

            // company fields: Company is a @OneToOne on HireTrack, field name "company"
            if (companyName != null && !companyName.isBlank()) {
                predicates.add(cb.like(
                        cb.lower(root.get("company").get("companyName")),
                        "%" + companyName.toLowerCase() + "%"
                ));
            }

            if (companyCountry != null && !companyCountry.isBlank()) {
                predicates.add(cb.like(
                        cb.lower(root.get("company").get("country")),
                        "%" + companyCountry.toLowerCase() + "%"
                ));
            }

            if (companyState != null && !companyState.isBlank()) {
                predicates.add(cb.like(
                        cb.lower(root.get("company").get("state")),
                        "%" + companyState.toLowerCase() + "%"
                ));
            }

            if (companyCity != null && !companyCity.isBlank()) {
                predicates.add(cb.like(
                        cb.lower(root.get("company").get("city")),
                        "%" + companyCity.toLowerCase() + "%"
                ));
            }

            // jobRole is a free-text String -> partial match makes sense
            if (jobRole != null && !jobRole.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("jobRole")), "%" + jobRole.toLowerCase() + "%"));
            }

            // jobType is a fixed dropdown String on the frontend -> exact match
            if (jobType != null && !jobType.isBlank()) {
                predicates.add(cb.equal(root.get("jobType"), jobType));
            }

            // minSalary/maxSalary are Double on HireTrack
            if (minSalary != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("minSalary"), minSalary));
            }

            if (maxSalary != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("maxSalary"), maxSalary));
            }

            if (currency != null && !currency.isBlank()) {
                predicates.add(cb.equal(root.get("currency"), currency));
            }

            // importantDates is a @OneToMany (HireTrack -> List<ImportantDate>), needs a join
            if (dateFrom != null || dateTo != null) {
                var importantDateJoin = root.join("importantDates", JoinType.INNER);

                if (dateFrom != null) {
                    predicates.add(cb.greaterThanOrEqualTo(importantDateJoin.get("eventDate"), dateFrom));
                }
                if (dateTo != null) {
                    predicates.add(cb.lessThanOrEqualTo(importantDateJoin.get("eventDate"), dateTo));
                }

                // joining a OneToMany can produce duplicate HireTrack rows if multiple
                // ImportantDate rows match -> distinct prevents that
                Objects.requireNonNull(query).distinct(true);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
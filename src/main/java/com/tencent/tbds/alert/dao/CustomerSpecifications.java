package com.tencent.tbds.alert.dao;

import com.tencent.tbds.alert.domain.Alert;
import com.tencent.tbds.alert.domain.AlertQueryCriteria;
import com.tencent.tbds.alert.domain.AlertTrigger;
import com.tencent.tbds.alert.domain.AlertTriggerQueryCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.awt.print.Pageable;
import java.sql.Timestamp;

/**
 * Created by jerryjzhang on 2016/3/22.
 */
public class CustomerSpecifications {
    public static Specification<AlertTrigger> triggerOccursInTimeWindow(final Timestamp startTime) {
        return new Specification<AlertTrigger>() {
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                return cb.greaterThanOrEqualTo(root.get("time").as(Timestamp.class), startTime);
            }
        };
    }

    public static Specification<AlertTrigger> AlertTriggerQuery(final AlertTriggerQueryCriteria criteria) {
        return new Specification<AlertTrigger>() {
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                Predicate predicate = cb.isNotNull(root.get("id").as(String.class));
                if(criteria.getAppId() != null){
                    predicate = cb.and(predicate, cb.equal(root.get("appId").as(String.class), criteria.getAppId()));
                }

                if(criteria.getMetricName() != null){
                    predicate = cb.and(predicate, cb.equal(root.get("metricName").as(String.class), criteria.getMetricName()));
                }

                if(criteria.isRead() != null) {
                    predicate = cb.and(predicate, cb.equal(root.get("read").as(Boolean.class), Boolean.valueOf(criteria.isRead())));
                }

                if(criteria.getStartTime() != null && criteria.getEndTime() != null){
                    Timestamp startTime = new Timestamp(criteria.getStartTime());
                    Timestamp endTime = new Timestamp(criteria.getEndTime());
                    predicate = cb.and(predicate, cb.between(root.get("time").as(Timestamp.class), startTime, endTime));
                }

                if(criteria.getKeyword() != null){
                    Expression expression = cb.lower(cb.concat(root.get("appId").as(String.class), root.get("metricName").as(String.class)));
                    predicate = cb.and(predicate, cb.like(expression, "%" + criteria.getKeyword() + "%"));
                }

                return predicate;
            }
        };
    }

    public static Specification<Alert> AlertQuery(final AlertQueryCriteria criteria){
        return new Specification<Alert>() {
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                Predicate predicate = cb.isNotNull(root.get("id").as(String.class));

                if(criteria.getAppId() != null){
                    predicate = cb.and(predicate, cb.equal(root.get("condition").get("appId").as(String.class), criteria.getAppId()));
                }

                if(criteria.getMetricName() != null){
                    predicate = cb.and(predicate, cb.equal(root.get("condition").get("metricName").as(String.class), criteria.getMetricName()));
                }

                if(criteria.getStartTime() != null && criteria.getEndTime() != null){
                    Timestamp startTime = new Timestamp(criteria.getStartTime());
                    Timestamp endTime = new Timestamp(criteria.getEndTime());
                    predicate = cb.and(predicate, cb.between(root.get("lastUpdateTime").as(Timestamp.class), startTime, endTime));
                }

                if(criteria.getKeyword() != null){
                    Expression expression = cb.lower(cb.concat(root.get("name").as(String.class),
                            root.get("condition").get("metricName").as(String.class)));
                    predicate = cb.and(predicate, cb.like(expression, "%" + criteria.getKeyword() + "%"));
                }

                return predicate;
            }
        };
    }
}

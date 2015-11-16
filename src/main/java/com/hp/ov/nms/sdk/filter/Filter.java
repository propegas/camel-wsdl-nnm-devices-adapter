package com.hp.ov.nms.sdk.filter;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

/**
 * Base Filter class for SDK.
 *
 * @author Rocky
 *
 */
@XmlType(namespace="http://filter.sdk.nms.ov.hp.com/")
public class Filter implements Serializable {
    private Condition condition=null;
    private Constraint constraint=null;
    private Expression expression=null;

    public Condition getCondition() {
        return condition;
    }
    public void setCondition(Condition condition) {
        this.condition = condition;
    }
    public Constraint getConstraint() {
        return constraint;
    }
    public void setConstraint(Constraint constraint) {
        this.constraint = constraint;
    }
    public Expression getExpression() {
        return expression;
    }
    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}

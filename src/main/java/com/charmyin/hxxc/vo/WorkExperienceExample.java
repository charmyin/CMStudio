package com.charmyin.hxxc.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.charmyin.cmstudio.basic.pagination.annotation.Paging;
import com.charmyin.cmstudio.basic.pagination.interceptor.IViewObjectExample;
import com.charmyin.cmstudio.basic.pagination.page.PageBase;
import com.charmyin.hxxc.vo.EmployerInfoExample.Criteria;

@Paging(field="pageVO")
public class WorkExperienceExample extends PageBase implements IViewObjectExample{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
    
    /**** New added begin ****/
    public List<Object> getParameters() {
    	List<Object> params = new ArrayList<Object>();
		for(Criteria cr : oredCriteria){
			for(Criterion crti : cr.getAllCriteria()){
				if(crti.getValue()!=null){
					params.add(crti.getValue());
				}
				if(crti.getSecondValue()!=null){
					params.add(crti.getSecondValue());
				}
				
			}
		}
		return params;
	}
    /**** New added end ****/

    public WorkExperienceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndNameIsNull() {
            addCriterion("end_name is null");
            return (Criteria) this;
        }

        public Criteria andEndNameIsNotNull() {
            addCriterion("end_name is not null");
            return (Criteria) this;
        }

        public Criteria andEndNameEqualTo(Date value) {
            addCriterion("end_name =", value, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameNotEqualTo(Date value) {
            addCriterion("end_name <>", value, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameGreaterThan(Date value) {
            addCriterion("end_name >", value, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameGreaterThanOrEqualTo(Date value) {
            addCriterion("end_name >=", value, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameLessThan(Date value) {
            addCriterion("end_name <", value, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameLessThanOrEqualTo(Date value) {
            addCriterion("end_name <=", value, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameIn(List<Date> values) {
            addCriterion("end_name in", values, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameNotIn(List<Date> values) {
            addCriterion("end_name not in", values, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameBetween(Date value1, Date value2) {
            addCriterion("end_name between", value1, value2, "endName");
            return (Criteria) this;
        }

        public Criteria andEndNameNotBetween(Date value1, Date value2) {
            addCriterion("end_name not between", value1, value2, "endName");
            return (Criteria) this;
        }

        public Criteria andWorkDescIsNull() {
            addCriterion("work_desc is null");
            return (Criteria) this;
        }

        public Criteria andWorkDescIsNotNull() {
            addCriterion("work_desc is not null");
            return (Criteria) this;
        }

        public Criteria andWorkDescEqualTo(String value) {
            addCriterion("work_desc =", value, "workDesc");
            return (Criteria) this;
        }

        public Criteria andWorkDescNotEqualTo(String value) {
            addCriterion("work_desc <>", value, "workDesc");
            return (Criteria) this;
        }

        public Criteria andWorkDescGreaterThan(String value) {
            addCriterion("work_desc >", value, "workDesc");
            return (Criteria) this;
        }

        public Criteria andWorkDescGreaterThanOrEqualTo(String value) {
            addCriterion("work_desc >=", value, "workDesc");
            return (Criteria) this;
        }

        public Criteria andWorkDescLessThan(String value) {
            addCriterion("work_desc <", value, "workDesc");
            return (Criteria) this;
        }

        public Criteria andWorkDescLessThanOrEqualTo(String value) {
            addCriterion("work_desc <=", value, "workDesc");
            return (Criteria) this;
        }

        public Criteria andWorkDescLike(String value) {
            addCriterion("work_desc like", value, "workDesc");
            return (Criteria) this;
        }

        public Criteria andWorkDescNotLike(String value) {
            addCriterion("work_desc not like", value, "workDesc");
            return (Criteria) this;
        }

        public Criteria andWorkDescIn(List<String> values) {
            addCriterion("work_desc in", values, "workDesc");
            return (Criteria) this;
        }

        public Criteria andWorkDescNotIn(List<String> values) {
            addCriterion("work_desc not in", values, "workDesc");
            return (Criteria) this;
        }

        public Criteria andWorkDescBetween(String value1, String value2) {
            addCriterion("work_desc between", value1, value2, "workDesc");
            return (Criteria) this;
        }

        public Criteria andWorkDescNotBetween(String value1, String value2) {
            addCriterion("work_desc not between", value1, value2, "workDesc");
            return (Criteria) this;
        }

        public Criteria andWorkDutyIsNull() {
            addCriterion("work_duty is null");
            return (Criteria) this;
        }

        public Criteria andWorkDutyIsNotNull() {
            addCriterion("work_duty is not null");
            return (Criteria) this;
        }

        public Criteria andWorkDutyEqualTo(String value) {
            addCriterion("work_duty =", value, "workDuty");
            return (Criteria) this;
        }

        public Criteria andWorkDutyNotEqualTo(String value) {
            addCriterion("work_duty <>", value, "workDuty");
            return (Criteria) this;
        }

        public Criteria andWorkDutyGreaterThan(String value) {
            addCriterion("work_duty >", value, "workDuty");
            return (Criteria) this;
        }

        public Criteria andWorkDutyGreaterThanOrEqualTo(String value) {
            addCriterion("work_duty >=", value, "workDuty");
            return (Criteria) this;
        }

        public Criteria andWorkDutyLessThan(String value) {
            addCriterion("work_duty <", value, "workDuty");
            return (Criteria) this;
        }

        public Criteria andWorkDutyLessThanOrEqualTo(String value) {
            addCriterion("work_duty <=", value, "workDuty");
            return (Criteria) this;
        }

        public Criteria andWorkDutyLike(String value) {
            addCriterion("work_duty like", value, "workDuty");
            return (Criteria) this;
        }

        public Criteria andWorkDutyNotLike(String value) {
            addCriterion("work_duty not like", value, "workDuty");
            return (Criteria) this;
        }

        public Criteria andWorkDutyIn(List<String> values) {
            addCriterion("work_duty in", values, "workDuty");
            return (Criteria) this;
        }

        public Criteria andWorkDutyNotIn(List<String> values) {
            addCriterion("work_duty not in", values, "workDuty");
            return (Criteria) this;
        }

        public Criteria andWorkDutyBetween(String value1, String value2) {
            addCriterion("work_duty between", value1, value2, "workDuty");
            return (Criteria) this;
        }

        public Criteria andWorkDutyNotBetween(String value1, String value2) {
            addCriterion("work_duty not between", value1, value2, "workDuty");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
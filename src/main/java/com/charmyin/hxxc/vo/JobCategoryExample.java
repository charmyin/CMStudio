package com.charmyin.hxxc.vo;

import java.util.ArrayList;
import java.util.List;

import com.charmyin.cmstudio.basic.pagination.annotation.Paging;
import com.charmyin.cmstudio.basic.pagination.interceptor.IViewObjectExample;
import com.charmyin.cmstudio.basic.pagination.page.PageBase;
import com.charmyin.hxxc.vo.EmployerInfoExample.Criteria;

@Paging(field="pageVO")
public class JobCategoryExample extends PageBase implements IViewObjectExample{
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

    public JobCategoryExample() {
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

        public Criteria andJobCategoryNameIsNull() {
            addCriterion("job_category_name is null");
            return (Criteria) this;
        }

        public Criteria andJobCategoryNameIsNotNull() {
            addCriterion("job_category_name is not null");
            return (Criteria) this;
        }

        public Criteria andJobCategoryNameEqualTo(String value) {
            addCriterion("job_category_name =", value, "jobCategoryName");
            return (Criteria) this;
        }

        public Criteria andJobCategoryNameNotEqualTo(String value) {
            addCriterion("job_category_name <>", value, "jobCategoryName");
            return (Criteria) this;
        }

        public Criteria andJobCategoryNameGreaterThan(String value) {
            addCriterion("job_category_name >", value, "jobCategoryName");
            return (Criteria) this;
        }

        public Criteria andJobCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("job_category_name >=", value, "jobCategoryName");
            return (Criteria) this;
        }

        public Criteria andJobCategoryNameLessThan(String value) {
            addCriterion("job_category_name <", value, "jobCategoryName");
            return (Criteria) this;
        }

        public Criteria andJobCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("job_category_name <=", value, "jobCategoryName");
            return (Criteria) this;
        }

        public Criteria andJobCategoryNameLike(String value) {
            addCriterion("job_category_name like", value, "jobCategoryName");
            return (Criteria) this;
        }

        public Criteria andJobCategoryNameNotLike(String value) {
            addCriterion("job_category_name not like", value, "jobCategoryName");
            return (Criteria) this;
        }

        public Criteria andJobCategoryNameIn(List<String> values) {
            addCriterion("job_category_name in", values, "jobCategoryName");
            return (Criteria) this;
        }

        public Criteria andJobCategoryNameNotIn(List<String> values) {
            addCriterion("job_category_name not in", values, "jobCategoryName");
            return (Criteria) this;
        }

        public Criteria andJobCategoryNameBetween(String value1, String value2) {
            addCriterion("job_category_name between", value1, value2, "jobCategoryName");
            return (Criteria) this;
        }

        public Criteria andJobCategoryNameNotBetween(String value1, String value2) {
            addCriterion("job_category_name not between", value1, value2, "jobCategoryName");
            return (Criteria) this;
        }

        public Criteria andJobCategoryIdIsNull() {
            addCriterion("job_category_id is null");
            return (Criteria) this;
        }

        public Criteria andJobCategoryIdIsNotNull() {
            addCriterion("job_category_id is not null");
            return (Criteria) this;
        }

        public Criteria andJobCategoryIdEqualTo(String value) {
            addCriterion("job_category_id =", value, "jobCategoryId");
            return (Criteria) this;
        }

        public Criteria andJobCategoryIdNotEqualTo(String value) {
            addCriterion("job_category_id <>", value, "jobCategoryId");
            return (Criteria) this;
        }

        public Criteria andJobCategoryIdGreaterThan(String value) {
            addCriterion("job_category_id >", value, "jobCategoryId");
            return (Criteria) this;
        }

        public Criteria andJobCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("job_category_id >=", value, "jobCategoryId");
            return (Criteria) this;
        }

        public Criteria andJobCategoryIdLessThan(String value) {
            addCriterion("job_category_id <", value, "jobCategoryId");
            return (Criteria) this;
        }

        public Criteria andJobCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("job_category_id <=", value, "jobCategoryId");
            return (Criteria) this;
        }

        public Criteria andJobCategoryIdLike(String value) {
            addCriterion("job_category_id like", value, "jobCategoryId");
            return (Criteria) this;
        }

        public Criteria andJobCategoryIdNotLike(String value) {
            addCriterion("job_category_id not like", value, "jobCategoryId");
            return (Criteria) this;
        }

        public Criteria andJobCategoryIdIn(List<String> values) {
            addCriterion("job_category_id in", values, "jobCategoryId");
            return (Criteria) this;
        }

        public Criteria andJobCategoryIdNotIn(List<String> values) {
            addCriterion("job_category_id not in", values, "jobCategoryId");
            return (Criteria) this;
        }

        public Criteria andJobCategoryIdBetween(String value1, String value2) {
            addCriterion("job_category_id between", value1, value2, "jobCategoryId");
            return (Criteria) this;
        }

        public Criteria andJobCategoryIdNotBetween(String value1, String value2) {
            addCriterion("job_category_id not between", value1, value2, "jobCategoryId");
            return (Criteria) this;
        }

        public Criteria andJnameIsNull() {
            addCriterion("jname is null");
            return (Criteria) this;
        }

        public Criteria andJnameIsNotNull() {
            addCriterion("jname is not null");
            return (Criteria) this;
        }

        public Criteria andJnameEqualTo(String value) {
            addCriterion("jname =", value, "jname");
            return (Criteria) this;
        }

        public Criteria andJnameNotEqualTo(String value) {
            addCriterion("jname <>", value, "jname");
            return (Criteria) this;
        }

        public Criteria andJnameGreaterThan(String value) {
            addCriterion("jname >", value, "jname");
            return (Criteria) this;
        }

        public Criteria andJnameGreaterThanOrEqualTo(String value) {
            addCriterion("jname >=", value, "jname");
            return (Criteria) this;
        }

        public Criteria andJnameLessThan(String value) {
            addCriterion("jname <", value, "jname");
            return (Criteria) this;
        }

        public Criteria andJnameLessThanOrEqualTo(String value) {
            addCriterion("jname <=", value, "jname");
            return (Criteria) this;
        }

        public Criteria andJnameLike(String value) {
            addCriterion("jname like", value, "jname");
            return (Criteria) this;
        }

        public Criteria andJnameNotLike(String value) {
            addCriterion("jname not like", value, "jname");
            return (Criteria) this;
        }

        public Criteria andJnameIn(List<String> values) {
            addCriterion("jname in", values, "jname");
            return (Criteria) this;
        }

        public Criteria andJnameNotIn(List<String> values) {
            addCriterion("jname not in", values, "jname");
            return (Criteria) this;
        }

        public Criteria andJnameBetween(String value1, String value2) {
            addCriterion("jname between", value1, value2, "jname");
            return (Criteria) this;
        }

        public Criteria andJnameNotBetween(String value1, String value2) {
            addCriterion("jname not between", value1, value2, "jname");
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
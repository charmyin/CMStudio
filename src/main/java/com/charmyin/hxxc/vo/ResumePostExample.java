package com.charmyin.hxxc.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.charmyin.cmstudio.basic.pagination.annotation.Paging;
import com.charmyin.cmstudio.basic.pagination.interceptor.IViewObjectExample;
import com.charmyin.cmstudio.basic.pagination.page.PageBase;
import com.charmyin.hxxc.vo.EmployerInfoExample.Criteria;

@Paging(field="pageVO")
public class ResumePostExample extends PageBase implements IViewObjectExample{
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
    
    public ResumePostExample() {
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

        public Criteria andJoinTimeIsNull() {
            addCriterion("join_time is null");
            return (Criteria) this;
        }

        public Criteria andJoinTimeIsNotNull() {
            addCriterion("join_time is not null");
            return (Criteria) this;
        }

        public Criteria andJoinTimeEqualTo(Date value) {
            addCriterion("join_time =", value, "joinTime");
            return (Criteria) this;
        }

        public Criteria andJoinTimeNotEqualTo(Date value) {
            addCriterion("join_time <>", value, "joinTime");
            return (Criteria) this;
        }

        public Criteria andJoinTimeGreaterThan(Date value) {
            addCriterion("join_time >", value, "joinTime");
            return (Criteria) this;
        }

        public Criteria andJoinTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("join_time >=", value, "joinTime");
            return (Criteria) this;
        }

        public Criteria andJoinTimeLessThan(Date value) {
            addCriterion("join_time <", value, "joinTime");
            return (Criteria) this;
        }

        public Criteria andJoinTimeLessThanOrEqualTo(Date value) {
            addCriterion("join_time <=", value, "joinTime");
            return (Criteria) this;
        }

        public Criteria andJoinTimeIn(List<Date> values) {
            addCriterion("join_time in", values, "joinTime");
            return (Criteria) this;
        }

        public Criteria andJoinTimeNotIn(List<Date> values) {
            addCriterion("join_time not in", values, "joinTime");
            return (Criteria) this;
        }

        public Criteria andJoinTimeBetween(Date value1, Date value2) {
            addCriterion("join_time between", value1, value2, "joinTime");
            return (Criteria) this;
        }

        public Criteria andJoinTimeNotBetween(Date value1, Date value2) {
            addCriterion("join_time not between", value1, value2, "joinTime");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(String value) {
            addCriterion("company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(String value) {
            addCriterion("company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(String value) {
            addCriterion("company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(String value) {
            addCriterion("company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(String value) {
            addCriterion("company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(String value) {
            addCriterion("company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLike(String value) {
            addCriterion("company_id like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotLike(String value) {
            addCriterion("company_id not like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<String> values) {
            addCriterion("company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<String> values) {
            addCriterion("company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(String value1, String value2) {
            addCriterion("company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(String value1, String value2) {
            addCriterion("company_id not between", value1, value2, "companyId");
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

        public Criteria andPositionIdIsNull() {
            addCriterion("position_id is null");
            return (Criteria) this;
        }

        public Criteria andPositionIdIsNotNull() {
            addCriterion("position_id is not null");
            return (Criteria) this;
        }

        public Criteria andPositionIdEqualTo(String value) {
            addCriterion("position_id =", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdNotEqualTo(String value) {
            addCriterion("position_id <>", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdGreaterThan(String value) {
            addCriterion("position_id >", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdGreaterThanOrEqualTo(String value) {
            addCriterion("position_id >=", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdLessThan(String value) {
            addCriterion("position_id <", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdLessThanOrEqualTo(String value) {
            addCriterion("position_id <=", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdLike(String value) {
            addCriterion("position_id like", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdNotLike(String value) {
            addCriterion("position_id not like", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdIn(List<String> values) {
            addCriterion("position_id in", values, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdNotIn(List<String> values) {
            addCriterion("position_id not in", values, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdBetween(String value1, String value2) {
            addCriterion("position_id between", value1, value2, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdNotBetween(String value1, String value2) {
            addCriterion("position_id not between", value1, value2, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionNameIsNull() {
            addCriterion("position_name is null");
            return (Criteria) this;
        }

        public Criteria andPositionNameIsNotNull() {
            addCriterion("position_name is not null");
            return (Criteria) this;
        }

        public Criteria andPositionNameEqualTo(String value) {
            addCriterion("position_name =", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameNotEqualTo(String value) {
            addCriterion("position_name <>", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameGreaterThan(String value) {
            addCriterion("position_name >", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameGreaterThanOrEqualTo(String value) {
            addCriterion("position_name >=", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameLessThan(String value) {
            addCriterion("position_name <", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameLessThanOrEqualTo(String value) {
            addCriterion("position_name <=", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameLike(String value) {
            addCriterion("position_name like", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameNotLike(String value) {
            addCriterion("position_name not like", value, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameIn(List<String> values) {
            addCriterion("position_name in", values, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameNotIn(List<String> values) {
            addCriterion("position_name not in", values, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameBetween(String value1, String value2) {
            addCriterion("position_name between", value1, value2, "positionName");
            return (Criteria) this;
        }

        public Criteria andPositionNameNotBetween(String value1, String value2) {
            addCriterion("position_name not between", value1, value2, "positionName");
            return (Criteria) this;
        }

        public Criteria andPostTimeIsNull() {
            addCriterion("post_time is null");
            return (Criteria) this;
        }

        public Criteria andPostTimeIsNotNull() {
            addCriterion("post_time is not null");
            return (Criteria) this;
        }

        public Criteria andPostTimeEqualTo(Date value) {
            addCriterion("post_time =", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeNotEqualTo(Date value) {
            addCriterion("post_time <>", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeGreaterThan(Date value) {
            addCriterion("post_time >", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("post_time >=", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeLessThan(Date value) {
            addCriterion("post_time <", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeLessThanOrEqualTo(Date value) {
            addCriterion("post_time <=", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeIn(List<Date> values) {
            addCriterion("post_time in", values, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeNotIn(List<Date> values) {
            addCriterion("post_time not in", values, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeBetween(Date value1, Date value2) {
            addCriterion("post_time between", value1, value2, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeNotBetween(Date value1, Date value2) {
            addCriterion("post_time not between", value1, value2, "postTime");
            return (Criteria) this;
        }

        public Criteria andSalaryMinIsNull() {
            addCriterion("salary_min is null");
            return (Criteria) this;
        }

        public Criteria andSalaryMinIsNotNull() {
            addCriterion("salary_min is not null");
            return (Criteria) this;
        }

        public Criteria andSalaryMinEqualTo(String value) {
            addCriterion("salary_min =", value, "salaryMin");
            return (Criteria) this;
        }

        public Criteria andSalaryMinNotEqualTo(String value) {
            addCriterion("salary_min <>", value, "salaryMin");
            return (Criteria) this;
        }

        public Criteria andSalaryMinGreaterThan(String value) {
            addCriterion("salary_min >", value, "salaryMin");
            return (Criteria) this;
        }

        public Criteria andSalaryMinGreaterThanOrEqualTo(String value) {
            addCriterion("salary_min >=", value, "salaryMin");
            return (Criteria) this;
        }

        public Criteria andSalaryMinLessThan(String value) {
            addCriterion("salary_min <", value, "salaryMin");
            return (Criteria) this;
        }

        public Criteria andSalaryMinLessThanOrEqualTo(String value) {
            addCriterion("salary_min <=", value, "salaryMin");
            return (Criteria) this;
        }

        public Criteria andSalaryMinLike(String value) {
            addCriterion("salary_min like", value, "salaryMin");
            return (Criteria) this;
        }

        public Criteria andSalaryMinNotLike(String value) {
            addCriterion("salary_min not like", value, "salaryMin");
            return (Criteria) this;
        }

        public Criteria andSalaryMinIn(List<String> values) {
            addCriterion("salary_min in", values, "salaryMin");
            return (Criteria) this;
        }

        public Criteria andSalaryMinNotIn(List<String> values) {
            addCriterion("salary_min not in", values, "salaryMin");
            return (Criteria) this;
        }

        public Criteria andSalaryMinBetween(String value1, String value2) {
            addCriterion("salary_min between", value1, value2, "salaryMin");
            return (Criteria) this;
        }

        public Criteria andSalaryMinNotBetween(String value1, String value2) {
            addCriterion("salary_min not between", value1, value2, "salaryMin");
            return (Criteria) this;
        }

        public Criteria andSalaryMaxIsNull() {
            addCriterion("salary_max is null");
            return (Criteria) this;
        }

        public Criteria andSalaryMaxIsNotNull() {
            addCriterion("salary_max is not null");
            return (Criteria) this;
        }

        public Criteria andSalaryMaxEqualTo(String value) {
            addCriterion("salary_max =", value, "salaryMax");
            return (Criteria) this;
        }

        public Criteria andSalaryMaxNotEqualTo(String value) {
            addCriterion("salary_max <>", value, "salaryMax");
            return (Criteria) this;
        }

        public Criteria andSalaryMaxGreaterThan(String value) {
            addCriterion("salary_max >", value, "salaryMax");
            return (Criteria) this;
        }

        public Criteria andSalaryMaxGreaterThanOrEqualTo(String value) {
            addCriterion("salary_max >=", value, "salaryMax");
            return (Criteria) this;
        }

        public Criteria andSalaryMaxLessThan(String value) {
            addCriterion("salary_max <", value, "salaryMax");
            return (Criteria) this;
        }

        public Criteria andSalaryMaxLessThanOrEqualTo(String value) {
            addCriterion("salary_max <=", value, "salaryMax");
            return (Criteria) this;
        }

        public Criteria andSalaryMaxLike(String value) {
            addCriterion("salary_max like", value, "salaryMax");
            return (Criteria) this;
        }

        public Criteria andSalaryMaxNotLike(String value) {
            addCriterion("salary_max not like", value, "salaryMax");
            return (Criteria) this;
        }

        public Criteria andSalaryMaxIn(List<String> values) {
            addCriterion("salary_max in", values, "salaryMax");
            return (Criteria) this;
        }

        public Criteria andSalaryMaxNotIn(List<String> values) {
            addCriterion("salary_max not in", values, "salaryMax");
            return (Criteria) this;
        }

        public Criteria andSalaryMaxBetween(String value1, String value2) {
            addCriterion("salary_max between", value1, value2, "salaryMax");
            return (Criteria) this;
        }

        public Criteria andSalaryMaxNotBetween(String value1, String value2) {
            addCriterion("salary_max not between", value1, value2, "salaryMax");
            return (Criteria) this;
        }

        public Criteria andResumeIdIsNull() {
            addCriterion("resume_id is null");
            return (Criteria) this;
        }

        public Criteria andResumeIdIsNotNull() {
            addCriterion("resume_id is not null");
            return (Criteria) this;
        }

        public Criteria andResumeIdEqualTo(String value) {
            addCriterion("resume_id =", value, "resumeId");
            return (Criteria) this;
        }

        public Criteria andResumeIdNotEqualTo(String value) {
            addCriterion("resume_id <>", value, "resumeId");
            return (Criteria) this;
        }

        public Criteria andResumeIdGreaterThan(String value) {
            addCriterion("resume_id >", value, "resumeId");
            return (Criteria) this;
        }

        public Criteria andResumeIdGreaterThanOrEqualTo(String value) {
            addCriterion("resume_id >=", value, "resumeId");
            return (Criteria) this;
        }

        public Criteria andResumeIdLessThan(String value) {
            addCriterion("resume_id <", value, "resumeId");
            return (Criteria) this;
        }

        public Criteria andResumeIdLessThanOrEqualTo(String value) {
            addCriterion("resume_id <=", value, "resumeId");
            return (Criteria) this;
        }

        public Criteria andResumeIdLike(String value) {
            addCriterion("resume_id like", value, "resumeId");
            return (Criteria) this;
        }

        public Criteria andResumeIdNotLike(String value) {
            addCriterion("resume_id not like", value, "resumeId");
            return (Criteria) this;
        }

        public Criteria andResumeIdIn(List<String> values) {
            addCriterion("resume_id in", values, "resumeId");
            return (Criteria) this;
        }

        public Criteria andResumeIdNotIn(List<String> values) {
            addCriterion("resume_id not in", values, "resumeId");
            return (Criteria) this;
        }

        public Criteria andResumeIdBetween(String value1, String value2) {
            addCriterion("resume_id between", value1, value2, "resumeId");
            return (Criteria) this;
        }

        public Criteria andResumeIdNotBetween(String value1, String value2) {
            addCriterion("resume_id not between", value1, value2, "resumeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIsNull() {
            addCriterion("employee_id is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIsNotNull() {
            addCriterion("employee_id is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdEqualTo(String value) {
            addCriterion("employee_id =", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotEqualTo(String value) {
            addCriterion("employee_id <>", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdGreaterThan(String value) {
            addCriterion("employee_id >", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdGreaterThanOrEqualTo(String value) {
            addCriterion("employee_id >=", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdLessThan(String value) {
            addCriterion("employee_id <", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdLessThanOrEqualTo(String value) {
            addCriterion("employee_id <=", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdLike(String value) {
            addCriterion("employee_id like", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotLike(String value) {
            addCriterion("employee_id not like", value, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdIn(List<String> values) {
            addCriterion("employee_id in", values, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotIn(List<String> values) {
            addCriterion("employee_id not in", values, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdBetween(String value1, String value2) {
            addCriterion("employee_id between", value1, value2, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeIdNotBetween(String value1, String value2) {
            addCriterion("employee_id not between", value1, value2, "employeeId");
            return (Criteria) this;
        }

        public Criteria andEmployeeRecordStatusIsNull() {
            addCriterion("employee_record_status is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeRecordStatusIsNotNull() {
            addCriterion("employee_record_status is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeRecordStatusEqualTo(Integer value) {
            addCriterion("employee_record_status =", value, "employeeRecordStatus");
            return (Criteria) this;
        }

        public Criteria andEmployeeRecordStatusNotEqualTo(Integer value) {
            addCriterion("employee_record_status <>", value, "employeeRecordStatus");
            return (Criteria) this;
        }

        public Criteria andEmployeeRecordStatusGreaterThan(Integer value) {
            addCriterion("employee_record_status >", value, "employeeRecordStatus");
            return (Criteria) this;
        }

        public Criteria andEmployeeRecordStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("employee_record_status >=", value, "employeeRecordStatus");
            return (Criteria) this;
        }

        public Criteria andEmployeeRecordStatusLessThan(Integer value) {
            addCriterion("employee_record_status <", value, "employeeRecordStatus");
            return (Criteria) this;
        }

        public Criteria andEmployeeRecordStatusLessThanOrEqualTo(Integer value) {
            addCriterion("employee_record_status <=", value, "employeeRecordStatus");
            return (Criteria) this;
        }

        public Criteria andEmployeeRecordStatusIn(List<Integer> values) {
            addCriterion("employee_record_status in", values, "employeeRecordStatus");
            return (Criteria) this;
        }

        public Criteria andEmployeeRecordStatusNotIn(List<Integer> values) {
            addCriterion("employee_record_status not in", values, "employeeRecordStatus");
            return (Criteria) this;
        }

        public Criteria andEmployeeRecordStatusBetween(Integer value1, Integer value2) {
            addCriterion("employee_record_status between", value1, value2, "employeeRecordStatus");
            return (Criteria) this;
        }

        public Criteria andEmployeeRecordStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("employee_record_status not between", value1, value2, "employeeRecordStatus");
            return (Criteria) this;
        }

        public Criteria andEmployerRecordStatusIsNull() {
            addCriterion("employer_record_status is null");
            return (Criteria) this;
        }

        public Criteria andEmployerRecordStatusIsNotNull() {
            addCriterion("employer_record_status is not null");
            return (Criteria) this;
        }

        public Criteria andEmployerRecordStatusEqualTo(Integer value) {
            addCriterion("employer_record_status =", value, "employerRecordStatus");
            return (Criteria) this;
        }

        public Criteria andEmployerRecordStatusNotEqualTo(Integer value) {
            addCriterion("employer_record_status <>", value, "employerRecordStatus");
            return (Criteria) this;
        }

        public Criteria andEmployerRecordStatusGreaterThan(Integer value) {
            addCriterion("employer_record_status >", value, "employerRecordStatus");
            return (Criteria) this;
        }

        public Criteria andEmployerRecordStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("employer_record_status >=", value, "employerRecordStatus");
            return (Criteria) this;
        }

        public Criteria andEmployerRecordStatusLessThan(Integer value) {
            addCriterion("employer_record_status <", value, "employerRecordStatus");
            return (Criteria) this;
        }

        public Criteria andEmployerRecordStatusLessThanOrEqualTo(Integer value) {
            addCriterion("employer_record_status <=", value, "employerRecordStatus");
            return (Criteria) this;
        }

        public Criteria andEmployerRecordStatusIn(List<Integer> values) {
            addCriterion("employer_record_status in", values, "employerRecordStatus");
            return (Criteria) this;
        }

        public Criteria andEmployerRecordStatusNotIn(List<Integer> values) {
            addCriterion("employer_record_status not in", values, "employerRecordStatus");
            return (Criteria) this;
        }

        public Criteria andEmployerRecordStatusBetween(Integer value1, Integer value2) {
            addCriterion("employer_record_status between", value1, value2, "employerRecordStatus");
            return (Criteria) this;
        }

        public Criteria andEmployerRecordStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("employer_record_status not between", value1, value2, "employerRecordStatus");
            return (Criteria) this;
        }

        public Criteria andEmployeeMessageIsNull() {
            addCriterion("employee_message is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeMessageIsNotNull() {
            addCriterion("employee_message is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeMessageEqualTo(String value) {
            addCriterion("employee_message =", value, "employeeMessage");
            return (Criteria) this;
        }

        public Criteria andEmployeeMessageNotEqualTo(String value) {
            addCriterion("employee_message <>", value, "employeeMessage");
            return (Criteria) this;
        }

        public Criteria andEmployeeMessageGreaterThan(String value) {
            addCriterion("employee_message >", value, "employeeMessage");
            return (Criteria) this;
        }

        public Criteria andEmployeeMessageGreaterThanOrEqualTo(String value) {
            addCriterion("employee_message >=", value, "employeeMessage");
            return (Criteria) this;
        }

        public Criteria andEmployeeMessageLessThan(String value) {
            addCriterion("employee_message <", value, "employeeMessage");
            return (Criteria) this;
        }

        public Criteria andEmployeeMessageLessThanOrEqualTo(String value) {
            addCriterion("employee_message <=", value, "employeeMessage");
            return (Criteria) this;
        }

        public Criteria andEmployeeMessageLike(String value) {
            addCriterion("employee_message like", value, "employeeMessage");
            return (Criteria) this;
        }

        public Criteria andEmployeeMessageNotLike(String value) {
            addCriterion("employee_message not like", value, "employeeMessage");
            return (Criteria) this;
        }

        public Criteria andEmployeeMessageIn(List<String> values) {
            addCriterion("employee_message in", values, "employeeMessage");
            return (Criteria) this;
        }

        public Criteria andEmployeeMessageNotIn(List<String> values) {
            addCriterion("employee_message not in", values, "employeeMessage");
            return (Criteria) this;
        }

        public Criteria andEmployeeMessageBetween(String value1, String value2) {
            addCriterion("employee_message between", value1, value2, "employeeMessage");
            return (Criteria) this;
        }

        public Criteria andEmployeeMessageNotBetween(String value1, String value2) {
            addCriterion("employee_message not between", value1, value2, "employeeMessage");
            return (Criteria) this;
        }

        public Criteria andEmployerMessageIsNull() {
            addCriterion("employer_message is null");
            return (Criteria) this;
        }

        public Criteria andEmployerMessageIsNotNull() {
            addCriterion("employer_message is not null");
            return (Criteria) this;
        }

        public Criteria andEmployerMessageEqualTo(String value) {
            addCriterion("employer_message =", value, "employerMessage");
            return (Criteria) this;
        }

        public Criteria andEmployerMessageNotEqualTo(String value) {
            addCriterion("employer_message <>", value, "employerMessage");
            return (Criteria) this;
        }

        public Criteria andEmployerMessageGreaterThan(String value) {
            addCriterion("employer_message >", value, "employerMessage");
            return (Criteria) this;
        }

        public Criteria andEmployerMessageGreaterThanOrEqualTo(String value) {
            addCriterion("employer_message >=", value, "employerMessage");
            return (Criteria) this;
        }

        public Criteria andEmployerMessageLessThan(String value) {
            addCriterion("employer_message <", value, "employerMessage");
            return (Criteria) this;
        }

        public Criteria andEmployerMessageLessThanOrEqualTo(String value) {
            addCriterion("employer_message <=", value, "employerMessage");
            return (Criteria) this;
        }

        public Criteria andEmployerMessageLike(String value) {
            addCriterion("employer_message like", value, "employerMessage");
            return (Criteria) this;
        }

        public Criteria andEmployerMessageNotLike(String value) {
            addCriterion("employer_message not like", value, "employerMessage");
            return (Criteria) this;
        }

        public Criteria andEmployerMessageIn(List<String> values) {
            addCriterion("employer_message in", values, "employerMessage");
            return (Criteria) this;
        }

        public Criteria andEmployerMessageNotIn(List<String> values) {
            addCriterion("employer_message not in", values, "employerMessage");
            return (Criteria) this;
        }

        public Criteria andEmployerMessageBetween(String value1, String value2) {
            addCriterion("employer_message between", value1, value2, "employerMessage");
            return (Criteria) this;
        }

        public Criteria andEmployerMessageNotBetween(String value1, String value2) {
            addCriterion("employer_message not between", value1, value2, "employerMessage");
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
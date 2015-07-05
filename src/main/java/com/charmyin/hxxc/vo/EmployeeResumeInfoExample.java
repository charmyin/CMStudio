package com.charmyin.hxxc.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.charmyin.cmstudio.basic.pagination.annotation.Paging;
import com.charmyin.cmstudio.basic.pagination.interceptor.IViewObjectExample;
import com.charmyin.cmstudio.basic.pagination.page.PageBase;
import com.charmyin.hxxc.vo.ItemExample.Criteria;
import com.charmyin.hxxc.vo.ItemExample.Criterion;

@Paging(field="pageVO") 
public class EmployeeResumeInfoExample  extends PageBase implements IViewObjectExample{
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

    public EmployeeResumeInfoExample() {
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

        public Criteria andResumeNameIsNull() {
            addCriterion("resume_name is null");
            return (Criteria) this;
        }

        public Criteria andResumeNameIsNotNull() {
            addCriterion("resume_name is not null");
            return (Criteria) this;
        }

        public Criteria andResumeNameEqualTo(String value) {
            addCriterion("resume_name =", value, "resumeName");
            return (Criteria) this;
        }

        public Criteria andResumeNameNotEqualTo(String value) {
            addCriterion("resume_name <>", value, "resumeName");
            return (Criteria) this;
        }

        public Criteria andResumeNameGreaterThan(String value) {
            addCriterion("resume_name >", value, "resumeName");
            return (Criteria) this;
        }

        public Criteria andResumeNameGreaterThanOrEqualTo(String value) {
            addCriterion("resume_name >=", value, "resumeName");
            return (Criteria) this;
        }

        public Criteria andResumeNameLessThan(String value) {
            addCriterion("resume_name <", value, "resumeName");
            return (Criteria) this;
        }

        public Criteria andResumeNameLessThanOrEqualTo(String value) {
            addCriterion("resume_name <=", value, "resumeName");
            return (Criteria) this;
        }

        public Criteria andResumeNameLike(String value) {
            addCriterion("resume_name like", value, "resumeName");
            return (Criteria) this;
        }

        public Criteria andResumeNameNotLike(String value) {
            addCriterion("resume_name not like", value, "resumeName");
            return (Criteria) this;
        }

        public Criteria andResumeNameIn(List<String> values) {
            addCriterion("resume_name in", values, "resumeName");
            return (Criteria) this;
        }

        public Criteria andResumeNameNotIn(List<String> values) {
            addCriterion("resume_name not in", values, "resumeName");
            return (Criteria) this;
        }

        public Criteria andResumeNameBetween(String value1, String value2) {
            addCriterion("resume_name between", value1, value2, "resumeName");
            return (Criteria) this;
        }

        public Criteria andResumeNameNotBetween(String value1, String value2) {
            addCriterion("resume_name not between", value1, value2, "resumeName");
            return (Criteria) this;
        }

        public Criteria andRaceIsNull() {
            addCriterion("race is null");
            return (Criteria) this;
        }

        public Criteria andRaceIsNotNull() {
            addCriterion("race is not null");
            return (Criteria) this;
        }

        public Criteria andRaceEqualTo(String value) {
            addCriterion("race =", value, "race");
            return (Criteria) this;
        }

        public Criteria andRaceNotEqualTo(String value) {
            addCriterion("race <>", value, "race");
            return (Criteria) this;
        }

        public Criteria andRaceGreaterThan(String value) {
            addCriterion("race >", value, "race");
            return (Criteria) this;
        }

        public Criteria andRaceGreaterThanOrEqualTo(String value) {
            addCriterion("race >=", value, "race");
            return (Criteria) this;
        }

        public Criteria andRaceLessThan(String value) {
            addCriterion("race <", value, "race");
            return (Criteria) this;
        }

        public Criteria andRaceLessThanOrEqualTo(String value) {
            addCriterion("race <=", value, "race");
            return (Criteria) this;
        }

        public Criteria andRaceLike(String value) {
            addCriterion("race like", value, "race");
            return (Criteria) this;
        }

        public Criteria andRaceNotLike(String value) {
            addCriterion("race not like", value, "race");
            return (Criteria) this;
        }

        public Criteria andRaceIn(List<String> values) {
            addCriterion("race in", values, "race");
            return (Criteria) this;
        }

        public Criteria andRaceNotIn(List<String> values) {
            addCriterion("race not in", values, "race");
            return (Criteria) this;
        }

        public Criteria andRaceBetween(String value1, String value2) {
            addCriterion("race between", value1, value2, "race");
            return (Criteria) this;
        }

        public Criteria andRaceNotBetween(String value1, String value2) {
            addCriterion("race not between", value1, value2, "race");
            return (Criteria) this;
        }

        public Criteria andNavPlaceIsNull() {
            addCriterion("nav_place is null");
            return (Criteria) this;
        }

        public Criteria andNavPlaceIsNotNull() {
            addCriterion("nav_place is not null");
            return (Criteria) this;
        }

        public Criteria andNavPlaceEqualTo(String value) {
            addCriterion("nav_place =", value, "navPlace");
            return (Criteria) this;
        }

        public Criteria andNavPlaceNotEqualTo(String value) {
            addCriterion("nav_place <>", value, "navPlace");
            return (Criteria) this;
        }

        public Criteria andNavPlaceGreaterThan(String value) {
            addCriterion("nav_place >", value, "navPlace");
            return (Criteria) this;
        }

        public Criteria andNavPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("nav_place >=", value, "navPlace");
            return (Criteria) this;
        }

        public Criteria andNavPlaceLessThan(String value) {
            addCriterion("nav_place <", value, "navPlace");
            return (Criteria) this;
        }

        public Criteria andNavPlaceLessThanOrEqualTo(String value) {
            addCriterion("nav_place <=", value, "navPlace");
            return (Criteria) this;
        }

        public Criteria andNavPlaceLike(String value) {
            addCriterion("nav_place like", value, "navPlace");
            return (Criteria) this;
        }

        public Criteria andNavPlaceNotLike(String value) {
            addCriterion("nav_place not like", value, "navPlace");
            return (Criteria) this;
        }

        public Criteria andNavPlaceIn(List<String> values) {
            addCriterion("nav_place in", values, "navPlace");
            return (Criteria) this;
        }

        public Criteria andNavPlaceNotIn(List<String> values) {
            addCriterion("nav_place not in", values, "navPlace");
            return (Criteria) this;
        }

        public Criteria andNavPlaceBetween(String value1, String value2) {
            addCriterion("nav_place between", value1, value2, "navPlace");
            return (Criteria) this;
        }

        public Criteria andNavPlaceNotBetween(String value1, String value2) {
            addCriterion("nav_place not between", value1, value2, "navPlace");
            return (Criteria) this;
        }

        public Criteria andBornPlaceIsNull() {
            addCriterion("born_place is null");
            return (Criteria) this;
        }

        public Criteria andBornPlaceIsNotNull() {
            addCriterion("born_place is not null");
            return (Criteria) this;
        }

        public Criteria andBornPlaceEqualTo(String value) {
            addCriterion("born_place =", value, "bornPlace");
            return (Criteria) this;
        }

        public Criteria andBornPlaceNotEqualTo(String value) {
            addCriterion("born_place <>", value, "bornPlace");
            return (Criteria) this;
        }

        public Criteria andBornPlaceGreaterThan(String value) {
            addCriterion("born_place >", value, "bornPlace");
            return (Criteria) this;
        }

        public Criteria andBornPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("born_place >=", value, "bornPlace");
            return (Criteria) this;
        }

        public Criteria andBornPlaceLessThan(String value) {
            addCriterion("born_place <", value, "bornPlace");
            return (Criteria) this;
        }

        public Criteria andBornPlaceLessThanOrEqualTo(String value) {
            addCriterion("born_place <=", value, "bornPlace");
            return (Criteria) this;
        }

        public Criteria andBornPlaceLike(String value) {
            addCriterion("born_place like", value, "bornPlace");
            return (Criteria) this;
        }

        public Criteria andBornPlaceNotLike(String value) {
            addCriterion("born_place not like", value, "bornPlace");
            return (Criteria) this;
        }

        public Criteria andBornPlaceIn(List<String> values) {
            addCriterion("born_place in", values, "bornPlace");
            return (Criteria) this;
        }

        public Criteria andBornPlaceNotIn(List<String> values) {
            addCriterion("born_place not in", values, "bornPlace");
            return (Criteria) this;
        }

        public Criteria andBornPlaceBetween(String value1, String value2) {
            addCriterion("born_place between", value1, value2, "bornPlace");
            return (Criteria) this;
        }

        public Criteria andBornPlaceNotBetween(String value1, String value2) {
            addCriterion("born_place not between", value1, value2, "bornPlace");
            return (Criteria) this;
        }

        public Criteria andGraduationIsNull() {
            addCriterion("graduation is null");
            return (Criteria) this;
        }

        public Criteria andGraduationIsNotNull() {
            addCriterion("graduation is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationEqualTo(String value) {
            addCriterion("graduation =", value, "graduation");
            return (Criteria) this;
        }

        public Criteria andGraduationNotEqualTo(String value) {
            addCriterion("graduation <>", value, "graduation");
            return (Criteria) this;
        }

        public Criteria andGraduationGreaterThan(String value) {
            addCriterion("graduation >", value, "graduation");
            return (Criteria) this;
        }

        public Criteria andGraduationGreaterThanOrEqualTo(String value) {
            addCriterion("graduation >=", value, "graduation");
            return (Criteria) this;
        }

        public Criteria andGraduationLessThan(String value) {
            addCriterion("graduation <", value, "graduation");
            return (Criteria) this;
        }

        public Criteria andGraduationLessThanOrEqualTo(String value) {
            addCriterion("graduation <=", value, "graduation");
            return (Criteria) this;
        }

        public Criteria andGraduationLike(String value) {
            addCriterion("graduation like", value, "graduation");
            return (Criteria) this;
        }

        public Criteria andGraduationNotLike(String value) {
            addCriterion("graduation not like", value, "graduation");
            return (Criteria) this;
        }

        public Criteria andGraduationIn(List<String> values) {
            addCriterion("graduation in", values, "graduation");
            return (Criteria) this;
        }

        public Criteria andGraduationNotIn(List<String> values) {
            addCriterion("graduation not in", values, "graduation");
            return (Criteria) this;
        }

        public Criteria andGraduationBetween(String value1, String value2) {
            addCriterion("graduation between", value1, value2, "graduation");
            return (Criteria) this;
        }

        public Criteria andGraduationNotBetween(String value1, String value2) {
            addCriterion("graduation not between", value1, value2, "graduation");
            return (Criteria) this;
        }

        public Criteria andStudyIsNull() {
            addCriterion("study is null");
            return (Criteria) this;
        }

        public Criteria andStudyIsNotNull() {
            addCriterion("study is not null");
            return (Criteria) this;
        }

        public Criteria andStudyEqualTo(String value) {
            addCriterion("study =", value, "study");
            return (Criteria) this;
        }

        public Criteria andStudyNotEqualTo(String value) {
            addCriterion("study <>", value, "study");
            return (Criteria) this;
        }

        public Criteria andStudyGreaterThan(String value) {
            addCriterion("study >", value, "study");
            return (Criteria) this;
        }

        public Criteria andStudyGreaterThanOrEqualTo(String value) {
            addCriterion("study >=", value, "study");
            return (Criteria) this;
        }

        public Criteria andStudyLessThan(String value) {
            addCriterion("study <", value, "study");
            return (Criteria) this;
        }

        public Criteria andStudyLessThanOrEqualTo(String value) {
            addCriterion("study <=", value, "study");
            return (Criteria) this;
        }

        public Criteria andStudyLike(String value) {
            addCriterion("study like", value, "study");
            return (Criteria) this;
        }

        public Criteria andStudyNotLike(String value) {
            addCriterion("study not like", value, "study");
            return (Criteria) this;
        }

        public Criteria andStudyIn(List<String> values) {
            addCriterion("study in", values, "study");
            return (Criteria) this;
        }

        public Criteria andStudyNotIn(List<String> values) {
            addCriterion("study not in", values, "study");
            return (Criteria) this;
        }

        public Criteria andStudyBetween(String value1, String value2) {
            addCriterion("study between", value1, value2, "study");
            return (Criteria) this;
        }

        public Criteria andStudyNotBetween(String value1, String value2) {
            addCriterion("study not between", value1, value2, "study");
            return (Criteria) this;
        }

        public Criteria andEducationIsNull() {
            addCriterion("education is null");
            return (Criteria) this;
        }

        public Criteria andEducationIsNotNull() {
            addCriterion("education is not null");
            return (Criteria) this;
        }

        public Criteria andEducationEqualTo(String value) {
            addCriterion("education =", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotEqualTo(String value) {
            addCriterion("education <>", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThan(String value) {
            addCriterion("education >", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThanOrEqualTo(String value) {
            addCriterion("education >=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThan(String value) {
            addCriterion("education <", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThanOrEqualTo(String value) {
            addCriterion("education <=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLike(String value) {
            addCriterion("education like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotLike(String value) {
            addCriterion("education not like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationIn(List<String> values) {
            addCriterion("education in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotIn(List<String> values) {
            addCriterion("education not in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationBetween(String value1, String value2) {
            addCriterion("education between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotBetween(String value1, String value2) {
            addCriterion("education not between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andPositionIsNull() {
            addCriterion("position is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("position is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(String value) {
            addCriterion("position =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(String value) {
            addCriterion("position <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(String value) {
            addCriterion("position >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(String value) {
            addCriterion("position >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(String value) {
            addCriterion("position <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(String value) {
            addCriterion("position <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLike(String value) {
            addCriterion("position like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotLike(String value) {
            addCriterion("position not like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<String> values) {
            addCriterion("position in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<String> values) {
            addCriterion("position not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(String value1, String value2) {
            addCriterion("position between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(String value1, String value2) {
            addCriterion("position not between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionidIsNull() {
            addCriterion("positionId is null");
            return (Criteria) this;
        }

        public Criteria andPositionidIsNotNull() {
            addCriterion("positionId is not null");
            return (Criteria) this;
        }

        public Criteria andPositionidEqualTo(String value) {
            addCriterion("positionId =", value, "positionid");
            return (Criteria) this;
        }

        public Criteria andPositionidNotEqualTo(String value) {
            addCriterion("positionId <>", value, "positionid");
            return (Criteria) this;
        }

        public Criteria andPositionidGreaterThan(String value) {
            addCriterion("positionId >", value, "positionid");
            return (Criteria) this;
        }

        public Criteria andPositionidGreaterThanOrEqualTo(String value) {
            addCriterion("positionId >=", value, "positionid");
            return (Criteria) this;
        }

        public Criteria andPositionidLessThan(String value) {
            addCriterion("positionId <", value, "positionid");
            return (Criteria) this;
        }

        public Criteria andPositionidLessThanOrEqualTo(String value) {
            addCriterion("positionId <=", value, "positionid");
            return (Criteria) this;
        }

        public Criteria andPositionidLike(String value) {
            addCriterion("positionId like", value, "positionid");
            return (Criteria) this;
        }

        public Criteria andPositionidNotLike(String value) {
            addCriterion("positionId not like", value, "positionid");
            return (Criteria) this;
        }

        public Criteria andPositionidIn(List<String> values) {
            addCriterion("positionId in", values, "positionid");
            return (Criteria) this;
        }

        public Criteria andPositionidNotIn(List<String> values) {
            addCriterion("positionId not in", values, "positionid");
            return (Criteria) this;
        }

        public Criteria andPositionidBetween(String value1, String value2) {
            addCriterion("positionId between", value1, value2, "positionid");
            return (Criteria) this;
        }

        public Criteria andPositionidNotBetween(String value1, String value2) {
            addCriterion("positionId not between", value1, value2, "positionid");
            return (Criteria) this;
        }

        public Criteria andWorkYearsIsNull() {
            addCriterion("work_years is null");
            return (Criteria) this;
        }

        public Criteria andWorkYearsIsNotNull() {
            addCriterion("work_years is not null");
            return (Criteria) this;
        }

        public Criteria andWorkYearsEqualTo(String value) {
            addCriterion("work_years =", value, "workYears");
            return (Criteria) this;
        }

        public Criteria andWorkYearsNotEqualTo(String value) {
            addCriterion("work_years <>", value, "workYears");
            return (Criteria) this;
        }

        public Criteria andWorkYearsGreaterThan(String value) {
            addCriterion("work_years >", value, "workYears");
            return (Criteria) this;
        }

        public Criteria andWorkYearsGreaterThanOrEqualTo(String value) {
            addCriterion("work_years >=", value, "workYears");
            return (Criteria) this;
        }

        public Criteria andWorkYearsLessThan(String value) {
            addCriterion("work_years <", value, "workYears");
            return (Criteria) this;
        }

        public Criteria andWorkYearsLessThanOrEqualTo(String value) {
            addCriterion("work_years <=", value, "workYears");
            return (Criteria) this;
        }

        public Criteria andWorkYearsLike(String value) {
            addCriterion("work_years like", value, "workYears");
            return (Criteria) this;
        }

        public Criteria andWorkYearsNotLike(String value) {
            addCriterion("work_years not like", value, "workYears");
            return (Criteria) this;
        }

        public Criteria andWorkYearsIn(List<String> values) {
            addCriterion("work_years in", values, "workYears");
            return (Criteria) this;
        }

        public Criteria andWorkYearsNotIn(List<String> values) {
            addCriterion("work_years not in", values, "workYears");
            return (Criteria) this;
        }

        public Criteria andWorkYearsBetween(String value1, String value2) {
            addCriterion("work_years between", value1, value2, "workYears");
            return (Criteria) this;
        }

        public Criteria andWorkYearsNotBetween(String value1, String value2) {
            addCriterion("work_years not between", value1, value2, "workYears");
            return (Criteria) this;
        }

        public Criteria andHealthIsNull() {
            addCriterion("health is null");
            return (Criteria) this;
        }

        public Criteria andHealthIsNotNull() {
            addCriterion("health is not null");
            return (Criteria) this;
        }

        public Criteria andHealthEqualTo(String value) {
            addCriterion("health =", value, "health");
            return (Criteria) this;
        }

        public Criteria andHealthNotEqualTo(String value) {
            addCriterion("health <>", value, "health");
            return (Criteria) this;
        }

        public Criteria andHealthGreaterThan(String value) {
            addCriterion("health >", value, "health");
            return (Criteria) this;
        }

        public Criteria andHealthGreaterThanOrEqualTo(String value) {
            addCriterion("health >=", value, "health");
            return (Criteria) this;
        }

        public Criteria andHealthLessThan(String value) {
            addCriterion("health <", value, "health");
            return (Criteria) this;
        }

        public Criteria andHealthLessThanOrEqualTo(String value) {
            addCriterion("health <=", value, "health");
            return (Criteria) this;
        }

        public Criteria andHealthLike(String value) {
            addCriterion("health like", value, "health");
            return (Criteria) this;
        }

        public Criteria andHealthNotLike(String value) {
            addCriterion("health not like", value, "health");
            return (Criteria) this;
        }

        public Criteria andHealthIn(List<String> values) {
            addCriterion("health in", values, "health");
            return (Criteria) this;
        }

        public Criteria andHealthNotIn(List<String> values) {
            addCriterion("health not in", values, "health");
            return (Criteria) this;
        }

        public Criteria andHealthBetween(String value1, String value2) {
            addCriterion("health between", value1, value2, "health");
            return (Criteria) this;
        }

        public Criteria andHealthNotBetween(String value1, String value2) {
            addCriterion("health not between", value1, value2, "health");
            return (Criteria) this;
        }

        public Criteria andJobAttemptIsNull() {
            addCriterion("job_attempt is null");
            return (Criteria) this;
        }

        public Criteria andJobAttemptIsNotNull() {
            addCriterion("job_attempt is not null");
            return (Criteria) this;
        }

        public Criteria andJobAttemptEqualTo(String value) {
            addCriterion("job_attempt =", value, "jobAttempt");
            return (Criteria) this;
        }

        public Criteria andJobAttemptNotEqualTo(String value) {
            addCriterion("job_attempt <>", value, "jobAttempt");
            return (Criteria) this;
        }

        public Criteria andJobAttemptGreaterThan(String value) {
            addCriterion("job_attempt >", value, "jobAttempt");
            return (Criteria) this;
        }

        public Criteria andJobAttemptGreaterThanOrEqualTo(String value) {
            addCriterion("job_attempt >=", value, "jobAttempt");
            return (Criteria) this;
        }

        public Criteria andJobAttemptLessThan(String value) {
            addCriterion("job_attempt <", value, "jobAttempt");
            return (Criteria) this;
        }

        public Criteria andJobAttemptLessThanOrEqualTo(String value) {
            addCriterion("job_attempt <=", value, "jobAttempt");
            return (Criteria) this;
        }

        public Criteria andJobAttemptLike(String value) {
            addCriterion("job_attempt like", value, "jobAttempt");
            return (Criteria) this;
        }

        public Criteria andJobAttemptNotLike(String value) {
            addCriterion("job_attempt not like", value, "jobAttempt");
            return (Criteria) this;
        }

        public Criteria andJobAttemptIn(List<String> values) {
            addCriterion("job_attempt in", values, "jobAttempt");
            return (Criteria) this;
        }

        public Criteria andJobAttemptNotIn(List<String> values) {
            addCriterion("job_attempt not in", values, "jobAttempt");
            return (Criteria) this;
        }

        public Criteria andJobAttemptBetween(String value1, String value2) {
            addCriterion("job_attempt between", value1, value2, "jobAttempt");
            return (Criteria) this;
        }

        public Criteria andJobAttemptNotBetween(String value1, String value2) {
            addCriterion("job_attempt not between", value1, value2, "jobAttempt");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlIsNull() {
            addCriterion("photo_url is null");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlIsNotNull() {
            addCriterion("photo_url is not null");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlEqualTo(String value) {
            addCriterion("photo_url =", value, "photoUrl");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlNotEqualTo(String value) {
            addCriterion("photo_url <>", value, "photoUrl");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlGreaterThan(String value) {
            addCriterion("photo_url >", value, "photoUrl");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlGreaterThanOrEqualTo(String value) {
            addCriterion("photo_url >=", value, "photoUrl");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlLessThan(String value) {
            addCriterion("photo_url <", value, "photoUrl");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlLessThanOrEqualTo(String value) {
            addCriterion("photo_url <=", value, "photoUrl");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlLike(String value) {
            addCriterion("photo_url like", value, "photoUrl");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlNotLike(String value) {
            addCriterion("photo_url not like", value, "photoUrl");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlIn(List<String> values) {
            addCriterion("photo_url in", values, "photoUrl");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlNotIn(List<String> values) {
            addCriterion("photo_url not in", values, "photoUrl");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlBetween(String value1, String value2) {
            addCriterion("photo_url between", value1, value2, "photoUrl");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlNotBetween(String value1, String value2) {
            addCriterion("photo_url not between", value1, value2, "photoUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlIsNull() {
            addCriterion("file_url is null");
            return (Criteria) this;
        }

        public Criteria andFileUrlIsNotNull() {
            addCriterion("file_url is not null");
            return (Criteria) this;
        }

        public Criteria andFileUrlEqualTo(String value) {
            addCriterion("file_url =", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotEqualTo(String value) {
            addCriterion("file_url <>", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlGreaterThan(String value) {
            addCriterion("file_url >", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlGreaterThanOrEqualTo(String value) {
            addCriterion("file_url >=", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlLessThan(String value) {
            addCriterion("file_url <", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlLessThanOrEqualTo(String value) {
            addCriterion("file_url <=", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlLike(String value) {
            addCriterion("file_url like", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotLike(String value) {
            addCriterion("file_url not like", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlIn(List<String> values) {
            addCriterion("file_url in", values, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotIn(List<String> values) {
            addCriterion("file_url not in", values, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlBetween(String value1, String value2) {
            addCriterion("file_url between", value1, value2, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotBetween(String value1, String value2) {
            addCriterion("file_url not between", value1, value2, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Date value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Date value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Date value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Date value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Date value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Date> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Date> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Date value1, Date value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Date value1, Date value2) {
            addCriterion("time not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
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

        public Criteria andExperienceIsNull() {
            addCriterion("experience is null");
            return (Criteria) this;
        }

        public Criteria andExperienceIsNotNull() {
            addCriterion("experience is not null");
            return (Criteria) this;
        }

        public Criteria andExperienceEqualTo(String value) {
            addCriterion("experience =", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceNotEqualTo(String value) {
            addCriterion("experience <>", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceGreaterThan(String value) {
            addCriterion("experience >", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceGreaterThanOrEqualTo(String value) {
            addCriterion("experience >=", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceLessThan(String value) {
            addCriterion("experience <", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceLessThanOrEqualTo(String value) {
            addCriterion("experience <=", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceLike(String value) {
            addCriterion("experience like", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceNotLike(String value) {
            addCriterion("experience not like", value, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceIn(List<String> values) {
            addCriterion("experience in", values, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceNotIn(List<String> values) {
            addCriterion("experience not in", values, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceBetween(String value1, String value2) {
            addCriterion("experience between", value1, value2, "experience");
            return (Criteria) this;
        }

        public Criteria andExperienceNotBetween(String value1, String value2) {
            addCriterion("experience not between", value1, value2, "experience");
            return (Criteria) this;
        }

        public Criteria andIndexOrderIsNull() {
            addCriterion("index_order is null");
            return (Criteria) this;
        }

        public Criteria andIndexOrderIsNotNull() {
            addCriterion("index_order is not null");
            return (Criteria) this;
        }

        public Criteria andIndexOrderEqualTo(Integer value) {
            addCriterion("index_order =", value, "indexOrder");
            return (Criteria) this;
        }

        public Criteria andIndexOrderNotEqualTo(Integer value) {
            addCriterion("index_order <>", value, "indexOrder");
            return (Criteria) this;
        }

        public Criteria andIndexOrderGreaterThan(Integer value) {
            addCriterion("index_order >", value, "indexOrder");
            return (Criteria) this;
        }

        public Criteria andIndexOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("index_order >=", value, "indexOrder");
            return (Criteria) this;
        }

        public Criteria andIndexOrderLessThan(Integer value) {
            addCriterion("index_order <", value, "indexOrder");
            return (Criteria) this;
        }

        public Criteria andIndexOrderLessThanOrEqualTo(Integer value) {
            addCriterion("index_order <=", value, "indexOrder");
            return (Criteria) this;
        }

        public Criteria andIndexOrderIn(List<Integer> values) {
            addCriterion("index_order in", values, "indexOrder");
            return (Criteria) this;
        }

        public Criteria andIndexOrderNotIn(List<Integer> values) {
            addCriterion("index_order not in", values, "indexOrder");
            return (Criteria) this;
        }

        public Criteria andIndexOrderBetween(Integer value1, Integer value2) {
            addCriterion("index_order between", value1, value2, "indexOrder");
            return (Criteria) this;
        }

        public Criteria andIndexOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("index_order not between", value1, value2, "indexOrder");
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
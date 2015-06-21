var mongoose = require('mongoose')
  , CommonModel = mongoose.model('{{config.firstNameCapital}}')
  , utils = require('../../lib/utils')
  , passport = require('passport');

exports.toSearchList = function(req, res){
  res.render("{{ config.path }}/{{ config.name }}",{object_name:"{{ config.cnName }}"});
};

exports.searchList = function(req, res) {
  var crite = utils.setCriteriaParam(req.body);
  var sortObj = utils.setSortParam(req.body);
  CommonModel.count(crite, function(errors, count){
    if(errors){
      res.json({
         "success":false,
         "msg":"查询过程中出错！",
         "error":errors
      });
      return;
    }
    CommonModel.queryList({rows:req.body.rows, page:req.body.page, criteria:crite, sort:sortObj}, function(err,list){
      utils.setQueryListResponse(err, req, res, list, count);
    });
  });
};

exports.add = function (req, res) {
    delete req.body._id;
    var commonModel = new CommonModel(req.body);
    commonModel.save(function(err) {
        utils.setSaveResponse(err, res, commonModel);
    });
};

exports.remove = function(req, res){
  var id = req.param("_id");
  CommonModel.findOneAndRemove(id, function(err, doc){
    utils.setSaveResponse(err, res, doc);
  });
};

exports.updateById = function(req, res){
    if(!req.body._id){
       res.json({
                    "success":false,
                    "msg":"主键_id不能为空"
                });
      return;
    }
    CommonModel.findOneAndUpdate({ _id:req.body._id},req.body, function (err, obj) {
      utils.setSaveResponse(err, res, obj);
    });

};


var Mustache = require('mustache');
var fs = require('fs');
var mkdirp = require('mkdirp');
//need to install mustache
//need to install mkdirp

//The following code is need append to all the model file.
//And need to be modified.
/**********************CONFIG INFO BEGIN************************/

/*//********手动配置字段Start******
var ObjectSchema = ProductSchema;//Schema upside
var path = "product";//the path to of object
var endName = "product";//the name of object
var cnName = "产品";//the chinese name of object
//********手动配置字段End*******

var firstNameCapital = endName.charAt(0).toUpperCase()+endName.slice(1);
var config={
  path:path,
  endName:endName,
  firstNameCapital:firstNameCapital,
  shema:ObjectSchema,
  cnName:cnName
};

//Get properties key value
var keyValues=[];
for(var prop in ObjectSchema.tree){
  if(prop=="__v" || prop=="_id" || prop=="id"){
    continue;
  }
  keyValues.push({name:prop, comment:ObjectSchema.tree[prop].comment, type:ObjectSchema.tree[prop].type});
}
exports.keyValues = keyValues;
exports.config = config;*/


/************************CONFIG INFO END**********************/

//Models' names are configed here! (The file name of the model)
var path = "hxxc";//global path
var modelObjs = [];

//Get all the vo files
var fileDir = ".\\src\\main\\java\\com\\charmyin\\hxxc\\";
var packagePath = "com.charmyin.hxxc";
//var globalModuleName = "hxxc";
var files = fs.readdirSync(fileDir+"vo")
//Iterate all the vo files
for (var i = 0; i < files.length; i++) {
  var nameLength =files[i].length;
  var endName = files[i].substring(nameLength-12, nameLength-5);
  //Get pure item not example item
  if(endName!="Example"){
    var tempModelObj = {};
    tempModelObj.keyValues=[];
    tempModelObj.config={
      path: path,
      packagePath: packagePath
     // globalModuleName: globalModuleName
    };
    fs.readFileSync(fileDir+"vo\\"+files[i]).toString().split('\n').forEach(function(line){
      line = line.trim()
      if(line.substring(0,7)=="private"){
        tempModelObj.keyValues .push({name:line.split(" ")[2].split(";")[0], comment:line.split("//")[1], type:line.split(" ")[1]});
      }else if("public class"==line.substring(0,12)){
        tempModelObj.config.firstNameCapital = line.split(" ")[2];
        tempModelObj.config.name = tempModelObj.config.firstNameCapital.charAt(0).toLowerCase()+tempModelObj.config.firstNameCapital.slice(1);
        tempModelObj.config.cnName =  line.split("//")[1];
        console.log(line)
      }
    });
    console.log(JSON.stringify(tempModelObj))
    modelObjs.push(tempModelObj)
  }
};

//Load templates
var controllerTmpelate=fs.readFileSync('autogenerate/template/TemplateController.java', 'utf8');
var serviceTemplate=fs.readFileSync('autogenerate/template/TemplateService.java', 'utf8');
var serviceImplTemplate=fs.readFileSync('autogenerate/template/TemplateServiceImpl.java', 'utf8');
var indexPageTemplate=fs.readFileSync('autogenerate/template/IndexTemplate.jsp', 'utf8');

//Create directory
if (!fs.existsSync("./autogenerate/generated/")){
    fs.mkdirSync("./autogenerate/generated/");
}
if (!fs.existsSync("./autogenerate/generated/"+path)){
    fs.mkdirSync("./autogenerate/generated/"+path);
}
if (!fs.existsSync("./autogenerate/generated/"+path+"/controller")){
    fs.mkdirSync("./autogenerate/generated/"+path+"/controller");
}
if (!fs.existsSync("./autogenerate/generated/"+path+"/service/")){
    fs.mkdirSync("./autogenerate/generated/"+path+"/service/");
}
if (!fs.existsSync("./autogenerate/generated/"+path+"/service/impl")){
    fs.mkdirSync("./autogenerate/generated/"+path+"/service/impl");
}
if (!fs.existsSync("./autogenerate/generated/"+path+"/pages")){
    fs.mkdirSync("./autogenerate/generated/"+path+"/pages");
}

modelObjs.forEach(function(modelObj){
  //Generate controller
  var controllerTmpelateOutput = Mustache.render(controllerTmpelate, modelObj);
  var controllerPath = "./autogenerate/generated/"+path+"/controller/"+modelObj.config.firstNameCapital+"Controller.java";
  fs.writeFile(controllerPath, controllerTmpelateOutput, function(err) {
      if(err) {
        console.log("error----"+controllerPath);
        return console.log(err);
      }
      console.log(controllerPath+"--- Generate Finished");
  });
  //Generate service
  var serviceTemplateOutput = Mustache.render(serviceTemplate, modelObj);
  var servicePath = "./autogenerate/generated/"+path+"/service/"+modelObj.config.firstNameCapital+"Service.java";
  fs.writeFile(servicePath, serviceTemplateOutput, function(err) {
      if(err) {
        console.log("error----"+servicePath);
        return console.log(err);
      }
      console.log(servicePath+"--- Generate Finished");
  });
  //Generate serviceImpl
  var serviceImplTemplateOutput = Mustache.render(serviceImplTemplate, modelObj);
  var serviceImplPath = "./autogenerate/generated/"+path+"/service/impl/"+modelObj.config.firstNameCapital+"ServiceImpl.java";
  fs.writeFile(serviceImplPath, serviceImplTemplateOutput, function(err) {
      if(err) {
        console.log("error----"+serviceImplPath);
        return console.log(err);
      }
      console.log(serviceImplPath+"--- Generate Finished");
  });
  //Generate ejsTemplate
  var indexPageTemplateOutput = Mustache.render(indexPageTemplate, modelObj);
  var indexPagePath = "./autogenerate/generated/"+path+"/pages/"+modelObj.config.name+"/index.jsp";
  if (!fs.existsSync("./autogenerate/generated/"+path+"/pages/"+modelObj.config.name+"/")){
      mkdirp.sync("./autogenerate/generated/"+path+"/pages/"+modelObj.config.name+"/");
  }
  fs.writeFile(indexPagePath, indexPageTemplateOutput, function(err) {
      if(err) {
        console.log("error----"+indexPagePath);
        return console.log(err);
      }
      console.log(indexPagePath+"--- Generate Finished");
  });

});

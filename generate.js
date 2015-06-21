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
      path:path
    };
    fs.readFileSync(fileDir+"vo\\"+files[i]).toString().split('\n').forEach(function(line){
      line = line.trim()
      if(line.substring(0,7)=="private"){
        tempModelObj.keyValues .push({name:line.split(" ")[2].split(";")[0], comment:line.split("//")[1], type:line.split(" ")[1]});
      }else if("public class"==line.substring(0,12)){
        tempModelObj.config.firstNameCapital = line.split(" ")[2];
        tempModelObj.config.name = tempModelObj.config.firstNameCapital.charAt(0).toLowerCase()+tempModelObj.config.firstNameCapital.slice(1);
        tempModelObj.config.cnName =  line.split("//")[1]
        console.log(line)
      }
    });
    console.log(JSON.stringify(tempModelObj))
    modelObjs.push(tempModelObj)
  }
};

//Load templates
/*var controllerTmpelate=fs.readFileSync('autogenerate/template/TemplateController.js', 'utf8');
var serviceTemplate=fs.readFileSync('autogenerate/template/TemplateSerivce.js', 'utf8');
var serviceImplTemplate=fs.readFileSync('autogenerate/template/TemplateSerivceImpl.ejs', 'utf8');*/
var indexPageTemplate=fs.readFileSync('autogenerate/template/IndexTemplate.jsp', 'utf8');

//Create directory
if (!fs.existsSync("./autogenerate/hxxc")){
    fs.mkdirSync("./autogenerate/hxxc");
}
if (!fs.existsSync("./autogenerate/hxxc/controller")){
    fs.mkdirSync("./autogenerate/hxxc/controller");
}
if (!fs.existsSync("./autogenerate/hxxc/service/")){
    fs.mkdirSync("./autogenerate/hxxc/service/");
}
if (!fs.existsSync("./autogenerate/hxxc/service/impl")){
    fs.mkdirSync("./autogenerate/hxxc/service/impl");
}
if (!fs.existsSync("./autogenerate/hxxc/pages")){
    fs.mkdirSync("./autogenerate/hxxc/pages");
}

modelObjs.forEach(function(modelObj){
 /* //Generate controllerTmpelate
  var controllerTmpelateOutput = Mustache.render(controllerTmpelate, modelObj);
  if (!fs.existsSync("./autogenerate/"+modelObj.config.path+"/")){
      mkdirp.sync("./autogenerate/"+modelObj.config.path+"/");
  }
  var controllerPath = "./autogenerate///"+modelObj.config.path+"/"+modelName+".js";
  fs.writeFile(controllerPath, controllerTmpelateOutput, function(err) {
      if(err) {
        console.log("error----"+controllerPath);
        return console.log(err);
      }
      console.log(controllerPath+"--- Generate Finished");
  });
  //Generate routeTemplate
  var routeTemplateOutput = Mustache.render(routeTemplate, modelObj);
  if (!fs.existsSync("./autogenerate/generateddemo/route/"+modelObj.config.path+"/")){
        mkdirp.sync("./autogenerate/generateddemo/route/"+modelObj.config.path+"/");
      }
  var routePath = "./autogenerate/generateddemo/route/"+modelObj.config.path+"/"+modelName+".js";
  fs.writeFile(routePath, routeTemplateOutput, function(err) {
      if(err) {
        console.log("error----"+routePath);
        return console.log(err);
      }
      console.log(routePath+"--- Generate Finished");
  });
  //Generate ejsTemplate
  var ejsTemplateOutput = Mustache.render(ejsTemplate, modelObj);
  if (!fs.existsSync("./autogenerate/generateddemo/ejs/"+modelObj.config.path+"/")){
    mkdirp.sync("./autogenerate/generateddemo/ejs/"+modelObj.config.path+"/");
  }
  var ejsPath = "./autogenerate/generateddemo/ejs/"+modelObj.config.path+"/"+modelName+".ejs";
  fs.writeFile(ejsPath, ejsTemplateOutput, function(err) {
      if(err) {
        console.log("error----"+ejsPath);
        return console.log(err);
      }
      console.log(ejsPath+"--- Generate Finished");
  });*/
  //Generate ejsTemplate
  var indexPageTemplateOutput = Mustache.render(indexPageTemplate, modelObj);
  if (!fs.existsSync("./autogenerate/hxxc/pages/"+modelObj.config.path+"/")){
    mkdirp.sync("./autogenerate/hxxc/pages/"+modelObj.config.path+"/");
  }
  var jspPath = "./autogenerate/hxxc/pages/"+modelObj.config.path+"/index.jsp";
  fs.writeFile(jspPath, indexPageTemplateOutput, function(err) {
      if(err) {
        console.log("error----"+jspPath);
        return console.log(err);
      }
      console.log(jspPath+"--- Generate Finished");
  });

});

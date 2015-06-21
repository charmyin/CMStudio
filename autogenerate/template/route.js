//{{ config.cnName }}管理Ctrl
var {{ config.name }}Ctrl = require('../app/controllers/{{ config.path }}/{{ config.name }}');


//{{ config.cnName }}管理
app.get("/{{ config.path }}/{{ config.name }}/toSearchList", {{ config.name }}Ctrl.toSearchList);
app.post("/{{ config.path }}/{{ config.name }}/searchList", {{ config.name }}Ctrl.searchList);
app.post("/{{ config.path }}/{{ config.name }}/save", {{ config.name }}Ctrl.add);
app.post("/{{ config.path }}/{{ config.name }}/remove", {{ config.name }}Ctrl.remove);
app.post("/{{ config.path }}/{{ config.name }}/update", {{ config.name }}Ctrl.updateById);

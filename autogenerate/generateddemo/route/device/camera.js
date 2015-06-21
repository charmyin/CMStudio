//相机信息管理Ctrl
var cameraCtrl = require('../app/controllers/device/camera');


//相机信息管理
app.get("/device/camera/toSearchList", cameraCtrl.toSearchList);
app.post("/device/camera/searchList", cameraCtrl.searchList);
app.post("/device/camera/save", cameraCtrl.add);
app.post("/device/camera/remove", cameraCtrl.remove);
app.post("/device/camera/update", cameraCtrl.updateById);

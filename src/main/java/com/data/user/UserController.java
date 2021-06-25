package com.data.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * UserController:
 *
 * @author sunchen
 * @date 2021/6/22 9:21 下午
 */
@RestController
public class UserController {

    @RequestMapping("/api/getToken")
    public JSONObject getToken(@RequestParam String userName, @RequestParam String password){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", UUID.randomUUID().toString());
        jsonObject.put("id", 1);
        jsonObject.put("rid", 1);
        jsonObject.put("username", "mengshiyu");
        jsonObject.put("email", "mengshiyu@qq.com");

        JSONObject j = new JSONObject();
        j.put("data", jsonObject);
        JSONObject m = new JSONObject();
        m.put("msg","success");
        m.put("status",200);
        j.put("meta",m);
        return j;
    }

    @RequestMapping("/api/users")
    public JSONObject getStudentList(@RequestParam(required = false)String query,
                                    @RequestParam int pagenum,
                                    @RequestParam int pagesize){
        JSONArray array = new JSONArray();
        for (int i = 1; i <= 100; i++) {
            JSONObject object1 = new JSONObject();
            object1.put("token", UUID.randomUUID().toString());
            object1.put("id", i);
            object1.put("username", "张三"+i);
            object1.put("mobile", "15443332222"+i);
            object1.put("email", "mengshiyu@qq.com"+i);
            object1.put("type", 1);
            object1.put("create_time", "2020-10-12T12:12:13.000Z");
            object1.put("mg_state", true);
            object1.put("role_name", "超级管理元"+i);
            array.add(object1);
        }
        List<Object> objects = array.subList((pagenum - 1) * pagesize, pagenum * pagesize);
        JSONObject o = new JSONObject();
        o.put("totalpage",Integer.valueOf((int) Math.ceil(100/pagesize)));
        o.put("pageNum",pagenum);
        o.put("users",objects);
        JSONObject j = new JSONObject();
        j.put("data", o);
        JSONObject m = new JSONObject();
        m.put("msg","success");
        m.put("status",200);
        j.put("meta",m);
        return j;
    }





    @RequestMapping("/api/menus")
    public JSONObject getMenuList(){


        JSONObject fatherUser = new JSONObject();
        fatherUser.put("id", 1);
        fatherUser.put("authName", "用户管理");
        fatherUser.put("path", "users");
        JSONArray userchildrenArray = new JSONArray();
        JSONObject userchildrenObj = new JSONObject();
        userchildrenObj.put("id", 104);
        userchildrenObj.put("authName", "用户列表");
        userchildrenObj.put("children", new JSONArray());
        userchildrenObj.put("path", "users");
        userchildrenArray.add(userchildrenObj);
        fatherUser.put("children", userchildrenArray);

        JSONObject fatherAuth = new JSONObject();
        fatherAuth.put("id", 2);
        fatherAuth.put("authName", "权限管理");
        fatherAuth.put("path", "auths");
        JSONArray authchildrenArray = new JSONArray();
        JSONObject authchildrenObj = new JSONObject();
        authchildrenObj.put("id", 105);
        authchildrenObj.put("authName", "角色列表");
        authchildrenObj.put("path","roles");
        authchildrenObj.put("children", new JSONArray());
        JSONObject authchildrenObj1 = new JSONObject();
        authchildrenObj1.put("id", 106);
        authchildrenObj1.put("authName", "权限列表");
        authchildrenObj1.put("path","auths");
        authchildrenObj1.put("children", new JSONArray());
        authchildrenArray.add(authchildrenObj);
        authchildrenArray.add(authchildrenObj1);
        fatherAuth.put("children", authchildrenArray);


        JSONObject fatherProduct = new JSONObject();
        fatherProduct.put("id", 3);
        fatherProduct.put("authName", "商品管理");
        fatherProduct.put("path", "product");
        JSONArray childrenArray = new JSONArray();
        JSONObject childrenObj = new JSONObject();
        childrenObj.put("id", 107);
        childrenObj.put("authName", "商品列表");
        childrenObj.put("path","products");
        childrenObj.put("children", new JSONArray());

        JSONObject childrenObj1 = new JSONObject();
        childrenObj1.put("id", 108);
        childrenObj1.put("authName", "分类参数");
        childrenObj1.put("path","classParam");
        childrenObj1.put("children", new JSONArray());

        JSONObject childrenObj2 = new JSONObject();
        childrenObj2.put("id", 109);
        childrenObj2.put("authName", "商品分类");
        childrenObj2.put("path","category");
        childrenObj2.put("children", new JSONArray());

        childrenArray.add(childrenObj);
        childrenArray.add(childrenObj1);
        childrenArray.add(childrenObj2);
        fatherProduct.put("children", childrenArray);



        JSONObject fatherOrder = new JSONObject();
        fatherOrder.put("id", 4);
        fatherOrder.put("authName", "订单管理");
        fatherOrder.put("path", "auths");
        JSONArray orderchildrenArray = new JSONArray();
        JSONObject orderchildrenObj = new JSONObject();
        orderchildrenObj.put("id", 111);
        orderchildrenObj.put("authName", "是否付款");
        orderchildrenObj.put("children", new JSONArray());
        orderchildrenObj.put("path","isPayMent");

        JSONObject orderchildrenObj1 = new JSONObject();
        orderchildrenObj1.put("id", 112);
        orderchildrenObj1.put("authName", "是否发货");
        orderchildrenObj1.put("children", new JSONArray());
        orderchildrenObj1.put("path","isSend");

        JSONObject orderchildrenObj2 = new JSONObject();
        orderchildrenObj2.put("id", 113);
        orderchildrenObj2.put("authName", "订单信息");
        orderchildrenObj2.put("children", new JSONArray());
        orderchildrenObj2.put("path","order");

        orderchildrenArray.add(orderchildrenObj);
        orderchildrenArray.add(orderchildrenObj1);
        orderchildrenArray.add(orderchildrenObj2);

        fatherOrder.put("children", orderchildrenArray);


        JSONArray data = new JSONArray();
        data.add(fatherUser);
        data.add(fatherAuth);
        data.add(fatherProduct);
        data.add(fatherOrder);
        JSONObject j = new JSONObject();
        j.put("data", data);
        JSONObject m = new JSONObject();
        m.put("msg","success");
        m.put("status",200);
        j.put("meta",m);
        return j;
    }

}

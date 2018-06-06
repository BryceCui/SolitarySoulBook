package com.cuipengyu.solitarysoulbook.activitys;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.BaseActivity;
import com.cuipengyu.solitarysoulbook.entity.Constants;
import com.cuipengyu.solitarysoulbook.entity.bean.UserBean;
import com.cuipengyu.solitarysoulbook.entity.db.UserBeanDao;
import com.cuipengyu.solitarysoulbook.utils.DbUtils;

public class RegisteredActivity extends BaseActivity implements View.OnClickListener {
    EditText username_et, password_et;
    UserBeanDao userBeanDao;

    @Override

    public int bindViewLayout() {
        return R.layout.activity_registered;
    }

    @Override
    public void initView() {
        setTitleBar("注册界面");
        setLeftImage();
        setLeftBarBack();
        username_et = findViewById(R.id.username_register_et);
        password_et = findViewById(R.id.password_register_et);
    }

    @Override
    public void initData() {
        userBeanDao = DbUtils.getSession().getUserBeanDao();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_registered:
                String username = username_et.getText().toString().trim();
                String password = password_et.getText().toString().trim();
                if (username.equals("")||password.equals("")){
                    Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                UserBean bean=userBeanDao.queryBuilder().where(UserBeanDao.Properties.Name.eq(username)).unique();
//                boolean isEmpty = userBeanDao.queryRaw("where name=?", username).isEmpty();
                if (bean==null) {
                    userBeanDao.insert(new UserBean(null, username, password,null));
                    Constants.USER_NAME = username;
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(this, "注册成功,即将跳转主界面", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "此用户名已经存在请更换用户名", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

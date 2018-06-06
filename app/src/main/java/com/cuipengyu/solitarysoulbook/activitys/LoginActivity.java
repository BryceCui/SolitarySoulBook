package com.cuipengyu.solitarysoulbook.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.BaseActivity;
import com.cuipengyu.solitarysoulbook.entity.Constants;
import com.cuipengyu.solitarysoulbook.entity.bean.UserBean;
import com.cuipengyu.solitarysoulbook.entity.db.UserBeanDao;
import com.cuipengyu.solitarysoulbook.utils.DbUtils;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    EditText username_et, password_et;
    UserBeanDao userBeanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int bindViewLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        setTitleBar("登录界面");
        userBeanDao = DbUtils.getSession().getUserBeanDao();
    }

    @Override
    public void initView() {
        username_et = findViewById(R.id.username_login_et);
        password_et = findViewById(R.id.password_login_et);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.check_login:
                String username = username_et.getText().toString().trim();
                String password = password_et.getText().toString().trim();
                UserBean query = userBeanDao.queryBuilder().where(UserBeanDao.Properties.Name.eq(username), UserBeanDao.Properties.PassWord.eq(password)).unique();
                if (query != null) {
                    Constants.USER_NAME = username;
                    Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "用户名或者密码错误", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login_to_register:
                Intent intent = new Intent(this, RegisteredActivity.class);
                startActivity(intent);
                break;
        }
    }
}

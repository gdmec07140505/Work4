package s07140505.gdmec.edu.cn.work4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ContactsMessageActivity extends AppCompatActivity {
    private TextView name,num,danwei,qq,dizhi;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
        name= (TextView) findViewById(R.id.name);
        num= (TextView) findViewById(R.id.num);
        danwei= (TextView) findViewById(R.id.danwei);
        qq= (TextView) findViewById(R.id.qq);
        dizhi= (TextView) findViewById(R.id.dizhi);
        Bundle localBundle=getIntent().getExtras();
        int id=localBundle.getInt("user_ID");
        ContactsTable ct=new ContactsTable(this);
        user=ct.getUserByID(id);
        name.setText("姓名："+user.getName());
        num.setText("电话："+user.getMoblie());
        qq.setText("QQ："+user.getQq());
        danwei.setText("单位："+user.getDanwei());
        dizhi.setText("地址："+user.getAddress());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(Menu.NONE, 1, Menu.NONE, "返回");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                finish();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
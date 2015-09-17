import cn.easemob.server.example.comm.Constants;
import cn.easemob.server.example.comm.Roles;
import cn.easemob.server.example.httpclient.apidemo.EasemobChatGroups;
import cn.easemob.server.example.httpclient.vo.ClientSecretCredential;
import cn.easemob.server.example.httpclient.vo.Credential;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2015/9/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Huanxin {

    @Test
    public void getToken() {
        String url = "https://a1.easemob.com/wedo/scud/token" ;

        Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID,
                Constants.APP_CLIENT_SECRET,"client_credentials");
        System.out.println(credential.getToken());
    }
}

package com.manager.appbanhang.Authentication;

import android.util.Log;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.collect.Lists;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class AccessToken {
    private static final String firebaseMessagingScope ="https://www.googleapis.com/auth/firebase.messaging";
    public String getAccessToken(){
       try{
           String jsonString ="{\n" +
                   "  \"type\": \"service_account\",\n" +
                   "  \"project_id\": \"appbanhang-b345c\",\n" +
                   "  \"private_key_id\": \"e11390f74a6cc37b7eda313a85f0f3748d1f7e34\",\n" +
                   "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCGgOhKgPunJqpN\\nxKkv3Fg0cLMG1rg6w2uh8ZEoc2slwoBhOpFv6baoGv0aZMJnjawCSJ2dxaSa7Dsc\\n4Un4u5E0N5WoWu4IPe3irZKplgG7VD8NmfHH6QO6f4SyJLIpFpsRvb+wi3wLiJNb\\nzo7n/5LJR6LUPlPMaVlL5jnzANRKv073g5kPzTm1ECCMul6DWkbD+Kp2cpcOwH46\\nLdS8L/82VobBYFLnvtwuvxs4tn7uMT4dhONGjAnZ9ll/j4FSxKzkuTxb6sc2dl9U\\n1/cGZKU9LVK6leLagaQKTallGrApNczgk0fQMzSa0kdC4P1ZFnl8kQsAR4mxgoUJ\\nDAut3WDhAgMBAAECggEAESa8+EssS+tFSjdqr5y0brN0UMnuoierQPJR5UuafS0p\\nO7zBMPknfpY6cjQpChVpurzRxH2geNTo0Mz1ps8cPAFehyJZOhnTjNU/X3qua9nZ\\nVH2t9CUFgbGjFShSw6SLT57Xo8aVoc7fo5RaasEPUbVd0RzFiMpiRahs+UzAxWsi\\nbWG3MgxDocJeHYerJzbcrmB2jX9qfJfRCXPzlI7SgXhEIZIRojuDqXlFJGuB2SgF\\nNgUOc5uXOQh9y/a0z3ndy2+SwwZLJt+wu7VTCjmSAmobxWFbPSmi7vccyDElnxlp\\niOMMsPJjUEpUScY/VHB3lIuTEQXoJycfFO035U3m8QKBgQC8NFQu/o5N3HhqfsOi\\n4O2W53yp7YSIYJL/r4Rsz0qN9EH0+SksLj1yx2M1vwno3evpsVvmefV+mrAVjj5+\\nnixk9VXFmkgszzoLUGFVcplJDpvGJyRCXJW26h5WdAQLFtrtyO/hEqxyZtAlhuDk\\n9Q1/BOkVY14uO14hG6ZY1h1RHQKBgQC29HBO14TlmKGEflUPyXNWZ9RZjjr6L8bS\\n5Yza4BwmqGbM+FVw6JV6xeT9p3syk+98HyP62VSBHoGDzGs9RSlxR8i7BN93K5FB\\nQgDa3/W/vVhps1wK1XGi6gY45PXALKMVq9KmfNuSywo7VyPfFgTferbza3CoaOZ5\\nTsU3vkznlQKBgCNNWYEbJIw8T6YCMyYpNMe6kiq5r8N5AfnN7XQPifeImpqd5fgw\\nkDwoZFafh/dTMT47k3Zu5/qgf2j7roNHniIt1rHjF6i4EjYvFKLXhSMsB1Iq42VD\\nQp312kzzJBMEbXpu13gLbadj26U+YI7x3F84B5SIeBEJ98UUD6bYOtWNAoGABKgM\\nA7WwiDduRPUCLTivWs+hs/XOwndc1BlLkHaLwOAgXa8kXY9N3qYSwfH1TMA/JCWW\\nwNevMuoX2cbmI9USzPNKxJfHKD1PoR2Q8AErPAoRqf8KpKvyDHdwWRpatt1r+S06\\nW0pqPD42sHPzUcY5sYZqCZ/+agrhcszycAzD4FECgYBQCKiCV0gLyI3saN1Mnam5\\nym+PrXRFNmtn7iM95dA0xGw5xfT6ZKFapHq6jgeIdGYQSq1wjRuGLDwt/ZG6hl8i\\nMamqk7fYiPhckRUZl4+RdeKMSKM9vbVpHmKVdvCapUXr/XRfvnHdz7dZg9gbQtKr\\nT+Yh+vLEm8gOvTkrrBe8zA==\\n-----END PRIVATE KEY-----\\n\",\n" +
                   "  \"client_email\": \"firebase-adminsdk-i1ffi@appbanhang-b345c.iam.gserviceaccount.com\",\n" +
                   "  \"client_id\": \"100478237149244448769\",\n" +
                   "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                   "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                   "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                   "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-i1ffi%40appbanhang-b345c.iam.gserviceaccount.com\",\n" +
                   "  \"universe_domain\": \"googleapis.com\"\n" +
                   "}\n";

           InputStream stream = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));

           GoogleCredentials googleCredentials = GoogleCredentials.fromStream(stream).createScoped(Lists.newArrayList(firebaseMessagingScope));

           googleCredentials.refresh();

           return googleCredentials.getAccessToken().getTokenValue();

       }catch(Exception e){
           Log.e("error", ""  + e.getMessage());
           return null;
       }
    }
}

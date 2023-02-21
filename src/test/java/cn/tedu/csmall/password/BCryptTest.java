package cn.tedu.csmall.password;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptTest {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    void encode() {
        String rawPassword = "123456";
        System.out.println("原文：" + rawPassword);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            String encodedPassword = passwordEncoder.encode(rawPassword);
            System.out.println("密文：" + encodedPassword);
        }
        long end = System.currentTimeMillis();

        System.out.println("耗时：" + (end - start));
    }

    //    原文：123456
    //    密文：$2a$10$Xf7irDXVnJTAJKROCQXK.uZW90F0yFGCbMt8LsYVZUFaHpMlnCny
    //    密文：$2a$10$g7d7g8/W38S/KKhBscPXW.C52f3yy2edGRFv0X4mkeEXEKQ6cszb2
    //    密文：$2a$10$6YsaN4W71KGle/CFD6ncMe3FOVOWC2Qd9RVsIA2lF5htHNFzeMGzi
    //    密文：$2a$10$hgv85JG70GJaZyBNN5/kvOk.LfFMVWGefsHaqA6mjEPwj0TKSjcIu
    //    密文：$2a$10$hg5RB00jGPCTC70ZUOwTROnIhFsjI6Rri.rYeVGauWrm1Ns4uAo/O
    //    密文：$2a$10$LNk.YJDMQc3We7501W28jOCnreRDdTV1craWCSCU0xLNDI9codk/W
    //    密文：$2a$10$aTcBRRT.oHeEyA6p16MXYuASbrhg4ys8q1xR2pz9DYoUyN7mvxyeS
    //    密文：$2a$10$dvG0S4Wfl1NbjJByrvhVueJn77zzWqE3L39tG2GM1EoKXo.TPSAri
    //    密文：$2a$10$dEbmN.6/keROd/1CHj/YmOiOhydbP1zipMQWPAxj0DKLcfF8/wufq
    //    密文：$2a$10$vNAQhMGXYetQB0/pe4BfZ.XHM2sOUpDZ0N5B.eaqh0ayFfzeccAJu

    @Test
    void matches() {
        String rawPassword = "123456";
        System.out.println("原文：" + rawPassword);

        String encodedPassword = "$2a$10$vNAQhMGXYetQB0/pe4BfZ.XHM2sOUpDZ0N5B.eaqh0ayFfzeccAJu";
        System.out.println("密文：" + encodedPassword);

        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
        System.out.println("匹配结果：" + matches);
    }
}

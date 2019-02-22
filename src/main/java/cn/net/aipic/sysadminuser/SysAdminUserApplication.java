package cn.net.aipic.sysadminuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SysAdminUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysAdminUserApplication.class, args);
    }

}

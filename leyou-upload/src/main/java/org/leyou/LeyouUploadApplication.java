package org.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @auther ChenLei
 * @create 2020-10-12:09 PM
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LeyouUploadApplication {
    public static void main(String[] args) {

        SpringApplication.run(LeyouUploadApplication.class);

    }
}

package gmcc.hxs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = "com.imooc.dataobject.mapper")
@EnableCaching
public class DnsApplication {
    public static void main(String[] args) {
        SpringApplication.run(DnsApplication.class, args);
    }
}

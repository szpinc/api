package org.szpinc.api.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.szpinc.api.model.Province;
import org.szpinc.api.service.ProvinceService;

import javax.annotation.Resource;

/**
 * Created by Ghost Dog on 2017/8/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring-mybatis.xml"})
public class ProvinceTest extends TestLog {

    @Resource
    private ProvinceService provinceService ;

    @Test
    public void testGet () throws Exception {

        Province province = provinceService.getProvinceById(1);

        if (log.isDebugEnabled()) {
            log.debug(province.getProvinceName());
        }

    }
}

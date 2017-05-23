package com.tsoftlatam.tab.sources;

import com.tsoftlatam.tab.controllers.ExtractorController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HpbsmextractorApplicationTests {

    @InjectMocks
    ExtractorController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    @Test
	public void contextLoads() throws IOException {
		String[] metricas;

		metricas = controller.getMetricas();

		assertThat(metricas.length > 0);
	}

	@Test
	public void extraerEntreFechas() throws IOException {
		String[] metricas;

		//1-5-2017
		String date1 = "1493596800";

		//1-6-2017
		String date2 = "1496275200";

		metricas =  controller.getMetricasEntreFechas(date1,date2);

		assertThat(metricas.length > 0);


	}

}

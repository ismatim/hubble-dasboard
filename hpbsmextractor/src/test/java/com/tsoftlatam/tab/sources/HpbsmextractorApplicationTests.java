package com.tsoftlatam.tab.sources;

import com.tsoftlatam.tab.controllers.ExtractorController;
import com.tsoftlatam.tab.entities.SampleV8;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = ExtractorController.class, secure = false)
public class HpbsmextractorApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    //Creo un mock que será la respuesta forzada que devolverán los metodos a testear. Se hace esto
    //para poder ejecutar los assert con una respuesta conocida.
    SampleV8 mockSample = new SampleV8("Benchmark Home Banking","Santander - Carga Portal", "Tsoft Agente Interno",
            0, "Pass", 0.0, 3152.0, 1.495646611E9,-2147468401 );

    //Instancio al controlador del extractor para poder levantarlo con Spring y utilizarlo.
    @MockBean
    private ExtractorController controller;

    @Test
    public void checkConnection() throws Exception {
        //Llamo al Mockito, con el controlador del extractor y pido que retorne el mockSample que definí al principio
        Mockito.when(controller.getMetricas()).thenReturn(mockSample.toString());

        //Armo la petición llamando a la url del metodo del controller y la ejecuto
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/res").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Verifico que la respuesta sea de codigo 200 = OK
        Assert.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
	public void contextLoads() throws Exception {

        Mockito.when(controller.getMetricas()).thenReturn(mockSample.toString());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/res").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Imprimo el resultado de la ejecución del result, que dará el mock
        System.out.println(result.getResponse().getContentAsString());

        //El test comparará 2 estructuras JSON que deberán ser iguales para pasar.
        String expected = "{applicationName='Benchmark Home Banking', transactionName='Santander - Carga Portal', locationName='Tsoft Agente Interno', errorCount=0, availabilityStatus='Pass', transactionStatus=0.0, responseTime=3152.0, timestamp=1.495646611E9, id=-2147468401}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	@Test
	public void extraerEntreFechas() throws Exception {

        //1-5-2017
        String date1 = "1493596800";
        //1-6-2017
        String date2 = "1496275200";
        Mockito.when(controller.getMetricasEntreFechas(date1, date2)).thenReturn(mockSample.toString());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/fechaInicial/"+ date1+"/fechaFinal/"+ date2).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());

        String expected = "{applicationName='Benchmark Home Banking', transactionName='Santander - Carga Portal', locationName='Tsoft Agente Interno', errorCount=0, availabilityStatus='Pass', transactionStatus=0.0, responseTime=3152.0, timestamp=1.495646611E9, id=-2147468401}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

}

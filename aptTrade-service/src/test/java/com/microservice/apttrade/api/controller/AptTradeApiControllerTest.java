package com.microservice.apttrade.api.controller;

import com.microservice.apttrade.api.service.RequestService;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


public class AptTradeApiControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(AptTradeApiControllerTest.class);

	private MockMvc mockMvc;

	@InjectMocks
	private AptTradeApiController aptTradeApiController;
//	@Mock
//	private AptTradeApiService aptTradeApiService;
	@Mock
	private RequestService requestService;

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(aptTradeApiController).build();
	}
//
//	@Test
//	public void listByAmdCodeAndYearTest() throws Exception{
//		String rnMgtSn = TestConfiguration.RN_MGT_SN;
//		int  page      = TestConfiguration.PAGE;
//		int  pageSize  = TestConfiguration.PAGE_SIZE;
//		long buldMnnm = TestConfiguration.BULD_MNNM;
//		long buldSlno = TestConfiguration.BULD_SLNO;
//
//
//		doNothing().when(aptTradeApiService).listByAddrCodeAndYear(rnMgtSn,buldMnnm, buldSlno, page);
//		MvcResult result = this.mockMvc.perform(get("/list/"+rnMgtSn)
//				.param("quarter",      String.valueOf(page))
//				.param("buldMnnm", String.valueOf(buldMnnm))
//				.param("buldSlno", String.valueOf(buldSlno))
//				.contentType( MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andDo(print())
//				.andExpect(jsonPath("$.content").isArray())
//				.andReturn();
//	}




}

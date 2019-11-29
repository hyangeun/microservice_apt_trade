package com.microservice.apttrade.api.service;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.microservice.apttrade.AptTradeApplication;
import com.microservice.apttrade.TestConfiguration;
import com.microservice.apttrade.address.dto.AddressData;
import com.microservice.apttrade.address.service.AddressApiService;
import com.microservice.apttrade.api.dto.ItemsResponse;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AptTradeApplication.class , properties = {"feign.post-api.url:http://google.com",
																	"feign.client.config.default.connect-timeout=60000",
																	"feign.client.config.default.read-timeout=60000",
																	"feign.hystrix.enabled=true"})
@EnableFeignClients(clients = AptTradeApiServiceTest.Address.class)
@ContextConfiguration(initializers = AptTradeApiServiceTest.RandomPortInitializer.class)
public class AptTradeApiServiceTest {
	private final Logger logger = LoggerFactory.getLogger(AptTradeApiServiceTest.class);

	MockMvc mockMvc;

	@ClassRule
	public static WireMockClassRule wireMockRule = new WireMockClassRule(
			wireMockConfig().dynamicPort()
	);

	@FeignClient(url = "${feign.post-api.url}", name="address-front", configuration = FeignClientsConfiguration.class)
	public interface Address {
		@GetMapping("/v1/address/list")
		AddressData listAddressByKeyword(@RequestParam(value = "page") Integer page,
										 @RequestParam(value = "pageSize") Integer pageSize,
										 @RequestParam(value = "keyword") String keyword);

	}

	@InjectMocks
	private AptTradeApiService aptTradeApiService;

	@Mock
	private AddressApiService addressApiService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(aptTradeApiService).build();
//		aptTradeApiService= new AptTradeApiService(TestConfiguration.AUTH_KEY, TestConfiguration.URL);
//		aptTradeApiService= new AptTradeApiService(TestConfiguration.AUTH_TEST_KEY, TestConfiguration.URL);

	}

	@Test
	public void listByAmdCodeAndYear() throws Exception {
		String rnMgtSn  = TestConfiguration.RN_MGT_SN;
		int  quarter    = TestConfiguration.QUARTER;
		long buldMnnm  = TestConfiguration.BULD_MNNM;
		long buldSlno  = TestConfiguration.BULD_SLNO;

		List<ItemsResponse> items= aptTradeApiService.listByAddrCodeAndQuarter(rnMgtSn,buldMnnm, buldSlno, quarter);
//		assertTrue(items.size() > 0);
		logger.info(items.toString());
		logger.info("====================================> " + items.size());
	}

	@Test
	public void listByAddrCodeAndYearAndMonth() throws Exception {

		String rnMgtSn  = TestConfiguration.RN_MGT_SN;
		String dealYm   = TestConfiguration.DEAL_YM;
		long buldMnnm  = TestConfiguration.BULD_MNNM;
		long buldSlno  = TestConfiguration.BULD_SLNO;

		List<ItemsResponse> items= aptTradeApiService.listByAddrCodeAndDealYm(rnMgtSn,buldMnnm, buldSlno, dealYm);
//		assertTrue(items.size() > 0);
		logger.info(items.toString());
		logger.debug(items.toString());
	}

//	@Test(expected = AddressDataException.class)
//	@Test
//	public void getByCondition_AddressDataException(){
//		String address = TestConfiguration.ADDRESS;
//		String price   = TestConfiguration.PRICE;
//		String area    = TestConfiguration.AREA;
//		int    quarter  = TestConfiguration.QUARTER;
//
//		stubFor(get(urlEqualTo("/v1/address/list"))
//				.willReturn(aResponse()
//						.withStatus(HttpStatus.OK.value())
//						.withBody(TestConfiguration.setAddressData().toString())));
//
//		when(addressApiService.listAddressByKeyword(1, 1000, address)).thenReturn(TestConfiguration.setAddressDataList());
//		List<ItemsResponse> items = aptTradeApiService.getByCondition(address, price, area, quarter);
//		logger.info(items.toString());
//	}
//
////	@Test
//	public void getByCondition(){
//		String address = TestConfiguration.ADDRESS;
//		String price   = TestConfiguration.PRICE;
//		String area    = TestConfiguration.AREA;
//		int    quarter  = TestConfiguration.QUARTER;
//
//		stubFor(get(urlEqualTo("/v1/address/list"))
//				.willReturn(aResponse()
//						.withStatus(HttpStatus.OK.value())
//						.withBody(TestConfiguration.setAddressData().toString())));
//
//		when(addressApiService.listAddressByKeyword(1, 10, address)).thenReturn(TestConfiguration.setAddressData());
//		List<ItemsResponse> items = aptTradeApiService.getByCondition(address, price, area, quarter);
//		logger.info(items.toString());
//	}

	public static class RandomPortInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		@Override
		public void initialize(ConfigurableApplicationContext applicationContext) {

			// If the next statement is commented out,
			// Feign will go to google.com instead of localhost
			TestPropertySourceUtils
					.addInlinedPropertiesToEnvironment(applicationContext,
							"google.url=" + "http://localhost:" + wireMockRule.port()
					);
		}
	}
}

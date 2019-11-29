package com.microservice.apttrade;

import com.microservice.apttrade.address.dto.AddressData;
import com.microservice.apttrade.address.dto.AddressDetailData;
import okhttp3.HttpUrl;

import java.util.ArrayList;
import java.util.List;

public class TestConfiguration {
	final static public String RN_MGT_SN = "415703209136";
	final static public String LAWD_CD  = "41570";
	final static public String DEAL_YM  = "201902";
	final static public String URL      = "http://182.252.131.40:9000/apiservice/4357";
	final static public String AUTH_KEY = "0543fgrib0ggh48ou4rtbgad1ov8820n8";
	final static public String AUTH_TEST_KEY = "1234";
	final static public String ADDRESS  = "서울시 삼성동";
	final static public String PRICE    = "35000-50000";
	final static public String AREA     = "30-60";
	final static public long   BULD_MNNM = 149;
	final static public long   BULD_SLNO = 0;
	final static public int    QUARTER    = 3;
	final static public int    PAGE       = 1;
	final static public int    PAGE_SIZE  = 10;


	public static AddressData setAddressDataList(){
		AddressData data = new AddressData();
		List<AddressDetailData> detailList = new ArrayList<>();
		AddressDetailData detail1 = new AddressDetailData("",
															"경기도 김포시 금포로 1127 (운양동)",
															"경기도 김포시 운양동 5-6",
															"10092",
															"4157010300",
															"415703209136",
															"4157010300100020005000001",
															"",
															"0",
															"경기도",
															"김포시",
															"운양동",
															"금포로",
															"1127",
															"0",
															"5",
															"6" ,
															"1");

		AddressDetailData detail2 = new AddressDetailData("",
													"경기도 김포시 금포로 1190 (운양동)",
													"경기도 김포시 운양동 7-14",
													"10075",
													"4157010300",
													"415703209136",
													"4157010300100020005000001",
													"",
													"0",
													"경기도",
													"김포시",
													"운양동",
													"금포로",
													"1190",
													"0",
													"7",
													"14",
													"1");

		detailList.add(detail1);
		detailList.add(detail2);
		data.setJuso(detailList);

		return data;
	}

	public static AddressData setAddressData(){
		AddressData data = new AddressData();
		List<AddressDetailData> detailList = new ArrayList<>();
		AddressDetailData detail1 = new AddressDetailData("",
															"경기도 김포시 금포로 1127 (운양동)",
															"경기도 김포시 운양동 5-6",
															"10092",
															"4157010300",
															"415703209136",
															"4157010300100020005000001",
															"",
															"0",
															"경기도",
															"김포시",
															"운양동",
															"금포로",
															"1127",
															"0",
															"5",
															"6",
															"1");

		detailList.add(detail1);
		data.setJuso(detailList);

		return data;
	}
}

package com.briup.service.impl;

import com.briup.bean.ShopAddress;
import com.briup.dao.ShopAddressMapper;
import com.briup.service.IShopAddressService;
import com.briup.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author horry
 * @Description TODO
 * @date 2024/1/4-9:26
 */
public class ShopAddressServiceImpl implements IShopAddressService {

	private static final ShopAddressMapper shopAddressMapper;

	static {
		SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true);
		shopAddressMapper = sqlSession.getMapper(ShopAddressMapper.class);
	}

	@Override
	public List<ShopAddress> findAddressByCustomerId(Integer id) {
		return shopAddressMapper.findAddressByCustomerId(id);
	}

	@Override
	public void saveAddress(ShopAddress sd) {
		if (checkStr(sd.getReceiveName())) {
			throw new RuntimeException("接收姓名不能为空");
		}
		if (checkStr(sd.getAddress())) {
			throw new RuntimeException("接收人地址不能为空");
		}
		if (checkStr(sd.getPhone())) {
			throw new RuntimeException("接收人联系方式不能为空");
		}

		shopAddressMapper.saveAddress(sd);
	}

	private boolean checkStr(String str) {
		return str == null || str.trim().isEmpty();
	}

}

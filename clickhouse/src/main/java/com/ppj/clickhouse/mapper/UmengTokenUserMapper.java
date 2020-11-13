package com.ppj.clickhouse.mapper;

import com.ppj.clickhouse.entity.UmengTokenUser;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pipi
 * @date 2020/6/5 12:08
 */
@Repository
public interface UmengTokenUserMapper {

  List<UmengTokenUser> findAll();
}

package io.admin.modules.borrow.service.impl;

import io.admin.modules.borrow.dao.ZbJrsqmxbDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.borrow.dao.ZbJrsqbDao;
import io.admin.modules.borrow.entity.ZbJrsqbEntity;
import io.admin.modules.borrow.service.ZbJrsqbService;
import org.springframework.transaction.annotation.Transactional;


@Service("zbJrsqbService")
public class ZbJrsqbServiceImpl extends ServiceImpl<ZbJrsqbDao, ZbJrsqbEntity> implements ZbJrsqbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String sqmc = (String)params.get("sqmc");
        String sqbmmc = (String)params.get("sqbmmc");
        String jcbmmc = (String)params.get("jcbmmc");

        Page<ZbJrsqbEntity> page = this.selectPage(
                new Query<ZbJrsqbEntity>(params).getPage(),
                new EntityWrapper<ZbJrsqbEntity>().
                        like(StringUtils.isNotBlank(sqmc), "sqmc", sqmc).
                        like(StringUtils.isNotBlank(sqbmmc), "sqbmmc", sqbmmc).
                        like(StringUtils.isNotBlank(jcbmmc), "jcbmmc", jcbmmc)
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageByZtm(Map<String, Object> params,String[] ztm) {
        String sqmc = (String)params.get("sqmc");
        String sqbmmc = (String)params.get("sqbmmc");
        String jcbmmc = (String)params.get("jcbmmc");

        Page<ZbJrsqbEntity> page = this.selectPage(
                new Query<ZbJrsqbEntity>(params).getPage(),
                new EntityWrapper<ZbJrsqbEntity>().
                        like(StringUtils.isNotBlank(sqmc), "sqmc", sqmc).
                        like(StringUtils.isNotBlank(sqbmmc), "sqbmmc", sqbmmc).
                        like(StringUtils.isNotBlank(jcbmmc), "jcbmmc", jcbmmc).
                        in("ztm", ztm)
        );
        return new PageUtils(page);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByJrsqIds(String[] jrsqIds) {
        baseMapper.deleteByJrsqIds(jrsqIds);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ZbJrsqbEntity selectByJrsqid(String jrsqid){
        List<ZbJrsqbEntity> list = baseMapper.selectList(
                new EntityWrapper<ZbJrsqbEntity>().
                        like("jrsqid", jrsqid)
        );

        return list.get(0);
    }


}

package io.admin.modules.borrow.service.impl;

import io.admin.modules.borrow.entity.ZbJrsqbEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.borrow.dao.JyJczbmxbDao;
import io.admin.modules.borrow.entity.JyJczbmxbEntity;
import io.admin.modules.borrow.service.JyJczbmxbService;


@Service("jyJczbmxbService")
public class JyJczbmxbServiceImpl extends ServiceImpl<JyJczbmxbDao, JyJczbmxbEntity> implements JyJczbmxbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JyJczbmxbEntity> page = this.selectPage(
                new Query<JyJczbmxbEntity>(params).getPage(),
                new EntityWrapper<JyJczbmxbEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<JyJczbmxbEntity> selectByJrsqidAndZt(String jrsqid, String[] zt){
        List<JyJczbmxbEntity> list = baseMapper.selectList(
                new EntityWrapper<JyJczbmxbEntity>().
                        eq("jrsqid", jrsqid).
                        in("zt", zt)
        );
        return list;
    }

}

package io.admin.modules.borrow.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.borrow.dao.JyJczbbDao;
import io.admin.modules.borrow.entity.JyJczbbEntity;
import io.admin.modules.borrow.service.JyJczbbService;


@Service("jyJczbbService")
public class JyJczbbServiceImpl extends ServiceImpl<JyJczbbDao, JyJczbbEntity> implements JyJczbbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JyJczbbEntity> page = this.selectPage(
                new Query<JyJczbbEntity>(params).getPage(),
                new EntityWrapper<JyJczbbEntity>()
        );

        return new PageUtils(page);
    }

}

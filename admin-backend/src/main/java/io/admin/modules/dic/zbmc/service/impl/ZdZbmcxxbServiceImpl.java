package io.admin.modules.dic.zbmc.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.Query;

import io.admin.modules.dic.zbmc.dao.ZdZbmcxxbDao;
import io.admin.modules.dic.zbmc.entity.ZdZbmcxxbEntity;
import io.admin.modules.dic.zbmc.service.ZdZbmcxxbService;


@Service("zdZbmcxxbService")
public class ZdZbmcxxbServiceImpl extends ServiceImpl<ZdZbmcxxbDao, ZdZbmcxxbEntity> implements ZdZbmcxxbService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZdZbmcxxbEntity> page = this.selectPage(
                new Query<ZdZbmcxxbEntity>(params).getPage(),
                new EntityWrapper<ZdZbmcxxbEntity>()
        );

        return new PageUtils(page);
    }
    @Override
    public String getXlh(String zblbid){
        List<ZdZbmcxxbEntity> list=baseMapper.selectList(new EntityWrapper<ZdZbmcxxbEntity>().
                like(StringUtils.isNotBlank(zblbid), "zbid", zblbid)
);
        String [] array = new String[list.size()]; //保存已经存在的培养方案号
        if(list.size() > 0){
            for(int i = 0;i < list.size(); i++){
                array[i] = list.get(i).getZbid();
            }
        }
        String SerialNum = createSerialNum(array, 3);// 创建后3位
        String xlh = zblbid + SerialNum;
        return xlh;
    }
    /**
     * 获得下一个流水号
     * @param array
     * @param len
     * @return
     */
    public String createSerialNum(String[] array, int len){
        int maxNum = 1; //初始化最大值为1
        String SerialNum;
        if(array.length > 0){
            for(int i = 0; i < array.length ; i++){
                String num = array[i];
                String previousSerialNum = num.substring(num.length() - len); //获得方案号号后3位
                int newNum = Integer.parseInt(previousSerialNum.replaceAll("^(0+)", ""));// 去掉前面的0
                if(newNum > maxNum){
                    maxNum = newNum;
                }
            }
            SerialNum = String.format("%0" + len + "d", (maxNum + 1)); // 数值前面加0，保存为len位
            return SerialNum;
        }else{
            SerialNum = String.format("%0" + len + "d",  1);
            return  SerialNum;
        }
    }

    @Override
    public List<ZdZbmcxxbEntity> selectByZblbid(String zblbid){
        List<ZdZbmcxxbEntity> zbmcList = baseMapper.selectList(new EntityWrapper<ZdZbmcxxbEntity>().
                like(StringUtils.isNotBlank(zblbid), "zblbid", zblbid));

        return zbmcList;
    }

}

package com.github.zuihou;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.zuihou.msgs.dao.MsgsCenterInfoMapper;
import com.github.zuihou.msgs.dto.MsgsCenterInfoPageResultDTO;
import com.github.zuihou.msgs.dto.MsgsCenterInfoQueryDTO;
import com.github.zuihou.msgs.service.MsgsCenterInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

/**
 * 消息测试类
 *
 * @author zuihou
 * @date 2019/08/02
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class MsgsCenterTest {
    @Autowired
    MsgsCenterInfoMapper msgsCenterInfoMapper;
    @Autowired
    MsgsCenterInfoService msgsCenterInfoService;

    @Test
    public void testMsgs() {
        MsgsCenterInfoQueryDTO data = new MsgsCenterInfoQueryDTO();
        data.setIsRead(true);
        data.setUserId(1L);
        data.setMsgsCenterType(null);
        IPage<MsgsCenterInfoPageResultDTO> page = msgsCenterInfoMapper.page(new Page(1, 20), data);

        page.getRecords().forEach(System.out::println);
    }


    @Test
    public void testMark() {
        msgsCenterInfoService.mark(Arrays.asList(588744392009842721L, 588746158852014433L), 1L);
    }
}

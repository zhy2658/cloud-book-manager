package com.example;

import static org.junit.Assert.assertTrue;

import com.example.entity.Torder;
import com.example.mapper.TorderMapper;
import com.example.service.TorderService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Resource
    TorderService torderService;

    @Test
    public void shouldAnswerWithTrue()
    {
        Torder torder=new Torder(18,"111",4,2,2323.3,121.3,3,new Date(),1);
        torderService.add(torder);

    }
}

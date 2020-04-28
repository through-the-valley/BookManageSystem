package com.example.demo.mapper;

import com.example.demo.model.Classleader;
import com.example.demo.model.ClassleaderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassleaderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classleader
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    long countByExample(ClassleaderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classleader
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    int deleteByExample(ClassleaderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classleader
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classleader
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    int insert(Classleader record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classleader
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    int insertSelective(Classleader record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classleader
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    List<Classleader> selectByExample(ClassleaderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classleader
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    Classleader selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classleader
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    int updateByExampleSelective(@Param("record") Classleader record, @Param("example") ClassleaderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classleader
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    int updateByExample(@Param("record") Classleader record, @Param("example") ClassleaderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classleader
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    int updateByPrimaryKeySelective(Classleader record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table classleader
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    int updateByPrimaryKey(Classleader record);
}
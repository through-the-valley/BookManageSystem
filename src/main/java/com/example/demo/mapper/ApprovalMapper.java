package com.example.demo.mapper;

import com.example.demo.model.Approval;
import com.example.demo.model.ApprovalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApprovalMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    long countByExample(ApprovalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    int deleteByExample(ApprovalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    int insert(Approval record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    int insertSelective(Approval record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    List<Approval> selectByExample(ApprovalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    Approval selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    int updateByExampleSelective(@Param("record") Approval record, @Param("example") ApprovalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    int updateByExample(@Param("record") Approval record, @Param("example") ApprovalExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    int updateByPrimaryKeySelective(Approval record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    int updateByPrimaryKey(Approval record);
}
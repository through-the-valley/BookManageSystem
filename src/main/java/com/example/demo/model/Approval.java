package com.example.demo.model;

public class Approval {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column approval.id
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column approval.book_id
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    private Integer bookId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column approval.teacher_id
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    private Integer teacherId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column approval.quantity
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    private Integer quantity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column approval.toclass
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    private String toclass;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column approval.pass
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    private Integer pass;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approval.id
     *
     * @return the value of approval.id
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approval.id
     *
     * @param id the value for approval.id
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approval.book_id
     *
     * @return the value of approval.book_id
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approval.book_id
     *
     * @param bookId the value for approval.book_id
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approval.teacher_id
     *
     * @return the value of approval.teacher_id
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approval.teacher_id
     *
     * @param teacherId the value for approval.teacher_id
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approval.quantity
     *
     * @return the value of approval.quantity
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approval.quantity
     *
     * @param quantity the value for approval.quantity
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approval.toclass
     *
     * @return the value of approval.toclass
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    public String getToclass() {
        return toclass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approval.toclass
     *
     * @param toclass the value for approval.toclass
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    public void setToclass(String toclass) {
        this.toclass = toclass == null ? null : toclass.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approval.pass
     *
     * @return the value of approval.pass
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    public Integer getPass() {
        return pass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approval.pass
     *
     * @param pass the value for approval.pass
     *
     * @mbg.generated Tue Apr 28 01:33:54 CST 2020
     */
    public void setPass(Integer pass) {
        this.pass = pass;
    }
}
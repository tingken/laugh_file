package com.quark.api.auto.bean;
/**
 * @author kingsley
 * @copyright quarktimes.com
 * @datetime 2016-07-07 15:34:21
 *
 */
public class OrdersSchedules{

    //
    public int orders_schedule_id;
    //课程日期
    public String schedule_data;
    //课程时间段
    public String schedule_hours;
    //退款状态：10-用户未申请退款，2-退款中[pending]，30-教练申请取消订单退款中[completed] 31-管理员确认退款完成[completed]，4-不能退款[unrefund]
    public String refund_status;
    //1-完成课程(finished)-20-教练确认时间(confirmed) 21-教练拒绝用户时间(refusetime)
    public String status;

    public int getOrders_schedule_id() {
        return orders_schedule_id;
    }

    public void setOrders_schedule_id(int orders_schedule_id) {
        this.orders_schedule_id = orders_schedule_id;
    }

    public String getSchedule_data() {
        return schedule_data;
    }

    public void setSchedule_data(String schedule_data) {
        this.schedule_data = schedule_data;
    }

    public String getSchedule_hours() {
        return schedule_hours;
    }

    public void setSchedule_hours(String schedule_hours) {
        this.schedule_hours = schedule_hours;
    }

    public String getRefund_status() {
        return refund_status;
    }

    public void setRefund_status(String refund_status) {
        this.refund_status = refund_status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
   //
   public ListOrdersSchedule listOrdersSchedule;

   public void setListOrdersSchedule(ListOrdersSchedule listordersschedule){
     this.listOrdersSchedule = listordersschedule;
   }
   public ListOrdersSchedule getListOrdersSchedule(){
     return this.listOrdersSchedule;
   }
}
package com.zhengze.usermanagement.mapper;

import com.zhengze.usermanagement.dao.ApprovalMessageDao;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/4/11 12:30
 */
@Mapper
public interface ApprovalMessageMapper {
    Integer insertApprovalMessage(@Param("approvalid")String approvalid, @Param("userId")String userId, @Param("approvalContent")String approvalContent, @Param("beUserid")String beUserid, @Param("createtime") Date createtime,@Param("IsPast")Integer IsPast,@Param("Reason")String Reason,@Param("goAbroad")String goAbroad,@Param("nature")String nature,@Param("goAbroadTime")Date goAbroadTime,@Param("outAbroadTime")Date outAbroadTime,@Param("username")String username);
    Integer submitApprovalMessage(@Param("approvalid")String approvalid, @Param("userId")String userId, @Param("approvalContent")String approvalContent, @Param("beUserid")String beUserid, @Param("createtime") Date createtime,@Param("IsPast")Integer IsPast,@Param("Reason")String Reason,@Param("goAbroad")String goAbroad,@Param("nature")String nature,@Param("goAbroadTime")Date goAbroadTime,@Param("outAbroadTime")Date outAbroadTime);

    Integer updateApprovalMessage(@Param("approvalid")String approvalid,@Param("beuserid")String beuserid,@Param("isPast")Integer isPast,@Param("reason")String reason,@Param("approvaltime")Date approvaltime);
    List<ApprovalMessageDao> getNotDealApprovals(@Param("beuserId")String beuserId);
    List<ApprovalMessageDao> getApprovalMessages(@Param("approvalid")String approvalid);
    List<String> getApprovalIdByUserId(@Param("userId")String userId);
    List<String> getNewApprovalIdByUserId(@Param("userId")String userId);
    List<ApprovalMessageDao> getApplyApprovalMessages();
    List<String> getAllApprovalIdByUserId(@Param("userId")String userId);
}

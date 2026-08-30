/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.seallicense.service;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Service;
import java.util.*;
@Service public class DomainDecisionService {
 public DecisionResult assess(DecisionRequest request) { if(request.approvalLevelsPassed()>request.requiredApprovalLevels())throw new IllegalArgumentException("已通过审批级数不能大于要求级数");int score=100;List<String> actions=new ArrayList<>();if(!request.sealActive()){score-=70;actions.add("冻结无效或停用印章");}if(!request.licenseValid()){score-=45;actions.add("完成关联证照续期或更换");}if(!request.requesterAuthorized()){score-=60;actions.add("阻断未授权申请人用印");}if(request.approvalLevelsPassed()<request.requiredApprovalLevels()){score-=50;actions.add("完成剩余分级审批");}if(!request.locationVerified()){score-=35;actions.add("核验印章使用地点与方式");}if(!request.evidenceAttached()){score-=30;actions.add("归档盖印文件和现场影像");}if(!request.returned()){score-=35;actions.add("催收借用印章或证照原件");}return result(score,actions,"ALLOW_AND_ARCHIVE","MANUAL_REVIEW","BLOCKED",Map.of("approvalProgress",request.approvalLevelsPassed()*100d/request.requiredApprovalLevels(),"useCount",request.useCount(),"returned",request.returned(),"evidenceAttached",request.evidenceAttached())); }
 private DecisionResult result(int raw,List<String> actions,String good,String warn,String bad,Map<String,Object> metrics) { int score=Math.max(0,Math.min(100,raw));String decision=score>=80?good:score>=50?warn:bad;return new DecisionResult(decision,score,metrics,List.copyOf(actions)); }
 private DecisionResult riskResult(int raw,List<String> actions,String good,String warn,String bad,Map<String,Object> metrics) { int score=Math.max(0,Math.min(100,raw));String decision=score>=70?bad:score>=40?warn:good;return new DecisionResult(decision,score,metrics,List.copyOf(actions)); }
 public record DecisionRequest(
        @NotBlank String applicationNo,
        @PositiveOrZero int approvalLevelsPassed,
        @Positive int requiredApprovalLevels,
        @Positive int useCount,
        boolean sealActive,
        boolean licenseValid,
        boolean requesterAuthorized,
        boolean returned,
        boolean evidenceAttached,
        boolean locationVerified) {}
 public record DecisionResult(String decision,int score,Map<String,Object> metrics,List<String> actions) {}
}

/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.seallicense.domain;
import org.springframework.stereotype.Component;
import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Component
public class DomainCatalog {
    private final Map<String, WorkflowAction> actions = new LinkedHashMap<>();
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public DomainCatalog() {
        actions.put("APPLY", new WorkflowAction("APPLY", "提交用印申请", List.of("草稿"), "待审批", "OPERATOR"));
        actions.put("APPROVE", new WorkflowAction("APPROVE", "批准用印授权", List.of("待审批"), "待执行", "ADMIN"));
        actions.put("COMPLETE", new WorkflowAction("COMPLETE", "确认用印归档", List.of("待执行"), "已归档", "ADMIN"));
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String systemName() { return "知华科技企业印章证照与资质管理系统"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String scene() { return "印章台账、用印申请、分级审批、智能印章、证照、资质、借还、年审、到期预警与审计"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String initialStatus() { return "草稿"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String partyLabel() { return "印章/证照/资质"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String amountLabel() { return "关联事项金额"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String quantityLabel() { return "使用次数"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String dueLabel() { return "归还或续期期限"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<ModuleDefinition> modules() { return List.of(
            new ModuleDefinition("SEAL_MASTER", "印章台账", "维护实体印章、电子印章、保管人、状态和适用范围"),
            new ModuleDefinition("SEAL_REQUEST", "用印申请", "登记文件、份数、事由、金额、相对方和使用方式"),
            new ModuleDefinition("APPROVAL", "分级审批", "按印章、金额、合同类型和风险执行多级审批"),
            new ModuleDefinition("SEAL_EXECUTION", "用印执行", "核验授权、记录盖印、影像、经办人、时间和设备"),
            new ModuleDefinition("LICENSE", "证照管理", "管理营业执照、许可证、登记证和原件保管"),
            new ModuleDefinition("QUALIFICATION", "企业资质", "维护资质等级、适用业务、发证机关和有效期"),
            new ModuleDefinition("BORROW_RETURN", "借用归还", "登记借用人、用途、期限、签收、归还和逾期"),
            new ModuleDefinition("RENEWAL", "年审与续期", "形成到期计划、材料清单、办理进度和新证归档"),
            new ModuleDefinition("AUDIT", "风险与审计", "识别越权、异常地点、逾期未还和未授权使用")
        ); }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Map<String, WorkflowAction> actions() { return Collections.unmodifiableMap(actions); }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record ModuleDefinition(String code,String name,String description) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record WorkflowAction(String code,String label,List<String> from,String to,String requiredRole) {}
}

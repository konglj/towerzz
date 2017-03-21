package com.sdtower.common.wxmsg;

public class WXInfo {
	
	public static final String appid = "wx3f9dfbd0aafc3a80_AA";
	public static final String secret = "60d150728db169c81a65cf677178cf35";
	
	public static final String topcolor="#FF0000";

	public static final String taskTemplateid="vPKdGLLYeNb3BaknmtF8eeiWLC-p8q74JQw1Hi9ajOE";//
	public static final String taskUrl="%s/manager/get_service.html?adminselectstate=1&msgorderid=%d&openid=%s";
	
	public static final String moneyTemplateid="hFx3VPYdhaQa32WpjC5EWMbXDe3TthiaE8PHOMitZAg";//
	public static final String moneyUrl="%s/money/mymoney.html?openid=%s";
	
	public static final String sh_order_successTemplateid="LSa3qOyqIG4rkG_ESw_mI-0_uKfVmjfBQE82FZTSMx8";//
	public static final String sh_order_successUrl="%s/order/order_list.html?msgorderid=%d&openid=%s";
	
	public static final String dk_successTemplateid="7JwufYCjihAW3s9FxeC49j1fi2DNVz8aDJ0wOmBZyJc";//
	public static final String dk_successUrl="%s/money/tx_record_list.html?openid=%s&txid=%s";
	
	public static final String send_tw_noticeTemplateid="vPKdGLLYeNb3BaknmtF8eeiWLC-p8q74JQw1Hi9ajOE";
	public static final String send_tw_noticeUrl="%s/twnotice/noticeinfo.html?noticeid=%d";
	
	public static final String dk_apply_successTemplateid="vPKdGLLYeNb3BaknmtF8eeiWLC-p8q74JQw1Hi9ajOE";
	public static final String dk_apply_successUrl="%s/money/mymoney.html?openid=%s";
	
	public static final String sh_order_yqTemplateid="vPKdGLLYeNb3BaknmtF8eeiWLC-p8q74JQw1Hi9ajOE";
	public static final String sh_order_yqUrl="%s/order/order_list.html?msgorderid=%d&openid=%s";
	
	  // 凭证获取（GET）
	public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    
	public final static String send_url="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

	
	
	
	//----------消息内容-------------------
	
	
	//++++++++++++++++任务消息内容+++++++++++++++
	public static final String task_add_order_title="您有新的谈址订单，请注意及时审核！！！";
	
	public static final String task_add_order_name="审核谈址订单";
	
	public static final String task_add_order_type="加急消息";
	
	public static final String task_add_order_remark="";
	
	
	public static final String task_yq_order_title="您有新的延期申请，请注意及时审核！！！";
	
	public static final String task_yq_order_name="审核延期申请";
	
	public static final String task_yq_order_type="加急消息";
	
	public static final String task_yq_order_remark="";
	
	//++++++++++++++++资金变动消息内容++++++++++++++++++++++++++
    public static final String money_add_title="您的账户有新增可提现金额！！！";
	
	public static final String  money_add_type_first="预付款到账";
	
	public static final String  money_add_type_end="尾款到账";
	
	public static final String money_add_remark="";
	
	  public static final String money_dk_title="您好，您的提现已经打款，收款账户%s,请及时确认收款。";
		
	public static final String  money_dk_qz_type="银行转账";
	
	public static final String  money_dk_lxfs="如有疑问请直接联系公众号客服 towerservice感谢您的使用";
	
	public static final String  money_dk_remark="";
	
	
	
	
	//++++++++++++++++订单审核结果通知++++++++++++++++++++++++++
	public static final String order_sh_success="尊敬的用户您好，您的订单已审核通过！！！";
	
	public static final String order_sh_success_remark="请单击查看详情，继续关注!!!";
	
	
	//++++++++++++++++公告消息通知内容++++++++++++++++++++++++++
	public static final String notice_add_title="尊敬的用户您好，您有新的公告消息!!!";
	
	public static final String notice_add_remark="点击详情，查看详细公告";
	
	//++++++++++++++++延期申请审核通知内容++++++++++++++++++++++++++
	
	public static final String order_sh_yq_success_title="尊敬的用户您好，您的延期申请已通过!!!";
	
	public static final String order_sh_yq_error_title="尊敬的用户您好，您的延期申请已失败!!!";
	
	public static final String order_sh_yq_success_content="%s的延期已经审核成功。";
	
	public static final String order_sh_yq_success_remark="点击详情，查看信息";
	
	public static final String order_sh_yq_error_content="%s的延期已经审核失败。";
	
	public static final String order_sh_yq_error_remark="点击详情，查看信息";
	
	//++++++++++++打款申请失败通知内容+++++++++++++++++++++
	
	public static final String money_dk_apply_title_error="尊敬的用户您好，您的提现失败，提现金额已返到您的钱包中，请核实您的收款账户信息，再次提现!!!";
	
	
	public static final String  money_dk_apply_content="提现编号%s，提现失败。";
	
	public static final String  money_dk_apply_remark="";
	

	
	
	
	
}

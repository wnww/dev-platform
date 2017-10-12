package com.yhhl.common;

/**
 * 常量
 * @author goldocean
 *
 */
public class Constants {

	/**
	 * 订单状态枚举
	 * @author goldocean
	 *
	 */
	public enum OrderStatus {
		/**
		 * 创建订单
		 */
		S11(11, "创建订单"),
		/**
		 * 支付成功
		 */
		S21(21, "支付成功"),
		/**
		 * 已出库
		 */
		S31(31, "已出库"),
		/**
		 * 已发货
		 */
		S41(41, "已发货"),
		/**
		 * 已收货
		 */
		S51(51, "已收货"),
		/**
		 * 已取消
		 */
		S61(61, "已取消"),
		/**
		 * 已退货
		 */
		S71(71, "已退货");

		// 状态值
		private int status;
		// 状态中文描述
		private String value;

		// 构造方法
		private OrderStatus(int status, String value) {
			this.status = status;
			this.value = value;
		}
		
		// 普通方法
        public static String getValue(int status) {
            for (OrderStatus os : OrderStatus.values()) {
                if (os.getStatus()==status) {
                    return os.getValue();
                }
            }
            return "未知状态："+status;
        }

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return this.status+" : "+this.value;
		}
	}
	
	public final static String TRUE = "1";
	public final static String FALSE = "0";
	
	public final static String FRONT = "front";
	public final static String BACK = "back";
}

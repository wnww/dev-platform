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
		S11(11, "创建订单"),
		S21(21, "支付成功"),
		S31(31, "已出库"),
		S41(41, "已发货"),
		S51(51, "已收货");

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
}
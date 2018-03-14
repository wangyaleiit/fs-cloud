package com.fs.utils;

public class PayConstant {

    public enum PayStatus {
        /**
         * 未支付
         */
    	UNPAY("0"),
        /**
         * 待支付
         */
        PAYING("1"),
        /**
         * 已支付
         */
        PAID("2"),
        /**
         * 已退款
         */
    	REFUND("3");
    	
		private String type;

	    private PayStatus(String type) {
	        this.type = type;
	    }

	    @Override
	    public String toString() {
	        return this.type.toString();
	    }
    }
    
    public enum SingleStatus {
        /**
         * 未接单
         */
    	UNSINGlE("0"),
        /**
         * 已接单
         */
    	SINGLED("1");
    	
		private String type;

	    private SingleStatus(String type) {
	        this.type = type;
	    }

	    @Override
	    public String toString() {
	        return this.type.toString();
	    }
    }
}

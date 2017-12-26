package com.bocom.enums;

public enum BizCategoryPoliceEnum {
    BizCategory_1(10, "禁毒应用"), BizCategory_2(11, "治安应用"), BizCategory_3(12, "交通应用"), BizCategory_4(13, "情指应用"), BizCategory_5(
            14, "刑侦应用"), BizCategory_6(15, "经侦应用"), BizCategory_7(16, "其他业务应用");
	//消防应用
//    BizCategory_1(10, "防火应用"), BizCategory_2(11, "灭火救援应用"), BizCategory_3(12, "消防宣传应用"), BizCategory_4(13, "办公应用"), BizCategory_5(
//			14, "训练应用"), BizCategory_6(15, "消防教育研究应用"), BizCategory_7(16, "其他业务应用"), BizCategory_8(17, "消防装备应用"), BizCategory_9(18, "消防医护应用"), BizCategory_10(19, "火调应用");
	
	private Integer code;
	private String name;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private BizCategoryPoliceEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	private BizCategoryPoliceEnum() {
	}

	public static String getNameByCode(String code) {
		try {
		    if (code.equals("10")) {
		        return "禁毒应用";
		    } else if (code.equals("11")) {
		        return "治安应用";
		    } else if (code.equals("12")) {
		        return "交通应用";
		    } else if (code.equals("13")) {
		        return "情指应用";
		    } else if (code.equals("14")) {
		        return "刑侦应用";
		    } else if (code.equals("15")) {
		        return "经侦应用";
		    } else if (code.equals("16")) {
		        return "其他业务应用";
		    } else {
		        return null;
		    }
//			if (code.equals("10")) {
//				return "防火应用";
//			} else if (code.equals("11")) {
//				return "灭火救援应用";
//			} else if (code.equals("12")) {
//				return "消防宣传应用";
//			} else if (code.equals("13")) {
//				return "办公应用";
//			} else if (code.equals("14")) {
//				return "训练应用";
//			} else if (code.equals("15")) {
//				return "消防教育研究应用";
//			}  else if (code.equals("17")) {
//                return "消防装备应用";
//            } else if (code.equals("18")) {
//                return "消防医护应用";
//            } else if (code.equals("19")) {
//                return "火调应用";
//            }else if (code.equals("16")) {
//				return "其他业务应用";
//			} else {
//				return null;
//			}
		} catch (NullPointerException e) {
			return null;
		}
	}
}

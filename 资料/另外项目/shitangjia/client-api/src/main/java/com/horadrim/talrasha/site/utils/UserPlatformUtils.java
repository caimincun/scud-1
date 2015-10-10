package com.horadrim.talrasha.site.utils;


import com.horadrim.talrasha.common.util.StringUtils;

public final class UserPlatformUtils {
    /**手机浏览器的User-Agent里的关键词*/
    private static String[] mobileUserAgents = new String[]{
	    "nokia",//诺基亚，有山寨机也写这个的，总还算是手机，Mozilla/5.0 (Nokia5800 XpressMusic)UC AppleWebkit(like Gecko) Safari/530
	    "symbian",//塞班系统的，
	    "windows ce",//Windows CE，Mozilla/4.0 (compatible; MSIE 6.0; Windows CE; IEMobile 7.11)
	    "windows phone", // Windows phone 7/8
	    "iphone",//iPhone是否也转wap？不管它，先区分出来再说。Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_1 like Mac OS X; zh-cn) AppleWebKit/532.9 (KHTML like Gecko) Mobile/8B117
	    "ipad",//iPad的ua，Mozilla/5.0 (iPad; U; CPU OS 3_2 like Mac OS X; zh-cn) AppleWebKit/531.21.10 (KHTML like Gecko) Version/4.0.4 Mobile/7B367 Safari/531.21.10
	    "ipod", // iPod
	    "android",//Android是否也转wap？Mozilla/5.0 (Linux; U; Android 2.1-update1; zh-cn; XT800 Build/TITA_M2_16.22.7) AppleWebKit/530.17 (KHTML like Gecko) Version/4.0 Mobile Safari/530.17
	    "blackberry",//BlackBerry8310/2.7.0.106-4.5.0.182
	    "webos",//palm手机，Mozilla/5.0 (webOS/1.4.5; U; zh-CN) AppleWebKit/532.2 (KHTML like Gecko) Version/1.0 Safari/532.2 Pre/1.0
	    "iemobile",//Windows CE手机自带浏览器，
    };

    /**
    * 根据当前请求的特征，判断该请求是否来自手机终端，主要检测特殊的头信息，以及user-Agent这个header
    * @param
    * @return 如果命中手机特征规则，则返回对应的特征字符串
    */
    public static boolean isMobileDevice(String userAgent){
        if (StringUtils.isBlank(userAgent)) {
            return false;
        }

        userAgent = userAgent.toLowerCase();
        for (String key : mobileUserAgents) {
        	if (userAgent.contains(key)) {
        		return true;
        	}
        }

        return false;
    }
}

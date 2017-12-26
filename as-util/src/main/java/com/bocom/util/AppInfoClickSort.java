package com.bocom.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;

import com.bocom.domain.ClickRate;
import com.bocom.dto.AppInfoBase;

/*****
 * 类名称：AppInfoClickSort
 * 类描述：按照点击量进行排序
 * 创建人：donghongguang
 * 创建时间：2017年4月25日 下午3:31:15
 * 修改人：
 * 修改时间：
 * @version 1.0.0
 */
public class AppInfoClickSort
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
//    @Value("${fastDFS.http.url}")
//    private static String fastDfsUrl;
    /*****
     * 功能：按照点击量进行排序
     * 创建人：donghongguang
     * 创建时间：2017年4月25日 下午3:33:29
     * @param 
     * @return 
     * @version 1.0.0
     * @param <T>
     */
    public static <T> List<AppInfoBase> sortAppInfo(List<T> objList,List<ClickRate> clickRateList){
        /**
         * 1.遍历点击量
         * 2.遍历object，查找是否相同，相同的赋值，移除list
         * 3.返回
         */
        List<AppInfoBase> appInfoBaseList = new ArrayList<AppInfoBase>() ;
        
        try
        {
           for(ClickRate clickRate : clickRateList){
                String appId = clickRate.getAppId();
                for(T obj : objList){
                    AppInfoBase appInfoBase = new AppInfoBase();
                    BeanUtils.copyProperties(obj, appInfoBase);
                    if(appInfoBase != null && appInfoBase.getAppId().equals(appId)){
                        appInfoBase.setClikeRate(clickRate.getClickRate());
                        appInfoBaseList.add(appInfoBase);
                        objList.remove(obj);
                        break ;
                    }
                }
            }
           for(Object obj : objList){
               AppInfoBase appInfoBase = new AppInfoBase();
               BeanUtils.copyProperties(obj, appInfoBase);
//               if(null != appInfoBase.getLogoApp() && !appInfoBase.getLogoApp().equals("")){
//                   if(appInfoBase.getLogoApp().startsWith("http://")){
//                       
//                   }else{
//                       appInfoBase.setLogoApp(fastDfsUrl + appInfoBase.getLogoApp());
//                   }
//               }
               appInfoBase.setClikeRate(0L);
               appInfoBaseList.add(appInfoBase);

           }
        }
        catch (Exception e)
        {
            return null;
        }
        return appInfoBaseList;
    }
}

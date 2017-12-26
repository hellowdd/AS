package com.bocom.service;

import com.bocom.domain.ResponseVo;

/*****
 * 类名称：AppRoleService
 * 类描述：我的应用及角色服务接口
 * 创建人：donghongguang
 * 创建时间：2017年4月25日 上午11:47:40
 * 修改人：
 * 修改时间：
 * @version 1.0.0
 */
public interface AppRoleService
{
    
    /*****
     * 功能：获取所有我的应用
     * 创建人：donghongguang
     * 创建时间：2017年4月25日 下午1:18:01
     * @param 
     * @return 
     * @version 1.0.0
     */
    public ResponseVo getMyApp();
    
    /*****
     * 功能：获取某一个应用所有的角色
     * 创建人：donghongguang
     * 创建时间：2017年4月25日 下午1:18:03
     * @param 
     * @return 
     * @version 1.0.0
     */
    public ResponseVo getAppAllRole(String appId,String appVersion);
}
